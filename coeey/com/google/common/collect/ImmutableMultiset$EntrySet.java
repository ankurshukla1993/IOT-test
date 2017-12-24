package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;

final class ImmutableMultiset$EntrySet extends ImmutableSet<Entry<E>> {
    private static final long serialVersionUID = 0;
    final /* synthetic */ ImmutableMultiset this$0;

    class C16241 extends ImmutableAsList<Entry<E>> {
        C16241() {
        }

        public Entry<E> get(int index) {
            return ImmutableMultiset$EntrySet.this.this$0.getEntry(index);
        }

        ImmutableCollection<Entry<E>> delegateCollection() {
            return ImmutableMultiset$EntrySet.this;
        }
    }

    private ImmutableMultiset$EntrySet(ImmutableMultiset immutableMultiset) {
        this.this$0 = immutableMultiset;
    }

    boolean isPartialView() {
        return this.this$0.isPartialView();
    }

    public UnmodifiableIterator<Entry<E>> iterator() {
        return asList().iterator();
    }

    ImmutableList<Entry<E>> createAsList() {
        return new C16241();
    }

    public int size() {
        return this.this$0.elementSet().size();
    }

    public boolean contains(Object o) {
        if (!(o instanceof Entry)) {
            return false;
        }
        Entry<?> entry = (Entry) o;
        if (entry.getCount() > 0 && this.this$0.count(entry.getElement()) == entry.getCount()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.this$0.hashCode();
    }

    Object writeReplace() {
        return new ImmutableMultiset$EntrySetSerializedForm(this.this$0);
    }
}
