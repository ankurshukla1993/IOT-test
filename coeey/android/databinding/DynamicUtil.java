package android.databinding;

public class DynamicUtil {
    public static int safeUnbox(Integer boxed) {
        return boxed == null ? 0 : boxed.intValue();
    }

    public static long safeUnbox(Long boxed) {
        return boxed == null ? 0 : boxed.longValue();
    }

    public static short safeUnbox(Short boxed) {
        return boxed == null ? (short) 0 : boxed.shortValue();
    }

    public static byte safeUnbox(Byte boxed) {
        return boxed == null ? (byte) 0 : boxed.byteValue();
    }

    public static char safeUnbox(Character boxed) {
        return boxed == null ? '\u0000' : boxed.charValue();
    }

    public static double safeUnbox(Double boxed) {
        return boxed == null ? 0.0d : boxed.doubleValue();
    }

    public static float safeUnbox(Float boxed) {
        return boxed == null ? 0.0f : boxed.floatValue();
    }

    public static boolean safeUnbox(Boolean boxed) {
        return boxed == null ? false : boxed.booleanValue();
    }
}
