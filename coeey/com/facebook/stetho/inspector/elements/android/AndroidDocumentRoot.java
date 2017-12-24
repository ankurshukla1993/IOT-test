package com.facebook.stetho.inspector.elements.android;

import android.app.Application;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.NodeType;

final class AndroidDocumentRoot extends AbstractChainedDescriptor<AndroidDocumentRoot> {
    private final Application mApplication;

    public AndroidDocumentRoot(Application application) {
        this.mApplication = (Application) Util.throwIfNull(application);
    }

    protected NodeType onGetNodeType(AndroidDocumentRoot element) {
        return NodeType.DOCUMENT_NODE;
    }

    protected String onGetNodeName(AndroidDocumentRoot element) {
        return "root";
    }

    protected void onGetChildren(AndroidDocumentRoot element, Accumulator<Object> children) {
        children.store(this.mApplication);
    }
}
