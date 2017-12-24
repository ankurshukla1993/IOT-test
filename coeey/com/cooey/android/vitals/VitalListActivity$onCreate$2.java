package com.cooey.android.vitals;

import android.arch.lifecycle.Observer;
import com.cooey.common.adapters.TimelineRecyclerAdapter;
import com.cooey.common.vo.timeline.TimelineItem;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "timelineItems", "", "Lcom/cooey/common/vo/timeline/TimelineItem;", "onChanged"}, k = 3, mv = {1, 1, 7})
/* compiled from: VitalListActivity.kt */
final class VitalListActivity$onCreate$2<T> implements Observer<List<? extends TimelineItem>> {
    final /* synthetic */ TimelineRecyclerAdapter $adapter;

    VitalListActivity$onCreate$2(TimelineRecyclerAdapter timelineRecyclerAdapter) {
        this.$adapter = timelineRecyclerAdapter;
    }

    public final void onChanged(@Nullable List<TimelineItem> timelineItems) {
        this.$adapter.setTimelineItems(timelineItems);
        this.$adapter.notifyDataSetChanged();
    }
}
