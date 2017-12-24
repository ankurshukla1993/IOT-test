package com.google.common.util.concurrent;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

class Futures$CombinedFuture<V, C> extends AbstractFuture<C> {
    private static final Logger logger = Logger.getLogger(Futures$CombinedFuture.class.getName());
    final boolean allMustSucceed;
    Futures$FutureCombiner<V, C> combiner;
    ImmutableCollection<? extends ListenableFuture<? extends V>> futures;
    final AtomicInteger remaining;
    Set<Throwable> seenExceptions;
    final Object seenExceptionsLock = new Object();
    List<Optional<V>> values;

    class C18581 implements Runnable {
        C18581() {
        }

        public void run() {
            if (Futures$CombinedFuture.this.isCancelled()) {
                Iterator i$ = Futures$CombinedFuture.this.futures.iterator();
                while (i$.hasNext()) {
                    ((ListenableFuture) i$.next()).cancel(Futures$CombinedFuture.this.wasInterrupted());
                }
            }
            Futures$CombinedFuture.this.futures = null;
            Futures$CombinedFuture.this.values = null;
            Futures$CombinedFuture.this.combiner = null;
        }
    }

    private void setOneValue(int r11, java.util.concurrent.Future<? extends V> r12) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x004e in list [B:17:0x0041]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r10 = this;
        r8 = 1;
        r7 = 0;
        r2 = r10.values;
        r6 = r10.isDone();
        if (r6 != 0) goto L_0x000c;
    L_0x000a:
        if (r2 != 0) goto L_0x001c;
    L_0x000c:
        r6 = r10.allMustSucceed;
        if (r6 != 0) goto L_0x0016;
    L_0x0010:
        r6 = r10.isCancelled();
        if (r6 == 0) goto L_0x004f;
    L_0x0016:
        r6 = r8;
    L_0x0017:
        r9 = "Future was done before all dependencies completed";
        com.google.common.base.Preconditions.checkState(r6, r9);
    L_0x001c:
        r6 = r12.isDone();	 Catch:{ CancellationException -> 0x005b, ExecutionException -> 0x008b, Throwable -> 0x00ba, all -> 0x00e7 }
        r9 = "Tried to set value from future which is not done";	 Catch:{ CancellationException -> 0x005b, ExecutionException -> 0x008b, Throwable -> 0x00ba, all -> 0x00e7 }
        com.google.common.base.Preconditions.checkState(r6, r9);	 Catch:{ CancellationException -> 0x005b, ExecutionException -> 0x008b, Throwable -> 0x00ba, all -> 0x00e7 }
        r4 = com.google.common.util.concurrent.Uninterruptibles.getUninterruptibly(r12);	 Catch:{ CancellationException -> 0x005b, ExecutionException -> 0x008b, Throwable -> 0x00ba, all -> 0x00e7 }
        if (r2 == 0) goto L_0x0032;	 Catch:{ CancellationException -> 0x005b, ExecutionException -> 0x008b, Throwable -> 0x00ba, all -> 0x00e7 }
    L_0x002b:
        r6 = com.google.common.base.Optional.fromNullable(r4);	 Catch:{ CancellationException -> 0x005b, ExecutionException -> 0x008b, Throwable -> 0x00ba, all -> 0x00e7 }
        r2.set(r11, r6);	 Catch:{ CancellationException -> 0x005b, ExecutionException -> 0x008b, Throwable -> 0x00ba, all -> 0x00e7 }
    L_0x0032:
        r6 = r10.remaining;
        r3 = r6.decrementAndGet();
        if (r3 < 0) goto L_0x0051;
    L_0x003a:
        r6 = "Less than 0 remaining futures";
        com.google.common.base.Preconditions.checkState(r8, r6);
        if (r3 != 0) goto L_0x004e;
    L_0x0041:
        r1 = r10.combiner;
        if (r1 == 0) goto L_0x0053;
    L_0x0045:
        if (r2 == 0) goto L_0x0053;
    L_0x0047:
        r6 = r1.combine(r2);
        r10.set(r6);
    L_0x004e:
        return;
    L_0x004f:
        r6 = r7;
        goto L_0x0017;
    L_0x0051:
        r8 = r7;
        goto L_0x003a;
    L_0x0053:
        r6 = r10.isDone();
        com.google.common.base.Preconditions.checkState(r6);
        goto L_0x004e;
    L_0x005b:
        r0 = move-exception;
        r6 = r10.allMustSucceed;	 Catch:{ CancellationException -> 0x005b, ExecutionException -> 0x008b, Throwable -> 0x00ba, all -> 0x00e7 }
        if (r6 == 0) goto L_0x0064;	 Catch:{ CancellationException -> 0x005b, ExecutionException -> 0x008b, Throwable -> 0x00ba, all -> 0x00e7 }
    L_0x0060:
        r6 = 0;	 Catch:{ CancellationException -> 0x005b, ExecutionException -> 0x008b, Throwable -> 0x00ba, all -> 0x00e7 }
        r10.cancel(r6);	 Catch:{ CancellationException -> 0x005b, ExecutionException -> 0x008b, Throwable -> 0x00ba, all -> 0x00e7 }
    L_0x0064:
        r6 = r10.remaining;
        r3 = r6.decrementAndGet();
        if (r3 < 0) goto L_0x0081;
    L_0x006c:
        r6 = "Less than 0 remaining futures";
        com.google.common.base.Preconditions.checkState(r8, r6);
        if (r3 != 0) goto L_0x004e;
    L_0x0073:
        r1 = r10.combiner;
        if (r1 == 0) goto L_0x0083;
    L_0x0077:
        if (r2 == 0) goto L_0x0083;
    L_0x0079:
        r6 = r1.combine(r2);
        r10.set(r6);
        goto L_0x004e;
    L_0x0081:
        r8 = r7;
        goto L_0x006c;
    L_0x0083:
        r6 = r10.isDone();
        com.google.common.base.Preconditions.checkState(r6);
        goto L_0x004e;
    L_0x008b:
        r0 = move-exception;
        r6 = r0.getCause();	 Catch:{ CancellationException -> 0x005b, ExecutionException -> 0x008b, Throwable -> 0x00ba, all -> 0x00e7 }
        r10.setExceptionAndMaybeLog(r6);	 Catch:{ CancellationException -> 0x005b, ExecutionException -> 0x008b, Throwable -> 0x00ba, all -> 0x00e7 }
        r6 = r10.remaining;
        r3 = r6.decrementAndGet();
        if (r3 < 0) goto L_0x00b0;
    L_0x009b:
        r6 = "Less than 0 remaining futures";
        com.google.common.base.Preconditions.checkState(r8, r6);
        if (r3 != 0) goto L_0x004e;
    L_0x00a2:
        r1 = r10.combiner;
        if (r1 == 0) goto L_0x00b2;
    L_0x00a6:
        if (r2 == 0) goto L_0x00b2;
    L_0x00a8:
        r6 = r1.combine(r2);
        r10.set(r6);
        goto L_0x004e;
    L_0x00b0:
        r8 = r7;
        goto L_0x009b;
    L_0x00b2:
        r6 = r10.isDone();
        com.google.common.base.Preconditions.checkState(r6);
        goto L_0x004e;
    L_0x00ba:
        r5 = move-exception;
        r10.setExceptionAndMaybeLog(r5);	 Catch:{ CancellationException -> 0x005b, ExecutionException -> 0x008b, Throwable -> 0x00ba, all -> 0x00e7 }
        r6 = r10.remaining;
        r3 = r6.decrementAndGet();
        if (r3 < 0) goto L_0x00dc;
    L_0x00c6:
        r6 = "Less than 0 remaining futures";
        com.google.common.base.Preconditions.checkState(r8, r6);
        if (r3 != 0) goto L_0x004e;
    L_0x00cd:
        r1 = r10.combiner;
        if (r1 == 0) goto L_0x00de;
    L_0x00d1:
        if (r2 == 0) goto L_0x00de;
    L_0x00d3:
        r6 = r1.combine(r2);
        r10.set(r6);
        goto L_0x004e;
    L_0x00dc:
        r8 = r7;
        goto L_0x00c6;
    L_0x00de:
        r6 = r10.isDone();
        com.google.common.base.Preconditions.checkState(r6);
        goto L_0x004e;
    L_0x00e7:
        r6 = move-exception;
        r9 = r10.remaining;
        r3 = r9.decrementAndGet();
        if (r3 < 0) goto L_0x0105;
    L_0x00f0:
        r7 = "Less than 0 remaining futures";
        com.google.common.base.Preconditions.checkState(r8, r7);
        if (r3 != 0) goto L_0x0104;
    L_0x00f7:
        r1 = r10.combiner;
        if (r1 == 0) goto L_0x0107;
    L_0x00fb:
        if (r2 == 0) goto L_0x0107;
    L_0x00fd:
        r7 = r1.combine(r2);
        r10.set(r7);
    L_0x0104:
        throw r6;
    L_0x0105:
        r8 = r7;
        goto L_0x00f0;
    L_0x0107:
        r7 = r10.isDone();
        com.google.common.base.Preconditions.checkState(r7);
        goto L_0x0104;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Futures$CombinedFuture.setOneValue(int, java.util.concurrent.Future):void");
    }

    Futures$CombinedFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed, Executor listenerExecutor, Futures$FutureCombiner<V, C> combiner) {
        this.futures = futures;
        this.allMustSucceed = allMustSucceed;
        this.remaining = new AtomicInteger(futures.size());
        this.combiner = combiner;
        this.values = Lists.newArrayListWithCapacity(futures.size());
        init(listenerExecutor);
    }

    protected void init(Executor listenerExecutor) {
        addListener(new C18581(), MoreExecutors.directExecutor());
        if (this.futures.isEmpty()) {
            set(this.combiner.combine(ImmutableList.of()));
            return;
        }
        int i;
        for (i = 0; i < this.futures.size(); i++) {
            this.values.add(null);
        }
        i = 0;
        Iterator i$ = this.futures.iterator();
        while (i$.hasNext()) {
            final ListenableFuture<? extends V> listenable = (ListenableFuture) i$.next();
            int i2 = i + 1;
            final int index = i;
            listenable.addListener(new Runnable() {
                public void run() {
                    Futures$CombinedFuture.this.setOneValue(index, listenable);
                }
            }, listenerExecutor);
            i = i2;
        }
    }

    private void setExceptionAndMaybeLog(Throwable throwable) {
        boolean visibleFromOutputFuture = false;
        boolean firstTimeSeeingThisException = true;
        if (this.allMustSucceed) {
            visibleFromOutputFuture = super.setException(throwable);
            synchronized (this.seenExceptionsLock) {
                if (this.seenExceptions == null) {
                    this.seenExceptions = Sets.newHashSet();
                }
                firstTimeSeeingThisException = this.seenExceptions.add(throwable);
            }
        }
        if ((throwable instanceof Error) || (this.allMustSucceed && !visibleFromOutputFuture && firstTimeSeeingThisException)) {
            logger.log(Level.SEVERE, "input future failed.", throwable);
        }
    }
}
