package com.facebook.stetho.server;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class LeakyBufferedInputStream extends BufferedInputStream {
    private boolean mLeaked;
    private boolean mMarked;

    public LeakyBufferedInputStream(InputStream in, int bufSize) {
        super(in, bufSize);
    }

    public synchronized void mark(int readlimit) {
        throwIfLeaked();
        this.mMarked = true;
        super.mark(readlimit);
    }

    public synchronized void reset() throws IOException {
        throwIfLeaked();
        this.mMarked = false;
        super.reset();
    }

    public boolean markSupported() {
        return true;
    }

    public synchronized InputStream leakBufferAndStream() {
        throwIfLeaked();
        throwIfMarked();
        this.mLeaked = true;
        return new CompositeInputStream(new InputStream[]{new ByteArrayInputStream(clearBufferLocked()), this.in});
    }

    private byte[] clearBufferLocked() {
        byte[] leaked = new byte[(this.count - this.pos)];
        System.arraycopy(this.buf, this.pos, leaked, 0, leaked.length);
        this.pos = 0;
        this.count = 0;
        return leaked;
    }

    private void throwIfLeaked() {
        if (this.mLeaked) {
            throw new IllegalStateException();
        }
    }

    private void throwIfMarked() {
        if (this.mMarked) {
            throw new IllegalStateException();
        }
    }
}
