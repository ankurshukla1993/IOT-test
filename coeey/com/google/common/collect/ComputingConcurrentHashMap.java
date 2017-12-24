package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

class ComputingConcurrentHashMap<K, V> extends MapMakerInternalMap<K, V> {
    private static final long serialVersionUID = 4;
    final Function<? super K, ? extends V> computingFunction;

    private static final class ComputationExceptionReference<K, V> implements ValueReference<K, V> {
        final Throwable f199t;

        ComputationExceptionReference(Throwable t) {
            this.f199t = t;
        }

        public V get() {
            return null;
        }

        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public boolean isComputingReference() {
            return false;
        }

        public V waitForValue() throws ExecutionException {
            throw new ExecutionException(this.f199t);
        }

        public void clear(ValueReference<K, V> valueReference) {
        }
    }

    private static final class ComputedReference<K, V> implements ValueReference<K, V> {
        final V value;

        ComputedReference(@Nullable V value) {
            this.value = value;
        }

        public V get() {
            return this.value;
        }

        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public boolean isComputingReference() {
            return false;
        }

        public V waitForValue() {
            return get();
        }

        public void clear(ValueReference<K, V> valueReference) {
        }
    }

    static final class ComputingSegment<K, V> extends Segment<K, V> {
        ComputingSegment(MapMakerInternalMap<K, V> map, int initialCapacity, int maxSegmentSize) {
            super(map, initialCapacity, maxSegmentSize);
        }

