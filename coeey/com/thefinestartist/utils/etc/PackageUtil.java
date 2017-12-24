package com.thefinestartist.utils.etc;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.thefinestartist.Base;

public class PackageUtil {
    public static String FACEBOOK = "com.facebook.katana";
    public static String FANCY = "com.thefancy.app";
    public static String FLIPBOARD = "flipboard.app";
    public static String GMAIL = "com.google.android.gm";
    public static String GOOGLE_PLUS = "com.google.android.apps.plus";
    public static String KAKAOSTORY = "com.kakao.story";
    public static String KAKAOTALK = "com.kakao.talk";
    public static String PINTEREST = "com.pinterest";
    public static String TUMBLR = "com.tumblr";
    public static String TWITTER = "com.twitter.android";

    private PackageUtil() {
    }

    public static boolean isInstalled(String packageName) {
        try {
            Base.getContext().getPackageManager().getPackageInfo(packageName, 1);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static String getPackageName() {
        return Base.getContext().getPackageName();
    }

    public static void openPlayStore() {
        openPlayStore(Base.getContext().getPackageName());
    }

    public static void openPlayStore(String packageName) {
        Intent intent;
        try {
            intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName));
            intent.setFlags(268435456);
            Base.getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + packageName));
            intent.setFlags(268435456);
            Base.getContext().startActivity(intent);
        }
    }
}
