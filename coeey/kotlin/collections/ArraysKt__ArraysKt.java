package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a+\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00030\u0003¢\u0006\u0002\u0010\u0004\u001aG\u0010\u0005\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u00010\u0006\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0007*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00070\u00060\u0003¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"flatten", "", "T", "", "([[Ljava/lang/Object;)Ljava/util/List;", "unzip", "Lkotlin/Pair;", "R", "([Lkotlin/Pair;)Lkotlin/Pair;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 7}, xi = 1, xs = "kotlin/collections/ArraysKt")
/* compiled from: Arrays.kt */
class ArraysKt__ArraysKt extends ArraysKt__ArraysJVMKt {
    @NotNull
    public static final <T> List<T> flatten(@NotNull T[][] $receiver) {
        int i = 0;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        int sum$iv = 0;
        for (Object element$iv : (Object[]) $receiver) {
            sum$iv += ((Object[]) element$iv).length;
        }
        ArrayList result = new ArrayList(sum$iv);
        while (i < $receiver.length) {
            CollectionsKt__MutableCollectionsKt.addAll((Collection) result, $receiver[i]);
            i++;
        }
        return result;
    }

    @NotNull
    public static final <T, R> Pair<List<T>, List<R>> unzip(@NotNull Pair<? extends T, ? extends R>[] $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        ArrayList listT = new ArrayList(((Object[]) $receiver).length);
        ArrayList listR = new ArrayList(((Object[]) $receiver).length);
        for (Pair pair : $receiver) {
            listT.add(pair.getFirst());
            listR.add(pair.getSecond());
        }
        return TuplesKt.to(listT, listR);
    }
}
