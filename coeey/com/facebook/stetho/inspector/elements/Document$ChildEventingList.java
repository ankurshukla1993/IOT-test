package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import java.util.ArrayList;

final class Document$ChildEventingList extends ArrayList<Object> {
    private DocumentView mDocumentView;
    private Object mParentElement;
    private int mParentNodeId;
    final /* synthetic */ Document this$0;

    private Document$ChildEventingList(Document document) {
        this.this$0 = document;
        this.mParentElement = null;
        this.mParentNodeId = -1;
    }

    public void acquire(Object parentElement, DocumentView documentView) {
        int i;
        this.mParentElement = parentElement;
        if (this.mParentElement == null) {
            i = -1;
        } else {
            i = Document.access$500(this.this$0).getIdForObject(this.mParentElement).intValue();
        }
        this.mParentNodeId = i;
        this.mDocumentView = documentView;
    }

    public void release() {
        clear();
        this.mParentElement = null;
        this.mParentNodeId = -1;
        this.mDocumentView = null;
    }

    public void addWithEvent(int index, Object element, Accumulator<Object> insertedElements) {
        int previousNodeId;
        Object previousElement = index == 0 ? null : get(index - 1);
        if (previousElement == null) {
            previousNodeId = -1;
        } else {
            previousNodeId = Document.access$500(this.this$0).getIdForObject(previousElement).intValue();
        }
        add(index, element);
        Document.access$700(this.this$0).onChildNodeInserted(this.mDocumentView, element, this.mParentNodeId, previousNodeId, insertedElements);
    }

    public void removeWithEvent(int index) {
        Document.access$700(this.this$0).onChildNodeRemoved(this.mParentNodeId, Document.access$500(this.this$0).getIdForObject(remove(index)).intValue());
    }
}
