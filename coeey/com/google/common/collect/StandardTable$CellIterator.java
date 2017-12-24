package com.google.common.collect;

import com.google.common.collect.Table.Cell;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

class StandardTable$CellIterator implements Iterator<Cell<R, C, V>> {
    Iterator<Entry<C, V>> columnIterator;
    Entry<R, Map<C, V>> rowEntry;
    final Iterator<Entry<R, Map<C, V>>> rowIterator;
    final /* synthetic */ StandardTable this$0;

    private StandardTable$CellIterator(StandardTable standardTable) {
        this.this$0 = standardTable;
        this.rowIterator = this.this$0.backingMap.entrySet().iterator();
        this.columnIterator = Iterators.emptyModifiableIterator();
    }

    public boolean hasNext() {
        return this.rowIterator.hasNext() || this.columnIterator.hasNext();
    }

    public Cell<R, C, V> next() {
        if (!this.columnIterator.hasNext()) {
            this.rowEntry = (Entry) this.rowIterator.next();
            this.columnIterator = ((Map) this.rowEntry.getValue()).entrySet().iterator();
        }
        Entry<C, V> columnEntry = (Entry) this.columnIterator.next();
        return Tables.immutableCell(this.rowEntry.getKey(), columnEntry.getKey(), columnEntry.getValue());
    }

    public void remove() {
        this.columnIterator.remove();
        if (((Map) this.rowEntry.getValue()).isEmpty()) {
            this.rowIterator.remove();
        }
    }
}
