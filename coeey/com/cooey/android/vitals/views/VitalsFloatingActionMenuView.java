package com.cooey.android.vitals.views;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.cooey.android.vitals.R;
import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.android.vitals.repositories.VitalBlueprintsRepository;
import com.cooey.common.vo.Session;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u0010\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0002J\u0006\u00101\u001a\u00020.J\b\u00102\u001a\u00020.H\u0002J\b\u00103\u001a\u00020.H\u0002J\u0006\u00104\u001a\u00020.J\b\u00105\u001a\u00020.H\u0002J\u0006\u00106\u001a\u00020.J\u0010\u00107\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0002J\u0016\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020\"2\u0006\u0010:\u001a\u00020\"R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0011\"\u0004\b\u001a\u0010\u0013R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010(X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u0006;"}, d2 = {"Lcom/cooey/android/vitals/views/VitalsFloatingActionMenuView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "(Landroid/content/Context;)V", "fragmentManager", "Landroid/support/v4/app/FragmentManager;", "getFragmentManager", "()Landroid/support/v4/app/FragmentManager;", "setFragmentManager", "(Landroid/support/v4/app/FragmentManager;)V", "menuContainer", "Landroid/widget/LinearLayout;", "getMenuContainer", "()Landroid/widget/LinearLayout;", "setMenuContainer", "(Landroid/widget/LinearLayout;)V", "params", "Landroid/widget/FrameLayout$LayoutParams;", "getParams", "()Landroid/widget/FrameLayout$LayoutParams;", "rootContainer", "getRootContainer", "setRootContainer", "sesion", "Lcom/cooey/common/vo/Session;", "getSesion", "()Lcom/cooey/common/vo/Session;", "setSesion", "(Lcom/cooey/common/vo/Session;)V", "userId", "", "getUserId", "()Ljava/lang/String;", "setUserId", "(Ljava/lang/String;)V", "vitalBlueprintsRepository", "Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "getVitalBlueprintsRepository", "()Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "setVitalBlueprintsRepository", "(Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;)V", "generateMenuItems", "", "vitalBlueprint", "Lcom/cooey/android/vitals/VitalBlueprint;", "initialize", "initializeFabButton", "initializeFabMenu", "initializeView", "initializeViewForActivity", "initializeViewForFragment", "showVitalInputDialog", "sync", "token", "tenantId", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalsFloatingActionMenuView.kt */
public final class VitalsFloatingActionMenuView extends FrameLayout {
    private HashMap _$_findViewCache;
    @Nullable
    private FragmentManager fragmentManager;
    @Nullable
    private LinearLayout menuContainer;
    @NotNull
    private final LayoutParams params = new LayoutParams(-2, -2);
    @Nullable
    private LinearLayout rootContainer;
    @Nullable
    private Session sesion;
    @Nullable
    private String userId;
    @Nullable
    private VitalBlueprintsRepository vitalBlueprintsRepository;

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
    public final LinearLayout getRootContainer() {
        return this.rootContainer;
    }

    public final void setRootContainer(@Nullable LinearLayout <set-?>) {
        this.rootContainer = <set-?>;
    }

    @Nullable
    public final LinearLayout getMenuContainer() {
        return this.menuContainer;
    }

    public final void setMenuContainer(@Nullable LinearLayout <set-?>) {
        this.menuContainer = <set-?>;
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
    public final Session getSesion() {
        return this.sesion;
    }

    public final void setSesion(@Nullable Session <set-?>) {
        this.sesion = <set-?>;
    }

    @NotNull
    public final LayoutParams getParams() {
        return this.params;
    }

    public VitalsFloatingActionMenuView(@Nullable Context context, @NotNull AttributeSet attrs) {
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        super(context, attrs);
        initialize();
    }

    public VitalsFloatingActionMenuView(@Nullable Context context) {
        super(context);
        initialize();
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
        this.params.setMargins(30, 30, 30, 30);
        this.rootContainer = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_floating_action_menu, this, false);
        initializeFabMenu();
        initializeFabButton();
        addView(this.rootContainer);
    }

    private final void initializeFabMenu() {
        LinearLayout linearLayout = this.rootContainer;
        this.menuContainer = linearLayout != null ? (LinearLayout) linearLayout.findViewById(R.id.menu_container) : null;
        LifecycleOwner lifeCycleOwner = getContext();
        if (lifeCycleOwner == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.arch.lifecycle.LifecycleOwner");
        }
        lifeCycleOwner = lifeCycleOwner;
        VitalBlueprintsRepository vitalBlueprintsRepository = this.vitalBlueprintsRepository;
        if (vitalBlueprintsRepository != null) {
            LiveData vitalBlueprintsFromDatabase = vitalBlueprintsRepository.getVitalBlueprintsFromDatabase();
            if (vitalBlueprintsFromDatabase != null) {
                vitalBlueprintsFromDatabase.observe(lifeCycleOwner, new VitalsFloatingActionMenuView$initializeFabMenu$1(this));
            }
        }
    }

    private final void generateMenuItems(VitalBlueprint vitalBlueprint) {
        FloatingActionButton floatingActionButton = new FloatingActionButton(getContext());
        floatingActionButton.setLayoutParams(this.params);
        floatingActionButton.setOnClickListener(new VitalsFloatingActionMenuView$generateMenuItems$1(this, vitalBlueprint));
        LinearLayout linearLayout = this.menuContainer;
        if (linearLayout != null) {
            linearLayout.addView(floatingActionButton);
        }
    }

    private final void showVitalInputDialog(VitalBlueprint vitalBlueprint) {
        FragmentTransaction transaction;
        VitalInputDialogFragment vitalInputFragment = VitalInputDialogFragment.Companion.newIntance();
        vitalInputFragment.setVitalBlueprint(vitalBlueprint);
        vitalInputFragment.setUserId(this.userId);
        vitalInputFragment.setSession(this.sesion);
        FragmentManager fragmentManager = this.fragmentManager;
        if (fragmentManager != null) {
            transaction = fragmentManager.beginTransaction();
        } else {
            transaction = null;
        }
        if (transaction != null) {
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
        if (transaction != null) {
            FragmentTransaction add = transaction.add(16908290, (Fragment) vitalInputFragment);
            if (add != null) {
                FragmentTransaction addToBackStack = add.addToBackStack(null);
                if (addToBackStack != null) {
                    addToBackStack.commit();
                }
            }
        }
    }

    private final void initializeFabButton() {
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);
    }
}
