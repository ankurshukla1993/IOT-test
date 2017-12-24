package io.reactivex.internal.operators.maybe;

import io.reactivex.FlowableSubscriber;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

public final class MaybeDelayOtherPublisher<T, U> extends AbstractMaybeWithUpstream<T, T> {
    final Publisher<U> other;

    static final class DelayMaybeObserver<T, U> implements MaybeObserver<T>, Disposable {
        Disposable f2625d;
        final OtherSubscriber<T> other;
        final Publisher<U> otherSource;

        DelayMaybeObserver(MaybeObserver<? super T> actual, Publisher<U> otherSource) {
            this.other = new OtherSubscriber(actual);
            this.otherSource = otherSource;
        }

        public void dispose() {
            this.f2625d.dispose();
            this.f2625d = DisposableHelper.DISPOSED;
            SubscriptionHelper.cancel(this.other);
        }

        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled((Subscription) this.other.get());
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2625d, d)) {
                this.f2625d = d;
                this.other.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            this.f2625d = DisposableHelper.DISPOSED;
            this.other.value = value;
            subscribeNext();
        }

        public void onError(Throwable e) {
            this.f2625d = DisposableHelper.DISPOSED;
            this.other.error = e;
            subscribeNext();
        }

        public void onComplete() {
            this.f2625d = DisposableHelper.DISPOSED;
            subscribeNext();
        }

        void subscribeNext() {
            this.otherSource.subscribe(this.other);
        }
    }

    static final class OtherSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<Object> {
        private static final long serialVersionUID = -1215060610805418006L;
        final MaybeObserver<? super T> actual;
        Throwable error;
        T value;

        OtherSubscriber(MaybeObserver<? super T> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this, s)) {
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object t) {
            Subscription s = (Subscription) get();
            if (s != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                s.cancel();
                onComplete();
            }
        }

        public void onError(Throwable t) {
            if (this.error == null) {
                this.actual.onError(t);
                return;
            }
            this.actual.onError(new CompositeException(e, t));
        }

        public void onComplete() {
            Throwable e = this.error;
            if (e != null) {
                this.actual.onError(e);
                return;
            }
            T v = this.value;
            if (v != null) {
                this.actual.onSuccess(v);
            } else {
                this.actual.onComplete();
            }
        }
    }

    public MaybeDelayOtherPublisher(MaybeSource<T> source, Publisher<U> other) {
        super(source);
        this.other = other;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new DelayMaybeObserver(observer, this.other));
    }
}
