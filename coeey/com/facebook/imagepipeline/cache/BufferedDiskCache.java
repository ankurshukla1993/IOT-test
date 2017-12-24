package com.facebook.imagepipeline.cache;

import bolts.Task;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.memory.PooledByteStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public class BufferedDiskCache {
    private static final Class<?> TAG = BufferedDiskCache.class;
    private final FileCache mFileCache;
    private final ImageCacheStatsTracker mImageCacheStatsTracker;
    private final PooledByteBufferFactory mPooledByteBufferFactory;
    private final PooledByteStreams mPooledByteStreams;
    private final Executor mReadExecutor;
    private final StagingArea mStagingArea = StagingArea.getInstance();
    private final Executor mWriteExecutor;

    class C11535 implements Callable<Void> {
        C11535() {
        }

        public Void call() throws Exception {
            BufferedDiskCache.this.mStagingArea.clearAll();
            BufferedDiskCache.this.mFileCache.clearAll();
            return null;
        }
    }

    public BufferedDiskCache(FileCache fileCache, PooledByteBufferFactory pooledByteBufferFactory, PooledByteStreams pooledByteStreams, Executor readExecutor, Executor writeExecutor, ImageCacheStatsTracker imageCacheStatsTracker) {
        this.mFileCache = fileCache;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mPooledByteStreams = pooledByteStreams;
        this.mReadExecutor = readExecutor;
        this.mWriteExecutor = writeExecutor;
        this.mImageCacheStatsTracker = imageCacheStatsTracker;
    }

    public boolean containsSync(CacheKey key) {
        return this.mStagingArea.containsKey(key) || this.mFileCache.hasKeySync(key);
    }

    public Task<Boolean> contains(CacheKey key) {
        if (containsSync(key)) {
            return Task.forResult(Boolean.valueOf(true));
        }
        return containsAsync(key);
    }

    private Task<Boolean> containsAsync(final CacheKey key) {
        try {
            return Task.call(new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    return Boolean.valueOf(BufferedDiskCache.this.checkInStagingAreaAndFileCache(key));
                }
            }, this.mReadExecutor);
        } catch (Throwable exception) {
            FLog.m150w(TAG, exception, "Failed to schedule disk-cache read for %s", key.toString());
            return Task.forError(exception);
        }
    }

    public boolean diskCheckSync(CacheKey key) {
        if (containsSync(key)) {
            return true;
        }
        return checkInStagingAreaAndFileCache(key);
    }

    public Task<EncodedImage> get(CacheKey key, AtomicBoolean isCancelled) {
        EncodedImage pinnedImage = this.mStagingArea.get(key);
        if (pinnedImage != null) {
            return foundPinnedImage(key, pinnedImage);
        }
        return getAsync(key, isCancelled);
    }

    private boolean checkInStagingAreaAndFileCache(CacheKey key) {
        EncodedImage result = this.mStagingArea.get(key);
        if (result != null) {
            result.close();
            FLog.m132v(TAG, "Found image for %s in staging area", key.toString());
            this.mImageCacheStatsTracker.onStagingAreaHit();
            return true;
        }
        FLog.m132v(TAG, "Did not find image for %s in staging area", key.toString());
        this.mImageCacheStatsTracker.onStagingAreaMiss();
        try {
            return this.mFileCache.hasKey(key);
        } catch (Exception e) {
            return false;
        }
    }

    private Task<EncodedImage> getAsync(final CacheKey key, final AtomicBoolean isCancelled) {
        try {
            return Task.call(new Callable<EncodedImage>() {
                public EncodedImage call() throws Exception {
                    if (isCancelled.get()) {
                        throw new CancellationException();
                    }
                    EncodedImage result = BufferedDiskCache.this.mStagingArea.get(key);
                    if (result != null) {
                        FLog.m132v(BufferedDiskCache.TAG, "Found image for %s in staging area", key.toString());
                        BufferedDiskCache.this.mImageCacheStatsTracker.onStagingAreaHit();
                    } else {
                        FLog.m132v(BufferedDiskCache.TAG, "Did not find image for %s in staging area", key.toString());
                        BufferedDiskCache.this.mImageCacheStatsTracker.onStagingAreaMiss();
                        CloseableReference<PooledByteBuffer> ref;
                        try {
                            ref = CloseableReference.of(BufferedDiskCache.this.readFromDiskCache(key));
                            EncodedImage result2 = new EncodedImage(ref);
                            try {
                                CloseableReference.closeSafely(ref);
                                result = result2;
                            } catch (Exception e) {
                                result = result2;
                                return null;
                            }
                        } catch (Exception e2) {
                            return null;
                        } catch (Throwable th) {
                            CloseableReference.closeSafely(ref);
                        }
                    }
                    if (!Thread.interrupted()) {
                        return result;
                    }
                    FLog.m131v(BufferedDiskCache.TAG, "Host thread was interrupted, decreasing reference count");
                    if (result != null) {
                        result.close();
                    }
                    throw new InterruptedException();
                }
            }, this.mReadExecutor);
        } catch (Throwable exception) {
            FLog.m150w(TAG, exception, "Failed to schedule disk-cache read for %s", key.toString());
            return Task.forError(exception);
        }
    }

    public void put(final CacheKey key, EncodedImage encodedImage) {
        Preconditions.checkNotNull(key);
        Preconditions.checkArgument(EncodedImage.isValid(encodedImage));
        this.mStagingArea.put(key, encodedImage);
        final EncodedImage finalEncodedImage = EncodedImage.cloneOrNull(encodedImage);
        try {
            this.mWriteExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        BufferedDiskCache.this.writeToDiskCache(key, finalEncodedImage);
                    } finally {
                        BufferedDiskCache.this.mStagingArea.remove(key, finalEncodedImage);
                        EncodedImage.closeSafely(finalEncodedImage);
                    }
                }
            });
        } catch (Throwable exception) {
            FLog.m150w(TAG, exception, "Failed to schedule disk-cache write for %s", key.toString());
            this.mStagingArea.remove(key, encodedImage);
            EncodedImage.closeSafely(finalEncodedImage);
        }
    }

    public Task<Void> remove(final CacheKey key) {
        Preconditions.checkNotNull(key);
        this.mStagingArea.remove(key);
        try {
            return Task.call(new Callable<Void>() {
                public Void call() throws Exception {
                    BufferedDiskCache.this.mStagingArea.remove(key);
                    BufferedDiskCache.this.mFileCache.remove(key);
                    return null;
                }
            }, this.mWriteExecutor);
        } catch (Throwable exception) {
            FLog.m150w(TAG, exception, "Failed to schedule disk-cache remove for %s", key.toString());
            return Task.forError(exception);
        }
    }

    public Task<Void> clearAll() {
        this.mStagingArea.clearAll();
        try {
            return Task.call(new C11535(), this.mWriteExecutor);
        } catch (Throwable exception) {
            FLog.m150w(TAG, exception, "Failed to schedule disk-cache clear", new Object[0]);
            return Task.forError(exception);
        }
    }

    private Task<EncodedImage> foundPinnedImage(CacheKey key, EncodedImage pinnedImage) {
        FLog.m132v(TAG, "Found image for %s in staging area", key.toString());
        this.mImageCacheStatsTracker.onStagingAreaHit();
        return Task.forResult(pinnedImage);
    }

    private PooledByteBuffer readFromDiskCache(CacheKey key) throws IOException {
        InputStream is;
        try {
            FLog.m132v(TAG, "Disk cache read for %s", key.toString());
            BinaryResource diskCacheResource = this.mFileCache.getResource(key);
            if (diskCacheResource == null) {
                FLog.m132v(TAG, "Disk cache miss for %s", key.toString());
                this.mImageCacheStatsTracker.onDiskCacheMiss();
                return null;
            }
            FLog.m132v(TAG, "Found entry in disk cache for %s", key.toString());
            this.mImageCacheStatsTracker.onDiskCacheHit();
            is = diskCacheResource.openStream();
            PooledByteBuffer byteBuffer = this.mPooledByteBufferFactory.newByteBuffer(is, (int) diskCacheResource.size());
            is.close();
            FLog.m132v(TAG, "Successful read from disk cache for %s", key.toString());
            return byteBuffer;
        } catch (Throwable ioe) {
            FLog.m150w(TAG, ioe, "Exception reading from cache for %s", key.toString());
            this.mImageCacheStatsTracker.onDiskCacheGetFail();
            throw ioe;
        } catch (Throwable th) {
            is.close();
        }
    }

    private void writeToDiskCache(CacheKey key, final EncodedImage encodedImage) {
        FLog.m132v(TAG, "About to write to disk-cache for key %s", key.toString());
        try {
            this.mFileCache.insert(key, new WriterCallback() {
                public void write(OutputStream os) throws IOException {
                    BufferedDiskCache.this.mPooledByteStreams.copy(encodedImage.getInputStream(), os);
                }
            });
            FLog.m132v(TAG, "Successful disk-cache write for key %s", key.toString());
        } catch (Throwable ioe) {
            FLog.m150w(TAG, ioe, "Failed to write to disk-cache for key %s", key.toString());
        }
    }
}
