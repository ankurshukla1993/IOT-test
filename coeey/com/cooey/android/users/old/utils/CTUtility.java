package com.cooey.android.users.old.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

public class CTUtility {
    public static final String SHARED_PREF_NAME = "com.cooey.android.users.shared.pref";

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, 0).show();
    }

    public static void storeStringInSharedPref(Context context, String key, String value) {
        Editor editor = context.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringFromSharedPref(Context context, String key, String defaultValue) {
        return context.getSharedPreferences(SHARED_PREF_NAME, 0).getString(key, defaultValue);
    }

    public static String getAge(Date dateOfBirth) {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
        }
        int age = today.get(1) - birthDate.get(1);
        if (birthDate.get(6) - today.get(6) > 3 || birthDate.get(2) > today.get(2)) {
            age--;
        } else if (birthDate.get(2) == today.get(2) && birthDate.get(5) > today.get(5)) {
            age--;
        }
        return String.valueOf(age);
    }

    public static int getAgeInInteger(Date dateOfBirth) {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
        }
        int age = today.get(1) - birthDate.get(1);
        if (birthDate.get(6) - today.get(6) > 3 || birthDate.get(2) > today.get(2)) {
            return age - 1;
        }
        if (birthDate.get(2) != today.get(2) || birthDate.get(5) <= today.get(5)) {
            return age;
        }
        return age - 1;
    }

    public static float calculateBmi(float userHeight, float weight) {
        if (userHeight == 0.0f) {
            return 0.0f;
        }
        float height = (float) (((double) userHeight) * 0.01d);
        return Float.valueOf(String.format("%.2f", new Object[]{Float.valueOf(weight / (height * height))})).floatValue();
    }
}
