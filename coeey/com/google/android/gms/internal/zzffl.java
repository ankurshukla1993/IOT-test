package com.google.android.gms.internal;

import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import kotlin.text.Typography;

final class zzffl {
    static String zza(zzffi com_google_android_gms_internal_zzffi, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("# ").append(str);
        zza(com_google_android_gms_internal_zzffi, stringBuilder, 0);
        return stringBuilder.toString();
    }

    private static void zza(zzffi com_google_android_gms_internal_zzffi, StringBuilder stringBuilder, int i) {
        Map hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        Set<String> treeSet = new TreeSet();
        for (Method method : com_google_android_gms_internal_zzffi.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String replaceFirst : treeSet) {
            String replaceFirst2;
            String valueOf;
            Method method2;
            String replaceFirst3 = replaceFirst2.replaceFirst("get", "");
            if (replaceFirst3.endsWith("List") && !replaceFirst3.endsWith("OrBuilderList")) {
                valueOf = String.valueOf(replaceFirst3.substring(0, 1).toLowerCase());
                replaceFirst2 = String.valueOf(replaceFirst3.substring(1, replaceFirst3.length() - 4));
                valueOf = replaceFirst2.length() != 0 ? valueOf.concat(replaceFirst2) : new String(valueOf);
                String str = "get";
                replaceFirst2 = String.valueOf(replaceFirst3);
                method2 = (Method) hashMap.get(replaceFirst2.length() != 0 ? str.concat(replaceFirst2) : new String(str));
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zzb(stringBuilder, i, zztf(valueOf), zzfee.zza(method2, (Object) com_google_android_gms_internal_zzffi, new Object[0]));
                }
            }
            valueOf = "set";
            replaceFirst2 = String.valueOf(replaceFirst3);
            if (((Method) hashMap2.get(replaceFirst2.length() != 0 ? valueOf.concat(replaceFirst2) : new String(valueOf))) != null) {
                if (replaceFirst3.endsWith("Bytes")) {
                    valueOf = "get";
                    replaceFirst2 = String.valueOf(replaceFirst3.substring(0, replaceFirst3.length() - 5));
                    if (hashMap.containsKey(replaceFirst2.length() != 0 ? valueOf.concat(replaceFirst2) : new String(valueOf))) {
                    }
                }
                valueOf = String.valueOf(replaceFirst3.substring(0, 1).toLowerCase());
                replaceFirst2 = String.valueOf(replaceFirst3.substring(1));
                str = replaceFirst2.length() != 0 ? valueOf.concat(replaceFirst2) : new String(valueOf);
                valueOf = "get";
                replaceFirst2 = String.valueOf(replaceFirst3);
                method2 = (Method) hashMap.get(replaceFirst2.length() != 0 ? valueOf.concat(replaceFirst2) : new String(valueOf));
                String str2 = "has";
                valueOf = String.valueOf(replaceFirst3);
                Method method3 = (Method) hashMap.get(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                if (method2 != null) {
                    boolean equals;
                    zzffi zza = zzfee.zza(method2, (Object) com_google_android_gms_internal_zzffi, new Object[0]);
                    if (method3 == null) {
                        equals = zza instanceof Boolean ? !((Boolean) zza).booleanValue() : zza instanceof Integer ? ((Integer) zza).intValue() == 0 : zza instanceof Float ? ((Float) zza).floatValue() == 0.0f : zza instanceof Double ? ((Double) zza).doubleValue() == 0.0d : zza instanceof String ? zza.equals("") : zza instanceof zzfdh ? zza.equals(zzfdh.zzpal) : zza instanceof zzffi ? zza == ((zzffi) zza).zzcvh() : zza instanceof Enum ? ((Enum) zza).ordinal() == 0 : false;
                        equals = !equals;
                    } else {
                        equals = ((Boolean) zzfee.zza(method3, (Object) com_google_android_gms_internal_zzffi, new Object[0])).booleanValue();
                    }
                    if (equals) {
                        zzb(stringBuilder, i, zztf(str), zza);
                    }
                }
            }
        }
        if (com_google_android_gms_internal_zzffi instanceof zzfej) {
            Iterator it = ((zzfej) com_google_android_gms_internal_zzffi).zzpbz.iterator();
            if (it.hasNext()) {
                ((Entry) it.next()).getKey();
                throw new NoSuchMethodError();
            }
        }
        if (((zzfee) com_google_android_gms_internal_zzffi).zzpbs != null) {
            ((zzfee) com_google_android_gms_internal_zzffi).zzpbs.zzd(stringBuilder, i);
        }
    }

    static final void zzb(StringBuilder stringBuilder, int i, String str, Object obj) {
        int i2 = 0;
        if (obj instanceof List) {
            for (Object zzb : (List) obj) {
                zzb(stringBuilder, i, str, zzb);
            }
            return;
        }
        stringBuilder.append('\n');
        for (int i3 = 0; i3 < i; i3++) {
            stringBuilder.append(' ');
        }
        stringBuilder.append(str);
        if (obj instanceof String) {
            stringBuilder.append(": \"").append(zzfgc.zzaq(zzfdh.zztb((String) obj))).append(Typography.quote);
        } else if (obj instanceof zzfdh) {
            stringBuilder.append(": \"").append(zzfgc.zzaq((zzfdh) obj)).append(Typography.quote);
        } else if (obj instanceof zzfee) {
            stringBuilder.append(" {");
            zza((zzfee) obj, stringBuilder, i + 2);
            stringBuilder.append("\n");
            while (i2 < i) {
                stringBuilder.append(' ');
                i2++;
            }
            stringBuilder.append("}");
        } else {
            stringBuilder.append(": ").append(obj.toString());
        }
    }

    private static final String zztf(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                stringBuilder.append(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            }
            stringBuilder.append(Character.toLowerCase(charAt));
        }
        return stringBuilder.toString();
    }
}
