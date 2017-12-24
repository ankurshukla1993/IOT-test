package com.google.common.collect;

import com.google.common.collect.ArrayTable.ArrayMap;

class ArrayTable$Row extends ArrayMap<C, V> {
    final int rowIndex;
    final /* synthetic */ ArrayTable this$0;

    ArrayTable$Row(ArrayTable arrayTable, int rowIndex) {
        this.this$0 = arrayTable;
        super(ArrayTable.access$500(arrayTable), null);
        this.rowIndex = rowIndex;
    }

    String getKeyRole() {
        return "Column";
    }

    V getValue(int index) {
        return this.this$0.at(this.rowIndex, index);
    }

    V setValue(int index, V newValue) {
        return this.this$0.set(this.rowIndex, index, newValue);
    }
}
