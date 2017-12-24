package com.facebook;

import android.content.SharedPreferences;
import com.facebook.internal.Validate;
import com.google.android.gms.common.Scopes;
import org.json.JSONException;
import org.json.JSONObject;

final class ProfileCache {
    static final String CACHED_PROFILE_KEY = "com.facebook.ProfileManager.CachedProfile";
    static final String SHARED_PREFERENCES_NAME = "com.facebook.AccessTokenManager.SharedPreferences";
    private final SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences(SHARED_PREFERENCES_NAME, 0);

    ProfileCache() {
    }

    Profile load() {
        String jsonString = this.sharedPreferences.getString(CACHED_PROFILE_KEY, null);
        if (jsonString != null) {
            try {
                return new Profile(new JSONObject(jsonString));
            } catch (JSONException e) {
            }
        }
        return null;
    }

    void save(Profile profile) {
        Validate.notNull(profile, Scopes.PROFILE);
        JSONObject jsonObject = profile.toJSONObject();
        if (jsonObject != null) {
            this.sharedPreferences.edit().putString(CACHED_PROFILE_KEY, jsonObject.toString()).apply();
        }
    }

    void clear() {
        this.sharedPreferences.edit().remove(CACHED_PROFILE_KEY).apply();
    }
}
