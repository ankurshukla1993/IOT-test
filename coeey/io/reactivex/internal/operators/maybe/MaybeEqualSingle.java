package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiPredicate;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeEqualSingle<T> extends Single<Boolean> {
    final BiPredicate<? super T, ? super T> isEqual;
    final MaybeSource<? extends T> source1;
    final MaybeSource<? extends T> source2;

    static final class EqualCoordinator<T> extends AtomicInteger implements Disposable {
        final SingleObserver<? super Boolean> actual;
        final BiPredicate<? super T, ? super T> isEqual;
        final EqualObserver<T> observer1 = new EqualObserver(this);
        final EqualObserver<T> observer2 = new EqualObserver(this);

        EqualCoordinator(SingleObserver<? super Boolean> actual, BiPredicate<? super T, ? super T> isEqual) {
            super(2);
            this.actual = actual;
            this.isEqual = isEqual;
        }

        void subscribe(MaybeSource<? extends T> source1, MaybeSource<? extends T> source2) {
            source1.subscribe(this.observer1);
            source2.subscribe(this.observer2);
        }

        public void dispose() {
            this.observer1.dispose();
            this.observer2.dispose();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) this.observer1.get());
        }

        void done() {
            if (decrementAndGet() == 0) {
                Object o1 = this.observer1.value;
                Object o2 = this.observer2.value;
                if (o1 == null || o2 == null) {
                    SingleObserver singleObserver = this.actual;
                    boolean z = o1 == null && o2 == null;
                    singleObserver.onSuccess(Boolean.valueOf(z));
                    return;
                }
                try {
                    this.actual.onSuccess(Boolean.valueOf(this.isEqual.test(o1, o2)));
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    this.actual.onError(ex);
                }
            }
        }

        void error(EqualObserver<T> sender, Throwable ex) {
            if (getAndSet(0) > 0) {
                if (sender == this.observer1) {
                    this.observer2.dispose();
                } else {
                    this.observer1.dispose();
                }
                this.actual.onError(ex);
                return;
            }
            RxJavaPlugins.onError(ex);
        }
    }

    static final class EqualObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
        private static final long serialVersionUID = -3031974433025990931L;
        final EqualCoordinator<T> parent;
        Object value;

        EqualObserver(EqualCoordinator<T> parent) {
            this.parent = parent;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this, d);
        }

        public void onSuccess(T value) {
            this.value = value;
            this.parent.done();
        }

        public void onError(Throwable e) {
            this.parent.error(this, e);
        }

        public void onComplete() {
            this.parent.done();
        }
    }

    public MaybeEqualSingle(MaybeSource<? extends T> source1, MaybeSource<? extends T> source2, BiPredicate<? super T, ? super T> isEqual) {
        this.source1 = source1;
        this.source2 = source2;
        this.isEqual = isEqual;
    }

    protected void subscribeActual(SingleObserver<? super Boolean> observer) {
        EqualCoordinator<T> parent = new EqualCoordinator(observer, this.isEqual);
        observer.onSubscribe(parent);
        parent.subscribe(this.source1, this.source2);
    }
}
