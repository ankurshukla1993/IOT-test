package com.facebook.react.uimanager;

import java.util.Comparator;

class ViewAtIndex {
    public static Comparator<ViewAtIndex> COMPARATOR = new C13411();
    public final int mIndex;
    public final int mTag;

    static class C13411 implements Comparator<ViewAtIndex> {
        C13411() {
        }

        public int compare(ViewAtIndex lhs, ViewAtIndex rhs) {
            return lhs.mIndex - rhs.mIndex;
        }
    }

    public ViewAtIndex(int tag, int index) {
        this.mTag = tag;
        this.mIndex = index;
    }
}
