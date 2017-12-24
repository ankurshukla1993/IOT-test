package com.bartoszlipinski.viewpropertyobjectanimator;

import android.view.View;
import java.lang.ref.WeakReference;

abstract class ChangeUpdateListener {
    protected final WeakReference<View> mView;

    protected class FloatValues {
        public float mFrom;
        public float mTo;

        public FloatValues(float from, float to) {
            this.mFrom = from;
            this.mTo = to;
        }
    }

    protected class IntValues {
        public int mFrom;
        public int mTo;

        public IntValues(int from, int to) {
            this.mFrom = from;
            this.mTo = to;
        }
    }

    public ChangeUpdateListener(View view) {
        this.mView = new WeakReference(view);
    }

    public float calculateAnimatedValue(float initialValue, float targetValue, float animationFraction) {
        return targetValue - ((targetValue - initialValue) * (FlexItem.FLEX_SHRINK_DEFAULT - animationFraction));
    }

    protected boolean hasView() {
        return this.mView.get() != null;
    }
}
