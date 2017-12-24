package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.Iterator;
import org.reactivestreams.Subscriber;

public final class FlowableFromIterable<T> extends Flowable<T> {
    final Iterable<? extends T> source;

    static abstract class BaseRangeSubscription<T> extends BasicQueueSubscription<T> {
        private static final long serialVersionUID = -2252972430506210021L;
        volatile boolean cancelled;
        Iterator<? extends T> it;
        boolean once;

        abstract void fastPath();

        abstract void slowPath(long j);

        BaseRangeSubscription(Iterator<? extends T> it) {
            this.it = it;
        }

        public final int requestFusion(int mode) {
            return mode & 1;
        }

        @Nullable
        public final T poll() {
            if (this.it == null) {
                return null;
            }
            if (!this.once) {
                this.once = true;
            } else if (!this.it.hasNext()) {
                return null;
            }
            return ObjectHelper.requireNonNull(this.it.next(), "Iterator.next() returned a null value");
        }

        public final boolean isEmpty() {
            return this.it == null || !this.it.hasNext();
        }

        public final void clear() {
            this.it = null;
        }

        public final void request(long n) {
            if (!SubscriptionHelper.validate(n) || BackpressureHelper.add(this, n) != 0) {
                return;
            }
            if (n == Long.MAX_VALUE) {
                fastPath();
            } else {
                slowPath(n);
            }
        }

        public final void cancel() {
            this.cancelled = true;
        }
    }

    static final class IteratorConditionalSubscription<T> extends BaseRangeSubscription<T> {
        private static final long serialVersionUID = -6022804456014692607L;
        final ConditionalSubscriber<? super T> actual;

        IteratorConditionalSubscription(ConditionalSubscriber<? super T> actual, Iterator<? extends T> it) {
            super(it);
            this.actual = actual;
        }

        void fastPath() {
            Iterator<? extends T> it = this.it;
            ConditionalSubscriber<? super T> a = this.actual;
            while (!this.cancelled) {
                try {
                    T t = it.next();
                    if (!this.cancelled) {
                        if (t == null) {
                            a.onError(new NullPointerException("Iterator.next() returned a null value"));
                            return;
                        }
                        a.tryOnNext(t);
                        if (!this.cancelled) {
                            try {
                                if (!it.hasNext()) {
                                    if (!this.cancelled) {
                                        a.onComplete();
                                        return;
                                    }
                                    return;
                                }
                            } catch (Throwable ex) {
                                Exceptions.throwIfFatal(ex);
                                a.onError(ex);
                                return;
                            }
                        }
                        return;
                    }
                    return;
                } catch (Throwable ex2) {
                    Exceptions.throwIfFatal(ex2);
                    a.onError(ex2);
                    return;
                }
            }
        }

        void slowPath(long r) {
            long e = 0;
            Iterator<? extends T> it = this.it;
            ConditionalSubscriber<? super T> a = this.actual;
            while (true) {
                if (e == r) {
                    r = get();
                    if (e == r) {
                        r = addAndGet(-e);
                        if (r != 0) {
                            e = 0;
                        } else {
                            return;
                        }
                    }
                    continue;
                } else if (!this.cancelled) {
                    try {
                        T t = it.next();
                        if (!this.cancelled) {
                            if (t == null) {
                                a.onError(new NullPointerException("Iterator.next() returned a null value"));
                                return;
                            }
                            boolean b = a.tryOnNext(t);
                            if (!this.cancelled) {
                                try {
                                    if (!it.hasNext()) {
                                        break;
                                    } else if (b) {
                                        e++;
                                    }
                                } catch (Throwable ex) {
                                    Exceptions.throwIfFatal(ex);
                                    a.onError(ex);
                                    return;
                                }
                            }
                            return;
                        }
                        return;
                    } catch (Throwable ex2) {
                        Exceptions.throwIfFatal(ex2);
                        a.onError(ex2);
                        return;
                    }
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                a.onComplete();
            }
        }
    }

    static final class IteratorSubscription<T> extends BaseRangeSubscription<T> {
        private static final long serialVersionUID = -6022804456014692607L;
        final Subscriber<? super T> actual;

        IteratorSubscription(Subscriber<? super T> actual, Iterator<? extends T> it) {
            super(it);
            this.actual = actual;
        }

        void fastPath() {
            Iterator<? extends T> it = this.it;
            Subscriber<? super T> a = this.actual;
            while (!this.cancelled) {
                try {
                    T t = it.next();
                    if (!this.cancelled) {
                        if (t == null) {
                            a.onError(new NullPointerException("Iterator.next() returned a null value"));
                            return;
                        }
                        a.onNext(t);
                        if (!this.cancelled) {
                            try {
                                if (!it.hasNext()) {
                                    if (!this.cancelled) {
                                        a.onComplete();
                                        return;
                                    }
                                    return;
                                }
                            } catch (Throwable ex) {
                                Exceptions.throwIfFatal(ex);
                                a.onError(ex);
                                return;
                            }
                        }
                        return;
                    }
                    return;
                } catch (Throwable ex2) {
                    Exceptions.throwIfFatal(ex2);
                    a.onError(ex2);
                    return;
                }
            }
        }

        void slowPath(long r) {
            long e = 0;
            Iterator<? extends T> it = this.it;
            Subscriber<? super T> a = this.actual;
            while (true) {
                if (e == r) {
                    r = get();
                    if (e == r) {
                        r = addAndGet(-e);
                        if (r != 0) {
                            e = 0;
                        } else {
                            return;
                        }
                    }
                    continue;
                } else if (!this.cancelled) {
                    try {
                        T t = it.next();
                        if (!this.cancelled) {
                            if (t == null) {
                                a.onError(new NullPointerException("Iterator.next() returned a null value"));
                                return;
                            }
                            a.onNext(t);
                            if (!this.cancelled) {
                                try {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    e++;
                                } catch (Throwable ex) {
                                    Exceptions.throwIfFatal(ex);
                                    a.onError(ex);
                                    return;
                                }
                            }
                            return;
                        }
                        return;
                    } catch (Throwable ex2) {
                        Exceptions.throwIfFatal(ex2);
                        a.onError(ex2);
                        return;
                    }
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                a.onComplete();
            }
        }
    }

    public FlowableFromIterable(Iterable<? extends T> source) {
        this.source = source;
    }

    public void subscribeActual(Subscriber<? super T> s) {
        try {
            subscribe(s, this.source.iterator());
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            EmptySubscription.error(e, s);
        }
    }

    public static <T> void subscribe(Subscriber<? super T> s, Iterator<? extends T> it) {
        try {
            if (!it.hasNext()) {
                EmptySubscription.complete(s);
            } else if (s instanceof ConditionalSubscriber) {
                s.onSubscribe(new IteratorConditionalSubscription((ConditionalSubscriber) s, it));
            } else {
                s.onSubscribe(new IteratorSubscription(s, it));
            }
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            EmptySubscription.error(e, s);
        }
    }
}
