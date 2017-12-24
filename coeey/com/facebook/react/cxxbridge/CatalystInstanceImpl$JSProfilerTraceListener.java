package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.Systrace;
import com.facebook.systrace.TraceListener;
import java.lang.ref.WeakReference;

class CatalystInstanceImpl$JSProfilerTraceListener implements TraceListener {
    private final WeakReference<CatalystInstanceImpl> mOuter;

    public CatalystInstanceImpl$JSProfilerTraceListener(CatalystInstanceImpl outer) {
        this.mOuter = new WeakReference(outer);
    }

    public void onTraceStarted() {
        CatalystInstanceImpl impl = (CatalystInstanceImpl) this.mOuter.get();
        if (impl != null) {
            ((Systrace) impl.getJSModule(Systrace.class)).setEnabled(true);
        }
    }

    public void onTraceStopped() {
        CatalystInstanceImpl impl = (CatalystInstanceImpl) this.mOuter.get();
        if (impl != null) {
            ((Systrace) impl.getJSModule(Systrace.class)).setEnabled(false);
        }
    }
}
