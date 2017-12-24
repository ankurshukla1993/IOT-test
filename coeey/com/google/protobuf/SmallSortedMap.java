package com.google.protobuf;

import com.google.protobuf.FieldSet.FieldDescriptorLite;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class SmallSortedMap extends AbstractMap {
    private List entryList;
    private boolean isImmutable;
    private volatile EntrySet lazyEntrySet;
    private final int maxArraySize;
    private Map overflowEntries;

    final class C20041 extends SmallSortedMap {
        C20041(int i) {
            super(i);
        }

        public void makeImmutable() {
            if (!isImmutable()) {
                for (int i = 0; i < getNumArrayEntries(); i++) {
                    java.util.Map.Entry arrayEntryAt = getArrayEntryAt(i);
                    if (((FieldDescriptorLite) arrayEntryAt.getKey()).isRepeated()) {
                        arrayEntryAt.setValue(Collections.unmodifiableList((List) arrayEntryAt.getValue()));
                    }
                }
                for (java.util.Map.Entry entry : getOverflowEntries()) {
                    if (((FieldDescriptorLite) entry.getKey()).isRepeated()) {
                        entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                    }
                }
            }
            super.makeImmutable();
        }

        public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
            return super.put((FieldDescriptorLite) obj, obj2);
        }
    }

    class EmptySet {
        private static final Iterable ITERABLE = new C20062();
        private static final Iterator ITERATOR = new C20051();

        final class C20051 implements Iterator {
            C20051() {
            }

            public boolean hasNext() {
                return false;
            }

            public Object next() {
                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        final class C20062 implements Iterable {
            C20062() {
            }

            public Iterator iterator() {
                return EmptySet.ITERATOR;
            }
        }

        private EmptySet() {
        }

        static Iterable iterable() {
            return ITERABLE;
        }
    }

    class Entry implements Comparable, java.util.Map.Entry {
        private final Comparable key;
        private Object value;

        Entry(Comparable comparable, Object obj) {
            this.key = comparable;
            this.value = obj;
        }

        Entry(SmallSortedMap smallSortedMap, java.util.Map.Entry entry) {
            this((Comparable) entry.getKey(), entry.getValue());
        }

        private boolean equals(Object obj, Object obj2) {
            return obj == null ? obj2 == null : obj.equals(obj2);
        }

        public int compareTo(Entry entry) {
            return getKey().compareTo(entry.getKey());
        }

        public boolean equals(Object obj) {
            if (obj != this) {
                if (!(obj instanceof java.util.Map.Entry)) {
                    return false;
                }
                java.util.Map.Entry entry = (java.util.Map.Entry) obj;
                if (!equals(this.key, entry.getKey())) {
                    return false;
                }
                if (!equals(this.value, entry.getValue())) {
                    return false;
                }
            }
            return true;
        }

        public Comparable getKey() {
            return this.key;
        }

        public Object getValue() {
            return this.value;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.key == null ? 0 : this.key.hashCode();
            if (this.value != null) {
                i = this.value.hashCode();
            }
            return hashCode ^ i;
        }

        public Object setValue(Object obj) {
            SmallSortedMap.this.checkMutable();
            Object obj2 = this.value;
            this.value = obj;
            return obj2;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    class EntryIterator implements Iterator {
        private Iterator lazyOverflowIterator;
        private boolean nextCalledBeforeRemove;
        private int pos;

        private EntryIterator() {
            this.pos = -1;
        }

        private Iterator getOverflowIterator() {
            if (this.lazyOverflowIterator == null) {
                this.lazyOverflowIterator = SmallSortedMap.this.overflowEntries.entrySet().iterator();
            }
            return this.lazyOverflowIterator;
        }

        public boolean hasNext() {
            return this.pos + 1 < SmallSortedMap.this.entryList.size() || getOverflowIterator().hasNext();
        }

        public java.util.Map.Entry next() {
            this.nextCalledBeforeRemove = true;
            int i = this.pos + 1;
            this.pos = i;
            return i < SmallSortedMap.this.entryList.size() ? (java.util.Map.Entry) SmallSortedMap.this.entryList.get(this.pos) : (java.util.Map.Entry) getOverflowIterator().next();
        }

        public void remove() {
            if (this.nextCalledBeforeRemove) {
                this.nextCalledBeforeRemove = false;
                SmallSortedMap.this.checkMutable();
                if (this.pos < SmallSortedMap.this.entryList.size()) {
                    SmallSortedMap smallSortedMap = SmallSortedMap.this;
                    int i = this.pos;
                    this.pos = i - 1;
                    smallSortedMap.removeArrayEntryAt(i);
                    return;
                }
                getOverflowIterator().remove();
                return;
            }
            throw new IllegalStateException("remove() was called before next()");
        }
    }

    class EntrySet extends AbstractSet {
        private EntrySet() {
        }

        public boolean add(java.util.Map.Entry entry) {
            if (contains(entry)) {
                return false;
            }
            SmallSortedMap.this.put((Comparable) entry.getKey(), entry.getValue());
            return true;
        }

        public void clear() {
            SmallSortedMap.this.clear();
        }

        public boolean contains(Object obj) {
            java.util.Map.Entry entry = (java.util.Map.Entry) obj;
            Object obj2 = SmallSortedMap.this.get(entry.getKey());
            Object value = entry.getValue();
            return obj2 == value || (obj2 != null && obj2.equals(value));
        }

        public Iterator iterator() {
            return new EntryIterator();
        }

        public boolean remove(Object obj) {
            java.util.Map.Entry entry = (java.util.Map.Entry) obj;
            if (!contains(entry)) {
                return false;
            }
            SmallSortedMap.this.remove(entry.getKey());
            return true;
        }

        public int size() {
            return SmallSortedMap.this.size();
        }
    }

    private SmallSortedMap(int i) {
        this.maxArraySize = i;
        this.entryList = Collections.emptyList();
        this.overflowEntries = Collections.emptyMap();
    }

    private int binarySearchInArray(Comparable comparable) {
        int compareTo;
        int i = 0;
        int size = this.entryList.size() - 1;
        if (size >= 0) {
            compareTo = comparable.compareTo(((Entry) this.entryList.get(size)).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        while (i <= size) {
            int i2 = (i + size) / 2;
            compareTo = comparable.compareTo(((Entry) this.entryList.get(i2)).getKey());
            if (compareTo < 0) {
                compareTo = i2 - 1;
                size = i;
            } else if (compareTo <= 0) {
                return i2;
            } else {
                int i3 = size;
                size = i2 + 1;
                compareTo = i3;
            }
            i = size;
            size = compareTo;
        }
        return -(i + 1);
    }

    private void checkMutable() {
        if (this.isImmutable) {
            throw new UnsupportedOperationException();
        }
    }

    private void ensureEntryArrayMutable() {
        checkMutable();
        if (this.entryList.isEmpty() && !(this.entryList instanceof ArrayList)) {
            this.entryList = new ArrayList(this.maxArraySize);
        }
    }

    private SortedMap getOverflowEntriesMutable() {
        checkMutable();
        if (this.overflowEntries.isEmpty() && !(this.overflowEntries instanceof TreeMap)) {
            this.overflowEntries = new TreeMap();
        }
        return (SortedMap) this.overflowEntries;
    }

    static SmallSortedMap newFieldMap(int i) {
        return new C20041(i);
    }

    static SmallSortedMap newInstanceForTest(int i) {
        return new SmallSortedMap(i);
    }

    private Object removeArrayEntryAt(int i) {
        checkMutable();
        Object value = ((Entry) this.entryList.remove(i)).getValue();
        if (!this.overflowEntries.isEmpty()) {
            Iterator it = getOverflowEntriesMutable().entrySet().iterator();
            this.entryList.add(new Entry(this, (java.util.Map.Entry) it.next()));
            it.remove();
        }
        return value;
    }

    public void clear() {
        checkMutable();
        if (!this.entryList.isEmpty()) {
            this.entryList.clear();
        }
        if (!this.overflowEntries.isEmpty()) {
            this.overflowEntries.clear();
        }
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return binarySearchInArray(comparable) >= 0 || this.overflowEntries.containsKey(comparable);
    }

    public Set entrySet() {
        if (this.lazyEntrySet == null) {
            this.lazyEntrySet = new EntrySet();
        }
        return this.lazyEntrySet;
    }

    public Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int binarySearchInArray = binarySearchInArray(comparable);
        return binarySearchInArray >= 0 ? ((Entry) this.entryList.get(binarySearchInArray)).getValue() : this.overflowEntries.get(comparable);
    }

    public java.util.Map.Entry getArrayEntryAt(int i) {
        return (java.util.Map.Entry) this.entryList.get(i);
    }

    public int getNumArrayEntries() {
        return this.entryList.size();
    }

    public int getNumOverflowEntries() {
        return this.overflowEntries.size();
    }

    public Iterable getOverflowEntries() {
        return this.overflowEntries.isEmpty() ? EmptySet.iterable() : this.overflowEntries.entrySet();
    }

    public boolean isImmutable() {
        return this.isImmutable;
    }

    public void makeImmutable() {
        if (!this.isImmutable) {
            this.overflowEntries = this.overflowEntries.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.overflowEntries);
            this.isImmutable = true;
        }
    }

    public Object put(Comparable comparable, Object obj) {
        checkMutable();
        int binarySearchInArray = binarySearchInArray(comparable);
        if (binarySearchInArray >= 0) {
            return ((Entry) this.entryList.get(binarySearchInArray)).setValue(obj);
        }
        ensureEntryArrayMutable();
        int i = -(binarySearchInArray + 1);
        if (i >= this.maxArraySize) {
            return getOverflowEntriesMutable().put(comparable, obj);
        }
        if (this.entryList.size() == this.maxArraySize) {
            Entry entry = (Entry) this.entryList.remove(this.maxArraySize - 1);
            getOverflowEntriesMutable().put(entry.getKey(), entry.getValue());
        }
        this.entryList.add(i, new Entry(comparable, obj));
        return null;
    }

    public Object remove(Object obj) {
        checkMutable();
        Comparable comparable = (Comparable) obj;
        int binarySearchInArray = binarySearchInArray(comparable);
        return binarySearchInArray >= 0 ? removeArrayEntryAt(binarySearchInArray) : this.overflowEntries.isEmpty() ? null : this.overflowEntries.remove(comparable);
    }

    public int size() {
        return this.entryList.size() + this.overflowEntries.size();
    }
}
