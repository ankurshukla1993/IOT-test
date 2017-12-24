package org.jitsi.meet.sdk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import java.net.URL;

public class JitsiMeetActivity extends AppCompatActivity {
    private static final int OVERLAY_PERMISSION_REQUEST_CODE = ((int) (Math.random() * 32767.0d));
    private JitsiMeetView view;
    private boolean welcomePageEnabled;

    private boolean canRequestOverlayPermission() {
        return false;
    }

    public boolean getWelcomePageEnabled() {
        return this.view == null ? this.welcomePageEnabled : this.view.getWelcomePageEnabled();
    }

    private void initializeContentView() {
        JitsiMeetView view = initializeView();
        if (view != null) {
            this.view = view;
            setContentView(this.view);
        }
    }

    protected JitsiMeetView initializeView() {
        JitsiMeetView view = new JitsiMeetView(this);
        view.setWelcomePageEnabled(this.welcomePageEnabled);
        view.loadURL(null);
        return view;
    }

    public void loadURL(@Nullable URL url) {
        this.view.loadURL(url);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQUEST_CODE && canRequestOverlayPermission() && Settings.canDrawOverlays(this)) {
            initializeContentView();
        }
    }

    public void onBackPressed() {
        if (!JitsiMeetView.onBackPressed()) {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!canRequestOverlayPermission() || Settings.canDrawOverlays(this)) {
            initializeContentView();
        } else {
            startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())), OVERLAY_PERMISSION_REQUEST_CODE);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.view != null) {
            this.view.dispose();
            this.view = null;
        }
        JitsiMeetView.onHostDestroy(this);
    }

    public void onNewIntent(Intent intent) {
        JitsiMeetView.onNewIntent(intent);
    }

    protected void onPause() {
        super.onPause();
        JitsiMeetView.onHostPause(this);
    }

    protected void onResume() {
        super.onResume();
        JitsiMeetView.onHostResume(this);
    }

    public void setWelcomePageEnabled(boolean welcomePageEnabled) {
        if (this.view == null) {
            this.welcomePageEnabled = welcomePageEnabled;
        } else {
            this.view.setWelcomePageEnabled(welcomePageEnabled);
        }
    }
}
