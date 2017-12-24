package com.ihealth.communication.base.usb;

import android.content.Context;
import com.ftdi.j2xx.D2xxManager;
import com.ftdi.j2xx.D2xxManager.D2xxException;
import com.ftdi.j2xx.FT_Device;
import com.google.common.base.Ascii;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.util.LinkedList;
import java.util.Queue;
import tw.com.prolific.driver.pl2303.PL2303Driver;

public class FtdiUsb implements BaseComm {
    public static final int readLength = 512;
    public boolean READ_ENABLE = false;
    int f421a = PL2303Driver.BAUD57600;
    public boolean accessory_attached = false;
    byte f422b = (byte) 1;
    public boolean bReadThreadGoing = false;
    byte f423c = (byte) 8;
    byte f424d = (byte) 0;
    public boolean datareceived = false;
    byte f425e = (byte) 0;
    int f426f = 1;
    private D2xxManager f427g = null;
    public Context global_context;
    private FT_Device f428h = null;
    private int f429i = -1;
    public int iavailable = 0;
    private int f430j = -1;
    private int f431k;
    private BaseCommProtocol f432l;
    private Context f433m;
    private byte[] f434n = new byte[512];
    private int f435o = 0;
    private Queue f436p = new LinkedList();
    public byte[] readBuffer;
    public int readBufferlen;
    public C2038a readthread;
    public boolean uart_configured = false;

    public FtdiUsb(Context context) {
        this.f433m = context;
        this.global_context = context;
        try {
            this.f427g = D2xxManager.getInstance(this.global_context);
        } catch (D2xxException e) {
            e.printStackTrace();
        }
        m326a();
    }

    private void m326a() {
        if (!this.f427g.setVIDPID(1027, 44449)) {
            Log.w("ftd2xx-java", "setVIDPID Error");
        }
    }

