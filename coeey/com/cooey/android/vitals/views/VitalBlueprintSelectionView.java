package com.cooey.android.vitals.views;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.cooey.android.vitals.R;
import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.android.vitals.VitalInputActivity;
import com.cooey.android.vitals.VitalListActivity;
import com.cooey.android.vitals.adapters.VitalBlueprintSelectionRecyclerAdapter;
import com.cooey.android.vitals.repositories.VitalBlueprintsRepository;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.common.vo.Session;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u0010\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0002J\u0006\u00108\u001a\u000205J\u0006\u00109\u001a\u000205J\b\u0010:\u001a\u000205H\u0002J\u0006\u0010;\u001a\u000205J\b\u0010<\u001a\u000205H\u0002J\u0010\u0010=\u001a\u0002052\u0006\u00106\u001a\u000207H\u0002J\u0016\u0010>\u001a\u0002052\u0006\u0010?\u001a\u00020\u001d2\u0006\u0010@\u001a\u00020\u001dR\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010#X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001c\u0010(\u001a\u0004\u0018\u00010)X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001c\u0010.\u001a\u0004\u0018\u00010/X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103¨\u0006A"}, d2 = {"Lcom/cooey/android/vitals/views/VitalBlueprintSelectionView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "(Landroid/content/Context;)V", "fragmentManager", "Landroid/support/v4/app/FragmentManager;", "getFragmentManager", "()Landroid/support/v4/app/FragmentManager;", "setFragmentManager", "(Landroid/support/v4/app/FragmentManager;)V", "recyclerView", "Landroid/support/v7/widget/RecyclerView;", "session", "Lcom/cooey/common/vo/Session;", "getSession", "()Lcom/cooey/common/vo/Session;", "setSession", "(Lcom/cooey/common/vo/Session;)V", "showPrimary", "", "getShowPrimary", "()Z", "setShowPrimary", "(Z)V", "userId", "", "getUserId", "()Ljava/lang/String;", "setUserId", "(Ljava/lang/String;)V", "vitalBlueprintSelectionRecyclerAdapter", "Lcom/cooey/android/vitals/adapters/VitalBlueprintSelectionRecyclerAdapter;", "getVitalBlueprintSelectionRecyclerAdapter", "()Lcom/cooey/android/vitals/adapters/VitalBlueprintSelectionRecyclerAdapter;", "setVitalBlueprintSelectionRecyclerAdapter", "(Lcom/cooey/android/vitals/adapters/VitalBlueprintSelectionRecyclerAdapter;)V", "vitalBlueprintsRepository", "Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "getVitalBlueprintsRepository", "()Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "setVitalBlueprintsRepository", "(Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;)V", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "generateActionItems", "", "vitalBlueprint", "Lcom/cooey/android/vitals/VitalBlueprint;", "initialize", "initializeView", "initializeViewForActivity", "initializeViewForFragment", "setUpView", "showVitalInputDialog", "sync", "token", "tenantId", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalBlueprintSelectionView.kt */
public final class VitalBlueprintSelectionView extends FrameLayout {
    private HashMap _$_findViewCache;
    @Nullable
    private FragmentManager fragmentManager;
    private RecyclerView recyclerView;
    @Nullable
    private Session session;
    private boolean showPrimary = true;
    @Nullable
    private String userId;
    @Nullable
    private VitalBlueprintSelectionRecyclerAdapter vitalBlueprintSelectionRecyclerAdapter;
    @Nullable
    private VitalBlueprintsRepository vitalBlueprintsRepository;
    @Nullable
    private VitalRepository vitalRepository;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    @Nullable
    public final VitalBlueprintsRepository getVitalBlueprintsRepository() {
        return this.vitalBlueprintsRepository;
    }

    public final void setVitalBlueprintsRepository(@Nullable VitalBlueprintsRepository <set-?>) {
        this.vitalBlueprintsRepository = <set-?>;
    }

    @Nullable
    public final VitalRepository getVitalRepository() {
        return this.vitalRepository;
    }

    public final void setVitalRepository(@Nullable VitalRepository <set-?>) {
        this.vitalRepository = <set-?>;
    }

    @Nullable
    public final VitalBlueprintSelectionRecyclerAdapter getVitalBlueprintSelectionRecyclerAdapter() {
        return this.vitalBlueprintSelectionRecyclerAdapter;
    }

