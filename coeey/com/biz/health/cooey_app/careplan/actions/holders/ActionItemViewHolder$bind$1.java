package com.biz.health.cooey_app.careplan.actions.holders;

import android.view.View;
import android.view.View.OnClickListener;
import com.biz.health.cooey_app.C0644R;
import com.cooey.common.vo.ActionItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: ActionItemViewHolder.kt */
final class ActionItemViewHolder$bind$1 implements OnClickListener {
    final /* synthetic */ ActionItem $actionItem;
    final /* synthetic */ ActionItemViewHolder this$0;

    ActionItemViewHolder$bind$1(ActionItemViewHolder actionItemViewHolder, ActionItem actionItem) {
        this.this$0 = actionItemViewHolder;
        this.$actionItem = actionItem;
    }

    public final void onClick(View it) {
        ActionItemViewHolder actionItemViewHolder;
        String string;
        try {
            if (!(this.$actionItem == null || this.$actionItem.getParameterMap() == null || this.$actionItem.getParameterMap().get("instruction") == null)) {
                Boolean valueOf;
                String str = (String) this.$actionItem.getParameterMap().get("instruction");
                if (str != null) {
                    valueOf = Boolean.valueOf(((CharSequence) str).length() == 0);
                } else {
                    valueOf = null;
                }
                if (valueOf == null) {
                    Intrinsics.throwNpe();
                }
                if (!valueOf.booleanValue()) {
                    ActionItemViewHolder actionItemViewHolder2 = this.this$0;
                    Object obj = this.$actionItem.getParameterMap().get("instruction");
                    if (obj == null) {
                        Intrinsics.throwNpe();
                    }
                    actionItemViewHolder2.buildInfoDialog((String) obj);
                    return;
                }
            }
            actionItemViewHolder = this.this$0;
            string = this.this$0.itemView.getContext().getString(C0644R.string.action_item_no_info);
            Intrinsics.checkExpressionValueIsNotNull(string, "itemView.context.getStri…ring.action_item_no_info)");
            actionItemViewHolder.buildInfoDialog(string);
        } catch (Exception e) {
            e.printStackTrace();
            actionItemViewHolder = this.this$0;
            string = this.this$0.itemView.getContext().getString(C0644R.string.action_item_no_info);
            Intrinsics.checkExpressionValueIsNotNull(string, "itemView.context.getStri…ring.action_item_no_info)");
            actionItemViewHolder.buildInfoDialog(string);
        }
    }
}
