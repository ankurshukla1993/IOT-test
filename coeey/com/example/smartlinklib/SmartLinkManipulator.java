package com.example.smartlinklib;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

public class SmartLinkManipulator {
    public static final int DEVICE_COUNT_MULTIPLE = -1;
    public static final int DEVICE_COUNT_ONE = 1;
    private static SmartLinkManipulator me = null;
    private int CONTENT_CHECKSUM_BEFORE_DELAY_TIME;
    private int CONTENT_COUNT;
    private int CONTENT_GROUP_DELAY_TIME;
    private int CONTENT_PACKAGE_DELAY_TIME;
    private int HEADER_CAPACITY;
    private int HEADER_COUNT;
    private int HEADER_PACKAGE_DELAY_TIME;
    private final String RET_KEY;
    private String TAG;
    private String broadCastIP;
    private ConnectCallBack callback;
    private DatagramPacket dataPacket;
    Runnable findThread;
    private String gateWay;
    private InetAddress inetAddressbroadcast;
    public boolean isConnecting;
    private boolean isfinding;
    private DatagramPacket packetToSendbroadcast;
    private DatagramPacket packetToSendgateway;
    private int port;
    private String pswd;
    private byte[] receiveByte;
    private DatagramSocket socket;
    private String ssid;
    private Set<String> successMacSet;

    public interface ConnectCallBack {
        void onConnect(ModuleInfo moduleInfo);

        void onConnectOk();

        void onConnectTimeOut();
    }

