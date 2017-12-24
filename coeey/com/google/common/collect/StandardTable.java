package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Table.Cell;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
class StandardTable<R, C, V> extends AbstractTable<R, C, V> implements Serializable {
    private static final long serialVersionUID = 0;
    @GwtTransient
    final Map<R, Map<C, V>> backingMap;
    private transient Set<C> columnKeySet;
    private transient ColumnMap columnMap;
    @GwtTransient
    final Supplier<? extends Map<C, V>> factory;
    private transient Map<R, Map<C, V>> rowMap;

    StandardTable(Map<R, Map<C, V>> backingMap, Supplier<? extends Map<C, V>> factory) {
        this.backingMap = backingMap;
        this.factory = factory;
    }

    public boolean contains(@Nullable Object rowKey, @Nullable Object columnKey) {
        return (rowKey == null || columnKey == null || !super.contains(rowKey, columnKey)) ? false : true;
    }

    public boolean containsColumn(@Nullable Object columnKey) {
        if (columnKey == null) {
            return false;
        }
        for (Map<C, V> map : this.backingMap.values()) {
            if (Maps.safeContainsKey(map, columnKey)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsRow(@Nullable Object rowKey) {
        return rowKey != null && Maps.safeContainsKey(this.backingMap, rowKey);
    }

    public boolean containsValue(@Nullable Object value) {
        return value != null && super.containsValue(value);
    }

    public V get(@Nullable Object rowKey, @Nullable Object columnKey) {
        return (rowKey == null || columnKey == null) ? null : super.get(rowKey, columnKey);
    }

    public boolean isEmpty() {
        return this.backingMap.isEmpty();
    }

    public int size() {
        int size = 0;
        for (Map<C, V> map : this.backingMap.values()) {
            size += map.size();
        }
        return size;
    }

    public void clear() {
        this.backingMap.clear();
    }

    private Map<C, V> getOrCreate(R rowKey) {
        Map<C, V> map = (Map) this.backingMap.get(rowKey);
        if (map != null) {
            return map;
        }
        map = (Map) this.factory.get();
        this.backingMap.put(rowKey, map);
        return map;
    }

    public V put(R rowKey, C columnKey, V value) {
        Preconditions.checkNotNull(rowKey);
        Preconditions.checkNotNull(columnKey);
        Preconditions.checkNotNull(value);
        return getOrCreate(rowKey).put(columnKey, value);
    }

    public V remove(@Nullable Object rowKey, @Nullable Object columnKey) {
        V v = null;
        if (!(rowKey == null || columnKey == null)) {
            Map<C, V> map = (Map) Maps.safeGet(this.backingMap, rowKey);
            if (map != null) {
                v = map.remove(columnKey);
                if (map.isEmpty()) {
                    this.backingMap.remove(rowKey);
                }
            }
        }
        return v;
    }

    private Map<R, V> removeColumn(Object column) {
        Map<R, V> output = new LinkedHashMap();
        Iterator<Entry<R, Map<C, V>>> iterator = this.backingMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<R, Map<C, V>> entry = (Entry) iterator.next();
            V value = ((Map) entry.getValue()).remove(column);
            if (value != null) {
                output.put(entry.getKey(), value);
                if (((Map) entry.getValue()).isEmpty()) {
                    iterator.remove();
                }
            }
        }
        return output;
    }

    private boolean containsMapping(Object rowKey, Object columnKey, Object value) {
        return value != null && value.equals(get(rowKey, columnKey));
    }

    private boolean removeMapping(Object rowKey, Object columnKey, Object value) {
        if (!containsMapping(rowKey, columnKey, value)) {
            return false;
        }
        remove(rowKey, columnKey);
        return true;
    }

    public Set<Cell<R, C, V>> cellSet() {
        return super.cellSet();
    }

    Iterator<Cell<R, C, V>> cellIterator() {
        return new CellIterator(this, null);
    }

    public Map<C, V> row(R rowKey) {
        return new Row(this, rowKey);
    }

    public Map<R, V> column(C columnKey) {
        return new Column(this, columnKey);
    }

    public Set<R> rowKeySet() {
        return rowMap().keySet();
    }

    public Set<C> columnKeySet() {
        Set<C> set = this.columnKeySet;
        if (set != null) {
            return set;
        }
        set = new ColumnKeySet(this, null);
        this.columnKeySet = set;
        return set;
    }

    Iterator<C> createColumnKeyIterator() {
        return new ColumnKeyIterator(this, null);
    }

    public Collection<V> values() {
        return super.values();
    }

    public Map<R, Map<C, V>> rowMap() {
        Map<R, Map<C, V>> map = this.rowMap;
        if (map != null) {
            return map;
        }
        map = createRowMap();
        this.rowMap = map;
        return map;
    }

    Map<R, Map<C, V>> createRowMap() {
        return new RowMap(this);
    }

    public Map<C, Map<R, V>> columnMap() {
        Map<C, Map<R, V>> map = this.columnMap;
        if (map != null) {
            return map;
        }
        Map columnMap = new ColumnMap(this, null);
        this.columnMap = columnMap;
        return columnMap;
    }
}
