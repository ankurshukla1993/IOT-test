package com.biz.health.cooey_app;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity.Header;
import android.preference.PreferenceFragment;
import android.preference.RingtonePreference;
import android.preference.SwitchPreference;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.MenuItem;
import com.cooey.android.users.old.activities.ChangePasswordActivity;
import com.cooey.common.services.ApiClient;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.User;
import com.google.gson.GsonBuilder;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreferencesActivity extends AppCompatPreferenceActivity {
    static PreferenceStore preferenceStore = new PreferenceStore();
    private static OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new C06431();

    static class C06431 implements OnPreferenceChangeListener {

        class C06421 implements Callback<User> {
            C06421() {
            }

            public void onResponse(Call<User> call, Response<User> response) {
                String data = "";
            }

            public void onFailure(Call<User> call, Throwable t) {
                String data = "";
            }
        }

        C06431() {
        }

        public boolean onPreferenceChange(Preference preference, Object value) {
            CharSequence charSequence = null;
            String stringValue = value.toString();
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);
                if (index >= 0) {
                    charSequence = listPreference.getEntries()[index];
                }
                preference.setSummary(charSequence);
            } else if (!(preference instanceof RingtonePreference)) {
                if ((preference instanceof SwitchPreference) && preference.getKey().contentEquals("notifications_new_message")) {
                    User user = PreferencesActivity.preferenceStore.getUser(preference.getContext());
                    if (!(user == null || user.getUserSettings() == null)) {
                        user.getUserSettings().setVitalNotificationEnabled(((SwitchPreference) preference).isEnabled());
                        PreferencesActivity.preferenceStore.setUser(preference.getContext(), user);
                        String json = new GsonBuilder().create().toJson(user);
                        new ApiClient(preference.getContext(), "http://api.cooey.co.in/ehealth/").getUsersService().update(user).enqueue(new C06421());
                    }
                }
                preference.setSummary(stringValue);
            } else if (TextUtils.isEmpty(stringValue)) {
                preference.setSummary(C0644R.string.pref_ringtone_silent);
            } else {
                Ringtone ringtone = RingtoneManager.getRingtone(preference.getContext(), Uri.parse(stringValue));
                if (ringtone == null) {
                    preference.setSummary(null);
                } else {
                    preference.setSummary(ringtone.getTitle(preference.getContext()));
                }
            }
            return true;
        }
    }

    @TargetApi(11)
    public static class DataSyncPreferenceFragment extends PreferenceFragment {
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(C0644R.xml.pref_data_sync);
            setHasOptionsMenu(true);
            PreferencesActivity.bindPreferenceSummaryToValue(findPreference("sync_frequency"));
        }
    }

    @TargetApi(11)
    public static class GeneralPreferenceFragment extends PreferenceFragment {
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(C0644R.xml.pref_general);
            setHasOptionsMenu(true);
            PreferencesActivity.bindPreferenceSummaryToValue(findPreference("example_text"));
            PreferencesActivity.bindPreferenceSummaryToValue(findPreference("example_list"));
        }
    }

    @TargetApi(11)
    public static class NotificationPreferenceFragment extends PreferenceFragment {
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(C0644R.xml.pref_notification);
            setHasOptionsMenu(true);
            PreferencesActivity.bindPreferenceSummaryToValue(findPreference("notifications_new_message"));
        }
    }

    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 4;
    }

    private static void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);
        User user = new PreferenceStore().getUser(preference.getContext());
        if ((preference instanceof SwitchPreference) && preference.getKey().contentEquals("notifications_new_message") && user != null && user.getUserSettings() != null) {
            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, Boolean.valueOf(user.getUserSettings().isVitalNotificationEnabled()));
        }
    }

    public void onHeaderClick(Header header, int position) {
        super.onHeaderClick(header, position);
        if (position == 1) {
            Intent intent = new Intent(this, ChangePasswordActivity.class);
            String token = preferenceStore.getSession(this).getId();
            User user = preferenceStore.getUser(this);
            intent.putExtra("SessionId", token);
            intent.putExtra("USER", user);
            intent.putExtra("serverurl", "http://api.cooey.co.in/ehealth/");
            startActivity(intent);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }

    @TargetApi(11)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(C0644R.xml.pref_headers, target);
    }

    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName) || GeneralPreferenceFragment.class.getName().equals(fragmentName) || NotificationPreferenceFragment.class.getName().equals(fragmentName) || DataSyncPreferenceFragment.class.getName().equals(fragmentName);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                finish();
                onBackPressed();
                break;
        }
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
