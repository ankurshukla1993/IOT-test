package com.google.android.gms.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzcfh extends zzcii {
    zzcfh(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
    }

    private final Boolean zza(double d, zzckr com_google_android_gms_internal_zzckr) {
        try {
            return zza(new BigDecimal(d), com_google_android_gms_internal_zzckr, Math.ulp(d));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private final Boolean zza(long j, zzckr com_google_android_gms_internal_zzckr) {
        try {
            return zza(new BigDecimal(j), com_google_android_gms_internal_zzckr, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private final Boolean zza(zzckp com_google_android_gms_internal_zzckp, zzcky com_google_android_gms_internal_zzcky, long j) {
        Boolean zza;
        if (com_google_android_gms_internal_zzckp.zzjhf != null) {
            zza = zza(j, com_google_android_gms_internal_zzckp.zzjhf);
            if (zza == null) {
                return null;
            }
            if (!zza.booleanValue()) {
                return Boolean.valueOf(false);
            }
        }
        Set hashSet = new HashSet();
        for (zzckq com_google_android_gms_internal_zzckq : com_google_android_gms_internal_zzckp.zzjhd) {
            if (TextUtils.isEmpty(com_google_android_gms_internal_zzckq.zzjhk)) {
                zzawm().zzayt().zzj("null or empty param name in filter. event", zzawh().zzjb(com_google_android_gms_internal_zzcky.name));
                return null;
            }
            hashSet.add(com_google_android_gms_internal_zzckq.zzjhk);
        }
        Map arrayMap = new ArrayMap();
        for (zzckz com_google_android_gms_internal_zzckz : com_google_android_gms_internal_zzcky.zzjim) {
            if (hashSet.contains(com_google_android_gms_internal_zzckz.name)) {
                if (com_google_android_gms_internal_zzckz.zzjiq != null) {
                    arrayMap.put(com_google_android_gms_internal_zzckz.name, com_google_android_gms_internal_zzckz.zzjiq);
                } else if (com_google_android_gms_internal_zzckz.zzjgq != null) {
                    arrayMap.put(com_google_android_gms_internal_zzckz.name, com_google_android_gms_internal_zzckz.zzjgq);
                } else if (com_google_android_gms_internal_zzckz.zzfzi != null) {
                    arrayMap.put(com_google_android_gms_internal_zzckz.name, com_google_android_gms_internal_zzckz.zzfzi);
                } else {
                    zzawm().zzayt().zze("Unknown value for param. event, param", zzawh().zzjb(com_google_android_gms_internal_zzcky.name), zzawh().zzjc(com_google_android_gms_internal_zzckz.name));
                    return null;
                }
            }
        }
        for (zzckq com_google_android_gms_internal_zzckq2 : com_google_android_gms_internal_zzckp.zzjhd) {
            boolean equals = Boolean.TRUE.equals(com_google_android_gms_internal_zzckq2.zzjhj);
            String str = com_google_android_gms_internal_zzckq2.zzjhk;
            if (TextUtils.isEmpty(str)) {
                zzawm().zzayt().zzj("Event has empty param name. event", zzawh().zzjb(com_google_android_gms_internal_zzcky.name));
                return null;
            }
            Object obj = arrayMap.get(str);
            if (obj instanceof Long) {
                if (com_google_android_gms_internal_zzckq2.zzjhi == null) {
                    zzawm().zzayt().zze("No number filter for long param. event, param", zzawh().zzjb(com_google_android_gms_internal_zzcky.name), zzawh().zzjc(str));
                    return null;
                }
                zza = zza(((Long) obj).longValue(), com_google_android_gms_internal_zzckq2.zzjhi);
                if (zza == null) {
                    return null;
                }
                if (((!zza.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof Double) {
                if (com_google_android_gms_internal_zzckq2.zzjhi == null) {
                    zzawm().zzayt().zze("No number filter for double param. event, param", zzawh().zzjb(com_google_android_gms_internal_zzcky.name), zzawh().zzjc(str));
                    return null;
                }
                zza = zza(((Double) obj).doubleValue(), com_google_android_gms_internal_zzckq2.zzjhi);
                if (zza == null) {
                    return null;
                }
                if (((!zza.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof String) {
                if (com_google_android_gms_internal_zzckq2.zzjhh != null) {
                    zza = zza((String) obj, com_google_android_gms_internal_zzckq2.zzjhh);
                } else if (com_google_android_gms_internal_zzckq2.zzjhi == null) {
                    zzawm().zzayt().zze("No filter for String param. event, param", zzawh().zzjb(com_google_android_gms_internal_zzcky.name), zzawh().zzjc(str));
                    return null;
                } else if (zzckn.zzke((String) obj)) {
                    zza = zza((String) obj, com_google_android_gms_internal_zzckq2.zzjhi);
                } else {
                    zzawm().zzayt().zze("Invalid param value for number filter. event, param", zzawh().zzjb(com_google_android_gms_internal_zzcky.name), zzawh().zzjc(str));
                    return null;
                }
                if (zza == null) {
                    return null;
                }
                if (((!zza.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj == null) {
                zzawm().zzayx().zze("Missing param for filter. event, param", zzawh().zzjb(com_google_android_gms_internal_zzcky.name), zzawh().zzjc(str));
                return Boolean.valueOf(false);
            } else {
                zzawm().zzayt().zze("Unknown param type. event, param", zzawh().zzjb(com_google_android_gms_internal_zzcky.name), zzawh().zzjc(str));
                return null;
            }
        }
        return Boolean.valueOf(true);
    }

    private static Boolean zza(Boolean bool, boolean z) {
        return bool == null ? null : Boolean.valueOf(bool.booleanValue() ^ z);
    }

    private final Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!(z || i == 1)) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException e) {
                    zzawm().zzayt().zzj("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    private final Boolean zza(String str, zzckr com_google_android_gms_internal_zzckr) {
        Boolean bool = null;
        if (zzckn.zzke(str)) {
            try {
                bool = zza(new BigDecimal(str), com_google_android_gms_internal_zzckr, 0.0d);
            } catch (NumberFormatException e) {
            }
        }
        return bool;
    }

    private final Boolean zza(String str, zzckt com_google_android_gms_internal_zzckt) {
        int i = 0;
        String str2 = null;
        zzbq.checkNotNull(com_google_android_gms_internal_zzckt);
        if (str == null || com_google_android_gms_internal_zzckt.zzjht == null || com_google_android_gms_internal_zzckt.zzjht.intValue() == 0) {
            return null;
        }
        List list;
        if (com_google_android_gms_internal_zzckt.zzjht.intValue() == 6) {
            if (com_google_android_gms_internal_zzckt.zzjhw == null || com_google_android_gms_internal_zzckt.zzjhw.length == 0) {
                return null;
            }
        } else if (com_google_android_gms_internal_zzckt.zzjhu == null) {
            return null;
        }
        int intValue = com_google_android_gms_internal_zzckt.zzjht.intValue();
        boolean z = com_google_android_gms_internal_zzckt.zzjhv != null && com_google_android_gms_internal_zzckt.zzjhv.booleanValue();
        String toUpperCase = (z || intValue == 1 || intValue == 6) ? com_google_android_gms_internal_zzckt.zzjhu : com_google_android_gms_internal_zzckt.zzjhu.toUpperCase(Locale.ENGLISH);
        if (com_google_android_gms_internal_zzckt.zzjhw == null) {
            list = null;
        } else {
            String[] strArr = com_google_android_gms_internal_zzckt.zzjhw;
            if (z) {
                list = Arrays.asList(strArr);
            } else {
                list = new ArrayList();
                int length = strArr.length;
                while (i < length) {
                    list.add(strArr[i].toUpperCase(Locale.ENGLISH));
                    i++;
                }
            }
        }
        if (intValue == 1) {
            str2 = toUpperCase;
        }
        return zza(str, intValue, z, toUpperCase, list, str2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Boolean zza(java.math.BigDecimal r10, com.google.android.gms.internal.zzckr r11, double r12) {
        /*
        r8 = 4;
        r7 = -1;
        r1 = 0;
        r0 = 1;
        r2 = 0;
        com.google.android.gms.common.internal.zzbq.checkNotNull(r11);
        r3 = r11.zzjhl;
        if (r3 == 0) goto L_0x0014;
    L_0x000c:
        r3 = r11.zzjhl;
        r3 = r3.intValue();
        if (r3 != 0) goto L_0x0016;
    L_0x0014:
        r0 = r2;
    L_0x0015:
        return r0;
    L_0x0016:
        r3 = r11.zzjhl;
        r3 = r3.intValue();
        if (r3 != r8) goto L_0x0028;
    L_0x001e:
        r3 = r11.zzjho;
        if (r3 == 0) goto L_0x0026;
    L_0x0022:
        r3 = r11.zzjhp;
        if (r3 != 0) goto L_0x002e;
    L_0x0026:
        r0 = r2;
        goto L_0x0015;
    L_0x0028:
        r3 = r11.zzjhn;
        if (r3 != 0) goto L_0x002e;
    L_0x002c:
        r0 = r2;
        goto L_0x0015;
    L_0x002e:
        r3 = r11.zzjhl;
        r6 = r3.intValue();
        r3 = r11.zzjhl;
        r3 = r3.intValue();
        if (r3 != r8) goto L_0x0066;
    L_0x003c:
        r3 = r11.zzjho;
        r3 = com.google.android.gms.internal.zzckn.zzke(r3);
        if (r3 == 0) goto L_0x004c;
    L_0x0044:
        r3 = r11.zzjhp;
        r3 = com.google.android.gms.internal.zzckn.zzke(r3);
        if (r3 != 0) goto L_0x004e;
    L_0x004c:
        r0 = r2;
        goto L_0x0015;
    L_0x004e:
        r4 = new java.math.BigDecimal;	 Catch:{ NumberFormatException -> 0x0063 }
        r3 = r11.zzjho;	 Catch:{ NumberFormatException -> 0x0063 }
        r4.<init>(r3);	 Catch:{ NumberFormatException -> 0x0063 }
        r3 = new java.math.BigDecimal;	 Catch:{ NumberFormatException -> 0x0063 }
        r5 = r11.zzjhp;	 Catch:{ NumberFormatException -> 0x0063 }
        r3.<init>(r5);	 Catch:{ NumberFormatException -> 0x0063 }
        r5 = r2;
    L_0x005d:
        if (r6 != r8) goto L_0x007e;
    L_0x005f:
        if (r4 != 0) goto L_0x0080;
    L_0x0061:
        r0 = r2;
        goto L_0x0015;
    L_0x0063:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0015;
    L_0x0066:
        r3 = r11.zzjhn;
        r3 = com.google.android.gms.internal.zzckn.zzke(r3);
        if (r3 != 0) goto L_0x0070;
    L_0x006e:
        r0 = r2;
        goto L_0x0015;
    L_0x0070:
        r3 = new java.math.BigDecimal;	 Catch:{ NumberFormatException -> 0x007b }
        r4 = r11.zzjhn;	 Catch:{ NumberFormatException -> 0x007b }
        r3.<init>(r4);	 Catch:{ NumberFormatException -> 0x007b }
        r4 = r2;
        r5 = r3;
        r3 = r2;
        goto L_0x005d;
    L_0x007b:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0015;
    L_0x007e:
        if (r5 == 0) goto L_0x0083;
    L_0x0080:
        switch(r6) {
            case 1: goto L_0x0085;
            case 2: goto L_0x0092;
            case 3: goto L_0x00a0;
            case 4: goto L_0x00ee;
            default: goto L_0x0083;
        };
    L_0x0083:
        r0 = r2;
        goto L_0x0015;
    L_0x0085:
        r2 = r10.compareTo(r5);
        if (r2 != r7) goto L_0x0090;
    L_0x008b:
        r0 = java.lang.Boolean.valueOf(r0);
        goto L_0x0015;
    L_0x0090:
        r0 = r1;
        goto L_0x008b;
    L_0x0092:
        r2 = r10.compareTo(r5);
        if (r2 != r0) goto L_0x009e;
    L_0x0098:
        r0 = java.lang.Boolean.valueOf(r0);
        goto L_0x0015;
    L_0x009e:
        r0 = r1;
        goto L_0x0098;
    L_0x00a0:
        r2 = 0;
        r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1));
        if (r2 == 0) goto L_0x00e0;
    L_0x00a6:
        r2 = new java.math.BigDecimal;
        r2.<init>(r12);
        r3 = new java.math.BigDecimal;
        r4 = 2;
        r3.<init>(r4);
        r2 = r2.multiply(r3);
        r2 = r5.subtract(r2);
        r2 = r10.compareTo(r2);
        if (r2 != r0) goto L_0x00de;
    L_0x00bf:
        r2 = new java.math.BigDecimal;
        r2.<init>(r12);
        r3 = new java.math.BigDecimal;
        r4 = 2;
        r3.<init>(r4);
        r2 = r2.multiply(r3);
        r2 = r5.add(r2);
        r2 = r10.compareTo(r2);
        if (r2 != r7) goto L_0x00de;
    L_0x00d8:
        r0 = java.lang.Boolean.valueOf(r0);
        goto L_0x0015;
    L_0x00de:
        r0 = r1;
        goto L_0x00d8;
    L_0x00e0:
        r2 = r10.compareTo(r5);
        if (r2 != 0) goto L_0x00ec;
    L_0x00e6:
        r0 = java.lang.Boolean.valueOf(r0);
        goto L_0x0015;
    L_0x00ec:
        r0 = r1;
        goto L_0x00e6;
    L_0x00ee:
        r2 = r10.compareTo(r4);
        if (r2 == r7) goto L_0x0100;
    L_0x00f4:
        r2 = r10.compareTo(r3);
        if (r2 == r0) goto L_0x0100;
    L_0x00fa:
        r0 = java.lang.Boolean.valueOf(r0);
        goto L_0x0015;
    L_0x0100:
        r0 = r1;
        goto L_0x00fa;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcfh.zza(java.math.BigDecimal, com.google.android.gms.internal.zzckr, double):java.lang.Boolean");
    }

    @WorkerThread
    final zzckx[] zza(String str, zzcky[] com_google_android_gms_internal_zzckyArr, zzcld[] com_google_android_gms_internal_zzcldArr) {
        int intValue;
        BitSet bitSet;
        BitSet bitSet2;
        Map map;
        Map map2;
        zzbq.zzgh(str);
        HashSet hashSet = new HashSet();
        ArrayMap arrayMap = new ArrayMap();
        Map arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        Map zziy = zzawg().zziy(str);
        if (zziy != null) {
            for (Integer intValue2 : zziy.keySet()) {
                intValue = intValue2.intValue();
                zzclc com_google_android_gms_internal_zzclc = (zzclc) zziy.get(Integer.valueOf(intValue));
                bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue));
                bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue));
                if (bitSet == null) {
                    bitSet = new BitSet();
                    arrayMap2.put(Integer.valueOf(intValue), bitSet);
                    bitSet2 = new BitSet();
                    arrayMap3.put(Integer.valueOf(intValue), bitSet2);
                }
                for (int i = 0; i < (com_google_android_gms_internal_zzclc.zzjju.length << 6); i++) {
                    if (zzckn.zza(com_google_android_gms_internal_zzclc.zzjju, i)) {
                        zzawm().zzayx().zze("Filter already evaluated. audience ID, filter ID", Integer.valueOf(intValue), Integer.valueOf(i));
                        bitSet2.set(i);
                        if (zzckn.zza(com_google_android_gms_internal_zzclc.zzjjv, i)) {
                            bitSet.set(i);
                        }
                    }
                }
                zzckx com_google_android_gms_internal_zzckx = new zzckx();
                arrayMap.put(Integer.valueOf(intValue), com_google_android_gms_internal_zzckx);
                com_google_android_gms_internal_zzckx.zzjik = Boolean.valueOf(false);
                com_google_android_gms_internal_zzckx.zzjij = com_google_android_gms_internal_zzclc;
                com_google_android_gms_internal_zzckx.zzjii = new zzclc();
                com_google_android_gms_internal_zzckx.zzjii.zzjjv = zzckn.zza(bitSet);
                com_google_android_gms_internal_zzckx.zzjii.zzjju = zzckn.zza(bitSet2);
            }
        }
        if (com_google_android_gms_internal_zzckyArr != null) {
            ArrayMap arrayMap4 = new ArrayMap();
            for (zzcky com_google_android_gms_internal_zzcky : com_google_android_gms_internal_zzckyArr) {
                zzcft com_google_android_gms_internal_zzcft;
                zzcft zzae = zzawg().zzae(str, com_google_android_gms_internal_zzcky.name);
                if (zzae == null) {
                    zzawm().zzayt().zze("Event aggregate wasn't created during raw event logging. appId, event", zzcgj.zzje(str), zzawh().zzjb(com_google_android_gms_internal_zzcky.name));
                    com_google_android_gms_internal_zzcft = new zzcft(str, com_google_android_gms_internal_zzcky.name, 1, 1, com_google_android_gms_internal_zzcky.zzjin.longValue(), 0, null, null, null);
                } else {
                    com_google_android_gms_internal_zzcft = zzae.zzayk();
                }
                zzawg().zza(com_google_android_gms_internal_zzcft);
                long j = com_google_android_gms_internal_zzcft.zziwp;
                map = (Map) arrayMap4.get(com_google_android_gms_internal_zzcky.name);
                if (map == null) {
                    map = zzawg().zzaj(str, com_google_android_gms_internal_zzcky.name);
                    if (map == null) {
                        map = new ArrayMap();
                    }
                    arrayMap4.put(com_google_android_gms_internal_zzcky.name, map);
                    map2 = map;
                } else {
                    map2 = map;
                }
                for (Integer intValue22 : r7.keySet()) {
                    int intValue3 = intValue22.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue3))) {
                        zzawm().zzayx().zzj("Skipping failed audience ID", Integer.valueOf(intValue3));
                    } else {
                        bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue3));
                        bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue3));
                        if (((zzckx) arrayMap.get(Integer.valueOf(intValue3))) == null) {
                            zzckx com_google_android_gms_internal_zzckx2 = new zzckx();
                            arrayMap.put(Integer.valueOf(intValue3), com_google_android_gms_internal_zzckx2);
                            com_google_android_gms_internal_zzckx2.zzjik = Boolean.valueOf(true);
                            bitSet = new BitSet();
                            arrayMap2.put(Integer.valueOf(intValue3), bitSet);
                            bitSet2 = new BitSet();
                            arrayMap3.put(Integer.valueOf(intValue3), bitSet2);
                        }
                        for (zzckp com_google_android_gms_internal_zzckp : (List) r7.get(Integer.valueOf(intValue3))) {
                            if (zzawm().zzae(2)) {
                                zzawm().zzayx().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(intValue3), com_google_android_gms_internal_zzckp.zzjhb, zzawh().zzjb(com_google_android_gms_internal_zzckp.zzjhc));
                                zzawm().zzayx().zzj("Filter definition", zzawh().zza(com_google_android_gms_internal_zzckp));
                            }
                            if (com_google_android_gms_internal_zzckp.zzjhb == null || com_google_android_gms_internal_zzckp.zzjhb.intValue() > 256) {
                                zzawm().zzayt().zze("Invalid event filter ID. appId, id", zzcgj.zzje(str), String.valueOf(com_google_android_gms_internal_zzckp.zzjhb));
                            } else if (bitSet.get(com_google_android_gms_internal_zzckp.zzjhb.intValue())) {
                                zzawm().zzayx().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue3), com_google_android_gms_internal_zzckp.zzjhb);
                            } else {
                                Object obj;
                                Boolean zza = zza(com_google_android_gms_internal_zzckp, com_google_android_gms_internal_zzcky, j);
                                zzcgl zzayx = zzawm().zzayx();
                                String str2 = "Event filter result";
                                if (zza == null) {
                                    obj = "null";
                                } else {
                                    Boolean bool = zza;
                                }
                                zzayx.zzj(str2, obj);
                                if (zza == null) {
                                    hashSet.add(Integer.valueOf(intValue3));
                                } else {
                                    bitSet2.set(com_google_android_gms_internal_zzckp.zzjhb.intValue());
                                    if (zza.booleanValue()) {
                                        bitSet.set(com_google_android_gms_internal_zzckp.zzjhb.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (com_google_android_gms_internal_zzcldArr != null) {
            Map arrayMap5 = new ArrayMap();
            for (zzcld com_google_android_gms_internal_zzcld : com_google_android_gms_internal_zzcldArr) {
                map = (Map) arrayMap5.get(com_google_android_gms_internal_zzcld.name);
                if (map == null) {
                    map = zzawg().zzak(str, com_google_android_gms_internal_zzcld.name);
                    if (map == null) {
                        map = new ArrayMap();
                    }
                    arrayMap5.put(com_google_android_gms_internal_zzcld.name, map);
                    map2 = map;
                } else {
                    map2 = map;
                }
                for (Integer intValue222 : r7.keySet()) {
                    int intValue4 = intValue222.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue4))) {
                        zzawm().zzayx().zzj("Skipping failed audience ID", Integer.valueOf(intValue4));
                    } else {
                        bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue4));
                        bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue4));
                        if (((zzckx) arrayMap.get(Integer.valueOf(intValue4))) == null) {
                            com_google_android_gms_internal_zzckx2 = new zzckx();
                            arrayMap.put(Integer.valueOf(intValue4), com_google_android_gms_internal_zzckx2);
                            com_google_android_gms_internal_zzckx2.zzjik = Boolean.valueOf(true);
                            bitSet = new BitSet();
                            arrayMap2.put(Integer.valueOf(intValue4), bitSet);
                            bitSet2 = new BitSet();
                            arrayMap3.put(Integer.valueOf(intValue4), bitSet2);
                        }
                        for (zzcks com_google_android_gms_internal_zzcks : (List) r7.get(Integer.valueOf(intValue4))) {
                            if (zzawm().zzae(2)) {
                                zzawm().zzayx().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(intValue4), com_google_android_gms_internal_zzcks.zzjhb, zzawh().zzjd(com_google_android_gms_internal_zzcks.zzjhr));
                                zzawm().zzayx().zzj("Filter definition", zzawh().zza(com_google_android_gms_internal_zzcks));
                            }
                            if (com_google_android_gms_internal_zzcks.zzjhb == null || com_google_android_gms_internal_zzcks.zzjhb.intValue() > 256) {
                                zzawm().zzayt().zze("Invalid property filter ID. appId, id", zzcgj.zzje(str), String.valueOf(com_google_android_gms_internal_zzcks.zzjhb));
                                hashSet.add(Integer.valueOf(intValue4));
                                break;
                            } else if (bitSet.get(com_google_android_gms_internal_zzcks.zzjhb.intValue())) {
                                zzawm().zzayx().zze("Property filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue4), com_google_android_gms_internal_zzcks.zzjhb);
                            } else {
                                Object obj2;
                                zzckq com_google_android_gms_internal_zzckq = com_google_android_gms_internal_zzcks.zzjhs;
                                if (com_google_android_gms_internal_zzckq == null) {
                                    zzawm().zzayt().zzj("Missing property filter. property", zzawh().zzjd(com_google_android_gms_internal_zzcld.name));
                                    bool = null;
                                } else {
                                    boolean equals = Boolean.TRUE.equals(com_google_android_gms_internal_zzckq.zzjhj);
                                    if (com_google_android_gms_internal_zzcld.zzjiq != null) {
                                        if (com_google_android_gms_internal_zzckq.zzjhi == null) {
                                            zzawm().zzayt().zzj("No number filter for long property. property", zzawh().zzjd(com_google_android_gms_internal_zzcld.name));
                                            bool = null;
                                        } else {
                                            bool = zza(zza(com_google_android_gms_internal_zzcld.zzjiq.longValue(), com_google_android_gms_internal_zzckq.zzjhi), equals);
                                        }
                                    } else if (com_google_android_gms_internal_zzcld.zzjgq != null) {
                                        if (com_google_android_gms_internal_zzckq.zzjhi == null) {
                                            zzawm().zzayt().zzj("No number filter for double property. property", zzawh().zzjd(com_google_android_gms_internal_zzcld.name));
                                            bool = null;
                                        } else {
                                            bool = zza(zza(com_google_android_gms_internal_zzcld.zzjgq.doubleValue(), com_google_android_gms_internal_zzckq.zzjhi), equals);
                                        }
                                    } else if (com_google_android_gms_internal_zzcld.zzfzi == null) {
                                        zzawm().zzayt().zzj("User property has no value, property", zzawh().zzjd(com_google_android_gms_internal_zzcld.name));
                                        bool = null;
                                    } else if (com_google_android_gms_internal_zzckq.zzjhh == null) {
                                        if (com_google_android_gms_internal_zzckq.zzjhi == null) {
                                            zzawm().zzayt().zzj("No string or number filter defined. property", zzawh().zzjd(com_google_android_gms_internal_zzcld.name));
                                        } else if (zzckn.zzke(com_google_android_gms_internal_zzcld.zzfzi)) {
                                            bool = zza(zza(com_google_android_gms_internal_zzcld.zzfzi, com_google_android_gms_internal_zzckq.zzjhi), equals);
                                        } else {
                                            zzawm().zzayt().zze("Invalid user property value for Numeric number filter. property, value", zzawh().zzjd(com_google_android_gms_internal_zzcld.name), com_google_android_gms_internal_zzcld.zzfzi);
                                        }
                                        bool = null;
                                    } else {
                                        bool = zza(zza(com_google_android_gms_internal_zzcld.zzfzi, com_google_android_gms_internal_zzckq.zzjhh), equals);
                                    }
                                }
                                zzcgl zzayx2 = zzawm().zzayx();
                                String str3 = "Property filter result";
                                if (bool == null) {
                                    obj2 = "null";
                                } else {
                                    zza = bool;
                                }
                                zzayx2.zzj(str3, obj2);
                                if (bool == null) {
                                    hashSet.add(Integer.valueOf(intValue4));
                                } else {
                                    bitSet2.set(com_google_android_gms_internal_zzcks.zzjhb.intValue());
                                    if (bool.booleanValue()) {
                                        bitSet.set(com_google_android_gms_internal_zzcks.zzjhb.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        zzckx[] com_google_android_gms_internal_zzckxArr = new zzckx[arrayMap2.size()];
        int i2 = 0;
        for (Integer intValue2222 : arrayMap2.keySet()) {
            intValue = intValue2222.intValue();
            if (!hashSet.contains(Integer.valueOf(intValue))) {
                com_google_android_gms_internal_zzckx2 = (zzckx) arrayMap.get(Integer.valueOf(intValue));
                com_google_android_gms_internal_zzckx = com_google_android_gms_internal_zzckx2 == null ? new zzckx() : com_google_android_gms_internal_zzckx2;
                int i3 = i2 + 1;
                com_google_android_gms_internal_zzckxArr[i2] = com_google_android_gms_internal_zzckx;
                com_google_android_gms_internal_zzckx.zzjgx = Integer.valueOf(intValue);
                com_google_android_gms_internal_zzckx.zzjii = new zzclc();
                com_google_android_gms_internal_zzckx.zzjii.zzjjv = zzckn.zza((BitSet) arrayMap2.get(Integer.valueOf(intValue)));
                com_google_android_gms_internal_zzckx.zzjii.zzjju = zzckn.zza((BitSet) arrayMap3.get(Integer.valueOf(intValue)));
                zzcih zzawg = zzawg();
                zzfhk com_google_android_gms_internal_zzfhk = com_google_android_gms_internal_zzckx.zzjii;
                zzawg.zzwu();
                zzawg.zzut();
                zzbq.zzgh(str);
                zzbq.checkNotNull(com_google_android_gms_internal_zzfhk);
                try {
                    byte[] bArr = new byte[com_google_android_gms_internal_zzfhk.zzhl()];
                    zzfhc zzo = zzfhc.zzo(bArr, 0, bArr.length);
                    com_google_android_gms_internal_zzfhk.zza(zzo);
                    zzo.zzcus();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("app_id", str);
                    contentValues.put("audience_id", Integer.valueOf(intValue));
                    contentValues.put("current_results", bArr);
                    try {
                        if (zzawg.getWritableDatabase().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                            zzawg.zzawm().zzayr().zzj("Failed to insert filter results (got -1). appId", zzcgj.zzje(str));
                        }
                        i2 = i3;
                    } catch (SQLiteException e) {
                        zzawg.zzawm().zzayr().zze("Error storing filter results. appId", zzcgj.zzje(str), e);
                        i2 = i3;
                    }
                } catch (IOException e2) {
                    zzawg.zzawm().zzayr().zze("Configuration loss. Failed to serialize filter results. appId", zzcgj.zzje(str), e2);
                    i2 = i3;
                }
            }
        }
        return (zzckx[]) Arrays.copyOf(com_google_android_gms_internal_zzckxArr, i2);
    }

    protected final boolean zzaxn() {
        return false;
    }
}
