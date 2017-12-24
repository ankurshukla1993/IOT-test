package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.observers.BasicFuseableObserver;

public final class ObservableFilter<T> extends AbstractObservableWithUpstream<T, T> {
    final Predicate<? super T> predicate;

    static final class FilterObserver<T> extends BasicFuseableObserver<T, T> {
        final Predicate<? super T> filter;

        FilterObserver(Observer<? super T> actual, Predicate<? super T> filter) {
            super(actual);
            this.filter = filter;
        }

        public void onNext(T t) {
            if (this.sourceMode == 0) {
                try {
                    if (this.filter.test(t)) {
                        this.actual.onNext(t);
                        return;
                    }
                    return;
                } catch (Throwable e) {
                    fail(e);
                    return;
                }
            }
            this.actual.onNext(null);
        }

        public int requestFusion(int mode) {
            return transitiveBoundaryFusion(mode);
        }

        @Nullable
        public T poll() throws Exception {
            T v;
            do {
                v = this.qs.poll();
                if (v == null) {
                    break;
                }
            } while (!this.filter.test(v));
            return v;
        }
    }

    public ObservableFilter(ObservableSource<T> source, Predicate<? super T> predicate) {
        super(source);
        this.predicate = predicate;
    }

    public void subscribeActual(Observer<? super T> s) {
        this.source.subscribe(new FilterObserver(s, this.predicate));
    }
}
