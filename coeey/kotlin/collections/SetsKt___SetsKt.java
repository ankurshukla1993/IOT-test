package kotlin.collections;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a,\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0002¢\u0006\u0002\u0010\u0004\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0002¢\u0006\u0002\u0010\u0007\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0002\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0002\u001a,\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\b¢\u0006\u0002\u0010\u0004\u001a,\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0002¢\u0006\u0002\u0010\u0004\u001a4\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0002¢\u0006\u0002\u0010\u0007\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0002\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0002\u001a,\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\b¢\u0006\u0002\u0010\u0004¨\u0006\r"}, d2 = {"minus", "", "T", "element", "(Ljava/util/Set;Ljava/lang/Object;)Ljava/util/Set;", "elements", "", "(Ljava/util/Set;[Ljava/lang/Object;)Ljava/util/Set;", "", "Lkotlin/sequences/Sequence;", "minusElement", "plus", "plusElement", "kotlin-stdlib"}, k = 5, mv = {1, 1, 7}, xi = 1, xs = "kotlin/collections/SetsKt")
/* compiled from: _Sets.kt */
class SetsKt___SetsKt extends SetsKt__SetsKt {
    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> $receiver, T element) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        LinkedHashSet result = new LinkedHashSet(MapsKt__MapsKt.mapCapacity($receiver.size()));
        boolean removed = false;
        for (Object element$iv : $receiver) {
            Object obj;
            Object it = element$iv;
            if (removed || !Intrinsics.areEqual(it, (Object) element)) {
                obj = 1;
            } else {
                removed = true;
                obj = null;
            }
            if (obj != null) {
                result.add(element$iv);
            }
        }
        return result;
    }

    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> $receiver, @NotNull T[] elements) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        LinkedHashSet result = new LinkedHashSet($receiver);
        CollectionsKt__MutableCollectionsKt.removeAll((Collection) result, (Object[]) elements);
        return result;
    }

    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> $receiver, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        Collection other = CollectionsKt__IterablesKt.convertToSetForSetOperationWith(elements, $receiver);
        if (other.isEmpty()) {
            return CollectionsKt___CollectionsKt.toSet($receiver);
        }
        if (other instanceof Set) {
            Collection destination$iv = new LinkedHashSet();
            for (Object element$iv : $receiver) {
                if (!other.contains(element$iv)) {
                    destination$iv.add(element$iv);
                }
            }
            return (Set) destination$iv;
        }
        LinkedHashSet result = new LinkedHashSet($receiver);
        result.removeAll(other);
        return result;
    }

    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> $receiver, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        LinkedHashSet result = new LinkedHashSet($receiver);
        CollectionsKt__MutableCollectionsKt.removeAll((Collection) result, (Sequence) elements);
        return result;
    }

    @InlineOnly
    private static final <T> Set<T> minusElement(@NotNull Set<? extends T> $receiver, T element) {
        return minus((Set) $receiver, (Object) element);
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> $receiver, T element) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        LinkedHashSet result = new LinkedHashSet(MapsKt__MapsKt.mapCapacity($receiver.size() + 1));
        result.addAll($receiver);
        result.add(element);
        return result;
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> $receiver, @NotNull T[] elements) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        LinkedHashSet result = new LinkedHashSet(MapsKt__MapsKt.mapCapacity($receiver.size() + elements.length));
        result.addAll($receiver);
        CollectionsKt__MutableCollectionsKt.addAll((Collection) result, (Object[]) elements);
        return result;
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> $receiver, @NotNull Iterable<? extends T> elements) {
        int size;
        LinkedHashSet result;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        Integer collectionSizeOrNull = CollectionsKt__IterablesKt.collectionSizeOrNull(elements);
        if (collectionSizeOrNull != null) {
            size = $receiver.size() + collectionSizeOrNull.intValue();
            result = r3;
        } else {
            size = $receiver.size() * 2;
            result = r3;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsKt.mapCapacity(size));
        result.addAll($receiver);
        CollectionsKt__MutableCollectionsKt.addAll((Collection) result, (Iterable) elements);
        return result;
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> $receiver, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        LinkedHashSet result = new LinkedHashSet(MapsKt__MapsKt.mapCapacity($receiver.size() * 2));
        result.addAll($receiver);
        CollectionsKt__MutableCollectionsKt.addAll((Collection) result, (Sequence) elements);
        return result;
    }

    @InlineOnly
    private static final <T> Set<T> plusElement(@NotNull Set<? extends T> $receiver, T element) {
        return plus((Set) $receiver, (Object) element);
    }
}
