package com.google.common.collect;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@VisibleForTesting
class Ordering$ArbitraryOrdering extends Ordering<Object> {
    private Map<Object, Integer> uids = Platform.tryWeakKeys(new MapMaker()).makeComputingMap(new C17051());

    class C17051 implements Function<Object, Integer> {
        final AtomicInteger counter = new AtomicInteger(0);

        C17051() {
        }

        public Integer apply(Object from) {
            return Integer.valueOf(this.counter.getAndIncrement());
        }
    }

    Ordering$ArbitraryOrdering() {
    }

    public int compare(Object left, Object right) {
        if (left == right) {
            return 0;
        }
        if (left == null) {
            return -1;
        }
        if (right == null) {
            return 1;
        }
        int leftCode = identityHashCode(left);
        int rightCode = identityHashCode(right);
        if (leftCode == rightCode) {
            int result = ((Integer) this.uids.get(left)).compareTo((Integer) this.uids.get(right));
            if (result != 0) {
                return result;
            }
            throw new AssertionError();
        } else if (leftCode >= rightCode) {
            return 1;
        } else {
            return -1;
        }
    }

    public String toString() {
        return "Ordering.arbitrary()";
    }

    int identityHashCode(Object object) {
        return System.identityHashCode(object);
    }
}
