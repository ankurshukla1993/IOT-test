package com.ihealth.communication.control;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.imageutils.JfifUtil;
import com.facebook.react.uimanager.ViewProps;
import com.github.thunder413.datetimeutils.DateTimeFormat;
import com.google.common.base.Ascii;
import com.ihealth.androidbg.audio.AudioTrackManager;
import com.ihealth.androidbg.audio.BG1.BG1_Command_Interface;
import com.ihealth.androidbg.audio.BG1.CommSound;
import com.ihealth.androidbg.audio.BG1.CommSound_new;
import com.ihealth.androidbg.audio.CrcCheck;
import com.ihealth.androidbg.audio.TunnerThread;
import com.ihealth.androidbg.audio.XXTEA;
import com.ihealth.communication.cloud.data.BG_InAuthor;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import com.ihealth.communication.cloud.data.DataBaseTools;
import com.ihealth.communication.cloud.data.Make_Data_Util;
import com.ihealth.communication.cloud.p002a.C2040a;
import com.ihealth.communication.cloud.p002a.C2041b;
import com.ihealth.communication.cloud.p002a.C2051l;
import com.ihealth.communication.ins.IdentifyIns;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import humanize.util.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Bg1Control implements BG1_Command_Interface {
    private static final String f1042a = Bg1Control.class.getSimpleName();
    private static final IdentifyIns f1043b = new IdentifyIns();
    private static int f1044c = 11;
    private boolean f1045A = false;
    private boolean f1046B = false;
    private boolean f1047C = false;
    private boolean f1048D = false;
    private boolean f1049E = false;
    private int f1050F = 0;
    private boolean f1051G = false;
    private int f1052H;
    private int f1053I;
    private Runnable f1054J = new av(this);
    private Timer f1055K = null;
    private TimerTask f1056L = null;
    private Runnable f1057M = new az(this);
    private CommSound f1058d = null;
    private CommSound_new f1059e = null;
    private TunnerThread f1060f = null;
    private Context f1061g = null;
    private String f1062h = "";
    private byte[] f1063i = null;
    private String f1064j = "";
    private String f1065k = "";
    private long f1066l = 0;
    private Thread f1067m = null;
    private Thread f1068n = null;
    private Timer f1069o = null;
    private TimerTask f1070p = null;
    private Timer f1071q = null;
    private TimerTask f1072r = null;
    private AudioManager f1073s = null;
    private boolean f1074t = false;
    private boolean f1075u = false;
    private int f1076v = 16716548;
    private int f1077w = 16716549;
    private boolean f1078x = false;
    private int f1079y = 5;
    private boolean f1080z = false;

    private int m485a(long j, int i) {
        int i2 = 1;
        Log.v(f1042a, "send BottleId  new");
        byte[] bArr = new byte[]{(byte) 83, (byte) ((int) ((j >> 24) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) (j & 255)), (byte) i};
        if (this.f1059e != null) {
            bArr = this.f1059e.sendCommand((byte) 0, bArr, 3);
            if (bArr == null) {
                return 0;
            }
            if (bArr[0] == (byte) 84) {
                Log.v(f1042a, "different version of ensure info, send full-----------------> 1");
            } else if (bArr[0] == (byte) 85) {
                Log.v(f1042a, "different  bottle id, send short----------------------------> 2");
                i2 = 2;
            } else if (bArr[0] == (byte) 86) {
                Log.v(f1042a, "all the same,not need to send--------------------------------> 3");
                i2 = 3;
            } else if (AudioTrackManager.isR2017) {
                Log.v(f1042a, "receive error, send full-----------------> 1");
            }
            return i2;
        }
        i2 = 0;
        return i2;
    }

    private String m488a(int i) {
        Log.v(f1042a, "mode 2-- deviceId");
        byte[] bArr = new byte[]{(byte) -94, (byte) 79, (byte) i, (byte) 0, (byte) 0};
        byte[] bArr2 = null;
        String str = "";
        if (this.f1058d != null) {
            bArr2 = this.f1058d.sendCommand((byte) -96, bArr, 1);
        }
        return bArr2 == null ? "" : m490a(bArr2);
    }

    private static String m489a(String str, String[] strArr, String[] strArr2) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            for (int i = 0; i < strArr.length; i++) {
                jSONObject2.put(strArr2[i], strArr[i]);
            }
            jSONArray.put(jSONObject2);
            jSONObject.put(str, jSONArray);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject().toString();
        }
    }

    private String m490a(byte[] bArr) {
        int i = 0;
        int length = bArr.length;
        if (length <= 10) {
            return "";
        }
        byte[] bArr2 = new byte[(length - 8)];
        byte[] bArr3 = new byte[6];
        for (int i2 = 0; i2 < length - 8; i2++) {
            bArr2[i2] = bArr[i2 + 1];
        }
        while (i < 6) {
            bArr3[i] = bArr[(i + length) - 7];
            i++;
        }
        return new String(bArr2) + m501b(bArr3, bArr3.length);
    }

    private void m492a(byte b, int i) {
        new ax(this, b, i).start();
    }

    private static boolean m497a(String str) {
        if (str == null || str.length() != 60) {
            Log.e(f1042a, "Code type :: Error Code ------> " + str);
            return false;
        }
        byte[] hexStringToByte = ByteBufferUtil.hexStringToByte(str);
        if (hexStringToByte.length == 30) {
            int cRCValue = new CrcCheck(m499a(hexStringToByte, 28)).getCRCValue();
            if (hexStringToByte[28] == ((byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & cRCValue) >> 8)) && hexStringToByte[29] == ((byte) (cRCValue & 255))) {
                Log.v(f1042a, "Code type :: Normal QRCode ------> ");
                return true;
            }
        }
        Log.e(f1042a, "Code type :: Error Code ------> " + str);
        return false;
    }

    private boolean m498a(String str, boolean z) {
        m517e(str);
        this.f1066l = m519f(str);
        byte[] bArr;
        byte[] bArr2;
        Intent intent;
        Intent intent2;
        if (z) {
            bArr = new byte[]{(byte) -94, (byte) 80, this.f1063i[1], this.f1063i[2], this.f1063i[3], this.f1063i[4], this.f1063i[5], this.f1063i[6], this.f1063i[7], this.f1063i[8], this.f1063i[9], this.f1063i[10], this.f1063i[11], this.f1063i[12], this.f1063i[28], this.f1063i[29], this.f1063i[30]};
            bArr2 = null;
            if (this.f1058d != null) {
                bArr2 = this.f1058d.sendCommand((byte) 17, bArr, 51);
            }
            if (bArr2 == null) {
                Log.e(f1042a, "－－－－－－send code failed :: flag 6－－－－－－");
                intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
                intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 6);
                intent.setPackage(this.f1061g.getPackageName());
                if (!m544t()) {
                    return false;
                }
                this.f1061g.sendBroadcast(intent);
                return false;
            }
            Log.v(f1042a, "－－－－－－send code sueccess 2-1－－－－－－");
            bArr = new byte[]{(byte) -94, (byte) 80, this.f1063i[31], this.f1063i[32], this.f1063i[33], this.f1063i[106], this.f1063i[107], this.f1063i[108], this.f1063i[109], this.f1063i[110], this.f1063i[111], this.f1063i[112], this.f1063i[113], this.f1063i[114], this.f1063i[115], this.f1063i[122], this.f1063i[123]};
            bArr2 = null;
            if (this.f1058d != null) {
                bArr2 = this.f1058d.sendCommand((byte) 16, bArr, 50);
            }
            if (bArr2 == null) {
                Log.e(f1042a, "－－－－－－send code failed :: flag 7－－－－－－");
                intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
                intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 7);
                intent.setPackage(this.f1061g.getPackageName());
                if (!m544t()) {
                    return false;
                }
                this.f1061g.sendBroadcast(intent);
                return false;
            }
            Log.v(f1042a, "－－－－－－send code success 2-2－－－－－－");
            this.f1077w = 16716548;
            intent2 = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
            intent2.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 0);
            intent2.setPackage(this.f1061g.getPackageName());
            if (m544t()) {
                this.f1061g.sendBroadcast(intent2);
            }
            return true;
        }
        bArr = new byte[]{(byte) -94, (byte) 37, this.f1063i[1], this.f1063i[2], this.f1063i[3], this.f1063i[4], this.f1063i[5], this.f1063i[6], this.f1063i[7], this.f1063i[8], this.f1063i[9], this.f1063i[10], this.f1063i[11], this.f1063i[12], this.f1063i[28], this.f1063i[29], this.f1063i[30]};
        bArr2 = null;
        if (this.f1058d != null) {
            bArr2 = this.f1058d.sendCommand((byte) 85, bArr, 65);
        }
        if (bArr2 == null) {
            Log.e(f1042a, "－－－－－－send code failed :: flag 8－－－－－－");
            intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
            intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 8);
            intent.setPackage(this.f1061g.getPackageName());
            if (!m544t()) {
                return false;
            }
            this.f1061g.sendBroadcast(intent);
            return false;
        }
        Log.v(f1042a, "－－－－－－send code success 6-1－－－－－－");
        bArr = new byte[]{(byte) -94, (byte) 37, this.f1063i[31], this.f1063i[32], this.f1063i[33], this.f1063i[106], this.f1063i[107], this.f1063i[108], this.f1063i[109], this.f1063i[110], this.f1063i[111], this.f1063i[112], this.f1063i[113], this.f1063i[114], this.f1063i[115], this.f1063i[122], this.f1063i[123]};
        bArr2 = null;
        if (this.f1058d != null) {
            bArr2 = this.f1058d.sendCommand((byte) 84, bArr, 64);
        }
        if (bArr2 == null) {
            Log.e(f1042a, "－－－－－－send code failed :: flag 9－－－－－－");
            intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
            intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 9);
            intent.setPackage(this.f1061g.getPackageName());
            if (!m544t()) {
                return false;
            }
            this.f1061g.sendBroadcast(intent);
            return false;
        }
        Log.v(f1042a, "－－－－－－send code success 6-2－－－－－－");
        bArr = new byte[]{(byte) -94, (byte) 37, this.f1063i[36], this.f1063i[37], this.f1063i[38], this.f1063i[39], this.f1063i[40], this.f1063i[41], this.f1063i[42], this.f1063i[43], this.f1063i[44], this.f1063i[45], this.f1063i[46], this.f1063i[47], this.f1063i[48], this.f1063i[49], this.f1063i[50], this.f1063i[51]};
        bArr2 = null;
        if (this.f1058d != null) {
            bArr2 = this.f1058d.sendCommand((byte) 83, bArr, 63);
        }
        if (bArr2 == null) {
            Log.e(f1042a, "－－－－－－send code failed :: flag 10－－－－－－");
            intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
            intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 10);
            intent.setPackage(this.f1061g.getPackageName());
            if (!m544t()) {
                return false;
            }
            this.f1061g.sendBroadcast(intent);
            return false;
        }
        Log.v(f1042a, "－－－－－－send code success 6-3－－－－－－");
        bArr = new byte[]{(byte) -94, (byte) 37, this.f1063i[52], this.f1063i[53], this.f1063i[54], this.f1063i[55], this.f1063i[56], this.f1063i[57], this.f1063i[58], this.f1063i[59], this.f1063i[60], this.f1063i[61], this.f1063i[62], this.f1063i[63], this.f1063i[64], this.f1063i[65], this.f1063i[66], this.f1063i[67]};
        bArr2 = null;
        if (this.f1058d != null) {
            bArr2 = this.f1058d.sendCommand((byte) 82, bArr, 62);
        }
        if (bArr2 == null) {
            Log.e(f1042a, "－－－－－－send code failed :: flag 11－－－－－－");
            intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
            intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 11);
            intent.setPackage(this.f1061g.getPackageName());
            if (!m544t()) {
                return false;
            }
            this.f1061g.sendBroadcast(intent);
            return false;
        }
        Log.v(f1042a, "－－－－－－send code success 6-4－－－－－－");
        bArr = new byte[]{(byte) -94, (byte) 37, this.f1063i[68], this.f1063i[69], this.f1063i[70], this.f1063i[71], this.f1063i[72], this.f1063i[73], this.f1063i[74], this.f1063i[75], this.f1063i[76], this.f1063i[77], this.f1063i[78], this.f1063i[79], this.f1063i[80], this.f1063i[81], this.f1063i[82], this.f1063i[83]};
        bArr2 = null;
        if (this.f1058d != null) {
            bArr2 = this.f1058d.sendCommand((byte) 81, bArr, 61);
        }
        if (bArr2 == null) {
            Log.e(f1042a, "－－－－－－send code failed :: flag 12－－－－－－");
            intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
            intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 12);
            intent.setPackage(this.f1061g.getPackageName());
            if (!m544t()) {
                return false;
            }
            this.f1061g.sendBroadcast(intent);
            return false;
        }
        Log.v(f1042a, "－－－－－－send code success 6-5－－－－－－");
        bArr = new byte[]{(byte) -94, (byte) 37, this.f1063i[84], this.f1063i[85], this.f1063i[86], this.f1063i[87], this.f1063i[88], this.f1063i[89], this.f1063i[90], this.f1063i[91], this.f1063i[92], this.f1063i[93], this.f1063i[94], this.f1063i[95], this.f1063i[96], this.f1063i[97], this.f1063i[98], this.f1063i[99]};
        bArr2 = null;
        if (this.f1058d != null) {
            bArr2 = this.f1058d.sendCommand((byte) 80, bArr, 60);
        }
        if (bArr2 == null) {
            Log.e(f1042a, "－－－－－－send code failed :: flag 13－－－－－－");
            intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
            intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 13);
            intent.setPackage(this.f1061g.getPackageName());
            if (!m544t()) {
                return false;
            }
            this.f1061g.sendBroadcast(intent);
            return false;
        }
        Log.v(f1042a, "－－－－－－send code success 6-6－－－－－－");
        this.f1077w = 16716548;
        intent2 = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
        intent2.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 0);
        intent2.setPackage(this.f1061g.getPackageName());
        if (m544t()) {
            this.f1061g.sendBroadcast(intent2);
        }
        return true;
    }

    private static int[] m499a(byte[] bArr, int i) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = bArr[i2];
            if (iArr[i2] < 0) {
                iArr[i2] = iArr[i2] + 256;
            }
        }
        return iArr;
    }

    private String m500b(byte[] bArr) {
        Log.v(f1042a, "order = " + m501b(bArr, bArr.length));
        String substring = m501b(bArr, bArr.length).substring(2, 12);
        String str = "" + ((bArr[6] & 252) >> 2) + "." + (((bArr[6] & 3) * 2) + ((bArr[7] & 128) >> 7)) + "." + ((bArr[7] & 112) >> 4);
        String str2 = "" + (((bArr[7] & 15) * 4) + ((bArr[8] & JfifUtil.MARKER_SOFn) >> 6)) + "." + ((bArr[8] & 56) >> 3) + "." + (bArr[8] & 7);
        this.f1045A = (bArr[bArr.length + -1] & 1) == 1;
        this.f1046B = (bArr[bArr.length + -1] & 2) == 2;
        this.f1047C = (bArr[bArr.length + -1] & 4) == 4;
        Log.v(f1042a, "DeviceId=" + substring + " FirmWare=" + str + " HardWare=" + str2 + " isError = " + this.f1045A + " isIdent = " + this.f1046B + " isUnident10 = " + this.f1047C);
        if (str.equals("13.5.0") || str.equals("13.6.0")) {
            this.f1075u = true;
        } else {
            this.f1075u = false;
        }
        return m489a("IDPS", new String[]{substring + "", str, str2}, new String[]{"DeviceId", "FirmWare", "HardWare"});
    }

    private String m501b(byte[] bArr, int i) {
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            String toHexString = Integer.toHexString(bArr[i2] & 255);
            if (toHexString.length() == 1) {
                toHexString = '0' + toHexString;
            }
            str = str + toHexString.toUpperCase();
        }
        return str;
    }

    private void m503b(String str) {
        long j = 4294967295L;
        Log.v(f1042a, "send code");
        if (!this.f1045A || this.f1080z) {
            int i;
            Intent intent;
            switch (f1044c) {
                case 11:
                    j = m519f(str);
                    i = 1;
                    break;
                case 12:
                    j = m519f(str);
                    i = 3;
                    break;
                case 21:
                    i = 2;
                    break;
                case 22:
                    i = 4;
                    break;
                default:
                    i = 1;
                    j = 0;
                    break;
            }
            if (this.f1047C && !this.f1080z) {
                if (m535m()) {
                    this.f1046B = true;
                } else {
                    Log.v(f1042a, "failed 10 !!!");
                    intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
                    intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 17);
                    intent.setPackage(this.f1061g.getPackageName());
                    if (m544t()) {
                        this.f1061g.sendBroadcast(intent);
                        return;
                    }
                    return;
                }
            }
            if (this.f1080z) {
                SystemClock.sleep(1500);
                AudioTrackManager.inCommunication = true;
                Log.v(f1042a, "error 4 send code directly ---> bottleId = " + j + Constants.SPACE + "version = " + i);
                m506b(str, false);
            } else {
                Log.v(f1042a, "normal bottleId = " + j + Constants.SPACE + "version = " + i);
                AudioTrackManager.inCommunication = true;
                i = m485a(j, i);
                if (i == 1) {
                    m506b(str, false);
                } else if (i == 2) {
                    m506b(str, true);
                } else if (i == 3) {
                    intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
                    intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 0);
                    intent.setPackage(this.f1061g.getPackageName());
                    if (m544t()) {
                        this.f1061g.sendBroadcast(intent);
                    }
                } else {
                    Log.e(f1042a, "connection failed :: flag 18");
                    intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
                    intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 18);
                    intent.setPackage(this.f1061g.getPackageName());
                    if (m544t()) {
                        this.f1061g.sendBroadcast(intent);
                    }
                }
            }
            if (this.f1067m != null) {
                this.f1067m.interrupt();
                this.f1067m = null;
                return;
            }
            return;
        }
        Log.v(f1042a, "error ！！！return ");
    }

    private static boolean m504b() {
        try {
            String str = (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class}).invoke(null, new Object[]{"dolby.ds.state"});
            Log.v(f1042a, "dolby.ds.state " + str);
            return str.contains(ViewProps.ON);
        } catch (Exception e) {
            Log.v(f1042a, "dolby.ds.state " + e.toString());
            return false;
        }
    }

    private boolean m506b(String str, boolean z) {
        Log.v(f1042a, "send CODE new");
        m517e(str);
        this.f1066l = m519f(str);
        m546v();
        byte[] bArr;
        byte[] bArr2;
        Intent intent;
        Intent intent2;
        if (z) {
            SystemClock.sleep(100);
            bArr = new byte[]{(byte) 80, this.f1063i[1], this.f1063i[2], this.f1063i[3], this.f1063i[4], this.f1063i[5], this.f1063i[6], this.f1063i[7], this.f1063i[8], this.f1063i[9], this.f1063i[10], this.f1063i[11], this.f1063i[12], this.f1063i[28], this.f1063i[29]};
            bArr2 = null;
            if (this.f1059e != null) {
                bArr2 = this.f1059e.sendCommand((byte) 34, bArr, 52);
            }
            if (bArr2 == null) {
                Log.e(f1042a, "－－－－－－send code failed :: flag 19－－－－－－");
                intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
                intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 19);
                intent.setPackage(this.f1061g.getPackageName());
                if (!m544t() || this.f1080z) {
                    return false;
                }
                this.f1061g.sendBroadcast(intent);
                return false;
            }
            Log.v(f1042a, "－－－－－－send code success 3-1－－－－－－");
            SystemClock.sleep(100);
            bArr = new byte[]{this.f1063i[30], this.f1063i[31], this.f1063i[32], this.f1063i[33], this.f1063i[106], this.f1063i[107], this.f1063i[108], this.f1063i[109], this.f1063i[110], this.f1063i[111], this.f1063i[112], this.f1063i[113], this.f1063i[114], this.f1063i[115], this.f1063i[122]};
            bArr2 = null;
            if (this.f1059e != null) {
                bArr2 = this.f1059e.sendCommand((byte) 33, bArr, 51);
            }
            if (bArr2 == null) {
                Log.e(f1042a, "－－－－－－send code failed :: flag 20－－－－－－");
                intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
                intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 20);
                intent.setPackage(this.f1061g.getPackageName());
                if (!m544t() || this.f1080z) {
                    return false;
                }
                this.f1061g.sendBroadcast(intent);
                return false;
            }
            Log.v(f1042a, "－－－－－－send code success 3-2－－－－－－");
            SystemClock.sleep(100);
            bArr = new byte[]{this.f1063i[123]};
            bArr2 = null;
            if (this.f1059e != null) {
                bArr2 = this.f1059e.sendCommand((byte) 32, bArr, 50);
            }
            if (bArr2 == null) {
                Log.e(f1042a, "－－－－－－send code failed :: flag 21－－－－－－");
                intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
                intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 21);
                intent.setPackage(this.f1061g.getPackageName());
                if (!m544t() || this.f1080z) {
                    return false;
                }
                this.f1061g.sendBroadcast(intent);
                return false;
            }
            Log.v(f1042a, "－－－－－－send code success 3-3－－－－－－");
            this.f1077w = 16716549;
            intent2 = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
            intent2.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 0);
            intent2.setPackage(this.f1061g.getPackageName());
            if (m544t()) {
                this.f1061g.sendBroadcast(intent2);
            }
            if (m544t() && Build.MODEL.toUpperCase().contains("MI 2")) {
                m545u();
            }
            return true;
        }
        SystemClock.sleep(100);
        bArr = new byte[]{(byte) 37, this.f1063i[1], this.f1063i[2], this.f1063i[3], this.f1063i[4], this.f1063i[5], this.f1063i[6], this.f1063i[7], this.f1063i[8], this.f1063i[9], this.f1063i[10], this.f1063i[11], this.f1063i[12], this.f1063i[28], this.f1063i[29]};
        bArr2 = null;
        if (this.f1059e != null) {
            bArr2 = this.f1059e.sendCommand((byte) 102, bArr, 66);
        }
        if (bArr2 == null) {
            Log.e(f1042a, "－－－－－－send code failed :: flag 22－－－－－－");
            intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
            intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 22);
            intent.setPackage(this.f1061g.getPackageName());
            if (!m544t() || this.f1080z) {
                return false;
            }
            this.f1061g.sendBroadcast(intent);
            return false;
        }
        Log.v(f1042a, "－－－－－－send code success 7-1－－－－－－");
        SystemClock.sleep(100);
        bArr = new byte[]{this.f1063i[30], this.f1063i[31], this.f1063i[32], this.f1063i[33], this.f1063i[36], this.f1063i[37], this.f1063i[38], this.f1063i[39], this.f1063i[40], this.f1063i[41], this.f1063i[42], this.f1063i[43], this.f1063i[44], this.f1063i[45], this.f1063i[46]};
        bArr2 = null;
        if (this.f1059e != null) {
            bArr2 = this.f1059e.sendCommand((byte) 101, bArr, 65);
        }
        if (bArr2 == null) {
            Log.e(f1042a, "－－－－－－send code failed :: flag 23－－－－－－");
            intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
            intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 23);
            intent.setPackage(this.f1061g.getPackageName());
            if (!m544t() || this.f1080z) {
                return false;
            }
            this.f1061g.sendBroadcast(intent);
            return false;
        }
        Log.v(f1042a, "－－－－－－send code success 7-2－－－－－－");
        SystemClock.sleep(100);
        bArr = new byte[]{this.f1063i[47], this.f1063i[48], this.f1063i[49], this.f1063i[50], this.f1063i[51], this.f1063i[52], this.f1063i[53], this.f1063i[54], this.f1063i[55], this.f1063i[56], this.f1063i[57], this.f1063i[58], this.f1063i[59], this.f1063i[60], this.f1063i[61]};
        bArr2 = null;
        if (this.f1059e != null) {
            bArr2 = this.f1059e.sendCommand((byte) 100, bArr, 64);
        }
        if (bArr2 == null) {
            Log.e(f1042a, "－－－－－－send code failed :: flag 24－－－－－－");
            intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
            intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 24);
            intent.setPackage(this.f1061g.getPackageName());
            if (!m544t() || this.f1080z) {
                return false;
            }
            this.f1061g.sendBroadcast(intent);
            return false;
        }
        Log.v(f1042a, "－－－－－－send code success 7-3－－－－－－");
        SystemClock.sleep(100);
        bArr = new byte[]{this.f1063i[62], this.f1063i[63], this.f1063i[64], this.f1063i[65], this.f1063i[66], this.f1063i[67], this.f1063i[68], this.f1063i[69], this.f1063i[70], this.f1063i[71], this.f1063i[72], this.f1063i[73], this.f1063i[74], this.f1063i[75], this.f1063i[76]};
        bArr2 = null;
        if (this.f1059e != null) {
            bArr2 = this.f1059e.sendCommand((byte) 99, bArr, 63);
        }
        if (bArr2 == null) {
            Log.e(f1042a, "－－－－－－send code failed :: flag 25－－－－－－");
            intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
            intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 25);
            intent.setPackage(this.f1061g.getPackageName());
            if (!m544t() || this.f1080z) {
                return false;
            }
            this.f1061g.sendBroadcast(intent);
            return false;
        }
        Log.v(f1042a, "－－－－－－send code success 7-4－－－－－－");
        SystemClock.sleep(100);
        bArr = new byte[]{this.f1063i[77], this.f1063i[78], this.f1063i[79], this.f1063i[80], this.f1063i[81], this.f1063i[82], this.f1063i[83], this.f1063i[84], this.f1063i[85], this.f1063i[86], this.f1063i[87], this.f1063i[88], this.f1063i[89], this.f1063i[90], this.f1063i[91]};
        bArr2 = null;
        if (this.f1059e != null) {
            bArr2 = this.f1059e.sendCommand((byte) 98, bArr, 62);
        }
        if (bArr2 == null) {
            Log.e(f1042a, "－－－－－－send code failed :: flag 26－－－－－－");
            intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
            intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 26);
            intent.setPackage(this.f1061g.getPackageName());
            if (!m544t() || this.f1080z) {
                return false;
            }
            this.f1061g.sendBroadcast(intent);
            return false;
        }
        Log.v(f1042a, "－－－－－－send code success 7-5－－－－－－");
        SystemClock.sleep(100);
        bArr = new byte[]{this.f1063i[92], this.f1063i[93], this.f1063i[94], this.f1063i[95], this.f1063i[96], this.f1063i[97], this.f1063i[98], this.f1063i[99], this.f1063i[106], this.f1063i[107], this.f1063i[108], this.f1063i[109], this.f1063i[110], this.f1063i[111], this.f1063i[112]};
        bArr2 = null;
        if (this.f1059e != null) {
            bArr2 = this.f1059e.sendCommand((byte) 97, bArr, 61);
        }
        if (bArr2 == null) {
            Log.e(f1042a, "－－－－－－send code failed :: flag 27－－－－－－");
            intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
            intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 27);
            intent.setPackage(this.f1061g.getPackageName());
            if (!m544t() || this.f1080z) {
                return false;
            }
            this.f1061g.sendBroadcast(intent);
            return false;
        }
        Log.v(f1042a, "－－－－－－send code success 7-6－－－－－－");
        SystemClock.sleep(100);
        bArr = new byte[]{this.f1063i[113], this.f1063i[114], this.f1063i[115], this.f1063i[122], this.f1063i[123]};
        bArr2 = null;
        if (this.f1059e != null) {
            bArr2 = this.f1059e.sendCommand((byte) 96, bArr, 60);
        }
        if (bArr2 == null) {
            Log.e(f1042a, "－－－－－－send code failed :: flag 28－－－－－－");
            intent = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
            intent.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 28);
            intent.setPackage(this.f1061g.getPackageName());
            if (!m544t() || this.f1080z) {
                return false;
            }
            this.f1061g.sendBroadcast(intent);
            return false;
        }
        Log.v(f1042a, "－－－－－－send code success 7-7－－－－－－");
        this.f1077w = 16716549;
        intent2 = new Intent(Bg1Profile.ACTION_BG1_SENDCODE_RESULT);
        intent2.putExtra(Bg1Profile.BG1_SENDCODE_RESULT, 0);
        intent2.setPackage(this.f1061g.getPackageName());
        if (m544t()) {
            this.f1061g.sendBroadcast(intent2);
        }
        if (m544t() && Build.MODEL.toUpperCase().contains("MI 2")) {
            m545u();
        }
        return true;
    }

    private String m507c(String str) {
        Log.v(f1042a, "send AppID");
        byte[] bArr = new byte[10];
        bArr[0] = (byte) -94;
        bArr[1] = Framer.STDIN_FRAME_PREFIX;
        byte[] bytes = str.getBytes();
        for (int i = 0; i < 8; i++) {
            bArr[i + 2] = bytes[i];
        }
        byte[] bArr2 = null;
        String str2 = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        if (this.f1058d != null) {
            bArr2 = this.f1058d.sendCommand((byte) -96, bArr, 2);
        }
        return bArr2 == null ? AppEventsConstants.EVENT_PARAM_VALUE_NO : bArr2[0] == (byte) 46 ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "2";
    }

    private void m508c() {
        this.f1071q = new Timer();
        this.f1072r = new ba(this);
        this.f1071q.schedule(this.f1072r, 10000);
    }

    private byte[] m510c(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = bArr[3 - i];
            bArr2[i + 4] = bArr[7 - i];
            bArr2[i + 8] = bArr[11 - i];
            bArr2[i + 12] = bArr[15 - i];
        }
        return bArr2;
    }

    private String m511d(String str) {
        Log.v(f1042a, "send AppID new");
        String str2 = "";
        byte[] bArr = new byte[3];
        bArr[0] = (byte) -48;
        byte[] bytes = str.getBytes();
        for (int i = 0; i < 2; i++) {
            bArr[i + 1] = bytes[i];
        }
        byte[] bArr2 = null;
        if (this.f1059e != null) {
            bArr2 = this.f1059e.sendCommand((byte) 0, bArr, 2);
        } else {
            Log.v(f1042a, "myCommSound_new == null");
        }
        if (bArr2 == null) {
            Log.e(f1042a, "connection failed :: flag 16");
            str2 = "";
            Intent intent = new Intent(Bg1Profile.ACTION_BG1_CONNECT_RESULT);
            intent.putExtra(Bg1Profile.BG1_CONNECT_RESULT, 16);
            intent.setPackage(this.f1061g.getPackageName());
            if (m544t()) {
                this.f1061g.sendBroadcast(intent);
            }
        } else {
            this.f1077w = 16716549;
            str2 = m500b(bArr2);
            Intent intent2 = new Intent(Bg1Profile.ACTION_BG1_IDPS);
            intent2.putExtra(Bg1Profile.BG1_IDPS, str2);
            intent2.setPackage(this.f1061g.getPackageName());
            if (m544t()) {
                this.f1061g.sendBroadcast(intent2);
            }
            intent2 = new Intent(Bg1Profile.ACTION_BG1_CONNECT_RESULT);
            intent2.setPackage(this.f1061g.getPackageName());
            intent2.putExtra(Bg1Profile.BG1_CONNECT_RESULT, 0);
            if (m544t()) {
                this.f1061g.sendBroadcast(intent2);
            }
            if (m544t() && Build.MODEL.toUpperCase().contains("MI 2")) {
                m545u();
            }
        }
        return str2;
    }

    private void m512d() {
        if (this.f1071q != null) {
            this.f1071q.purge();
            this.f1071q.cancel();
            this.f1071q = null;
        }
        if (this.f1072r != null) {
            this.f1072r.cancel();
            this.f1072r = null;
        }
    }

    private byte[] m514d(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = bArr[3 - i];
            bArr2[i + 4] = bArr[7 - i];
        }
        return bArr2;
    }

    private void m516e() {
        this.f1069o = new Timer();
        this.f1070p = new bb(this);
        this.f1069o.schedule(this.f1070p, 5000);
    }

    private void m517e(String str) {
        this.f1063i = new byte[126];
        int cRCValue;
        if (f1044c == 21) {
            System.arraycopy(ByteBufferUtil.hexStringToByte("02323C46323C01006400FA00E1030168003C003C01F4025814015E3200A0002800A0032000320046005A006E0082009600AA00B400E60104011801400168017C0190019A04CA04B10497047D046304490430042303E203BB03A2036E033A0321030702FA10273D464E6F2E4D6577B6144E6B91FA0319010B07010248"), 0, this.f1063i, 0, 122);
            cRCValue = new CrcCheck(m499a(this.f1063i, 122)).getCRCValue();
            this.f1063i[122] = (byte) ((cRCValue & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
            this.f1063i[123] = (byte) (cRCValue & 255);
            this.f1063i[124] = (byte) 0;
            this.f1063i[125] = (byte) 1;
        } else if (f1044c == 22) {
            System.arraycopy(ByteBufferUtil.hexStringToByte("02323C46323C01006400FA00E1030168003C003C01F4025814015E3200A0002800A0032000320046005A006E0082009600AA00B400E601040118012C01400168017C01900584054F051C04EB04BC04900467045303E803C903AD0393037B03530343033510273D464E6F2E4D6577B6144E6B91FA0319010B070100A3"), 0, this.f1063i, 0, 122);
            cRCValue = new CrcCheck(m499a(this.f1063i, 122)).getCRCValue();
            this.f1063i[122] = (byte) ((cRCValue & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
            this.f1063i[123] = (byte) (cRCValue & 255);
            this.f1063i[124] = (byte) 0;
            this.f1063i[125] = (byte) 1;
        } else {
            byte[] hexStringToByte = ByteBufferUtil.hexStringToByte(str);
            byte[] hexStringToByte2 = ByteBufferUtil.hexStringToByte("02323C64323C01006400FA00E1030168003C003C01F4025814015E3200A0005A00A0032000320046005A006E0082009600AA00B400E60104011801400168017C0190019A04C604A8048B04700456043E0428041D03E803E803E803B303A3039D0399039810273D464E6F646464646464646464640319010B0701");
            if (f1044c == 11) {
                hexStringToByte2 = ByteBufferUtil.hexStringToByte("02323C64323C01006400FA00E1030168003C003C01F4025814015E3200A0005A00A0032000320046005A006E0082009600AA00B400E60104011801400168017C0190019A04C604A8048B04700456043E0428041D03E803E803E803B303A3039D0399039810273D464E6F646464646464646464640319010B0701");
            } else if (f1044c == 12) {
                hexStringToByte2 = ByteBufferUtil.hexStringToByte("02323C64323C01006400FA00E1030168003C003C01F4025814015E3200A0005A00A0032000320046005A006E0082009600AA00B400E601040118012C01400168017C01900584054F051C04EB04BC0490048F047103E803C903AD0393037B03530343033510273D464E6F646464646464646464640319010B0701");
            }
            this.f1063i[0] = hexStringToByte[0];
            this.f1063i[1] = hexStringToByte[1];
            this.f1063i[2] = hexStringToByte[2];
            this.f1063i[3] = hexStringToByte[3];
            this.f1063i[4] = hexStringToByte[4];
            this.f1063i[5] = hexStringToByte[5];
            this.f1063i[6] = (byte) 1;
            int i = (int) (((((double) (hexStringToByte[6] & 255)) * 0.1d) * 1000.0d) / 20.0d);
            this.f1063i[7] = (byte) ((i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
            this.f1063i[8] = (byte) (i & 255);
            i = (int) (((((double) (hexStringToByte[7] & 255)) * 0.1d) * 1000.0d) / 20.0d);
            this.f1063i[9] = (byte) ((i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
            this.f1063i[10] = (byte) (i & 255);
            i = (int) (((((double) (hexStringToByte[8] & 255)) * 0.1d) * 1000.0d) / 20.0d);
            this.f1063i[11] = (byte) ((i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
            this.f1063i[12] = (byte) (i & 255);
            for (i = 13; i < 30; i++) {
                this.f1063i[i] = hexStringToByte2[i];
            }
            i = (int) (((((double) (hexStringToByte[9] & 255)) * 0.1d) * 1000.0d) / 20.0d);
            this.f1063i[30] = (byte) ((i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
            this.f1063i[31] = (byte) (i & 255);
            this.f1063i[32] = hexStringToByte[10];
            this.f1063i[33] = hexStringToByte[11];
            for (i = 34; i < 106; i++) {
                this.f1063i[i] = hexStringToByte2[i];
            }
            this.f1063i[106] = hexStringToByte[12];
            this.f1063i[107] = hexStringToByte[13];
            this.f1063i[108] = hexStringToByte[14];
            this.f1063i[109] = hexStringToByte[15];
            this.f1063i[110] = hexStringToByte[16];
            this.f1063i[111] = hexStringToByte[17];
            this.f1063i[112] = hexStringToByte[18];
            this.f1063i[113] = hexStringToByte[19];
            this.f1063i[114] = hexStringToByte[20];
            this.f1063i[115] = hexStringToByte[21];
            this.f1063i[116] = hexStringToByte2[116];
            this.f1063i[117] = Ascii.EM;
            this.f1063i[118] = hexStringToByte[23];
            this.f1063i[119] = Ascii.VT;
            this.f1063i[120] = (byte) 7;
            this.f1063i[121] = (byte) 1;
            cRCValue = new CrcCheck(m499a(this.f1063i, 122)).getCRCValue();
            this.f1063i[122] = (byte) ((cRCValue & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
            this.f1063i[123] = (byte) (cRCValue & 255);
            this.f1063i[124] = (byte) 0;
            this.f1063i[125] = (byte) 1;
            Log.v(f1042a, "Code process before = " + m501b(this.f1063i, this.f1063i.length));
            if (this.f1075u && (this.f1063i[123] == (byte) -6 || this.f1063i[123] == (byte) -4)) {
                if ((this.f1063i[113] & 1) == 1) {
                    this.f1063i[113] = (byte) (this.f1063i[113] & 254);
                    Log.v(f1042a, "1->0");
                } else {
                    this.f1063i[113] = (byte) (this.f1063i[113] | 1);
                    Log.v(f1042a, "0->1");
                }
                cRCValue = new CrcCheck(m499a(this.f1063i, 122)).getCRCValue();
                this.f1063i[122] = (byte) ((cRCValue & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
                this.f1063i[123] = (byte) (cRCValue & 255);
            }
            Log.v(f1042a, "Code process after = " + m501b(this.f1063i, this.f1063i.length));
        }
    }

    private void m518e(byte[] bArr) {
        int i = 0;
        if (bArr != null) {
            Log.v(f1042a, "analysis:" + m501b(bArr, bArr.length));
            if (bArr[0] != (byte) -96) {
                Log.v(f1042a, "header error");
            } else if (bArr[1] + 3 != bArr.length) {
                Log.v(f1042a, "length error");
            } else if (bArr[4] != (byte) -94) {
                Log.v(f1042a, "product id error");
            } else {
                int i2 = bArr[5] & 255;
                Intent intent;
                switch (bArr[5]) {
                    case (byte) 44:
                        switch (bArr[6]) {
                            case (byte) 1:
                                i = 1;
                                break;
                            case (byte) 2:
                                i = 2;
                                break;
                            case (byte) 3:
                                i = 3;
                                break;
                            case (byte) 4:
                                this.f1080z = true;
                                AudioTrackManager.inCommunication = false;
                                i = 4;
                                break;
                            case (byte) 5:
                                i = 5;
                                break;
                            case (byte) 6:
                                i = 6;
                                break;
                            case (byte) 7:
                                i = 7;
                                break;
                            case (byte) 8:
                                i = 8;
                                break;
                            case (byte) 9:
                                i = 9;
                                break;
                            case (byte) 10:
                                i = 10;
                                break;
                        }
                        if (this.f1052H != i2) {
                            m541q();
                            this.f1052H = i2;
                            Intent intent2 = new Intent(Bg1Profile.ACTION_BG1_MEASURE_ERROR);
                            intent2.putExtra(Bg1Profile.BG1_MEASURE_ERROR, i);
                            intent2.setPackage(this.f1061g.getPackageName());
                            if (m544t()) {
                                this.f1061g.sendBroadcast(intent2);
                                return;
                            }
                            return;
                        }
                        Log.v(f1042a, "2C already received------");
                        return;
                    case (byte) 51:
                        if (this.f1052H != i2) {
                            m541q();
                            this.f1052H = i2;
                            intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_STRIP_IN);
                            intent.setPackage(this.f1061g.getPackageName());
                            if (m544t()) {
                                this.f1061g.sendBroadcast(intent);
                                return;
                            }
                            return;
                        }
                        Log.v(f1042a, "33 already received------");
                        return;
                    case (byte) 52:
                        if (this.f1052H != i2) {
                            m541q();
                            this.f1052H = i2;
                            intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_GET_BLOOD);
                            intent.setPackage(this.f1061g.getPackageName());
                            if (m544t()) {
                                this.f1061g.sendBroadcast(intent);
                                return;
                            }
                            return;
                        }
                        Log.v(f1042a, "34 already received------");
                        return;
                    case (byte) 54:
                        if (this.f1052H != i2) {
                            m541q();
                            this.f1052H = i2;
                            int i3 = (bArr[7] & 255) + ((bArr[6] & 255) * 256);
                            if (i3 < 20 || i3 > 600) {
                                intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_ERROR);
                                intent.putExtra(Bg1Profile.BG1_MEASURE_ERROR, 1);
                                intent.setPackage(this.f1061g.getPackageName());
                                if (m544t()) {
                                    this.f1061g.sendBroadcast(intent);
                                }
                            } else {
                                intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_RESULT);
                                intent.putExtra(Bg1Profile.BG1_MEASURE_RESULT, i3);
                                intent.putExtra("dataID", C2051l.m387a(this.f1064j, C2051l.m390b(), i3));
                                intent.setPackage(this.f1061g.getPackageName());
                                if (m544t()) {
                                    this.f1061g.sendBroadcast(intent);
                                }
                            }
                            if (C2041b.f505a) {
                                new DataBaseTools(this.f1061g).addData(DataBaseConstants.TABLE_TB_BGRESULT, Make_Data_Util.makeDataSingleBg(this.f1062h, i3, iHealthDevicesManager.TYPE_BG1, this.f1064j, this.f1066l));
                                BG_InAuthor instance = BG_InAuthor.getInstance();
                                instance.initAuthor(this.f1061g, this.f1062h);
                                instance.run();
                                return;
                            }
                            return;
                        }
                        Log.v(f1042a, "36 already received------");
                        return;
                    case (byte) 59:
                        if (this.f1052H != i2) {
                            m541q();
                            this.f1052H = i2;
                            intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_STANDBY);
                            intent.setPackage(this.f1061g.getPackageName());
                            if (m544t()) {
                                this.f1061g.sendBroadcast(intent);
                                return;
                            }
                            return;
                        }
                        Log.v(f1042a, "3B already received------");
                        return;
                    case (byte) 82:
                        if (this.f1052H != i2) {
                            m541q();
                            this.f1052H = i2;
                            intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_STRIP_OUT);
                            intent.setPackage(this.f1061g.getPackageName());
                            if (m544t()) {
                                this.f1061g.sendBroadcast(intent);
                                return;
                            }
                            return;
                        }
                        Log.v(f1042a, "52 already received------");
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private long m519f(String str) {
        byte[] hexStringToByte = ByteBufferUtil.hexStringToByte(str);
        return (hexStringToByte == null || hexStringToByte.length != 30) ? 0 : (((((((long) (hexStringToByte[26] & 255)) * 256) * 256) * 256) + ((((long) (hexStringToByte[27] & 255)) * 256) * 256)) + (((long) (hexStringToByte[24] & 255)) * 256)) + ((long) (hexStringToByte[25] & 255));
    }

    private void m520f() {
        m512d();
        if (this.f1069o != null) {
            this.f1069o.purge();
            this.f1069o.cancel();
            this.f1069o = null;
        }
        if (this.f1070p != null) {
            this.f1070p.cancel();
            this.f1070p = null;
        }
    }

    private void m521f(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        Intent intent;
        if (bArr.length == 3) {
            switch (this.f1076v) {
                case 16716548:
                case 16716549:
                    if (bArr[0] == (byte) -94 && bArr[1] == (byte) 1 && bArr[2] == (byte) -93) {
                        m520f();
                        this.f1080z = false;
                        this.f1074t = false;
                        intent = new Intent(Bg1Profile.ACTION_BG1_DEVICE_READY);
                        intent.setPackage(this.f1061g.getPackageName());
                        if (m544t()) {
                            this.f1061g.sendBroadcast(intent);
                        }
                        if (this.f1067m == null) {
                            this.f1067m = new Thread(this.f1057M);
                            this.f1067m.start();
                        }
                    }
                    if (bArr[0] == (byte) -94 && bArr[1] == (byte) 3 && bArr[2] == (byte) -91) {
                        m520f();
                        this.f1080z = false;
                        this.f1074t = true;
                        intent = new Intent(Bg1Profile.ACTION_BG1_DEVICE_READY);
                        intent.setPackage(this.f1061g.getPackageName());
                        if (m544t()) {
                            this.f1061g.sendBroadcast(intent);
                        }
                        if (this.f1067m == null) {
                            this.f1067m = new Thread(this.f1057M);
                            this.f1067m.start();
                            break;
                        }
                    }
                    break;
                case 16716551:
                    break;
                default:
                    return;
            }
            if ((bArr[0] == (byte) 35 && bArr[1] == (byte) 69 && bArr[2] == (byte) 104) || ((bArr[0] == (byte) 1 && bArr[1] == (byte) 35 && bArr[2] == (byte) 70) || (AudioTrackManager.isR2017 && bArr[0] == (byte) 52 && bArr[1] == (byte) 86 && bArr[2] == Byte.MIN_VALUE))) {
                m520f();
                this.f1080z = false;
                this.f1074t = false;
                if (this.f1060f != null) {
                    this.f1060f.set1307(true);
                }
                intent = new Intent(Bg1Profile.ACTION_BG1_DEVICE_READY);
                intent.setPackage(this.f1061g.getPackageName());
                if (m544t()) {
                    this.f1061g.sendBroadcast(intent);
                }
                if (this.f1067m == null) {
                    this.f1067m = new Thread(this.f1057M);
                    this.f1067m.start();
                }
            }
        } else if (bArr.length == 5) {
            if ((bArr[0] & 15) == bArr.length - 3) {
                r1 = 0;
                for (r0 = 1; r0 < bArr.length; r0++) {
                    r1 = (byte) (r1 + bArr[r0]);
                }
                if (((byte) (((byte) (((byte) ((r1 & 240) >> 4)) + ((byte) (r1 & 15)))) << 4)) == ((byte) (bArr[0] & 240)) && bArr[1] == (byte) 0) {
                    switch (bArr[3]) {
                        case (byte) 44:
                            r0 = bArr[3] & 255;
                            r1 = bArr[2] & 255;
                            m492a((byte) 44, r1);
                            if (this.f1052H == r0 && this.f1053I == r1) {
                                Log.v(f1042a, "2C already received------");
                            } else {
                                this.f1052H = r0;
                                this.f1053I = r1;
                                intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_ERROR);
                                intent.putExtra(Bg1Profile.BG1_MEASURE_ERROR, bArr[4] & 255);
                                intent.setPackage(this.f1061g.getPackageName());
                                if (m544t()) {
                                    this.f1061g.sendBroadcast(intent);
                                }
                            }
                            if (bArr[4] == (byte) 4) {
                                this.f1080z = true;
                                AudioTrackManager.inCommunication = false;
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            }
        } else if (bArr.length == 7 && (bArr[0] & 15) == bArr.length - 3) {
            r1 = 0;
            for (r0 = 1; r0 < bArr.length; r0++) {
                r1 = (byte) (r1 + bArr[r0]);
            }
            if (((byte) (((byte) (((byte) ((r1 & 240) >> 4)) + ((byte) (r1 & 15)))) << 4)) == ((byte) (bArr[0] & 240)) && bArr[1] == (byte) 0) {
                switch (bArr[3]) {
                    case (byte) 51:
                        if (bArr[6] == (byte) 0) {
                            r0 = bArr[3] & 255;
                            r1 = bArr[2] & 255;
                            m492a((byte) 51, r1);
                            if (this.f1052H == r0 && this.f1053I == r1) {
                                Log.v(f1042a, "33 already received------");
                                return;
                            }
                            this.f1052H = r0;
                            this.f1053I = r1;
                            intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_STRIP_IN);
                            intent.setPackage(this.f1061g.getPackageName());
                            if (m544t()) {
                                this.f1061g.sendBroadcast(intent);
                                return;
                            }
                            return;
                        }
                        return;
                    case (byte) 52:
                        if (bArr[4] == (byte) 0 && bArr[5] == (byte) 0 && bArr[6] == (byte) 0) {
                            r0 = bArr[3] & 255;
                            r1 = bArr[2] & 255;
                            m492a((byte) 52, r1);
                            if (this.f1052H == r0 && this.f1053I == r1) {
                                Log.v(f1042a, "34 already received------");
                                return;
                            }
                            this.f1052H = r0;
                            this.f1053I = r1;
                            intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_GET_BLOOD);
                            intent.setPackage(this.f1061g.getPackageName());
                            if (m544t()) {
                                this.f1061g.sendBroadcast(intent);
                                return;
                            }
                            return;
                        }
                        return;
                    case (byte) 54:
                        if (bArr[6] == (byte) 0) {
                            r0 = bArr[3] & 255;
                            r1 = bArr[2] & 255;
                            m492a((byte) 54, r1);
                            if (this.f1052H == r0 && this.f1053I == r1) {
                                Log.v(f1042a, "36 already received------");
                            } else {
                                this.f1052H = r0;
                                this.f1053I = r1;
                                r1 = (bArr[5] & 255) + ((bArr[4] & 255) * 256);
                                if (r1 < 20 || r1 > 600) {
                                    intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_ERROR);
                                    intent.putExtra(Bg1Profile.BG1_MEASURE_ERROR, 1);
                                    intent.setPackage(this.f1061g.getPackageName());
                                    if (m544t()) {
                                        this.f1061g.sendBroadcast(intent);
                                    }
                                } else {
                                    intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_RESULT);
                                    intent.putExtra(Bg1Profile.BG1_MEASURE_RESULT, r1);
                                    intent.putExtra("dataID", C2051l.m387a(this.f1065k, C2051l.m390b(), r1));
                                    intent.setPackage(this.f1061g.getPackageName());
                                    if (m544t()) {
                                        this.f1061g.sendBroadcast(intent);
                                    }
                                }
                                if (C2041b.f505a) {
                                    new DataBaseTools(this.f1061g).addData(DataBaseConstants.TABLE_TB_BGRESULT, Make_Data_Util.makeDataSingleBg(this.f1062h, r1, iHealthDevicesManager.TYPE_BG1, this.f1065k, this.f1066l));
                                    BG_InAuthor instance = BG_InAuthor.getInstance();
                                    instance.initAuthor(this.f1061g, this.f1062h);
                                    instance.run();
                                }
                            }
                            if (!this.f1051G) {
                                this.f1051G = true;
                                new bg(this).start();
                                return;
                            }
                            return;
                        }
                        return;
                    case (byte) 59:
                        if (bArr[4] == (byte) 0 && bArr[5] == (byte) 0 && bArr[6] == (byte) 0) {
                            r0 = bArr[3] & 255;
                            r1 = bArr[2] & 255;
                            m492a((byte) 59, r1);
                            if (this.f1052H == r0 && this.f1053I == r1) {
                                Log.v(f1042a, "3B already received------");
                                return;
                            }
                            this.f1052H = r0;
                            this.f1053I = r1;
                            intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_STANDBY);
                            intent.setPackage(this.f1061g.getPackageName());
                            if (m544t()) {
                                this.f1061g.sendBroadcast(intent);
                                return;
                            }
                            return;
                        }
                        return;
                    case (byte) 82:
                        if (bArr[4] == (byte) 0 && bArr[5] == (byte) 0 && bArr[6] == (byte) 0) {
                            r0 = bArr[3] & 255;
                            r1 = bArr[2] & 255;
                            m492a((byte) 82, r1);
                            if (this.f1052H == r0 && this.f1053I == r1) {
                                Log.v(f1042a, "52 already received------");
                                return;
                            }
                            this.f1052H = r0;
                            this.f1053I = r1;
                            intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_STRIP_OUT);
                            intent.setPackage(this.f1061g.getPackageName());
                            if (m544t()) {
                                this.f1061g.sendBroadcast(intent);
                                return;
                            }
                            return;
                        }
                        return;
                    case (byte) 89:
                        if (bArr[4] == (byte) 0 && bArr[5] == (byte) 0 && bArr[6] == (byte) 0) {
                            r0 = bArr[3] & 255;
                            m492a((byte) 89, bArr[2] & 255);
                            if (!this.f1051G) {
                                this.f1051G = true;
                                new aw(this).start();
                                return;
                            }
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private String m523g() {
        String str = "appID";
        str = new C2040a(this.f1061g).m361a();
        return str.substring(str.length() - 8, str.length());
    }

    public static String getBottleInfoFromQR(String QRCode) {
        int i = 25;
        Log.p(f1042a, Level.INFO, "getBottleInfoFromQR", new Object[]{QRCode});
        if (m497a(QRCode)) {
            byte[] hexStringToByte = ByteBufferUtil.hexStringToByte(QRCode);
            if (hexStringToByte == null || hexStringToByte.length != 30) {
                return new JSONObject().toString();
            }
            long j = (((((((long) (hexStringToByte[26] & 255)) * 256) * 256) * 256) + ((((long) (hexStringToByte[27] & 255)) * 256) * 256)) + (((long) (hexStringToByte[24] & 255)) * 256)) + ((long) (hexStringToByte[25] & 255));
            int i2 = (hexStringToByte[24] & 254) >> 1;
            int i3 = ((hexStringToByte[24] & 1) * 8) + ((hexStringToByte[25] & 224) >> 5);
            int i4 = hexStringToByte[25] & 31;
            if (i2 == 15 && i3 == 1 && i4 == 15) {
                i = 10;
            } else {
                int i5 = hexStringToByte[22] & 255;
                if (i5 <= 25) {
                    i = i5;
                }
            }
            String str = "20" + i2 + "-" + (i3 > 10 ? Integer.valueOf(i3) : AppEventsConstants.EVENT_PARAM_VALUE_NO + i3) + "-" + (i4 > 10 ? Integer.valueOf(i4) : AppEventsConstants.EVENT_PARAM_VALUE_NO + i4);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormat.DATE_PATTERN_1);
            try {
                str = simpleDateFormat.format(simpleDateFormat.parse(str));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return m489a("bottleInfo", new String[]{j + "", str, i + ""}, new String[]{"bottleId", "overDate", "stripNum"});
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("description", "QRCode format error!");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static Bg1Control getInstance() {
        return bh.f1294a;
    }

    private String m525h() {
        String str = "appID";
        return new C2040a(this.f1061g).m361a().substring(0, 2);
    }

    private void m528i() {
        m539o();
        this.f1080z = false;
        this.f1078x = false;
        this.f1075u = false;
        Intent intent;
        if (m531k()) {
            String a = m488a(2);
            this.f1064j = a;
            if (a.equals("")) {
                Log.e(f1042a, "connection failed :: flag 2");
                intent = new Intent(Bg1Profile.ACTION_BG1_CONNECT_RESULT);
                intent.putExtra(Bg1Profile.BG1_CONNECT_RESULT, 2);
                intent.setPackage(this.f1061g.getPackageName());
                if (m544t()) {
                    this.f1061g.sendBroadcast(intent);
                    return;
                }
                return;
            }
            a = m489a("IDPS", new String[]{a, "13.4.0", "13.4.0"}, new String[]{"DeviceId", "FirmWare", "HardWare"});
            Intent intent2 = new Intent(Bg1Profile.ACTION_BG1_IDPS);
            intent2.putExtra(Bg1Profile.BG1_IDPS, a);
            intent2.setPackage(this.f1061g.getPackageName());
            if (m544t()) {
                this.f1061g.sendBroadcast(intent2);
            }
            a = m507c(m523g());
            Log.v(f1042a, "isRem " + a);
            if (a.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                this.f1048D = true;
                this.f1077w = 16716548;
                intent = new Intent(Bg1Profile.ACTION_BG1_CONNECT_RESULT);
                intent.putExtra(Bg1Profile.BG1_CONNECT_RESULT, 0);
                intent.setPackage(this.f1061g.getPackageName());
                if (m544t()) {
                    this.f1061g.sendBroadcast(intent);
                    return;
                }
                return;
            } else if (a.equals("2")) {
                this.f1048D = false;
                if (m534l()) {
                    this.f1077w = 16716548;
                    intent = new Intent(Bg1Profile.ACTION_BG1_CONNECT_RESULT);
                    intent.putExtra(Bg1Profile.BG1_CONNECT_RESULT, 0);
                    intent.setPackage(this.f1061g.getPackageName());
                    if (m544t()) {
                        this.f1061g.sendBroadcast(intent);
                        return;
                    }
                    return;
                }
                Log.e(f1042a, "connection failed :: flag 5");
                intent = new Intent(Bg1Profile.ACTION_BG1_CONNECT_RESULT);
                intent.putExtra(Bg1Profile.BG1_CONNECT_RESULT, 5);
                intent.setPackage(this.f1061g.getPackageName());
                if (m544t()) {
                    this.f1061g.sendBroadcast(intent);
                    return;
                }
                return;
            } else {
                Log.e(f1042a, "connection failed :: flag 3");
                intent = new Intent(Bg1Profile.ACTION_BG1_CONNECT_RESULT);
                intent.putExtra(Bg1Profile.BG1_CONNECT_RESULT, 3);
                intent.setPackage(this.f1061g.getPackageName());
                if (m544t()) {
                    this.f1061g.sendBroadcast(intent);
                    return;
                }
                return;
            }
        }
        Log.e(f1042a, "connection failed :: flag 1");
        intent = new Intent(Bg1Profile.ACTION_BG1_CONNECT_RESULT);
        intent.putExtra(Bg1Profile.BG1_CONNECT_RESULT, 1);
        intent.setPackage(this.f1061g.getPackageName());
        if (m544t()) {
            this.f1061g.sendBroadcast(intent);
        }
    }

    private void m530j() {
        m511d(m525h());
    }

    private boolean m531k() {
        Log.v(f1042a, "handshake----");
        byte[] bArr = null;
        if (this.f1058d != null) {
            bArr = this.f1058d.sendCommand(0);
        }
        if (bArr == null) {
            return false;
        }
        Log.v(f1042a, "handshake success----");
        return true;
    }

    private boolean m534l() {
        int i;
        byte[] bArr = new byte[16];
        byte[] bArr2 = new byte[16];
        byte[] bArr3 = new byte[16];
        byte[] bArr4 = new byte[16];
        bArr3 = new byte[16];
        byte[] bArr5 = new byte[16];
        byte[] ka = f1043b.getKa("BG1304");
        new Random(System.currentTimeMillis()).nextBytes(bArr);
        for (i = 0; i < 16; i++) {
            if (bArr[i] < (byte) 0) {
                bArr[i] = (byte) (0 - bArr[i]);
            }
        }
        byte[] bArr6 = new byte[18];
        bArr6[0] = (byte) -94;
        bArr6[1] = (byte) -6;
        for (i = 0; i < bArr.length; i++) {
            bArr6[i + 2] = bArr[i];
        }
        byte[] sendCommand = this.f1058d != null ? this.f1058d.sendCommand((byte) -96, bArr6, 43) : null;
        if (sendCommand == null) {
            Log.v(f1042a, "－－－－－－identify first step error－－－－－－");
            return false;
        }
        byte[] sendCommand2 = this.f1058d != null ? this.f1058d.sendCommand((byte) -94, new byte[]{(byte) -94, (byte) -5}, 42) : null;
        if (sendCommand2 == null) {
            Log.v(f1042a, "－－－－－－identify second step error－－－－－－");
            return false;
        }
        bArr6 = this.f1058d != null ? this.f1058d.sendCommand((byte) -95, new byte[]{(byte) -94, (byte) -5}, 41) : null;
        if (bArr6 == null) {
            Log.v(f1042a, "－－－－－－identify third step error－－－－－－");
            return false;
        }
        for (i = 0; i < 16; i++) {
            bArr5[i] = sendCommand[i + 1];
            bArr2[i] = sendCommand2[i + 1];
            bArr4[i] = bArr6[i + 1];
        }
        Log.v(f1042a, "-----------------------------------------");
        Log.v(f1042a, "ID =" + m501b(bArr5, 16));
        Log.v(f1042a, "R1'=" + m501b(bArr2, 16));
        Log.v(f1042a, "R2'=" + m501b(bArr4, 16));
        Log.v(f1042a, "-----------------------------------------");
        bArr3 = new byte[16];
        bArr3 = XXTEA.encrypt(m510c(bArr5), ka);
        bArr6 = m510c(XXTEA.encrypt(m510c(bArr2), bArr3));
        Log.v(f1042a, "-----------------------------------------");
        Log.v(f1042a, "R1     =" + m501b(bArr, 16));
        Log.v(f1042a, "R1_back=" + m501b(bArr6, 16));
        Log.v(f1042a, "-----------------------------------------");
        if (m501b(bArr6, 16).equals(m501b(bArr, 16))) {
            bArr6 = m510c(XXTEA.encrypt(m510c(bArr4), bArr3));
            sendCommand2 = new byte[18];
            sendCommand2[0] = (byte) -94;
            sendCommand2[1] = (byte) -4;
            for (i = 0; i < bArr6.length; i++) {
                sendCommand2[i + 2] = bArr6[i];
            }
            bArr3 = null;
            if (this.f1058d != null) {
                bArr3 = this.f1058d.sendCommand((byte) -96, sendCommand2, 40);
            }
            if (bArr3 == null) {
                return false;
            }
            if (bArr3[0] == (byte) -3) {
                Log.v(f1042a, "identify success");
                return true;
            }
            Log.v(f1042a, "returnDataFD error");
        } else {
            Log.v(f1042a, "R1 do not match the R1 back～～");
        }
        return false;
    }

    private boolean m535m() {
        int i;
        byte[] bArr = new byte[8];
        byte[] bArr2 = new byte[8];
        byte[] bArr3 = new byte[8];
        byte[] bArr4 = new byte[8];
        bArr3 = new byte[8];
        byte[] bArr5 = new byte[8];
        bArr3 = new byte[16];
        bArr3 = this.f1074t ? f1043b.getKa("BGAG680") : f1043b.getKa("BG1305");
        new Random(System.currentTimeMillis()).nextBytes(bArr);
        for (i = 0; i < 8; i++) {
            if (bArr[i] < (byte) 0) {
                bArr[i] = (byte) (0 - bArr[i]);
            }
        }
        byte[] bArr6 = new byte[9];
        bArr6[0] = (byte) -6;
        for (i = 0; i < bArr.length; i++) {
            bArr6[i + 1] = bArr[i];
        }
        byte[] sendCommand = this.f1059e != null ? this.f1059e.sendCommand((byte) 0, bArr6, 42) : null;
        if (sendCommand == null) {
            return false;
        }
        Log.v(f1042a, "returnDataFB_11 =" + m501b(sendCommand, sendCommand.length));
        bArr6 = this.f1059e != null ? this.f1059e.sendCommand((byte) -95, new byte[]{(byte) -5}, 41) : null;
        if (bArr6 == null) {
            return false;
        }
        int i2;
        Log.v(f1042a, "returnDataFB_10 =" + m501b(bArr6, bArr6.length));
        for (i = 0; i < 8; i++) {
            bArr5[i] = sendCommand[i + 1];
            if (i < 6) {
                bArr2[i] = sendCommand[(i + 8) + 1];
            } else {
                bArr2[i] = bArr6[i - 6];
            }
            bArr4[i] = bArr6[i + 2];
        }
        Log.v(f1042a, "-----------------------------------------");
        Log.v(f1042a, "ID =" + m501b(bArr5, 8));
        Log.v(f1042a, "R1'=" + m501b(bArr2, 8));
        Log.v(f1042a, "R2'=" + m501b(bArr4, 8));
        Log.v(f1042a, "-----------------------------------------");
        byte[] bArr7 = new byte[8];
        bArr7 = XXTEA.encrypt(m514d(bArr5), bArr3);
        bArr6 = new byte[16];
        for (i2 = 0; i2 < 8; i2++) {
            bArr6[i2] = bArr7[i2];
        }
        bArr3 = m514d(XXTEA.encrypt(m514d(bArr2), bArr6));
        Log.v(f1042a, "-----------------------------------------");
        Log.v(f1042a, "R1     =" + m501b(bArr, 8));
        Log.v(f1042a, "R1_back=" + m501b(bArr3, 8));
        Log.v(f1042a, "-----------------------------------------");
        if (m501b(bArr3, 8).equals(m501b(bArr, 8))) {
            bArr3 = XXTEA.encrypt(m514d(bArr4), bArr6);
            Log.v(f1042a, "-----------------------------------------");
            Log.v(f1042a, "R2        =" + m501b(bArr3, 8));
            bArr7 = m514d(bArr3);
            Log.v(f1042a, "R2_reverse=" + m501b(bArr7, 8));
            Log.v(f1042a, "-----------------------------------------");
            bArr6 = new byte[9];
            bArr6[0] = (byte) -4;
            for (i2 = 0; i2 < bArr7.length; i2++) {
                bArr6[i2 + 1] = bArr7[i2];
            }
            bArr3 = null;
            if (this.f1059e != null) {
                bArr3 = this.f1059e.sendCommand((byte) -96, bArr6, 40);
            }
            if (bArr3 == null) {
                return false;
            }
            if (bArr3[0] == (byte) -3) {
                Log.v(f1042a, "identify success");
                return true;
            }
            Log.v(f1042a, "returnDataFD error");
        } else {
            Log.v(f1042a, "R1 do not match the R1 back～～");
        }
        return false;
    }

    private void m537n() {
        m540p();
        Log.v(f1042a, "start BG1 communication");
        Log.v(f1042a, "manufacturer = " + Build.MANUFACTURER);
        Log.v(f1042a, "brand = " + Build.BRAND);
        Log.v(f1042a, "model = " + Build.MODEL);
        Log.v(f1042a, "RELEASE = " + VERSION.RELEASE);
        this.f1060f = new TunnerThread();
        this.f1060f.msgSubject.detach(this);
        this.f1060f.msgSubject.attach(this);
        this.f1059e = new CommSound_new(this.f1060f);
        AudioTrackManager.inCommunication = true;
        AudioTrackManager.getInstance().initManager();
        new Thread(new be(this)).start();
        m508c();
        this.f1078x = true;
        if (this.f1076v == 16716548) {
            m516e();
        }
    }

    private void m539o() {
        m540p();
        this.f1058d = new CommSound(this.f1060f);
        AudioTrackManager.inCommunication = true;
    }

    private void m540p() {
        AudioTrackManager.inCommunication = false;
        if (this.f1059e != null) {
            this.f1059e = null;
        }
        if (this.f1058d != null) {
            this.f1058d = null;
        }
        if (this.f1067m != null) {
            this.f1067m.interrupt();
            this.f1067m = null;
        }
        if (this.f1068n != null) {
            this.f1068n.interrupt();
            this.f1068n = null;
        }
        this.f1052H = 0;
        this.f1053I = 0;
        m520f();
    }

    private void m541q() {
        new Timer().schedule(new bf(this), 1200);
    }

    private void m542r() {
        int streamMaxVolume = this.f1073s.getStreamMaxVolume(3);
        this.f1073s.setStreamVolume(3, streamMaxVolume, 0);
        r0 = streamMaxVolume != this.f1073s.getStreamVolume(3) ? streamMaxVolume - 2 : streamMaxVolume;
        while (r0 > (streamMaxVolume * 2) / 3 && r0 != this.f1073s.getStreamVolume(3)) {
            r0--;
            this.f1073s.setStreamVolume(3, r0, 0);
        }
        if (VERSION.RELEASE.compareTo("4.3") >= 0 && r0 < streamMaxVolume) {
            if (this.f1049E) {
                this.f1073s.setStreamVolume(3, streamMaxVolume, 1);
            } else {
                this.f1073s.setStreamVolume(3, streamMaxVolume, 0);
            }
        }
        Log.v(f1042a, "set volume ----------> " + this.f1073s.getStreamVolume(3));
    }

    private void m543s() {
        this.f1073s.setStreamVolume(3, this.f1079y, 0);
        Log.v(f1042a, "reset volume ----------> " + this.f1073s.getStreamVolume(3));
    }

    private boolean m544t() {
        return this.f1050F == 1;
    }

    private void m545u() {
        m546v();
        this.f1055K = new Timer();
        this.f1056L = new ay(this);
        this.f1055K.schedule(this.f1056L, 500, 1500);
    }

    private void m546v() {
        if (this.f1056L != null) {
            this.f1056L.cancel();
            this.f1056L = null;
        }
        if (this.f1055K != null) {
            this.f1055K.purge();
            this.f1055K.cancel();
            this.f1055K = null;
        }
    }

    public void connect() {
        if (this.f1050F != 1) {
            this.f1050F = 1;
            this.f1079y = this.f1073s.getStreamVolume(3);
            Log.v(f1042a, "volume    ----------> " + this.f1073s.getStreamVolume(3));
            Log.v(f1042a, "volume MAX----------> " + this.f1073s.getStreamMaxVolume(3));
            m537n();
        }
    }

    public void disconnect() {
        this.f1050F = 0;
        if (this.f1060f != null) {
            this.f1060f.close();
            this.f1060f = null;
        }
        m540p();
        AudioTrackManager.getInstance().stop();
        m546v();
    }

    public void init(Context context, String userName, int filter) {
        Log.p(f1042a, Level.INFO, "init", new Object[]{userName, Integer.valueOf(filter)});
        this.f1049E = false;
        this.f1061g = context;
        this.f1062h = userName;
        this.f1073s = (AudioManager) context.getSystemService("audio");
        switch (filter) {
            case 16716548:
            case 16716549:
            case 16716551:
                this.f1076v = filter;
                break;
            default:
                this.f1076v = 16716548;
                break;
        }
        if (m504b()) {
            Intent intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_ERROR);
            intent.putExtra(Bg1Profile.BG1_MEASURE_ERROR, 401);
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent);
        }
    }

    public void init(Context context, String userName, int filter, boolean showUI) {
        Log.p(f1042a, Level.INFO, "init", new Object[]{userName, Integer.valueOf(filter), Boolean.valueOf(showUI)});
        this.f1049E = showUI;
        this.f1061g = context;
        this.f1062h = userName;
        this.f1073s = (AudioManager) context.getSystemService("audio");
        switch (filter) {
            case 16716548:
            case 16716549:
            case 16716551:
                this.f1076v = filter;
                break;
            default:
                this.f1076v = 16716548;
                break;
        }
        if (m504b()) {
            Intent intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_ERROR);
            intent.putExtra(Bg1Profile.BG1_MEASURE_ERROR, 401);
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent);
        }
    }

    public void msgBytes(byte[] msg) {
        Log.v(f1042a, "msg:" + m501b(msg, msg.length));
        switch (msg.length) {
            case 3:
            case 5:
            case 7:
                if (this.f1078x) {
                    m521f(msg);
                    return;
                }
                return;
            case 10:
                m518e(msg);
                return;
            default:
                Log.v(f1042a, "------------- in communication process -------------");
                return;
        }
    }

    public void sendCode(String QRCode) {
        Log.p(f1042a, Level.INFO, "sendCode", new Object[]{QRCode});
        if (m497a(QRCode)) {
            f1044c = 11;
            new bc(this, QRCode).start();
            return;
        }
        Intent intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_ERROR);
        intent.putExtra(Bg1Profile.BG1_MEASURE_ERROR, 400);
        intent.setPackage(this.f1061g.getPackageName());
        if (m544t()) {
            this.f1061g.sendBroadcast(intent);
        }
    }

    public void sendCode(String QRCode, int stripType, int measureType) {
        Log.p(f1042a, Level.INFO, "sendCode", new Object[]{QRCode, Integer.valueOf(stripType), Integer.valueOf(measureType)});
        if (stripType > 2 || stripType < 1 || measureType > 2 || measureType < 1 || (stripType == 1 && !m497a(QRCode))) {
            Intent intent = new Intent(Bg1Profile.ACTION_BG1_MEASURE_ERROR);
            intent.putExtra(Bg1Profile.BG1_MEASURE_ERROR, 400);
            intent.setPackage(this.f1061g.getPackageName());
            if (m544t()) {
                this.f1061g.sendBroadcast(intent);
                return;
            }
            return;
        }
        f1044c = (stripType * 10) + measureType;
        new bd(this, QRCode).start();
    }
}
