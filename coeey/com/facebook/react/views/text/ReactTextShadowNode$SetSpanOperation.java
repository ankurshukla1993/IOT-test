package com.facebook.react.views.text;

import android.text.SpannableStringBuilder;

class ReactTextShadowNode$SetSpanOperation {
    protected int end;
    protected int start;
    protected Object what;

    ReactTextShadowNode$SetSpanOperation(int start, int end, Object what) {
        this.start = start;
        this.end = end;
        this.what = what;
    }

    public void execute(SpannableStringBuilder sb) {
        int spanFlags = 34;
        if (this.start == 0) {
            spanFlags = 18;
        }
        sb.setSpan(this.what, this.start, this.end, spanFlags);
    }
}
