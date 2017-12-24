package com.biz.health.cooey_app.dashboard;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.dashboard.widgets.WidgetsFragment;
import com.cooey.android.users.old.fragments.AboutFragment;
import com.cooey.android.vitals.repositories.VitalBlueprintsRepository;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.common.views.TimelineFragment;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.User;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\u0018\u0000 =2\u00020\u0001:\u0001=BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\b\u00107\u001a\u00020\u0011H\u0016J\u0010\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u0011H\u0016J\u0010\u0010;\u001a\u00020<2\u0006\u0010:\u001a\u00020\u0011H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u00101\u001a\u0004\u0018\u000102X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106¨\u0006>"}, d2 = {"Lcom/biz/health/cooey_app/dashboard/DashboardViewPagerAdapter;", "Landroid/support/v4/app/FragmentStatePagerAdapter;", "context", "Landroid/content/Context;", "fragmentManager", "Landroid/support/v4/app/FragmentManager;", "timelineFragment", "Lcom/cooey/common/views/TimelineFragment;", "vitalBlueprintsRepository", "Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "user", "Lcom/cooey/common/vo/User;", "session", "Lcom/cooey/common/vo/Session;", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "mode", "", "(Landroid/content/Context;Landroid/support/v4/app/FragmentManager;Lcom/cooey/common/views/TimelineFragment;Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;Lcom/cooey/common/vo/User;Lcom/cooey/common/vo/Session;Lcom/cooey/android/vitals/repositories/VitalRepository;I)V", "getContext", "()Landroid/content/Context;", "getFragmentManager", "()Landroid/support/v4/app/FragmentManager;", "setFragmentManager", "(Landroid/support/v4/app/FragmentManager;)V", "getMode", "()I", "setMode", "(I)V", "getSession", "()Lcom/cooey/common/vo/Session;", "setSession", "(Lcom/cooey/common/vo/Session;)V", "getTimelineFragment", "()Lcom/cooey/common/views/TimelineFragment;", "setTimelineFragment", "(Lcom/cooey/common/views/TimelineFragment;)V", "getUser", "()Lcom/cooey/common/vo/User;", "setUser", "(Lcom/cooey/common/vo/User;)V", "getVitalBlueprintsRepository", "()Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "setVitalBlueprintsRepository", "(Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;)V", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "widgetFragment", "Lcom/biz/health/cooey_app/dashboard/widgets/WidgetsFragment;", "getWidgetFragment", "()Lcom/biz/health/cooey_app/dashboard/widgets/WidgetsFragment;", "setWidgetFragment", "(Lcom/biz/health/cooey_app/dashboard/widgets/WidgetsFragment;)V", "getCount", "getItem", "Landroid/support/v4/app/Fragment;", "position", "getPageTitle", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: DashboardViewPagerAdapter.kt */
public final class DashboardViewPagerAdapter extends FragmentStatePagerAdapter {
    public static final Companion Companion = new Companion();
    private static final String PREFERENCE_NAME = PREFERENCE_NAME;
    private static final String PROFILE_KEY = PROFILE_KEY;
    @NotNull
    private final Context context;
    @NotNull
    private FragmentManager fragmentManager;
    private int mode;
    @Nullable
    private Session session;
    @NotNull
    private TimelineFragment timelineFragment;
    @Nullable
    private User user;
    @NotNull
    private VitalBlueprintsRepository vitalBlueprintsRepository;
    @NotNull
    private VitalRepository vitalRepository;
    @Nullable
    private WidgetsFragment widgetFragment;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/biz/health/cooey_app/dashboard/DashboardViewPagerAdapter$Companion;", "", "()V", "PREFERENCE_NAME", "", "getPREFERENCE_NAME", "()Ljava/lang/String;", "PROFILE_KEY", "getPROFILE_KEY", "app_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: DashboardViewPagerAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        private final String getPREFERENCE_NAME() {
            return DashboardViewPagerAdapter.PREFERENCE_NAME;
        }

        private final String getPROFILE_KEY() {
            return DashboardViewPagerAdapter.PROFILE_KEY;
        }
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final FragmentManager getFragmentManager() {
        return this.fragmentManager;
    }

    @NotNull
    public final TimelineFragment getTimelineFragment() {
        return this.timelineFragment;
    }

    public final void setFragmentManager(@NotNull FragmentManager <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.fragmentManager = <set-?>;
    }

    public final void setTimelineFragment(@NotNull TimelineFragment <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.timelineFragment = <set-?>;
    }

