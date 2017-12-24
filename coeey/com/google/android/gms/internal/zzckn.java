package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.measurement.AppMeasurement$Event;
import com.google.android.gms.measurement.AppMeasurement$UserProperty;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.security.auth.x500.X500Principal;

public final class zzckn extends zzcii {
    private static String[] zzjgs = new String[]{"firebase_"};
    private SecureRandom zzjgt;
    private final AtomicLong zzjgu = new AtomicLong(0);
    private int zzjgv;

    zzckn(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
    }

    private final int zza(String str, Object obj, boolean z) {
        if (z) {
            int length;
            Object obj2;
            String str2 = "param";
            if (obj instanceof Parcelable[]) {
                length = ((Parcelable[]) obj).length;
            } else if (obj instanceof ArrayList) {
                length = ((ArrayList) obj).size();
            } else {
                length = 1;
                if (obj2 == null) {
                    return 17;
                }
            }
            if (length > 1000) {
                zzawm().zzayt().zzd("Parameter array is too long; discarded. Value kind, name, array length", str2, str, Integer.valueOf(length));
                obj2 = null;
            } else {
                length = 1;
            }
            if (obj2 == null) {
                return 17;
            }
        }
        return zzkc(str) ? zza("param", str, 256, obj, z) : zza("param", str, 100, obj, z) ? 0 : 4;
    }

