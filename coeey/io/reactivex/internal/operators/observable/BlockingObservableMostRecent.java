package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.observers.DefaultObserver;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class BlockingObservableMostRecent<T> implements Iterable<T> {
    final T initialValue;
    final ObservableSource<T> source;

    static final class MostRecentObserver<T> extends DefaultObserver<T> {
        volatile Object value;

        final class Iterator implements java.util.Iterator<T> {
            private Object buf;

            Iterator() {
            }

            public boolean hasNext() {
                this.buf = MostRecentObserver.this.value;
                return !NotificationLite.isComplete(this.buf);
            }

            public T next() {
                try {
                    if (this.buf == null) {
                        Object obj = MostRecentObserver.this.value;
                    }
                    if (NotificationLite.isComplete(this.buf)) {
                        throw new NoSuchElementException();
                    } else if (NotificationLite.isError(this.buf)) {
                        throw ExceptionHelper.wrapOrThrow(NotificationLite.getError(this.buf));
                    } else {
                        T value = NotificationLite.getValue(this.buf);
                        this.buf = null;
                        return value;
                    }
                } finally {
                    this.buf = null;
                }
            }

            public void remove() {
                throw new UnsupportedOperationException("Read only iterator");
            }
        }

        MostRecentObserver(T value) {
            this.value = NotificationLite.next(value);
        }

        public void onComplete() {
            this.value = NotificationLite.complete();
        }

        public void onError(Throwable e) {
            this.value = NotificationLite.error(e);
        }

        public void onNext(T args) {
            this.value = NotificationLite.next(args);
        }

        public Iterator getIterable() {
            return new Iterator();
        }
    }

    public BlockingObservableMostRecent(ObservableSource<T> source, T initialValue) {
        this.source = source;
        this.initialValue = initialValue;
    }

    public Iterator<T> iterator() {
        MostRecentObserver<T> mostRecentObserver = new MostRecentObserver(this.initialValue);
        this.source.subscribe(mostRecentObserver);
        return mostRecentObserver.getIterable();
    }
}
