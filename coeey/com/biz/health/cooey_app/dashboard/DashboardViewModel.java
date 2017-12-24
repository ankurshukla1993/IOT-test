package com.biz.health.cooey_app.dashboard;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.cooey.android.vitals.Vital;
import com.cooey.android.vitals.repositories.VitalBlueprintsRepository;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.android.vitals.services.VitalsService;
import com.cooey.android.vitals.services.VitalsService.DefaultImpls;
import com.cooey.common.generators.ServiceGenerator;
import com.cooey.common.services.ActionItemService;
import com.cooey.common.services.ActionSummaryService;
import com.cooey.common.services.ActivitiesService;
import com.cooey.common.services.ApiClient;
import com.cooey.common.services.CareplanService;
import com.cooey.common.services.MedicinesService;
import com.cooey.common.services.NoteService;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.views.TimelineFragment;
import com.cooey.common.vo.Action;
import com.cooey.common.vo.Activity;
import com.cooey.common.vo.ActivityResponse;
import com.cooey.common.vo.Channel;
import com.cooey.common.vo.ChannelType;
import com.cooey.common.vo.Medicine;
import com.cooey.common.vo.Note;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.User;
import com.cooey.common.vo.careplan.Careplan;
import com.cooey.common.vo.careplan.Intervention;
import com.cooey.common.vo.timeline.TimelineItem;
import com.cooey.common.vo.timeline.TimelineItemType;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u0006\u0010T\u001a\u00020UJ\u0016\u0010V\u001a\n\u0012\u0004\u0012\u00020W\u0018\u00010\u00102\u0006\u0010X\u001a\u00020:J\u0010\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020LH\u0002J\n\u0010\\\u001a\u0004\u0018\u00010BH\u0007J\u0006\u0010]\u001a\u00020UJ\u000e\u0010^\u001a\u00020U2\u0006\u0010_\u001a\u00020BJ\u0006\u0010`\u001a\u00020UR\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00108F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0019\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u00108F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0013R\u0019\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u00108F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0013R\u000e\u0010 \u001a\u00020!X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\"\u001a\u0004\u0018\u00010#8F¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R&\u0010*\u001a\u00020)2\u0006\u0010(\u001a\u00020)8\u0007@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0019\u0010.\u001a\n\u0012\u0004\u0012\u00020/\u0018\u00010\u00108F¢\u0006\u0006\u001a\u0004\b0\u0010\u0013R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0014\u00109\u001a\u00020:XD¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u0010\u0010A\u001a\u0004\u0018\u00010BX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u0019\u0010K\u001a\n\u0012\u0004\u0012\u00020L\u0018\u00010\u00108F¢\u0006\u0006\u001a\u0004\bM\u0010\u0013R\u001c\u0010N\u001a\u0004\u0018\u00010OX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010S¨\u0006a"}, d2 = {"Lcom/biz/health/cooey_app/dashboard/DashboardViewModel;", "Landroid/databinding/BaseObservable;", "context", "Landroid/content/Context;", "timelineFragment", "Lcom/cooey/common/views/TimelineFragment;", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "vitalBlueprintsRepository", "Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "session", "Lcom/cooey/common/vo/Session;", "user", "Lcom/cooey/common/vo/User;", "(Landroid/content/Context;Lcom/cooey/common/views/TimelineFragment;Lcom/cooey/android/vitals/repositories/VitalRepository;Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;Lcom/cooey/common/vo/Session;Lcom/cooey/common/vo/User;)V", "actionsCompletedForUser", "", "Lcom/cooey/common/vo/Action;", "getActionsCompletedForUser", "()Ljava/util/List;", "actionsItemService", "Lcom/cooey/common/services/ActionItemService;", "getActionsItemService", "()Lcom/cooey/common/services/ActionItemService;", "setActionsItemService", "(Lcom/cooey/common/services/ActionItemService;)V", "activitiesForUser", "Lcom/cooey/common/vo/Activity;", "getActivitiesForUser", "allNotesForUser", "Lcom/cooey/common/vo/Note;", "getAllNotesForUser", "apiClient", "Lcom/cooey/common/services/ApiClient;", "carePlanForUser", "Lcom/cooey/common/vo/careplan/Careplan;", "getCarePlanForUser", "()Lcom/cooey/common/vo/careplan/Careplan;", "getContext", "()Landroid/content/Context;", "inProgress", "", "isInProgress", "()Z", "setInProgress", "(Z)V", "medicinesForUser", "Lcom/cooey/common/vo/Medicine;", "getMedicinesForUser", "getSession", "()Lcom/cooey/common/vo/Session;", "setSession", "(Lcom/cooey/common/vo/Session;)V", "getTimelineFragment", "()Lcom/cooey/common/views/TimelineFragment;", "setTimelineFragment", "(Lcom/cooey/common/views/TimelineFragment;)V", "url", "", "getUrl", "()Ljava/lang/String;", "getUser", "()Lcom/cooey/common/vo/User;", "setUser", "(Lcom/cooey/common/vo/User;)V", "viewPagerAdapter", "Lcom/biz/health/cooey_app/dashboard/DashboardViewPagerAdapter;", "getVitalBlueprintsRepository", "()Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "setVitalBlueprintsRepository", "(Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;)V", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "vitalsForUser", "Lcom/cooey/android/vitals/Vital;", "getVitalsForUser", "vitalsService", "Lcom/cooey/android/vitals/services/VitalsService;", "getVitalsService", "()Lcom/cooey/android/vitals/services/VitalsService;", "setVitalsService", "(Lcom/cooey/android/vitals/services/VitalsService;)V", "getActionItemsForUser", "", "getInterventionsForUser", "Lcom/cooey/common/vo/careplan/Intervention;", "careplanId", "getTimelineItemForVital", "Lcom/cooey/common/vo/timeline/TimelineItem;", "vital", "getViewPagerAdapter", "registerNotificationChannel", "setViewPagerAdapter", "dashboardViewPagerAdapter", "syncData", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: DashboardViewModel.kt */
public final class DashboardViewModel extends BaseObservable {
    @Nullable
    private ActionItemService actionsItemService = ((ActionItemService) new ServiceGenerator(this.url).create(ActionItemService.class));
    private final ApiClient apiClient;
    @NotNull
    private final Context context;
    private boolean isInProgress;
    @Nullable
    private Session session;
    @NotNull
    private TimelineFragment timelineFragment;
    @NotNull
    private final String url = "http://api.cooey.co.in/ehealth/";
    @Nullable
    private User user;
    private DashboardViewPagerAdapter viewPagerAdapter;
    @NotNull
    private VitalBlueprintsRepository vitalBlueprintsRepository;
    @NotNull
    private VitalRepository vitalRepository;
    @Nullable
    private VitalsService vitalsService = ((VitalsService) new ServiceGenerator(this.url).create(VitalsService.class));

    public DashboardViewModel(@NotNull Context context, @NotNull TimelineFragment timelineFragment, @NotNull VitalRepository vitalRepository, @NotNull VitalBlueprintsRepository vitalBlueprintsRepository, @Nullable Session session, @Nullable User user) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(timelineFragment, "timelineFragment");
        Intrinsics.checkParameterIsNotNull(vitalRepository, "vitalRepository");
        Intrinsics.checkParameterIsNotNull(vitalBlueprintsRepository, "vitalBlueprintsRepository");
        this.context = context;
        this.timelineFragment = timelineFragment;
        this.vitalRepository = vitalRepository;
        this.vitalBlueprintsRepository = vitalBlueprintsRepository;
        this.session = session;
        this.user = user;
        Context context2 = this.context;
        Context context3 = this.context;
        if (context3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.app.AppCompatActivity");
        }
        FragmentManager supportFragmentManager = ((AppCompatActivity) context3).getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "(context as AppCompatAct…y).supportFragmentManager");
        TimelineFragment timelineFragment2 = this.timelineFragment;
        VitalBlueprintsRepository vitalBlueprintsRepository2 = this.vitalBlueprintsRepository;
        User user2 = this.user;
        Session session2 = this.session;
        if (session2 == null) {
            Intrinsics.throwNpe();
        }
        this.viewPagerAdapter = new DashboardViewPagerAdapter(context2, supportFragmentManager, timelineFragment2, vitalBlueprintsRepository2, user2, session2, this.vitalRepository, 0);
        this.apiClient = new ApiClient(this.context, "http://api.cooey.co.in/ehealth/");
        this.session = new PreferenceStore().getSession(this.context);
        DashboardViewPagerAdapter dashboardViewPagerAdapter = this.viewPagerAdapter;
        if (dashboardViewPagerAdapter == null) {
            Intrinsics.throwNpe();
        }
        dashboardViewPagerAdapter.setSession(this.session);
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @Nullable
    public final Session getSession() {
        return this.session;
    }

    @NotNull
    public final TimelineFragment getTimelineFragment() {
        return this.timelineFragment;
    }

    @Nullable
    public final User getUser() {
        return this.user;
    }

    @NotNull
    public final VitalBlueprintsRepository getVitalBlueprintsRepository() {
        return this.vitalBlueprintsRepository;
    }

    @NotNull
    public final VitalRepository getVitalRepository() {
        return this.vitalRepository;
    }

    public final void setSession(@Nullable Session <set-?>) {
        this.session = <set-?>;
    }

    public final void setTimelineFragment(@NotNull TimelineFragment <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.timelineFragment = <set-?>;
    }

    public final void setUser(@Nullable User <set-?>) {
        this.user = <set-?>;
    }

    public final void setVitalBlueprintsRepository(@NotNull VitalBlueprintsRepository <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.vitalBlueprintsRepository = <set-?>;
    }

    public final void setVitalRepository(@NotNull VitalRepository <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.vitalRepository = <set-?>;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    @Nullable
    public final VitalsService getVitalsService() {
        return this.vitalsService;
    }

    public final void setVitalsService(@Nullable VitalsService <set-?>) {
        this.vitalsService = <set-?>;
    }

    @Nullable
    public final ActionItemService getActionsItemService() {
        return this.actionsItemService;
    }

    public final void setActionsItemService(@Nullable ActionItemService <set-?>) {
        this.actionsItemService = <set-?>;
    }

    @Bindable
    public final boolean isInProgress() {
        return this.isInProgress;
    }

    public final void setInProgress(boolean inProgress) {
        this.isInProgress = inProgress;
        notifyPropertyChanged(17);
    }

    @Nullable
    public final List<Vital> getVitalsForUser() {
        try {
            Response vitalResponse;
            VitalsService vitalsService = this.vitalsService;
            if (vitalsService != null) {
                String id;
                Session session = this.session;
                if (session != null) {
                    id = session.getId();
                } else {
                    id = null;
                }
                if (id == null) {
                    Intrinsics.throwNpe();
                }
                User user = this.user;
                String id2 = user != null ? user.getId() : null;
                if (id2 == null) {
                    Intrinsics.throwNpe();
                }
                Call vitalsForUser$default = DefaultImpls.getVitalsForUser$default(vitalsService, id, id2, Integer.valueOf(0), Integer.valueOf(0), null, 16, null);
                if (vitalsForUser$default != null) {
                    vitalResponse = vitalsForUser$default.execute();
                    if (vitalResponse == null) {
                        return (List) vitalResponse.body();
                    }
                    return null;
                }
            }
            vitalResponse = null;
            if (vitalResponse == null) {
                return null;
            }
            return (List) vitalResponse.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public final List<Medicine> getMedicinesForUser() {
        try {
            String id;
            MedicinesService medicinesService = this.apiClient.getMedicinesService();
            Session session = this.session;
            if (session != null) {
                id = session.getId();
            } else {
                id = null;
            }
            if (id == null) {
                Intrinsics.throwNpe();
            }
            User user = this.user;
            if (user == null) {
                Intrinsics.throwNpe();
            }
            return (List) medicinesService.getMedicineReminderForPatient(id, user.getId()).execute().body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public final List<Activity> getActivitiesForUser() {
        try {
            String id;
            ArrayList activityList = new ArrayList();
            ActivitiesService activitiesService = this.apiClient.getActivitiesService();
            Session session = this.session;
            if (session != null) {
                id = session.getId();
            } else {
                id = null;
            }
            if (id == null) {
                Intrinsics.throwNpe();
            }
            User user = this.user;
            if (user == null) {
                Intrinsics.throwNpe();
            }
            Response response = activitiesService.getActivitiesOfUser(id, user.getId()).execute();
            if (response.body() != null) {
                Object body = response.body();
                if (body == null) {
                    Intrinsics.throwNpe();
                }
                if (((List) body).size() > 0) {
                    body = response.body();
                    if (body == null) {
                        Intrinsics.throwNpe();
                    }
                    for (ActivityResponse activityResponse : (List) body) {
                        Activity activity = activityResponse.getActivity();
                        activity.setPatientId((String) activityResponse.getUserId().get(0));
                        activityList.add(activity);
                    }
                }
            }
            return activityList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public final List<Action> getActionsCompletedForUser() {
        try {
            String id;
            ActionSummaryService actionSummaryService = this.apiClient.getActionSummaryService();
            Session session = this.session;
            if (session != null) {
                id = session.getId();
            } else {
                id = null;
            }
            if (id == null) {
                Intrinsics.throwNpe();
            }
            User user = this.user;
            if (user == null) {
                Intrinsics.throwNpe();
            }
            return (List) actionSummaryService.getActionCompleted(id, user.getId()).execute().body();
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    public final Careplan getCarePlanForUser() {
        Careplan careplan = null;
        Response response = null;
        try {
            CareplanService careplanService = this.apiClient.getCareplanService();
            Session session = this.session;
            String id = session != null ? session.getId() : null;
            if (id == null) {
                Intrinsics.throwNpe();
            }
            User user = this.user;
            if (user == null) {
                Intrinsics.throwNpe();
            }
            response = careplanService.getCareplan(id, user.getId()).execute();
            if (response == null) {
                Intrinsics.throwNpe();
            }
            if (response.body() != null && response.errorBody() == null) {
                careplan = (Careplan) response.body();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return careplan;
    }

    @Nullable
    public final List<Note> getAllNotesForUser() {
        try {
            NoteService noteService = this.apiClient.getNoteService();
            Session session = this.session;
            String id = session != null ? session.getId() : null;
            if (id == null) {
                Intrinsics.throwNpe();
            }
            User user = this.user;
            if (user == null) {
                Intrinsics.throwNpe();
            }
            Response response = noteService.getAllNotesForUser(id, user.getId()).execute();
            if (response.body() == null || response.errorBody() != null) {
                return new ArrayList();
            }
            return (List) response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    @Nullable
    @Bindable
    public final DashboardViewPagerAdapter getViewPagerAdapter() {
        return this.viewPagerAdapter;
    }

    public final void setViewPagerAdapter(@NotNull DashboardViewPagerAdapter dashboardViewPagerAdapter) {
        Intrinsics.checkParameterIsNotNull(dashboardViewPagerAdapter, "dashboardViewPagerAdapter");
        this.viewPagerAdapter = dashboardViewPagerAdapter;
        notifyPropertyChanged(54);
    }

    @Nullable
    public final List<Intervention> getInterventionsForUser(@NotNull String careplanId) {
        Intrinsics.checkParameterIsNotNull(careplanId, "careplanId");
        try {
            CareplanService careplanService = this.apiClient.getCareplanService();
            Session session = this.session;
            Response response = careplanService.getCareplanInterventions(session != null ? session.getId() : null, careplanId).execute();
            if (response.body() == null || response.errorBody() != null) {
                return new ArrayList();
            }
            return (List) response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    public final void getActionItemsForUser() {
        String str = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(6, -30);
            long thirtyDaysAgo = cal.getTimeInMillis();
            ActionItemService actionItemService = this.actionsItemService;
            if (actionItemService != null) {
                Session session = this.session;
                String id = session != null ? session.getId() : null;
                User user = this.user;
                if (user != null) {
                    str = user.getId();
                }
                Call actionItemsForPatients = actionItemService.getActionItemsForPatients(id, str, thirtyDaysAgo, 0);
                if (actionItemsForPatients != null) {
                    actionItemsForPatients.enqueue(new DashboardViewModel$getActionItemsForUser$1(this));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void syncData() {
        new Thread(new DashboardViewModel$syncData$runnable$1(this)).start();
    }

    private final TimelineItem getTimelineItemForVital(Vital vital) {
        return new TimelineItem(vital.getId(), TimelineItemType.INSTANCE.getVITAL(), vital.getTakenOn(), vital.getUserId(), vital.getVitalType());
    }

    public final void registerNotificationChannel() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Channel channel = new Channel();
        channel.setSource("ANDROID");
        Session session = this.session;
        channel.setUserId(session != null ? session.getUserId() : null);
        channel.setSourceId(token);
        channel.setType(ChannelType.NOTIFICATION);
        try {
            this.apiClient.getChannelsService().create(channel).enqueue(new DashboardViewModel$registerNotificationChannel$1());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
