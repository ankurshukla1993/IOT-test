package com.facebook.common.soloader;

public class SoLoaderShim {
    private static volatile Handler sHandler = new DefaultHandler();

    public interface Handler {
        void loadLibrary(String str);
    }

    static class C11251 implements Handler {
        C11251() {
        }

        public void loadLibrary(String libraryName) {
        }
    }

    public static class DefaultHandler implements Handler {
        public void loadLibrary(String libraryName) {
            System.loadLibrary(libraryName);
        }
    }

    public static void setHandler(Handler handler) {
        if (handler == null) {
            throw new NullPointerException("Handler cannot be null");
        }
        sHandler = handler;
    }

    public static void loadLibrary(String libraryName) {
        sHandler.loadLibrary(libraryName);
    }

    public static void setInTestMode() {
        setHandler(new C11251());
    }
}
