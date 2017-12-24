package com.thefinestartist.utils.etc;

import android.os.Build.VERSION;

public class APILevel {
    private APILevel() {
    }

    public static boolean require(int level) {
        return VERSION.SDK_INT >= level;
    }

    public static boolean requireCupcake() {
        return VERSION.SDK_INT >= 3;
    }

    public static boolean requireDonut() {
        return VERSION.SDK_INT >= 4;
    }

    public static boolean requireEclair() {
        return VERSION.SDK_INT >= 5;
    }

    public static boolean requireFroyo() {
        return VERSION.SDK_INT >= 8;
    }

    public static boolean requireGingerbread() {
        return VERSION.SDK_INT >= 9;
    }

    public static boolean requireHoneycomb() {
        return VERSION.SDK_INT >= 11;
    }

    public static boolean requireHoneycombMR2() {
        return VERSION.SDK_INT >= 13;
    }

    public static boolean requireIceCreamSandwich() {
        return VERSION.SDK_INT >= 14;
    }

    public static boolean requireJellyBean() {
        return VERSION.SDK_INT >= 16;
    }

    public static boolean requireJellyBeanMR1() {
        return VERSION.SDK_INT >= 17;
    }

    public static boolean requireJellyBeanMR2() {
        return VERSION.SDK_INT >= 18;
    }

    public static boolean requireKitkat() {
        return VERSION.SDK_INT >= 19;
    }

    public static boolean requireLollipop() {
        return VERSION.SDK_INT >= 21;
    }

    public static boolean requireLollipopMR1() {
        return VERSION.SDK_INT >= 22;
    }

    public static boolean requireMarshmallow() {
        return VERSION.SDK_INT >= 23;
    }

    public static boolean deprecatedAt(int level) {
        return VERSION.SDK_INT < level;
    }

    public static boolean deprecatedAtCupcake() {
        return VERSION.SDK_INT < 3;
    }

    public static boolean deprecatedAtDonut() {
        return VERSION.SDK_INT < 4;
    }

    public static boolean deprecatedAtEclair() {
        return VERSION.SDK_INT < 5;
    }

    public static boolean deprecatedAtFroyo() {
        return VERSION.SDK_INT < 8;
    }

    public static boolean deprecatedAtGingerbread() {
        return VERSION.SDK_INT < 9;
    }

    public static boolean deprecatedAtHoneycomb() {
        return VERSION.SDK_INT < 11;
    }

    public static boolean deprecatedAtHoneycombMR2() {
        return VERSION.SDK_INT < 13;
    }

    public static boolean deprecatedAtIceCreamSandwich() {
        return VERSION.SDK_INT < 14;
    }

    public static boolean deprecatedAtJellyBean() {
        return VERSION.SDK_INT < 16;
    }

    public static boolean deprecatedAtJellyBeanMR1() {
        return VERSION.SDK_INT < 17;
    }

    public static boolean deprecatedAtJellyBeanMR2() {
        return VERSION.SDK_INT < 18;
    }

    public static boolean deprecatedAtKitkat() {
        return VERSION.SDK_INT < 19;
    }

    public static boolean deprecatedAtLollipop() {
        return VERSION.SDK_INT < 21;
    }

    public static boolean deprecatedAtLollipopMR1() {
        return VERSION.SDK_INT < 22;
    }

    public static boolean deprecatedAtMarshmallow() {
        return VERSION.SDK_INT < 23;
    }
}
