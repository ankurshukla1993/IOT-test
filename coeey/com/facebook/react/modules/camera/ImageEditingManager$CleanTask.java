package com.facebook.react.modules.camera;

import android.content.Context;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactContext;
import java.io.File;
import java.io.FilenameFilter;

class ImageEditingManager$CleanTask extends GuardedAsyncTask<Void, Void> {
    private final Context mContext;

    class C13141 implements FilenameFilter {
        C13141() {
        }

        public boolean accept(File dir, String filename) {
            return filename.startsWith("ReactNative_cropped_image_");
        }
    }

    private ImageEditingManager$CleanTask(ReactContext context) {
        super(context);
        this.mContext = context;
    }

    protected void doInBackgroundGuarded(Void... params) {
        cleanDirectory(this.mContext.getCacheDir());
        File externalCacheDir = this.mContext.getExternalCacheDir();
        if (externalCacheDir != null) {
            cleanDirectory(externalCacheDir);
        }
    }

    private void cleanDirectory(File directory) {
        File[] toDelete = directory.listFiles(new C13141());
        if (toDelete != null) {
            for (File file : toDelete) {
                file.delete();
            }
        }
    }
}