    @NotNull
    public final VitalBlueprintsRepository getVitalBlueprintsRepository() {
        return this.vitalBlueprintsRepository;
    }

    public final void setVitalBlueprintsRepository(@NotNull VitalBlueprintsRepository <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.vitalBlueprintsRepository = <set-?>;
    }

    public DashboardViewPagerAdapter(@NotNull Context context, @NotNull FragmentManager fragmentManager, @NotNull TimelineFragment timelineFragment, @NotNull VitalBlueprintsRepository vitalBlueprintsRepository, @Nullable User user, @Nullable Session session, @NotNull VitalRepository vitalRepository, int mode) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        Intrinsics.checkParameterIsNotNull(timelineFragment, "timelineFragment");
        Intrinsics.checkParameterIsNotNull(vitalBlueprintsRepository, "vitalBlueprintsRepository");
        Intrinsics.checkParameterIsNotNull(vitalRepository, "vitalRepository");
        super(fragmentManager);
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.timelineFragment = timelineFragment;
        this.vitalBlueprintsRepository = vitalBlueprintsRepository;
        this.user = user;
        this.session = session;
        this.vitalRepository = vitalRepository;
        this.mode = mode;
        com.biz.health.cooey_app.dashboard.widgets.WidgetsFragment.Companion companion = WidgetsFragment.Companion;
        User user2 = this.user;
        if (user2 == null) {
            Intrinsics.throwNpe();
        }
        Session session2 = this.session;
        String id = session2 != null ? session2.getId() : null;
        if (id == null) {
            Intrinsics.throwNpe();
        }
        this.widgetFragment = companion.newInstance(user2, id);
        WidgetsFragment widgetsFragment = this.widgetFragment;
        if (widgetsFragment != null) {
            widgetsFragment.setVitalBlueprintRepository(this.vitalBlueprintsRepository);
        }
        widgetsFragment = this.widgetFragment;
        if (widgetsFragment != null) {
            widgetsFragment.setVitalRepository(this.vitalRepository);
        }
    }

    public final int getMode() {
        return this.mode;
    }

    @Nullable
    public final Session getSession() {
        return this.session;
    }

    @Nullable
    public final User getUser() {
        return this.user;
    }

    @NotNull
    public final VitalRepository getVitalRepository() {
        return this.vitalRepository;
    }

    public final void setMode(int <set-?>) {
        this.mode = <set-?>;
    }

    public final void setSession(@Nullable Session <set-?>) {
        this.session = <set-?>;
    }

    public final void setUser(@Nullable User <set-?>) {
        this.user = <set-?>;
    }

    public final void setVitalRepository(@NotNull VitalRepository <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.vitalRepository = <set-?>;
    }

    @Nullable
    public final WidgetsFragment getWidgetFragment() {
        return this.widgetFragment;
    }

    public final void setWidgetFragment(@Nullable WidgetsFragment <set-?>) {
        this.widgetFragment = <set-?>;
    }

    @NotNull
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                WidgetsFragment widgetsFragment = this.widgetFragment;
                if (widgetsFragment == null) {
                    Intrinsics.throwNpe();
                }
                return widgetsFragment;
            case 1:
                return this.timelineFragment;
            case 2:
                AboutFragment aboutFragment = new AboutFragment();
                aboutFragment.user = this.user;
                Session session = this.session;
                if (session == null) {
                    Intrinsics.throwNpe();
                }
                aboutFragment.sessionId = session.getId();
                aboutFragment.serverUrl = "http://api.cooey.co.in/ehealth/";
                aboutFragment.tenantId = this.context.getString(C0644R.string.tenant_id);
                return aboutFragment;
            default:
                return this.timelineFragment;
        }
    }

    public int getCount() {
        return 3;
    }

    @NotNull
    public CharSequence getPageTitle(int position) {
        String string;
        if (position == 0) {
            string = this.context.getResources().getString(C0644R.string.home);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.resources.getString(R.string.home)");
            return string;
        } else if (position == 1) {
            string = this.context.getResources().getString(C0644R.string.timeline);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.resources.getString(R.string.timeline)");
            return string;
        } else if (position == 2) {
            string = this.context.getResources().getString(C0644R.string.profile);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.resources.getString(R.string.profile)");
            return string;
        } else if (position == 3) {
            return "ABOUT";
        } else {
            string = this.context.getResources().getString(C0644R.string.graphs);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.resources.getString(R.string.graphs)");
            return string;
        }
    }
}
