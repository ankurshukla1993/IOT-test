package io.realm.internal;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

final class NativeObjectReference extends PhantomReference<NativeObject> {
    private static ReferencePool referencePool = new ReferencePool();
    private final NativeContext context;
    private final long nativeFinalizerPtr;
    private final long nativePtr;
    private NativeObjectReference next;
    private NativeObjectReference prev;

    private static class ReferencePool {
        NativeObjectReference head;

        private ReferencePool() {
        }

        synchronized void add(NativeObjectReference ref) {
            ref.prev = null;
            ref.next = this.head;
            if (this.head != null) {
                this.head.prev = ref;
            }
            this.head = ref;
        }

        synchronized void remove(NativeObjectReference ref) {
            NativeObjectReference next = ref.next;
            NativeObjectReference prev = ref.prev;
            ref.next = null;
            ref.prev = null;
            if (prev != null) {
                prev.next = next;
            } else {
                this.head = next;
            }
            if (next != null) {
                next.prev = prev;
            }
        }
    }

    private static native void nativeCleanUp(long j, long j2);

    NativeObjectReference(NativeContext context, NativeObject referent, ReferenceQueue<? super NativeObject> referenceQueue) {
        super(referent, referenceQueue);
        this.nativePtr = referent.getNativePtr();
        this.nativeFinalizerPtr = referent.getNativeFinalizerPtr();
        this.context = context;
        referencePool.add(this);
    }

    void cleanup() {
        synchronized (this.context) {
            nativeCleanUp(this.nativeFinalizerPtr, this.nativePtr);
        }
        referencePool.remove(this);
    }
}
