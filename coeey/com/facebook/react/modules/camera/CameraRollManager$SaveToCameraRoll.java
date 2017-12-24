package com.facebook.react.modules.camera;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Environment;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

class CameraRollManager$SaveToCameraRoll extends GuardedAsyncTask<Void, Void> {
    private final Context mContext;
    private final Promise mPromise;
    private final Uri mUri;

    class C13131 implements OnScanCompletedListener {
        C13131() {
        }

        public void onScanCompleted(String path, Uri uri) {
            if (uri != null) {
                CameraRollManager$SaveToCameraRoll.this.mPromise.resolve(uri.toString());
            } else {
                CameraRollManager$SaveToCameraRoll.this.mPromise.reject("E_UNABLE_TO_SAVE", "Could not add image to gallery");
            }
        }
    }

    public CameraRollManager$SaveToCameraRoll(ReactContext context, Uri uri, Promise promise) {
        super(context);
        this.mContext = context;
        this.mUri = uri;
        this.mPromise = promise;
    }

    protected void doInBackgroundGuarded(Void... params) {
        File source = new File(this.mUri.getPath());
        FileChannel input = null;
        FileChannel output = null;
        try {
            File exportDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            exportDir.mkdirs();
            if (exportDir.isDirectory()) {
                String sourceName;
                String sourceExt;
                int n;
                File dest = new File(exportDir, source.getName());
                String fullSourceName = source.getName();
                if (fullSourceName.indexOf(46) >= 0) {
                    sourceName = fullSourceName.substring(0, fullSourceName.lastIndexOf(46));
                    sourceExt = fullSourceName.substring(fullSourceName.lastIndexOf(46));
                    n = 0;
                } else {
                    sourceName = fullSourceName;
                    sourceExt = "";
                    n = 0;
                }
                while (!dest.createNewFile()) {
                    int n2 = n + 1;
                    dest = new File(exportDir, sourceName + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + n + sourceExt);
                    n = n2;
                }
                input = new FileInputStream(source).getChannel();
                output = new FileOutputStream(dest).getChannel();
                output.transferFrom(input, 0, input.size());
                input.close();
                output.close();
                MediaScannerConnection.scanFile(this.mContext, new String[]{dest.getAbsolutePath()}, null, new C13131());
                if (input != null && input.isOpen()) {
                    try {
                        input.close();
                    } catch (Throwable e) {
                        FLog.m112e(ReactConstants.TAG, "Could not close input channel", e);
                    }
                }
                if (output != null && output.isOpen()) {
                    try {
                        output.close();
                        return;
                    } catch (Throwable e2) {
                        FLog.m112e(ReactConstants.TAG, "Could not close output channel", e2);
                        return;
                    }
                }
                return;
            }
            this.mPromise.reject("E_UNABLE_TO_LOAD", "External media storage directory not available");
            if (input != null && input.isOpen()) {
                try {
                    input.close();
                } catch (Throwable e22) {
                    FLog.m112e(ReactConstants.TAG, "Could not close input channel", e22);
                }
            }
            if (output != null && output.isOpen()) {
                try {
                    output.close();
                } catch (Throwable e222) {
                    FLog.m112e(ReactConstants.TAG, "Could not close output channel", e222);
                }
            }
        } catch (IOException e3) {
            this.mPromise.reject(e3);
            if (input != null && input.isOpen()) {
                try {
                    input.close();
                } catch (Throwable e2222) {
                    FLog.m112e(ReactConstants.TAG, "Could not close input channel", e2222);
                }
            }
            if (output != null && output.isOpen()) {
                try {
                    output.close();
                } catch (Throwable e22222) {
                    FLog.m112e(ReactConstants.TAG, "Could not close output channel", e22222);
                }
            }
        } catch (Throwable th) {
            if (input != null && input.isOpen()) {
                try {
                    input.close();
                } catch (Throwable e222222) {
                    FLog.m112e(ReactConstants.TAG, "Could not close input channel", e222222);
                }
            }
            if (output != null && output.isOpen()) {
                try {
                    output.close();
                } catch (Throwable e2222222) {
                    FLog.m112e(ReactConstants.TAG, "Could not close output channel", e2222222);
                }
            }
        }
    }
}
