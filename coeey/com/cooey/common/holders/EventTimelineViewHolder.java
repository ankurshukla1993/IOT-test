package com.cooey.common.holders;

import android.widget.Button;
import android.widget.TextView;
import com.cooey.common.R;
import com.cooey.common.realm_store.ActivityStore;
import com.cooey.common.utils.DateHelper;
import com.cooey.common.views.TimelineItemView;
import com.cooey.common.vo.Activity;
import com.cooey.common.vo.timeline.TimelineItem;
import com.github.thunder413.datetimeutils.DateTimeStyle;
import com.github.thunder413.datetimeutils.DateTimeUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010*H\u0016R\"\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0018\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\t\"\u0004\b\u001a\u0010\u000bR\"\u0010\u001b\u001a\n \u0007*\u0004\u0018\u00010\u001c0\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010!\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\t\"\u0004\b#\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010\u0004¨\u0006,"}, d2 = {"Lcom/cooey/common/holders/EventTimelineViewHolder;", "Lcom/cooey/common/holders/TimelineItemViewHolder;", "view", "Lcom/cooey/common/views/TimelineItemView;", "(Lcom/cooey/common/views/TimelineItemView;)V", "activityTitleTextView", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "getActivityTitleTextView", "()Landroid/widget/TextView;", "setActivityTitleTextView", "(Landroid/widget/TextView;)V", "dateFormat", "Ljava/text/DateFormat;", "getDateFormat", "()Ljava/text/DateFormat;", "setDateFormat", "(Ljava/text/DateFormat;)V", "dateHelper", "Lcom/cooey/common/utils/DateHelper;", "getDateHelper", "()Lcom/cooey/common/utils/DateHelper;", "setDateHelper", "(Lcom/cooey/common/utils/DateHelper;)V", "eventTextView", "getEventTextView", "setEventTextView", "rescheduleTextView", "Landroid/widget/Button;", "getRescheduleTextView", "()Landroid/widget/Button;", "setRescheduleTextView", "(Landroid/widget/Button;)V", "timeTextView", "getTimeTextView", "setTimeTextView", "getView", "()Lcom/cooey/common/views/TimelineItemView;", "setView", "bind", "", "currentTimelineItem", "Lcom/cooey/common/vo/timeline/TimelineItem;", "prevTimelineItem", "common_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: EventTimelineViewHolder.kt */
public final class EventTimelineViewHolder extends TimelineItemViewHolder {
    private TextView activityTitleTextView = ((TextView) this.view.findViewById(R.id.activity_title));
    @NotNull
    private DateFormat dateFormat = new SimpleDateFormat("h:mm a");
    @NotNull
    private DateHelper dateHelper = new DateHelper();
    private TextView eventTextView = ((TextView) this.view.findViewById(R.id.event_text));
    private Button rescheduleTextView = ((Button) this.view.findViewById(R.id.text_reschedule));
    private TextView timeTextView = ((TextView) this.view.findViewById(R.id.time));
    @NotNull
    private TimelineItemView view;

    public EventTimelineViewHolder(@NotNull TimelineItemView view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super(view);
        this.view = view;
    }

    @NotNull
    public final TimelineItemView getView() {
        return this.view;
    }

    public final void setView(@NotNull TimelineItemView <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.view = <set-?>;
    }

    public final TextView getEventTextView() {
        return this.eventTextView;
    }

    public final void setEventTextView(TextView <set-?>) {
        this.eventTextView = <set-?>;
    }

    public final TextView getActivityTitleTextView() {
        return this.activityTitleTextView;
    }

    public final void setActivityTitleTextView(TextView <set-?>) {
        this.activityTitleTextView = <set-?>;
    }

    public final TextView getTimeTextView() {
        return this.timeTextView;
    }

    public final void setTimeTextView(TextView <set-?>) {
        this.timeTextView = <set-?>;
    }

    public final Button getRescheduleTextView() {
        return this.rescheduleTextView;
    }

    public final void setRescheduleTextView(Button <set-?>) {
        this.rescheduleTextView = <set-?>;
    }

    @NotNull
    public final DateHelper getDateHelper() {
        return this.dateHelper;
    }

    public final void setDateHelper(@NotNull DateHelper <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.dateHelper = <set-?>;
    }

    @NotNull
    public final DateFormat getDateFormat() {
        return this.dateFormat;
    }

    public final void setDateFormat(@NotNull DateFormat <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.dateFormat = <set-?>;
    }

    public void bind(@Nullable TimelineItem currentTimelineItem, @Nullable TimelineItem prevTimelineItem) {
        if (currentTimelineItem != null) {
            Activity event = ActivityStore.getInstance(this.view.getContext()).getActivity(currentTimelineItem.getId());
            if (event != null) {
                this.activityTitleTextView.setText(event.getCategory());
            }
            this.eventTextView.setText(event.getName());
            this.view.setTimestamp(currentTimelineItem.getTimestamp());
            if (prevTimelineItem == null) {
                this.view.showDate(true);
            } else {
                boolean isSameDay = this.dateHelper.checkIfSameDay(currentTimelineItem.getTimestamp(), prevTimelineItem.getTimestamp());
                TimelineItemView timelineItemView = this.view;
                if (timelineItemView != null) {
                    timelineItemView.showDate(!isSameDay);
                }
            }
            this.rescheduleTextView.setOnClickListener(EventTimelineViewHolder$bind$1.INSTANCE);
            this.timeTextView.setText(this.dateFormat.format(new Date(currentTimelineItem.getTimestamp())) + ", " + DateTimeUtils.formatWithStyle(new Date(currentTimelineItem.getTimestamp()), DateTimeStyle.FULL));
        }
    }
}
