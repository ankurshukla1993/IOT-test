package com.facebook.react.devsupport;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.facebook.react.C1275R;

public class DevSettingsActivity extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(C1275R.string.catalyst_settings_title);
        addPreferencesFromResource(C1275R.xml.preferences);
    }
}
