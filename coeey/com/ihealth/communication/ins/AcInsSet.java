package com.ihealth.communication.ins;

import android.content.Context;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.base.protocol.BleCommProtocol;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import com.ihealth.communication.cloud.data.DataBaseTools;
import com.ihealth.communication.cloud.data.Make_Data_Util;
import com.ihealth.communication.cloud.data.PO_InAuthor;
import com.ihealth.communication.cloud.p002a.C2041b;
import com.ihealth.communication.control.PoProfile;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import com.ihealth.communication.utils.MD5;
import com.ihealth.communication.utils.PublicMethod;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import org.joda.time.DateTimeConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class AcInsSet extends IdentifyIns implements NewDataCallback, GetBaseCommProtocolCallback {
    static final /* synthetic */ boolean f1695a = (!AcInsSet.class.desiredAssertionStatus());
    private static ArrayList f1696w = new ArrayList();
    private TimerTask f1697A;
    private String f1698b;
    private String f1699c;
    private BleCommProtocol f1700l;
    private BaseCommCallback f1701m;
    private InsCallback f1702n;
    private Context f1703o;
    private String f1704p;
    private String f1705q;
    private List f1706r = new ArrayList();
    private byte[] f1707s;
    private int f1708t = 0;
    private boolean f1709u = false;
    private byte[] f1710v;
    private Timer f1711x;
    private TimerTask f1712y;
    private Timer f1713z;

    class C21081 extends Thread {
        final /* synthetic */ AcInsSet f1689a;

        C21081(AcInsSet this$0) {
            this.f1689a = this$0;
        }

        public void run() {
            super.run();
            b.a(this.f1689a.f1703o, this.f1689a.f1698b);
        }
    }

    class C21092 extends Thread {
        final /* synthetic */ AcInsSet f1690a;

        C21092(AcInsSet this$0) {
            this.f1690a = this$0;
        }

        public void run() {
            super.run();
            b.b(this.f1690a.f1703o, this.f1690a.f1698b);
        }
    }

    class C21103 extends TimerTask {
        final /* synthetic */ AcInsSet f1691a;

        C21103(AcInsSet this$0) {
            this.f1691a = this$0;
        }

        public void run() {
            this.f1691a.m906g();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("error", 700);
                this.f1691a.f1702n.onNotify(this.f1691a.f1698b, this.f1691a.f1699c, PoProfile.ACTION_ERROR_PO, jSONObject.toString());
            } catch (Exception e) {
                Log.p("AcInsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
            }
        }
    }

    public AcInsSet(String username, Context context, BaseComm com, String mac, String accessoryFirm, String type, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        Log.p("AcInsSet", Level.INFO, "AcInsSet", new Object[]{username, mac, accessoryFirm, type});
        this.f1705q = username;
        this.f1698b = mac;
        this.f1699c = type;
        this.f1701m = baseCommCallback;
        this.f1702n = insCallback;
        this.f1703o = context;
        this.f1704p = accessoryFirm;
        this.f1700l = new BleCommProtocol(context, com, this.f1698b, (byte) -84, this);
        m896c();
        m759a(insCallback, mac, type, com);
    }

    private void m895b(byte b) {
        this.f1700l.packageData(this.f1698b, new byte[]{(byte) -84, b});
    }

    private void m896c() {
        new C21081(this).start();
    }

    private void m898c(final byte[] bArr) {
        m907h();
        this.f1713z = new Timer();
        this.f1697A = new TimerTask(this) {
            static final /* synthetic */ boolean f1692a = (!AcInsSet.class.desiredAssertionStatus());
            final /* synthetic */ AcInsSet f1694c;

            public void run() {
                this.f1694c.m907h();
                if (iHealthDevicesManager.getInstance().getPo3Control(this.f1694c.f1698b) != null) {
                    try {
                        int[] a = b.a(bArr);
                        JSONObject a2 = b.a(a);
                        String dataID = PublicMethod.getDataID(this.f1694c.f1698b, a[0] + "", PublicMethod.getTs());
                        if (f1692a || a2 != null) {
                            a2.put("dataID", MD5.md5String(dataID));
                            this.f1694c.f1702n.onNotify(this.f1694c.f1698b, this.f1694c.f1699c, PoProfile.ACTION_RESULTDATA_PO, a2.toString());
                            if (C2041b.f505a) {
                                String str = "";
                                int size;
                                if (AcInsSet.f1696w.size() >= 90) {
                                    for (size = AcInsSet.f1696w.size() - 90; size < AcInsSet.f1696w.size(); size++) {
                                        str = str + ((String) AcInsSet.f1696w.get(size));
                                    }
                                } else {
                                    for (size = 0; size < AcInsSet.f1696w.size(); size++) {
                                        str = str + ((String) AcInsSet.f1696w.get(size));
                                    }
                                }
                                AcInsSet.f1696w.clear();
                                if (str.length() > 2) {
                                    str = str.substring(1);
                                }
                                new DataBaseTools(this.f1694c.f1703o).addData(DataBaseConstants.TABLE_TB_PO, Make_Data_Util.makeDataSinglePo(dataID, this.f1694c.f1705q, a[1], a[0], a[2], this.f1694c.f1698b, str));
                                PO_InAuthor instance = PO_InAuthor.getInstance();
                                instance.initAuthor(this.f1694c.f1703o, this.f1694c.f1705q);
                                instance.run();
                                return;
                            }
                            return;
                        }
                        throw new AssertionError();
                    } catch (Exception e) {
                        Log.p("AcInsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
                    }
                }
            }
        };
        this.f1713z.schedule(this.f1697A, 1200);
    }

    private void m900d() {
        this.f1700l.packageData(this.f1698b, new byte[]{(byte) -84, (byte) -91});
    }

    private void m902e() {
        this.f1700l.packageData(this.f1698b, new byte[]{(byte) -84, (byte) -88});
    }

    private void m903f() {
        Log.p("AcInsSet", Level.INFO, "aaIns", new Object[0]);
        this.f1700l.packageData(this.f1698b, new byte[]{(byte) -84, (byte) -86});
    }

    private void m906g() {
        if (this.f1712y != null) {
            this.f1712y.cancel();
            this.f1712y = null;
        }
        if (this.f1711x != null) {
            this.f1711x.cancel();
            this.f1711x = null;
        }
    }

    private void m907h() {
        if (this.f1697A != null) {
            this.f1697A.cancel();
        }
        if (this.f1713z != null) {
            this.f1713z.cancel();
        }
        this.f1713z = null;
        this.f1697A = null;
    }

    public void TimeoutTimer() {
        Log.p("AcInsSet", Level.INFO, "TimeoutTimer", new Object[0]);
        m906g();
        this.f1711x = new Timer();
        this.f1712y = new C21103(this);
        this.f1711x.schedule(this.f1712y, 4000);
    }

    public void a2Ins() {
        Log.p("AcInsSet", Level.INFO, "a2Ins", new Object[0]);
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getDefault());
        Integer valueOf = Integer.valueOf(instance.get(1) - 2000);
        Integer valueOf2 = Integer.valueOf(instance.get(2) + 1);
        Integer valueOf3 = Integer.valueOf(instance.get(5));
        Integer valueOf4 = Integer.valueOf(instance.get(11));
        Integer valueOf5 = Integer.valueOf(instance.get(12));
        Integer valueOf6 = Integer.valueOf(instance.get(13));
        byte byteValue = valueOf.byteValue();
        byte byteValue2 = valueOf2.byteValue();
        byte byteValue3 = valueOf3.byteValue();
        byte byteValue4 = valueOf4.byteValue();
        byte byteValue5 = valueOf5.byteValue();
        byte byteValue6 = valueOf6.byteValue();
        this.f1700l.packageData(this.f1698b, new byte[]{(byte) -84, (byte) -94, byteValue, byteValue2, byteValue3, byteValue4, byteValue5, byteValue6});
    }

    public void a9Ins() {
        Log.p("AcInsSet", Level.INFO, "a9Ins", new Object[0]);
        this.f1700l.packageData(this.f1698b, new byte[]{(byte) -84, (byte) -87});
    }

    public void c1Ins() {
        Log.p("AcInsSet", Level.INFO, "c1Ins", new Object[0]);
        this.f1700l.packageData(this.f1698b, new byte[]{(byte) -84, (byte) -63});
    }

    public void destroy() {
        Log.p("AcInsSet", Level.INFO, "destroy", new Object[0]);
        this.f1701m = null;
        this.f1702n = null;
        this.f1703o = null;
        if (this.f1700l != null) {
            this.f1700l.destroy();
        }
        this.f1700l = null;
    }

    public BaseCommProtocol getBaseCommProtocol() {
        return this.f1700l;
    }

    public int getOffNum() {
        return this.f1708t;
    }

    public void haveNewData(int what, int stateId, byte[] command) {
        Log.p("AcInsSet", Level.DEBUG, "haveNewData", new Object[]{Integer.valueOf(what), Integer.valueOf(stateId), ByteBufferUtil.Bytes2HexString(command)});
        String toHexString = Integer.toHexString(what & 255);
        if (toHexString.length() == 1) {
            '0' + toHexString;
        }
        m757a(what);
        JSONObject a;
        String str;
        switch (what) {
            case 162:
                m900d();
                new C21092(this).start();
                return;
            case 166:
                m895b((byte) -90);
                return;
            case 167:
                try {
                    int[] a2 = b.a(command);
                    for (int i = 0; i < 3; i++) {
                        f1696w.add("A" + this.f1710v[i + 5]);
                    }
                    JSONObject a3 = b.a(a2);
                    if (f1695a || a3 != null) {
                        this.f1702n.onNotify(this.f1698b, this.f1699c, PoProfile.ACTION_LIVEDA_PO, a3.toString());
                        this.f1710v = command;
                        this.f1709u = true;
                        m898c(this.f1710v);
                        return;
                    }
                    throw new AssertionError();
                } catch (Exception e) {
                    Log.p("AcInsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
                }
                break;
            case DateTimeConstants.HOURS_PER_WEEK /*168*/:
                m902e();
                if (this.f1709u) {
                    this.f1709u = false;
                    m907h();
                    int[] a4 = b.a(this.f1710v);
                    toHexString = PublicMethod.getDataID(this.f1698b, a4[0] + "", PublicMethod.getTs());
                    try {
                        a = b.a(a4);
                        if (f1695a || a != null) {
                            a.put("dataID", MD5.md5String(toHexString));
                            this.f1702n.onNotify(this.f1698b, this.f1699c, PoProfile.ACTION_RESULTDATA_PO, a.toString());
                            if (C2041b.f505a) {
                                String str2 = "";
                                int size;
                                String str3;
                                if (f1696w.size() >= 90) {
                                    str = str2;
                                    size = f1696w.size() - 90;
                                    while (size < f1696w.size()) {
                                        str3 = str + ((String) f1696w.get(size));
                                        size++;
                                        str = str3;
                                    }
                                } else {
                                    str = str2;
                                    size = 0;
                                    while (size < f1696w.size()) {
                                        str3 = str + ((String) f1696w.get(size));
                                        size++;
                                        str = str3;
                                    }
                                }
                                f1696w.clear();
                                new DataBaseTools(this.f1703o).addData(DataBaseConstants.TABLE_TB_PO, Make_Data_Util.makeDataSinglePo(toHexString, this.f1705q, a4[1], a4[0], a4[2], this.f1698b, str.length() > 2 ? str.substring(1) : str));
                                PO_InAuthor instance = PO_InAuthor.getInstance();
                                instance.initAuthor(this.f1703o, this.f1705q);
                                instance.run();
                                return;
                            }
                            return;
                        }
                        throw new AssertionError();
                    } catch (Exception e2) {
                        Log.p("AcInsSet", Level.WARN, "Exception", new Object[]{e2.getMessage()});
                    }
                } else {
                    return;
                }
                break;
            case 169:
                this.f1708t = command[0];
                if (this.f1708t == 0) {
                    try {
                        this.f1702n.onNotify(this.f1698b, this.f1699c, PoProfile.ACTION_NO_OFFLINEDATA_PO, new JSONObject().toString());
                        return;
                    } catch (Exception e3) {
                        Log.p("AcInsSet", Level.WARN, "Exception", new Object[]{e3.getMessage()});
                        return;
                    }
                }
                m903f();
                return;
            case 171:
                m895b((byte) -85);
                this.f1707s = ByteBufferUtil.BufferMerger(null, command);
                return;
            case 172:
                m895b((byte) -84);
                this.f1706r.add(ByteBufferUtil.BufferMerger(this.f1707s, command));
                this.f1707s = null;
                return;
            case 173:
                m895b((byte) -83);
                try {
                    Object obj = new Object();
                    str = b.a(this.f1703o, this.f1698b, this.f1704p, this.f1706r);
                    if (str == null) {
                        this.f1702n.onNotify(this.f1698b, this.f1699c, PoProfile.ACTION_NO_OFFLINEDATA_PO, obj.toString());
                    } else {
                        this.f1702n.onNotify(this.f1698b, this.f1699c, PoProfile.ACTION_OFFLINEDATA_PO, str);
                    }
                } catch (JSONException e4) {
                    Log.p("AcInsSet", Level.WARN, "Exception", new Object[]{e4.getMessage()});
                }
                this.f1706r.clear();
                m900d();
                return;
            case 193:
                byte b = command[0];
                try {
                    a = new JSONObject();
                    a.put("battery", b);
                    this.f1702n.onNotify(this.f1698b, this.f1699c, PoProfile.ACTION_BATTERY_PO, a.toString());
                    return;
                } catch (Exception e32) {
                    Log.p("AcInsSet", Level.WARN, "Exception", new Object[]{e32.getMessage()});
                    return;
                }
            case 251:
                byte[] a5 = m762a(command, this.f1699c, (byte) -84);
                m758a(252, 4000, 253, 254);
                this.f1700l.packageData(this.f1698b, a5);
                return;
            case 253:
                this.f1701m.onConnectionStateChange(this.f1698b, this.f1699c, 1, 0, null);
                return;
            default:
                return;
        }
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
    }

    public void identify() {
        Log.p("AcInsSet", Level.INFO, "identify", new Object[0]);
        m758a((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 4000, 251, 253, 254);
        this.f1700l.packageData(this.f1698b, m760a((byte) -84));
    }
}
