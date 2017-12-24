package com.facebook.stetho.inspector.elements;

final class Document$ProviderListener implements DocumentProviderListener {
    final /* synthetic */ Document this$0;

    private Document$ProviderListener(Document document) {
        this.this$0 = document;
    }

    public void onPossiblyChanged() {
        Document.access$1200(this.this$0);
    }

    public void onAttributeModified(Object element, String name, String value) {
        this.this$0.verifyThreadAccess();
        Document.access$700(this.this$0).onAttributeModified(element, name, value);
    }

    public void onAttributeRemoved(Object element, String name) {
        this.this$0.verifyThreadAccess();
        Document.access$700(this.this$0).onAttributeRemoved(element, name);
    }

    public void onInspectRequested(Object element) {
        this.this$0.verifyThreadAccess();
        Document.access$700(this.this$0).onInspectRequested(element);
    }
}
