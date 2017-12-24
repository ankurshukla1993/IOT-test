package com.google.android.gms.internal;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzffu<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzkqq;
    private final int zzpdv;
    private List<zzffz> zzpdw;
    private Map<K, V> zzpdx;
    private volatile zzfgb zzpdy;
    private Map<K, V> zzpdz;

    private zzffu(int i) {
        this.zzpdv = i;
        this.zzpdw = Collections.emptyList();
        this.zzpdx = Collections.emptyMap();
        this.zzpdz = Collections.emptyMap();
    }

    private final int zza(K k) {
        int compareTo;
        int size = this.zzpdw.size() - 1;
        if (size >= 0) {
            compareTo = k.compareTo((Comparable) ((zzffz) this.zzpdw.get(size)).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        int i2 = size;
        while (i <= i2) {
            size = (i + i2) / 2;
            compareTo = k.compareTo((Comparable) ((zzffz) this.zzpdw.get(size)).getKey());
            if (compareTo < 0) {
                i2 = size - 1;
            } else if (compareTo <= 0) {
                return size;
            } else {
                i = size + 1;
            }
        }
        return -(i + 1);
    }

    private final void zzcwl() {
        if (this.zzkqq) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzcwm() {
        zzcwl();
        if (this.zzpdx.isEmpty() && !(this.zzpdx instanceof TreeMap)) {
            this.zzpdx = new TreeMap();
            this.zzpdz = ((TreeMap) this.zzpdx).descendingMap();
        }
        return (SortedMap) this.zzpdx;
    }

    static <FieldDescriptorType extends zzfed<FieldDescriptorType>> zzffu<FieldDescriptorType, Object> zzlp(int i) {
        return new zzffv(i);
    }

    private final V zzlr(int i) {
        zzcwl();
        V value = ((zzffz) this.zzpdw.remove(i)).getValue();
        if (!this.zzpdx.isEmpty()) {
            Iterator it = zzcwm().entrySet().iterator();
            this.zzpdw.add(new zzffz(this, (Entry) it.next()));
            it.remove();
        }
        return value;
    }

    public void clear() {
        zzcwl();
        if (!this.zzpdw.isEmpty()) {
            this.zzpdw.clear();
        }
        if (!this.zzpdx.isEmpty()) {
            this.zzpdx.clear();
        }
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzpdx.containsKey(comparable);
    }

    public Set<Entry<K, V>> entrySet() {
        if (this.zzpdy == null) {
            this.zzpdy = new zzfgb();
        }
        return this.zzpdy;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzffu)) {
            return super.equals(obj);
        }
        zzffu com_google_android_gms_internal_zzffu = (zzffu) obj;
        int size = size();
        if (size != com_google_android_gms_internal_zzffu.size()) {
            return false;
        }
        int zzcwj = zzcwj();
        if (zzcwj != com_google_android_gms_internal_zzffu.zzcwj()) {
            return entrySet().equals(com_google_android_gms_internal_zzffu.entrySet());
        }
        for (int i = 0; i < zzcwj; i++) {
            if (!zzlq(i).equals(com_google_android_gms_internal_zzffu.zzlq(i))) {
                return false;
            }
        }
        return zzcwj != size ? this.zzpdx.equals(com_google_android_gms_internal_zzffu.zzpdx) : true;
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        return zza >= 0 ? ((zzffz) this.zzpdw.get(zza)).getValue() : this.zzpdx.get(comparable);
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < zzcwj(); i2++) {
            i += ((zzffz) this.zzpdw.get(i2)).hashCode();
        }
        return this.zzpdx.size() > 0 ? this.zzpdx.hashCode() + i : i;
    }

    public final boolean isImmutable() {
        return this.zzkqq;
    }

    public /* synthetic */ Object put(Object obj, Object obj2) {
        return zza((Comparable) obj, obj2);
    }

    public V remove(Object obj) {
        zzcwl();
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        return zza >= 0 ? zzlr(zza) : this.zzpdx.isEmpty() ? null : this.zzpdx.remove(comparable);
    }

    public int size() {
        return this.zzpdw.size() + this.zzpdx.size();
    }

    public final V zza(K k, V v) {
        zzcwl();
        int zza = zza((Comparable) k);
        if (zza >= 0) {
            return ((zzffz) this.zzpdw.get(zza)).setValue(v);
        }
        zzcwl();
        if (this.zzpdw.isEmpty() && !(this.zzpdw instanceof ArrayList)) {
            this.zzpdw = new ArrayList(this.zzpdv);
        }
        int i = -(zza + 1);
        if (i >= this.zzpdv) {
            return zzcwm().put(k, v);
        }
        if (this.zzpdw.size() == this.zzpdv) {
            zzffz com_google_android_gms_internal_zzffz = (zzffz) this.zzpdw.remove(this.zzpdv - 1);
            zzcwm().put((Comparable) com_google_android_gms_internal_zzffz.getKey(), com_google_android_gms_internal_zzffz.getValue());
        }
        this.zzpdw.add(i, new zzffz(this, k, v));
        return null;
    }

    public void zzbim() {
        if (!this.zzkqq) {
            this.zzpdx = this.zzpdx.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzpdx);
            this.zzpdz = this.zzpdz.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzpdz);
            this.zzkqq = true;
        }
    }

    public final int zzcwj() {
        return this.zzpdw.size();
    }

    public final Iterable<Entry<K, V>> zzcwk() {
        return this.zzpdx.isEmpty() ? zzffw.zzcwn() : this.zzpdx.entrySet();
    }

    public final Entry<K, V> zzlq(int i) {
        return (Entry) this.zzpdw.get(i);
    }
}
