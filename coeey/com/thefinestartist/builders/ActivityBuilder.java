package com.thefinestartist.builders;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.thefinestartist.Base;
import com.thefinestartist.utils.content.ContextUtil;
import java.io.Serializable;
import java.util.ArrayList;

public class ActivityBuilder {
    final Intent intent;

    public <C extends Activity> ActivityBuilder(@NonNull Class<C> clazz) {
        this.intent = new Intent(Base.getContext(), clazz);
    }

    public <T extends Serializable> ActivityBuilder set(@NonNull String key, T value) {
        this.intent.putExtra(key, value);
        return this;
    }

    public ActivityBuilder set(@NonNull String key, Parcelable value) {
        this.intent.putExtra(key, value);
        return this;
    }

    public ActivityBuilder set(@NonNull String key, Parcelable[] value) {
        this.intent.putExtra(key, value);
        return this;
    }

    public <T extends Parcelable> ActivityBuilder set(@NonNull String key, ArrayList<T> value) {
        this.intent.putExtra(key, value);
        return this;
    }

    public ActivityBuilder remove(@NonNull String key) {
        this.intent.removeExtra(key);
        return this;
    }

    public ActivityBuilder setFlags(int flags) {
        this.intent.setFlags(flags);
        return this;
    }

    public ActivityBuilder addFlags(int flags) {
        this.intent.addFlags(flags);
        return this;
    }

    public Intent buildIntent() {
        return this.intent;
    }

    public void start() {
        ContextUtil.startActivity(this.intent);
    }

    public void startForResult(@NonNull Activity activity, int requestCode) {
        activity.startActivityForResult(this.intent, requestCode);
    }

    @TargetApi(16)
    public void startForResult(@NonNull Activity activity, int requestCode, @Nullable Bundle options) {
        activity.startActivityForResult(this.intent, requestCode, options);
    }
}
