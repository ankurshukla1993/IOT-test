package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public final class PasswordSpecification extends zzbej implements ReflectedParcelable {
    public static final Creator<PasswordSpecification> CREATOR = new zzi();
    public static final PasswordSpecification zzecu = new zza().zzj(12, 16).zzel("abcdefghijkmnopqrstxyzABCDEFGHJKLMNPQRSTXY3456789").zze("abcdefghijkmnopqrstxyz", 1).zze("ABCDEFGHJKLMNPQRSTXY", 1).zze("3456789", 1).zzaak();
    private static PasswordSpecification zzecv = new zza().zzj(12, 16).zzel("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890").zze("abcdefghijklmnopqrstuvwxyz", 1).zze("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1).zze("1234567890", 1).zzaak();
    private final Random zzbeb;
    @VisibleForTesting
    private String zzecw;
    @VisibleForTesting
    private List<String> zzecx;
    @VisibleForTesting
    private List<Integer> zzecy;
    @VisibleForTesting
    private int zzecz;
    @VisibleForTesting
    private int zzeda;
    private final int[] zzedb;

    public static class zza {
        private final List<String> zzecx = new ArrayList();
        private final List<Integer> zzecy = new ArrayList();
        private int zzecz = 12;
        private int zzeda = 16;
        private final TreeSet<Character> zzedc = new TreeSet();

        private static TreeSet<Character> zzn(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                throw new zzb(String.valueOf(str2).concat(" cannot be null or empty"));
            }
            TreeSet<Character> treeSet = new TreeSet();
            for (char c : str.toCharArray()) {
                if (PasswordSpecification.zzc(c, 32, 126)) {
                    throw new zzb(String.valueOf(str2).concat(" must only contain ASCII printable characters"));
                }
                treeSet.add(Character.valueOf(c));
            }
            return treeSet;
        }

        public final PasswordSpecification zzaak() {
            if (this.zzedc.isEmpty()) {
                throw new zzb("no allowed characters specified");
            }
            int i = 0;
            for (Integer intValue : this.zzecy) {
                i = intValue.intValue() + i;
            }
            if (i > this.zzeda) {
                throw new zzb("required character count cannot be greater than the max password size");
            }
            boolean[] zArr = new boolean[95];
            for (String toCharArray : this.zzecx) {
                for (char c : toCharArray.toCharArray()) {
                    if (zArr[c - 32]) {
                        throw new zzb("character " + c + " occurs in more than one required character set");
                    }
                    zArr[c - 32] = true;
                }
            }
            return new PasswordSpecification(PasswordSpecification.zzb(this.zzedc), this.zzecx, this.zzecy, this.zzecz, this.zzeda);
        }

        public final zza zze(@NonNull String str, int i) {
            this.zzecx.add(PasswordSpecification.zzb(zzn(str, "requiredChars")));
            this.zzecy.add(Integer.valueOf(1));
            return this;
        }

        public final zza zzel(@NonNull String str) {
            this.zzedc.addAll(zzn(str, "allowedChars"));
            return this;
        }

        public final zza zzj(int i, int i2) {
            this.zzecz = 12;
            this.zzeda = 16;
            return this;
        }
    }

    public static class zzb extends Error {
        public zzb(String str) {
            super(str);
        }
    }

    PasswordSpecification(String str, List<String> list, List<Integer> list2, int i, int i2) {
        this.zzecw = str;
        this.zzecx = Collections.unmodifiableList(list);
        this.zzecy = Collections.unmodifiableList(list2);
        this.zzecz = i;
        this.zzeda = i2;
        int[] iArr = new int[95];
        Arrays.fill(iArr, -1);
        int i3 = 0;
        for (String toCharArray : this.zzecx) {
            for (char c : toCharArray.toCharArray()) {
                iArr[c - 32] = i3;
            }
            i3++;
        }
        this.zzedb = iArr;
        this.zzbeb = new SecureRandom();
    }

    private static String zzb(Collection<Character> collection) {
        char[] cArr = new char[collection.size()];
        int i = 0;
        for (Character charValue : collection) {
            int i2 = i + 1;
            cArr[i] = charValue.charValue();
            i = i2;
        }
        return new String(cArr);
    }

    private static boolean zzc(int i, int i2, int i3) {
        return i < 32 || i > 126;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, this.zzecw, false);
        zzbem.zzb(parcel, 2, this.zzecx, false);
        zzbem.zza(parcel, 3, this.zzecy, false);
        zzbem.zzc(parcel, 4, this.zzecz);
        zzbem.zzc(parcel, 5, this.zzeda);
        zzbem.zzai(parcel, zze);
    }
}
