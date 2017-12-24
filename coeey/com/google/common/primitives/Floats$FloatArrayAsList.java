package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

@GwtCompatible
class Floats$FloatArrayAsList extends AbstractList<Float> implements RandomAccess, Serializable {
    private static final long serialVersionUID = 0;
    final float[] array;
    final int end;
    final int start;

    Floats$FloatArrayAsList(float[] array) {
        this(array, 0, array.length);
    }

    Floats$FloatArrayAsList(float[] array, int start, int end) {
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

    public Float get(int index) {
        Preconditions.checkElementIndex(index, size());
        return Float.valueOf(this.array[this.start + index]);
    }

    public boolean contains(Object target) {
        return (target instanceof Float) && Floats.access$000(this.array, ((Float) target).floatValue(), this.start, this.end) != -1;
    }

    public int indexOf(Object target) {
        if (target instanceof Float) {
            int i = Floats.access$000(this.array, ((Float) target).floatValue(), this.start, this.end);
            if (i >= 0) {
                return i - this.start;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object target) {
        if (target instanceof Float) {
            int i = Floats.access$100(this.array, ((Float) target).floatValue(), this.start, this.end);
            if (i >= 0) {
                return i - this.start;
            }
        }
        return -1;
    }

    public Float set(int index, Float element) {
        Preconditions.checkElementIndex(index, size());
        float oldValue = this.array[this.start + index];
        this.array[this.start + index] = ((Float) Preconditions.checkNotNull(element)).floatValue();
        return Float.valueOf(oldValue);
    }

    public List<Float> subList(int fromIndex, int toIndex) {
        Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
        if (fromIndex == toIndex) {
            return Collections.emptyList();
        }
        return new Floats$FloatArrayAsList(this.array, this.start + fromIndex, this.start + toIndex);
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Floats$FloatArrayAsList)) {
            return super.equals(object);
        }
        Floats$FloatArrayAsList that = (Floats$FloatArrayAsList) object;
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
            result = (result * 31) + Floats.hashCode(this.array[i]);
        }
        return result;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(size() * 12);
        builder.append('[').append(this.array[this.start]);
        for (int i = this.start + 1; i < this.end; i++) {
            builder.append(", ").append(this.array[i]);
        }
        return builder.append(']').toString();
    }

    float[] toFloatArray() {
        int size = size();
        float[] result = new float[size];
        System.arraycopy(this.array, this.start, result, 0, size);
        return result;
    }
}
