package com.biz.health.cooey_app.dashboard.widgets.holders;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.graphics.Color;
import com.biz.health.cooey_app.MainActivity;
import com.biz.health.cooey_app.PatientApplication;
import com.biz.health.cooey_app.databinding.CareplanWidgetBinding;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.CommonDatabase;
import com.cooey.common.dao.ActionItemDao;
import com.cooey.common.services.ApiClient;
import com.cooey.common.services.CareplanService;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.CareplanSummary;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.User;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0002J\b\u0010\u001d\u001a\u00020\u001bH\u0002J\u0018\u0010\u001e\u001a\u00020\u001b2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0002R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/biz/health/cooey_app/dashboard/widgets/holders/CareplanWidgetViewHolder;", "Lcom/biz/health/cooey_app/dashboard/widgets/holders/WidgetViewHolder;", "context", "Landroid/content/Context;", "careplanWidgetBinding", "Lcom/biz/health/cooey_app/databinding/CareplanWidgetBinding;", "sessionId", "", "(Landroid/content/Context;Lcom/biz/health/cooey_app/databinding/CareplanWidgetBinding;Ljava/lang/String;)V", "actionsSummary", "", "Lcom/cooey/common/vo/CareplanSummary;", "apiClient", "Lcom/cooey/common/services/ApiClient;", "getCareplanWidgetBinding$app_release", "()Lcom/biz/health/cooey_app/databinding/CareplanWidgetBinding;", "setCareplanWidgetBinding$app_release", "(Lcom/biz/health/cooey_app/databinding/CareplanWidgetBinding;)V", "session", "Lcom/cooey/common/vo/Session;", "getSessionId", "()Ljava/lang/String;", "setSessionId", "(Ljava/lang/String;)V", "user", "Lcom/cooey/common/vo/User;", "bind", "", "getActionSummaryforUser", "getCareplanDetials", "updateActionSummary", "actionSummaries", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: CareplanWidgetViewHolder.kt */
public final class CareplanWidgetViewHolder extends WidgetViewHolder {
    private List<? extends CareplanSummary> actionsSummary;
    private ApiClient apiClient;
    @NotNull
    private CareplanWidgetBinding careplanWidgetBinding;
    private final Context context;
    private Session session;
    @NotNull
    private String sessionId;
    private User user;

