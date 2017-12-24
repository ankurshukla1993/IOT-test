package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Table.Cell;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.concurrent.Immutable;

@GwtCompatible
@Immutable
final class SparseImmutableTable<R, C, V> extends RegularImmutableTable<R, C, V> {
    private final ImmutableMap<C, Map<R, V>> columnMap;
    private final int[] iterationOrderColumn;
    private final int[] iterationOrderRow;
    private final ImmutableMap<R, Map<C, V>> rowMap;

    SparseImmutableTable(ImmutableList<Cell<R, C, V>> cellList, ImmutableSet<R> rowSpace, ImmutableSet<C> columnSpace) {
        Map<R, Integer> rowIndex = Maps.newHashMap();
        Map<R, Map<C, V>> rows = Maps.newLinkedHashMap();
        Iterator i$ = rowSpace.iterator();
        while (i$.hasNext()) {
            R row = i$.next();
            rowIndex.put(row, Integer.valueOf(rows.size()));
            rows.put(row, new LinkedHashMap());
        }
        Map<C, Map<R, V>> columns = Maps.newLinkedHashMap();
        i$ = columnSpace.iterator();
        while (i$.hasNext()) {
            columns.put(i$.next(), new LinkedHashMap());
        }
        int[] iterationOrderRow = new int[cellList.size()];
        int[] iterationOrderColumn = new int[cellList.size()];
        for (int i = 0; i < cellList.size(); i++) {
            Cell<R, C, V> cell = (Cell) cellList.get(i);
            R rowKey = cell.getRowKey();
            C columnKey = cell.getColumnKey();
            V value = cell.getValue();
            iterationOrderRow[i] = ((Integer) rowIndex.get(rowKey)).intValue();
            Map<C, V> thisRow = (Map) rows.get(rowKey);
            iterationOrderColumn[i] = thisRow.size();
            V oldValue = thisRow.put(columnKey, value);
            if (oldValue != null) {
                String valueOf = String.valueOf(String.valueOf(rowKey));
                String valueOf2 = String.valueOf(String.valueOf(columnKey));
                String valueOf3 = String.valueOf(String.valueOf(value));
                String valueOf4 = String.valueOf(String.valueOf(oldValue));
                throw new IllegalArgumentException(new StringBuilder((((valueOf.length() + 37) + valueOf2.length()) + valueOf3.length()) + valueOf4.length()).append("Duplicate value for row=").append(valueOf).append(", column=").append(valueOf2).append(": ").append(valueOf3).append(", ").append(valueOf4).toString());
            }
            ((Map) columns.get(columnKey)).put(rowKey, value);
        }
        this.iterationOrderRow = iterationOrderRow;
        this.iterationOrderColumn = iterationOrderColumn;
        ImmutableMap$Builder<R, Map<C, V>> rowBuilder = ImmutableMap.builder();
        for (Entry<R, Map<C, V>> row2 : rows.entrySet()) {
            rowBuilder.put(row2.getKey(), ImmutableMap.copyOf((Map) row2.getValue()));
        }
        this.rowMap = rowBuilder.build();
        ImmutableMap$Builder<C, Map<R, V>> columnBuilder = ImmutableMap.builder();
        for (Entry<C, Map<R, V>> col : columns.entrySet()) {
            columnBuilder.put(col.getKey(), ImmutableMap.copyOf((Map) col.getValue()));
        }
        this.columnMap = columnBuilder.build();
    }

    public ImmutableMap<C, Map<R, V>> columnMap() {
        return this.columnMap;
    }

    public ImmutableMap<R, Map<C, V>> rowMap() {
        return this.rowMap;
    }

    public int size() {
        return this.iterationOrderRow.length;
    }

    Cell<R, C, V> getCell(int index) {
        Entry<R, Map<C, V>> rowEntry = (Entry) this.rowMap.entrySet().asList().get(this.iterationOrderRow[index]);
        ImmutableMap<C, V> row = (ImmutableMap) rowEntry.getValue();
        Entry<C, V> colEntry = (Entry) row.entrySet().asList().get(this.iterationOrderColumn[index]);
        return cellOf(rowEntry.getKey(), colEntry.getKey(), colEntry.getValue());
    }

    V getValue(int index) {
        ImmutableMap<C, V> row = (ImmutableMap) this.rowMap.values().asList().get(this.iterationOrderRow[index]);
        return row.values().asList().get(this.iterationOrderColumn[index]);
    }
}
