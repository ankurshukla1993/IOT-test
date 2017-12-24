package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import com.google.common.collect.TreeMultiset.AvlNode;
import java.util.Iterator;
import java.util.NoSuchElementException;

class TreeMultiset$3 implements Iterator<Entry<E>> {
    AvlNode<E> current = TreeMultiset.access$1600(this.this$0);
    Entry<E> prevEntry = null;
    final /* synthetic */ TreeMultiset this$0;

    TreeMultiset$3(TreeMultiset treeMultiset) {
        this.this$0 = treeMultiset;
    }

    public boolean hasNext() {
        if (this.current == null) {
            return false;
        }
        if (!TreeMultiset.access$1300(this.this$0).tooLow(this.current.getElement())) {
            return true;
        }
        this.current = null;
        return false;
    }

    public Entry<E> next() {
        if (hasNext()) {
            Entry<E> result = TreeMultiset.access$1400(this.this$0, this.current);
            this.prevEntry = result;
            if (AvlNode.access$1100(this.current) == TreeMultiset.access$1500(this.this$0)) {
                this.current = null;
            } else {
                this.current = AvlNode.access$1100(this.current);
            }
            return result;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        boolean z;
        if (this.prevEntry != null) {
            z = true;
        } else {
            z = false;
        }
        CollectPreconditions.checkRemove(z);
        this.this$0.setCount(this.prevEntry.getElement(), 0);
        this.prevEntry = null;
    }
}
