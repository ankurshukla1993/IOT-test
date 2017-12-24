package com.google.common.collect;

import com.google.common.collect.TreeMultiset.AvlNode;
import javax.annotation.Nullable;

enum TreeMultiset$Aggregate {
    SIZE {
        int nodeAggregate(AvlNode<?> node) {
            return AvlNode.access$200(node);
        }

        long treeAggregate(@Nullable AvlNode<?> root) {
            return root == null ? 0 : AvlNode.access$300(root);
        }
    },
    DISTINCT {
        int nodeAggregate(AvlNode<?> avlNode) {
            return 1;
        }

        long treeAggregate(@Nullable AvlNode<?> root) {
            return root == null ? 0 : (long) AvlNode.access$400(root);
        }
    };

    abstract int nodeAggregate(AvlNode<?> avlNode);

    abstract long treeAggregate(@Nullable AvlNode<?> avlNode);
}
