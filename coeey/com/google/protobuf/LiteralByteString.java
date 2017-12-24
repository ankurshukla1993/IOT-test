package com.google.protobuf;

import com.google.protobuf.ByteString.ByteIterator;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class LiteralByteString extends ByteString {
    protected final byte[] bytes;
    private int hash = 0;

    class LiteralByteIterator implements ByteIterator {
        private final int limit;
        private int position;

        private LiteralByteIterator() {
            this.position = 0;
            this.limit = LiteralByteString.this.size();
        }

        public boolean hasNext() {
            return this.position < this.limit;
        }

        public Byte next() {
            return Byte.valueOf(nextByte());
        }

        public byte nextByte() {
            try {
                byte[] bArr = LiteralByteString.this.bytes;
                int i = this.position;
                this.position = i + 1;
                return bArr[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchElementException(e.getMessage());
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    LiteralByteString(byte[] bArr) {
        this.bytes = bArr;
    }

    static int hashCode(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    static int hashCode(byte[] bArr) {
        int hashCode = hashCode(bArr.length, bArr, 0, bArr.length);
        return hashCode == 0 ? 1 : hashCode;
    }

    public ByteBuffer asReadOnlyByteBuffer() {
        return ByteBuffer.wrap(this.bytes, getOffsetIntoBytes(), size()).asReadOnlyBuffer();
    }

    public List asReadOnlyByteBufferList() {
        List arrayList = new ArrayList(1);
        arrayList.add(asReadOnlyByteBuffer());
        return arrayList;
    }

    public byte byteAt(int i) {
        return this.bytes[i];
    }

    public void copyTo(ByteBuffer byteBuffer) {
        byteBuffer.put(this.bytes, getOffsetIntoBytes(), size());
    }

    protected void copyToInternal(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.bytes, i, bArr, i2, i3);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString)) {
            return false;
        }
        if (size() != ((ByteString) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (obj instanceof LiteralByteString) {
            return equalsRange((LiteralByteString) obj, 0, size());
        }
        if (obj instanceof RopeByteString) {
            return obj.equals(this);
        }
        throw new IllegalArgumentException("Has a new type of ByteString been created? Found " + obj.getClass());
    }

    boolean equalsRange(LiteralByteString literalByteString, int i, int i2) {
        if (i2 > literalByteString.size()) {
            throw new IllegalArgumentException("Length too large: " + i2 + size());
        } else if (i + i2 > literalByteString.size()) {
            throw new IllegalArgumentException("Ran off end of other: " + i + ", " + i2 + ", " + literalByteString.size());
        } else {
            byte[] bArr = this.bytes;
            byte[] bArr2 = literalByteString.bytes;
            int offsetIntoBytes = i2 + getOffsetIntoBytes();
            int offsetIntoBytes2 = getOffsetIntoBytes();
            int offsetIntoBytes3 = literalByteString.getOffsetIntoBytes() + i;
            while (offsetIntoBytes2 < offsetIntoBytes) {
                if (bArr[offsetIntoBytes2] != bArr2[offsetIntoBytes3]) {
                    return false;
                }
                offsetIntoBytes2++;
                offsetIntoBytes3++;
            }
            return true;
        }
    }

    protected int getOffsetIntoBytes() {
        return 0;
    }

    protected int getTreeDepth() {
        return 0;
    }

    public int hashCode() {
        int i = this.hash;
        if (i == 0) {
            i = size();
            i = partialHash(i, 0, i);
            if (i == 0) {
                i = 1;
            }
            this.hash = i;
        }
        return i;
    }

    protected boolean isBalanced() {
        return true;
    }

    public boolean isValidUtf8() {
        int offsetIntoBytes = getOffsetIntoBytes();
        return Utf8.isValidUtf8(this.bytes, offsetIntoBytes, size() + offsetIntoBytes);
    }

    public ByteIterator iterator() {
        return new LiteralByteIterator();
    }

    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance(this);
    }

    public InputStream newInput() {
        return new ByteArrayInputStream(this.bytes, getOffsetIntoBytes(), size());
    }

    protected int partialHash(int i, int i2, int i3) {
        return hashCode(i, this.bytes, getOffsetIntoBytes() + i2, i3);
    }

    protected int partialIsValidUtf8(int i, int i2, int i3) {
        int offsetIntoBytes = getOffsetIntoBytes() + i2;
        return Utf8.partialIsValidUtf8(i, this.bytes, offsetIntoBytes, offsetIntoBytes + i3);
    }

    protected int peekCachedHashCode() {
        return this.hash;
    }

    public int size() {
        return this.bytes.length;
    }

    public ByteString substring(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i + " < 0");
        } else if (i2 > size()) {
            throw new IndexOutOfBoundsException("End index: " + i2 + " > " + size());
        } else {
            int i3 = i2 - i;
            if (i3 >= 0) {
                return i3 == 0 ? ByteString.EMPTY : new BoundedByteString(this.bytes, getOffsetIntoBytes() + i, i3);
            } else {
                throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i + ", " + i2);
            }
        }
    }

    public String toString(String str) {
        return new String(this.bytes, getOffsetIntoBytes(), size(), str);
    }

    public void writeTo(OutputStream outputStream) {
        outputStream.write(toByteArray());
    }

    void writeToInternal(OutputStream outputStream, int i, int i2) {
        outputStream.write(this.bytes, getOffsetIntoBytes() + i, i2);
    }
}
