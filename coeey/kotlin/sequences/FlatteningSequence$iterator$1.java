package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0015\n\u0000\n\u0002\u0010(\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\t\u0010\f\u001a\u00020\u000bH\u0002J\u000e\u0010\r\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u000eR\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0001X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0005¨\u0006\u000f"}, d2 = {"kotlin/sequences/FlatteningSequence$iterator$1", "", "(Lkotlin/sequences/FlatteningSequence;)V", "itemIterator", "getItemIterator", "()Ljava/util/Iterator;", "setItemIterator", "(Ljava/util/Iterator;)V", "iterator", "getIterator", "ensureItemIterator", "", "hasNext", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
/* compiled from: Sequences.kt */
public final class FlatteningSequence$iterator$1 implements Iterator<E>, KMappedMarker {
    @Nullable
    private Iterator<? extends E> itemIterator;
    @NotNull
    private final Iterator<T> iterator;
    final /* synthetic */ FlatteningSequence this$0;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    FlatteningSequence$iterator$1(FlatteningSequence $outer) {
        this.this$0 = $outer;
        this.iterator = $outer.sequence.iterator();
    }

    @NotNull
    public final Iterator<T> getIterator() {
        return this.iterator;
    }

    @Nullable
    public final Iterator<E> getItemIterator() {
        return this.itemIterator;
    }

    public final void setItemIterator(@Nullable Iterator<? extends E> <set-?>) {
        this.itemIterator = <set-?>;
    }

    public E next() {
        if (ensureItemIterator()) {
            Iterator it = this.itemIterator;
            if (it == null) {
                Intrinsics.throwNpe();
            }
            return it.next();
        }
        throw new NoSuchElementException();
    }

    public boolean hasNext() {
        return ensureItemIterator();
    }

    private final boolean ensureItemIterator() {
        Iterator it = this.itemIterator;
        if (!(it == null || it.hasNext())) {
            this.itemIterator = (Iterator) null;
        }
        while (this.itemIterator == null) {
            if (!this.iterator.hasNext()) {
                return false;
            }
            Iterator nextItemIterator = (Iterator) this.this$0.iterator.invoke(this.this$0.transformer.invoke(this.iterator.next()));
            if (nextItemIterator.hasNext()) {
                this.itemIterator = nextItemIterator;
                return true;
            }
        }
        return true;
    }
}
