package kotlin.io;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0010(\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\t\u0010\u0007\u001a\u00020\u0005H\u0002J\t\u0010\b\u001a\u00020\u0002H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0002X\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"kotlin/io/LinesSequence$iterator$1", "", "", "(Lkotlin/io/LinesSequence;)V", "done", "", "nextValue", "hasNext", "next", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
/* compiled from: ReadWrite.kt */
public final class LinesSequence$iterator$1 implements Iterator<String>, KMappedMarker {
    private boolean done;
    private String nextValue;
    final /* synthetic */ LinesSequence this$0;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    LinesSequence$iterator$1(LinesSequence $outer) {
        this.this$0 = $outer;
    }

    public boolean hasNext() {
        if (this.nextValue == null && !this.done) {
            this.nextValue = this.this$0.reader.readLine();
            if (this.nextValue == null) {
                this.done = true;
            }
        }
        if (this.nextValue != null) {
            return true;
        }
        return false;
    }

    @NotNull
    public String next() {
        if (hasNext()) {
            String answer = this.nextValue;
            this.nextValue = (String) null;
            if (answer == null) {
                Intrinsics.throwNpe();
            }
            return answer;
        }
        throw new NoSuchElementException();
    }
}
