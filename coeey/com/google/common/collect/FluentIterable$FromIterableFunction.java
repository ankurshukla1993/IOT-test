package com.google.common.collect;

import com.google.common.base.Function;

class FluentIterable$FromIterableFunction<E> implements Function<Iterable<E>, FluentIterable<E>> {
    private FluentIterable$FromIterableFunction() {
    }

    public FluentIterable<E> apply(Iterable<E> fromObject) {
        return FluentIterable.from(fromObject);
    }
}
