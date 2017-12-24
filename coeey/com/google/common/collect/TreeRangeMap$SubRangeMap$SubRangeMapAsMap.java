package com.google.common.collect;

import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.TreeRangeMap.SubRangeMap;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

class TreeRangeMap$SubRangeMap$SubRangeMapAsMap extends AbstractMap<Range<K>, V> {
    final /* synthetic */ SubRangeMap this$1;

    class C17522 extends Maps$EntrySet<Range<K>, V> {
        C17522() {
        }

        Map<Range<K>, V> map() {
            return TreeRangeMap$SubRangeMap$SubRangeMapAsMap.this;
        }

        public Iterator<Entry<Range<K>, V>> iterator() {
            if (SubRangeMap.access$300(TreeRangeMap$SubRangeMap$SubRangeMapAsMap.this.this$1).isEmpty()) {
                return Iterators.emptyIterator();
            }
            final Iterator<TreeRangeMap$RangeMapEntry<K, V>> backingItr = TreeRangeMap.access$100(TreeRangeMap$SubRangeMap$SubRangeMapAsMap.this.this$1.this$0).tailMap((Cut) MoreObjects.firstNonNull(TreeRangeMap.access$100(TreeRangeMap$SubRangeMap$SubRangeMapAsMap.this.this$1.this$0).floorKey(SubRangeMap.access$300(TreeRangeMap$SubRangeMap$SubRangeMapAsMap.this.this$1).lowerBound), SubRangeMap.access$300(TreeRangeMap$SubRangeMap$SubRangeMapAsMap.this.this$1).lowerBound), true).values().iterator();
            return new AbstractIterator<Entry<Range<K>, V>>() {
                protected Entry<Range<K>, V> computeNext() {
                    while (backingItr.hasNext()) {
                        TreeRangeMap$RangeMapEntry<K, V> entry = (TreeRangeMap$RangeMapEntry) backingItr.next();
                        if (entry.getLowerBound().compareTo(SubRangeMap.access$300(TreeRangeMap$SubRangeMap$SubRangeMapAsMap.this.this$1).upperBound) >= 0) {
                            break;
                        } else if (entry.getUpperBound().compareTo(SubRangeMap.access$300(TreeRangeMap$SubRangeMap$SubRangeMapAsMap.this.this$1).lowerBound) > 0) {
                            return Maps.immutableEntry(entry.getKey().intersection(SubRangeMap.access$300(TreeRangeMap$SubRangeMap$SubRangeMapAsMap.this.this$1)), entry.getValue());
                        }
                    }
                    return (Entry) endOfData();
                }
            };
        }

        public boolean retainAll(Collection<?> c) {
            return TreeRangeMap$SubRangeMap$SubRangeMapAsMap.this.removeEntryIf(Predicates.not(Predicates.in(c)));
        }

        public int size() {
            return Iterators.size(iterator());
        }

        public boolean isEmpty() {
            return !iterator().hasNext();
        }
    }

    TreeRangeMap$SubRangeMap$SubRangeMapAsMap(SubRangeMap subRangeMap) {
        this.this$1 = subRangeMap;
    }

    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    public V get(Object key) {
        try {
            if (!(key instanceof Range)) {
                return null;
            }
            Range<K> r = (Range) key;
            if (!SubRangeMap.access$300(this.this$1).encloses(r) || r.isEmpty()) {
                return null;
            }
            TreeRangeMap$RangeMapEntry<K, V> candidate = null;
            if (r.lowerBound.compareTo(SubRangeMap.access$300(this.this$1).lowerBound) == 0) {
                Entry<Cut<K>, TreeRangeMap$RangeMapEntry<K, V>> entry = TreeRangeMap.access$100(this.this$1.this$0).floorEntry(r.lowerBound);
                if (entry != null) {
                    candidate = (TreeRangeMap$RangeMapEntry) entry.getValue();
                }
            } else {
                candidate = (TreeRangeMap$RangeMapEntry) TreeRangeMap.access$100(this.this$1.this$0).get(r.lowerBound);
            }
            if (candidate != null && candidate.getKey().isConnected(SubRangeMap.access$300(this.this$1)) && candidate.getKey().intersection(SubRangeMap.access$300(this.this$1)).equals(r)) {
                return candidate.getValue();
            }
            return null;
        } catch (ClassCastException e) {
            return null;
        }
    }

    public V remove(Object key) {
        V value = get(key);
        if (value == null) {
            return null;
        }
        this.this$1.this$0.remove((Range) key);
        return value;
    }

    public void clear() {
        this.this$1.clear();
    }

    private boolean removeEntryIf(Predicate<? super Entry<Range<K>, V>> predicate) {
        List<Range<K>> toRemove = Lists.newArrayList();
        for (Entry<Range<K>, V> entry : entrySet()) {
            if (predicate.apply(entry)) {
                toRemove.add(entry.getKey());
            }
        }
        for (Range<K> range : toRemove) {
            this.this$1.this$0.remove(range);
        }
        return !toRemove.isEmpty();
    }

    public Set<Range<K>> keySet() {
        return new Maps$KeySet<Range<K>, V>(this) {
            public boolean remove(@Nullable Object o) {
                return TreeRangeMap$SubRangeMap$SubRangeMapAsMap.this.remove(o) != null;
            }

            public boolean retainAll(Collection<?> c) {
                return TreeRangeMap$SubRangeMap$SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.not(Predicates.in(c)), Maps.keyFunction()));
            }
        };
    }

    public Set<Entry<Range<K>, V>> entrySet() {
        return new C17522();
    }

    public Collection<V> values() {
        return new Maps$Values<Range<K>, V>(this) {
            public boolean removeAll(Collection<?> c) {
                return TreeRangeMap$SubRangeMap$SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.in(c), Maps.valueFunction()));
            }

            public boolean retainAll(Collection<?> c) {
                return TreeRangeMap$SubRangeMap$SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.not(Predicates.in(c)), Maps.valueFunction()));
            }
        };
    }
}
