package kotlin.collections;

import java.util.Comparator;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001aY\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0004\"\u0004\b\u0001\u0010\u00032*\u0010\u0005\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u0006\"\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007¢\u0006\u0002\u0010\b\u001a@\u0010\t\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\n2\u0006\u0010\u000b\u001a\u0002H\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00030\rH\b¢\u0006\u0002\u0010\u000e\u001a\u0019\u0010\u000f\u001a\u00020\u0010*\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u0011H\b\u001a:\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0004\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\u001a@\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00112\u000e\u0010\u0014\u001a\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0015¨\u0006\u0016"}, d2 = {"sortedMapOf", "Ljava/util/SortedMap;", "K", "V", "", "pairs", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)Ljava/util/SortedMap;", "getOrPut", "Ljava/util/concurrent/ConcurrentMap;", "key", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/concurrent/ConcurrentMap;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "toProperties", "Ljava/util/Properties;", "", "", "toSortedMap", "comparator", "Ljava/util/Comparator;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 7}, xi = 1, xs = "kotlin/collections/MapsKt")
/* compiled from: MapsJVM.kt */
class MapsKt__MapsJVMKt extends MapsKt__MapWithDefaultKt {
    public static final <K, V> V getOrPut(@NotNull ConcurrentMap<K, V> $receiver, K key, @NotNull Function0<? extends V> defaultValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(defaultValue, "defaultValue");
        V v = $receiver.get(key);
        if (v != null) {
            return v;
        }
        v = defaultValue.invoke();
        V putIfAbsent = $receiver.putIfAbsent(key, v);
        return putIfAbsent != null ? putIfAbsent : v;
    }

    @NotNull
    public static final <K extends Comparable<? super K>, V> SortedMap<K, V> toSortedMap(@NotNull Map<? extends K, ? extends V> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new TreeMap($receiver);
    }

    @NotNull
    public static final <K, V> SortedMap<K, V> toSortedMap(@NotNull Map<? extends K, ? extends V> $receiver, @NotNull Comparator<? super K> comparator) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        TreeMap $receiver2 = new TreeMap(comparator);
        $receiver2.putAll($receiver);
        return $receiver2;
    }

    @NotNull
    public static final <K extends Comparable<? super K>, V> SortedMap<K, V> sortedMapOf(@NotNull Pair<? extends K, ? extends V>... pairs) {
        Intrinsics.checkParameterIsNotNull(pairs, "pairs");
        TreeMap $receiver = new TreeMap();
        MapsKt__MapsKt.putAll((Map) $receiver, (Pair[]) pairs);
        return $receiver;
    }

    @InlineOnly
    private static final Properties toProperties(@NotNull Map<String, String> $receiver) {
        Properties $receiver2 = new Properties();
        $receiver2.putAll($receiver);
        return $receiver2;
    }
}
