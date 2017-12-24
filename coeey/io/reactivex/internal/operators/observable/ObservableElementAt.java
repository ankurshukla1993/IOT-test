package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;

public final class ObservableElementAt<T> extends AbstractObservableWithUpstream<T, T> {
    final T defaultValue;
    final boolean errorOnFewer;
    final long index;

    static final class ElementAtObserver<T> implements Observer<T>, Disposable {
        final Observer<? super T> actual;
        long count;
        final T defaultValue;
        boolean done;
        final boolean errorOnFewer;
        final long index;
        Disposable f2689s;

        ElementAtObserver(Observer<? super T> actual, long index, T defaultValue, boolean errorOnFewer) {
            this.actual = actual;
            this.index = index;
            this.defaultValue = defaultValue;
            this.errorOnFewer = errorOnFewer;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2689s, s)) {
                this.f2689s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2689s.dispose();
        }

        public boolean isDisposed() {
            return this.f2689s.isDisposed();
        }

        public void onNext(T t) {
            if (!this.done) {
                long c = this.count;
                if (c == this.index) {
                    this.done = true;
                    this.f2689s.dispose();
                    this.actual.onNext(t);
                    this.actual.onComplete();
                    return;
                }
                this.count = 1 + c;
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.actual.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                T v = this.defaultValue;
                if (v == null && this.errorOnFewer) {
                    this.actual.onError(new NoSuchElementException());
                    return;
                }
                if (v != null) {
                    this.actual.onNext(v);
                }
                this.actual.onComplete();
            }
        }
    }

    public ObservableElementAt(ObservableSource<T> source, long index, T defaultValue, boolean errorOnFewer) {
        super(source);
        this.index = index;
        this.defaultValue = defaultValue;
        this.errorOnFewer = errorOnFewer;
    }

    public void subscribeActual(Observer<? super T> t) {
        this.source.subscribe(new ElementAtObserver(t, this.index, this.defaultValue, this.errorOnFewer));
    }
}
