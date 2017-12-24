package com.facebook.react.views.textinput;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;

class ReactTextInputManager$1 implements OnFocusChangeListener {
    final /* synthetic */ ReactTextInputManager this$0;
    final /* synthetic */ ReactEditText val$editText;
    final /* synthetic */ ThemedReactContext val$reactContext;

    ReactTextInputManager$1(ReactTextInputManager this$0, ThemedReactContext themedReactContext, ReactEditText reactEditText) {
        this.this$0 = this$0;
        this.val$reactContext = themedReactContext;
        this.val$editText = reactEditText;
    }

    public void onFocusChange(View v, boolean hasFocus) {
        EventDispatcher eventDispatcher = ((UIManagerModule) this.val$reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        if (hasFocus) {
            eventDispatcher.dispatchEvent(new ReactTextInputFocusEvent(this.val$editText.getId()));
            return;
        }
        eventDispatcher.dispatchEvent(new ReactTextInputBlurEvent(this.val$editText.getId()));
        eventDispatcher.dispatchEvent(new ReactTextInputEndEditingEvent(this.val$editText.getId(), this.val$editText.getText().toString()));
    }
}
