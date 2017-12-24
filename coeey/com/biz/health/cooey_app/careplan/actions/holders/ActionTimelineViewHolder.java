package com.biz.health.cooey_app.careplan.actions.holders;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.PatientApplication;
import com.cooey.android.vitals.helpers.DateHelper;
import com.cooey.common.CommonDatabase;
import com.cooey.common.dao.ActionItemDao;
import com.cooey.common.holders.TimelineItemViewHolder;
import com.cooey.common.views.TimelineItemView;
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

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010+H\u0016R\"\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\"\u0010\u001e\u001a\n \t*\u0004\u0018\u00010\b0\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000b\"\u0004\b \u0010\rR\"\u0010!\u001a\n \t*\u0004\u0018\u00010\b0\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000b\"\u0004\b#\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006-"}, d2 = {"Lcom/biz/health/cooey_app/careplan/actions/holders/ActionTimelineViewHolder;", "Lcom/cooey/common/holders/TimelineItemViewHolder;", "view", "Lcom/cooey/common/views/TimelineItemView;", "lifecycleOwner", "Landroid/arch/lifecycle/LifecycleOwner;", "(Lcom/cooey/common/views/TimelineItemView;Landroid/arch/lifecycle/LifecycleOwner;)V", "actionTextView", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "getActionTextView", "()Landroid/widget/TextView;", "setActionTextView", "(Landroid/widget/TextView;)V", "dateFormat", "Ljava/text/DateFormat;", "getDateFormat", "()Ljava/text/DateFormat;", "setDateFormat", "(Ljava/text/DateFormat;)V", "dateHelper", "Lcom/cooey/android/vitals/helpers/DateHelper;", "getDateHelper", "()Lcom/cooey/android/vitals/helpers/DateHelper;", "setDateHelper", "(Lcom/cooey/android/vitals/helpers/DateHelper;)V", "getLifecycleOwner", "()Landroid/arch/lifecycle/LifecycleOwner;", "setLifecycleOwner", "(Landroid/arch/lifecycle/LifecycleOwner;)V", "statusTextView", "getStatusTextView", "setStatusTextView", "timeTextView", "getTimeTextView", "setTimeTextView", "getView", "()Lcom/cooey/common/views/TimelineItemView;", "setView", "(Lcom/cooey/common/views/TimelineItemView;)V", "bind", "", "currentTimelineItem", "Lcom/cooey/common/vo/timeline/TimelineItem;", "prevTimelineItem", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: ActionTimelineViewHolder.kt */
public final class ActionTimelineViewHolder extends TimelineItemViewHolder {
    private TextView actionTextView = ((TextView) this.view.findViewById(C0644R.id.action_text));
    @NotNull
    private DateFormat dateFormat = new SimpleDateFormat("h:mm a");
    @NotNull
    private DateHelper dateHelper = new DateHelper();
    @NotNull
    private LifecycleOwner lifecycleOwner;
    private TextView statusTextView = ((TextView) this.view.findViewById(C0644R.id.status_text));
    private TextView timeTextView = ((TextView) this.view.findViewById(C0644R.id.time));
    @NotNull
    private TimelineItemView view;

    public ActionTimelineViewHolder(@NotNull TimelineItemView view, @NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        super(view);
        this.view = view;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NotNull
    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    @NotNull
    public final TimelineItemView getView() {
        return this.view;
    }

    public final void setLifecycleOwner(@NotNull LifecycleOwner <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.lifecycleOwner = <set-?>;
    }

    public final void setView(@NotNull TimelineItemView <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.view = <set-?>;
    }

    public final TextView getActionTextView() {
        return this.actionTextView;
    }

    public final void setActionTextView(TextView <set-?>) {
        this.actionTextView = <set-?>;
    }

    public final TextView getStatusTextView() {
        return this.statusTextView;
    }

    public final void setStatusTextView(TextView <set-?>) {
        this.statusTextView = <set-?>;
    }

    public final TextView getTimeTextView() {
        return this.timeTextView;
    }

    public final void setTimeTextView(TextView <set-?>) {
        this.timeTextView = <set-?>;
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
            LiveData actionItemLiveData;
            TimelineItemView timelineItemView;
            boolean isSameDay;
            TimelineItemView timelineItemView2;
            CommonDatabase commonDatabase = PatientApplication.Companion.getCommonDatabase();
            if (commonDatabase != null) {
                ActionItemDao ActionItemDao = commonDatabase.ActionItemDao();
                if (ActionItemDao != null) {
                    actionItemLiveData = ActionItemDao.getActionItem(currentTimelineItem.getId());
                    if (actionItemLiveData != null) {
                        actionItemLiveData.observe(this.lifecycleOwner, new ActionTimelineViewHolder$bind$1(this));
                    }
                    this.view.setTimestamp(currentTimelineItem.getTimestamp());
                    if (prevTimelineItem != null) {
                        timelineItemView = this.view;
                        if (timelineItemView != null) {
                            timelineItemView.showDate(true);
                        }
                    } else {
                        isSameDay = this.dateHelper.checkIfSameDay(currentTimelineItem.getTimestamp(), prevTimelineItem.getTimestamp());
                        timelineItemView2 = this.view;
                        if (timelineItemView2 != null) {
                            timelineItemView2.showDate(isSameDay);
                        }
                    }
                    this.timeTextView.setText(this.dateFormat.format(new Date(currentTimelineItem.getTimestamp())) + ", " + DateTimeUtils.formatWithStyle(new Date(currentTimelineItem.getTimestamp()), DateTimeStyle.FULL));
                }
            }
            actionItemLiveData = null;
            if (actionItemLiveData != null) {
                actionItemLiveData.observe(this.lifecycleOwner, new ActionTimelineViewHolder$bind$1(this));
            }
            this.view.setTimestamp(currentTimelineItem.getTimestamp());
            if (prevTimelineItem != null) {
                isSameDay = this.dateHelper.checkIfSameDay(currentTimelineItem.getTimestamp(), prevTimelineItem.getTimestamp());
                timelineItemView2 = this.view;
                if (timelineItemView2 != null) {
                    if (isSameDay) {
                    }
                    timelineItemView2.showDate(isSameDay);
                }
            } else {
                timelineItemView = this.view;
                if (timelineItemView != null) {
                    timelineItemView.showDate(true);
                }
            }
            this.timeTextView.setText(this.dateFormat.format(new Date(currentTimelineItem.getTimestamp())) + ", " + DateTimeUtils.formatWithStyle(new Date(currentTimelineItem.getTimestamp()), DateTimeStyle.FULL));
        }
    }
}
