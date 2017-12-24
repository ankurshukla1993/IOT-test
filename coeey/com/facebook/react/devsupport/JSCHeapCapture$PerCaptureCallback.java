package com.facebook.react.devsupport;

import java.io.File;

interface JSCHeapCapture$PerCaptureCallback {
    void onFailure(JSCHeapCapture$CaptureException jSCHeapCapture$CaptureException);

    void onSuccess(File file);
}
