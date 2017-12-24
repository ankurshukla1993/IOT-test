package com.facebook.soloader;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public final class SysUtil {
    private static final byte APK_SIGNATURE_VERSION = (byte) 1;

    private static final class LollipopSysdeps {
        private LollipopSysdeps() {
        }

        public static String[] getSupportedAbis() {
            return Build.SUPPORTED_32_BIT_ABIS;
        }

        public static void fallocateIfSupported(FileDescriptor fd, long length) throws IOException {
            try {
                Os.posix_fallocate(fd, 0, length);
            } catch (ErrnoException ex) {
                if (ex.errno != OsConstants.EOPNOTSUPP && ex.errno != OsConstants.ENOSYS && ex.errno != OsConstants.EINVAL) {
                    throw new IOException(ex.toString(), ex);
                }
            }
        }
    }

    public static int findAbiScore(String[] supportedAbis, String abi) {
        int i = 0;
        while (i < supportedAbis.length) {
            if (supportedAbis[i] != null && abi.equals(supportedAbis[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void deleteOrThrow(File file) throws IOException {
        if (!file.delete()) {
            throw new IOException("could not delete file " + file);
        }
    }

    public static String[] getSupportedAbis() {
        if (VERSION.SDK_INT >= 21) {
            return LollipopSysdeps.getSupportedAbis();
        }
        return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
    }

    public static void fallocateIfSupported(FileDescriptor fd, long length) throws IOException {
        if (VERSION.SDK_INT >= 21) {
            LollipopSysdeps.fallocateIfSupported(fd, length);
        }
    }

    public static void dumbDeleteRecursive(File file) throws IOException {
        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            if (fileList != null) {
                for (File entry : fileList) {
                    dumbDeleteRecursive(entry);
                }
            } else {
                return;
            }
        }
        if (!file.delete() && file.exists()) {
            throw new IOException("could not delete: " + file);
        }
    }

    static void mkdirOrThrow(File dir) throws IOException {
        if (!dir.mkdirs() && !dir.isDirectory()) {
            throw new IOException("cannot mkdir: " + dir);
        }
    }

    static int copyBytes(RandomAccessFile os, InputStream is, int byteLimit, byte[] buffer) throws IOException {
        int bytesCopied = 0;
        while (bytesCopied < byteLimit) {
            int nrRead = is.read(buffer, 0, Math.min(buffer.length, byteLimit - bytesCopied));
            if (nrRead == -1) {
                break;
            }
            os.write(buffer, 0, nrRead);
            bytesCopied += nrRead;
        }
        return bytesCopied;
    }

    static void fsyncRecursive(File fileName) throws IOException {
        RandomAccessFile file;
        Throwable th;
        Throwable th2;
        if (fileName.isDirectory()) {
            File[] files = fileName.listFiles();
            if (files == null) {
                throw new IOException("cannot list directory " + fileName);
            }
            for (File fsyncRecursive : files) {
                fsyncRecursive(fsyncRecursive);
            }
            return;
        } else if (!fileName.getPath().endsWith("_lock")) {
            file = new RandomAccessFile(fileName, "r");
            th = null;
            try {
                file.getFD().sync();
                if (file == null) {
                    return;
                }
                if (th != null) {
                    try {
                        file.close();
                        return;
                    } catch (Throwable x2) {
                        th.addSuppressed(x2);
                        return;
                    }
                }
                file.close();
                return;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                th3 = th2;
                th2 = th4;
            }
        } else {
            return;
        }
        if (file != null) {
            if (th3 != null) {
                try {
                    file.close();
                } catch (Throwable x22) {
                    th3.addSuppressed(x22);
                }
            } else {
                file.close();
            }
        }
        throw th2;
        throw th2;
    }

    public static byte[] makeApkDepBlock(File apkFile) {
        Parcel parcel = Parcel.obtain();
        parcel.writeByte((byte) 1);
        parcel.writeString(apkFile.getPath());
        parcel.writeLong(apkFile.lastModified());
        byte[] depsBlock = parcel.marshall();
        parcel.recycle();
        return depsBlock;
    }
}
