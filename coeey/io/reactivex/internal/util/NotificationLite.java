package io.reactivex.internal.util;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.ObjectHelper;
import java.io.Serializable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public enum NotificationLite {
    COMPLETE;

    static final class DisposableNotification implements Serializable {
        private static final long serialVersionUID = -7482590109178395495L;
        final Disposable f2807d;

        DisposableNotification(Disposable d) {
            this.f2807d = d;
        }

        public String toString() {
            return "NotificationLite.Disposable[" + this.f2807d + "]";
        }
    }

    static final class ErrorNotification implements Serializable {
        private static final long serialVersionUID = -8759979445933046293L;
        final Throwable f2808e;

        ErrorNotification(Throwable e) {
            this.f2808e = e;
        }

        public String toString() {
            return "NotificationLite.Error[" + this.f2808e + "]";
        }

        public int hashCode() {
            return this.f2808e.hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ErrorNotification)) {
                return false;
            }
            return ObjectHelper.equals(this.f2808e, ((ErrorNotification) obj).f2808e);
        }
    }

    static final class SubscriptionNotification implements Serializable {
        private static final long serialVersionUID = -1322257508628817540L;
        final Subscription f2809s;

        SubscriptionNotification(Subscription s) {
            this.f2809s = s;
        }

        public String toString() {
            return "NotificationLite.Subscription[" + this.f2809s + "]";
        }
    }

    public static <T> Object next(T value) {
        return value;
    }

    public static Object complete() {
        return COMPLETE;
    }

    public static Object error(Throwable e) {
        return new ErrorNotification(e);
    }

    public static Object subscription(Subscription s) {
        return new SubscriptionNotification(s);
    }

    public static Object disposable(Disposable d) {
        return new DisposableNotification(d);
    }

    public static boolean isComplete(Object o) {
        return o == COMPLETE;
    }

    public static boolean isError(Object o) {
        return o instanceof ErrorNotification;
    }

    public static boolean isSubscription(Object o) {
        return o instanceof SubscriptionNotification;
    }

    public static boolean isDisposable(Object o) {
        return o instanceof DisposableNotification;
    }

    public static <T> T getValue(Object o) {
        return o;
    }

    public static Throwable getError(Object o) {
        return ((ErrorNotification) o).f2808e;
    }

    public static Subscription getSubscription(Object o) {
        return ((SubscriptionNotification) o).f2809s;
    }

    public static Disposable getDisposable(Object o) {
        return ((DisposableNotification) o).f2807d;
    }

    public static <T> boolean accept(Object o, Subscriber<? super T> s) {
        if (o == COMPLETE) {
            s.onComplete();
            return true;
        } else if (o instanceof ErrorNotification) {
            s.onError(((ErrorNotification) o).f2808e);
            return true;
        } else {
            s.onNext(o);
            return false;
        }
    }

    public static <T> boolean accept(Object o, Observer<? super T> s) {
        if (o == COMPLETE) {
            s.onComplete();
            return true;
        } else if (o instanceof ErrorNotification) {
            s.onError(((ErrorNotification) o).f2808e);
            return true;
        } else {
            s.onNext(o);
            return false;
        }
    }

    public static <T> boolean acceptFull(Object o, Subscriber<? super T> s) {
        if (o == COMPLETE) {
            s.onComplete();
            return true;
        } else if (o instanceof ErrorNotification) {
            s.onError(((ErrorNotification) o).f2808e);
            return true;
        } else if (o instanceof SubscriptionNotification) {
            s.onSubscribe(((SubscriptionNotification) o).f2809s);
            return false;
        } else {
            s.onNext(o);
            return false;
        }
    }

    public static <T> boolean acceptFull(Object o, Observer<? super T> s) {
        if (o == COMPLETE) {
            s.onComplete();
            return true;
        } else if (o instanceof ErrorNotification) {
            s.onError(((ErrorNotification) o).f2808e);
            return true;
        } else if (o instanceof DisposableNotification) {
            s.onSubscribe(((DisposableNotification) o).f2807d);
            return false;
        } else {
            s.onNext(o);
            return false;
        }
    }

    public String toString() {
        return "NotificationLite.Complete";
    }
}
