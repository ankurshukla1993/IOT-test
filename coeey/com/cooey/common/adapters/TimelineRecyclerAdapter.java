package com.cooey.common.adapters;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.ViewGroup;
import com.cooey.common.generators.ITimelineViewHolderGenerator;
import com.cooey.common.holders.TimelineHeaderViewHolder;
import com.cooey.common.holders.TimelineItemViewHolder;
import com.cooey.common.vo.timeline.TimelineItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\u0010\nJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0016J\u001a\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001f\u001a\u00020\u001dH\u0016J\u001a\u0010#\u001a\u00020\u00022\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020\u001dH\u0016R\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006'"}, d2 = {"Lcom/cooey/common/adapters/TimelineRecyclerAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/cooey/common/holders/TimelineItemViewHolder;", "timelineViewHolderGenerator", "Lcom/cooey/common/generators/ITimelineViewHolderGenerator;", "timelineHeaderViewHolder", "Lcom/cooey/common/holders/TimelineHeaderViewHolder;", "timelineItems", "", "Lcom/cooey/common/vo/timeline/TimelineItem;", "(Lcom/cooey/common/generators/ITimelineViewHolderGenerator;Lcom/cooey/common/holders/TimelineHeaderViewHolder;Ljava/util/List;)V", "isHeaderEnabled", "", "()Z", "setHeaderEnabled", "(Z)V", "getTimelineHeaderViewHolder", "()Lcom/cooey/common/holders/TimelineHeaderViewHolder;", "setTimelineHeaderViewHolder", "(Lcom/cooey/common/holders/TimelineHeaderViewHolder;)V", "getTimelineItems", "()Ljava/util/List;", "setTimelineItems", "(Ljava/util/List;)V", "getTimelineViewHolderGenerator", "()Lcom/cooey/common/generators/ITimelineViewHolderGenerator;", "setTimelineViewHolderGenerator", "(Lcom/cooey/common/generators/ITimelineViewHolderGenerator;)V", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "common_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: TimelineRecyclerAdapter.kt */
public final class TimelineRecyclerAdapter extends Adapter<TimelineItemViewHolder> {
    private boolean isHeaderEnabled;
    @Nullable
    private TimelineHeaderViewHolder timelineHeaderViewHolder;
    @Nullable
    private List<TimelineItem> timelineItems;
    @NotNull
    private ITimelineViewHolderGenerator timelineViewHolderGenerator;

    public TimelineRecyclerAdapter(@NotNull ITimelineViewHolderGenerator timelineViewHolderGenerator, @Nullable TimelineHeaderViewHolder timelineHeaderViewHolder, @Nullable List<TimelineItem> timelineItems) {
        Intrinsics.checkParameterIsNotNull(timelineViewHolderGenerator, "timelineViewHolderGenerator");
        this.timelineViewHolderGenerator = timelineViewHolderGenerator;
        this.timelineHeaderViewHolder = timelineHeaderViewHolder;
        this.timelineItems = timelineItems;
    }

    @Nullable
    public final TimelineHeaderViewHolder getTimelineHeaderViewHolder() {
        return this.timelineHeaderViewHolder;
    }

    @Nullable
    public final List<TimelineItem> getTimelineItems() {
        return this.timelineItems;
    }

    @NotNull
    public final ITimelineViewHolderGenerator getTimelineViewHolderGenerator() {
        return this.timelineViewHolderGenerator;
    }

    public final void setTimelineHeaderViewHolder(@Nullable TimelineHeaderViewHolder <set-?>) {
        this.timelineHeaderViewHolder = <set-?>;
    }

    public final void setTimelineItems(@Nullable List<TimelineItem> <set-?>) {
        this.timelineItems = <set-?>;
    }

    public final void setTimelineViewHolderGenerator(@NotNull ITimelineViewHolderGenerator <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.timelineViewHolderGenerator = <set-?>;
    }