        V getOrCompute(K r15, int r16, com.google.common.base.Function<? super K, ? extends V> r17) throws java.util.concurrent.ExecutionException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
            /*
            r14 = this;
        L_0x0000:
            r4 = r14.getEntry(r15, r16);	 Catch:{ all -> 0x00b1 }
            if (r4 == 0) goto L_0x0013;	 Catch:{ all -> 0x00b1 }
        L_0x0006:
            r10 = r14.getLiveValue(r4);	 Catch:{ all -> 0x00b1 }
            if (r10 == 0) goto L_0x0013;	 Catch:{ all -> 0x00b1 }
        L_0x000c:
            r14.recordRead(r4);	 Catch:{ all -> 0x00b1 }
            r14.postReadCleanup();
        L_0x0012:
            return r10;
        L_0x0013:
            if (r4 == 0) goto L_0x001f;
        L_0x0015:
            r12 = r4.getValueReference();	 Catch:{ all -> 0x00b1 }
            r12 = r12.isComputingReference();	 Catch:{ all -> 0x00b1 }
            if (r12 != 0) goto L_0x00e7;	 Catch:{ all -> 0x00b1 }
        L_0x001f:
            r3 = 1;	 Catch:{ all -> 0x00b1 }
            r1 = 0;	 Catch:{ all -> 0x00b1 }
            r14.lock();	 Catch:{ all -> 0x00b1 }
            r14.preWriteCleanup();	 Catch:{ all -> 0x00a9 }
            r12 = r14.count;	 Catch:{ all -> 0x00a9 }
            r8 = r12 + -1;	 Catch:{ all -> 0x00a9 }
            r9 = r14.table;	 Catch:{ all -> 0x00a9 }
            r12 = r9.length();	 Catch:{ all -> 0x00a9 }
            r12 = r12 + -1;	 Catch:{ all -> 0x00a9 }
            r7 = r16 & r12;	 Catch:{ all -> 0x00a9 }
            r6 = r9.get(r7);	 Catch:{ all -> 0x00a9 }
            r6 = (com.google.common.collect.MapMakerInternalMap.ReferenceEntry) r6;	 Catch:{ all -> 0x00a9 }
            r4 = r6;	 Catch:{ all -> 0x00a9 }
        L_0x003c:
            if (r4 == 0) goto L_0x0061;	 Catch:{ all -> 0x00a9 }
        L_0x003e:
            r5 = r4.getKey();	 Catch:{ all -> 0x00a9 }
            r12 = r4.getHash();	 Catch:{ all -> 0x00a9 }
            r0 = r16;	 Catch:{ all -> 0x00a9 }
            if (r12 != r0) goto L_0x00dc;	 Catch:{ all -> 0x00a9 }
        L_0x004a:
            if (r5 == 0) goto L_0x00dc;	 Catch:{ all -> 0x00a9 }
        L_0x004c:
            r12 = r14.map;	 Catch:{ all -> 0x00a9 }
            r12 = r12.keyEquivalence;	 Catch:{ all -> 0x00a9 }
            r12 = r12.equivalent(r15, r5);	 Catch:{ all -> 0x00a9 }
            if (r12 == 0) goto L_0x00dc;	 Catch:{ all -> 0x00a9 }
        L_0x0056:
            r11 = r4.getValueReference();	 Catch:{ all -> 0x00a9 }
            r12 = r11.isComputingReference();	 Catch:{ all -> 0x00a9 }
            if (r12 == 0) goto L_0x008b;	 Catch:{ all -> 0x00a9 }
        L_0x0060:
            r3 = 0;	 Catch:{ all -> 0x00a9 }
        L_0x0061:
            if (r3 == 0) goto L_0x0079;	 Catch:{ all -> 0x00a9 }
        L_0x0063:
            r2 = new com.google.common.collect.ComputingConcurrentHashMap$ComputingValueReference;	 Catch:{ all -> 0x00a9 }
            r0 = r17;	 Catch:{ all -> 0x00a9 }
            r2.<init>(r0);	 Catch:{ all -> 0x00a9 }
            if (r4 != 0) goto L_0x00e2;
        L_0x006c:
            r0 = r16;	 Catch:{ all -> 0x0107 }
            r4 = r14.newEntry(r15, r0, r6);	 Catch:{ all -> 0x0107 }
            r4.setValueReference(r2);	 Catch:{ all -> 0x0107 }
            r9.set(r7, r4);	 Catch:{ all -> 0x0107 }
            r1 = r2;
        L_0x0079:
            r14.unlock();	 Catch:{ all -> 0x00b1 }
            r14.postWriteCleanup();	 Catch:{ all -> 0x00b1 }
            if (r3 == 0) goto L_0x00e7;	 Catch:{ all -> 0x00b1 }
        L_0x0081:
            r0 = r16;	 Catch:{ all -> 0x00b1 }
            r10 = r14.compute(r15, r0, r4, r1);	 Catch:{ all -> 0x00b1 }
            r14.postReadCleanup();
            goto L_0x0012;
        L_0x008b:
            r12 = r4.getValueReference();	 Catch:{ all -> 0x00a9 }
            r10 = r12.get();	 Catch:{ all -> 0x00a9 }
            if (r10 != 0) goto L_0x00b6;	 Catch:{ all -> 0x00a9 }
        L_0x0095:
            r12 = com.google.common.collect.MapMaker$RemovalCause.COLLECTED;	 Catch:{ all -> 0x00a9 }
            r0 = r16;	 Catch:{ all -> 0x00a9 }
            r14.enqueueNotification(r5, r0, r10, r12);	 Catch:{ all -> 0x00a9 }
        L_0x009c:
            r12 = r14.evictionQueue;	 Catch:{ all -> 0x00a9 }
            r12.remove(r4);	 Catch:{ all -> 0x00a9 }
            r12 = r14.expirationQueue;	 Catch:{ all -> 0x00a9 }
            r12.remove(r4);	 Catch:{ all -> 0x00a9 }
            r14.count = r8;	 Catch:{ all -> 0x00a9 }
            goto L_0x0061;
        L_0x00a9:
            r12 = move-exception;
        L_0x00aa:
            r14.unlock();	 Catch:{ all -> 0x00b1 }
            r14.postWriteCleanup();	 Catch:{ all -> 0x00b1 }
            throw r12;	 Catch:{ all -> 0x00b1 }
        L_0x00b1:
            r12 = move-exception;
            r14.postReadCleanup();
            throw r12;
        L_0x00b6:
            r12 = r14.map;	 Catch:{ all -> 0x00a9 }
            r12 = r12.expires();	 Catch:{ all -> 0x00a9 }
            if (r12 == 0) goto L_0x00ce;	 Catch:{ all -> 0x00a9 }
        L_0x00be:
            r12 = r14.map;	 Catch:{ all -> 0x00a9 }
            r12 = r12.isExpired(r4);	 Catch:{ all -> 0x00a9 }
            if (r12 == 0) goto L_0x00ce;	 Catch:{ all -> 0x00a9 }
        L_0x00c6:
            r12 = com.google.common.collect.MapMaker$RemovalCause.EXPIRED;	 Catch:{ all -> 0x00a9 }
            r0 = r16;	 Catch:{ all -> 0x00a9 }
            r14.enqueueNotification(r5, r0, r10, r12);	 Catch:{ all -> 0x00a9 }
            goto L_0x009c;	 Catch:{ all -> 0x00a9 }
        L_0x00ce:
            r14.recordLockedRead(r4);	 Catch:{ all -> 0x00a9 }
            r14.unlock();	 Catch:{ all -> 0x00b1 }
            r14.postWriteCleanup();	 Catch:{ all -> 0x00b1 }
            r14.postReadCleanup();
            goto L_0x0012;
        L_0x00dc:
            r4 = r4.getNext();	 Catch:{ all -> 0x00a9 }
            goto L_0x003c;
        L_0x00e2:
            r4.setValueReference(r2);	 Catch:{ all -> 0x0107 }
            r1 = r2;
            goto L_0x0079;
        L_0x00e7:
            r12 = java.lang.Thread.holdsLock(r4);	 Catch:{ all -> 0x00b1 }
            if (r12 != 0) goto L_0x0105;	 Catch:{ all -> 0x00b1 }
        L_0x00ed:
            r12 = 1;	 Catch:{ all -> 0x00b1 }
        L_0x00ee:
            r13 = "Recursive computation";	 Catch:{ all -> 0x00b1 }
            com.google.common.base.Preconditions.checkState(r12, r13);	 Catch:{ all -> 0x00b1 }
            r12 = r4.getValueReference();	 Catch:{ all -> 0x00b1 }
            r10 = r12.waitForValue();	 Catch:{ all -> 0x00b1 }
            if (r10 == 0) goto L_0x0000;	 Catch:{ all -> 0x00b1 }
        L_0x00fd:
            r14.recordRead(r4);	 Catch:{ all -> 0x00b1 }
            r14.postReadCleanup();
            goto L_0x0012;
        L_0x0105:
            r12 = 0;
            goto L_0x00ee;
        L_0x0107:
            r12 = move-exception;
            r1 = r2;
            goto L_0x00aa;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ComputingConcurrentHashMap.ComputingSegment.getOrCompute(java.lang.Object, int, com.google.common.base.Function):V");
        }

        V compute(K key, int hash, ReferenceEntry<K, V> e, ComputingValueReference<K, V> computingValueReference) throws ExecutionException {
            V v = null;
            long start = System.nanoTime();
            long end = 0;
            try {
                synchronized (e) {
                    v = computingValueReference.compute(key, hash);
                    end = System.nanoTime();
                }
                if (v != null) {
                    if (put(key, hash, v, true) != null) {
                        enqueueNotification(key, hash, v, MapMaker$RemovalCause.REPLACED);
                    }
                }
                if (end == 0) {
                    end = System.nanoTime();
                }
                if (v == null) {
                    clearValue(key, hash, computingValueReference);
                }
                return v;
            } catch (Throwable th) {
                if (end == 0) {
                    end = System.nanoTime();
                }
                if (v == null) {
                    clearValue(key, hash, computingValueReference);
                }
            }
        }
    }

    static final class ComputingSerializationProxy<K, V> extends AbstractSerializationProxy<K, V> {
        private static final long serialVersionUID = 4;
        final Function<? super K, ? extends V> computingFunction;

        ComputingSerializationProxy(Strength keyStrength, Strength valueStrength, Equivalence<Object> keyEquivalence, Equivalence<Object> valueEquivalence, long expireAfterWriteNanos, long expireAfterAccessNanos, int maximumSize, int concurrencyLevel, MapMaker$RemovalListener<? super K, ? super V> removalListener, ConcurrentMap<K, V> delegate, Function<? super K, ? extends V> computingFunction) {
            super(keyStrength, valueStrength, keyEquivalence, valueEquivalence, expireAfterWriteNanos, expireAfterAccessNanos, maximumSize, concurrencyLevel, removalListener, delegate);
            this.computingFunction = computingFunction;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            writeMapTo(out);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            this.delegate = readMapMaker(in).makeComputingMap(this.computingFunction);
            readEntries(in);
        }

        Object readResolve() {
            return this.delegate;
        }
    }

    private static final class ComputingValueReference<K, V> implements ValueReference<K, V> {
        @GuardedBy("ComputingValueReference.this")
        volatile ValueReference<K, V> computedReference = MapMakerInternalMap.unset();
        final Function<? super K, ? extends V> computingFunction;

        public ComputingValueReference(Function<? super K, ? extends V> computingFunction) {
            this.computingFunction = computingFunction;
        }

        public V get() {
            return null;
        }

        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, @Nullable V v, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public boolean isComputingReference() {
            return true;
        }

        public V waitForValue() throws ExecutionException {
            if (this.computedReference == MapMakerInternalMap.UNSET) {
                boolean z = false;
                try {
                    synchronized (this) {
                        while (this.computedReference == MapMakerInternalMap.UNSET) {
                            try {
                                wait();
                            } catch (InterruptedException e) {
                                z = true;
                            }
                        }
                    }
                } finally {
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            return this.computedReference.waitForValue();
        }

        public void clear(ValueReference<K, V> newValue) {
            setValueReference(newValue);
        }

        V compute(K key, int hash) throws ExecutionException {
            try {
                V value = this.computingFunction.apply(key);
                setValueReference(new ComputedReference(value));
                return value;
            } catch (Throwable t) {
                setValueReference(new ComputationExceptionReference(t));
                ExecutionException executionException = new ExecutionException(t);
            }
        }

        void setValueReference(ValueReference<K, V> valueReference) {
            synchronized (this) {
                if (this.computedReference == MapMakerInternalMap.UNSET) {
                    this.computedReference = valueReference;
                    notifyAll();
                }
            }
        }
    }

    ComputingConcurrentHashMap(MapMaker builder, Function<? super K, ? extends V> computingFunction) {
        super(builder);
        this.computingFunction = (Function) Preconditions.checkNotNull(computingFunction);
    }

    Segment<K, V> createSegment(int initialCapacity, int maxSegmentSize) {
        return new ComputingSegment(this, initialCapacity, maxSegmentSize);
    }

    ComputingSegment<K, V> segmentFor(int hash) {
        return (ComputingSegment) super.segmentFor(hash);
    }

    V getOrCompute(K key) throws ExecutionException {
        int hash = hash(Preconditions.checkNotNull(key));
        return segmentFor(hash).getOrCompute(key, hash, this.computingFunction);
    }

    Object writeReplace() {
        return new ComputingSerializationProxy(this.keyStrength, this.valueStrength, this.keyEquivalence, this.valueEquivalence, this.expireAfterWriteNanos, this.expireAfterAccessNanos, this.maximumSize, this.concurrencyLevel, this.removalListener, this, this.computingFunction);
    }
}
