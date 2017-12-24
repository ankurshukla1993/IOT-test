package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

class EventSubscriber {
    private final Method method;
    private final Object target;

    EventSubscriber(Object target, Method method) {
        Preconditions.checkNotNull(target, "EventSubscriber target cannot be null.");
        Preconditions.checkNotNull(method, "EventSubscriber method cannot be null.");
        this.target = target;
        this.method = method;
        method.setAccessible(true);
    }

    public void handleEvent(Object event) throws InvocationTargetException {
        String valueOf;
        Preconditions.checkNotNull(event);
        try {
            this.method.invoke(this.target, new Object[]{event});
        } catch (IllegalArgumentException e) {
            valueOf = String.valueOf(String.valueOf(event));
            throw new Error(new StringBuilder(valueOf.length() + 33).append("Method rejected target/argument: ").append(valueOf).toString(), e);
        } catch (IllegalAccessException e2) {
            valueOf = String.valueOf(String.valueOf(event));
            throw new Error(new StringBuilder(valueOf.length() + 28).append("Method became inaccessible: ").append(valueOf).toString(), e2);
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof Error) {
                throw ((Error) e3.getCause());
            }
            throw e3;
        }
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.method));
        return new StringBuilder(valueOf.length() + 10).append("[wrapper ").append(valueOf).append("]").toString();
    }

    public int hashCode() {
        return ((this.method.hashCode() + 31) * 31) + System.identityHashCode(this.target);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof EventSubscriber)) {
            return false;
        }
        EventSubscriber that = (EventSubscriber) obj;
        if (this.target == that.target && this.method.equals(that.method)) {
            return true;
        }
        return false;
    }

    public Object getSubscriber() {
        return this.target;
    }

    public Method getMethod() {
        return this.method;
    }
}
