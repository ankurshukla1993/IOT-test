package com.facebook.react.views.scroll;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper.ScrollCommandHandler;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper.ScrollToCommandData;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper.ScrollToEndCommandData;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "RCTScrollView")
public class ReactScrollViewManager extends ViewGroupManager<ReactScrollView> implements ScrollCommandHandler<ReactScrollView> {
    protected static final String REACT_CLASS = "RCTScrollView";
    @Nullable
    private FpsListener mFpsListener;

    public ReactScrollViewManager() {
        this(null);
    }

    public ReactScrollViewManager(@Nullable FpsListener fpsListener) {
        this.mFpsListener = null;
        this.mFpsListener = fpsListener;
    }

    public String getName() {
        return REACT_CLASS;
    }

    public ReactScrollView createViewInstance(ThemedReactContext context) {
        return new ReactScrollView(context, this.mFpsListener);
    }

    @ReactProp(defaultBoolean = true, name = "scrollEnabled")
    public void setScrollEnabled(ReactScrollView view, boolean value) {
        view.setScrollEnabled(value);
    }

    @ReactProp(name = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(ReactScrollView view, boolean value) {
        view.setVerticalScrollBarEnabled(value);
    }

    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(ReactScrollView view, boolean removeClippedSubviews) {
        view.setRemoveClippedSubviews(removeClippedSubviews);
    }

    @ReactProp(name = "sendMomentumEvents")
    public void setSendMomentumEvents(ReactScrollView view, boolean sendMomentumEvents) {
        view.setSendMomentumEvents(sendMomentumEvents);
    }

    @ReactProp(name = "scrollPerfTag")
    public void setScrollPerfTag(ReactScrollView view, String scrollPerfTag) {
        view.setScrollPerfTag(scrollPerfTag);
    }

    @ReactProp(customType = "Color", defaultInt = 0, name = "endFillColor")
    public void setBottomFillColor(ReactScrollView view, int color) {
        view.setEndFillColor(color);
    }

    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(ReactScrollView view, String value) {
        view.setOverScrollMode(ReactScrollViewHelper.parseOverScrollMode(value));
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return ReactScrollViewCommandHelper.getCommandsMap();
    }

    public void receiveCommand(ReactScrollView scrollView, int commandId, @Nullable ReadableArray args) {
        ReactScrollViewCommandHelper.receiveCommand(this, scrollView, commandId, args);
    }

    public void scrollTo(ReactScrollView scrollView, ScrollToCommandData data) {
        if (data.mAnimated) {
            scrollView.smoothScrollTo(data.mDestX, data.mDestY);
        } else {
            scrollView.scrollTo(data.mDestX, data.mDestY);
        }
    }

    public void scrollToEnd(ReactScrollView scrollView, ScrollToEndCommandData data) {
        int bottom = scrollView.getChildAt(0).getHeight() + scrollView.getPaddingBottom();
        if (data.mAnimated) {
            scrollView.smoothScrollTo(scrollView.getScrollX(), bottom);
        } else {
            scrollView.scrollTo(scrollView.getScrollX(), bottom);
        }
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return createExportedCustomDirectEventTypeConstants();
    }

    public static Map createExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put(ScrollEventType.SCROLL.getJSEventName(), MapBuilder.of("registrationName", "onScroll")).put(ScrollEventType.BEGIN_DRAG.getJSEventName(), MapBuilder.of("registrationName", "onScrollBeginDrag")).put(ScrollEventType.END_DRAG.getJSEventName(), MapBuilder.of("registrationName", "onScrollEndDrag")).put(ScrollEventType.ANIMATION_END.getJSEventName(), MapBuilder.of("registrationName", "onScrollAnimationEnd")).put(ScrollEventType.MOMENTUM_BEGIN.getJSEventName(), MapBuilder.of("registrationName", "onMomentumScrollBegin")).put(ScrollEventType.MOMENTUM_END.getJSEventName(), MapBuilder.of("registrationName", "onMomentumScrollEnd")).build();
    }
}
