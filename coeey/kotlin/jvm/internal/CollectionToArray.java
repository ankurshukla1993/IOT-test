package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class CollectionToArray {
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

    public static Object[] toArray(Collection<?> collection) {
        int size = collection.size();
        if (size == 0) {
            return EMPTY_OBJECT_ARRAY;
        }
        Object[] r = new Object[size];
        Iterator<?> it = collection.iterator();
        for (int i = 0; i < size; i++) {
            if (!it.hasNext()) {
                return Arrays.copyOf(r, i);
            }
            r[i] = it.next();
        }
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    public static <T, E> T[] toArray(Collection<E> collection, T[] a) {
        int size = collection.size();
        T[] r = a.length >= size ? a : (Object[]) Array.newInstance(a.getClass().getComponentType(), size);
        Iterator<E> it = collection.iterator();
        int i = 0;
        while (i < r.length) {
            if (it.hasNext()) {
                r[i] = it.next();
                i++;
            } else if (a != r) {
                return Arrays.copyOf(r, i);
            } else {
                r[i] = null;
                return r;
            }
        }
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    private static <T> T[] finishToArray(T[] r, Iterator<?> it) {
        int i = r.length;
        while (it.hasNext()) {
            int cap = r.length;
            if (i == cap) {
                int newCap = ((cap / 2) + 1) * 3;
                if (newCap <= cap) {
                    if (cap == Integer.MAX_VALUE) {
                        throw new OutOfMemoryError("Required array size too large");
                    }
                    newCap = Integer.MAX_VALUE;
                }
                r = Arrays.copyOf(r, newCap);
            }
            int i2 = i + 1;
            r[i] = it.next();
            i = i2;
        }
        return i == r.length ? r : Arrays.copyOf(r, i);
    }

    private CollectionToArray() {
    }
}
