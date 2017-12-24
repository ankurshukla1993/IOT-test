package com.evernote.android.job.util.support;

import android.support.annotation.NonNull;
import com.evernote.android.job.util.JobCat;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.vrallev.android.cat.CatLog;
import org.xmlpull.v1.XmlPullParserException;

public final class PersistableBundleCompat {
    private static final CatLog CAT = new JobCat("PersistableBundleCompat");
    private static final String UTF_8 = "UTF-8";
    private final Map<String, Object> mValues;

    public PersistableBundleCompat() {
        this(new HashMap());
    }

    public PersistableBundleCompat(PersistableBundleCompat bundle) {
        this(new HashMap(bundle.mValues));
    }

    private PersistableBundleCompat(Map<String, Object> values) {
        this.mValues = values;
    }

    public void putBoolean(String key, boolean value) {
        this.mValues.put(key, Boolean.valueOf(value));
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        Object value = this.mValues.get(key);
        if (value instanceof Boolean) {
            return ((Boolean) value).booleanValue();
        }
        return defaultValue;
    }

    public void putInt(String key, int value) {
        this.mValues.put(key, Integer.valueOf(value));
    }

    public int getInt(String key, int defaultValue) {
        Object value = this.mValues.get(key);
        if (value instanceof Integer) {
            return ((Integer) value).intValue();
        }
        return defaultValue;
    }

    public void putIntArray(String key, int[] value) {
        this.mValues.put(key, value);
    }

    public int[] getIntArray(String key) {
        Object value = this.mValues.get(key);
        if (value instanceof int[]) {
            return (int[]) value;
        }
        return null;
    }

    public void putLong(String key, long value) {
        this.mValues.put(key, Long.valueOf(value));
    }

    public long getLong(String key, long defaultValue) {
        Object value = this.mValues.get(key);
        if (value instanceof Long) {
            return ((Long) value).longValue();
        }
        return defaultValue;
    }

    public void putLongArray(String key, long[] value) {
        this.mValues.put(key, value);
    }

    public long[] getLongArray(String key) {
        Object value = this.mValues.get(key);
        if (value instanceof long[]) {
            return (long[]) value;
        }
        return null;
    }

    public void putDouble(String key, double value) {
        this.mValues.put(key, Double.valueOf(value));
    }

    public double getDouble(String key, double defaultValue) {
        Object value = this.mValues.get(key);
        if (value instanceof Double) {
            return ((Double) value).doubleValue();
        }
        return defaultValue;
    }

    public void putDoubleArray(String key, double[] value) {
        this.mValues.put(key, value);
    }

    public double[] getDoubleArray(String key) {
        Object value = this.mValues.get(key);
        if (value instanceof double[]) {
            return (double[]) value;
        }
        return null;
    }

    public void putString(String key, String value) {
        this.mValues.put(key, value);
    }

    public String getString(String key, String defaultValue) {
        Object value = this.mValues.get(key);
        if (value instanceof String) {
            return (String) value;
        }
        return defaultValue;
    }

    public void putStringArray(String key, String[] value) {
        this.mValues.put(key, value);
    }

    public String[] getStringArray(String key) {
        Object value = this.mValues.get(key);
        if (value instanceof String[]) {
            return (String[]) value;
        }
        return null;
    }

    public void putPersistableBundleCompat(String key, PersistableBundleCompat value) {
        this.mValues.put(key, value == null ? null : value.mValues);
    }

    public PersistableBundleCompat getPersistableBundleCompat(String key) {
        Object value = this.mValues.get(key);
        if (value instanceof Map) {
            return new PersistableBundleCompat((Map) value);
        }
        return null;
    }

    public void clear() {
        this.mValues.clear();
    }

    public boolean containsKey(String key) {
        return this.mValues.containsKey(key);
    }

    public Object get(String key) {
        return this.mValues.get(key);
    }

    public boolean isEmpty() {
        return this.mValues.isEmpty();
    }

    public Set<String> keySet() {
        return this.mValues.keySet();
    }

    public void putAll(PersistableBundleCompat bundle) {
        this.mValues.putAll(bundle.mValues);
    }

    public void remove(String key) {
        this.mValues.remove(key);
    }

    public int size() {
        return this.mValues.size();
    }

    @NonNull
    public String saveToXml() {
        String byteArrayOutputStream;
        Exception e;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            XmlUtils.writeMapXml(this.mValues, outputStream);
            byteArrayOutputStream = outputStream.toString("UTF-8");
            try {
                outputStream.close();
            } catch (IOException e2) {
            }
        } catch (Exception e3) {
            e = e3;
            CAT.e(e);
            byteArrayOutputStream = "";
            try {
                outputStream.close();
            } catch (IOException e4) {
            }
        } catch (Exception e32) {
            e = e32;
            CAT.e(e);
            byteArrayOutputStream = "";
            outputStream.close();
        } catch (Error e5) {
            CAT.e(e5);
            byteArrayOutputStream = "";
            try {
                outputStream.close();
            } catch (IOException e6) {
            }
        } catch (Throwable th) {
            try {
                outputStream.close();
            } catch (IOException e7) {
            }
        }
        return byteArrayOutputStream;
    }

    @NonNull
    public static PersistableBundleCompat fromXml(@NonNull String xml) {
        PersistableBundleCompat persistableBundleCompat;
        Exception e;
        Exception e2;
        Throwable th;
        VerifyError e3;
        ByteArrayInputStream inputStream = null;
        try {
            ByteArrayInputStream inputStream2 = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            try {
                persistableBundleCompat = new PersistableBundleCompat(XmlUtils.readMapXml(inputStream2));
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e4) {
                    }
                }
                inputStream = inputStream2;
            } catch (XmlPullParserException e5) {
                e = e5;
                inputStream = inputStream2;
                e2 = e;
                try {
                    CAT.e(e2);
                    persistableBundleCompat = new PersistableBundleCompat();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e6) {
                        }
                    }
                    return persistableBundleCompat;
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e7) {
                        }
                    }
                    throw th;
                }
            } catch (IOException e8) {
                e = e8;
                inputStream = inputStream2;
                e2 = e;
                CAT.e(e2);
                persistableBundleCompat = new PersistableBundleCompat();
                if (inputStream != null) {
                    inputStream.close();
                }
                return persistableBundleCompat;
            } catch (VerifyError e9) {
                e3 = e9;
                inputStream = inputStream2;
                CAT.e(e3);
                persistableBundleCompat = new PersistableBundleCompat();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e10) {
                    }
                }
                return persistableBundleCompat;
            } catch (Throwable th3) {
                th = th3;
                inputStream = inputStream2;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (XmlPullParserException e11) {
            e = e11;
            e2 = e;
            CAT.e(e2);
            persistableBundleCompat = new PersistableBundleCompat();
            if (inputStream != null) {
                inputStream.close();
            }
            return persistableBundleCompat;
        } catch (IOException e12) {
            e = e12;
            e2 = e;
            CAT.e(e2);
            persistableBundleCompat = new PersistableBundleCompat();
            if (inputStream != null) {
                inputStream.close();
            }
            return persistableBundleCompat;
        } catch (VerifyError e13) {
            e3 = e13;
            CAT.e(e3);
            persistableBundleCompat = new PersistableBundleCompat();
            if (inputStream != null) {
                inputStream.close();
            }
            return persistableBundleCompat;
        }
        return persistableBundleCompat;
    }
}
