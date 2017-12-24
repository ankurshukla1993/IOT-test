package com.cooey.android.video_call;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import java.net.MalformedURLException;
import java.net.URL;
import org.jitsi.meet.sdk.JitsiMeetView;

public class VideoCallActivity extends AppCompatActivity {
    private String demo = "";
    String url = "https://meet.cooey.co.in/cooeytest";
    private JitsiMeetView view;

    class C08121 implements OnClickListener {
        C08121() {
        }

        public void onClick(View v) {
            VideoCallActivity.this.finish();
        }
    }

    public void onBackPressed() {
        if (!JitsiMeetView.onBackPressed()) {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0811R.layout.activity_video_call);
        if (!(getIntent() == null || getIntent().getStringExtra("url") == null)) {
            this.url = getIntent().getStringExtra("url");
        }
        ((FloatingActionButton) findViewById(C0811R.id.floatingActionButton)).setOnClickListener(new C08121());
        this.view = new JitsiMeetView(this);
        FrameLayout frameLayout = (FrameLayout) findViewById(C0811R.id.video_container);
        try {
            this.view.loadURL(new URL(this.url));
            this.view.setLayoutParams(new LayoutParams(-1, -1));
            this.view.setFocusableInTouchMode(false);
            this.view.setClickable(false);
            frameLayout.addView(this.view);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.view.dispose();
        this.view = null;
        JitsiMeetView.onHostDestroy(this);
    }

    public void onNewIntent(Intent intent) {
        setIntent(intent);
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
}
