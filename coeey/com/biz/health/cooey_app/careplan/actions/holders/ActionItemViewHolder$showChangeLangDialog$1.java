package com.biz.health.cooey_app.careplan.actions.holders;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import com.cooey.common.vo.ActionItem;
import com.cooey.common.vo.FeedBackInputItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "whichButton", "", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: ActionItemViewHolder.kt */
final class ActionItemViewHolder$showChangeLangDialog$1 implements OnClickListener {
    final /* synthetic */ ActionItem $actionItem;
    final /* synthetic */ EditText $editText;
    final /* synthetic */ List $feedBackInputItems;
    final /* synthetic */ LinearLayout $linearLayout;
    final /* synthetic */ LinearLayout $linearLayoutEdit;
    final /* synthetic */ String $nextOrSave;
    final /* synthetic */ int $position;
    final /* synthetic */ RadioButton $rdbFalse;
    final /* synthetic */ RadioButton $rdbTrue;
    final /* synthetic */ ActionItemViewHolder this$0;

    ActionItemViewHolder$showChangeLangDialog$1(ActionItemViewHolder actionItemViewHolder, String str, List list, EditText editText, RadioButton radioButton, RadioButton radioButton2, ActionItem actionItem, int i, LinearLayout linearLayout, LinearLayout linearLayout2) {
        this.this$0 = actionItemViewHolder;
        this.$nextOrSave = str;
        this.$feedBackInputItems = list;
        this.$editText = editText;
        this.$rdbTrue = radioButton;
        this.$rdbFalse = radioButton2;
        this.$actionItem = actionItem;
        this.$position = i;
        this.$linearLayoutEdit = linearLayout;
        this.$linearLayout = linearLayout2;
    }

    public final void onClick(DialogInterface dialog, int whichButton) {
        String str = this.$nextOrSave;
        CharSequence charSequence = "Next";
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        } else if (str.contentEquals(charSequence)) {
            str = ((FeedBackInputItem) this.$feedBackInputItems.get(this.this$0.getIndex())).getType();
            charSequence = "Text";
            if (str == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            if (str.contentEquals(charSequence)) {
                ((FeedBackInputItem) this.$feedBackInputItems.get(this.this$0.getIndex())).setValue(this.$editText.getText().toString());
            } else {
                str = ((FeedBackInputItem) this.$feedBackInputItems.get(this.this$0.getIndex())).getType();
                charSequence = "Boolean";
                if (str == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                } else if (str.contentEquals(charSequence)) {
                    if (this.$rdbTrue.isChecked()) {
                        trueOrFalse = "Yes";
                    } else if (this.$rdbFalse.isChecked()) {
                        trueOrFalse = "No";
                    } else {
                        trueOrFalse = "No";
                    }
                    ((FeedBackInputItem) this.$feedBackInputItems.get(this.this$0.getIndex())).setValue(trueOrFalse);
                }
            }
            ActionItemViewHolder actionItemViewHolder = this.this$0;
            actionItemViewHolder.setIndex(actionItemViewHolder.getIndex() + 1);
            this.this$0.showChangeLangDialog(this.$feedBackInputItems, this.$actionItem, this.$position);
        } else {
            str = ((FeedBackInputItem) this.$feedBackInputItems.get(this.this$0.getIndex())).getType();
            charSequence = "Text";
            if (str == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            if (str.contentEquals(charSequence)) {
                this.$linearLayoutEdit.setVisibility(0);
                ((FeedBackInputItem) this.$feedBackInputItems.get(this.this$0.getIndex())).setValue(this.$editText.getText().toString());
            } else {
                str = ((FeedBackInputItem) this.$feedBackInputItems.get(this.this$0.getIndex())).getType();
                charSequence = "Boolean";
                if (str == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                } else if (str.contentEquals(charSequence)) {
                    this.$linearLayout.setVisibility(0);
                    if (this.$rdbTrue.isChecked()) {
                        trueOrFalse = "Yes";
                    } else if (this.$rdbFalse.isChecked()) {
                        trueOrFalse = "No";
                    } else {
                        trueOrFalse = "No";
                    }
                    ((FeedBackInputItem) this.$feedBackInputItems.get(this.this$0.getIndex())).setValue(trueOrFalse);
                }
            }
            if (this.this$0.getGpsTracker().canGetLocation()) {
                double latitude = this.this$0.getGpsTracker().getLatitude();
                double longitude = this.this$0.getGpsTracker().getLongitude();
                this.$actionItem.setLatitude(latitude);
                this.$actionItem.setLongitude(longitude);
            }
            if (this.$feedBackInputItems.size() > 0) {
                ActionItem actionItem = this.$actionItem;
                ActionItemViewHolder actionItemViewHolder2 = this.this$0;
                String parameters = this.$actionItem.getParameters();
                Intrinsics.checkExpressionValueIsNotNull(parameters, "actionItem.parameters");
                actionItem.setParameters(actionItemViewHolder2.setParameterMap(parameters, "feedBackInput", this.$feedBackInputItems));
            }
            this.$actionItem.setCompleted(true);
            this.this$0.postData(this.$actionItem);
            this.this$0.setIndex(0);
        }
    }
}
