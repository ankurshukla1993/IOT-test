package com.google.protobuf;

import java.util.Iterator;
import java.util.Map.Entry;

public class LazyField extends LazyFieldLite {
    private final MessageLite defaultInstance;

    class LazyEntry implements Entry {
        private Entry entry;

        private LazyEntry(Entry entry) {
            this.entry = entry;
        }

        public LazyField getField() {
            return (LazyField) this.entry.getValue();
        }

        public Object getKey() {
            return this.entry.getKey();
        }

        public Object getValue() {
            LazyField lazyField = (LazyField) this.entry.getValue();
            return lazyField == null ? null : lazyField.getValue();
        }

        public Object setValue(Object obj) {
            if (obj instanceof MessageLite) {
                return ((LazyField) this.entry.getValue()).setValue((MessageLite) obj);
            }
            throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
        }
    }

    class LazyIterator implements Iterator {
        private Iterator iterator;

        public LazyIterator(Iterator it) {
            this.iterator = it;
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public Entry next() {
            Entry entry = (Entry) this.iterator.next();
            return entry.getValue() instanceof LazyField ? new LazyEntry(entry) : entry;
        }

        public void remove() {
            this.iterator.remove();
        }
    }

    public LazyField(MessageLite messageLite, ExtensionRegistryLite extensionRegistryLite, ByteString byteString) {
        super(extensionRegistryLite, byteString);
        this.defaultInstance = messageLite;
    }

    public boolean containsDefaultInstance() {
        return super.containsDefaultInstance() || this.value == this.defaultInstance;
    }

    public boolean equals(Object obj) {
        return getValue().equals(obj);
    }

    public MessageLite getValue() {
        return getValue(this.defaultInstance);
    }

    public int hashCode() {
        return getValue().hashCode();
    }

    public String toString() {
        return getValue().toString();
    }
}
