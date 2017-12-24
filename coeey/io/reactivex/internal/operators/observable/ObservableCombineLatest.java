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
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableCombineLatest<T, R> extends Observable<R> {
    final int bufferSize;
    final Function<? super Object[], ? extends R> combiner;
    final boolean delayError;
    final ObservableSource<? extends T>[] sources;
    final Iterable<? extends ObservableSource<? extends T>> sourcesIterable;

    static final class CombinerObserver<T, R> implements Observer<T> {
        final int index;
        final LatestCoordinator<T, R> parent;
        final AtomicReference<Disposable> f2674s = new AtomicReference();

        CombinerObserver(LatestCoordinator<T, R> parent, int index) {
            this.parent = parent;
            this.index = index;
        }

        public void onSubscribe(Disposable s) {
            DisposableHelper.setOnce(this.f2674s, s);
        }

        public void onNext(T t) {
            this.parent.combine(t, this.index);
        }

        public void onError(Throwable t) {
            this.parent.onError(t);
            this.parent.combine(null, this.index);
        }

        public void onComplete() {
            this.parent.combine(null, this.index);
        }

        public void dispose() {
            DisposableHelper.dispose(this.f2674s);
        }
    }

    static final class LatestCoordinator<T, R> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 8567835998786448817L;
        int active;
        final Observer<? super R> actual;
        volatile boolean cancelled;
        final Function<? super Object[], ? extends R> combiner;
        int complete;
        final boolean delayError;
        volatile boolean done;
        final AtomicThrowable errors = new AtomicThrowable();
        final T[] latest;
        final CombinerObserver<T, R>[] observers;
        final SpscLinkedArrayQueue<Object> queue;

        LatestCoordinator(Observer<? super R> actual, Function<? super Object[], ? extends R> combiner, int count, int bufferSize, boolean delayError) {
            this.actual = actual;
            this.combiner = combiner;
            this.delayError = delayError;
            this.latest = new Object[count];
            this.observers = new CombinerObserver[count];
            this.queue = new SpscLinkedArrayQueue(bufferSize);
        }

        public void subscribe(ObservableSource<? extends T>[] sources) {
            int i;
            Observer<T>[] as = this.observers;
            int len = as.length;
            for (i = 0; i < len; i++) {
                as[i] = new CombinerObserver(this, i);
            }
            lazySet(0);
            this.actual.onSubscribe(this);
            for (i = 0; i < len && !this.done && !this.cancelled; i++) {
                sources[i].subscribe(as[i]);
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelSources();
                if (getAndIncrement() == 0) {
                    clear(this.queue);
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void cancel(SpscLinkedArrayQueue<?> q) {
            clear(q);
            cancelSources();
        }

        void cancelSources() {
            for (CombinerObserver<T, R> s : this.observers) {
                s.dispose();
            }
        }

        void clear(SpscLinkedArrayQueue<?> q) {
            synchronized (this) {
                Arrays.fill(this.latest, null);
            }
            q.clear();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void combine(T r10, int r11) {
            /*
            r9 = this;
            r3 = 0;
            r7 = 1;
            r8 = r9.observers;
            r2 = r8[r11];
            monitor-enter(r9);
            r8 = r9.cancelled;	 Catch:{ all -> 0x004d }
            if (r8 == 0) goto L_0x000d;
        L_0x000b:
            monitor-exit(r9);	 Catch:{ all -> 0x004d }
        L_0x000c:
            return;
        L_0x000d:
            r8 = r9.latest;	 Catch:{ all -> 0x004d }
            r5 = r8.length;	 Catch:{ all -> 0x004d }
            r8 = r9.latest;	 Catch:{ all -> 0x004d }
            r6 = r8[r11];	 Catch:{ all -> 0x004d }
            r0 = r9.active;	 Catch:{ all -> 0x004d }
            if (r6 != 0) goto L_0x001c;
        L_0x0018:
            r0 = r0 + 1;
            r9.active = r0;	 Catch:{ all -> 0x004d }
        L_0x001c:
            r1 = r9.complete;	 Catch:{ all -> 0x004d }
            if (r10 != 0) goto L_0x0048;
        L_0x0020:
            r1 = r1 + 1;
            r9.complete = r1;	 Catch:{ all -> 0x004d }
        L_0x0024:
            if (r0 != r5) goto L_0x0050;
        L_0x0026:
            r4 = r7;
        L_0x0027:
            if (r1 == r5) goto L_0x002d;
        L_0x0029:
            if (r10 != 0) goto L_0x002e;
        L_0x002b:
            if (r6 != 0) goto L_0x002e;
        L_0x002d:
            r3 = r7;
        L_0x002e:
            if (r3 != 0) goto L_0x0060;
        L_0x0030:
            if (r10 == 0) goto L_0x0052;
        L_0x0032:
            if (r4 == 0) goto L_0x0052;
        L_0x0034:
            r7 = r9.queue;	 Catch:{ all -> 0x004d }
            r8 = r9.latest;	 Catch:{ all -> 0x004d }
            r8 = r8.clone();	 Catch:{ all -> 0x004d }
            r7.offer(r2, r8);	 Catch:{ all -> 0x004d }
        L_0x003f:
            monitor-exit(r9);	 Catch:{ all -> 0x004d }
            if (r4 != 0) goto L_0x0044;
        L_0x0042:
            if (r10 != 0) goto L_0x000c;
        L_0x0044:
            r9.drain();
            goto L_0x000c;
        L_0x0048:
            r8 = r9.latest;	 Catch:{ all -> 0x004d }
            r8[r11] = r10;	 Catch:{ all -> 0x004d }
            goto L_0x0024;
        L_0x004d:
            r7 = move-exception;
            monitor-exit(r9);	 Catch:{ all -> 0x004d }
            throw r7;
        L_0x0050:
            r4 = r3;
            goto L_0x0027;
        L_0x0052:
            if (r10 != 0) goto L_0x003f;
        L_0x0054:
            r7 = r9.errors;	 Catch:{ all -> 0x004d }
            r7 = r7.get();	 Catch:{ all -> 0x004d }
            if (r7 == 0) goto L_0x003f;
        L_0x005c:
            r7 = 1;
            r9.done = r7;	 Catch:{ all -> 0x004d }
            goto L_0x003f;
        L_0x0060:
            r7 = 1;
            r9.done = r7;	 Catch:{ all -> 0x004d }
            goto L_0x003f;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableCombineLatest.LatestCoordinator.combine(java.lang.Object, int):void");
        }

        void drain() {
            if (getAndIncrement() == 0) {
                SpscLinkedArrayQueue<Object> q = this.queue;
                Observer<? super R> a = this.actual;
                boolean delayError = this.delayError;
                int missed = 1;
                while (!checkTerminated(this.done, q.isEmpty(), a, q, delayError)) {
                    while (true) {
                        boolean d = this.done;
                        boolean empty = ((CombinerObserver) q.poll()) == null;
                        if (!checkTerminated(d, empty, a, q, delayError)) {
                            if (empty) {
                                missed = addAndGet(-missed);
                                if (missed == 0) {
                                    return;
                                }
                            }
                            try {
                                a.onNext(ObjectHelper.requireNonNull(this.combiner.apply((Object[]) ((Object[]) q.poll())), "The combiner returned a null"));
                            } catch (Throwable ex) {
                                Exceptions.throwIfFatal(ex);
                                this.cancelled = true;
                                cancel(q);
                                a.onError(ex);
                                return;
                            }
                        }
                        return;
                    }
                }
            }
        }

        boolean checkTerminated(boolean d, boolean empty, Observer<?> a, SpscLinkedArrayQueue<?> q, boolean delayError) {
            if (this.cancelled) {
                cancel(q);
                return true;
            }
            if (d) {
                if (delayError) {
                    if (empty) {
                        cancel(q);
                        Throwable e = this.errors.terminate();
                        if (e != null) {
                            a.onError(e);
                            return true;
                        }
                        a.onComplete();
                        return true;
                    }
                } else if (((Throwable) this.errors.get()) != null) {
                    cancel(q);
                    a.onError(this.errors.terminate());
                    return true;
                } else if (empty) {
                    clear(this.queue);
                    a.onComplete();
                    return true;
                }
            }
            return false;
        }

        void onError(Throwable e) {
            if (!this.errors.addThrowable(e)) {
                RxJavaPlugins.onError(e);
            }
        }
    }

    public ObservableCombineLatest(ObservableSource<? extends T>[] sources, Iterable<? extends ObservableSource<? extends T>> sourcesIterable, Function<? super Object[], ? extends R> combiner, int bufferSize, boolean delayError) {
        this.sources = sources;
        this.sourcesIterable = sourcesIterable;
        this.combiner = combiner;
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
            return;
        }
        new LatestCoordinator(s, this.combiner, count, this.bufferSize, this.delayError).subscribe(sources);
    }
}
