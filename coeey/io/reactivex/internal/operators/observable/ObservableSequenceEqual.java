package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiPredicate;
import io.reactivex.internal.disposables.ArrayCompositeDisposable;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableSequenceEqual<T> extends Observable<Boolean> {
    final int bufferSize;
    final BiPredicate<? super T, ? super T> comparer;
    final ObservableSource<? extends T> first;
    final ObservableSource<? extends T> second;

    static final class EqualCoordinator<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = -6178010334400373240L;
        final Observer<? super Boolean> actual;
        volatile boolean cancelled;
        final BiPredicate<? super T, ? super T> comparer;
        final ObservableSource<? extends T> first;
        final EqualObserver<T>[] observers;
        final ArrayCompositeDisposable resources = new ArrayCompositeDisposable(2);
        final ObservableSource<? extends T> second;
        T v1;
        T v2;

        EqualCoordinator(Observer<? super Boolean> actual, int bufferSize, ObservableSource<? extends T> first, ObservableSource<? extends T> second, BiPredicate<? super T, ? super T> comparer) {
            this.actual = actual;
            this.first = first;
            this.second = second;
            this.comparer = comparer;
            as = new EqualObserver[2];
            this.observers = as;
            as[0] = new EqualObserver(this, 0, bufferSize);
            as[1] = new EqualObserver(this, 1, bufferSize);
        }

        boolean setDisposable(Disposable s, int index) {
            return this.resources.setResource(index, s);
        }

        void subscribe() {
            EqualObserver<T>[] as = this.observers;
            this.first.subscribe(as[0]);
            this.second.subscribe(as[1]);
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.resources.dispose();
                if (getAndIncrement() == 0) {
                    EqualObserver<T>[] as = this.observers;
                    as[0].queue.clear();
                    as[1].queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void cancel(SpscLinkedArrayQueue<T> q1, SpscLinkedArrayQueue<T> q2) {
            this.cancelled = true;
            q1.clear();
            q2.clear();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                int missed = 1;
                EqualObserver<T>[] as = this.observers;
                EqualObserver<T> s1 = as[0];
                SpscLinkedArrayQueue<T> q1 = s1.queue;
                EqualObserver<T> s2 = as[1];
                SpscLinkedArrayQueue<T> q2 = s2.queue;
                while (!this.cancelled) {
                    Throwable e;
                    boolean d1 = s1.done;
                    if (d1) {
                        e = s1.error;
                        if (e != null) {
                            cancel(q1, q2);
                            this.actual.onError(e);
                            return;
                        }
                    }
                    boolean d2 = s2.done;
                    if (d2) {
                        e = s2.error;
                        if (e != null) {
                            cancel(q1, q2);
                            this.actual.onError(e);
                            return;
                        }
                    }
                    if (this.v1 == null) {
                        this.v1 = q1.poll();
                    }
                    boolean e1 = this.v1 == null;
                    if (this.v2 == null) {
                        this.v2 = q2.poll();
                    }
                    boolean e2 = this.v2 == null;
                    if (d1 && d2 && e1 && e2) {
                        this.actual.onNext(Boolean.valueOf(true));
                        this.actual.onComplete();
                        return;
                    } else if (d1 && d2 && e1 != e2) {
                        cancel(q1, q2);
                        this.actual.onNext(Boolean.valueOf(false));
                        this.actual.onComplete();
                        return;
                    } else {
                        if (!(e1 || e2)) {
                            try {
                                if (this.comparer.test(this.v1, this.v2)) {
                                    this.v1 = null;
                                    this.v2 = null;
                                } else {
                                    cancel(q1, q2);
                                    this.actual.onNext(Boolean.valueOf(false));
                                    this.actual.onComplete();
                                    return;
                                }
                            } catch (Throwable ex) {
                                Exceptions.throwIfFatal(ex);
                                cancel(q1, q2);
                                this.actual.onError(ex);
                                return;
                            }
                        }
                        if (e1 || e2) {
                            missed = addAndGet(-missed);
                            if (missed == 0) {
                                return;
                            }
                        }
                    }
                }
                q1.clear();
                q2.clear();
            }
        }
    }

    static final class EqualObserver<T> implements Observer<T> {
        volatile boolean done;
        Throwable error;
        final int index;
        final EqualCoordinator<T> parent;
        final SpscLinkedArrayQueue<T> queue;

        EqualObserver(EqualCoordinator<T> parent, int index, int bufferSize) {
            this.parent = parent;
            this.index = index;
            this.queue = new SpscLinkedArrayQueue(bufferSize);
        }

        public void onSubscribe(Disposable s) {
            this.parent.setDisposable(s, this.index);
        }

        public void onNext(T t) {
            this.queue.offer(t);
            this.parent.drain();
        }

        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            this.parent.drain();
        }

        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }
    }

    public ObservableSequenceEqual(ObservableSource<? extends T> first, ObservableSource<? extends T> second, BiPredicate<? super T, ? super T> comparer, int bufferSize) {
        this.first = first;
        this.second = second;
        this.comparer = comparer;
        this.bufferSize = bufferSize;
    }

    public void subscribeActual(Observer<? super Boolean> s) {
        EqualCoordinator<T> ec = new EqualCoordinator(s, this.bufferSize, this.first, this.second, this.comparer);
        s.onSubscribe(ec);
        ec.subscribe();
    }
}
