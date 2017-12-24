package com.google.common.primitives;

import java.util.Comparator;

enum Ints$LexicographicalComparator implements Comparator<int[]> {
    INSTANCE;

    public int compare(int[] left, int[] right) {
        int minLength = Math.min(left.length, right.length);
        for (int i = 0; i < minLength; i++) {
            int result = Ints.compare(left[i], right[i]);
            if (result != 0) {
                return result;
            }
        }
        return left.length - right.length;
    }
}
