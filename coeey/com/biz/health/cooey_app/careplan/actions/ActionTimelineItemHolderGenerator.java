package com.biz.health.cooey_app.careplan.actions;

import android.arch.lifecycle.LifecycleOwner;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.careplan.actions.holders.ActionTimelineViewHolder;
import com.cooey.common.generators.ITimelineViewHolderGenerator;
import com.cooey.common.holders.TimelineItemViewHolder;
import com.cooey.common.views.TimelineItemView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u000e"}, d2 = {"Lcom/biz/health/cooey_app/careplan/actions/ActionTimelineItemHolderGenerator;", "Lcom/cooey/common/generators/ITimelineViewHolderGenerator;", "lifecycleOwner", "Landroid/arch/lifecycle/LifecycleOwner;", "(Landroid/arch/lifecycle/LifecycleOwner;)V", "getLifecycleOwner", "()Landroid/arch/lifecycle/LifecycleOwner;", "setLifecycleOwner", "generate", "Lcom/cooey/common/holders/TimelineItemViewHolder;", "parent", "Landroid/view/ViewGroup;", "viewType", "", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: ActionTimelineItemHolderGenerator.kt */
public final class ActionTimelineItemHolderGenerator implements ITimelineViewHolderGenerator {
    @NotNull
    private LifecycleOwner lifecycleOwner;

    public ActionTimelineItemHolderGenerator(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        this.lifecycleOwner = lifecycleOwner;
    }

    @NotNull
    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    public final void setLifecycleOwner(@NotNull LifecycleOwner <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.lifecycleOwner = <set-?>;
    }

    @Nullable
    public TimelineItemViewHolder generate(@Nullable ViewGroup parent, int viewType) {
        TimelineItemView timelineItemView = LayoutInflater.from(parent != null ? parent.getContext() : null).inflate(C0644R.layout.item_action_timeline, parent, false);
        if (timelineItemView != null) {
            return new ActionTimelineViewHolder(timelineItemView, this.lifecycleOwner);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.cooey.common.views.TimelineItemView");
    }
}
