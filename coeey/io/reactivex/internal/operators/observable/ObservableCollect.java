package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;

public final class ObservableCollect<T, U> extends AbstractObservableWithUpstream<T, U> {
    final BiConsumer<? super U, ? super T> collector;
    final Callable<? extends U> initialSupplier;

    static final class CollectObserver<T, U> implements Observer<T>, Disposable {
        final Observer<? super U> actual;
        final BiConsumer<? super U, ? super T> collector;
        boolean done;
        Disposable f2670s;
        final U f2671u;

        CollectObserver(Observer<? super U> actual, U u, BiConsumer<? super U, ? super T> collector) {
            this.actual = actual;
            this.collector = collector;
            this.f2671u = u;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2670s, s)) {
                this.f2670s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2670s.dispose();
        }

        public boolean isDisposed() {
            return this.f2670s.isDisposed();
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    this.collector.accept(this.f2671u, t);
                } catch (Throwable e) {
                    this.f2670s.dispose();
                    onError(e);
                }
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.actual.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onNext(this.f2671u);
                this.actual.onComplete();
            }
        }
    }

    public ObservableCollect(ObservableSource<T> source, Callable<? extends U> initialSupplier, BiConsumer<? super U, ? super T> collector) {
        super(source);
        this.initialSupplier = initialSupplier;
        this.collector = collector;
    }

    protected void subscribeActual(Observer<? super U> t) {
        try {
            this.source.subscribe(new CollectObserver(t, ObjectHelper.requireNonNull(this.initialSupplier.call(), "The initialSupplier returned a null value"), this.collector));
        } catch (Throwable e) {
            EmptyDisposable.error(e, (Observer) t);
        }
    }
}
