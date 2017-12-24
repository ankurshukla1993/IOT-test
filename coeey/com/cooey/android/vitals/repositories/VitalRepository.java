package com.cooey.android.vitals.repositories;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import com.cooey.android.vitals.Vital;
import com.cooey.android.vitals.dao.VitalDao;
import com.cooey.android.vitals.services.VitalsService;
import com.cooey.android.vitals.services.VitalsService.DefaultImpls;
import com.cooey.common.dao.TimelineItemDao;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.timeline.TimelineItem;
import com.cooey.common.vo.timeline.TimelineItemType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 -2\u00020\u0001:\u0001-B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fJ\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001dH\u0002J\"\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0%0\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fJ\u0016\u0010&\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c2\u0006\u0010 \u001a\u00020\u001fJ\u000e\u0010'\u001a\u00020(2\u0006\u0010#\u001a\u00020\u001dJ\u0014\u0010)\u001a\u00020(2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001d0%J\u000e\u0010+\u001a\u00020(2\u0006\u0010 \u001a\u00020\u001fJ\u000e\u0010,\u001a\u00020(2\u0006\u0010#\u001a\u00020\u001dR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006."}, d2 = {"Lcom/cooey/android/vitals/repositories/VitalRepository;", "", "context", "Landroid/content/Context;", "session", "Lcom/cooey/common/vo/Session;", "vitalDao", "Lcom/cooey/android/vitals/dao/VitalDao;", "timelineItemDao", "Lcom/cooey/common/dao/TimelineItemDao;", "(Landroid/content/Context;Lcom/cooey/common/vo/Session;Lcom/cooey/android/vitals/dao/VitalDao;Lcom/cooey/common/dao/TimelineItemDao;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getSession", "()Lcom/cooey/common/vo/Session;", "setSession", "(Lcom/cooey/common/vo/Session;)V", "getTimelineItemDao", "()Lcom/cooey/common/dao/TimelineItemDao;", "setTimelineItemDao", "(Lcom/cooey/common/dao/TimelineItemDao;)V", "getVitalDao", "()Lcom/cooey/android/vitals/dao/VitalDao;", "setVitalDao", "(Lcom/cooey/android/vitals/dao/VitalDao;)V", "getLatestVitalForType", "Landroid/arch/lifecycle/LiveData;", "Lcom/cooey/android/vitals/Vital;", "type", "", "userId", "getTimelineItemForVital", "Lcom/cooey/common/vo/timeline/TimelineItem;", "vital", "getVitalsForType", "", "getVitalsForUser", "insertVital", "", "save", "vitals", "syncVitals", "updateVital", "Companion", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalRepository.kt */
public final class VitalRepository {
    public static final Companion Companion = new Companion();
    @Nullable
    private static VitalsService vitalsService;
    @NotNull
    private Context context;
    @Nullable
    private Session session;
    @NotNull
    private TimelineItemDao timelineItemDao;
    @NotNull
    private VitalDao vitalDao;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/cooey/android/vitals/repositories/VitalRepository$Companion;", "", "()V", "vitalsService", "Lcom/cooey/android/vitals/services/VitalsService;", "getVitalsService", "()Lcom/cooey/android/vitals/services/VitalsService;", "setVitalsService", "(Lcom/cooey/android/vitals/services/VitalsService;)V", "vitals_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: VitalRepository.kt */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final VitalsService getVitalsService() {
            return VitalRepository.vitalsService;
        }

        public final void setVitalsService(@Nullable VitalsService <set-?>) {
            VitalRepository.vitalsService = <set-?>;
        }
    }

    public final void save(@org.jetbrains.annotations.NotNull java.util.List<com.cooey.android.vitals.Vital> r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.cooey.android.vitals.repositories.VitalRepository.save(java.util.List):void
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
        r5 = "vitals";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r5);
        r3 = new java.util.ArrayList;
        r3.<init>();
        r3 = (java.util.List) r3;
        r0 = r8;
        r0 = (java.lang.Iterable) r0;
        r5 = r0.iterator();
        r6 = r5.hasNext();
        if (r6 == 0) goto L_0x002b;
    L_0x001a:
        r1 = r5.next();
        r4 = r1;
        r4 = (com.cooey.android.vitals.Vital) r4;
        r2 = r7.getTimelineItemForVital(r4);
        r3.add(r2);
        goto L_0x0014;
        r5 = r7.vitalDao;
        r5.insert(r8);
        r5 = r7.timelineItemDao;
        r5.insert(r3);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cooey.android.vitals.repositories.VitalRepository.save(java.util.List):void");
    }

    public VitalRepository(@NotNull Context context, @Nullable Session session, @NotNull VitalDao vitalDao, @NotNull TimelineItemDao timelineItemDao) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(vitalDao, "vitalDao");
        Intrinsics.checkParameterIsNotNull(timelineItemDao, "timelineItemDao");
        this.context = context;
        this.session = session;
        this.vitalDao = vitalDao;
        this.timelineItemDao = timelineItemDao;
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
    public final TimelineItemDao getTimelineItemDao() {
        return this.timelineItemDao;
    }

    @NotNull
    public final VitalDao getVitalDao() {
        return this.vitalDao;
    }

    public final void setContext(@NotNull Context <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.context = <set-?>;
    }

    public final void setSession(@Nullable Session <set-?>) {
        this.session = <set-?>;
    }

    public final void setTimelineItemDao(@NotNull TimelineItemDao <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.timelineItemDao = <set-?>;
    }

    public final void setVitalDao(@NotNull VitalDao <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.vitalDao = <set-?>;
    }

    public final void insertVital(@NotNull Vital vital) {
        Intrinsics.checkParameterIsNotNull(vital, "vital");
        VitalsService vitalsService = Companion.getVitalsService();
        if (vitalsService != null) {
            Session session = this.session;
            String id = session != null ? session.getId() : null;
            if (id == null) {
                Intrinsics.throwNpe();
            }
            Call addVitalForUser = vitalsService.addVitalForUser(id, vital);
            if (addVitalForUser != null) {
                addVitalForUser.enqueue(new VitalRepository$insertVital$1());
            }
        }
        TimelineItem timelineItem = getTimelineItemForVital(vital);
        this.vitalDao.insert(vital);
        this.timelineItemDao.insert(timelineItem);
    }

    private final TimelineItem getTimelineItemForVital(Vital vital) {
        return new TimelineItem(vital.getId(), TimelineItemType.INSTANCE.getVITAL(), vital.getTakenOn(), vital.getUserId(), vital.getVitalType());
    }

    public final void updateVital(@NotNull Vital vital) {
        Intrinsics.checkParameterIsNotNull(vital, "vital");
        this.vitalDao.update(vital);
    }

    @Nullable
    public final LiveData<Vital> getVitalsForUser(@NotNull String userId) {
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        return this.vitalDao.getVitalsForPatient(userId);
    }

    @NotNull
    public final LiveData<Vital> getLatestVitalForType(@NotNull String type, @NotNull String userId) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        return this.vitalDao.getLatestVitalForTypeByTimestampDesc(type, userId);
    }

    public final void syncVitals(@NotNull String userId) {
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        VitalsService vitalsService = Companion.getVitalsService();
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
            Call vitalsForUser$default = DefaultImpls.getVitalsForUser$default(vitalsService, id, userId, null, null, null, 28, null);
            if (vitalsForUser$default != null) {
                vitalsForUser$default.enqueue(new VitalRepository$syncVitals$1(this));
            }
        }
    }

    @NotNull
    public final LiveData<List<Vital>> getVitalsForType(@NotNull String type, @NotNull String userId) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        return this.vitalDao.getVitalsForTypeByTimestampDesc(type, userId);
    }
}
