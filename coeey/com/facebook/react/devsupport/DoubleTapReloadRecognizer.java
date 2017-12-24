package com.facebook.react.devsupport;

import android.os.Handler;
import android.view.View;
import android.widget.EditText;

public class DoubleTapReloadRecognizer {
    private static final long DOUBLE_TAP_DELAY = 200;
    private boolean mDoRefresh = false;

    class C13011 implements Runnable {
        C13011() {
        }

        public void run() {
            DoubleTapReloadRecognizer.this.mDoRefresh = false;
        }
    }

    public boolean didDoubleTapR(int keyCode, View view) {
        if (keyCode == 46 && !(view instanceof EditText)) {
            if (this.mDoRefresh) {
                this.mDoRefresh = false;
                return true;
            }
            this.mDoRefresh = true;
            new Handler().postDelayed(new C13011(), DOUBLE_TAP_DELAY);
        }
        return false;
    }
}
