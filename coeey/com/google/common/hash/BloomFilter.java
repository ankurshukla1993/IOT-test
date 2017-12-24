package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.hash.BloomFilterStrategies.BitArray;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.annotation.Nullable;

@Beta
public final class BloomFilter<T> implements Predicate<T>, Serializable {
    private static final Strategy DEFAULT_STRATEGY = BloomFilterStrategies.MURMUR128_MITZ_64;
    private final BitArray bits;
    private final Funnel<? super T> funnel;
    private final int numHashFunctions;
    private final Strategy strategy;

    private BloomFilter(BitArray bits, int numHashFunctions, Funnel<? super T> funnel, Strategy strategy) {
        boolean z;
        Preconditions.checkArgument(numHashFunctions > 0, "numHashFunctions (%s) must be > 0", new Object[]{Integer.valueOf(numHashFunctions)});
        if (numHashFunctions <= 255) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "numHashFunctions (%s) must be <= 255", new Object[]{Integer.valueOf(numHashFunctions)});
        this.bits = (BitArray) Preconditions.checkNotNull(bits);
        this.numHashFunctions = numHashFunctions;
        this.funnel = (Funnel) Preconditions.checkNotNull(funnel);
        this.strategy = (Strategy) Preconditions.checkNotNull(strategy);
    }

    public BloomFilter<T> copy() {
        return new BloomFilter(this.bits.copy(), this.numHashFunctions, this.funnel, this.strategy);
    }

    public boolean mightContain(T object) {
        return this.strategy.mightContain(object, this.funnel, this.numHashFunctions, this.bits);
    }

    @Deprecated
    public boolean apply(T input) {
        return mightContain(input);
    }

    public boolean put(T object) {
        return this.strategy.put(object, this.funnel, this.numHashFunctions, this.bits);
    }

    public double expectedFpp() {
        return Math.pow(((double) this.bits.bitCount()) / ((double) bitSize()), (double) this.numHashFunctions);
    }

    @VisibleForTesting
    long bitSize() {
        return this.bits.bitSize();
    }

    public boolean isCompatible(BloomFilter<T> that) {
        Preconditions.checkNotNull(that);
        return this != that && this.numHashFunctions == that.numHashFunctions && bitSize() == that.bitSize() && this.strategy.equals(that.strategy) && this.funnel.equals(that.funnel);
    }

    public void putAll(BloomFilter<T> that) {
        boolean z;
        Preconditions.checkNotNull(that);
        Preconditions.checkArgument(this != that, "Cannot combine a BloomFilter with itself.");
        if (this.numHashFunctions == that.numHashFunctions) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "BloomFilters must have the same number of hash functions (%s != %s)", new Object[]{Integer.valueOf(this.numHashFunctions), Integer.valueOf(that.numHashFunctions)});
        if (bitSize() == that.bitSize()) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "BloomFilters must have the same size underlying bit arrays (%s != %s)", new Object[]{Long.valueOf(bitSize()), Long.valueOf(that.bitSize())});
        Preconditions.checkArgument(this.strategy.equals(that.strategy), "BloomFilters must have equal strategies (%s != %s)", new Object[]{this.strategy, that.strategy});
        Preconditions.checkArgument(this.funnel.equals(that.funnel), "BloomFilters must have equal funnels (%s != %s)", new Object[]{this.funnel, that.funnel});
        this.bits.putAll(that.bits);
    }

    public boolean equals(@Nullable Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof BloomFilter)) {
            return false;
        }
        BloomFilter<?> that = (BloomFilter) object;
        if (this.numHashFunctions == that.numHashFunctions && this.funnel.equals(that.funnel) && this.bits.equals(that.bits) && this.strategy.equals(that.strategy)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.numHashFunctions), this.funnel, this.strategy, this.bits);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int expectedInsertions, double fpp) {
        return create(funnel, expectedInsertions, fpp, DEFAULT_STRATEGY);
    }

    @VisibleForTesting
    static <T> BloomFilter<T> create(Funnel<? super T> funnel, int expectedInsertions, double fpp, Strategy strategy) {
        boolean z;
        Preconditions.checkNotNull(funnel);
        if (expectedInsertions >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "Expected insertions (%s) must be >= 0", new Object[]{Integer.valueOf(expectedInsertions)});
        if (fpp > 0.0d) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "False positive probability (%s) must be > 0.0", new Object[]{Double.valueOf(fpp)});
        if (fpp < 1.0d) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "False positive probability (%s) must be < 1.0", new Object[]{Double.valueOf(fpp)});
        Preconditions.checkNotNull(strategy);
        if (expectedInsertions == 0) {
            expectedInsertions = 1;
        }
        long numBits = optimalNumOfBits((long) expectedInsertions, fpp);
        try {
            return new BloomFilter(new BitArray(numBits), optimalNumOfHashFunctions((long) expectedInsertions, numBits), funnel, strategy);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Could not create BloomFilter of " + numBits + " bits", e);
        }
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int expectedInsertions) {
        return create(funnel, expectedInsertions, 0.03d);
    }

    @VisibleForTesting
    static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((((double) m) / ((double) n)) * Math.log(2.0d)));
    }

    @VisibleForTesting
    static long optimalNumOfBits(long n, double p) {
        if (p == 0.0d) {
            p = Double.MIN_VALUE;
        }
        return (long) ((((double) (-n)) * Math.log(p)) / (Math.log(2.0d) * Math.log(2.0d)));
    }

    private Object writeReplace() {
        return new SerialForm(this);
    }

    public void writeTo(OutputStream out) throws IOException {
        DataOutputStream dout = new DataOutputStream(out);
        dout.writeByte(SignedBytes.checkedCast((long) this.strategy.ordinal()));
        dout.writeByte(UnsignedBytes.checkedCast((long) this.numHashFunctions));
        dout.writeInt(this.bits.data.length);
        for (long value : this.bits.data) {
            dout.writeLong(value);
        }
    }

    public static <T> BloomFilter<T> readFrom(InputStream in, Funnel<T> funnel) throws IOException {
        Preconditions.checkNotNull(in, "InputStream");
        Preconditions.checkNotNull(funnel, "Funnel");
        try {
            DataInputStream din = new DataInputStream(in);
            int strategyOrdinal = din.readByte();
            int numHashFunctions = UnsignedBytes.toInt(din.readByte());
            int dataLength = din.readInt();
            Strategy strategy = BloomFilterStrategies.values()[strategyOrdinal];
            long[] data = new long[dataLength];
            for (int i = 0; i < data.length; i++) {
                data[i] = din.readLong();
            }
            return new BloomFilter(new BitArray(data), numHashFunctions, funnel, strategy);
        } catch (RuntimeException e) {
            String valueOf = String.valueOf(String.valueOf("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: "));
            IOException ioException = new IOException(new StringBuilder(valueOf.length() + 65).append(valueOf).append(-1).append(" numHashFunctions: ").append(-1).append(" dataLength: ").append(-1).toString());
            ioException.initCause(e);
            throw ioException;
        }
    }
}
