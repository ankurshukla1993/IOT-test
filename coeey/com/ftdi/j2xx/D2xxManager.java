package com.ftdi.j2xx;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D2xxManager {
    protected static final String ACTION_USB_PERMISSION = "com.ftdi.j2xx";
    public static final int FTDI_BREAK_OFF = 0;
    public static final int FTDI_BREAK_ON = 16384;
    public static final byte FT_BI = (byte) 16;
    public static final byte FT_BITMODE_ASYNC_BITBANG = (byte) 1;
    public static final byte FT_BITMODE_CBUS_BITBANG = (byte) 32;
    public static final byte FT_BITMODE_FAST_SERIAL = (byte) 16;
    public static final byte FT_BITMODE_MCU_HOST = (byte) 8;
    public static final byte FT_BITMODE_MPSSE = (byte) 2;
    public static final byte FT_BITMODE_RESET = (byte) 0;
    public static final byte FT_BITMODE_SYNC_BITBANG = (byte) 4;
    public static final byte FT_BITMODE_SYNC_FIFO = (byte) 64;
    public static final byte FT_CTS = (byte) 16;
    public static final byte FT_DATA_BITS_7 = (byte) 7;
    public static final byte FT_DATA_BITS_8 = (byte) 8;
    public static final byte FT_DCD = Byte.MIN_VALUE;
    public static final int FT_DEVICE_2232 = 4;
    public static final int FT_DEVICE_2232H = 6;
    public static final int FT_DEVICE_232B = 0;
    public static final int FT_DEVICE_232H = 8;
    public static final int FT_DEVICE_232R = 5;
    public static final int FT_DEVICE_245R = 5;
    public static final int FT_DEVICE_4232H = 7;
    public static final int FT_DEVICE_8U232AM = 1;
    public static final int FT_DEVICE_UNKNOWN = 3;
    public static final int FT_DEVICE_X_SERIES = 9;
    public static final byte FT_DSR = (byte) 32;
    public static final byte FT_EVENT_LINE_STATUS = (byte) 4;
    public static final byte FT_EVENT_MODEM_STATUS = (byte) 2;
    public static final byte FT_EVENT_REMOVED = (byte) 8;
    public static final byte FT_EVENT_RXCHAR = (byte) 1;
    public static final byte FT_FE = (byte) 8;
    public static final byte FT_FLAGS_HI_SPEED = (byte) 2;
    public static final byte FT_FLAGS_OPENED = (byte) 1;
    public static final short FT_FLOW_DTR_DSR = (short) 512;
    public static final short FT_FLOW_NONE = (short) 0;
    public static final short FT_FLOW_RTS_CTS = (short) 256;
    public static final short FT_FLOW_XON_XOFF = (short) 1024;
    public static final byte FT_OE = (byte) 2;
    public static final byte FT_PARITY_EVEN = (byte) 2;
    public static final byte FT_PARITY_MARK = (byte) 3;
    public static final byte FT_PARITY_NONE = (byte) 0;
    public static final byte FT_PARITY_ODD = (byte) 1;
    public static final byte FT_PARITY_SPACE = (byte) 4;
    public static final byte FT_PE = (byte) 4;
    public static final byte FT_PURGE_RX = (byte) 1;
    public static final byte FT_PURGE_TX = (byte) 2;
    public static final byte FT_RI = (byte) 64;
    public static final byte FT_STOP_BITS_1 = (byte) 0;
    public static final byte FT_STOP_BITS_2 = (byte) 2;
    private static final String TAG = "D2xx::";
    private static Context mContext = null;
    private static D2xxManager mInstance = null;
    private static PendingIntent mPendingIntent = null;
    private static IntentFilter mPermissionFilter = null;
    private static List<FtVidPid> mSupportedDevices = new ArrayList(Arrays.asList(new FtVidPid[]{new FtVidPid(1027, 24597), new FtVidPid(1027, 24596), new FtVidPid(1027, 24593), new FtVidPid(1027, 24592), new FtVidPid(1027, 24577), new FtVidPid(1027, 24582), new FtVidPid(1027, 64193), new FtVidPid(1027, 64194), new FtVidPid(1027, 64195), new FtVidPid(1027, 64196), new FtVidPid(1027, 64197), new FtVidPid(1027, 64198), new FtVidPid(1027, 24594), new FtVidPid(2220, 4133), new FtVidPid(5590, 1), new FtVidPid(1027, 24599)}));
    private static BroadcastReceiver mUsbDevicePermissions = new C14762();
    private static UsbManager mUsbManager;
    private ArrayList<FT_Device> mFtdiDevices;
    private BroadcastReceiver mUsbPlugEvents = new C14751();

    class C14751 extends BroadcastReceiver {
        C14751() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
                UsbDevice dev = (UsbDevice) intent.getParcelableExtra("device");
                FT_Device ftDev = D2xxManager.this.findDevice(dev);
                while (ftDev != null) {
                    ftDev.close();
                    synchronized (D2xxManager.this.mFtdiDevices) {
                        D2xxManager.this.mFtdiDevices.remove(ftDev);
                    }
                    ftDev = D2xxManager.this.findDevice(dev);
                }
            } else if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(action)) {
                D2xxManager.this.addUsbDevice((UsbDevice) intent.getParcelableExtra("device"));
            }
        }
    }

    class C14762 extends BroadcastReceiver {
        C14762() {
        }

        public void onReceive(Context context, Intent intent) {
            if (D2xxManager.ACTION_USB_PERMISSION.equals(intent.getAction())) {
                synchronized (this) {
                    UsbDevice device = (UsbDevice) intent.getParcelableExtra("device");
                    if (!intent.getBooleanExtra("permission", false)) {
                        Log.d(D2xxManager.TAG, "permission denied for device " + device);
                    }
                }
            }
        }
    }

    public static class D2xxException extends IOException {
        private static final long serialVersionUID = 1;

        public D2xxException(String ftStatusMsg) {
            super(ftStatusMsg);
        }
    }

    public static class DriverParameters {
        private int mBufferSize = 16384;
        private int mMaxTransferSize = 16384;
        private int mNrBuffers = 16;
        private int mRxTimeout = 5000;

        public boolean setMaxBufferSize(int size) {
            if (size < 64 || size > 16384) {
                Log.e(D2xxManager.TAG, "***bufferSize Out of correct range***");
                return false;
            }
            this.mBufferSize = size;
            return true;
        }

        public int getMaxBufferSize() {
            return this.mBufferSize;
        }

        public boolean setMaxTransferSize(int size) {
            if (size < 64 || size > 16384) {
                Log.e(D2xxManager.TAG, "***maxTransferSize Out of correct range***");
                return false;
            }
            this.mMaxTransferSize = size;
            return true;
        }

        public int getMaxTransferSize() {
            return this.mMaxTransferSize;
        }

        public boolean setBufferNumber(int number) {
            if (number < 2 || number > 16) {
                Log.e(D2xxManager.TAG, "***nrBuffers Out of correct range***");
                return false;
            }
            this.mNrBuffers = number;
            return true;
        }

        public int getBufferNumber() {
            return this.mNrBuffers;
        }

        public boolean setReadTimeout(int timeout) {
            this.mRxTimeout = timeout;
            return true;
        }

        public int getReadTimeout() {
            return this.mRxTimeout;
        }
    }

    public static class FtDeviceInfoListNode {
        public short bcdDevice;
        public int breakOnParam;
        public String description;
        public int flags;
        public int handle;
        public byte iSerialNumber;
        public int id;
        public short lineStatus;
        public int location;
        public short modemStatus;
        public String serialNumber;
        public int type;
    }

    private FT_Device findDevice(UsbDevice usbDev) {
        FT_Device rtDev = null;
        synchronized (this.mFtdiDevices) {
            int nr_dev = this.mFtdiDevices.size();
            for (int i = 0; i < nr_dev; i++) {
                FT_Device ftDevice = (FT_Device) this.mFtdiDevices.get(i);
                if (ftDevice.getUsbDevice().equals(usbDev)) {
                    rtDev = ftDevice;
                    break;
                }
            }
        }
        return rtDev;
    }

    public boolean isFtDevice(UsbDevice dev) {
        boolean rc = false;
        if (mContext == null) {
            return 0;
        }
        FtVidPid vidPid = new FtVidPid(dev.getVendorId(), dev.getProductId());
        if (mSupportedDevices.contains(vidPid)) {
            rc = true;
        }
        Log.v(TAG, vidPid.toString());
        return rc;
    }

    private static synchronized boolean updateContext(Context context) {
        int rc;
        synchronized (D2xxManager.class) {
            if (context == null) {
                rc = false;
            } else {
                if (mContext != context) {
                    mContext = context;
                    mPendingIntent = PendingIntent.getBroadcast(mContext.getApplicationContext(), 0, new Intent(ACTION_USB_PERMISSION), 134217728);
                    mPermissionFilter = new IntentFilter(ACTION_USB_PERMISSION);
                    mContext.getApplicationContext().registerReceiver(mUsbDevicePermissions, mPermissionFilter);
                }
                boolean rc2 = true;
            }
        }
        return rc;
    }

    private boolean isPermitted(UsbDevice dev) {
        if (!mUsbManager.hasPermission(dev)) {
            mUsbManager.requestPermission(dev, mPendingIntent);
        }
        if (mUsbManager.hasPermission(dev)) {
            return true;
        }
        return false;
    }

    private D2xxManager(Context parentContext) throws D2xxException {
        Log.v(TAG, "Start constructor");
        if (parentContext == null) {
            throw new D2xxException("D2xx init failed: Can not find parentContext!");
        }
        updateContext(parentContext);
        if (findUsbManger()) {
            this.mFtdiDevices = new ArrayList();
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
            filter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
            parentContext.getApplicationContext().registerReceiver(this.mUsbPlugEvents, filter);
            Log.v(TAG, "End constructor");
            return;
        }
        throw new D2xxException("D2xx init failed: Can not find UsbManager!");
    }

    public static synchronized D2xxManager getInstance(Context parentContext) throws D2xxException {
        D2xxManager d2xxManager;
        synchronized (D2xxManager.class) {
            if (mInstance == null) {
                mInstance = new D2xxManager(parentContext);
            }
            if (parentContext != null) {
                updateContext(parentContext);
            }
            d2xxManager = mInstance;
        }
        return d2xxManager;
    }

    private static boolean findUsbManger() {
        if (mUsbManager == null && mContext != null) {
            mUsbManager = (UsbManager) mContext.getApplicationContext().getSystemService("usb");
        }
        return mUsbManager != null;
    }

    public boolean setVIDPID(int vendorId, int productId) {
        boolean rc = false;
        if (vendorId == 0 || productId == 0) {
            Log.d(TAG, "Invalid parameter to setVIDPID");
        } else {
            FtVidPid vidpid = new FtVidPid(vendorId, productId);
            if (mSupportedDevices.contains(vidpid)) {
                Log.i(TAG, "Existing vid:" + vendorId + "  pid:" + productId);
                return true;
            } else if (mSupportedDevices.add(vidpid)) {
                rc = true;
            } else {
                Log.d(TAG, "Failed to add VID/PID combination to list.");
            }
        }
        return rc;
    }

    public int[][] getVIDPID() {
        int listSize = mSupportedDevices.size();
        int[][] arrayVIDPID = (int[][]) Array.newInstance(Integer.TYPE, new int[]{2, listSize});
        for (int i = 0; i < listSize; i++) {
            FtVidPid vidpid = (FtVidPid) mSupportedDevices.get(i);
            arrayVIDPID[0][i] = vidpid.getVid();
            arrayVIDPID[1][i] = vidpid.getPid();
        }
        return arrayVIDPID;
    }

    private void clearDevices() {
        synchronized (this.mFtdiDevices) {
            int nr_dev = this.mFtdiDevices.size();
            for (int i = 0; i < nr_dev; i++) {
                this.mFtdiDevices.remove(i);
            }
        }
    }

    public int createDeviceInfoList(Context parentContext) {
        ArrayList<FT_Device> devices = new ArrayList();
        if (parentContext == null) {
            return 0;
        }
        int rc;
        updateContext(parentContext);
        for (UsbDevice usbDevice : mUsbManager.getDeviceList().values()) {
            if (isFtDevice(usbDevice)) {
                int numInterfaces = usbDevice.getInterfaceCount();
                for (int i = 0; i < numInterfaces; i++) {
                    if (isPermitted(usbDevice)) {
                        synchronized (this.mFtdiDevices) {
                            FT_Device ftDev = findDevice(usbDevice);
                            if (ftDev == null) {
                                ftDev = new FT_Device(parentContext, mUsbManager, usbDevice, usbDevice.getInterface(i));
                            } else {
                                this.mFtdiDevices.remove(ftDev);
                                ftDev.setContext(parentContext);
                            }
                            devices.add(ftDev);
                        }
                    }
                }
                continue;
            }
        }
        synchronized (this.mFtdiDevices) {
            clearDevices();
            this.mFtdiDevices = devices;
            rc = this.mFtdiDevices.size();
        }
        return rc;
    }

    public synchronized int getDeviceInfoList(int numDevs, FtDeviceInfoListNode[] deviceList) {
        for (int i = 0; i < numDevs; i++) {
            deviceList[i] = ((FT_Device) this.mFtdiDevices.get(i)).mDeviceInfoNode;
        }
        return this.mFtdiDevices.size();
    }

    public synchronized FtDeviceInfoListNode getDeviceInfoListDetail(int index) {
        FtDeviceInfoListNode ftDeviceInfoListNode;
        if (index > this.mFtdiDevices.size() || index < 0) {
            ftDeviceInfoListNode = null;
        } else {
            ftDeviceInfoListNode = ((FT_Device) this.mFtdiDevices.get(index)).mDeviceInfoNode;
        }
        return ftDeviceInfoListNode;
    }

    public static int getLibraryVersion() {
        return 268435456;
    }

    private boolean tryOpen(Context parentContext, FT_Device ftDev, DriverParameters params) {
        boolean rc = false;
        if (ftDev == null) {
            return 0;
        }
        if (parentContext == null) {
            return 0;
        }
        ftDev.setContext(parentContext);
        if (params != null) {
            ftDev.setDriverParameters(params);
        }
        if (ftDev.openDevice(mUsbManager) && ftDev.isOpen()) {
            rc = true;
        }
        return rc;
    }

    public synchronized FT_Device openByUsbDevice(Context parentContext, UsbDevice dev, DriverParameters params) {
        FT_Device ftDev;
        ftDev = null;
        if (isFtDevice(dev)) {
            ftDev = findDevice(dev);
            if (!tryOpen(parentContext, ftDev, params)) {
                ftDev = null;
            }
        }
        return ftDev;
    }

    public synchronized FT_Device openByUsbDevice(Context parentContext, UsbDevice dev) {
        return openByUsbDevice(parentContext, dev, null);
    }

    public synchronized FT_Device openByIndex(Context parentContext, int index, DriverParameters params) {
        FT_Device ftDev;
        if (index < 0) {
            ftDev = null;
        } else if (parentContext == null) {
            ftDev = null;
        } else {
            updateContext(parentContext);
            FT_Device ftDev2 = (FT_Device) this.mFtdiDevices.get(index);
            if (!tryOpen(parentContext, ftDev2, params)) {
                ftDev2 = null;
            }
            ftDev = ftDev2;
        }
        return ftDev;
    }

    public synchronized FT_Device openByIndex(Context parentContext, int index) {
        return openByIndex(parentContext, index, null);
    }

    public synchronized FT_Device openBySerialNumber(Context parentContext, String serialNumber, DriverParameters params) {
        FT_Device ftDev;
        FT_Device ftDev2 = null;
        if (parentContext == null) {
            ftDev = null;
        } else {
            updateContext(parentContext);
            for (int i = 0; i < this.mFtdiDevices.size(); i++) {
                FT_Device tmpDev = (FT_Device) this.mFtdiDevices.get(i);
                if (tmpDev != null) {
                    FtDeviceInfoListNode devInfo = tmpDev.mDeviceInfoNode;
                    if (devInfo == null) {
                        Log.d(TAG, "***devInfo cannot be null***");
                    } else if (devInfo.serialNumber.equals(serialNumber)) {
                        ftDev2 = tmpDev;
                        break;
                    }
                }
            }
            if (!tryOpen(parentContext, ftDev2, params)) {
                ftDev2 = null;
            }
            ftDev = ftDev2;
        }
        return ftDev;
    }

    public synchronized FT_Device openBySerialNumber(Context parentContext, String serialNumber) {
        return openBySerialNumber(parentContext, serialNumber, null);
    }

    public synchronized FT_Device openByDescription(Context parentContext, String description, DriverParameters params) {
        FT_Device ftDev;
        FT_Device ftDev2 = null;
        if (parentContext == null) {
            ftDev = null;
        } else {
            updateContext(parentContext);
            for (int i = 0; i < this.mFtdiDevices.size(); i++) {
                FT_Device tmpDev = (FT_Device) this.mFtdiDevices.get(i);
                if (tmpDev != null) {
                    FtDeviceInfoListNode devInfo = tmpDev.mDeviceInfoNode;
                    if (devInfo == null) {
                        Log.d(TAG, "***devInfo cannot be null***");
                    } else if (devInfo.description.equals(description)) {
                        ftDev2 = tmpDev;
                        break;
                    }
                }
            }
            if (!tryOpen(parentContext, ftDev2, params)) {
                ftDev2 = null;
            }
            ftDev = ftDev2;
        }
        return ftDev;
    }

    public synchronized FT_Device openByDescription(Context parentContext, String description) {
        return openByDescription(parentContext, description, null);
    }

    public synchronized FT_Device openByLocation(Context parentContext, int location, DriverParameters params) {
        FT_Device ftDev;
        FT_Device ftDev2 = null;
        if (parentContext == null) {
            ftDev = null;
        } else {
            updateContext(parentContext);
            for (int i = 0; i < this.mFtdiDevices.size(); i++) {
                FT_Device tmpDev = (FT_Device) this.mFtdiDevices.get(i);
                if (tmpDev != null) {
                    FtDeviceInfoListNode devInfo = tmpDev.mDeviceInfoNode;
                    if (devInfo == null) {
                        Log.d(TAG, "***devInfo cannot be null***");
                    } else if (devInfo.location == location) {
                        ftDev2 = tmpDev;
                        break;
                    }
                }
            }
            if (!tryOpen(parentContext, ftDev2, params)) {
                ftDev2 = null;
            }
            ftDev = ftDev2;
        }
        return ftDev;
    }

    public synchronized FT_Device openByLocation(Context parentContext, int location) {
        return openByLocation(parentContext, location, null);
    }

    public int addUsbDevice(UsbDevice dev) {
        int rc = 0;
        if (isFtDevice(dev)) {
            int numInterfaces = dev.getInterfaceCount();
            for (int i = 0; i < numInterfaces; i++) {
                if (isPermitted(dev)) {
                    synchronized (this.mFtdiDevices) {
                        FT_Device ftDev = findDevice(dev);
                        if (ftDev == null) {
                            ftDev = new FT_Device(mContext, mUsbManager, dev, dev.getInterface(i));
                        } else {
                            ftDev.setContext(mContext);
                        }
                        this.mFtdiDevices.add(ftDev);
                        rc++;
                    }
                }
            }
        }
        return rc;
    }
}
