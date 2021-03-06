package com.google.protobuf;

import com.google.protobuf.ByteString.ByteIterator;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

class RopeByteString extends ByteString {
    private static final int[] minLengthByDepth;
    private int hash;
    private final ByteString left;
    private final int leftLength;
    private final ByteString right;
    private final int totalLength;
    private final int treeDepth;

    class Balancer {
        private final Stack prefixesStack;

        private Balancer() {
            this.prefixesStack = new Stack();
        }

        private ByteString balance(ByteString byteString, ByteString byteString2) {
            doBalance(byteString);
            doBalance(byteString2);
            ByteString byteString3 = (ByteString) this.prefixesStack.pop();
            while (!this.prefixesStack.isEmpty()) {
                byteString3 = new RopeByteString((ByteString) this.prefixesStack.pop(), byteString3);
            }
            return byteString3;
        }

        private void doBalance(ByteString byteString) {
            if (byteString.isBalanced()) {
                insert(byteString);
            } else if (byteString instanceof RopeByteString) {
                RopeByteString ropeByteString = (RopeByteString) byteString;
                doBalance(ropeByteString.left);
                doBalance(ropeByteString.right);
            } else {
                throw new IllegalArgumentException("Has a new type of ByteString been created? Found " + byteString.getClass());
            }
        }

        private int getDepthBinForLength(int i) {
            int binarySearch = Arrays.binarySearch(RopeByteString.minLengthByDepth, i);
            return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
        }

        private void insert(ByteString byteString) {
            int depthBinForLength = getDepthBinForLength(byteString.size());
            int i = RopeByteString.minLengthByDepth[depthBinForLength + 1];
            if (this.prefixesStack.isEmpty() || ((ByteString) this.prefixesStack.peek()).size() >= i) {
                this.prefixesStack.push(byteString);
                return;
            }
            int i2 = RopeByteString.minLengthByDepth[depthBinForLength];
            ByteString byteString2 = (ByteString) this.prefixesStack.pop();
            while (!this.prefixesStack.isEmpty() && ((ByteString) this.prefixesStack.peek()).size() < i2) {
                byteString2 = new RopeByteString((ByteString) this.prefixesStack.pop(), byteString2);
            }
            byteString2 = new RopeByteString(byteString2, byteString);
            while (!this.prefixesStack.isEmpty()) {
                if (((ByteString) this.prefixesStack.peek()).size() >= RopeByteString.minLengthByDepth[getDepthBinForLength(byteString2.size()) + 1]) {
                    break;
                }
                byteString2 = new RopeByteString((ByteString) this.prefixesStack.pop(), byteString2);
            }
            this.prefixesStack.push(byteString2);
        }
    }

    class PieceIterator implements Iterator {
        private final Stack breadCrumbs;
        private LiteralByteString next;

        private PieceIterator(ByteString byteString) {
            this.breadCrumbs = new Stack();
            this.next = getLeafByLeft(byteString);
        }

        private LiteralByteString getLeafByLeft(ByteString byteString) {
            ByteString byteString2 = byteString;
            while (byteString2 instanceof RopeByteString) {
                RopeByteString ropeByteString = (RopeByteString) byteString2;
                this.breadCrumbs.push(ropeByteString);
                byteString2 = ropeByteString.left;
            }
            return (LiteralByteString) byteString2;
        }

