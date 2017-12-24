package tw.com.prolific.driver.pl2303;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Build.VERSION;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class PL2303Driver {
    private static final int f2836A = 2056;
    private static final int f2837B = 2313;
    public static final int BAUD0 = 0;
    public static final int BAUD115200 = 115200;
    public static final int BAUD1200 = 1200;
    public static final int BAUD12000000 = 12000000;
    public static final int BAUD1228800 = 1228800;
    public static final int BAUD14400 = 14400;
    public static final int BAUD150 = 150;
    public static final int BAUD1800 = 1800;
    public static final int BAUD19200 = 19200;
    public static final int BAUD230400 = 230400;
    public static final int BAUD2400 = 2400;
    public static final int BAUD2457600 = 2457600;
    public static final int BAUD300 = 300;
    public static final int BAUD3000000 = 3000000;
    public static final int BAUD38400 = 38400;
    public static final int BAUD460800 = 460800;
    public static final int BAUD4800 = 4800;
    public static final int BAUD57600 = 57600;
    public static final int BAUD600 = 600;
    public static final int BAUD6000000 = 6000000;
    public static final int BAUD614400 = 614400;
    public static final int BAUD75 = 75;
    public static final int BAUD921600 = 921600;
    public static final int BAUD9600 = 9600;
    private static final int f2838E = 1;
    private static final int f2839F = 2;
    private static final int f2840G = 32;
    public static final int PL2303HXD_CTS_ON = 128;
    public static final int PL2303HXD_DCD_ON = 1;
    public static final int PL2303HXD_DSR_ON = 2;
    public static final int PL2303HXD_RI_ON = 8;
    public static final int PL_MAX_INTERFACE_NUM = 4;
    public static final int READBUF_SIZE = 4096;
    public static Object ReadQueueLock = new Object();
    public static final int WRITEBUF_SIZE = 4096;
    static final int f2841a = 11;
    private static /* synthetic */ int[] aH = null;
    private static /* synthetic */ int[] aI = null;
    private static /* synthetic */ int[] aJ = null;
    private static /* synthetic */ int[] aK = null;
    private static /* synthetic */ int[] aL = null;
    static final int f2842b = 12;
    private static final boolean f2843f = false;
    private static final boolean f2844g = false;
    private static final boolean f2845h = false;
    private static String f2846i = "2.0.12.31";
    private static final int f2847k = 33;
    private static final int f2848l = 32;
    private static final int f2849m = 33;
    private static final int f2850n = 35;
    private static final int f2851o = 0;
    private static final int f2852p = 161;
    private static final int f2853q = 33;
    private static final int f2854r = 64;
    private static final int f2855s = 1;
    public static UsbDevice sDevice = null;
    private static final int f2856t = 192;
    private static final int f2857u = 1;
    private static final int f2858v = 33;
    private static final int f2859w = 34;
    private static final int f2860x = 0;
    private static final int f2861y = 1;
    private static final int f2862z = 2;
    private int f2863C;
    private byte f2864D;
    private final int f2865H;
    private UsbManager f2866I;
    private UsbDevice f2867J;
    private UsbDeviceConnection f2868K;
    private UsbInterface f2869L;
    private UsbEndpoint f2870M;
    private UsbEndpoint f2871N;
    private UsbEndpoint f2872O;
    private int f2873P;
    public final int PLDETACHED_VALUE;
    public final String PLUART_DETACHED;
    public final String PLUART_MESSAGE;
    private int f2874Q;
    private int f2875R;
    private int f2876S;
    private int f2877T;
    private ArrayBlockingQueue<Integer> f2878U;
    private C2646a f2879V;
    private boolean f2880W;
    private int f2881X;
    private int f2882Y;
    private boolean f2883Z;
    private int aA;
    private int aB;
    private int aC;
    private int aD;
    private boolean aE;
    private final BroadcastReceiver aF;
    private Runnable aG;
    private boolean aa;
    private String ab;
    private ArrayList<String> ac;
    private int ad;
    private int ae;
    private final int af;
    private final int ag;
    private final int ah;
    private final int ai;
    private final int aj;
    private final int ak;
    private final int al;
    private final int am;
    private boolean an;
    private boolean ao;
    private boolean ap;
    private FlowControl aq;
    private boolean ar;
    private final boolean as;
    private final boolean at;
    private final int au;
    private final int av;
    private int aw;
    private int ax;
    private int ay;
    private int az;
    byte[] f2884c;
    Context f2885d;
    private boolean f2886e;
    private byte[] f2887j;

    class C26441 extends BroadcastReceiver {
        final /* synthetic */ PL2303Driver f2823a;

        C26441(PL2303Driver pL2303Driver) {
            this.f2823a = pL2303Driver;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onReceive(android.content.Context r7, android.content.Intent r8) {
            /*
            r6 = this;
            r1 = 0;
            r2 = r8.getAction();
            r0 = "device";
            r0 = r8.getParcelableExtra(r0);
            r0 = (android.hardware.usb.UsbDevice) r0;
            r3 = "android.hardware.usb.action.USB_DEVICE_ATTACHED";
            r3 = r3.equals(r2);
            if (r3 == 0) goto L_0x0016;
        L_0x0015:
            return;
        L_0x0016:
            r3 = "android.hardware.usb.action.USB_DEVICE_DETACHED";
            r3 = r3.equals(r2);
            if (r3 == 0) goto L_0x0055;
        L_0x001e:
            r0 = r0.getDeviceName();
            r1 = r6.f2823a;
            r1 = r1.f2867J;
            if (r1 == 0) goto L_0x0015;
        L_0x002a:
            r1 = r6.f2823a;
            r1 = r1.f2867J;
            r0 = r1.equals(r0);
            if (r0 == 0) goto L_0x0015;
        L_0x0036:
            r0 = new android.content.Intent;
            r1 = "tw.PL2303USBMessage";
            r0.<init>(r1);
            r1 = "USB.Detached";
            r2 = 255; // 0xff float:3.57E-43 double:1.26E-321;
            r2 = java.lang.String.valueOf(r2);
            r0.putExtra(r1, r2);
            r1 = r6.f2823a;
            r1 = r1.f2885d;
            r1.sendBroadcast(r0);
            r0 = r6.f2823a;
            r0.end();
            goto L_0x0015;
        L_0x0055:
            r3 = r6.f2823a;
            r3 = r3.ab;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0015;
        L_0x0061:
            monitor-enter(r6);
            r2 = "permission";
            r3 = 0;
            r2 = r8.getBooleanExtra(r2, r3);	 Catch:{ all -> 0x006d }
            if (r2 != 0) goto L_0x0070;
        L_0x006b:
            monitor-exit(r6);	 Catch:{ all -> 0x006d }
            goto L_0x0015;
        L_0x006d:
            r0 = move-exception;
            monitor-exit(r6);	 Catch:{ all -> 0x006d }
            throw r0;
        L_0x0070:
            if (r0 == 0) goto L_0x006b;
        L_0x0072:
            r2 = r6.f2823a;	 Catch:{ all -> 0x006d }
            r2 = r2.ad;	 Catch:{ all -> 0x006d }
            if (r1 >= r2) goto L_0x006b;
        L_0x007a:
            r2 = "%04X:%04X";
            r3 = 2;
            r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x006d }
            r4 = 0;
            r5 = r0.getVendorId();	 Catch:{ all -> 0x006d }
            r5 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x006d }
            r3[r4] = r5;	 Catch:{ all -> 0x006d }
            r4 = 1;
            r5 = r0.getProductId();	 Catch:{ all -> 0x006d }
            r5 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x006d }
            r3[r4] = r5;	 Catch:{ all -> 0x006d }
            r2 = java.lang.String.format(r2, r3);	 Catch:{ all -> 0x006d }
            r3 = r6.f2823a;	 Catch:{ all -> 0x006d }
            r3 = r3.ac;	 Catch:{ all -> 0x006d }
            r3 = r3.get(r1);	 Catch:{ all -> 0x006d }
            r2 = r2.equals(r3);	 Catch:{ all -> 0x006d }
            if (r2 == 0) goto L_0x00b1;
        L_0x00a9:
            r1 = r6.f2823a;	 Catch:{ all -> 0x006d }
            r1.m2214b(r0);	 Catch:{ all -> 0x006d }
            monitor-exit(r6);	 Catch:{ all -> 0x006d }
            goto L_0x0015;
        L_0x00b1:
            r1 = r1 + 1;
            goto L_0x0072;
            */
            throw new UnsupportedOperationException("Method not decompiled: tw.com.prolific.driver.pl2303.PL2303Driver.1.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    class C26452 implements Runnable {
        final /* synthetic */ PL2303Driver f2824a;

        C26452(PL2303Driver pL2303Driver) {
            this.f2824a = pL2303Driver;
        }

        public void run() {
            UsbDevice usbDevice = PL2303Driver.sDevice;
            if (!this.f2824a.isConnected()) {
                this.f2824a.m2204a(usbDevice);
                this.f2824a.an = true;
            }
        }
    }

    public enum BaudRate {
        B0,
        B75,
        B150,
        B300,
        B600,
        B1200,
        B1800,
        B2400,
        B4800,
        B9600,
        B14400,
        B19200,
        B38400,
        B57600,
        B115200,
        B230400,
        B460800,
        B614400,
        B921600,
        B1228800,
        B2457600,
        B3000000,
        B6000000,
        B12000000
    }

    public enum DataBits {
        D5,
        D6,
        D7,
        D8
    }

    public enum FlowControl {
        OFF,
        RTSCTS,
        RFRCTS,
        DTRDSR,
        RTSCTSDTRDSR,
        XONXOFF
    }

    public enum Parity {
        NONE,
        ODD,
        EVEN
    }

    public enum StopBits {
        S1,
        S2
    }

    class C2646a extends Thread {
        final /* synthetic */ PL2303Driver f2830a;
        private int f2831b;
        private int f2832c;
        private boolean f2833d = true;
        private boolean f2834e = false;
        private AtomicInteger f2835f = new AtomicInteger(500);

        C2646a(PL2303Driver pL2303Driver) {
            this.f2830a = pL2303Driver;
        }

        public void m2194a() {
            this.f2832c = 0;
            this.f2831b = 0;
            this.f2830a.f2878U.clear();
        }

        public void m2195a(int i) {
            m2194a();
            m2197b(i);
        }

        public void m2197b(int i) {
            this.f2835f.set(i);
        }

        public void m2196b() {
            this.f2834e = true;
            do {
            } while (isAlive());
            this.f2830a.f2878U.clear();
        }

        private void m2193c(int i) {
            if (i != 0) {
                long currentTimeMillis = System.currentTimeMillis();
                long currentTimeMillis2;
                do {
                    currentTimeMillis2 = System.currentTimeMillis();
                    Thread.yield();
                } while (currentTimeMillis2 - currentTimeMillis <= ((long) i));
            }
        }

        public void run() {
            try {
                byte[] bArr = new byte[4096];
                while (!this.f2834e) {
                    this.f2831b = this.f2830a.m2201a(bArr, bArr.length);
                    if (this.f2831b > 0) {
                        synchronized (PL2303Driver.ReadQueueLock) {
                            this.f2832c = this.f2830a.f2878U.size();
                            if (4096 != this.f2832c) {
                                for (int i = 0; i < this.f2831b && this.f2832c < 4096; i++) {
                                    int intValue = Integer.valueOf(bArr[i]).intValue();
                                    if (FlowControl.XONXOFF == this.f2830a.aq) {
                                        if (19 == intValue) {
                                            this.f2830a.ar = false;
                                        } else if (17 == intValue) {
                                            this.f2830a.ar = true;
                                        }
                                    }
                                    this.f2833d = this.f2830a.f2878U.offer(Integer.valueOf(intValue));
                                    if (!this.f2833d) {
                                        break;
                                    }
                                    this.f2832c = this.f2830a.f2878U.size();
                                }
                            }
                        }
                    }
                    m2193c(this.f2835f.get());
                }
            } catch (Exception e) {
            }
        }
    }

    static /* synthetic */ int[] m2211a() {
        int[] iArr = aH;
        if (iArr == null) {
            iArr = new int[BaudRate.values().length];
            try {
                iArr[BaudRate.B0.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BaudRate.B115200.ordinal()] = 15;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BaudRate.B1200.ordinal()] = 6;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[BaudRate.B12000000.ordinal()] = 24;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[BaudRate.B1228800.ordinal()] = 20;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[BaudRate.B14400.ordinal()] = 11;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[BaudRate.B150.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[BaudRate.B1800.ordinal()] = 7;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[BaudRate.B19200.ordinal()] = 12;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[BaudRate.B230400.ordinal()] = 16;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[BaudRate.B2400.ordinal()] = 8;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[BaudRate.B2457600.ordinal()] = 21;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[BaudRate.B300.ordinal()] = 4;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[BaudRate.B3000000.ordinal()] = 22;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[BaudRate.B38400.ordinal()] = 13;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[BaudRate.B460800.ordinal()] = 17;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[BaudRate.B4800.ordinal()] = 9;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[BaudRate.B57600.ordinal()] = 14;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[BaudRate.B600.ordinal()] = 5;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[BaudRate.B6000000.ordinal()] = 23;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[BaudRate.B614400.ordinal()] = 18;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[BaudRate.B75.ordinal()] = 2;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[BaudRate.B921600.ordinal()] = 19;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[BaudRate.B9600.ordinal()] = 10;
            } catch (NoSuchFieldError e24) {
            }
            aH = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] m2218b() {
        int[] iArr = aI;
        if (iArr == null) {
            iArr = new int[StopBits.values().length];
            try {
                iArr[StopBits.S1.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[StopBits.S2.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            aI = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] m2221c() {
        int[] iArr = aJ;
        if (iArr == null) {
            iArr = new int[Parity.values().length];
            try {
                iArr[Parity.EVEN.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Parity.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Parity.ODD.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            aJ = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] m2225d() {
        int[] iArr = aK;
        if (iArr == null) {
            iArr = new int[DataBits.values().length];
            try {
                iArr[DataBits.D5.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[DataBits.D6.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[DataBits.D7.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[DataBits.D8.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            aK = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] m2227e() {
        int[] iArr = aL;
        if (iArr == null) {
            iArr = new int[FlowControl.values().length];
            try {
                iArr[FlowControl.DTRDSR.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[FlowControl.OFF.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[FlowControl.RFRCTS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[FlowControl.RTSCTS.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[FlowControl.RTSCTSDTRDSR.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[FlowControl.XONXOFF.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            aL = iArr;
        }
        return iArr;
    }

    private void m2205a(UsbManager usbManager, Context context, String str, boolean z) {
        this.f2866I = usbManager;
        this.f2867J = null;
        this.f2870M = null;
        this.f2871N = null;
        this.f2872O = null;
        this.f2873P = 0;
        this.f2874Q = 0;
        this.an = false;
        this.f2880W = false;
        this.ao = false;
        this.f2885d = context;
        this.ap = z;
        this.ab = str;
        this.ar = true;
        this.aq = FlowControl.OFF;
        m2217b("067B:2303");
        m2217b("067B:2304");
        m2217b("067B:2551");
        m2217b("067B:2503");
        m2217b("067B:A100");
        m2217b("067B:AAA5");
        m2217b("05AD:0FBA");
        this.ad = this.ac.size();
        this.aw = 0;
        this.ax = 15;
        this.ay = 3;
        this.az = 0;
        this.aA = 0;
        this.aB = 0;
        this.aC = 0;
        this.aD = 0;
        this.f2875R = 100;
        this.f2876S = 100;
        this.f2877T = 100;
    }

    public PL2303Driver(UsbManager manager, Context mContext, String sAppName) {
        this.f2886e = false;
        this.f2887j = new byte[7];
        this.f2863C = 0;
        this.f2864D = (byte) 0;
        this.f2865H = 64;
        this.f2884c = new byte[4096];
        this.f2878U = new ArrayBlockingQueue(4096, true);
        this.f2881X = 0;
        this.f2882Y = 0;
        this.f2883Z = false;
        this.aa = false;
        this.ac = new ArrayList();
        this.ae = 0;
        this.af = 2;
        this.ag = 3;
        this.ah = 4;
        this.ai = 5;
        this.aj = 6;
        this.ak = 7;
        this.al = 8;
        this.am = 9;
        this.as = true;
        this.at = false;
        this.au = 17;
        this.av = 19;
        this.PLUART_MESSAGE = "tw.PL2303USBMessage";
        this.PLUART_DETACHED = "USB.Detached";
        this.PLDETACHED_VALUE = 255;
        this.aE = false;
        this.aF = new C26441(this);
        this.aG = new C26452(this);
        m2205a(manager, mContext, sAppName, true);
    }

    public PL2303Driver(UsbManager manager, Context mContext, String sAppName, boolean bWithQueue) {
        this.f2886e = false;
        this.f2887j = new byte[7];
        this.f2863C = 0;
        this.f2864D = (byte) 0;
        this.f2865H = 64;
        this.f2884c = new byte[4096];
        this.f2878U = new ArrayBlockingQueue(4096, true);
        this.f2881X = 0;
        this.f2882Y = 0;
        this.f2883Z = false;
        this.aa = false;
        this.ac = new ArrayList();
        this.ae = 0;
        this.af = 2;
        this.ag = 3;
        this.ah = 4;
        this.ai = 5;
        this.aj = 6;
        this.ak = 7;
        this.al = 8;
        this.am = 9;
        this.as = true;
        this.at = false;
        this.au = 17;
        this.av = 19;
        this.PLUART_MESSAGE = "tw.PL2303USBMessage";
        this.PLUART_DETACHED = "USB.Detached";
        this.PLDETACHED_VALUE = 255;
        this.aE = false;
        this.aF = new C26441(this);
        this.aG = new C26452(this);
        m2205a(manager, mContext, sAppName, bWithQueue);
    }

    private void m2204a(UsbDevice usbDevice) {
        if (this.f2868K != null) {
            if (this.f2869L != null) {
                this.f2868K.releaseInterface(this.f2869L);
                this.f2869L = null;
            }
            this.f2868K.close();
            this.f2867J = null;
            this.f2868K = null;
        }
        if (usbDevice != null) {
            int i = 0;
            while (i < usbDevice.getInterfaceCount()) {
                UsbInterface usbInterface = usbDevice.getInterface(i);
                if (255 == usbInterface.getInterfaceClass() && usbInterface.getInterfaceProtocol() == 0 && usbInterface.getInterfaceSubclass() == 0) {
                    break;
                }
                i++;
            }
            i = 0;
            UsbInterface usbInterface2 = usbDevice.getInterface(i);
            if (usbDevice != null && usbInterface2 != null) {
                UsbDeviceConnection openDevice = this.f2866I.openDevice(usbDevice);
                if (openDevice == null) {
                    return;
                }
                if (openDevice.claimInterface(usbInterface2, true)) {
                    this.f2867J = usbDevice;
                    this.f2868K = openDevice;
                    this.f2869L = usbInterface2;
                    if (!m2210a(this.f2869L)) {
                        return;
                    }
                    return;
                }
                openDevice.close();
            }
        }
    }

    boolean m2238a(String str) {
        boolean z = false;
        String str2 = "";
        if (VERSION.SDK_INT >= 21) {
            return true;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("toolbox ls " + str).getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                str2 = new StringBuilder(String.valueOf(str2)).append(readLine).toString();
            }
            if (str.compareTo(str2) == 0) {
                z = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return z;
    }

    private boolean m2217b(String str) {
        this.ac.add(str);
        this.ad = this.ac.size();
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean enumerate() {
        /*
        r10 = this;
        r9 = 2;
        r3 = 1;
        r2 = 0;
        r0 = r10.f2885d;
        r1 = "usb";
        r0 = r0.getSystemService(r1);
        r0 = (android.hardware.usb.UsbManager) r0;
        r10.f2866I = r0;
        r0 = r10.f2866I;
        r0 = r0.getDeviceList();
        r0 = r0.values();
        r4 = r0.iterator();
        r0 = r10.f2885d;
        r1 = new android.content.Intent;
        r5 = r10.ab;
        r1.<init>(r5);
        r5 = android.app.PendingIntent.getBroadcast(r0, r2, r1, r2);
    L_0x002a:
        r0 = r4.hasNext();
        if (r0 != 0) goto L_0x0031;
    L_0x0030:
        return r2;
    L_0x0031:
        r0 = r4.next();
        r0 = (android.hardware.usb.UsbDevice) r0;
        r1 = r2;
    L_0x0038:
        r6 = r10.ad;
        if (r1 >= r6) goto L_0x002a;
    L_0x003c:
        r6 = "%04X:%04X";
        r7 = new java.lang.Object[r9];
        r8 = r0.getVendorId();
        r8 = java.lang.Integer.valueOf(r8);
        r7[r2] = r8;
        r8 = r0.getProductId();
        r8 = java.lang.Integer.valueOf(r8);
        r7[r3] = r8;
        r6 = java.lang.String.format(r6, r7);
        r7 = r10.ac;
        r7 = r7.get(r1);
        r6 = r6.equals(r7);
        if (r6 == 0) goto L_0x006e;
    L_0x0064:
        r6 = r0.getDeviceName();
        r6 = r10.m2238a(r6);
        if (r6 != 0) goto L_0x0071;
    L_0x006e:
        r1 = r1 + 1;
        goto L_0x0038;
    L_0x0071:
        r6 = new android.content.IntentFilter;
        r7 = r10.ab;
        r6.<init>(r7);
        r7 = "android.hardware.usb.action.USB_DEVICE_DETACHED";
        r6.addAction(r7);
        r7 = r10.f2885d;
        r8 = r10.aF;
        r7.registerReceiver(r8, r6);
        r6 = r10.f2866I;
        r6 = r6.hasPermission(r0);
        if (r6 != 0) goto L_0x0092;
    L_0x008c:
        r6 = r10.f2866I;
        r6.requestPermission(r0, r5);
        goto L_0x006e;
    L_0x0092:
        r10.m2214b(r0);
        r1 = "%04X:%04X";
        r4 = new java.lang.Object[r9];
        r5 = r0.getVendorId();
        r5 = java.lang.Integer.valueOf(r5);
        r4[r2] = r5;
        r5 = r0.getProductId();
        r5 = java.lang.Integer.valueOf(r5);
        r4[r3] = r5;
        r1 = java.lang.String.format(r1, r4);
        r4 = "067B:2551";
        r1 = r1.equals(r4);
        if (r1 != 0) goto L_0x0101;
    L_0x00b9:
        r1 = "%04X:%04X";
        r4 = new java.lang.Object[r9];
        r5 = r0.getVendorId();
        r5 = java.lang.Integer.valueOf(r5);
        r4[r2] = r5;
        r5 = r0.getProductId();
        r5 = java.lang.Integer.valueOf(r5);
        r4[r3] = r5;
        r1 = java.lang.String.format(r1, r4);
        r4 = "067B:2503";
        r1 = r1.equals(r4);
        if (r1 != 0) goto L_0x0101;
    L_0x00dd:
        r1 = "%04X:%04X";
        r4 = new java.lang.Object[r9];
        r5 = r0.getVendorId();
        r5 = java.lang.Integer.valueOf(r5);
        r4[r2] = r5;
        r0 = r0.getProductId();
        r0 = java.lang.Integer.valueOf(r0);
        r4[r3] = r0;
        r0 = java.lang.String.format(r1, r4);
        r1 = "067B:A100";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0103;
    L_0x0101:
        r10.ao = r3;
    L_0x0103:
        r2 = r3;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: tw.com.prolific.driver.pl2303.PL2303Driver.enumerate():boolean");
    }

    private void m2214b(UsbDevice usbDevice) {
        sDevice = usbDevice;
        new Thread(this.aG).start();
    }

    private boolean m2229f() {
        if (!this.an || this.f2867J == null || m2199a(this.f2868K) < 0) {
            return false;
        }
        if (this.ae != 4 && this.ae != 6) {
            return false;
        }
        if (this.ap) {
            this.f2879V = new C2646a(this);
        } else {
            this.f2879V = null;
        }
        return true;
    }

    public boolean InitByDefualtValue() {
        if (!m2229f()) {
            return false;
        }
        if (this.ap) {
            m2230g();
        }
        return true;
    }

    public boolean InitByBaudRate(BaudRate R) {
        if (!m2229f()) {
            return false;
        }
        int upVar;
        try {
            upVar = setup(R, DataBits.D8, StopBits.S1, Parity.NONE, FlowControl.OFF);
        } catch (IOException e) {
            e.printStackTrace();
            upVar = 0;
        }
        if (upVar < 0) {
            return false;
        }
        if (this.ap) {
            m2230g();
        }
        return true;
    }

    public boolean InitByBaudRate(BaudRate R, int TimeoutConstant) {
        if (!m2229f()) {
            return false;
        }
        int upVar;
        try {
            upVar = setup(R, DataBits.D8, StopBits.S1, Parity.NONE, FlowControl.OFF);
        } catch (IOException e) {
            e.printStackTrace();
            upVar = 0;
        }
        if (upVar < 0) {
            return false;
        }
        if (!PL2303Device_SetCommTimeouts(TimeoutConstant)) {
            return false;
        }
        if (this.ap) {
            m2230g();
        }
        return true;
    }

    public boolean InitByPortSetting(BaudRate R, DataBits D, StopBits S, Parity P, FlowControl F) {
        if (!m2229f()) {
            return false;
        }
        int upVar;
        try {
            upVar = setup(R, D, S, P, F);
        } catch (IOException e) {
            e.printStackTrace();
            upVar = 0;
        }
        if (upVar < 0) {
            return false;
        }
        if (this.ap) {
            m2230g();
        }
        return true;
    }

    public void end() {
        if (this.f2867J != null) {
            if (this.ap) {
                m2231h();
            }
            this.ao = false;
            this.f2885d.unregisterReceiver(this.aF);
            m2204a(null);
        }
    }

    public boolean isConnected() {
        if (this.f2867J == null || this.f2870M == null || this.f2871N == null) {
            return false;
        }
        return true;
    }

    private boolean m2210a(UsbInterface usbInterface) {
        int i = 0;
        if (usbInterface == null) {
            return false;
        }
        while (i < usbInterface.getEndpointCount()) {
            if (usbInterface.getEndpoint(i).getType() == 2) {
                if (usbInterface.getEndpoint(i).getDirection() == 128) {
                    this.f2870M = usbInterface.getEndpoint(i);
                } else {
                    this.f2871N = usbInterface.getEndpoint(i);
                }
            } else if (usbInterface.getEndpoint(i).getType() == 3 && usbInterface.getEndpoint(i).getDirection() == 128) {
                this.f2872O = usbInterface.getEndpoint(i);
            }
            i++;
        }
        return true;
    }

    private void m2230g() {
        if (!this.f2880W) {
            this.f2879V.start();
            this.f2880W = this.f2879V.isAlive();
        }
    }

    private void m2231h() {
        if (this.f2880W && this.f2879V != null) {
            this.f2879V.m2196b();
            this.f2880W = this.f2879V.isAlive();
            this.f2879V = null;
        }
    }

    private void m2207a(BaudRate baudRate) {
        int i;
        int[] iArr = new int[]{3, 5, 10, 25, 100, 200};
        int i2 = iArr[3];
        switch (m2211a()[baudRate.ordinal()]) {
            case 1:
                i = AbstractSpiCall.DEFAULT_TIMEOUT;
                break;
            case 2:
            case 3:
                i = iArr[5];
                break;
            case 4:
            case 5:
                i = iArr[4];
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                i = iArr[3];
                break;
            case 11:
            case 12:
            case 13:
            case 14:
                i = iArr[2];
                break;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                i = iArr[1];
                break;
            case 20:
            case 21:
            case 22:
            case 23:
                i = iArr[0];
                break;
            default:
                return;
        }
        if (this.f2879V != null) {
            this.f2879V.m2197b(i);
        }
    }

    public int read(byte[] buf) {
        int i = 0;
        int length = buf.length;
        if (length == 0) {
            return 0;
        }
        if (length > 4096) {
            buf = new byte[4096];
        }
        int size;
        if (this.ap) {
            synchronized (ReadQueueLock) {
                size = this.f2878U.size();
                if (size > 0) {
                    if (length >= size) {
                        length = size;
                    }
                    while (i < length) {
                        Integer num = (Integer) this.f2878U.poll();
                        if (num == null) {
                            break;
                        }
                        buf[i] = (byte) (num.intValue() & 255);
                        i++;
                    }
                } else {
                    length = 0;
                }
            }
        } else {
            size = m2201a(buf, length);
            if (size <= 0) {
                length = 0;
            } else if (length >= size) {
                length = size;
            }
        }
        return length;
    }

    private int m2201a(byte[] bArr, int i) {
        int i2 = 0;
        if (bArr.length == 0 || i == 0) {
            return 0;
        }
        int bulkTransfer;
        if (this.f2874Q <= 0 || i > this.f2874Q) {
            if (this.f2874Q > 0) {
                i -= this.f2874Q;
                System.arraycopy(this.f2884c, this.f2873P, bArr, 0, this.f2874Q);
            }
            bulkTransfer = this.f2868K.bulkTransfer(this.f2870M, this.f2884c, this.f2884c.length, this.f2875R);
            if (bulkTransfer < 0) {
                return bulkTransfer;
            }
            if (bulkTransfer == 0) {
                return 0;
            }
            int i3;
            int i4 = bulkTransfer / 64;
            if (bulkTransfer % 64 > 0) {
                i4++;
            }
            this.f2874Q = bulkTransfer;
            int i5 = 0;
            bulkTransfer = 0;
            while (i5 < i4) {
                int i6 = i5 * 64;
                int i7 = bulkTransfer;
                bulkTransfer = 0;
                while (bulkTransfer < 64) {
                    i3 = i7 + 1;
                    this.f2884c[i7] = this.f2884c[i6 + bulkTransfer];
                    bulkTransfer++;
                    i7 = i3;
                }
                i5++;
                bulkTransfer = i7;
            }
            this.f2873P = 0;
            i4 = 0;
            while (this.f2874Q > 0 && i > 0) {
                bulkTransfer = i4 + 1;
                byte[] bArr2 = this.f2884c;
                i3 = this.f2873P;
                this.f2873P = i3 + 1;
                bArr[i4] = bArr2[i3];
                if (this.f2886e) {
                    this.f2881X++;
                    while ((this.f2881X - 1) % 10 != Byte.valueOf(bArr[bulkTransfer - 1]).byteValue() - 48) {
                        this.f2881X++;
                    }
                }
                this.f2874Q--;
                i--;
                i4 = bulkTransfer;
            }
            if (this.f2886e) {
                if (i4 > 0) {
                    this.f2882Y += i4;
                    this.f2883Z = true;
                }
                if (this.f2883Z) {
                    this.f2883Z = false;
                }
            }
            return i4;
        }
        if (this.f2886e) {
            while (i2 < i) {
                byte[] bArr3 = this.f2884c;
                bulkTransfer = this.f2873P;
                this.f2873P = bulkTransfer + 1;
                bArr[i2] = bArr3[bulkTransfer];
                this.f2881X++;
                while ((this.f2881X - 1) % 10 != Byte.valueOf(bArr[i2]).byteValue() - 48) {
                    this.f2881X++;
                }
                i2++;
            }
            this.f2882Y += i;
            this.f2883Z = true;
        } else {
            System.arraycopy(this.f2884c, this.f2873P, bArr, 0, i);
        }
        this.f2874Q -= i;
        return i;
    }

    public int write(byte[] buf) {
        return write(buf, buf.length);
    }

    public int write(byte[] buf, int wlength) {
        Object obj = new byte[4096];
        if (FlowControl.XONXOFF == this.aq && !this.ar) {
            return 0;
        }
        int i = 0;
        while (i < wlength) {
            int i2;
            if (i + 4096 > wlength) {
                i2 = wlength - i;
            } else {
                i2 = 4096;
            }
            System.arraycopy(buf, i, obj, 0, i2);
            i2 = this.f2868K.bulkTransfer(this.f2871N, obj, i2, this.f2876S);
            if (i2 < 0) {
                return -1;
            }
            i += i2;
        }
        return i;
    }

    public int setup(BaudRate R, DataBits D, StopBits S, Parity P, FlowControl F) throws IOException {
        if (this.f2868K == null) {
            return -1;
        }
        if (FlowControl.XONXOFF == this.aq && !this.ar) {
            return 0;
        }
        int controlTransfer = this.f2868K.controlTransfer(f2852p, 33, 0, 0, this.f2887j, 7, this.f2877T);
        if (controlTransfer < 0) {
            return controlTransfer;
        }
        switch (m2211a()[R.ordinal()]) {
            case 1:
                controlTransfer = 0;
                break;
            case 2:
                controlTransfer = 75;
                break;
            case 3:
                controlTransfer = BAUD150;
                break;
            case 4:
                controlTransfer = 300;
                break;
            case 5:
                controlTransfer = 600;
                break;
            case 6:
                controlTransfer = BAUD1200;
                break;
            case 7:
                controlTransfer = BAUD1800;
                break;
            case 8:
                controlTransfer = BAUD2400;
                break;
            case 9:
                controlTransfer = BAUD4800;
                break;
            case 10:
                controlTransfer = BAUD9600;
                break;
            case 11:
                controlTransfer = BAUD14400;
                break;
            case 12:
                controlTransfer = BAUD19200;
                break;
            case 13:
                controlTransfer = BAUD38400;
                break;
            case 14:
                controlTransfer = BAUD57600;
                break;
            case 15:
                controlTransfer = BAUD115200;
                break;
            case 16:
                controlTransfer = BAUD230400;
                break;
            case 17:
                controlTransfer = BAUD460800;
                break;
            case 18:
                controlTransfer = BAUD614400;
                break;
            case 19:
                controlTransfer = BAUD921600;
                break;
            case 20:
                controlTransfer = BAUD1228800;
                break;
            case 21:
                controlTransfer = BAUD2457600;
                break;
            case 22:
                controlTransfer = BAUD3000000;
                break;
            case 23:
                controlTransfer = BAUD6000000;
                break;
            case 24:
                controlTransfer = BAUD12000000;
                break;
            default:
                return -2;
        }
        if (controlTransfer > BAUD1228800 && this.ae == 0) {
            return -2;
        }
        if (this.f2879V != null) {
            m2207a(R);
        }
        this.f2887j[0] = (byte) (controlTransfer & 255);
        this.f2887j[1] = (byte) ((controlTransfer >> 8) & 255);
        this.f2887j[2] = (byte) ((controlTransfer >> 16) & 255);
        this.f2887j[3] = (byte) ((controlTransfer >> 24) & 255);
        switch (m2218b()[S.ordinal()]) {
            case 1:
                this.f2887j[4] = (byte) 0;
                break;
            case 2:
                this.f2887j[4] = (byte) 2;
                break;
            default:
                return -3;
        }
        switch (m2221c()[P.ordinal()]) {
            case 1:
                this.f2887j[5] = (byte) 0;
                break;
            case 2:
                this.f2887j[5] = (byte) 1;
                break;
            case 3:
                this.f2887j[5] = (byte) 2;
                break;
            default:
                return -4;
        }
        switch (m2225d()[D.ordinal()]) {
            case 1:
                this.f2887j[6] = (byte) 5;
                break;
            case 2:
                this.f2887j[6] = (byte) 6;
                break;
            case 3:
                this.f2887j[6] = (byte) 7;
                break;
            case 4:
                this.f2887j[6] = (byte) 8;
                break;
            default:
                return -5;
        }
        controlTransfer = this.f2868K.controlTransfer(33, 32, 0, 0, this.f2887j, 7, this.f2877T);
        if (controlTransfer < 0) {
            return controlTransfer;
        }
        controlTransfer = this.f2868K.controlTransfer(33, 35, 0, 0, null, 0, this.f2877T);
        if (controlTransfer < 0) {
            return controlTransfer;
        }
        switch (m2227e()[F.ordinal()]) {
            case 1:
                controlTransfer = this.f2868K.controlTransfer(64, 1, 0, 0, null, 0, this.f2877T);
                if (controlTransfer < 0) {
                    return controlTransfer;
                }
                controlTransfer = this.f2868K.controlTransfer(64, 1, 1, 0, null, 0, this.f2877T);
                if (controlTransfer < 0) {
                    return controlTransfer;
                }
                controlTransfer = this.f2868K.controlTransfer(64, 1, 2, 68, null, 0, this.f2877T);
                if (controlTransfer < 0) {
                    return controlTransfer;
                }
                break;
            case 2:
                controlTransfer = this.f2868K.controlTransfer(64, 1, 0, 97, null, 0, this.f2877T);
                if (controlTransfer < 0) {
                    return controlTransfer;
                }
                controlTransfer = this.f2868K.controlTransfer(64, 1, 1, 0, null, 0, this.f2877T);
                if (controlTransfer < 0) {
                    return controlTransfer;
                }
                controlTransfer = this.f2868K.controlTransfer(64, 1, 2, 68, null, 0, this.f2877T);
                if (controlTransfer < 0) {
                    return controlTransfer;
                }
                break;
            case 3:
                break;
            case 4:
                if (this.ae == 4) {
                    controlTransfer = this.f2868K.controlTransfer(64, 1, 0, 73, null, 0, this.f2877T);
                    if (controlTransfer < 0) {
                        return controlTransfer;
                    }
                    controlTransfer = this.f2868K.controlTransfer(64, 1, 1, 5, null, 0, this.f2877T);
                    if (controlTransfer < 0) {
                        return controlTransfer;
                    }
                    controlTransfer = this.f2868K.controlTransfer(64, 1, 2, 68, null, 0, this.f2877T);
                    if (controlTransfer < 0) {
                        return controlTransfer;
                    }
                }
                break;
            case 5:
                if (this.ae == 4) {
                    controlTransfer = this.f2868K.controlTransfer(64, 1, 0, 105, null, 0, this.f2877T);
                    if (controlTransfer < 0) {
                        return controlTransfer;
                    }
                    controlTransfer = this.f2868K.controlTransfer(64, 1, 1, 7, null, 0, this.f2877T);
                    if (controlTransfer < 0) {
                        return controlTransfer;
                    }
                    controlTransfer = this.f2868K.controlTransfer(64, 1, 2, 68, null, 0, this.f2877T);
                    if (controlTransfer < 0) {
                        return controlTransfer;
                    }
                }
                break;
            case 6:
                controlTransfer = this.f2868K.controlTransfer(64, 1, 0, 193, null, 0, this.f2877T);
                if (controlTransfer < 0) {
                    return controlTransfer;
                }
                controlTransfer = this.f2868K.controlTransfer(64, 1, 1, 0, null, 0, this.f2877T);
                if (controlTransfer < 0) {
                    return controlTransfer;
                }
                controlTransfer = this.f2868K.controlTransfer(64, 1, 2, 68, null, 0, this.f2877T);
                if (controlTransfer < 0) {
                    return controlTransfer;
                }
                break;
            default:
                return -6;
        }
        this.aq = F;
        if (this.aa) {
            controlTransfer = m2198a(0, 49);
            if (controlTransfer < 0) {
                return controlTransfer;
            }
            controlTransfer = m2198a(1, 8);
            if (controlTransfer < 0) {
                return controlTransfer;
            }
        }
        return 0;
    }

    public int setDTR(boolean state) {
        if (state && (this.f2863C & 1) != 1) {
            this.f2863C++;
        }
        if (!state && (this.f2863C & 1) == 1) {
            this.f2863C--;
        }
        int controlTransfer = this.f2868K.controlTransfer(33, 34, this.f2863C, 0, null, 0, this.f2877T);
        if (controlTransfer < 0) {
            return controlTransfer;
        }
        return 0;
    }

    public int setRTS(boolean state) {
        if (state && (this.f2863C & 2) != 2) {
            this.f2863C += 2;
        }
        if (!state && (this.f2863C & 2) == 2) {
            this.f2863C -= 2;
        }
        int controlTransfer = this.f2868K.controlTransfer(33, 34, this.f2863C, 0, null, 0, this.f2877T);
        if (controlTransfer < 0) {
            return controlTransfer;
        }
        return 0;
    }

    private int m2199a(UsbDeviceConnection usbDeviceConnection) {
        int i;
        int[] iArr = new int[2];
        iArr[0] = 0;
        if (this.ao) {
            this.ae = 4;
            i = 0;
        } else {
            int m;
            if (!m2235l()) {
                m = m2236m();
                if (m < 0) {
                    return m;
                }
            }
            if (usbDeviceConnection.getRawDescriptors()[13] == (byte) 4) {
                this.ae = 4;
            }
            m = m2233j();
            if (m < 0) {
                return m;
            }
            if (this.aE) {
                m = m2234k();
                if (m < 0) {
                    return m;
                }
            }
            if (usbDeviceConnection.getRawDescriptors()[13] == (byte) 5) {
                m = m2232i();
                if (m < 0) {
                    return m;
                }
            }
            i = m;
        }
        if (this.ae != 4 && this.ae != 6) {
            return -1;
        }
        int[] iArr2 = iArr;
        int i2 = 128;
        while (i2 <= 130) {
            int[] b = m2219b(i2);
            if (b[0] < 0) {
                return b[0];
            }
            i2++;
            iArr2 = b;
        }
        try {
            i = setup(BaudRate.B9600, DataBits.D8, StopBits.S1, Parity.NONE, FlowControl.OFF);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (iArr2[0] < 0) {
            return i;
        }
        return 0;
    }

    private int m2232i() {
        int[] iArr = new int[2];
        iArr = m2219b(148);
        if (iArr[0] < 0) {
            return iArr[0];
        }
        if ((iArr[1] & 148) == 148) {
            this.ae = 6;
            return 0;
        }
        this.ae = 2;
        return 0;
    }

    private int m2233j() {
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        int[] b = m2219b(129);
        if (b[0] < 0) {
            return b[0];
        }
        int i = b[1];
        b[0] = m2198a(1, 255);
        if (b[0] < 0) {
            return b[0];
        }
        iArr = m2219b(129);
        if (iArr[0] < 0) {
            return iArr[0];
        }
        if ((iArr[1] & 15) == 15) {
            this.ae = 4;
            iArr = m2222c((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            if (iArr[0] < 0) {
                return iArr[0];
            }
            iArr2[0] = iArr[1];
            iArr = m2222c(251);
            if (iArr[0] < 0) {
                return iArr[0];
            }
            iArr2[1] = iArr[1];
            if (iArr2[0] == 1 && iArr2[1] == 4) {
                this.ae = 2;
            } else if (!(iArr2[0] == 2 && iArr2[1] == 4) && (!(iArr2[0] == 3 && iArr2[1] == 4) && iArr2[0] == 1 && iArr2[1] == 3)) {
                this.ae = 2;
            }
        } else {
            this.ae = 2;
        }
        iArr[0] = m2198a(1, i);
        if (iArr[0] < 0) {
            return iArr[0];
        }
        return 0;
    }

    private String m2203a(int i) {
        return new String(new char[]{Character.forDigit((i >> 4) & 15, 16), Character.forDigit(i & 15, 16)});
    }

    private static String m2213b(byte[] bArr, int i) {
        StringBuffer stringBuffer;
        NoSuchAlgorithmException e;
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-512");
            instance.reset();
            byte[] digest = instance.digest(bArr);
            stringBuffer = new StringBuffer();
            try {
                int length = digest.length;
                for (int i2 = 0; i2 < length; i2++) {
                    stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(digest[i2])}));
                }
            } catch (NoSuchAlgorithmException e2) {
                e = e2;
            }
        } catch (NoSuchAlgorithmException e3) {
            NoSuchAlgorithmException noSuchAlgorithmException = e3;
            stringBuffer = null;
            e = noSuchAlgorithmException;
            e.printStackTrace();
            return stringBuffer.toString();
        }
        return stringBuffer.toString();
    }

    private int m2234k() {
        int[] iArr = new int[2];
        int[] c = m2222c(9);
        if (c[0] < 0) {
            return c[0];
        }
        if ((c[1] & 8) == 8) {
            c[0] = m2198a(0, 49);
            if (c[0] < 0) {
                return c[0];
            }
            c[0] = m2198a(1, 8);
            if (c[0] < 0) {
                return c[0];
            }
            this.aa = true;
        }
        return c[0];
    }

    public int PL2303TB_Set_PWM(int PWM_IO_Num, byte Frequency_value, byte Duty_value) {
        int[] iArr = new int[2];
        if (this.f2868K == null) {
            return -1;
        }
        int i;
        int i2 = ((Duty_value & 255) << 8) + Frequency_value;
        switch (PWM_IO_Num) {
            case 0:
                iArr[0] = m2198a(2, 0);
                if (iArr[0] >= 0) {
                    i = 16;
                    break;
                }
                return iArr[0];
            case 1:
                i = 17;
                break;
            case 2:
                i = 18;
                break;
            case 3:
                i = 19;
                break;
            default:
                return -1;
        }
        iArr[0] = m2198a(i, i2);
        if (iArr[0] < 0) {
            return iArr[0];
        }
        return 0;
    }

    public int PL2303TB_Enable_GPIO(int GPIO_Num, boolean Enable) {
        int[] iArr = new int[2];
        if (this.f2868K == null) {
            return -1;
        }
        if (GPIO_Num == 6 || GPIO_Num == 7 || GPIO_Num == 9) {
            iArr[0] = m2198a(2, 0);
            if (iArr[0] < 0) {
                return iArr[0];
            }
        }
        switch (GPIO_Num) {
            case 0:
                if (!Enable) {
                    this.aC &= -2;
                    break;
                }
                this.aC |= 1;
                break;
            case 1:
                if (!Enable) {
                    this.aC &= -3;
                    break;
                }
                this.aC |= 2;
                break;
            case 2:
                if (!Enable) {
                    this.aC &= -5;
                    break;
                }
                this.aC |= 4;
                break;
            case 3:
                if (!Enable) {
                    this.aC &= -9;
                    break;
                }
                this.aC |= 8;
                break;
            case 4:
                if (!Enable) {
                    this.aC &= -17;
                    break;
                }
                this.aC |= 16;
                break;
            case 5:
                if (!Enable) {
                    this.aC &= -33;
                    break;
                }
                this.aC |= 32;
                break;
            case 6:
                if (!Enable) {
                    this.aC &= -65;
                    break;
                }
                this.aC |= 64;
                break;
            case 7:
                if (!Enable) {
                    this.aC &= -129;
                    break;
                }
                this.aC |= 128;
                break;
            case 8:
                if (!Enable) {
                    this.aC &= -257;
                    break;
                }
                this.aC |= 256;
                break;
            case 9:
                if (!Enable) {
                    this.aC &= -513;
                    break;
                }
                this.aC |= 512;
                break;
            case 10:
                if (!Enable) {
                    this.aC &= -1025;
                    break;
                }
                this.aC |= 1024;
                break;
            case 11:
                if (!Enable) {
                    this.aC &= -2049;
                    break;
                }
                this.aC |= 2048;
                break;
            default:
                return -1;
        }
        iArr[0] = m2198a(14, this.aC);
        if (iArr[0] < 0) {
            return iArr[0];
        }
        return 0;
    }

    public int PL2303HXD_Enable_GPIO(int GPIO_Num, boolean Enable) {
        int[] iArr = new int[2];
        if (this.f2868K == null) {
            return -1;
        }
        int i;
        switch (GPIO_Num) {
            case 0:
                iArr = m2219b(129);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                if (Enable) {
                    i = iArr[1] | 16;
                    iArr[1] = i;
                } else {
                    i = iArr[1] & -17;
                    iArr[1] = i;
                }
                iArr[0] = m2198a(1, i);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                break;
            case 1:
                iArr = m2219b(129);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                if (Enable) {
                    i = iArr[1] | 32;
                    iArr[1] = i;
                } else {
                    i = iArr[1] & -33;
                    iArr[1] = i;
                }
                iArr[0] = m2198a(1, i);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                break;
            case 2:
                if (Enable) {
                    this.ax |= 3;
                } else {
                    this.ax &= -4;
                }
                iArr[0] = m2198a(12, this.ax);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                break;
            case 3:
                if (Enable) {
                    this.ax |= 12;
                } else {
                    this.ax &= -13;
                }
                iArr[0] = m2198a(12, this.ax);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                break;
            case 4:
                if (Enable) {
                    this.az |= 3;
                } else {
                    this.az &= -4;
                }
                iArr[0] = m2198a(6, this.az);
                break;
            case 5:
                if (Enable) {
                    this.az |= 12;
                } else {
                    this.az &= -13;
                }
                iArr[0] = m2198a(6, this.az);
                break;
            case 6:
                if (Enable) {
                    this.az |= 48;
                } else {
                    this.az &= -49;
                }
                iArr[0] = m2198a(6, this.az);
                break;
            case 7:
                if (Enable) {
                    this.az |= 192;
                } else {
                    this.az &= -193;
                }
                iArr[0] = m2198a(6, this.az);
                break;
            default:
                return -1;
        }
        return 0;
    }

    public int PL2303TB_Set_GPIO_Value(int GPIO_Num, int val) {
        int i = 1;
        int[] iArr = new int[2];
        if (this.f2868K == null) {
            return -1;
        }
        iArr = m2219b(143);
        if (iArr[0] < 0) {
            return iArr[0];
        }
        switch (GPIO_Num) {
            case 0:
                if (val != 1) {
                    i = 0;
                    break;
                }
                break;
            case 1:
                if (val != 1) {
                    i = 0;
                    break;
                }
                i = 2;
                break;
            case 2:
                if (val != 1) {
                    i = 0;
                    break;
                }
                i = 4;
                break;
            case 3:
                if (val != 1) {
                    i = 0;
                    break;
                }
                i = 4;
                break;
            case 4:
                if (val != 1) {
                    i = 0;
                    break;
                }
                i = 16;
                break;
            case 5:
                if (val != 1) {
                    i = 0;
                    break;
                }
                i = 32;
                break;
            case 6:
                if (val != 1) {
                    i = 0;
                    break;
                }
                i = 64;
                break;
            case 7:
                if (val != 1) {
                    i = 0;
                    break;
                }
                i = 128;
                break;
            case 8:
                if (val != 1) {
                    i = 0;
                    break;
                }
                i = 256;
                break;
            case 9:
                if (val != 1) {
                    i = 0;
                    break;
                }
                i = 512;
                break;
            case 10:
                if (val != 1) {
                    i = 0;
                    break;
                }
                i = 1024;
                break;
            case 11:
                if (val != 1) {
                    i = 0;
                    break;
                }
                i = 2048;
                break;
            default:
                return -1;
        }
        iArr[0] = m2198a(15, i);
        if (iArr[0] < 0) {
            return iArr[0];
        }
        return 0;
    }

    public int PL2303HXD_Set_GPIO_Value(int GPIO_Num, int val) {
        int[] iArr = new int[2];
        if (this.f2868K == null) {
            return -1;
        }
        int i;
        switch (GPIO_Num) {
            case 0:
                iArr = m2219b(129);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                if (val == 1) {
                    i = iArr[1] | 64;
                    iArr[1] = i;
                } else {
                    i = iArr[1] & -65;
                    iArr[1] = i;
                }
                iArr[0] = m2198a(1, i);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                break;
            case 1:
                iArr = m2219b(129);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                if (val == 1) {
                    i = iArr[1] | 128;
                    iArr[1] = i;
                } else {
                    i = iArr[1] & -129;
                    iArr[1] = i;
                }
                iArr[0] = m2198a(1, i);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                break;
            case 2:
                iArr = m2219b(141);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                if (val == 1) {
                    i = iArr[1] | 1;
                    iArr[1] = i;
                } else {
                    i = iArr[1] & -2;
                    iArr[1] = i;
                }
                iArr[0] = m2198a(13, i);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                break;
            case 3:
                iArr = m2219b(141);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                if (val == 1) {
                    i = iArr[1] | 2;
                    iArr[1] = i;
                } else {
                    i = iArr[1] & -3;
                    iArr[1] = i;
                }
                iArr[0] = m2198a(13, i);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                break;
            case 4:
                if (this.aB == 0) {
                    this.aA = 0;
                } else {
                    this.aA = this.aB;
                }
                if (val == 1) {
                    this.aA |= 1;
                } else {
                    this.aA &= -2;
                }
                this.aB = this.aA;
                iArr[0] = m2198a(7, this.aA);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                break;
            case 5:
                if (this.aB == 0) {
                    this.aA = 0;
                } else {
                    this.aA = this.aB;
                }
                if (val == 1) {
                    this.aA |= 2;
                } else {
                    this.aA &= -3;
                }
                this.aB = this.aA;
                iArr[0] = m2198a(7, this.aA);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                break;
            case 6:
                if (this.aB == 0) {
                    this.aA = 0;
                } else {
                    this.aA = this.aB;
                }
                if (val == 1) {
                    this.aA |= 4;
                } else {
                    this.aA &= -5;
                }
                this.aB = this.aA;
                iArr[0] = m2198a(7, this.aA);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                break;
            case 7:
                if (this.aB == 0) {
                    this.aA = 0;
                } else {
                    this.aA = this.aB;
                }
                if (val == 1) {
                    this.aA |= 8;
                } else {
                    this.aA &= -9;
                }
                this.aB = this.aA;
                iArr[0] = m2198a(7, this.aA);
                if (iArr[0] < 0) {
                    return iArr[0];
                }
                break;
            default:
                return -1;
        }
        return 0;
    }

    public int[] PL2303TB_Get_GPIO_Value(int GPIO_Num) {
        int[] iArr = new int[2];
        if (this.f2868K != null) {
            iArr = m2219b(143);
            if (iArr[0] >= 0) {
                switch (GPIO_Num) {
                    case 0:
                        if ((iArr[1] & 1) != 1) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    case 1:
                        if ((iArr[1] & 2) != 2) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    case 2:
                        if ((iArr[1] & 4) != 4) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    case 3:
                        if ((iArr[1] & 8) != 8) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    case 4:
                        if ((iArr[1] & 16) != 16) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    case 5:
                        if ((iArr[1] & 32) != 32) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    case 6:
                        if ((iArr[1] & 64) != 64) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    case 7:
                        if ((iArr[1] & 128) != 128) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    case 8:
                        if ((iArr[1] & 256) != 256) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    case 9:
                        if ((iArr[1] & 512) != 512) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    case 10:
                        if ((iArr[1] & 1024) != 1024) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    case 11:
                        if ((iArr[1] & 2048) != 2048) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    default:
                        iArr[0] = -1;
                        break;
                }
            }
        }
        iArr[0] = -1;
        return iArr;
    }

    public int[] PL2303HXD_Get_GPIO_Value(int GPIO_Num) {
        int[] iArr = new int[2];
        if (this.f2868K != null) {
            switch (GPIO_Num) {
                case 0:
                    iArr = m2219b(129);
                    if (iArr[0] >= 0) {
                        if ((iArr[1] & 64) != 64) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    }
                    break;
                case 1:
                    iArr = m2219b(129);
                    if (iArr[0] >= 0) {
                        if ((iArr[1] & 128) != 128) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    }
                    break;
                case 2:
                    iArr = m2219b(141);
                    if (iArr[0] >= 0) {
                        if ((iArr[1] & 1) != 1) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    }
                    break;
                case 3:
                    iArr = m2219b(141);
                    if (iArr[0] >= 0) {
                        if ((iArr[1] & 2) != 2) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    }
                    break;
                case 4:
                    iArr = m2219b(135);
                    if (iArr[0] >= 0) {
                        if ((iArr[1] & 1) != 1) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    }
                    break;
                case 5:
                    iArr = m2219b(135);
                    if (iArr[0] >= 0) {
                        if ((iArr[1] & 2) != 2) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    }
                    break;
                case 6:
                    iArr = m2219b(135);
                    if (iArr[0] >= 0) {
                        if ((iArr[1] & 4) != 4) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    }
                    break;
                case 7:
                    iArr = m2219b(135);
                    if (iArr[0] >= 0) {
                        if ((iArr[1] & 8) != 8) {
                            iArr[1] = 0;
                            break;
                        }
                        iArr[1] = 1;
                        break;
                    }
                    break;
                default:
                    iArr[0] = -1;
                    break;
            }
        }
        iArr[0] = -1;
        return iArr;
    }

    private int m2198a(int i, int i2) {
        if (this.f2868K == null) {
            return -1;
        }
        int controlTransfer = this.f2868K.controlTransfer(64, 1, i, i2, null, 0, this.f2877T);
        return controlTransfer < 0 ? controlTransfer : controlTransfer;
    }

    private int[] m2219b(int i) {
        r8 = new int[2];
        byte[] bArr = new byte[]{0};
        if (this.f2868K == null) {
            r8[0] = -1;
            return r8;
        }
        r8[0] = this.f2868K.controlTransfer(192, 1, i, 0, bArr, 1, this.f2877T);
        if (r8[0] < 0) {
            return r8;
        }
        r8[1] = bArr[0];
        return r8;
    }

    private int[] m2222c(int i) {
        new int[2][0] = 0;
        int[] b = m2219b(132);
        if (b[0] >= 0) {
            b[0] = m2198a(4, i);
            if (b[0] >= 0) {
                b = m2219b(132);
                if (b[0] >= 0) {
                    b = m2219b(131);
                    if (b[0] < 0) {
                    }
                }
            }
        }
        return b;
    }

    private boolean m2235l() {
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        for (int i = 0; i < 2; i++) {
            int[] c = m2222c(i);
            if (c[0] < 0) {
                return this.aE;
            }
            iArr2[i] = c[1];
        }
        if (iArr2[0] == 123 && iArr2[1] == 6) {
            this.aE = true;
        }
        return this.aE;
    }

    private int m2236m() {
        int[] iArr = new int[3];
        iArr[0] = 1;
        iArr[2] = 68;
        int[] iArr2 = new int[2];
        iArr2[0] = m2198a((int) f2836A, 0);
        if (iArr2[0] < 0) {
            return iArr2[0];
        }
        iArr2[0] = m2198a((int) f2837B, 0);
        if (iArr2[0] < 0) {
            return iArr2[0];
        }
        int i;
        for (i = 0; i <= 2; i++) {
            iArr2[0] = m2198a(i, iArr[i]);
            if (iArr2[0] < 0) {
                return iArr2[0];
            }
        }
        for (i = 128; i <= 130; i++) {
            iArr2 = m2219b(i);
            if (iArr2[0] < 0) {
                return iArr2[0];
            }
        }
        return iArr2[0];
    }

    private int m2223d(int i) {
        byte[] bArr = new byte[2];
        byte[] bArr2 = new byte[2];
        if (this.f2868K == null) {
            return -1;
        }
        bArr2[0] = (byte) (i & 255);
        bArr2[1] = (byte) ((i >> 8) & 255);
        int controlTransfer = this.f2868K.controlTransfer(f2852p, 32, 0, 0, bArr2, 2, this.f2876S);
        if (controlTransfer < 0) {
            return controlTransfer;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.f2868K.bulkTransfer(this.f2872O, bArr, bArr.length, this.f2875R) >= 0) {
            return (bArr[1] << 8) | bArr[0];
        }
        return 0;
    }

    private int[] m2237n() {
        new int[2][0] = 0;
        int[] b = m2219b(135);
        return b[0] < 0 ? b : b;
    }

    public int[] PL2303HXD_GetCommModemStatus() {
        int i = 0;
        int[] iArr = new int[2];
        iArr = m2237n();
        if (iArr[0] < 0) {
            return iArr;
        }
        if ((iArr[1] & 1) != 1) {
            i = 8;
        }
        if ((iArr[1] & 2) == 2) {
            i &= -2;
        } else {
            i |= 1;
        }
        if ((iArr[1] & 4) == 4) {
            i &= -3;
        } else {
            i |= 2;
        }
        if ((iArr[1] & 8) == 8) {
            i &= -129;
        } else {
            i |= 128;
        }
        iArr[1] = i;
        return iArr;
    }

    public void PL2303LibGetVersion(byte[] byVersion) {
        int length;
        if (byVersion.length < f2846i.length()) {
            length = byVersion.length;
        } else {
            length = f2846i.length();
        }
        char[] toCharArray = f2846i.toCharArray();
        for (int i = 0; i < length; i++) {
            byVersion[i] = (byte) toCharArray[i];
        }
    }

    public boolean PL2303USBFeatureSupported() {
        return this.f2885d.getPackageManager().hasSystemFeature("android.hardware.usb.host");
    }

    public String PL2303Device_GetSerialNumber() {
        if (isConnected()) {
            return this.f2868K.getSerial();
        }
        return null;
    }

    public boolean PL2303Device_IsHasPermission() {
        return this.an;
    }

    public boolean PL2303Device_IsSupportChip() {
        if (this.ae == 4) {
            return true;
        }
        return false;
    }

    public boolean PL2303Device_SetCommTimeouts(int TimeoutConstant) {
        if (TimeoutConstant < 0) {
            return false;
        }
        this.f2875R = TimeoutConstant;
        this.f2876S = TimeoutConstant;
        return true;
    }

    public boolean PL2303Device_GetCommTimeouts(int TimeoutConstant) {
        TimeoutConstant = this.f2876S;
        return true;
    }

    private static void m2206a(Object obj) {
    }
}
