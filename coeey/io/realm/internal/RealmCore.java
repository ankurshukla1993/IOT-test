package io.realm.internal;

import android.content.Context;
import com.getkeepsafe.relinker.ReLinker;
import io.realm.BuildConfig;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Locale;

public class RealmCore {
    private static final String BINARIES_PATH = ("lib" + PATH_SEP + ".." + FILE_SEP + "lib");
    private static final String FILE_SEP = File.separator;
    private static final String JAVA_LIBRARY_PATH = "java.library.path";
    private static final String PATH_SEP = File.pathSeparator;
    private static volatile boolean libraryIsLoaded = false;

    public static boolean osIsWindows() {
        return System.getProperty("os.name").toLowerCase(Locale.getDefault()).contains("win");
    }

    public static synchronized void loadLibrary(Context context) {
        synchronized (RealmCore.class) {
            if (!libraryIsLoaded) {
                ReLinker.loadLibrary(context, "realm-jni", BuildConfig.VERSION_NAME);
                libraryIsLoaded = true;
            }
        }
    }

    private static String loadLibraryWindows() {
        try {
            addNativeLibraryPath(BINARIES_PATH);
            resetLibraryPath();
        } catch (Throwable th) {
        }
        String jnilib = loadCorrectLibrary("realm_jni32d", "realm_jni64d");
        if (jnilib != null) {
            System.out.println("!!! Realm debug version loaded. !!!\n");
        } else {
            jnilib = loadCorrectLibrary("realm_jni32", "realm_jni64");
            if (jnilib == null) {
                System.err.println("Searched java.library.path=" + System.getProperty(JAVA_LIBRARY_PATH));
                throw new RuntimeException("Couldn't load the Realm JNI library 'realm_jni32.dll or realm_jni64.dll'. Please include the directory to the library in java.library.path.");
            }
        }
        return jnilib;
    }

    private static String loadCorrectLibrary(String... libraryCandidateNames) {
        int length = libraryCandidateNames.length;
        int i = 0;
        while (i < length) {
            String libraryCandidateName = libraryCandidateNames[i];
            try {
                System.loadLibrary(libraryCandidateName);
                return libraryCandidateName;
            } catch (Throwable th) {
                i++;
            }
        }
        return null;
    }

    public static void addNativeLibraryPath(String path) {
        try {
            System.setProperty(JAVA_LIBRARY_PATH, System.getProperty(JAVA_LIBRARY_PATH) + PATH_SEP + path + PATH_SEP);
        } catch (Exception e) {
            throw new RuntimeException("Cannot set the library path!", e);
        }
    }

    private static void resetLibraryPath() {
        try {
            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException("Cannot reset the library path!", e);
        }
    }
}
