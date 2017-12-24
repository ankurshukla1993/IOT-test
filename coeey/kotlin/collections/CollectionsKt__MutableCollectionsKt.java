package kotlin.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000j\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001f\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a9\u0010\t\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f2\u0006\u0010\r\u001a\u00020\u0001H\u0002¢\u0006\u0002\b\u000e\u001a9\u0010\t\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f2\u0006\u0010\r\u001a\u00020\u0001H\u0002¢\u0006\u0002\b\u000e\u001a(\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u0006\u0010\u0012\u001a\u0002H\u0002H\n¢\u0006\u0002\u0010\u0013\u001a.\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\n¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\n\u001a)\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\n\u001a(\u0010\u0015\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u0006\u0010\u0012\u001a\u0002H\u0002H\n¢\u0006\u0002\u0010\u0013\u001a.\u0010\u0015\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\n¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0015\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\n\u001a)\u0010\u0015\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\n\u001a-\u0010\u0016\u001a\u00020\u0001\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b\u0017*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0012\u001a\u0002H\u0002H\b¢\u0006\u0002\u0010\u0018\u001a&\u0010\u0016\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u001aH\b¢\u0006\u0002\u0010\u001b\u001a-\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a&\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a&\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a.\u0010\u001c\u001a\u00020\u0001\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b\u0017*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u001dH\b\u001a*\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a*\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a-\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a&\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a&\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a.\u0010\u001e\u001a\u00020\u0001\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b\u0017*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u001dH\b\u001a*\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a*\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a\u0015\u0010\u001f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0003H\u0002¢\u0006\u0002\b \u001a\"\u0010!\u001a\u00020\u0011\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\"*\b\u0012\u0004\u0012\u0002H\u00020\u000fH\u0007\u001a3\u0010!\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0018\u0010#\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u001a0$H\b\u001a5\u0010!\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u001a\u0010%\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020&j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`'H\b\u001a4\u0010(\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u001a\u0010%\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020&j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`'H\u0007¨\u0006)"}, d2 = {"addAll", "", "T", "", "elements", "", "(Ljava/util/Collection;[Ljava/lang/Object;)Z", "", "Lkotlin/sequences/Sequence;", "filterInPlace", "", "predicate", "Lkotlin/Function1;", "predicateResultToRemove", "filterInPlace$CollectionsKt__MutableCollectionsKt", "", "minusAssign", "", "element", "(Ljava/util/Collection;Ljava/lang/Object;)V", "(Ljava/util/Collection;[Ljava/lang/Object;)V", "plusAssign", "remove", "Lkotlin/internal/OnlyInputTypes;", "(Ljava/util/Collection;Ljava/lang/Object;)Z", "index", "", "(Ljava/util/List;I)Ljava/lang/Object;", "removeAll", "", "retainAll", "retainNothing", "retainNothing$CollectionsKt__MutableCollectionsKt", "sort", "", "comparison", "Lkotlin/Function2;", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "sortWith", "kotlin-stdlib"}, k = 5, mv = {1, 1, 7}, xi = 1, xs = "kotlin/collections/CollectionsKt")
/* compiled from: MutableCollections.kt */
class CollectionsKt__MutableCollectionsKt extends CollectionsKt__IteratorsKt {
    private static final <T> boolean filterInPlace$CollectionsKt__MutableCollectionsKt(@org.jetbrains.annotations.NotNull java.lang.Iterable<? extends T> r1, kotlin.jvm.functions.Function1<? super T, java.lang.Boolean> r2, boolean r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.collections.CollectionsKt__MutableCollectionsKt.filterInPlace$CollectionsKt__MutableCollectionsKt(java.lang.Iterable, kotlin.jvm.functions.Function1, boolean):boolean
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r0 = 0;
        r2 = r2.iterator();
        r1 = r2.hasNext();
        if (r1 == 0) goto L_0x0020;
    L_0x000b:
        r1 = r2.next();
        r1 = r3.invoke(r1);
        r1 = (java.lang.Boolean) r1;
        r1 = r1.booleanValue();
        if (r1 != r4) goto L_0x0005;
        r2.remove();
        r0 = 1;
        goto L_0x0005;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.CollectionsKt__MutableCollectionsKt.filterInPlace$CollectionsKt__MutableCollectionsKt(java.lang.Iterable, kotlin.jvm.functions.Function1, boolean):boolean");
    }

