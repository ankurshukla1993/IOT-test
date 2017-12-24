package com.google.common.collect;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.collect.LinkedHashMultimap.ValueSetLink;
import javax.annotation.Nullable;

@VisibleForTesting
final class LinkedHashMultimap$ValueEntry<K, V> extends ImmutableEntry<K, V> implements ValueSetLink<K, V> {
    @Nullable
    LinkedHashMultimap$ValueEntry<K, V> nextInValueBucket;
    LinkedHashMultimap$ValueEntry<K, V> predecessorInMultimap;
    ValueSetLink<K, V> predecessorInValueSet;
    final int smearedValueHash;
    LinkedHashMultimap$ValueEntry<K, V> successorInMultimap;
    ValueSetLink<K, V> successorInValueSet;

    LinkedHashMultimap$ValueEntry(@Nullable K key, @Nullable V value, int smearedValueHash, @Nullable LinkedHashMultimap$ValueEntry<K, V> nextInValueBucket) {
        super(key, value);
        this.smearedValueHash = smearedValueHash;
        this.nextInValueBucket = nextInValueBucket;
    }

    boolean matchesValue(@Nullable Object v, int smearedVHash) {
        return this.smearedValueHash == smearedVHash && Objects.equal(getValue(), v);
    }

    public ValueSetLink<K, V> getPredecessorInValueSet() {
        return this.predecessorInValueSet;
    }

    public ValueSetLink<K, V> getSuccessorInValueSet() {
        return this.successorInValueSet;
    }

    public void setPredecessorInValueSet(ValueSetLink<K, V> entry) {
        this.predecessorInValueSet = entry;
    }

    public void setSuccessorInValueSet(ValueSetLink<K, V> entry) {
        this.successorInValueSet = entry;
    }

    public LinkedHashMultimap$ValueEntry<K, V> getPredecessorInMultimap() {
        return this.predecessorInMultimap;
    }

    public LinkedHashMultimap$ValueEntry<K, V> getSuccessorInMultimap() {
        return this.successorInMultimap;
    }

    public void setSuccessorInMultimap(LinkedHashMultimap$ValueEntry<K, V> multimapSuccessor) {
        this.successorInMultimap = multimapSuccessor;
    }

    public void setPredecessorInMultimap(LinkedHashMultimap$ValueEntry<K, V> multimapPredecessor) {
        this.predecessorInMultimap = multimapPredecessor;
    }
}
