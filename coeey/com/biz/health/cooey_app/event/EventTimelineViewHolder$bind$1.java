package com.biz.health.cooey_app.event;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import com.biz.health.cooey_app.RescheduleEventActivity;
import com.cooey.common.vo.Activity;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref.ObjectRef;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: EventTimelineViewHolder.kt */
final class EventTimelineViewHolder$bind$1 implements OnClickListener {
    final /* synthetic */ ObjectRef $event;
    final /* synthetic */ EventTimelineViewHolder this$0;

    EventTimelineViewHolder$bind$1(EventTimelineViewHolder eventTimelineViewHolder, ObjectRef objectRef) {
        this.this$0 = eventTimelineViewHolder;
        this.$event = objectRef;
    }

    public final void onClick(View it) {
        if (((Activity) this.$event.element) != null) {
            String name;
            Intent intent = new Intent(this.this$0.getView().getContext(), RescheduleEventActivity.class);
            String str = "eventName";
            Activity activity = (Activity) this.$event.element;
            if (activity != null) {
                name = activity.getName();
            } else {
                name = null;
            }
            intent.putExtra(str, name);
            str = "start";
            activity = (Activity) this.$event.element;
            if (activity != null) {
                name = activity.getStart();
            } else {
                name = null;
            }
            intent.putExtra(str, name);
            str = "end";
            activity = (Activity) this.$event.element;
            if (activity != null) {
                name = activity.getEnd();
            } else {
                name = null;
            }
            intent.putExtra(str, name);
            str = "Activity";
            activity = (Activity) this.$event.element;
            if (activity != null) {
                name = activity.getId();
            } else {
                name = null;
            }
            intent.putExtra(str, name);
            intent.putExtra("patientId", ((Activity) this.$event.element).getPatientId());
            intent.putExtra("caretakerId", ((Activity) this.$event.element).getUserId());
            ContextCompat.startActivity(this.this$0.getView().getContext(), intent, null);
        }
    }
}
