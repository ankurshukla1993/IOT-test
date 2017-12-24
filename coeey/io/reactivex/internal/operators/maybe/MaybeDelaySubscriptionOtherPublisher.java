package io.reactivex.internal.operators.maybe;

import io.reactivex.FlowableSubscriber;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

public final class MaybeDelaySubscriptionOtherPublisher<T, U> extends AbstractMaybeWithUpstream<T, T> {
    final Publisher<U> other;

    static final class DelayMaybeObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
        private static final long serialVersionUID = 706635022205076709L;
        final MaybeObserver<? super T> actual;

        DelayMaybeObserver(MaybeObserver<? super T> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this, d);
        }

        public void onSuccess(T value) {
            this.actual.onSuccess(value);
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    static final class OtherSubscriber<T> implements FlowableSubscriber<Object>, Disposable {
        final DelayMaybeObserver<T> main;
        Subscription f2626s;
        MaybeSource<T> source;

        OtherSubscriber(MaybeObserver<? super T> actual, MaybeSource<T> source) {
            this.main = new DelayMaybeObserver(actual);
            this.source = source;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2626s, s)) {
                this.f2626s = s;
                this.main.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object t) {
            if (this.f2626s != SubscriptionHelper.CANCELLED) {
                this.f2626s.cancel();
                this.f2626s = SubscriptionHelper.CANCELLED;
                subscribeNext();
            }
        }

        public void onError(Throwable t) {
            if (this.f2626s != SubscriptionHelper.CANCELLED) {
                this.f2626s = SubscriptionHelper.CANCELLED;
                this.main.actual.onError(t);
                return;
            }
            RxJavaPlugins.onError(t);
        }

        public void onComplete() {
            if (this.f2626s != SubscriptionHelper.CANCELLED) {
                this.f2626s = SubscriptionHelper.CANCELLED;
                subscribeNext();
            }
        }

        void subscribeNext() {
            MaybeSource<T> src = this.source;
            this.source = null;
            src.subscribe(this.main);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) this.main.get());
        }

        public void dispose() {
            this.f2626s.cancel();
            this.f2626s = SubscriptionHelper.CANCELLED;
            DisposableHelper.dispose(this.main);
        }
    }

    public MaybeDelaySubscriptionOtherPublisher(MaybeSource<T> source, Publisher<U> other) {
        super(source);
        this.other = other;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.other.subscribe(new OtherSubscriber(observer, this.source));
    }
}
