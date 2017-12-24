package com.cooey.common.generators;

import android.arch.lifecycle.LifecycleOwner;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cooey.common.CommonDatabase;
import com.cooey.common.R;
import com.cooey.common.holders.ActionTimelineViewHolder;
import com.cooey.common.holders.TimelineItemViewHolder;
import com.cooey.common.views.TimelineItemView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/cooey/common/generators/ActionTimelineItemHolderGenerator;", "Lcom/cooey/common/generators/ITimelineViewHolderGenerator;", "lifecycleOwner", "Landroid/arch/lifecycle/LifecycleOwner;", "commonDatabase", "Lcom/cooey/common/CommonDatabase;", "(Landroid/arch/lifecycle/LifecycleOwner;Lcom/cooey/common/CommonDatabase;)V", "getCommonDatabase", "()Lcom/cooey/common/CommonDatabase;", "setCommonDatabase", "(Lcom/cooey/common/CommonDatabase;)V", "getLifecycleOwner", "()Landroid/arch/lifecycle/LifecycleOwner;", "setLifecycleOwner", "(Landroid/arch/lifecycle/LifecycleOwner;)V", "generate", "Lcom/cooey/common/holders/TimelineItemViewHolder;", "parent", "Landroid/view/ViewGroup;", "viewType", "", "common_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: ActionTimelineItemHolderGenerator.kt */
public final class ActionTimelineItemHolderGenerator implements ITimelineViewHolderGenerator {
    @NotNull
    private CommonDatabase commonDatabase;
    @NotNull
    private LifecycleOwner lifecycleOwner;

    public ActionTimelineItemHolderGenerator(@NotNull LifecycleOwner lifecycleOwner, @NotNull CommonDatabase commonDatabase) {
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkParameterIsNotNull(commonDatabase, "commonDatabase");
        this.lifecycleOwner = lifecycleOwner;
        this.commonDatabase = commonDatabase;
    }

    @NotNull
    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    public final void setLifecycleOwner(@NotNull LifecycleOwner <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.lifecycleOwner = <set-?>;
    }

    @NotNull
    public final CommonDatabase getCommonDatabase() {
        return this.commonDatabase;
    }

    public final void setCommonDatabase(@NotNull CommonDatabase <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.commonDatabase = <set-?>;
    }

    @Nullable
    public TimelineItemViewHolder generate(@Nullable ViewGroup parent, int viewType) {
        TimelineItemView timelineItemView = LayoutInflater.from(parent != null ? parent.getContext() : null).inflate(R.layout.item_action_timeline, parent, false);
        if (timelineItemView != null) {
            return new ActionTimelineViewHolder(timelineItemView, this.lifecycleOwner, this.commonDatabase);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.cooey.common.views.TimelineItemView");
    }
}
