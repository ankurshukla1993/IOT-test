package com.ihealth.communication.ins;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdManager.DiscoveryListener;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.MulticastLock;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.example.smartlinklib.ModuleInfo;
import com.example.smartlinklib.SmartLinkManipulator;
import com.example.smartlinklib.SmartLinkManipulator.ConnectCallBack;
import com.ihealth.communication.cloud.CommCloudHS6;
import com.ihealth.communication.cloud.data.Date_TB_HS6MeasureResult;
import com.ihealth.communication.cloud.data.UserNetData;
import com.ihealth.communication.cloud.p002a.C2051l;
import com.ihealth.communication.control.HS6Control;
import com.ihealth.communication.manager.iHealthDeviceHs6Callback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.CRC32;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class HS6InsSet {
    private final Context f1852a;
    private final String f1853b;
    private final String f1854c;
    private final iHealthDeviceHs6Callback f1855d;
    private WifiManager f1856e;
    private XmitterBeanThread f1857f;
    private NsdManager f1858g;
    private boolean f1859h;

    class C21271 implements ConnectCallBack {
        final /* synthetic */ HS6InsSet f1843a;

        C21271(HS6InsSet this$0) {
            this.f1843a = this$0;
        }

        public void onConnect(ModuleInfo moduleInfo) {
            Log.p("HS6InsSet", Level.INFO, "onConnect", new Object[]{moduleInfo.toString()});
        }

        public void onConnectOk() {
            Log.p("HS6InsSet", Level.INFO, "onConnectOk", new Object[0]);
            this.f1843a.m998a();
        }

        public void onConnectTimeOut() {
            Log.p("HS6InsSet", Level.INFO, "onConnectTimeOut", new Object[0]);
            this.f1843a.m1003b();
        }
    }

    class XmitterBeanThread extends Thread implements DiscoveryListener {
        final /* synthetic */ HS6InsSet f1845a;
        private final XmitterBean f1846b;
        private int f1847c;
        private int f1848d;
        private Timer f1849e;
        private TimerTask f1850f;
        private boolean f1851g;

        class C21281 extends TimerTask {
            final /* synthetic */ XmitterBeanThread f1844a;

            C21281(XmitterBeanThread this$1) {
                this.f1844a = this$1;
            }

            public void run() {
                if (!this.f1844a.f1845a.f1859h) {
                    this.f1844a.f1845a.m1003b();
                    if (VERSION.SDK_INT >= 16) {
                        this.f1844a.f1845a.f1858g.stopServiceDiscovery(this.f1844a);
                    }
                }
            }
        }

        XmitterBeanThread(HS6InsSet hS6InsSet, XmitterBean xmitterBean) {
            this.f1845a = hS6InsSet;
            this.f1846b = xmitterBean;
        }

        private void m987a() {
            switch (this.f1847c) {
                case 0:
                    if (this.f1848d == 3) {
                        this.f1847c = 1;
                        this.f1848d = 0;
                        return;
                    }
                    m988a(this.f1848d);
                    this.f1848d++;
                    return;
                case 1:
                    m989a(this.f1848d, 2);
                    this.f1848d++;
                    if (this.f1846b.getSsidLen() % 2 == 1) {
                        if (this.f1848d * 2 == this.f1846b.getSsidLen() + 5) {
                            m989a(this.f1848d, 1);
                            this.f1847c = 2;
                            this.f1848d = 0;
                            return;
                        }
                        return;
                    } else if ((this.f1848d - 1) * 2 == this.f1846b.getSsidLen() + 4) {
                        this.f1847c = 2;
                        this.f1848d = 0;
                        return;
                    } else {
                        return;
                    }
                case 2:
                    m992b(this.f1848d, 2);
                    this.f1848d++;
                    if (this.f1846b.getPassLen() % 2 == 1) {
                        if (this.f1848d * 2 == this.f1846b.getPassLen() + 5) {
                            m992b(this.f1848d, 1);
                            this.f1847c = 0;
                            this.f1848d = 0;
                            return;
                        }
                        return;
                    } else if ((this.f1848d - 1) * 2 == this.f1846b.getPassLen() + 4) {
                        this.f1847c = 0;
                        this.f1848d = 0;
                        return;
                    } else {
                        return;
                    }
                default:
                    android.util.Log.i("HS6InsSet", "I shouldn't be here");
                    return;
            }
        }

        private void m988a(int i) {
            m990a(i | 120, this.f1846b.getPreamble()[(i * 2) + 1], this.f1846b.getPreamble()[i * 2]);
        }

        private void m989a(int i, int i2) {
            if (i == 0) {
                m990a(64, this.f1846b.getSsidLen(), this.f1846b.getSsidLen());
            } else if (i == 1 || i == 2) {
                m990a(i | 64, (this.f1846b.getSsidCRC() >> ((((i - 1) * 2) + 1) * 8)) & 255, (this.f1846b.getSsidCRC() >> (((i - 1) * 2) * 8)) & 255);
            } else {
                m990a(i | 64, i2 == 2 ? this.f1846b.getSsid().getBytes()[((i - 3) * 2) + 1] & 255 : 0, this.f1846b.getSsid().getBytes()[(i - 3) * 2] & 255);
            }
        }

        private void m990a(int i, int i2, int i3) {
            try {
                byte[] bytes = "a".getBytes();
                InetAddress byName = InetAddress.getByName("239." + (i & 127) + "." + i2 + "." + i3);
                MulticastSocket multicastSocket = new MulticastSocket(1234);
                multicastSocket.send(new DatagramPacket(bytes, bytes.length, byName, 5500));
                multicastSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void m991b() {
            this.f1847c = 0;
            this.f1848d = 0;
        }

        private void m992b(int i, int i2) {
            int i3 = 0;
            if (i == 0) {
                m990a(0, this.f1846b.getPassLen(), this.f1846b.getPassLen());
            } else if (i == 1 || i == 2) {
                m990a(i, (this.f1846b.getPassCRC() >> ((((i - 1) * 2) + 1) * 8)) & 255, (this.f1846b.getPassCRC() >> (((i - 1) * 2) * 8)) & 255);
            } else {
                int i4 = this.f1846b.getPassphrase()[(i - 3) * 2] & 255;
                if (i2 == 2) {
                    i3 = this.f1846b.getPassphrase()[((i - 3) * 2) + 1] & 255;
                }
                m990a(i, i3, i4);
            }
        }

        private void m993c() {
            m995e();
            m994d();
            this.f1849e = new Timer();
            this.f1850f = new C21281(this);
            this.f1849e.schedule(this.f1850f, 15000);
        }

        private void m994d() {
            if (this.f1849e != null) {
                this.f1849e.cancel();
                this.f1849e = null;
            }
            if (this.f1850f != null) {
                this.f1850f.cancel();
                this.f1850f = null;
            }
        }

        private void m995e() {
            if (VERSION.SDK_INT >= 16) {
                this.f1845a.f1858g = (NsdManager) this.f1845a.f1852a.getSystemService("servicediscovery");
                this.f1845a.f1858g.discoverServices("_ezconnect-prov._tcp", 1, this);
            }
        }

        public void onDiscoveryStarted(String serviceType) {
            Log.p("HS6InsSet", Level.INFO, "onDiscoveryStarted", new Object[]{serviceType});
        }

        public void onDiscoveryStopped(String serviceType) {
            Log.p("HS6InsSet", Level.INFO, "onDiscoveryStopped", new Object[]{serviceType});
        }

        public void onServiceFound(NsdServiceInfo serviceInfo) {
            m994d();
            this.f1845a.f1859h = true;
            this.f1845a.m998a();
            if (VERSION.SDK_INT >= 16) {
                Log.p("HS6InsSet", Level.INFO, "NsdServiceInfo:  " + serviceInfo.toString(), new Object[0]);
            } else {
                Log.p("HS6InsSet", Level.INFO, "onServiceFound", new Object[]{serviceInfo});
            }
            if (VERSION.SDK_INT >= 16) {
                this.f1845a.f1858g.stopServiceDiscovery(this);
            }
        }

        public void onServiceLost(NsdServiceInfo serviceInfo) {
            Log.p("HS6InsSet", Level.INFO, "onServiceLost", new Object[]{serviceInfo});
            if (VERSION.SDK_INT >= 16) {
                Log.p("HS6InsSet", Level.INFO, "onServiceLost", new Object[]{serviceInfo.toString()});
                return;
            }
            Log.p("HS6InsSet", Level.INFO, "onServiceLost", new Object[0]);
        }

        public void onStartDiscoveryFailed(String serviceType, int errorCode) {
            Log.p("HS6InsSet", Level.INFO, "onStartDiscoveryFailed", new Object[]{serviceType, Integer.valueOf(errorCode)});
            this.f1845a.m1003b();
        }

        public void onStopDiscoveryFailed(String serviceType, int errorCode) {
            Log.p("HS6InsSet", Level.INFO, "onStopDiscoveryFailed", new Object[]{serviceType, Integer.valueOf(errorCode)});
            this.f1845a.m1003b();
        }

        public void run() {
            MulticastLock createMulticastLock = this.f1845a.f1856e.createMulticastLock("mcastlock");
            createMulticastLock.acquire();
            int i = 0;
            while (this.f1851g) {
                if (this.f1847c == 0 && this.f1848d == 0) {
                    i++;
                }
                if (i >= 300 || isInterrupted()) {
                    this.f1851g = false;
                    m993c();
                    break;
                }
                m987a();
            }
            createMulticastLock.release();
        }

        public void setRun(boolean isRun) {
            this.f1851g = isRun;
            if (isRun) {
                m991b();
                start();
                return;
            }
            interrupt();
        }
    }

    public HS6InsSet(Context context, String userName, String type, iHealthDeviceHs6Callback hs6Callback) {
        this.f1852a = context;
        this.f1853b = userName;
        this.f1854c = type;
        this.f1855d = hs6Callback;
    }

    private UserNetData m997a(String str, float f, int i, int i2, int i3) {
        UserNetData userNetData = new UserNetData();
        userNetData.setID(0);
        userNetData.setBirthday(C2051l.m384a(str));
        userNetData.setEmail(new String[]{"", "", "", "", "", "", "", "", "", ""});
        userNetData.setGender(i3);
        userNetData.setIsSporter(i2);
        userNetData.setHeight(i);
        userNetData.setWeight(f);
        return userNetData;
    }

    private void m998a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(HS6Control.SETWIFI_RESULT, true);
        } catch (JSONException e) {
            Log.p("HS6InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
        }
        if (this.f1855d != null) {
            this.f1855d.setWifiNotify(this.f1854c, HS6Control.ACTION_HS6_SETWIFI, jSONObject.toString());
        }
    }

    private void m1000a(String str, String str2, String str3) {
        if (this.f1857f != null && this.f1857f.isAlive()) {
            this.f1857f.setRun(false);
            this.f1857f = null;
        }
        m1005b(str, str2, str3);
    }

    private byte[] m1002a(byte[] bArr, String str, String str2) {
        byte[] bArr2 = null;
        try {
            byte[] bArr3 = new byte[16];
            for (int i = 0; i < 16; i++) {
                bArr3[i] = (byte) 0;
            }
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
            Key secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(str2.toCharArray(), str.getBytes(), 4096, 256)).getEncoded(), "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
            instance.init(1, secretKeySpec, ivParameterSpec);
            bArr2 = instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bArr2;
    }

    private void m1003b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(HS6Control.SETWIFI_RESULT, false);
        } catch (JSONException e) {
            Log.p("HS6InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
            e.printStackTrace();
        }
        if (this.f1855d != null) {
            this.f1855d.setWifiNotify(this.f1854c, HS6Control.ACTION_HS6_SETWIFI, jSONObject.toString());
        }
    }

    private void m1005b(String str, String str2, String str3) {
        XmitterBean xmitterBean = new XmitterBean();
        int length = str.length();
        if (VERSION.SDK_INT >= 17 && str.startsWith("\"") && str.endsWith("\"")) {
            str = str.substring(1, length - 1);
            length -= 2;
        }
        xmitterBean.setSsid(str);
        xmitterBean.setSsidLen(length);
        CRC32 crc32 = new CRC32();
        crc32.reset();
        crc32.update(str.getBytes());
        xmitterBean.setSsidCRC((int) crc32.getValue());
        crc32 = new CRC32();
        crc32.reset();
        crc32.update(str2.getBytes());
        xmitterBean.setPassCRC((int) crc32.getValue());
        if (str2.length() % 16 == 0) {
            xmitterBean.setPassLen(str2.length());
        } else {
            xmitterBean.setPassLen((16 - (str2.length() % 16)) + str2.length());
        }
        byte[] bArr = new byte[xmitterBean.getPassLen()];
        for (length = 0; length < str2.length(); length++) {
            bArr[length] = str2.getBytes()[length];
        }
        xmitterBean.setPassphrase(m1002a(bArr, str, str3));
        char[] cArr = new char[6];
        String[] split = this.f1856e.getConnectionInfo().getBSSID().split(":");
        for (length = 0; length < 6; length++) {
            cArr[length] = (char) Integer.parseInt(split[length], 16);
        }
        xmitterBean.setMac(cArr);
        xmitterBean.setPreamble(new char[]{'E', 'Z', 'P', 'R', '2', '2'});
        this.f1857f = new XmitterBeanThread(this, xmitterBean);
        this.f1857f.setRun(true);
    }

    public boolean bindDeviceHS6(String birthday, float weight, int height, int isSporter, int gender, String serialNumber) {
        Log.p("HS6InsSet", Level.INFO, "bindDeviceHS6", new Object[]{birthday, Float.valueOf(weight), Integer.valueOf(height), Integer.valueOf(isSporter), Integer.valueOf(gender), serialNumber});
        Date_TB_HS6MeasureResult date_TB_HS6MeasureResult = new Date_TB_HS6MeasureResult();
        date_TB_HS6MeasureResult.setMAC(serialNumber);
        date_TB_HS6MeasureResult.setModel(iHealthDevicesManager.TYPE_HS6);
        date_TB_HS6MeasureResult.setTS(System.currentTimeMillis() / 1000);
        date_TB_HS6MeasureResult.setPosition(0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(date_TB_HS6MeasureResult);
        CommCloudHS6 commCloudHS6 = new CommCloudHS6(this.f1853b, this.f1852a);
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            if (commCloudHS6.user_upload(this.f1853b, m997a(birthday, weight, height, isSporter, gender)) != 100) {
                jSONObject.put("hs6_error", 1);
                this.f1855d.onNotify(date_TB_HS6MeasureResult.getMAC(), this.f1854c, "hs6_error", jSONObject.toString());
            } else if (commCloudHS6.User_netdevice_Bind(arrayList)) {
                ArrayList arrayList2 = commCloudHS6.Bind_HS6ReturnArr;
                if (arrayList2 == null || arrayList2.size() <= 0) {
                    jSONObject.put("hs6_error", 2);
                    this.f1855d.onNotify(date_TB_HS6MeasureResult.getMAC(), this.f1854c, "hs6_error", jSONObject.toString());
                } else {
                    for (int i = 0; i < arrayList2.size(); i++) {
                        JSONObject jSONObject2 = new JSONObject();
                        int action = ((Date_TB_HS6MeasureResult) arrayList2.get(i)).getAction();
                        if (action == 1 || action == 2) {
                            jSONObject2.put(HS6Control.BIND_HS6_RESULT, action);
                        } else {
                            jSONObject2.put(HS6Control.BIND_HS6_RESULT, 3);
                        }
                        jSONObject2.put(HS6Control.HS6_MODEL, date_TB_HS6MeasureResult.getModel());
                        jSONObject2.put("position", ((Date_TB_HS6MeasureResult) arrayList2.get(i)).getPosition());
                        jSONObject2.put(HS6Control.HS6_SETTED_WIFI, ((Date_TB_HS6MeasureResult) arrayList2.get(i)).getSetWifi());
                        jSONArray.put(jSONObject2);
                    }
                    jSONObject.put(HS6Control.HS6_BIND_EXTRA, jSONArray);
                    this.f1855d.onNotify(date_TB_HS6MeasureResult.getMAC(), this.f1854c, HS6Control.ACTION_HS6_BIND, jSONObject.toString());
                }
            } else {
                jSONObject.put("hs6_error", 2);
                this.f1855d.onNotify(date_TB_HS6MeasureResult.getMAC(), this.f1854c, "hs6_error", jSONObject.toString());
            }
        } catch (Exception e) {
            Log.p("HS6InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
            e.printStackTrace();
        }
        return true;
    }

    public boolean getToken(String clientId, String clientSecret, String username, String clientPara) {
        Log.p("HS6InsSet", Level.INFO, "getToken", new Object[]{clientId, clientSecret, username, clientPara});
        CommCloudHS6 commCloudHS6 = new CommCloudHS6(username, this.f1852a);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(HS6Control.GET_TOKEN_RESULT, new JSONTokener(commCloudHS6.sdk_get_token(clientId, clientSecret, username, clientPara)).nextValue());
            this.f1855d.onNotify("", this.f1854c, HS6Control.ACTION_HS6_GET_TOKEN, jSONObject.toString());
        } catch (Exception e) {
            Log.p("HS6InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
            e.printStackTrace();
        }
        return true;
    }

    public boolean setUnit(String username, int unitType) {
        Log.p("HS6InsSet", Level.INFO, "setUnit", new Object[]{username, Integer.valueOf(unitType)});
        CommCloudHS6 commCloudHS6 = new CommCloudHS6(username, this.f1852a);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(HS6Control.SET_UNIT_RESULT, commCloudHS6.sync_unit(username, unitType) == 100);
            this.f1855d.onNotify("", this.f1854c, HS6Control.ACTION_HS6_SET_UNIT, jSONObject.toString());
        } catch (Exception e) {
            Log.p("HS6InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
            e.printStackTrace();
        }
        return true;
    }

    public boolean setWifi(String ssid, String password) {
        Log.p("HS6InsSet", Level.INFO, "setWifi", new Object[]{ssid, password});
        SmartLinkManipulator instence = SmartLinkManipulator.getInstence();
        try {
            instence.setConnection(ssid, password, this.f1852a);
        } catch (Exception e) {
            Log.p("HS6InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
        }
        instence.Startconnection(new C21271(this));
        return true;
    }

    public boolean setWifi(String ssid, String password, String deviceKey) {
        Log.p("HS6InsSet", Level.INFO, "setWifi", new Object[]{ssid, password, deviceKey});
        this.f1856e = (WifiManager) this.f1852a.getSystemService("wifi");
        if (TextUtils.isEmpty(ssid)) {
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        if (TextUtils.isEmpty(deviceKey)) {
            deviceKey = "device_key";
        }
        m1000a(ssid, password, deviceKey);
        return true;
    }

    public boolean unBindDeviceHS6(String serialNumber) {
        Log.p("HS6InsSet", Level.INFO, "unBindDeviceHS6", new Object[]{serialNumber});
        Date_TB_HS6MeasureResult date_TB_HS6MeasureResult = new Date_TB_HS6MeasureResult();
        date_TB_HS6MeasureResult.setMAC(serialNumber);
        date_TB_HS6MeasureResult.setModel(iHealthDevicesManager.TYPE_HS6);
        date_TB_HS6MeasureResult.setTS(System.currentTimeMillis() / 1000);
        ArrayList arrayList = new ArrayList();
        arrayList.add(date_TB_HS6MeasureResult);
        CommCloudHS6 commCloudHS6 = new CommCloudHS6(this.f1853b, this.f1852a);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(HS6Control.HS6_UNBIND_RESULT, commCloudHS6.User_netdevice_Unbind(arrayList));
            this.f1855d.onNotify(date_TB_HS6MeasureResult.getMAC(), this.f1854c, HS6Control.ACTION_HS6_UNBIND, jSONObject.toString());
        } catch (Exception e) {
            Log.p("HS6InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
            e.printStackTrace();
        }
        return true;
    }
}
