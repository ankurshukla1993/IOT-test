package io.realm.internal;

import android.os.Build;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.log.RealmLog;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

public class Util {
    static native String nativeGetTablePrefix();

    public static String getTablePrefix() {
        return nativeGetTablePrefix();
    }

    public static Class<? extends RealmModel> getOriginalModelClass(Class<? extends RealmModel> clazz) {
        Class<? extends RealmModel> superclass = clazz.getSuperclass();
        if (superclass.equals(Object.class) || superclass.equals(RealmObject.class)) {
            return clazz;
        }
        return superclass;
    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw, true));
        return sw.getBuffer().toString();
    }

    public static boolean isEmulator() {
        if (Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains(CommonUtils.GOOGLE_SDK) || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || ((Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || CommonUtils.GOOGLE_SDK.equals(Build.PRODUCT))) {
            return true;
        }
        return false;
    }

    public static boolean isEmptyString(@Nullable String str) {
        return str == null || str.length() == 0;
    }

    public static boolean deleteRealm(String canonicalPath, File realmFolder, String realmFileName) {
        boolean realmDeleted = true;
        String management = ".management";
        File managementFolder = new File(realmFolder, realmFileName + ".management");
        File[] files = managementFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (realmDeleted && file.delete()) {
                    realmDeleted = true;
                } else {
                    realmDeleted = false;
                }
            }
        }
        if (realmDeleted && managementFolder.delete()) {
            realmDeleted = true;
        } else {
            realmDeleted = false;
        }
        if (realmDeleted && deletes(canonicalPath, realmFolder, realmFileName)) {
            return true;
        }
        return false;
    }

    private static boolean deletes(String canonicalPath, File rootFolder, String realmFileName) {
        AtomicBoolean realmDeleted = new AtomicBoolean(true);
        for (File fileToDelete : Arrays.asList(new File[]{new File(rootFolder, realmFileName), new File(rootFolder, realmFileName + ".lock"), new File(rootFolder, realmFileName + ".log_a"), new File(rootFolder, realmFileName + ".log_b"), new File(rootFolder, realmFileName + ".log"), new File(canonicalPath)})) {
            if (fileToDelete.exists() && !fileToDelete.delete()) {
                realmDeleted.set(false);
                RealmLog.warn("Could not delete the file %s", fileToDelete);
            }
        }
        return realmDeleted.get();
    }
}
