package com.cooey.android.vitals;

import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.cooey.android.vitals.generators.VitalTimelineViewHolderGenerator;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.android.vitals.views.VitalGraphView;
import com.cooey.android.vitals.views.VitalInputDialogFragment;
import com.cooey.common.CommonDatabase;
import com.cooey.common.adapters.TimelineRecyclerAdapter;
import com.cooey.common.dao.TimelineItemDao;
import com.cooey.common.holders.TimelineHeaderViewHolder;
import com.cooey.common.views.TimelineFragment;
import com.cooey.common.views.TimelineHeaderView;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.timeline.TimelineItem;
import com.cooey.common.vo.timeline.TimelineItemType;
import humanize.util.Constants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0011H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/cooey/android/vitals/VitalListActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "showDialogIntent", "", "vitalInputFragment", "Lcom/cooey/android/vitals/views/VitalInputDialogFragment;", "getVitalInputFragment", "()Lcom/cooey/android/vitals/views/VitalInputDialogFragment;", "setVitalInputFragment", "(Lcom/cooey/android/vitals/views/VitalInputDialogFragment;)V", "isBlueprintTimelineItem", "timelineItem", "Lcom/cooey/common/vo/timeline/TimelineItem;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onSaveInstanceState", "outState", "Companion", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalListActivity.kt */
public final class VitalListActivity extends AppCompatActivity {
    public static final Companion Companion = new Companion();
    @Nullable
    private static CommonDatabase commonDatabase;
    @Nullable
    private static Session session;
    @Nullable
    private static String userId;
    @Nullable
    private static VitalBlueprint vitalBlueprint;
    @Nullable
    private static VitalRepository vitalRepository;
    private HashMap _$_findViewCache;
    private boolean showDialogIntent;
    @Nullable
    private VitalInputDialogFragment vitalInputFragment;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lcom/cooey/android/vitals/VitalListActivity$Companion;", "", "()V", "commonDatabase", "Lcom/cooey/common/CommonDatabase;", "getCommonDatabase", "()Lcom/cooey/common/CommonDatabase;", "setCommonDatabase", "(Lcom/cooey/common/CommonDatabase;)V", "session", "Lcom/cooey/common/vo/Session;", "getSession", "()Lcom/cooey/common/vo/Session;", "setSession", "(Lcom/cooey/common/vo/Session;)V", "userId", "", "getUserId", "()Ljava/lang/String;", "setUserId", "(Ljava/lang/String;)V", "vitalBlueprint", "Lcom/cooey/android/vitals/VitalBlueprint;", "getVitalBlueprint", "()Lcom/cooey/android/vitals/VitalBlueprint;", "setVitalBlueprint", "(Lcom/cooey/android/vitals/VitalBlueprint;)V", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "vitals_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: VitalListActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final String getUserId() {
            return VitalListActivity.userId;
        }

        public final void setUserId(@Nullable String <set-?>) {
            VitalListActivity.userId = <set-?>;
        }

        @Nullable
        public final VitalRepository getVitalRepository() {
            return VitalListActivity.vitalRepository;
        }

        public final void setVitalRepository(@Nullable VitalRepository <set-?>) {
            VitalListActivity.vitalRepository = <set-?>;
        }

        @Nullable
        public final VitalBlueprint getVitalBlueprint() {
            return VitalListActivity.vitalBlueprint;
        }

        public final void setVitalBlueprint(@Nullable VitalBlueprint <set-?>) {
            VitalListActivity.vitalBlueprint = <set-?>;
        }

        @Nullable
        public final CommonDatabase getCommonDatabase() {
            return VitalListActivity.commonDatabase;
        }

        public final void setCommonDatabase(@Nullable CommonDatabase <set-?>) {
            VitalListActivity.commonDatabase = <set-?>;
        }

        @Nullable
        public final Session getSession() {
            return VitalListActivity.session;
        }

