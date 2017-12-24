package com.google.common.collect;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

class StandardTable$ColumnKeyIterator extends AbstractIterator<C> {
    Iterator<Entry<C, V>> entryIterator;
    final Iterator<Map<C, V>> mapIterator;
    final Map<C, V> seen;
    final /* synthetic */ StandardTable this$0;

    private StandardTable$ColumnKeyIterator(StandardTable standardTable) {
        this.this$0 = standardTable;
        this.seen = (Map) this.this$0.factory.get();
        this.mapIterator = this.this$0.backingMap.values().iterator();
        this.entryIterator = Iterators.emptyIterator();
    }

    protected C computeNext() {
        while (true) {
            if (this.entryIterator.hasNext()) {
                Entry<C, V> entry = (Entry) this.entryIterator.next();
                if (!this.seen.containsKey(entry.getKey())) {
                    this.seen.put(entry.getKey(), entry.getValue());
                    return entry.getKey();
                }
            } else if (!this.mapIterator.hasNext()) {
                return endOfData();
            } else {
                this.entryIterator = ((Map) this.mapIterator.next()).entrySet().iterator();
            }
        }
    }
}
