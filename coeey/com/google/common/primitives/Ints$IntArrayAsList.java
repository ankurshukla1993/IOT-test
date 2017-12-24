package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

@GwtCompatible
class Ints$IntArrayAsList extends AbstractList<Integer> implements RandomAccess, Serializable {
    private static final long serialVersionUID = 0;
    final int[] array;
    final int end;
    final int start;

    Ints$IntArrayAsList(int[] array) {
        this(array, 0, array.length);
    }

    Ints$IntArrayAsList(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    public int size() {
        return this.end - this.start;
    }

    public boolean isEmpty() {
        return false;
    }

    public Integer get(int index) {
        Preconditions.checkElementIndex(index, size());
        return Integer.valueOf(this.array[this.start + index]);
    }

    public boolean contains(Object target) {
        return (target instanceof Integer) && Ints.access$000(this.array, ((Integer) target).intValue(), this.start, this.end) != -1;
    }

    public int indexOf(Object target) {
        if (target instanceof Integer) {
            int i = Ints.access$000(this.array, ((Integer) target).intValue(), this.start, this.end);
            if (i >= 0) {
                return i - this.start;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object target) {
        if (target instanceof Integer) {
            int i = Ints.access$100(this.array, ((Integer) target).intValue(), this.start, this.end);
            if (i >= 0) {
                return i - this.start;
            }
        }
        return -1;
    }

    public Integer set(int index, Integer element) {
        Preconditions.checkElementIndex(index, size());
        int oldValue = this.array[this.start + index];
        this.array[this.start + index] = ((Integer) Preconditions.checkNotNull(element)).intValue();
        return Integer.valueOf(oldValue);
    }

    public List<Integer> subList(int fromIndex, int toIndex) {
        Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
        if (fromIndex == toIndex) {
            return Collections.emptyList();
        }
        return new Ints$IntArrayAsList(this.array, this.start + fromIndex, this.start + toIndex);
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Ints$IntArrayAsList)) {
            return super.equals(object);
        }
        Ints$IntArrayAsList that = (Ints$IntArrayAsList) object;
        int size = size();
        if (that.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (this.array[this.start + i] != that.array[that.start + i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int result = 1;
        for (int i = this.start; i < this.end; i++) {
            result = (result * 31) + Ints.hashCode(this.array[i]);
        }
        return result;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(size() * 5);
        builder.append('[').append(this.array[this.start]);
        for (int i = this.start + 1; i < this.end; i++) {
            builder.append(", ").append(this.array[i]);
        }
        return builder.append(']').toString();
    }

    int[] toIntArray() {
        int size = size();
        int[] result = new int[size];
        System.arraycopy(this.array, this.start, result, 0, size);
        return result;
    }
}
