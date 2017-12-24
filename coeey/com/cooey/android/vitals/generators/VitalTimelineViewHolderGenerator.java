package com.cooey.android.vitals.generators;

import android.arch.lifecycle.LifecycleOwner;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cooey.android.vitals.R;
import com.cooey.android.vitals.exceptions.VitalViewHolderGenerationException;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.android.vitals.viewholders.VitalTimelineItemViewHolder;
import com.cooey.common.generators.ITimelineViewHolderGenerator;
import com.cooey.common.holders.TimelineItemViewHolder;
import com.cooey.common.views.TimelineItemView;
import com.cooey.common.vo.timeline.TimelineItemType;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001b"}, d2 = {"Lcom/cooey/android/vitals/generators/VitalTimelineViewHolderGenerator;", "Lcom/cooey/common/generators/ITimelineViewHolderGenerator;", "lifecycleOwner", "Landroid/arch/lifecycle/LifecycleOwner;", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "(Landroid/arch/lifecycle/LifecycleOwner;Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "getLifecycleOwner", "()Landroid/arch/lifecycle/LifecycleOwner;", "setLifecycleOwner", "(Landroid/arch/lifecycle/LifecycleOwner;)V", "showPrimary", "", "getShowPrimary", "()Z", "setShowPrimary", "(Z)V", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "generate", "Lcom/cooey/common/holders/TimelineItemViewHolder;", "parent", "Landroid/view/ViewGroup;", "viewType", "", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalTimelineViewHolderGenerator.kt */
public final class VitalTimelineViewHolderGenerator implements ITimelineViewHolderGenerator {
    @Nullable
    private LifecycleOwner lifecycleOwner;
    private boolean showPrimary;
    @Nullable
    private VitalRepository vitalRepository;

    public VitalTimelineViewHolderGenerator(@Nullable LifecycleOwner lifecycleOwner, @Nullable VitalRepository vitalRepository) {
        this.lifecycleOwner = lifecycleOwner;
        this.vitalRepository = vitalRepository;
    }

    @Nullable
    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    @Nullable
    public final VitalRepository getVitalRepository() {
        return this.vitalRepository;
    }

    public final void setLifecycleOwner(@Nullable LifecycleOwner <set-?>) {
        this.lifecycleOwner = <set-?>;
    }

    public final void setVitalRepository(@Nullable VitalRepository <set-?>) {
        this.vitalRepository = <set-?>;
    }

    public final boolean getShowPrimary() {
        return this.showPrimary;
    }

    public final void setShowPrimary(boolean <set-?>) {
        this.showPrimary = <set-?>;
    }

    @Nullable
    public TimelineItemViewHolder generate(@Nullable ViewGroup parent, int viewType) {
        if (viewType == TimelineItemType.INSTANCE.getVITAL()) {
            TimelineItemView timelineItemView = LayoutInflater.from(parent != null ? parent.getContext() : null).inflate(R.layout.layout_timeline_item_vital, parent, false);
            if (timelineItemView == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.cooey.common.views.TimelineItemView");
            }
            timelineItemView = timelineItemView;
            LifecycleOwner lifecycleOwner = this.lifecycleOwner;
            VitalRepository vitalRepository = this.vitalRepository;
            if (vitalRepository == null) {
                Intrinsics.throwNpe();
            }
            return new VitalTimelineItemViewHolder(timelineItemView, lifecycleOwner, vitalRepository, this.showPrimary);
        }
        throw new VitalViewHolderGenerationException("This cannot generate view holders other than the vital view holders");
    }
}
