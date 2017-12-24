package com.facebook.react.views.switchview;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;

class ReactSwitch extends SwitchCompat {
    private boolean mAllowChange = true;

    public ReactSwitch(Context context) {
        super(context);
    }

    public void setChecked(boolean checked) {
        if (this.mAllowChange) {
            this.mAllowChange = false;
            super.setChecked(checked);
        }
    }

    void setOn(boolean on) {
        if (isChecked() != on) {
            super.setChecked(on);
        }
        this.mAllowChange = true;
    }
}
