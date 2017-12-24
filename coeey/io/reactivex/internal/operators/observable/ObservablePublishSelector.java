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
import io.reactivex.subjects.PublishSubject;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservablePublishSelector<T, R> extends AbstractObservableWithUpstream<T, R> {
    final Function<? super Observable<T>, ? extends ObservableSource<R>> selector;

    static final class SourceObserver<T, R> implements Observer<T> {
        final PublishSubject<T> subject;
        final AtomicReference<Disposable> target;

        SourceObserver(PublishSubject<T> subject, AtomicReference<Disposable> target) {
            this.subject = subject;
            this.target = target;
        }

        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this.target, d);
        }

        public void onNext(T value) {
            this.subject.onNext(value);
        }

        public void onError(Throwable e) {
            this.subject.onError(e);
        }

        public void onComplete() {
            this.subject.onComplete();
        }
    }

    static final class TargetObserver<T, R> extends AtomicReference<Disposable> implements Observer<R>, Disposable {
        private static final long serialVersionUID = 854110278590336484L;
        final Observer<? super R> actual;
        Disposable f2711d;

        TargetObserver(Observer<? super R> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2711d, d)) {
                this.f2711d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(R value) {
            this.actual.onNext(value);
        }

        public void onError(Throwable e) {
            DisposableHelper.dispose(this);
            this.actual.onError(e);
        }

        public void onComplete() {
            DisposableHelper.dispose(this);
            this.actual.onComplete();
        }

        public void dispose() {
            this.f2711d.dispose();
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return this.f2711d.isDisposed();
        }
    }

    public ObservablePublishSelector(ObservableSource<T> source, Function<? super Observable<T>, ? extends ObservableSource<R>> selector) {
        super(source);
        this.selector = selector;
    }

    protected void subscribeActual(Observer<? super R> observer) {
        PublishSubject<T> subject = PublishSubject.create();
        try {
            ObservableSource<? extends R> target = (ObservableSource) ObjectHelper.requireNonNull(this.selector.apply(subject), "The selector returned a null ObservableSource");
            TargetObserver<T, R> o = new TargetObserver(observer);
            target.subscribe(o);
            this.source.subscribe(new SourceObserver(subject, o));
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            EmptyDisposable.error(ex, (Observer) observer);
        }
    }
}
