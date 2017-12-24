package com.facebook.react.views.scroll;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;

public class ReactScrollViewHelper {
    public static final String AUTO = "auto";
    public static final long MOMENTUM_DELAY = 20;
    public static final String OVER_SCROLL_ALWAYS = "always";
    public static final String OVER_SCROLL_NEVER = "never";

    public static void emitScrollEvent(ViewGroup scrollView) {
        emitScrollEvent(scrollView, ScrollEventType.SCROLL);
    }

    public static void emitScrollBeginDragEvent(ViewGroup scrollView) {
        emitScrollEvent(scrollView, ScrollEventType.BEGIN_DRAG);
    }

    public static void emitScrollEndDragEvent(ViewGroup scrollView) {
        emitScrollEvent(scrollView, ScrollEventType.END_DRAG);
    }

    public static void emitScrollMomentumBeginEvent(ViewGroup scrollView) {
        emitScrollEvent(scrollView, ScrollEventType.MOMENTUM_BEGIN);
    }

    public static void emitScrollMomentumEndEvent(ViewGroup scrollView) {
        emitScrollEvent(scrollView, ScrollEventType.MOMENTUM_END);
    }

    private static void emitScrollEvent(ViewGroup scrollView, ScrollEventType scrollEventType) {
        View contentView = scrollView.getChildAt(0);
        if (contentView != null) {
            ((UIManagerModule) ((ReactContext) scrollView.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(ScrollEvent.obtain(scrollView.getId(), scrollEventType, scrollView.getScrollX(), scrollView.getScrollY(), contentView.getWidth(), contentView.getHeight(), scrollView.getWidth(), scrollView.getHeight()));
        }
    }

    public static int parseOverScrollMode(String jsOverScrollMode) {
        if (jsOverScrollMode == null || jsOverScrollMode.equals(AUTO)) {
            return 1;
        }
        if (jsOverScrollMode.equals(OVER_SCROLL_ALWAYS)) {
            return 0;
        }
        if (jsOverScrollMode.equals(OVER_SCROLL_NEVER)) {
            return 2;
        }
        throw new JSApplicationIllegalArgumentException("wrong overScrollMode: " + jsOverScrollMode);
    }
}
