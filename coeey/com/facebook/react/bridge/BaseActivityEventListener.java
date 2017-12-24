package com.facebook.react.bridge;

import android.app.Activity;
import android.content.Intent;

public class BaseActivityEventListener implements ActivityEventListener {
    @Deprecated
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
    }

    public void onNewIntent(Intent intent) {
    }
}
