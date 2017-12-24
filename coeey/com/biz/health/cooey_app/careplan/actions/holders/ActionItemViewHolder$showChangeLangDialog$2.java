package com.biz.health.cooey_app.careplan.actions.holders;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.cooey.common.vo.ActionItem;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "whichButton", "", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: ActionItemViewHolder.kt */
final class ActionItemViewHolder$showChangeLangDialog$2 implements OnClickListener {
    final /* synthetic */ ActionItem $actionItem;
    final /* synthetic */ ActionItemViewHolder this$0;

    ActionItemViewHolder$showChangeLangDialog$2(ActionItemViewHolder actionItemViewHolder, ActionItem actionItem) {
        this.this$0 = actionItemViewHolder;
        this.$actionItem = actionItem;
    }

    public final void onClick(DialogInterface dialog, int whichButton) {
        if (this.this$0.getIndex() > 0) {
            this.this$0.setIndex(0);
        }
        this.$actionItem.setCompleted(false);
    }
}
