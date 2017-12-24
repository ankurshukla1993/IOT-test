package com.facebook.react.modules.core;

import java.util.Comparator;

class Timing$1 implements Comparator<Timing$Timer> {
    final /* synthetic */ Timing this$0;

    Timing$1(Timing this$0) {
        this.this$0 = this$0;
    }

    public int compare(Timing$Timer lhs, Timing$Timer rhs) {
        long diff = lhs.mTargetTime - rhs.mTargetTime;
        if (diff == 0) {
            return 0;
        }
        if (diff < 0) {
            return -1;
        }
        return 1;
    }
}
