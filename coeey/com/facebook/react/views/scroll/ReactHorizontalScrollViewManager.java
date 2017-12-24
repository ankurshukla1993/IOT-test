package com.facebook.react.views.scroll;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper.ScrollCommandHandler;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper.ScrollToCommandData;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper.ScrollToEndCommandData;
import javax.annotation.Nullable;

@ReactModule(name = "AndroidHorizontalScrollView")
public class ReactHorizontalScrollViewManager extends ViewGroupManager<ReactHorizontalScrollView> implements ScrollCommandHandler<ReactHorizontalScrollView> {
    protected static final String REACT_CLASS = "AndroidHorizontalScrollView";
    @Nullable
    private FpsListener mFpsListener;

    public ReactHorizontalScrollViewManager() {
        this(null);
    }

    public ReactHorizontalScrollViewManager(@Nullable FpsListener fpsListener) {
        this.mFpsListener = null;
        this.mFpsListener = fpsListener;
    }

    public String getName() {
        return REACT_CLASS;
    }

    public ReactHorizontalScrollView createViewInstance(ThemedReactContext context) {
        return new ReactHorizontalScrollView(context, this.mFpsListener);
    }

    @ReactProp(defaultBoolean = true, name = "scrollEnabled")
    public void setScrollEnabled(ReactHorizontalScrollView view, boolean value) {
        view.setScrollEnabled(value);
    }

    @ReactProp(name = "showsHorizontalScrollIndicator")
    public void setShowsHorizontalScrollIndicator(ReactHorizontalScrollView view, boolean value) {
        view.setHorizontalScrollBarEnabled(value);
    }

    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(ReactHorizontalScrollView view, boolean removeClippedSubviews) {
        view.setRemoveClippedSubviews(removeClippedSubviews);
    }

    @ReactProp(name = "sendMomentumEvents")
    public void setSendMomentumEvents(ReactHorizontalScrollView view, boolean sendMomentumEvents) {
        view.setSendMomentumEvents(sendMomentumEvents);
    }

    @ReactProp(name = "scrollPerfTag")
    public void setScrollPerfTag(ReactHorizontalScrollView view, String scrollPerfTag) {
        view.setScrollPerfTag(scrollPerfTag);
    }

    @ReactProp(name = "pagingEnabled")
    public void setPagingEnabled(ReactHorizontalScrollView view, boolean pagingEnabled) {
        view.setPagingEnabled(pagingEnabled);
    }

    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(ReactHorizontalScrollView view, String value) {
        view.setOverScrollMode(ReactScrollViewHelper.parseOverScrollMode(value));
    }

    public void receiveCommand(ReactHorizontalScrollView scrollView, int commandId, @Nullable ReadableArray args) {
        ReactScrollViewCommandHelper.receiveCommand(this, scrollView, commandId, args);
    }

    public void scrollTo(ReactHorizontalScrollView scrollView, ScrollToCommandData data) {
        if (data.mAnimated) {
            scrollView.smoothScrollTo(data.mDestX, data.mDestY);
        } else {
            scrollView.scrollTo(data.mDestX, data.mDestY);
        }
    }

    public void scrollToEnd(ReactHorizontalScrollView scrollView, ScrollToEndCommandData data) {
        int right = scrollView.getChildAt(0).getWidth() + scrollView.getPaddingRight();
        if (data.mAnimated) {
            scrollView.smoothScrollTo(right, scrollView.getScrollY());
        } else {
            scrollView.scrollTo(right, scrollView.getScrollY());
        }
    }

    @ReactProp(customType = "Color", defaultInt = 0, name = "endFillColor")
    public void setBottomFillColor(ReactHorizontalScrollView view, int color) {
        view.setEndFillColor(color);
    }
}
