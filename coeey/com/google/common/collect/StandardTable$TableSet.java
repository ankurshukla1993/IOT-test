package com.google.common.collect;

abstract class StandardTable$TableSet<T> extends ImprovedAbstractSet<T> {
    final /* synthetic */ StandardTable this$0;

    private StandardTable$TableSet(StandardTable standardTable) {
        this.this$0 = standardTable;
    }

    public boolean isEmpty() {
        return this.this$0.backingMap.isEmpty();
    }

    public void clear() {
        this.this$0.backingMap.clear();
    }
}
