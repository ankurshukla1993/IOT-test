package io.reactivex.processors;

import java.util.concurrent.atomic.AtomicReference;

final class ReplayProcessor$TimedNode<T> extends AtomicReference<ReplayProcessor$TimedNode<T>> {
    private static final long serialVersionUID = 6404226426336033100L;
    final long time;
    final T value;

    ReplayProcessor$TimedNode(T value, long time) {
        this.value = value;
        this.time = time;
    }
}
