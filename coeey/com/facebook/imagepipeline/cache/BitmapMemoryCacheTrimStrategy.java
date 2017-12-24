package com.facebook.imagepipeline.cache;

import android.os.Build.VERSION;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;

public class BitmapMemoryCacheTrimStrategy implements CountingMemoryCache$CacheTrimStrategy {
    private static final String TAG = "BitmapMemoryCacheTrimStrategy";

    public double getTrimRatio(MemoryTrimType trimType) {
        switch (trimType) {
            case OnCloseToDalvikHeapLimit:
                if (VERSION.SDK_INT >= 21) {
                    return MemoryTrimType.OnCloseToDalvikHeapLimit.getSuggestedTrimRatio();
                }
                return 0.0d;
            case OnAppBackgrounded:
            case OnSystemLowMemoryWhileAppInForeground:
            case OnSystemLowMemoryWhileAppInBackground:
                return 1.0d;
            default:
                FLog.wtf(TAG, "unknown trim type: %s", trimType);
                return 0.0d;
        }
    }
}
