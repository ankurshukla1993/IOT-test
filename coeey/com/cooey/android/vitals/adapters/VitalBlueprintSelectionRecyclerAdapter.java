package com.cooey.android.vitals.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.common.vo.Session;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00013B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\b\u0010)\u001a\u00020*H\u0016J\u001a\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u00022\u0006\u0010.\u001a\u00020*H\u0016J\u001a\u0010/\u001a\u00020\u00022\b\u00100\u001a\u0004\u0018\u0001012\u0006\u00102\u001a\u00020*H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\"\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u00064"}, d2 = {"Lcom/cooey/android/vitals/adapters/VitalBlueprintSelectionRecyclerAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/cooey/android/vitals/adapters/VitalBlueprintSelectionRecyclerAdapter$VitalBlueprintViewHolder;", "context", "Landroid/content/Context;", "userId", "", "session", "Lcom/cooey/common/vo/Session;", "fragmentManager", "Landroid/support/v4/app/FragmentManager;", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "(Landroid/content/Context;Ljava/lang/String;Lcom/cooey/common/vo/Session;Landroid/support/v4/app/FragmentManager;Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getFragmentManager", "()Landroid/support/v4/app/FragmentManager;", "setFragmentManager", "(Landroid/support/v4/app/FragmentManager;)V", "getSession", "()Lcom/cooey/common/vo/Session;", "setSession", "(Lcom/cooey/common/vo/Session;)V", "getUserId", "()Ljava/lang/String;", "setUserId", "(Ljava/lang/String;)V", "vitalBlueprints", "", "Lcom/cooey/android/vitals/VitalBlueprint;", "getVitalBlueprints", "()Ljava/util/List;", "setVitalBlueprints", "(Ljava/util/List;)V", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "VitalBlueprintViewHolder", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalBlueprintSelectionRecyclerAdapter.kt */
public final class VitalBlueprintSelectionRecyclerAdapter extends Adapter<VitalBlueprintViewHolder> {
    @NotNull
    private Context context;
    @Nullable
    private FragmentManager fragmentManager;
    @Nullable
    private Session session;
    @Nullable
    private String userId;
    @Nullable
    private List<VitalBlueprint> vitalBlueprints;
    @Nullable
    private VitalRepository vitalRepository;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J6\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0014"}, d2 = {"Lcom/cooey/android/vitals/adapters/VitalBlueprintSelectionRecyclerAdapter$VitalBlueprintViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "view", "Landroid/widget/TextView;", "(Landroid/widget/TextView;)V", "getView", "()Landroid/widget/TextView;", "setView", "bind", "", "vitalBlueprint", "Lcom/cooey/android/vitals/VitalBlueprint;", "userId", "", "session", "Lcom/cooey/common/vo/Session;", "fragmentManager", "Landroid/support/v4/app/FragmentManager;", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "vitals_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: VitalBlueprintSelectionRecyclerAdapter.kt */
    public static final class VitalBlueprintViewHolder extends ViewHolder {
        @NotNull
        private TextView view;

        public VitalBlueprintViewHolder(@NotNull TextView view) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            super(view);
            this.view = view;
        }

        @NotNull
        public final TextView getView() {
            return this.view;
        }

        public final void setView(@NotNull TextView <set-?>) {
            Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
            this.view = <set-?>;
        }

        public final void bind(@Nullable VitalBlueprint vitalBlueprint, @Nullable String userId, @NotNull Session session, @Nullable FragmentManager fragmentManager, @Nullable VitalRepository vitalRepository) {
            Intrinsics.checkParameterIsNotNull(session, SettingsJsonConstants.SESSION_KEY);
            this.view.setText(vitalBlueprint != null ? vitalBlueprint.getName() : null);
            this.view.setOnClickListener(new C0650x172864fd(vitalBlueprint, userId, session, vitalRepository, fragmentManager));
        }
    }

    public VitalBlueprintSelectionRecyclerAdapter(@NotNull Context context, @Nullable String userId, @Nullable Session session, @Nullable FragmentManager fragmentManager, @Nullable VitalRepository vitalRepository) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.userId = userId;
        this.session = session;
        this.fragmentManager = fragmentManager;
        this.vitalRepository = vitalRepository;
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @Nullable
    public final FragmentManager getFragmentManager() {
        return this.fragmentManager;
    }

    @Nullable
    public final Session getSession() {
        return this.session;
    }

    @Nullable
    public final String getUserId() {
        return this.userId;
    }

    @Nullable
    public final VitalRepository getVitalRepository() {
        return this.vitalRepository;
    }

    public final void setContext(@NotNull Context <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.context = <set-?>;
    }

    public final void setFragmentManager(@Nullable FragmentManager <set-?>) {
        this.fragmentManager = <set-?>;
    }

    public final void setSession(@Nullable Session <set-?>) {
        this.session = <set-?>;
    }

    public final void setUserId(@Nullable String <set-?>) {
        this.userId = <set-?>;
    }

    public final void setVitalRepository(@Nullable VitalRepository <set-?>) {
        this.vitalRepository = <set-?>;
    }

    @Nullable
    public final List<VitalBlueprint> getVitalBlueprints() {
        return this.vitalBlueprints;
    }

    public final void setVitalBlueprints(@Nullable List<VitalBlueprint> <set-?>) {
        this.vitalBlueprints = <set-?>;
    }

    public void onBindViewHolder(@Nullable VitalBlueprintViewHolder holder, int position) {
        List list = this.vitalBlueprints;
        VitalBlueprint vitalBlueprint = list != null ? (VitalBlueprint) list.get(position) : null;
        if (holder != null) {
            String str = this.userId;
            Session session = this.session;
            if (session == null) {
                Intrinsics.throwNpe();
            }
            holder.bind(vitalBlueprint, str, session, this.fragmentManager, this.vitalRepository);
        }
    }

    @NotNull
    public VitalBlueprintViewHolder onCreateViewHolder(@Nullable ViewGroup parent, int viewType) {
        TextView textView = new TextView(this.context);
        textView.setLayoutParams(new LayoutParams(-1, -2));
        textView.setPadding(25, 25, 25, 25);
        textView.setTextSize(2, 20.0f);
        return new VitalBlueprintViewHolder(textView);
    }

    public int getItemCount() {
        if (this.vitalBlueprints == null) {
            return 0;
        }
        List list = this.vitalBlueprints;
        Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        return valueOf.intValue();
    }
}
