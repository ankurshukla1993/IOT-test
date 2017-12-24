package com.biz.health.cooey_app.note.holders;

import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.cooey.android.vitals.helpers.DateHelper;
import com.cooey.common.holders.TimelineItemViewHolder;
import com.cooey.common.realm_store.NoteStore;
import com.cooey.common.views.TimelineItemView;
import com.cooey.common.vo.Note;
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

@Metadata(bv = {1, 0, 2}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0018\u001a\n \u0013*\u0004\u0018\u00010\u00120\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u0004¨\u0006#"}, d2 = {"Lcom/biz/health/cooey_app/note/holders/NoteTimelineViewHolder;", "Lcom/cooey/common/holders/TimelineItemViewHolder;", "view", "Lcom/cooey/common/views/TimelineItemView;", "(Lcom/cooey/common/views/TimelineItemView;)V", "dateFormat", "Ljava/text/DateFormat;", "getDateFormat", "()Ljava/text/DateFormat;", "setDateFormat", "(Ljava/text/DateFormat;)V", "dateHelper", "Lcom/cooey/android/vitals/helpers/DateHelper;", "getDateHelper", "()Lcom/cooey/android/vitals/helpers/DateHelper;", "setDateHelper", "(Lcom/cooey/android/vitals/helpers/DateHelper;)V", "noteTextView", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "getNoteTextView", "()Landroid/widget/TextView;", "setNoteTextView", "(Landroid/widget/TextView;)V", "timeTextView", "getTimeTextView", "setTimeTextView", "getView", "()Lcom/cooey/common/views/TimelineItemView;", "setView", "bind", "", "currentTimelineItem", "Lcom/cooey/common/vo/timeline/TimelineItem;", "prevTimelineItem", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: NoteTimelineViewHolder.kt */
public final class NoteTimelineViewHolder extends TimelineItemViewHolder {
    @NotNull
    private DateFormat dateFormat = new SimpleDateFormat("h:mm a");
    @NotNull
    private DateHelper dateHelper = new DateHelper();
    private TextView noteTextView = ((TextView) this.view.findViewById(C0644R.id.note_text));
    private TextView timeTextView = ((TextView) this.view.findViewById(C0644R.id.time));
    @NotNull
    private TimelineItemView view;

    public NoteTimelineViewHolder(@NotNull TimelineItemView view) {
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

    public final TextView getNoteTextView() {
        return this.noteTextView;
    }

    public final void setNoteTextView(TextView <set-?>) {
        this.noteTextView = <set-?>;
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
            Note note = NoteStore.getInstance(this.view.getContext()).getNote(currentTimelineItem.getId());
            if (!(note == null || note.getContents() == null)) {
                this.noteTextView.setText(note.getContents());
            }
            this.view.setTimestamp(currentTimelineItem.getTimestamp());
            if (prevTimelineItem == null) {
                TimelineItemView timelineItemView = this.view;
                if (timelineItemView != null) {
                    timelineItemView.showDate(true);
                }
            } else {
                boolean isSameDay = this.dateHelper.checkIfSameDay(currentTimelineItem.getTimestamp(), prevTimelineItem.getTimestamp());
                TimelineItemView timelineItemView2 = this.view;
                if (timelineItemView2 != null) {
                    timelineItemView2.showDate(!isSameDay);
                }
            }
            this.view.findViewById(C0644R.id.note_text);
            this.timeTextView.setText(this.dateFormat.format(new Date(currentTimelineItem.getTimestamp())) + ", " + DateTimeUtils.formatWithStyle(new Date(currentTimelineItem.getTimestamp()), DateTimeStyle.FULL));
        }
    }
}
