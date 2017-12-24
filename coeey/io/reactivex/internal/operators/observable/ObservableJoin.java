package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AbstractObservableWithUpstream<TLeft, R> {
    final Function<? super TLeft, ? extends ObservableSource<TLeftEnd>> leftEnd;
    final ObservableSource<? extends TRight> other;
    final BiFunction<? super TLeft, ? super TRight, ? extends R> resultSelector;
    final Function<? super TRight, ? extends ObservableSource<TRightEnd>> rightEnd;

    static final class JoinDisposable<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements Disposable, JoinSupport {
        static final Integer LEFT_CLOSE = Integer.valueOf(3);
        static final Integer LEFT_VALUE = Integer.valueOf(1);
        static final Integer RIGHT_CLOSE = Integer.valueOf(4);
        static final Integer RIGHT_VALUE = Integer.valueOf(2);
        private static final long serialVersionUID = -6071216598687999801L;
        final AtomicInteger active;
        final Observer<? super R> actual;
        volatile boolean cancelled;
        final CompositeDisposable disposables = new CompositeDisposable();
        final AtomicReference<Throwable> error = new AtomicReference();
        final Function<? super TLeft, ? extends ObservableSource<TLeftEnd>> leftEnd;
        int leftIndex;
        final Map<Integer, TLeft> lefts = new LinkedHashMap();
        final SpscLinkedArrayQueue<Object> queue = new SpscLinkedArrayQueue(Observable.bufferSize());
        final BiFunction<? super TLeft, ? super TRight, ? extends R> resultSelector;
        final Function<? super TRight, ? extends ObservableSource<TRightEnd>> rightEnd;
        int rightIndex;
        final Map<Integer, TRight> rights = new LinkedHashMap();

        JoinDisposable(Observer<? super R> actual, Function<? super TLeft, ? extends ObservableSource<TLeftEnd>> leftEnd, Function<? super TRight, ? extends ObservableSource<TRightEnd>> rightEnd, BiFunction<? super TLeft, ? super TRight, ? extends R> resultSelector) {
            this.actual = actual;
            this.leftEnd = leftEnd;
            this.rightEnd = rightEnd;
            this.resultSelector = resultSelector;
            this.active = new AtomicInteger(2);
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void cancelAll() {
            this.disposables.dispose();
        }

        void errorAll(Observer<?> a) {
            Throwable ex = ExceptionHelper.terminate(this.error);
            this.lefts.clear();
            this.rights.clear();
            a.onError(ex);
        }

        void fail(Throwable exc, Observer<?> a, SpscLinkedArrayQueue<?> q) {
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
                Observer<? super R> a = this.actual;
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
                        LeftRightEndObserver end;
                        TRight right;
                        if (mode == LEFT_VALUE) {
                            left = val;
                            idx = this.leftIndex;
                            this.leftIndex = idx + 1;
                            this.lefts.put(Integer.valueOf(idx), left);
                            try {
                                ObservableSource<TLeftEnd> p = (ObservableSource) ObjectHelper.requireNonNull(this.leftEnd.apply(left), "The leftEnd returned a null ObservableSource");
                                end = new LeftRightEndObserver(this, true, idx);
                                this.disposables.add(end);
                                p.subscribe(end);
                                if (((Throwable) this.error.get()) != null) {
                                    q.clear();
                                    cancelAll();
                                    errorAll(a);
                                    return;
                                }
                                for (TRight right2 : this.rights.values()) {
                                    try {
                                        a.onNext(ObjectHelper.requireNonNull(this.resultSelector.apply(left, right2), "The resultSelector returned a null value"));
                                    } catch (Throwable exc) {
                                        fail(exc, a, q);
                                        return;
                                    }
                                }
                                continue;
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
                                ObservableSource<TRightEnd> p2 = (ObservableSource) ObjectHelper.requireNonNull(this.rightEnd.apply(right2), "The rightEnd returned a null ObservableSource");
                                end = new LeftRightEndObserver(this, false, idx);
                                this.disposables.add(end);
                                p2.subscribe(end);
                                if (((Throwable) this.error.get()) != null) {
                                    q.clear();
                                    cancelAll();
                                    errorAll(a);
                                    return;
                                }
                                for (TLeft left2 : this.lefts.values()) {
                                    try {
                                        a.onNext(ObjectHelper.requireNonNull(this.resultSelector.apply(left2, right2), "The resultSelector returned a null value"));
                                    } catch (Throwable exc22) {
                                        fail(exc22, a, q);
                                        return;
                                    }
                                }
                                continue;
                            } catch (Throwable exc222) {
                                fail(exc222, a, q);
                                return;
                            }
                        } else if (mode == LEFT_CLOSE) {
                            end = (LeftRightEndObserver) val;
                            this.lefts.remove(Integer.valueOf(end.index));
                            this.disposables.remove(end);
                        } else {
                            end = (LeftRightEndObserver) val;
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

        public void innerComplete(LeftRightObserver sender) {
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

        public void innerClose(boolean isLeft, LeftRightEndObserver index) {
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

    public ObservableJoin(ObservableSource<TLeft> source, ObservableSource<? extends TRight> other, Function<? super TLeft, ? extends ObservableSource<TLeftEnd>> leftEnd, Function<? super TRight, ? extends ObservableSource<TRightEnd>> rightEnd, BiFunction<? super TLeft, ? super TRight, ? extends R> resultSelector) {
        super(source);
        this.other = other;
        this.leftEnd = leftEnd;
        this.rightEnd = rightEnd;
        this.resultSelector = resultSelector;
    }

    protected void subscribeActual(Observer<? super R> s) {
        JoinDisposable<TLeft, TRight, TLeftEnd, TRightEnd, R> parent = new JoinDisposable(s, this.leftEnd, this.rightEnd, this.resultSelector);
        s.onSubscribe(parent);
        LeftRightObserver left = new LeftRightObserver(parent, true);
        parent.disposables.add(left);
        LeftRightObserver right = new LeftRightObserver(parent, false);
        parent.disposables.add(right);
        this.source.subscribe(left);
        this.other.subscribe(right);
    }
}
