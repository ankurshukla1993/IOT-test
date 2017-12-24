package com.google.protobuf;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public class UnmodifiableLazyStringList extends AbstractList implements LazyStringList, RandomAccess {
    private final LazyStringList list;

    class C20122 implements Iterator {
        Iterator iter = UnmodifiableLazyStringList.this.list.iterator();

        C20122() {
        }

        public boolean hasNext() {
            return this.iter.hasNext();
        }

        public String next() {
            return (String) this.iter.next();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public UnmodifiableLazyStringList(LazyStringList lazyStringList) {
        this.list = lazyStringList;
    }

    public void add(ByteString byteString) {
        throw new UnsupportedOperationException();
    }

    public void add(byte[] bArr) {
        throw new UnsupportedOperationException();
    }

    public boolean addAllByteArray(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public boolean addAllByteString(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public List asByteArrayList() {
        return Collections.unmodifiableList(this.list.asByteArrayList());
    }

    public List asByteStringList() {
        return Collections.unmodifiableList(this.list.asByteStringList());
    }

    public String get(int i) {
        return (String) this.list.get(i);
    }

    public byte[] getByteArray(int i) {
        return this.list.getByteArray(i);
    }

    public ByteString getByteString(int i) {
        return this.list.getByteString(i);
    }

    public List getUnderlyingElements() {
        return this.list.getUnderlyingElements();
    }

    public LazyStringList getUnmodifiableView() {
        return this;
    }

    public Iterator iterator() {
        return new C20122();
    }

    public ListIterator listIterator(final int i) {
        return new ListIterator() {
            ListIterator iter = UnmodifiableLazyStringList.this.list.listIterator(i);

            public void add(String str) {
                throw new UnsupportedOperationException();
            }

            public boolean hasNext() {
                return this.iter.hasNext();
            }

            public boolean hasPrevious() {
                return this.iter.hasPrevious();
            }

            public String next() {
                return (String) this.iter.next();
            }

            public int nextIndex() {
                return this.iter.nextIndex();
            }

            public String previous() {
                return (String) this.iter.previous();
            }

            public int previousIndex() {
                return this.iter.previousIndex();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public void set(String str) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public void mergeFrom(LazyStringList lazyStringList) {
        throw new UnsupportedOperationException();
    }

    public void set(int i, ByteString byteString) {
        throw new UnsupportedOperationException();
    }

    public void set(int i, byte[] bArr) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.list.size();
    }
}
