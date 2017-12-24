package com.ihealth.communication.ins;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.common.base.Ascii;
import com.google.common.net.HttpHeaders;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.base.protocol.BleCommProtocol;
import com.ihealth.communication.cloud.data.AM_CommCloud;
import com.ihealth.communication.cloud.data.AM_InAuthor;
import com.ihealth.communication.cloud.p002a.C2041b;
import com.ihealth.communication.cloud.p002a.C2051l;
import com.ihealth.communication.control.AmProfile;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.DataThreadPoolManager;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import com.ihealth.communication.utils.MD5;
import com.ihealth.communication.utils.PublicMethod;
import humanize.util.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.joda.time.DateTimeConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AaInsSet extends IdentifyIns implements NewDataCallback, GetBaseCommProtocolCallback {
    private static String f1635a = "110";
    private byte f1636A;
    private int f1637B;
    private int f1638C;
    private int f1639D;
    private byte f1640E;
    private byte f1641F;
    private byte[] f1642G;
    private byte[] f1643H;
    private byte[] f1644I;
    private int f1645J;
    private byte f1646K;
    private byte f1647L;
    private boolean f1648M = false;
    private int[] f1649N;
    private int f1650O = 0;
    private byte[] f1651P;
    private byte[] f1652Q;
    private List f1653R;
    private byte[] f1654S;
    private List f1655T;
    private byte[] f1656U;
    private int f1657V = 0;
    private JSONArray f1658W;
    private boolean f1659X = false;
    private final BaseComm f1660b;
    private Context f1661c;
    private String f1662l;
    private String f1663m;
    private BleCommProtocol f1664n;
    private byte f1665o = (byte) -86;
    private InsCallback f1666p;
    private BaseCommCallback f1667q;
    private String f1668r;
    private int f1669s;
    private int f1670t = 134;
    private int f1671u = 138;
    private int f1672v = 139;
    private AM_CommCloud f1673w;
    private String f1674x = null;
    private String f1675y = null;
    private byte f1676z;

    class C21034 implements Runnable {
        final /* synthetic */ AaInsSet f1628a;

        C21034(AaInsSet this$0) {
            this.f1628a = this$0;
        }

        public void run() {
            try {
                if (this.f1628a.f1673w.ambinding(this.f1628a.f1668r, this.f1628a.f1662l, this.f1628a.f1674x, this.f1628a.f1675y) == 100) {
                    this.f1628a.f1666p.onNotifyWithAction(this.f1628a.f1662l, this.f1628a.f1663m, AmProfile.ACTION_CLOUD_BINDING_AM_SUCCESS, null);
                } else {
                    this.f1628a.f1666p.onNotifyWithAction(this.f1628a.f1662l, this.f1628a.f1663m, AmProfile.ACTION_CLOUD_BINDING_AM_FAIL, null);
                }
            } catch (Exception e) {
                this.f1628a.f1666p.onNotifyWithAction(this.f1628a.f1662l, this.f1628a.f1663m, AmProfile.ACTION_CLOUD_BINDING_AM_FAIL, null);
                e.printStackTrace();
            }
        }
    }

    class C21056 implements Runnable {
        final /* synthetic */ AaInsSet f1631a;

        C21056(AaInsSet this$0) {
            this.f1631a = this$0;
        }

        public void run() {
            try {
                this.f1631a.f1673w.amunbinding(this.f1631a.f1668r, this.f1631a.f1662l, this.f1631a.f1674x, this.f1631a.f1675y);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    enum Command {
        Unknown(0),
        Verification_Feedback(251),
        Verification_Success(253),
        Verification_Failed(254),
        Reset_Success(161),
        GetUserId_Success(162),
        SetUserId_Success(163),
        SyncTime_Success(164),
        SetUserInfo_Success(165),
        GetUserInfo_Success(182),
        GetAlarmNum_Success(166),
        GetAlarmInfo_Success(167),
        SetAlarmInfo_Success(DateTimeConstants.HOURS_PER_WEEK),
        DeleteAlarm_Success(169),
        GetReminder_Success(179),
        SetReminder_Success(170),
        SetMode_Success(181),
        SyncActivityData_Start(171),
        SyncActivityData_Data(173),
        SyncActivityData_Finish(174),
        SyncSleepData_Start(175),
        SyncSleepData_Data(177),
        SyncSleepData_Finish(178),
        GetDeviceInfo_Success(180),
        SyncStageData_Start(11),
        SyncStageData_Data(12),
        SyncStageData_Finish(13),
        SyncRealTimeData_Success(191),
        SetBMR_Success(183),
        GetSwimParameter_Success(5),
        SetSwimParameter_Success(6),
        SendRandomNumber_Success(9),
        GetPicture_Success(16),
        SetPicture_Success(17),
        GetHourMode_Success(1),
        SetHourMode_Success(2);
        
        int f1634a;

        private Command(int what) {
            this.f1634a = what;
        }

        static Command m840a(int i) {
            for (Command command : values()) {
                if (command.f1634a == i) {
                    return command;
                }
            }
            return Unknown;
        }

        public String toString() {
            return String.format("%s(0x%02X)", new Object[]{name(), Integer.valueOf(this.f1634a)});
        }
    }

    public AaInsSet(BaseComm com, Context context, String mac, String type, String userName, BaseCommCallback BaseCommCallback, InsCallback insCallback) {
        m846a("AaInsSet_Constructor", mac, type, userName);
        this.f1661c = context;
        this.f1662l = mac;
        this.f1663m = type;
        this.f1668r = userName;
        this.f1667q = BaseCommCallback;
        this.f1666p = insCallback;
        this.f1660b = com;
        this.f1664n = new BleCommProtocol(context, com, this.f1662l, this.f1665o, this);
        this.f1653R = new ArrayList();
        this.f1655T = new ArrayList();
        this.f1669s = Integer.parseInt(iHealthDevicesManager.getInstance().getIdps(this.f1662l).getAccessoryFirmwareVersion().replace(".", ""));
        Log.d("AaInsSet", "currentFirmwareVersion = " + this.f1669s);
        new AM_InAuthor().initAuthor(context, userName);
        this.f1673w = new AM_CommCloud(context);
        m867d();
        m759a(insCallback, mac, type, com);
    }

    private JSONArray m842a(List list) {
        int i;
        JSONArray jSONArray = new JSONArray();
        if (this.f1663m.equals(iHealthDevicesManager.TYPE_AM3S) && this.f1669s >= Integer.parseInt(f1635a)) {
            this.f1659X = true;
            i = 8;
        } else if (this.f1663m.equals(iHealthDevicesManager.TYPE_AM4)) {
            this.f1659X = true;
            i = 8;
        } else {
            this.f1659X = false;
            i = 6;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            byte[] bArr = (byte[]) list.get(i2);
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray2 = new JSONArray();
            try {
                int i3 = (bArr[0] & 255) + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE;
                int i4 = bArr[1] & 255;
                int i5 = bArr[2] & 255;
                int i6 = bArr[3] & 255;
                for (int i7 = 6; i7 < bArr.length; i7 += i) {
                    JSONObject jSONObject2 = new JSONObject();
                    int i8 = bArr[i7] & 255;
                    int i9 = bArr[i7 + 1] & 255;
                    String format = String.format("%d-%02d-%02d %02d:%02d:00", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i8), Integer.valueOf(i9)});
                    jSONObject2.put("time", format);
                    jSONObject2.put(AmProfile.SYNC_ACTIVITY_DATA_STEP_LENGTH_AM, i6);
                    if (this.f1659X) {
                        i8 = ((((((bArr[i7 + 2] & 255) * 256) * 256) * 256) + (((bArr[i7 + 3] & 255) * 256) * 256)) + ((bArr[i7 + 4] & 255) * 256)) + (bArr[i7 + 5] & 255);
                        jSONObject2.put("step", i8);
                        jSONObject2.put("calorie", ((bArr[i7 + 6] & 255) * 256) + (bArr[i7 + 7] & 255));
                    } else {
                        i8 = ((bArr[i7 + 2] & 255) * 256) + (bArr[i7 + 3] & 255);
                        jSONObject2.put("step", i8);
                        jSONObject2.put("calorie", ((bArr[i7 + 4] & 255) * 256) + (bArr[i7 + 5] & 255));
                    }
                    JSONObject jSONObject3 = jSONObject2;
                    String str = "dataID";
                    jSONObject3.put(str, MD5.md5String(PublicMethod.getAMDataID(this.f1662l, i8 + "", PublicMethod.String2TS(format))));
                    jSONArray2.put(jSONObject2);
                }
                jSONObject.putOpt(AmProfile.SYNC_ACTIVITY_EACH_DATA_AM, jSONArray2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    private void m843a() {
        byte[] bArr;
        if (this.f1642G.length != 4) {
            bArr = new byte[15];
            bArr[9] = this.f1642G[0];
            bArr[10] = this.f1642G[1];
            bArr[11] = this.f1643H[0];
            bArr[12] = this.f1643H[1];
            bArr[13] = this.f1644I[0];
            bArr[14] = this.f1644I[1];
        } else if (this.f1663m.equals(iHealthDevicesManager.TYPE_AM3) || this.f1663m.equals(iHealthDevicesManager.TYPE_AM3S) || (this.f1663m.equals(iHealthDevicesManager.TYPE_AM4) && this.f1669s < this.f1671u)) {
            bArr = new byte[21];
            bArr[9] = this.f1642G[0];
            bArr[10] = this.f1642G[1];
            bArr[11] = this.f1642G[2];
            bArr[12] = this.f1642G[3];
            bArr[13] = this.f1643H[0];
            bArr[14] = this.f1643H[1];
            bArr[15] = this.f1643H[2];
            bArr[16] = this.f1643H[3];
            bArr[17] = this.f1644I[0];
            bArr[18] = this.f1644I[1];
            bArr[19] = this.f1644I[2];
            bArr[20] = this.f1644I[3];
        } else {
            bArr = new byte[23];
            bArr[9] = this.f1642G[0];
            bArr[10] = this.f1642G[1];
            bArr[11] = this.f1642G[2];
            bArr[12] = this.f1642G[3];
            bArr[13] = this.f1643H[0];
            bArr[14] = this.f1643H[1];
            bArr[15] = this.f1643H[2];
            bArr[16] = this.f1643H[3];
            bArr[17] = this.f1644I[0];
            bArr[18] = this.f1644I[1];
            bArr[19] = this.f1644I[2];
            bArr[20] = this.f1644I[3];
            bArr[21] = this.f1646K;
            bArr[22] = this.f1647L;
        }
        bArr[0] = this.f1665o;
        bArr[1] = (byte) -91;
        bArr[2] = this.f1676z;
        bArr[3] = this.f1636A;
        bArr[4] = (byte) this.f1639D;
        bArr[5] = this.f1640E;
        bArr[6] = (byte) this.f1637B;
        bArr[7] = (byte) this.f1638C;
        bArr[8] = this.f1641F;
        m758a(165, 4000, 165);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    private void m844a(int i, boolean z) {
        m861c(i);
        this.f1648M = z;
        byte[] intTo2Byte = ByteBufferUtil.intTo2Byte(i);
        byte[] bArr = new byte[]{this.f1665o, (byte) -73, intTo2Byte[0], intTo2Byte[1]};
        m758a(183, 4000, 183);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    private static void m846a(String str, Object... objArr) {
        Log.p("AaInsSet", Level.INFO, str, objArr);
    }

    private void m847a(List list, final JSONObject jSONObject) {
        try {
            jSONObject.put("sleep", m851b(list));
            this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SYNC_SLEEP_DATA_AM, jSONObject.toString());
            if (C2041b.f505a) {
                DataThreadPoolManager.getInstance().addExecuteTask(new Runnable(this) {
                    final /* synthetic */ AaInsSet f1625b;

                    public void run() {
                        this.f1625b.m856b(jSONObject);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m848a(JSONObject jSONObject) {
        if (jSONObject != null) {
            C2051l.m394c(this.f1661c, this.f1668r, this.f1662l, this.f1663m, jSONObject);
        }
    }

    private void m849a(byte[] bArr, final JSONObject jSONObject) {
        if (this.f1663m.equals(iHealthDevicesManager.TYPE_AM4) && this.f1669s < this.f1670t) {
            try {
                jSONObject.put(AmProfile.SYNC_STAGE_DATA_AM, m860c(new byte[0]));
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SYNC_STAGE_DATA_AM, jSONObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (bArr != null) {
            try {
                jSONObject.put(AmProfile.SYNC_STAGE_DATA_AM, m860c(bArr));
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SYNC_STAGE_DATA_AM, jSONObject.toString());
                if (C2041b.f505a) {
                    DataThreadPoolManager.getInstance().addExecuteTask(new Runnable(this) {
                        final /* synthetic */ AaInsSet f1623b;

                        public void run() {
                            this.f1623b.m848a(jSONObject);
                        }
                    });
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            try {
                jSONObject.put(AmProfile.SYNC_STAGE_DATA_AM, m860c(new byte[0]));
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SYNC_STAGE_DATA_AM, jSONObject.toString());
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
    }

    private JSONArray m851b(List list) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            byte[] bArr = (byte[]) list.get(i);
            JSONArray jSONArray2 = new JSONArray();
            try {
                String valueOf = String.valueOf((bArr[0] & 255) + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE);
                String valueOf2 = String.valueOf(bArr[1] & 255);
                String valueOf3 = String.valueOf(bArr[2] & 255);
                String valueOf4 = String.valueOf(bArr[3] & 255);
                String str = Integer.parseInt(valueOf4) < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + valueOf4 : valueOf4;
                valueOf4 = String.valueOf(bArr[4] & 255);
                String str2 = Integer.parseInt(valueOf4) < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + valueOf4 : valueOf4;
                valueOf4 = String.valueOf(bArr[5] & 255);
                if (Integer.parseInt(valueOf4) < 10) {
                    valueOf4 = AppEventsConstants.EVENT_PARAM_VALUE_NO + valueOf4;
                }
                long String2TS = PublicMethod.String2TS(valueOf + "-" + valueOf2 + "-" + valueOf3 + Constants.SPACE + str + ":" + str2 + ":" + valueOf4);
                for (int i2 = 8; i2 < bArr.length; i2++) {
                    JSONObject jSONObject = new JSONObject();
                    valueOf2 = PublicMethod.TS2String(String2TS);
                    jSONObject.put("time", valueOf2);
                    jSONObject.put("level", (bArr[i2] & 255) + "");
                    jSONObject.put("dataID", MD5.md5String(PublicMethod.getAMDataID(this.f1662l, (bArr[i2] & 255) + "", PublicMethod.String2TS(valueOf2))));
                    String2TS += 300;
                    jSONArray2.put(jSONObject);
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(AmProfile.SYNC_SLEEP_EACH_DATA_AM, jSONArray2);
                jSONArray.put(jSONObject2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray;
    }

    private void m852b(byte b) {
        this.f1664n.packageData(this.f1662l, new byte[]{this.f1665o, b});
    }

    private void m853b(int i) {
        m846a("a7Ins", Integer.valueOf(i));
        byte[] bArr = new byte[]{this.f1665o, (byte) -89, (byte) (this.f1649N[i] & 255)};
        m758a(167, 4000, 167);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    private void m855b(List list, final JSONObject jSONObject) {
        try {
            jSONObject.put(AmProfile.SYNC_ACTIVITY_DATA_AM, m842a(list));
            this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SYNC_ACTIVITY_DATA_AM, jSONObject.toString());
            if (C2041b.f505a) {
                DataThreadPoolManager.getInstance().addExecuteTask(new Runnable(this) {
                    final /* synthetic */ AaInsSet f1627b;

                    public void run() {
                        this.f1627b.m863c(jSONObject);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m856b(JSONObject jSONObject) {
        if (jSONObject != null) {
            C2051l.m392b(this.f1661c, this.f1668r, this.f1662l, this.f1663m, jSONObject);
        }
    }

    private void m857b(byte[] bArr, JSONObject jSONObject) {
        int i;
        int i2;
        if (this.f1663m.equals(iHealthDevicesManager.TYPE_AM3S) && this.f1669s >= Integer.parseInt(f1635a)) {
            i = (bArr[3] & 255) + ((((((bArr[0] & 255) * 256) * 256) * 256) + (((bArr[1] & 255) * 256) * 256)) + ((bArr[2] & 255) * 256));
            i2 = ((bArr[4] & 255) * 256) + (bArr[5] & 255);
        } else if (this.f1663m.equals(iHealthDevicesManager.TYPE_AM4)) {
            i = (bArr[3] & 255) + ((((((bArr[0] & 255) * 256) * 256) * 256) + (((bArr[1] & 255) * 256) * 256)) + ((bArr[2] & 255) * 256));
            i2 = ((bArr[4] & 255) * 256) + (bArr[5] & 255);
        } else {
            i = (bArr[1] & 255) + ((bArr[0] & 255) * 256);
            i2 = ((bArr[2] & 255) * 256) + (bArr[3] & 255);
        }
        try {
            jSONObject.put("step", i);
            jSONObject.put("calorie", i2);
            jSONObject.put(AmProfile.SYNC_REAL_TOTALCALORIE_AM, i2 + m858c());
            this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SYNC_REAL_DATA_AM, jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int m858c() {
        Context context = this.f1661c;
        String string = this.f1661c.getSharedPreferences("BMR_Content", 0).getString("current_bmr", "");
        if (string == null || string == "") {
            string = String.valueOf(this.f1645J);
        }
        int parseInt = Integer.parseInt(string);
        if (String.valueOf(parseInt) == null || String.valueOf(parseInt) == "") {
            parseInt = 0;
        }
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        return (int) ((((float) parseInt) / 86400.0f) * ((float) ((((Integer.parseInt(PublicMethod.TS2String(currentTimeMillis).split(Constants.SPACE)[1].split(":")[0]) * 60) * 60) + (Integer.parseInt(PublicMethod.TS2String(currentTimeMillis).split(Constants.SPACE)[1].split(":")[1]) * 60)) + Integer.parseInt(PublicMethod.TS2String(currentTimeMillis).split(Constants.SPACE)[1].split(":")[2]))));
    }

    private JSONArray m860c(byte[] bArr) {
        JSONArray jSONArray = new JSONArray();
        int i = 21;
        if (this.f1663m.equals(iHealthDevicesManager.TYPE_AM3S)) {
            i = 17;
        }
        for (int i2 = 0; i2 < bArr.length; i2 += i) {
            byte[] bArr2 = new byte[i];
            for (int i3 = 0; i3 < i; i3++) {
                bArr2[i3] = bArr[i2 + i3];
            }
            switch (bArr2[0] & 255) {
                case 0:
                    jSONArray.put(m875f(bArr2));
                    break;
                case 1:
                    jSONArray.put(m866d(bArr2));
                    break;
                case 2:
                    jSONArray.put(m871e(bArr2));
                    break;
                case 3:
                    jSONArray.put(m879g(bArr2));
                    break;
                default:
                    break;
            }
        }
        return jSONArray;
    }

    private void m861c(int i) {
        Context context = this.f1661c;
        Editor edit = this.f1661c.getSharedPreferences("BMR_Content", 0).edit();
        edit.putString("current_bmr", String.valueOf(i));
        edit.commit();
    }

    private void m863c(JSONObject jSONObject) {
        if (jSONObject != null) {
            C2051l.m389a(this.f1661c, this.f1668r, this.f1662l, this.f1663m, jSONObject);
        }
    }

    private void m864c(byte[] bArr, JSONObject jSONObject) {
        int i = bArr[1] & 255;
        int i2 = bArr[2] & 255;
        int i3 = bArr[3] & 255;
        int i4 = bArr[4] & 255;
        try {
            jSONObject.put(AmProfile.GET_SWIM_SWITCH_AM, bArr[0] & 255);
            jSONObject.put(AmProfile.GET_SWIMLANE_LENGTH_AM, i);
            jSONObject.put(AmProfile.GET_SWIM_CUTOUT_HOUR_AM, i2);
            jSONObject.put(AmProfile.GET_SWIM_CUTOUT_MINUTE_AM, i3);
            jSONObject.put(AmProfile.GET_SWIM_UNIT_AM, i4);
            this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_GET_SWIMINFO_AM, jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject m866d(byte[] bArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            Object obj;
            int i = (bArr[1] & 255) + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE;
            int i2 = bArr[2] & 255;
            int i3 = bArr[3] & 255;
            int i4 = bArr[4] & 255;
            int i5 = bArr[5] & 255;
            String format = String.format("%d-%02d-%02d %02d:%02d:00", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)});
            i5 = ((bArr[6] & 255) * 256) + (bArr[7] & 255);
            if (this.f1663m.equals(iHealthDevicesManager.TYPE_AM3S) && this.f1669s >= Integer.parseInt(f1635a)) {
                this.f1659X = true;
            } else if (this.f1663m.equals(iHealthDevicesManager.TYPE_AM4)) {
                this.f1659X = true;
            } else {
                this.f1659X = false;
            }
            if (this.f1659X) {
                i3 = ((((((bArr[8] & 255) * 256) * 256) * 256) + (((bArr[9] & 255) * 256) * 256)) + ((bArr[10] & 255) * 256)) + (bArr[11] & 255);
                obj = String.valueOf(bArr[12] & 255) + "." + String.valueOf(bArr[13] & 255);
                i = ((bArr[14] & 255) * 256) + (bArr[15] & 255);
            } else {
                i3 = ((bArr[8] & 255) * 256) + (bArr[9] & 255);
                obj = String.valueOf(bArr[10] & 255) + "." + String.valueOf(bArr[11] & 255);
                i = ((bArr[12] & 255) * 256) + (bArr[13] & 255);
            }
            jSONObject.put("type", AmProfile.SYNC_STAGE_DATA_TYPE_WORKOUT_AM);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_STOP_TIME_AM, format);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_USED_TIME_AM, i5);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_WORKOUT_STEP_AM, i3);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_DISTANCE_AM, obj);
            jSONObject.put("calorie", i);
            jSONObject.put("dataID", MD5.md5String(PublicMethod.getAMDataID(this.f1662l, i + "", PublicMethod.String2TS(format))));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void m867d() {
        this.f1675y = this.f1661c.getSharedPreferences(this.f1668r + "userinfo", 0).getString(HttpHeaders.HOST, "");
        if ("".equals(this.f1675y)) {
            this.f1675y = C2041b.f506b;
        }
        this.f1674x = this.f1661c.getSharedPreferences(this.f1668r + "userinfo", 0).getString("accessToken", "");
    }

    private void m868d(final JSONObject jSONObject) {
        if (!C2041b.f505a) {
            try {
                jSONObject.put(AmProfile.CLOUD_SEARCH_AM, 0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (this.f1675y != null && this.f1674x != null) {
            DataThreadPoolManager.getInstance().addExecuteTask(new Runnable(this) {
                final /* synthetic */ AaInsSet f1630b;

                public void run() {
                    try {
                        if (this.f1630b.f1673w.amsearch(this.f1630b.f1668r, this.f1630b.f1674x, this.f1630b.f1675y) == 100) {
                            jSONObject.put(AmProfile.CLOUD_SEARCH_AM, this.f1630b.f1673w.getAmsearch_return().mac[0]);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void m869d(byte[] bArr, JSONObject jSONObject) {
        int i = bArr[1] & 255;
        try {
            jSONObject.put(AmProfile.QUERY_STATE_AM, bArr[0] & 255);
            jSONObject.put("battery", i);
            this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_QUERY_STATE_AM, jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject m871e(byte[] bArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            int i = (bArr[1] & 255) + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE;
            int i2 = bArr[2] & 255;
            int i3 = bArr[3] & 255;
            int i4 = bArr[4] & 255;
            int i5 = bArr[5] & 255;
            String format = String.format("%d-%02d-%02d %02d:%02d:00", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)});
            i2 = ((bArr[6] & 255) * 256) + (bArr[7] & 255);
            i3 = ((bArr[8] & 255) * 256) + (bArr[9] & 255);
            i4 = bArr[10] & 255;
            jSONObject.put("type", "sleep");
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_STOP_TIME_AM, format);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_USED_TIME_AM, i2);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_SLEEP_EFFICIENCY_AM, ((double) i3) / 10.0d);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_SLEEP_IS50MIN_AM, i4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void m872e() {
        if (!C2041b.f505a) {
            this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_CLOUD_BINDING_AM_SUCCESS, null);
        } else if (this.f1675y == null || this.f1674x == null) {
            this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_CLOUD_BINDING_AM_FAIL, null);
        } else {
            DataThreadPoolManager.getInstance().addExecuteTask(new C21034(this));
        }
    }

    private void m873e(byte[] bArr, JSONObject jSONObject) {
        int i;
        int i2;
        if (bArr[1] > (byte) 59) {
            i = ((bArr[0] & 255) * 256) + (bArr[1] & 255);
            i2 = (byte) (i / 60);
            i = (byte) (i % 60);
        } else {
            i2 = bArr[0];
            i = bArr[1];
        }
        Object[] objArr = new Object[]{Integer.valueOf(i2), Integer.valueOf(i)};
        i2 = bArr[2] & 255;
        try {
            jSONObject.put("time", String.format("%02d:%02d", objArr));
            if (i2 == 0) {
                jSONObject.put("switch", false);
            } else {
                jSONObject.put("switch", true);
            }
            this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_GET_ACTIVITY_REMIND_AM, jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject m875f(byte[] bArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            int i = (bArr[1] & 255) + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE;
            int i2 = bArr[2] & 255;
            int i3 = bArr[3] & 255;
            int i4 = bArr[4] & 255;
            int i5 = bArr[5] & 255;
            int i6 = bArr[6] & 255;
            String format = String.format("%d-%02d-%02d %02d:%02d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6)});
            i2 = ((bArr[7] & 255) * 256) + (bArr[8] & 255);
            i3 = ((bArr[12] & 255) * 256) + (bArr[13] & 255);
            i4 = ((bArr[14] & 255) * 256) + (bArr[15] & 255);
            i5 = bArr[11] & 255;
            i6 = bArr[9] & 255;
            int i7 = bArr[10] & 255;
            int i8 = ((bArr[16] & 255) * 256) + (bArr[17] & 255);
            int i9 = ((bArr[18] & 255) * 256) + (bArr[19] & 255);
            int i10 = bArr[20] & 255;
            jSONObject.put("type", AmProfile.SYNC_STAGE_DATA_TYPE_SWIM_AM);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_STOP_TIME_AM, format);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_USED_TIME_AM, i2);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_SWIM_PULL_TIMES_AM, i3);
            jSONObject.put("calorie", i4);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_SWIM_STROKE_AM, i5);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_SWIM_TURNS_AM, i6);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_SWIMPOOL_LENGTH_AM, i7);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_SWIM_CUTINDIF_AM, i8);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_SWIM_CUTOUTDIF_AM, i9);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_SWIM_PROCESSFLAG_AM, i10);
            long String2TS = PublicMethod.String2TS(format);
            jSONObject.put("dataID", MD5.md5String(this.f1662l + (String2TS - ((long) i2)) + String2TS));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void m876f() {
        if (C2041b.f505a && this.f1675y != null && this.f1674x != null) {
            DataThreadPoolManager.getInstance().addExecuteTask(new C21056(this));
        }
    }

    private void m877f(byte[] bArr, JSONObject jSONObject) {
        try {
            if (this.f1650O == 0) {
                this.f1658W = new JSONArray();
            }
            int i = bArr[0] & 255;
            if (i != 0) {
                int i2 = bArr[1] & 255;
                int i3 = bArr[2] & 255;
                int i4 = bArr[3] & 255;
                JSONObject jSONObject2 = new JSONObject();
                byte b = bArr[4];
                if ((b & 1) == 0) {
                    jSONObject2.put(AmProfile.GET_ALARM_WEEK_SUNDAY_AM, false);
                } else {
                    jSONObject2.put(AmProfile.GET_ALARM_WEEK_SUNDAY_AM, true);
                }
                if ((b & 2) == 0) {
                    jSONObject2.put(AmProfile.GET_ALARM_WEEK_MONDAY_AM, false);
                } else {
                    jSONObject2.put(AmProfile.GET_ALARM_WEEK_MONDAY_AM, true);
                }
                if ((b & 4) == 0) {
                    jSONObject2.put(AmProfile.GET_ALARM_WEEK_TUESDAY_AM, false);
                } else {
                    jSONObject2.put(AmProfile.GET_ALARM_WEEK_TUESDAY_AM, true);
                }
                if ((b & 8) == 0) {
                    jSONObject2.put(AmProfile.GET_ALARM_WEEK_WEDNESDAY_AM, false);
                } else {
                    jSONObject2.put(AmProfile.GET_ALARM_WEEK_WEDNESDAY_AM, true);
                }
                if ((b & 16) == 0) {
                    jSONObject2.put(AmProfile.GET_ALARM_WEEK_THURSDAY_AM, false);
                } else {
                    jSONObject2.put(AmProfile.GET_ALARM_WEEK_THURSDAY_AM, true);
                }
                if ((b & 32) == 0) {
                    jSONObject2.put(AmProfile.GET_ALARM_WEEK_FRIDAY_AM, false);
                } else {
                    jSONObject2.put(AmProfile.GET_ALARM_WEEK_FRIDAY_AM, true);
                }
                if ((b & 64) == 0) {
                    jSONObject2.put(AmProfile.GET_ALARM_WEEK_SATURDAY_AM, false);
                } else {
                    jSONObject2.put(AmProfile.GET_ALARM_WEEK_SATURDAY_AM, true);
                }
                int i5 = bArr[5] & 255;
                jSONObject.put(AmProfile.GET_ALARM_ID_AM, i);
                Object[] objArr = new Object[]{Integer.valueOf(i2), Integer.valueOf(i3)};
                jSONObject.put("time", String.format("%02d:%02d", objArr));
                if (i4 == 1) {
                    jSONObject.put(AmProfile.GET_ALARM_ISREPEAT_AM, true);
                } else {
                    jSONObject.put(AmProfile.GET_ALARM_ISREPEAT_AM, false);
                }
                jSONObject.put(AmProfile.GET_ALARM_WEEK_AM, jSONObject2);
                if (i5 == 0) {
                    jSONObject.put("switch", false);
                } else {
                    jSONObject.put("switch", true);
                }
                this.f1658W.put(jSONObject);
            }
            this.f1650O++;
            if (this.f1650O >= this.f1649N.length) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(AmProfile.GET_ALARM_CLOCK_DETAIL, this.f1658W);
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_GET_ALARMINFO_AM, jSONObject3.toString());
                return;
            }
            m853b(this.f1650O);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject m879g(byte[] bArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            int i = (bArr[1] & 255) + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE;
            int i2 = bArr[2] & 255;
            int i3 = bArr[3] & 255;
            String format = String.format("%d-%02d-%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
            i2 = ((bArr[4] & 255) * 256) + (bArr[5] & 255);
            i3 = ((bArr[6] & 255) * 256) + (bArr[7] & 255);
            int i4 = ((bArr[8] & 255) * 256) + (bArr[9] & 255);
            int i5 = ((bArr[10] & 255) * 256) + (bArr[11] & 255);
            int i6 = ((bArr[12] & 255) * 256) + (bArr[13] & 255);
            jSONObject.put("type", AmProfile.SYNC_STAGE_DATA_TYPE_PAGE_VIEW_SUMMARY);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_VIEW_SUMMARY_DATE_AM, format);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_VIEW_SUMMARY_STEP_AM, i2);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_VIEW_SUMMARY_DISTANCE_AM, i3);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_VIEW_SUMMARY_CALORIE_AM, i4);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_VIEW_SUMMARY_TARGET_AM, i5);
            jSONObject.put(AmProfile.SYNC_STAGE_DATA_VIEW_SUMMARY_SWIM_AM, i6);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void m880g(byte[] bArr, JSONObject jSONObject) {
        int i = bArr[0] & 255;
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 1; i2 < bArr.length; i2++) {
            jSONArray.put(bArr[i2]);
        }
        try {
            jSONObject.put(AmProfile.GET_ALARMNUM_AM, i);
            jSONObject.put(AmProfile.GET_ALARMNUM_ID_AM, jSONArray);
            this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_GET_ALARMNUM_AM, jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m881h(byte[] bArr, JSONObject jSONObject) {
        int i;
        int i2;
        int i3;
        int i4 = bArr[0] & 255;
        int i5 = bArr[1] & 255;
        int i6 = bArr[2] & 255;
        int i7 = bArr[3] & 255;
        double doubleValue = Double.valueOf((String.valueOf(bArr[4] & 255) + "." + String.valueOf(bArr[5] & 255)).toString()).doubleValue();
        int i8 = bArr[6] & 255;
        int i9 = 0;
        if (this.f1663m.equals(iHealthDevicesManager.TYPE_AM3S) && this.f1669s >= Integer.parseInt(f1635a)) {
            i = ((((((bArr[7] & 255) * 256) * 256) * 256) + (((bArr[8] & 255) * 256) * 256)) + ((bArr[9] & 255) * 256)) + (bArr[10] & 255);
            i2 = (bArr[14] & 255) + ((((((bArr[11] & 255) * 256) * 256) * 256) + (((bArr[12] & 255) * 256) * 256)) + ((bArr[13] & 255) * 256));
            i3 = ((((((bArr[15] & 255) * 256) * 256) * 256) + (((bArr[16] & 255) * 256) * 256)) + ((bArr[17] & 255) * 256)) + (bArr[18] & 255);
        } else if (this.f1663m.equals(iHealthDevicesManager.TYPE_AM4)) {
            i = ((((((bArr[7] & 255) * 256) * 256) * 256) + (((bArr[8] & 255) * 256) * 256)) + ((bArr[9] & 255) * 256)) + (bArr[10] & 255);
            i2 = (bArr[14] & 255) + ((((((bArr[11] & 255) * 256) * 256) * 256) + (((bArr[12] & 255) * 256) * 256)) + ((bArr[13] & 255) * 256));
            i3 = ((((((bArr[15] & 255) * 256) * 256) * 256) + (((bArr[16] & 255) * 256) * 256)) + ((bArr[17] & 255) * 256)) + (bArr[18] & 255);
        } else {
            i = ((bArr[7] & 255) * 256) + (bArr[8] & 255);
            i2 = (bArr[10] & 255) + ((bArr[9] & 255) * 256);
            i3 = ((bArr[11] & 255) * 256) + (bArr[12] & 255);
        }
        if (!(this.f1663m.equals(iHealthDevicesManager.TYPE_AM3) || this.f1663m.equals(iHealthDevicesManager.TYPE_AM3S) || (this.f1663m.equals(iHealthDevicesManager.TYPE_AM4) && this.f1669s < this.f1671u))) {
            i9 = ((bArr[19] & 255) * 60) + (bArr[20] & 255);
        }
        try {
            jSONObject.put(AmProfile.GET_USER_AGE_AM, i4);
            jSONObject.put("step", i5);
            jSONObject.put("height", i6);
            jSONObject.put("gender", i7);
            jSONObject.put("weight", doubleValue);
            jSONObject.put(AmProfile.GET_USER_UNIT_AM, i8);
            jSONObject.put(AmProfile.GET_USER_TARGET1_AM, i);
            jSONObject.put(AmProfile.GET_USER_TARGET2_AM, i2);
            jSONObject.put(AmProfile.GET_USER_TARGET3_AM, i3);
            if (!(this.f1663m.equals(iHealthDevicesManager.TYPE_AM3) || this.f1663m.equals(iHealthDevicesManager.TYPE_AM3S) || (this.f1663m.equals(iHealthDevicesManager.TYPE_AM4) && this.f1669s < this.f1671u))) {
                jSONObject.put(AmProfile.GET_USER_SWIMTARGET_AM, i9);
            }
            this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_GET_USERINFO_AM, jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m882i(byte[] bArr, JSONObject jSONObject) {
        try {
            jSONObject.put("userid", ByteBufferUtil.byteToUserId(bArr));
            this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_USERID_AM, jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m883j(byte[] bArr, JSONObject jSONObject) {
        try {
            jSONObject.put(AmProfile.RESET_AM, bArr[0] & 255);
            this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_RESET_AM, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void a1Ins(int userId) {
        m846a("a1Ins", Integer.valueOf(userId));
        byte[] intTo4Byte = ByteBufferUtil.intTo4Byte((long) userId);
        byte[] bArr = new byte[6];
        bArr[0] = this.f1665o;
        bArr[1] = (byte) -95;
        for (int i = 0; i < intTo4Byte.length; i++) {
            bArr[i + 2] = (byte) -1;
        }
        this.f1666p.enterAction(AmProfile.ACTION_RESET_AM);
        m758a(161, 4000, 161);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void a2Ins() {
        m846a("a2Ins", new Object[0]);
        byte[] bArr = new byte[]{this.f1665o, (byte) -94};
        this.f1666p.enterAction(AmProfile.ACTION_USERID_AM);
        m758a(162, 4000, 162);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void a3Ins(int userId) {
        m846a("a3Ins", Integer.valueOf(userId));
        byte[] intToUserId = ByteBufferUtil.intToUserId(userId);
        byte[] bArr = new byte[6];
        bArr[0] = this.f1665o;
        bArr[1] = (byte) -93;
        for (int i = 0; i < intToUserId.length; i++) {
            bArr[i + 2] = intToUserId[i];
        }
        this.f1666p.enterAction(AmProfile.ACTION_SET_USERID_SUCCESS_AM);
        m758a(163, 4000, 163);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void a4Ins(int year, int month, int day, int hour, int min, int sed, int week) {
        m846a("a4Ins", Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day), Integer.valueOf(hour), Integer.valueOf(min), Integer.valueOf(sed), Integer.valueOf(week));
        if (String.valueOf(year).length() > 2) {
            year -= 2000;
        }
        byte b = (byte) (year & 255);
        byte b2 = (byte) (month & 255);
        byte b3 = (byte) (day & 255);
        byte b4 = (byte) (hour & 255);
        byte b5 = (byte) (min & 255);
        byte b6 = (byte) (sed & 255);
        byte b7 = (byte) (week & 255);
        byte[] bArr = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        byte[] bArr2 = new byte[13];
        bArr2[0] = this.f1665o;
        bArr2[1] = (byte) -92;
        bArr2[2] = b;
        bArr2[3] = b2;
        bArr2[4] = b3;
        bArr2[5] = b4;
        bArr2[6] = b5;
        bArr2[7] = b6;
        bArr2[8] = b7;
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i + 9] = bArr[i];
        }
        this.f1666p.enterAction(AmProfile.ACTION_SYNC_TIME_SUCCESS_AM);
        m758a(164, 4000, 164);
        this.f1664n.packageData(this.f1662l, bArr2);
    }

    public void a6Ins() {
        m846a("a6Ins", new Object[0]);
        byte[] bArr = new byte[]{this.f1665o, (byte) -90};
        this.f1666p.enterAction(AmProfile.ACTION_GET_ALARMNUM_AM);
        m758a(166, 4000, 166);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void a7Ins(int[] ids) {
        m846a("a7Ins", Arrays.toString(ids));
        this.f1650O = 0;
        this.f1649N = new int[ids.length];
        System.arraycopy(ids, 0, this.f1649N, 0, ids.length);
        byte[] bArr = new byte[]{this.f1665o, (byte) -89, (byte) (ids[this.f1650O] & 255)};
        this.f1666p.enterAction(AmProfile.ACTION_GET_ALARMINFO_AM);
        m758a(167, 4000, 167);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void a8Ins(int id, int hour, int min, boolean isRepeat, byte week, boolean isOpen) {
        m846a("a8Ins", Integer.valueOf(id), Integer.valueOf(hour), Integer.valueOf(min), Boolean.valueOf(isRepeat), Byte.valueOf(week), Boolean.valueOf(isOpen));
        byte b = (byte) (id & 255);
        byte b2 = (byte) (hour & 255);
        byte b3 = (byte) (min & 255);
        byte b4 = isRepeat ? (byte) 1 : (byte) 0;
        byte b5 = isOpen ? (byte) 1 : (byte) 0;
        byte[] bArr = new byte[]{this.f1665o, (byte) -88, b, b2, b3, b4, week, b5};
        this.f1666p.enterAction(AmProfile.ACTION_SET_ALARMINFO_SUCCESS_AM);
        m758a((int) DateTimeConstants.HOURS_PER_WEEK, 4000, DateTimeConstants.HOURS_PER_WEEK);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void a9Ins(int id) {
        m846a("a9Ins", Integer.valueOf(id));
        byte[] bArr = new byte[]{this.f1665o, (byte) -87, (byte) (id & 255)};
        this.f1666p.enterAction(AmProfile.ACTION_DELETE_ALARM_SUCCESS_AM);
        m758a(169, 4000, 169);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void aaIns(int hour, int minute, boolean isOpen) {
        m846a("aaIns", Integer.valueOf(hour), Integer.valueOf(minute), Boolean.valueOf(isOpen));
        byte[] bArr = new byte[5];
        bArr[0] = this.f1665o;
        bArr[1] = (byte) -86;
        bArr[2] = (byte) hour;
        bArr[3] = (byte) minute;
        if (isOpen) {
            bArr[4] = (byte) 1;
        } else {
            bArr[4] = (byte) 0;
        }
        this.f1666p.enterAction(AmProfile.ACTION_SET_ACTIVITYREMIND_SUCCESS_AM);
        m758a(170, 4000, 170);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void aaIns(int mins, boolean isOpen) {
        m846a("aaIns", Integer.valueOf(mins), Boolean.valueOf(isOpen));
        byte[] bArr = new byte[5];
        bArr[0] = this.f1665o;
        bArr[1] = (byte) -86;
        bArr[2] = (byte) ((65535 & mins) / 60);
        bArr[3] = (byte) ((65535 & mins) % 60);
        if (isOpen) {
            bArr[4] = (byte) 1;
        } else {
            bArr[4] = (byte) 0;
        }
        this.f1666p.enterAction(AmProfile.ACTION_SET_ACTIVITYREMIND_SUCCESS_AM);
        m758a(170, 4000, 170);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void acIns() {
        m846a("acIns", new Object[0]);
        byte[] bArr = new byte[]{this.f1665o, (byte) -84};
        this.f1666p.enterAction(AmProfile.ACTION_SYNC_ACTIVITY_DATA_AM);
        m758a(172, 4000, 171);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void b0Ins() {
        m846a("b0Ins", new Object[0]);
        byte[] bArr = new byte[]{this.f1665o, (byte) -80};
        this.f1666p.enterAction(AmProfile.ACTION_SYNC_SLEEP_DATA_AM);
        m758a(176, 4000, 175);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void b3Ins() {
        m846a("b3Ins", new Object[0]);
        byte[] bArr = new byte[]{this.f1665o, (byte) -77};
        this.f1666p.enterAction(AmProfile.ACTION_GET_ACTIVITY_REMIND_AM);
        m758a(179, 4000, 179);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void b4Ins() {
        m846a("b4Ins", new Object[0]);
        byte[] bArr = new byte[]{this.f1665o, (byte) -76};
        this.f1666p.enterAction(AmProfile.ACTION_QUERY_STATE_AM);
        m758a(180, 4000, 180);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void b5Ins(int state) {
        m846a("b5Ins", Integer.valueOf(state));
        byte[] bArr = new byte[]{this.f1665o, (byte) -75, (byte) (state & 255)};
        this.f1666p.enterAction(AmProfile.ACTION_SET_DEVICE_MODE_AM);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void b5Ins(int state, int minute) {
        m846a("b5Ins", Integer.valueOf(state), Integer.valueOf(minute));
        byte[] bArr = new byte[]{this.f1665o, (byte) -75, (byte) (state & 255), (byte) ((minute & 255) / 60), (byte) ((minute & 255) % 60)};
        this.f1666p.enterAction(AmProfile.ACTION_SET_DEVICE_MODE_AM);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void b6Ins() {
        m846a("b6Ins", new Object[0]);
        byte[] bArr = new byte[]{this.f1665o, (byte) -74};
        this.f1666p.enterAction(AmProfile.ACTION_GET_USERINFO_AM);
        m758a(182, 4000, 182);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void b7Ins(int bmr) {
        m846a("b7Ins", Integer.valueOf(bmr));
        this.f1666p.enterAction(AmProfile.ACTION_SET_BMR_SUCCESS_AM);
        m844a(bmr, false);
    }

    public void bfIns() {
        m846a("bfIns", new Object[0]);
        byte[] bArr = new byte[]{this.f1665o, (byte) -65};
        this.f1666p.enterAction(AmProfile.ACTION_SYNC_REAL_DATA_AM);
        m758a(191, 4000, 191);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void checkSwimPara() {
        m846a("checkSwimPara", new Object[0]);
        byte[] bArr = new byte[]{this.f1665o, (byte) 5};
        this.f1666p.enterAction(AmProfile.ACTION_GET_SWIMINFO_AM);
        m758a(5, 4000, 5);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void destroy() {
        if (this.f1664n != null) {
            this.f1664n.destroy();
        }
        this.f1664n = null;
        this.f1661c = null;
        this.f1667q = null;
        this.f1666p = null;
        if (this.f1653R != null) {
            this.f1653R.clear();
        }
        this.f1653R = null;
        if (this.f1655T != null) {
            this.f1655T.clear();
        }
        this.f1655T = null;
    }

    public BaseCommProtocol getBaseCommProtocol() {
        return this.f1664n;
    }

    public int getBattery() {
        m846a("getBattery", new Object[0]);
        return this.f1657V;
    }

    public void haveNewData(int what, int stateId, byte[] returnData) {
        int i = 0;
        Log.p("AaInsSet", Level.DEBUG, "haveNewData", new Object[]{Command.m840a(what), Integer.valueOf(stateId), ByteBufferUtil.Bytes2HexString(returnData)});
        JSONObject jSONObject = new JSONObject();
        m757a(what);
        switch (Command.m840a(what)) {
            case Verification_Feedback:
                byte[] a = m762a(returnData, this.f1663m, this.f1665o);
                m758a(252, 4000, 253, 254);
                this.f1664n.packageData(this.f1662l, a);
                return;
            case Verification_Success:
                this.f1667q.onConnectionStateChange(this.f1662l, this.f1663m, 1, 0, null);
                return;
            case Verification_Failed:
                this.f1660b.disconnect();
                return;
            case Reset_Success:
                if (returnData != null) {
                    m883j(returnData, jSONObject);
                    m876f();
                    return;
                }
                return;
            case GetUserId_Success:
                if (returnData != null) {
                    m882i(returnData, jSONObject);
                    m868d(jSONObject);
                    return;
                }
                return;
            case SetUserId_Success:
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SET_USERID_SUCCESS_AM, null);
                m872e();
                return;
            case SyncTime_Success:
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SYNC_TIME_SUCCESS_AM, null);
                return;
            case SetUserInfo_Success:
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SET_USERINFO_SUCCESS_AM, null);
                return;
            case GetUserInfo_Success:
                if (returnData != null) {
                    m881h(returnData, jSONObject);
                    return;
                }
                return;
            case GetAlarmNum_Success:
                if (returnData != null) {
                    m880g(returnData, jSONObject);
                    return;
                }
                return;
            case GetAlarmInfo_Success:
                if (returnData != null) {
                    m877f(returnData, jSONObject);
                    return;
                }
                return;
            case SetAlarmInfo_Success:
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SET_ALARMINFO_SUCCESS_AM, null);
                return;
            case DeleteAlarm_Success:
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_DELETE_ALARM_SUCCESS_AM, null);
                return;
            case GetReminder_Success:
                if (returnData != null) {
                    m873e(returnData, jSONObject);
                    return;
                }
                return;
            case SetReminder_Success:
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SET_ACTIVITYREMIND_SUCCESS_AM, null);
                return;
            case SetMode_Success:
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SET_DEVICE_MODE_AM, null);
                return;
            case SyncActivityData_Start:
                m852b((byte) -85);
                if (this.f1654S != null) {
                    this.f1653R.add(this.f1654S);
                    this.f1654S = null;
                }
                this.f1654S = ByteBufferUtil.rejectBuffer(returnData);
                return;
            case SyncActivityData_Finish:
                m852b((byte) -82);
                if (this.f1654S != null) {
                    this.f1653R.add(this.f1654S);
                    this.f1654S = null;
                }
                if (this.f1653R != null) {
                    m855b(this.f1653R, jSONObject);
                }
                this.f1653R = null;
                this.f1653R = new ArrayList();
                return;
            case SyncActivityData_Data:
                m852b((byte) -83);
                this.f1654S = ByteBufferUtil.BufferMerger(this.f1654S, returnData);
                return;
            case SyncSleepData_Start:
                m852b((byte) -81);
                if (this.f1656U != null) {
                    this.f1655T.add(this.f1656U);
                    this.f1656U = null;
                }
                this.f1656U = ByteBufferUtil.rejectBuffer(returnData);
                return;
            case SyncSleepData_Finish:
                m852b((byte) -78);
                if (this.f1656U != null) {
                    this.f1655T.add(this.f1656U);
                    this.f1656U = null;
                }
                if (this.f1655T != null) {
                    m847a(this.f1655T, jSONObject);
                }
                this.f1655T = null;
                this.f1655T = new ArrayList();
                return;
            case SyncSleepData_Data:
                m852b((byte) -79);
                this.f1656U = ByteBufferUtil.BufferMerger(this.f1656U, returnData);
                return;
            case GetDeviceInfo_Success:
                if (returnData != null) {
                    m869d(returnData, jSONObject);
                    return;
                }
                return;
            case SyncStageData_Start:
                m852b((byte) Ascii.VT);
                this.f1652Q = new byte[0];
                return;
            case SyncStageData_Data:
                m852b((byte) Ascii.FF);
                this.f1652Q = ByteBufferUtil.BufferMerger(this.f1652Q, returnData);
                return;
            case SyncStageData_Finish:
                m852b((byte) 13);
                m849a(this.f1652Q, jSONObject);
                this.f1652Q = null;
                return;
            case SyncRealTimeData_Success:
                if (returnData != null) {
                    m857b(returnData, jSONObject);
                    return;
                }
                return;
            case SetBMR_Success:
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SET_BMR_SUCCESS_AM, null);
                if (this.f1648M) {
                    m843a();
                    return;
                }
                return;
            case GetSwimParameter_Success:
                if (returnData != null) {
                    m864c(returnData, jSONObject);
                    return;
                }
                return;
            case SetSwimParameter_Success:
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SET_SWIMINFO_AM, null);
                return;
            case SendRandomNumber_Success:
                String str = "";
                while (i < this.f1651P.length) {
                    str = str + this.f1651P[i] + "";
                    i++;
                }
                try {
                    jSONObject.put(AmProfile.GET_RANDOM_AM, str);
                    this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_GET_RANDOM_AM, jSONObject.toString());
                    return;
                } catch (Exception e) {
                    return;
                }
            case GetPicture_Success:
                try {
                    jSONObject.put("get_picture_am", returnData[0] & 255);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, "get_picture_am", jSONObject.toString());
                return;
            case SetPicture_Success:
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SET_PICTURE_SUCCESS_AM, null);
                return;
            case GetHourMode_Success:
                try {
                    jSONObject.put(AmProfile.GET_HOUR_MODE_AM, returnData[0] & 255);
                } catch (JSONException e22) {
                    e22.printStackTrace();
                }
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_GET_HOUR_MODE_AM, jSONObject.toString());
                return;
            case SetHourMode_Success:
                this.f1666p.onNotifyWithAction(this.f1662l, this.f1663m, AmProfile.ACTION_SET_HOUR_MODE_SUCCESS_AM, null);
                return;
            default:
                return;
        }
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
    }

    public void identify() {
        m846a("identify", new Object[0]);
        m758a((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 4000, 251, 253, 254);
        this.f1664n.packageData(this.f1662l, m760a(this.f1665o));
    }

    public void setSwimPara(boolean isOpen, byte poolLength, byte hours, byte minites, byte unit) {
        m846a("setSwimPara", Boolean.valueOf(isOpen), Byte.valueOf(poolLength), Byte.valueOf(hours), Byte.valueOf(minites), Byte.valueOf(unit));
        byte[] bArr = new byte[7];
        bArr[0] = this.f1665o;
        bArr[1] = (byte) 6;
        if (!isOpen) {
            bArr[2] = (byte) 0;
        } else if (this.f1669s >= this.f1672v) {
            bArr[2] = (byte) 2;
        } else {
            bArr[2] = (byte) 1;
        }
        bArr[3] = poolLength;
        bArr[4] = hours;
        bArr[5] = minites;
        bArr[6] = unit;
        this.f1666p.enterAction(AmProfile.ACTION_SET_SWIMINFO_AM);
        m758a(6, 4000, 6);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void setUserInfo(int age, int step, float height, int sex, float weight, int weightUnit, int target1, int target2, int target3, int bmr) {
        m846a("setUserInfo", Integer.valueOf(age), Integer.valueOf(step), Float.valueOf(height), Integer.valueOf(sex), Float.valueOf(weight), Integer.valueOf(weightUnit), Integer.valueOf(target1), Integer.valueOf(target2), Integer.valueOf(target3), Integer.valueOf(bmr));
        this.f1676z = (byte) age;
        this.f1636A = (byte) step;
        this.f1637B = (int) Float.parseFloat(weight + "");
        this.f1638C = ((int) (Float.parseFloat(weight + "") * 10.0f)) % 10;
        this.f1639D = (int) Float.parseFloat(height + "");
        this.f1640E = (byte) sex;
        this.f1641F = (byte) weightUnit;
        if (this.f1663m.equals(iHealthDevicesManager.TYPE_AM3S) && this.f1669s >= Integer.parseInt(f1635a)) {
            this.f1642G = ByteBufferUtil.intTo4Byte((long) target1);
            this.f1643H = ByteBufferUtil.intTo4Byte((long) target2);
            this.f1644I = ByteBufferUtil.intTo4Byte((long) target3);
        } else if (this.f1663m.equals(iHealthDevicesManager.TYPE_AM4)) {
            this.f1642G = ByteBufferUtil.intTo4Byte((long) target1);
            this.f1643H = ByteBufferUtil.intTo4Byte((long) target2);
            this.f1644I = ByteBufferUtil.intTo4Byte((long) target3);
        } else {
            this.f1642G = ByteBufferUtil.intTo2Byte(target1);
            this.f1643H = ByteBufferUtil.intTo2Byte(target2);
            this.f1644I = ByteBufferUtil.intTo2Byte(target3);
        }
        this.f1645J = bmr;
        this.f1666p.enterAction(AmProfile.ACTION_SET_USERINFO_SUCCESS_AM);
        if (C2041b.f505a) {
            m844a(this.f1645J, true);
        } else {
            m843a();
        }
    }

    public void setUserInfoForAM4Plus(int age, int step, float height, int sex, float weight, int weightUnit, int target1, int target2, int target3, int bmr, int min) {
        m846a("setUserInfoForAM4Plus", Integer.valueOf(age), Integer.valueOf(step), Float.valueOf(height), Integer.valueOf(sex), Float.valueOf(weight), Integer.valueOf(weightUnit), Integer.valueOf(target1), Integer.valueOf(target2), Integer.valueOf(target3), Integer.valueOf(bmr), Integer.valueOf(min));
        this.f1676z = (byte) age;
        this.f1636A = (byte) step;
        this.f1637B = (int) Float.parseFloat(weight + "");
        this.f1638C = ((int) (Float.parseFloat(weight + "") * 10.0f)) % 10;
        this.f1639D = (int) Float.parseFloat(height + "");
        this.f1640E = (byte) sex;
        this.f1641F = (byte) weightUnit;
        this.f1646K = (byte) (min / 60);
        this.f1647L = (byte) (min % 60);
        if (this.f1663m.equals(iHealthDevicesManager.TYPE_AM3S) && this.f1669s >= Integer.parseInt(f1635a)) {
            this.f1642G = ByteBufferUtil.intTo4Byte((long) target1);
            this.f1643H = ByteBufferUtil.intTo4Byte((long) target2);
            this.f1644I = ByteBufferUtil.intTo4Byte((long) target3);
        } else if (this.f1663m.equals(iHealthDevicesManager.TYPE_AM4)) {
            this.f1642G = ByteBufferUtil.intTo4Byte((long) target1);
            this.f1643H = ByteBufferUtil.intTo4Byte((long) target2);
            this.f1644I = ByteBufferUtil.intTo4Byte((long) target3);
        } else {
            this.f1642G = ByteBufferUtil.intTo2Byte(target1);
            this.f1643H = ByteBufferUtil.intTo2Byte(target2);
            this.f1644I = ByteBufferUtil.intTo2Byte(target3);
        }
        this.f1645J = bmr;
        this.f1666p.enterAction(AmProfile.ACTION_SET_USERINFO_SUCCESS_AM);
        if (C2041b.f505a) {
            m844a(this.f1645J, true);
        } else {
            m843a();
        }
    }

    public void x01Ins() {
        m846a("x01Ins", new Object[0]);
        byte[] bArr = new byte[]{this.f1665o, (byte) 1};
        this.f1666p.enterAction(AmProfile.ACTION_GET_HOUR_MODE_AM);
        m758a(1, 4000, 1);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void x02Ins(int hour) {
        m846a("x02Ins", Integer.valueOf(hour));
        byte[] bArr = new byte[]{this.f1665o, (byte) 2, (byte) hour};
        this.f1666p.enterAction(AmProfile.ACTION_SET_HOUR_MODE_SUCCESS_AM);
        m758a(2, 4000, 2);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void x09Ins(Integer[] random) {
        m846a("x09Ins", Arrays.toString(random));
        byte[] bArr = new byte[8];
        bArr[0] = this.f1665o;
        bArr[1] = (byte) 9;
        this.f1651P = new byte[6];
        for (int i = 0; i < 6; i++) {
            bArr[i + 2] = random[i].byteValue();
            this.f1651P[i] = random[i].byteValue();
        }
        this.f1666p.enterAction(AmProfile.ACTION_GET_RANDOM_AM);
        m758a(9, 4000, 9);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void x0aIns() {
        m846a("x0aIns", new Object[0]);
        byte[] bArr = new byte[]{this.f1665o, (byte) 10};
        this.f1666p.enterAction(AmProfile.ACTION_SYNC_STAGE_DATA_AM);
        m758a(10, 4000, 11);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void x10Ins() {
        m846a("x10Ins", new Object[0]);
        byte[] bArr = new byte[]{this.f1665o, (byte) 16};
        this.f1666p.enterAction("get_picture_am");
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void x11Ins(int which) {
        m846a("x11Ins", Integer.valueOf(which));
        byte[] bArr = new byte[]{this.f1665o, (byte) 17, (byte) (which & 255)};
        this.f1666p.enterAction(AmProfile.ACTION_SET_PICTURE_SUCCESS_AM);
        this.f1664n.packageData(this.f1662l, bArr);
    }

    public void xBAIns() {
        m846a("xBAIns", new Object[0]);
        this.f1664n.packageData(this.f1662l, new byte[]{this.f1665o, (byte) -70});
    }

    public void xD6Ins(String account, String mac) {
        int i = 0;
        m846a("xD6Ins", account, mac);
        byte[] bArr = new byte[18];
        bArr[0] = (byte) -86;
        bArr[1] = (byte) -42;
        byte[] md5 = MD5.md5(account + mac);
        while (i < md5.length) {
            bArr[i + 2] = md5[i];
            i++;
        }
        this.f1664n.packageData(this.f1662l, bArr);
    }
}
