package com.facebook.react.uimanager;

import android.util.SparseBooleanArray;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import javax.annotation.Nullable;

public class NativeViewHierarchyOptimizer {
    private static final boolean ENABLED = true;
    private final ShadowNodeRegistry mShadowNodeRegistry;
    private final SparseBooleanArray mTagsWithLayoutVisited = new SparseBooleanArray();
    private final UIViewOperationQueue mUIViewOperationQueue;

    private static class NodeIndexPair {
        public final int index;
        public final ReactShadowNode node;

        NodeIndexPair(ReactShadowNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public NativeViewHierarchyOptimizer(UIViewOperationQueue uiViewOperationQueue, ShadowNodeRegistry shadowNodeRegistry) {
        this.mUIViewOperationQueue = uiViewOperationQueue;
        this.mShadowNodeRegistry = shadowNodeRegistry;
    }

    public void handleCreateView(ReactShadowNode node, ThemedReactContext themedContext, @Nullable ReactStylesDiffMap initialProps) {
        boolean isLayoutOnly = node.getViewClass().equals("RCTView") && isLayoutOnlyAndCollapsable(initialProps);
        node.setIsLayoutOnly(isLayoutOnly);
        if (!isLayoutOnly) {
            this.mUIViewOperationQueue.enqueueCreateView(themedContext, node.getReactTag(), node.getViewClass(), initialProps);
        }
    }

    public static void handleRemoveNode(ReactShadowNode node) {
        node.removeAllNativeChildren();
    }

    public void handleUpdateView(ReactShadowNode node, String className, ReactStylesDiffMap props) {
        boolean needsToLeaveLayoutOnly = node.isLayoutOnly() && !isLayoutOnlyAndCollapsable(props);
        if (needsToLeaveLayoutOnly) {
            transitionLayoutOnlyViewToNativeView(node, props);
        } else if (!node.isLayoutOnly()) {
            this.mUIViewOperationQueue.enqueueUpdateProperties(node.getReactTag(), className, props);
        }
    }

    public void handleManageChildren(ReactShadowNode nodeToManage, int[] indicesToRemove, int[] tagsToRemove, ViewAtIndex[] viewsToAdd, int[] tagsToDelete) {
        for (int tagToRemove : tagsToRemove) {
            boolean delete = false;
            for (int i : tagsToDelete) {
                if (i == tagToRemove) {
                    delete = true;
                    break;
                }
            }
            removeNodeFromParent(this.mShadowNodeRegistry.getNode(tagToRemove), delete);
        }
        for (ViewAtIndex toAdd : viewsToAdd) {
            addNodeToNode(nodeToManage, this.mShadowNodeRegistry.getNode(toAdd.mTag), toAdd.mIndex);
        }
    }

    public void handleSetChildren(ReactShadowNode nodeToManage, ReadableArray childrenTags) {
        for (int i = 0; i < childrenTags.size(); i++) {
            addNodeToNode(nodeToManage, this.mShadowNodeRegistry.getNode(childrenTags.getInt(i)), i);
        }
    }

    public void handleUpdateLayout(ReactShadowNode node) {
        applyLayoutBase(node);
    }

    public void onBatchComplete() {
        this.mTagsWithLayoutVisited.clear();
    }

    private NodeIndexPair walkUpUntilNonLayoutOnly(ReactShadowNode node, int indexInNativeChildren) {
        while (node.isLayoutOnly()) {
            ReactShadowNode parent = node.getParent();
            if (parent == null) {
                return null;
            }
            indexInNativeChildren += parent.getNativeOffsetForChild(node);
            node = parent;
        }
        return new NodeIndexPair(node, indexInNativeChildren);
    }

    private void addNodeToNode(ReactShadowNode parent, ReactShadowNode child, int index) {
        int indexInNativeChildren = parent.getNativeOffsetForChild(parent.getChildAt(index));
        if (parent.isLayoutOnly()) {
            NodeIndexPair result = walkUpUntilNonLayoutOnly(parent, indexInNativeChildren);
            if (result != null) {
                parent = result.node;
                indexInNativeChildren = result.index;
            } else {
                return;
            }
        }
        if (child.isLayoutOnly()) {
            addLayoutOnlyNode(parent, child, indexInNativeChildren);
        } else {
            addNonLayoutNode(parent, child, indexInNativeChildren);
        }
    }

    private void removeNodeFromParent(ReactShadowNode nodeToRemove, boolean shouldDelete) {
        ReactShadowNode nativeNodeToRemoveFrom = nodeToRemove.getNativeParent();
        if (nativeNodeToRemoveFrom != null) {
            nativeNodeToRemoveFrom.removeNativeChildAt(nativeNodeToRemoveFrom.indexOfNativeChild(nodeToRemove));
            this.mUIViewOperationQueue.enqueueManageChildren(nativeNodeToRemoveFrom.getReactTag(), new int[]{nativeNodeToRemoveFrom.indexOfNativeChild(nodeToRemove)}, null, shouldDelete ? new int[]{nodeToRemove.getReactTag()} : null);
            return;
        }
        for (int i = nodeToRemove.getChildCount() - 1; i >= 0; i--) {
            removeNodeFromParent(nodeToRemove.getChildAt(i), shouldDelete);
        }
    }

    private void addLayoutOnlyNode(ReactShadowNode nonLayoutOnlyNode, ReactShadowNode layoutOnlyNode, int index) {
        addGrandchildren(nonLayoutOnlyNode, layoutOnlyNode, index);
    }

    private void addNonLayoutNode(ReactShadowNode parent, ReactShadowNode child, int index) {
        parent.addNativeChildAt(child, index);
        this.mUIViewOperationQueue.enqueueManageChildren(parent.getReactTag(), null, new ViewAtIndex[]{new ViewAtIndex(child.getReactTag(), index)}, null);
    }

    private void addGrandchildren(ReactShadowNode nativeParent, ReactShadowNode child, int index) {
        boolean z;
        if (nativeParent.isLayoutOnly()) {
            z = false;
        } else {
            z = true;
        }
        Assertions.assertCondition(z);
        int currentIndex = index;
        for (int i = 0; i < child.getChildCount(); i++) {
            ReactShadowNode grandchild = child.getChildAt(i);
            if (grandchild.getNativeParent() == null) {
                z = true;
            } else {
                z = false;
            }
            Assertions.assertCondition(z);
            if (grandchild.isLayoutOnly()) {
                int grandchildCountBefore = nativeParent.getNativeChildCount();
                addLayoutOnlyNode(nativeParent, grandchild, currentIndex);
                currentIndex += nativeParent.getNativeChildCount() - grandchildCountBefore;
            } else {
                addNonLayoutNode(nativeParent, grandchild, currentIndex);
                currentIndex++;
            }
        }
    }

    private void applyLayoutBase(ReactShadowNode node) {
        int tag = node.getReactTag();
        if (!this.mTagsWithLayoutVisited.get(tag)) {
            this.mTagsWithLayoutVisited.put(tag, true);
            ReactShadowNode parent = node.getParent();
            int x = node.getScreenX();
            int y = node.getScreenY();
            while (parent != null && parent.isLayoutOnly()) {
                x += Math.round(parent.getLayoutX());
                y += Math.round(parent.getLayoutY());
                parent = parent.getParent();
            }
            applyLayoutRecursive(node, x, y);
        }
    }

    private void applyLayoutRecursive(ReactShadowNode toUpdate, int x, int y) {
        if (toUpdate.isLayoutOnly() || toUpdate.getNativeParent() == null) {
            for (int i = 0; i < toUpdate.getChildCount(); i++) {
                ReactShadowNode child = toUpdate.getChildAt(i);
                int childTag = child.getReactTag();
                if (!this.mTagsWithLayoutVisited.get(childTag)) {
                    this.mTagsWithLayoutVisited.put(childTag, true);
                    applyLayoutRecursive(child, child.getScreenX() + x, child.getScreenY() + y);
                }
            }
            return;
        }
        this.mUIViewOperationQueue.enqueueUpdateLayout(toUpdate.getNativeParent().getReactTag(), toUpdate.getReactTag(), x, y, toUpdate.getScreenWidth(), toUpdate.getScreenHeight());
    }

    private void transitionLayoutOnlyViewToNativeView(ReactShadowNode node, @Nullable ReactStylesDiffMap props) {
        boolean z = false;
        ReactShadowNode parent = node.getParent();
        if (parent == null) {
            node.setIsLayoutOnly(false);
            return;
        }
        int i;
        int childIndex = parent.indexOf(node);
        parent.removeChildAt(childIndex);
        removeNodeFromParent(node, false);
        node.setIsLayoutOnly(false);
        this.mUIViewOperationQueue.enqueueCreateView(node.getRootNode().getThemedContext(), node.getReactTag(), node.getViewClass(), props);
        parent.addChildAt(node, childIndex);
        addNodeToNode(parent, node, childIndex);
        for (i = 0; i < node.getChildCount(); i++) {
            addNodeToNode(node, node.getChildAt(i), i);
        }
        if (this.mTagsWithLayoutVisited.size() == 0) {
            z = true;
        }
        Assertions.assertCondition(z);
        applyLayoutBase(node);
        for (i = 0; i < node.getChildCount(); i++) {
            applyLayoutBase(node.getChildAt(i));
        }
        this.mTagsWithLayoutVisited.clear();
    }

    private static boolean isLayoutOnlyAndCollapsable(@Nullable ReactStylesDiffMap props) {
        if (props == null) {
            return true;
        }
        if (props.hasKey(ViewProps.COLLAPSABLE) && !props.getBoolean(ViewProps.COLLAPSABLE, true)) {
            return false;
        }
        ReadableMapKeySetIterator keyIterator = props.mBackingMap.keySetIterator();
        while (keyIterator.hasNextKey()) {
            if (!ViewProps.isLayoutOnly(props.mBackingMap, keyIterator.nextKey())) {
                return false;
            }
        }
        return true;
    }
}
