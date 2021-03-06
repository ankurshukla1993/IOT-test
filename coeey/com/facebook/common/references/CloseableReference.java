package com.facebook.common.references;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class CloseableReference<T> implements Cloneable, Closeable {
    private static final ResourceReleaser<Closeable> DEFAULT_CLOSEABLE_RELEASER = new 1();
    private static Class<CloseableReference> TAG = CloseableReference.class;
    @GuardedBy("this")
    private boolean mIsClosed = false;
    private final SharedReference<T> mSharedReference;

    private CloseableReference(SharedReference<T> sharedReference) {
        this.mSharedReference = (SharedReference) Preconditions.checkNotNull(sharedReference);
        sharedReference.addReference();
    }

    private CloseableReference(T t, ResourceReleaser<T> resourceReleaser) {
        this.mSharedReference = new SharedReference(t, resourceReleaser);
    }

    @Nullable
    public static <T extends Closeable> CloseableReference<T> of(@Nullable T t) {
        if (t == null) {
            return null;
        }
        return new CloseableReference(t, DEFAULT_CLOSEABLE_RELEASER);
    }

    @Nullable
    public static <T> CloseableReference<T> of(@Nullable T t, ResourceReleaser<T> resourceReleaser) {
        if (t == null) {
            return null;
        }
        return new CloseableReference(t, resourceReleaser);
    }

    public void close() {
        synchronized (this) {
            if (this.mIsClosed) {
                return;
            }
            this.mIsClosed = true;
            this.mSharedReference.deleteReference();
        }
    }

    public synchronized T get() {
        Preconditions.checkState(!this.mIsClosed);
        return this.mSharedReference.get();
    }

    public synchronized CloseableReference<T> clone() {
        Preconditions.checkState(isValid());
        return new CloseableReference(this.mSharedReference);
    }

    public synchronized CloseableReference<T> cloneOrNull() {
        return isValid() ? new CloseableReference(this.mSharedReference) : null;
    }

    public synchronized boolean isValid() {
        return !this.mIsClosed;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void finalize() throws java.lang.Throwable {
        /*
        r5 = this;
        monitor-enter(r5);	 Catch:{ all -> 0x0048 }
        r0 = r5.mIsClosed;	 Catch:{ all -> 0x0045 }
        if (r0 == 0) goto L_0x000a;
    L_0x0005:
        monitor-exit(r5);	 Catch:{ all -> 0x0045 }
        super.finalize();
    L_0x0009:
        return;
    L_0x000a:
        monitor-exit(r5);	 Catch:{ all -> 0x0045 }
        r0 = TAG;	 Catch:{ all -> 0x0048 }
        r1 = "Finalized without closing: %x %x (type = %s)";
        r2 = 3;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0048 }
        r3 = 0;
        r4 = java.lang.System.identityHashCode(r5);	 Catch:{ all -> 0x0048 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x0048 }
        r2[r3] = r4;	 Catch:{ all -> 0x0048 }
        r3 = 1;
        r4 = r5.mSharedReference;	 Catch:{ all -> 0x0048 }
        r4 = java.lang.System.identityHashCode(r4);	 Catch:{ all -> 0x0048 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x0048 }
        r2[r3] = r4;	 Catch:{ all -> 0x0048 }
        r3 = 2;
        r4 = r5.mSharedReference;	 Catch:{ all -> 0x0048 }
        r4 = r4.get();	 Catch:{ all -> 0x0048 }
        r4 = r4.getClass();	 Catch:{ all -> 0x0048 }
        r4 = r4.getSimpleName();	 Catch:{ all -> 0x0048 }
        r2[r3] = r4;	 Catch:{ all -> 0x0048 }
        com.facebook.common.logging.FLog.w(r0, r1, r2);	 Catch:{ all -> 0x0048 }
        r5.close();	 Catch:{ all -> 0x0048 }
        super.finalize();
        goto L_0x0009;
    L_0x0045:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0045 }
        throw r0;	 Catch:{ all -> 0x0048 }
    L_0x0048:
        r0 = move-exception;
        super.finalize();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.references.CloseableReference.finalize():void");
    }

    @VisibleForTesting
    public synchronized SharedReference<T> getUnderlyingReferenceTestOnly() {
        return this.mSharedReference;
    }

    public synchronized int getValueHash() {
        return isValid() ? System.identityHashCode(this.mSharedReference.get()) : 0;
    }

    public static boolean isValid(@Nullable CloseableReference<?> ref) {
        return ref != null && ref.isValid();
    }

    @Nullable
    public static <T> CloseableReference<T> cloneOrNull(@Nullable CloseableReference<T> ref) {
        return ref != null ? ref.cloneOrNull() : null;
    }

    public static <T> List<CloseableReference<T>> cloneOrNull(Collection<CloseableReference<T>> refs) {
        if (refs == null) {
            return null;
        }
        List<CloseableReference<T>> ret = new ArrayList(refs.size());
        for (CloseableReference ref : refs) {
            ret.add(cloneOrNull(ref));
        }
        return ret;
    }

    public static void closeSafely(@Nullable CloseableReference<?> ref) {
        if (ref != null) {
            ref.close();
        }
    }

    public static void closeSafely(@Nullable Iterable<? extends CloseableReference<?>> references) {
        if (references != null) {
            for (CloseableReference ref : references) {
                closeSafely(ref);
            }
        }
    }
}
