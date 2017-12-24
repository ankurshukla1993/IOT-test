package com.google.common.primitives;

import java.util.Comparator;

enum Floats$LexicographicalComparator implements Comparator<float[]> {
    INSTANCE;

    public int compare(float[] left, float[] right) {
        int minLength = Math.min(left.length, right.length);
        for (int i = 0; i < minLength; i++) {
            int result = Float.compare(left[i], right[i]);
            if (result != 0) {
                return result;
            }
        }
        return left.length - right.length;
    }
}
