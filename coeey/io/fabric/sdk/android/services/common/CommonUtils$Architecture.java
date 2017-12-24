package io.fabric.sdk.android.services.common;

import android.os.Build;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

enum CommonUtils$Architecture {
    X86_32,
    X86_64,
    ARM_UNKNOWN,
    PPC,
    PPC64,
    ARMV6,
    ARMV7,
    UNKNOWN,
    ARMV7S,
    ARM64;
    
    private static final Map<String, CommonUtils$Architecture> matcher = null;

    static {
        matcher = new HashMap(4);
        matcher.put("armeabi-v7a", ARMV7);
        matcher.put("armeabi", ARMV6);
        matcher.put("arm64-v8a", ARM64);
        matcher.put("x86", X86_32);
    }

    static CommonUtils$Architecture getValue() {
        String arch = Build.CPU_ABI;
        if (TextUtils.isEmpty(arch)) {
            Fabric.getLogger().mo5779d(Fabric.TAG, "Architecture#getValue()::Build.CPU_ABI returned null or empty");
            return UNKNOWN;
        }
        CommonUtils$Architecture value = (CommonUtils$Architecture) matcher.get(arch.toLowerCase(Locale.US));
        if (value == null) {
            return UNKNOWN;
        }
        return value;
    }
}