        public final void setSession(@Nullable Session <set-?>) {
            VitalListActivity.session = <set-?>;
        }
    }

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
    public final VitalInputDialogFragment getVitalInputFragment() {
        return this.vitalInputFragment;
    }

    public final void setVitalInputFragment(@Nullable VitalInputDialogFragment <set-?>) {
        this.vitalInputFragment = <set-?>;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        String type;
        VitalGraphView vitalGraphView;
        com.cooey.common.views.TimelineFragment.Companion companion;
        String userId;
        TimelineFragment newFragment;
        FragmentTransaction transaction;
        TimelineRecyclerAdapter adapter;
        CommonDatabase commonDatabase;
        TimelineItemDao TimelineItemDao;
        LiveData timelineItemsLiveData;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_list);
        setSupportActionBar((Toolbar) _$_findCachedViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) "");
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setDisplayHomeAsUpEnabled(true);
        }
        TextView textView = (TextView) _$_findCachedViewById(R.id.toolbar_title);
        VitalBlueprint vitalBlueprint = Companion.getVitalBlueprint();
        if (vitalBlueprint != null) {
            type = vitalBlueprint.getType();
            if (type != null) {
                type = StringsKt__StringsJVMKt.replace$default(type, EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR, Constants.SPACE, false, 4, null);
                textView.setText(type);
                ((FloatingActionButton) _$_findCachedViewById(R.id.fab)).setOnClickListener(new VitalListActivity$onCreate$1(this));
                vitalGraphView = (VitalGraphView) findViewById(R.id.vital_graph_view);
                vitalGraphView.setVitalRepository(Companion.getVitalRepository());
                vitalGraphView.setLifeCycleOwner(this);
                vitalGraphView.setUserId(Companion.getUserId());
                vitalBlueprint = Companion.getVitalBlueprint();
                if (vitalBlueprint == null) {
                    Intrinsics.throwNpe();
                }
                vitalGraphView.loadVitals(vitalBlueprint);
                companion = TimelineFragment.Companion;
                userId = Companion.getUserId();
                if (userId == null) {
                    Intrinsics.throwNpe();
                }
                newFragment = companion.newInstance(userId);
                transaction = getSupportFragmentManager().beginTransaction();
                adapter = new TimelineRecyclerAdapter(new VitalTimelineViewHolderGenerator(this, Companion.getVitalRepository()), new TimelineHeaderViewHolder(new TimelineHeaderView(this)), null);
                adapter.setHeaderEnabled(false);
                newFragment.setTimelineRecyclerAdapter(adapter);
                commonDatabase = Companion.getCommonDatabase();
                if (commonDatabase != null) {
                    TimelineItemDao = commonDatabase.TimelineItemDao();
                    if (TimelineItemDao != null) {
                        String userId2;
                        int vital = TimelineItemType.INSTANCE.getVITAL();
                        vitalBlueprint = Companion.getVitalBlueprint();
                        type = vitalBlueprint == null ? vitalBlueprint.getType() : null;
                        if (type == null) {
                            Intrinsics.throwNpe();
                        }
                        userId2 = Companion.getUserId();
                        if (userId2 == null) {
                            Intrinsics.throwNpe();
                        }
                        timelineItemsLiveData = TimelineItemDao.getTimelineItemsByTypeAndSubType(vital, type, userId2);
                        if (timelineItemsLiveData != null) {
                            timelineItemsLiveData.observe(this, new VitalListActivity$onCreate$2(adapter));
                        }
                        transaction.replace(R.id.container, newFragment);
                        transaction.commit();
                        this.showDialogIntent = getIntent().getBooleanExtra("SHOW_INPUT_DIALOG", false);
                        if (!this.showDialogIntent) {
                            ((FloatingActionButton) _$_findCachedViewById(R.id.fab)).performClick();
                        }
                    }
                }
                timelineItemsLiveData = null;
                if (timelineItemsLiveData != null) {
                    timelineItemsLiveData.observe(this, new VitalListActivity$onCreate$2(adapter));
                }
                transaction.replace(R.id.container, newFragment);
                transaction.commit();
                this.showDialogIntent = getIntent().getBooleanExtra("SHOW_INPUT_DIALOG", false);
                if (!this.showDialogIntent) {
                    ((FloatingActionButton) _$_findCachedViewById(R.id.fab)).performClick();
                }
            }
        }
        type = null;
        textView.setText(type);
        ((FloatingActionButton) _$_findCachedViewById(R.id.fab)).setOnClickListener(new VitalListActivity$onCreate$1(this));
        vitalGraphView = (VitalGraphView) findViewById(R.id.vital_graph_view);
        vitalGraphView.setVitalRepository(Companion.getVitalRepository());
        vitalGraphView.setLifeCycleOwner(this);
        vitalGraphView.setUserId(Companion.getUserId());
        vitalBlueprint = Companion.getVitalBlueprint();
        if (vitalBlueprint == null) {
            Intrinsics.throwNpe();
        }
        vitalGraphView.loadVitals(vitalBlueprint);
        companion = TimelineFragment.Companion;
        userId = Companion.getUserId();
        if (userId == null) {
            Intrinsics.throwNpe();
        }
        newFragment = companion.newInstance(userId);
        transaction = getSupportFragmentManager().beginTransaction();
        adapter = new TimelineRecyclerAdapter(new VitalTimelineViewHolderGenerator(this, Companion.getVitalRepository()), new TimelineHeaderViewHolder(new TimelineHeaderView(this)), null);
        adapter.setHeaderEnabled(false);
        newFragment.setTimelineRecyclerAdapter(adapter);
        commonDatabase = Companion.getCommonDatabase();
        if (commonDatabase != null) {
            TimelineItemDao = commonDatabase.TimelineItemDao();
            if (TimelineItemDao != null) {
                int vital2 = TimelineItemType.INSTANCE.getVITAL();
                vitalBlueprint = Companion.getVitalBlueprint();
                if (vitalBlueprint == null) {
                }
                if (type == null) {
                    Intrinsics.throwNpe();
                }
                userId2 = Companion.getUserId();
                if (userId2 == null) {
                    Intrinsics.throwNpe();
                }
                timelineItemsLiveData = TimelineItemDao.getTimelineItemsByTypeAndSubType(vital2, type, userId2);
                if (timelineItemsLiveData != null) {
                    timelineItemsLiveData.observe(this, new VitalListActivity$onCreate$2(adapter));
                }
                transaction.replace(R.id.container, newFragment);
                transaction.commit();
                this.showDialogIntent = getIntent().getBooleanExtra("SHOW_INPUT_DIALOG", false);
                if (!this.showDialogIntent) {
                    ((FloatingActionButton) _$_findCachedViewById(R.id.fab)).performClick();
                }
            }
        }
        timelineItemsLiveData = null;
        if (timelineItemsLiveData != null) {
            timelineItemsLiveData.observe(this, new VitalListActivity$onCreate$2(adapter));
        }
        transaction.replace(R.id.container, newFragment);
        transaction.commit();
        this.showDialogIntent = getIntent().getBooleanExtra("SHOW_INPUT_DIALOG", false);
        if (!this.showDialogIntent) {
            ((FloatingActionButton) _$_findCachedViewById(R.id.fab)).performClick();
        }
    }

    public final boolean isBlueprintTimelineItem(@NotNull TimelineItem timelineItem) {
        Boolean bool = null;
        Intrinsics.checkParameterIsNotNull(timelineItem, "timelineItem");
        try {
            String subType = timelineItem.getSubType();
            if (subType != null) {
                String type;
                VitalBlueprint vitalBlueprint = Companion.getVitalBlueprint();
                if (vitalBlueprint != null) {
                    type = vitalBlueprint.getType();
                }
                if (type == null) {
                    Intrinsics.throwNpe();
                }
                CharSequence charSequence = type;
                if (subType == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                bool = Boolean.valueOf(subType.contentEquals(charSequence));
            }
            if (bool == null) {
                Intrinsics.throwNpe();
            }
            return bool.booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            default:
                return false;
        }
    }

    protected void onSaveInstanceState(@Nullable Bundle outState) {
    }
}
