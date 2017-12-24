package com.ihealth.communication.manager;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.widget.Toast;
import com.evernote.android.job.JobRequest;
import com.facebook.appevents.AppEventsConstants;
import com.github.thunder413.datetimeutils.DateTimeFormat;
import com.google.common.base.Ascii;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.ihealth.communication.a.a;
import com.ihealth.communication.base.ble.AndroidBle;
import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.ble.BleConfig;
import com.ihealth.communication.base.ble.BleConfig.BleUuid;
import com.ihealth.communication.base.ble.BtleCallback;
import com.ihealth.communication.base.bt.BtCommThread;
import com.ihealth.communication.base.bt.BtCommThreadEE;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.usb.FtdiUsb;
import com.ihealth.communication.base.usb.Pl2303Usb;
import com.ihealth.communication.base.wifi.UdpSearchCallback;
import com.ihealth.communication.base.wifi.WifiCommThread;
import com.ihealth.communication.clientmanager.iHealthDeviceClientMap;
import com.ihealth.communication.cloud.a.b;
import com.ihealth.communication.cloudmanager.iHealthDeviceCloudManager;
import com.ihealth.communication.control.ABPMControl;
import com.ihealth.communication.control.AbiControl;
import com.ihealth.communication.control.Am3Control;
import com.ihealth.communication.control.Am3sControl;
import com.ihealth.communication.control.Am4Control;
import com.ihealth.communication.control.BPControl;
import com.ihealth.communication.control.Bg5Control;
import com.ihealth.communication.control.Bg5lControl;
import com.ihealth.communication.control.BgControl;
import com.ihealth.communication.control.Bp3lControl;
import com.ihealth.communication.control.Bp3mControl;
import com.ihealth.communication.control.Bp550BTControl;
import com.ihealth.communication.control.Bp5Control;
import com.ihealth.communication.control.Bp5sControl;
import com.ihealth.communication.control.Bp723Control;
import com.ihealth.communication.control.Bp7Control;
import com.ihealth.communication.control.Bp7sControl;
import com.ihealth.communication.control.Bp926Control;
import com.ihealth.communication.control.BsControl;
import com.ihealth.communication.control.BtmControl;
import com.ihealth.communication.control.DeviceControl;
import com.ihealth.communication.control.Hs3Control;
import com.ihealth.communication.control.Hs4Control;
import com.ihealth.communication.control.Hs4sControl;
import com.ihealth.communication.control.Hs5Control;
import com.ihealth.communication.control.Hs5ControlForBt;
import com.ihealth.communication.control.HsControl;
import com.ihealth.communication.control.HsProfile;
import com.ihealth.communication.control.Po3Control;
import com.ihealth.communication.control.PoControl;
import com.ihealth.communication.ins.F0InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.privatecontrol.AbiControlSubManager;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.IDPS;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import humanize.util.Constants;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
import tw.com.prolific.driver.pl2303.PL2303Driver;

public class iHealthDevicesManager {
    public static final int DEVICE_STATE_CONNECTED = 1;
    public static final int DEVICE_STATE_CONNECTING = 0;
    public static final int DEVICE_STATE_CONNECTIONFAIL = 3;
    public static final int DEVICE_STATE_DISCONNECTED = 2;
    public static final int DEVICE_STATE_RECONNECTING = 4;
    public static final long DISCOVERY_ABPM = 1024;
    public static final long DISCOVERY_AM3 = 1;
    public static final long DISCOVERY_AM3S = 2;
    public static final long DISCOVERY_AM4 = 4;
    public static final long DISCOVERY_BG5 = 4294967296L;
    public static final long DISCOVERY_BG5l = 4096;
    public static final long DISCOVERY_BP3L = 32;
    public static final long DISCOVERY_BP3M = 8388608;
    public static final long DISCOVERY_BP5 = 33554432;
    public static final long DISCOVERY_BP550BT = 128;
    public static final long DISCOVERY_BP5S = 262144;
    public static final long DISCOVERY_BP7 = 67108864;
    public static final long DISCOVERY_BP7S = 16777216;
    public static final long DISCOVERY_BTM = 64;
    public static final long DISCOVERY_CBG = 8192;
    public static final long DISCOVERY_CBP = 2048;
    public static final long DISCOVERY_CBS = 65536;
    public static final long DISCOVERY_CHS = 32768;
    public static final long DISCOVERY_CPO = 16384;
    public static final long DISCOVERY_HS3 = 134217728;
    public static final long DISCOVERY_HS4 = 16;
    public static final long DISCOVERY_HS4S = 268435456;
    public static final long DISCOVERY_HS5_BT = 536870912;
    public static final long DISCOVERY_HS5_WIFI = 1073741824;
    public static final long DISCOVERY_KD723 = 512;
    public static final long DISCOVERY_KD926 = 256;
    public static final long DISCOVERY_PO3 = 8;
    public static final String IHEALTH_COMM_TIMEOUT = "communicate_timeout";
    public static final String IHEALTH_COMM_TIMEOUT_COMMAND_ID = "communicate_timeout_id";
    public static final String IHEALTH_DEVICE_MAC = "mac";
    public static final String IHEALTH_DEVICE_NAME = "name";
    public static final String IHEALTH_DEVICE_TYPE = "type";
    public static final String IHEALTH_MSG_BG5_EE = "com.ihealth.msg.btdevicemanager.bt.bg5.ee";
    public static final String IHEALTH_MSG_BG5_EE_EXTRA = "com.ihealth.msg.btdevicemanager.bt.bg5.extra";
    public static final String MSG_BASECOMMTIMEOUT = "ihealth_base_comm_timeout";
    public static final String TYPE_550BT = "KN-550BT";
    public static final String TYPE_ABPM = "ABPM";
    public static final String TYPE_AM3 = "AM3";
    public static final String TYPE_AM3S = "AM3S";
    public static final String TYPE_AM4 = "AM4";
    public static final String TYPE_BG1 = "BG1";
    public static final String TYPE_BG5 = "BG5";
    public static final String TYPE_BG5l = "BG5L";
    public static final String TYPE_BP3L = "BP3L";
    public static final String TYPE_BP3M = "BP3M";
    public static final String TYPE_BP5 = "BP5";
    public static final String TYPE_BP5S = "BP5S";
    public static final String TYPE_BP7 = "BP7";
    public static final String TYPE_BP7S = "BP7S";
    public static final String TYPE_BPM1 = "BPM1";
    public static final String TYPE_BTM = "BTM";
    public static final String TYPE_CBG = "CBG";
    public static final String TYPE_CBP = "CBP";
    public static final String TYPE_CBS = "CBS";
    public static final String TYPE_CHS = "CHS";
    public static final String TYPE_CPO = "CPO";
    public static final String TYPE_HS3 = "HS3";
    public static final String TYPE_HS4 = "HS4";
    public static final String TYPE_HS4S = "HS4S";
    public static final String TYPE_HS5 = "HS5";
    public static final String TYPE_HS5S = "HS5S";
    public static final String TYPE_HS5_BT = "HS5BT";
    public static final String TYPE_HS6 = "HS6";
    public static final String TYPE_KD723 = "KD-723";
    public static final String TYPE_KD926 = "KD-926";
    public static final String TYPE_PO3 = "PO3";
    private static DatagramSocket aO;
    private static Timer aT;
    public static boolean stopUDPSearch = false;
    private Map f1942A;
    private Map f1943B;
    private Map f1944C;
    private Map f1945D;
    private Map f1946E;
    private Map f1947F;
    private Map f1948G;
    private Map f1949H;
    private Map f1950I;
    private Map f1951J;
    private Map f1952K;
    private Map f1953L;
    private Map f1954M;
    private Map f1955N;
    private final BaseCommCallback f1956O;
    private iHealthDeviceCloudManager f1957P;
    private String f1958Q;
    private Bp3mControl f1959R;
    private Bp3lControl f1960S;
    private Bp5Control f1961T;
    private Bp5sControl f1962U;
    private Bp7Control f1963V;
    private Bp7sControl f1964W;
    private Bp550BTControl f1965X;
    private Bp926Control f1966Y;
    private Bp723Control f1967Z;
    boolean f1968a;
    private BroadcastReceiver aA;
    private IDPS aB;
    private Map aC;
    private BleComm aD;
    private BaseComm aE;
    private String aF;
    private BleUuid aG;
    private Map aH;
    private ArrayList aI;
    private int aJ;
    private WifiCommThread aK;
    private WifiManager aL;
    private Handler aM;
    private HandlerThread aN;
    private boolean aP;
    private UdpSearchCallback aQ;
    private TimerTask aR;
    private boolean aS;
    private InetAddress aU;
    private DatagramPacket aV;
    private byte[] aW;
    private final Handler aX;
    private NotifyThread aY;
    private ScanThread aZ;
    private ABPMControl aa;
    private Hs3Control ab;
    private Hs4Control ac;
    private Hs4sControl ad;
    private Hs5ControlForBt ae;
    private Am3Control af;
    private Am3sControl ag;
    private Am4Control ah;
    private Po3Control ai;
    private Hs5Control aj;
    private Bg5Control ak;
    private BtmControl al;
    private BPControl am;
    private Bg5lControl an;
    private BgControl ao;
    private PoControl ap;
    private HsControl aq;
    private BsControl ar;
    private Map as;
    private SharedPreferences at;
    private Editor au;
    private iHealthDeviceClientMap av;
    private C2155j aw;
    private boolean ax;
    private Date ay;
    private final Object az;
    ScanFinishThread f1969b;
    private ConnectionThread ba;
    private int bb;
    private UsbManager bc;
    private int bd;
    private byte be;
    private byte bf;
    private byte bg;
    private byte bh;
    private int bi;
    private PendingIntent bj;
    private FtdiUsb bk;
    private Pl2303Usb bl;
    private BtCommThreadEE f1970c;
    public Map commandCacheControlMap;
    private long f1971d;
    private long f1972e;
    private Context f1973f;
    private BluetoothAdapter f1974g;
    private Map f1975h;
    private Map f1976i;
    private Map f1977j;
    private Map f1978k;
    private Map f1979l;
    private Map f1980m;
    public BtleCallback mBtleCallback;
    private Map f1981n;
    private Map f1982o;
    private Map f1983p;
    private Map f1984q;
    private Map f1985r;
    private Map f1986s;
    private Map f1987t;
    private Map f1988u;
    private Map f1989v;
    private Map f1990w;
    private Map f1991x;
    private Map f1992y;
    private Map f1993z;

    class C21431 extends BaseCommCallback {
        C21431() {
        }

        public void onConnectionStateChange(String mac, String type, int status, int errorID, Map manufactorData) {
            Object obj = 1;
            if (iHealthDevicesManager.this.ba != null) {
                if (1 != status) {
                    if (2 == status) {
                        if (iHealthDevicesManager.this.as.get(mac) == null) {
                            status = 3;
                        }
                        if (mac != null) {
                            iHealthDevicesManager.this.m1109b(mac, type);
                            obj = null;
                        }
                    } else if (status == 0) {
                        if (iHealthDevicesManager.TYPE_HS5.equals(type)) {
                            iHealthDevicesManager.this.f1944C.put(mac, mac);
                            obj = null;
                        }
                    } else if (mac != null) {
                        iHealthDevicesManager.this.m1109b(mac, type);
                    }
                    obj = null;
                } else if (iHealthDevicesManager.this.as.get(mac) == null) {
                    iHealthDevicesManager.this.m1097a(mac, type);
                    obj = null;
                }
                if (obj == null) {
                    iHealthDevicesManager.this.ba.setConnectionMessage(mac, type, status, errorID, manufactorData);
                    iHealthDevicesManager.this.aX.postDelayed(iHealthDevicesManager.this.ba, 100);
                }
            }
        }
    }

    class C21453 extends InsCallback {
        C21453() {
        }

        public void onNotify(String mac, String type, String action, String message) {
            iHealthDevicesManager.this.aY.setNotifyMessage(mac, type, action, message);
            iHealthDevicesManager.this.aX.post(iHealthDevicesManager.this.aY);
        }
    }

    class C21474 implements C2146o {
        C21474() {
        }

        public void onStop() {
            if (iHealthDevicesManager.this.f1969b != null) {
                iHealthDevicesManager.this.aX.post(iHealthDevicesManager.this.f1969b);
            }
        }
    }

    class C21485 implements C2146o {
        C21485() {
        }

        public void onStop() {
            if (iHealthDevicesManager.this.f1969b != null) {
                iHealthDevicesManager.this.aX.post(iHealthDevicesManager.this.f1969b);
            }
        }
    }

