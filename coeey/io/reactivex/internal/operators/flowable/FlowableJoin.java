package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AbstractFlowableWithUpstream<TLeft, R> {
    final Function<? super TLeft, ? extends Publisher<TLeftEnd>> leftEnd;
    final Publisher<? extends TRight> other;
    final BiFunction<? super TLeft, ? super TRight, ? extends R> resultSelector;
    final Function<? super TRight, ? extends Publisher<TRightEnd>> rightEnd;

    static final class JoinSubscription<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements Subscription, JoinSupport {
        static final Integer LEFT_CLOSE = Integer.valueOf(3);
        static final Integer LEFT_VALUE = Integer.valueOf(1);
        static final Integer RIGHT_CLOSE = Integer.valueOf(4);
        static final Integer RIGHT_VALUE = Integer.valueOf(2);
        private static final long serialVersionUID = -6071216598687999801L;
        final AtomicInteger active;
        final Subscriber<? super R> actual;
        volatile boolean cancelled;
        final CompositeDisposable disposables = new CompositeDisposable();
        final AtomicReference<Throwable> error = new AtomicReference();
        final Function<? super TLeft, ? extends Publisher<TLeftEnd>> leftEnd;
        int leftIndex;
        final Map<Integer, TLeft> lefts = new LinkedHashMap();
        final SpscLinkedArrayQueue<Object> queue = new SpscLinkedArrayQueue(Flowable.bufferSize());
        final AtomicLong requested = new AtomicLong();
        final BiFunction<? super TLeft, ? super TRight, ? extends R> resultSelector;
        final Function<? super TRight, ? extends Publisher<TRightEnd>> rightEnd;
        int rightIndex;
        final Map<Integer, TRight> rights = new LinkedHashMap();

        JoinSubscription(Subscriber<? super R> actual, Function<? super TLeft, ? extends Publisher<TLeftEnd>> leftEnd, Function<? super TRight, ? extends Publisher<TRightEnd>> rightEnd, BiFunction<? super TLeft, ? super TRight, ? extends R> resultSelector) {
            this.actual = actual;
            this.leftEnd = leftEnd;
            this.rightEnd = rightEnd;
            this.resultSelector = resultSelector;
            this.active = new AtomicInteger(2);
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        void cancelAll() {
            this.disposables.dispose();
        }

        void errorAll(Subscriber<?> a) {
            Throwable ex = ExceptionHelper.terminate(this.error);
            this.lefts.clear();
            this.rights.clear();
            a.onError(ex);
        }

        void fail(Throwable exc, Subscriber<?> a, SimpleQueue<?> q) {
            Exceptions.throwIfFatal(exc);
            ExceptionHelper.addThrowable(this.error, exc);
            q.clear();
            cancelAll();
            errorAll(a);
        }

        void drain() {
            if (getAndIncrement() == 0) {
                int missed = 1;
                SpscLinkedArrayQueue<Object> q = this.queue;
                Subscriber<? super R> a = this.actual;
                while (!this.cancelled) {
                    if (((Throwable) this.error.get()) != null) {
                        q.clear();
                        cancelAll();
                        errorAll(a);
                        return;
                    }
                    boolean d = this.active.get() == 0;
                    Integer mode = (Integer) q.poll();
                    boolean empty = mode == null;
                    if (d && empty) {
                        this.lefts.clear();
                        this.rights.clear();
                        this.disposables.dispose();
                        a.onComplete();
                        return;
                    } else if (empty) {
                        missed = addAndGet(-missed);
                        if (missed == 0) {
                            return;
                        }
                    } else {
                        TLeft val = q.poll();
                        TLeft left;
                        int idx;
                        LeftRightEndSubscriber end;
                        long r;
                        long e;
                        R w;
                        TRight right;
                        if (mode == LEFT_VALUE) {
                            left = val;
                            idx = this.leftIndex;
                            this.leftIndex = idx + 1;
                            this.lefts.put(Integer.valueOf(idx), left);
                            try {
                                Publisher<TLeftEnd> p = (Publisher) ObjectHelper.requireNonNull(this.leftEnd.apply(left), "The leftEnd returned a null Publisher");
                                end = new LeftRightEndSubscriber(this, true, idx);
                                this.disposables.add(end);
                                p.subscribe(end);
                                if (((Throwable) this.error.get()) != null) {
                                    q.clear();
                                    cancelAll();
                                    errorAll(a);
                                    return;
                                }
                                r = this.requested.get();
                                e = 0;
                                for (TRight right2 : this.rights.values()) {
                                    try {
                                        w = ObjectHelper.requireNonNull(this.resultSelector.apply(left, right2), "The resultSelector returned a null value");
                                        if (e != r) {
                                            a.onNext(w);
                                            e++;
                                        } else {
                                            ExceptionHelper.addThrowable(this.error, new MissingBackpressureException("Could not emit value due to lack of requests"));
                                            q.clear();
                                            cancelAll();
                                            errorAll(a);
                                            return;
                                        }
                                    } catch (Throwable exc) {
                                        fail(exc, a, q);
                                        return;
                                    }
                                }
                                if (e != 0) {
                                    BackpressureHelper.produced(this.requested, e);
                                }
                            } catch (Throwable exc2) {
                                fail(exc2, a, q);
                                return;
                            }
                        } else if (mode == RIGHT_VALUE) {
                            right2 = val;
                            idx = this.rightIndex;
                            this.rightIndex = idx + 1;
                            this.rights.put(Integer.valueOf(idx), right2);
                            try {
                                Publisher<TRightEnd> p2 = (Publisher) ObjectHelper.requireNonNull(this.rightEnd.apply(right2), "The rightEnd returned a null Publisher");
                                end = new LeftRightEndSubscriber(this, false, idx);
                                this.disposables.add(end);
                                p2.subscribe(end);
                                if (((Throwable) this.error.get()) != null) {
                                    q.clear();
                                    cancelAll();
                                    errorAll(a);
                                    return;
                                }
                                r = this.requested.get();
                                e = 0;
                                for (TLeft left2 : this.lefts.values()) {
                                    try {
                                        w = ObjectHelper.requireNonNull(this.resultSelector.apply(left2, right2), "The resultSelector returned a null value");
                                        if (e != r) {
                                            a.onNext(w);
                                            e++;
                                        } else {
                                            ExceptionHelper.addThrowable(this.error, new MissingBackpressureException("Could not emit value due to lack of requests"));
                                            q.clear();
                                            cancelAll();
                                            errorAll(a);
                                            return;
                                        }
                                    } catch (Throwable exc22) {
                                        fail(exc22, a, q);
                                        return;
                                    }
                                }
                                if (e != 0) {
                                    BackpressureHelper.produced(this.requested, e);
                                }
                            } catch (Throwable exc222) {
                                fail(exc222, a, q);
                                return;
                            }
                        } else if (mode == LEFT_CLOSE) {
                            end = (LeftRightEndSubscriber) val;
                            this.lefts.remove(Integer.valueOf(end.index));
                            this.disposables.remove(end);
                        } else if (mode == RIGHT_CLOSE) {
                            end = (LeftRightEndSubscriber) val;
                            this.rights.remove(Integer.valueOf(end.index));
                            this.disposables.remove(end);
                        }
                    }
                }
                q.clear();
            }
        }

        public void innerError(Throwable ex) {
            if (ExceptionHelper.addThrowable(this.error, ex)) {
                this.active.decrementAndGet();
                drain();
                return;
            }
            RxJavaPlugins.onError(ex);
        }

        public void innerComplete(LeftRightSubscriber sender) {
            this.disposables.delete(sender);
            this.active.decrementAndGet();
            drain();
        }

        public void innerValue(boolean isLeft, Object o) {
            synchronized (this) {
                this.queue.offer(isLeft ? LEFT_VALUE : RIGHT_VALUE, o);
            }
            drain();
        }

        public void innerClose(boolean isLeft, LeftRightEndSubscriber index) {
            synchronized (this) {
                this.queue.offer(isLeft ? LEFT_CLOSE : RIGHT_CLOSE, index);
            }
            drain();
        }

        public void innerCloseError(Throwable ex) {
            if (ExceptionHelper.addThrowable(this.error, ex)) {
                drain();
            } else {
                RxJavaPlugins.onError(ex);
            }
        }
    }

    public FlowableJoin(Flowable<TLeft> source, Publisher<? extends TRight> other, Function<? super TLeft, ? extends Publisher<TLeftEnd>> leftEnd, Function<? super TRight, ? extends Publisher<TRightEnd>> rightEnd, BiFunction<? super TLeft, ? super TRight, ? extends R> resultSelector) {
        super(source);
        this.other = other;
        this.leftEnd = leftEnd;
        this.rightEnd = rightEnd;
        this.resultSelector = resultSelector;
    }

    protected void subscribeActual(Subscriber<? super R> s) {
        JoinSubscription<TLeft, TRight, TLeftEnd, TRightEnd, R> parent = new JoinSubscription(s, this.leftEnd, this.rightEnd, this.resultSelector);
        s.onSubscribe(parent);
        LeftRightSubscriber left = new LeftRightSubscriber(parent, true);
        parent.disposables.add(left);
        LeftRightSubscriber right = new LeftRightSubscriber(parent, false);
        parent.disposables.add(right);
        this.source.subscribe(left);
        this.other.subscribe(right);
    }
}
