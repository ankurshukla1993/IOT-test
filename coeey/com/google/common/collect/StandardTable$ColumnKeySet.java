package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

class StandardTable$ColumnKeySet extends StandardTable$TableSet<C> {
    final /* synthetic */ StandardTable this$0;

    private StandardTable$ColumnKeySet(StandardTable standardTable) {
        this.this$0 = standardTable;
        super(standardTable);
    }

    public Iterator<C> iterator() {
        return this.this$0.createColumnKeyIterator();
    }

    public int size() {
        return Iterators.size(iterator());
    }

    public boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }
        boolean changed = false;
        Iterator<Map<C, V>> iterator = this.this$0.backingMap.values().iterator();
        while (iterator.hasNext()) {
            Map<C, V> map = (Map) iterator.next();
            if (map.keySet().remove(obj)) {
                changed = true;
                if (map.isEmpty()) {
                    iterator.remove();
                }
            }
        }
        return changed;
    }

    public boolean removeAll(Collection<?> c) {
        Preconditions.checkNotNull(c);
        boolean changed = false;
        Iterator<Map<C, V>> iterator = this.this$0.backingMap.values().iterator();
        while (iterator.hasNext()) {
            Map<C, V> map = (Map) iterator.next();
            if (Iterators.removeAll(map.keySet().iterator(), c)) {
                changed = true;
                if (map.isEmpty()) {
                    iterator.remove();
                }
            }
        }
        return changed;
    }

    public boolean retainAll(Collection<?> c) {
        Preconditions.checkNotNull(c);
        boolean changed = false;
        Iterator<Map<C, V>> iterator = this.this$0.backingMap.values().iterator();
        while (iterator.hasNext()) {
            Map<C, V> map = (Map) iterator.next();
            if (map.keySet().retainAll(c)) {
                changed = true;
                if (map.isEmpty()) {
                    iterator.remove();
                }
            }
        }
        return changed;
    }

    public boolean contains(Object obj) {
        return this.this$0.containsColumn(obj);
    }
}
