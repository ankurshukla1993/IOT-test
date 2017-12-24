package com.flipboard.bottomsheet;

import android.view.View;

public abstract class BaseViewTransformer implements ViewTransformer {
    public static final float MAX_DIM_ALPHA = 0.7f;

    public float getDimAlpha(float translation, float maxTranslation, float peekedTranslation, BottomSheetLayout parent, View view) {
        return MAX_DIM_ALPHA * (translation / maxTranslation);
    }
}
