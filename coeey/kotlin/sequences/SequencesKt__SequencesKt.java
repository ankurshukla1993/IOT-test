package kotlin.sequences;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.internal.InlineOnly;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\u001a+\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\b\u001a\u0012\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002\u001a&\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0004\u001a<\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\b2\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u00042\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000b\u001a=\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\b2\b\u0010\f\u001a\u0004\u0018\u0001H\u00022\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000bH\u0007¢\u0006\u0002\u0010\r\u001a+\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0010\"\u0002H\u0002¢\u0006\u0002\u0010\u0011\u001a\u001f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0013H\b\u001a\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005\u001a\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u001aC\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0016*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u00050\u000bH\u0002¢\u0006\u0002\b\u0017\u001a)\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00180\u0001H\u0007¢\u0006\u0002\b\u0019\u001a\"\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0001\u001a@\u0010\u001a\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u001c0\u001b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0016*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00160\u001b0\u0001¨\u0006\u001d"}, d2 = {"Sequence", "Lkotlin/sequences/Sequence;", "T", "iterator", "Lkotlin/Function0;", "", "emptySequence", "generateSequence", "", "nextFunction", "seedFunction", "Lkotlin/Function1;", "seed", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;", "sequenceOf", "elements", "", "([Ljava/lang/Object;)Lkotlin/sequences/Sequence;", "asSequence", "Ljava/util/Enumeration;", "constrainOnce", "flatten", "R", "flatten$SequencesKt__SequencesKt", "", "flattenSequenceOfIterable", "unzip", "Lkotlin/Pair;", "", "kotlin-stdlib"}, k = 5, mv = {1, 1, 7}, xi = 1, xs = "kotlin/sequences/SequencesKt")
/* compiled from: Sequences.kt */
class SequencesKt__SequencesKt {
    @InlineOnly
    private static final <T> Sequence<T> Sequence(Function0<? extends Iterator<? extends T>> iterator) {
        return new SequencesKt__SequencesKt$Sequence$1(iterator);
    }

    @NotNull
    public static final <T> Sequence<T> asSequence(@NotNull Iterator<? extends T> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return constrainOnce(new SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1($receiver));
    }

    @InlineOnly
    private static final <T> Sequence<T> asSequence(@NotNull Enumeration<T> $receiver) {
        return asSequence(CollectionsKt__IteratorsKt.iterator((Enumeration) $receiver));
    }

    @NotNull
    public static final <T> Sequence<T> sequenceOf(@NotNull T... elements) {
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return (elements.length == 0 ? 1 : null) != null ? emptySequence() : ArraysKt___ArraysKt.asSequence((Object[]) elements);
    }

    @NotNull
    public static final <T> Sequence<T> emptySequence() {
        return EmptySequence.INSTANCE;
    }

    @NotNull
    public static final <T> Sequence<T> flatten(@NotNull Sequence<? extends Sequence<? extends T>> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return flatten$SequencesKt__SequencesKt($receiver, SequencesKt__SequencesKt$flatten$1.INSTANCE);
    }

    @NotNull
    @JvmName(name = "flattenSequenceOfIterable")
    public static final <T> Sequence<T> flattenSequenceOfIterable(@NotNull Sequence<? extends Iterable<? extends T>> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return flatten$SequencesKt__SequencesKt($receiver, SequencesKt__SequencesKt$flatten$2.INSTANCE);
    }

    private static final <T, R> Sequence<R> flatten$SequencesKt__SequencesKt(@NotNull Sequence<? extends T> $receiver, Function1<? super T, ? extends Iterator<? extends R>> iterator) {
        if ($receiver instanceof TransformingSequence) {
            return ((TransformingSequence) $receiver).flatten$kotlin_stdlib(iterator);
        }
        return new FlatteningSequence($receiver, SequencesKt__SequencesKt$flatten$3.INSTANCE, iterator);
    }

    @NotNull
    public static final <T, R> Pair<List<T>, List<R>> unzip(@NotNull Sequence<? extends Pair<? extends T, ? extends R>> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        ArrayList listT = new ArrayList();
        ArrayList listR = new ArrayList();
        for (Pair pair : $receiver) {
            listT.add(pair.getFirst());
            listR.add(pair.getSecond());
        }
        return TuplesKt.to(listT, listR);
    }

    @NotNull
    public static final <T> Sequence<T> constrainOnce(@NotNull Sequence<? extends T> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver instanceof ConstrainedOnceSequence ? $receiver : new ConstrainedOnceSequence($receiver);
    }

    @NotNull
    public static final <T> Sequence<T> generateSequence(@NotNull Function0<? extends T> nextFunction) {
        Intrinsics.checkParameterIsNotNull(nextFunction, "nextFunction");
        return constrainOnce(new GeneratorSequence(nextFunction, new SequencesKt__SequencesKt$generateSequence$1(nextFunction)));
    }

    @NotNull
    @LowPriorityInOverloadResolution
    public static final <T> Sequence<T> generateSequence(@Nullable T seed, @NotNull Function1<? super T, ? extends T> nextFunction) {
        Intrinsics.checkParameterIsNotNull(nextFunction, "nextFunction");
        if (seed == null) {
            return EmptySequence.INSTANCE;
        }
        return new GeneratorSequence(new SequencesKt__SequencesKt$generateSequence$2(seed), nextFunction);
    }

    @NotNull
    public static final <T> Sequence<T> generateSequence(@NotNull Function0<? extends T> seedFunction, @NotNull Function1<? super T, ? extends T> nextFunction) {
        Intrinsics.checkParameterIsNotNull(seedFunction, "seedFunction");
        Intrinsics.checkParameterIsNotNull(nextFunction, "nextFunction");
        return new GeneratorSequence(seedFunction, nextFunction);
    }
}
