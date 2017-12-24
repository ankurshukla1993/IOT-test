package com.biz.health.cooey_app.careplan.actions.holders;

import android.arch.lifecycle.Observer;
import com.biz.health.cooey_app.C0644R;
import com.cooey.common.vo.ActionItem;
import com.facebook.internal.AnalyticsEvents;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "actionItem", "Lcom/cooey/common/vo/ActionItem;", "onChanged"}, k = 3, mv = {1, 1, 7})
/* compiled from: ActionTimelineViewHolder.kt */
final class ActionTimelineViewHolder$bind$1<T> implements Observer<ActionItem> {
    final /* synthetic */ ActionTimelineViewHolder this$0;

    ActionTimelineViewHolder$bind$1(ActionTimelineViewHolder actionTimelineViewHolder) {
        this.this$0 = actionTimelineViewHolder;
    }

    public final void onChanged(@Nullable ActionItem actionItem) {
        this.this$0.getActionTextView().setText(actionItem != null ? actionItem.getTitle() : null);
        if (actionItem == null || !actionItem.isCompleted()) {
            this.this$0.getStatusTextView().setText("Not Completed");
            this.this$0.getStatusTextView().setBackgroundResource(C0644R.drawable.chip_alert_background);
            return;
        }
        this.this$0.getStatusTextView().setText(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_COMPLETED);
        this.this$0.getStatusTextView().setBackgroundResource(C0644R.drawable.chip_completed_background);
    }
}
