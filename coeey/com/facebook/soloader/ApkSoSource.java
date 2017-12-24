package com.facebook.soloader;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;

public class ApkSoSource extends ExtractFromZipSoSource {
    private static final byte APK_SIGNATURE_VERSION = (byte) 1;
    public static final int PREFER_ANDROID_LIBS_DIRECTORY = 1;
    private static final String TAG = "ApkSoSource";
    private final int mFlags;

    protected class ApkUnpacker extends ZipUnpacker {
        private final int mFlags;
        private File mLibDir;

        ApkUnpacker() throws IOException {
            super();
            this.mLibDir = new File(ApkSoSource.this.mContext.getApplicationInfo().nativeLibraryDir);
            this.mFlags = ApkSoSource.this.mFlags;
        }

        protected boolean shouldExtract(ZipEntry ze, String soName) {
            String zipPath = ze.getName();
            if ((this.mFlags & 1) == 0) {
                Log.d(ApkSoSource.TAG, "allowing consideration of " + zipPath + ": self-extraction preferred");
                return true;
            }
            File sysLibFile = new File(this.mLibDir, soName);
            if (sysLibFile.isFile()) {
                if (sysLibFile.length() != ze.getSize()) {
                    Log.d(ApkSoSource.TAG, String.format("allowing consideration of %s: sysdir file length is %s, but the file is %s bytes long in the APK", new Object[]{sysLibFile, Long.valueOf(sysLibLength), Long.valueOf(apkLibLength)}));
                    return true;
                }
                Log.d(ApkSoSource.TAG, "not allowing consideration of " + zipPath + ": deferring to libdir");
                return false;
            }
            Log.d(ApkSoSource.TAG, String.format("allowing considering of %s: %s not in system lib dir", new Object[]{zipPath, soName}));
            return true;
        }
    }

    public ApkSoSource(Context context, String name, int flags) {
        super(context, name, new File(context.getApplicationInfo().sourceDir), "^lib/([^/]+)/([^/]+\\.so)$");
        this.mFlags = flags;
    }

    protected Unpacker makeUnpacker() throws IOException {
        return new ApkUnpacker();
    }

    protected byte[] getDepsBlock() throws IOException {
        return SysUtil.makeApkDepBlock(this.mZipFileName);
    }
}
