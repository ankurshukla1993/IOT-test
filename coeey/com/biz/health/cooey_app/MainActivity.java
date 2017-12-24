package com.biz.health.cooey_app;

import android.app.Activity;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;
import chatkit.holders.BloodPressureCardViewHolder;
import com.biz.health.cooey_app.account.CooeyLoginActivity;
import com.biz.health.cooey_app.account.LoginMainActivity;
import com.biz.health.cooey_app.dashboard.DashboardViewModel;
import com.biz.health.cooey_app.dashboard.DashboardViewPagerAdapter;
import com.biz.health.cooey_app.dashboard.widgets.WidgetRecyclerAdapter;
import com.biz.health.cooey_app.dashboard.widgets.WidgetsFragment;
import com.biz.health.cooey_app.databinding.ActivityMainBinding;
import com.biz.health.cooey_app.generators.TimelineViewHolderGenerator;
import com.biz.health.cooey_app.medicine.ViewAllMedicinesActivity;
import com.biz.health.cooey_app.note.NoteFullScreenDialog;
import com.biz.health.cooey_app.secondary_vital.SecondaryVitalsActivity;
import com.cooey.android.medical_reports.MedicalReportActivity;
import com.cooey.android.users.PeopleSelectorView;
import com.cooey.android.vitals.repositories.VitalBlueprintsRepository;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.android.vitals.views.VitalBlueprintSelectionView;
import com.cooey.common.CommonDatabase;
import com.cooey.common.adapters.TimelineRecyclerAdapter;
import com.cooey.common.dao.TimelineItemDao;
import com.cooey.common.generators.ITimelineViewHolderGenerator;
import com.cooey.common.generators.TimelineItemTypeSelectionDialogGenerator;
import com.cooey.common.holders.TimelineHeaderViewHolder;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.views.TimelineFragment;
import com.cooey.common.views.TimelineFragment.Companion;
import com.cooey.common.views.TimelineHeaderView;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.User;
import com.cooey.common.vo.Vital;
import com.cooey.common.vo.timeline.TimelineItemType;
import com.cooey.devices.CooeyDeviceManager;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.cooey.devices.vo.UserInfo;
import com.facebook.internal.NativeProtocol;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=J\u0010\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020AH\u0016J\u0006\u0010B\u001a\u000209J\b\u0010C\u001a\u000209H\u0004J\u0006\u0010D\u001a\u000209J\u0006\u0010E\u001a\u000209J\u0006\u0010F\u001a\u00020?J\u000e\u0010G\u001a\u00020?2\u0006\u0010:\u001a\u00020;J\"\u0010H\u001a\u0002092\u0006\u0010I\u001a\u00020!2\u0006\u0010J\u001a\u00020!2\b\u0010K\u001a\u0004\u0018\u00010LH\u0014J\b\u0010M\u001a\u000209H\u0016J\u0012\u0010N\u001a\u0002092\b\u0010O\u001a\u0004\u0018\u00010PH\u0014J\u0010\u0010Q\u001a\u00020?2\u0006\u0010R\u001a\u00020SH\u0016J\u0010\u0010T\u001a\u00020?2\u0006\u0010U\u001a\u00020VH\u0016J+\u0010W\u001a\u0002092\u0006\u0010I\u001a\u00020!2\f\u0010X\u001a\b\u0012\u0004\u0012\u00020=0Y2\u0006\u0010Z\u001a\u00020[H\u0016¢\u0006\u0002\u0010\\J\b\u0010]\u001a\u000209H\u0014J\u0006\u0010^\u001a\u000209J\u0006\u0010_\u001a\u000209J\b\u0010`\u001a\u000209H\u0002R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR \u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020'X.¢\u0006\u0002\n\u0000R\u001c\u0010(\u001a\u0004\u0018\u00010)X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u000e\u0010.\u001a\u00020/X.¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X.¢\u0006\u0002\n\u0000R\u001c\u00102\u001a\u0004\u0018\u000103X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107¨\u0006a"}, d2 = {"Lcom/biz/health/cooey_app/MainActivity;", "Landroid/support/v7/app/AppCompatActivity;", "Landroid/arch/lifecycle/LifecycleObserver;", "()V", "activityMainBinding", "Lcom/biz/health/cooey_app/databinding/ActivityMainBinding;", "getActivityMainBinding", "()Lcom/biz/health/cooey_app/databinding/ActivityMainBinding;", "setActivityMainBinding", "(Lcom/biz/health/cooey_app/databinding/ActivityMainBinding;)V", "adapter", "Lcom/cooey/common/adapters/TimelineRecyclerAdapter;", "alertDialog", "Landroid/support/v7/app/AlertDialog;", "getAlertDialog", "()Landroid/support/v7/app/AlertDialog;", "setAlertDialog", "(Landroid/support/v7/app/AlertDialog;)V", "bottomSheetBehavior", "Landroid/support/design/widget/BottomSheetBehavior;", "Landroid/view/View;", "getBottomSheetBehavior", "()Landroid/support/design/widget/BottomSheetBehavior;", "setBottomSheetBehavior", "(Landroid/support/design/widget/BottomSheetBehavior;)V", "dashboardViewModel", "Lcom/biz/health/cooey_app/dashboard/DashboardViewModel;", "getDashboardViewModel", "()Lcom/biz/health/cooey_app/dashboard/DashboardViewModel;", "setDashboardViewModel", "(Lcom/biz/health/cooey_app/dashboard/DashboardViewModel;)V", "filteredTypes", "", "", "getFilteredTypes", "()Ljava/util/List;", "setFilteredTypes", "(Ljava/util/List;)V", "newFragment", "Lcom/cooey/common/views/TimelineFragment;", "session", "Lcom/cooey/common/vo/Session;", "getSession", "()Lcom/cooey/common/vo/Session;", "setSession", "(Lcom/cooey/common/vo/Session;)V", "timelineHeaderViewHolder", "Lcom/cooey/common/holders/TimelineHeaderViewHolder;", "timelineViewHolderGenerator", "Lcom/biz/health/cooey_app/generators/TimelineViewHolderGenerator;", "user", "Lcom/cooey/common/vo/User;", "getUser", "()Lcom/cooey/common/vo/User;", "setUser", "(Lcom/cooey/common/vo/User;)V", "buildCloseDialog", "", "context", "Landroid/content/Context;", "message", "", "dispatchTouchEvent", "", "event", "Landroid/view/MotionEvent;", "getCheckedFeatures", "getPermissions", "initTimelineAdapterData", "initalize", "isBluetoothEnabled", "isLocationServiceEnabled", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onRequestPermissionsResult", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "showChatOptions", "showVideoCallOptions", "turnOnBluetooth", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: MainActivity.kt */
public final class MainActivity extends AppCompatActivity implements LifecycleObserver {
    private HashMap _$_findViewCache;
    @Nullable
    private ActivityMainBinding activityMainBinding;
    private TimelineRecyclerAdapter adapter;
    @Nullable
    private AlertDialog alertDialog;
    @Nullable
    private BottomSheetBehavior<View> bottomSheetBehavior;
    @Nullable
    private DashboardViewModel dashboardViewModel;
    @NotNull
    private List<Integer> filteredTypes = CollectionsKt__CollectionsKt.mutableListOf(Integer.valueOf(TimelineItemType.INSTANCE.getVITAL()), Integer.valueOf(TimelineItemType.INSTANCE.getNOTE()), Integer.valueOf(TimelineItemType.INSTANCE.getACTION()), Integer.valueOf(TimelineItemType.INSTANCE.getEVENT()));
    private TimelineFragment newFragment;
    @Nullable
    private Session session;
    private TimelineHeaderViewHolder timelineHeaderViewHolder;
    private TimelineViewHolderGenerator timelineViewHolderGenerator;
    @Nullable
    private User user;

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

    public final boolean isLocationServiceEnabled(@org.jetbrains.annotations.NotNull android.content.Context r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.biz.health.cooey_app.MainActivity.isLocationServiceEnabled(android.content.Context):boolean
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
	at jadx.core.dex.nodes.MethodNode.addJump(MethodNode.java:370)
	at jadx.core.dex.nodes.MethodNode.initJumps(MethodNode.java:360)
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:106)
	... 7 more
*/
        /*
        r0 = this;
        r3 = "context";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r3);
        r1 = 0;
        r1 = (android.location.LocationManager) r1;
        r0 = 0;
        r2 = 0;
        r3 = "location";
        r1 = r6.getSystemService(r3);
        if (r1 != 0) goto L_0x001a;
    L_0x0012:
        r3 = new kotlin.TypeCastException;
        r4 = "null cannot be cast to non-null type android.location.LocationManager";
        r3.<init>(r4);
        throw r3;
    L_0x001a:
        r1 = (android.location.LocationManager) r1;
        r3 = "gps";	 Catch:{ Exception -> 0x0034 }
        r0 = r1.isProviderEnabled(r3);	 Catch:{ Exception -> 0x0034 }
        r3 = "network";	 Catch:{ Exception -> 0x0032 }
        r2 = r1.isProviderEnabled(r3);	 Catch:{ Exception -> 0x0032 }
    L_0x002a:
        if (r0 != 0) goto L_0x002e;
    L_0x002c:
        if (r2 == 0) goto L_0x0030;
    L_0x002e:
        r3 = 1;
    L_0x002f:
        return r3;
    L_0x0030:
        r3 = 0;
        goto L_0x002f;
    L_0x0032:
        r3 = move-exception;
        goto L_0x002a;
    L_0x0034:
        r3 = move-exception;
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.biz.health.cooey_app.MainActivity.isLocationServiceEnabled(android.content.Context):boolean");
    }

    @NotNull
    public static final /* synthetic */ TimelineRecyclerAdapter access$getAdapter$p(MainActivity $this) {
        TimelineRecyclerAdapter timelineRecyclerAdapter = $this.adapter;
        if (timelineRecyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        return timelineRecyclerAdapter;
    }

    @Nullable
    public final DashboardViewModel getDashboardViewModel() {
        return this.dashboardViewModel;
    }

    public final void setDashboardViewModel(@Nullable DashboardViewModel <set-?>) {
        this.dashboardViewModel = <set-?>;
    }

    @Nullable
    public final ActivityMainBinding getActivityMainBinding() {
        return this.activityMainBinding;
    }

    public final void setActivityMainBinding(@Nullable ActivityMainBinding <set-?>) {
        this.activityMainBinding = <set-?>;
    }

    @Nullable
    public final AlertDialog getAlertDialog() {
        return this.alertDialog;
    }

    public final void setAlertDialog(@Nullable AlertDialog <set-?>) {
        this.alertDialog = <set-?>;
    }

    @Nullable
    public final User getUser() {
        return this.user;
    }

    public final void setUser(@Nullable User <set-?>) {
        this.user = <set-?>;
    }

    @Nullable
    public final Session getSession() {
        return this.session;
    }

    public final void setSession(@Nullable Session <set-?>) {
        this.session = <set-?>;
    }

    @Nullable
    public final BottomSheetBehavior<View> getBottomSheetBehavior() {
        return this.bottomSheetBehavior;
    }

    public final void setBottomSheetBehavior(@Nullable BottomSheetBehavior<View> <set-?>) {
        this.bottomSheetBehavior = <set-?>;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (VERSION.SDK_INT > 9) {
            StrictMode.setThreadPolicy(new Builder().permitAll().build());
        }
        this.user = new PreferenceStore().getUser(this);
        if (this.user == null && Intrinsics.areEqual(getString(C0644R.string.tenant_id), (Object) "59103fd758e21e3dcc022760")) {
            startActivity(new Intent(this, CooeyLoginActivity.class));
            finish();
        } else if (this.user == null) {
            startActivity(new Intent(this, LoginMainActivity.class));
            finish();
        } else {
            Stetho.initializeWithDefaults(this);
            this.activityMainBinding = (ActivityMainBinding) DataBindingUtil.setContentView(this, C0644R.layout.activity_main);
            this.session = new PreferenceStore().getSession(this);
            PatientApplication.Companion.setSession(this.session);
            initalize();
        }
    }

    @NotNull
    public final List<Integer> getFilteredTypes() {
        return this.filteredTypes;
    }

    public final void setFilteredTypes(@NotNull List<Integer> <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.filteredTypes = <set-?>;
    }

    public final void initalize() {
        Session session;
        String id;
        String tenantId;
        VitalBlueprintSelectionView vitalBlueprintSelectionView;
        User user;
        FloatingActionButton floatingActionButton;
        DashboardViewModel dashboardViewModel;
        Toolbar toolbar = null;
        VitalBlueprintsRepository vitalBlueprintRepository = PatientApplication.Companion.getVitalBlueprintRepository();
        if (vitalBlueprintRepository != null) {
            session = this.session;
            if (session != null) {
                id = session.getId();
            } else {
                id = null;
            }
            if (id == null) {
                Intrinsics.throwNpe();
            }
            session = this.session;
            tenantId = session != null ? session.getTenantId() : null;
            if (tenantId == null) {
                Intrinsics.throwNpe();
            }
            vitalBlueprintRepository.syncVitalBlueprints(id, tenantId);
        }
        ActivityMainBinding activityMainBinding = this.activityMainBinding;
        if (activityMainBinding != null) {
            vitalBlueprintSelectionView = activityMainBinding.vitalBlueprintSelector;
            if (vitalBlueprintSelectionView != null) {
                vitalBlueprintSelectionView.setVitalBlueprintsRepository(PatientApplication.Companion.getVitalBlueprintRepository());
            }
        }
        activityMainBinding = this.activityMainBinding;
        if (activityMainBinding != null) {
            vitalBlueprintSelectionView = activityMainBinding.vitalBlueprintSelector;
            if (vitalBlueprintSelectionView != null) {
                vitalBlueprintSelectionView.setVitalRepository(PatientApplication.Companion.getVitalsRepository());
            }
        }
        activityMainBinding = this.activityMainBinding;
        if (activityMainBinding != null) {
            VitalBlueprintSelectionView vitalBlueprintSelectionView2 = activityMainBinding.vitalBlueprintSelector;
            if (vitalBlueprintSelectionView2 != null) {
                user = this.user;
                if (user != null) {
                    tenantId = user.getId();
                } else {
                    tenantId = null;
                }
                if (tenantId == null) {
                    Intrinsics.throwNpe();
                }
                vitalBlueprintSelectionView2.setUserId(tenantId);
            }
        }
        activityMainBinding = this.activityMainBinding;
        if (activityMainBinding != null) {
            vitalBlueprintSelectionView = activityMainBinding.vitalBlueprintSelector;
            if (vitalBlueprintSelectionView != null) {
                vitalBlueprintSelectionView.setSession(this.session);
            }
        }
        activityMainBinding = this.activityMainBinding;
        if (activityMainBinding != null) {
            vitalBlueprintSelectionView = activityMainBinding.vitalBlueprintSelector;
            if (vitalBlueprintSelectionView != null) {
                vitalBlueprintSelectionView.initialize();
            }
        }
        activityMainBinding = this.activityMainBinding;
        if (activityMainBinding != null) {
            VitalBlueprintSelectionView vitalBlueprintSelectionView3 = activityMainBinding.vitalBlueprintSelector;
            if (vitalBlueprintSelectionView3 != null) {
                session = this.session;
                if (session != null) {
                    id = session.getId();
                } else {
                    id = null;
                }
                if (id == null) {
                    Intrinsics.throwNpe();
                }
                session = this.session;
                tenantId = session != null ? session.getTenantId() : null;
                if (tenantId == null) {
                    Intrinsics.throwNpe();
                }
                vitalBlueprintSelectionView3.sync(id, tenantId);
            }
        }
        activityMainBinding = this.activityMainBinding;
        if (activityMainBinding != null) {
            vitalBlueprintSelectionView = activityMainBinding.vitalBlueprintSelector;
        } else {
            vitalBlueprintSelectionView = null;
        }
        this.bottomSheetBehavior = BottomSheetBehavior.from(vitalBlueprintSelectionView);
        BottomSheetBehavior bottomSheetBehavior = this.bottomSheetBehavior;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setHideable(true);
        }
        bottomSheetBehavior = this.bottomSheetBehavior;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(5);
        }
        activityMainBinding = this.activityMainBinding;
        if (activityMainBinding != null) {
            floatingActionButton = activityMainBinding.floatingActionButton;
            if (floatingActionButton != null) {
                floatingActionButton.setOnClickListener(new MainActivity$initalize$1(this));
            }
        }
        Companion companion = TimelineFragment.Companion;
        user = this.user;
        if (user != null) {
            tenantId = user.getId();
        } else {
            tenantId = null;
        }
        if (tenantId == null) {
            Intrinsics.throwNpe();
        }
        this.newFragment = companion.newInstance(tenantId);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (this == null) {
            Intrinsics.throwNpe();
        }
        TimelineHeaderView timelineHeaderView = new TimelineHeaderView(this);
        FloatingActionButton fabButton = timelineHeaderView.getFabButton();
        if (fabButton != null) {
            fabButton.setImageResource(C0644R.drawable.ic_filter_list_white_24dp);
        }
        floatingActionButton = timelineHeaderView.getFabButton();
        if (floatingActionButton != null) {
            floatingActionButton.setOnClickListener(new MainActivity$initalize$2(this));
        }
        this.timelineHeaderViewHolder = new TimelineHeaderViewHolder(timelineHeaderView);
        LifecycleOwner lifecycleOwner = this;
        VitalRepository vitalsRepository = PatientApplication.Companion.getVitalsRepository();
        if (vitalsRepository == null) {
            Intrinsics.throwNpe();
        }
        this.timelineViewHolderGenerator = new TimelineViewHolderGenerator(lifecycleOwner, vitalsRepository);
        TimelineViewHolderGenerator timelineViewHolderGenerator = this.timelineViewHolderGenerator;
        if (timelineViewHolderGenerator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timelineViewHolderGenerator");
        }
        ITimelineViewHolderGenerator iTimelineViewHolderGenerator = timelineViewHolderGenerator;
        TimelineHeaderViewHolder timelineHeaderViewHolder = this.timelineHeaderViewHolder;
        if (timelineHeaderViewHolder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timelineHeaderViewHolder");
        }
        this.adapter = new TimelineRecyclerAdapter(iTimelineViewHolderGenerator, timelineHeaderViewHolder, null);
        TimelineRecyclerAdapter timelineRecyclerAdapter = this.adapter;
        if (timelineRecyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        timelineRecyclerAdapter.setHeaderEnabled(true);
        TimelineFragment timelineFragment = this.newFragment;
        if (timelineFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("newFragment");
        }
        TimelineRecyclerAdapter timelineRecyclerAdapter2 = this.adapter;
        if (timelineRecyclerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        timelineFragment.setTimelineRecyclerAdapter(timelineRecyclerAdapter2);
        initTimelineAdapterData();
        Context context = this;
        TimelineFragment timelineFragment2 = this.newFragment;
        if (timelineFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("newFragment");
        }
        VitalRepository vitalsRepository2 = PatientApplication.Companion.getVitalsRepository();
        if (vitalsRepository2 == null) {
            Intrinsics.throwNpe();
        }
        VitalBlueprintsRepository vitalBlueprintRepository2 = PatientApplication.Companion.getVitalBlueprintRepository();
        if (vitalBlueprintRepository2 == null) {
            Intrinsics.throwNpe();
        }
        Session session2 = this.session;
        if (session2 == null) {
            Intrinsics.throwNpe();
        }
        this.dashboardViewModel = new DashboardViewModel(context, timelineFragment2, vitalsRepository2, vitalBlueprintRepository2, session2, this.user);
        activityMainBinding = this.activityMainBinding;
        if (activityMainBinding != null) {
            activityMainBinding.setDashboardViewModel(this.dashboardViewModel);
        }
        activityMainBinding = this.activityMainBinding;
        if (activityMainBinding != null) {
            dashboardViewModel = activityMainBinding.getDashboardViewModel();
            if (dashboardViewModel != null) {
                dashboardViewModel.notifyChange();
            }
        }
        activityMainBinding = this.activityMainBinding;
        if (activityMainBinding != null) {
            TabLayout tabLayout = activityMainBinding.tabs;
            if (tabLayout != null) {
                tabLayout.bringToFront();
            }
        }
        activityMainBinding = this.activityMainBinding;
        if (activityMainBinding != null) {
            toolbar = activityMainBinding.toolbar;
        }
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            Intrinsics.throwNpe();
        }
        supportActionBar.setTitle((CharSequence) "");
        dashboardViewModel = this.dashboardViewModel;
        if (dashboardViewModel != null) {
            dashboardViewModel.registerNotificationChannel();
        }
    }

    public final void initTimelineAdapterData() {
        LiveData timelineItemsLiveData = null;
        CommonDatabase commonDatabase = PatientApplication.Companion.getCommonDatabase();
        if (commonDatabase != null) {
            TimelineItemDao TimelineItemDao = commonDatabase.TimelineItemDao();
            if (TimelineItemDao != null) {
                Collection thisCollection$iv = this.filteredTypes;
                if (thisCollection$iv == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
                }
                Object[] toArray = thisCollection$iv.toArray(new Integer[thisCollection$iv.size()]);
                if (toArray == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                String id;
                Integer[] numArr = (Integer[]) toArray;
                User user = this.user;
                if (user != null) {
                    id = user.getId();
                }
                if (id == null) {
                    Intrinsics.throwNpe();
                }
                timelineItemsLiveData = TimelineItemDao.getTimelineItemsByTypes(numArr, id);
            }
        }
        if (timelineItemsLiveData != null) {
            timelineItemsLiveData.observe(this, new MainActivity$initTimelineAdapterData$1(this));
        }
    }

    public boolean onCreateOptionsMenu(@NotNull Menu menu) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        getMenuInflater().inflate(C0644R.menu.menu_activity_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        TimelineItemDao timelineItemDao = null;
        Intrinsics.checkParameterIsNotNull(item, "item");
        User user;
        String id;
        switch (item.getItemId()) {
            case C0644R.id.action_settings:
                startActivity(new Intent(this, PreferencesActivity.class));
                break;
            case C0644R.id.devices:
                String id2;
                UserInfo userInfo = new UserInfo();
                user = this.user;
                if (user != null) {
                    id2 = user.getId();
                }
                userInfo.setPatientId(id2);
                if (isBluetoothEnabled()) {
                    if (!isLocationServiceEnabled(this)) {
                        buildCloseDialog(this, "Enable Location to access devices");
                        break;
                    }
                    CooeyDeviceManager.getInstance().setUserInfo(userInfo).launchDeviceManager(this);
                    break;
                }
                getPermissions();
                break;
            case C0644R.id.logout:
                CharSequence[] values = (CharSequence[]) new CharSequence[]{"Yes", "No"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle((CharSequence) "Confirm Logout ?").setPositiveButton((CharSequence) "Yes", (OnClickListener) new MainActivity$onOptionsItemSelected$1(this)).setNegativeButton((CharSequence) "No", (OnClickListener) MainActivity$onOptionsItemSelected$2.INSTANCE);
                this.alertDialog = builder.create();
                AlertDialog alertDialog = this.alertDialog;
                if (alertDialog != null) {
                    alertDialog.show();
                    break;
                }
                break;
            case C0644R.id.medical_profile:
                Intent medicalProfileActivityIntent = new Intent(this, SecondaryVitalsActivity.class);
                String str = "userId";
                user = this.user;
                if (user != null) {
                    id = user.getId();
                } else {
                    id = null;
                }
                medicalProfileActivityIntent.putExtra(str, id);
                startActivity(medicalProfileActivityIntent);
                break;
            case C0644R.id.medicine:
                startActivity(new Intent(this, ViewAllMedicinesActivity.class));
                break;
            case C0644R.id.note_menu_item:
                NoteFullScreenDialog.Companion companion = NoteFullScreenDialog.Companion;
                user = this.user;
                if (user != null) {
                    id = user.getId();
                } else {
                    id = null;
                }
                if (id == null) {
                    Intrinsics.throwNpe();
                }
                NoteFullScreenDialog noteFullScreenDialog = companion.newInstance(id);
                noteFullScreenDialog.show(getSupportFragmentManager(), "note_dialog");
                CommonDatabase commonDatabase = PatientApplication.Companion.getCommonDatabase();
                if (commonDatabase != null) {
                    timelineItemDao = commonDatabase.TimelineItemDao();
                }
                noteFullScreenDialog.setTimelineDao(timelineItemDao);
                break;
            case C0644R.id.reports:
                Intent i = new Intent(this, MedicalReportActivity.class);
                id = "userId";
                User user2 = this.user;
                if (user2 == null) {
                    Intrinsics.throwNpe();
                }
                i.putExtra(id, user2.getId());
                id = "authToken";
                Session session = this.session;
                if (session == null) {
                    Intrinsics.throwNpe();
                }
                i.putExtra(id, session.getId());
                i.putExtra("serverurl", "http://api.cooey.co.in/ehealth/");
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String heartRate = null;
        Session session;
        HashMap values;
        Gson gson;
        Vital vital;
        User user;
        switch (resultCode) {
            case 3984:
                try {
                    Integer spo2;
                    session = new PreferenceStore().getSession(this);
                    if (data != null) {
                        spo2 = Integer.valueOf(data.getIntExtra("SPO2", 0));
                    } else {
                        spo2 = null;
                    }
                    if (data != null) {
                        Integer pulseRate = Integer.valueOf(data.getIntExtra("PULSE_RATE", 0));
                    } else {
                        String str = null;
                    }
                    values = new HashMap();
                    gson = new GsonBuilder().create();
                    values.put("SPO2", String.valueOf(spo2));
                    vital = new Vital();
                    vital.setVitalType("SPO2");
                    vital.setParameters(gson.toJson(values));
                    user = this.user;
                    if (user == null) {
                        Intrinsics.throwNpe();
                    }
                    vital.setUserId(user.getId());
                    vital.setTimestamp(Calendar.getInstance().getTime().getTime());
                    vital.setTakenOn(Calendar.getInstance().getTime().getTime());
                    return;
                } catch (Exception e) {
                    return;
                }
            case 4872:
                String systolic;
                String diastolic;
                session = new PreferenceStore().getSession(this);
                if (data != null) {
                    systolic = data.getStringExtra(BloodPressureCardViewHolder.SYSTOLIC);
                } else {
                    systolic = null;
                }
                if (data != null) {
                    diastolic = data.getStringExtra("DIAS2TOLIC");
                } else {
                    diastolic = null;
                }
                if (data != null) {
                    heartRate = data.getStringExtra("HEART_RATE");
                }
                values = new HashMap();
                gson = new GsonBuilder().create();
                if (systolic != null) {
                    values.put(CooeySQLHelper.COL_SYS, systolic);
                }
                if (diastolic != null) {
                    values.put(CooeySQLHelper.COL_DIA, diastolic);
                }
                if (heartRate != null) {
                    values.put("heartRate", heartRate);
                }
                vital = new Vital();
                vital.setVitalType("BLOOD_PRESSURE");
                vital.setParameters(gson.toJson(values));
                user = this.user;
                if (user == null) {
                    Intrinsics.throwNpe();
                }
                vital.setUserId(user.getId());
                vital.setTimestamp(Calendar.getInstance().getTime().getTime());
                vital.setTakenOn(Calendar.getInstance().getTime().getTime());
                return;
            default:
                return;
        }
    }

    public final void showVideoCallOptions() {
        PeopleSelectorView peopleSelectorView = new PeopleSelectorView(this);
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        User user = this.user;
        if (user == null) {
            Intrinsics.throwNpe();
        }
        peopleSelectorView.initialize(user.getId(), new MainActivity$showVideoCallOptions$1(this, dialog));
        peopleSelectorView.setLayoutParams(new LayoutParams(-1, (int) TypedValue.applyDimension(1, 300.0f, getResources().getDisplayMetrics())));
        dialog.setContentView((View) peopleSelectorView);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    public final void showChatOptions() {
        PeopleSelectorView peopleSelectorView = new PeopleSelectorView(this);
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        User user = this.user;
        if (user == null) {
            Intrinsics.throwNpe();
        }
        peopleSelectorView.initialize(user.getId(), new MainActivity$showChatOptions$1(this, dialog));
        peopleSelectorView.setLayoutParams(new LayoutParams(-1, (int) TypedValue.applyDimension(1, 300.0f, getResources().getDisplayMetrics())));
        dialog.setContentView((View) peopleSelectorView);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    public boolean dispatchTouchEvent(@NotNull MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, NotificationCompat.CATEGORY_EVENT);
        if (event.getAction() == 0) {
            BottomSheetBehavior bottomSheetBehavior = this.bottomSheetBehavior;
            if (bottomSheetBehavior != null && bottomSheetBehavior.getState() == 3) {
                Rect outRect = new Rect();
                ActivityMainBinding activityMainBinding = this.activityMainBinding;
                if (activityMainBinding != null) {
                    VitalBlueprintSelectionView vitalBlueprintSelectionView = activityMainBinding.vitalBlueprintSelector;
                    if (vitalBlueprintSelectionView != null) {
                        vitalBlueprintSelectionView.getGlobalVisibleRect(outRect);
                    }
                }
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    bottomSheetBehavior = this.bottomSheetBehavior;
                    if (bottomSheetBehavior != null) {
                        bottomSheetBehavior.setState(5);
                    }
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    protected void onResume() {
        super.onResume();
        getCheckedFeatures();
        DashboardViewModel dashboardViewModel = this.dashboardViewModel;
        if (dashboardViewModel != null) {
            dashboardViewModel.getActionItemsForUser();
        }
        dashboardViewModel = this.dashboardViewModel;
        if (dashboardViewModel != null) {
            dashboardViewModel.syncData();
        }
        dashboardViewModel = this.dashboardViewModel;
        if (dashboardViewModel != null) {
            DashboardViewPagerAdapter viewPagerAdapter = dashboardViewModel.getViewPagerAdapter();
            if (viewPagerAdapter != null) {
                WidgetsFragment widgetFragment = viewPagerAdapter.getWidgetFragment();
                if (widgetFragment != null) {
                    WidgetRecyclerAdapter widgetRecyclerAdapter = widgetFragment.getWidgetRecyclerAdapter();
                    if (widgetRecyclerAdapter != null) {
                        widgetRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }

    public final void getCheckedFeatures() {
        this.filteredTypes.clear();
        boolean[] booleanArray = TimelineItemTypeSelectionDialogGenerator.Companion.getCheckedFeatures();
        if (booleanArray[0]) {
            this.filteredTypes.add(Integer.valueOf(TimelineItemType.INSTANCE.getVITAL()));
        }
        if (booleanArray[1]) {
            this.filteredTypes.add(Integer.valueOf(TimelineItemType.INSTANCE.getNOTE()));
        }
        if (booleanArray[2]) {
            this.filteredTypes.add(Integer.valueOf(TimelineItemType.INSTANCE.getACTION()));
        }
        if (booleanArray[3]) {
            this.filteredTypes.add(Integer.valueOf(TimelineItemType.INSTANCE.getEVENT()));
        }
        initTimelineAdapterData();
    }

    public final void buildCloseDialog(@NotNull Context context, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(message, "message");
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle((CharSequence) context.getResources().getString(C0644R.string.alert_dialog_title));
        alertBuilder.setMessage((CharSequence) message);
        alertBuilder.setPositiveButton((CharSequence) context.getResources().getString(C0644R.string.error_dialog_btn), (OnClickListener) new MainActivity$buildCloseDialog$1(this));
        alertBuilder.setNegativeButton((CharSequence) context.getResources().getString(C0644R.string.cancel), (OnClickListener) MainActivity$buildCloseDialog$2.INSTANCE);
        alertBuilder.show();
    }

    public final boolean isBluetoothEnabled() {
        try {
            return BluetoothAdapter.getDefaultAdapter().enable();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    protected final void getPermissions() {
        if (VERSION.SDK_INT < 23) {
            turnOnBluetooth();
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            turnOnBluetooth();
        } else {
            Activity activity = this;
            ActivityCompat.requestPermissions(activity, (String[]) new String[]{"android.permission.BLUETOOTH", "android.permission.ACCESS_FINE_LOCATION"}, 1224);
        }
    }

    private final void turnOnBluetooth() {
        try {
            BluetoothAdapter.getDefaultAdapter().enable();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkParameterIsNotNull(permissions, NativeProtocol.RESULT_ARGS_PERMISSIONS);
        Intrinsics.checkParameterIsNotNull(grantResults, "grantResults");
        switch (requestCode) {
            case 1224:
                if (grantResults.length <= 0 || grantResults[0] != 0) {
                    Toast.makeText(this, "Cannot scan devices without permissions.", 0).show();
                    return;
                } else {
                    turnOnBluetooth();
                    return;
                }
            default:
                return;
        }
    }
}
