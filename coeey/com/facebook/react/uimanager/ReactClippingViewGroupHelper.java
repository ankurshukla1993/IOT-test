package com.facebook.react.uimanager;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewParent;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class ReactClippingViewGroupHelper {
    public static final String PROP_REMOVE_CLIPPED_SUBVIEWS = "removeClippedSubviews";
    private static final Rect sHelperRect = new Rect();

    public static void calculateClippingRect(View view, Rect outputRect) {
        ViewParent parent = view.getParent();
        if (parent == null) {
            outputRect.setEmpty();
            return;
        }
        if (parent instanceof ReactClippingViewGroup) {
            ReactClippingViewGroup clippingViewGroup = (ReactClippingViewGroup) parent;
            if (clippingViewGroup.getRemoveClippedSubviews()) {
                clippingViewGroup.getClippingRect(sHelperRect);
                if (sHelperRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                    sHelperRect.offset(-view.getLeft(), -view.getTop());
                    sHelperRect.offset(view.getScrollX(), view.getScrollY());
                    outputRect.set(sHelperRect);
                    return;
                }
                outputRect.setEmpty();
                return;
            }
        }
        view.getDrawingRect(outputRect);
    }
}
