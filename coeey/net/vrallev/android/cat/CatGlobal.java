package net.vrallev.android.cat;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.vrallev.android.cat.instance.CatLazy;
import net.vrallev.android.cat.print.CatPrinter;

public final class CatGlobal {
    private static final List<String> DISABLED_PACKAGES = new ArrayList();
    private static final Set<String> DISABLED_TAGS = new HashSet();
    private static final Map<String, CatLog> PACKAGE_CAT_LOGS = new HashMap();
    private static final List<String> PACKAGE_CAT_LOG_KEYS = new ArrayList();
    private static CatLog defaultCatLog = new CatLazy();

    private CatGlobal() {
    }

    static synchronized void print(int priority, String tag, String message, Throwable t, List<? extends CatPrinter> printers) {
        synchronized (CatGlobal.class) {
            if (DISABLED_TAGS.isEmpty() || !DISABLED_TAGS.contains(tag)) {
                if (!((!DISABLED_PACKAGES.isEmpty() && isCallingClassDisabled()) || printers == null || printers.isEmpty())) {
                    for (int i = 0; i < printers.size(); i++) {
                        ((CatPrinter) printers.get(i)).println(priority, tag, message, t);
                    }
                }
            }
        }
    }

    public static synchronized void setTagEnabled(String tag, boolean enabled) {
        synchronized (CatGlobal.class) {
            if (enabled) {
                DISABLED_TAGS.remove(tag);
            } else {
                DISABLED_TAGS.add(tag);
            }
        }
    }

    public static synchronized void setPackageEnabled(String packageString, boolean enabled) {
        synchronized (CatGlobal.class) {
            if (enabled) {
                DISABLED_PACKAGES.remove(packageString);
            } else {
                DISABLED_PACKAGES.add(packageString);
            }
        }
    }

    public static boolean isCallingClassDisabled() {
        String packageName = CatUtil.getCallingPackage();
        for (int i = 0; i < DISABLED_PACKAGES.size(); i++) {
            if (packageName.startsWith((String) DISABLED_PACKAGES.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static synchronized void setDefaultCatLog(@NonNull CatLog catLog) {
        synchronized (CatGlobal.class) {
            defaultCatLog = catLog;
        }
    }

    static synchronized CatLog getDefaultCatLog() {
        CatLog catLog;
        synchronized (CatGlobal.class) {
            if (!PACKAGE_CAT_LOG_KEYS.isEmpty()) {
                String callingPackage = CatUtil.getCallingPackage();
                for (int i = PACKAGE_CAT_LOG_KEYS.size() - 1; i >= 0; i--) {
                    String catLogPackage = (String) PACKAGE_CAT_LOG_KEYS.get(i);
                    if (callingPackage.startsWith(catLogPackage)) {
                        catLog = (CatLog) PACKAGE_CAT_LOGS.get(catLogPackage);
                        break;
                    }
                }
            }
            catLog = defaultCatLog;
        }
        return catLog;
    }

    public static synchronized void setDefaultCatLogPackage(@NonNull String catLogPackage, CatLog catLog) {
        synchronized (CatGlobal.class) {
            PACKAGE_CAT_LOGS.put(catLogPackage, catLog);
            PACKAGE_CAT_LOG_KEYS.add(catLogPackage);
        }
    }

    public static synchronized void removeDefaultCatLogPackage(@NonNull String catLogPackage) {
        synchronized (CatGlobal.class) {
            PACKAGE_CAT_LOGS.remove(catLogPackage);
            PACKAGE_CAT_LOG_KEYS.remove(catLogPackage);
        }
    }
}