    private void m328a(byte[] bArr, int i) {
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            try {
                if (bArr[i3] == (byte) -96) {
                    i4++;
                }
                this.f436p.offer(Byte.valueOf(bArr[i3]));
                i3++;
            } catch (Exception e) {
                Log.w("uartInterface", "uncaughtException:" + e.toString());
                return;
            }
        }
        while (i2 <= i4) {
            m329b();
            i2++;
        }
    }

    private void m329b() {
        if (this.f432l == null) {
            Log.e("uartInterface", "commProtocol is null and clear readDataQueue");
            this.f436p.clear();
        } else if (6 > this.f436p.size()) {
            Log.w("uartInterface", "command length is not wrong");
        } else {
            if (160 == (((Byte) this.f436p.peek()).byteValue() & 255)) {
                this.f436p.poll();
            }
            int byteValue = ((Byte) this.f436p.peek()).byteValue() & 255;
            int i = byteValue + 3;
            if (this.f436p.size() >= byteValue + 2) {
                byte[] bArr = new byte[i];
                bArr[0] = (byte) -96;
                for (int i2 = 1; i2 < i; i2++) {
                    Byte valueOf = Byte.valueOf(((Byte) this.f436p.poll()).byteValue());
                    if (valueOf != null) {
                        bArr[i2] = valueOf.byteValue();
                    }
                }
                if (bArr.length > 3) {
                    byteValue = bArr[3] & 255;
                    if (this.f431k != byteValue) {
                        this.f431k = byteValue;
                        this.readBuffer = new byte[bArr.length];
                        this.readBufferlen = bArr.length;
                        for (byteValue = 0; byteValue < bArr.length; byteValue++) {
                            this.readBuffer[byteValue] = bArr[byteValue];
                        }
                        this.f432l.unPackageData(this.readBuffer);
                        return;
                    }
                    Log.v("uartInterface", "Duplicate command");
                    return;
                }
                return;
            }
            Log.w("uartInterface", "This is not full command");
        }
    }

    public void SetConfig(int baud, byte dataBits, byte stopBits, byte parity, byte flowControl) {
        short s = (short) 0;
        if (this.f428h.isOpen()) {
            this.f428h.setBitMode((byte) 0, (byte) 0);
            this.f428h.setBaudRate(baud);
            switch (dataBits) {
                case (byte) 7:
                    dataBits = (byte) 7;
                    break;
                case (byte) 8:
                    dataBits = (byte) 8;
                    break;
                default:
                    dataBits = (byte) 8;
                    break;
            }
            switch (stopBits) {
                case (byte) 1:
                    stopBits = (byte) 0;
                    break;
                case (byte) 2:
                    stopBits = (byte) 2;
                    break;
                default:
                    stopBits = (byte) 0;
                    break;
            }
            switch (parity) {
                case (byte) 0:
                    parity = (byte) 0;
                    break;
                case (byte) 1:
                    parity = (byte) 1;
                    break;
                case (byte) 2:
                    parity = (byte) 2;
                    break;
                case (byte) 3:
                    parity = (byte) 3;
                    break;
                case (byte) 4:
                    parity = (byte) 4;
                    break;
                default:
                    parity = (byte) 0;
                    break;
            }
            this.f428h.setDataCharacteristics(dataBits, stopBits, parity);
            switch (flowControl) {
                case (byte) 1:
                    s = D2xxManager.FT_FLOW_RTS_CTS;
                    break;
                case (byte) 2:
                    s = D2xxManager.FT_FLOW_DTR_DSR;
                    break;
                case (byte) 3:
                    s = D2xxManager.FT_FLOW_XON_XOFF;
                    break;
            }
            this.f428h.setFlowControl(s, Ascii.VT, (byte) 13);
            this.uart_configured = true;
        }
    }

    public void addCommContinueNotify(String mac, BaseCommProtocol dataCallBack) {
    }

    public void addCommNotify(BaseCommProtocol dataCallBack) {
        this.f432l = dataCallBack;
    }

    public void addCommNotify(String mac, BaseCommProtocol dataCallBack) {
    }

    public void connectFunction() {
        int i = this.f435o + 1;
        if (this.f430j != this.f435o) {
            if (this.f428h == null) {
                this.f428h = this.f427g.openByIndex(this.f433m, this.f435o);
            } else {
                synchronized (this.f428h) {
                    this.f428h = this.f427g.openByIndex(this.f433m, this.f435o);
                }
            }
            this.uart_configured = false;
            if (this.f428h == null) {
                Log.w("usb", "open device port(" + i + ") NG, ftDev == null");
                return;
            } else if (true == this.f428h.isOpen()) {
                this.f430j = this.f435o;
                Log.v("usb", "open device port(" + i + ") OK");
                if (!this.bReadThreadGoing) {
                    this.readthread = new C2038a(this);
                    this.readthread.start();
                    this.bReadThreadGoing = true;
                    return;
                }
                return;
            } else {
                Log.w("usb", "open device port(" + i + ") NG");
                return;
            }
        }
        Log.w("usb", "Device port " + i + " is already opened");
    }

    public int createDeviceList() {
        if (this.f427g == null) {
            return this.f429i;
        }
        int createDeviceInfoList = this.f427g.createDeviceInfoList(this.f433m);
        if (createDeviceInfoList <= 0) {
            Log.w("uartInterface", "createDeviceInfoList -- fail");
            this.f429i = -1;
            this.f430j = -1;
        } else if (this.f429i != createDeviceInfoList) {
            this.f429i = createDeviceInfoList;
        }
        return this.f429i;
    }

    public void disConnectFunction() {
        if (this.f428h != null && true == this.f428h.isOpen()) {
            this.f428h.close();
        }
    }

    public void disconnect() {
        Log.w("uartInterface", "disconnect--FtdiUsb");
        disConnectFunction();
    }

    public void disconnect(String mac) {
    }

    public Context getContext() {
        return this.f433m;
    }

    public void removeCommNotify(BaseCommProtocol dataCallBack) {
    }

    public void removeCommNotify(String mac) {
    }

    public void sendData(String mac, String deviceIP, byte[] data) {
    }

    public void sendData(String mac, byte[] data) {
        if (this.f428h == null) {
            Log.w("uartInterface", "ftDev is null");
            return;
        }
        Log.p("uartInterface", Level.VERBOSE, "sendData", new Object[]{mac, ByteBufferUtil.Bytes2HexString(data, data.length)});
        this.f428h.setLatencyTimer((byte) 16);
        this.f428h.write(data, data.length);
    }
}