    private static Object zza(int i, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (!(obj instanceof Boolean)) {
            return obj instanceof Float ? Double.valueOf(((Float) obj).doubleValue()) : ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) ? zza(String.valueOf(obj), i, z) : null;
        } else {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        }
    }

    public static Object zza(zzcky com_google_android_gms_internal_zzcky, String str) {
        for (zzckz com_google_android_gms_internal_zzckz : com_google_android_gms_internal_zzcky.zzjim) {
            if (com_google_android_gms_internal_zzckz.name.equals(str)) {
                if (com_google_android_gms_internal_zzckz.zzfzi != null) {
                    return com_google_android_gms_internal_zzckz.zzfzi;
                }
                if (com_google_android_gms_internal_zzckz.zzjiq != null) {
                    return com_google_android_gms_internal_zzckz.zzjiq;
                }
                if (com_google_android_gms_internal_zzckz.zzjgq != null) {
                    return com_google_android_gms_internal_zzckz.zzjgq;
                }
            }
        }
        return null;
    }

    public static String zza(String str, int i, boolean z) {
        return str.codePointCount(0, str.length()) > i ? z ? String.valueOf(str.substring(0, str.offsetByCodePoints(0, i))).concat("...") : null : str;
    }

    @Nullable
    public static String zza(String str, String[] strArr, String[] strArr2) {
        zzbq.checkNotNull(strArr);
        zzbq.checkNotNull(strArr2);
        int min = Math.min(strArr.length, strArr2.length);
        for (int i = 0; i < min; i++) {
            if (zzas(str, strArr[i])) {
                return strArr2[i];
            }
        }
        return null;
    }

    private final boolean zza(String str, String str2, int i, Object obj, boolean z) {
        if (obj == null || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Boolean) || (obj instanceof Double)) {
            return true;
        }
        if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
            String valueOf = String.valueOf(obj);
            if (valueOf.codePointCount(0, valueOf.length()) <= i) {
                return true;
            }
            zzawm().zzayt().zzd("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
            return false;
        } else if ((obj instanceof Bundle) && z) {
            return true;
        } else {
            int length;
            int i2;
            Object obj2;
            if ((obj instanceof Parcelable[]) && z) {
                Parcelable[] parcelableArr = (Parcelable[]) obj;
                length = parcelableArr.length;
                i2 = 0;
                while (i2 < length) {
                    obj2 = parcelableArr[i2];
                    if (obj2 instanceof Bundle) {
                        i2++;
                    } else {
                        zzawm().zzayt().zze("All Parcelable[] elements must be of type Bundle. Value type, name", obj2.getClass(), str2);
                        return false;
                    }
                }
                return true;
            } else if (!(obj instanceof ArrayList) || !z) {
                return false;
            } else {
                ArrayList arrayList = (ArrayList) obj;
                length = arrayList.size();
                i2 = 0;
                while (i2 < length) {
                    obj2 = arrayList.get(i2);
                    i2++;
                    if (!(obj2 instanceof Bundle)) {
                        zzawm().zzayt().zze("All ArrayList elements must be of type Bundle. Value type, name", obj2.getClass(), str2);
                        return false;
                    }
                }
                return true;
            }
        }
    }

    private final boolean zza(String str, String[] strArr, String str2) {
        if (str2 == null) {
            zzawm().zzayr().zzj("Name is required and can't be null. Type", str);
            return false;
        }
        boolean z;
        zzbq.checkNotNull(str2);
        for (String startsWith : zzjgs) {
            if (str2.startsWith(startsWith)) {
                z = true;
                break;
            }
        }
        z = false;
        if (z) {
            zzawm().zzayr().zze("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        }
        if (strArr != null) {
            zzbq.checkNotNull(strArr);
            for (String startsWith2 : strArr) {
                if (zzas(str2, startsWith2)) {
                    z = true;
                    break;
                }
            }
            z = false;
            if (z) {
                zzawm().zzayr().zze("Name is reserved. Type, name", str, str2);
                return false;
            }
        }
        return true;
    }

    public static boolean zza(long[] jArr, int i) {
        return i < (jArr.length << 6) && (jArr[i / 64] & (1 << (i % 64))) != 0;
    }

    static byte[] zza(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            return marshall;
        } finally {
            obtain.recycle();
        }
    }

    public static long[] zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        long[] jArr = new long[length];
        int i = 0;
        while (i < length) {
            jArr[i] = 0;
            int i2 = 0;
            while (i2 < 64 && (i << 6) + i2 < bitSet.length()) {
                if (bitSet.get((i << 6) + i2)) {
                    jArr[i] = jArr[i] | (1 << i2);
                }
                i2++;
            }
            i++;
        }
        return jArr;
    }

    static zzckz[] zza(zzckz[] com_google_android_gms_internal_zzckzArr, String str, Object obj) {
        for (zzckz com_google_android_gms_internal_zzckz : com_google_android_gms_internal_zzckzArr) {
            if (Objects.equals(com_google_android_gms_internal_zzckz.name, str)) {
                com_google_android_gms_internal_zzckz.zzjiq = null;
                com_google_android_gms_internal_zzckz.zzfzi = null;
                com_google_android_gms_internal_zzckz.zzjgq = null;
                if (obj instanceof Long) {
                    com_google_android_gms_internal_zzckz.zzjiq = (Long) obj;
                    return com_google_android_gms_internal_zzckzArr;
                } else if (obj instanceof String) {
                    com_google_android_gms_internal_zzckz.zzfzi = (String) obj;
                    return com_google_android_gms_internal_zzckzArr;
                } else if (!(obj instanceof Double)) {
                    return com_google_android_gms_internal_zzckzArr;
                } else {
                    com_google_android_gms_internal_zzckz.zzjgq = (Double) obj;
                    return com_google_android_gms_internal_zzckzArr;
                }
            }
        }
        Object obj2 = new zzckz[(com_google_android_gms_internal_zzckzArr.length + 1)];
        System.arraycopy(com_google_android_gms_internal_zzckzArr, 0, obj2, 0, com_google_android_gms_internal_zzckzArr.length);
        zzckz com_google_android_gms_internal_zzckz2 = new zzckz();
        com_google_android_gms_internal_zzckz2.name = str;
        if (obj instanceof Long) {
            com_google_android_gms_internal_zzckz2.zzjiq = (Long) obj;
        } else if (obj instanceof String) {
            com_google_android_gms_internal_zzckz2.zzfzi = (String) obj;
        } else if (obj instanceof Double) {
            com_google_android_gms_internal_zzckz2.zzjgq = (Double) obj;
        }
        obj2[com_google_android_gms_internal_zzckzArr.length] = com_google_android_gms_internal_zzckz2;
        return obj2;
    }

    public static Bundle[] zzae(Object obj) {
        if (obj instanceof Bundle) {
            return new Bundle[]{(Bundle) obj};
        } else if (obj instanceof Parcelable[]) {
            return (Bundle[]) Arrays.copyOf((Parcelable[]) obj, ((Parcelable[]) obj).length, Bundle[].class);
        } else {
            if (!(obj instanceof ArrayList)) {
                return null;
            }
            ArrayList arrayList = (ArrayList) obj;
            return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
        }
    }

    public static Object zzaf(Object obj) {
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        Throwable th;
        if (obj == null) {
            return null;
        }
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            } catch (Throwable th2) {
                th = th2;
                objectInputStream = null;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                throw th;
            }
            try {
                Object readObject = objectInputStream.readObject();
                try {
                    objectOutputStream.close();
                    objectInputStream.close();
                    return readObject;
                } catch (IOException e) {
                    return null;
                } catch (ClassNotFoundException e2) {
                    return null;
                }
            } catch (Throwable th3) {
                th = th3;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            objectInputStream = null;
            objectOutputStream = null;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            throw th;
        }
    }

    private final boolean zzag(Context context, String str) {
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = zzbgc.zzcy(context).getPackageInfo(str, 64);
            if (!(packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0)) {
                return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
            }
        } catch (CertificateException e) {
            zzawm().zzayr().zzj("Error obtaining certificate", e);
        } catch (NameNotFoundException e2) {
            zzawm().zzayr().zzj("Package name not found", e2);
        }
        return true;
    }

    private final boolean zzaq(String str, String str2) {
        if (str2 == null) {
            zzawm().zzayr().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzawm().zzayr().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt)) {
                int length = str2.length();
                codePointAt = Character.charCount(codePointAt);
                while (codePointAt < length) {
                    int codePointAt2 = str2.codePointAt(codePointAt);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        codePointAt += Character.charCount(codePointAt2);
                    } else {
                        zzawm().zzayr().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzawm().zzayr().zze("Name must start with a letter. Type, name", str, str2);
            return false;
        }
    }

    private final boolean zzar(String str, String str2) {
        if (str2 == null) {
            zzawm().zzayr().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzawm().zzayr().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt) || codePointAt == 95) {
                int length = str2.length();
                codePointAt = Character.charCount(codePointAt);
                while (codePointAt < length) {
                    int codePointAt2 = str2.codePointAt(codePointAt);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        codePointAt += Character.charCount(codePointAt2);
                    } else {
                        zzawm().zzayr().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzawm().zzayr().zze("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
    }

    public static boolean zzas(String str, String str2) {
        return (str == null && str2 == null) ? true : str == null ? false : str.equals(str2);
    }

    private static void zzb(Bundle bundle, Object obj) {
        zzbq.checkNotNull(bundle);
        if (obj == null) {
            return;
        }
        if ((obj instanceof String) || (obj instanceof CharSequence)) {
            bundle.putLong("_el", (long) String.valueOf(obj).length());
        }
    }

    private final boolean zzb(String str, int i, String str2) {
        if (str2 == null) {
            zzawm().zzayr().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.codePointCount(0, str2.length()) <= i) {
            return true;
        } else {
            zzawm().zzayr().zzd("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    private static boolean zzd(Bundle bundle, int i) {
        if (bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i);
        return true;
    }

    @WorkerThread
    static boolean zzd(zzcfx com_google_android_gms_internal_zzcfx, zzcff com_google_android_gms_internal_zzcff) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfx);
        zzbq.checkNotNull(com_google_android_gms_internal_zzcff);
        return !TextUtils.isEmpty(com_google_android_gms_internal_zzcff.zziux);
    }

    static MessageDigest zzed(String str) {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                if (instance != null) {
                    return instance;
                }
                i++;
            } catch (NoSuchAlgorithmException e) {
            }
        }
        return null;
    }

    static boolean zzjt(String str) {
        zzbq.zzgh(str);
        return str.charAt(0) != '_' || str.equals("_ep");
    }

    private final int zzjy(String str) {
        return !zzaq("event param", str) ? 3 : !zza("event param", null, str) ? 14 : zzb("event param", 40, str) ? 0 : 3;
    }

    private final int zzjz(String str) {
        return !zzar("event param", str) ? 3 : !zza("event param", null, str) ? 14 : zzb("event param", 40, str) ? 0 : 3;
    }

    private static int zzkb(String str) {
        return "_ldl".equals(str) ? 2048 : 36;
    }

    public static boolean zzkc(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
    }

    static boolean zzke(String str) {
        return str != null && str.matches("(\\+|-)?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    @WorkerThread
    static boolean zzkh(String str) {
        zzbq.zzgh(str);
        boolean z = true;
        switch (str.hashCode()) {
            case 94660:
                if (str.equals("_in")) {
                    z = false;
                    break;
                }
                break;
            case 95025:
                if (str.equals("_ug")) {
                    z = true;
                    break;
                }
                break;
            case 95027:
                if (str.equals("_ui")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
            case true:
            case true:
                return true;
            default:
                return false;
        }
    }

    public static boolean zzo(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    static long zzr(byte[] bArr) {
        long j = null;
        zzbq.checkNotNull(bArr);
        zzbq.checkState(bArr.length > 0);
        long j2 = 0;
        int length = bArr.length - 1;
        while (length >= 0 && length >= bArr.length - 8) {
            j2 += (((long) bArr[length]) & 255) << j;
            j += 8;
            length--;
        }
        return j2;
    }

    public static boolean zzt(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 4);
            return serviceInfo != null && serviceInfo.enabled;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final Bundle zza(String str, Bundle bundle, @Nullable List<String> list, boolean z, boolean z2) {
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = new Bundle(bundle);
        int i = 0;
        for (String str2 : bundle.keySet()) {
            int i2 = 0;
            if (list == null || !list.contains(str2)) {
                if (z) {
                    i2 = zzjy(str2);
                }
                if (i2 == 0) {
                    i2 = zzjz(str2);
                }
            }
            if (i2 != 0) {
                if (zzd(bundle2, i2)) {
                    bundle2.putString("_ev", zza(str2, 40, true));
                    if (i2 == 3) {
                        zzb(bundle2, (Object) str2);
                    }
                }
                bundle2.remove(str2);
            } else {
                i2 = zza(str2, bundle.get(str2), z2);
                if (i2 == 0 || "_ev".equals(str2)) {
                    if (zzjt(str2)) {
                        i++;
                        if (i > 25) {
                            zzawm().zzayr().zze("Event can't contain more then 25 params", zzawh().zzjb(str), zzawh().zzx(bundle));
                            zzd(bundle2, 5);
                            bundle2.remove(str2);
                        }
                    }
                    i = i;
                } else {
                    if (zzd(bundle2, i2)) {
                        bundle2.putString("_ev", zza(str2, 40, true));
                        zzb(bundle2, bundle.get(str2));
                    }
                    bundle2.remove(str2);
                }
            }
        }
        return bundle2;
    }

    final zzcfx zza(String str, Bundle bundle, String str2, long j, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (zzjv(str) != 0) {
            zzawm().zzayr().zzj("Invalid conditional property event name", zzawh().zzjd(str));
            throw new IllegalArgumentException();
        }
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        bundle2.putString("_o", str2);
        return new zzcfx(str, new zzcfu(zzy(zza(str, bundle2, Collections.singletonList("_o"), false, false))), str2, j);
    }

    public final void zza(int i, String str, String str2, int i2) {
        zza(null, i, str, str2, i2);
    }

    public final void zza(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (str != null) {
                zzawm().zzayu().zze("Not putting event parameter. Invalid value type. name, type", zzawh().zzjc(str), obj != null ? obj.getClass().getSimpleName() : null);
            }
        }
    }

    public final void zza(zzckz com_google_android_gms_internal_zzckz, Object obj) {
        zzbq.checkNotNull(obj);
        com_google_android_gms_internal_zzckz.zzfzi = null;
        com_google_android_gms_internal_zzckz.zzjiq = null;
        com_google_android_gms_internal_zzckz.zzjgq = null;
        if (obj instanceof String) {
            com_google_android_gms_internal_zzckz.zzfzi = (String) obj;
        } else if (obj instanceof Long) {
            com_google_android_gms_internal_zzckz.zzjiq = (Long) obj;
        } else if (obj instanceof Double) {
            com_google_android_gms_internal_zzckz.zzjgq = (Double) obj;
        } else {
            zzawm().zzayr().zzj("Ignoring invalid (type) event param value", obj);
        }
    }

    public final void zza(zzcld com_google_android_gms_internal_zzcld, Object obj) {
        zzbq.checkNotNull(obj);
        com_google_android_gms_internal_zzcld.zzfzi = null;
        com_google_android_gms_internal_zzcld.zzjiq = null;
        com_google_android_gms_internal_zzcld.zzjgq = null;
        if (obj instanceof String) {
            com_google_android_gms_internal_zzcld.zzfzi = (String) obj;
        } else if (obj instanceof Long) {
            com_google_android_gms_internal_zzcld.zzjiq = (Long) obj;
        } else if (obj instanceof Double) {
            com_google_android_gms_internal_zzcld.zzjgq = (Double) obj;
        } else {
            zzawm().zzayr().zzj("Ignoring invalid (type) user attribute value", obj);
        }
    }

    public final void zza(String str, int i, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        zzd(bundle, i);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString(str2, str3);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", (long) i2);
        }
        this.zzitk.zzawa().zzc(ReactScrollViewHelper.AUTO, "_err", bundle);
    }

    @WorkerThread
    final long zzaf(Context context, String str) {
        zzut();
        zzbq.checkNotNull(context);
        zzbq.zzgh(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest zzed = zzed(CommonUtils.MD5_INSTANCE);
        if (zzed == null) {
            zzawm().zzayr().log("Could not get MD5 instance");
            return -1;
        }
        if (packageManager != null) {
            try {
                if (!zzag(context, str)) {
                    PackageInfo packageInfo = zzbgc.zzcy(context).getPackageInfo(getContext().getPackageName(), 64);
                    if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                        return zzr(zzed.digest(packageInfo.signatures[0].toByteArray()));
                    }
                    zzawm().zzayt().log("Could not get signatures");
                    return -1;
                }
            } catch (NameNotFoundException e) {
                zzawm().zzayr().zzj("Package name not found", e);
            }
        }
        return 0;
    }

    public final /* bridge */ /* synthetic */ void zzavw() {
        super.zzavw();
    }

    public final /* bridge */ /* synthetic */ void zzavx() {
        super.zzavx();
    }

    public final /* bridge */ /* synthetic */ zzcfa zzavy() {
        return super.zzavy();
    }

    public final /* bridge */ /* synthetic */ zzcfh zzavz() {
        return super.zzavz();
    }

    public final /* bridge */ /* synthetic */ zzcik zzawa() {
        return super.zzawa();
    }

    public final /* bridge */ /* synthetic */ zzcge zzawb() {
        return super.zzawb();
    }

    public final /* bridge */ /* synthetic */ zzcfr zzawc() {
        return super.zzawc();
    }

    public final /* bridge */ /* synthetic */ zzcjd zzawd() {
        return super.zzawd();
    }

    public final /* bridge */ /* synthetic */ zzciz zzawe() {
        return super.zzawe();
    }

    public final /* bridge */ /* synthetic */ zzcgf zzawf() {
        return super.zzawf();
    }

    public final /* bridge */ /* synthetic */ zzcfl zzawg() {
        return super.zzawg();
    }

    public final /* bridge */ /* synthetic */ zzcgh zzawh() {
        return super.zzawh();
    }

    public final /* bridge */ /* synthetic */ zzckn zzawi() {
        return super.zzawi();
    }

    public final /* bridge */ /* synthetic */ zzchd zzawj() {
        return super.zzawj();
    }

    public final /* bridge */ /* synthetic */ zzckc zzawk() {
        return super.zzawk();
    }

    public final /* bridge */ /* synthetic */ zzche zzawl() {
        return super.zzawl();
    }

    public final /* bridge */ /* synthetic */ zzcgj zzawm() {
        return super.zzawm();
    }

    public final /* bridge */ /* synthetic */ zzcgu zzawn() {
        return super.zzawn();
    }

    public final /* bridge */ /* synthetic */ zzcfk zzawo() {
        return super.zzawo();
    }

    protected final boolean zzaxn() {
        return true;
    }

    @WorkerThread
    protected final void zzaym() {
        zzut();
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                zzawm().zzayt().log("Utils falling back to Random for random id");
            }
        }
        this.zzjgu.set(nextLong);
    }

    final <T extends Parcelable> T zzb(byte[] bArr, Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        T t;
        try {
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            t = (Parcelable) creator.createFromParcel(obtain);
            return t;
        } catch (zzbel e) {
            t = zzawm().zzayr();
            t.log("Failed to load parcelable from buffer");
            return null;
        } finally {
            obtain.recycle();
        }
    }

    public final byte[] zzb(zzcla com_google_android_gms_internal_zzcla) {
        try {
            byte[] bArr = new byte[com_google_android_gms_internal_zzcla.zzhl()];
            zzfhc zzo = zzfhc.zzo(bArr, 0, bArr.length);
            com_google_android_gms_internal_zzcla.zza(zzo);
            zzo.zzcus();
            return bArr;
        } catch (IOException e) {
            zzawm().zzayr().zzj("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    public final long zzbam() {
        long nextLong;
        if (this.zzjgu.get() == 0) {
            synchronized (this.zzjgu) {
                nextLong = new Random(System.nanoTime() ^ zzwh().currentTimeMillis()).nextLong();
                int i = this.zzjgv + 1;
                this.zzjgv = i;
                nextLong += (long) i;
            }
        } else {
            synchronized (this.zzjgu) {
                this.zzjgu.compareAndSet(-1, 1);
                nextLong = this.zzjgu.getAndIncrement();
            }
        }
        return nextLong;
    }

    @WorkerThread
    final SecureRandom zzban() {
        zzut();
        if (this.zzjgt == null) {
            this.zzjgt = new SecureRandom();
        }
        return this.zzjgt;
    }

    @WorkerThread
    public final boolean zzdu(String str) {
        zzut();
        if (zzbgc.zzcy(getContext()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzawm().zzayw().zzj("Permission not granted", str);
        return false;
    }

    public final boolean zzf(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzwh().currentTimeMillis() - j) > j2;
    }

    public final int zzju(String str) {
        return !zzaq(NotificationCompat.CATEGORY_EVENT, str) ? 2 : !zza(NotificationCompat.CATEGORY_EVENT, AppMeasurement$Event.zzitl, str) ? 13 : zzb(NotificationCompat.CATEGORY_EVENT, 40, str) ? 0 : 2;
    }

    public final int zzjv(String str) {
        return !zzar(NotificationCompat.CATEGORY_EVENT, str) ? 2 : !zza(NotificationCompat.CATEGORY_EVENT, AppMeasurement$Event.zzitl, str) ? 13 : zzb(NotificationCompat.CATEGORY_EVENT, 40, str) ? 0 : 2;
    }

    public final int zzjw(String str) {
        return !zzaq("user property", str) ? 6 : !zza("user property", AppMeasurement$UserProperty.zzits, str) ? 15 : zzb("user property", 24, str) ? 0 : 6;
    }

    public final int zzjx(String str) {
        return !zzar("user property", str) ? 6 : !zza("user property", AppMeasurement$UserProperty.zzits, str) ? 15 : zzb("user property", 24, str) ? 0 : 6;
    }

    public final Object zzk(String str, Object obj) {
        int i = 256;
        if ("_ev".equals(str)) {
            return zza(256, obj, true);
        }
        if (!zzkc(str)) {
            i = 100;
        }
        return zza(i, obj, false);
    }

    public final boolean zzka(String str) {
        if (TextUtils.isEmpty(str)) {
            zzawm().zzayr().log("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            return false;
        }
        zzbq.checkNotNull(str);
        if (str.matches("^1:\\d+:android:[a-f0-9]+$")) {
            return true;
        }
        zzawm().zzayr().zzj("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", str);
        return false;
    }

    public final boolean zzkd(String str) {
        return TextUtils.isEmpty(str) ? false : zzawo().zzaxr().equals(str);
    }

    final boolean zzkf(String str) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(zzawj().zzam(str, "measurement.upload.blacklist_internal"));
    }

    final boolean zzkg(String str) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(zzawj().zzam(str, "measurement.upload.blacklist_public"));
    }

    public final int zzl(String str, Object obj) {
        return "_ldl".equals(str) ? zza("user property referrer", str, zzkb(str), obj, false) : zza("user property", str, zzkb(str), obj, false) ? 0 : 7;
    }

    public final Object zzm(String str, Object obj) {
        return "_ldl".equals(str) ? zza(zzkb(str), obj, true) : zza(zzkb(str), obj, false);
    }

    public final Bundle zzp(@NonNull Uri uri) {
        Bundle bundle = null;
        if (uri != null) {
            try {
                Object queryParameter;
                Object queryParameter2;
                Object queryParameter3;
                Object queryParameter4;
                if (uri.isHierarchical()) {
                    queryParameter = uri.getQueryParameter("utm_campaign");
                    queryParameter2 = uri.getQueryParameter("utm_source");
                    queryParameter3 = uri.getQueryParameter("utm_medium");
                    queryParameter4 = uri.getQueryParameter("gclid");
                } else {
                    queryParameter4 = null;
                    queryParameter3 = null;
                    queryParameter2 = null;
                    queryParameter = null;
                }
                if (!(TextUtils.isEmpty(queryParameter) && TextUtils.isEmpty(queryParameter2) && TextUtils.isEmpty(queryParameter3) && TextUtils.isEmpty(queryParameter4))) {
                    bundle = new Bundle();
                    if (!TextUtils.isEmpty(queryParameter)) {
                        bundle.putString(Param.CAMPAIGN, queryParameter);
                    }
                    if (!TextUtils.isEmpty(queryParameter2)) {
                        bundle.putString(Param.SOURCE, queryParameter2);
                    }
                    if (!TextUtils.isEmpty(queryParameter3)) {
                        bundle.putString(Param.MEDIUM, queryParameter3);
                    }
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("gclid", queryParameter4);
                    }
                    queryParameter4 = uri.getQueryParameter("utm_term");
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString(Param.TERM, queryParameter4);
                    }
                    queryParameter4 = uri.getQueryParameter("utm_content");
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("content", queryParameter4);
                    }
                    queryParameter4 = uri.getQueryParameter(Param.ACLID);
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString(Param.ACLID, queryParameter4);
                    }
                    queryParameter4 = uri.getQueryParameter(Param.CP1);
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString(Param.CP1, queryParameter4);
                    }
                    queryParameter4 = uri.getQueryParameter("anid");
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("anid", queryParameter4);
                    }
                }
            } catch (UnsupportedOperationException e) {
                zzawm().zzayt().zzj("Install referrer url isn't a hierarchical URI", e);
            }
        }
        return bundle;
    }

    public final byte[] zzp(byte[] bArr) throws IOException {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzawm().zzayr().zzj("Failed to gzip content", e);
            throw e;
        }
    }

    public final byte[] zzq(byte[] bArr) throws IOException {
        try {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            zzawm().zzayr().zzj("Failed to ungzip content", e);
            throw e;
        }
    }

    public final /* bridge */ /* synthetic */ void zzut() {
        super.zzut();
    }

    public final /* bridge */ /* synthetic */ zzd zzwh() {
        return super.zzwh();
    }

    final Bundle zzy(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object zzk = zzk(str, bundle.get(str));
                if (zzk == null) {
                    zzawm().zzayt().zzj("Param value can't be null", zzawh().zzjc(str));
                } else {
                    zza(bundle2, str, zzk);
                }
            }
        }
        return bundle2;
    }
}
