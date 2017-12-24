package com.facebook.stetho.common.android;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.ViewParent;
import javax.annotation.Nullable;

final class ViewUtil {
    private ViewUtil() {
    }

    @Nullable
    static Activity tryGetActivity(View view) {
        if (view == null) {
            return null;
        }
        Activity activityFromContext = tryGetActivity(view.getContext());
        if (activityFromContext != null) {
            return activityFromContext;
        }
        ViewParent parent = view.getParent();
        return parent instanceof View ? tryGetActivity((View) parent) : null;
    }

    @Nullable
    private static Activity tryGetActivity(Context context) {
        while (context != null) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            if (!(context instanceof ContextWrapper)) {
                return null;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }
}