    public CareplanWidgetViewHolder(@NotNull Context context, @NotNull CareplanWidgetBinding careplanWidgetBinding, @NotNull String sessionId) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(careplanWidgetBinding, "careplanWidgetBinding");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        super(careplanWidgetBinding);
        this.context = context;
        this.careplanWidgetBinding = careplanWidgetBinding;
        this.sessionId = sessionId;
    }

    @NotNull
    public final CareplanWidgetBinding getCareplanWidgetBinding$app_release() {
        return this.careplanWidgetBinding;
    }

    @NotNull
    public final String getSessionId() {
        return this.sessionId;
    }

    public final void setCareplanWidgetBinding$app_release(@NotNull CareplanWidgetBinding <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.careplanWidgetBinding = <set-?>;
    }

    public final void setSessionId(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.sessionId = <set-?>;
    }

    public void bind(@NotNull User user) {
        LiveData liveActionItems;
        Context context;
        Intrinsics.checkParameterIsNotNull(user, "user");
        this.apiClient = new ApiClient(this.context, "http://api.cooey.co.in/ehealth/");
        this.session = new PreferenceStore().getSession(this.context);
        this.user = new PreferenceStore().getUser(this.context);
        CommonDatabase commonDatabase = PatientApplication.Companion.getCommonDatabase();
        if (commonDatabase != null) {
            ActionItemDao ActionItemDao = commonDatabase.ActionItemDao();
            if (ActionItemDao != null) {
                String id = user.getId();
                Intrinsics.checkExpressionValueIsNotNull(id, "user.id");
                liveActionItems = ActionItemDao.getActionItemsForUser(id);
                if (liveActionItems != null) {
                    context = this.context;
                    if (context != null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.biz.health.cooey_app.MainActivity");
                    }
                    liveActionItems.observe((MainActivity) context, new CareplanWidgetViewHolder$bind$1(this));
                }
                getCareplanDetials();
                this.careplanWidgetBinding.carePlanCard.setOnClickListener(new CareplanWidgetViewHolder$bind$2(this));
            }
        }
        liveActionItems = null;
        if (liveActionItems != null) {
            context = this.context;
            if (context != null) {
                liveActionItems.observe((MainActivity) context, new CareplanWidgetViewHolder$bind$1(this));
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.biz.health.cooey_app.MainActivity");
            }
        }
        getCareplanDetials();
        this.careplanWidgetBinding.carePlanCard.setOnClickListener(new CareplanWidgetViewHolder$bind$2(this));
    }

    private final void getCareplanDetials() {
        String id;
        String token = this.sessionId;
        CareplanService careplanService = new ApiClient(this.context, "http://api.cooey.co.in/ehealth/").getCareplanService();
        User user = this.user;
        if (user != null) {
            id = user.getId();
        } else {
            id = null;
        }
        careplanService.getCareplan(token, id).enqueue(new CareplanWidgetViewHolder$getCareplanDetials$1(this));
    }

    private final void getActionSummaryforUser() {
        try {
            ApiClient apiClient = new ApiClient(this.context, "http://api.cooey.co.in/ehealth/");
            Session session = new PreferenceStore().getSession(this.context);
            this.careplanWidgetBinding.careplanProgressBar.setVisibility(0);
            CareplanService careplanService = apiClient.getCareplanService();
            String id = session.getId();
            User user = this.user;
            if (user == null) {
                Intrinsics.throwNpe();
            }
            careplanService.getCareplanSummary(id, user.getId()).enqueue(new CareplanWidgetViewHolder$getActionSummaryforUser$1(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void updateActionSummary(List<? extends CareplanSummary> actionSummaries) {
        this.careplanWidgetBinding.upperConstraintLayout.setVisibility(0);
        this.careplanWidgetBinding.lowerContainerLayout.setVisibility(0);
        if (actionSummaries != null) {
            for (CareplanSummary actionSummary : actionSummaries) {
                if (actionSummary.getRateIndicator() < ((double) null)) {
                    actionSummary.setRateIndicator(0.0d);
                }
                String itemType = actionSummary.getItemType();
                if (itemType != null) {
                    switch (itemType.hashCode()) {
                        case -873340145:
                            if (!itemType.equals(CTConstants.ACTIVITY_TYPE)) {
                                break;
                            }
                            this.careplanWidgetBinding.activityProgress.setProgress((int) actionSummary.getRateIndicator());
                            this.careplanWidgetBinding.activityProgress.setFinishedStrokeColor(Color.rgb(66, 145, 241));
                            break;
                        case 2098164:
                            if (!itemType.equals(CTConstants.DIET_TYPE)) {
                                break;
                            }
                            this.careplanWidgetBinding.dietProgress.setProgress((int) actionSummary.getRateIndicator());
                            this.careplanWidgetBinding.dietProgress.setFinishedStrokeColor(Color.rgb(66, 145, 241));
                            break;
                        case 2580550:
                            if (!itemType.equals(CTConstants.TODO_TYPE)) {
                                break;
                            }
                            this.careplanWidgetBinding.todoProgress.setProgress((int) actionSummary.getRateIndicator());
                            this.careplanWidgetBinding.todoProgress.setFinishedStrokeColor(Color.rgb(66, 145, 241));
                            break;
                        case 81680364:
                            if (!itemType.equals(CTConstants.VITAL_TYPE)) {
                                break;
                            }
                            this.careplanWidgetBinding.vitalProgress.setProgress((int) actionSummary.getRateIndicator());
                            this.careplanWidgetBinding.vitalProgress.setFinishedStrokeColor(Color.rgb(66, 145, 241));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
}
