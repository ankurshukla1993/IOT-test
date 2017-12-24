package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;

public abstract class ByteSource {
    private static final int BUF_SIZE = 4096;
    private static final byte[] countBuffer = new byte[4096];

    private final class AsCharSource extends CharSource {
        private final Charset charset;

        private AsCharSource(Charset charset) {
            this.charset = (Charset) Preconditions.checkNotNull(charset);
        }

        public Reader openStream() throws IOException {
            return new InputStreamReader(ByteSource.this.openStream(), this.charset);
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(ByteSource.this.toString()));
            String valueOf2 = String.valueOf(String.valueOf(this.charset));
            return new StringBuilder((valueOf.length() + 15) + valueOf2.length()).append(valueOf).append(".asCharSource(").append(valueOf2).append(")").toString();
        }
    }

    private static class ByteArrayByteSource extends ByteSource {
        protected final byte[] bytes;

        protected ByteArrayByteSource(byte[] bytes) {
            this.bytes = (byte[]) Preconditions.checkNotNull(bytes);
        }

        public InputStream openStream() {
            return new ByteArrayInputStream(this.bytes);
        }

        public InputStream openBufferedStream() throws IOException {
            return openStream();
        }

        public boolean isEmpty() {
            return this.bytes.length == 0;
        }

        public long size() {
            return (long) this.bytes.length;
        }

        public byte[] read() {
            return (byte[]) this.bytes.clone();
        }

        public long copyTo(OutputStream output) throws IOException {
            output.write(this.bytes);
            return (long) this.bytes.length;
        }

        public <T> T read(ByteProcessor<T> processor) throws IOException {
            processor.processBytes(this.bytes, 0, this.bytes.length);
            return processor.getResult();
        }

        public HashCode hash(HashFunction hashFunction) throws IOException {
            return hashFunction.hashBytes(this.bytes);
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(Ascii.truncate(BaseEncoding.base16().encode(this.bytes), 30, "...")));
            return new StringBuilder(valueOf.length() + 17).append("ByteSource.wrap(").append(valueOf).append(")").toString();
        }
    }

    private static final class ConcatenatedByteSource extends ByteSource {
        private final Iterable<? extends ByteSource> sources;

        ConcatenatedByteSource(Iterable<? extends ByteSource> sources) {
            this.sources = (Iterable) Preconditions.checkNotNull(sources);
        }

        public InputStream openStream() throws IOException {
            return new MultiInputStream(this.sources.iterator());
        }

        public boolean isEmpty() throws IOException {
            for (ByteSource source : this.sources) {
                if (!source.isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        public long size() throws IOException {
            long result = 0;
            for (ByteSource source : this.sources) {
                result += source.size();
            }
            return result;
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.sources));
            return new StringBuilder(valueOf.length() + 19).append("ByteSource.concat(").append(valueOf).append(")").toString();
        }
    }

    private static final class EmptyByteSource extends ByteArrayByteSource {
        private static final EmptyByteSource INSTANCE = new EmptyByteSource();

        private EmptyByteSource() {
            super(new byte[0]);
        }

        public CharSource asCharSource(Charset charset) {
            Preconditions.checkNotNull(charset);
            return CharSource.empty();
        }

        public byte[] read() {
            return this.bytes;
        }

        public String toString() {
            return "ByteSource.empty()";
        }
    }

    private final class SlicedByteSource extends ByteSource {
        private final long length;
        private final long offset;

        private SlicedByteSource(long offset, long length) {
            boolean z;
            if (offset >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "offset (%s) may not be negative", Long.valueOf(offset));
            if (length >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "length (%s) may not be negative", Long.valueOf(length));
            this.offset = offset;
            this.length = length;
        }

        public InputStream openStream() throws IOException {
            return sliceStream(ByteSource.this.openStream());
        }

        public InputStream openBufferedStream() throws IOException {
            return sliceStream(ByteSource.this.openBufferedStream());
        }

        private InputStream sliceStream(InputStream in) throws IOException {
            Closer closer;
            if (this.offset > 0) {
                try {
                    ByteStreams.skipFully(in, this.offset);
                } catch (Throwable th) {
                    closer.close();
                }
            }
            return ByteStreams.limit(in, this.length);
        }

        public ByteSource slice(long offset, long length) {
            boolean z;
            if (offset >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "offset (%s) may not be negative", Long.valueOf(offset));
            if (length >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "length (%s) may not be negative", Long.valueOf(length));
            return ByteSource.this.slice(this.offset + offset, Math.min(length, this.length - offset));
        }

        public boolean isEmpty() throws IOException {
            return this.length == 0 || super.isEmpty();
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(ByteSource.this.toString()));
            long j = this.offset;
            return new StringBuilder(valueOf.length() + 50).append(valueOf).append(".slice(").append(j).append(", ").append(this.length).append(")").toString();
        }
    }

    public abstract InputStream openStream() throws IOException;

    protected ByteSource() {
    }

    public CharSource asCharSource(Charset charset) {
        return new AsCharSource(charset);
    }

    public InputStream openBufferedStream() throws IOException {
        InputStream in = openStream();
        return in instanceof BufferedInputStream ? (BufferedInputStream) in : new BufferedInputStream(in);
    }

    public ByteSource slice(long offset, long length) {
        return new SlicedByteSource(offset, length);
    }

    public boolean isEmpty() throws IOException {
        Closer closer = Closer.create();
        try {
            boolean z = ((InputStream) closer.register(openStream())).read() == -1;
            closer.close();
            return z;
        } catch (Throwable th) {
            closer.close();
        }
    }

    public long size() throws IOException {
        long countBySkipping;
        Closer closer = Closer.create();
        try {
            countBySkipping = countBySkipping((InputStream) closer.register(openStream()));
            closer.close();
        } catch (IOException e) {
            closer.close();
            closer = Closer.create();
            countBySkipping = countByReading((InputStream) closer.register(openStream()));
            closer.close();
        } catch (Throwable e2) {
            try {
                RuntimeException rethrow = closer.rethrow(e2);
            } catch (Throwable th) {
                closer.close();
            }
        }
        return countBySkipping;
    }

    private long countBySkipping(InputStream in) throws IOException {
        long count = 0;
        while (true) {
            long skipped = in.skip((long) Math.min(in.available(), Integer.MAX_VALUE));
            if (skipped > 0) {
                count += skipped;
            } else if (in.read() == -1) {
                return count;
            } else {
                if (count == 0 && in.available() == 0) {
                    throw new IOException();
                }
                count++;
            }
        }
    }

    private long countByReading(InputStream in) throws IOException {
        long count = 0;
        while (true) {
            long read = (long) in.read(countBuffer);
            if (read == -1) {
                return count;
            }
            count += read;
        }
    }

    public long copyTo(OutputStream output) throws IOException {
        Preconditions.checkNotNull(output);
        Closer closer = Closer.create();
        try {
            long copy = ByteStreams.copy((InputStream) closer.register(openStream()), output);
            closer.close();
            return copy;
        } catch (Throwable th) {
            closer.close();
        }
    }

    public long copyTo(ByteSink sink) throws IOException {
        Preconditions.checkNotNull(sink);
        Closer closer = Closer.create();
        try {
            long copy = ByteStreams.copy((InputStream) closer.register(openStream()), (OutputStream) closer.register(sink.openStream()));
            closer.close();
            return copy;
        } catch (Throwable th) {
            closer.close();
        }
    }

    public byte[] read() throws IOException {
        Closer closer = Closer.create();
        try {
            byte[] toByteArray = ByteStreams.toByteArray((InputStream) closer.register(openStream()));
            closer.close();
            return toByteArray;
        } catch (Throwable th) {
            closer.close();
        }
    }

    @Beta
    public <T> T read(ByteProcessor<T> processor) throws IOException {
        Preconditions.checkNotNull(processor);
        Closer closer = Closer.create();
        try {
            T readBytes = ByteStreams.readBytes((InputStream) closer.register(openStream()), processor);
            closer.close();
            return readBytes;
        } catch (Throwable th) {
            closer.close();
        }
    }

    public HashCode hash(HashFunction hashFunction) throws IOException {
        Hasher hasher = hashFunction.newHasher();
        copyTo(Funnels.asOutputStream(hasher));
        return hasher.hash();
    }

    public boolean contentEquals(ByteSource other) throws IOException {
        Preconditions.checkNotNull(other);
        byte[] buf1 = new byte[4096];
        byte[] buf2 = new byte[4096];
        Closer closer = Closer.create();
        try {
            InputStream in1 = (InputStream) closer.register(openStream());
            InputStream in2 = (InputStream) closer.register(other.openStream());
            int read1;
            do {
                read1 = ByteStreams.read(in1, buf1, 0, 4096);
                if (!(read1 == ByteStreams.read(in2, buf2, 0, 4096) && Arrays.equals(buf1, buf2))) {
                    closer.close();
                    return false;
                }
            } while (read1 == 4096);
            closer.close();
            return true;
        } catch (Throwable th) {
            closer.close();
        }
    }

    public static ByteSource concat(Iterable<? extends ByteSource> sources) {
        return new ConcatenatedByteSource(sources);
    }

    public static ByteSource concat(Iterator<? extends ByteSource> sources) {
        return concat(ImmutableList.copyOf(sources));
    }

    public static ByteSource concat(ByteSource... sources) {
        return concat(ImmutableList.copyOf(sources));
    }

    public static ByteSource wrap(byte[] b) {
        return new ByteArrayByteSource(b);
    }

    public static ByteSource empty() {
        return EmptyByteSource.INSTANCE;
    }
}
