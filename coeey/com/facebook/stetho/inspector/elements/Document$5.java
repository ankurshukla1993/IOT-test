package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.ShadowDocument.Update;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class Document$5 implements Accumulator<Object> {
    private Accumulator<Object> insertedElements = new C14391();
    private final HashSet<Object> listenerInsertedElements = new HashSet();
    final /* synthetic */ Document this$0;
    final /* synthetic */ Update val$docUpdate;

    class C14391 implements Accumulator<Object> {
        C14391() {
        }

        public void store(Object element) {
            if (Document$5.this.val$docUpdate.isElementChanged(element)) {
                Document$5.this.listenerInsertedElements.add(element);
            }
        }
    }

    Document$5(Document this$0, Update update) {
        this.this$0 = this$0;
        this.val$docUpdate = update;
    }

    public void store(Object element) {
        if (Document.access$500(this.this$0).containsObject(element) && !this.listenerInsertedElements.contains(element)) {
            List<Object> oldChildren;
            ElementInfo oldElementInfo = Document.access$100(this.this$0).getElementInfo(element);
            ElementInfo newElementInfo = this.val$docUpdate.getElementInfo(element);
            if (oldElementInfo != null) {
                oldChildren = oldElementInfo.children;
            } else {
                oldChildren = Collections.emptyList();
            }
            List<Object> newChildren = newElementInfo.children;
            Document$ChildEventingList listenerChildren = Document.access$900(this.this$0, element, this.val$docUpdate);
            int N = oldChildren.size();
            for (int i = 0; i < N; i++) {
                Object childElement = oldChildren.get(i);
                if (Document.access$500(this.this$0).containsObject(childElement)) {
                    ElementInfo newChildElementInfo = this.val$docUpdate.getElementInfo(childElement);
                    if (newChildElementInfo == null || newChildElementInfo.parentElement == element) {
                        listenerChildren.add(childElement);
                    }
                }
            }
            Document.access$1000(listenerChildren, newChildren, this.insertedElements);
            Document.access$1100(this.this$0, listenerChildren);
        }
    }
}
