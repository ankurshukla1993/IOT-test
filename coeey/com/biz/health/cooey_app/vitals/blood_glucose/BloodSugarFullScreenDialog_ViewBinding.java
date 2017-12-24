package com.biz.health.cooey_app.vitals.blood_glucose;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.biz.health.cooey_app.C0644R;

public class BloodSugarFullScreenDialog_ViewBinding implements Unbinder {
    private BloodSugarFullScreenDialog target;
    private View view2131362614;

    @UiThread
    public BloodSugarFullScreenDialog_ViewBinding(final BloodSugarFullScreenDialog target, View source) {
        this.target = target;
        target.addBloodSugarText = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.addBloodSugarText, "field 'addBloodSugarText'", TextView.class);
        target.notesText = (EditText) Utils.findRequiredViewAsType(source, C0644R.id.notes_text, "field 'notesText'", EditText.class);
        target.timeText = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.timeText, "field 'timeText'", TextView.class);
        target.happy = (RadioButton) Utils.findRequiredViewAsType(source, C0644R.id.happy_mood, "field 'happy'", RadioButton.class);
        target.sad = (RadioButton) Utils.findRequiredViewAsType(source, C0644R.id.sad_mood, "field 'sad'", RadioButton.class);
        target.indifferent = (RadioButton) Utils.findRequiredViewAsType(source, C0644R.id.indifferent_mood, "field 'indifferent'", RadioButton.class);
        target.afterFood = (RadioButton) Utils.findRequiredViewAsType(source, C0644R.id.after_food, "field 'afterFood'", RadioButton.class);
        target.beforeFood = (RadioButton) Utils.findRequiredViewAsType(source, C0644R.id.before_food, "field 'beforeFood'", RadioButton.class);
        target.randomTime = (RadioButton) Utils.findRequiredViewAsType(source, C0644R.id.random_time, "field 'randomTime'", RadioButton.class);
        target.noteLinearLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0644R.id.noteLinearLayout, "field 'noteLinearLayout'", LinearLayout.class);
        target.toolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0644R.id.toolbar, "field 'toolbar'", Toolbar.class);
        View view = Utils.findRequiredView(source, C0644R.id.timeLinearLayout, "method 'setTimeText'");
        this.view2131362614 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.setTimeText();
            }
        });
    }

    @CallSuper
    public void unbind() {
        BloodSugarFullScreenDialog target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target.addBloodSugarText = null;
        target.notesText = null;
        target.timeText = null;
        target.happy = null;
        target.sad = null;
        target.indifferent = null;
        target.afterFood = null;
        target.beforeFood = null;
        target.randomTime = null;
        target.noteLinearLayout = null;
        target.toolbar = null;
        this.view2131362614.setOnClickListener(null);
        this.view2131362614 = null;
    }
}
