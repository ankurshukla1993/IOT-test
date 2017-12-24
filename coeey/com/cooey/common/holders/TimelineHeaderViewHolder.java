package com.cooey.common.holders;

import android.view.View;
import com.cooey.common.vo.timeline.TimelineItem;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\r"}, d2 = {"Lcom/cooey/common/holders/TimelineHeaderViewHolder;", "Lcom/cooey/common/holders/TimelineItemViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "getView", "()Landroid/view/View;", "setView", "bind", "", "currentTimelineItem", "Lcom/cooey/common/vo/timeline/TimelineItem;", "prevTimelineItem", "common_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: TimelineHeaderViewHolder.kt */
public final class TimelineHeaderViewHolder extends TimelineItemViewHolder {
    @Nullable
    private View view;

    public TimelineHeaderViewHolder(@Nullable View view) {
        super(view);
        this.view = view;
    }

    @Nullable
    public final View getView() {
        return this.view;
    }

    public final void setView(@Nullable View <set-?>) {
        this.view = <set-?>;
    }

    public void bind(@Nullable TimelineItem currentTimelineItem, @Nullable TimelineItem prevTimelineItem) {
    }
}
