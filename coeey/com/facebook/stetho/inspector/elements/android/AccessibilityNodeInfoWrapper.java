package com.facebook.stetho.inspector.elements.android;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import com.facebook.stetho.common.android.AccessibilityUtil;

public final class AccessibilityNodeInfoWrapper {
    public static AccessibilityNodeInfoCompat createNodeInfoFromView(View view) {
        AccessibilityNodeInfoCompat nodeInfo = AccessibilityNodeInfoCompat.obtain();
        ViewCompat.onInitializeAccessibilityNodeInfo(view, nodeInfo);
        return nodeInfo;
    }

    public static boolean getIsAccessibilityFocused(View view) {
        AccessibilityNodeInfoCompat node = createNodeInfoFromView(view);
        boolean isAccessibilityFocused = node.isAccessibilityFocused();
        node.recycle();
        return isAccessibilityFocused;
    }

    public static boolean getIgnored(View view) {
        int important = ViewCompat.getImportantForAccessibility(view);
        if (important == 2 || important == 4) {
            return true;
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (ViewCompat.getImportantForAccessibility((View) parent) == 4) {
                return true;
            }
        }
        AccessibilityNodeInfoCompat node = createNodeInfoFromView(view);
        try {
            if (!node.isVisibleToUser()) {
                return true;
            }
            if (AccessibilityUtil.isAccessibilityFocusable(node, view)) {
                if (node.getChildCount() <= 0) {
                    node.recycle();
                    return false;
                } else if (AccessibilityUtil.isSpeakingNode(node, view)) {
                    node.recycle();
                    return false;
                } else {
                    node.recycle();
                    return true;
                }
            } else if (AccessibilityUtil.hasFocusableAncestor(node, view) || !AccessibilityUtil.hasText(node)) {
                node.recycle();
                return true;
            } else {
                node.recycle();
                return false;
            }
        } finally {
            node.recycle();
        }
    }

    public static String getIgnoredReasons(View view) {
        int important = ViewCompat.getImportantForAccessibility(view);
        if (important == 2) {
            return "View has importantForAccessibility set to 'NO'.";
        }
        if (important == 4) {
            return "View has importantForAccessibility set to 'NO_HIDE_DESCENDANTS'.";
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (ViewCompat.getImportantForAccessibility((View) parent) == 4) {
                return "An ancestor View has importantForAccessibility set to 'NO_HIDE_DESCENDANTS'.";
            }
        }
        AccessibilityNodeInfoCompat node = createNodeInfoFromView(view);
        try {
            String str;
            if (!node.isVisibleToUser()) {
                str = "View is not visible.";
                return str;
            } else if (AccessibilityUtil.isAccessibilityFocusable(node, view)) {
                str = "View is actionable, but has no description.";
                node.recycle();
                return str;
            } else if (AccessibilityUtil.hasText(node)) {
                str = "View is not actionable, and an ancestor View has co-opted its description.";
                node.recycle();
                return str;
            } else {
                str = "View is not actionable and has no description.";
                node.recycle();
                return str;
            }
        } finally {
            node.recycle();
        }
    }

    @Nullable
    public static String getFocusableReasons(View view) {
        AccessibilityNodeInfoCompat node = createNodeInfoFromView(view);
        try {
            String str;
            boolean hasText = AccessibilityUtil.hasText(node);
            boolean isCheckable = node.isCheckable();
            boolean hasNonActionableSpeakingDescendants = AccessibilityUtil.hasNonActionableSpeakingDescendants(node, view);
            if (AccessibilityUtil.isActionableForAccessibility(node)) {
                if (node.getChildCount() <= 0) {
                    str = "View is actionable and has no children.";
                    return str;
                } else if (hasText) {
                    str = "View is actionable and has a description.";
                    node.recycle();
                    return str;
                } else if (isCheckable) {
                    str = "View is actionable and checkable.";
                    node.recycle();
                    return str;
                } else if (hasNonActionableSpeakingDescendants) {
                    str = "View is actionable and has non-actionable descendants with descriptions.";
                    node.recycle();
                    return str;
                }
            }
            if (AccessibilityUtil.isTopLevelScrollItem(node, view)) {
                if (hasText) {
                    str = "View is a direct child of a scrollable container and has a description.";
                    node.recycle();
                    return str;
                } else if (isCheckable) {
                    str = "View is a direct child of a scrollable container and is checkable.";
                    node.recycle();
                    return str;
                } else if (hasNonActionableSpeakingDescendants) {
                    str = "View is a direct child of a scrollable container and has non-actionable descendants with descriptions.";
                    node.recycle();
                    return str;
                }
            }
            if (hasText) {
                str = "View has a description and is not actionable, but has no actionable ancestor.";
                node.recycle();
                return str;
            }
            node.recycle();
            return null;
        } finally {
            node.recycle();
        }
    }

