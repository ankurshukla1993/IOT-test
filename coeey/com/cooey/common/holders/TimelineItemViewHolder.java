package com.cooey.common.holders;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.cooey.common.vo.timeline.TimelineItem;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH&¨\u0006\n"}, d2 = {"Lcom/cooey/common/holders/TimelineItemViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "currentTimelineItem", "Lcom/cooey/common/vo/timeline/TimelineItem;", "prevTimelineItem", "common_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: TimelineItemViewHolder.kt */
public abstract class TimelineItemViewHolder extends ViewHolder {
    public abstract void bind(@Nullable TimelineItem timelineItem, @Nullable TimelineItem timelineItem2);

    public TimelineItemViewHolder(@Nullable View itemView) {
        super(itemView);
    }
}
