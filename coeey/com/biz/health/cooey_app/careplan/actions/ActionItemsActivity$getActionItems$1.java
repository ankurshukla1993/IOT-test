package com.biz.health.cooey_app.careplan.actions;

import android.arch.lifecycle.Observer;
import com.cooey.common.vo.ActionItem;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "actionItems", "", "Lcom/cooey/common/vo/ActionItem;", "onChanged"}, k = 3, mv = {1, 1, 7})
/* compiled from: ActionItemsActivity.kt */
final class ActionItemsActivity$getActionItems$1<T> implements Observer<List<? extends ActionItem>> {
    final /* synthetic */ ActionItemsActivity this$0;

    ActionItemsActivity$getActionItems$1(ActionItemsActivity actionItemsActivity) {
        this.this$0 = actionItemsActivity;
    }

    public final void onChanged(@Nullable List<? extends ActionItem> actionItems) {
        ActionItemAdapter actionItemAdapter = this.this$0.getActionItemAdapter();
        if (actionItemAdapter != null) {
            actionItemAdapter.actionItems = actionItems;
        }
        actionItemAdapter = this.this$0.getActionItemAdapter();
        if (actionItemAdapter != null) {
            actionItemAdapter.notifyDataSetChanged();
        }
    }
}
