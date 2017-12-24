package com.cooey.android.video_call;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.System;
import android.support.v7.app.AppCompatActivity;
import com.cooey.android.video_call.databinding.ActivityCallRingBinding;

public class CallRingActivity extends AppCompatActivity {
    MediaPlayer player;

    class C08101 implements Runnable {
        C08101() {
        }

        public void run() {
            CallRingActivity.this.player.stop();
            CallRingActivity.this.finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(6815872);
        ActivityCallRingBinding activityCallRingBinding = (ActivityCallRingBinding) DataBindingUtil.setContentView(this, C0811R.layout.activity_call_ring);
        CallRingViewModel callRingViewModel = new CallRingViewModel(this, getIntent().getExtras());
        callRingViewModel.setName(getIntent().getStringExtra("name"));
        activityCallRingBinding.setCallRingViewModel(callRingViewModel);
        this.player = MediaPlayer.create(this, System.DEFAULT_RINGTONE_URI);
        this.player.start();
        new Handler().postDelayed(new C08101(), 60000);
    }

    public void onPause() {
        super.onPause();
        if (this.player != null) {
            this.player.stop();
        }
    }
}
