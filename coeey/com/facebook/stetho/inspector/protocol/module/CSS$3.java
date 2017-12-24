package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import com.facebook.stetho.inspector.protocol.module.CSS.CSSProperty;
import com.facebook.stetho.inspector.protocol.module.CSS.SetPropertyTextResult;

class CSS$3 implements Runnable {
    final /* synthetic */ CSS this$0;
    final /* synthetic */ String val$key;
    final /* synthetic */ int val$nodeId;
    final /* synthetic */ SetPropertyTextResult val$result;
    final /* synthetic */ String val$ruleName;
    final /* synthetic */ String val$value;

    class C14531 implements StyleAccumulator {
        C14531() {
        }

        public void store(String name, String value, boolean isDefault) {
            CSSProperty property = new CSSProperty(null);
            property.name = name;
            property.value = value;
            CSS$3.this.val$result.style.cssProperties.add(property);
        }
    }

    CSS$3(CSS this$0, int i, String str, String str2, String str3, SetPropertyTextResult setPropertyTextResult) {
        this.this$0 = this$0;
        this.val$nodeId = i;
        this.val$key = str;
        this.val$ruleName = str2;
        this.val$value = str3;
        this.val$result = setPropertyTextResult;
    }

    public void run() {
        Object elementForNodeId = CSS.access$200(this.this$0).getElementForNodeId(this.val$nodeId);
        if (elementForNodeId == null) {
            LogUtil.m201w("Failed to get style of an element that does not exist, nodeid=" + this.val$nodeId);
            return;
        }
        if (this.val$key != null) {
            CSS.access$200(this.this$0).setElementStyle(elementForNodeId, this.val$ruleName, this.val$key, this.val$value);
        }
        CSS.access$200(this.this$0).getElementStyles(elementForNodeId, this.val$ruleName, new C14531());
    }
}
