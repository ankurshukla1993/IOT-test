package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps.SortedKeySet;
import com.google.common.collect.StandardTable.Row;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.SortedSet;
import javax.annotation.Nullable;

class TreeBasedTable$TreeRow extends Row implements SortedMap<C, V> {
    @Nullable
    final C lowerBound;
    final /* synthetic */ TreeBasedTable this$0;
    @Nullable
    final C upperBound;
    transient SortedMap<C, V> wholeRow;

    TreeBasedTable$TreeRow(TreeBasedTable treeBasedTable, R rowKey) {
        this(treeBasedTable, rowKey, null, null);
    }

    TreeBasedTable$TreeRow(TreeBasedTable treeBasedTable, @Nullable R rowKey, @Nullable C lowerBound, C upperBound) {
        this.this$0 = treeBasedTable;
        super(treeBasedTable, rowKey);
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        boolean z = lowerBound == null || upperBound == null || compare(lowerBound, upperBound) <= 0;
        Preconditions.checkArgument(z);
    }

    public SortedSet<C> keySet() {
        return new SortedKeySet(this);
    }

    public Comparator<? super C> comparator() {
        return this.this$0.columnComparator();
    }

    int compare(Object a, Object b) {
        return comparator().compare(a, b);
    }

    boolean rangeContains(@Nullable Object o) {
        return o != null && ((this.lowerBound == null || compare(this.lowerBound, o) <= 0) && (this.upperBound == null || compare(this.upperBound, o) > 0));
    }

    public SortedMap<C, V> subMap(C fromKey, C toKey) {
        boolean z = rangeContains(Preconditions.checkNotNull(fromKey)) && rangeContains(Preconditions.checkNotNull(toKey));
        Preconditions.checkArgument(z);
        return new TreeBasedTable$TreeRow(this.this$0, this.rowKey, fromKey, toKey);
    }

    public SortedMap<C, V> headMap(C toKey) {
        Preconditions.checkArgument(rangeContains(Preconditions.checkNotNull(toKey)));
        return new TreeBasedTable$TreeRow(this.this$0, this.rowKey, this.lowerBound, toKey);
    }

    public SortedMap<C, V> tailMap(C fromKey) {
        Preconditions.checkArgument(rangeContains(Preconditions.checkNotNull(fromKey)));
        return new TreeBasedTable$TreeRow(this.this$0, this.rowKey, fromKey, this.upperBound);
    }

    public C firstKey() {
        if (backingRowMap() != null) {
            return backingRowMap().firstKey();
        }
        throw new NoSuchElementException();
    }

    public C lastKey() {
        if (backingRowMap() != null) {
            return backingRowMap().lastKey();
        }
        throw new NoSuchElementException();
    }

    SortedMap<C, V> wholeRow() {
        if (this.wholeRow == null || (this.wholeRow.isEmpty() && this.this$0.backingMap.containsKey(this.rowKey))) {
            this.wholeRow = (SortedMap) this.this$0.backingMap.get(this.rowKey);
        }
        return this.wholeRow;
    }

    SortedMap<C, V> backingRowMap() {
        return (SortedMap) super.backingRowMap();
    }

    SortedMap<C, V> computeBackingRowMap() {
        SortedMap<C, V> map = wholeRow();
        if (map == null) {
            return null;
        }
        if (this.lowerBound != null) {
            map = map.tailMap(this.lowerBound);
        }
        if (this.upperBound != null) {
            map = map.headMap(this.upperBound);
        }
        return map;
    }

    void maintainEmptyInvariant() {
        if (wholeRow() != null && this.wholeRow.isEmpty()) {
            this.this$0.backingMap.remove(this.rowKey);
            this.wholeRow = null;
            this.backingRowMap = null;
        }
    }

    public boolean containsKey(Object key) {
        return rangeContains(key) && super.containsKey(key);
    }

    public V put(C key, V value) {
        Preconditions.checkArgument(rangeContains(Preconditions.checkNotNull(key)));
        return super.put(key, value);
    }
}