    public final boolean isHeaderEnabled() {
        return this.isHeaderEnabled;
    }

    public final void setHeaderEnabled(boolean <set-?>) {
        this.isHeaderEnabled = <set-?>;
    }

    public void onBindViewHolder(@Nullable TimelineItemViewHolder holder, int position) {
        List list;
        TimelineItem timelineItem;
        TimelineItem timelineItem2;
        if (this.isHeaderEnabled) {
            if (position == 0) {
                if (holder != null) {
                    holder.bind(null, null);
                }
            } else if (position == 1) {
                if (holder != null) {
                    list = this.timelineItems;
                    holder.bind(list != null ? (TimelineItem) list.get(position - 1) : null, null);
                }
            } else if (holder != null) {
                list = this.timelineItems;
                timelineItem = list != null ? (TimelineItem) list.get(position - 1) : null;
                if (timelineItem == null) {
                    Intrinsics.throwNpe();
                }
                list = this.timelineItems;
                timelineItem2 = list != null ? (TimelineItem) list.get(position - 2) : null;
                if (timelineItem2 == null) {
                    Intrinsics.throwNpe();
                }
                holder.bind(timelineItem, timelineItem2);
            }
        } else if (position > 0) {
            if (holder != null) {
                list = this.timelineItems;
                timelineItem = list != null ? (TimelineItem) list.get(position) : null;
                if (timelineItem == null) {
                    Intrinsics.throwNpe();
                }
                list = this.timelineItems;
                timelineItem2 = list != null ? (TimelineItem) list.get(position - 1) : null;
                if (timelineItem2 == null) {
                    Intrinsics.throwNpe();
                }
                holder.bind(timelineItem, timelineItem2);
            }
        } else if (holder != null) {
            list = this.timelineItems;
            timelineItem2 = list != null ? (TimelineItem) list.get(position) : null;
            if (timelineItem2 == null) {
                Intrinsics.throwNpe();
            }
            holder.bind(timelineItem2, null);
        }
    }

    @NotNull
    public TimelineItemViewHolder onCreateViewHolder(@Nullable ViewGroup parent, int viewType) {
        if (viewType == 100) {
            TimelineHeaderViewHolder timelineHeaderViewHolder = this.timelineHeaderViewHolder;
            if (timelineHeaderViewHolder != null) {
                return timelineHeaderViewHolder;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.cooey.common.holders.TimelineItemViewHolder");
        }
        TimelineItemViewHolder generate = this.timelineViewHolderGenerator.generate(parent, viewType);
        if (generate != null) {
            return generate;
        }
        Intrinsics.throwNpe();
        return generate;
    }

    public int getItemCount() {
        int count = 0;
        try {
            if (this.timelineItems != null) {
                List list = this.timelineItems;
                Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
                if (valueOf == null) {
                    Intrinsics.throwNpe();
                }
                count = valueOf.intValue();
            }
            if (this.isHeaderEnabled) {
                return count + 1;
            }
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    public int getItemViewType(int position) {
        Integer num = null;
        List list;
        TimelineItem timelineItem;
        if (!this.isHeaderEnabled) {
            Integer valueOf;
            list = this.timelineItems;
            if (list != null) {
                timelineItem = (TimelineItem) list.get(position);
                if (timelineItem != null) {
                    valueOf = Integer.valueOf(timelineItem.getType());
                    if (valueOf == null) {
                        Intrinsics.throwNpe();
                    }
                    return valueOf.intValue();
                }
            }
            valueOf = null;
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            return valueOf.intValue();
        } else if (position == 0) {
            return 100;
        } else {
            list = this.timelineItems;
            if (list != null) {
                timelineItem = (TimelineItem) list.get(position - 1);
                if (timelineItem != null) {
                    num = Integer.valueOf(timelineItem.getType());
                }
            }
            if (num == null) {
                Intrinsics.throwNpe();
            }
            return num.intValue();
        }
    }
}
