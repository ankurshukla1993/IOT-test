package com.google.common.collect;

import com.google.common.collect.ArrayTable.ArrayMap;

class ArrayTable$Column extends ArrayMap<R, V> {
    final int columnIndex;
    final /* synthetic */ ArrayTable this$0;

    ArrayTable$Column(ArrayTable arrayTable, int columnIndex) {
        this.this$0 = arrayTable;
        super(ArrayTable.access$200(arrayTable), null);
        this.columnIndex = columnIndex;
    }

    String getKeyRole() {
        return "Row";
    }

    V getValue(int index) {
        return this.this$0.at(index, this.columnIndex);
    }

    V setValue(int index, V newValue) {
        return this.this$0.set(index, this.columnIndex, newValue);
    }
}
