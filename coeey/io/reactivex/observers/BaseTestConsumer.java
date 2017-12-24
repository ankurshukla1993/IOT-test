package io.reactivex.observers;

import humanize.util.Constants;
import io.reactivex.Notification;
import io.reactivex.annotations.Experimental;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.VolatileSizeArrayList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class BaseTestConsumer<T, U extends BaseTestConsumer<T, U>> implements Disposable {
    protected boolean checkSubscriptionOnce;
    protected long completions;
    protected final CountDownLatch done = new CountDownLatch(1);
    protected final List<Throwable> errors = new VolatileSizeArrayList();
    protected int establishedFusionMode;
    protected int initialFusionMode;
    protected Thread lastThread;
    protected CharSequence tag;
    protected boolean timeout;
    protected final List<T> values = new VolatileSizeArrayList();

    public enum TestWaitStrategy implements Runnable {
        SPIN {
            public void run() {
            }
        },
        YIELD {
            public void run() {
                Thread.yield();
            }
        },
        SLEEP_1MS {
            public void run() {
                TestWaitStrategy.sleep(1);
            }
        },
        SLEEP_10MS {
            public void run() {
                TestWaitStrategy.sleep(10);
            }
        },
        SLEEP_100MS {
            public void run() {
                TestWaitStrategy.sleep(100);
            }
        },
        SLEEP_1000MS {
            public void run() {
                TestWaitStrategy.sleep(1000);
            }
        };

        public abstract void run();

        static void sleep(int millis) {
            try {
                Thread.sleep((long) millis);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public abstract U assertNotSubscribed();

    public abstract U assertSubscribed();

    public final Thread lastThread() {
        return this.lastThread;
    }

    public final List<T> values() {
        return this.values;
    }

    public final List<Throwable> errors() {
        return this.errors;
    }

    public final long completions() {
        return this.completions;
    }

    public final boolean isTerminated() {
        return this.done.getCount() == 0;
    }

    public final int valueCount() {
        return this.values.size();
    }

    public final int errorCount() {
        return this.errors.size();
    }

    protected final AssertionError fail(String message) {
        StringBuilder b = new StringBuilder(message.length() + 64);
        b.append(message);
        b.append(" (").append("latch = ").append(this.done.getCount()).append(", ").append("values = ").append(this.values.size()).append(", ").append("errors = ").append(this.errors.size()).append(", ").append("completions = ").append(this.completions);
        if (this.timeout) {
            b.append(", timeout!");
        }
        if (isDisposed()) {
            b.append(", disposed!");
        }
        CharSequence tag = this.tag;
        if (tag != null) {
            b.append(", tag = ").append(tag);
        }
        b.append(')');
        AssertionError ae = new AssertionError(b.toString());
        if (!this.errors.isEmpty()) {
            if (this.errors.size() == 1) {
                ae.initCause((Throwable) this.errors.get(0));
            } else {
                ae.initCause(new CompositeException(this.errors));
            }
        }
        return ae;
    }

    public final U await() throws InterruptedException {
        if (this.done.getCount() != 0) {
            this.done.await();
        }
        return this;
    }

    public final boolean await(long time, TimeUnit unit) throws InterruptedException {
        boolean d;
        boolean z = true;
        if (this.done.getCount() == 0 || this.done.await(time, unit)) {
            d = true;
        } else {
            d = false;
        }
        if (d) {
            z = false;
        }
        this.timeout = z;
        return d;
    }

    public final U assertComplete() {
        long c = this.completions;
        if (c == 0) {
            throw fail("Not completed");
        } else if (c <= 1) {
            return this;
        } else {
            throw fail("Multiple completions: " + c);
        }
    }

    public final U assertNotComplete() {
        long c = this.completions;
        if (c == 1) {
            throw fail("Completed!");
        } else if (c <= 1) {
            return this;
        } else {
            throw fail("Multiple completions: " + c);
        }
    }

    public final U assertNoErrors() {
        if (this.errors.size() == 0) {
            return this;
        }
        throw fail("Error(s) present: " + this.errors);
    }

    public final U assertError(Throwable error) {
        return assertError(Functions.equalsWith(error));
    }

    public final U assertError(Class<? extends Throwable> errorClass) {
        return assertError(Functions.isInstanceOf(errorClass));
    }

    public final U assertError(Predicate<Throwable> errorPredicate) {
        int s = this.errors.size();
        if (s == 0) {
            throw fail("No errors");
        }
        boolean found = false;
        for (Throwable e : this.errors) {
            try {
                if (errorPredicate.test(e)) {
                    found = true;
                    break;
                }
            } catch (Exception ex) {
                throw ExceptionHelper.wrapOrThrow(ex);
            }
        }
        if (!found) {
            throw fail("Error not present");
        } else if (s == 1) {
            return this;
        } else {
            throw fail("Error present but other errors as well");
        }
    }

    public final U assertValue(T value) {
        if (this.values.size() != 1) {
            throw fail("Expected: " + valueAndClass(value) + ", Actual: " + this.values);
        }
        T v = this.values.get(0);
        if (ObjectHelper.equals(value, v)) {
            return this;
        }
        throw fail("Expected: " + valueAndClass(value) + ", Actual: " + valueAndClass(v));
    }

    public final U assertNever(T value) {
        int s = this.values.size();
        for (int i = 0; i < s; i++) {
            if (ObjectHelper.equals(this.values.get(i), value)) {
                throw fail("Value at position " + i + " is equal to " + valueAndClass(value) + "; Expected them to be different");
            }
        }
        return this;
    }

    public final U assertValue(Predicate<T> valuePredicate) {
        assertValueAt(0, (Predicate) valuePredicate);
        if (this.values.size() <= 1) {
            return this;
        }
        throw fail("Value present but other values as well");
    }

    public final U assertNever(Predicate<? super T> valuePredicate) {
        int s = this.values.size();
        int i = 0;
        while (i < s) {
            try {
                if (valuePredicate.test(this.values.get(i))) {
                    throw fail("Value at position " + i + " matches predicate " + valuePredicate.toString() + ", which was not expected.");
                }
                i++;
            } catch (Exception ex) {
                throw ExceptionHelper.wrapOrThrow(ex);
            }
        }
        return this;
    }

    @Experimental
    public final U assertValueAt(int index, T value) {
        int s = this.values.size();
        if (s == 0) {
            throw fail("No values");
        } else if (index >= s) {
            throw fail("Invalid index: " + index);
        } else {
            T v = this.values.get(index);
            if (ObjectHelper.equals(value, v)) {
                return this;
            }
            throw fail("Expected: " + valueAndClass(value) + ", Actual: " + valueAndClass(v));
        }
    }

    public final U assertValueAt(int index, Predicate<T> valuePredicate) {
        if (this.values.size() == 0) {
            throw fail("No values");
        } else if (index >= this.values.size()) {
            throw fail("Invalid index: " + index);
        } else {
            boolean found = false;
            try {
                if (valuePredicate.test(this.values.get(index))) {
                    found = true;
                }
                if (found) {
                    return this;
                }
                throw fail("Value not present");
            } catch (Exception ex) {
                throw ExceptionHelper.wrapOrThrow(ex);
            }
        }
    }

    public static String valueAndClass(Object o) {
        if (o != null) {
            return o + " (class: " + o.getClass().getSimpleName() + ")";
        }
        return "null";
    }

    public final U assertValueCount(int count) {
        int s = this.values.size();
        if (s == count) {
            return this;
        }
        throw fail("Value counts differ; Expected: " + count + ", Actual: " + s);
    }

    public final U assertNoValues() {
        return assertValueCount(0);
    }

    public final U assertValues(T... values) {
        int s = this.values.size();
        if (s != values.length) {
            throw fail("Value count differs; Expected: " + values.length + Constants.SPACE + Arrays.toString(values) + ", Actual: " + s + Constants.SPACE + this.values);
        }
        int i = 0;
        while (i < s) {
            T v = this.values.get(i);
            T u = values[i];
            if (ObjectHelper.equals(u, v)) {
                i++;
            } else {
                throw fail("Values at position " + i + " differ; Expected: " + valueAndClass(u) + ", Actual: " + valueAndClass(v));
            }
        }
        return this;
    }

    public final U assertValueSet(Collection<? extends T> expected) {
        if (expected.isEmpty()) {
            assertNoValues();
        } else {
            for (T v : this.values) {
                if (!expected.contains(v)) {
                    throw fail("Value not in the expected collection: " + valueAndClass(v));
                }
            }
        }
        return this;
    }

    public final U assertValueSequence(Iterable<? extends T> sequence) {
        int i = 0;
        Iterator<T> vit = this.values.iterator();
        Iterator<? extends T> it = sequence.iterator();
        while (true) {
            boolean actualNext = it.hasNext();
            boolean expectedNext = vit.hasNext();
            if (actualNext && expectedNext) {
                T v = it.next();
                T u = vit.next();
                if (ObjectHelper.equals(u, v)) {
                    i++;
                } else {
                    throw fail("Values at position " + i + " differ; Expected: " + valueAndClass(u) + ", Actual: " + valueAndClass(v));
                }
            } else if (actualNext) {
                throw fail("More values received than expected (" + i + ")");
            } else if (expectedNext) {
                return this;
            } else {
                throw fail("Fewer values received than expected (" + i + ")");
            }
        }
        if (actualNext) {
            throw fail("More values received than expected (" + i + ")");
        } else if (expectedNext) {
            return this;
        } else {
            throw fail("Fewer values received than expected (" + i + ")");
        }
    }

    public final U assertTerminated() {
        if (this.done.getCount() != 0) {
            throw fail("Subscriber still running!");
        }
        long c = this.completions;
        if (c > 1) {
            throw fail("Terminated with multiple completions: " + c);
        }
        int s = this.errors.size();
        if (s > 1) {
            throw fail("Terminated with multiple errors: " + s);
        } else if (c == 0 || s == 0) {
            return this;
        } else {
            throw fail("Terminated with multiple completions and errors: " + c);
        }
    }

    public final U assertNotTerminated() {
        if (this.done.getCount() != 0) {
            return this;
        }
        throw fail("Subscriber terminated!");
    }

    public final boolean awaitTerminalEvent() {
        try {
            await();
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public final boolean awaitTerminalEvent(long duration, TimeUnit unit) {
        try {
            return await(duration, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public final U assertErrorMessage(String message) {
        int s = this.errors.size();
        if (s == 0) {
            throw fail("No errors");
        } else if (s == 1) {
            String errorMessage = ((Throwable) this.errors.get(0)).getMessage();
            if (ObjectHelper.equals(message, errorMessage)) {
                return this;
            }
            throw fail("Error message differs; Expected: " + message + ", Actual: " + errorMessage);
        } else {
            throw fail("Multiple errors");
        }
    }

    public final List<List<Object>> getEvents() {
        List<List<Object>> result = new ArrayList();
        result.add(values());
        result.add(errors());
        List<Object> completeList = new ArrayList();
        for (long i = 0; i < this.completions; i++) {
            completeList.add(Notification.createOnComplete());
        }
        result.add(completeList);
        return result;
    }

    public final U assertResult(T... values) {
        return assertSubscribed().assertValues(values).assertNoErrors().assertComplete();
    }

    public final U assertFailure(Class<? extends Throwable> error, T... values) {
        return assertSubscribed().assertValues(values).assertError((Class) error).assertNotComplete();
    }

    public final U assertFailure(Predicate<Throwable> errorPredicate, T... values) {
        return assertSubscribed().assertValues(values).assertError((Predicate) errorPredicate).assertNotComplete();
    }

    public final U assertFailureAndMessage(Class<? extends Throwable> error, String message, T... values) {
        return assertSubscribed().assertValues(values).assertError((Class) error).assertErrorMessage(message).assertNotComplete();
    }

    public final U awaitDone(long time, TimeUnit unit) {
        try {
            if (!this.done.await(time, unit)) {
                this.timeout = true;
                dispose();
            }
            return this;
        } catch (InterruptedException ex) {
            dispose();
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

    public final U assertEmpty() {
        return assertSubscribed().assertNoValues().assertNoErrors().assertNotComplete();
    }

    public final U withTag(CharSequence tag) {
        this.tag = tag;
        return this;
    }

    public final U awaitCount(int atLeast) {
        return awaitCount(atLeast, TestWaitStrategy.SLEEP_10MS, 5000);
    }

    public final U awaitCount(int atLeast, Runnable waitStrategy) {
        return awaitCount(atLeast, waitStrategy, 5000);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final U awaitCount(int r8, java.lang.Runnable r9, long r10) {
        /*
        r7 = this;
        r4 = 0;
        r0 = java.lang.System.currentTimeMillis();
    L_0x0006:
        r2 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x0017;
    L_0x000a:
        r2 = java.lang.System.currentTimeMillis();
        r2 = r2 - r0;
        r2 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1));
        if (r2 < 0) goto L_0x0017;
    L_0x0013:
        r2 = 1;
        r7.timeout = r2;
    L_0x0016:
        return r7;
    L_0x0017:
        r2 = r7.done;
        r2 = r2.getCount();
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 == 0) goto L_0x0016;
    L_0x0021:
        r2 = r7.values;
        r2 = r2.size();
        if (r2 >= r8) goto L_0x0016;
    L_0x0029:
        r9.run();
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.observers.BaseTestConsumer.awaitCount(int, java.lang.Runnable, long):U");
    }

    public final boolean isTimeout() {
        return this.timeout;
    }

    public final U clearTimeout() {
        this.timeout = false;
        return this;
    }

    public final U assertTimeout() {
        if (this.timeout) {
            return this;
        }
        throw fail("No timeout?!");
    }

    public final U assertNoTimeout() {
        if (!this.timeout) {
            return this;
        }
        throw fail("Timeout?!");
    }
}
