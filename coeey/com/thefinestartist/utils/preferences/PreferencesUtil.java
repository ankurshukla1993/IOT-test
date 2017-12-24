package com.thefinestartist.utils.preferences;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.util.Base64;
import com.thefinestartist.Base;
import com.thefinestartist.utils.log.LogHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Set;

public class PreferencesUtil {
    private static final LogHelper LogHelper = new LogHelper(PreferencesUtil.class);
    private static String defaultName = PreferencesUtil.class.getCanonicalName();

    protected PreferencesUtil() {
    }

    private static SharedPreferences getPreferences(String name) {
        return Base.getContext().getSharedPreferences(name, 0);
    }

    public static String getDefaultName() {
        return defaultName;
    }

    public static void setDefaultName(String name) {
        defaultName = name;
    }

    public static boolean get(String key, boolean defValue) {
        return get(defaultName, key, defValue);
    }

    public static int get(String key, int defValue) {
        return get(defaultName, key, defValue);
    }

    public static float get(String key, float defValue) {
        return get(defaultName, key, defValue);
    }

    public static long get(String key, long defValue) {
        return get(defaultName, key, defValue);
    }

    public static String get(String key, String defValue) {
        return get(defaultName, key, defValue);
    }

    @TargetApi(11)
    public static Set<String> get(String key, Set<String> defValue) {
        return get(defaultName, key, (Set) defValue);
    }

    @TargetApi(8)
    public static <C extends Serializable> C get(String key, C defValue) {
        return get(defaultName, key, (Serializable) defValue);
    }

    public static boolean get(String name, String key, boolean defValue) {
        return getPreferences(name).getBoolean(key, defValue);
    }

    public static int get(String name, String key, int defValue) {
        return getPreferences(name).getInt(key, defValue);
    }

    public static float get(String name, String key, float defValue) {
        return getPreferences(name).getFloat(key, defValue);
    }

    public static long get(String name, String key, long defValue) {
        return getPreferences(name).getLong(key, defValue);
    }

    public static String get(String name, String key, String defValue) {
        return getPreferences(name).getString(key, defValue);
    }

    @TargetApi(11)
    public static Set<String> get(String name, String key, Set<String> defValue) {
        return getPreferences(name).getStringSet(key, defValue);
    }

