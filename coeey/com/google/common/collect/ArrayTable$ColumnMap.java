package com.google.common.collect;

import com.google.common.collect.ArrayTable.ArrayMap;
import java.util.Map;

class ArrayTable$ColumnMap extends ArrayMap<C, Map<R, V>> {
    final /* synthetic */ ArrayTable this$0;

    private ArrayTable$ColumnMap(ArrayTable arrayTable) {
        this.this$0 = arrayTable;
        super(ArrayTable.access$500(arrayTable), null);
    }

    String getKeyRole() {
        return "Column";
    }

    Map<R, V> getValue(int index) {
        return new ArrayTable$Column(this.this$0, index);
    }

    Map<R, V> setValue(int index, Map<R, V> map) {
        throw new UnsupportedOperationException();
    }

    public Map<R, V> put(C c, Map<R, V> map) {
        throw new UnsupportedOperationException();
    }
}