    @InlineOnly
    private static final <T> boolean remove(@NotNull Collection<? extends T> $receiver, T element) {
        if ($receiver != null) {
            return TypeIntrinsics.asMutableCollection($receiver).remove(element);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }

    @InlineOnly
    private static final <T> boolean removeAll(@NotNull Collection<? extends T> $receiver, Collection<? extends T> elements) {
        if ($receiver != null) {
            return TypeIntrinsics.asMutableCollection($receiver).removeAll(elements);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }

    @InlineOnly
    private static final <T> boolean retainAll(@NotNull Collection<? extends T> $receiver, Collection<? extends T> elements) {
        if ($receiver != null) {
            return TypeIntrinsics.asMutableCollection($receiver).retainAll(elements);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use removeAt(index) instead.", replaceWith = @ReplaceWith(expression = "removeAt(index)", imports = {}))
    @InlineOnly
    private static final <T> T remove(@NotNull List<T> $receiver, int index) {
        return $receiver.remove(index);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use sortWith(comparator) instead.", replaceWith = @ReplaceWith(expression = "this.sortWith(comparator)", imports = {}))
    @InlineOnly
    private static final <T> void sort(@NotNull List<T> $receiver, Comparator<? super T> comparator) {
        throw new NotImplementedError(null, 1, null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use sortWith(Comparator(comparison)) instead.", replaceWith = @ReplaceWith(expression = "this.sortWith(Comparator(comparison))", imports = {}))
    @InlineOnly
    private static final <T> void sort(@NotNull List<T> $receiver, Function2<? super T, ? super T, Integer> comparison) {
        throw new NotImplementedError(null, 1, null);
    }

    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> $receiver, T element) {
        $receiver.add(element);
    }

    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> $receiver, Iterable<? extends T> elements) {
        addAll((Collection) $receiver, (Iterable) elements);
    }

    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> $receiver, T[] elements) {
        addAll((Collection) $receiver, (Object[]) elements);
    }

    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> $receiver, Sequence<? extends T> elements) {
        addAll((Collection) $receiver, (Sequence) elements);
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> $receiver, T element) {
        $receiver.remove(element);
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> $receiver, Iterable<? extends T> elements) {
        removeAll((Collection) $receiver, (Iterable) elements);
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> $receiver, T[] elements) {
        removeAll((Collection) $receiver, (Object[]) elements);
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> $receiver, Sequence<? extends T> elements) {
        removeAll((Collection) $receiver, (Sequence) elements);
    }

    public static final <T> boolean addAll(@NotNull Collection<? super T> $receiver, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        if (elements instanceof Collection) {
            return $receiver.addAll((Collection) elements);
        }
        boolean result = false;
        for (Object item : elements) {
            if ($receiver.add(item)) {
                result = true;
            }
        }
        return result;
    }

    public static final <T> boolean addAll(@NotNull Collection<? super T> $receiver, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        boolean result = false;
        for (Object item : elements) {
            if ($receiver.add(item)) {
                result = true;
            }
        }
        return result;
    }

    public static final <T> boolean addAll(@NotNull Collection<? super T> $receiver, @NotNull T[] elements) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return $receiver.addAll(ArraysKt___ArraysKt.asList((Object[]) elements));
    }

    public static final <T> boolean removeAll(@NotNull Iterable<? extends T> $receiver, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((Iterable) $receiver, (Function1) predicate, true);
    }

    public static final <T> boolean retainAll(@NotNull Iterable<? extends T> $receiver, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((Iterable) $receiver, (Function1) predicate, false);
    }

    public static final <T> boolean removeAll(@NotNull List<T> $receiver, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((List) $receiver, (Function1) predicate, true);
    }

    public static final <T> boolean retainAll(@NotNull List<T> $receiver, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((List) $receiver, (Function1) predicate, false);
    }

    private static final <T> boolean filterInPlace$CollectionsKt__MutableCollectionsKt(@NotNull List<T> $receiver, Function1<? super T, Boolean> predicate, boolean predicateResultToRemove) {
        if ($receiver instanceof RandomAccess) {
            int writeIndex = 0;
            int lastIndex = CollectionsKt__CollectionsKt.getLastIndex($receiver);
            if (0 <= lastIndex) {
                int readIndex = 0;
                while (true) {
                    Object element = $receiver.get(readIndex);
                    if (((Boolean) predicate.invoke(element)).booleanValue() != predicateResultToRemove) {
                        if (writeIndex != readIndex) {
                            $receiver.set(writeIndex, element);
                        }
                        writeIndex++;
                    }
                    if (readIndex == lastIndex) {
                        break;
                    }
                    readIndex++;
                }
            }
            if (writeIndex >= $receiver.size()) {
                return false;
            }
            int removeIndex = CollectionsKt__CollectionsKt.getLastIndex($receiver);
            if (removeIndex >= writeIndex) {
                while (true) {
                    $receiver.remove(removeIndex);
                    if (removeIndex == writeIndex) {
                        break;
                    }
                    removeIndex--;
                }
            }
            return true;
        } else if ($receiver != null) {
            return filterInPlace$CollectionsKt__MutableCollectionsKt(TypeIntrinsics.asMutableIterable($receiver), (Function1) predicate, predicateResultToRemove);
        } else {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableIterable<T>");
        }
    }

    public static final <T> boolean removeAll(@NotNull Collection<? super T> $receiver, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return TypeIntrinsics.asMutableCollection($receiver).removeAll(CollectionsKt__IterablesKt.convertToSetForSetOperationWith(elements, $receiver));
    }

    public static final <T> boolean removeAll(@NotNull Collection<? super T> $receiver, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        HashSet set = SequencesKt___SequencesKt.toHashSet(elements);
        return (!((Collection) set).isEmpty() ? 1 : null) != null && $receiver.removeAll(set);
    }

    public static final <T> boolean removeAll(@NotNull Collection<? super T> $receiver, @NotNull T[] elements) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return ((elements.length == 0 ? 1 : null) == null ? 1 : null) != null && $receiver.removeAll(ArraysKt___ArraysKt.toHashSet((Object[]) elements));
    }

    public static final <T> boolean retainAll(@NotNull Collection<? super T> $receiver, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return TypeIntrinsics.asMutableCollection($receiver).retainAll(CollectionsKt__IterablesKt.convertToSetForSetOperationWith(elements, $receiver));
    }

    public static final <T> boolean retainAll(@NotNull Collection<? super T> $receiver, @NotNull T[] elements) {
        Object obj;
        Object obj2 = 1;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        if (elements.length == 0) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            obj2 = null;
        }
        if (obj2 != null) {
            return $receiver.retainAll(ArraysKt___ArraysKt.toHashSet((Object[]) elements));
        }
        return retainNothing$CollectionsKt__MutableCollectionsKt($receiver);
    }

    public static final <T> boolean retainAll(@NotNull Collection<? super T> $receiver, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        HashSet set = SequencesKt___SequencesKt.toHashSet(elements);
        if ((!((Collection) set).isEmpty() ? 1 : null) != null) {
            return $receiver.retainAll(set);
        }
        return retainNothing$CollectionsKt__MutableCollectionsKt($receiver);
    }

    private static final boolean retainNothing$CollectionsKt__MutableCollectionsKt(@NotNull Collection<?> $receiver) {
        boolean result = !$receiver.isEmpty();
        $receiver.clear();
        return result;
    }

    public static final <T extends Comparable<? super T>> void sort(@NotNull List<T> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if ($receiver.size() > 1) {
            Collections.sort($receiver);
        }
    }

    public static final <T> void sortWith(@NotNull List<T> $receiver, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        if ($receiver.size() > 1) {
            Collections.sort($receiver, comparator);
        }
    }
}
