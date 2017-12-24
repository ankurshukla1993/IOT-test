package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzb;
import com.google.android.gms.common.util.zzo;
import com.google.android.gms.common.util.zzp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class zzbfk {
    protected static <O, I> I zza(zzbfl<I, O> com_google_android_gms_internal_zzbfl_I__O, Object obj) {
        return com_google_android_gms_internal_zzbfl_I__O.zzfzt != null ? com_google_android_gms_internal_zzbfl_I__O.convertBack(obj) : obj;
    }

    private static void zza(StringBuilder stringBuilder, zzbfl com_google_android_gms_internal_zzbfl, Object obj) {
        if (com_google_android_gms_internal_zzbfl.zzfzk == 11) {
            stringBuilder.append(((zzbfk) com_google_android_gms_internal_zzbfl.zzfzq.cast(obj)).toString());
        } else if (com_google_android_gms_internal_zzbfl.zzfzk == 7) {
            stringBuilder.append("\"");
            stringBuilder.append(zzo.zzgm((String) obj));
            stringBuilder.append("\"");
        } else {
            stringBuilder.append(obj);
        }
    }

    private static void zza(StringBuilder stringBuilder, zzbfl com_google_android_gms_internal_zzbfl, ArrayList<Object> arrayList) {
        stringBuilder.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                zza(stringBuilder, com_google_android_gms_internal_zzbfl, obj);
            }
        }
        stringBuilder.append("]");
    }

    public String toString() {
        Map zzaaj = zzaaj();
        StringBuilder stringBuilder = new StringBuilder(100);
        for (String str : zzaaj.keySet()) {
            zzbfl com_google_android_gms_internal_zzbfl = (zzbfl) zzaaj.get(str);
            if (zza(com_google_android_gms_internal_zzbfl)) {
                Object zza = zza(com_google_android_gms_internal_zzbfl, zzb(com_google_android_gms_internal_zzbfl));
                if (stringBuilder.length() == 0) {
                    stringBuilder.append("{");
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append("\"").append(str).append("\":");
                if (zza != null) {
                    switch (com_google_android_gms_internal_zzbfl.zzfzm) {
                        case 8:
                            stringBuilder.append("\"").append(zzb.zzj((byte[]) zza)).append("\"");
                            break;
                        case 9:
                            stringBuilder.append("\"").append(zzb.zzk((byte[]) zza)).append("\"");
                            break;
                        case 10:
                            zzp.zza(stringBuilder, (HashMap) zza);
                            break;
                        default:
                            if (!com_google_android_gms_internal_zzbfl.zzfzl) {
                                zza(stringBuilder, com_google_android_gms_internal_zzbfl, zza);
                                break;
                            }
                            zza(stringBuilder, com_google_android_gms_internal_zzbfl, (ArrayList) zza);
                            break;
                    }
                }
                stringBuilder.append("null");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append("}");
        } else {
            stringBuilder.append("{}");
        }
        return stringBuilder.toString();
    }

    protected boolean zza(zzbfl com_google_android_gms_internal_zzbfl) {
        if (com_google_android_gms_internal_zzbfl.zzfzm != 11) {
            return zzgk(com_google_android_gms_internal_zzbfl.zzfzo);
        }
        if (com_google_android_gms_internal_zzbfl.zzfzn) {
            String str = com_google_android_gms_internal_zzbfl.zzfzo;
            throw new UnsupportedOperationException("Concrete type arrays not supported");
        }
        str = com_google_android_gms_internal_zzbfl.zzfzo;
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    public abstract Map<String, zzbfl<?, ?>> zzaaj();

    protected Object zzb(zzbfl com_google_android_gms_internal_zzbfl) {
        String str = com_google_android_gms_internal_zzbfl.zzfzo;
        if (com_google_android_gms_internal_zzbfl.zzfzq == null) {
            return zzgj(com_google_android_gms_internal_zzbfl.zzfzo);
        }
        zzgj(com_google_android_gms_internal_zzbfl.zzfzo);
        zzbq.zza(true, "Concrete field shouldn't be value object: %s", com_google_android_gms_internal_zzbfl.zzfzo);
        boolean z = com_google_android_gms_internal_zzbfl.zzfzn;
        try {
            char toUpperCase = Character.toUpperCase(str.charAt(0));
            str = str.substring(1);
            return getClass().getMethod(new StringBuilder(String.valueOf(str).length() + 4).append("get").append(toUpperCase).append(str).toString(), new Class[0]).invoke(this, new Object[0]);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract Object zzgj(String str);

    protected abstract boolean zzgk(String str);
}
