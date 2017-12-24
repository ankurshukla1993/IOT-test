package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.FuseToObservable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Collection;
import java.util.concurrent.Callable;

public final class ObservableToListSingle<T, U extends Collection<? super T>> extends Single<U> implements FuseToObservable<U> {
    final Callable<U> collectionSupplier;
    final ObservableSource<T> source;

    static final class ToListObserver<T, U extends Collection<? super T>> implements Observer<T>, Disposable {
        final SingleObserver<? super U> actual;
        U collection;
        Disposable f2744s;

        ToListObserver(SingleObserver<? super U> actual, U collection) {
            this.actual = actual;
            this.collection = collection;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2744s, s)) {
                this.f2744s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2744s.dispose();
        }

        public boolean isDisposed() {
            return this.f2744s.isDisposed();
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
            this.actual.onSuccess(c);
        }
    }

    public ObservableToListSingle(ObservableSource<T> source, int defaultCapacityHint) {
        this.source = source;
        this.collectionSupplier = Functions.createArrayList(defaultCapacityHint);
    }

    public ObservableToListSingle(ObservableSource<T> source, Callable<U> collectionSupplier) {
        this.source = source;
        this.collectionSupplier = collectionSupplier;
    }

    public void subscribeActual(SingleObserver<? super U> t) {
        try {
            this.source.subscribe(new ToListObserver(t, (Collection) ObjectHelper.requireNonNull(this.collectionSupplier.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            EmptyDisposable.error(e, (SingleObserver) t);
        }
    }

    public Observable<U> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableToList(this.source, this.collectionSupplier));
    }
}
