package com.google.protobuf;

import com.google.protobuf.MessageLite.Builder;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CodedInputStream {
    private static final int BUFFER_SIZE = 4096;
    private static final int DEFAULT_RECURSION_LIMIT = 64;
    private static final int DEFAULT_SIZE_LIMIT = 67108864;
    private final byte[] buffer;
    private final boolean bufferIsImmutable;
    private int bufferPos;
    private int bufferSize;
    private int bufferSizeAfterLimit;
    private int currentLimit;
    private boolean enableAliasing;
    private final InputStream input;
    private int lastTag;
    private int recursionDepth;
    private int recursionLimit;
    private RefillCallback refillCallback;
    private int sizeLimit;
    private int totalBytesRetired;

    private CodedInputStream(LiteralByteString literalByteString) {
        this.enableAliasing = false;
        this.currentLimit = Integer.MAX_VALUE;
        this.recursionLimit = 64;
        this.sizeLimit = DEFAULT_SIZE_LIMIT;
        this.refillCallback = null;
        this.buffer = literalByteString.bytes;
        this.bufferPos = literalByteString.getOffsetIntoBytes();
        this.bufferSize = this.bufferPos + literalByteString.size();
        this.totalBytesRetired = -this.bufferPos;
        this.input = null;
        this.bufferIsImmutable = true;
    }

    private CodedInputStream(InputStream inputStream) {
        this.enableAliasing = false;
        this.currentLimit = Integer.MAX_VALUE;
        this.recursionLimit = 64;
        this.sizeLimit = DEFAULT_SIZE_LIMIT;
        this.refillCallback = null;
        this.buffer = new byte[4096];
        this.bufferSize = 0;
        this.bufferPos = 0;
        this.totalBytesRetired = 0;
        this.input = inputStream;
        this.bufferIsImmutable = false;
    }

    private CodedInputStream(byte[] bArr, int i, int i2) {
        this.enableAliasing = false;
        this.currentLimit = Integer.MAX_VALUE;
        this.recursionLimit = 64;
        this.sizeLimit = DEFAULT_SIZE_LIMIT;
        this.refillCallback = null;
        this.buffer = bArr;
        this.bufferSize = i + i2;
        this.bufferPos = i;
        this.totalBytesRetired = -i;
        this.input = null;
        this.bufferIsImmutable = false;
    }

    public static int decodeZigZag32(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public static long decodeZigZag64(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    private void ensureAvailable(int i) {
        if (this.bufferSize - this.bufferPos < i) {
            refillBuffer(i);
        }
    }

    static CodedInputStream newInstance(LiteralByteString literalByteString) {
        CodedInputStream codedInputStream = new CodedInputStream(literalByteString);
        try {
            codedInputStream.pushLimit(literalByteString.size());
            return codedInputStream;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static CodedInputStream newInstance(InputStream inputStream) {
        return new CodedInputStream(inputStream);
    }

    public static CodedInputStream newInstance(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return newInstance(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
        }
        ByteBuffer duplicate = byteBuffer.duplicate();
        byte[] bArr = new byte[duplicate.remaining()];
        duplicate.get(bArr);
        return newInstance(bArr);
    }

    public static CodedInputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i, int i2) {
        CodedInputStream codedInputStream = new CodedInputStream(bArr, i, i2);
        try {
            codedInputStream.pushLimit(i2);
            return codedInputStream;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    private byte[] readRawBytesSlowPath(int i) {
        if (i <= 0) {
            if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            throw InvalidProtocolBufferException.negativeSize();
        } else if ((this.totalBytesRetired + this.bufferPos) + i > this.currentLimit) {
            skipRawBytes((this.currentLimit - this.totalBytesRetired) - this.bufferPos);
            throw InvalidProtocolBufferException.truncatedMessage();
        } else if (i < 4096) {
            Object obj = new byte[i];
            r2 = this.bufferSize - this.bufferPos;
            System.arraycopy(this.buffer, this.bufferPos, obj, 0, r2);
            this.bufferPos = this.bufferSize;
            ensureAvailable(i - r2);
            System.arraycopy(this.buffer, 0, obj, r2, i - r2);
            this.bufferPos = i - r2;
            return obj;
        } else {
            int i2 = this.bufferPos;
            int i3 = this.bufferSize;
            this.totalBytesRetired += this.bufferSize;
            this.bufferPos = 0;
            this.bufferSize = 0;
            int i4 = i - (i3 - i2);
            List<byte[]> arrayList = new ArrayList();
            int i5 = i4;
            while (i5 > 0) {
                Object obj2 = new byte[Math.min(i5, 4096)];
                i4 = 0;
                while (i4 < obj2.length) {
                    r2 = this.input == null ? -1 : this.input.read(obj2, i4, obj2.length - i4);
                    if (r2 == -1) {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                    this.totalBytesRetired += r2;
                    i4 += r2;
                }
                i4 = i5 - obj2.length;
                arrayList.add(obj2);
                i5 = i4;
            }
            Object obj3 = new byte[i];
            i4 = i3 - i2;
            System.arraycopy(this.buffer, i2, obj3, 0, i4);
            r2 = i4;
            for (byte[] bArr : arrayList) {
                System.arraycopy(bArr, 0, obj3, r2, bArr.length);
                r2 = bArr.length + r2;
            }
            return obj3;
        }
    }

    public static int readRawVarint32(int i, InputStream inputStream) {
        if ((i & 128) != 0) {
            int read;
            i &= 127;
            int i2 = 7;
            while (i2 < 32) {
                read = inputStream.read();
                if (read != -1) {
                    i |= (read & 127) << i2;
                    if ((read & 128) == 0) {
                        break;
                    }
                    i2 += 7;
                } else {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
            }
            while (i2 < 64) {
                read = inputStream.read();
                if (read == -1) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                } else if ((read & 128) != 0) {
                    i2 += 7;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }
        return i;
    }

    static int readRawVarint32(InputStream inputStream) {
        int read = inputStream.read();
        if (read != -1) {
            return readRawVarint32(read, inputStream);
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    private void recomputeBufferSizeAfterLimit() {
        this.bufferSize += this.bufferSizeAfterLimit;
        int i = this.totalBytesRetired + this.bufferSize;
        if (i > this.currentLimit) {
            this.bufferSizeAfterLimit = i - this.currentLimit;
            this.bufferSize -= this.bufferSizeAfterLimit;
            return;
        }
        this.bufferSizeAfterLimit = 0;
    }

    private void refillBuffer(int i) {
        if (!tryRefillBuffer(i)) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    private void skipRawBytesSlowPath(int i) {
        if (i < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        } else if ((this.totalBytesRetired + this.bufferPos) + i > this.currentLimit) {
            skipRawBytes((this.currentLimit - this.totalBytesRetired) - this.bufferPos);
            throw InvalidProtocolBufferException.truncatedMessage();
        } else {
            int i2 = this.bufferSize - this.bufferPos;
            this.bufferPos = this.bufferSize;
            refillBuffer(1);
            while (i - i2 > this.bufferSize) {
                i2 += this.bufferSize;
                this.bufferPos = this.bufferSize;
                refillBuffer(1);
            }
            this.bufferPos = i - i2;
        }
    }

    private void skipRawVarint() {
        if (this.bufferSize - this.bufferPos >= 10) {
            byte[] bArr = this.buffer;
            int i = this.bufferPos;
            int i2 = 0;
            while (i2 < 10) {
                int i3 = i + 1;
                if (bArr[i] >= (byte) 0) {
                    this.bufferPos = i3;
                    return;
                } else {
                    i2++;
                    i = i3;
                }
            }
        }
        skipRawVarintSlowPath();
    }

    private void skipRawVarintSlowPath() {
        int i = 0;
        while (i < 10) {
            if (readRawByte() < (byte) 0) {
                i++;
            } else {
                return;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    private boolean tryRefillBuffer(int i) {
        if (this.bufferPos + i <= this.bufferSize) {
            throw new IllegalStateException("refillBuffer() called when " + i + " bytes were already available in buffer");
        } else if ((this.totalBytesRetired + this.bufferPos) + i > this.currentLimit) {
            return false;
        } else {
            if (this.refillCallback != null) {
                this.refillCallback.onRefill();
            }
            if (this.input == null) {
                return false;
            }
            int i2 = this.bufferPos;
            if (i2 > 0) {
                if (this.bufferSize > i2) {
                    System.arraycopy(this.buffer, i2, this.buffer, 0, this.bufferSize - i2);
                }
                this.totalBytesRetired += i2;
                this.bufferSize -= i2;
                this.bufferPos = 0;
            }
            i2 = this.input.read(this.buffer, this.bufferSize, this.buffer.length - this.bufferSize);
            if (i2 == 0 || i2 < -1 || i2 > this.buffer.length) {
                throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + i2 + "\nThe InputStream implementation is buggy.");
            } else if (i2 <= 0) {
                return false;
            } else {
                this.bufferSize += i2;
                if ((this.totalBytesRetired + i) - this.sizeLimit > 0) {
                    throw InvalidProtocolBufferException.sizeLimitExceeded();
                }
                recomputeBufferSizeAfterLimit();
                return this.bufferSize >= i ? true : tryRefillBuffer(i);
            }
        }
    }

    public void checkLastTagWas(int i) {
        if (this.lastTag != i) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }

    public void enableAliasing(boolean z) {
        this.enableAliasing = z;
    }

    public int getBytesUntilLimit() {
        if (this.currentLimit == Integer.MAX_VALUE) {
            return -1;
        }
        return this.currentLimit - (this.totalBytesRetired + this.bufferPos);
    }

    public int getLastTag() {
        return this.lastTag;
    }

    public int getTotalBytesRead() {
        return this.totalBytesRetired + this.bufferPos;
    }

    public boolean isAtEnd() {
        return this.bufferPos == this.bufferSize && !tryRefillBuffer(1);
    }

    public void popLimit(int i) {
        this.currentLimit = i;
        recomputeBufferSizeAfterLimit();
    }

    public int pushLimit(int i) {
        if (i < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        int i2 = (this.totalBytesRetired + this.bufferPos) + i;
        int i3 = this.currentLimit;
        if (i2 > i3) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        this.currentLimit = i2;
        recomputeBufferSizeAfterLimit();
        return i3;
    }

    public boolean readBool() {
        return readRawVarint64() != 0;
    }

    public byte[] readByteArray() {
        int readRawVarint32 = readRawVarint32();
        if (readRawVarint32 > this.bufferSize - this.bufferPos || readRawVarint32 <= 0) {
            return readRawBytesSlowPath(readRawVarint32);
        }
        byte[] copyOfRange = Arrays.copyOfRange(this.buffer, this.bufferPos, this.bufferPos + readRawVarint32);
        this.bufferPos = readRawVarint32 + this.bufferPos;
        return copyOfRange;
    }

    public ByteBuffer readByteBuffer() {
        int readRawVarint32 = readRawVarint32();
        if (readRawVarint32 > this.bufferSize - this.bufferPos || readRawVarint32 <= 0) {
            return readRawVarint32 == 0 ? Internal.EMPTY_BYTE_BUFFER : ByteBuffer.wrap(readRawBytesSlowPath(readRawVarint32));
        } else {
            ByteBuffer slice = (this.input == null && !this.bufferIsImmutable && this.enableAliasing) ? ByteBuffer.wrap(this.buffer, this.bufferPos, readRawVarint32).slice() : ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, this.bufferPos, this.bufferPos + readRawVarint32));
            this.bufferPos = readRawVarint32 + this.bufferPos;
            return slice;
        }
    }

    public ByteString readBytes() {
        int readRawVarint32 = readRawVarint32();
        if (readRawVarint32 > this.bufferSize - this.bufferPos || readRawVarint32 <= 0) {
            return readRawVarint32 == 0 ? ByteString.EMPTY : new LiteralByteString(readRawBytesSlowPath(readRawVarint32));
        } else {
            ByteString boundedByteString = (this.bufferIsImmutable && this.enableAliasing) ? new BoundedByteString(this.buffer, this.bufferPos, readRawVarint32) : ByteString.copyFrom(this.buffer, this.bufferPos, readRawVarint32);
            this.bufferPos = readRawVarint32 + this.bufferPos;
            return boundedByteString;
        }
    }

    public double readDouble() {
        return Double.longBitsToDouble(readRawLittleEndian64());
    }

    public int readEnum() {
        return readRawVarint32();
    }

    public int readFixed32() {
        return readRawLittleEndian32();
    }

    public long readFixed64() {
        return readRawLittleEndian64();
    }

    public float readFloat() {
        return Float.intBitsToFloat(readRawLittleEndian32());
    }

    public MessageLite readGroup(int i, Parser parser, ExtensionRegistryLite extensionRegistryLite) {
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
        this.recursionDepth++;
        MessageLite messageLite = (MessageLite) parser.parsePartialFrom(this, extensionRegistryLite);
        checkLastTagWas(WireFormat.makeTag(i, 4));
        this.recursionDepth--;
        return messageLite;
    }

    public void readGroup(int i, Builder builder, ExtensionRegistryLite extensionRegistryLite) {
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
        this.recursionDepth++;
        builder.mergeFrom(this, extensionRegistryLite);
        checkLastTagWas(WireFormat.makeTag(i, 4));
        this.recursionDepth--;
    }

    public int readInt32() {
        return readRawVarint32();
    }

    public long readInt64() {
        return readRawVarint64();
    }

    public MessageLite readMessage(Parser parser, ExtensionRegistryLite extensionRegistryLite) {
        int readRawVarint32 = readRawVarint32();
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
        int pushLimit = pushLimit(readRawVarint32);
        this.recursionDepth++;
        MessageLite messageLite = (MessageLite) parser.parsePartialFrom(this, extensionRegistryLite);
        checkLastTagWas(0);
        this.recursionDepth--;
        popLimit(pushLimit);
        return messageLite;
    }

    public void readMessage(Builder builder, ExtensionRegistryLite extensionRegistryLite) {
        int readRawVarint32 = readRawVarint32();
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
        readRawVarint32 = pushLimit(readRawVarint32);
        this.recursionDepth++;
        builder.mergeFrom(this, extensionRegistryLite);
        checkLastTagWas(0);
        this.recursionDepth--;
        popLimit(readRawVarint32);
    }

    public byte readRawByte() {
        if (this.bufferPos == this.bufferSize) {
            refillBuffer(1);
        }
        byte[] bArr = this.buffer;
        int i = this.bufferPos;
        this.bufferPos = i + 1;
        return bArr[i];
    }

    public byte[] readRawBytes(int i) {
        int i2 = this.bufferPos;
        if (i > this.bufferSize - i2 || i <= 0) {
            return readRawBytesSlowPath(i);
        }
        this.bufferPos = i2 + i;
        return Arrays.copyOfRange(this.buffer, i2, i2 + i);
    }

    public int readRawLittleEndian32() {
        int i = this.bufferPos;
        if (this.bufferSize - i < 4) {
            refillBuffer(4);
            i = this.bufferPos;
        }
        byte[] bArr = this.buffer;
        this.bufferPos = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16));
    }

    public long readRawLittleEndian64() {
        int i = this.bufferPos;
        if (this.bufferSize - i < 8) {
            refillBuffer(8);
            i = this.bufferPos;
        }
        byte[] bArr = this.buffer;
        this.bufferPos = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48));
    }

    public int readRawVarint32() {
        int i = this.bufferPos;
        if (this.bufferSize != i) {
            byte[] bArr = this.buffer;
            int i2 = i + 1;
            byte b = bArr[i];
            if (b >= (byte) 0) {
                this.bufferPos = i2;
                return b;
            } else if (this.bufferSize - i2 >= 9) {
                int i3 = i2 + 1;
                i = b ^ (bArr[i2] << 7);
                if (((long) i) < 0) {
                    i = (int) (-128 ^ ((long) i));
                } else {
                    i2 = i3 + 1;
                    i ^= bArr[i3] << 14;
                    if (((long) i) >= 0) {
                        i = (int) (((long) i) ^ 16256);
                        i3 = i2;
                    } else {
                        i3 = i2 + 1;
                        i ^= bArr[i2] << 21;
                        if (((long) i) < 0) {
                            i = (int) (-2080896 ^ ((long) i));
                        } else {
                            i2 = i3 + 1;
                            byte b2 = bArr[i3];
                            i = (int) (266354560 ^ ((long) (i ^ (b2 << 28))));
                            if (b2 < (byte) 0) {
                                i3 = i2 + 1;
                                if (bArr[i2] < (byte) 0) {
                                    i2 = i3 + 1;
                                    if (bArr[i3] < (byte) 0) {
                                        i3 = i2 + 1;
                                        if (bArr[i2] < (byte) 0) {
                                            i2 = i3 + 1;
                                            if (bArr[i3] < (byte) 0) {
                                                i3 = i2 + 1;
                                                if (bArr[i2] < (byte) 0) {
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            i3 = i2;
                        }
                    }
                }
                this.bufferPos = i3;
                return i;
            }
        }
        return (int) readRawVarint64SlowPath();
    }

    public long readRawVarint64() {
        int i = this.bufferPos;
        if (this.bufferSize != i) {
            byte[] bArr = this.buffer;
            int i2 = i + 1;
            byte b = bArr[i];
            if (b >= (byte) 0) {
                this.bufferPos = i2;
                return (long) b;
            } else if (this.bufferSize - i2 >= 9) {
                int i3 = i2 + 1;
                long j = (long) (b ^ (bArr[i2] << 7));
                if (j < 0) {
                    j ^= -128;
                } else {
                    int i4 = i3 + 1;
                    j ^= (long) (bArr[i3] << 14);
                    if (j >= 0) {
                        j ^= 16256;
                        i3 = i4;
                    } else {
                        i3 = i4 + 1;
                        j ^= (long) (bArr[i4] << 21);
                        if (j < 0) {
                            j ^= -2080896;
                        } else {
                            i4 = i3 + 1;
                            j ^= ((long) bArr[i3]) << 28;
                            if (j >= 0) {
                                j ^= 266354560;
                                i3 = i4;
                            } else {
                                i3 = i4 + 1;
                                j ^= ((long) bArr[i4]) << 35;
                                if (j < 0) {
                                    j ^= -34093383808L;
                                } else {
                                    i4 = i3 + 1;
                                    j ^= ((long) bArr[i3]) << 42;
                                    if (j >= 0) {
                                        j ^= 4363953127296L;
                                        i3 = i4;
                                    } else {
                                        i3 = i4 + 1;
                                        j ^= ((long) bArr[i4]) << 49;
                                        if (j < 0) {
                                            j ^= -558586000294016L;
                                        } else {
                                            i4 = i3 + 1;
                                            j = (j ^ (((long) bArr[i3]) << 56)) ^ 71499008037633920L;
                                            if (j < 0) {
                                                i3 = i4 + 1;
                                                if (((long) bArr[i4]) < 0) {
                                                }
                                            } else {
                                                i3 = i4;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.bufferPos = i3;
                return j;
            }
        }
        return readRawVarint64SlowPath();
    }

    long readRawVarint64SlowPath() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte readRawByte = readRawByte();
            j |= ((long) (readRawByte & 127)) << i;
            if ((readRawByte & 128) == 0) {
                return j;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    public int readSFixed32() {
        return readRawLittleEndian32();
    }

    public long readSFixed64() {
        return readRawLittleEndian64();
    }

    public int readSInt32() {
        return decodeZigZag32(readRawVarint32());
    }

    public long readSInt64() {
        return decodeZigZag64(readRawVarint64());
    }

    public String readString() {
        int readRawVarint32 = readRawVarint32();
        if (readRawVarint32 > this.bufferSize - this.bufferPos || readRawVarint32 <= 0) {
            return readRawVarint32 == 0 ? "" : new String(readRawBytesSlowPath(readRawVarint32), "UTF-8");
        } else {
            String str = new String(this.buffer, this.bufferPos, readRawVarint32, "UTF-8");
            this.bufferPos = readRawVarint32 + this.bufferPos;
            return str;
        }
    }

    public String readStringRequireUtf8() {
        byte[] bArr;
        int readRawVarint32 = readRawVarint32();
        int i = this.bufferPos;
        if (readRawVarint32 <= this.bufferSize - i && readRawVarint32 > 0) {
            bArr = this.buffer;
            this.bufferPos = i + readRawVarint32;
        } else if (readRawVarint32 == 0) {
            return "";
        } else {
            bArr = readRawBytesSlowPath(readRawVarint32);
            i = 0;
        }
        if (Utf8.isValidUtf8(bArr, i, i + readRawVarint32)) {
            return new String(bArr, i, readRawVarint32, "UTF-8");
        }
        throw InvalidProtocolBufferException.invalidUtf8();
    }

    public int readTag() {
        if (isAtEnd()) {
            this.lastTag = 0;
            return 0;
        }
        this.lastTag = readRawVarint32();
        if (WireFormat.getTagFieldNumber(this.lastTag) != 0) {
            return this.lastTag;
        }
        throw InvalidProtocolBufferException.invalidTag();
    }

    public int readUInt32() {
        return readRawVarint32();
    }

    public long readUInt64() {
        return readRawVarint64();
    }

    @Deprecated
    public void readUnknownGroup(int i, Builder builder) {
        readGroup(i, builder, null);
    }

    public void resetSizeCounter() {
        this.totalBytesRetired = -this.bufferPos;
    }

    public int setRecursionLimit(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Recursion limit cannot be negative: " + i);
        }
        int i2 = this.recursionLimit;
        this.recursionLimit = i;
        return i2;
    }

    public int setSizeLimit(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Size limit cannot be negative: " + i);
        }
        int i2 = this.sizeLimit;
        this.sizeLimit = i;
        return i2;
    }

    public boolean skipField(int i) {
        switch (WireFormat.getTagWireType(i)) {
            case 0:
                skipRawVarint();
                return true;
            case 1:
                skipRawBytes(8);
                return true;
            case 2:
                skipRawBytes(readRawVarint32());
                return true;
            case 3:
                skipMessage();
                checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                skipRawBytes(4);
                return true;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    public boolean skipField(int i, CodedOutputStream codedOutputStream) {
        long readInt64;
        int makeTag;
        switch (WireFormat.getTagWireType(i)) {
            case 0:
                readInt64 = readInt64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeUInt64NoTag(readInt64);
                return true;
            case 1:
                readInt64 = readRawLittleEndian64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed64NoTag(readInt64);
                return true;
            case 2:
                ByteString readBytes = readBytes();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeBytesNoTag(readBytes);
                return true;
            case 3:
                codedOutputStream.writeRawVarint32(i);
                skipMessage(codedOutputStream);
                makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4);
                checkLastTagWas(makeTag);
                codedOutputStream.writeRawVarint32(makeTag);
                return true;
            case 4:
                return false;
            case 5:
                makeTag = readRawLittleEndian32();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed32NoTag(makeTag);
                return true;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    public void skipMessage() {
        int readTag;
        do {
            readTag = readTag();
            if (readTag == 0) {
                return;
            }
        } while (skipField(readTag));
    }

    public void skipMessage(CodedOutputStream codedOutputStream) {
        int readTag;
        do {
            readTag = readTag();
            if (readTag == 0) {
                return;
            }
        } while (skipField(readTag, codedOutputStream));
    }

    public void skipRawBytes(int i) {
        if (i > this.bufferSize - this.bufferPos || i < 0) {
            skipRawBytesSlowPath(i);
        } else {
            this.bufferPos += i;
        }
    }
}
