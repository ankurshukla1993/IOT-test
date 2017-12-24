package com.cooey.android.users.old.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.cooey.common.config.VitalTemplatesConfigListItem;
import com.cooey.common.vo.SettingsConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class PartnerConfigPreferences {
    public static final String PARTNER_CONFIG_KEY = "partner_config_key";
    public static final String PARTNER_VITAL_KEY = "partner_vital_key";
    private Gson gson = new GsonBuilder().create();

    class C08041 extends TypeToken<List<VitalTemplatesConfigListItem>> {
        C08041() {
        }
    }

    public void setPartnerConfig(Context context, SettingsConfig settingsConfig) {
        Editor editor = context.getSharedPreferences(CTUtility.SHARED_PREF_NAME, 0).edit();
        editor.putString(PARTNER_CONFIG_KEY, this.gson.toJson((Object) settingsConfig));
        editor.apply();
    }

    public SettingsConfig getPartnerConfig(Context context) {
        return (SettingsConfig) this.gson.fromJson(context.getSharedPreferences(CTUtility.SHARED_PREF_NAME, 0).getString(PARTNER_CONFIG_KEY, null), SettingsConfig.class);
    }

    public void setVitalConfig(Context context, List<VitalTemplatesConfigListItem> vitalConfigResponses) {
        Editor editor = context.getSharedPreferences(CTUtility.SHARED_PREF_NAME, 0).edit();
        editor.putString(PARTNER_VITAL_KEY, this.gson.toJson((Object) vitalConfigResponses));
        editor.apply();
    }

    public List<VitalTemplatesConfigListItem> getVitalConfigResponse(Context context) {
        return (List) this.gson.fromJson(context.getSharedPreferences(CTUtility.SHARED_PREF_NAME, 0).getString(PARTNER_VITAL_KEY, null), new C08041().getType());
    }
}
