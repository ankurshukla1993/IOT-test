package com.cooey.common.holders;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/cooey/common/holders/ActionTimelineViewHolder$generateFeedBackDialog$1", "Landroid/view/View$OnClickListener;", "(Landroid/support/v7/app/AlertDialog;)V", "onClick", "", "v", "Landroid/view/View;", "common_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: ActionTimelineViewHolder.kt */
public final class ActionTimelineViewHolder$generateFeedBackDialog$1 implements OnClickListener {
    final /* synthetic */ AlertDialog $alertDialog;

    ActionTimelineViewHolder$generateFeedBackDialog$1(AlertDialog $captured_local_variable$0) {
        this.$alertDialog = $captured_local_variable$0;
    }

    public void onClick(@Nullable View v) {
        this.$alertDialog.dismiss();
    }
}
