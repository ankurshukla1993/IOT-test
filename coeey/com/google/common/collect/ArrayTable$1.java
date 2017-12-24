package com.google.common.collect;

import com.google.common.collect.Table.Cell;

class ArrayTable$1 extends AbstractIndexedListIterator<Cell<R, C, V>> {
    final /* synthetic */ ArrayTable this$0;

    ArrayTable$1(ArrayTable arrayTable, int x0) {
        this.this$0 = arrayTable;
        super(x0);
    }

    protected Cell<R, C, V> get(final int index) {
        return new AbstractCell<R, C, V>() {
            final int columnIndex = (index % ArrayTable.access$000(ArrayTable$1.this.this$0).size());
            final int rowIndex = (index / ArrayTable.access$000(ArrayTable$1.this.this$0).size());

            public R getRowKey() {
                return ArrayTable.access$100(ArrayTable$1.this.this$0).get(this.rowIndex);
            }

            public C getColumnKey() {
                return ArrayTable.access$000(ArrayTable$1.this.this$0).get(this.columnIndex);
            }

            public V getValue() {
                return ArrayTable$1.this.this$0.at(this.rowIndex, this.columnIndex);
            }
        };
    }
}
