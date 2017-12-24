package com.google.protobuf;

import java.io.OutputStream;
import java.nio.ByteBuffer;

class CodedOutputStream$ByteBufferOutputStream extends OutputStream {
    private final ByteBuffer byteBuffer;

    public CodedOutputStream$ByteBufferOutputStream(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    public void write(int i) {
        this.byteBuffer.put((byte) i);
    }

    public void write(byte[] bArr, int i, int i2) {
        this.byteBuffer.put(bArr, i, i2);
    }
}
