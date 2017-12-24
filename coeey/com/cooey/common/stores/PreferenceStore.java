package com.cooey.common.stores;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.cooey.common.config.VitalTemplatesConfigListItem;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.SettingsConfig;
import com.cooey.common.vo.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class PreferenceStore {
    private static final String CARETAKER_FILED = "CARETAKER";
    private static final String CARETAKER_PREFERENCE = "CARETAKER_PREFERENCE";
    private static final String GUARDIAN_FIELD = "GUARDIAN";
    private static final String GUARDIAN_PREFERENCE = "GUARDIAN_PREFERENCE";
    public static final String PARTNER_CONFIG_ID = "PARTNER_CONFIG_ID";
    public static final String PARTNER_FIELD = "PARTNER";
    public static final String PARTNER_ID = "PARTNER_PREFERENCES";
    public static final String PARTNER_VITAL_KEY = "PARTNER_VITAL_KEY";
    private static final String SESSION_FIELD = "SESSION";
    private static final String SESSION_STORE_ID = "SESSION_PREFERENCES";
    private static final String STORE_ID = "PREFERENCES";
    private static final String USER_FIELD = "USER";
    private Gson gson = new GsonBuilder().create();

    class C09001 extends TypeToken<List<VitalTemplatesConfigListItem>> {
        C09001() {
        }
    }

    public User getUser(Context context) {
        return (User) this.gson.fromJson(context.getSharedPreferences(STORE_ID, 0).getString(USER_FIELD, null), User.class);
    }

    public void setUser(Context context, User user) {
        Editor editor = context.getSharedPreferences(STORE_ID, 0).edit();
        editor.putString(USER_FIELD, this.gson.toJson((Object) user));
        editor.apply();
    }

    public void setSession(Context context, Session session) {
        Editor editor = context.getSharedPreferences(SESSION_STORE_ID, 0).edit();
        editor.putString(SESSION_FIELD, this.gson.toJson((Object) session));
        editor.apply();
    }

    public Session getSession(Context context) {
        return (Session) this.gson.fromJson(context.getSharedPreferences(SESSION_STORE_ID, 0).getString(SESSION_FIELD, null), Session.class);
    }

    public static void clearSharedPreference(Context context) {
        context.getSharedPreferences(STORE_ID, 0).edit().clear().apply();
        context.getSharedPreferences(SESSION_STORE_ID, 0).edit().clear().apply();
        context.getSharedPreferences(PARTNER_ID, 0).edit().clear().apply();
    }

    public void setPartnerConfig(Context config, SettingsConfig settingsConfig) {
        Editor editor = config.getSharedPreferences(PARTNER_ID, 0).edit();
        editor.putString(PARTNER_FIELD, this.gson.toJson((Object) settingsConfig));
        editor.apply();
    }

    public SettingsConfig getPartnerConfig(Context context) {
        return (SettingsConfig) this.gson.fromJson(context.getSharedPreferences(PARTNER_ID, 0).getString(PARTNER_FIELD, null), SettingsConfig.class);
    }

    public User getUsers(Context context, String userId) {
        return (User) this.gson.fromJson(context.getSharedPreferences(STORE_ID, 0).getString(USER_FIELD, null), User.class);
    }

    public void setVitalConfig(Context context, List<VitalTemplatesConfigListItem> vitalConfigResponses) {
        Editor editor = context.getSharedPreferences(PARTNER_CONFIG_ID, 0).edit();
        editor.putString(PARTNER_VITAL_KEY, this.gson.toJson((Object) vitalConfigResponses));
        editor.apply();
    }

    public List<VitalTemplatesConfigListItem> getVitalConfigResponse(Context context) {
        return (List) this.gson.fromJson(context.getSharedPreferences(PARTNER_CONFIG_ID, 0).getString(PARTNER_VITAL_KEY, null), new C09001().getType());
    }

    public User getGuardian(Context context) {
        return (User) this.gson.fromJson(context.getSharedPreferences(GUARDIAN_PREFERENCE, 0).getString(GUARDIAN_FIELD, null), User.class);
    }

    public void setGuardian(Context context, User user) {
        Editor editor = context.getSharedPreferences(GUARDIAN_PREFERENCE, 0).edit();
        editor.putString(GUARDIAN_FIELD, this.gson.toJson((Object) user));
        editor.apply();
    }

    public User getCareTaker(Context context) {
        return (User) this.gson.fromJson(context.getSharedPreferences(CARETAKER_PREFERENCE, 0).getString(CARETAKER_FILED, null), User.class);
    }

    public void setCareTaker(Context context, User user) {
        Editor editor = context.getSharedPreferences(CARETAKER_PREFERENCE, 0).edit();
        editor.putString(CARETAKER_FILED, this.gson.toJson((Object) user));
        editor.apply();
    }
}
