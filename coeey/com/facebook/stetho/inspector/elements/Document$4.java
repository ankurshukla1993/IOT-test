package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.ShadowDocument.Update;
import java.util.ArrayList;
import java.util.Collections;

class Document$4 implements Accumulator<Object> {
    final /* synthetic */ Document this$0;
    final /* synthetic */ Update val$docUpdate;
    final /* synthetic */ ArrayList val$garbageElementIds;

    Document$4(Document this$0, ArrayList arrayList, Update update) {
        this.this$0 = this$0;
        this.val$garbageElementIds = arrayList;
        this.val$docUpdate = update;
    }

    public void store(Object element) {
        Integer nodeId = (Integer) Util.throwIfNull(Document.access$500(this.this$0).getIdForObject(element));
        if (Collections.binarySearch(this.val$garbageElementIds, nodeId) < 0) {
            ElementInfo oldElementInfo = Document.access$100(this.this$0).getElementInfo(element);
            if (oldElementInfo != null && this.val$docUpdate.getElementInfo(element).parentElement != oldElementInfo.parentElement) {
                Document.access$700(this.this$0).onChildNodeRemoved(Document.access$500(this.this$0).getIdForObject(oldElementInfo.parentElement).intValue(), nodeId.intValue());
            }
        }
    }
}
