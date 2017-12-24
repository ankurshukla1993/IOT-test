package com.ihealth.communication.base.wifi;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class WifiCommThread extends Thread implements BaseComm {
    private static boolean f454b = true;
    public static boolean stopFlag = false;
    private DatagramSocket f455a;
    private byte[] f456c = new byte[256];
    private byte[] f457d = new byte[256];
    private WifiUnpackageData f458e;
    private InetAddress f459f;
    private Context f460g;

    public WifiCommThread(UdpSearchCallback udpSearchCallback, Context context, DatagramSocket udpSocket, WifiManager wifiManager) {
        this.f460g = context;
        this.f455a = udpSocket;
        this.f458e = new WifiUnpackageData(udpSearchCallback);
    }

    private byte[] m337a(byte[] bArr) {
        int i = (bArr[1] & 255) + 3;
        byte[] bArr2 = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr2[i2] = bArr[i2];
        }
        return bArr2;
    }

    public void addCommContinueNotify(String mac, BaseCommProtocol dataCallBack) {
    }

    public void addCommNotify(BaseCommProtocol dataCallBack) {
    }

    public void addCommNotify(String mac, BaseCommProtocol dataCallBack) {
        this.f458e.addCommNotify(mac, dataCallBack);
    }

    public void disconnect() {
    }

    public void disconnect(String mac) {
    }

    public Context getContext() {
        return this.f460g;
    }

    public void removeCommNotify(BaseCommProtocol dataCallBack) {
    }

    public void removeCommNotify(String mac) {
    }

    public void run() {
        super.run();
        while (!stopFlag) {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(this.f456c, this.f456c.length);
                this.f455a.receive(datagramPacket);
                this.f457d = datagramPacket.getData();
                byte[] a = m337a(this.f457d);
                if (a.length > 0) {
                    Log.p("WifiCommThread------", Level.VERBOSE, "Read", new Object[]{ByteBufferUtil.Bytes2HexString(a, a.length)});
                    this.f458e.addReadData(a, a.length);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void sendData(String mac, String deviceIP, byte[] data) {
        try {
            this.f455a.setBroadcast(true);
            this.f459f = InetAddress.getByName(deviceIP);
            Log.p("WifiCommThread------", Level.VERBOSE, "sendData", new Object[]{mac, deviceIP, ByteBufferUtil.Bytes2HexString(data, data.length)});
            this.f455a.send(new DatagramPacket(data, data.length, this.f459f, AbstractSpiCall.DEFAULT_TIMEOUT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendData(String mac, byte[] data) {
    }
}
