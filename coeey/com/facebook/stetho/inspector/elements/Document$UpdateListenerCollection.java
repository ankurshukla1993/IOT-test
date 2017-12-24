package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import java.util.ArrayList;
import java.util.List;

class Document$UpdateListenerCollection implements Document$UpdateListener {
    private final List<Document$UpdateListener> mListeners = new ArrayList();
    private volatile Document$UpdateListener[] mListenersSnapshot;
    final /* synthetic */ Document this$0;

    public Document$UpdateListenerCollection(Document document) {
        this.this$0 = document;
    }

    public synchronized void add(Document$UpdateListener listener) {
        this.mListeners.add(listener);
        this.mListenersSnapshot = null;
    }

    public synchronized void remove(Document$UpdateListener listener) {
        this.mListeners.remove(listener);
        this.mListenersSnapshot = null;
    }

    public synchronized void clear() {
        this.mListeners.clear();
        this.mListenersSnapshot = null;
    }

    private Document$UpdateListener[] getListenersSnapshot() {
        Document$UpdateListener[] listenersSnapshot;
        while (true) {
            listenersSnapshot = this.mListenersSnapshot;
            if (listenersSnapshot != null) {
                break;
            }
            synchronized (this) {
                if (this.mListenersSnapshot == null) {
                    break;
                }
            }
        }
        this.mListenersSnapshot = (Document$UpdateListener[]) this.mListeners.toArray(new Document$UpdateListener[this.mListeners.size()]);
        listenersSnapshot = this.mListenersSnapshot;
        return listenersSnapshot;
    }

    public void onAttributeModified(Object element, String name, String value) {
        for (Document$UpdateListener listener : getListenersSnapshot()) {
            listener.onAttributeModified(element, name, value);
        }
    }

    public void onAttributeRemoved(Object element, String name) {
        for (Document$UpdateListener listener : getListenersSnapshot()) {
            listener.onAttributeRemoved(element, name);
        }
    }

    public void onInspectRequested(Object element) {
        for (Document$UpdateListener listener : getListenersSnapshot()) {
            listener.onInspectRequested(element);
        }
    }

    public void onChildNodeRemoved(int parentNodeId, int nodeId) {
        for (Document$UpdateListener listener : getListenersSnapshot()) {
            listener.onChildNodeRemoved(parentNodeId, nodeId);
        }
    }

    public void onChildNodeInserted(DocumentView view, Object element, int parentNodeId, int previousNodeId, Accumulator<Object> insertedItems) {
        for (Document$UpdateListener listener : getListenersSnapshot()) {
            listener.onChildNodeInserted(view, element, parentNodeId, previousNodeId, insertedItems);
        }
    }
}
