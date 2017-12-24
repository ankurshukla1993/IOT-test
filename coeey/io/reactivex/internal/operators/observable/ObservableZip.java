package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableZip<T, R> extends Observable<R> {
    final int bufferSize;
    final boolean delayError;
    final ObservableSource<? extends T>[] sources;
    final Iterable<? extends ObservableSource<? extends T>> sourcesIterable;
    final Function<? super Object[], ? extends R> zipper;

    static final class ZipCoordinator<T, R> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 2983708048395377667L;
        final Observer<? super R> actual;
        volatile boolean cancelled;
        final boolean delayError;
        final ZipObserver<T, R>[] observers;
        final T[] row;
        final Function<? super Object[], ? extends R> zipper;

        ZipCoordinator(Observer<? super R> actual, Function<? super Object[], ? extends R> zipper, int count, boolean delayError) {
            this.actual = actual;
            this.zipper = zipper;
            this.observers = new ZipObserver[count];
            this.row = new Object[count];
            this.delayError = delayError;
        }

        public void subscribe(ObservableSource<? extends T>[] sources, int bufferSize) {
            int i;
            ZipObserver<T, R>[] s = this.observers;
            int len = s.length;
            for (i = 0; i < len; i++) {
                s[i] = new ZipObserver(this, bufferSize);
            }
            lazySet(0);
            this.actual.onSubscribe(this);
            for (i = 0; i < len && !this.cancelled; i++) {
                sources[i].subscribe(s[i]);
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelSources();
                if (getAndIncrement() == 0) {
                    clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void cancel() {
            clear();
            cancelSources();
        }

        void cancelSources() {
            for (ZipObserver<?, ?> zs : this.observers) {
                zs.dispose();
            }
        }

        void clear() {
            for (ZipObserver<?, ?> zs : this.observers) {
                zs.queue.clear();
            }
        }

        public void drain() {
            Throwable ex;
            if (getAndIncrement() == 0) {
                int missing = 1;
                ZipObserver<T, R>[] zs = this.observers;
                Observer<? super R> a = this.actual;
                T[] os = this.row;
                boolean delayError = this.delayError;
                while (true) {
                    int i = 0;
                    int emptyCount = 0;
                    for (ZipObserver<T, R> z : zs) {
                        if (os[i] == null) {
                            boolean d = z.done;
                            T v = z.queue.poll();
                            boolean empty = v == null;
                            if (!checkTerminated(d, empty, a, delayError, z)) {
                                if (empty) {
                                    emptyCount++;
                                } else {
                                    os[i] = v;
                                }
                            } else {
                                return;
                            }
                        } else if (z.done && !delayError) {
                            ex = z.error;
                            if (ex != null) {
                                cancel();
                                a.onError(ex);
                                return;
                            }
                        }
                        i++;
                    }
                    if (emptyCount != 0) {
                        missing = addAndGet(-missing);
                        if (missing == 0) {
                            return;
                        }
                    } else {
                        try {
                            a.onNext(ObjectHelper.requireNonNull(this.zipper.apply(os.clone()), "The zipper returned a null value"));
                            Arrays.fill(os, null);
                        } catch (Throwable ex2) {
                            Exceptions.throwIfFatal(ex2);
                            cancel();
                            a.onError(ex2);
                            return;
                        }
                    }
                }
            }
        }

        boolean checkTerminated(boolean d, boolean empty, Observer<? super R> a, boolean delayError, ZipObserver<?, ?> source) {
            if (this.cancelled) {
                cancel();
                return true;
            }
            if (d) {
                Throwable e;
                if (!delayError) {
                    e = source.error;
                    if (e != null) {
                        cancel();
                        a.onError(e);
                        return true;
                    } else if (empty) {
                        cancel();
                        a.onComplete();
                        return true;
                    }
                } else if (empty) {
                    e = source.error;
                    cancel();
                    if (e != null) {
                        a.onError(e);
                        return true;
                    }
                    a.onComplete();
                    return true;
                }
            }
            return false;
        }
    }

    static final class ZipObserver<T, R> implements Observer<T> {
        volatile boolean done;
        Throwable error;
        final ZipCoordinator<T, R> parent;
        final SpscLinkedArrayQueue<T> queue;
        final AtomicReference<Disposable> f2761s = new AtomicReference();

        ZipObserver(ZipCoordinator<T, R> parent, int bufferSize) {
            this.parent = parent;
            this.queue = new SpscLinkedArrayQueue(bufferSize);
        }

        public void onSubscribe(Disposable s) {
            DisposableHelper.setOnce(this.f2761s, s);
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

        public void dispose() {
            DisposableHelper.dispose(this.f2761s);
        }
    }

    public ObservableZip(ObservableSource<? extends T>[] sources, Iterable<? extends ObservableSource<? extends T>> sourcesIterable, Function<? super Object[], ? extends R> zipper, int bufferSize, boolean delayError) {
        this.sources = sources;
        this.sourcesIterable = sourcesIterable;
        this.zipper = zipper;
        this.bufferSize = bufferSize;
        this.delayError = delayError;
    }

    public void subscribeActual(Observer<? super R> s) {
        ObservableSource<? extends T>[] sources = this.sources;
        int count = 0;
        if (sources == null) {
            sources = new Observable[8];
            for (ObservableSource<? extends T> p : this.sourcesIterable) {
                if (count == sources.length) {
                    ObservableSource<? extends T>[] b = new ObservableSource[((count >> 2) + count)];
                    System.arraycopy(sources, 0, b, 0, count);
                    sources = b;
                }
                int count2 = count + 1;
                sources[count] = p;
                count = count2;
            }
        } else {
            count = sources.length;
        }
        if (count == 0) {
            EmptyDisposable.complete((Observer) s);
        } else {
            new ZipCoordinator(s, this.zipper, count, this.delayError).subscribe(sources, this.bufferSize);
        }
    }
}
