package com.biz.health.cooey_app.vitals.bp;

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

public class BPFullScreenDialog_ViewBinding implements Unbinder {
    private BPFullScreenDialog target;
    private View view2131362614;

    @UiThread
    public BPFullScreenDialog_ViewBinding(final BPFullScreenDialog target, View source) {
        this.target = target;
        target.systolicText = (EditText) Utils.findRequiredViewAsType(source, C0644R.id.sysval, "field 'systolicText'", EditText.class);
        target.diastolicText = (EditText) Utils.findRequiredViewAsType(source, C0644R.id.diaval, "field 'diastolicText'", EditText.class);
        target.heartBeatText = (EditText) Utils.findRequiredViewAsType(source, C0644R.id.heartbeatVal, "field 'heartBeatText'", EditText.class);
        target.notesText = (EditText) Utils.findRequiredViewAsType(source, C0644R.id.notes_text, "field 'notesText'", EditText.class);
        target.timeText = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.timeText, "field 'timeText'", TextView.class);
        target.happy = (RadioButton) Utils.findRequiredViewAsType(source, C0644R.id.happy_mood, "field 'happy'", RadioButton.class);
        target.sad = (RadioButton) Utils.findRequiredViewAsType(source, C0644R.id.sad_mood, "field 'sad'", RadioButton.class);
        target.indifferent = (RadioButton) Utils.findRequiredViewAsType(source, C0644R.id.indifferent_mood, "field 'indifferent'", RadioButton.class);
        target.leftHand = (RadioButton) Utils.findRequiredViewAsType(source, C0644R.id.left_hand, "field 'leftHand'", RadioButton.class);
        target.rightHand = (RadioButton) Utils.findRequiredViewAsType(source, C0644R.id.right_hand, "field 'rightHand'", RadioButton.class);
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
        BPFullScreenDialog target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target.systolicText = null;
        target.diastolicText = null;
        target.heartBeatText = null;
        target.notesText = null;
        target.timeText = null;
        target.happy = null;
        target.sad = null;
        target.indifferent = null;
        target.leftHand = null;
        target.rightHand = null;
        target.noteLinearLayout = null;
        target.toolbar = null;
        this.view2131362614.setOnClickListener(null);
        this.view2131362614 = null;
    }
}
