package com.facebook.stetho.common.android;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.Spinner;
import java.util.List;

public final class AccessibilityUtil {
    private AccessibilityUtil() {
    }

    public static boolean hasText(@Nullable AccessibilityNodeInfoCompat node) {
        if (node == null) {
            return false;
        }
        if (TextUtils.isEmpty(node.getText()) && TextUtils.isEmpty(node.getContentDescription())) {
            return false;
        }
        return true;
    }

    public static boolean isSpeakingNode(@Nullable AccessibilityNodeInfoCompat node, @Nullable View view) {
        if (node == null || view == null || !node.isVisibleToUser()) {
            return false;
        }
        int important = ViewCompat.getImportantForAccessibility(view);
        if (important == 4) {
            return false;
        }
        if (important == 2 && node.getChildCount() <= 0) {
            return false;
        }
        if (node.isCheckable() || hasText(node) || hasNonActionableSpeakingDescendants(node, view)) {
            return true;
        }
        return false;
    }

    public static boolean hasNonActionableSpeakingDescendants(@Nullable AccessibilityNodeInfoCompat node, @Nullable View view) {
        boolean z = false;
        if (node == null || view == null || !(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = viewGroup.getChildAt(i);
            if (childView != null) {
                AccessibilityNodeInfoCompat childNode = AccessibilityNodeInfoCompat.obtain();
                try {
                    ViewCompat.onInitializeAccessibilityNodeInfo(childView, childNode);
                    if (!isAccessibilityFocusable(childNode, childView)) {
                        if (isSpeakingNode(childNode, childView)) {
                            childNode.recycle();
                            return true;
                        }
                        childNode.recycle();
                    }
                } finally {
                    childNode.recycle();
                }
            }
        }
        return z;
    }

    public static boolean isAccessibilityFocusable(@Nullable AccessibilityNodeInfoCompat node, @Nullable View view) {
        boolean z = true;
        if (node == null || view == null || !node.isVisibleToUser()) {
            return false;
        }
        if (isActionableForAccessibility(node)) {
            return true;
        }
        if (!(isTopLevelScrollItem(node, view) && isSpeakingNode(node, view))) {
            z = false;
        }
        return z;
    }

    public static boolean isTopLevelScrollItem(@Nullable AccessibilityNodeInfoCompat node, @Nullable View view) {
        if (node == null || view == null) {
            return false;
        }
        View parent = (View) ViewCompat.getParentForAccessibility(view);
        if (parent == null) {
            return false;
        }
        if (node.isScrollable()) {
            return true;
        }
        List actionList = node.getActionList();
        if (actionList.contains(Integer.valueOf(4096)) || actionList.contains(Integer.valueOf(8192))) {
            return true;
        }
        if (parent instanceof Spinner) {
            return false;
        }
        if ((parent instanceof AdapterView) || (parent instanceof ScrollView) || (parent instanceof HorizontalScrollView)) {
            return true;
        }
        return false;
    }

    public static boolean isActionableForAccessibility(@Nullable AccessibilityNodeInfoCompat node) {
        if (node == null) {
            return false;
        }
        if (node.isClickable() || node.isLongClickable() || node.isFocusable()) {
            return true;
        }
        List actionList = node.getActionList();
        if (actionList.contains(Integer.valueOf(16)) || actionList.contains(Integer.valueOf(32)) || actionList.contains(Integer.valueOf(1))) {
            return true;
        }
        return false;
    }

    public static boolean hasFocusableAncestor(@Nullable AccessibilityNodeInfoCompat node, @Nullable View view) {
        if (node == null || view == null) {
            return false;
        }
        ViewParent parentView = ViewCompat.getParentForAccessibility(view);
        if (!(parentView instanceof View)) {
            return false;
        }
        AccessibilityNodeInfoCompat parentNode = AccessibilityNodeInfoCompat.obtain();
        try {
            ViewCompat.onInitializeAccessibilityNodeInfo((View) parentView, parentNode);
            if (parentNode == null) {
                return false;
            }
            if (isAccessibilityFocusable(parentNode, (View) parentView)) {
                parentNode.recycle();
                return true;
            } else if (hasFocusableAncestor(parentNode, (View) parentView)) {
                parentNode.recycle();
                return true;
            } else {
                parentNode.recycle();
                return false;
            }
        } finally {
            parentNode.recycle();
        }
    }
}
