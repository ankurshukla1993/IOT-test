package com.facebook.common.activitylistener;

import android.app.Activity;

public interface ActivityListener {
    void onActivityCreate(Activity activity);

    void onDestroy(Activity activity);

    void onPause(Activity activity);

    void onResume(Activity activity);

    void onStart(Activity activity);

    void onStop(Activity activity);
}
