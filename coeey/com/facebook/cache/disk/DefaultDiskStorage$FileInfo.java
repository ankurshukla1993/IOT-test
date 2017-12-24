package com.facebook.cache.disk;

import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;

class DefaultDiskStorage$FileInfo {
    public final String resourceId;
    public final FileType type;

    private DefaultDiskStorage$FileInfo(FileType type, String resourceId) {
        this.type = type;
        this.resourceId = resourceId;
    }

    public String toString() {
        return this.type + "(" + this.resourceId + ")";
    }

    public String toPath(String parentPath) {
        return parentPath + File.separator + this.resourceId + this.type.extension;
    }

    public File createTempFile(File parent) throws IOException {
        return File.createTempFile(this.resourceId + ".", ".tmp", parent);
    }

    @Nullable
    public static DefaultDiskStorage$FileInfo fromFile(File file) {
        String name = file.getName();
        int pos = name.lastIndexOf(46);
        if (pos <= 0) {
            return null;
        }
        FileType type = FileType.fromExtension(name.substring(pos));
        if (type == null) {
            return null;
        }
        String resourceId = name.substring(0, pos);
        if (type.equals(FileType.TEMP)) {
            int numPos = resourceId.lastIndexOf(46);
            if (numPos <= 0) {
                return null;
            }
            resourceId = resourceId.substring(0, numPos);
        }
        return new DefaultDiskStorage$FileInfo(type, resourceId);
    }
}
