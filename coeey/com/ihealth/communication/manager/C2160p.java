package com.ihealth.communication.manager;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.facebook.appevents.AppEventsConstants;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;

class C2160p {
    static String m1197a(Context context) {
        String packageName = context.getPackageName();
        String str = null;
        try {
            str = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        String str2 = "Mr. Liu is Awesome!";
        String str3 = "";
        if (str.contains(".")) {
            str = str.split("\\.")[0];
        }
        return C2160p.m1198a(packageName + str + str2);
    }

    private static String m1198a(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(CommonUtils.MD5_INSTANCE).digest(str.getBytes("UTF-8"));
            StringBuilder stringBuilder = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                if ((b & 255) < 16) {
                    stringBuilder.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                stringBuilder.append(Integer.toHexString(b & 255));
            }
            return stringBuilder.toString();
        } catch (Throwable e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (Throwable e2) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e2);
        }
    }
}
