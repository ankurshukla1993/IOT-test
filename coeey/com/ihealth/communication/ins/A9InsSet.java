package com.ihealth.communication.ins;

import android.content.Context;
import android.os.SystemClock;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.base.protocol.WifiCommProtocol;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import com.ihealth.communication.cloud.data.DataBaseTools;
import com.ihealth.communication.cloud.data.HS_InAuthor;
import com.ihealth.communication.cloud.data.Make_Data_Util;
import com.ihealth.communication.cloud.p002a.C2041b;
import com.ihealth.communication.cloud.p002a.C2051l;
import com.ihealth.communication.control.HsProfile;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Hs5DataUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import com.ihealth.communication.utils.MD5;
import com.ihealth.communication.utils.PublicMethod;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class A9InsSet extends IdentifyIns implements NewDataCallback {
    Timer f1604a;
    TimerTask f1605b;
    private String f1606c = "www.ihealthcloud.net";
    private String f1607l = "/api/LowerMachine/LowerMachineProcess.htm";
    private BaseCommProtocol f1608m;
    private ArrayList f1609n = new ArrayList();
    private String f1610o = "";
    private String f1611p = "";
    private InsCallback f1612q;
    private String f1613r;
    private BaseCommCallback f1614s;
    private UserListInHs5 f1615t;
    private int f1616u = 0;
    private Context f1617v;
    private String f1618w;
    private Hs5ManageCommand f1619x = null;
    private byte[] f1620y = new byte[]{(byte) 46, (byte) 46, (byte) 46, (byte) 46, (byte) 46, (byte) 46, (byte) 46, (byte) 46, (byte) 46, (byte) 46, (byte) 46, (byte) 46, (byte) 46, (byte) 46, (byte) 46, (byte) 46};
    private Status f1621z = Status.Idle;

    class C20981 extends TimerTask {
        final /* synthetic */ A9InsSet f1589a;

        C20981(A9InsSet this$0) {
            this.f1589a = this$0;
        }

        public void run() {
            this.f1589a.stopTimeoutTimer();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("error", 700);
                this.f1589a.f1612q.onNotify(this.f1589a.f1610o, this.f1589a.f1613r, HsProfile.ACTION_ERROR_HS, jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    enum Command {
        Unknown(0),
        IpAddress_Verifying(239),
        Verification_Feedback(251),
        Verification_Success(253),
        Verification_Failed(254),
        SetCloudAddress_Part1(89),
        SetCloudAddress_Part2(90),
        CreateUser_Management(49),
        AddNewUser(81),
        DeleteUser(82),
        UpdateUserInfo(84),
        MeasureConnection(50),
        HistoryConnection(51),
        GetMemoryData(65),
        GetHistoryData(66),
        RealTimeData(53),
        StableWeight(54),
        ImpedanceWeight(55),
        ResultData(56),
        Measurement_Stop(57),
        Error(52);
        
        int f1592a;

        private Command(int what) {
            this.f1592a = what;
        }

        static Command m824a(int i) {
            for (Command command : values()) {
                if (command.f1592a == i) {
                    return command;
                }
            }
            return Unknown;
        }

        public String toString() {
            return String.format("%s(0x%02X)", new Object[]{name(), Integer.valueOf(this.f1592a)});
        }
    }

    abstract class Hs5ManageCommand {
        protected int f1593f = 0;
        protected int f1594g = 0;
        final /* synthetic */ A9InsSet f1595h;

        protected Hs5ManageCommand(A9InsSet a9InsSet, int userPosition, int userId) {
            this.f1595h = a9InsSet;
            this.f1593f = userPosition;
            this.f1594g = userId;
        }

        abstract void mo5527a();

        public int getUserId() {
            return this.f1594g;
        }

        public int getUserPosition() {
            return this.f1593f;
        }
    }

    class Hs5AddUserCommand extends Hs5ManageCommand {
        protected int f1596a = 0;
        protected int f1597b = 0;
        protected int f1598c = 0;
        protected int f1599d = 0;
        final /* synthetic */ A9InsSet f1600e;

        public Hs5AddUserCommand(A9InsSet a9InsSet, int userPosition, int userId, int age, int height, int isSportsman, int gender) {
            this.f1600e = a9InsSet;
            super(a9InsSet, userPosition, userId);
            this.f1596a = age;
            this.f1597b = height;
            this.f1598c = isSportsman;
            this.f1599d = gender;
        }

        void mo5527a() {
            byte[] bArr = new byte[11];
            bArr[0] = (byte) -87;
            bArr[1] = (byte) 81;
            bArr[2] = (byte) this.f;
            byte[] intToByteForuserId = ByteBufferUtil.intToByteForuserId(this.g);
            bArr[3] = intToByteForuserId[0];
            bArr[4] = intToByteForuserId[1];
            bArr[5] = intToByteForuserId[2];
            bArr[6] = intToByteForuserId[3];
            if (this.f1596a > 100) {
                this.f1596a = 99;
            } else if (this.f1596a < 6) {
                this.f1596a = 7;
            }
            bArr[7] = (byte) this.f1596a;
            if (this.f1597b > 220) {
                this.f1597b = 219;
            } else if (this.f1597b <= 80) {
                this.f1597b = 81;
            }
            bArr[8] = (byte) this.f1597b;
            bArr[9] = (byte) this.f1598c;
            bArr[10] = (byte) this.f1599d;
            this.f1600e.f1608m.packageData(bArr);
        }
    }

    class Hs5DeleteUserCommand extends Hs5ManageCommand {
        final /* synthetic */ A9InsSet f1601a;

        public Hs5DeleteUserCommand(A9InsSet a9InsSet, int userPosition, int userId) {
            this.f1601a = a9InsSet;
            super(a9InsSet, userPosition, userId);
        }

        void mo5527a() {
            r0 = new byte[7];
            byte[] intToByteForuserId = ByteBufferUtil.intToByteForuserId(this.g);
            r0[3] = intToByteForuserId[0];
            r0[4] = intToByteForuserId[1];
            r0[5] = intToByteForuserId[2];
            r0[6] = intToByteForuserId[3];
            this.f1601a.f1608m.packageData(r0);
        }
    }

    class Hs5UpdateUserCommand extends Hs5AddUserCommand {
        final /* synthetic */ A9InsSet f1602i;

        public Hs5UpdateUserCommand(A9InsSet a9InsSet, int userPosition, int userId, int age, int height, int isSportsman, int gender) {
            this.f1602i = a9InsSet;
            super(a9InsSet, userPosition, userId, age, height, isSportsman, gender);
        }

        void mo5527a() {
            byte[] bArr = new byte[11];
            bArr[0] = (byte) -87;
            bArr[1] = (byte) 84;
            bArr[2] = (byte) this.f;
            byte[] intToByteForuserId = ByteBufferUtil.intToByteForuserId(this.g);
            bArr[3] = intToByteForuserId[0];
            bArr[4] = intToByteForuserId[1];
            bArr[5] = intToByteForuserId[2];
            bArr[6] = intToByteForuserId[3];
            if (this.a > 100) {
                this.a = 99;
            } else if (this.a < 6) {
                this.a = 7;
            }
            bArr[7] = (byte) this.a;
            if (this.b > 220) {
                this.b = 219;
            } else if (this.b <= 80) {
                this.b = 81;
            }
            bArr[8] = (byte) this.b;
            bArr[9] = (byte) this.c;
            bArr[10] = (byte) this.d;
            this.f1602i.f1608m.packageData(bArr);
        }
    }

    public enum Status {
        Idle,
        Management,
        Measurement,
        GetMemoryData
    }

    public A9InsSet(String userName, Context context, BaseComm comm, String deviceMac, String deviceIp, InsCallback insCallback, BaseCommCallback baseCommCallback, String type, UserListInHs5 userListInHs5) {
        m832a("A9InsSet_Constructor", userName, deviceMac, deviceIp, type, userListInHs5);
        this.f1610o = deviceMac;
        this.f1611p = deviceIp;
        this.f1612q = insCallback;
        this.f1618w = userName;
        this.f1614s = baseCommCallback;
        this.f1613r = type;
        this.f1615t = userListInHs5;
        this.f1617v = context;
        this.f1608m = new WifiCommProtocol(context, comm, deviceMac, deviceIp, type, baseCommCallback, insCallback);
        this.f1608m.setInsSet(this);
        comm.addCommNotify(deviceMac, this.f1608m);
        m759a(insCallback, deviceMac, type, comm);
    }

    private void m830a() {
        int i;
        int firstFreeInScale = this.f1615t.getFirstFreeInScale();
        if (this.f1615t.m1030a() == -1) {
            Log.w("A9InsSet", "the user isn't in scale");
            if (firstFreeInScale == -1) {
                i = 3;
                Log.w("A9InsSet", " the user scale is full need delete");
            } else {
                i = 2;
                Log.w("A9InsSet", "the scale has empty position");
            }
        } else {
            i = 1;
            Log.w("A9InsSet", "the user is in scale");
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(HsProfile.USERINFO_IN_HS, i);
            jSONObject.put(HsProfile.USERPOSITION_HS, this.f1615t.m1030a());
            jSONObject.put(HsProfile.EMPTYPOSITION_HS, firstFreeInScale);
            JSONArray jSONArray = new JSONArray();
            for (int put : this.f1615t.getStates()) {
                jSONArray.put(put);
            }
            jSONObject.put("status", jSONArray);
            this.f1612q.onNotify(this.f1610o, this.f1613r, HsProfile.ACTION_MANAGEMENT_HS, jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m831a(int i, int i2, String str, byte[] bArr) {
        boolean z = true;
        byte b = (byte) 0;
        m757a(i);
        int i3;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        switch (Command.m824a(i)) {
            case IpAddress_Verifying:
                if (ByteBufferUtil.Bytes2HexString(bArr).matches(ByteBufferUtil.Bytes2HexString(this.f1620y))) {
                    Log.w("A9InsSet", "Scale is now communicating with i-cloud.");
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (byte b2 : bArr) {
                        stringBuilder.append(Character.toString((char) b2));
                    }
                    Log.i("A9InsSet", "Scale is now communicating with " + stringBuilder.toString());
                }
                this.f1614s.onConnectionStateChange(this.f1610o, this.f1613r, 2, 0, null);
                return;
            case Verification_Feedback:
                byte[] a = m762a(ByteBufferUtil.bufferCut(bArr, 0, 48), iHealthDevicesManager.TYPE_HS5, (byte) -87);
                m758a(252, 4000, 253, 254);
                this.f1608m.packageData(a);
                return;
            case Verification_Success:
                setCloud1(this.f1606c);
                return;
            case Verification_Failed:
                this.f1614s.onConnectionStateChange(this.f1610o, this.f1613r, 2, 0, null);
                return;
            case SetCloudAddress_Part1:
                if (bArr[0] == (byte) 1) {
                    setCloud2(this.f1607l);
                    return;
                }
                Log.w("A9InsSet", "Set cloud address part 1 fail");
                this.f1614s.onConnectionStateChange(this.f1610o, this.f1613r, 2, 0, null);
                return;
            case SetCloudAddress_Part2:
                if (bArr[0] == (byte) 1) {
                    this.f1614s.onConnectionStateChange(this.f1610o, this.f1613r, 1, 0, null);
                    return;
                }
                Log.w("A9InsSet", "Set cloud address part 2 fail");
                this.f1614s.onConnectionStateChange(this.f1610o, this.f1613r, 2, 0, null);
                return;
            case CreateUser_Management:
                this.f1615t.checkUserInHs5(this.f1616u, ByteBufferUtil.Bytes2HexString(bArr));
                m830a();
                return;
            case AddNewUser:
                if (bArr[0] != (byte) 1) {
                    Log.w("A9InsSet", "Add new user fail");
                    z = false;
                } else if (this.f1619x == null || !(this.f1619x instanceof Hs5AddUserCommand)) {
                    Log.e("A9InsSet", "mManageCommand = " + this.f1619x);
                } else {
                    this.f1615t.m1031a(this.f1619x.getUserPosition(), this.f1619x.getUserId());
                }
                try {
                    jSONObject = new JSONObject();
                    jSONObject.put(HsProfile.ADDUSER_RESULT_HS, z);
                    this.f1612q.onNotify(this.f1610o, this.f1613r, HsProfile.ACTION_ADDUSER_HS, jSONObject.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.f1619x = null;
                return;
            case DeleteUser:
                if (bArr[0] != (byte) 1) {
                    Log.w("A9InsSet", "Delete user fail");
                    z = false;
                } else if (this.f1619x == null || !(this.f1619x instanceof Hs5DeleteUserCommand)) {
                    Log.e("A9InsSet", "mManageCommand = " + this.f1619x);
                } else {
                    this.f1615t.m1031a(this.f1619x.getUserPosition(), -1);
                }
                try {
                    jSONObject = new JSONObject();
                    jSONObject.put(HsProfile.DELETEUSER_RESULT_HS, z);
                    this.f1612q.onNotify(this.f1610o, this.f1613r, HsProfile.ACTION_DELETEUSER_HS, jSONObject.toString());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                this.f1619x = null;
                return;
            case UpdateUserInfo:
                if (bArr[0] != (byte) 1) {
                    Log.w("A9InsSet", "Update user information fail");
                    z = false;
                } else if (this.f1619x == null || !(this.f1619x instanceof Hs5UpdateUserCommand)) {
                    Log.e("A9InsSet", "mManageCommand = " + this.f1619x);
                } else {
                    this.f1615t.m1031a(this.f1619x.getUserPosition(), this.f1619x.getUserId());
                }
                try {
                    jSONObject = new JSONObject();
                    jSONObject.put(HsProfile.UPDATEUSER_RESULT_HS, z);
                    this.f1612q.onNotify(this.f1610o, this.f1613r, HsProfile.ACTION_UPDATEUSER_HS, jSONObject.toString());
                } catch (Exception e22) {
                    e22.printStackTrace();
                }
                this.f1619x = null;
                return;
            case MeasureConnection:
                boolean z2;
                if (i2 != 160) {
                    Log.w("A9InsSet", "Measure connection fail");
                } else {
                    z2 = true;
                }
                try {
                    jSONObject = new JSONObject();
                    jSONObject.put(HsProfile.MEASUREMENT_CONNECTION_ESTABLISH_RESULT, z2);
                    this.f1612q.onNotify(this.f1610o, this.f1613r, HsProfile.ACTION_MEASUREMENT_HS, jSONObject.toString());
                    return;
                } catch (Exception e222) {
                    e222.printStackTrace();
                    return;
                }
            case HistoryConnection:
                if (bArr[0] == (byte) 1) {
                    Log.w("A9InsSet", "The user isn't in scale");
                    this.f1612q.onNotify(this.f1610o, this.f1613r, HsProfile.ACTION_NO_SPECIFIED_USER, null);
                    return;
                } else if (bArr[0] == (byte) 2) {
                    transMemoryPara();
                    return;
                } else {
                    return;
                }
            case GetMemoryData:
                i3 = bArr[0] & 255;
                if ((bArr[1] & 255) != 0) {
                    transMemoryData();
                    return;
                }
                Log.w("A9InsSet", "Has no history");
                this.f1612q.onNotify(this.f1610o, this.f1613r, HsProfile.ACTION_NO_HISTORICALDATA, null);
                this.f1609n.clear();
                return;
            case GetHistoryData:
                byte b3 = bArr[2];
                while (b < b3) {
                    this.f1609n.add(ByteBufferUtil.Bytes2HexString(ByteBufferUtil.bytesCutt((b * 16) + 4, ((b * 16) + 4) + 15, bArr)));
                    b++;
                }
                if (bArr[3] != (byte) 0) {
                    transMemoryData();
                    return;
                }
                Log.w("A9InsSet", "No history data");
                m833a(this.f1609n);
                this.f1609n.clear();
                return;
            case RealTimeData:
                stopTimeoutTimer();
                TimeoutTimer();
                i3 = ((bArr[0] & 255) * 256) + (bArr[1] & 255);
                if (i3 != 0) {
                    try {
                        jSONObject2 = new JSONObject();
                        jSONObject2.put("value", String.valueOf(((double) i3) / 10.0d));
                        this.f1612q.onNotify(this.f1610o, this.f1613r, HsProfile.ACTION_LIVEDATA_HS, jSONObject2.toString());
                        return;
                    } catch (Exception e2222) {
                        e2222.printStackTrace();
                        return;
                    }
                }
                return;
            case StableWeight:
                m836b((byte) 54);
                stopTimeoutTimer();
                TimeoutTimer();
                i3 = ((bArr[0] & 255) * 256) + (bArr[1] & 255);
                if (i3 != 0) {
                    try {
                        jSONObject2 = new JSONObject();
                        jSONObject2.put(HsProfile.STABLEDATA_HS, String.valueOf(((double) i3) / 10.0d));
                        this.f1612q.onNotify(this.f1610o, this.f1613r, HsProfile.ACTION_STABLEDATA_HS, jSONObject2.toString());
                        return;
                    } catch (Exception e22222) {
                        e22222.printStackTrace();
                        return;
                    }
                }
                return;
            case ImpedanceWeight:
                m836b((byte) 55);
                stopTimeoutTimer();
                TimeoutTimer();
                i3 = ((bArr[0] & 255) * 256) + (bArr[1] & 255);
                if (i3 != 0) {
                    try {
                        jSONObject2 = new JSONObject();
                        jSONObject2.put(HsProfile.IMPEDANCEDATA_HS, String.valueOf(((double) i3) / 10.0d));
                        this.f1612q.onNotify(this.f1610o, this.f1613r, HsProfile.ACTION_IMPEDANCEDATA_HS, jSONObject2.toString());
                        return;
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                        return;
                    }
                }
                return;
            case ResultData:
                stopTimeoutTimer();
                SystemClock.sleep(200);
                m836b((byte) 56);
                m834a(Hs5DataUtil.parseData(bArr));
                return;
            case Measurement_Stop:
                stopTimeoutTimer();
                SystemClock.sleep(200);
                m838c((byte) 57);
                return;
            case Error:
                i3 = bArr[0] & 255;
                Log.w("A9InsSet", "error=" + i3);
                stopTimeoutTimer();
                SystemClock.sleep(200);
                m836b((byte) 52);
                try {
                    jSONObject2 = new JSONObject();
                    jSONObject2.put("error", i3);
                    this.f1612q.onNotify(this.f1610o, this.f1613r, HsProfile.ACTION_ERROR_HS, jSONObject2.toString());
                    return;
                } catch (Exception e222222) {
                    e222222.printStackTrace();
                    return;
                }
            default:
                stopTimeoutTimer();
                return;
        }
    }

    private static void m832a(String str, Object... objArr) {
        Log.p("A9InsSet", Level.INFO, str, objArr);
    }

    private void m833a(ArrayList arrayList) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int[] iArr = new int[13];
            int[] parseData = Hs5DataUtil.parseData(ByteBufferUtil.hexStringToByte((String) it.next()));
            String dateStr = Hs5DataUtil.getDateStr(parseData);
            long BirthdayToLong = ByteBufferUtil.BirthdayToLong(dateStr);
            if (BirthdayToLong <= C2051l.m390b()) {
                String dataID = PublicMethod.getDataID(this.f1610o, String.valueOf(((double) parseData[6]) / 10.0d) + "", BirthdayToLong);
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("dataID", dataID);
                    jSONObject2.put("date", dateStr);
                    jSONObject2.put("value", String.valueOf(((double) parseData[6]) / 10.0d));
                    jSONObject2.put(HsProfile.FAT_HS, String.valueOf(((double) parseData[7]) / 10.0d));
                    jSONObject2.put(HsProfile.WATER_HS, String.valueOf(((double) parseData[8]) / 10.0d));
                    jSONObject2.put(HsProfile.MUSCLE_HS, String.valueOf(((double) parseData[9]) / 10.0d));
                    jSONObject2.put(HsProfile.SKELETON_HS, String.valueOf(((double) parseData[10]) / 10.0d));
                    jSONObject2.put(HsProfile.FATELEVEL_HS, parseData[11]);
                    jSONObject2.put("DCI", parseData[12]);
                    jSONArray.put(jSONObject2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Log.w("A9InsSet", "it is future time");
            }
        }
        if (jSONArray.length() > 0) {
            try {
                jSONObject.put(HsProfile.HISTORDATA__HS, jSONArray);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            this.f1612q.onNotify(this.f1610o, this.f1613r, HsProfile.ACTION_HISTORICAL_DATA_HS, jSONObject.toString());
            return;
        }
        this.f1612q.onNotify(this.f1610o, this.f1613r, HsProfile.ACTION_NO_HISTORICALDATA, jSONObject.toString());
    }

    private void m834a(int[] iArr) {
        JSONObject jSONObject = new JSONObject();
        float f = (float) (((double) iArr[6]) / 10.0d);
        float f2 = (float) (((double) iArr[7]) / 10.0d);
        float f3 = (float) (((double) iArr[8]) / 10.0d);
        float f4 = (float) (((double) iArr[9]) / 10.0d);
        float f5 = (float) (((double) iArr[10]) / 10.0d);
        int i = iArr[11];
        int i2 = iArr[12];
        String dataID = PublicMethod.getDataID(this.f1610o, f + "", PublicMethod.getTs());
        try {
            jSONObject.put("dataID", MD5.md5String(dataID));
            jSONObject.put("value", String.valueOf(f));
            jSONObject.put(HsProfile.FAT_HS, String.valueOf(f2));
            jSONObject.put(HsProfile.WATER_HS, String.valueOf(f3));
            jSONObject.put(HsProfile.MUSCLE_HS, String.valueOf(f4));
            jSONObject.put(HsProfile.SKELETON_HS, String.valueOf(f5));
            jSONObject.put(HsProfile.FATELEVEL_HS, i);
            jSONObject.put("DCI", i2);
            this.f1612q.onNotify(this.f1610o, this.f1613r, HsProfile.ACTION_ONLINE_RESULT_HS, jSONObject.toString());
            if (C2041b.f505a) {
                new DataBaseTools(this.f1617v).addData(DataBaseConstants.TABLE_TB_HSRESULT, Make_Data_Util.makeDataSingleHs(dataID, this.f1618w, f, f2, f3, f4, f5, i, (float) i2, iHealthDevicesManager.TYPE_HS5, this.f1610o));
                HS_InAuthor instance = HS_InAuthor.getInstance();
                instance.initAuthor(this.f1617v, this.f1618w);
                instance.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m836b(byte b) {
        if (this.f1610o != null && this.f1611p != null) {
            this.f1608m.packageData(new byte[]{(byte) -87, b});
        }
    }

    private void m838c(byte b) {
        if (this.f1610o != null && this.f1611p != null) {
            this.f1608m.packageDataAsk(new byte[]{(byte) -87, b});
        }
    }

    public void DeleteUserInScale(int userPstCode, int userId) {
        m832a("DeleteUserInScale", Integer.valueOf(userPstCode), Integer.valueOf(userId));
        if (this.f1619x == null) {
            this.f1619x = new Hs5DeleteUserCommand(this, userPstCode, userId);
            this.f1619x.mo5527a();
        }
    }

    public void TimeoutTimer() {
        m832a("TimeoutTimer", new Object[0]);
        this.f1604a = new Timer();
        this.f1605b = new C20981(this);
        this.f1604a.schedule(this.f1605b, 4000);
    }

    public void WriteUserToScale(int userPstCode, int userId, int age, int height, int isSporter, int gender) {
        m832a("WriteUserToScale", Integer.valueOf(userPstCode), Integer.valueOf(userId), Integer.valueOf(age), Integer.valueOf(height), Integer.valueOf(isSporter), Integer.valueOf(gender));
        if (this.f1619x == null) {
            this.f1619x = new Hs5AddUserCommand(this, userPstCode, userId, age, height, isSporter, gender);
            this.f1619x.mo5527a();
        }
    }

    public void creatMeasurementCnn(int userPstCode, int userId) {
        m832a("creatMeasurementCnn", Integer.valueOf(userPstCode), Integer.valueOf(userId));
        this.f1621z = Status.Measurement;
        r0 = new byte[7];
        byte[] intToByteForuserId = ByteBufferUtil.intToByteForuserId(userId);
        r0[3] = intToByteForuserId[0];
        r0[4] = intToByteForuserId[1];
        r0[5] = intToByteForuserId[2];
        r0[6] = intToByteForuserId[3];
        this.f1608m.packageData(r0);
    }

    public void creatMemoryCnn(int userPstCode, int userId) {
        m832a("creatMemoryCnn", Integer.valueOf(userPstCode), Integer.valueOf(userId));
        this.f1621z = Status.GetMemoryData;
        byte[] bArr = new byte[13];
        bArr[0] = (byte) -87;
        bArr[1] = (byte) 51;
        bArr[2] = (byte) userPstCode;
        byte[] intToByteForuserId = ByteBufferUtil.intToByteForuserId(userId);
        bArr[3] = intToByteForuserId[0];
        bArr[4] = intToByteForuserId[1];
        bArr[5] = intToByteForuserId[2];
        bArr[6] = intToByteForuserId[3];
        Date date = new Date();
        byte year = (byte) (date.getYear() - 100);
        byte month = (byte) (date.getMonth() + 1);
        byte date2 = (byte) date.getDate();
        byte hours = (byte) date.getHours();
        byte minutes = (byte) date.getMinutes();
        byte seconds = (byte) date.getSeconds();
        byte[] bArr2 = new byte[]{year, month, date2, hours, minutes, seconds};
        bArr[7] = bArr2[0];
        bArr[8] = bArr2[1];
        bArr[9] = bArr2[2];
        bArr[10] = bArr2[3];
        bArr[11] = bArr2[4];
        bArr[12] = bArr2[5];
        this.f1608m.packageData(bArr);
    }

    public void createManagementCnn() {
        m832a("createManagementCnn", new Object[0]);
        if (this.f1610o != null && this.f1611p != null) {
            this.f1621z = Status.Management;
            byte[] bArr = new byte[8];
            bArr[0] = (byte) -87;
            bArr[1] = Framer.STDOUT_FRAME_PREFIX;
            Date date = new Date();
            byte year = (byte) (date.getYear() - 100);
            byte month = (byte) (date.getMonth() + 1);
            byte date2 = (byte) date.getDate();
            byte hours = (byte) date.getHours();
            byte minutes = (byte) date.getMinutes();
            byte seconds = (byte) date.getSeconds();
            ByteBufferUtil.ByteBufferCopy(new byte[]{year, month, date2, hours, minutes, seconds}, 0, 6, bArr, 2);
            this.f1608m.packageData(bArr);
        }
    }

    public void finishCnn() {
        m832a("finishCnn", new Object[0]);
        this.f1608m.packageData(new byte[]{(byte) -87, (byte) 57});
    }

    public Status getStatus() {
        return this.f1621z;
    }

    public void haveNewData(int what, int stateId, byte[] returnData) {
        Command a = Command.m824a(what);
        Log.p("A9InsSet", Level.DEBUG, "haveNewData", new Object[]{a, Integer.valueOf(stateId), ByteBufferUtil.Bytes2HexString(returnData)});
        String str = "";
        String Bytes2HexString = ByteBufferUtil.Bytes2HexString(ByteBufferUtil.bytesCutt(returnData.length - 6, returnData.length - 1, returnData));
        if (returnData != null) {
            m831a(what, stateId, Bytes2HexString, ByteBufferUtil.bytesCutt(0, returnData.length - 7, returnData));
        }
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
    }

    public void identify() {
        m832a("identify", new Object[0]);
        m758a((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 4000, 251, 253, 254);
        this.f1608m.packageData(m760a((byte) -87));
    }

    public void setCloud1(String cloud1) {
        m832a("setCloud1", cloud1);
        if (this.f1610o != null && this.f1611p != null) {
            byte[] bArr = new byte[50];
            bArr[0] = (byte) -87;
            bArr[1] = (byte) 89;
            byte[] bArr2 = new byte[48];
            try {
                bArr2 = cloud1.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            for (int i = 2; i < bArr2.length + 2; i++) {
                bArr[i] = bArr2[i - 2];
            }
            this.f1608m.packageData(bArr);
        }
    }

    public void setCloud2(String cloud2) {
        m832a("setCloud2", cloud2);
        if (this.f1610o != null && this.f1611p != null) {
            byte[] bArr = new byte[66];
            bArr[0] = (byte) -87;
            bArr[1] = (byte) 90;
            byte[] bArr2 = new byte[64];
            try {
                bArr2 = cloud2.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            for (int i = 2; i < bArr2.length + 2; i++) {
                bArr[i] = bArr2[i - 2];
            }
            this.f1608m.packageData(bArr);
        }
    }

    public void setUserId(int usrId) {
        m832a("setUserId", Integer.valueOf(usrId));
        this.f1616u = usrId;
    }

    public void stopTimeoutTimer() {
        if (this.f1605b != null) {
            this.f1605b.cancel();
            this.f1605b = null;
        }
        if (this.f1604a != null) {
            this.f1604a.cancel();
            this.f1604a = null;
        }
    }

    public void transMemoryData() {
        m832a("transMemoryData", new Object[0]);
        this.f1608m.packageData(new byte[]{(byte) -87, (byte) 66});
    }

    public void transMemoryPara() {
        m832a("transMemoryPara", new Object[0]);
        this.f1608m.packageData(new byte[]{(byte) -87, (byte) 65});
    }

    public void updateUserInfo(int userPstCode, int userId, int age, int height, int isSporter, int gender) {
        m832a("updateUserInfo", Integer.valueOf(userPstCode), Integer.valueOf(userId), Integer.valueOf(age), Integer.valueOf(height), Integer.valueOf(isSporter), Integer.valueOf(gender));
        if (this.f1619x == null) {
            this.f1619x = new Hs5UpdateUserCommand(this, userPstCode, userId, age, height, isSporter, gender);
            this.f1619x.mo5527a();
        }
    }
}
