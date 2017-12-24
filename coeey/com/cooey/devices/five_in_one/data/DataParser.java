package com.cooey.devices.five_in_one.data;

import java.util.concurrent.LinkedBlockingQueue;

public class DataParser {
    public static byte[] CMD_FW_VERSION = new byte[]{(byte) 85, (byte) -86, (byte) 4, (byte) -4, (byte) 0, (byte) -1};
    public static byte[] CMD_HW_VERSION = new byte[]{(byte) 85, (byte) -86, (byte) 4, (byte) -3, (byte) 0, (byte) -2};
    public static byte[] CMD_START_NIBP = new byte[]{(byte) 85, (byte) -86, (byte) 4, (byte) 2, (byte) 1, (byte) -8};
    public static byte[] CMD_STOP_NIBP = new byte[]{(byte) 85, (byte) -86, (byte) 4, (byte) 2, (byte) 0, (byte) -7};
    private int[] PACKAGE_HEAD = new int[]{85, 170};
    private final int PKG_ECG_PARAMS = 2;
    private final int PKG_ECG_WAVE = 1;
    private final int PKG_HW_VER = 253;
    private final int PKG_NIBP = 3;
    private final int PKG_SPO2_PARAMS = 4;
    private final int PKG_SPO2_WAVE = 254;
    private final int PKG_SW_VER = 252;
    private final int PKG_TEMP = 5;
    public String TAG = getClass().getSimpleName();
    private LinkedBlockingQueue<Integer> bufferQueue = new LinkedBlockingQueue(256);
    private boolean isStop = true;
    private onPackageReceivedListener mListener;
    private ParseRunnable mParseRunnable;

    class ParseRunnable implements Runnable {
        int dat;
        int[] packageData;

        ParseRunnable() {
        }

        public void run() {
            while (DataParser.this.isStop) {
                this.dat = DataParser.this.getData();
                if (this.dat == DataParser.this.PACKAGE_HEAD[0]) {
                    this.dat = DataParser.this.getData();
                    if (this.dat == DataParser.this.PACKAGE_HEAD[1]) {
                        int packageLen = DataParser.this.getData();
                        this.packageData = new int[(DataParser.this.PACKAGE_HEAD.length + packageLen)];
                        this.packageData[0] = DataParser.this.PACKAGE_HEAD[0];
                        this.packageData[1] = DataParser.this.PACKAGE_HEAD[1];
                        this.packageData[2] = packageLen;
                        for (int i = 3; i < DataParser.this.PACKAGE_HEAD.length + packageLen; i++) {
                            this.packageData[i] = DataParser.this.getData();
                        }
                        if (DataParser.this.CheckSum(this.packageData)) {
                            DataParser.this.ParsePackage(this.packageData);
                        }
                    }
                }
            }
        }
    }

    public interface onPackageReceivedListener {
        void onECGReceived(ECG ecg);

        void onECGWaveReceived(int i);

        void onFirmwareReceived(String str);

        void onHardwareReceived(String str);

        void onNIBPReceived(NIBP nibp);

        void onSpO2Received(SpO2 spO2);

        void onSpO2WaveReceived(int i);

        void onTempReceived(Temp temp);
    }

    public DataParser(onPackageReceivedListener listener) {
        this.mListener = listener;
    }

    public void start() {
        this.mParseRunnable = new ParseRunnable();
        new Thread(this.mParseRunnable).start();
    }

    public void stop() {
        this.isStop = true;
    }

    private void ParsePackage(int[] pkgData) {
        int[] tempBuffer = new int[5];
        int i;
        switch (pkgData[3]) {
            case 1:
                this.mListener.onECGWaveReceived(pkgData[4]);
                return;
            case 2:
                int heartRate = pkgData[5];
                if (pkgData[2] == 9) {
                    heartRate += pkgData[9] * 256;
                }
                this.mListener.onECGReceived(new ECG(heartRate, pkgData[6], pkgData[4]));
                return;
            case 3:
                this.mListener.onNIBPReceived(new NIBP(pkgData[6], pkgData[7], pkgData[8], pkgData[5] * 2, pkgData[4]));
                return;
            case 4:
                this.mListener.onSpO2Received(new SpO2(pkgData[5], pkgData[6], pkgData[4]));
                return;
            case 5:
                this.mListener.onTempReceived(new Temp(((double) ((pkgData[5] * 10) + pkgData[6])) / 10.0d, pkgData[4]));
                return;
            case 252:
                StringBuilder sb = new StringBuilder();
                for (i = 4; i < pkgData.length - 1; i++) {
                    sb.append((char) (pkgData[i] & 255));
                }
                this.mListener.onFirmwareReceived(sb.toString());
                return;
            case 253:
                StringBuilder sb1 = new StringBuilder();
                for (i = 4; i < pkgData.length - 1; i++) {
                    sb1.append((char) (pkgData[i] & 255));
                }
                this.mListener.onHardwareReceived(sb1.toString());
                return;
            case 254:
                this.mListener.onSpO2WaveReceived(pkgData[4]);
                return;
            default:
                return;
        }
    }

    public void add(byte[] dat) {
        for (byte b : dat) {
            try {
                this.bufferQueue.put(Integer.valueOf(toUnsignedInt(b)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int getData() {
        int dat = 0;
        try {
            dat = ((Integer) this.bufferQueue.take()).intValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return dat;
    }

    private boolean CheckSum(int[] packageData) {
        int sum = 0;
        for (int i = 2; i < packageData.length - 1; i++) {
            sum += packageData[i];
        }
        if (((sum ^ -1) & 255) == (packageData[packageData.length - 1] & 255)) {
            return true;
        }
        return false;
    }

    private int toUnsignedInt(byte x) {
        return x & 255;
    }
}
