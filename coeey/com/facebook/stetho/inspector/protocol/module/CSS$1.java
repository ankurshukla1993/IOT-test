package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.ComputedStyleAccumulator;
import com.facebook.stetho.inspector.protocol.module.CSS.CSSComputedStyleProperty;
import com.facebook.stetho.inspector.protocol.module.CSS.GetComputedStyleForNodeRequest;
import com.facebook.stetho.inspector.protocol.module.CSS.GetComputedStyleForNodeResult;

class CSS$1 implements Runnable {
    final /* synthetic */ CSS this$0;
    final /* synthetic */ GetComputedStyleForNodeRequest val$request;
    final /* synthetic */ GetComputedStyleForNodeResult val$result;

    class C14501 implements ComputedStyleAccumulator {
        C14501() {
        }

        public void store(String name, String value) {
            CSSComputedStyleProperty property = new CSSComputedStyleProperty(null);
            property.name = name;
            property.value = value;
            CSS$1.this.val$result.computedStyle.add(property);
        }
    }

    CSS$1(CSS this$0, GetComputedStyleForNodeRequest getComputedStyleForNodeRequest, GetComputedStyleForNodeResult getComputedStyleForNodeResult) {
        this.this$0 = this$0;
        this.val$request = getComputedStyleForNodeRequest;
        this.val$result = getComputedStyleForNodeResult;
    }

    public void run() {
        Object element = CSS.access$200(this.this$0).getElementForNodeId(this.val$request.nodeId);
        if (element == null) {
            LogUtil.m189e("Tried to get the style of an element that does not exist, using nodeid=" + this.val$request.nodeId);
        } else {
            CSS.access$200(this.this$0).getElementComputedStyles(element, new C14501());
        }
    }
}
