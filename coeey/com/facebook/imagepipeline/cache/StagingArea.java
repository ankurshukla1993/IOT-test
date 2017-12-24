package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

public class StagingArea {
    private static final Class<?> TAG = StagingArea.class;
    @GuardedBy("this")
    private Map<CacheKey, EncodedImage> mMap = new HashMap();

    private StagingArea() {
    }

    public static StagingArea getInstance() {
        return new StagingArea();
    }

    public synchronized void put(CacheKey key, EncodedImage encodedImage) {
        Preconditions.checkNotNull(key);
        Preconditions.checkArgument(EncodedImage.isValid(encodedImage));
        EncodedImage.closeSafely((EncodedImage) this.mMap.put(key, EncodedImage.cloneOrNull(encodedImage)));
        logStats();
    }

    public void clearAll() {
        synchronized (this) {
            List<EncodedImage> old = new ArrayList(this.mMap.values());
            this.mMap.clear();
        }
        for (int i = 0; i < old.size(); i++) {
            EncodedImage encodedImage = (EncodedImage) old.get(i);
            if (encodedImage != null) {
                encodedImage.close();
            }
        }
    }

    public boolean remove(CacheKey key) {
        Preconditions.checkNotNull(key);
        synchronized (this) {
            EncodedImage encodedImage = (EncodedImage) this.mMap.remove(key);
        }
        if (encodedImage == null) {
            return false;
        }
        try {
            boolean isValid = encodedImage.isValid();
            return isValid;
        } finally {
            encodedImage.close();
        }
    }

    public synchronized boolean remove(CacheKey key, EncodedImage encodedImage) {
        boolean z = false;
        synchronized (this) {
            Preconditions.checkNotNull(key);
            Preconditions.checkNotNull(encodedImage);
            Preconditions.checkArgument(EncodedImage.isValid(encodedImage));
            EncodedImage oldValue = (EncodedImage) this.mMap.get(key);
            if (oldValue != null) {
                CloseableReference<PooledByteBuffer> oldRef = oldValue.getByteBufferRef();
                CloseableReference<PooledByteBuffer> ref = encodedImage.getByteBufferRef();
                if (!(oldRef == null || ref == null)) {
                    try {
                        if (oldRef.get() == ref.get()) {
                            this.mMap.remove(key);
                            logStats();
                            z = true;
                        }
                    } finally {
                        CloseableReference.closeSafely(ref);
                        CloseableReference.closeSafely(oldRef);
                        EncodedImage.closeSafely(oldValue);
                    }
                }
                CloseableReference.closeSafely(ref);
                CloseableReference.closeSafely(oldRef);
                EncodedImage.closeSafely(oldValue);
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.facebook.imagepipeline.image.EncodedImage get(com.facebook.cache.common.CacheKey r8) {
        /*
        r7 = this;
        monitor-enter(r7);
        com.facebook.common.internal.Preconditions.checkNotNull(r8);	 Catch:{ all -> 0x0051 }
        r2 = r7.mMap;	 Catch:{ all -> 0x0051 }
        r0 = r2.get(r8);	 Catch:{ all -> 0x0051 }
        r0 = (com.facebook.imagepipeline.image.EncodedImage) r0;	 Catch:{ all -> 0x0051 }
        if (r0 == 0) goto L_0x004b;
    L_0x000e:
        monitor-enter(r0);	 Catch:{ all -> 0x0051 }
        r2 = com.facebook.imagepipeline.image.EncodedImage.isValid(r0);	 Catch:{ all -> 0x004d }
        if (r2 != 0) goto L_0x0045;
    L_0x0015:
        r2 = r7.mMap;	 Catch:{ all -> 0x004d }
        r2.remove(r8);	 Catch:{ all -> 0x004d }
        r2 = TAG;	 Catch:{ all -> 0x004d }
        r3 = "Found closed reference %d for key %s (%d)";
        r4 = 3;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x004d }
        r5 = 0;
        r6 = java.lang.System.identityHashCode(r0);	 Catch:{ all -> 0x004d }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ all -> 0x004d }
        r4[r5] = r6;	 Catch:{ all -> 0x004d }
        r5 = 1;
        r6 = r8.toString();	 Catch:{ all -> 0x004d }
        r4[r5] = r6;	 Catch:{ all -> 0x004d }
        r5 = 2;
        r6 = java.lang.System.identityHashCode(r8);	 Catch:{ all -> 0x004d }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ all -> 0x004d }
        r4[r5] = r6;	 Catch:{ all -> 0x004d }
        com.facebook.common.logging.FLog.m149w(r2, r3, r4);	 Catch:{ all -> 0x004d }
        r2 = 0;
        monitor-exit(r0);	 Catch:{ all -> 0x004d }
    L_0x0043:
        monitor-exit(r7);
        return r2;
    L_0x0045:
        r1 = com.facebook.imagepipeline.image.EncodedImage.cloneOrNull(r0);	 Catch:{ all -> 0x004d }
        monitor-exit(r0);	 Catch:{ all -> 0x0054 }
        r0 = r1;
    L_0x004b:
        r2 = r0;
        goto L_0x0043;
    L_0x004d:
        r2 = move-exception;
        r1 = r0;
    L_0x004f:
        monitor-exit(r0);	 Catch:{ all -> 0x0054 }
        throw r2;	 Catch:{ all -> 0x0051 }
    L_0x0051:
        r2 = move-exception;
        monitor-exit(r7);
        throw r2;
    L_0x0054:
        r2 = move-exception;
        goto L_0x004f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.StagingArea.get(com.facebook.cache.common.CacheKey):com.facebook.imagepipeline.image.EncodedImage");
    }

    public synchronized boolean containsKey(CacheKey key) {
        boolean z = false;
        synchronized (this) {
            Preconditions.checkNotNull(key);
            if (this.mMap.containsKey(key)) {
                EncodedImage storedEncodedImage = (EncodedImage) this.mMap.get(key);
                synchronized (storedEncodedImage) {
                    if (EncodedImage.isValid(storedEncodedImage)) {
                        z = true;
                    } else {
                        this.mMap.remove(key);
                        FLog.m149w(TAG, "Found closed reference %d for key %s (%d)", Integer.valueOf(System.identityHashCode(storedEncodedImage)), key.toString(), Integer.valueOf(System.identityHashCode(key)));
                    }
                }
            }
        }
        return z;
    }

    private synchronized void logStats() {
        FLog.m132v(TAG, "Count = %d", Integer.valueOf(this.mMap.size()));
    }
}
