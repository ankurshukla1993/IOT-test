package com.facebook.react.views.textinput;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;

class ReactTextInputManager$ReactSelectionWatcher implements SelectionWatcher {
    private EventDispatcher mEventDispatcher;
    private int mPreviousSelectionEnd;
    private int mPreviousSelectionStart;
    private ReactEditText mReactEditText;
    final /* synthetic */ ReactTextInputManager this$0;

    public ReactTextInputManager$ReactSelectionWatcher(ReactTextInputManager reactTextInputManager, ReactEditText editText) {
        this.this$0 = reactTextInputManager;
        this.mReactEditText = editText;
        this.mEventDispatcher = ((UIManagerModule) ((ReactContext) editText.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
    }

    public void onSelectionChanged(int start, int end) {
        if (this.mPreviousSelectionStart != start || this.mPreviousSelectionEnd != end) {
            this.mEventDispatcher.dispatchEvent(new ReactTextInputSelectionEvent(this.mReactEditText.getId(), start, end));
            this.mPreviousSelectionStart = start;
            this.mPreviousSelectionEnd = end;
        }
    }
}