        private LiteralByteString getNextNonEmptyLeaf() {
            while (!this.breadCrumbs.isEmpty()) {
                LiteralByteString leafByLeft = getLeafByLeft(((RopeByteString) this.breadCrumbs.pop()).right);
                if (!leafByLeft.isEmpty()) {
                    return leafByLeft;
                }
            }
            return null;
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public LiteralByteString next() {
            if (this.next == null) {
                throw new NoSuchElementException();
            }
            LiteralByteString literalByteString = this.next;
            this.next = getNextNonEmptyLeaf();
            return literalByteString;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    class RopeByteIterator implements ByteIterator {
        private ByteIterator bytes;
        int bytesRemaining;
        private final PieceIterator pieces;

        private RopeByteIterator() {
            this.pieces = new PieceIterator(RopeByteString.this);
            this.bytes = this.pieces.next().iterator();
            this.bytesRemaining = RopeByteString.this.size();
        }

        public boolean hasNext() {
            return this.bytesRemaining > 0;
        }

        public Byte next() {
            return Byte.valueOf(nextByte());
        }

        public byte nextByte() {
            if (!this.bytes.hasNext()) {
                this.bytes = this.pieces.next().iterator();
            }
            this.bytesRemaining--;
            return this.bytes.nextByte();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    class RopeInputStream extends InputStream {
        private LiteralByteString currentPiece;
        private int currentPieceIndex;
        private int currentPieceOffsetInRope;
        private int currentPieceSize;
        private int mark;
        private PieceIterator pieceIterator;

        public RopeInputStream() {
            initialize();
        }

        private void advanceIfCurrentPieceFullyRead() {
            if (this.currentPiece != null && this.currentPieceIndex == this.currentPieceSize) {
                this.currentPieceOffsetInRope += this.currentPieceSize;
                this.currentPieceIndex = 0;
                if (this.pieceIterator.hasNext()) {
                    this.currentPiece = this.pieceIterator.next();
                    this.currentPieceSize = this.currentPiece.size();
                    return;
                }
                this.currentPiece = null;
                this.currentPieceSize = 0;
            }
        }

        private void initialize() {
            this.pieceIterator = new PieceIterator(RopeByteString.this);
            this.currentPiece = this.pieceIterator.next();
            this.currentPieceSize = this.currentPiece.size();
            this.currentPieceIndex = 0;
            this.currentPieceOffsetInRope = 0;
        }

        private int readSkipInternal(byte[] bArr, int i, int i2) {
            int i3 = i;
            int i4 = i2;
            while (i4 > 0) {
                advanceIfCurrentPieceFullyRead();
                if (this.currentPiece == null) {
                    if (i4 == i2) {
                        return -1;
                    }
                    return i2 - i4;
                }
                int min = Math.min(this.currentPieceSize - this.currentPieceIndex, i4);
                if (bArr != null) {
                    this.currentPiece.copyTo(bArr, this.currentPieceIndex, i3, min);
                    i3 += min;
                }
                this.currentPieceIndex += min;
                i4 -= min;
            }
            return i2 - i4;
        }

        public int available() {
            return RopeByteString.this.size() - (this.currentPieceOffsetInRope + this.currentPieceIndex);
        }

        public void mark(int i) {
            this.mark = this.currentPieceOffsetInRope + this.currentPieceIndex;
        }

        public boolean markSupported() {
            return true;
        }

        public int read() {
            advanceIfCurrentPieceFullyRead();
            if (this.currentPiece == null) {
                return -1;
            }
            LiteralByteString literalByteString = this.currentPiece;
            int i = this.currentPieceIndex;
            this.currentPieceIndex = i + 1;
            return literalByteString.byteAt(i) & 255;
        }

        public int read(byte[] bArr, int i, int i2) {
            if (bArr == null) {
                throw new NullPointerException();
            } else if (i >= 0 && i2 >= 0 && i2 <= bArr.length - i) {
                return readSkipInternal(bArr, i, i2);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        public void reset() {
            synchronized (this) {
                initialize();
                readSkipInternal(null, 0, this.mark);
            }
        }

        public long skip(long j) {
            if (j < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (j > 2147483647L) {
                j = 2147483647L;
            }
            return (long) readSkipInternal(null, 0, (int) j);
        }
    }

    static {
        int i = 1;
        List arrayList = new ArrayList();
        int i2 = 1;
        while (i2 > 0) {
            arrayList.add(Integer.valueOf(i2));
            int i3 = i2;
            i2 = i + i2;
            i = i3;
        }
        arrayList.add(Integer.valueOf(Integer.MAX_VALUE));
        minLengthByDepth = new int[arrayList.size()];
        for (i2 = 0; i2 < minLengthByDepth.length; i2++) {
            minLengthByDepth[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
    }

    private RopeByteString(ByteString byteString, ByteString byteString2) {
        this.hash = 0;
        this.left = byteString;
        this.right = byteString2;
        this.leftLength = byteString.size();
        this.totalLength = this.leftLength + byteString2.size();
        this.treeDepth = Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1;
    }

    static ByteString concatenate(ByteString byteString, ByteString byteString2) {
        RopeByteString ropeByteString = byteString instanceof RopeByteString ? (RopeByteString) byteString : null;
        if (byteString2.size() == 0) {
            return byteString;
        }
        if (byteString.size() == 0) {
            return byteString2;
        }
        int size = byteString.size() + byteString2.size();
        if (size < 128) {
            return concatenateBytes(byteString, byteString2);
        }
        if (ropeByteString != null && ropeByteString.right.size() + byteString2.size() < 128) {
            return new RopeByteString(ropeByteString.left, concatenateBytes(ropeByteString.right, byteString2));
        } else if (ropeByteString == null || ropeByteString.left.getTreeDepth() <= ropeByteString.right.getTreeDepth() || ropeByteString.getTreeDepth() <= byteString2.getTreeDepth()) {
            return size >= minLengthByDepth[Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1] ? new RopeByteString(byteString, byteString2) : new Balancer().balance(byteString, byteString2);
        } else {
            return new RopeByteString(ropeByteString.left, new RopeByteString(ropeByteString.right, byteString2));
        }
    }

    private static LiteralByteString concatenateBytes(ByteString byteString, ByteString byteString2) {
        int size = byteString.size();
        int size2 = byteString2.size();
        byte[] bArr = new byte[(size + size2)];
        byteString.copyTo(bArr, 0, 0, size);
        byteString2.copyTo(bArr, 0, size, size2);
        return new LiteralByteString(bArr);
    }

    private boolean equalsFragments(ByteString byteString) {
        Iterator pieceIterator = new PieceIterator(this);
        LiteralByteString literalByteString = (LiteralByteString) pieceIterator.next();
        Iterator pieceIterator2 = new PieceIterator(byteString);
        int i = 0;
        LiteralByteString literalByteString2 = literalByteString;
        int i2 = 0;
        LiteralByteString literalByteString3 = (LiteralByteString) pieceIterator2.next();
        int i3 = 0;
        while (true) {
            int size = literalByteString2.size() - i;
            int size2 = literalByteString3.size() - i2;
            int min = Math.min(size, size2);
            if (!(i == 0 ? literalByteString2.equalsRange(literalByteString3, i2, min) : literalByteString3.equalsRange(literalByteString2, i, min))) {
                return false;
            }
            int i4 = i3 + min;
            if (i4 >= this.totalLength) {
                break;
            }
            LiteralByteString literalByteString4;
            if (min == size) {
                i = 0;
                literalByteString2 = (LiteralByteString) pieceIterator.next();
            } else {
                i += min;
            }
            if (min == size2) {
                literalByteString4 = (LiteralByteString) pieceIterator2.next();
                i3 = 0;
            } else {
                i3 = i2 + min;
                literalByteString4 = literalByteString3;
            }
            literalByteString3 = literalByteString4;
            i2 = i3;
            i3 = i4;
        }
        if (i4 == this.totalLength) {
            return true;
        }
        throw new IllegalStateException();
    }

    static RopeByteString newInstanceForTest(ByteString byteString, ByteString byteString2) {
        return new RopeByteString(byteString, byteString2);
    }

    public ByteBuffer asReadOnlyByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    public List asReadOnlyByteBufferList() {
        List arrayList = new ArrayList();
        PieceIterator pieceIterator = new PieceIterator(this);
        while (pieceIterator.hasNext()) {
            arrayList.add(pieceIterator.next().asReadOnlyByteBuffer());
        }
        return arrayList;
    }

    public byte byteAt(int i) {
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + i);
        } else if (i <= this.totalLength) {
            return i < this.leftLength ? this.left.byteAt(i) : this.right.byteAt(i - this.leftLength);
        } else {
            throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + this.totalLength);
        }
    }

    public void copyTo(ByteBuffer byteBuffer) {
        this.left.copyTo(byteBuffer);
        this.right.copyTo(byteBuffer);
    }

    protected void copyToInternal(byte[] bArr, int i, int i2, int i3) {
        if (i + i3 <= this.leftLength) {
            this.left.copyToInternal(bArr, i, i2, i3);
        } else if (i >= this.leftLength) {
            this.right.copyToInternal(bArr, i - this.leftLength, i2, i3);
        } else {
            int i4 = this.leftLength - i;
            this.left.copyToInternal(bArr, i, i2, i4);
            this.right.copyToInternal(bArr, 0, i2 + i4, i3 - i4);
        }
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof ByteString)) {
                return false;
            }
            ByteString byteString = (ByteString) obj;
            if (this.totalLength != byteString.size()) {
                return false;
            }
            if (this.totalLength != 0) {
                if (this.hash != 0) {
                    int peekCachedHashCode = byteString.peekCachedHashCode();
                    if (!(peekCachedHashCode == 0 || this.hash == peekCachedHashCode)) {
                        return false;
                    }
                }
                return equalsFragments(byteString);
            }
        }
        return true;
    }

    protected int getTreeDepth() {
        return this.treeDepth;
    }

    public int hashCode() {
        int i = this.hash;
        if (i == 0) {
            i = partialHash(this.totalLength, 0, this.totalLength);
            if (i == 0) {
                i = 1;
            }
            this.hash = i;
        }
        return i;
    }

    protected boolean isBalanced() {
        return this.totalLength >= minLengthByDepth[this.treeDepth];
    }

    public boolean isValidUtf8() {
        return this.right.partialIsValidUtf8(this.left.partialIsValidUtf8(0, 0, this.leftLength), 0, this.right.size()) == 0;
    }

    public ByteIterator iterator() {
        return new RopeByteIterator();
    }

    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance(new RopeInputStream());
    }

    public InputStream newInput() {
        return new RopeInputStream();
    }

    protected int partialHash(int i, int i2, int i3) {
        if (i2 + i3 <= this.leftLength) {
            return this.left.partialHash(i, i2, i3);
        }
        if (i2 >= this.leftLength) {
            return this.right.partialHash(i, i2 - this.leftLength, i3);
        }
        int i4 = this.leftLength - i2;
        return this.right.partialHash(this.left.partialHash(i, i2, i4), 0, i3 - i4);
    }

    protected int partialIsValidUtf8(int i, int i2, int i3) {
        if (i2 + i3 <= this.leftLength) {
            return this.left.partialIsValidUtf8(i, i2, i3);
        }
        if (i2 >= this.leftLength) {
            return this.right.partialIsValidUtf8(i, i2 - this.leftLength, i3);
        }
        int i4 = this.leftLength - i2;
        return this.right.partialIsValidUtf8(this.left.partialIsValidUtf8(i, i2, i4), 0, i3 - i4);
    }

    protected int peekCachedHashCode() {
        return this.hash;
    }

    public int size() {
        return this.totalLength;
    }

    public ByteString substring(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i + " < 0");
        } else if (i2 > this.totalLength) {
            throw new IndexOutOfBoundsException("End index: " + i2 + " > " + this.totalLength);
        } else {
            int i3 = i2 - i;
            if (i3 >= 0) {
                return i3 == 0 ? ByteString.EMPTY : i3 != this.totalLength ? i2 <= this.leftLength ? this.left.substring(i, i2) : i >= this.leftLength ? this.right.substring(i - this.leftLength, i2 - this.leftLength) : new RopeByteString(this.left.substring(i), this.right.substring(0, i2 - this.leftLength)) : this;
            } else {
                throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i + ", " + i2);
            }
        }
    }

    public String toString(String str) {
        return new String(toByteArray(), str);
    }

    public void writeTo(OutputStream outputStream) {
        this.left.writeTo(outputStream);
        this.right.writeTo(outputStream);
    }

    void writeToInternal(OutputStream outputStream, int i, int i2) {
        if (i + i2 <= this.leftLength) {
            this.left.writeToInternal(outputStream, i, i2);
        } else if (i >= this.leftLength) {
            this.right.writeToInternal(outputStream, i - this.leftLength, i2);
        } else {
            int i3 = this.leftLength - i;
            this.left.writeToInternal(outputStream, i, i3);
            this.right.writeToInternal(outputStream, 0, i2 - i3);
        }
    }
}
