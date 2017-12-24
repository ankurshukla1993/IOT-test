package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.DiskStorage.DiskDumpInfo;
import com.facebook.cache.disk.DiskStorage.DiskDumpInfoEntry;
import com.facebook.cache.disk.DiskStorage.Entry;
import com.facebook.cache.disk.DiskStorage.Inserter;
import com.facebook.common.file.FileTree;
import com.facebook.common.file.FileTreeVisitor;
import com.facebook.common.file.FileUtils;
import com.facebook.common.file.FileUtils.CreateDirectoryException;
import com.facebook.common.file.FileUtils.ParentDirNotFoundException;
import com.facebook.common.file.FileUtils.RenameException;
import com.facebook.common.internal.CountingOutputStream;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.time.Clock;
import com.facebook.common.time.SystemClock;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DefaultDiskStorage implements DiskStorage {
    private static final String CONTENT_FILE_EXTENSION = ".cnt";
    private static final String DEFAULT_DISK_STORAGE_VERSION_PREFIX = "v2";
    private static final int SHARDING_BUCKET_COUNT = 100;
    private static final Class<?> TAG = DefaultDiskStorage.class;
    private static final String TEMP_FILE_EXTENSION = ".tmp";
    static final long TEMP_FILE_LIFETIME_MS = TimeUnit.MINUTES.toMillis(30);
    private final CacheErrorLogger mCacheErrorLogger;
    private final Clock mClock = SystemClock.get();
    private final File mRootDirectory;
    private final File mVersionDirectory;

    private class EntriesCollector implements FileTreeVisitor {
        private final List<Entry> result;

        private EntriesCollector() {
            this.result = new ArrayList();
        }

        public void preVisitDirectory(File directory) {
        }

        public void visitFile(File file) {
            FileInfo info = DefaultDiskStorage.this.getShardFileInfo(file);
            if (info != null && info.type == FileType.CONTENT) {
                this.result.add(new EntryImpl(info.resourceId, file));
            }
        }

        public void postVisitDirectory(File directory) {
        }

        public List<Entry> getEntries() {
            return Collections.unmodifiableList(this.result);
        }
    }

    @VisibleForTesting
    static class EntryImpl implements Entry {
        private final String id;
        private final FileBinaryResource resource;
        private long size;
        private long timestamp;

        private EntryImpl(String id, File cachedFile) {
            Preconditions.checkNotNull(cachedFile);
            this.id = (String) Preconditions.checkNotNull(id);
            this.resource = FileBinaryResource.createOrNull(cachedFile);
            this.size = -1;
            this.timestamp = -1;
        }

        public String getId() {
            return this.id;
        }

        public long getTimestamp() {
            if (this.timestamp < 0) {
                this.timestamp = this.resource.getFile().lastModified();
            }
            return this.timestamp;
        }

        public FileBinaryResource getResource() {
            return this.resource;
        }

        public long getSize() {
            if (this.size < 0) {
                this.size = this.resource.size();
            }
            return this.size;
        }
    }

    private enum FileType {
        CONTENT(DefaultDiskStorage.CONTENT_FILE_EXTENSION),
        TEMP(DefaultDiskStorage.TEMP_FILE_EXTENSION);
        
        public final String extension;

        private FileType(String extension) {
            this.extension = extension;
        }

        public static FileType fromExtension(String extension) {
            if (DefaultDiskStorage.CONTENT_FILE_EXTENSION.equals(extension)) {
                return CONTENT;
            }
            if (DefaultDiskStorage.TEMP_FILE_EXTENSION.equals(extension)) {
                return TEMP;
            }
            return null;
        }
    }

    private static class IncompleteFileException extends IOException {
        public final long actual;
        public final long expected;

        public IncompleteFileException(long expected, long actual) {
            super("File was not written completely. Expected: " + expected + ", found: " + actual);
            this.expected = expected;
            this.actual = actual;
        }
    }

    @VisibleForTesting
    class InserterImpl implements Inserter {
        private final String mResourceId;
        @VisibleForTesting
        final File mTemporaryFile;

        public InserterImpl(String resourceId, File temporaryFile) {
            this.mResourceId = resourceId;
            this.mTemporaryFile = temporaryFile;
        }

        public void writeData(WriterCallback callback, Object debugInfo) throws IOException {
            try {
                FileOutputStream fileStream = new FileOutputStream(this.mTemporaryFile);
                try {
                    CountingOutputStream countingStream = new CountingOutputStream(fileStream);
                    callback.write(countingStream);
                    countingStream.flush();
                    long length = countingStream.getCount();
                    if (this.mTemporaryFile.length() != length) {
                        throw new IncompleteFileException(length, this.mTemporaryFile.length());
                    }
                } finally {
                    fileStream.close();
                }
            } catch (FileNotFoundException fne) {
                DefaultDiskStorage.this.mCacheErrorLogger.logError(CacheErrorCategory.WRITE_UPDATE_FILE_NOT_FOUND, DefaultDiskStorage.TAG, "updateResource", fne);
                throw fne;
            }
        }

        public BinaryResource commit(Object debugInfo) throws IOException {
            File targetFile = DefaultDiskStorage.this.getContentFileFor(this.mResourceId);
            try {
                FileUtils.rename(this.mTemporaryFile, targetFile);
                if (targetFile.exists()) {
                    targetFile.setLastModified(DefaultDiskStorage.this.mClock.now());
                }
                return FileBinaryResource.createOrNull(targetFile);
            } catch (RenameException re) {
                CacheErrorCategory category;
                Throwable cause = re.getCause();
                if (cause == null) {
                    category = CacheErrorCategory.WRITE_RENAME_FILE_OTHER;
                } else if (cause instanceof ParentDirNotFoundException) {
                    category = CacheErrorCategory.WRITE_RENAME_FILE_TEMPFILE_PARENT_NOT_FOUND;
                } else if (cause instanceof FileNotFoundException) {
                    category = CacheErrorCategory.WRITE_RENAME_FILE_TEMPFILE_NOT_FOUND;
                } else {
                    category = CacheErrorCategory.WRITE_RENAME_FILE_OTHER;
                }
                DefaultDiskStorage.this.mCacheErrorLogger.logError(category, DefaultDiskStorage.TAG, "commit", re);
                throw re;
            }
        }

        public boolean cleanUp() {
            return !this.mTemporaryFile.exists() || this.mTemporaryFile.delete();
        }
    }

    private class PurgingVisitor implements FileTreeVisitor {
        private boolean insideBaseDirectory;

        private PurgingVisitor() {
        }

        public void preVisitDirectory(File directory) {
            if (!this.insideBaseDirectory && directory.equals(DefaultDiskStorage.this.mVersionDirectory)) {
                this.insideBaseDirectory = true;
            }
        }

        public void visitFile(File file) {
            if (!this.insideBaseDirectory || !isExpectedFile(file)) {
                file.delete();
            }
        }

        public void postVisitDirectory(File directory) {
            if (!(DefaultDiskStorage.this.mRootDirectory.equals(directory) || this.insideBaseDirectory)) {
                directory.delete();
            }
            if (this.insideBaseDirectory && directory.equals(DefaultDiskStorage.this.mVersionDirectory)) {
                this.insideBaseDirectory = false;
            }
        }

        private boolean isExpectedFile(File file) {
            boolean z = false;
            FileInfo info = DefaultDiskStorage.this.getShardFileInfo(file);
            if (info == null) {
                return false;
            }
            if (info.type == FileType.TEMP) {
                return isRecentFile(file);
            }
            if (info.type == FileType.CONTENT) {
                z = true;
            }
            Preconditions.checkState(z);
            return true;
        }

        private boolean isRecentFile(File file) {
            return file.lastModified() > DefaultDiskStorage.this.mClock.now() - DefaultDiskStorage.TEMP_FILE_LIFETIME_MS;
        }
    }

    public DefaultDiskStorage(File rootDirectory, int version, CacheErrorLogger cacheErrorLogger) {
        Preconditions.checkNotNull(rootDirectory);
        this.mRootDirectory = rootDirectory;
        this.mVersionDirectory = new File(this.mRootDirectory, getVersionSubdirectoryName(version));
        this.mCacheErrorLogger = cacheErrorLogger;
        recreateDirectoryIfVersionChanges();
    }

    @VisibleForTesting
    static String getVersionSubdirectoryName(int version) {
        return String.format((Locale) null, "%s.ols%d.%d", new Object[]{DEFAULT_DISK_STORAGE_VERSION_PREFIX, Integer.valueOf(100), Integer.valueOf(version)});
    }

    public boolean isEnabled() {
        return true;
    }

    private void recreateDirectoryIfVersionChanges() {
        boolean recreateBase = false;
        if (!this.mRootDirectory.exists()) {
            recreateBase = true;
        } else if (!this.mVersionDirectory.exists()) {
            recreateBase = true;
            FileTree.deleteRecursively(this.mRootDirectory);
        }
        if (recreateBase) {
            try {
                FileUtils.mkdirs(this.mVersionDirectory);
            } catch (CreateDirectoryException e) {
                this.mCacheErrorLogger.logError(CacheErrorCategory.WRITE_CREATE_DIR, TAG, "version directory could not be created: " + this.mVersionDirectory, null);
            }
        }
    }

    @VisibleForTesting
    File getContentFileFor(String resourceId) {
        return new File(getFilename(resourceId));
    }

    private String getSubdirectoryPath(String resourceId) {
        return this.mVersionDirectory + File.separator + String.valueOf(Math.abs(resourceId.hashCode() % 100));
    }

    private File getSubdirectory(String resourceId) {
        return new File(getSubdirectoryPath(resourceId));
    }

    public void purgeUnexpectedResources() {
        FileTree.walkFileTree(this.mRootDirectory, new PurgingVisitor());
    }

    private void mkdirs(File directory, String message) throws IOException {
        try {
            FileUtils.mkdirs(directory);
        } catch (CreateDirectoryException cde) {
            this.mCacheErrorLogger.logError(CacheErrorCategory.WRITE_CREATE_DIR, TAG, message, cde);
            throw cde;
        }
    }

    public Inserter insert(String resourceId, Object debugInfo) throws IOException {
        FileInfo info = new FileInfo(FileType.TEMP, resourceId, null);
        File parent = getSubdirectory(info.resourceId);
        if (!parent.exists()) {
            mkdirs(parent, "insert");
        }
        try {
            return new InserterImpl(resourceId, info.createTempFile(parent));
        } catch (IOException ioe) {
            this.mCacheErrorLogger.logError(CacheErrorCategory.WRITE_CREATE_TEMPFILE, TAG, "insert", ioe);
            throw ioe;
        }
    }

    public BinaryResource getResource(String resourceId, Object debugInfo) {
        File file = getContentFileFor(resourceId);
        if (!file.exists()) {
            return null;
        }
        file.setLastModified(this.mClock.now());
        return FileBinaryResource.createOrNull(file);
    }

    private String getFilename(String resourceId) {
        FileInfo fileInfo = new FileInfo(FileType.CONTENT, resourceId, null);
        return fileInfo.toPath(getSubdirectoryPath(fileInfo.resourceId));
    }

    public boolean contains(String resourceId, Object debugInfo) {
        return query(resourceId, false);
    }

    public boolean touch(String resourceId, Object debugInfo) {
        return query(resourceId, true);
    }

    private boolean query(String resourceId, boolean touch) {
        File contentFile = getContentFileFor(resourceId);
        boolean exists = contentFile.exists();
        if (touch && exists) {
            contentFile.setLastModified(this.mClock.now());
        }
        return exists;
    }

    public long remove(Entry entry) {
        return doRemove(((EntryImpl) entry).getResource().getFile());
    }

    public long remove(String resourceId) {
        return doRemove(getContentFileFor(resourceId));
    }

    private long doRemove(File contentFile) {
        if (!contentFile.exists()) {
            return 0;
        }
        return !contentFile.delete() ? -1 : contentFile.length();
    }

    public void clearAll() {
        FileTree.deleteContents(this.mRootDirectory);
    }

    public DiskDumpInfo getDumpInfo() throws IOException {
        List<Entry> entries = getEntries();
        DiskDumpInfo dumpInfo = new DiskDumpInfo();
        for (Entry entry : entries) {
            DiskDumpInfoEntry infoEntry = dumpCacheEntry(entry);
            String type = infoEntry.type;
            if (!dumpInfo.typeCounts.containsKey(type)) {
                dumpInfo.typeCounts.put(type, Integer.valueOf(0));
            }
            dumpInfo.typeCounts.put(type, Integer.valueOf(((Integer) dumpInfo.typeCounts.get(type)).intValue() + 1));
            dumpInfo.entries.add(infoEntry);
        }
        return dumpInfo;
    }

    private DiskDumpInfoEntry dumpCacheEntry(Entry entry) throws IOException {
        EntryImpl entryImpl = (EntryImpl) entry;
        String firstBits = "";
        byte[] bytes = entryImpl.getResource().read();
        String type = typeOfBytes(bytes);
        if (type.equals("undefined") && bytes.length >= 4) {
            firstBits = String.format((Locale) null, "0x%02X 0x%02X 0x%02X 0x%02X", new Object[]{Byte.valueOf(bytes[0]), Byte.valueOf(bytes[1]), Byte.valueOf(bytes[2]), Byte.valueOf(bytes[3])});
        }
        return new DiskDumpInfoEntry(entryImpl.getResource().getFile().getPath(), type, (float) entryImpl.getSize(), firstBits);
    }

    private String typeOfBytes(byte[] bytes) {
        if (bytes.length >= 2) {
            if (bytes[0] == (byte) -1 && bytes[1] == (byte) -40) {
                return "jpg";
            }
            if (bytes[0] == (byte) -119 && bytes[1] == (byte) 80) {
                return "png";
            }
            if (bytes[0] == (byte) 82 && bytes[1] == (byte) 73) {
                return "webp";
            }
            if (bytes[0] == (byte) 71 && bytes[1] == (byte) 73) {
                return "gif";
            }
        }
        return "undefined";
    }

    public List<Entry> getEntries() throws IOException {
        EntriesCollector collector = new EntriesCollector();
        FileTree.walkFileTree(this.mVersionDirectory, collector);
        return collector.getEntries();
    }

    private FileInfo getShardFileInfo(File file) {
        FileInfo info = FileInfo.fromFile(file);
        if (info == null) {
            return null;
        }
        if (!getSubdirectory(info.resourceId).equals(file.getParentFile())) {
            info = null;
        }
        return info;
    }
}
