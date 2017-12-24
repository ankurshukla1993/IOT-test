package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableAmb<T> extends Observable<T> {
    final ObservableSource<? extends T>[] sources;
    final Iterable<? extends ObservableSource<? extends T>> sourcesIterable;

    static final class AmbCoordinator<T> implements Disposable {
        final Observer<? super T> actual;
        final AmbInnerObserver<T>[] observers;
        final AtomicInteger winner = new AtomicInteger();

        AmbCoordinator(Observer<? super T> actual, int count) {
            this.actual = actual;
            this.observers = new AmbInnerObserver[count];
        }

        public void subscribe(ObservableSource<? extends T>[] sources) {
            int i;
            AmbInnerObserver<T>[] as = this.observers;
            int len = as.length;
            for (i = 0; i < len; i++) {
                as[i] = new AmbInnerObserver(this, i + 1, this.actual);
            }
            this.winner.lazySet(0);
            this.actual.onSubscribe(this);
            for (i = 0; i < len && this.winner.get() == 0; i++) {
                sources[i].subscribe(as[i]);
            }
        }

        public boolean win(int index) {
            int w = this.winner.get();
            if (w == 0) {
                if (!this.winner.compareAndSet(0, index)) {
                    return false;
                }
                AmbInnerObserver<T>[] a = this.observers;
                int n = a.length;
                for (int i = 0; i < n; i++) {
                    if (i + 1 != index) {
                        a[i].dispose();
                    }
                }
                return true;
            } else if (w != index) {
                return false;
            } else {
                return true;
            }
        }

        public void dispose() {
            if (this.winner.get() != -1) {
                this.winner.lazySet(-1);
                for (AmbInnerObserver<T> a : this.observers) {
                    a.dispose();
                }
            }
        }

        public boolean isDisposed() {
            return this.winner.get() == -1;
        }
    }

    static final class AmbInnerObserver<T> extends AtomicReference<Disposable> implements Observer<T> {
        private static final long serialVersionUID = -1185974347409665484L;
        final Observer<? super T> actual;
        final int index;
        final AmbCoordinator<T> parent;
        boolean won;

        AmbInnerObserver(AmbCoordinator<T> parent, int index, Observer<? super T> actual) {
            this.parent = parent;
            this.index = index;
            this.actual = actual;
        }

        public void onSubscribe(Disposable s) {
            DisposableHelper.setOnce(this, s);
        }

        public void onNext(T t) {
            if (this.won) {
                this.actual.onNext(t);
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.actual.onNext(t);
            } else {
                ((Disposable) get()).dispose();
            }
        }

        public void onError(Throwable t) {
            if (this.won) {
                this.actual.onError(t);
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.actual.onError(t);
            } else {
                RxJavaPlugins.onError(t);
            }
        }

        public void onComplete() {
            if (this.won) {
                this.actual.onComplete();
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.actual.onComplete();
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }

    public ObservableAmb(ObservableSource<? extends T>[] sources, Iterable<? extends ObservableSource<? extends T>> sourcesIterable) {
        this.sources = sources;
        this.sourcesIterable = sourcesIterable;
    }

    public void subscribeActual(Observer<? super T> s) {
        int i;
        Throwable e;
        ObservableSource<? extends T>[] sources = this.sources;
        if (sources == null) {
            sources = new Observable[8];
            try {
                int count = 0;
                for (ObservableSource<? extends T> p : this.sourcesIterable) {
                    try {
                        if (p == null) {
                            EmptyDisposable.error(new NullPointerException("One of the sources is null"), (Observer) s);
                            i = count;
                            return;
                        }
                        if (count == sources.length) {
                            ObservableSource<? extends T>[] b = new ObservableSource[((count >> 2) + count)];
                            System.arraycopy(sources, 0, b, 0, count);
                            sources = b;
                        }
                        i = count + 1;
                        sources[count] = p;
                        count = i;
                    } catch (Throwable th) {
                        e = th;
                        i = count;
                    }
                }
                i = count;
            } catch (Throwable th2) {
                e = th2;
            }
        } else {
            i = sources.length;
        }
        if (i == 0) {
            EmptyDisposable.complete((Observer) s);
            return;
        } else if (i == 1) {
            sources[0].subscribe(s);
            return;
        } else {
            new AmbCoordinator(s, i).subscribe(sources);
            return;
        }
        Exceptions.throwIfFatal(e);
        EmptyDisposable.error(e, (Observer) s);
    }
}
