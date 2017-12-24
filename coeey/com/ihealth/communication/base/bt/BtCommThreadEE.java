package com.ihealth.communication.base.bt;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

public class BtCommThreadEE extends Thread implements BaseComm {
    Timer f333a = new Timer();
    TimerTask f334b;
    private boolean f335c = true;
    private BluetoothDevice f336d = null;
    private BluetoothSocket f337e = null;
    private InputStream f338f = null;
    private OutputStream f339g = null;
    private int f340h;
    private Context f341i;
    private byte[] f342j;
    private BaseCommCallback f343k;
    private boolean f344l = false;

    public BtCommThreadEE(Context context, BluetoothDevice device, BluetoothSocket socket, BaseCommCallback baseCommCallback) {
        this.f341i = context;
        this.f336d = device;
        this.f337e = socket;
        this.f338f = socket.getInputStream();
        this.f339g = socket.getOutputStream();
        this.f342j = new byte[256];
        this.f343k = baseCommCallback;
    }

    public void addCommContinueNotify(String mac, BaseCommProtocol dataCallBack) {
    }

    public void addCommNotify(BaseCommProtocol dataCallBack) {
    }

    public void addCommNotify(String mac, BaseCommProtocol dataCallBack) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
        r2 = this;
        r1 = 0;
        r0 = r2.f337e;	 Catch:{ IOException -> 0x0010 }
        if (r0 == 0) goto L_0x000a;
    L_0x0005:
        r0 = r2.f337e;	 Catch:{ IOException -> 0x0010 }
        r0.close();	 Catch:{ IOException -> 0x0010 }
    L_0x000a:
        r0 = 0;
        r2.f337e = r0;	 Catch:{ IOException -> 0x0010 }
        r2.f337e = r1;
    L_0x000f:
        return;
    L_0x0010:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0017 }
        r2.f337e = r1;
        goto L_0x000f;
    L_0x0017:
        r0 = move-exception;
        r2.f337e = r1;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ihealth.communication.base.bt.BtCommThreadEE.close():void");
    }

    public void disconnect() {
        close();
    }

    public void disconnect(String mac) {
        close();
    }

    public Context getContext() {
        return this.f341i;
    }

    public void removeCommNotify(BaseCommProtocol dataCallBack) {
    }

    public void removeCommNotify(String mac) {
    }

    public void run() {
        while (true) {
            try {
                this.f340h = this.f338f.read(this.f342j);
                String str = "000";
                if (this.f340h > 0) {
                    if (this.f334b != null) {
                        this.f334b.cancel();
                        this.f334b = null;
                    }
                    Log.p("BtCommThreadEE", Level.VERBOSE, "Read", new Object[]{ByteBufferUtil.Bytes2HexString(this.f342j, this.f340h)});
                    if (this.f340h > 19) {
                        str = String.format("%c.%c.%c", new Object[]{Byte.valueOf(this.f342j[14]), Byte.valueOf(this.f342j[16]), Byte.valueOf(this.f342j[18])});
                    }
                }
                this.f344l = true;
                Intent intent = new Intent(iHealthDevicesManager.IHEALTH_MSG_BG5_EE);
                intent.putExtra(iHealthDevicesManager.IHEALTH_DEVICE_MAC, this.f336d.getAddress().replace(":", ""));
                intent.putExtra("name", this.f336d.getName());
                intent.putExtra(iHealthDevicesManager.IHEALTH_MSG_BG5_EE_EXTRA, str);
                intent.putExtra("android.bluetooth.device.extra.DEVICE", this.f336d);
                intent.setPackage(this.f341i.getPackageName());
                this.f341i.sendBroadcast(intent);
            } catch (IOException e) {
                Log.w("BtCommThreadEE", "Read() -- Exception: " + e);
                if (!this.f344l) {
                    this.f343k.onConnectionStateChange(this.f336d.getAddress().replace(":", ""), this.f336d.getName(), 3, 0, null);
                    return;
                }
                return;
            }
        }
    }

    public void sendData(String mac, String deviceIP, byte[] data) {
    }

    public void sendData(String mac, byte[] data) {
        try {
            Log.p("BtCommThreadEE", Level.VERBOSE, "sendData", new Object[]{mac, ByteBufferUtil.Bytes2HexString(data, data.length)});
            this.f339g.write(data);
            this.f339g.flush();
            this.f334b = new C2033a(this);
            this.f333a.schedule(this.f334b, 2000);
        } catch (IOException e) {
            Log.w("BtCommThreadEE", "sendData() -- IOException: " + e);
        }
    }
}
