package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.Document$UpdateListener;
import com.facebook.stetho.inspector.elements.DocumentView;
import com.facebook.stetho.inspector.protocol.module.DOM.AttributeModifiedEvent;
import com.facebook.stetho.inspector.protocol.module.DOM.AttributeRemovedEvent;
import com.facebook.stetho.inspector.protocol.module.DOM.ChildNodeInsertedEvent;
import com.facebook.stetho.inspector.protocol.module.DOM.ChildNodeRemovedEvent;
import com.facebook.stetho.inspector.protocol.module.DOM.InspectNodeRequestedEvent;

final class DOM$DocumentUpdateListener implements Document$UpdateListener {
    final /* synthetic */ DOM this$0;

    private DOM$DocumentUpdateListener(DOM dom) {
        this.this$0 = dom;
    }

    public void onAttributeModified(Object element, String name, String value) {
        AttributeModifiedEvent message = new AttributeModifiedEvent(null);
        message.nodeId = DOM.access$300(this.this$0).getNodeIdForElement(element).intValue();
        message.name = name;
        message.value = value;
        DOM.access$1200(this.this$0).sendNotificationToPeers("DOM.attributeModified", message);
    }

    public void onAttributeRemoved(Object element, String name) {
        AttributeRemovedEvent message = new AttributeRemovedEvent(null);
        message.nodeId = DOM.access$300(this.this$0).getNodeIdForElement(element).intValue();
        message.name = name;
        DOM.access$1200(this.this$0).sendNotificationToPeers("DOM.attributeRemoved", message);
    }

    public void onInspectRequested(Object element) {
        Integer nodeId = DOM.access$300(this.this$0).getNodeIdForElement(element);
        if (nodeId == null) {
            LogUtil.m186d("DocumentProvider.Listener.onInspectRequested() called for a non-mapped node: element=%s", element);
            return;
        }
        InspectNodeRequestedEvent message = new InspectNodeRequestedEvent(null);
        message.nodeId = nodeId.intValue();
        DOM.access$1200(this.this$0).sendNotificationToPeers("DOM.inspectNodeRequested", message);
    }

    public void onChildNodeRemoved(int parentNodeId, int nodeId) {
        ChildNodeRemovedEvent removedEvent = DOM.access$1500(this.this$0);
        removedEvent.parentNodeId = parentNodeId;
        removedEvent.nodeId = nodeId;
        DOM.access$1200(this.this$0).sendNotificationToPeers("DOM.childNodeRemoved", removedEvent);
        DOM.access$1600(this.this$0, removedEvent);
    }

    public void onChildNodeInserted(DocumentView view, Object element, int parentNodeId, int previousNodeId, Accumulator<Object> insertedElements) {
        ChildNodeInsertedEvent insertedEvent = DOM.access$1700(this.this$0);
        insertedEvent.parentNodeId = parentNodeId;
        insertedEvent.previousNodeId = previousNodeId;
        insertedEvent.node = DOM.access$400(this.this$0, element, view, insertedElements);
        DOM.access$1200(this.this$0).sendNotificationToPeers("DOM.childNodeInserted", insertedEvent);
        DOM.access$1800(this.this$0, insertedEvent);
    }
}
