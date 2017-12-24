package net.vrallev.android.cat;

import android.util.LruCache;

public final class CatUtil {
    private static final LruCache<String, Boolean> IGNORED_CLASS_NAMES = new LruCache<String, Boolean>(100) {
        protected Boolean create(String className) {
            try {
                Class<?> clazz = Class.forName(className);
                if (hasInvalidInterfaces(clazz)) {
                    return Boolean.valueOf(true);
                }
                Class<?> superclass = clazz.getSuperclass();
                while (superclass != null) {
                    if (!CatUtil.isValidClass(superclass.getName()) || hasInvalidInterfaces(superclass)) {
                        return Boolean.valueOf(true);
                    }
                    superclass = superclass.getSuperclass();
                }
                return Boolean.valueOf(false);
            } catch (Exception e) {
                return Boolean.valueOf(false);
            }
        }

        private boolean hasInvalidInterfaces(Class<?> clazz) {
            if (clazz == null) {
                return false;
            }
            Class<?>[] interfaces = clazz.getInterfaces();
            if (interfaces == null || interfaces.length == 0) {
                return false;
            }
            for (Class<?> interfaceClass : interfaces) {
                if (!CatUtil.isValidClass(interfaceClass.getName())) {
                    return true;
                }
            }
            return false;
        }
    };
    private static final String PACKAGE;

    static {
        Package catPackage = Cat.class.getPackage();
        if (catPackage == null) {
            PACKAGE = null;
        } else {
            PACKAGE = catPackage.getName();
        }
    }

    public static boolean isValidClass(String className) {
        return PACKAGE == null || !className.startsWith(PACKAGE);
    }

    private static boolean isClassNameIgnored(String className) {
        return ((Boolean) IGNORED_CLASS_NAMES.get(className)).booleanValue();
    }

    public static String getCallingClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 3; i < stackTrace.length; i++) {
            String className = stackTrace[i].getClassName();
            if (isValidClass(className) && !isClassNameIgnored(className)) {
                return className;
            }
        }
        return "Cat";
    }

    public static String getCallingClassNameSimple() {
        return simpleClassName(getCallingClassName());
    }

    public static String simpleClassName(String className) {
        String[] split = className.split("\\.");
        return split.length == 0 ? className : split[split.length - 1];
    }

    public static String stripInnerClass(String className) {
        String[] split = className.split("\\$");
        return split.length == 0 ? className : split[0];
    }

    public static String getCallingPackage() {
        String className = getCallingClassName();
        String[] split = className.split("\\.");
        return split.length <= 1 ? className : className.substring(0, (className.length() - 1) - split[split.length - 1].length());
    }

    private CatUtil() {
    }
}
