package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class NativePooledByteBufferFactory implements PooledByteBufferFactory {
    private final NativeMemoryChunkPool mPool;
    private final PooledByteStreams mPooledByteStreams;

    public NativePooledByteBufferFactory(NativeMemoryChunkPool pool, PooledByteStreams pooledByteStreams) {
        this.mPool = pool;
        this.mPooledByteStreams = pooledByteStreams;
    }

    public NativePooledByteBuffer newByteBuffer(int size) {
        Preconditions.checkArgument(size > 0);
        CloseableReference<NativeMemoryChunk> chunkRef = CloseableReference.of(this.mPool.get(size), this.mPool);
        try {
            NativePooledByteBuffer nativePooledByteBuffer = new NativePooledByteBuffer(chunkRef, size);
            return nativePooledByteBuffer;
        } finally {
            chunkRef.close();
        }
    }

    public NativePooledByteBuffer newByteBuffer(InputStream inputStream) throws IOException {
        NativePooledByteBufferOutputStream outputStream = new NativePooledByteBufferOutputStream(this.mPool);
        try {
            NativePooledByteBuffer newByteBuf = newByteBuf(inputStream, outputStream);
            return newByteBuf;
        } finally {
            outputStream.close();
        }
    }

    public NativePooledByteBuffer newByteBuffer(byte[] bytes) {
        NativePooledByteBufferOutputStream outputStream = new NativePooledByteBufferOutputStream(this.mPool, bytes.length);
        try {
            outputStream.write(bytes, 0, bytes.length);
            NativePooledByteBuffer toByteBuffer = outputStream.toByteBuffer();
            outputStream.close();
            return toByteBuffer;
        } catch (IOException ioe) {
            throw Throwables.propagate(ioe);
        } catch (Throwable th) {
            outputStream.close();
        }
    }

    public NativePooledByteBuffer newByteBuffer(InputStream inputStream, int initialCapacity) throws IOException {
        NativePooledByteBufferOutputStream outputStream = new NativePooledByteBufferOutputStream(this.mPool, initialCapacity);
        try {
            NativePooledByteBuffer newByteBuf = newByteBuf(inputStream, outputStream);
            return newByteBuf;
        } finally {
            outputStream.close();
        }
    }

    @VisibleForTesting
    NativePooledByteBuffer newByteBuf(InputStream inputStream, NativePooledByteBufferOutputStream outputStream) throws IOException {
        this.mPooledByteStreams.copy(inputStream, outputStream);
        return outputStream.toByteBuffer();
    }

    public NativePooledByteBufferOutputStream newOutputStream() {
        return new NativePooledByteBufferOutputStream(this.mPool);
    }

    public NativePooledByteBufferOutputStream newOutputStream(int initialCapacity) {
        return new NativePooledByteBufferOutputStream(this.mPool, initialCapacity);
    }
}
