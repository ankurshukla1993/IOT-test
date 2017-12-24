package com.google.common.base;

import java.util.Iterator;

class Converter$1 implements Iterable<B> {
    final /* synthetic */ Converter this$0;
    final /* synthetic */ Iterable val$fromIterable;

    class C15301 implements Iterator<B> {
        private final Iterator<? extends A> fromIterator = Converter$1.this.val$fromIterable.iterator();

        C15301() {
        }

        public boolean hasNext() {
            return this.fromIterator.hasNext();
        }

        public B next() {
            return Converter$1.this.this$0.convert(this.fromIterator.next());
        }

        public void remove() {
            this.fromIterator.remove();
        }
    }

    Converter$1(Converter converter, Iterable iterable) {
        this.this$0 = converter;
        this.val$fromIterable = iterable;
    }

    public Iterator<B> iterator() {
        return new C15301();
    }
}
