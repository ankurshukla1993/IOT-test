package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.collect.MapDifference.ValueDifference;
import java.util.Map;

class Maps$MapDifferenceImpl<K, V> implements MapDifference<K, V> {
    final Map<K, ValueDifference<V>> differences;
    final Map<K, V> onBoth;
    final Map<K, V> onlyOnLeft;
    final Map<K, V> onlyOnRight;

    Maps$MapDifferenceImpl(Map<K, V> onlyOnLeft, Map<K, V> onlyOnRight, Map<K, V> onBoth, Map<K, ValueDifference<V>> differences) {
        this.onlyOnLeft = Maps.access$100(onlyOnLeft);
        this.onlyOnRight = Maps.access$100(onlyOnRight);
        this.onBoth = Maps.access$100(onBoth);
        this.differences = Maps.access$100(differences);
    }

    public boolean areEqual() {
        return this.onlyOnLeft.isEmpty() && this.onlyOnRight.isEmpty() && this.differences.isEmpty();
    }

    public Map<K, V> entriesOnlyOnLeft() {
        return this.onlyOnLeft;
    }

    public Map<K, V> entriesOnlyOnRight() {
        return this.onlyOnRight;
    }

    public Map<K, V> entriesInCommon() {
        return this.onBoth;
    }

    public Map<K, ValueDifference<V>> entriesDiffering() {
        return this.differences;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof MapDifference)) {
            return false;
        }
        MapDifference<?, ?> other = (MapDifference) object;
        if (entriesOnlyOnLeft().equals(other.entriesOnlyOnLeft()) && entriesOnlyOnRight().equals(other.entriesOnlyOnRight()) && entriesInCommon().equals(other.entriesInCommon()) && entriesDiffering().equals(other.entriesDiffering())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{entriesOnlyOnLeft(), entriesOnlyOnRight(), entriesInCommon(), entriesDiffering()});
    }

    public String toString() {
        if (areEqual()) {
            return "equal";
        }
        StringBuilder result = new StringBuilder("not equal");
        if (!this.onlyOnLeft.isEmpty()) {
            result.append(": only on left=").append(this.onlyOnLeft);
        }
        if (!this.onlyOnRight.isEmpty()) {
            result.append(": only on right=").append(this.onlyOnRight);
        }
        if (!this.differences.isEmpty()) {
            result.append(": value differences=").append(this.differences);
        }
        return result.toString();
    }
}
