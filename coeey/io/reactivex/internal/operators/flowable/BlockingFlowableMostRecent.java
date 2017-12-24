package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.subscribers.DefaultSubscriber;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class BlockingFlowableMostRecent<T> implements Iterable<T> {
    final T initialValue;
    final Flowable<T> source;

    static final class MostRecentSubscriber<T> extends DefaultSubscriber<T> {
        volatile Object value;

        final class Iterator implements java.util.Iterator<T> {
            private Object buf;

            Iterator() {
            }

            public boolean hasNext() {
                this.buf = MostRecentSubscriber.this.value;
                return !NotificationLite.isComplete(this.buf);
            }

            public T next() {
                try {
                    if (this.buf == null) {
                        Object obj = MostRecentSubscriber.this.value;
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

        MostRecentSubscriber(T value) {
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

    public BlockingFlowableMostRecent(Flowable<T> source, T initialValue) {
        this.source = source;
        this.initialValue = initialValue;
    }

    public Iterator<T> iterator() {
        MostRecentSubscriber<T> mostRecentSubscriber = new MostRecentSubscriber(this.initialValue);
        this.source.subscribe(mostRecentSubscriber);
        return mostRecentSubscriber.getIterable();
    }
}
