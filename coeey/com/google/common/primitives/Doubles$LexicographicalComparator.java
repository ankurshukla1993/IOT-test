package com.google.common.primitives;

import java.util.Comparator;

enum Doubles$LexicographicalComparator implements Comparator<double[]> {
    INSTANCE;

    public int compare(double[] left, double[] right) {
        int minLength = Math.min(left.length, right.length);
        for (int i = 0; i < minLength; i++) {
            int result = Double.compare(left[i], right[i]);
            if (result != 0) {
                return result;
            }
        }
        return left.length - right.length;
    }
}
