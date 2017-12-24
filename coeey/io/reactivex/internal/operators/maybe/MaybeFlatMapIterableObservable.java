package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.BasicQueueDisposable;
import java.util.Iterator;

public final class MaybeFlatMapIterableObservable<T, R> extends Observable<R> {
    final Function<? super T, ? extends Iterable<? extends R>> mapper;
    final MaybeSource<T> source;

    static final class FlatMapIterableObserver<T, R> extends BasicQueueDisposable<R> implements MaybeObserver<T> {
        final Observer<? super R> actual;
        volatile boolean cancelled;
        Disposable f2634d;
        volatile Iterator<? extends R> it;
        final Function<? super T, ? extends Iterable<? extends R>> mapper;
        boolean outputFused;

        FlatMapIterableObserver(Observer<? super R> actual, Function<? super T, ? extends Iterable<? extends R>> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2634d, d)) {
                this.f2634d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            Observer<? super R> a = this.actual;
            try {
                Iterator<? extends R> iterator = ((Iterable) this.mapper.apply(value)).iterator();
                if (iterator.hasNext()) {
                    this.it = iterator;
                    if (this.outputFused) {
                        a.onNext(null);
                        a.onComplete();
                        return;
                    }
                    while (!this.cancelled) {
                        try {
                            a.onNext(iterator.next());
                            if (!this.cancelled) {
                                try {
                                    if (!iterator.hasNext()) {
                                        a.onComplete();
                                        return;
                                    }
                                } catch (Throwable ex) {
                                    Exceptions.throwIfFatal(ex);
                                    a.onError(ex);
                                    return;
                                }
                            }
                            return;
                        } catch (Throwable ex2) {
                            Exceptions.throwIfFatal(ex2);
                            a.onError(ex2);
                            return;
                        }
                    }
                    return;
                }
                a.onComplete();
            } catch (Throwable ex22) {
                Exceptions.throwIfFatal(ex22);
                a.onError(ex22);
            }
        }

        public void onError(Throwable e) {
            this.f2634d = DisposableHelper.DISPOSED;
            this.actual.onError(e);
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void dispose() {
            this.cancelled = true;
            this.f2634d.dispose();
            this.f2634d = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public int requestFusion(int mode) {
            if ((mode & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        public void clear() {
            this.it = null;
        }

        public boolean isEmpty() {
            return this.it == null;
        }

        @Nullable
        public R poll() throws Exception {
            Iterator<? extends R> iterator = this.it;
            if (iterator == null) {
                return null;
            }
            R v = ObjectHelper.requireNonNull(iterator.next(), "The iterator returned a null value");
            if (iterator.hasNext()) {
                return v;
            }
            this.it = null;
            return v;
        }
    }

    public MaybeFlatMapIterableObservable(MaybeSource<T> source, Function<? super T, ? extends Iterable<? extends R>> mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    protected void subscribeActual(Observer<? super R> s) {
        this.source.subscribe(new FlatMapIterableObserver(s, this.mapper));
    }
}
