package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.Collection;
import java.util.concurrent.Callable;

public final class ObservableToList<T, U extends Collection<? super T>> extends AbstractObservableWithUpstream<T, U> {
    final Callable<U> collectionSupplier;

    static final class ToListObserver<T, U extends Collection<? super T>> implements Observer<T>, Disposable {
        final Observer<? super U> actual;
        U collection;
        Disposable f2743s;

        ToListObserver(Observer<? super U> actual, U collection) {
            this.actual = actual;
            this.collection = collection;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2743s, s)) {
                this.f2743s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2743s.dispose();
        }

        public boolean isDisposed() {
            return this.f2743s.isDisposed();
        }

        public void onNext(T t) {
            this.collection.add(t);
        }

        public void onError(Throwable t) {
            this.collection = null;
            this.actual.onError(t);
        }

        public void onComplete() {
            U c = this.collection;
            this.collection = null;
            this.actual.onNext(c);
            this.actual.onComplete();
        }
    }

    public ObservableToList(ObservableSource<T> source, int defaultCapacityHint) {
        super(source);
        this.collectionSupplier = Functions.createArrayList(defaultCapacityHint);
    }

    public ObservableToList(ObservableSource<T> source, Callable<U> collectionSupplier) {
        super(source);
        this.collectionSupplier = collectionSupplier;
    }

    public void subscribeActual(Observer<? super U> t) {
        try {
            this.source.subscribe(new ToListObserver(t, (Collection) ObjectHelper.requireNonNull(this.collectionSupplier.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            EmptyDisposable.error(e, (Observer) t);
        }
    }
}
