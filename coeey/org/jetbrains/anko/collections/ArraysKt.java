package org.jetbrains.anko.collections;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0005\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0007\u001a2\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t0\fH\b¢\u0006\u0002\u0010\r\u001a2\u0010\u000e\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t0\fH\b¢\u0006\u0002\u0010\r\u001a8\u0010\u000f\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t0\u0010H\b¢\u0006\u0002\u0010\u0011\u001a8\u0010\u0012\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t0\u0010H\b¢\u0006\u0002\u0010\u0011¨\u0006\u0013"}, d2 = {"asSequence", "Lkotlin/sequences/Sequence;", "T", "Landroid/util/SparseArray;", "", "Landroid/util/SparseBooleanArray;", "", "Landroid/util/SparseIntArray;", "forEachByIndex", "", "", "f", "Lkotlin/Function1;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "forEachReversedByIndex", "forEachReversedWithIndex", "Lkotlin/Function2;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "forEachWithIndex", "common-compileReleaseKotlin"}, k = 2, mv = {1, 1, 0})
/* compiled from: Arrays.kt */
public final class ArraysKt {
    public static final <T> void forEachByIndex(T[] $receiver, @NotNull Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        int lastIndex = $receiver.length - 1;
        int i = 0;
        if (0 <= lastIndex) {
            while (true) {
                f.invoke($receiver[i]);
                if (i != lastIndex) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static final <T> void forEachWithIndex(T[] $receiver, @NotNull Function2<? super Integer, ? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        int lastIndex = $receiver.length - 1;
        int i = 0;
        if (0 <= lastIndex) {
            while (true) {
                f.invoke(Integer.valueOf(i), $receiver[i]);
                if (i != lastIndex) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static final <T> void forEachReversedByIndex(T[] $receiver, @NotNull Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        for (int i = $receiver.length - 1; i >= 0; i--) {
            f.invoke($receiver[i]);
        }
    }

    public static final <T> void forEachReversedWithIndex(T[] $receiver, @NotNull Function2<? super Integer, ? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        for (int i = $receiver.length - 1; i >= 0; i--) {
            f.invoke(Integer.valueOf(i), $receiver[i]);
        }
    }

    @NotNull
    public static final <T> Sequence<T> asSequence(SparseArray<T> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new SparseArraySequence($receiver);
    }

    @NotNull
    public static final <T> Sequence<Boolean> asSequence(SparseBooleanArray $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new SparseBooleanArraySequence($receiver);
    }

    @NotNull
    public static final <T> Sequence<Integer> asSequence(SparseIntArray $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new SparseIntArraySequence($receiver);
    }
}
