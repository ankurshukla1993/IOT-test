package com.google.common.base;

import javax.annotation.Nullable;

class Joiner$1 extends Joiner {
    final /* synthetic */ Joiner this$0;
    final /* synthetic */ String val$nullText;

    Joiner$1(Joiner joiner, Joiner x0, String str) {
        this.this$0 = joiner;
        this.val$nullText = str;
        super(x0, null);
    }

    CharSequence toString(@Nullable Object part) {
        return part == null ? this.val$nullText : this.this$0.toString(part);
    }

    public Joiner useForNull(String nullText) {
        throw new UnsupportedOperationException("already specified useForNull");
    }

    public Joiner skipNulls() {
        throw new UnsupportedOperationException("already specified useForNull");
    }
}
