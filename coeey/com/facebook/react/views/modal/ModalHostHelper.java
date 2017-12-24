package com.facebook.react.views.modal;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.infer.annotation.Assertions;

class ModalHostHelper {
    private static final Point MAX_POINT = new Point();
    private static final Point MIN_POINT = new Point();
    private static final Point SIZE_POINT = new Point();

    ModalHostHelper() {
    }

    @TargetApi(16)
    public static Point getModalHostSize(Context context) {
        Display display = ((WindowManager) Assertions.assertNotNull((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
        display.getCurrentSizeRange(MIN_POINT, MAX_POINT);
        display.getSize(SIZE_POINT);
        if (SIZE_POINT.x < SIZE_POINT.y) {
            return new Point(MIN_POINT.x, MAX_POINT.y);
        }
        return new Point(MAX_POINT.x, MIN_POINT.y);
    }
}
