package com.facebook.react.views.scroll;

public enum ScrollEventType {
    BEGIN_DRAG("topScrollBeginDrag"),
    END_DRAG("topScrollEndDrag"),
    SCROLL("topScroll"),
    MOMENTUM_BEGIN("topMomentumScrollBegin"),
    MOMENTUM_END("topMomentumScrollEnd"),
    ANIMATION_END("topScrollAnimationEnd");
    
    private final String mJSEventName;

    private ScrollEventType(String jsEventName) {
        this.mJSEventName = jsEventName;
    }

    public String getJSEventName() {
        return this.mJSEventName;
    }
}
