package com.facebook.react.views.textinput;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;

class ReactTextInputManager$2 implements OnEditorActionListener {
    final /* synthetic */ ReactTextInputManager this$0;
    final /* synthetic */ ReactEditText val$editText;
    final /* synthetic */ ThemedReactContext val$reactContext;

    ReactTextInputManager$2(ReactTextInputManager this$0, ThemedReactContext themedReactContext, ReactEditText reactEditText) {
        this.this$0 = this$0;
        this.val$reactContext = themedReactContext;
        this.val$editText = reactEditText;
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
        if ((actionId & 255) > 0 || actionId == 0) {
            ((UIManagerModule) this.val$reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ReactTextInputSubmitEditingEvent(this.val$editText.getId(), this.val$editText.getText().toString()));
        }
        if (this.val$editText.getBlurOnSubmit()) {
            this.val$editText.clearFocus();
        }
        return true;
    }
}
