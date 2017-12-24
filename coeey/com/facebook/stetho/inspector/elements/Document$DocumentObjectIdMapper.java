package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.inspector.helper.ObjectIdMapper;

final class Document$DocumentObjectIdMapper extends ObjectIdMapper {
    final /* synthetic */ Document this$0;

    private Document$DocumentObjectIdMapper(Document document) {
        this.this$0 = document;
    }

    protected void onMapped(Object object, int id) {
        this.this$0.verifyThreadAccess();
        Document.access$200(this.this$0).getNodeDescriptor(object).hook(object);
    }

    protected void onUnmapped(Object object, int id) {
        this.this$0.verifyThreadAccess();
        Document.access$200(this.this$0).getNodeDescriptor(object).unhook(object);
    }
}
