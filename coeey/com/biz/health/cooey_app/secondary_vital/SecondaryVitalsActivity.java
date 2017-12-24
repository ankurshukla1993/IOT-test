package com.biz.health.cooey_app.secondary_vital;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.graphics.Rect;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import com.biz.health.cooey_app.PatientApplication;
import com.cooey.android.vitals.views.VitalBlueprintSelectionView;
import com.cooey.common.CommonDatabase;
import com.cooey.common.adapters.TimelineRecyclerAdapter;
import com.cooey.common.dao.TimelineItemDao;
import com.cooey.common.vo.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u001aH\u0016J\u0012\u0010\u001c\u001a\u00020\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Lcom/biz/health/cooey_app/secondary_vital/SecondaryVitalsActivity;", "Landroid/support/v7/app/AppCompatActivity;", "Landroid/arch/lifecycle/LifecycleOwner;", "()V", "adapter", "Lcom/cooey/common/adapters/TimelineRecyclerAdapter;", "bottomSheetBehavior", "Landroid/support/design/widget/BottomSheetBehavior;", "Landroid/view/View;", "session", "Lcom/cooey/common/vo/Session;", "userId", "", "vitalBlueprintSelectionView", "Lcom/cooey/android/vitals/views/VitalBlueprintSelectionView;", "vitalTypes", "", "getVitalTypes", "()Ljava/util/List;", "setVitalTypes", "(Ljava/util/List;)V", "dispatchTouchEvent", "", "event", "Landroid/view/MotionEvent;", "initVitals", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: SecondaryVitalsActivity.kt */
public final class SecondaryVitalsActivity extends AppCompatActivity implements LifecycleOwner {
    private HashMap _$_findViewCache;
    private TimelineRecyclerAdapter adapter;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    private Session session;
    private String userId;
    private VitalBlueprintSelectionView vitalBlueprintSelectionView;
    @NotNull
    private List<String> vitalTypes = new ArrayList();

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

