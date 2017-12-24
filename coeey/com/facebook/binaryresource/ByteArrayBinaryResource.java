package com.facebook.binaryresource;

import com.facebook.common.internal.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteArrayBinaryResource implements BinaryResource {
    private final byte[] mBytes;

    public ByteArrayBinaryResource(byte[] bytes) {
        this.mBytes = (byte[]) Preconditions.checkNotNull(bytes);
    }

    public long size() {
        return (long) this.mBytes.length;
    }

    public InputStream openStream() throws IOException {
        return new ByteArrayInputStream(this.mBytes);
    }

    public byte[] read() {
        return this.mBytes;
    }
}
