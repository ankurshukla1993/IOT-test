package com.google.common.collect;

import com.google.common.collect.TreeMultiset.AvlNode;

class TreeMultiset$1 extends Multisets$AbstractEntry<E> {
    final /* synthetic */ TreeMultiset this$0;
    final /* synthetic */ AvlNode val$baseEntry;

    TreeMultiset$1(TreeMultiset treeMultiset, AvlNode avlNode) {
        this.this$0 = treeMultiset;
        this.val$baseEntry = avlNode;
    }

    public E getElement() {
        return this.val$baseEntry.getElement();
    }

    public int getCount() {
        int result = this.val$baseEntry.getCount();
        if (result == 0) {
            return this.this$0.count(getElement());
        }
        return result;
    }
}
