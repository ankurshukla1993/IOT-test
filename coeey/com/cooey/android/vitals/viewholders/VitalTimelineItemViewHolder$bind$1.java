package com.cooey.android.vitals.viewholders;

import android.arch.lifecycle.Observer;
import android.widget.TextView;
import com.cooey.android.vitals.Vital;
import com.cooey.common.views.TimelineItemView;
import com.cooey.common.vo.timeline.TimelineItem;
import com.github.thunder413.datetimeutils.DateTimeStyle;
import com.github.thunder413.datetimeutils.DateTimeUtils;
import humanize.util.Constants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "vital", "Lcom/cooey/android/vitals/Vital;", "onChanged"}, k = 3, mv = {1, 1, 7})
/* compiled from: VitalTimelineItemViewHolder.kt */
final class VitalTimelineItemViewHolder$bind$1<T> implements Observer<Vital> {
    final /* synthetic */ TimelineItem $currentTimelineItem;
    final /* synthetic */ TimelineItem $prevTimelineItem;
    final /* synthetic */ VitalTimelineItemViewHolder this$0;

    VitalTimelineItemViewHolder$bind$1(VitalTimelineItemViewHolder vitalTimelineItemViewHolder, TimelineItem timelineItem, TimelineItem timelineItem2) {
        this.this$0 = vitalTimelineItemViewHolder;
        this.$currentTimelineItem = timelineItem;
        this.$prevTimelineItem = timelineItem2;
    }

    public final void onChanged(@Nullable Vital vital) {
        String vitalType;
        Long valueOf;
        TimelineItemView view;
        boolean z = false;
        Long l = null;
        TextView vitalNameTextView = this.this$0.getVitalNameTextView();
        if (vitalNameTextView != null) {
            if (vital != null) {
                vitalType = vital.getVitalType();
                if (vitalType != null) {
                    vitalType = StringsKt__StringsJVMKt.replace$default(vitalType, EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR, Constants.SPACE, false, 4, null);
                    vitalNameTextView.setText(vitalType);
                }
            }
            vitalType = null;
            vitalNameTextView.setText(vitalType);
        }
        TextView access$getTimeTextView$p = this.this$0.timeTextView;
        if (access$getTimeTextView$p != null) {
            access$getTimeTextView$p.setText(this.this$0.getDateFormat().format(new Date(this.$currentTimelineItem.getTimestamp())) + ", " + DateTimeUtils.formatWithStyle(new Date(this.$currentTimelineItem.getTimestamp()), DateTimeStyle.FULL));
        }
        VitalTimelineItemViewHolder vitalTimelineItemViewHolder = this.this$0;
        if (vital != null) {
            vitalType = vital.getParameters();
        } else {
            vitalType = null;
        }
        vitalTimelineItemViewHolder.generateParametersView(vitalType);
        if (vital != null) {
            valueOf = Long.valueOf(vital.getTakenOn());
        } else {
            valueOf = null;
        }
        if (valueOf != null) {
            view = this.this$0.getView();
            if (view != null) {
                if (vital != null) {
                    l = Long.valueOf(vital.getTakenOn());
                }
                if (l == null) {
                    Intrinsics.throwNpe();
                }
                view.setTimestamp(l.longValue());
            }
        }
        if (this.$currentTimelineItem == null || this.$prevTimelineItem == null) {
            view = this.this$0.getView();
            if (view != null) {
                view.showDate(true);
                return;
            }
            return;
        }
        boolean isSameDay = this.this$0.getDateHelper().checkIfSameDay(this.$currentTimelineItem.getTimestamp(), this.$prevTimelineItem.getTimestamp());
        view = this.this$0.getView();
        if (view != null) {
            if (!isSameDay) {
                z = true;
            }
            view.showDate(z);
        }
    }
}