    public final void setVitalBlueprintSelectionRecyclerAdapter(@Nullable VitalBlueprintSelectionRecyclerAdapter <set-?>) {
        this.vitalBlueprintSelectionRecyclerAdapter = <set-?>;
    }

    @Nullable
    public final FragmentManager getFragmentManager() {
        return this.fragmentManager;
    }

    public final void setFragmentManager(@Nullable FragmentManager <set-?>) {
        this.fragmentManager = <set-?>;
    }

    @Nullable
    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(@Nullable String <set-?>) {
        this.userId = <set-?>;
    }

    @Nullable
    public final Session getSession() {
        return this.session;
    }

    public final void setSession(@Nullable Session <set-?>) {
        this.session = <set-?>;
    }

    public final boolean getShowPrimary() {
        return this.showPrimary;
    }

    public final void setShowPrimary(boolean <set-?>) {
        this.showPrimary = <set-?>;
    }

    public VitalBlueprintSelectionView(@Nullable Context context, @NotNull AttributeSet attrs) {
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        super(context, attrs);
        setUpView();
    }

    public VitalBlueprintSelectionView(@Nullable Context context) {
        super(context);
        setUpView();
    }

    private final void setUpView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_recycler_view, this, false);
        if (inflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.widget.RecyclerView");
        }
        this.recyclerView = (RecyclerView) inflate;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        addView(this.recyclerView);
    }

    public final void initialize() {
        if (getContext() instanceof AppCompatActivity) {
            initializeViewForActivity();
        } else {
            initializeViewForFragment();
        }
    }

    public final void sync(@NotNull String token, @NotNull String tenantId) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        Intrinsics.checkParameterIsNotNull(tenantId, "tenantId");
        VitalBlueprintsRepository vitalBlueprintsRepository = this.vitalBlueprintsRepository;
        if (vitalBlueprintsRepository != null) {
            vitalBlueprintsRepository.syncVitalBlueprints(token, tenantId);
        }
    }

    public final void initializeViewForFragment() {
        Context context = getContext();
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v4.app.Fragment");
        }
        this.fragmentManager = ((Fragment) context).getFragmentManager();
        initializeView();
    }

    private final void initializeViewForActivity() {
        Context context = getContext();
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.app.AppCompatActivity");
        }
        this.fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        initializeView();
    }

    public final void initializeView() {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        this.vitalBlueprintSelectionRecyclerAdapter = new VitalBlueprintSelectionRecyclerAdapter(context, this.userId, this.session, this.fragmentManager, this.vitalRepository);
        LifecycleOwner lifeCycleOwner = getContext();
        if (lifeCycleOwner == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.arch.lifecycle.LifecycleOwner");
        }
        lifeCycleOwner = lifeCycleOwner;
        VitalBlueprintsRepository vitalBlueprintsRepository = this.vitalBlueprintsRepository;
        if (vitalBlueprintsRepository != null) {
            LiveData vitalBlueprintsFromDatabase = vitalBlueprintsRepository.getVitalBlueprintsFromDatabase(this.showPrimary);
            if (vitalBlueprintsFromDatabase != null) {
                vitalBlueprintsFromDatabase.observe(lifeCycleOwner, new VitalBlueprintSelectionView$initializeView$1(this));
            }
        }
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(this.vitalBlueprintSelectionRecyclerAdapter);
        }
    }

    private final void generateActionItems(VitalBlueprint vitalBlueprint) {
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setPadding(20, 20, 20, 20);
        textView.setOnClickListener(new VitalBlueprintSelectionView$generateActionItems$1(this, vitalBlueprint));
        addView(textView, new LayoutParams(-2, -2));
    }

    private final void showVitalInputDialog(VitalBlueprint vitalBlueprint) {
        VitalInputActivity.Companion.setVitalBlueprint(VitalListActivity.Companion.getVitalBlueprint());
        VitalInputActivity.Companion.setVitalRepository(VitalListActivity.Companion.getVitalRepository());
        VitalInputActivity.Companion.setUserId(VitalListActivity.Companion.getUserId());
        VitalInputActivity.Companion.setSession(VitalListActivity.Companion.getSession());
        getContext().startActivity(new Intent(getContext(), VitalInputActivity.class));
    }
}
