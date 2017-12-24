package com.biz.health.cooey_app.dashboard.widgets.holders;

import android.support.v4.app.NotificationCompat;
import com.cooey.common.vo.CareplanSummary;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J0\u0010\u000b\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\b2\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"com/biz/health/cooey_app/dashboard/widgets/holders/CareplanWidgetViewHolder$getActionSummaryforUser$1", "Lretrofit2/Callback;", "", "Lcom/cooey/common/vo/CareplanSummary;", "(Lcom/biz/health/cooey_app/dashboard/widgets/holders/CareplanWidgetViewHolder;)V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: CareplanWidgetViewHolder.kt */
public final class CareplanWidgetViewHolder$getActionSummaryforUser$1 implements Callback<List<? extends CareplanSummary>> {
    final /* synthetic */ CareplanWidgetViewHolder this$0;

    CareplanWidgetViewHolder$getActionSummaryforUser$1(CareplanWidgetViewHolder $outer) {
        this.this$0 = $outer;
    }

    public void onResponse(@NotNull Call<List<CareplanSummary>> call, @NotNull Response<List<CareplanSummary>> response) {
        Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(response, "response");
        this.this$0.getCareplanWidgetBinding$app_release().careplanProgressBar.setVisibility(8);
        if (response.body() != null) {
            this.this$0.updateActionSummary((List) response.body());
        }
    }

    public void onFailure(@NotNull Call<List<CareplanSummary>> call, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(t, "t");
        this.this$0.getCareplanWidgetBinding$app_release().careplanProgressBar.setVisibility(8);
    }
}
