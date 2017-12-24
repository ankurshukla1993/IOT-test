package com.google.android.flexbox;

import android.support.annotation.NonNull;

class FlexboxHelper$Order implements Comparable<FlexboxHelper$Order> {
    int index;
    int order;

    private FlexboxHelper$Order() {
    }

    public int compareTo(@NonNull FlexboxHelper$Order another) {
        if (this.order != another.order) {
            return this.order - another.order;
        }
        return this.index - another.index;
    }

    public String toString() {
        return "Order{order=" + this.order + ", index=" + this.index + '}';
    }
}
