package com.facebook.react.uimanager;

import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.util.ArrayList;

class UIViewOperationQueue$2 implements Runnable {
    final /* synthetic */ UIViewOperationQueue this$0;
    final /* synthetic */ int val$batchId;
    final /* synthetic */ UIViewOperationQueue$UIOperation[] val$nonBatchedOperations;
    final /* synthetic */ ArrayList val$operations;

    UIViewOperationQueue$2(UIViewOperationQueue this$0, int i, UIViewOperationQueue$UIOperation[] uIViewOperationQueue$UIOperationArr, ArrayList arrayList) {
        this.this$0 = this$0;
        this.val$batchId = i;
        this.val$nonBatchedOperations = uIViewOperationQueue$UIOperationArr;
        this.val$operations = arrayList;
    }

    public void run() {
        SystraceMessage.beginSection(0, "DispatchUI").arg("BatchId", this.val$batchId).flush();
        try {
            if (this.val$nonBatchedOperations != null) {
                for (UIViewOperationQueue$UIOperation op : this.val$nonBatchedOperations) {
                    op.execute();
                }
            }
            if (this.val$operations != null) {
                for (int i = 0; i < this.val$operations.size(); i++) {
                    ((UIViewOperationQueue$UIOperation) this.val$operations.get(i)).execute();
                }
            }
            UIViewOperationQueue.access$000(this.this$0).clearLayoutAnimation();
            if (UIViewOperationQueue.access$1400(this.this$0) != null) {
                UIViewOperationQueue.access$1400(this.this$0).onViewHierarchyUpdateFinished();
            }
            Systrace.endSection(0);
        } catch (Throwable th) {
            Systrace.endSection(0);
        }
    }
}
