package com.biz.health.cooey_app.dashboard.widgets;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.PatientApplication;
import com.biz.health.cooey_app.dashboard.widgets.holders.CareplanWidgetViewHolder;
import com.biz.health.cooey_app.dashboard.widgets.holders.SummaryViewHolder;
import com.biz.health.cooey_app.dashboard.widgets.holders.TipWidgetViewHolder;
import com.biz.health.cooey_app.dashboard.widgets.holders.VitalsDashboardViewHolder;
import com.biz.health.cooey_app.dashboard.widgets.holders.WidgetViewHolder;
import com.biz.health.cooey_app.databinding.CareplanWidgetBinding;
import com.biz.health.cooey_app.databinding.SummaryWidgetBinding;
import com.biz.health.cooey_app.databinding.TipWidgetBinding;
import com.biz.health.cooey_app.databinding.VitalsDashboardWidgetBinding;
import com.cooey.android.vitals.repositories.VitalBlueprintsRepository;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.common.vo.User;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B=\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\b\u0010)\u001a\u00020\u001bH\u0016J\u0010\u0010*\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020\u001bH\u0016J\u0018\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00022\u0006\u0010+\u001a\u00020\u001bH\u0016J\u0018\u0010/\u001a\u00020\u00022\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u001bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u00063"}, d2 = {"Lcom/biz/health/cooey_app/dashboard/widgets/WidgetRecyclerAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/biz/health/cooey_app/dashboard/widgets/holders/WidgetViewHolder;", "context", "Landroid/content/Context;", "layoutInflater", "Landroid/view/LayoutInflater;", "user", "Lcom/cooey/common/vo/User;", "sessionId", "", "lifecycleOwner", "Landroid/arch/lifecycle/LifecycleOwner;", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "vitalBlueprintRepository", "Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "(Landroid/content/Context;Landroid/view/LayoutInflater;Lcom/cooey/common/vo/User;Ljava/lang/String;Landroid/arch/lifecycle/LifecycleOwner;Lcom/cooey/android/vitals/repositories/VitalRepository;Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;)V", "getLifecycleOwner", "()Landroid/arch/lifecycle/LifecycleOwner;", "setLifecycleOwner", "(Landroid/arch/lifecycle/LifecycleOwner;)V", "getSessionId", "()Ljava/lang/String;", "setSessionId", "(Ljava/lang/String;)V", "size", "", "getSize", "()Ljava/lang/Integer;", "setSize", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getVitalBlueprintRepository", "()Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "setVitalBlueprintRepository", "(Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;)V", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "getItemCount", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: WidgetRecyclerAdapter.kt */
public final class WidgetRecyclerAdapter extends Adapter<WidgetViewHolder> {
    private final Context context;
    private final LayoutInflater layoutInflater;
    @NotNull
    private LifecycleOwner lifecycleOwner;
    @NotNull
    private String sessionId;
    @Nullable
    private Integer size;
    private final User user;
    @NotNull
    private VitalBlueprintsRepository vitalBlueprintRepository;
    @NotNull
    private VitalRepository vitalRepository;

    @NotNull
    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    @NotNull
    public final String getSessionId() {
        return this.sessionId;
    }

    public final void setLifecycleOwner(@NotNull LifecycleOwner <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.lifecycleOwner = <set-?>;
    }

    public final void setSessionId(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.sessionId = <set-?>;
    }

    public WidgetRecyclerAdapter(@NotNull Context context, @NotNull LayoutInflater layoutInflater, @NotNull User user, @NotNull String sessionId, @NotNull LifecycleOwner lifecycleOwner, @NotNull VitalRepository vitalRepository, @NotNull VitalBlueprintsRepository vitalBlueprintRepository) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(layoutInflater, "layoutInflater");
        Intrinsics.checkParameterIsNotNull(user, "user");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkParameterIsNotNull(vitalRepository, "vitalRepository");
        Intrinsics.checkParameterIsNotNull(vitalBlueprintRepository, "vitalBlueprintRepository");
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.user = user;
        this.sessionId = sessionId;
        this.lifecycleOwner = lifecycleOwner;
        this.vitalRepository = vitalRepository;
        this.vitalBlueprintRepository = vitalBlueprintRepository;
        if (Intrinsics.areEqual(this.context.getString(C0644R.string.tenant_id), (Object) "59103fd758e21e3dcc022760")) {
            this.size = Integer.valueOf(2);
        } else {
            this.size = Integer.valueOf(3);
        }
    }

    @NotNull
    public final VitalBlueprintsRepository getVitalBlueprintRepository() {
        return this.vitalBlueprintRepository;
    }

    @NotNull
    public final VitalRepository getVitalRepository() {
        return this.vitalRepository;
    }

    public final void setVitalBlueprintRepository(@NotNull VitalBlueprintsRepository <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.vitalBlueprintRepository = <set-?>;
    }

    public final void setVitalRepository(@NotNull VitalRepository <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.vitalRepository = <set-?>;
    }

    @Nullable
    public final Integer getSize() {
        return this.size;
    }

    public final void setSize(@Nullable Integer <set-?>) {
        this.size = <set-?>;
    }

    @NotNull
    public WidgetViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        switch (viewType) {
            case 0:
                VitalsDashboardWidgetBinding vitalsDashboardWidgetBinding = (VitalsDashboardWidgetBinding) DataBindingUtil.inflate(this.layoutInflater, C0644R.layout.vitals_dashboard_widget, parent, false);
                vitalsDashboardWidgetBinding.vitalWidgetView.setLifeCycleOwner(this.lifecycleOwner);
                vitalsDashboardWidgetBinding.vitalWidgetView.setVitalBlueprintsRepository(this.vitalBlueprintRepository);
                vitalsDashboardWidgetBinding.vitalWidgetView.setVitalRepository(this.vitalRepository);
                vitalsDashboardWidgetBinding.vitalWidgetView.setUserId(this.user.getId());
                vitalsDashboardWidgetBinding.vitalWidgetView.setCommonDatabase(PatientApplication.Companion.getCommonDatabase());
                vitalsDashboardWidgetBinding.vitalWidgetView.initializeView();
                Intrinsics.checkExpressionValueIsNotNull(vitalsDashboardWidgetBinding, "vitalsDashboardWidgetBinding");
                return new VitalsDashboardViewHolder(vitalsDashboardWidgetBinding);
            case 1:
                CareplanWidgetBinding careplanWidgetBinding = (CareplanWidgetBinding) DataBindingUtil.inflate(this.layoutInflater, C0644R.layout.careplan_widget, parent, false);
                Context context = this.context;
                Intrinsics.checkExpressionValueIsNotNull(careplanWidgetBinding, "careplanWidgetBinding");
                return new CareplanWidgetViewHolder(context, careplanWidgetBinding, this.sessionId);
            case 2:
                return new TipWidgetViewHolder(this.context, (TipWidgetBinding) DataBindingUtil.inflate(this.layoutInflater, C0644R.layout.tip_widget, parent, false));
            default:
                return new SummaryViewHolder((SummaryWidgetBinding) DataBindingUtil.inflate(this.layoutInflater, C0644R.layout.summary_widget, parent, false));
        }
    }

    public void onBindViewHolder(@NotNull WidgetViewHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        holder.bind(this.user);
    }

    public int getItemCount() {
        Integer num = this.size;
        if (num == null) {
            Intrinsics.throwNpe();
        }
        return num.intValue();
    }

    public int getItemViewType(int position) {
        Integer num = this.size;
        if (num != null && num.intValue() == 2 && position == 1) {
            return position + 1;
        }
        return position;
    }
}
