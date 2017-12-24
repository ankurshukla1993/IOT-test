package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000#\n\u0000\n\u0002\u0010(\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\t\u0010\u0011\u001a\u00020\u0012H\u0002J\u000e\u0010\u0013\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0005R\u001e\u0010\u0003\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\b\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0014"}, d2 = {"kotlin/sequences/GeneratorSequence$iterator$1", "", "(Lkotlin/sequences/GeneratorSequence;)V", "nextItem", "getNextItem", "()Ljava/lang/Object;", "setNextItem", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "nextState", "", "getNextState", "()I", "setNextState", "(I)V", "calcNext", "", "hasNext", "", "next", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
/* compiled from: Sequences.kt */
public final class GeneratorSequence$iterator$1 implements Iterator<T>, KMappedMarker {
    @Nullable
    private T nextItem;
    private int nextState = -2;
    final /* synthetic */ GeneratorSequence this$0;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    GeneratorSequence$iterator$1(GeneratorSequence $outer) {
        this.this$0 = $outer;
    }

    @Nullable
    public final T getNextItem() {
        return this.nextItem;
    }

    public final void setNextItem(@Nullable T <set-?>) {
        this.nextItem = <set-?>;
    }

    public final int getNextState() {
        return this.nextState;
    }

    public final void setNextState(int <set-?>) {
        this.nextState = <set-?>;
    }

    private final void calcNext() {
        Object invoke;
        if (this.nextState == -2) {
            invoke = this.this$0.getInitialValue.invoke();
        } else {
            Function1 access$getGetNextValue$p = this.this$0.getNextValue;
            Object obj = this.nextItem;
            if (obj == null) {
                Intrinsics.throwNpe();
            }
            invoke = access$getGetNextValue$p.invoke(obj);
        }
        this.nextItem = invoke;
        this.nextState = this.nextItem == null ? 0 : 1;
    }

    @NotNull
    public T next() {
        if (this.nextState < 0) {
            calcNext();
        }
        if (this.nextState == 0) {
            throw new NoSuchElementException();
        }
        Object result = this.nextItem;
        if (result == null) {
            throw new TypeCastException("null cannot be cast to non-null type T");
        }
        this.nextState = -1;
        return result;
    }

    public boolean hasNext() {
        if (this.nextState < 0) {
            calcNext();
        }
        if (this.nextState == 1) {
            return true;
        }
        return false;
    }
}
