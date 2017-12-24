package com.google.common.base;

import java.util.Iterator;

class Splitter$5 implements Iterable<String> {
    final /* synthetic */ Splitter this$0;
    final /* synthetic */ CharSequence val$sequence;

    Splitter$5(Splitter splitter, CharSequence charSequence) {
        this.this$0 = splitter;
        this.val$sequence = charSequence;
    }

    public Iterator<String> iterator() {
        return Splitter.access$000(this.this$0, this.val$sequence);
    }

    public String toString() {
        return Joiner.on(", ").appendTo(new StringBuilder().append('['), this).append(']').toString();
    }
}
