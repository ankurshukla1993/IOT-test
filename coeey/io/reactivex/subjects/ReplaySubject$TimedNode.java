package io.reactivex.subjects;

import java.util.concurrent.atomic.AtomicReference;

final class ReplaySubject$TimedNode<T> extends AtomicReference<ReplaySubject$TimedNode<T>> {
    private static final long serialVersionUID = 6404226426336033100L;
    final long time;
    final T value;

    ReplaySubject$TimedNode(T value, long time) {
        this.value = value;
        this.time = time;
    }
}
