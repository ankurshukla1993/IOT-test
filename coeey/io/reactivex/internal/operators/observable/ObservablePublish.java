package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.HasUpstreamObservableSource;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservablePublish<T> extends ConnectableObservable<T> implements HasUpstreamObservableSource<T> {
    final AtomicReference<PublishObserver<T>> current;
    final ObservableSource<T> onSubscribe;
    final ObservableSource<T> source;

    static final class InnerDisposable<T> extends AtomicReference<Object> implements Disposable {
        private static final long serialVersionUID = -1100270633763673112L;
        final Observer<? super T> child;

        InnerDisposable(Observer<? super T> child) {
            this.child = child;
        }

        public boolean isDisposed() {
            return get() == this;
        }

        public void dispose() {
            InnerDisposable o = getAndSet(this);
            if (o != null && o != this) {
                ((PublishObserver) o).remove(this);
            }
        }

        void setParent(PublishObserver<T> p) {
            if (!compareAndSet(null, p)) {
                p.remove(this);
            }
        }
    }

    static final class PublishObserver<T> implements Observer<T>, Disposable {
        static final InnerDisposable[] EMPTY = new InnerDisposable[0];
        static final InnerDisposable[] TERMINATED = new InnerDisposable[0];
        final AtomicReference<PublishObserver<T>> current;
        final AtomicReference<InnerDisposable<T>[]> observers = new AtomicReference(EMPTY);
        final AtomicReference<Disposable> f2710s = new AtomicReference();
        final AtomicBoolean shouldConnect;

        PublishObserver(AtomicReference<PublishObserver<T>> current) {
            this.current = current;
            this.shouldConnect = new AtomicBoolean();
        }

        public void dispose() {
            if (this.observers.get() != TERMINATED && ((InnerDisposable[]) this.observers.getAndSet(TERMINATED)) != TERMINATED) {
                this.current.compareAndSet(this, null);
                DisposableHelper.dispose(this.f2710s);
            }
        }

        public boolean isDisposed() {
            return this.observers.get() == TERMINATED;
        }

        public void onSubscribe(Disposable s) {
            DisposableHelper.setOnce(this.f2710s, s);
        }

        public void onNext(T t) {
            for (InnerDisposable<T> inner : (InnerDisposable[]) this.observers.get()) {
                inner.child.onNext(t);
            }
        }

        public void onError(Throwable e) {
            this.current.compareAndSet(this, null);
            InnerDisposable[] a = (InnerDisposable[]) this.observers.getAndSet(TERMINATED);
            if (a.length != 0) {
                for (InnerDisposable<T> inner : a) {
                    inner.child.onError(e);
                }
                return;
            }
            RxJavaPlugins.onError(e);
        }

        public void onComplete() {
            this.current.compareAndSet(this, null);
            for (InnerDisposable<T> inner : (InnerDisposable[]) this.observers.getAndSet(TERMINATED)) {
                inner.child.onComplete();
            }
        }

        boolean add(InnerDisposable<T> producer) {
            InnerDisposable[] c;
            InnerDisposable<T>[] u;
            do {
                c = (InnerDisposable[]) this.observers.get();
                if (c == TERMINATED) {
                    return false;
                }
                int len = c.length;
                u = new InnerDisposable[(len + 1)];
                System.arraycopy(c, 0, u, 0, len);
                u[len] = producer;
            } while (!this.observers.compareAndSet(c, u));
            return true;
        }

        void remove(InnerDisposable<T> producer) {
            InnerDisposable[] c;
            InnerDisposable<T>[] u;
            do {
                c = (InnerDisposable[]) this.observers.get();
                int len = c.length;
                if (len != 0) {
                    int j = -1;
                    for (int i = 0; i < len; i++) {
                        if (c[i].equals(producer)) {
                            j = i;
                            break;
                        }
                    }
                    if (j < 0) {
                        return;
                    }
                    if (len == 1) {
                        u = EMPTY;
                    } else {
                        u = new InnerDisposable[(len - 1)];
                        System.arraycopy(c, 0, u, 0, j);
                        System.arraycopy(c, j + 1, u, j, (len - j) - 1);
                    }
                } else {
                    return;
                }
            } while (!this.observers.compareAndSet(c, u));
        }
    }

    static final class PublishSource<T> implements ObservableSource<T> {
        private final AtomicReference<PublishObserver<T>> curr;

        PublishSource(AtomicReference<PublishObserver<T>> curr) {
            this.curr = curr;
        }

        public void subscribe(Observer<? super T> child) {
            InnerDisposable<T> inner = new InnerDisposable(child);
            child.onSubscribe(inner);
            while (true) {
                PublishObserver<T> r = (PublishObserver) this.curr.get();
                if (r == null || r.isDisposed()) {
                    PublishObserver<T> u = new PublishObserver(this.curr);
                    if (this.curr.compareAndSet(r, u)) {
                        r = u;
                    } else {
                        continue;
                    }
                }
                if (r.add(inner)) {
                    inner.setParent(r);
                    return;
                }
            }
        }
    }

    public static <T> ConnectableObservable<T> create(ObservableSource<T> source) {
        AtomicReference<PublishObserver<T>> curr = new AtomicReference();
        return RxJavaPlugins.onAssembly(new ObservablePublish(new PublishSource(curr), source, curr));
    }

    private ObservablePublish(ObservableSource<T> onSubscribe, ObservableSource<T> source, AtomicReference<PublishObserver<T>> current) {
        this.onSubscribe = onSubscribe;
        this.source = source;
        this.current = current;
    }

    public ObservableSource<T> source() {
        return this.source;
    }

    protected void subscribeActual(Observer<? super T> observer) {
        this.onSubscribe.subscribe(observer);
    }

    public void connect(Consumer<? super Disposable> connection) {
        PublishObserver<T> ps;
        PublishObserver<T> u;
        boolean doConnect = true;
        do {
            ps = (PublishObserver) this.current.get();
            if (ps != null && !ps.isDisposed()) {
                break;
            }
            u = new PublishObserver(this.current);
        } while (!this.current.compareAndSet(ps, u));
        ps = u;
        if (ps.shouldConnect.get() || !ps.shouldConnect.compareAndSet(false, true)) {
            doConnect = false;
        }
        try {
            connection.accept(ps);
            if (doConnect) {
                this.source.subscribe(ps);
            }
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            RuntimeException wrapOrThrow = ExceptionHelper.wrapOrThrow(ex);
        }
    }
}
