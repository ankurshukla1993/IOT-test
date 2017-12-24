package com.ihealth.communication.cloud;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import com.github.thunder413.datetimeutils.DateTimeFormat;
import com.google.common.net.HttpHeaders;
import com.ihealth.communication.cloud.p002a.C2041b;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class UserCheckSDK {
    public static final int UserAuthen_CombinedSuccess = 3;
    public static final int UserAuthen_InternetError = 8;
    public static final int UserAuthen_InvalidateUserInfo = 5;
    public static final int UserAuthen_LoginSuccess = 2;
    public static final int UserAuthen_RegisterSuccess = 1;
    public static final int UserAuthen_SDKInvalidateRight = 6;
    public static final int UserAuthen_TrySuccess = 4;
    public static final int UserAuthen_UserInvalidateRight = 7;
    public static int returncode;

    private int m351a(Context context) {
        String str = "";
        str = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences("noNetWorkTime", 0);
        String string = sharedPreferences.getString("Time", "");
        if (!"".equals(string)) {
            return (!m357a(string, m355a(System.currentTimeMillis())) || sharedPreferences.getBoolean("IsIdentifed", false)) ? 4 : 8;
        } else {
            Editor edit = sharedPreferences.edit();
            edit.putString("Time", m355a(System.currentTimeMillis()));
            edit.commit();
            return 4;
        }
    }

    private int m352a(Context context, String str, String str2, String str3, String[] strArr, String str4) {
        CommCloudSDK commCloudSDK = new CommCloudSDK(context);
        ReturnDataUser returnDataUser = new ReturnDataUser();
        try {
            ReturnDataUser UserCombine = commCloudSDK.UserCombine(str, str2, str3, str4);
            if (!"100".equals(UserCombine.getResultCode())) {
                return ("223".equals(UserCombine.getResultCode()) || "224".equals(UserCombine.getResultCode())) ? 5 : 8;
            } else {
                Object obj;
                for (CharSequence contains : strArr) {
                    if (UserCombine.getApiName().contains(contains)) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    return 7;
                }
                saveUserInfo(context, str3, UserCombine.getApiName(), null, UserCombine.getAccessToken(), UserCombine.getRefreshToken(), null, null, UserCombine.getId());
                m359b(context);
                return 3;
            }
        } catch (Exception e) {
            return 8;
        }
    }

    private int m353a(Context context, String[] strArr, String str, String str2, String str3, String str4) {
        return m352a(context, str, str2, str3, strArr, str4);
    }

    private Boolean m354a(String str) {
        return Boolean.valueOf(Pattern.compile("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$").matcher(str).matches());
    }

    private static String m355a(long j) {
        DateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1);
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return simpleDateFormat.format(instance.getTime());
    }

    private static String m356a(Context context, String str) {
        String string = context.getSharedPreferences(str + "userinfo", 0).getString(HttpHeaders.HOST, "");
        return "".equals(string) ? C2041b.f506b : string;
    }

    private boolean m357a(String str, String str2) {
        long time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1);
        try {
            time = (simpleDateFormat.parse(str2).getTime() - simpleDateFormat.parse(str).getTime()) / 86400000;
        } catch (ParseException e) {
            e.printStackTrace();
            time = 0;
        }
        return time > 10;
    }

    private int m358b(Context context, String[] strArr, String str, String str2, String str3, String str4) {
        CommCloudSDK commCloudSDK = new CommCloudSDK(context);
        ReturnDataUser returnDataUser = new ReturnDataUser();
        try {
            ReturnDataUser UserSign = commCloudSDK.UserSign(str, str2, str3, str4);
            if (!"100".equals(UserSign.getResultCode())) {
                return ("223".equals(UserSign.getResultCode()) || "224".equals(UserSign.getResultCode())) ? 5 : 8;
            } else {
                Object obj;
                for (CharSequence contains : strArr) {
                    if (UserSign.getApiName().contains(contains)) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    return 7;
                }
                saveUserInfo(context, str3, UserSign.getApiName(), null, UserSign.getAccessToken(), UserSign.getRefreshToken(), null, null, UserSign.getId());
                m359b(context);
                return 2;
            }
        } catch (Exception e) {
            return 8;
        }
    }

    private static void m359b(Context context) {
        Editor edit = context.getSharedPreferences("noNetWorkTime", 0).edit();
        edit.putString("Time", m355a(System.currentTimeMillis()));
        edit.apply();
    }

    public static int entry(Context context, String client_id, String client_secret, String username, String apiName_in, String host) {
        CommCloudSDK commCloudSDK = new CommCloudSDK(context);
        ReturnDataUser returnDataUser = new ReturnDataUser();
        try {
            ReturnDataUser UserRegister = commCloudSDK.UserRegister(client_id, client_secret, username, host);
            if (!"100".equals(UserRegister.getResultCode())) {
                return 8;
            }
            if (!UserRegister.getApiName().contains(apiName_in)) {
                return ("223".equals(UserRegister.getResultCode()) || "224".equals(UserRegister.getResultCode())) ? 5 : 7;
            } else {
                saveUserInfo(context, username, UserRegister.getApiName(), host, UserRegister.getAccessToken(), UserRegister.getRefreshToken(), null, null, UserRegister.getId());
                m359b(context);
                return 1;
            }
        } catch (Exception e) {
            return 8;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo[] allNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getAllNetworkInfo();
        if (allNetworkInfo == null) {
            return false;
        }
        for (NetworkInfo state : allNetworkInfo) {
            if (state.getState() == State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    public static void saveUserInfo(Context context, String username, String apiName, String host, String accessToken, String refreshToken, String client_id, String client_secret, int userID) {
        Editor edit = context.getSharedPreferences(username + "userinfo", 0).edit();
        if (username != null) {
            edit.putString("email", username);
        }
        if (apiName != null) {
            edit.putString("apiName", apiName);
        }
        if (host != null) {
            edit.putString(HttpHeaders.HOST, host);
        }
        if (accessToken != null) {
            edit.putString("accessToken", accessToken);
        }
        if (refreshToken != null) {
            edit.putString("refreshToken", refreshToken);
        }
        if (client_id != null) {
            edit.putString("client_id", client_id);
        }
        if (client_secret != null) {
            edit.putString("client_secret", client_secret);
        }
        if (!(userID == -1 || userID == 0)) {
            edit.putInt("user_ID", userID);
        }
        edit.apply();
    }

    public int CheckSDK(Context context, String[] apiName2, String client_id, String client_secret, String username) {
        CommCloudSDK commCloudSDK = new CommCloudSDK(context);
        if (!m354a(username).booleanValue() || username.length() > 129) {
            return 5;
        }
        if (!isNetworkAvailable(context)) {
            return m351a(context);
        }
        String a = m356a(context, username);
        String string = context.getSharedPreferences(username + "userinfo", 0).getString("apiName", "");
        ReturnDataUser returnDataUser;
        if (string.equals("")) {
            returnDataUser = new ReturnDataUser();
            try {
                returnDataUser = commCloudSDK.UserExistForThird(client_id, client_secret, username, a);
                String a2 = m356a(context, username);
                String resultCode = returnDataUser.getResultCode();
                if (!"100".equals(resultCode)) {
                    return ("223".equals(resultCode) || "224".equals(resultCode) || "225".equals(resultCode)) ? 5 : 8;
                } else {
                    int status = returnDataUser.getStatus();
                    saveUserInfo(context, username, null, null, null, null, client_id, client_secret, returnDataUser.getId());
                    if (status == 3) {
                        return userRegisterAndCheck(context, apiName2, client_id, client_secret, username);
                    }
                    if (status == 2) {
                        return m353a(context, apiName2, client_id, client_secret, username, a2);
                    }
                    return status == 1 ? m358b(context, apiName2, client_id, client_secret, username, a2) : 8;
                }
            } catch (Exception e) {
                return 8;
            }
        }
        Object obj = null;
        for (CharSequence contains : apiName2) {
            if (string.contains(contains)) {
                obj = 1;
                Editor edit = context.getSharedPreferences("ihealthdevicepermission", 0).edit();
                edit.putString(client_id, string);
                edit.apply();
                break;
            }
        }
        if (obj != null) {
            return 2;
        }
        returnDataUser = new ReturnDataUser();
        try {
            ReturnDataUser UserSign = commCloudSDK.UserSign(client_id, client_secret, username, a);
            if (!"100".equals(UserSign.getResultCode())) {
                return ("223".equals(UserSign.getResultCode()) || "224".equals(UserSign.getResultCode())) ? 5 : 8;
            } else {
                obj = null;
                for (CharSequence contains2 : apiName2) {
                    if (UserSign.getApiName().contains(contains2)) {
                        obj = 1;
                        break;
                    }
                }
                if (obj == null) {
                    return 7;
                }
                saveUserInfo(context, null, UserSign.getApiName(), null, UserSign.getAccessToken(), UserSign.getRefreshToken(), null, null, UserSign.getId());
                return 2;
            }
        } catch (Exception e2) {
            return 8;
        }
    }

    public int userRegisterAndCheck(Context context, String[] apiName2, String client_id, String client_secret, String username) {
        try {
            String ServiceHostListByCountry_get = new CommCloudCenter(context).ServiceHostListByCountry_get();
            return entry(context, client_id, client_secret, username, apiName2[0], ServiceHostListByCountry_get);
        } catch (Exception e) {
            return 8;
        }
    }
}