    class C21496 extends BroadcastReceiver {
        C21496() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!action.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED")) {
                String address;
                String name;
                if ("android.bluetooth.device.action.FOUND".equals(action)) {
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    address = bluetoothDevice.getAddress();
                    action = bluetoothDevice.getName();
                    Log.m1213p("Runtime_iHealthDM", Level.VERBOSE, "ACTION_FOUND", bluetoothDevice.getName(), bluetoothDevice.getAddress());
                    if (VERSION.SDK_INT < 18 || (VERSION.SDK_INT >= 18 && bluetoothDevice.getType() != 2)) {
                        if (action != null && action.contains(iHealthDevicesManager.TYPE_BG5) && (iHealthDevicesManager.this.f1971d & iHealthDevicesManager.DISCOVERY_BG5) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_BG5, bluetoothDevice, null, null, 201, 0, new HashMap());
                        } else if (action != null && action.contains(iHealthDevicesManager.TYPE_BP5) && (iHealthDevicesManager.this.f1971d & iHealthDevicesManager.DISCOVERY_BP5) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_BP5, bluetoothDevice, null, null, 201, 0, new HashMap());
                        } else if (action != null && action.contains(iHealthDevicesManager.TYPE_BP7S) && (iHealthDevicesManager.this.f1971d & iHealthDevicesManager.DISCOVERY_BP7S) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_BP7S, bluetoothDevice, null, null, 201, 0, new HashMap());
                        } else if (action != null && action.contains(iHealthDevicesManager.TYPE_BP7) && (iHealthDevicesManager.this.f1971d & iHealthDevicesManager.DISCOVERY_BP7) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_BP7, bluetoothDevice, null, null, 201, 0, new HashMap());
                        } else if (action != null && action.contains("iHealth HS3") && (iHealthDevicesManager.this.f1971d & iHealthDevicesManager.DISCOVERY_HS3) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_HS3, bluetoothDevice, null, null, 201, 0, new HashMap());
                        } else if (action != null && action.contains(iHealthDevicesManager.TYPE_HS4S) && (iHealthDevicesManager.this.f1971d & iHealthDevicesManager.DISCOVERY_HS4S) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_HS4S, bluetoothDevice, null, null, 201, 0, new HashMap());
                        } else if (action != null && action.contains("iHealth HS5") && (iHealthDevicesManager.this.f1971d & iHealthDevicesManager.DISCOVERY_HS5_BT) != 0) {
                            name = bluetoothDevice.getName();
                            Object obj = "";
                            if (!TextUtils.isEmpty(name)) {
                                int indexOf = name.indexOf(iHealthDevicesManager.TYPE_HS5);
                                if (indexOf != -1) {
                                    obj = name.substring(indexOf + 3);
                                } else {
                                    Log.m1211e("Runtime_iHealthDM", "HS5 not in the name of the bluetooth device.");
                                }
                            }
                            Map hashMap = new HashMap();
                            hashMap.put(HsProfile.SCALE_WIFI_MAC_SUFFIX, obj);
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_HS5_BT, bluetoothDevice, null, null, 201, 0, hashMap);
                        } else if (action != null && action.contains("926") && (iHealthDevicesManager.this.f1971d & 256) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_KD926, bluetoothDevice, null, null, 202, 0, new HashMap());
                        } else if (action != null && action.contains("723") && (iHealthDevicesManager.this.f1971d & 512) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_KD723, bluetoothDevice, null, null, 202, 0, new HashMap());
                        } else if (action != null && action.contains("BP Monitor") && (iHealthDevicesManager.this.f1971d & 1024) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_ABPM, bluetoothDevice, null, null, 202, 0, new HashMap());
                        }
                    } else if (VERSION.SDK_INT >= 18 && bluetoothDevice.getType() == 2) {
                        if (action != null && ((action.contains("Activity Monitor") || (action.contains(iHealthDevicesManager.TYPE_AM3) && !action.contains(iHealthDevicesManager.TYPE_AM3S))) && (iHealthDevicesManager.this.f1971d & 1) != 0)) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_AM3, bluetoothDevice, null, null, 202, 0, new HashMap());
                        } else if (action != null && action.contains(iHealthDevicesManager.TYPE_AM3S) && (iHealthDevicesManager.this.f1971d & 2) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_AM3S, bluetoothDevice, null, null, 202, 0, new HashMap());
                        } else if (action != null && action.contains(iHealthDevicesManager.TYPE_AM4) && (iHealthDevicesManager.this.f1971d & 4) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_AM4, bluetoothDevice, null, null, 202, 0, new HashMap());
                        } else if (action != null && action.contains(iHealthDevicesManager.TYPE_BP3L) && (iHealthDevicesManager.this.f1971d & 32) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_BP3L, bluetoothDevice, null, null, 202, 0, new HashMap());
                        } else if (action != null && action.contains(iHealthDevicesManager.TYPE_BP5S) && (iHealthDevicesManager.this.f1971d & 262144) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_BP5S, bluetoothDevice, null, null, 202, 0, new HashMap());
                        } else if (action != null && ((action.contains("Body Scale") || (action.contains(iHealthDevicesManager.TYPE_HS4) && !action.contains(iHealthDevicesManager.TYPE_HS4S))) && (iHealthDevicesManager.this.f1971d & 16) != 0)) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_HS4, bluetoothDevice, null, null, 202, 0, new HashMap());
                        } else if (action != null && ((action.contains("Pulse Oximeter") || action.contains(iHealthDevicesManager.TYPE_PO3)) && (iHealthDevicesManager.this.f1971d & 8) != 0)) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_PO3, bluetoothDevice, null, null, 202, 0, new HashMap());
                        } else if (action != null && action.contains("550") && (iHealthDevicesManager.this.f1971d & 128) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_550BT, bluetoothDevice, null, null, 202, 0, new HashMap());
                        } else if (action != null && action.contains("FDTH") && (iHealthDevicesManager.this.f1971d & 64) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_BTM, bluetoothDevice, null, null, 202, 0, new HashMap());
                        } else if (action != null && action.contains("926") && (iHealthDevicesManager.this.f1971d & 256) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_KD926, bluetoothDevice, null, null, 202, 0, new HashMap());
                        } else if (action != null && action.contains("723") && (iHealthDevicesManager.this.f1971d & 512) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_KD723, bluetoothDevice, null, null, 202, 0, new HashMap());
                        } else if (action != null && action.contains("BP Monitor") && (iHealthDevicesManager.this.f1971d & 1024) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_ABPM, bluetoothDevice, null, null, 202, 0, new HashMap());
                        } else if (action != null && action.contains(iHealthDevicesManager.TYPE_BG5) && (iHealthDevicesManager.this.f1971d & 4096) != 0) {
                            iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_BG5l, bluetoothDevice, null, null, 202, 0, new HashMap());
                        }
                    }
                } else if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
                    Date date = new Date();
                    if (date.getTime() - iHealthDevicesManager.this.ay.getTime() > 1000) {
                        iHealthDevicesManager.this.ay = date;
                        if (iHealthDevicesManager.this.f1969b != null) {
                            iHealthDevicesManager.this.aX.post(iHealthDevicesManager.this.f1969b);
                        }
                    }
                } else if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                    if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                        iHealthDevicesManager.this.f1968a = false;
                        Log.m1214v("Runtime_iHealthDM", "net is unavailable");
                        if (iHealthDevicesManager.this.f1942A.size() != 0) {
                            for (Entry key : iHealthDevicesManager.this.f1942A.entrySet()) {
                                iHealthDevicesManager.this.f1956O.onConnectionStateChange((String) key.getKey(), iHealthDevicesManager.TYPE_HS5, 2, 0, null);
                            }
                            return;
                        }
                        return;
                    }
                    iHealthDevicesManager.this.f1968a = true;
                    Log.m1214v("Runtime_iHealthDM", "network name：" + activeNetworkInfo.getTypeName());
                } else if (action.equals(iHealthDevicesManager.IHEALTH_MSG_BG5_EE)) {
                    iHealthDevicesManager.this.m1116c(intent.getStringExtra(iHealthDevicesManager.IHEALTH_DEVICE_MAC), intent.getStringExtra(iHealthDevicesManager.IHEALTH_MSG_BG5_EE_EXTRA));
                    Log.m1214v("Runtime_iHealthDM", "Read BG5 idps success");
                    iHealthDevicesManager.this.f1970c.close();
                    new ConnectThread((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), iHealthDevicesManager.TYPE_BG5, false).start();
                } else if (action.equals(iHealthDevicesIDPS.MSG_IHEALTH_DEVICE_IDPS)) {
                    action = intent.getStringExtra(iHealthDevicesIDPS.PROTOCOLSTRING);
                    address = intent.getStringExtra(iHealthDevicesIDPS.ACCESSORYNAME);
                    name = intent.getStringExtra(iHealthDevicesIDPS.FIRMWAREVERSION);
                    String stringExtra = intent.getStringExtra(iHealthDevicesIDPS.HARDWAREVERSION);
                    String stringExtra2 = intent.getStringExtra(iHealthDevicesIDPS.MANUFACTURER);
                    String stringExtra3 = intent.getStringExtra(iHealthDevicesIDPS.MODENUMBER);
                    String stringExtra4 = intent.getStringExtra(iHealthDevicesIDPS.SERIALNUMBER);
                    String stringExtra5 = intent.getStringExtra("type");
                    IDPS idps = new IDPS();
                    idps.setProtoclString(action);
                    idps.setAccessoryName(address);
                    idps.setAccessoryFirmwareVersion(name);
                    idps.setAccessoryHardwareVersion(stringExtra);
                    idps.setAccessoryManufaturer(stringExtra2);
                    idps.setAccessoryModelNumber(stringExtra3);
                    idps.setAccessorySerialNumber(stringExtra4);
                    idps.setDeviceType(stringExtra5);
                    iHealthDevicesManager.this.aC.put(stringExtra4, idps);
                } else if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(action)) {
                    UsbDevice m = iHealthDevicesManager.this.m1139l();
                    if (m != null && (iHealthDevicesManager.this.f1971d & iHealthDevicesManager.DISCOVERY_BP3M) != 0) {
                        iHealthDevicesManager.this.m1098a("000000000000", iHealthDevicesManager.TYPE_BP3M, null, null, m, ScanDevice.LINK_USB, 0, new HashMap());
                    }
                } else if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
                    if (iHealthDevicesManager.this.f1959R != null) {
                        iHealthDevicesManager.this.f1959R = null;
                        iHealthDevicesManager.this.f1956O.onConnectionStateChange("000000000000", iHealthDevicesManager.TYPE_BP3M, 2, 0, null);
                    }
                    if (iHealthDevicesManager.this.bk != null) {
                        iHealthDevicesManager.this.bk.disConnectFunction();
                    }
                    if (iHealthDevicesManager.this.bl != null) {
                        iHealthDevicesManager.this.bl.disConnectFunction();
                    }
                } else if ("com.android.example.USB_PERMISSION".equals(action)) {
                    iHealthDevicesManager.this.m1142m();
                } else if (iHealthDevicesManager.MSG_BASECOMMTIMEOUT.equals(action)) {
                    iHealthDevicesManager.this.aY.setNotifyMessage(intent.getStringExtra(iHealthDevicesManager.IHEALTH_DEVICE_MAC), "", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT, "");
                    iHealthDevicesManager.this.aX.post(iHealthDevicesManager.this.aY);
                }
            }
        }
    }

    class C21528 implements BtleCallback {

        class C21511 implements Runnable {
            C21511() {
            }

            public void run() {
                iHealthDevicesManager.this.m1130g(iHealthDevicesManager.this.aB.getDeviceMac(), iHealthDevicesManager.this.aB.getDeviceType());
            }
        }

        C21528() {
        }

        private void commandSaveSpecialPhone(String tempMac) {
            if (((IDPS) iHealthDevicesManager.this.aC.get(tempMac)) == null) {
                return;
            }
            if (VERSION.RELEASE.startsWith("4.3") || VERSION.RELEASE.startsWith("4.4")) {
                Log.m1214v("Runtime_iHealthDM", "Special phone");
                SharedPreferences sharedPreferences = iHealthDevicesManager.this.f1973f.getSharedPreferences("SpecialPhone" + VERSION.RELEASE, 0);
                if (!sharedPreferences.getBoolean("FirstSpecialFlag", false)) {
                    Editor edit = sharedPreferences.edit();
                    edit.putInt("SpecialPhoneStatus", 1);
                    edit.putBoolean("FirstSpecialFlag", true);
                    edit.commit();
                }
            }
        }

        public void onCharacteristicChanged(BluetoothDevice device, byte[] characteristicChangedValue) {
            Log.m1213p("Runtime_iHealthDM", Level.VERBOSE, "onCharacteristicChanged", device.getAddress().replace(":", ""));
        }

        public void onCharacteristicRead(byte[] characteristicReadValue, UUID charUuid, int status) {
            int i = 0;
            String uuid = charUuid.toString();
            int length;
            int i2;
            if (uuid.equals(iHealthDevicesManager.this.aG.PROTOCOL_STRING)) {
                length = characteristicReadValue.length;
                i2 = 0;
                while (i < length && characteristicReadValue[i] != (byte) 0) {
                    i2++;
                    i++;
                }
                try {
                    iHealthDevicesManager.this.aB.setProtoclString(new String(ByteBufferUtil.bufferCut(characteristicReadValue, 0, i2), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                iHealthDevicesManager.this.m1129g();
            } else if (uuid.equals(iHealthDevicesManager.this.aG.ACCESSORY_NAME)) {
                length = characteristicReadValue.length;
                i2 = 0;
                while (i < length && characteristicReadValue[i] != (byte) 0) {
                    i2++;
                    i++;
                }
                try {
                    iHealthDevicesManager.this.aB.setAccessoryName(new String(ByteBufferUtil.bufferCut(characteristicReadValue, 0, i2), "UTF-8"));
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
                iHealthDevicesManager.this.m1129g();
            } else if (uuid.equals(iHealthDevicesManager.this.aG.ACCESSORY_FIRMWARE_VERSION)) {
                if (characteristicReadValue[0] <= Ascii.RS || characteristicReadValue.length != 3) {
                    iHealthDevicesManager.this.aB.setAccessoryFirmwareVersion(ByteBufferUtil.Bytes2HexString(characteristicReadValue));
                } else {
                    try {
                        iHealthDevicesManager.this.aB.setAccessoryFirmwareVersion(String.format("%c.%c.%c", new Object[]{Byte.valueOf(characteristicReadValue[0]), Byte.valueOf(characteristicReadValue[1]), Byte.valueOf(characteristicReadValue[2])}));
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                iHealthDevicesManager.this.m1129g();
            } else if (uuid.equals(iHealthDevicesManager.this.aG.ACCESSORY_HARDWARE_VERSION)) {
                if (characteristicReadValue[0] > Ascii.RS && characteristicReadValue.length == 3) {
                    try {
                        iHealthDevicesManager.this.aB.setAccessoryHardwareVersion(String.format("%c.%c.%c", new Object[]{Byte.valueOf(characteristicReadValue[0]), Byte.valueOf(characteristicReadValue[1]), Byte.valueOf(characteristicReadValue[2])}));
                    } catch (Exception e32) {
                        e32.printStackTrace();
                    }
                } else if (characteristicReadValue[0] == (byte) 0 && characteristicReadValue[1] == (byte) 53 && characteristicReadValue[2] == (byte) 48) {
                    iHealthDevicesManager.this.aB.setAccessoryHardwareVersion("5.0.1");
                } else {
                    iHealthDevicesManager.this.aB.setAccessoryHardwareVersion(ByteBufferUtil.Bytes2HexString(characteristicReadValue));
                }
                iHealthDevicesManager.this.m1129g();
            } else if (uuid.equals(iHealthDevicesManager.this.aG.ACCESSORY_MANUFA)) {
                length = characteristicReadValue.length;
                i2 = 0;
                while (i < length && characteristicReadValue[i] != (byte) 0) {
                    i2++;
                    i++;
                }
                try {
                    iHealthDevicesManager.this.aB.setAccessoryManufaturer(new String(ByteBufferUtil.bufferCut(characteristicReadValue, 0, i2), "UTF-8"));
                } catch (UnsupportedEncodingException e22) {
                    e22.printStackTrace();
                }
                iHealthDevicesManager.this.m1129g();
            } else if (uuid.equals(iHealthDevicesManager.this.aG.ACCESSORY_MODEL)) {
                length = characteristicReadValue.length;
                i2 = 0;
                while (i < length && characteristicReadValue[i] != (byte) 0) {
                    i2++;
                    i++;
                }
                try {
                    iHealthDevicesManager.this.aB.setAccessoryModelNumber(new String(ByteBufferUtil.bufferCut(characteristicReadValue, 0, i2), "UTF-8"));
                } catch (UnsupportedEncodingException e222) {
                    e222.printStackTrace();
                }
                iHealthDevicesManager.this.m1129g();
            } else if (uuid.equals(iHealthDevicesManager.this.aG.ACCESSORY_SERIAL)) {
                iHealthDevicesManager.this.aB.setAccessorySerialNumber(ByteBufferUtil.Bytes2HexString(characteristicReadValue));
                iHealthDevicesManager.this.m1129g();
            } else {
                Log.m1214v("Runtime_iHealthDM", "Invalidate characteristic UUID");
            }
        }

        public void onConnectionStateChange(BluetoothDevice device, int status, int newState) {
            String replace = device.getAddress().replace(":", "");
            Log.m1213p("Runtime_iHealthDM", Level.VERBOSE, "onConnectionStateChange", replace, Integer.valueOf(status), Integer.valueOf(newState));
            if (newState == 4) {
                iHealthDevicesManager.this.f1956O.onConnectionStateChange(replace, (String) iHealthDevicesManager.this.f1955N.get(replace), 4, status, null);
            } else if (status == 0 && newState == 2) {
                iHealthDevicesManager.this.aF = device.getAddress();
            } else if (status == 0 && newState == 0) {
                r2 = (String) iHealthDevicesManager.this.f1955N.get(replace);
                if (((String) iHealthDevicesManager.this.as.get(replace)) != null) {
                    iHealthDevicesManager.this.f1956O.onConnectionStateChange(replace, r2, 2, status, null);
                    r0 = new Intent();
                    r0.setAction(F0InsSet.MSG_DISCONNECTED);
                    r0.putExtra(iHealthDevicesManager.IHEALTH_DEVICE_MAC, replace);
                    r0.setPackage(iHealthDevicesManager.this.f1973f.getPackageName());
                    iHealthDevicesManager.this.f1973f.sendBroadcast(r0);
                    return;
                }
                commandSaveSpecialPhone(replace);
                iHealthDevicesManager.this.f1956O.onConnectionStateChange(replace, r2, 3, status, null);
            } else {
                r2 = (String) iHealthDevicesManager.this.f1955N.get(replace);
                if (((String) iHealthDevicesManager.this.as.get(replace)) != null) {
                    iHealthDevicesManager.this.f1956O.onConnectionStateChange(replace, r2, 2, status, null);
                    r0 = new Intent();
                    r0.setAction(F0InsSet.MSG_DISCONNECTED);
                    r0.putExtra(iHealthDevicesManager.IHEALTH_DEVICE_MAC, replace);
                    r0.setPackage(iHealthDevicesManager.this.f1973f.getPackageName());
                    iHealthDevicesManager.this.f1973f.sendBroadcast(r0);
                    return;
                }
                commandSaveSpecialPhone(replace);
                iHealthDevicesManager.this.f1956O.onConnectionStateChange(replace, r2, 3, status, null);
            }
        }

        public void onScanResult(BluetoothDevice scandevice, int rssi, String serviceUuid, Map manufactorData) {
            String address = scandevice.getAddress();
            String name = scandevice.getName();
            if (serviceUuid.contains(BleConfig.UUID_HS4_SERVICE) && (iHealthDevicesManager.this.f1971d & 16) != 0) {
                iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_HS4, scandevice, null, null, 202, rssi, manufactorData);
            } else if (serviceUuid.contains(BleConfig.UUID_ABPM_SERVICE) && (iHealthDevicesManager.this.f1971d & 1024) != 0) {
                iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_ABPM, scandevice, null, null, 202, rssi, manufactorData);
            } else if ((serviceUuid.contains(BleConfig.UUID_PO3_SERVICE) || serviceUuid.contains(BleConfig.UUID_PO3_SERVICE_128)) && (iHealthDevicesManager.this.f1971d & 8) != 0) {
                iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_PO3, scandevice, null, null, 202, rssi, manufactorData);
            } else if ((serviceUuid.contains(BleConfig.UUID_AM3_SERVICE) || serviceUuid.contains(BleConfig.UUID_AM3_Qualcomm_SERVICE)) && (iHealthDevicesManager.this.f1971d & 1) != 0) {
                iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_AM3, scandevice, null, null, 202, rssi, manufactorData);
            } else if (serviceUuid.contains(BleConfig.UUID_AM3S_SERVICE) && (iHealthDevicesManager.this.f1971d & 2) != 0) {
                iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_AM3S, scandevice, null, null, 202, rssi, manufactorData);
            } else if (serviceUuid.contains(BleConfig.UUID_AM4_SERVICE) && (iHealthDevicesManager.this.f1971d & 4) != 0) {
                iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_AM4, scandevice, null, null, 202, rssi, manufactorData);
            } else if (serviceUuid.contains(BleConfig.UUID_BP3L_SERVICE) && (iHealthDevicesManager.this.f1971d & 32) != 0) {
                iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_BP3L, scandevice, null, null, 202, rssi, manufactorData);
            } else if (serviceUuid.contains(BleConfig.UUID_AV20_SERVICE) && (iHealthDevicesManager.this.f1971d & 262144) != 0) {
                iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_BP5S, scandevice, null, null, 202, rssi, manufactorData);
            } else if (serviceUuid.contains(BleConfig.UUID_AV10_SERVICE)) {
                if (name == null) {
                    return;
                }
                if (name.contains("926") && (iHealthDevicesManager.this.f1971d & 256) != 0) {
                    iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_KD926, scandevice, null, null, 202, rssi, manufactorData);
                } else if (name.contains("723") && (iHealthDevicesManager.this.f1971d & 512) != 0) {
                    iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_KD723, scandevice, null, null, 202, rssi, manufactorData);
                } else if (name.contains("550") && (iHealthDevicesManager.this.f1971d & 128) != 0) {
                    iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_550BT, scandevice, null, null, 202, rssi, manufactorData);
                }
            } else if (serviceUuid.contains("636f6d2e-6a69-7561-6e2e-424c45303100") && (iHealthDevicesManager.this.f1971d & 64) != 0) {
                iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_BTM, scandevice, null, null, 202, rssi, manufactorData);
            } else if (serviceUuid.contains(BleConfig.UUID_BP_SERVICE) && (iHealthDevicesManager.this.f1971d & 2048) != 0) {
                iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_CBP, scandevice, null, null, 202, rssi, manufactorData);
            } else if (serviceUuid.contains(BleConfig.UUID_BG5l_SERVICE) && (iHealthDevicesManager.this.f1971d & 4096) != 0) {
                iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_BG5l, scandevice, null, null, 202, rssi, manufactorData);
            } else if (serviceUuid.contains(BleConfig.UUID_BG_SERVICE) && (iHealthDevicesManager.this.f1971d & 8192) != 0) {
                iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_CBG, scandevice, null, null, 202, rssi, manufactorData);
            } else if (serviceUuid.contains(BleConfig.UUID_PO_SERVICE) && (iHealthDevicesManager.this.f1971d & 16384) != 0) {
                iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_CPO, scandevice, null, null, 202, rssi, manufactorData);
            } else if (serviceUuid.contains(BleConfig.UUID_HS_SERVICE) && (iHealthDevicesManager.this.f1971d & 32768) != 0) {
                iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_CHS, scandevice, null, null, 202, rssi, manufactorData);
            } else if (serviceUuid.contains(BleConfig.UUID_BS_SERVICE) && (iHealthDevicesManager.this.f1971d & 65536) != 0) {
                iHealthDevicesManager.this.m1098a(address, iHealthDevicesManager.TYPE_CBS, scandevice, null, null, 202, rssi, manufactorData);
            }
        }

        public void onServicesDiscovered(BluetoothDevice device, List uuids, int status) {
            iHealthDevicesManager.this.aG = BleConfig.getUuidString(uuids);
            iHealthDevicesManager.this.aB = new IDPS();
            iHealthDevicesManager.this.aB.setDeviceName(device.getName());
            iHealthDevicesManager.this.aB.setDeviceMac(device.getAddress().replace(":", ""));
            String str = iHealthDevicesManager.this.aG.BLE_SERVICE;
            if (str == null) {
                Log.m1211e("Runtime_iHealthDM", "Not found service");
                iHealthDevicesManager.this.aD.refresh(device.getAddress().replace(":", ""));
                return;
            }
            str = iHealthDevicesManager.this.m1126f(str, device.getName());
            iHealthDevicesManager.this.aB.setDeviceType(str);
            iHealthDevicesManager.this.f1955N.put(device.getAddress().replace(":", ""), str);
            if (iHealthDevicesManager.this.aI != null) {
                iHealthDevicesManager.this.aI.clear();
            }
            iHealthDevicesManager.this.m1127f();
            iHealthDevicesManager.this.aI = iHealthDevicesManager.this.aG.listUuid;
            iHealthDevicesManager.this.aH.put(device.getAddress().replace(":", ""), iHealthDevicesManager.this.aG);
            iHealthDevicesManager.this.m1129g();
        }

        public void onServicesObtain() {
            int i = 0;
            if ((VERSION.RELEASE.startsWith("4.3") || VERSION.RELEASE.startsWith("4.4")) && iHealthDevicesManager.this.f1973f.getSharedPreferences("SpecialPhone" + VERSION.RELEASE, 0).getInt("SpecialPhoneStatus", 0) > 0) {
                i = 5000;
                Log.m1214v("Runtime_iHealthDM", "Special phone identify");
            }
            new Handler(Looper.getMainLooper()).postDelayed(new C21511(), (long) i);
        }
    }

    class C21539 extends UdpSearchCallback {
        C21539() {
        }

        public void searchUdpNotify(byte[] datas) {
            iHealthDevicesManager.this.m1099a(ByteBufferUtil.Bytes2HexString(ByteBufferUtil.bytesCutt(datas.length - 7, datas.length - 2, datas)), ByteBufferUtil.bytesCutt(6, datas.length - 8, datas));
        }
    }

    class ConnectThread extends Thread {
        private BluetoothDevice device;
        private String mType;
        private boolean needReadEE = false;
        private BluetoothSocket socket;
        private Timer timerSocket = null;
        private TimerTask timerTaskSocket = null;
        private UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

        public ConnectThread(BluetoothDevice device, String type, boolean needEE) {
            this.device = device;
            this.mType = type;
            this.needReadEE = needEE;
        }

        private boolean cancelConnectTimeout(String tag) {
            boolean z = true;
            if (this.timerSocket != null) {
                this.timerSocket.cancel();
                this.timerSocket = null;
            } else {
                z = false;
            }
            if (this.timerTaskSocket != null) {
                this.timerTaskSocket.cancel();
                this.timerTaskSocket = null;
            }
            return z;
        }

        private void connectTimeout(final String tempMac, final String tempType, long delay) {
            this.timerSocket = new Timer();
            this.timerTaskSocket = new TimerTask() {
                public void run() {
                    ConnectThread.this.timerSocket = null;
                    ConnectThread.this.timerTaskSocket = null;
                    try {
                        if (ConnectThread.this.socket != null) {
                            ConnectThread.this.socket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (iHealthDevicesManager.this.f1956O != null) {
                        iHealthDevicesManager.this.f1956O.onConnectionStateChange(tempMac, tempType, 3, 0, null);
                    }
                }
            };
            this.timerSocket.schedule(this.timerTaskSocket, delay);
        }

        private void createIOStream() {
            if (this.needReadEE) {
                try {
                    iHealthDevicesManager.this.f1970c = new BtCommThreadEE(iHealthDevicesManager.this.f1973f, this.device, this.socket, iHealthDevicesManager.this.f1956O);
                    iHealthDevicesManager.this.f1970c.start();
                    SystemClock.sleep(500);
                    iHealthDevicesManager.this.m1090a(iHealthDevicesManager.this.f1970c, this.device.getAddress().replace(":", ""), this.device.getName(), this.needReadEE);
                    return;
                } catch (IOException e) {
                    Log.m1215w("Runtime_iHealthDM", "createIOStream -- e: " + e);
                    iHealthDevicesManager.this.f1956O.onConnectionStateChange(this.device.getAddress().replace(":", ""), this.mType, 3, 0, null);
                    return;
                }
            }
            try {
                BaseComm btCommThread = new BtCommThread(iHealthDevicesManager.this.f1973f, this.device, this.mType, this.socket, iHealthDevicesManager.this.f1956O);
                btCommThread.start();
                SystemClock.sleep(500);
                iHealthDevicesManager.this.m1090a(btCommThread, this.device.getAddress().replace(":", ""), this.device.getName(), this.needReadEE);
            } catch (IOException e2) {
                Log.m1215w("Runtime_iHealthDM", "createIOStream -- e: " + e2);
                iHealthDevicesManager.this.f1956O.onConnectionStateChange(this.device.getAddress().replace(":", ""), this.mType, 3, 0, null);
            }
        }

        private boolean createSocket(BluetoothDevice device) {
            Object obj;
            boolean z = false;
            try {
                this.socket = device.createRfcommSocketToServiceRecord(this.uuid);
                this.socket.connect();
            } catch (Exception e) {
                Log.m1215w("Runtime_iHealthDM", "createSocket() -- e1:" + e);
                if (device.getBondState() != 12) {
                    return false;
                }
                try {
                    this.socket = device.createInsecureRfcommSocketToServiceRecord(this.uuid);
                    this.socket.connect();
                } catch (Exception e2) {
                    Log.m1215w("Runtime_iHealthDM", "createSocket() -- e2:" + e2);
                    if (device.getBondState() != 12 || device.getName().contains(iHealthDevicesManager.TYPE_BG5)) {
                        return false;
                    }
                    try {
                        Method method = device.getClass().getMethod("createInsecureRfcommSocket", new Class[]{Integer.TYPE});
                        if (method != null) {
                            try {
                                this.socket = (BluetoothSocket) method.invoke(device, new Object[]{Integer.valueOf(6)});
                                this.socket.connect();
                                return true;
                            } catch (Exception e3) {
                                Exception exception = e3;
                                z = true;
                                Exception exception2 = exception;
                                Log.m1211e("Runtime_iHealthDM", "createSocket() -- e3:" + obj);
                                return z;
                            }
                        }
                        Log.m1211e("Runtime_iHealthDM", "createSocket() -- (m==null)");
                        return false;
                    } catch (Exception e4) {
                        obj = e4;
                        Log.m1211e("Runtime_iHealthDM", "createSocket() -- e3:" + obj);
                        return z;
                    }
                }
            }
            SystemClock.sleep(300);
            return this.socket != null && this.socket.isConnected();
        }

        private boolean createSocketEE(BluetoothDevice device) {
            try {
                this.socket = (BluetoothSocket) device.getClass().getMethod("createRfcommSocket", new Class[]{Integer.TYPE}).invoke(device, new Object[]{Integer.valueOf(10)});
                byte[] hexStringToByte = ByteBufferUtil.hexStringToByte(device.getAddress().replace(":", ""));
                byte[] bArr = new byte[]{hexStringToByte[hexStringToByte.length - 4], hexStringToByte[hexStringToByte.length - 3], hexStringToByte[hexStringToByte.length - 2], hexStringToByte[hexStringToByte.length - 1]};
                device.getClass().getMethod("setPin", new Class[]{byte[].class}).invoke(device, new Object[]{bArr});
                this.socket.connect();
                SystemClock.sleep(300);
                return this.socket != null && this.socket.isConnected();
            } catch (SecurityException e) {
                e.printStackTrace();
                return false;
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
                return false;
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
                return false;
            } catch (IllegalAccessException e4) {
                e4.printStackTrace();
                return false;
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
                return false;
            } catch (IOException e6) {
                e6.printStackTrace();
                return false;
            }
        }

        public void run() {
            int i = 1;
            super.run();
            try {
                connectTimeout(this.device.getAddress().replace(":", ""), this.mType, JobRequest.DEFAULT_BACKOFF_MS);
                if (this.needReadEE) {
                    createSocketEE(this.device);
                    if (!this.socket.isConnected()) {
                        Log.m1214v("Runtime_iHealthDM", "create ee socket -- continue create Socket");
                        this.needReadEE = false;
                        createSocket(this.device);
                    }
                } else {
                    createSocket(this.device);
                }
                if (!cancelConnectTimeout("createSocket() - End")) {
                    return;
                }
                if (this.socket.isConnected()) {
                    Log.m1214v("Runtime_iHealthDM", "createIOStream()");
                    createIOStream();
                    return;
                }
                if (this.device.getBondState() == 12) {
                    i = 0;
                }
                iHealthDevicesManager.this.f1956O.onConnectionStateChange(this.device.getAddress().replace(":", ""), this.mType, 3, i, null);
            } catch (Exception e) {
                Log.m1211e("Runtime_iHealthDM", "createSocket() -- Exception: " + e);
                iHealthDevicesManager.this.f1956O.onConnectionStateChange(this.device.getAddress().replace(":", ""), this.mType, 3, 0, null);
            }
        }
    }

    class ConnectionThread implements Runnable {
        private Queue connectionResultQueue = new LinkedList();

        class ConnectionInfoClass {
            public int errorID;
            public String mac;
            public Map manufactorData;
            public int status;
            public String type;

            ConnectionInfoClass() {
            }
        }

        public void run() {
            ConnectionInfoClass connectionInfoClass = (ConnectionInfoClass) this.connectionResultQueue.poll();
            if (connectionInfoClass != null) {
                int i;
                Log.m1213p("Runtime_iHealthDM", Level.INFO, "onDeviceConnectionStateChange", connectionInfoClass.mac, connectionInfoClass.type, Integer.valueOf(connectionInfoClass.status), Integer.valueOf(connectionInfoClass.errorID));
                List<iHealthDevicesCallback> callbacklist = iHealthDevicesManager.this.av.getCallbacklist(connectionInfoClass.mac, connectionInfoClass.type);
                if (callbacklist.size() > 0) {
                    i = 0;
                    for (iHealthDevicesCallback com_ihealth_communication_manager_iHealthDevicesCallback : callbacklist) {
                        com_ihealth_communication_manager_iHealthDevicesCallback.onDeviceConnectionStateChange(connectionInfoClass.mac, connectionInfoClass.type, connectionInfoClass.status, connectionInfoClass.errorID);
                        com_ihealth_communication_manager_iHealthDevicesCallback.onDeviceConnectionStateChange(connectionInfoClass.mac, connectionInfoClass.type, connectionInfoClass.status, connectionInfoClass.errorID, connectionInfoClass.manufactorData);
                        i = 1;
                    }
                } else {
                    iHealthDevicesManager.this.m1085a();
                    i = 0;
                }
                if (i == 0) {
                    Log.m1215w("Runtime_iHealthDM", "ConnectionThread -- Invalid callback");
                }
            }
        }

        public void setConnectionMessage(String mac, String type, int status, int errorID, Map manufactorData) {
            ConnectionInfoClass connectionInfoClass = new ConnectionInfoClass();
            connectionInfoClass.mac = mac;
            connectionInfoClass.type = type;
            connectionInfoClass.status = status;
            connectionInfoClass.errorID = errorID;
            connectionInfoClass.manufactorData = manufactorData;
            this.connectionResultQueue.offer(connectionInfoClass);
            if (status == 2) {
                a aVar = (a) iHealthDevicesManager.this.commandCacheControlMap.get(mac);
                if (aVar != null) {
                    aVar.a();
                    iHealthDevicesManager.this.commandCacheControlMap.remove(mac);
                }
            }
        }
    }

    class NotifyThread implements Runnable {
        private Queue notifyResultQueue = new ConcurrentLinkedQueue();

        class NotifyInfoClass {
            public String action;
            public String mac;
            public String message;
            public String type;

            private NotifyInfoClass() {
            }
        }

        public void run() {
            int i = 0;
            NotifyInfoClass notifyInfoClass = (NotifyInfoClass) this.notifyResultQueue.poll();
            if (notifyInfoClass != null) {
                Log.m1213p("Runtime_iHealthDM", Level.INFO, "onDeviceNotify", notifyInfoClass.mac, notifyInfoClass.type, notifyInfoClass.action, notifyInfoClass.message);
                if (iHealthDevicesManager.this.commandCacheControlMap.get(notifyInfoClass.mac) != null) {
                    Log.m1213p("Runtime_iHealthDM", Level.INFO, "Command notify call back", "Action = " + notifyInfoClass.action, "Queen Size = " + ((a) iHealthDevicesManager.this.commandCacheControlMap.get(notifyInfoClass.mac)).b().size());
                    r1.a(notifyInfoClass.mac, notifyInfoClass.action, notifyInfoClass.message);
                }
                if (iHealthDevicesManager.this.as.get(notifyInfoClass.mac) != null) {
                    int i2 = 0;
                    for (iHealthDevicesCallback onDeviceNotify : iHealthDevicesManager.this.av.getCallbacklist(notifyInfoClass.mac, notifyInfoClass.type)) {
                        onDeviceNotify.onDeviceNotify(notifyInfoClass.mac, notifyInfoClass.type, notifyInfoClass.action, notifyInfoClass.message);
                        i2 = 1;
                    }
                    i = i2;
                }
            }
            if (i == 0) {
                Log.m1215w("Runtime_iHealthDM", "NotifyThread -- Invalid callback");
            }
        }

        public void setNotifyMessage(String mac, String type, String action, String message) {
            NotifyInfoClass notifyInfoClass = new NotifyInfoClass();
            notifyInfoClass.mac = mac;
            notifyInfoClass.type = type;
            notifyInfoClass.action = action;
            notifyInfoClass.message = message;
            this.notifyResultQueue.offer(notifyInfoClass);
        }
    }

    class ScanDevice {
        public static final int LINK_AU = 205;
        public static final int LINK_BLE = 202;
        public static final int LINK_BT = 201;
        public static final int LINK_USB = 204;
        public static final int LINK_WIFI = 203;
        private BluetoothDevice mDevice = null;
        private String mDeviceMac = null;
        private String mDeviceType = null;
        private Hs5Control mHs5Control;
        private UsbDevice mUsbDevice = null;
        private int mlinkType = 0;

        public ScanDevice(int mlinkType, BluetoothDevice bluetoothDevice, Hs5Control hs5Control, UsbDevice usbDevice, String mac, String type) {
            this.mlinkType = mlinkType;
            this.mDevice = bluetoothDevice;
            this.mDeviceMac = mac;
            this.mDeviceType = type;
            this.mHs5Control = hs5Control;
            this.mUsbDevice = usbDevice;
        }

        public BluetoothDevice getDevice() {
            return this.mDevice;
        }

        public String getDeviceMac() {
            return this.mDeviceMac;
        }

        public String getDeviceType() {
            return this.mDeviceType;
        }

        public Hs5Control getHs5control() {
            return this.mHs5Control;
        }

        public UsbDevice getUsbDevice() {
            return this.mUsbDevice;
        }

        public int getlinkType() {
            return this.mlinkType;
        }
    }

    class ScanFinishThread implements Runnable {
        private ScanFinishThread() {
        }

        public void run() {
            int i = 0;
            Log.m1213p("Runtime_iHealthDM", Level.INFO, "onScanFinish", new Object[0]);
            for (iHealthDevicesCallback onScanFinish : iHealthDevicesManager.this.av.getCallbacklist_All()) {
                onScanFinish.onScanFinish();
                i = 1;
            }
            if (i == 0) {
                Log.m1215w("Runtime_iHealthDM", "ScanFinishThread -- Invalid callback");
            }
        }
    }

    class ScanThread implements Runnable {
        private Queue scanResultQueue = new LinkedList();

        class ScanInfoClass {
            public String mac;
            public Map manufactureData;
            public int rssi;
            public String type;

            private ScanInfoClass() {
                this.mac = "";
                this.type = "";
                this.rssi = 0;
                this.manufactureData = new HashMap();
            }
        }

        public void run() {
            int i = 0;
            ScanInfoClass scanInfoClass = (ScanInfoClass) this.scanResultQueue.poll();
            if (scanInfoClass != null) {
                Log.m1213p("Runtime_iHealthDM", Level.INFO, "onScanDevice", scanInfoClass.mac, scanInfoClass.type, Integer.valueOf(scanInfoClass.rssi), scanInfoClass.manufactureData);
                for (iHealthDevicesCallback com_ihealth_communication_manager_iHealthDevicesCallback : iHealthDevicesManager.this.av.getCallbacklist(scanInfoClass.mac, scanInfoClass.type)) {
                    com_ihealth_communication_manager_iHealthDevicesCallback.onScanDevice(scanInfoClass.mac, scanInfoClass.type, scanInfoClass.rssi);
                    com_ihealth_communication_manager_iHealthDevicesCallback.onScanDevice(scanInfoClass.mac, scanInfoClass.type, scanInfoClass.rssi, scanInfoClass.manufactureData);
                    i = 1;
                }
                if (i == 0) {
                    Log.m1215w("Runtime_iHealthDM", "ScanThread -- Invalid callback");
                }
            }
        }

        public void setScanMessage(String mac, String type, int rssi, Map manufactureData) {
            ScanInfoClass scanInfoClass = new ScanInfoClass();
            scanInfoClass.mac = mac;
            scanInfoClass.type = type;
            scanInfoClass.rssi = rssi;
            scanInfoClass.manufactureData = manufactureData;
            this.scanResultQueue.offer(scanInfoClass);
        }
    }

    class SingletonHolder {
        static final iHealthDevicesManager INSTANCE = new iHealthDevicesManager();

        private SingletonHolder() {
        }
    }

    private iHealthDevicesManager() {
        this.f1970c = null;
        this.f1971d = 0;
        this.f1972e = 0;
        this.f1975h = new ConcurrentHashMap();
        this.f1976i = new ConcurrentHashMap();
        this.f1977j = new ConcurrentHashMap();
        this.f1978k = new ConcurrentHashMap();
        this.f1979l = new ConcurrentHashMap();
        this.f1980m = new ConcurrentHashMap();
        this.f1981n = new ConcurrentHashMap();
        this.f1982o = new ConcurrentHashMap();
        this.f1983p = new ConcurrentHashMap();
        this.f1984q = new ConcurrentHashMap();
        this.f1985r = new ConcurrentHashMap();
        this.f1986s = new ConcurrentHashMap();
        this.f1987t = new ConcurrentHashMap();
        this.f1988u = new ConcurrentHashMap();
        this.f1989v = new ConcurrentHashMap();
        this.f1990w = new ConcurrentHashMap();
        this.f1991x = new ConcurrentHashMap();
        this.f1992y = new ConcurrentHashMap();
        this.f1993z = new ConcurrentHashMap();
        this.f1942A = new ConcurrentHashMap();
        this.f1943B = new ConcurrentHashMap();
        this.f1944C = new ConcurrentHashMap();
        this.f1945D = new ConcurrentHashMap();
        this.f1946E = new ConcurrentHashMap();
        this.f1947F = new ConcurrentHashMap();
        this.f1948G = new ConcurrentHashMap();
        this.f1949H = new ConcurrentHashMap();
        this.f1950I = new ConcurrentHashMap();
        this.f1951J = new ConcurrentHashMap();
        this.f1952K = new ConcurrentHashMap();
        this.f1953L = new ConcurrentHashMap();
        this.f1954M = new ConcurrentHashMap();
        this.f1955N = new ConcurrentHashMap();
        this.commandCacheControlMap = new ConcurrentHashMap();
        this.f1956O = new C21431();
        this.as = new ConcurrentHashMap();
        this.av = new iHealthDeviceClientMap();
        this.aw = new C2155j();
        this.ax = false;
        this.ay = new Date();
        this.az = new Object();
        this.f1968a = false;
        this.aA = new C21496();
        this.aC = new ConcurrentHashMap();
        this.aH = new HashMap();
        this.aJ = -1;
        this.mBtleCallback = new C21528();
        this.aP = true;
        this.aQ = new C21539();
        this.aW = new byte[]{(byte) -80, (byte) 4, (byte) 0, (byte) 0, (byte) -1, (byte) -48, (byte) -49};
        this.aX = new Handler(Looper.getMainLooper());
        this.bb = 0;
        this.bd = PL2303Driver.BAUD57600;
        this.be = (byte) 1;
        this.bf = (byte) 8;
        this.bg = (byte) 0;
        this.bh = (byte) 0;
    }

    private String m1082a(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(CommonUtils.MD5_INSTANCE).digest((str.substring(0, str.length() - 1) + "@#$").getBytes("UTF-8"));
            StringBuilder stringBuilder = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                if ((b & 255) < 16) {
                    stringBuilder.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                stringBuilder.append(Integer.toHexString(b & 255));
            }
            stringBuilder.append(Integer.toHexString(digest[1] & 255));
            return stringBuilder.toString();
        } catch (Throwable e) {
            throw new RuntimeException("Huh, getPackageName should be supported?", e);
        } catch (Throwable e2) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e2);
        }
    }

    private void m1085a() {
        for (String str : this.f1955N.keySet()) {
            String str2 = (String) this.f1955N.get(str);
            if (str2.equals(TYPE_BP5)) {
                Bp5Control bp5Control = (Bp5Control) this.f1977j.get(str);
                if (bp5Control != null) {
                    bp5Control.disconnect();
                }
            } else if (str2.equals(TYPE_BP5S)) {
                Bp5sControl bp5sControl = (Bp5sControl) this.f1978k.get(str);
                if (bp5sControl != null) {
                    bp5sControl.disconnect();
                }
            } else if (str2.equals(TYPE_BP7)) {
                Bp7Control bp7Control = (Bp7Control) this.f1979l.get(str);
                if (bp7Control != null) {
                    bp7Control.disconnect();
                }
            } else if (str2.equals(TYPE_BP7S)) {
                Bp7sControl bp7sControl = (Bp7sControl) this.f1980m.get(str);
                if (bp7sControl != null) {
                    bp7sControl.disconnect();
                }
            } else if (str2.equals(TYPE_BG5)) {
                Bg5Control bg5Control = (Bg5Control) this.f1946E.get(str);
                if (bg5Control != null) {
                    bg5Control.disconnect();
                }
            } else if (str2.equals(TYPE_HS3)) {
                Hs3Control hs3Control = (Hs3Control) this.f1985r.get(str);
                if (hs3Control != null) {
                    hs3Control.disconnect();
                }
            } else if (str2.equals(TYPE_HS4)) {
                Hs4Control hs4Control = (Hs4Control) this.f1986s.get(str);
                if (hs4Control != null) {
                    hs4Control.disconnect();
                }
            } else if (str2.equals(TYPE_HS4S)) {
                Hs4sControl hs4sControl = (Hs4sControl) this.f1987t.get(str);
                if (hs4sControl != null) {
                    hs4sControl.disconnect();
                }
            } else {
                this.aD.disconnect(str);
            }
        }
    }

    private void m1086a(BluetoothDevice bluetoothDevice, String str) {
        String replace = bluetoothDevice.getAddress().replace(":", "");
        if (this.f1947F.containsKey(replace)) {
            Log.m1214v("Runtime_iHealthDM", "already connecting this device mac =" + replace);
            return;
        }
        this.f1947F.put(replace, replace);
        if (str.contains(TYPE_BG5)) {
            String ee = getEE(replace);
            if (ee.equals("000") || ee.length() < 5) {
                new ConnectThread(bluetoothDevice, str, true).start();
                return;
            }
            m1120d(replace, ee);
            new ConnectThread(bluetoothDevice, str, false).start();
            return;
        }
        new ConnectThread(bluetoothDevice, str, false).start();
    }

    private void m1087a(Context context, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        this.f1942A.clear();
        this.f1943B.clear();
        this.f1944C.clear();
        this.f1945D.clear();
        if (this.aN == null || !this.aN.isAlive()) {
            this.aN = new HandlerThread("MyHandlerThread");
            this.aN.start();
        } else {
            Log.m1214v("Runtime_iHealthDM", "handlerthread has existed");
        }
        if (this.aM != null) {
            Log.m1214v("Runtime_iHealthDM", "mHandler has existed");
        } else {
            this.aM = new Handler(this.aN.getLooper());
        }
        this.aL = (WifiManager) this.f1973f.getSystemService("wifi");
        m1132h();
    }

    private void m1088a(UsbDevice usbDevice) {
        this.bj = PendingIntent.getBroadcast(this.f1973f, 0, new Intent("com.android.example.USB_PERMISSION"), 0);
        if (usbDevice == null) {
            return;
        }
        if (this.bc.hasPermission(usbDevice)) {
            m1142m();
        } else {
            this.bc.requestPermission(usbDevice, this.bj);
        }
    }

    private void m1089a(BaseComm baseComm) {
        this.f1959R = new Bp3mControl(this.f1973f, baseComm, this.f1958Q, "000000000000", TYPE_BP3M, m1104b(), this.f1956O);
        this.f1959R.init();
    }

    private void m1090a(BaseComm baseComm, String str, String str2, boolean z) {
        if (str2.contains(TYPE_BP5)) {
            this.f1961T = new Bp5Control(this.f1973f, baseComm, this.f1958Q, str, TYPE_BP5, m1104b(), this.f1956O);
            this.f1961T.init();
        } else if (str2.contains(TYPE_BP7S)) {
            this.f1964W = new Bp7sControl(this.f1973f, baseComm, this.f1958Q, str, TYPE_BP7S, m1104b(), this.f1956O);
            this.f1964W.init();
        } else if (str2.contains(TYPE_BP7)) {
            this.f1963V = new Bp7Control(this.f1973f, baseComm, this.f1958Q, str, TYPE_BP7, m1104b(), this.f1956O);
            this.f1963V.init();
        } else if (str2.contains("iHealth HS3")) {
            this.ab = new Hs3Control(this.f1958Q, baseComm, this.f1973f, str, TYPE_HS3, this.f1956O, m1104b());
            this.ab.init();
        } else if (str2.contains(TYPE_HS4S)) {
            this.ad = new Hs4sControl(this.f1958Q, this.f1973f, baseComm, str, TYPE_HS4S, this.f1956O, m1104b());
            this.ad.init();
        } else if (str2.contains("iHealth HS5")) {
            this.ae = new Hs5ControlForBt(baseComm, str, TYPE_HS5_BT, this.f1956O, m1104b());
            this.ae.init();
        } else if (str2.contains(TYPE_BG5)) {
            this.ak = new Bg5Control(this.f1958Q, baseComm, this.f1973f, str, TYPE_BG5, z, this.f1956O, m1104b());
            this.ak.init();
        }
    }

    private void m1096a(String str, Hs5Control hs5Control) {
        hs5Control.init();
    }

    private void m1097a(String str, String str2) {
        boolean z = true;
        this.as.put(str, str2);
        this.f1947F.remove(str);
        if (str2.equals(TYPE_BP3M)) {
            this.f1975h.put(str, this.f1959R);
            z = false;
        } else if (str2.equals(TYPE_BP3L)) {
            this.f1976i.put(str, this.f1960S);
            z = true;
        } else if (str2.equals(TYPE_BP5)) {
            this.f1977j.put(str, this.f1961T);
        } else if (str2.equals(TYPE_BP5S)) {
            this.f1978k.put(str, this.f1962U);
            z = true;
        } else if (str2.equals(TYPE_BP7)) {
            this.f1979l.put(str, this.f1963V);
        } else if (str2.equals(TYPE_BP7S)) {
            this.f1980m.put(str, this.f1964W);
        } else if (str2.equals(TYPE_550BT)) {
            this.f1981n.put(str, this.f1965X);
            z = true;
        } else if (str2.equals(TYPE_KD926)) {
            this.f1982o.put(str, this.f1966Y);
            z = true;
        } else if (str2.equals(TYPE_KD723)) {
            this.f1983p.put(str, this.f1967Z);
            z = true;
        } else if (str2.equals(TYPE_ABPM)) {
            this.f1984q.put(str, this.aa);
            z = true;
        } else if (str2.equals(TYPE_HS3)) {
            this.f1985r.put(str, this.ab);
        } else if (str2.equals(TYPE_HS4)) {
            this.f1986s.put(str, this.ac);
            z = true;
        } else if (str2.equals(TYPE_HS4S)) {
            this.f1987t.put(str, this.ad);
        } else if (str2.equals(TYPE_HS5)) {
            this.f1945D.put(str, Integer.valueOf(6));
            this.f1942A.put(str, this.aj);
            this.f1943B.put(str, this.aj);
            this.f1944C.remove(str);
            z = true;
        } else if (str2.equals(TYPE_HS6)) {
            z = false;
        } else if (str2.equals(TYPE_AM3)) {
            this.f1989v.put(str, this.af);
            z = true;
        } else if (str2.equals(TYPE_AM3S)) {
            this.f1990w.put(str, this.ag);
            z = true;
        } else if (str2.equals(TYPE_AM4)) {
            this.f1991x.put(str, this.ah);
            z = true;
        } else if (str2.equals(TYPE_PO3)) {
            this.f1992y.put(str, this.ai);
            z = true;
        } else if (str2.equals(TYPE_HS5_BT)) {
            this.f1988u.put(str, this.ae);
        } else if (str2.equals(TYPE_BG5)) {
            this.f1946E.put(str, this.ak);
        } else if (str2.equals(TYPE_BTM)) {
            this.f1948G.put(str, this.al);
            z = true;
        } else if (str2.equals(TYPE_CBP)) {
            this.f1949H.put(str, this.am);
            z = true;
        } else if (str2.equals(TYPE_BG5l)) {
            this.f1993z.put(str, this.an);
            z = true;
        } else if (str2.equals(TYPE_CBG)) {
            this.f1950I.put(str, this.ao);
            z = true;
        } else if (str2.equals(TYPE_CPO)) {
            this.f1951J.put(str, this.ap);
            z = true;
        } else if (str2.equals(TYPE_CHS)) {
            this.f1952K.put(str, this.aq);
            z = true;
        } else if (str2.equals(TYPE_CBS)) {
            this.f1953L.put(str, this.ar);
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return;
        }
        if (VERSION.RELEASE.startsWith("4.3") || VERSION.RELEASE.startsWith("4.4")) {
            Log.m1214v("Runtime_iHealthDM", "Special phone");
            SharedPreferences sharedPreferences = this.f1973f.getSharedPreferences("SpecialPhone" + VERSION.RELEASE, 0);
            if (!sharedPreferences.getBoolean("FirstSpecialFlag", false)) {
                Editor edit = sharedPreferences.edit();
                edit.putBoolean("FirstSpecialFlag", true);
                edit.commit();
            }
        }
    }

    private void m1098a(String str, String str2, BluetoothDevice bluetoothDevice, Hs5Control hs5Control, UsbDevice usbDevice, int i, int i2, Map map) {
        String replace = str.replace(":", "");
        this.f1955N.put(replace, str2);
        synchronized (this.az) {
            if (this.f1954M.get(replace) == null) {
                this.f1954M.put(replace, new ScanDevice(i, bluetoothDevice, hs5Control, usbDevice, replace, str2));
                this.aZ.setScanMessage(replace, str2, i2, map);
                this.aX.post(this.aZ);
            }
        }
    }

    private void m1099a(String str, byte[] bArr) {
        byte[] bArr2 = new byte[16];
        byte[] bArr3 = new byte[10];
        byte[] bArr4 = new byte[3];
        byte[] bArr5 = new byte[3];
        byte[] bArr6 = new byte[7];
        byte[] bArr7 = new byte[9];
        byte[] bArr8 = new byte[16];
        byte[] bArr9 = new byte[32];
        byte[] bArr10 = new byte[4];
        byte[] bArr11 = new byte[6];
        String str2 = "";
        String str3 = "";
        str3 = "";
        str3 = "";
        str3 = "";
        str3 = "";
        str3 = "";
        str3 = "";
        str3 = "";
        int i = 0;
        while (i < bArr2.length) {
            try {
                bArr2[i] = bArr[i + 0];
                i++;
            } catch (Exception e) {
                Log.m1215w("Runtime_iHealthDM", "AnalysisIDPS() -- e: " + e);
                return;
            }
        }
        String str4 = new String(bArr2, "UTF-8");
        for (i = 0; i < bArr3.length; i++) {
            bArr3[i] = bArr[i + 16];
        }
        String str5 = new String(bArr3, "UTF-8");
        for (i = 0; i < bArr4.length; i++) {
            bArr4[i] = bArr[i + 32];
        }
        String format = String.format("%c.%c.%c", new Object[]{Byte.valueOf(bArr4[0]), Byte.valueOf(bArr4[1]), Byte.valueOf(bArr4[2])});
        for (i = 0; i < bArr5.length; i++) {
            bArr5[i] = bArr[i + 35];
        }
        String format2 = String.format("%c.%c.%c", new Object[]{Byte.valueOf(bArr5[0]), Byte.valueOf(bArr5[1]), Byte.valueOf(bArr5[2])});
        for (i = 0; i < bArr6.length; i++) {
            bArr6[i] = bArr[i + 38];
        }
        String str6 = new String(bArr6, "UTF-8");
        for (i = 0; i < bArr7.length; i++) {
            bArr7[i] = bArr[i + 54];
        }
        str6 = new String(bArr7, "UTF-8");
        for (i = 0; i < bArr8.length; i++) {
            bArr8[i] = bArr[i + 70];
        }
        String Bytes2HexString = ByteBufferUtil.Bytes2HexString(bArr8);
        for (i = 0; i < bArr9.length; i++) {
            bArr9[i] = bArr[i + 86];
        }
        String Bytes2HexString2 = ByteBufferUtil.Bytes2HexString(bArr9);
        for (i = 0; i < bArr10.length; i++) {
            bArr10[i] = bArr[i + 118];
        }
        str3 = ByteBufferUtil.Bytes2HexString(bArr10);
        String str7 = Integer.valueOf(str3.substring(0, 2), 16) + "." + Integer.valueOf(str3.substring(2, 4), 16) + "." + Integer.valueOf(str3.substring(4, 6), 16) + "." + Integer.valueOf(str3.substring(6), 16);
        str3 = (((((((((str2 + "protocolVerStr：" + str4 + "\n") + "accessoryNameStr：" + str5 + "\n") + "firmwareStr：" + format + "\n") + "hardwareStr：" + format2 + "\n") + "manufacturerStr：" + str6 + "\n") + "modelNumberStr：" + str6 + "\n") + "serialNumberStr：" + Bytes2HexString + "\n") + "deviceNameStr：" + Bytes2HexString2 + "\n") + "Device IP：" + str7 + "\n") + "Device Mac：" + str + "\n";
        if (this.f1943B.containsKey(str)) {
            Log.m1214v("Runtime_iHealthDM", "HS5 has been in the Map");
            for (Entry entry : this.f1945D.entrySet()) {
                Log.m1214v("Runtime_iHealthDM", "the device is in map--mac=" + ((String) entry.getKey()) + "still has " + entry.getValue() + "  times changes");
                if (((String) entry.getKey()).equals(str)) {
                    entry.setValue(Integer.valueOf(6));
                }
            }
        } else if (!this.ax) {
            Log.m1210d("Runtime_iHealthDM", "AnalysisIDPS() not in discovery process, so skip adding HS5 into connecting map.");
        } else if (this.f1944C.containsKey(str)) {
            Log.m1214v("Runtime_iHealthDM", "HS5 connectting……");
        } else {
            Log.m1214v("Runtime_iHealthDM", "new HS5：" + str3);
            if (this.aK != null) {
                this.f1956O.onConnectionStateChange(str, TYPE_HS5, 0, 0, null);
                this.aj = new Hs5Control(this.f1958Q, this.f1973f, str, str7, this.aK, this.f1956O, m1104b(), TYPE_HS5);
                IDPS idps = new IDPS();
                idps.setProtoclString(str4);
                idps.setAccessoryName(str5);
                idps.setAccessoryFirmwareVersion(format);
                idps.setAccessoryHardwareVersion(format2);
                idps.setAccessoryManufaturer(str6);
                idps.setAccessoryModelNumber(str6);
                idps.setAccessorySerialNumber(Bytes2HexString);
                idps.setDeviceName(Bytes2HexString2);
                idps.setDeviceIP(str7);
                idps.setDeviceMac(str);
                idps.setDeviceType(TYPE_HS5);
                this.aj.setWifiIDPSData(idps);
                m1098a(str, TYPE_HS5, null, this.aj, null, ScanDevice.LINK_WIFI, 0, new HashMap());
            }
        }
    }

    private boolean m1100a(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Param.LOCATION);
        boolean isProviderEnabled = locationManager.isProviderEnabled("gps");
        boolean isProviderEnabled2 = locationManager.isProviderEnabled("network");
        int i = context.getApplicationInfo().targetSdkVersion;
        if (isProviderEnabled || isProviderEnabled2) {
            return true;
        }
        if (i < 23 || VERSION.SDK_INT < 23) {
            Log.m1214v("Runtime_iHealthDM", "Location is disabled, Target level:" + context.getApplicationInfo().targetSdkVersion);
            return true;
        }
        Log.m1211e("Runtime_iHealthDM", "Location is disabled, Target level:" + context.getApplicationInfo().targetSdkVersion);
        return false;
    }

    private boolean m1101a(IDPS idps) {
        String deviceType = idps.getDeviceType();
        if (deviceType == null) {
            return true;
        }
        if (deviceType.equals(TYPE_BTM) || deviceType.equals(TYPE_CBP) || deviceType.equals(TYPE_CBG) || deviceType.equals(TYPE_CPO) || deviceType.equals(TYPE_CHS) || deviceType.equals(TYPE_CBS)) {
            return false;
        }
        deviceType = idps.getDeviceName();
        return (deviceType == null || !deviceType.contains("FDTH")) ? !idps.getProtoclString().startsWith("com.") || idps.getAccessoryFirmwareVersion() == null || idps.getAccessoryHardwareVersion() == null : false;
    }

    private boolean m1102a(String str, String str2, String str3, ScanDevice scanDevice) {
        String deviceType = scanDevice.getDeviceType();
        BluetoothDevice device = scanDevice.getDevice();
        int i = scanDevice.getlinkType();
        Hs5Control hs5control = scanDevice.getHs5control();
        switch (i) {
            case 201:
                m1086a(device, deviceType);
                break;
            case 202:
                m1106b(device, deviceType);
                break;
            case ScanDevice.LINK_WIFI /*203*/:
                m1096a(str2, hs5control);
                break;
            case ScanDevice.LINK_USB /*204*/:
                m1088a(scanDevice.getUsbDevice());
                break;
        }
        return true;
    }

    private boolean m1103a(String[] strArr) {
        Pattern compile = Pattern.compile("[a-zA-Z0-9]{12}");
        for (CharSequence matcher : strArr) {
            if (!compile.matcher(matcher).find()) {
                return false;
            }
        }
        return true;
    }

    private InsCallback m1104b() {
        return new C21453();
    }

    private synchronized void m1106b(BluetoothDevice bluetoothDevice, final String str) {
        final String replace = bluetoothDevice.getAddress().replace(":", "");
        this.f1956O.onConnectionStateChange(replace, str, 0, 0, null);
        boolean connectDevice = this.aD != null ? this.aD.connectDevice(bluetoothDevice.getAddress()) : false;
        Log.m1214v("Runtime_iHealthDM", "connection result: " + connectDevice);
        if (!connectDevice) {
            this.aX.postDelayed(new Runnable() {
                public void run() {
                    iHealthDevicesManager.this.f1956O.onConnectionStateChange(replace, str, 3, 0, null);
                }
            }, 1000);
        }
    }

    private synchronized void m1108b(String str) {
        boolean z = false;
        synchronized (this) {
            String str2 = str.substring(0, 2) + ":" + str.substring(2, 4) + ":" + str.substring(4, 6) + ":" + str.substring(6, 8) + ":" + str.substring(8, 10) + ":" + str.substring(10, 12);
            if (this.aD != null) {
                z = this.aD.connectDevice(str2);
            }
            Log.m1214v("Runtime_iHealthDM", "connection result: " + z);
            if (!z) {
                this.f1956O.onConnectionStateChange(str, "", 3, 0, null);
            }
        }
    }

    private void m1109b(String str, String str2) {
        Log.m1213p("Runtime_iHealthDM", Level.VERBOSE, "removeDevice", str, str2);
        if (this.as != null) {
            this.as.remove(str);
        }
        if (this.f1947F != null) {
            this.f1947F.remove(str);
        }
        if (str2 == null) {
            Log.m1214v("Runtime_iHealthDM", "removeDevice type==null");
        } else if (str2.equals(TYPE_BP3M) && this.f1975h != null) {
            this.f1975h.remove(str);
        } else if (TYPE_BP3L.equals(str2) && this.f1976i != null) {
            this.f1976i.remove(str);
        } else if (TYPE_BP5.equals(str2)) {
            if (this.f1977j != null) {
                this.f1977j.remove(str);
            }
            AbiControlSubManager.getInstance().remove(str);
        } else if (TYPE_BP5S.equals(str2)) {
            this.f1978k.remove(str);
        } else if (TYPE_BP7.equals(str2) && this.f1979l != null) {
            this.f1979l.remove(str);
        } else if (TYPE_BP7S.equals(str2) && this.f1980m != null) {
            this.f1980m.remove(str);
        } else if (TYPE_550BT.equals(str2) && this.f1981n != null) {
            this.f1981n.remove(str);
        } else if (TYPE_KD926.equals(str2) && this.f1982o != null) {
            this.f1982o.remove(str);
        } else if (TYPE_KD723.equals(str2) && this.f1983p != null) {
            this.f1983p.remove(str);
        } else if (TYPE_ABPM.equals(str2) && this.f1984q != null) {
            this.f1984q.remove(str);
        } else if (TYPE_HS3.equals(str2) && this.f1985r != null) {
            this.f1985r.remove(str);
        } else if (TYPE_HS4.equals(str2) && this.f1986s != null) {
            this.f1986s.remove(str);
        } else if (TYPE_HS4S.equals(str2) && this.f1987t != null) {
            this.f1987t.remove(str);
        } else if (TYPE_HS5.equals(str2) && this.f1942A != null) {
            this.f1942A.remove(str);
            this.f1943B.remove(str);
            this.f1944C.remove(str);
            this.f1945D.remove(str);
        } else if (!TYPE_HS6.equals(str2)) {
            if (TYPE_AM3.equals(str2) && this.f1989v != null) {
                this.f1989v.remove(str);
            } else if (TYPE_AM3S.equals(str2) && this.f1990w != null) {
                this.f1990w.remove(str);
            } else if (TYPE_AM4.equals(str2) && this.f1991x != null) {
                this.f1991x.remove(str);
            } else if (TYPE_PO3.equals(str2) && this.f1992y != null) {
                this.f1992y.remove(str);
            } else if (TYPE_HS5_BT.equals(str2) && this.f1988u != null) {
                this.f1988u.remove(str);
            } else if (TYPE_BG5.equals(str2) && this.f1946E != null) {
                this.f1946E.remove(str);
            } else if (TYPE_BTM.equals(str2) && this.f1948G != null) {
                this.f1948G.remove(str);
            } else if (TYPE_CBP.equals(str2) && this.f1949H != null) {
                this.f1949H.remove(str);
            } else if (TYPE_BG5l.equals(str2) && this.f1993z != null) {
                this.f1993z.remove(str);
            } else if (TYPE_CBG.equals(str2) && this.f1950I != null) {
                this.f1950I.remove(str);
            } else if (TYPE_CPO.equals(str2) && this.f1951J != null) {
                this.f1951J.remove(str);
            } else if (TYPE_CHS.equals(str2) && this.f1952K != null) {
                this.f1952K.remove(str);
            } else if (TYPE_CBS.equals(str2) && this.f1953L != null) {
                this.f1953L.remove(str);
            }
        }
        System.gc();
    }

    private boolean m1110b(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le") && VERSION.SDK_INT >= 18;
    }

    private boolean m1111b(String[] strArr) {
        String str = "BP3MBP3LBP5BP7BP7SBG5HS3HS4HS4SHS5HS6AM3AM3SAM4PO3HS5btBG1BG5BG5LABPMCBP926723550";
        for (CharSequence contains : strArr) {
            if (!str.contains(contains)) {
                return false;
            }
        }
        return true;
    }

    private String m1112c(String str) {
        return str == null ? null : (str.contains("Activity Monitor") || (str.contains(TYPE_AM3) && !str.contains(TYPE_AM3S))) ? TYPE_AM3 : str.contains(TYPE_AM3S) ? TYPE_AM3S : str.contains(TYPE_AM4) ? TYPE_AM4 : (str.contains("Pulse Oximeter") || str.contains(TYPE_PO3)) ? TYPE_PO3 : (str.contains("Body Scale") || (str.contains(TYPE_HS4) && !str.contains(TYPE_HS4S))) ? TYPE_HS4 : str.contains(TYPE_BP3L) ? TYPE_BP3L : str.contains("550") ? TYPE_550BT : str.contains("926") ? TYPE_KD926 : str.contains("723") ? TYPE_KD723 : str.contains(TYPE_ABPM) ? TYPE_ABPM : str.contains("FDTH") ? TYPE_BTM : null;
    }

    private void m1114c() {
        stopDiscovery();
    }

    private void m1116c(String str, String str2) {
        this.at = this.f1973f.getSharedPreferences("bg5_ee_new", 0);
        this.au = this.at.edit();
        this.au.putString(str, str2);
        this.au.commit();
        m1120d(str, str2);
    }

    private void m1118d() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction(IHEALTH_MSG_BG5_EE);
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction(iHealthDevicesIDPS.MSG_IHEALTH_DEVICE_IDPS);
        intentFilter.addAction("device");
        intentFilter.addAction("permission");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter.addAction("com.android.example.USB_PERMISSION");
        intentFilter.addAction(MSG_BASECOMMTIMEOUT);
        this.f1973f.registerReceiver(this.aA, intentFilter);
    }

    private void m1120d(String str, String str2) {
        IDPS idps = (IDPS) this.aC.get(str);
        if (idps == null) {
            idps = new IDPS();
        }
        idps.setAccessorySerialNumber(str);
        idps.setAccessoryFirmwareVersion(str2);
        idps.setAccessoryName(TYPE_BG5);
        idps.setAccessoryModelNumber("BG5 11070");
        idps.setProtoclString("com.jiuan.BGV31");
        idps.setAccessoryManufaturer("iHealth");
        this.aC.put(str, idps);
    }

    private void m1123e() {
        if (true == m1110b(this.f1973f)) {
            this.aD = new AndroidBle(this.f1973f, this.mBtleCallback);
            this.aE = this.aD.getBaseComm();
        }
    }

    private synchronized void m1124e(String str, String str2) {
        if (this.f1974g != null) {
            m1086a(this.f1974g.getRemoteDevice(str.substring(0, 2) + ":" + str.substring(2, 4) + ":" + str.substring(4, 6) + ":" + str.substring(6, 8) + ":" + str.substring(8, 10) + ":" + str.substring(10, 12)), str2);
        }
    }

    private String m1126f(String str, String str2) {
        return str == null ? "" : (str.equals(BleConfig.UUID_AM3_SERVICE) || str.equals(BleConfig.UUID_AM3_Qualcomm_SERVICE)) ? TYPE_AM3 : str.equals(BleConfig.UUID_AM3S_SERVICE) ? TYPE_AM3S : str.equals(BleConfig.UUID_AM4_SERVICE) ? TYPE_AM4 : (str.equals(BleConfig.UUID_PO3_SERVICE) || str.equals(BleConfig.UUID_PO3_SERVICE_128)) ? TYPE_PO3 : str.equals(BleConfig.UUID_HS4_SERVICE) ? TYPE_HS4 : str.equals(BleConfig.UUID_BP3L_SERVICE) ? TYPE_BP3L : str.equals(BleConfig.UUID_AV20_SERVICE) ? TYPE_BP5S : str.equals("636f6d2e-6a69-7561-6e2e-424c45303100") ? TYPE_BTM : (str.equals(BleConfig.UUID_AV10_SERVICE) && (str2 == null || str2.contains("550"))) ? TYPE_550BT : (str.equals(BleConfig.UUID_AV10_SERVICE) && (str2 == null || str2.contains("926"))) ? TYPE_KD926 : (str.equals(BleConfig.UUID_AV10_SERVICE) && (str2 == null || str2.contains("723"))) ? TYPE_KD723 : str.equals(BleConfig.UUID_ABPM_SERVICE) ? TYPE_ABPM : str.equals(BleConfig.UUID_BP_SERVICE) ? TYPE_CBP : str.equals(BleConfig.UUID_BG5l_SERVICE) ? TYPE_BG5l : str.equals(BleConfig.UUID_BG_SERVICE) ? TYPE_CBG : str.equals(BleConfig.UUID_PO_SERVICE) ? TYPE_CPO : str.equals(BleConfig.UUID_HS_SERVICE) ? TYPE_CHS : str.equals(BleConfig.UUID_BS_SERVICE) ? TYPE_CBS : "";
    }

    private void m1127f() {
        this.aJ = -1;
    }

    private void m1129g() {
        this.aJ++;
        if (this.aJ < this.aI.size()) {
            boolean Obtain = this.aD.Obtain(this.aB.getDeviceMac(), UUID.fromString(this.aG.BLE_IDPS_INFO), UUID.fromString((String) this.aI.get(this.aJ)));
            if (!Obtain) {
                Log.m1214v("Runtime_iHealthDM", "Obtain:" + Obtain);
            }
        } else if (m1101a(this.aB)) {
            this.aD.refresh(this.aF.replace(":", ""));
        } else {
            String f = m1126f(this.aG.BLE_SERVICE, this.aB.getDeviceName());
            if (f.equals(TYPE_CBP) || f.equals(TYPE_CBG) || f.equals(TYPE_CPO) || f.equals(TYPE_CHS) || f.equals(TYPE_CBS)) {
                m1130g(this.aB.getDeviceMac(), this.aB.getDeviceType());
            } else {
                this.aD.getService(this.aB.getDeviceMac(), this.aG.BLE_SERVICE, this.aG.BLE_TRANSMIT, this.aG.BLE_RECEIVE, this.aG.BLE_IDPS_INFO, false);
            }
        }
    }

    private void m1130g(String str, String str2) {
        String c = str2 == null ? m1112c(this.aB.getAccessoryModelNumber()) : str2;
        Log.m1213p("Runtime_iHealthDM", Level.VERBOSE, "identify", str, c);
        this.aB.setDeviceType(c);
        this.aC.put(str, this.aB);
        DeviceControl deviceControl = null;
        if (c.equals(TYPE_AM3)) {
            this.af = new Am3Control(this.aE, this.f1973f, str, c, this.f1958Q, this.f1956O, m1104b());
            deviceControl = this.af;
        } else if (c.equals(TYPE_AM3S)) {
            this.ag = new Am3sControl(this.aE, this.f1973f, str, c, this.f1958Q, this.f1956O, m1104b());
            deviceControl = this.ag;
        } else if (c.equals(TYPE_AM4)) {
            this.ah = new Am4Control(this.aE, this.f1973f, str, c, this.f1958Q, this.f1956O, m1104b());
            deviceControl = this.ah;
        } else if (c.equals(TYPE_PO3)) {
            this.ai = new Po3Control(this.f1958Q, this.f1973f, this.aB.getAccessoryFirmwareVersion(), this.aE, str, c, this.f1956O, m1104b());
            deviceControl = this.ai;
        } else if (c.equals(TYPE_HS4)) {
            this.ac = new Hs4Control(this.f1958Q, this.f1973f, this.aE, str, c, this.f1956O, m1104b());
            deviceControl = this.ac;
        } else if (c.equals(TYPE_BP3L)) {
            this.f1960S = new Bp3lControl(this.f1973f, this.aE, this.f1958Q, str, c, this.f1956O, m1104b());
            deviceControl = this.f1960S;
        } else if (c.equals(TYPE_BP5S)) {
            this.f1962U = new Bp5sControl(this.f1973f, this.aE, this.f1958Q, str, c, this.f1956O, m1104b());
            deviceControl = this.f1962U;
        } else if (c.equals(TYPE_550BT)) {
            this.f1965X = new Bp550BTControl(this.f1973f, this.aE, this.f1958Q, str, c, m1104b(), this.f1956O);
            deviceControl = this.f1965X;
        } else if (c.equals(TYPE_KD926)) {
            this.f1966Y = new Bp926Control(this.f1973f, this.aE, this.f1958Q, str, c, m1104b(), this.f1956O);
            deviceControl = this.f1966Y;
        } else if (c.equals(TYPE_KD723)) {
            this.f1967Z = new Bp723Control(this.f1973f, this.aE, this.f1958Q, str, c, m1104b(), this.f1956O);
            deviceControl = this.f1967Z;
        } else if (c.equals(TYPE_ABPM)) {
            this.aa = new ABPMControl(this.f1973f, this.aE, this.f1958Q, str, c, m1104b(), this.f1956O);
            deviceControl = this.aa;
        } else if (c.equals(TYPE_BTM)) {
            this.al = new BtmControl(this.f1973f, this.aE, this.f1958Q, str, c, m1104b(), this.f1956O);
            deviceControl = this.al;
        } else if (c.equals(TYPE_CBP)) {
            this.am = new BPControl(this.f1973f, this.aE, this.aD, this.f1958Q, str, c, m1104b(), this.f1956O);
            deviceControl = this.am;
        } else if (c.equals(TYPE_BG5l)) {
            this.an = new Bg5lControl(this.f1973f, this.aE, this.f1958Q, str, c, m1104b(), this.f1956O);
            deviceControl = this.an;
        } else if (c.equals(TYPE_CBG)) {
            this.ao = new BgControl(this.f1973f, this.aE, this.aD, this.f1958Q, str, c, m1104b(), this.f1956O);
            deviceControl = this.ao;
        } else if (c.equals(TYPE_CPO)) {
            this.ap = new PoControl(this.f1973f, this.aE, this.aD, this.f1958Q, str, c, m1104b(), this.f1956O);
            deviceControl = this.ap;
        } else if (c.equals(TYPE_CHS)) {
            this.aq = new HsControl(this.f1973f, this.aE, this.aD, this.f1958Q, str, c, m1104b(), this.f1956O);
            deviceControl = this.aq;
        } else if (c.equals(TYPE_CBS)) {
            this.ar = new BsControl(this.f1973f, this.aE, this.aD, this.f1958Q, str, c, m1104b(), this.f1956O);
            deviceControl = this.ar;
        } else {
            Log.m1214v("Runtime_iHealthDM", "no such device");
        }
        if (deviceControl != null) {
            deviceControl.init();
        }
    }

    public static iHealthDevicesManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private void m1132h() {
        if (this.aL == null) {
            Log.m1211e("Runtime_iHealthDM", "wifi is closed");
        } else if (aO == null) {
            try {
                aO = new DatagramSocket(8000);
                if (this.aK == null || !this.aK.isAlive()) {
                    this.aK = new WifiCommThread(this.aQ, this.f1973f, aO, this.aL);
                    if (this.aM != null) {
                        this.aM.post(this.aK);
                        return;
                    }
                    return;
                }
                Log.m1214v("Runtime_iHealthDM", "wifiCommThread has existed");
            } catch (Exception e) {
                Log.m1215w("Runtime_iHealthDM", "openUdpSoket() -- e: " + e);
            }
        }
    }

    private void m1134i() {
        m1138k();
        this.aS = true;
        aT = new Timer();
        this.aR = new TimerTask() {
            public void run() {
                if (iHealthDevicesManager.stopUDPSearch) {
                    Log.m1214v("Runtime_iHealthDM", "+udp scan is be stopped");
                    return;
                }
                try {
                    iHealthDevicesManager.this.m1136j();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        aT.schedule(this.aR, 1000, 5000);
    }

    private void m1136j() {
        if (this.f1968a) {
            try {
                if (VERSION.SDK_INT <= 23) {
                    Log.m1212i("Runtime_iHealthDM", "version under Android M, setBroadcast.");
                    aO.setBroadcast(true);
                }
                this.aU = InetAddress.getByName("255.255.255.255");
                this.aV = new DatagramPacket(this.aW, 7, this.aU, AbstractSpiCall.DEFAULT_TIMEOUT);
                aO.send(this.aV);
                for (Entry entry : this.f1945D.entrySet()) {
                    Log.m1214v("Runtime_iHealthDM", "start wifi scan ，need all wifi device count - 1,current mac = " + ((String) entry.getKey()));
                    entry.setValue(Integer.valueOf(((Integer) entry.getValue()).intValue() - 1));
                    if (((Integer) entry.getValue()).intValue() == 0) {
                        this.f1945D.remove(entry.getKey());
                        this.f1943B.remove(entry.getKey());
                        this.f1944C.remove(entry.getKey());
                        this.f1956O.onConnectionStateChange((String) entry.getKey(), TYPE_HS5, 2, 0, null);
                    }
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                if (aO != null) {
                    aO.close();
                    aO = null;
                    return;
                }
                return;
            }
        }
        Log.m1211e("Runtime_iHealthDM", "UDPSearch but wifi is invalid");
    }

    private void m1138k() {
        this.aS = false;
        if (aT != null) {
            aT.cancel();
            aT = null;
        }
        if (this.aR != null) {
            this.aR.cancel();
            this.aR = null;
        }
    }

    private UsbDevice m1139l() {
        this.bc = (UsbManager) this.f1973f.getSystemService("usb");
        for (Entry entry : this.bc.getDeviceList().entrySet()) {
            int vendorId = ((UsbDevice) entry.getValue()).getVendorId();
            int productId = ((UsbDevice) entry.getValue()).getProductId();
            if (vendorId == 1118 && productId == 688) {
                this.bb = 1;
                return (UsbDevice) entry.getValue();
            } else if (vendorId == 1027 && productId == 24577) {
                this.bb = 1;
                return (UsbDevice) entry.getValue();
            } else if (vendorId == 1027 && productId == 24596) {
                this.bb = 1;
                return (UsbDevice) entry.getValue();
            } else if (vendorId == 1027 && productId == 24592) {
                this.bb = 1;
                return (UsbDevice) entry.getValue();
            } else if (vendorId == 1027 && productId == 24593) {
                this.bb = 1;
                return (UsbDevice) entry.getValue();
            } else if (vendorId == 1027 && productId == 24597) {
                this.bb = 1;
                return (UsbDevice) entry.getValue();
            } else if (vendorId == 1412 && productId == 45088) {
                this.bb = 1;
                return (UsbDevice) entry.getValue();
            } else if (vendorId == 1659 && productId == 8963) {
                this.bb = 2;
                return (UsbDevice) entry.getValue();
            } else if (vendorId == 1659 && productId == 45088) {
                this.bb = 2;
                return (UsbDevice) entry.getValue();
            }
        }
        return null;
    }

    private void m1142m() {
        if (1 == this.bb) {
            Toast.makeText(this.f1973f, "USBDEVICE_FTDI == mState", 1).show();
            m1144n();
        } else if (2 == this.bb) {
            Toast.makeText(this.f1973f, "USBDEVICE_PL2303 == mState)", 1).show();
            m1146o();
        }
    }

    private void m1144n() {
        this.bk = new FtdiUsb(this.f1973f);
        this.bi = 0;
        this.bi = this.bk.createDeviceList();
        if (this.bi > 0) {
            this.bk.connectFunction();
            this.bk.SetConfig(this.bd, this.bf, this.be, this.bg, this.bh);
            new Timer().schedule(new TimerTask() {
                public void run() {
                    iHealthDevicesManager.this.m1089a(iHealthDevicesManager.this.bk);
                }
            }, 200);
        }
    }

    private void m1146o() {
        this.bl = new Pl2303Usb(this.f1973f);
        this.bl.initSerialPort();
        SystemClock.sleep(500);
        if (this.bl.setSerialPort() >= 0) {
            this.bl.openUsbSerial();
            this.bl.readUsbSerialThread();
            m1089a(this.bl);
        }
    }

    public boolean addCallbackFilterForAddress(int clientCallbackId, String... macs) {
        Log.m1213p("Runtime_iHealthDM", Level.INFO, "addCallbackFilterForAddress", Integer.valueOf(clientCallbackId), macs);
        if (!m1103a(macs)) {
            return false;
        }
        this.av.addCallbackFilter(clientCallbackId, macs);
        return true;
    }

    public boolean addCallbackFilterForDeviceType(int clientCallbackId, String... deviceTypes) {
        Log.m1213p("Runtime_iHealthDM", Level.INFO, "addCallbackFilterForDeviceType", Integer.valueOf(clientCallbackId), deviceTypes);
        if (!m1111b(deviceTypes)) {
            return false;
        }
        this.av.addCallbackFilter(clientCallbackId, deviceTypes);
        return true;
    }

    public boolean connectDevice(String userName, String mac) {
        Log.m1213p("Runtime_iHealthDM", Level.ERROR, "connectDevice", userName, mac, "Error:The api is deprecated, use {@link iHealthDevicesManager#connectDevice(userName, mac, type)} instead");
        this.f1956O.onConnectionStateChange(mac, "", 3, 0, null);
        return false;
    }

    public boolean connectDevice(String userName, final String mac, final String type) {
        Log.m1213p("Runtime_iHealthDM", Level.INFO, "connectDevice", userName, mac, type);
        if (mac != null && type != null && mac.length() >= 12 && type.length() > 0) {
            if (this.as == null || mac == null || this.as.get(mac) == null) {
                ScanDevice scanDevice = (ScanDevice) this.f1954M.get(mac);
                m1114c();
                if (!b.a || (this.f1957P != null && this.f1957P.getDevicePermisson(userName, type))) {
                    this.f1955N.put(mac, type);
                    if (scanDevice == null) {
                        if (type.equals(TYPE_BG5) || type.equals(TYPE_BP5) || type.equals(TYPE_BP7) || type.equals(TYPE_BP7S) || type.equals(TYPE_HS4S)) {
                            m1124e(mac, type);
                        } else if (type.equals(TYPE_HS5) || type.equals(TYPE_HS5S) || type.equals(TYPE_HS6) || type.equals(TYPE_BP3M)) {
                            this.f1956O.onConnectionStateChange(mac, type, 3, 0, null);
                            return false;
                        } else {
                            m1108b(mac);
                        }
                        return true;
                    }
                    m1102a(userName, mac, type, scanDevice);
                    return true;
                }
            }
            this.aX.postDelayed(new Runnable() {
                public void run() {
                    iHealthDevicesManager.this.f1956O.onConnectionStateChange(mac, type, 1, 0, null);
                }
            }, 1000);
            return true;
        }
        this.f1956O.onConnectionStateChange(mac, type, 3, 0, null);
        return false;
    }

    public void destroy() {
        Log.m1213p("Runtime_iHealthDM", Level.INFO, "destroy", new Object[0]);
        try {
            AbiControlSubManager.getInstance().destory();
            synchronized (this.av) {
                this.av.clear();
            }
            m1085a();
            if (this.f1974g != null) {
                this.f1974g.cancelDiscovery();
            }
            if (this.aD != null) {
                this.aD.scan(false);
            }
            this.f1973f.unregisterReceiver(this.aA);
            iHealthDevicesUpgradeManager.getInstance().destroy();
            this.aC.clear();
        } catch (IllegalArgumentException e) {
            Log.m1215w("Runtime_iHealthDM", "destroy() -- e: " + e);
        }
    }

    public void disconnectDevice(String mac, String deviceType) {
        Log.m1213p("Runtime_iHealthDM", Level.INFO, "disconnectDevice", mac, deviceType);
        if (deviceType == null) {
            deviceType = (String) this.f1955N.get(mac);
        }
        if (mac == null || deviceType == null) {
            Log.m1213p("Runtime_iHealthDM", Level.VERBOSE, "disconnectDevice", mac, deviceType);
        } else if (deviceType.equals(TYPE_BP5)) {
            Bp5Control bp5Control = (Bp5Control) this.f1977j.get(mac);
            if (bp5Control != null) {
                bp5Control.disconnect();
            }
        } else if (deviceType.equals(TYPE_BP5S)) {
            Bp5sControl bp5sControl = (Bp5sControl) this.f1978k.get(mac);
            if (bp5sControl != null) {
                bp5sControl.disconnect();
            }
        } else if (deviceType.equals(TYPE_BP7)) {
            Bp7Control bp7Control = (Bp7Control) this.f1979l.get(mac);
            if (bp7Control != null) {
                bp7Control.disconnect();
            }
        } else if (deviceType.equals(TYPE_BP7S)) {
            Bp7sControl bp7sControl = (Bp7sControl) this.f1980m.get(mac);
            if (bp7sControl != null) {
                bp7sControl.disconnect();
            }
        } else if (deviceType.equals(TYPE_BG5)) {
            Bg5Control bg5Control = (Bg5Control) this.f1946E.get(mac);
            if (bg5Control != null) {
                bg5Control.disconnect();
            }
        } else if (deviceType.equals(TYPE_HS3)) {
            Hs3Control hs3Control = (Hs3Control) this.f1985r.get(mac);
            if (hs3Control != null) {
                hs3Control.disconnect();
            }
        } else if (deviceType.equals(TYPE_HS4S)) {
            Hs4sControl hs4sControl = (Hs4sControl) this.f1987t.get(mac);
            if (hs4sControl != null) {
                hs4sControl.disconnect();
            }
        } else {
            this.aD.disconnect(mac);
        }
    }

    public ABPMControl getABPMControl(String mac) {
        return (mac == null || this.f1984q == null) ? null : (ABPMControl) this.f1984q.get(mac);
    }

    public List getABPMDevices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1984q.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public AbiControl getAbiControl(String mac) {
        return mac == null ? null : AbiControlSubManager.getInstance().getAbiControl(mac);
    }

    public AbiControl getAbiControlforUp(String mac) {
        return mac == null ? null : AbiControlSubManager.getInstance().getAbiControlforUp(mac);
    }

    public Am3Control getAm3Control(String mac) {
        return (mac == null || this.f1989v == null) ? null : (Am3Control) this.f1989v.get(mac);
    }

    public List getAm3Devices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1989v.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Am3sControl getAm3sControl(String mac) {
        return (mac == null || this.f1990w == null) ? null : (Am3sControl) this.f1990w.get(mac);
    }

    public List getAm3sDevices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1990w.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Am4Control getAm4Control(String mac) {
        return (mac == null || this.f1991x == null) ? null : (Am4Control) this.f1991x.get(mac);
    }

    public List getAm4Devices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1991x.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Bg5lControl getBG5lControl(String mac) {
        return (mac == null || this.f1993z == null) ? null : (Bg5lControl) this.f1993z.get(mac);
    }

    public List getBG5lDevices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1993z.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public BPControl getBPControl(String mac) {
        return (mac == null || this.f1949H == null) ? null : (BPControl) this.f1949H.get(mac);
    }

    public List getBPDevices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1949H.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Bg5Control getBg5Control(String mac) {
        return (mac == null || this.f1946E == null) ? null : (Bg5Control) this.f1946E.get(mac);
    }

    public List getBg5Devices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1946E.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public BgControl getBgControl(String mac) {
        return (mac == null || this.f1950I == null) ? null : (BgControl) this.f1950I.get(mac);
    }

    public List getBgDevices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1950I.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Bp3lControl getBp3lControl(String mac) {
        return (mac == null || this.f1976i == null) ? null : (Bp3lControl) this.f1976i.get(mac);
    }

    public List getBp3lDevices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1976i.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Bp3mControl getBp3mControl(String mac) {
        return (mac == null || this.f1975h == null) ? null : (Bp3mControl) this.f1975h.get(mac);
    }

    public List getBp3mDevices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1975h.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Bp550BTControl getBp550BTControl(String mac) {
        return (mac == null || this.f1981n == null) ? null : (Bp550BTControl) this.f1981n.get(mac);
    }

    public List getBp550BTDevices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1981n.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Bp5Control getBp5Control(String mac) {
        return (mac == null || this.f1977j == null) ? null : (Bp5Control) this.f1977j.get(mac);
    }

    public List getBp5Devices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1977j.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Bp5sControl getBp5sControl(String mac) {
        return (mac == null || this.f1978k == null) ? null : (Bp5sControl) this.f1978k.get(mac);
    }

    public List getBp5sDevices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1978k.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Bp723Control getBp723Control(String mac) {
        return (mac == null || this.f1983p == null) ? null : (Bp723Control) this.f1983p.get(mac);
    }

    public List getBp723Devices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1983p.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Bp7Control getBp7Control(String mac) {
        return (mac == null || this.f1979l == null) ? null : (Bp7Control) this.f1979l.get(mac);
    }

    public List getBp7Devices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1979l.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Bp7sControl getBp7sControl(String mac) {
        return (mac == null || this.f1980m == null) ? null : (Bp7sControl) this.f1980m.get(mac);
    }

    public List getBp7sDevices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1980m.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Bp926Control getBp926Control(String mac) {
        return (mac == null || this.f1982o == null) ? null : (Bp926Control) this.f1982o.get(mac);
    }

    public List getBp926Devices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1982o.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public BsControl getBsControl(String mac) {
        return (mac == null || this.f1953L == null) ? null : (BsControl) this.f1953L.get(mac);
    }

    public List getBsDevices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1953L.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public BtmControl getBtmControl(String mac) {
        return (mac == null || this.f1948G == null) ? null : (BtmControl) this.f1948G.get(mac);
    }

    public String getDevicesIDPS(String mac) {
        IDPS idps = (IDPS) this.aC.get(mac);
        if (idps == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(iHealthDevicesIDPS.PROTOCOLSTRING, idps.getProtoclString());
            jSONObject.put(iHealthDevicesIDPS.ACCESSORYNAME, idps.getAccessoryName());
            Object accessoryModelNumber = idps.getAccessoryModelNumber();
            if (!TextUtils.isEmpty(accessoryModelNumber)) {
                String[] split = accessoryModelNumber.split(Constants.SPACE);
                if (split.length > 0) {
                    jSONObject.put(iHealthDevicesIDPS.ACCESSORYNAME, split[0]);
                }
            }
            CharSequence accessoryName = idps.getAccessoryName();
            if (!TextUtils.isEmpty(accessoryName) && "BP3".equals(accessoryName)) {
                jSONObject.put(iHealthDevicesIDPS.ACCESSORYNAME, TYPE_BP3M);
            }
            jSONObject.put(iHealthDevicesIDPS.FIRMWAREVERSION, idps.getAccessoryFirmwareVersion());
            jSONObject.put(iHealthDevicesIDPS.HARDWAREVERSION, idps.getAccessoryHardwareVersion());
            jSONObject.put(iHealthDevicesIDPS.MANUFACTURER, idps.getAccessoryManufaturer());
            jSONObject.put(iHealthDevicesIDPS.SERIALNUMBER, idps.getAccessorySerialNumber());
            jSONObject.put(iHealthDevicesIDPS.MODENUMBER, idps.getAccessoryModelNumber());
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getEE(String mac) {
        this.at = this.f1973f.getSharedPreferences("bg5_ee_new", 0);
        return this.at.getString(mac, "000");
    }

    public Hs3Control getHs3Control(String mac) {
        return (mac == null || this.f1985r == null) ? null : (Hs3Control) this.f1985r.get(mac);
    }

    public List getHs3Devices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1985r.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Hs4Control getHs4Control(String mac) {
        return (mac == null || this.f1986s == null) ? null : (Hs4Control) this.f1986s.get(mac);
    }

    public List getHs4Devices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1986s.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Hs4sControl getHs4sControl(String mac) {
        return (mac == null || this.f1987t == null) ? null : (Hs4sControl) this.f1987t.get(mac);
    }

    public List getHs4sDevices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1987t.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public Hs5Control getHs5Control(String mac) {
        return (Hs5Control) this.f1942A.get(mac);
    }

    public Hs5ControlForBt getHs5ControlForBt(String mac) {
        return (mac == null || this.f1988u == null) ? null : (Hs5ControlForBt) this.f1988u.get(mac);
    }

    public int getHs5Number() {
        return this.f1942A.size();
    }

    public HsControl getHsControl(String mac) {
        return (mac == null || this.f1952K == null) ? null : (HsControl) this.f1952K.get(mac);
    }

    public List getHsDevices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1952K.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public IDPS getIdps(String mac) {
        IDPS idps = (IDPS) this.aC.get(mac);
        if (idps != null) {
            Object accessoryModelNumber = idps.getAccessoryModelNumber();
            if (!TextUtils.isEmpty(accessoryModelNumber)) {
                String[] split = accessoryModelNumber.split(Constants.SPACE);
                if (split.length > 0) {
                    idps.setAccessoryName(split[0]);
                }
            }
            if (!TextUtils.isEmpty(idps.getAccessoryName()) && "BP3".equals(idps.getAccessoryName())) {
                idps.setAccessoryName(TYPE_BP3M);
            }
        }
        return idps;
    }

    public Po3Control getPo3Control(String mac) {
        return (Po3Control) this.f1992y.get(mac);
    }

    public List getPo3Devices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1992y.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public PoControl getPoControl(String mac) {
        return (mac == null || this.f1951J == null) ? null : (PoControl) this.f1951J.get(mac);
    }

    public List getPoDevices() {
        List arrayList = new ArrayList();
        for (Entry key : this.f1951J.entrySet()) {
            arrayList.add((String) key.getKey());
        }
        return arrayList;
    }

    public void init(Context mContext) {
        Log.m1213p("Runtime_iHealthDM", Level.INFO, "init", new Object[0]);
        Log.m1215w("Runtime_iHealthDM", "iHealthLibrary Version:ASDK_2.3.2.22");
        Log.m1215w("Runtime_iHealthDM", "Manufacturer:" + Build.MANUFACTURER + "  Model:" + Build.MODEL + "  api:" + VERSION.SDK_INT + "  version:" + VERSION.RELEASE);
        this.f1946E.clear();
        this.f1954M.clear();
        this.f1975h.clear();
        this.f1976i.clear();
        this.f1977j.clear();
        this.f1978k.clear();
        this.f1979l.clear();
        this.f1980m.clear();
        this.f1981n.clear();
        this.f1982o.clear();
        this.f1983p.clear();
        this.f1984q.clear();
        this.f1985r.clear();
        this.f1986s.clear();
        this.f1987t.clear();
        this.f1988u.clear();
        this.f1989v.clear();
        this.f1990w.clear();
        this.f1991x.clear();
        this.f1992y.clear();
        this.f1948G.clear();
        this.f1949H.clear();
        this.f1993z.clear();
        this.f1950I.clear();
        this.f1952K.clear();
        this.f1951J.clear();
        this.f1952K.clear();
        this.f1953L.clear();
        this.f1947F.clear();
        this.f1973f = mContext;
        m1123e();
        this.f1942A.clear();
        this.f1943B.clear();
        this.f1944C.clear();
        this.f1945D.clear();
        this.f1974g = BluetoothAdapter.getDefaultAdapter();
        m1118d();
        m1087a(mContext, this.f1956O, m1104b());
        iHealthDevicesUpgradeManager.getInstance().m1184a(mContext, m1104b());
        String packageName = mContext.getPackageName();
        if (packageName.length() >= 12) {
            if (m1082a(packageName.substring(0, 12)).equals("af7043a8b34a1bf4ebe9949bdf94f73870")) {
                b.a = false;
            } else {
                b.a = true;
            }
        }
        if (b.a) {
            packageName = m1082a(packageName);
            if (packageName.equals("cebc91e451a413cc66d5253aacb8640bbc") || packageName.equals("ad9541cd84371aa9a98dfbc200cd25fa95") || packageName.equals("e67478cfd3493889c5c9eb80a26501bd74") || packageName.equals("b4552034f0f4bb02cdcd89afe0628a2155")) {
                b.a = false;
            } else if (packageName.equals("c067e30bc78239171b25095164ebd0c267")) {
                try {
                    if (new Date().before(new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1).parse("2018-09-30 12:00:00"))) {
                        b.a = false;
                    } else {
                        b.a = true;
                    }
                } catch (ParseException e) {
                    Log.m1215w("Runtime_iHealthDM", "ParseException:" + e);
                    b.a = true;
                }
            } else if (packageName.equals("12ef676a39a8b4052315241c7e92f254ef")) {
                try {
                    if (new Date().before(new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1).parse("2017-03-31 12:00:00"))) {
                        b.a = false;
                    } else {
                        b.a = true;
                    }
                } catch (ParseException e2) {
                    Log.m1215w("Runtime_iHealthDM", "ParseException:" + e2);
                    b.a = true;
                }
            } else {
                b.a = true;
            }
        }
        this.aY = new NotifyThread();
        this.aZ = new ScanThread();
        this.f1969b = new ScanFinishThread();
        this.ba = new ConnectionThread();
        AbiControlSubManager.getInstance().init();
        this.f1972e = DISCOVERY_BP3M;
        m1100a(mContext);
    }

    public int registerClientCallback(iHealthDevicesCallback miHealthDevicesCallback) {
        Log.m1213p("Runtime_iHealthDM", Level.INFO, "registerClientCallback", miHealthDevicesCallback);
        return this.av.add(miHealthDevicesCallback);
    }

    public boolean sdkAuthWithAppSecret(Context context, String secret) {
        if (context == null) {
            Log.m1211e("Runtime_iHealthDM", "sdkAuthWithAppSecret() parameter context is null, authenticate failed.");
            b.a = true;
            return false;
        } else if (secret == null) {
            Log.m1211e("Runtime_iHealthDM", "sdkAuthWithAppSecret() parameter secret is null, authenticate failed.");
            b.a = true;
            return false;
        } else if (secret.equals(C2160p.m1197a(context))) {
            Log.m1212i("Runtime_iHealthDM", "sdkAuthWithAppSecret() authenticate successfully.");
            b.a = false;
            return true;
        } else {
            Log.m1212i("Runtime_iHealthDM", "sdkAuthWithAppSecret() authenticate failed.");
            b.a = true;
            return false;
        }
    }

    public void sdkUserInAuthor(Context con, String userName, String clientId, String clientSecret, int clientCallbackId) {
        Log.m1213p("Runtime_iHealthDM", Level.INFO, "sdkUserInAuthor", con, userName, clientId, clientSecret, Integer.valueOf(clientCallbackId));
        this.f1958Q = userName;
        this.f1957P = new iHealthDeviceCloudManager(this.f1973f, this.av.getCallback(clientCallbackId));
        this.f1957P.SDKUserInAuthor(userName, clientId, clientSecret);
    }

    public void sdkUserInAuthor(Context con, String userName, String clientId, String clientSecret, int clientCallbackId, String filePath, String password) {
        Log.m1213p("Runtime_iHealthDM", Level.INFO, "sdkUserInAuthor", con, userName, clientId, clientSecret, Integer.valueOf(clientCallbackId), filePath, password);
        this.f1958Q = userName;
        Editor edit = this.f1973f.getSharedPreferences("IHCertificateFileInfo", 0).edit();
        edit.putString("cert_path", filePath);
        edit.putString("cert_password", password);
        edit.commit();
        edit.apply();
        this.f1957P = new iHealthDeviceCloudManager(this.f1973f, this.av.getCallback(clientCallbackId));
        this.f1957P.SDKUserInAuthor(userName, clientId, clientSecret);
    }

    public void startDiscovery(long type) {
        Log.m1213p("Runtime_iHealthDM", Level.INFO, "startDiscovery", Long.toHexString(type));
        this.f1954M.clear();
        this.f1971d |= type;
        if ((this.f1971d & DISCOVERY_BP3M) != 0) {
            UsbDevice l = m1139l();
            if (l != null) {
                m1098a("000000000000", TYPE_BP3M, null, null, l, ScanDevice.LINK_USB, 0, new HashMap());
            }
            if (this.f1971d == DISCOVERY_BP3M) {
                return;
            }
        }
        if ((this.f1971d & DISCOVERY_HS5_WIFI) != 0) {
            this.ax = true;
            if (!this.aS) {
                m1134i();
            }
        }
        if (this.f1971d == DISCOVERY_HS5_WIFI) {
            return;
        }
        if (this.f1974g == null || !this.f1974g.isEnabled()) {
            Log.m1211e("Runtime_iHealthDM", "Bluetooth is currently disabled");
            if ((this.f1971d & DISCOVERY_HS5_WIFI) == 0 && (this.f1971d & DISCOVERY_BP3M) == 0 && this.f1969b != null) {
                this.aX.post(this.f1969b);
            }
        } else if (m1100a(this.f1973f)) {
            this.aw.m1192a(this.aD, type, new C21474());
        } else {
            this.aw.m1193a(new C21485());
        }
    }

    public void stopDiscovery() {
        Log.m1213p("Runtime_iHealthDM", Level.INFO, "stopDiscovery", new Object[0]);
        this.f1971d = 0;
        this.ax = false;
        if (this.aw.m1194b()) {
            this.aw.m1191a();
            if (this.f1969b != null) {
                this.aX.post(this.f1969b);
            }
        }
    }

    public void unRegisterClientCallback(int clientId) {
        Log.m1213p("Runtime_iHealthDM", Level.INFO, "unRegisterClientCallback", Integer.valueOf(clientId));
        this.av.remove(clientId);
    }
}
