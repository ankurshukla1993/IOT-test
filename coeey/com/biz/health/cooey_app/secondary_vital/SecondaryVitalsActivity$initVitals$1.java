package com.biz.health.cooey_app.secondary_vital;

import android.arch.lifecycle.Observer;
import com.cooey.common.adapters.TimelineRecyclerAdapter;
import com.cooey.common.vo.timeline.TimelineItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "timelineItems", "", "Lcom/cooey/common/vo/timeline/TimelineItem;", "onChanged"}, k = 3, mv = {1, 1, 7})
/* compiled from: SecondaryVitalsActivity.kt */
final class SecondaryVitalsActivity$initVitals$1<T> implements Observer<List<? extends TimelineItem>> {
    final /* synthetic */ SecondaryVitalsActivity this$0;

    SecondaryVitalsActivity$initVitals$1(SecondaryVitalsActivity secondaryVitalsActivity) {
        this.this$0 = secondaryVitalsActivity;
    }

    public final void onChanged(@Nullable List<TimelineItem> timelineItems) {
        List filteredItems;
        if (timelineItems != null) {
            Collection destination$iv$iv = new ArrayList();
            for (TimelineItem element$iv$iv : timelineItems) {
                if (CollectionsKt___CollectionsKt.contains(this.this$0.getVitalTypes(), element$iv$iv.getSubType())) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            filteredItems = (List) destination$iv$iv;
        } else {
            filteredItems = null;
        }
        TimelineRecyclerAdapter access$getAdapter$p = this.this$0.adapter;
        if (access$getAdapter$p != null) {
            access$getAdapter$p.setTimelineItems(filteredItems);
        }
        access$getAdapter$p = this.this$0.adapter;
        if (access$getAdapter$p != null) {
            access$getAdapter$p.notifyDataSetChanged();
        }
    }
}
