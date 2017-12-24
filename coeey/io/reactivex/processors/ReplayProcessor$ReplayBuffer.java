package io.reactivex.processors;

interface ReplayProcessor$ReplayBuffer<T> {
    void complete();

    void error(Throwable th);

    Throwable getError();

    T getValue();

    T[] getValues(T[] tArr);

    boolean isDone();

    void next(T t);

    void replay(ReplayProcessor$ReplaySubscription<T> replayProcessor$ReplaySubscription);

    int size();
}
