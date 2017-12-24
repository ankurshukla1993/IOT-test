package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.ShadowDocument.Update;
import java.util.ArrayList;

class Document$3 implements Accumulator<Object> {
    final /* synthetic */ Document this$0;
    final /* synthetic */ Update val$docUpdate;
    final /* synthetic */ ArrayList val$garbageElementIds;

    Document$3(Document this$0, Update update, ArrayList arrayList) {
        this.this$0 = this$0;
        this.val$docUpdate = update;
        this.val$garbageElementIds = arrayList;
    }

    public void store(Object element) {
        Integer nodeId = (Integer) Util.throwIfNull(Document.access$500(this.this$0).getIdForObject(element));
        if (this.val$docUpdate.getElementInfo(element).parentElement == null) {
            Document.access$700(this.this$0).onChildNodeRemoved(Document.access$500(this.this$0).getIdForObject(Document.access$100(this.this$0).getElementInfo(element).parentElement).intValue(), nodeId.intValue());
        }
        this.val$garbageElementIds.add(nodeId);
    }
}