    protected void onCreate(@org.jetbrains.annotations.Nullable android.os.Bundle r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.biz.health.cooey_app.secondary_vital.SecondaryVitalsActivity.onCreate(android.os.Bundle):void
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
	at jadx.core.dex.nodes.MethodNode.initJumps(MethodNode.java:356)
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:106)
	... 7 more
*/
        /*
        r0 = this;
        super.onCreate(r13);
        r9 = r12.getIntent();
        r10 = "userId";
        r9 = r9.getStringExtra(r10);
        r12.userId = r9;
        r9 = 2131492941; // 0x7f0c004d float:1.8609348E38 double:1.0530974365E-314;
        r12.setContentView(r9);
        r9 = com.biz.health.cooey_app.C0644R.id.toolbar;
        r9 = r12._$_findCachedViewById(r9);
        r9 = (android.support.v7.widget.Toolbar) r9;
        r12.setSupportActionBar(r9);
        r9 = r12.getSupportActionBar();
        if (r9 == 0) goto L_0x002b;
    L_0x0027:
        r10 = 1;
        r9.setDisplayHomeAsUpEnabled(r10);
    L_0x002b:
        r9 = com.biz.health.cooey_app.PatientApplication.Companion;
        r9 = r9.getSession();
        if (r9 != 0) goto L_0x0044;
    L_0x0033:
        r10 = com.biz.health.cooey_app.PatientApplication.Companion;
        r11 = new com.cooey.common.stores.PreferenceStore;
        r11.<init>();
        r9 = r12;
        r9 = (android.content.Context) r9;
        r9 = r11.getSession(r9);
        r10.setSession(r9);
    L_0x0044:
        r10 = new com.cooey.common.stores.PreferenceStore;
        r10.<init>();
        r9 = r12;
        r9 = (android.content.Context) r9;
        r9 = r10.getSession(r9);
        r12.session = r9;
        r9 = 2131362793; // 0x7f0a03e9 float:1.8345377E38 double:1.053033135E-314;
        r9 = r12.findViewById(r9);
        r9 = (com.cooey.android.vitals.views.VitalBlueprintSelectionView) r9;
        r12.vitalBlueprintSelectionView = r9;
        r9 = r12.vitalBlueprintSelectionView;
        if (r9 == 0) goto L_0x006a;
    L_0x0061:
        r10 = com.biz.health.cooey_app.PatientApplication.Companion;
        r10 = r10.getVitalBlueprintRepository();
        r9.setVitalBlueprintsRepository(r10);
    L_0x006a:
        r9 = r12.vitalBlueprintSelectionView;
        if (r9 == 0) goto L_0x0077;
    L_0x006e:
        r10 = com.biz.health.cooey_app.PatientApplication.Companion;
        r10 = r10.getVitalsRepository();
        r9.setVitalRepository(r10);
    L_0x0077:
        r9 = r12.vitalBlueprintSelectionView;
        if (r9 == 0) goto L_0x0080;
    L_0x007b:
        r10 = r12.userId;
        r9.setUserId(r10);
    L_0x0080:
        r9 = r12.vitalBlueprintSelectionView;
        if (r9 == 0) goto L_0x0089;
    L_0x0084:
        r10 = r12.session;
        r9.setSession(r10);
    L_0x0089:
        r9 = r12.vitalBlueprintSelectionView;
        if (r9 == 0) goto L_0x0091;
    L_0x008d:
        r10 = 0;
        r9.setShowPrimary(r10);
    L_0x0091:
        r9 = r12.vitalBlueprintSelectionView;
        if (r9 == 0) goto L_0x0098;
    L_0x0095:
        r9.initialize();
    L_0x0098:
        r11 = r12.vitalBlueprintSelectionView;
        if (r11 == 0) goto L_0x00ba;
    L_0x009c:
        r9 = r12.session;
        if (r9 == 0) goto L_0x016e;
    L_0x00a0:
        r9 = r9.getId();
        r10 = r9;
        if (r10 != 0) goto L_0x00aa;
    L_0x00a7:
        kotlin.jvm.internal.Intrinsics.throwNpe();
    L_0x00aa:
        r9 = r12.session;
        if (r9 == 0) goto L_0x0172;
    L_0x00ae:
        r9 = r9.getTenantId();
        if (r9 != 0) goto L_0x00b7;
    L_0x00b4:
        kotlin.jvm.internal.Intrinsics.throwNpe();
    L_0x00b7:
        r11.sync(r10, r9);
    L_0x00ba:
        r9 = 2131362793; // 0x7f0a03e9 float:1.8345377E38 double:1.053033135E-314;
        r9 = r12.findViewById(r9);
        r9 = android.support.design.widget.BottomSheetBehavior.from(r9);
        r12.bottomSheetBehavior = r9;
        r9 = r12.bottomSheetBehavior;
        if (r9 == 0) goto L_0x00cf;
    L_0x00cb:
        r10 = 1;
        r9.setHideable(r10);
    L_0x00cf:
        r9 = r12.bottomSheetBehavior;
        if (r9 == 0) goto L_0x00d7;
    L_0x00d3:
        r10 = 5;
        r9.setState(r10);
    L_0x00d7:
        r9 = com.biz.health.cooey_app.C0644R.id.fab;
        r9 = r12._$_findCachedViewById(r9);
        r9 = (android.support.design.widget.FloatingActionButton) r9;
        r10 = new com.biz.health.cooey_app.secondary_vital.SecondaryVitalsActivity$onCreate$1;
        r10.<init>(r12);
        r10 = (android.view.View.OnClickListener) r10;
        r9.setOnClickListener(r10);
        r9 = com.cooey.common.views.TimelineFragment.Companion;
        r10 = r12.userId;
        if (r10 != 0) goto L_0x00f2;
    L_0x00ef:
        kotlin.jvm.internal.Intrinsics.throwNpe();
    L_0x00f2:
        r4 = r9.newInstance(r10);
        r9 = r12.getSupportFragmentManager();
        r7 = r9.beginTransaction();
        r5 = new com.cooey.common.views.TimelineHeaderView;
        r9 = r12;
        r9 = (android.content.Context) r9;
        r5.<init>(r9);
        r6 = new com.cooey.common.holders.TimelineHeaderViewHolder;
        r5 = (android.view.View) r5;
        r6.<init>(r5);
        r8 = new com.cooey.android.vitals.generators.VitalTimelineViewHolderGenerator;
        r9 = r12;
        r9 = (android.arch.lifecycle.LifecycleOwner) r9;
        r10 = com.biz.health.cooey_app.PatientApplication.Companion;
        r10 = r10.getVitalsRepository();
        r8.<init>(r9, r10);
        r9 = new com.cooey.common.adapters.TimelineRecyclerAdapter;
        r8 = (com.cooey.common.generators.ITimelineViewHolderGenerator) r8;
        r10 = 0;
        r9.<init>(r8, r6, r10);
        r12.adapter = r9;
        r9 = r12.adapter;
        if (r9 == 0) goto L_0x012d;
    L_0x0129:
        r10 = 0;
        r9.setHeaderEnabled(r10);
    L_0x012d:
        r9 = r12.adapter;
        r4.setTimelineRecyclerAdapter(r9);
        r9 = com.biz.health.cooey_app.PatientApplication.Companion;
        r9 = r9.getVitalBlueprintRepository();
        if (r9 == 0) goto L_0x0175;
    L_0x013a:
        r2 = r9.getVitalBlueprintsFromDatabaseSync();
        if (r2 == 0) goto L_0x0177;
    L_0x0140:
        r0 = r2;
        r0 = (java.lang.Iterable) r0;
        r9 = r0.iterator();
        r10 = r9.hasNext();
        if (r10 == 0) goto L_0x0177;
    L_0x014d:
        r3 = r9.next();
        r1 = r3;
        r1 = (com.cooey.android.vitals.VitalBlueprint) r1;
        r10 = r1.isPrimary();
        if (r10 != 0) goto L_0x015d;
    L_0x015a:
        kotlin.jvm.internal.Intrinsics.throwNpe();
    L_0x015d:
        r10 = r10.booleanValue();
        if (r10 != 0) goto L_0x016c;
    L_0x0163:
        r10 = r12.vitalTypes;
        r11 = r1.getType();
        r10.add(r11);
        goto L_0x0147;
    L_0x016e:
        r9 = 0;
        r10 = r9;
        goto L_0x00a5;
    L_0x0172:
        r9 = 0;
        goto L_0x00b2;
    L_0x0175:
        r2 = 0;
        goto L_0x013e;
    L_0x0177:
        r12.initVitals();
        r9 = 2131362003; // 0x7f0a00d3 float:1.8343774E38 double:1.0530327445E-314;
        r4 = (android.support.v4.app.Fragment) r4;
        r7.replace(r9, r4);
        r7.commit();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.biz.health.cooey_app.secondary_vital.SecondaryVitalsActivity.onCreate(android.os.Bundle):void");
    }

    @NotNull
    public final List<String> getVitalTypes() {
        return this.vitalTypes;
    }

    public final void setVitalTypes(@NotNull List<String> <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.vitalTypes = <set-?>;
    }

    public final void initVitals() {
        LiveData timelineItemsLiveData;
        CommonDatabase commonDatabase = PatientApplication.Companion.getCommonDatabase();
        if (commonDatabase != null) {
            TimelineItemDao TimelineItemDao = commonDatabase.TimelineItemDao();
            if (TimelineItemDao != null) {
                String str = this.userId;
                if (str == null) {
                    Intrinsics.throwNpe();
                }
                timelineItemsLiveData = TimelineItemDao.getTimelineItemsByType(0, str);
                if (timelineItemsLiveData != null) {
                    timelineItemsLiveData.observe(this, new SecondaryVitalsActivity$initVitals$1(this));
                }
            }
        }
        timelineItemsLiveData = null;
        if (timelineItemsLiveData != null) {
            timelineItemsLiveData.observe(this, new SecondaryVitalsActivity$initVitals$1(this));
        }
    }

    public boolean dispatchTouchEvent(@NotNull MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, NotificationCompat.CATEGORY_EVENT);
        if (event.getAction() == 0) {
            BottomSheetBehavior bottomSheetBehavior = this.bottomSheetBehavior;
            if (bottomSheetBehavior != null && bottomSheetBehavior.getState() == 3) {
                Rect outRect = new Rect();
                VitalBlueprintSelectionView vitalBlueprintSelectionView = this.vitalBlueprintSelectionView;
                if (vitalBlueprintSelectionView != null) {
                    vitalBlueprintSelectionView.getGlobalVisibleRect(outRect);
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

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
