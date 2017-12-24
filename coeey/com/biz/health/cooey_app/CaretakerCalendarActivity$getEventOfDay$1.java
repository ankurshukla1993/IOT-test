package com.biz.health.cooey_app;

import com.biz.health.cooey_app.calendar_events.BlockedDatesAdapter;
import com.cooey.common.vo.FreeSlot;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0004J(\u0010\u0005\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J4\u0010\u000b\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\b2\u0014\u0010\f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\rH\u0016¨\u0006\u000e"}, d2 = {"com/biz/health/cooey_app/CaretakerCalendarActivity$getEventOfDay$1", "Lretrofit2/Callback;", "", "Lcom/cooey/common/vo/FreeSlot;", "(Lcom/biz/health/cooey_app/CaretakerCalendarActivity;)V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: CaretakerCalendarActivity.kt */
public final class CaretakerCalendarActivity$getEventOfDay$1 implements Callback<List<FreeSlot>> {
    final /* synthetic */ CaretakerCalendarActivity this$0;

    CaretakerCalendarActivity$getEventOfDay$1(CaretakerCalendarActivity $outer) {
        this.this$0 = $outer;
    }

    public void onFailure(@Nullable Call<List<FreeSlot>> call, @Nullable Throwable t) {
        this.this$0.getPbFreeSlots().setVisibility(8);
        this.this$0.getTxtError().setText("Sorry could not fetch the schedules");
        this.this$0.getTxtError().setVisibility(0);
    }

    public void onResponse(@Nullable Call<List<FreeSlot>> call, @Nullable Response<List<FreeSlot>> response) {
        List list;
        if (response != null) {
            list = (List) response.body();
        } else {
            list = null;
        }
        if (list != null) {
            list = response != null ? (List) response.body() : null;
            if (list == null) {
                Intrinsics.throwNpe();
            }
            if (list.size() > 0) {
                this.this$0.hideOrVisible(true);
                Object body = response.body();
                if (body == null) {
                    Intrinsics.throwNpe();
                }
                for (FreeSlot freeSlot : (List) body) {
                    this.this$0.getFreeSlotList().add(freeSlot);
                }
                this.this$0.setBlockedDateAdapter(new BlockedDatesAdapter(this.this$0, this.this$0.getFreeSlotList()));
                this.this$0.getBlockedDateRecylerView().setAdapter(this.this$0.getBlockedDateAdapter());
                this.this$0.getPbFreeSlots().setVisibility(8);
            }
        }
        this.this$0.getTxtError().setText("No schedules for the day");
        this.this$0.hideOrVisible(false);
        this.this$0.getPbFreeSlots().setVisibility(8);
    }
}
