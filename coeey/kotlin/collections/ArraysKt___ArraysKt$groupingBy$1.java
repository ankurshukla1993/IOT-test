package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00028\u00012\u0006\u0010\u0004\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0016¨\u0006\b"}, d2 = {"kotlin/collections/ArraysKt___ArraysKt$groupingBy$1", "Lkotlin/collections/Grouping;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "keyOf", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "sourceIterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
/* compiled from: _Arrays.kt */
public final class ArraysKt___ArraysKt$groupingBy$1 implements Grouping<T, K> {
    final /* synthetic */ Function1 $keySelector;
    final /* synthetic */ Object[] receiver$0;

    public ArraysKt___ArraysKt$groupingBy$1(T[] $receiver, Function1 $captured_local_variable$1) {
        this.receiver$0 = $receiver;
        this.$keySelector = $captured_local_variable$1;
    }

    @NotNull
    public Iterator<T> sourceIterator() {
        return ArrayIteratorKt.iterator(this.receiver$0);
    }

    public K keyOf(T element) {
        return this.$keySelector.invoke(element);
    }
}
