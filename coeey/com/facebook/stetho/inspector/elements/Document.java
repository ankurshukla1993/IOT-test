package com.facebook.stetho.inspector.elements;

import android.os.SystemClock;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.ArrayListAccumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.ShadowDocument.Update;
import com.facebook.stetho.inspector.elements.ShadowDocument.UpdateBuilder;
import com.facebook.stetho.inspector.helper.ObjectIdMapper;
import com.facebook.stetho.inspector.helper.ThreadBoundProxy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class Document extends ThreadBoundProxy {
    private AttributeListAccumulator mCachedAttributeAccumulator;
    private ChildEventingList mCachedChildEventingList;
    private ArrayListAccumulator<Object> mCachedChildrenAccumulator;
    private final Queue<Object> mCachedUpdateQueue = new ArrayDeque();
    private DocumentProvider mDocumentProvider;
    private final DocumentProviderFactory mFactory;
    private final ObjectIdMapper mObjectIdMapper = new DocumentObjectIdMapper(this, null);
    @GuardedBy("this")
    private int mReferenceCounter = 0;
    private ShadowDocument mShadowDocument;
    private UpdateListenerCollection mUpdateListeners = new UpdateListenerCollection(this);

    public Document(DocumentProviderFactory factory) {
        super(factory);
        this.mFactory = factory;
    }

    public synchronized void addRef() {
        int i = this.mReferenceCounter;
        this.mReferenceCounter = i + 1;
        if (i == 0) {
            init();
        }
    }

    public synchronized void release() {
        if (this.mReferenceCounter > 0) {
            int i = this.mReferenceCounter - 1;
            this.mReferenceCounter = i;
            if (i == 0) {
                cleanUp();
            }
        }
    }

    private void init() {
        this.mDocumentProvider = this.mFactory.create();
        this.mDocumentProvider.postAndWait(new 1(this));
    }

    private void cleanUp() {
        this.mDocumentProvider.postAndWait(new 2(this));
        this.mUpdateListeners.clear();
    }

    public void addUpdateListener(UpdateListener updateListener) {
        this.mUpdateListeners.add(updateListener);
    }

    public void removeUpdateListener(UpdateListener updateListener) {
        this.mUpdateListeners.remove(updateListener);
    }

    @Nullable
    public NodeDescriptor getNodeDescriptor(Object element) {
        verifyThreadAccess();
        return this.mDocumentProvider.getNodeDescriptor(element);
    }

    public void highlightElement(Object element, int color) {
        verifyThreadAccess();
        this.mDocumentProvider.highlightElement(element, color);
    }

    public void hideHighlight() {
        verifyThreadAccess();
        this.mDocumentProvider.hideHighlight();
    }

    public void setInspectModeEnabled(boolean enabled) {
        verifyThreadAccess();
        this.mDocumentProvider.setInspectModeEnabled(enabled);
    }

    @Nullable
    public Integer getNodeIdForElement(Object element) {
        return this.mObjectIdMapper.getIdForObject(element);
    }

    @Nullable
    public Object getElementForNodeId(int id) {
        return this.mObjectIdMapper.getObjectForId(id);
    }

    public void setAttributesAsText(Object element, String text) {
        verifyThreadAccess();
        this.mDocumentProvider.setAttributesAsText(element, text);
    }

    public void getElementStyleRuleNames(Object element, StyleRuleNameAccumulator accumulator) {
        getNodeDescriptor(element).getStyleRuleNames(element, accumulator);
    }

    public void getElementStyles(Object element, String ruleName, StyleAccumulator accumulator) {
        getNodeDescriptor(element).getStyles(element, ruleName, accumulator);
    }

    public void setElementStyle(Object element, String ruleName, String name, String value) {
        getNodeDescriptor(element).setStyle(element, ruleName, name, value);
    }

    public void getElementComputedStyles(Object element, ComputedStyleAccumulator styleAccumulator) {
        getNodeDescriptor(element).getComputedStyles(element, styleAccumulator);
    }

    public DocumentView getDocumentView() {
        verifyThreadAccess();
        return this.mShadowDocument;
    }

    public Object getRootElement() {
        verifyThreadAccess();
        Object rootElement = this.mDocumentProvider.getRootElement();
        if (rootElement == null) {
            throw new IllegalStateException();
        } else if (rootElement == this.mShadowDocument.getRootElement()) {
            return rootElement;
        } else {
            throw new IllegalStateException();
        }
    }

    public void findMatchingElements(String query, Accumulator<Integer> matchedIds) {
        verifyThreadAccess();
        findMatches(this.mDocumentProvider.getRootElement(), Pattern.compile(Pattern.quote(query), 2), matchedIds);
    }

    private void findMatches(Object element, Pattern queryPattern, Accumulator<Integer> matchedIds) {
        ElementInfo info = this.mShadowDocument.getElementInfo(element);
        int size = info.children.size();
        for (int i = 0; i < size; i++) {
            Object childElement = info.children.get(i);
            if (doesElementMatch(childElement, queryPattern)) {
                matchedIds.store(this.mObjectIdMapper.getIdForObject(childElement));
            }
            findMatches(childElement, queryPattern, matchedIds);
        }
    }

    private boolean doesElementMatch(Object element, Pattern queryPattern) {
        AttributeListAccumulator accumulator = acquireCachedAttributeAccumulator();
        NodeDescriptor descriptor = this.mDocumentProvider.getNodeDescriptor(element);
        descriptor.getAttributes(element, accumulator);
        int N = accumulator.size();
        for (int i = 0; i < N; i++) {
            if (queryPattern.matcher((CharSequence) accumulator.get(i)).find()) {
                releaseCachedAttributeAccumulator(accumulator);
                return true;
            }
        }
        releaseCachedAttributeAccumulator(accumulator);
        return queryPattern.matcher(descriptor.getNodeName(element)).find();
    }

    private ChildEventingList acquireChildEventingList(Object parentElement, DocumentView documentView) {
        ChildEventingList childEventingList = this.mCachedChildEventingList;
        if (childEventingList == null) {
            childEventingList = new ChildEventingList(this, null);
        }
        this.mCachedChildEventingList = null;
        childEventingList.acquire(parentElement, documentView);
        return childEventingList;
    }

    private void releaseChildEventingList(ChildEventingList childEventingList) {
        childEventingList.release();
        if (this.mCachedChildEventingList == null) {
            this.mCachedChildEventingList = childEventingList;
        }
    }

    private AttributeListAccumulator acquireCachedAttributeAccumulator() {
        AttributeListAccumulator accumulator = this.mCachedAttributeAccumulator;
        if (accumulator == null) {
            accumulator = new AttributeListAccumulator();
        }
        this.mCachedChildrenAccumulator = null;
        return accumulator;
    }

    private void releaseCachedAttributeAccumulator(AttributeListAccumulator accumulator) {
        accumulator.clear();
        if (this.mCachedAttributeAccumulator == null) {
            this.mCachedAttributeAccumulator = accumulator;
        }
    }

    private ArrayListAccumulator<Object> acquireChildrenAccumulator() {
        ArrayListAccumulator<Object> accumulator = this.mCachedChildrenAccumulator;
        if (accumulator == null) {
            accumulator = new ArrayListAccumulator();
        }
        this.mCachedChildrenAccumulator = null;
        return accumulator;
    }

    private void releaseChildrenAccumulator(ArrayListAccumulator<Object> accumulator) {
        accumulator.clear();
        if (this.mCachedChildrenAccumulator == null) {
            this.mCachedChildrenAccumulator = accumulator;
        }
    }

    private Update createShadowDocumentUpdate() {
        verifyThreadAccess();
        if (this.mDocumentProvider.getRootElement() != this.mShadowDocument.getRootElement()) {
            throw new IllegalStateException();
        }
        ArrayListAccumulator<Object> childrenAccumulator = acquireChildrenAccumulator();
        UpdateBuilder updateBuilder = this.mShadowDocument.beginUpdate();
        this.mCachedUpdateQueue.add(this.mDocumentProvider.getRootElement());
        while (!this.mCachedUpdateQueue.isEmpty()) {
            Object element = this.mCachedUpdateQueue.remove();
            NodeDescriptor descriptor = this.mDocumentProvider.getNodeDescriptor(element);
            this.mObjectIdMapper.putObject(element);
            descriptor.getChildren(element, childrenAccumulator);
            int i = 0;
            int size = childrenAccumulator.size();
            while (i < size) {
                Object child = childrenAccumulator.get(i);
                if (child != null) {
                    this.mCachedUpdateQueue.add(child);
                } else {
                    LogUtil.e("%s.getChildren() emitted a null child at position %s for element %s", new Object[]{descriptor.getClass().getName(), Integer.toString(i), element});
                    childrenAccumulator.remove(i);
                    i--;
                    size--;
                }
                i++;
            }
            updateBuilder.setElementChildren(element, childrenAccumulator);
            childrenAccumulator.clear();
        }
        releaseChildrenAccumulator(childrenAccumulator);
        return updateBuilder.build();
    }

    private void updateTree() {
        long startTimeMs = SystemClock.elapsedRealtime();
        Update docUpdate = createShadowDocumentUpdate();
        boolean isEmpty = docUpdate.isEmpty();
        if (isEmpty) {
            docUpdate.abandon();
        } else {
            applyDocumentUpdate(docUpdate);
        }
        String str = "Document.updateTree() completed in %s ms%s";
        Object[] objArr = new Object[2];
        objArr[0] = Long.toString(SystemClock.elapsedRealtime() - startTimeMs);
        objArr[1] = isEmpty ? " (no changes)" : "";
        LogUtil.d(str, objArr);
    }

    private void applyDocumentUpdate(Update docUpdate) {
        ArrayList<Integer> garbageElementIds = new ArrayList();
        docUpdate.getGarbageElements(new 3(this, docUpdate, garbageElementIds));
        Collections.sort(garbageElementIds);
        docUpdate.getChangedElements(new 4(this, garbageElementIds, docUpdate));
        int N = garbageElementIds.size();
        for (int i = 0; i < N; i++) {
            this.mObjectIdMapper.removeObjectById(((Integer) garbageElementIds.get(i)).intValue());
        }
        docUpdate.getChangedElements(new 5(this, docUpdate));
        docUpdate.commit();
    }

    private static void updateListenerChildren(ChildEventingList listenerChildren, List<Object> newChildren, Accumulator<Object> insertedElements) {
        int index = 0;
        while (index <= listenerChildren.size()) {
            if (index == listenerChildren.size()) {
                if (index != newChildren.size()) {
                    listenerChildren.addWithEvent(index, newChildren.get(index), insertedElements);
                    index++;
                } else {
                    return;
                }
            } else if (index == newChildren.size()) {
                listenerChildren.removeWithEvent(index);
            } else {
                Object listenerElement = listenerChildren.get(index);
                Object newElement = newChildren.get(index);
                if (listenerElement == newElement) {
                    index++;
                } else {
                    int newElementListenerIndex = listenerChildren.indexOf(newElement);
                    if (newElementListenerIndex == -1) {
                        listenerChildren.addWithEvent(index, newElement, insertedElements);
                        index++;
                    } else {
                        listenerChildren.removeWithEvent(newElementListenerIndex);
                        listenerChildren.addWithEvent(index, newElement, insertedElements);
                        index++;
                    }
                }
            }
        }
    }
}