    @Nullable
    public static String getActions(View view) {
        AccessibilityNodeInfoCompat node = createNodeInfoFromView(view);
        try {
            String stringBuilder;
            StringBuilder actionLabels = new StringBuilder();
            String separator = ", ";
            for (AccessibilityActionCompat action : node.getActionList()) {
                if (actionLabels.length() > 0) {
                    actionLabels.append(", ");
                }
                switch (action.getId()) {
                    case 1:
                        actionLabels.append("focus");
                        break;
                    case 2:
                        actionLabels.append("clear-focus");
                        break;
                    case 4:
                        actionLabels.append("select");
                        break;
                    case 8:
                        actionLabels.append("clear-selection");
                        break;
                    case 16:
                        actionLabels.append("click");
                        break;
                    case 32:
                        actionLabels.append("long-click");
                        break;
                    case 64:
                        actionLabels.append("accessibility-focus");
                        break;
                    case 128:
                        actionLabels.append("clear-accessibility-focus");
                        break;
                    case 256:
                        actionLabels.append("next-at-movement-granularity");
                        break;
                    case 512:
                        actionLabels.append("previous-at-movement-granularity");
                        break;
                    case 1024:
                        actionLabels.append("next-html-element");
                        break;
                    case 2048:
                        actionLabels.append("previous-html-element");
                        break;
                    case 4096:
                        actionLabels.append("scroll-forward");
                        break;
                    case 8192:
                        actionLabels.append("scroll-backward");
                        break;
                    case 16384:
                        actionLabels.append("copy");
                        break;
                    case 32768:
                        actionLabels.append("paste");
                        break;
                    case 65536:
                        actionLabels.append("cut");
                        break;
                    case 131072:
                        actionLabels.append("set-selection");
                        break;
                    default:
                        CharSequence label = action.getLabel();
                        if (label == null) {
                            actionLabels.append("unknown");
                            break;
                        }
                        actionLabels.append(label);
                        break;
                }
            }
            if (actionLabels.length() > 0) {
                stringBuilder = actionLabels.toString();
            } else {
                stringBuilder = null;
            }
            node.recycle();
            return stringBuilder;
        } catch (Throwable th) {
            node.recycle();
        }
    }

    @Nullable
    public static CharSequence getDescription(View view) {
        CharSequence charSequence = null;
        AccessibilityNodeInfoCompat node = createNodeInfoFromView(view);
        try {
            CharSequence contentDescription = node.getContentDescription();
            CharSequence nodeText = node.getText();
            boolean hasNodeText = !TextUtils.isEmpty(nodeText);
            boolean isEditText = view instanceof EditText;
            if (!TextUtils.isEmpty(contentDescription) && (!isEditText || !hasNodeText)) {
                node.recycle();
                return contentDescription;
            } else if (hasNodeText) {
                node.recycle();
                return nodeText;
            } else if (view instanceof ViewGroup) {
                StringBuilder concatChildDescription = new StringBuilder();
                String separator = ", ";
                ViewGroup viewGroup = (ViewGroup) view;
                int count = viewGroup.getChildCount();
                for (int i = 0; i < count; i++) {
                    View child = viewGroup.getChildAt(i);
                    AccessibilityNodeInfoCompat childNodeInfo = AccessibilityNodeInfoCompat.obtain();
                    ViewCompat.onInitializeAccessibilityNodeInfo(child, childNodeInfo);
                    CharSequence childNodeDescription = null;
                    if (AccessibilityUtil.isSpeakingNode(childNodeInfo, child) && !AccessibilityUtil.isAccessibilityFocusable(childNodeInfo, child)) {
                        childNodeDescription = getDescription(child);
                    }
                    if (!TextUtils.isEmpty(childNodeDescription)) {
                        if (concatChildDescription.length() > 0) {
                            concatChildDescription.append(", ");
                        }
                        concatChildDescription.append(childNodeDescription);
                    }
                    childNodeInfo.recycle();
                }
                if (concatChildDescription.length() > 0) {
                    charSequence = concatChildDescription.toString();
                }
                node.recycle();
                return charSequence;
            } else {
                node.recycle();
                return null;
            }
        } catch (Throwable th) {
            node.recycle();
        }
    }
}
