package com.biz.health.cooey_app.dashboard.widgets.holders;

import com.biz.health.cooey_app.C0644R;
import com.cooey.common.vo.careplan.Careplan;
import com.github.thunder413.datetimeutils.DateTimeStyle;
import com.github.thunder413.datetimeutils.DateTimeUtils;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J(\u0010\n\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00072\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"com/biz/health/cooey_app/dashboard/widgets/holders/CareplanWidgetViewHolder$getCareplanDetials$1", "Lretrofit2/Callback;", "Lcom/cooey/common/vo/careplan/Careplan;", "(Lcom/biz/health/cooey_app/dashboard/widgets/holders/CareplanWidgetViewHolder;)V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: CareplanWidgetViewHolder.kt */
public final class CareplanWidgetViewHolder$getCareplanDetials$1 implements Callback<Careplan> {
    final /* synthetic */ CareplanWidgetViewHolder this$0;

    CareplanWidgetViewHolder$getCareplanDetials$1(CareplanWidgetViewHolder $outer) {
        this.this$0 = $outer;
    }

    public void onResponse(@Nullable Call<Careplan> call, @Nullable Response<Careplan> response) {
        Long l = null;
        if (response == null || response.body() == null) {
            this.this$0.getCareplanWidgetBinding$app_release().careplanName.setText(this.this$0.context.getString(C0644R.string.careplan_not_assigned));
            return;
        }
        Long valueOf;
        Careplan careplan = (Careplan) response.body();
        this.this$0.getCareplanWidgetBinding$app_release().careplanName.setText(careplan != null ? careplan.getName() : null);
        careplan = (Careplan) response.body();
        if (careplan != null) {
            valueOf = Long.valueOf(careplan.getBeginTime());
        } else {
            valueOf = null;
        }
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        String beginDate = DateTimeUtils.formatWithStyle(new Date(valueOf.longValue()), DateTimeStyle.MEDIUM);
        careplan = (Careplan) response.body();
        if (careplan != null) {
            valueOf = Long.valueOf(careplan.getEndTime());
        } else {
            valueOf = null;
        }
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        this.this$0.getCareplanWidgetBinding$app_release().careplanDate.setText(beginDate + " - " + DateTimeUtils.formatWithStyle(new Date(valueOf.longValue()), DateTimeStyle.MEDIUM));
        careplan = (Careplan) response.body();
        if (careplan != null) {
            valueOf = Long.valueOf(careplan.getEndTime());
        } else {
            valueOf = null;
        }
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        long longValue = valueOf.longValue();
        careplan = (Careplan) response.body();
        if (careplan != null) {
            l = Long.valueOf(careplan.getBeginTime());
        }
        if (l == null) {
            Intrinsics.throwNpe();
        }
        this.this$0.getCareplanWidgetBinding$app_release().careplanDuration.setText("Duration: " + TimeUnit.DAYS.convert(longValue - l.longValue(), TimeUnit.MILLISECONDS) + " DAYS");
    }

    public void onFailure(@Nullable Call<Careplan> call, @Nullable Throwable t) {
    }
}
