package com.facebook.react.devsupport;

import java.io.File;
import java.util.LinkedList;

class JSCHeapCapture$1 implements JSCHeapCapture$PerCaptureCallback {
    final /* synthetic */ JSCHeapCapture$CaptureCallback val$callback;
    final /* synthetic */ LinkedList val$captureFailures;
    final /* synthetic */ LinkedList val$captureFiles;
    final /* synthetic */ int val$numRegisteredDumpers;

    JSCHeapCapture$1(LinkedList linkedList, LinkedList linkedList2, int i, JSCHeapCapture$CaptureCallback jSCHeapCapture$CaptureCallback) {
        this.val$captureFiles = linkedList;
        this.val$captureFailures = linkedList2;
        this.val$numRegisteredDumpers = i;
        this.val$callback = jSCHeapCapture$CaptureCallback;
    }

    public void onSuccess(File capture) {
        this.val$captureFiles.add(capture);
        if (this.val$captureFiles.size() + this.val$captureFailures.size() == this.val$numRegisteredDumpers) {
            this.val$callback.onComplete(this.val$captureFiles, this.val$captureFailures);
        }
    }

    public void onFailure(JSCHeapCapture$CaptureException cause) {
        this.val$captureFailures.add(cause);
        if (this.val$captureFiles.size() + this.val$captureFailures.size() == this.val$numRegisteredDumpers) {
            this.val$callback.onComplete(this.val$captureFiles, this.val$captureFailures);
        }
    }
}
