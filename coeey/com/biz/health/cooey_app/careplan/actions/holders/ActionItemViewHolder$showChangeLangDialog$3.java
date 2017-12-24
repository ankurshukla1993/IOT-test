package com.biz.health.cooey_app.careplan.actions.holders;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.cooey.common.vo.ActionItem;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "onCancel"}, k = 3, mv = {1, 1, 7})
/* compiled from: ActionItemViewHolder.kt */
final class ActionItemViewHolder$showChangeLangDialog$3 implements OnCancelListener {
    final /* synthetic */ ActionItem $actionItem;

    ActionItemViewHolder$showChangeLangDialog$3(ActionItem actionItem) {
        this.$actionItem = actionItem;
    }

    public final void onCancel(DialogInterface it) {
        this.$actionItem.setCompleted(false);
    }
}
