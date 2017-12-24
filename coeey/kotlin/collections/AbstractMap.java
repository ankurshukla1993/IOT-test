package kotlin.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin(version = "1.1")
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010&\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\b'\u0018\u0000 **\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003:\u0001*B\u0007\b\u0004¢\u0006\u0002\u0010\u0004J\u001f\u0010\u0013\u001a\u00020\u00142\u0010\u0010\u0015\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u0016H\u0000¢\u0006\u0002\b\u0017J\u0015\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001aJ\u001a\u0010\u001b\u001a\u00020\u00142\u000b\u0010\u001c\u001a\u00078\u0001¢\u0006\u0002\b\u001dH\u0016¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001e\u001a\u00020\u00142\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0002J\u0018\u0010!\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0019\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\"J\b\u0010#\u001a\u00020\rH\u0016J#\u0010$\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00162\u0006\u0010\u0019\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010%J\b\u0010&\u001a\u00020\u0014H\u0016J\b\u0010'\u001a\u00020(H\u0016J\u0012\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010 H\u0002J\u001c\u0010'\u001a\u00020(2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0016H\bR\u001a\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00068\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\b8\b@\bX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006+"}, d2 = {"Lkotlin/collections/AbstractMap;", "K", "V", "", "()V", "_keys", "", "_values", "", "keys", "getKeys", "()Ljava/util/Set;", "size", "", "getSize", "()I", "values", "getValues", "()Ljava/util/Collection;", "containsEntry", "", "entry", "", "containsEntry$kotlin_stdlib", "containsKey", "key", "(Ljava/lang/Object;)Z", "containsValue", "value", "Lkotlin/UnsafeVariance;", "equals", "other", "", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "hashCode", "implFindEntry", "(Ljava/lang/Object;)Ljava/util/Map$Entry;", "isEmpty", "toString", "", "o", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
/* compiled from: AbstractMap.kt */
public abstract class AbstractMap<K, V> implements Map<K, V>, KMappedMarker {
    public static final Companion Companion = new Companion();
    private volatile Set<? extends K> _keys;
    private volatile Collection<? extends V> _values;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0002\b\bJ\u001d\u0010\t\u001a\u00020\n2\u000e\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006H\u0000¢\u0006\u0002\b\u000bJ\u001d\u0010\f\u001a\u00020\r2\u000e\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006H\u0000¢\u0006\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lkotlin/collections/AbstractMap$Companion;", "", "()V", "entryEquals", "", "e", "", "other", "entryEquals$kotlin_stdlib", "entryHashCode", "", "entryHashCode$kotlin_stdlib", "entryToString", "", "entryToString$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
    /* compiled from: AbstractMap.kt */
    public static final class Companion {
        private Companion() {
        }

        public final int entryHashCode$kotlin_stdlib(@NotNull Entry<?, ?> e) {
            int i = 0;
            Intrinsics.checkParameterIsNotNull(e, "e");
            Entry<?, ?> $receiver = e;
            Object key = $receiver.getKey();
            int hashCode = key != null ? key.hashCode() : 0;
            Object value = $receiver.getValue();
            if (value != null) {
                i = value.hashCode();
            }
            return hashCode ^ i;
        }

        @NotNull
        public final String entryToString$kotlin_stdlib(@NotNull Entry<?, ?> e) {
            Intrinsics.checkParameterIsNotNull(e, "e");
            Entry<?, ?> $receiver = e;
            return "" + $receiver.getKey() + '=' + $receiver.getValue();
        }

        public final boolean entryEquals$kotlin_stdlib(@NotNull Entry<?, ?> e, @Nullable Object other) {
            Intrinsics.checkParameterIsNotNull(e, "e");
            if (!(other instanceof Entry)) {
                return false;
            }
            boolean z = Intrinsics.areEqual(e.getKey(), ((Entry) other).getKey()) && Intrinsics.areEqual(e.getValue(), ((Entry) other).getValue());
            return z;
        }
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public abstract Set getEntries();

    public V put(K k, V v) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public V remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    protected AbstractMap() {
    }

    public final /* bridge */ Set<Entry<K, V>> entrySet() {
        return getEntries();
    }

    public final /* bridge */ Set<K> keySet() {
        return getKeys();
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public final /* bridge */ Collection<V> values() {
        return getValues();
    }

    public boolean containsKey(Object key) {
        return implFindEntry(key) != null;
    }

    public boolean containsValue(Object value) {
        Iterable<Entry> $receiver$iv = entrySet();
        if (($receiver$iv instanceof Collection) && ((Collection) $receiver$iv).isEmpty()) {
            return false;
        }
        for (Entry it : $receiver$iv) {
            if (Intrinsics.areEqual(it.getValue(), value)) {
                return true;
            }
        }
        return false;
    }

    public final boolean containsEntry$kotlin_stdlib(@Nullable Entry<?, ?> entry) {
        if (!(entry instanceof Entry)) {
            return false;
        }
        Object key = entry.getKey();
        Object value = entry.getValue();
        if (this == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
        }
        Object ourValue = get(key);
        if ((Intrinsics.areEqual(value, ourValue) ^ 1) != 0) {
            return false;
        }
        if (ourValue == null) {
            if (this == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
            } else if (!containsKey(key)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(@Nullable Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Map) || size() != ((Map) other).size()) {
            return false;
        }
        boolean z;
        Iterable<Entry> $receiver$iv = ((Map) other).entrySet();
        if (($receiver$iv instanceof Collection) && ((Collection) $receiver$iv).isEmpty()) {
            z = true;
        } else {
            for (Entry it : $receiver$iv) {
                if (!containsEntry$kotlin_stdlib(it)) {
                    z = false;
                    break;
                }
            }
            z = true;
        }
        return z;
    }

    @Nullable
    public V get(Object key) {
        Entry implFindEntry = implFindEntry(key);
        return implFindEntry != null ? implFindEntry.getValue() : null;
    }

    public int hashCode() {
        return entrySet().hashCode();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int getSize() {
        return entrySet().size();
    }

    @NotNull
    public Set<K> getKeys() {
        if (this._keys == null) {
            this._keys = new AbstractMap$keys$1(this);
        }
        Set<K> set = this._keys;
        if (set == null) {
            Intrinsics.throwNpe();
        }
        return set;
    }

    @NotNull
    public String toString() {
        return CollectionsKt___CollectionsKt.joinToString$default(entrySet(), ", ", "{", "}", 0, null, new AbstractMap$toString$1(this), 24, null);
    }

    private final String toString(Entry<? extends K, ? extends V> entry) {
        return toString(entry.getKey()) + "=" + toString(entry.getValue());
    }

    private final String toString(Object o) {
        return o == ((AbstractMap) this) ? "(this Map)" : String.valueOf(o);
    }

    @NotNull
    public Collection<V> getValues() {
        if (this._values == null) {
            this._values = new AbstractMap$values$1(this);
        }
        Collection<V> collection = this._values;
        if (collection == null) {
            Intrinsics.throwNpe();
        }
        return collection;
    }

    private final Entry<K, V> implFindEntry(K key) {
        Entry entry;
        for (Entry element$iv : entrySet()) {
            if (Intrinsics.areEqual(element$iv.getKey(), (Object) key)) {
                entry = element$iv;
                break;
            }
        }
        entry = null;
        return entry;
    }
}
