package kotlin.io;

import java.io.BufferedInputStream;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.ByteIterator;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\t\u0010\u0012\u001a\u00020\u0004H\u0002J\b\u0010\t\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\b¨\u0006\u0016"}, d2 = {"kotlin/io/ByteStreamsKt$iterator$1", "Lkotlin/collections/ByteIterator;", "(Ljava/io/BufferedInputStream;)V", "finished", "", "getFinished", "()Z", "setFinished", "(Z)V", "nextByte", "", "getNextByte", "()I", "setNextByte", "(I)V", "nextPrepared", "getNextPrepared", "setNextPrepared", "hasNext", "", "prepareNext", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
/* compiled from: IOStreams.kt */
public final class ByteStreamsKt$iterator$1 extends ByteIterator {
    private boolean finished;
    private int nextByte = -1;
    private boolean nextPrepared;
    final /* synthetic */ BufferedInputStream receiver$0;

    ByteStreamsKt$iterator$1(BufferedInputStream $receiver) {
        this.receiver$0 = $receiver;
    }

    public final int getNextByte() {
        return this.nextByte;
    }

    public final void setNextByte(int <set-?>) {
        this.nextByte = <set-?>;
    }

    public final boolean getNextPrepared() {
        return this.nextPrepared;
    }

    public final void setNextPrepared(boolean <set-?>) {
        this.nextPrepared = <set-?>;
    }

    public final boolean getFinished() {
        return this.finished;
    }

    public final void setFinished(boolean <set-?>) {
        this.finished = <set-?>;
    }

    private final void prepareNext() {
        boolean z = true;
        if (!this.nextPrepared && !this.finished) {
            this.nextByte = this.receiver$0.read();
            this.nextPrepared = true;
            if (this.nextByte != -1) {
                z = false;
            }
            this.finished = z;
        }
    }

    public boolean hasNext() {
        prepareNext();
        return !this.finished;
    }

    public byte nextByte() {
        prepareNext();
        if (this.finished) {
            throw new NoSuchElementException("Input stream is over.");
        }
        byte res = (byte) this.nextByte;
        this.nextPrepared = false;
        return res;
    }
}
