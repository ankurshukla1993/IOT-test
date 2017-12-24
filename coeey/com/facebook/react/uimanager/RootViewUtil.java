package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewParent;
import com.facebook.infer.annotation.Assertions;

public class RootViewUtil {
    public static RootView getRootView(View reactView) {
        View current = reactView;
        while (!(current instanceof RootView)) {
            ViewParent next = current.getParent();
            if (next == null) {
                return null;
            }
            Assertions.assertCondition(next instanceof View);
            current = (View) next;
        }
        return (RootView) current;
    }
}
