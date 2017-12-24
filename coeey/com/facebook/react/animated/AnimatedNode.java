package com.facebook.react.animated;

import com.facebook.infer.annotation.Assertions;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

abstract class AnimatedNode {
    private static final int DEFAULT_ANIMATED_NODE_CHILD_COUNT = 1;
    public static final int INITIAL_BFS_COLOR = 0;
    int mActiveIncomingNodes = 0;
    int mBFSColor = 0;
    @Nullable
    List<AnimatedNode> mChildren;
    int mTag = -1;

    AnimatedNode() {
    }

    public final void addChild(AnimatedNode child) {
        if (this.mChildren == null) {
            this.mChildren = new ArrayList(1);
        }
        ((List) Assertions.assertNotNull(this.mChildren)).add(child);
        child.onAttachedToNode(this);
    }

    public final void removeChild(AnimatedNode child) {
        if (this.mChildren != null) {
            child.onDetachedFromNode(this);
            this.mChildren.remove(child);
        }
    }

    public void onAttachedToNode(AnimatedNode parent) {
    }

    public void onDetachedFromNode(AnimatedNode parent) {
    }

    public void update() {
    }
}