    class C10741 implements Runnable {
        C10741() {
        }

        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }
            for (int i = 0; i < 20 && SmartLinkManipulator.this.isConnecting; i++) {
                SmartLinkManipulator.this.sendFindCmd();
                if (SmartLinkManipulator.this.isConnecting) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e2) {
                    }
                }
            }
            if (SmartLinkManipulator.this.isConnecting) {
                if (SmartLinkManipulator.this.successMacSet.size() <= 0) {
                    SmartLinkManipulator.this.callback.onConnectTimeOut();
                } else if (SmartLinkManipulator.this.successMacSet.size() > 0) {
                    SmartLinkManipulator.this.callback.onConnectOk();
                }
            }
            Log.e(SmartLinkManipulator.this.TAG, "stop find");
            SmartLinkManipulator.this.isfinding = false;
            SmartLinkManipulator.this.StopConnection();
        }
    }

    class C10752 implements Runnable {
        C10752() {
        }

        public void run() {
            while (SmartLinkManipulator.this.isConnecting) {
                SmartLinkManipulator.this.connect();
            }
            Log.e(SmartLinkManipulator.this.TAG, "StopConnet");
            SmartLinkManipulator.this.StopConnection();
        }
    }

    class C10763 extends Thread {
        C10763() {
        }

        public void run() {
            while (SmartLinkManipulator.this.isConnecting) {
                try {
                    SmartLinkManipulator.this.socket.receive(SmartLinkManipulator.this.dataPacket);
                    int len = SmartLinkManipulator.this.dataPacket.getLength();
                    if (len > 0) {
                        String receiveStr = new String(SmartLinkManipulator.this.receiveByte, 0, len, "UTF-8");
                        if (receiveStr.contains("smart_config")) {
                            Log.e("RECV", "smart_config");
                            ModuleInfo mi = new ModuleInfo();
                            mi.setMac(receiveStr.replace("smart_config", "").trim());
                            String ip = SmartLinkManipulator.this.dataPacket.getAddress().getHostAddress();
                            if (!ip.equalsIgnoreCase("0.0.0.0") && !ip.contains(":")) {
                                mi.setModuleIP(ip);
                                if (!SmartLinkManipulator.this.successMacSet.contains(mi.getMac())) {
                                    SmartLinkManipulator.this.successMacSet.add(mi.getMac());
                                    SmartLinkManipulator.this.callback.onConnect(mi);
                                }
                            } else {
                                return;
                            }
                        }
                        continue;
                    } else {
                        continue;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Log.e(SmartLinkManipulator.this.TAG, "end RECV");
            SmartLinkManipulator.this.StopConnection();
        }
    }

    private SmartLinkManipulator() {
        this.TAG = "HFdebug";
        this.successMacSet = new HashSet();
        this.HEADER_COUNT = 200;
        this.HEADER_PACKAGE_DELAY_TIME = 10;
        this.HEADER_CAPACITY = 76;
        this.CONTENT_COUNT = 5;
        this.CONTENT_PACKAGE_DELAY_TIME = 50;
        this.CONTENT_CHECKSUM_BEFORE_DELAY_TIME = 100;
        this.CONTENT_GROUP_DELAY_TIME = 500;
        this.RET_KEY = "smart_config";
        this.port = 49999;
        this.receiveByte = new byte[512];
        this.isConnecting = false;
        this.isfinding = false;
        this.findThread = new C10741();
        this.isConnecting = false;
        this.isfinding = false;
    }

    public static SmartLinkManipulator getInstence() {
        if (me == null) {
            me = new SmartLinkManipulator();
        }
        return me;
    }

    public void setConnection(String ssid, String password, Context ctx) throws SocketException, UnknownHostException {
        Log.e(this.TAG, new StringBuilder(String.valueOf(ssid)).append(":").append(password).toString());
        this.ssid = ssid;
        this.pswd = password;
        this.broadCastIP = getBroadcastAddress(ctx);
        this.socket = new DatagramSocket(this.port);
        this.socket.setBroadcast(true);
        this.inetAddressbroadcast = InetAddress.getByName(this.broadCastIP);
    }

    public void Startconnection(ConnectCallBack callback) {
        Log.e(this.TAG, "Startconnection");
        this.callback = callback;
        this.isConnecting = true;
        receive();
        this.successMacSet.clear();
        new Thread(new C10752()).start();
        if (!this.isfinding) {
            this.isfinding = true;
            new Thread(this.findThread).start();
        }
    }

    public void StopConnection() {
        this.isConnecting = false;
        if (this.socket != null) {
            this.socket.close();
        }
    }

    public String getBroadcastAddress(Context ctx) {
        DhcpInfo myDhcpInfo = ((WifiManager) ctx.getSystemService("wifi")).getDhcpInfo();
        if (myDhcpInfo == null) {
            return "255.255.255.255";
        }
        int broadcast = (myDhcpInfo.ipAddress & myDhcpInfo.netmask) | (myDhcpInfo.netmask ^ -1);
        byte[] quads = new byte[4];
        for (int k = 0; k < 4; k++) {
            quads[k] = (byte) ((broadcast >> (k * 8)) & 255);
        }
        try {
            return InetAddress.getByAddress(quads).getHostAddress();
        } catch (Exception e) {
            return "255.255.255.255";
        }
    }

    private void connect() {
        int count;
        int i;
        Log.e(this.TAG, "connect");
        byte[] header = getBytes(this.HEADER_CAPACITY);
        for (count = 1; count <= this.HEADER_COUNT && this.isConnecting; count++) {
            send(header);
            try {
                Thread.sleep((long) this.HEADER_PACKAGE_DELAY_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String pwd = this.pswd;
        int[] content = new int[(pwd.length() + 2)];
        content[0] = 89;
        int j = 1;
        for (i = 0; i < pwd.length(); i++) {
            content[j] = pwd.charAt(i) + 76;
            j++;
        }
        content[content.length - 1] = 86;
        for (count = 1; count <= this.CONTENT_COUNT && this.isConnecting; count++) {
            i = 0;
            while (i < content.length) {
                int t;
                int _count = 1;
                if (i == 0 || i == content.length - 1) {
                    _count = 3;
                }
                for (t = 1; t <= _count && this.isConnecting; t++) {
                    send(getBytes(content[i]));
                    if (i != content.length) {
                        try {
                            Thread.sleep((long) this.CONTENT_PACKAGE_DELAY_TIME);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                if (i != content.length) {
                    try {
                        Thread.sleep((long) this.CONTENT_PACKAGE_DELAY_TIME);
                    } catch (InterruptedException e22) {
                        e22.printStackTrace();
                    }
                }
                i++;
            }
            try {
                Thread.sleep((long) this.CONTENT_CHECKSUM_BEFORE_DELAY_TIME);
            } catch (InterruptedException e222) {
                e222.printStackTrace();
            }
            int checkLength = (pwd.length() + 256) + 76;
            for (t = 1; t <= 3 && this.isConnecting; t++) {
                send(getBytes(checkLength));
                if (t < 3) {
                    try {
                        Thread.sleep((long) this.CONTENT_PACKAGE_DELAY_TIME);
                    } catch (InterruptedException e2222) {
                        e2222.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep((long) this.CONTENT_GROUP_DELAY_TIME);
            } catch (InterruptedException e22222) {
                e22222.printStackTrace();
            }
        }
        Log.e(this.TAG, "connect END");
    }

    private byte[] getBytes(int capacity) {
        byte[] data = new byte[capacity];
        for (int i = 0; i < capacity; i++) {
            data[i] = (byte) 5;
        }
        return data;
    }

    public char byteToChar(byte[] b) {
        return (char) (((b[0] & 255) << 8) | (b[1] & 255));
    }

    public void sendFindCmd() {
        System.out.println("smartlinkfind");
        this.packetToSendbroadcast = new DatagramPacket("smartlinkfind".getBytes(), "smartlinkfind".getBytes().length, this.inetAddressbroadcast, 48899);
        try {
            this.socket.send(this.packetToSendbroadcast);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendHFA11Cmd() {
        System.out.println("smartlinkfind");
        this.packetToSendbroadcast = new DatagramPacket("HF-A11ASSISTHREAD".getBytes(), "HF-A11ASSISTHREAD".getBytes().length, this.inetAddressbroadcast, 48899);
        try {
            this.socket.send(this.packetToSendbroadcast);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(byte[] data) {
        this.packetToSendbroadcast = new DatagramPacket(data, data.length, this.inetAddressbroadcast, this.port);
        try {
            this.socket.send(this.packetToSendbroadcast);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receive() {
        Log.e(this.TAG, "start RECV");
        this.dataPacket = new DatagramPacket(this.receiveByte, this.receiveByte.length);
        new C10763().start();
    }
}
