package com.facebook.react;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import javax.annotation.Nullable;

public abstract class ReactFragmentActivity extends FragmentActivity implements DefaultHardwareBackBtnHandler, PermissionAwareActivity {
    private final ReactActivityDelegate mDelegate = createReactActivityDelegate();

    protected ReactFragmentActivity() {
    }

    @Nullable
    protected String getMainComponentName() {
        return null;
    }

    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegate((FragmentActivity) this, getMainComponentName());
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mDelegate.onCreate(savedInstanceState);
    }

    protected void onPause() {
        super.onPause();
        this.mDelegate.onPause();
    }

    protected void onResume() {
        super.onResume();
        this.mDelegate.onResume();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mDelegate.onDestroy();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.mDelegate.onActivityResult(requestCode, resultCode, data);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return this.mDelegate.onKeyUp(keyCode, event) || super.onKeyUp(keyCode, event);
    }

    public void onBackPressed() {
        if (!this.mDelegate.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    public void onNewIntent(Intent intent) {
        if (!this.mDelegate.onNewIntent(intent)) {
            super.onNewIntent(intent);
        }
    }

    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        this.mDelegate.requestPermissions(permissions, requestCode, listener);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        this.mDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected final ReactNativeHost getReactNativeHost() {
        return this.mDelegate.getReactNativeHost();
    }

    protected final ReactInstanceManager getReactInstanceManager() {
        return this.mDelegate.getReactInstanceManager();
    }

    protected final void loadApp(String appKey) {
        this.mDelegate.loadApp(appKey);
    }
}
