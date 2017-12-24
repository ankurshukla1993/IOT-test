package com.cooey.common.holders;

import android.arch.lifecycle.Observer;
import android.view.View;
import android.view.View.OnClickListener;
import com.cooey.common.vo.ActionItem;
import com.google.gson.Gson;
import java.util.ArrayList;
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
        if (actionItem != null && actionItem.getParameterMap() != null) {
            String feedbackData = new Gson().toJson(actionItem.getParameterMap().get("feedBackInput"));
            if (feedbackData != null) {
                int i;
                if (feedbackData.length() > 0) {
                    i = 1;
                } else {
                    i = 0;
                }
                if (i != 0) {
                    this.this$0.getBtnFeedback().setVisibility(0);
                    final ArrayList feedbackInputItems = (ArrayList) new Gson().fromJson(feedbackData, new ActionTimelineViewHolder$bind$1$feedbackInPutType$1().getType());
                    this.this$0.getBtnFeedback().setOnClickListener(new OnClickListener() {
                        public void onClick(@Nullable View v) {
                            this.this$0.generateFeedBackDialog(feedbackInputItems);
                        }
                    });
                }
            }
        }
    }
}
