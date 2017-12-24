package com.ihealth.communication.utils;

import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import humanize.util.Constants;

public class Hs5DataUtil {
    public static String getDateStr(int[] data) {
        int i = data[0] + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE;
        int i2 = data[1];
        int i3 = data[2];
        int i4 = data[3];
        int i5 = data[4];
        int i6 = data[5];
        String valueOf = String.valueOf(i);
        String str = "";
        str = i2 < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + String.valueOf(i2) : String.valueOf(i2);
        String str2 = "";
        str2 = i3 < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + String.valueOf(i3) : String.valueOf(i3);
        String str3 = "";
        str3 = i4 < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + String.valueOf(i4) : String.valueOf(i4);
        String str4 = "";
        str4 = i5 < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + String.valueOf(i5) : String.valueOf(i5);
        String str5 = "";
        return valueOf + "-" + str + "-" + str2 + Constants.SPACE + str3 + ":" + str4 + ":" + (i6 < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + String.valueOf(i6) : String.valueOf(i6));
    }

    public static long getTS() {
        return System.currentTimeMillis() / 1000;
    }

    public static int[] parseData(byte[] returnData) {
        int[] iArr = new int[]{returnData[0] & 255, (returnData[4] & 240) >> 4, ((((returnData[3] & 128) >> 7) * 16) + (((returnData[2] & 128) >> 7) * 8)) + ((returnData[1] & 224) >> 5), returnData[1] & 31, returnData[2] & 127, returnData[3] & 127, ((returnData[4] & 15) * 256) + (returnData[5] & 255), ((returnData[6] & 255) * 256) + (returnData[7] & 255), ((returnData[8] & 255) * 256) + (returnData[9] & 255), ((returnData[10] & 255) * 256) + (returnData[11] & 255), returnData[12] & 255, returnData[13] & 255, ((returnData[14] & 255) * 256) + (returnData[15] & 255)};
        Log.m1214v("HS5Wifi", "Hs5DataUtil----Time====" + (returnData[0] & 255) + "-" + r1 + "-" + r2 + Constants.SPACE + r3 + ":" + r4 + ":" + r5);
        Log.m1214v("HS5Wifi", "Hs5DataUtil----Composition====" + r6 + "-" + r7 + "-" + r8 + Constants.SPACE + r9 + ":" + r10 + ":" + r11 + ":" + r12);
        return iArr;
    }
}
