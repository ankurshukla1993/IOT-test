package com.facebook.react.touch;

import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import javax.annotation.Nullable;

public class JSResponderHandler implements OnInterceptTouchEventListener {
    private static final int JS_RESPONDER_UNSET = -1;
    private volatile int mCurrentJSResponder = -1;
    @Nullable
    private ViewParent mViewParentBlockingNativeResponder;

    public void setJSResponder(int tag, @Nullable ViewParent viewParentBlockingNativeResponder) {
        this.mCurrentJSResponder = tag;
        maybeUnblockNativeResponder();
        if (viewParentBlockingNativeResponder != null) {
            viewParentBlockingNativeResponder.requestDisallowInterceptTouchEvent(true);
            this.mViewParentBlockingNativeResponder = viewParentBlockingNativeResponder;
        }
    }

    public void clearJSResponder() {
        this.mCurrentJSResponder = -1;
        maybeUnblockNativeResponder();
    }

    private void maybeUnblockNativeResponder() {
        if (this.mViewParentBlockingNativeResponder != null) {
            this.mViewParentBlockingNativeResponder.requestDisallowInterceptTouchEvent(false);
            this.mViewParentBlockingNativeResponder = null;
        }
    }

    public boolean onInterceptTouchEvent(ViewGroup v, MotionEvent event) {
        int currentJSResponder = this.mCurrentJSResponder;
        if (currentJSResponder == -1 || event.getAction() == 1) {
            return false;
        }
        if (v.getId() == currentJSResponder) {
            return true;
        }
        return false;
    }
}
