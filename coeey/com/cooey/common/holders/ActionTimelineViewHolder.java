package com.cooey.common.holders;

import android.arch.lifecycle.LifecycleOwner;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.cooey.common.CommonDatabase;
import com.cooey.common.R;
import com.cooey.common.adapters.FeedBackAdapter;
import com.cooey.common.utils.DateHelper;
import com.cooey.common.views.TimelineItemView;
import com.cooey.common.vo.FeedBackInputItem;
import com.cooey.common.vo.timeline.TimelineItem;
import com.github.thunder413.datetimeutils.DateTimeStyle;
import com.github.thunder413.datetimeutils.DateTimeUtils;
import com.google.gson.Gson;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001c\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u000108H\u0016J\u0018\u0010:\u001a\u0002062\u000e\u0010;\u001a\n\u0012\u0004\u0012\u00020=\u0018\u00010<H\u0002J*\u0010>\u001a\n \u000b*\u0004\u0018\u0001H?H?\"\u0006\b\u0000\u0010?\u0018\u0001*\u00020@2\u0006\u0010A\u001a\u00020BH\b¢\u0006\u0002\u0010CR\"\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\n \u000b*\u0004\u0018\u00010\u00110\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\"\u0010+\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\r\"\u0004\b-\u0010\u000fR\"\u0010.\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\r\"\u0004\b0\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104¨\u0006D"}, d2 = {"Lcom/cooey/common/holders/ActionTimelineViewHolder;", "Lcom/cooey/common/holders/TimelineItemViewHolder;", "view", "Lcom/cooey/common/views/TimelineItemView;", "lifecycleOwner", "Landroid/arch/lifecycle/LifecycleOwner;", "commonData", "Lcom/cooey/common/CommonDatabase;", "(Lcom/cooey/common/views/TimelineItemView;Landroid/arch/lifecycle/LifecycleOwner;Lcom/cooey/common/CommonDatabase;)V", "actionTextView", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "getActionTextView", "()Landroid/widget/TextView;", "setActionTextView", "(Landroid/widget/TextView;)V", "btnFeedback", "Landroid/widget/Button;", "getBtnFeedback", "()Landroid/widget/Button;", "setBtnFeedback", "(Landroid/widget/Button;)V", "commonDatbase", "getCommonDatbase", "()Lcom/cooey/common/CommonDatabase;", "setCommonDatbase", "(Lcom/cooey/common/CommonDatabase;)V", "dateFormat", "Ljava/text/DateFormat;", "getDateFormat", "()Ljava/text/DateFormat;", "setDateFormat", "(Ljava/text/DateFormat;)V", "dateHelper", "Lcom/cooey/common/utils/DateHelper;", "getDateHelper", "()Lcom/cooey/common/utils/DateHelper;", "setDateHelper", "(Lcom/cooey/common/utils/DateHelper;)V", "getLifecycleOwner", "()Landroid/arch/lifecycle/LifecycleOwner;", "setLifecycleOwner", "(Landroid/arch/lifecycle/LifecycleOwner;)V", "statusTextView", "getStatusTextView", "setStatusTextView", "timeTextView", "getTimeTextView", "setTimeTextView", "getView", "()Lcom/cooey/common/views/TimelineItemView;", "setView", "(Lcom/cooey/common/views/TimelineItemView;)V", "bind", "", "currentTimelineItem", "Lcom/cooey/common/vo/timeline/TimelineItem;", "prevTimelineItem", "generateFeedBackDialog", "feedbackInputItems", "", "Lcom/cooey/common/vo/FeedBackInputItem;", "fromJson", "T", "Lcom/google/gson/Gson;", "json", "", "(Lcom/google/gson/Gson;Ljava/lang/String;)Ljava/lang/Object;", "common_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: ActionTimelineViewHolder.kt */
public final class ActionTimelineViewHolder extends TimelineItemViewHolder {
    private TextView actionTextView = ((TextView) this.view.findViewById(R.id.action_text));
    private Button btnFeedback = ((Button) this.view.findViewById(R.id.btn_feedback));
    @NotNull
    private CommonDatabase commonDatbase;
    @NotNull
    private DateFormat dateFormat = new SimpleDateFormat("h:mm a");
    @NotNull
    private DateHelper dateHelper = new DateHelper();
    @NotNull
    private LifecycleOwner lifecycleOwner;
    private TextView statusTextView = ((TextView) this.view.findViewById(R.id.status_text));
    private TextView timeTextView = ((TextView) this.view.findViewById(R.id.time));
    @NotNull
    private TimelineItemView view;

    public ActionTimelineViewHolder(@NotNull TimelineItemView view, @NotNull LifecycleOwner lifecycleOwner, @NotNull CommonDatabase commonData) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkParameterIsNotNull(commonData, "commonData");
        super(view);
        this.view = view;
        this.lifecycleOwner = lifecycleOwner;
        this.commonDatbase = commonData;
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

    public final Button getBtnFeedback() {
        return this.btnFeedback;
    }

    public final void setBtnFeedback(Button <set-?>) {
        this.btnFeedback = <set-?>;
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

    @NotNull
    public final CommonDatabase getCommonDatbase() {
        return this.commonDatbase;
    }

    public final void setCommonDatbase(@NotNull CommonDatabase <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.commonDatbase = <set-?>;
    }

    public void bind(@Nullable TimelineItem currentTimelineItem, @Nullable TimelineItem prevTimelineItem) {
        if (currentTimelineItem != null) {
            this.commonDatbase.ActionItemDao().getActionItem(currentTimelineItem.getId()).observe(this.lifecycleOwner, new ActionTimelineViewHolder$bind$1(this));
            this.view.setTimestamp(currentTimelineItem.getTimestamp());
            if (prevTimelineItem == null) {
                this.view.showDate(true);
            } else {
                this.view.showDate(!this.dateHelper.checkIfSameDay(currentTimelineItem.getTimestamp(), prevTimelineItem.getTimestamp()));
            }
            this.timeTextView.setText(this.dateFormat.format(new Date(currentTimelineItem.getTimestamp())) + ", " + DateTimeUtils.formatWithStyle(new Date(currentTimelineItem.getTimestamp()), DateTimeStyle.FULL));
        }
    }

    private final void generateFeedBackDialog(List<? extends FeedBackInputItem> feedbackInputItems) {
        if (feedbackInputItems != null) {
            if ((!((Collection) feedbackInputItems).isEmpty() ? 1 : null) != null) {
                AlertDialog alertDialog = new Builder(this.view.getContext()).create();
                View content = LayoutInflater.from(this.view.getContext()).inflate(R.layout.dialog_feedback_item, null);
                alertDialog.setView(content);
                alertDialog.setTitle("Feedback ");
                Window window = alertDialog.getWindow();
                window.setLayout(-2, -2);
                window.setGravity(17);
                RecyclerView recylerview = content.findViewById(R.id.rev_feedback_item);
                Intrinsics.checkExpressionValueIsNotNull(recylerview, "content.findViewById(R.id.rev_feedback_item)");
                recylerview = recylerview;
                Button btnCancel = content.findViewById(R.id.btn_cancel);
                Intrinsics.checkExpressionValueIsNotNull(btnCancel, "content.findViewById(R.id.btn_cancel)");
                btnCancel.setOnClickListener(new ActionTimelineViewHolder$generateFeedBackDialog$1(alertDialog));
                recylerview.setAdapter(new FeedBackAdapter(this.view.getContext(), feedbackInputItems));
                recylerview.setLayoutManager(new LinearLayoutManager(this.view.getContext()));
                alertDialog.show();
            }
        }
    }

    private final <T> T fromJson(@NotNull Gson $receiver, String json) {
        Intrinsics.needClassReification();
        return $receiver.fromJson(json, new ActionTimelineViewHolder$fromJson$1().getType());
    }
}
