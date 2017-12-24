package com.ihealth.communication.utils;

import com.ihealth.communication.utils.Log.Level;

/* synthetic */ class C2175d {
    static final /* synthetic */ int[] f2123a = new int[Level.values().length];

    static {
        try {
            f2123a[Level.VERBOSE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f2123a[Level.DEBUG.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f2123a[Level.INFO.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f2123a[Level.WARN.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f2123a[Level.ERROR.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
    }
}
