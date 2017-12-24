package com.cooey.common.services;

import android.content.Context;
import com.cooey.common.vo.BooleanRealmListConverter;
import com.cooey.common.vo.RealmBoolean;
import com.cooey.common.vo.RealmString;
import com.cooey.common.vo.RealmStringListConverter;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.realm.RealmList;
import io.realm.RealmObject;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    private ActionItemService actionItemService;
    ActionSummaryService actionSummaryService;
    ActivitiesService activitiesService;
    private CareplanService careplanService;
    private ChannelsService channelsService;
    ContentService contentService;
    private final Context context;
    private final Retrofit devCareplanRetrofit;
    private final Retrofit devRetrofit;
    DeviceAlertService deviceAlertService;
    DietTemplatesService dietTemplatesService;
    GuardianService guardianService;
    MedicinesService medicinesService;
    private NoteService noteService;
    PartnerConfigService partnerConfigService;
    SessionsService sessionsService;
    UsersService usersService;
    VitalConfigService vitalConfigService;
    VitalsService vitalsService;

    class C08961 extends TypeToken<RealmList<RealmString>> {
        C08961() {
        }
    }

    class C08972 implements JsonDeserializer<Date> {
        C08972() {
        }

        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return new Date(json.getAsJsonPrimitive().getAsLong());
        }
    }

    class C08983 extends TypeToken<RealmList<RealmBoolean>> {
        C08983() {
        }
    }

    class C08994 implements ExclusionStrategy {
        C08994() {
        }

        public boolean shouldSkipField(FieldAttributes f) {
            return f.getDeclaringClass().equals(RealmObject.class);
        }

        public boolean shouldSkipClass(Class<?> cls) {
            return false;
        }
    }

    public ApiClient(Context context, String serverUrl) {
        this.context = context;
        Builder client = new Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(Level.BODY);
        client.addInterceptor(loggingInterceptor);
        client.readTimeout(1, TimeUnit.MINUTES);
        client.connectTimeout(1, TimeUnit.MINUTES);
        Gson gson = new GsonBuilder().setExclusionStrategies(new C08994()).registerTypeAdapter(new C08983().getType(), new BooleanRealmListConverter()).registerTypeAdapter(Date.class, new C08972()).registerTypeAdapter(new C08961().getType(), new RealmStringListConverter()).create();
        this.devRetrofit = new Retrofit.Builder().baseUrl(serverUrl).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create(gson)).client(client.build()).build();
        this.devCareplanRetrofit = new Retrofit.Builder().baseUrl(serverUrl).addConverterFactory(ScalarsConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create(gson)).client(client.build()).build();
    }

    public <T> T create(Class<T> service) {
        return this.devRetrofit.create(service);
    }

    public SessionsService getSessionsService() {
        if (this.sessionsService == null) {
            this.sessionsService = (SessionsService) this.devRetrofit.create(SessionsService.class);
        }
        return this.sessionsService;
    }

    public UsersService getUsersService() {
        if (this.usersService == null) {
            this.usersService = (UsersService) this.devRetrofit.create(UsersService.class);
        }
        return this.usersService;
    }

    public VitalsService getVitalsService() {
        if (this.vitalsService == null) {
            this.vitalsService = (VitalsService) this.devRetrofit.create(VitalsService.class);
        }
        return this.vitalsService;
    }

    public MedicinesService getMedicinesService() {
        if (this.medicinesService == null) {
            this.medicinesService = (MedicinesService) this.devRetrofit.create(MedicinesService.class);
        }
        return this.medicinesService;
    }

    public ActivitiesService getActivitiesService() {
        if (this.activitiesService == null) {
            this.activitiesService = (ActivitiesService) this.devRetrofit.create(ActivitiesService.class);
        }
        return this.activitiesService;
    }

    public DietTemplatesService getDietTemplatesService() {
        if (this.dietTemplatesService == null) {
            this.dietTemplatesService = (DietTemplatesService) this.devRetrofit.create(DietTemplatesService.class);
        }
        return this.dietTemplatesService;
    }

    public ChannelsService getChannelsService() {
        if (this.channelsService == null) {
            this.channelsService = (ChannelsService) this.devRetrofit.create(ChannelsService.class);
        }
        return this.channelsService;
    }

    public ActionItemService getActionItemService() {
        if (this.actionItemService == null) {
            this.actionItemService = (ActionItemService) this.devRetrofit.create(ActionItemService.class);
        }
        return this.actionItemService;
    }

    public CareplanService getCareplanService() {
        if (this.careplanService == null) {
            this.careplanService = (CareplanService) this.devRetrofit.create(CareplanService.class);
        }
        return this.careplanService;
    }

    public CareplanService getCareplanServiceWithObservable() {
        if (this.careplanService == null) {
            this.careplanService = (CareplanService) this.devCareplanRetrofit.create(CareplanService.class);
        }
        return this.careplanService;
    }

    public ActivitiesService getActivityServiceWithObservable() {
        if (this.activitiesService == null) {
            this.activitiesService = (ActivitiesService) this.devCareplanRetrofit.create(ActivitiesService.class);
        }
        return this.activitiesService;
    }

    public PartnerConfigService getPartnerConfigService() {
        if (this.partnerConfigService == null) {
            this.partnerConfigService = (PartnerConfigService) this.devRetrofit.create(PartnerConfigService.class);
        }
        return this.partnerConfigService;
    }

    public DeviceAlertService getDeviceAlertService() {
        if (this.deviceAlertService == null) {
            this.deviceAlertService = (DeviceAlertService) this.devRetrofit.create(DeviceAlertService.class);
        }
        return this.deviceAlertService;
    }

    public VitalConfigService getVitalConfigService() {
        if (this.vitalConfigService == null) {
            this.vitalConfigService = (VitalConfigService) this.devRetrofit.create(VitalConfigService.class);
        }
        return this.vitalConfigService;
    }

    public ActionSummaryService getActionSummaryService() {
        if (this.actionSummaryService == null) {
            this.actionSummaryService = (ActionSummaryService) this.devRetrofit.create(ActionSummaryService.class);
        }
        return this.actionSummaryService;
    }

    public ContentService getContentService() {
        if (this.contentService == null) {
            this.contentService = (ContentService) this.devRetrofit.create(ContentService.class);
        }
        return this.contentService;
    }

    public GuardianService getGuardianService() {
        if (this.contentService == null) {
            this.guardianService = (GuardianService) this.devRetrofit.create(GuardianService.class);
        }
        return this.guardianService;
    }

    public NoteService getNoteService() {
        if (this.noteService == null) {
            this.noteService = (NoteService) this.devRetrofit.create(NoteService.class);
        }
        return this.noteService;
    }
}
