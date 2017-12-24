package com.flipboard.bottomsheet;

import android.view.View;

public interface ViewTransformer {
    float getDimAlpha(float f, float f2, float f3, BottomSheetLayout bottomSheetLayout, View view);

    void transformView(float f, float f2, float f3, BottomSheetLayout bottomSheetLayout, View view);
}
