package com.cooey.android.vitals.views;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.cooey.android.vitals.R;
import com.cooey.android.vitals.adapters.VitalWidgetRecyclerAdapter;
import com.cooey.android.vitals.repositories.VitalBlueprintsRepository;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.common.CommonDatabase;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u0006\u00102\u001a\u000203J\b\u00104\u001a\u000203H\u0002R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010!X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010&\u001a\u0004\u0018\u00010'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001c\u0010,\u001a\u0004\u0018\u00010-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101¨\u00065"}, d2 = {"Lcom/cooey/android/vitals/views/VitalWidgetView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "(Landroid/content/Context;)V", "commonDatabase", "Lcom/cooey/common/CommonDatabase;", "getCommonDatabase", "()Lcom/cooey/common/CommonDatabase;", "setCommonDatabase", "(Lcom/cooey/common/CommonDatabase;)V", "lifeCycleOwner", "Landroid/arch/lifecycle/LifecycleOwner;", "getLifeCycleOwner", "()Landroid/arch/lifecycle/LifecycleOwner;", "setLifeCycleOwner", "(Landroid/arch/lifecycle/LifecycleOwner;)V", "recyclerView", "Landroid/support/v7/widget/RecyclerView;", "getRecyclerView", "()Landroid/support/v7/widget/RecyclerView;", "setRecyclerView", "(Landroid/support/v7/widget/RecyclerView;)V", "userId", "", "getUserId", "()Ljava/lang/String;", "setUserId", "(Ljava/lang/String;)V", "vitalBlueprintsRepository", "Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "getVitalBlueprintsRepository", "()Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "setVitalBlueprintsRepository", "(Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;)V", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "vitalWidgetRecyclerAdapter", "Lcom/cooey/android/vitals/adapters/VitalWidgetRecyclerAdapter;", "getVitalWidgetRecyclerAdapter", "()Lcom/cooey/android/vitals/adapters/VitalWidgetRecyclerAdapter;", "setVitalWidgetRecyclerAdapter", "(Lcom/cooey/android/vitals/adapters/VitalWidgetRecyclerAdapter;)V", "initializeView", "", "setUpView", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalWidgetView.kt */
public final class VitalWidgetView extends FrameLayout {
    private HashMap _$_findViewCache;
    @Nullable
    private CommonDatabase commonDatabase;
    @Nullable
    private LifecycleOwner lifeCycleOwner;
    @Nullable
    private RecyclerView recyclerView;
    @Nullable
    private String userId;
    @Nullable
    private VitalBlueprintsRepository vitalBlueprintsRepository;
    @Nullable
    private VitalRepository vitalRepository;
    @Nullable
    private VitalWidgetRecyclerAdapter vitalWidgetRecyclerAdapter;

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
    public final RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    public final void setRecyclerView(@Nullable RecyclerView <set-?>) {
        this.recyclerView = <set-?>;
    }

    @Nullable
    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(@Nullable String <set-?>) {
        this.userId = <set-?>;
    }

    @Nullable
    public final VitalWidgetRecyclerAdapter getVitalWidgetRecyclerAdapter() {
        return this.vitalWidgetRecyclerAdapter;
    }

    public final void setVitalWidgetRecyclerAdapter(@Nullable VitalWidgetRecyclerAdapter <set-?>) {
        this.vitalWidgetRecyclerAdapter = <set-?>;
    }

    @Nullable
    public final LifecycleOwner getLifeCycleOwner() {
        return this.lifeCycleOwner;
    }

    public final void setLifeCycleOwner(@Nullable LifecycleOwner <set-?>) {
        this.lifeCycleOwner = <set-?>;
    }

    @Nullable
    public final CommonDatabase getCommonDatabase() {
        return this.commonDatabase;
    }

    public final void setCommonDatabase(@Nullable CommonDatabase <set-?>) {
        this.commonDatabase = <set-?>;
    }

    public VitalWidgetView(@Nullable Context context, @NotNull AttributeSet attrs) {
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        super(context, attrs);
        setUpView();
    }

    public VitalWidgetView(@Nullable Context context) {
        super(context);
        setUpView();
    }

    private final void setUpView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_recycler_view, this, false);
        if (inflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.widget.RecyclerView");
        }
        this.recyclerView = (RecyclerView) inflate;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 0, false);
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        addView(this.recyclerView);
    }

    public final void initializeView() {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        String str = this.userId;
        VitalRepository vitalRepository = this.vitalRepository;
        VitalBlueprintsRepository vitalBlueprintsRepository = this.vitalBlueprintsRepository;
        LifecycleOwner lifecycleOwner = this.lifeCycleOwner;
        if (lifecycleOwner == null) {
            Intrinsics.throwNpe();
        }
        CommonDatabase commonDatabase = this.commonDatabase;
        if (commonDatabase == null) {
            Intrinsics.throwNpe();
        }
        this.vitalWidgetRecyclerAdapter = new VitalWidgetRecyclerAdapter(context, str, vitalRepository, vitalBlueprintsRepository, lifecycleOwner, commonDatabase);
        LifecycleOwner lifeCycleOwner = getContext();
        if (lifeCycleOwner == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.arch.lifecycle.LifecycleOwner");
        }
        lifeCycleOwner = lifeCycleOwner;
        VitalBlueprintsRepository vitalBlueprintsRepository2 = this.vitalBlueprintsRepository;
        if (vitalBlueprintsRepository2 != null) {
            LiveData vitalBlueprintsFromDatabase = vitalBlueprintsRepository2.getVitalBlueprintsFromDatabase(true);
            if (vitalBlueprintsFromDatabase != null) {
                vitalBlueprintsFromDatabase.observe(lifeCycleOwner, new VitalWidgetView$initializeView$1(this));
            }
        }
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(this.vitalWidgetRecyclerAdapter);
        }
    }
}
