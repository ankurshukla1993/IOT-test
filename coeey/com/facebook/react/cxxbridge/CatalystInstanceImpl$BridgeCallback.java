package com.facebook.react.cxxbridge;

import java.lang.ref.WeakReference;

class CatalystInstanceImpl$BridgeCallback implements ReactCallback {
    private final WeakReference<CatalystInstanceImpl> mOuter;

    public CatalystInstanceImpl$BridgeCallback(CatalystInstanceImpl outer) {
        this.mOuter = new WeakReference(outer);
    }

    public void onBatchComplete() {
        CatalystInstanceImpl impl = (CatalystInstanceImpl) this.mOuter.get();
        if (impl != null) {
            CatalystInstanceImpl.access$100(impl).onBatchComplete();
        }
    }

    public void incrementPendingJSCalls() {
        CatalystInstanceImpl impl = (CatalystInstanceImpl) this.mOuter.get();
        if (impl != null) {
            CatalystInstanceImpl.access$200(impl);
        }
    }

    public void decrementPendingJSCalls() {
        CatalystInstanceImpl impl = (CatalystInstanceImpl) this.mOuter.get();
        if (impl != null) {
            CatalystInstanceImpl.access$300(impl);
        }
    }

    public void onNativeException(Exception e) {
        CatalystInstanceImpl impl = (CatalystInstanceImpl) this.mOuter.get();
        if (impl != null) {
            CatalystInstanceImpl.access$400(impl, e);
        }
    }
}
