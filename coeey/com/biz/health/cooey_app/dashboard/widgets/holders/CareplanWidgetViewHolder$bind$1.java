package com.biz.health.cooey_app.dashboard.widgets.holders;

import android.arch.lifecycle.Observer;
import com.cooey.common.vo.ActionItem;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lcom/cooey/common/vo/ActionItem;", "onChanged"}, k = 3, mv = {1, 1, 7})
/* compiled from: CareplanWidgetViewHolder.kt */
final class CareplanWidgetViewHolder$bind$1<T> implements Observer<List<? extends ActionItem>> {
    final /* synthetic */ CareplanWidgetViewHolder this$0;

    CareplanWidgetViewHolder$bind$1(CareplanWidgetViewHolder careplanWidgetViewHolder) {
        this.this$0 = careplanWidgetViewHolder;
    }

    public final void onChanged(@Nullable List<? extends ActionItem> it) {
        this.this$0.getActionSummaryforUser();
    }
}
