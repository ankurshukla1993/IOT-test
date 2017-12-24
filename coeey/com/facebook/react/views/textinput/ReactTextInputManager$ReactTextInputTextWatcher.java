package com.facebook.react.views.textinput;

import android.text.Editable;
import android.text.TextWatcher;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;

class ReactTextInputManager$ReactTextInputTextWatcher implements TextWatcher {
    private ReactEditText mEditText;
    private EventDispatcher mEventDispatcher;
    private String mPreviousText = null;
    final /* synthetic */ ReactTextInputManager this$0;

    public ReactTextInputManager$ReactTextInputTextWatcher(ReactTextInputManager reactTextInputManager, ReactContext reactContext, ReactEditText editText) {
        this.this$0 = reactTextInputManager;
        this.mEventDispatcher = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        this.mEditText = editText;
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        this.mPreviousText = s.toString();
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (count != 0 || before != 0) {
            Assertions.assertNotNull(this.mPreviousText);
            String newText = s.toString().substring(start, start + count);
            String oldText = this.mPreviousText.substring(start, start + before);
            if (count != before || !newText.equals(oldText)) {
                int contentWidth = this.mEditText.getWidth();
                int contentHeight = this.mEditText.getHeight();
                if (this.mEditText.getLayout() != null) {
                    contentWidth = (this.mEditText.getCompoundPaddingLeft() + this.mEditText.getLayout().getWidth()) + this.mEditText.getCompoundPaddingRight();
                    contentHeight = (this.mEditText.getCompoundPaddingTop() + this.mEditText.getLayout().getHeight()) + this.mEditText.getCompoundPaddingTop();
                }
                this.mEventDispatcher.dispatchEvent(new ReactTextChangedEvent(this.mEditText.getId(), s.toString(), PixelUtil.toDIPFromPixel((float) contentWidth), PixelUtil.toDIPFromPixel((float) contentHeight), this.mEditText.incrementAndGetEventCounter()));
                this.mEventDispatcher.dispatchEvent(new ReactTextInputEvent(this.mEditText.getId(), newText, oldText, start, start + before));
            }
        }
    }

    public void afterTextChanged(Editable s) {
    }
}
