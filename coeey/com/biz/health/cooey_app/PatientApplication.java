package com.biz.health.cooey_app;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import com.biz.health.cooey_app.reminders.ReminderJobCreator;
import com.cooey.android.vitals.VitalsDatabase;
import com.cooey.android.vitals.dao.VitalBlueprintDao;
import com.cooey.android.vitals.dao.VitalDao;
import com.cooey.android.vitals.repositories.VitalBlueprintsRepository;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.android.vitals.services.VitalBlueprintService;
import com.cooey.android.vitals.services.VitalsService;
import com.cooey.common.CommonDatabase;
import com.cooey.common.dao.TimelineItemDao;
import com.cooey.common.generators.ServiceGenerator;
import com.cooey.common.vo.Session;
import com.evernote.android.job.JobManager;
import io.realm.Realm;
import io.realm.RealmConfiguration.Builder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/biz/health/cooey_app/PatientApplication;", "Landroid/app/Application;", "()V", "onCreate", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: PatientApplication.kt */
public final class PatientApplication extends Application {
    public static final Companion Companion = new Companion();
    @Nullable
    private static Context applicationContext = null;
    @Nullable
    private static CommonDatabase commonDatabase = null;
    @Nullable
    private static final String serverUrl = "http://api.cooey.co.in/ehealth/";
    @Nullable
    private static Session session;
    @Nullable
    private static VitalBlueprintsRepository vitalBlueprintRepository;
    @Nullable
    private static VitalsDatabase vitalsDatabase;
    @Nullable
    private static VitalRepository vitalsRepository;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u0010XD¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R(\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010!X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010&\u001a\u0004\u0018\u00010'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+¨\u0006,"}, d2 = {"Lcom/biz/health/cooey_app/PatientApplication$Companion;", "", "()V", "applicationContext", "Landroid/content/Context;", "getApplicationContext", "()Landroid/content/Context;", "setApplicationContext", "(Landroid/content/Context;)V", "commonDatabase", "Lcom/cooey/common/CommonDatabase;", "getCommonDatabase", "()Lcom/cooey/common/CommonDatabase;", "setCommonDatabase", "(Lcom/cooey/common/CommonDatabase;)V", "serverUrl", "", "getServerUrl", "()Ljava/lang/String;", "value", "Lcom/cooey/common/vo/Session;", "session", "getSession", "()Lcom/cooey/common/vo/Session;", "setSession", "(Lcom/cooey/common/vo/Session;)V", "vitalBlueprintRepository", "Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "getVitalBlueprintRepository", "()Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "setVitalBlueprintRepository", "(Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;)V", "vitalsDatabase", "Lcom/cooey/android/vitals/VitalsDatabase;", "getVitalsDatabase", "()Lcom/cooey/android/vitals/VitalsDatabase;", "setVitalsDatabase", "(Lcom/cooey/android/vitals/VitalsDatabase;)V", "vitalsRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "getVitalsRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalsRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "app_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: PatientApplication.kt */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final Context getApplicationContext() {
            return PatientApplication.applicationContext;
        }

        public final void setApplicationContext(@Nullable Context <set-?>) {
            PatientApplication.applicationContext = <set-?>;
        }

        @Nullable
        public final Session getSession() {
            return PatientApplication.session;
        }

        public final void setSession(@Nullable Session value) {
            VitalDao VitalDao;
            TimelineItemDao timelineItemDao = null;
            VitalsService vitalService = (VitalsService) new ServiceGenerator("http://api.cooey.co.in/ehealth/").create(VitalsService.class);
            com.cooey.android.vitals.repositories.VitalRepository.Companion companion = VitalRepository.Companion;
            com.cooey.android.vitals.repositories.VitalRepository.Companion companion2 = VitalRepository.Companion;
            companion.setVitalsService(vitalService);
            Companion companion3 = PatientApplication.Companion;
            Context applicationContext = PatientApplication.Companion.getApplicationContext();
            if (applicationContext == null) {
                Intrinsics.throwNpe();
            }
            VitalsDatabase vitalsDatabase = PatientApplication.Companion.getVitalsDatabase();
            if (vitalsDatabase != null) {
                VitalDao = vitalsDatabase.VitalDao();
            } else {
                VitalDao = null;
            }
            if (VitalDao == null) {
                Intrinsics.throwNpe();
            }
            CommonDatabase commonDatabase = PatientApplication.Companion.getCommonDatabase();
            if (commonDatabase != null) {
                timelineItemDao = commonDatabase.TimelineItemDao();
            }
            if (timelineItemDao == null) {
                Intrinsics.throwNpe();
            }
            companion3.setVitalsRepository(new VitalRepository(applicationContext, value, VitalDao, timelineItemDao));
        }

        @Nullable
        public final VitalsDatabase getVitalsDatabase() {
            return PatientApplication.vitalsDatabase;
        }

        public final void setVitalsDatabase(@Nullable VitalsDatabase <set-?>) {
            PatientApplication.vitalsDatabase = <set-?>;
        }

        @Nullable
        public final CommonDatabase getCommonDatabase() {
            return PatientApplication.commonDatabase;
        }

        public final void setCommonDatabase(@Nullable CommonDatabase <set-?>) {
            PatientApplication.commonDatabase = <set-?>;
        }

        @Nullable
        public final VitalRepository getVitalsRepository() {
            return PatientApplication.vitalsRepository;
        }

        public final void setVitalsRepository(@Nullable VitalRepository <set-?>) {
            PatientApplication.vitalsRepository = <set-?>;
        }

        @Nullable
        public final VitalBlueprintsRepository getVitalBlueprintRepository() {
            return PatientApplication.vitalBlueprintRepository;
        }

        public final void setVitalBlueprintRepository(@Nullable VitalBlueprintsRepository <set-?>) {
            PatientApplication.vitalBlueprintRepository = <set-?>;
        }

        @Nullable
        public final String getServerUrl() {
            return PatientApplication.serverUrl;
        }
    }

    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm.setDefaultConfiguration(new Builder().deleteRealmIfMigrationNeeded().build());
        JobManager jobManager = JobManager.create(this);
        Companion.setApplicationContext(this);
        jobManager.addJobCreator(new ReminderJobCreator(getApplicationContext()));
        Companion.setVitalsDatabase((VitalsDatabase) Room.databaseBuilder(this, VitalsDatabase.class, "vitals.db").allowMainThreadQueries().fallbackToDestructiveMigration().build());
        Companion.setCommonDatabase((CommonDatabase) Room.databaseBuilder(this, CommonDatabase.class, "common.db").allowMainThreadQueries().fallbackToDestructiveMigration().build());
        VitalBlueprintService vitalBlueprintService = (VitalBlueprintService) new ServiceGenerator("http://api.cooey.co.in/ehealth/").create(VitalBlueprintService.class);
        Companion companion = Companion;
        Intrinsics.checkExpressionValueIsNotNull(vitalBlueprintService, "vitalBlueprintService");
        VitalsDatabase vitalsDatabase = Companion.getVitalsDatabase();
        VitalBlueprintDao VitalBlueprintDao = vitalsDatabase != null ? vitalsDatabase.VitalBlueprintDao() : null;
        if (VitalBlueprintDao == null) {
            Intrinsics.throwNpe();
        }
        companion.setVitalBlueprintRepository(new VitalBlueprintsRepository(vitalBlueprintService, VitalBlueprintDao));
    }
}
