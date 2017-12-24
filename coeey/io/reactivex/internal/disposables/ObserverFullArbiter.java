package io.reactivex.internal.disposables;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObserverFullArbiter<T> extends FullArbiterPad1 implements Disposable {
    final Observer<? super T> actual;
    volatile boolean cancelled;
    final SpscLinkedArrayQueue<Object> queue;
    Disposable resource;
    volatile Disposable f2471s = EmptyDisposable.INSTANCE;

    public ObserverFullArbiter(Observer<? super T> actual, Disposable resource, int capacity) {
        this.actual = actual;
        this.resource = resource;
        this.queue = new SpscLinkedArrayQueue(capacity);
    }

    public void dispose() {
        if (!this.cancelled) {
            this.cancelled = true;
            disposeResource();
        }
    }

    public boolean isDisposed() {
        Disposable d = this.resource;
        return d != null ? d.isDisposed() : this.cancelled;
    }

    void disposeResource() {
        Disposable d = this.resource;
        this.resource = null;
        if (d != null) {
            d.dispose();
        }
    }

    public boolean setDisposable(Disposable s) {
        if (this.cancelled) {
            return false;
        }
        this.queue.offer(this.f2471s, NotificationLite.disposable(s));
        drain();
        return true;
    }

    public boolean onNext(T value, Disposable s) {
        if (this.cancelled) {
            return false;
        }
        this.queue.offer(s, NotificationLite.next(value));
        drain();
        return true;
    }

    public void onError(Throwable value, Disposable s) {
        if (this.cancelled) {
            RxJavaPlugins.onError(value);
            return;
        }
        this.queue.offer(s, NotificationLite.error(value));
        drain();
    }

    public void onComplete(Disposable s) {
        this.queue.offer(s, NotificationLite.complete());
        drain();
    }

    void drain() {
        if (this.wip.getAndIncrement() == 0) {
            int missed = 1;
            SpscLinkedArrayQueue<Object> q = this.queue;
            Observer<? super T> a = this.actual;
            while (true) {
                Disposable o = q.poll();
                if (o == null) {
                    missed = this.wip.addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                } else {
                    Object v = q.poll();
                    if (o == this.f2471s) {
                        if (NotificationLite.isDisposable(v)) {
                            Disposable next = NotificationLite.getDisposable(v);
                            this.f2471s.dispose();
                            if (this.cancelled) {
                                next.dispose();
                            } else {
                                this.f2471s = next;
                            }
                        } else if (NotificationLite.isError(v)) {
                            q.clear();
                            disposeResource();
                            Throwable ex = NotificationLite.getError(v);
                            if (this.cancelled) {
                                RxJavaPlugins.onError(ex);
                            } else {
                                this.cancelled = true;
                                a.onError(ex);
                            }
                        } else if (NotificationLite.isComplete(v)) {
                            q.clear();
                            disposeResource();
                            if (!this.cancelled) {
                                this.cancelled = true;
                                a.onComplete();
                            }
                        } else {
                            a.onNext(NotificationLite.getValue(v));
                        }
                    }
                }
            }
        }
    }
}
