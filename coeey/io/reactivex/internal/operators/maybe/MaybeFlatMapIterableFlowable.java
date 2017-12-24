package io.reactivex.internal.operators.maybe;

import io.reactivex.Flowable;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;

public final class MaybeFlatMapIterableFlowable<T, R> extends Flowable<R> {
    final Function<? super T, ? extends Iterable<? extends R>> mapper;
    final MaybeSource<T> source;

    static final class FlatMapIterableObserver<T, R> extends BasicIntQueueSubscription<R> implements MaybeObserver<T> {
        private static final long serialVersionUID = -8938804753851907758L;
        final Subscriber<? super R> actual;
        volatile boolean cancelled;
        Disposable f2633d;
        volatile Iterator<? extends R> it;
        final Function<? super T, ? extends Iterable<? extends R>> mapper;
        boolean outputFused;
        final AtomicLong requested = new AtomicLong();

        FlatMapIterableObserver(Subscriber<? super R> actual, Function<? super T, ? extends Iterable<? extends R>> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2633d, d)) {
                this.f2633d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            try {
                Iterator<? extends R> iterator = ((Iterable) this.mapper.apply(value)).iterator();
                if (iterator.hasNext()) {
                    this.it = iterator;
                    drain();
                    return;
                }
                this.actual.onComplete();
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.actual.onError(ex);
            }
        }

        public void onError(Throwable e) {
            this.f2633d = DisposableHelper.DISPOSED;
            this.actual.onError(e);
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            this.f2633d.dispose();
            this.f2633d = DisposableHelper.DISPOSED;
        }

        void fastPath(Subscriber<? super R> a, Iterator<? extends R> iterator) {
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
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Subscriber<? super R> a = this.actual;
                Iterator<? extends R> iterator = this.it;
                if (!this.outputFused || iterator == null) {
                    int missed = 1;
                    while (true) {
                        if (iterator != null) {
                            long r = this.requested.get();
                            if (r == Long.MAX_VALUE) {
                                fastPath(a, iterator);
                                return;
                            }
                            long e = 0;
                            while (e != r) {
                                if (!this.cancelled) {
                                    try {
                                        a.onNext(ObjectHelper.requireNonNull(iterator.next(), "The iterator returned a null value"));
                                        if (!this.cancelled) {
                                            e++;
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
                            if (e != 0) {
                                BackpressureHelper.produced(this.requested, e);
                            }
                        }
                        missed = addAndGet(-missed);
                        if (missed == 0) {
                            return;
                        }
                        if (iterator == null) {
                            iterator = this.it;
                        }
                    }
                } else {
                    a.onNext(null);
                    a.onComplete();
                }
            }
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

    public MaybeFlatMapIterableFlowable(MaybeSource<T> source, Function<? super T, ? extends Iterable<? extends R>> mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    protected void subscribeActual(Subscriber<? super R> s) {
        this.source.subscribe(new FlatMapIterableObserver(s, this.mapper));
    }
}
