package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

final class SipHashFunction extends AbstractStreamingHashFunction implements Serializable {
    private static final long serialVersionUID = 0;
    private final int f204c;
    private final int f205d;
    private final long k0;
    private final long k1;

    private static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;
        private long f201b = 0;
        private final int f202c;
        private final int f203d;
        private long finalM = 0;
        private long v0 = 8317987319222330741L;
        private long v1 = 7237128888997146477L;
        private long v2 = 7816392313619706465L;
        private long v3 = 8387220255154660723L;

        SipHasher(int c, int d, long k0, long k1) {
            super(8);
            this.f202c = c;
            this.f203d = d;
            this.v0 ^= k0;
            this.v1 ^= k1;
            this.v2 ^= k0;
            this.v3 ^= k1;
        }

        protected void process(ByteBuffer buffer) {
            this.f201b += 8;
            processM(buffer.getLong());
        }

        protected void processRemaining(ByteBuffer buffer) {
            this.f201b += (long) buffer.remaining();
            int i = 0;
            while (buffer.hasRemaining()) {
                this.finalM ^= (((long) buffer.get()) & 255) << i;
                i += 8;
            }
        }

        public HashCode makeHash() {
            this.finalM ^= this.f201b << 56;
            processM(this.finalM);
            this.v2 ^= 255;
            sipRound(this.f203d);
            return HashCode.fromLong(((this.v0 ^ this.v1) ^ this.v2) ^ this.v3);
        }

        private void processM(long m) {
            this.v3 ^= m;
            sipRound(this.f202c);
            this.v0 ^= m;
        }

        private void sipRound(int iterations) {
            for (int i = 0; i < iterations; i++) {
                this.v0 += this.v1;
                this.v2 += this.v3;
                this.v1 = Long.rotateLeft(this.v1, 13);
                this.v3 = Long.rotateLeft(this.v3, 16);
                this.v1 ^= this.v0;
                this.v3 ^= this.v2;
                this.v0 = Long.rotateLeft(this.v0, 32);
                this.v2 += this.v1;
                this.v0 += this.v3;
                this.v1 = Long.rotateLeft(this.v1, 17);
                this.v3 = Long.rotateLeft(this.v3, 21);
                this.v1 ^= this.v2;
                this.v3 ^= this.v0;
                this.v2 = Long.rotateLeft(this.v2, 32);
            }
        }
    }

    SipHashFunction(int c, int d, long k0, long k1) {
        boolean z;
        Preconditions.checkArgument(c > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", Integer.valueOf(c));
        if (d > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "The number of SipRound iterations (d=%s) during Finalization must be positive.", Integer.valueOf(d));
        this.f204c = c;
        this.f205d = d;
        this.k0 = k0;
        this.k1 = k1;
    }

    public int bits() {
        return 64;
    }

    public Hasher newHasher() {
        return new SipHasher(this.f204c, this.f205d, this.k0, this.k1);
    }

    public String toString() {
        int i = this.f204c;
        int i2 = this.f205d;
        long j = this.k0;
        return "Hashing.sipHash" + i + i2 + "(" + j + ", " + this.k1 + ")";
    }

    public boolean equals(@Nullable Object object) {
        if (!(object instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction other = (SipHashFunction) object;
        if (this.f204c == other.f204c && this.f205d == other.f205d && this.k0 == other.k0 && this.k1 == other.k1) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) ((((long) ((getClass().hashCode() ^ this.f204c) ^ this.f205d)) ^ this.k0) ^ this.k1);
    }
}
