package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.Experimental;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.plugins.RxJavaPlugins;

@Experimental
public final class ObservableDoFinally<T> extends AbstractObservableWithUpstream<T, T> {
    final Action onFinally;

    static final class DoFinallyObserver<T> extends BasicIntQueueDisposable<T> implements Observer<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final Observer<? super T> actual;
        Disposable f2687d;
        final Action onFinally;
        QueueDisposable<T> qd;
        boolean syncFused;

        DoFinallyObserver(Observer<? super T> actual, Action onFinally) {
            this.actual = actual;
            this.onFinally = onFinally;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2687d, d)) {
                this.f2687d = d;
                if (d instanceof QueueDisposable) {
                    this.qd = (QueueDisposable) d;
                }
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
            runFinally();
        }

        public void onComplete() {
            this.actual.onComplete();
            runFinally();
        }

        public void dispose() {
            this.f2687d.dispose();
            runFinally();
        }

        public boolean isDisposed() {
            return this.f2687d.isDisposed();
        }

        public int requestFusion(int mode) {
            boolean z = true;
            QueueDisposable<T> qd = this.qd;
            if (qd == null || (mode & 4) != 0) {
                return 0;
            }
            int m = qd.requestFusion(mode);
            if (m == 0) {
                return m;
            }
            if (m != 1) {
                z = false;
            }
            this.syncFused = z;
            return m;
        }

        public void clear() {
            this.qd.clear();
        }

        public boolean isEmpty() {
            return this.qd.isEmpty();
        }

        @Nullable
        public T poll() throws Exception {
            T v = this.qd.poll();
            if (v == null && this.syncFused) {
                runFinally();
            }
            return v;
        }

        void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.run();
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    RxJavaPlugins.onError(ex);
                }
            }
        }
    }

    public ObservableDoFinally(ObservableSource<T> source, Action onFinally) {
        super(source);
        this.onFinally = onFinally;
    }

    protected void subscribeActual(Observer<? super T> s) {
        this.source.subscribe(new DoFinallyObserver(s, this.onFinally));
    }
}
