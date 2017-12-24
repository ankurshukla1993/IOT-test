package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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
import io.reactivex.processors.UnicastProcessor;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableGroupJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AbstractFlowableWithUpstream<TLeft, R> {
    final Function<? super TLeft, ? extends Publisher<TLeftEnd>> leftEnd;
    final Publisher<? extends TRight> other;
    final BiFunction<? super TLeft, ? super Flowable<TRight>, ? extends R> resultSelector;
    final Function<? super TRight, ? extends Publisher<TRightEnd>> rightEnd;

    interface JoinSupport {
        void innerClose(boolean z, LeftRightEndSubscriber leftRightEndSubscriber);

        void innerCloseError(Throwable th);

        void innerComplete(LeftRightSubscriber leftRightSubscriber);

        void innerError(Throwable th);

        void innerValue(boolean z, Object obj);
    }

    static final class GroupJoinSubscription<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements Subscription, JoinSupport {
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
        final Map<Integer, UnicastProcessor<TRight>> lefts = new LinkedHashMap();
        final SpscLinkedArrayQueue<Object> queue = new SpscLinkedArrayQueue(Flowable.bufferSize());
        final AtomicLong requested = new AtomicLong();
        final BiFunction<? super TLeft, ? super Flowable<TRight>, ? extends R> resultSelector;
        final Function<? super TRight, ? extends Publisher<TRightEnd>> rightEnd;
        int rightIndex;
        final Map<Integer, TRight> rights = new LinkedHashMap();

        GroupJoinSubscription(Subscriber<? super R> actual, Function<? super TLeft, ? extends Publisher<TLeftEnd>> leftEnd, Function<? super TRight, ? extends Publisher<TRightEnd>> rightEnd, BiFunction<? super TLeft, ? super Flowable<TRight>, ? extends R> resultSelector) {
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
            for (UnicastProcessor<TRight> up : this.lefts.values()) {
                up.onError(ex);
            }
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
                        for (UnicastProcessor<?> up : this.lefts.values()) {
                            up.onComplete();
                        }
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
                        UnicastProcessor<TRight> up2;
                        int idx;
                        LeftRightEndSubscriber end;
                        TRight right;
                        if (mode == LEFT_VALUE) {
                            TLeft left = val;
                            up2 = UnicastProcessor.create();
                            idx = this.leftIndex;
                            this.leftIndex = idx + 1;
                            this.lefts.put(Integer.valueOf(idx), up2);
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
                                try {
                                    R w = ObjectHelper.requireNonNull(this.resultSelector.apply(left, up2), "The resultSelector returned a null value");
                                    if (this.requested.get() != 0) {
                                        a.onNext(w);
                                        BackpressureHelper.produced(this.requested, 1);
                                        for (TRight right2 : this.rights.values()) {
                                            up2.onNext(right2);
                                        }
                                    } else {
                                        fail(new MissingBackpressureException("Could not emit value due to lack of requests"), a, q);
                                        return;
                                    }
                                } catch (Throwable exc) {
                                    fail(exc, a, q);
                                    return;
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
                                for (UnicastProcessor<TRight> up22 : this.lefts.values()) {
                                    up22.onNext(right2);
                                }
                            } catch (Throwable exc22) {
                                fail(exc22, a, q);
                                return;
                            }
                        } else if (mode == LEFT_CLOSE) {
                            end = (LeftRightEndSubscriber) val;
                            up22 = (UnicastProcessor) this.lefts.remove(Integer.valueOf(end.index));
                            this.disposables.remove(end);
                            if (up22 != null) {
                                up22.onComplete();
                            }
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

    static final class LeftRightEndSubscriber extends AtomicReference<Subscription> implements FlowableSubscriber<Object>, Disposable {
        private static final long serialVersionUID = 1883890389173668373L;
        final int index;
        final boolean isLeft;
        final JoinSupport parent;

        LeftRightEndSubscriber(JoinSupport parent, boolean isLeft, int index) {
            this.parent = parent;
            this.isLeft = isLeft;
            this.index = index;
        }

        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled((Subscription) get());
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this, s)) {
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object t) {
            if (SubscriptionHelper.cancel(this)) {
                this.parent.innerClose(this.isLeft, this);
            }
        }

        public void onError(Throwable t) {
            this.parent.innerCloseError(t);
        }

        public void onComplete() {
            this.parent.innerClose(this.isLeft, this);
        }
    }

    static final class LeftRightSubscriber extends AtomicReference<Subscription> implements FlowableSubscriber<Object>, Disposable {
        private static final long serialVersionUID = 1883890389173668373L;
        final boolean isLeft;
        final JoinSupport parent;

        LeftRightSubscriber(JoinSupport parent, boolean isLeft) {
            this.parent = parent;
            this.isLeft = isLeft;
        }

        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled((Subscription) get());
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this, s)) {
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object t) {
            this.parent.innerValue(this.isLeft, t);
        }

        public void onError(Throwable t) {
            this.parent.innerError(t);
        }

        public void onComplete() {
            this.parent.innerComplete(this);
        }
    }

    public FlowableGroupJoin(Flowable<TLeft> source, Publisher<? extends TRight> other, Function<? super TLeft, ? extends Publisher<TLeftEnd>> leftEnd, Function<? super TRight, ? extends Publisher<TRightEnd>> rightEnd, BiFunction<? super TLeft, ? super Flowable<TRight>, ? extends R> resultSelector) {
        super(source);
        this.other = other;
        this.leftEnd = leftEnd;
        this.rightEnd = rightEnd;
        this.resultSelector = resultSelector;
    }

    protected void subscribeActual(Subscriber<? super R> s) {
        GroupJoinSubscription<TLeft, TRight, TLeftEnd, TRightEnd, R> parent = new GroupJoinSubscription(s, this.leftEnd, this.rightEnd, this.resultSelector);
        s.onSubscribe(parent);
        LeftRightSubscriber left = new LeftRightSubscriber(parent, true);
        parent.disposables.add(left);
        LeftRightSubscriber right = new LeftRightSubscriber(parent, false);
        parent.disposables.add(right);
        this.source.subscribe(left);
        this.other.subscribe(right);
    }
}
