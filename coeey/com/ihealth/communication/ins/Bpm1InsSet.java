package com.ihealth.communication.ins;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.facebook.imageutils.JfifUtil;
import com.ihealth.communication.base.wifi.iHealthDeviceBPM1Callback;
import com.ihealth.communication.control.BpProfile;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import com.ihealth.communication.utils.WifiAdmin;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

public class Bpm1InsSet {
    private String f1788a = "192.168.10.1";
    private String f1789b;
    private iHealthDeviceBPM1Callback f1790c;
    private WifiAdmin f1791d;
    private DatagramSocket f1792e;
    private Timer f1793f;
    private TimerTask f1794g;
    private String f1795h;
    private long f1796i;
    private boolean f1797j;
    private Thread f1798k;
    private JSONObject f1799l;
    private Timer f1800m;
    private TimerTask f1801n;
    private int f1802o = -1;

    class C21161 extends Thread {
        final /* synthetic */ Bpm1InsSet f1773a;

        C21161(Bpm1InsSet this$0) {
            this.f1773a = this$0;
        }

        public void run() {
            while (this.f1773a.f1791d.checkState() != 3) {
                try {
                    C21161.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.f1773a.m960e();
            interrupt();
        }
    }

    class C21214 extends Thread {
        final /* synthetic */ Bpm1InsSet f1780a;

        class C21191 extends TimerTask {
            final /* synthetic */ C21214 f1778a;

            C21191(C21214 this$1) {
                this.f1778a = this$1;
            }

            public void run() {
                if (!this.f1778a.f1780a.f1791d.getSSID().contains(this.f1778a.f1780a.f1795h)) {
                    this.f1778a.f1780a.m953b(3);
                }
            }
        }

        class C21202 extends Thread {
            final /* synthetic */ C21214 f1779a;

            C21202(C21214 this$1) {
                this.f1779a = this$1;
            }

            public void run() {
                while (true) {
                    try {
                        if (!this.f1779a.f1780a.f1791d.getWifiInfo().getSSID().contains(this.f1779a.f1780a.f1795h) || this.f1779a.f1780a.f1791d.getWifiInfo().getIpAddress() == 0) {
                            C21202.sleep(500);
                        } else {
                            this.f1779a.f1780a.f1788a = this.f1779a.f1780a.f1791d.getServerAddress();
                            this.f1779a.f1780a.m963f();
                            C21202.sleep(1000);
                            this.f1779a.f1780a.m953b(1);
                            interrupt();
                            return;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        C21214(Bpm1InsSet this$0) {
            this.f1780a = this$0;
        }

        public void run() {
            List wifiList = this.f1780a.f1791d.getWifiList();
            while (true) {
                if (wifiList != null && wifiList.size() != 0) {
                    break;
                }
                List wifiList2 = this.f1780a.f1791d.getWifiList();
                try {
                    Thread.sleep(100);
                    wifiList = wifiList2;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    wifiList = wifiList2;
                }
            }
            int i = 0;
            while (i < wifiList.size()) {
                if (TextUtils.isEmpty(this.f1780a.f1795h) || !((ScanResult) wifiList.get(i)).SSID.contains(this.f1780a.f1795h)) {
                    i++;
                } else if (this.f1780a.f1791d.addNetwork(this.f1780a.f1791d.createWifiInfo(((ScanResult) wifiList.get(i)).SSID, null, 1))) {
                    this.f1780a.f1793f = new Timer();
                    this.f1780a.f1794g = new C21191(this);
                    this.f1780a.f1793f.schedule(this.f1780a.f1794g, this.f1780a.f1796i);
                    new C21202(this).start();
                    return;
                } else {
                    this.f1780a.m953b(2);
                    return;
                }
            }
            this.f1780a.m953b(4);
        }
    }

    class ReceiveThread extends Thread {
        final /* synthetic */ Bpm1InsSet f1785a;
        private Timer f1786b;
        private TimerTask f1787c;

        class C21241 extends TimerTask {
            final /* synthetic */ ReceiveThread f1784a;

            C21241(ReceiveThread this$1) {
                this.f1784a = this$1;
            }

            public void run() {
                if (this.f1784a.f1785a.f1797j) {
                    this.f1784a.f1785a.m953b(5);
                }
            }
        }

        private ReceiveThread(Bpm1InsSet bpm1InsSet) {
            this.f1785a = bpm1InsSet;
        }

        private void m939a() {
            this.f1786b = new Timer();
            this.f1787c = new C21241(this);
            this.f1786b.schedule(this.f1787c, 10000);
        }

        private void m940b() {
            if (this.f1786b != null) {
                this.f1786b.cancel();
                this.f1786b.purge();
                this.f1786b = null;
            }
            if (this.f1787c != null) {
                this.f1787c.cancel();
                this.f1787c = null;
            }
        }

        public void run() {
            byte[] bArr = new byte[4096];
            DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length);
            while (this.f1785a.f1797j) {
                try {
                    this.f1785a.f1792e.receive(datagramPacket);
                    this.f1785a.f1788a = datagramPacket.getAddress().toString().replace("/", "");
                    if (datagramPacket.getData().length != 0) {
                        bArr = BPMethod.m913a(datagramPacket.getData());
                        if ((bArr[6] & 255) == 224) {
                            this.f1785a.m946a();
                            this.f1785a.f1790c.onNewDataNotify(this.f1785a.f1789b, BpProfile.ACTION_IDPS_BPM1, BPMethod.m914b(bArr).toString());
                        } else if ((bArr[6] & 255) == JfifUtil.MARKER_APP1) {
                            this.f1785a.f1790c.onNewDataNotify(this.f1785a.f1789b, BpProfile.ACTION_ROUTERS_BPM1, BPMethod.m915c(bArr).toString());
                        } else if ((bArr[6] & 255) == 226) {
                            this.f1785a.m946a();
                            if (BPMethod.m917e(bArr) == 0) {
                                this.f1785a.m953b(6);
                            } else {
                                this.f1785a.m953b(7);
                            }
                        } else {
                            int d = BPMethod.m916d(bArr);
                            switch (d) {
                                case -1:
                                    Log.i("info", "心跳:   " + d);
                                    m940b();
                                    m939a();
                                    break;
                                case 0:
                                    this.f1785a.m953b(8);
                                    break;
                                case 1:
                                    this.f1785a.m953b(9);
                                    break;
                                case 2:
                                    this.f1785a.m953b(10);
                                    break;
                                case 3:
                                    this.f1785a.m953b(11);
                                    break;
                            }
                            if (d != -1) {
                                this.f1785a.m951a(C2132a.m1034a(d));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f1785a.m953b(5);
                    return;
                }
            }
        }
    }

    public Bpm1InsSet(Context context, String type, iHealthDeviceBPM1Callback iHealthDeviceBPM1Callback) {
        Log.p("Bpm1InsSet", Level.INFO, "Bpm1InsSet", new Object[]{type});
        try {
            this.f1789b = type;
            this.f1790c = iHealthDeviceBPM1Callback;
            this.f1791d = new WifiAdmin(context);
            this.f1792e = new DatagramSocket(22342);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private void m946a() {
        if (this.f1800m != null) {
            this.f1800m.cancel();
            this.f1800m.purge();
            this.f1800m = null;
        }
        if (this.f1801n != null) {
            this.f1801n.cancel();
            this.f1801n = null;
        }
    }

    private void m947a(final int i) {
        this.f1800m = new Timer();
        this.f1801n = new TimerTask(this) {
            final /* synthetic */ Bpm1InsSet f1775b;

            public void run() {
                this.f1775b.f1802o = this.f1775b.f1802o + 1;
                if (this.f1775b.f1802o >= 3) {
                    this.f1775b.m953b(5);
                } else if (i == 1) {
                    this.f1775b.m957c();
                } else {
                    this.f1775b.m952b();
                }
            }
        };
        this.f1800m.schedule(this.f1801n, 2000);
    }

    private void m948a(int i, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(BpProfile.STATE_NUMBER_BPM1, i);
            jSONObject.put(BpProfile.STATE_DESCRIPTION_BPM1, str);
            this.f1790c.onNewDataNotify(this.f1789b, BpProfile.ACTION_STATE_BPM1, jSONObject.toString());
        } catch (JSONException e) {
            Log.p("Bpm1InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
        }
    }

    private void m950a(final String str) {
        this.f1791d.startScan();
        new Thread(this) {
            final /* synthetic */ Bpm1InsSet f1783b;

            class C21221 extends TimerTask {
                final /* synthetic */ C21235 f1781a;

                C21221(C21235 this$1) {
                    this.f1781a = this$1;
                }

                public void run() {
                    if (this.f1781a.f1783b.f1791d.getSSID().contains(str)) {
                        this.f1781a.f1783b.m953b(13);
                    } else {
                        this.f1781a.f1783b.m953b(12);
                    }
                }
            }

            public void run() {
                List wifiList = this.f1783b.f1791d.getWifiList();
                while (true) {
                    if (wifiList != null && wifiList.size() != 0) {
                        break;
                    }
                    List wifiList2 = this.f1783b.f1791d.getWifiList();
                    try {
                        Thread.sleep(100);
                        wifiList = wifiList2;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        wifiList = wifiList2;
                    }
                }
                for (int i = 0; i < wifiList.size(); i++) {
                    if (((ScanResult) wifiList.get(i)).SSID.contains(str)) {
                        int i2;
                        for (int i3 = 0; i3 < this.f1783b.f1791d.getConfiguration().size(); i3++) {
                            if (((WifiConfiguration) this.f1783b.f1791d.getConfiguration().get(i3)).SSID.contains(str)) {
                                i2 = ((WifiConfiguration) this.f1783b.f1791d.getConfiguration().get(i3)).networkId;
                                break;
                            }
                        }
                        i2 = -1;
                        if (i2 != -1) {
                            this.f1783b.f1791d.connectConfiguration(i2);
                            this.f1783b.m963f();
                            this.f1783b.f1793f = new Timer();
                            this.f1783b.f1794g = new C21221(this);
                            this.f1783b.f1793f.schedule(this.f1783b.f1794g, this.f1783b.f1796i);
                            return;
                        }
                    }
                }
                this.f1783b.m953b(8);
            }
        }.start();
    }

    private void m951a(final byte[] bArr) {
        new Thread(this) {
            final /* synthetic */ Bpm1InsSet f1777b;

            public void run() {
                try {
                    this.f1777b.f1792e.send(new DatagramPacket(bArr, bArr.length, InetAddress.getByName(this.f1777b.f1788a), 22342));
                    interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void m952b() {
        m946a();
        m947a(2);
        m951a(C2132a.m1035a(this.f1799l));
    }

    private void m953b(int i) {
        String str = "Unknown State";
        switch (i) {
            case 1:
                str = "Is connected to the BPM1";
                break;
            case 2:
                str = "Connection BPM1 failed";
                break;
            case 3:
                str = "Connection BPM1 timeout";
                break;
            case 4:
                str = "Not found BPM1";
                break;
            case 5:
                str = "Connection has been disconnected";
                break;
            case 6:
                str = "Don't switch the channel";
                break;
            case 7:
                str = "Switch the channel (at this point, the AP with the mobile phone may lose connection)";
                break;
            case 8:
                str = "Can't find the router";
                break;
            case 9:
                str = "Password error";
                break;
            case 10:
                str = "DHCP error";
                break;
            case 11:
                str = "Set up the success";
                break;
            case 12:
                str = "Connection failed";
                break;
            case 13:
                str = "WiFi is connected to the specified";
                break;
        }
        m948a(i, str);
    }

    private void m957c() {
        m946a();
        m947a(1);
        m958d();
        m951a(C2132a.m1033a());
    }

    private void m958d() {
        if (!this.f1797j) {
            this.f1797j = true;
            this.f1798k = new ReceiveThread();
            this.f1798k.start();
        }
    }

    private void m960e() {
        this.f1791d.startScan();
        new C21214(this).start();
    }

    private void m963f() {
        if (this.f1793f != null) {
            this.f1793f.cancel();
            this.f1793f.purge();
            this.f1793f = null;
        }
        if (this.f1794g != null) {
            this.f1794g.cancel();
            this.f1794g = null;
        }
    }

    public void connectConfiguration(String wifiConfig) {
        Log.p("Bpm1InsSet", Level.INFO, "connectConfiguration", new Object[]{wifiConfig});
        if (!TextUtils.isEmpty(wifiConfig)) {
            if (!this.f1791d.isWifiEnabled()) {
                this.f1791d.openWifi();
            } else if (this.f1791d.getSSID().contains(wifiConfig)) {
                m953b(13);
                return;
            }
            m950a(wifiConfig);
        }
    }

    public void connectDevice(String deviceTag, long delay) {
        Log.p("Bpm1InsSet", Level.INFO, "connectDevice", new Object[]{deviceTag, Long.valueOf(delay)});
        m963f();
        this.f1795h = deviceTag;
        this.f1796i = delay;
        if (!this.f1791d.isWifiEnabled()) {
            this.f1791d.openWifi();
            new C21161(this).start();
        } else if (this.f1791d.isWifiConnected() && this.f1791d.getSSID().contains(deviceTag)) {
            m953b(1);
        } else {
            m960e();
        }
    }

    public void disconnect() {
        Log.p("Bpm1InsSet", Level.INFO, "disconnect", new Object[0]);
        if (this.f1797j) {
            this.f1797j = false;
            this.f1798k.interrupt();
            this.f1798k = null;
        }
        if (this.f1792e == null) {
            return;
        }
        if (VERSION.SDK_INT >= 24) {
            if (!this.f1792e.isClosed()) {
                this.f1792e.close();
            }
            this.f1792e.disconnect();
            return;
        }
        this.f1792e.disconnect();
        this.f1792e.close();
    }

    public void getIDPS() {
        Log.p("Bpm1InsSet", Level.INFO, "getIDPS", new Object[0]);
        this.f1802o = -1;
        m957c();
    }

    public void getRouters() {
        Log.p("Bpm1InsSet", Level.INFO, "getRouters", new Object[0]);
        m951a(C2132a.m1036b());
    }

    public void sendRouter(JSONObject object) {
        Log.p("Bpm1InsSet", Level.INFO, "sendRouter", new Object[]{object.toString()});
        this.f1799l = object;
        this.f1802o = -1;
        m952b();
    }
}
