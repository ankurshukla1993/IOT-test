package com.ihealth.communication.utils;

public class Log {

    public enum Level {
        VERBOSE(2),
        DEBUG(3),
        INFO(4),
        WARN(5),
        ERROR(6);
        
        int f2107a;

        private Level(int level) {
            this.f2107a = 2;
            this.f2107a = level;
        }
    }

    private Log() {
    }

    public static void m1210d(String tag, String msg) {
        Logger.m1225d(tag, msg);
    }

    public static void m1211e(String tag, String msg) {
        Logger.m1226e(tag, msg);
    }

    public static void m1212i(String tag, String msg) {
        Logger.m1227i(tag, msg);
    }

    public static void m1213p(String tag, Level level, String methodName, Object... parameters) {
        if (methodName != null) {
            try {
                String str;
                if (methodName.contains("()")) {
                    methodName = methodName.replace("()", "");
                }
                if (parameters.length == 0) {
                    str = methodName + "()";
                } else {
                    StringBuilder stringBuilder = new StringBuilder(methodName + "(");
                    for (Object append : parameters) {
                        stringBuilder.append(append).append(", ");
                    }
                    stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append(")");
                    str = stringBuilder.toString();
                }
                switch (C2175d.f2123a[level.ordinal()]) {
                    case 1:
                        Logger.m1228v(tag, str);
                        return;
                    case 2:
                        Logger.m1225d(tag, str);
                        return;
                    case 3:
                        Logger.m1227i(tag, str);
                        return;
                    case 4:
                        Logger.m1229w(tag, str);
                        return;
                    case 5:
                        Logger.m1226e(tag, str);
                        return;
                    default:
                        return;
                }
            } catch (Exception e) {
                Logger.m1229w("Log", "printMethodInfo -- Exception:" + e);
            }
        }
    }

    public static void m1214v(String tag, String msg) {
        Logger.m1228v(tag, msg);
    }

    public static void m1215w(String tag, String msg) {
        Logger.m1229w(tag, msg);
    }
}
