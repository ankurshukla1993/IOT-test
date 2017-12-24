package com.cooey.android.video_call;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CallRingViewModel extends BaseObservable {
    private final Bundle bundle;
    private final Context context;
    private String name;

    @Bindable
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public CallRingViewModel(Context context, Bundle bundle) {
        this.context = context;
        this.bundle = bundle;
    }

    public void answerCall() {
        Intent i = new Intent(this.context, VideoCallActivity.class);
        i.putExtras(this.bundle);
        this.context.startActivity(i);
        ((AppCompatActivity) this.context).finish();
    }

    public void dismissCall() {
        ((AppCompatActivity) this.context).finish();
    }
}
