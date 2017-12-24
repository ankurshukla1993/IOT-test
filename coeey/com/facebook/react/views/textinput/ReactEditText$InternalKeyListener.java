package com.facebook.react.views.textinput;

import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;

class ReactEditText$InternalKeyListener implements KeyListener {
    private int mInputType = 0;

    public void setInputType(int inputType) {
        this.mInputType = inputType;
    }

    public int getInputType() {
        return this.mInputType;
    }

    public boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {
        return ReactEditText.access$300().onKeyDown(view, text, keyCode, event);
    }

    public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
        return ReactEditText.access$300().onKeyUp(view, text, keyCode, event);
    }

    public boolean onKeyOther(View view, Editable text, KeyEvent event) {
        return ReactEditText.access$300().onKeyOther(view, text, event);
    }

    public void clearMetaKeyState(View view, Editable content, int states) {
        ReactEditText.access$300().clearMetaKeyState(view, content, states);
    }
}
