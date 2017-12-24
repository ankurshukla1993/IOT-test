package com.google.common.collect;

import com.google.common.collect.ArrayTable.ArrayMap;
import java.util.Map;

class ArrayTable$RowMap extends ArrayMap<R, Map<C, V>> {
    final /* synthetic */ ArrayTable this$0;

    private ArrayTable$RowMap(ArrayTable arrayTable) {
        this.this$0 = arrayTable;
        super(ArrayTable.access$200(arrayTable), null);
    }

    String getKeyRole() {
        return "Row";
    }

    Map<C, V> getValue(int index) {
        return new ArrayTable$Row(this.this$0, index);
    }

    Map<C, V> setValue(int index, Map<C, V> map) {
        throw new UnsupportedOperationException();
    }

    public Map<C, V> put(R r, Map<C, V> map) {
        throw new UnsupportedOperationException();
    }
}
