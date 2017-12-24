package com.facebook.react.views.textinput;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;

class ReactTextInputManager$ReactContentSizeWatcher implements ContentSizeWatcher {
    private ReactEditText mEditText;
    private EventDispatcher mEventDispatcher;
    private int mPreviousContentHeight = 0;
    private int mPreviousContentWidth = 0;
    final /* synthetic */ ReactTextInputManager this$0;

    public ReactTextInputManager$ReactContentSizeWatcher(ReactTextInputManager reactTextInputManager, ReactEditText editText) {
        this.this$0 = reactTextInputManager;
        this.mEditText = editText;
        this.mEventDispatcher = ((UIManagerModule) ((ReactContext) editText.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
    }

    public void onLayout() {
        int contentWidth = this.mEditText.getWidth();
        int contentHeight = this.mEditText.getHeight();
        if (this.mEditText.getLayout() != null) {
            contentWidth = (this.mEditText.getCompoundPaddingLeft() + this.mEditText.getLayout().getWidth()) + this.mEditText.getCompoundPaddingRight();
            contentHeight = (this.mEditText.getCompoundPaddingTop() + this.mEditText.getLayout().getHeight()) + this.mEditText.getCompoundPaddingBottom();
        }
        if (contentWidth != this.mPreviousContentWidth || contentHeight != this.mPreviousContentHeight) {
            this.mPreviousContentHeight = contentHeight;
            this.mPreviousContentWidth = contentWidth;
            this.mEventDispatcher.dispatchEvent(new ReactContentSizeChangedEvent(this.mEditText.getId(), PixelUtil.toDIPFromPixel((float) contentWidth), PixelUtil.toDIPFromPixel((float) contentHeight)));
        }
    }
}
