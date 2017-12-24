package org.jetbrains.anko.collections;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\b\u001a-\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\b\u001a3\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\bH\b\u001a3\u0010\n\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\bH\b\u001a.\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u000f\u001a.\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u000f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f¨\u0006\u0011"}, d2 = {"forEachByIndex", "", "T", "", "f", "Lkotlin/Function1;", "forEachReversedByIndex", "forEachReversedWithIndex", "Lkotlin/Function2;", "", "forEachWithIndex", "toAndroidPair", "Landroid/util/Pair;", "F", "S", "Lkotlin/Pair;", "toKotlinPair", "common-compileReleaseKotlin"}, k = 2, mv = {1, 1, 0})
/* compiled from: Collections.kt */
public final class CollectionsKt {
    public static final <T> void forEachByIndex(List<? extends T> $receiver, @NotNull Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        int lastIndex = $receiver.size() - 1;
        int i = 0;
        if (0 <= lastIndex) {
            while (true) {
                f.invoke($receiver.get(i));
                if (i != lastIndex) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static final <T> void forEachWithIndex(List<? extends T> $receiver, @NotNull Function2<? super Integer, ? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        int lastIndex = $receiver.size() - 1;
        int i = 0;
        if (0 <= lastIndex) {
            while (true) {
                f.invoke(Integer.valueOf(i), $receiver.get(i));
                if (i != lastIndex) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static final <T> void forEachReversedByIndex(List<? extends T> $receiver, @NotNull Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        for (int i = $receiver.size() - 1; i >= 0; i--) {
            f.invoke($receiver.get(i));
        }
    }

    public static final <T> void forEachReversedWithIndex(List<? extends T> $receiver, @NotNull Function2<? super Integer, ? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        for (int i = $receiver.size() - 1; i >= 0; i--) {
            f.invoke(Integer.valueOf(i), $receiver.get(i));
        }
    }

    @NotNull
    public static final <F, S> Pair<F, S> toKotlinPair(android.util.Pair<F, S> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return TuplesKt.to($receiver.first, $receiver.second);
    }

    @NotNull
    public static final <F, S> android.util.Pair<F, S> toAndroidPair(Pair<? extends F, ? extends S> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new android.util.Pair($receiver.getFirst(), $receiver.getSecond());
    }
}
