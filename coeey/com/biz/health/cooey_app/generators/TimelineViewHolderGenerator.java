package com.biz.health.cooey_app.generators;

import android.arch.lifecycle.LifecycleOwner;
import android.view.ViewGroup;
import com.biz.health.cooey_app.careplan.actions.ActionTimelineItemHolderGenerator;
import com.biz.health.cooey_app.note.NoteTimelineViewHolderGenerator;
import com.cooey.android.vitals.generators.VitalTimelineViewHolderGenerator;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.common.generators.ITimelineViewHolderGenerator;
import com.cooey.common.holders.TimelineItemViewHolder;
import com.cooey.common.vo.timeline.TimelineItemType;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\tH\u0016R6\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0001`\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u001c"}, d2 = {"Lcom/biz/health/cooey_app/generators/TimelineViewHolderGenerator;", "Lcom/cooey/common/generators/ITimelineViewHolderGenerator;", "lifecycleOwner", "Landroid/arch/lifecycle/LifecycleOwner;", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "(Landroid/arch/lifecycle/LifecycleOwner;Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "generatorMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getGeneratorMap", "()Ljava/util/HashMap;", "setGeneratorMap", "(Ljava/util/HashMap;)V", "getLifecycleOwner", "()Landroid/arch/lifecycle/LifecycleOwner;", "setLifecycleOwner", "(Landroid/arch/lifecycle/LifecycleOwner;)V", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "generate", "Lcom/cooey/common/holders/TimelineItemViewHolder;", "parent", "Landroid/view/ViewGroup;", "viewType", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: TimelineViewHolderGenerator.kt */
public final class TimelineViewHolderGenerator implements ITimelineViewHolderGenerator {
    @NotNull
    private HashMap<Integer, ITimelineViewHolderGenerator> generatorMap = new HashMap();
    @NotNull
    private LifecycleOwner lifecycleOwner;
    @NotNull
    private VitalRepository vitalRepository;

    public TimelineViewHolderGenerator(@NotNull LifecycleOwner lifecycleOwner, @NotNull VitalRepository vitalRepository) {
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkParameterIsNotNull(vitalRepository, "vitalRepository");
        this.lifecycleOwner = lifecycleOwner;
        this.vitalRepository = vitalRepository;
        this.generatorMap.put(Integer.valueOf(TimelineItemType.INSTANCE.getVITAL()), new VitalTimelineViewHolderGenerator(this.lifecycleOwner, this.vitalRepository));
        this.generatorMap.put(Integer.valueOf(TimelineItemType.INSTANCE.getNOTE()), new NoteTimelineViewHolderGenerator());
        this.generatorMap.put(Integer.valueOf(TimelineItemType.INSTANCE.getEVENT()), new EventTimelineViewHolderGenerator());
        this.generatorMap.put(Integer.valueOf(TimelineItemType.INSTANCE.getACTION()), new ActionTimelineItemHolderGenerator(this.lifecycleOwner));
    }

    @NotNull
    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    @NotNull
    public final VitalRepository getVitalRepository() {
        return this.vitalRepository;
    }

    public final void setLifecycleOwner(@NotNull LifecycleOwner <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.lifecycleOwner = <set-?>;
    }

    public final void setVitalRepository(@NotNull VitalRepository <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.vitalRepository = <set-?>;
    }

    @NotNull
    public final HashMap<Integer, ITimelineViewHolderGenerator> getGeneratorMap() {
        return this.generatorMap;
    }

    public final void setGeneratorMap(@NotNull HashMap<Integer, ITimelineViewHolderGenerator> <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.generatorMap = <set-?>;
    }

    @Nullable
    public TimelineItemViewHolder generate(@Nullable ViewGroup parent, int viewType) {
        ITimelineViewHolderGenerator iTimelineViewHolderGenerator = (ITimelineViewHolderGenerator) this.generatorMap.get(Integer.valueOf(viewType));
        return iTimelineViewHolderGenerator != null ? iTimelineViewHolderGenerator.generate(parent, viewType) : null;
    }
}
