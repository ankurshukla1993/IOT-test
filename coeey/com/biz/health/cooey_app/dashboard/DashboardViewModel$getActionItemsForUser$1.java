package com.biz.health.cooey_app.dashboard;

import com.biz.health.cooey_app.PatientApplication;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.common.CommonDatabase;
import com.cooey.common.dao.ActionItemDao;
import com.cooey.common.vo.ActionItem;
import com.cooey.common.vo.timeline.TimelineItem;
import com.cooey.common.vo.timeline.TimelineItemType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0004J(\u0010\u0005\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J4\u0010\u000b\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\b2\u0014\u0010\f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\rH\u0016¨\u0006\u000e"}, d2 = {"com/biz/health/cooey_app/dashboard/DashboardViewModel$getActionItemsForUser$1", "Lretrofit2/Callback;", "", "Lcom/cooey/common/vo/ActionItem;", "(Lcom/biz/health/cooey_app/dashboard/DashboardViewModel;)V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: DashboardViewModel.kt */
public final class DashboardViewModel$getActionItemsForUser$1 implements Callback<List<ActionItem>> {
    final /* synthetic */ DashboardViewModel this$0;

    DashboardViewModel$getActionItemsForUser$1(DashboardViewModel $outer) {
        this.this$0 = $outer;
    }

    public void onFailure(@Nullable Call<List<ActionItem>> call, @Nullable Throwable t) {
    }

    public void onResponse(@Nullable Call<List<ActionItem>> call, @Nullable Response<List<ActionItem>> response) {
        List timelineItems = new ArrayList();
        if (response != null) {
            List<ActionItem> list = (List) response.body();
            if (list != null) {
                for (ActionItem actionItem : list) {
                    String id = actionItem.getId();
                    Intrinsics.checkExpressionValueIsNotNull(id, "actionItem.id");
                    int action = TimelineItemType.INSTANCE.getACTION();
                    long scheduledOn = actionItem.getScheduledOn();
                    String patientId = actionItem.getPatientId();
                    Intrinsics.checkExpressionValueIsNotNull(patientId, "actionItem.patientId");
                    timelineItems.add(new TimelineItem(id, action, scheduledOn, patientId, null, 16, null));
                }
            }
            VitalRepository vitalRepository = this.this$0.getVitalRepository();
            (vitalRepository != null ? vitalRepository.getTimelineItemDao() : null).insert(timelineItems);
            CommonDatabase commonDatabase = PatientApplication.Companion.getCommonDatabase();
            if (commonDatabase != null) {
                ActionItemDao ActionItemDao = commonDatabase.ActionItemDao();
                if (ActionItemDao != null) {
                    ActionItem[] actionItemArr;
                    List list2 = (List) response.body();
                    if (list2 != null) {
                        Collection thisCollection$iv = list2;
                        if (thisCollection$iv == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
                        }
                        Object[] toArray = thisCollection$iv.toArray(new ActionItem[thisCollection$iv.size()]);
                        if (toArray == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                        actionItemArr = (ActionItem[]) toArray;
                    } else {
                        actionItemArr = null;
                    }
                    if (actionItemArr == null) {
                        Intrinsics.throwNpe();
                    }
                    ActionItemDao.insert(actionItemArr);
                }
            }
        }
    }
}