    @TargetApi(8)
    public static <C extends Serializable> C get(String name, String key, C defValue) {
        Exception e;
        Throwable th;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        C result = defValue;
        String value = getPreferences(name).getString(key, null);
        if (value != null) {
            try {
                ObjectInputStream ois2;
                ByteArrayInputStream bais2 = new ByteArrayInputStream(Base64.decode(value.getBytes(), 0));
                try {
                    ois2 = new ObjectInputStream(bais2);
                } catch (Exception e2) {
                    e = e2;
                    bais = bais2;
                    try {
                        LogHelper.m2060e(e);
                        if (ois != null) {
                            try {
                                ois.close();
                            } catch (Exception e3) {
                                LogHelper.m2060e(e3);
                            }
                        }
                        if (bais != null) {
                            try {
                                bais.close();
                            } catch (Exception e32) {
                                LogHelper.m2060e(e32);
                            }
                        }
                        return result;
                    } catch (Throwable th2) {
                        th = th2;
                        if (ois != null) {
                            try {
                                ois.close();
                            } catch (Exception e322) {
                                LogHelper.m2060e(e322);
                            }
                        }
                        if (bais != null) {
                            try {
                                bais.close();
                            } catch (Exception e3222) {
                                LogHelper.m2060e(e3222);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bais = bais2;
                    if (ois != null) {
                        ois.close();
                    }
                    if (bais != null) {
                        bais.close();
                    }
                    throw th;
                }
                try {
                    result = (Serializable) ois2.readObject();
                    if (ois2 != null) {
                        try {
                            ois2.close();
                        } catch (Exception e32222) {
                            LogHelper.m2060e(e32222);
                        }
                    }
                    if (bais2 != null) {
                        try {
                            bais2.close();
                            ois = ois2;
                            bais = bais2;
                        } catch (Exception e322222) {
                            LogHelper.m2060e(e322222);
                            ois = ois2;
                            bais = bais2;
                        }
                    } else {
                        bais = bais2;
                    }
                } catch (Exception e4) {
                    e322222 = e4;
                    ois = ois2;
                    bais = bais2;
                    LogHelper.m2060e(e322222);
                    if (ois != null) {
                        ois.close();
                    }
                    if (bais != null) {
                        bais.close();
                    }
                    return result;
                } catch (Throwable th4) {
                    th = th4;
                    ois = ois2;
                    bais = bais2;
                    if (ois != null) {
                        ois.close();
                    }
                    if (bais != null) {
                        bais.close();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e322222 = e5;
                LogHelper.m2060e(e322222);
                if (ois != null) {
                    ois.close();
                }
                if (bais != null) {
                    bais.close();
                }
                return result;
            }
        }
        return result;
    }

    public static void put(String key, boolean value) {
        put(defaultName, key, value);
    }

    public static void put(String key, int value) {
        put(defaultName, key, value);
    }

    public static void put(String key, float value) {
        put(defaultName, key, value);
    }

    public static void put(String key, long value) {
        put(defaultName, key, value);
    }

    public static void put(String key, String value) {
        put(defaultName, key, value);
    }

    @TargetApi(11)
    public static void put(String key, Set<String> value) {
        put(defaultName, key, (Set) value);
    }

    @TargetApi(8)
    public static <C extends Serializable> void put(String key, C value) {
        put(defaultName, key, (Serializable) value);
    }

    public static void put(String name, String key, boolean value) {
        getPreferences(name).edit().putBoolean(key, value).commit();
    }

    public static void put(String name, String key, int value) {
        getPreferences(name).edit().putInt(key, value).commit();
    }

    public static void put(String name, String key, float value) {
        getPreferences(name).edit().putFloat(key, value).commit();
    }

    public static void put(String name, String key, long value) {
        getPreferences(name).edit().putLong(key, value).commit();
    }

    public static void put(String name, String key, String value) {
        getPreferences(name).edit().putString(key, value).commit();
    }

    @TargetApi(11)
    public static void put(String name, String key, Set<String> value) {
        getPreferences(name).edit().putStringSet(key, value).commit();
    }

    @TargetApi(8)
    public static <C extends Serializable> void put(String name, String key, C value) {
        Exception e;
        Throwable th;
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos2 = new ObjectOutputStream(baos2);
                try {
                    oos2.writeObject(value);
                    getPreferences(name).edit().putString(key, new String(Base64.encode(baos2.toByteArray(), 0))).commit();
                    if (oos2 != null) {
                        try {
                            oos2.close();
                        } catch (Exception e2) {
                            LogHelper.m2060e(e2);
                        }
                    }
                    if (baos2 != null) {
                        try {
                            baos2.close();
                        } catch (Exception e22) {
                            LogHelper.m2060e(e22);
                        }
                    }
                } catch (IOException e3) {
                    e22 = e3;
                    oos = oos2;
                    baos = baos2;
                    try {
                        LogHelper.m2060e(e22);
                        throw new RuntimeException(e22);
                    } catch (Throwable th2) {
                        th = th2;
                        if (oos != null) {
                            try {
                                oos.close();
                            } catch (Exception e222) {
                                LogHelper.m2060e(e222);
                            }
                        }
                        if (baos != null) {
                            try {
                                baos.close();
                            } catch (Exception e2222) {
                                LogHelper.m2060e(e2222);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    oos = oos2;
                    baos = baos2;
                    if (oos != null) {
                        oos.close();
                    }
                    if (baos != null) {
                        baos.close();
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e2222 = e4;
                baos = baos2;
                LogHelper.m2060e(e2222);
                throw new RuntimeException(e2222);
            } catch (Throwable th4) {
                th = th4;
                baos = baos2;
                if (oos != null) {
                    oos.close();
                }
                if (baos != null) {
                    baos.close();
                }
                throw th;
            }
        } catch (IOException e5) {
            e2222 = e5;
            LogHelper.m2060e(e2222);
            throw new RuntimeException(e2222);
        }
    }

    public static void remove(String key) {
        remove(defaultName, key);
    }

    public static void remove(String name, String key) {
        getPreferences(name).edit().remove(key).commit();
    }

    public static void clear() {
        clear(defaultName);
    }

    public static void clear(String name) {
        getPreferences(name).edit().clear().commit();
    }
}
