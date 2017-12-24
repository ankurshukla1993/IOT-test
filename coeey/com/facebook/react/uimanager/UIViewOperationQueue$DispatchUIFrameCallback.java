package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.ReactChoreographer.CallbackType;
import com.facebook.systrace.Systrace;

class UIViewOperationQueue$DispatchUIFrameCallback extends GuardedChoreographerFrameCallback {
    private static final int FRAME_TIME_MS = 16;
    private static final int MIN_TIME_LEFT_IN_FRAME_TO_SCHEDULE_MORE_WORK_MS = 8;
    final /* synthetic */ UIViewOperationQueue this$0;

    private UIViewOperationQueue$DispatchUIFrameCallback(UIViewOperationQueue uIViewOperationQueue, ReactContext reactContext) {
        this.this$0 = uIViewOperationQueue;
        super(reactContext);
    }

    public void doFrameGuarded(long frameTimeNanos) {
        Systrace.beginSection(0, "dispatchNonBatchedUIOperations");
        try {
            dispatchPendingNonBatchedOperations(frameTimeNanos);
            UIViewOperationQueue.access$1500(this.this$0);
            ReactChoreographer.getInstance().postFrameCallback(CallbackType.DISPATCH_UI, this);
        } finally {
            Systrace.endSection(0);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void dispatchPendingNonBatchedOperations(long r12) {
        /*
        r11 = this;
    L_0x0000:
        r4 = 16;
        r6 = java.lang.System.nanoTime();
        r6 = r6 - r12;
        r8 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r6 = r6 / r8;
        r2 = r4 - r6;
        r4 = 8;
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 >= 0) goto L_0x0014;
    L_0x0013:
        return;
    L_0x0014:
        r1 = r11.this$0;
        r4 = com.facebook.react.uimanager.UIViewOperationQueue.access$1600(r1);
        monitor-enter(r4);
        r1 = r11.this$0;	 Catch:{ all -> 0x0029 }
        r1 = com.facebook.react.uimanager.UIViewOperationQueue.access$1700(r1);	 Catch:{ all -> 0x0029 }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x0029 }
        if (r1 == 0) goto L_0x002c;
    L_0x0027:
        monitor-exit(r4);	 Catch:{ all -> 0x0029 }
        goto L_0x0013;
    L_0x0029:
        r1 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0029 }
        throw r1;
    L_0x002c:
        r1 = r11.this$0;	 Catch:{ all -> 0x0029 }
        r1 = com.facebook.react.uimanager.UIViewOperationQueue.access$1700(r1);	 Catch:{ all -> 0x0029 }
        r0 = r1.pollFirst();	 Catch:{ all -> 0x0029 }
        r0 = (com.facebook.react.uimanager.UIViewOperationQueue$UIOperation) r0;	 Catch:{ all -> 0x0029 }
        monitor-exit(r4);	 Catch:{ all -> 0x0029 }
        r0.execute();
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.UIViewOperationQueue$DispatchUIFrameCallback.dispatchPendingNonBatchedOperations(long):void");
    }
}
