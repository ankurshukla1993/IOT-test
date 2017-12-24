package com.ihealth.communication.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Environment;
import android.text.TextUtils;
import com.facebook.stetho.server.http.HttpStatus;
import com.ihealth.communication.cloud.a.c;
import com.ihealth.communication.control.AmProfile;
import com.ihealth.communication.control.UpDeviceControl;
import com.ihealth.communication.control.UpgradeProfile;
import com.ihealth.communication.ins.F0InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.FirmWare;
import com.ihealth.communication.utils.FirmWare.FirmwareInfo;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.util.EncodingUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class iHealthDevicesUpgradeManager {
    private Map f1994a;
    private FirmWare f1995b;
    private List f1996c;
    private boolean f1997d;
    private int f1998e;
    private boolean f1999f;
    private String f2000g;
    private Context f2001h;
    private UpDeviceControl f2002i;
    private c f2003j;
    private InsCallback f2004k;
    private BroadcastReceiver f2005l;
    private boolean f2006m;

    private iHealthDevicesUpgradeManager() {
        this.f1994a = new HashMap();
        this.f1999f = true;
        this.f2005l = new C2161q(this);
        this.f2006m = false;
    }

    private FirmWare m1160a() {
        File file = new File(m1171b() + "/iHealth/");
        if (!file.exists()) {
            file.mkdirs();
        }
        return m1161a(new File(file, "AMIndex.txt"));
    }

    private FirmWare m1161a(File file) {
        int i = 0;
        FirmWare firmWare = new FirmWare();
        if (this.f1997d) {
            firmWare.setBlockNum(new byte[]{(byte) (this.f1998e & 255), (byte) ((this.f1998e >> 8) & 255)});
        } else {
            firmWare.setBlockNum(new byte[]{(byte) 0, (byte) 0});
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            int i2 = 0;
            while (i2 < 16) {
                if (bArr[i2] == (byte) 0) {
                    break;
                }
                i2++;
            }
            i2 = 0;
            if (i2 > 0) {
                firmWare.setModel(new String(ByteBufferUtil.bufferCut(bArr, 0, i2), "UTF-8"));
            }
            Object[] objArr = new Object[1];
            objArr[0] = ByteBufferUtil.Bytes2HexString(new byte[]{bArr[16]});
            firmWare.setType(String.format("0x%s", objArr));
            firmWare.setCrcCode((long) ((((bArr[17] & 255) + ((bArr[18] & 255) * 256)) + (((bArr[19] & 255) * 256) * 256)) + ((((bArr[20] & 255) * 256) * 256) * 256)));
            firmWare.setFwVer(new byte[]{bArr[21], bArr[22], bArr[23]});
            byte[] bArr2 = new byte[]{bArr[24], bArr[25], bArr[26]};
            firmWare.setFwSize(bArr2);
            int i3 = bArr[27] & 255;
            firmWare.setSupportHwAmount(i3);
            List arrayList = new ArrayList();
            for (i2 = 0; i2 < i3; i2++) {
                arrayList.add(new byte[]{bArr[(i3 * 3) + 28], bArr[((i3 * 3) + 28) + 1], bArr[((i3 * 3) + 28) + 2]});
            }
            firmWare.setHwVerList(arrayList);
            i2 = bArr[(i3 * 3) + 28] & 255;
            firmWare.setFwAmount(i2);
            arrayList = new ArrayList();
            while (i < i2) {
                FirmwareInfo firmwareInfo = new FirmwareInfo();
                firmwareInfo.setFwVerN(new byte[]{bArr[(((i3 * 3) + 28) + (i * 6)) + 1], bArr[((((i3 * 3) + 28) + (i * 6)) + 1) + 1], bArr[((((i3 * 3) + 28) + (i * 6)) + 1) + 2]});
                firmwareInfo.setFwSizeN(new byte[]{bArr[((((i3 * 3) + 28) + (i * 6)) + 1) + 3], bArr[(((((i3 * 3) + 28) + (i * 6)) + 1) + 3) + 1], bArr[(((((i3 * 3) + 28) + (i * 6)) + 1) + 3) + 2]});
                arrayList.add(firmwareInfo);
                i++;
            }
            firmWare.setFwList(arrayList);
            List arrayList2 = new ArrayList();
            i2 = 128;
            i = ((bArr2[0] & 255) + ((bArr2[1] & 255) << 8)) + ((bArr2[2] & 255) << 16);
            i = i % 128 == 0 ? i / 128 : (i / 128) + 1;
            while (i > 0) {
                arrayList2.add(new byte[]{bArr[i2], bArr[i2 + 1]});
                i2 += 2;
                i--;
            }
            firmWare.setCrcList(arrayList2);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return firmWare;
    }

    private void m1165a(String str, String str2) {
        List arrayList = new ArrayList();
        if (this.f1995b.getBlockNum() != null) {
            byte[] blockNum = this.f1995b.getBlockNum();
            arrayList.add(Byte.valueOf(blockNum[0]));
            arrayList.add(Byte.valueOf(blockNum[1]));
        }
        if (this.f1995b.getFwVer() != null) {
            blockNum = this.f1995b.getFwVer();
            arrayList.add(Byte.valueOf(blockNum[0]));
            arrayList.add(Byte.valueOf(blockNum[1]));
            arrayList.add(Byte.valueOf(blockNum[2]));
        }
        if (this.f1995b.getFwSize() != null) {
            blockNum = this.f1995b.getFwSize();
            arrayList.add(Byte.valueOf(blockNum[0]));
            arrayList.add(Byte.valueOf(blockNum[1]));
            arrayList.add(Byte.valueOf(blockNum[2]));
        }
        List fwList = this.f1995b.getFwList();
        for (int i = 0; i < this.f1995b.getFwAmount(); i++) {
            FirmwareInfo firmwareInfo = (FirmwareInfo) fwList.get(i);
            arrayList.add(Byte.valueOf(firmwareInfo.getFwVerN()[0]));
            arrayList.add(Byte.valueOf(firmwareInfo.getFwVerN()[1]));
            arrayList.add(Byte.valueOf(firmwareInfo.getFwVerN()[2]));
            arrayList.add(Byte.valueOf(firmwareInfo.getFwSizeN()[0]));
            arrayList.add(Byte.valueOf(firmwareInfo.getFwSizeN()[1]));
            arrayList.add(Byte.valueOf(firmwareInfo.getFwSizeN()[2]));
        }
        this.f2002i = getUpDeviceControl(str, str2);
        if (this.f2002i == null) {
            this.f2006m = false;
            this.f1999f = true;
            if (isUpgradeState(str, str2)) {
                stopUpgrade(str, str2);
                return;
            }
            return;
        }
        this.f2002i.borrowComm();
        this.f2002i.setInformation(arrayList);
        this.f2002i.setData(this.f1995b, this.f1996c);
        this.f2002i.startUpgrade();
    }

    private void m1166a(String str, String str2, int i) {
        try {
            this.f2006m = false;
            this.f1999f = true;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(UpgradeProfile.DEVICE_UP_ERROR, i);
            this.f2004k.onNotify(str, str2, UpgradeProfile.ACTION_DEVICE_ERROR, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void m1167a(String str, String str2, JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("up.device.up.mode");
            String str3 = (String) jSONObject.get(UpgradeProfile.DEVICE_HARDWARE_VERSION);
            String str4 = (String) jSONObject.get(UpgradeProfile.DEVICE_TYPE);
            String str5 = (String) jSONObject.get("up.device.up.mode");
            String str6 = (String) jSONObject.get(UpgradeProfile.DEVICE_FIRMWARE_VERSION);
            int intValue = ((Integer) jSONObject.get(UpgradeProfile.DEVICE_UPGRADE_FLAG)).intValue();
            if (this.f1995b.getModel().equals(string)) {
                String str7 = new String(this.f1995b.getFwVer());
                string = str7.substring(0, 1) + "." + str7.substring(1, 2) + "." + str7.substring(2, 3);
                this.f1998e = ((Integer) jSONObject.get(UpgradeProfile.DEVICE_BLOCK_NUM)).intValue();
                boolean b = m1174b(str6, string);
                if (str3 == null || str3.length() == 0 || str5 == null || str5.length() == 0 || str4 == null || str4.length() == 0 || str6 == null || str6.length() == 0 || string.length() == 0) {
                    if (isUpgradeState(str, str2)) {
                        stopUpgrade(str, str2);
                    }
                    if (this.f2002i != null) {
                        this.f2002i.returnComm();
                    }
                    m1166a(str, str2, 201);
                    return;
                } else if ((str6.equals(string) && intValue == 0) || b) {
                    if (this.f2002i != null) {
                        this.f2002i.returnComm();
                    }
                    m1166a(str, str2, 400);
                    return;
                } else {
                    this.f1997d = Integer.parseInt(str6.replace(".", "")) == Integer.parseInt(string.replace(".", ""));
                    if (this.f1997d) {
                        this.f1995b.setBlockNum(new byte[]{(byte) (this.f1998e & 255), (byte) ((this.f1998e >> 8) & 255)});
                    } else {
                        this.f1995b.setBlockNum(new byte[]{(byte) 0, (byte) 0});
                    }
                    m1165a(str, str2);
                    return;
                }
            }
            m1166a(str, str2, 405);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static String m1171b() {
        return Environment.getExternalStorageDirectory().toString();
    }

    private List m1172b(File file) {
        List arrayList = new ArrayList();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(new byte[fileInputStream.available()]);
            fileInputStream.close();
            FileInputStream fileInputStream2 = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Object obj = new byte[128];
            while (true) {
                int read = fileInputStream2.read(obj);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(obj, 0, read);
                Object obj2 = new byte[128];
                System.arraycopy(obj, 0, obj2, 0, read);
                if (read < 128) {
                    while (read < 128) {
                        obj2[read] = (byte) -1;
                        read++;
                    }
                }
                arrayList.add(obj2);
            }
            EncodingUtils.getString(byteArrayOutputStream.toByteArray(), "UTF-8");
            byteArrayOutputStream.close();
            fileInputStream2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.m1214v("iHealthDUM", "code bags:" + arrayList.size());
        return arrayList;
    }

    private boolean m1174b(String str, String str2) {
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int length = split.length >= split2.length ? split2.length : split.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            int parseInt = Integer.parseInt(split[i]) - Integer.parseInt(split2[i]);
            if (parseInt != 0) {
                return parseInt > 0;
            } else {
                if (i == length - 1) {
                    z = split.length > split2.length;
                }
                i++;
            }
        }
        return z;
    }

    private List m1176c() {
        File file = new File(m1171b() + "/iHealth/");
        if (!file.exists()) {
            file.mkdirs();
        }
        return m1172b(new File(file, "AMDownload.txt"));
    }

    public static iHealthDevicesUpgradeManager getInstance() {
        return C2168x.f2036a;
    }

    void m1184a(Context context, InsCallback insCallback) {
        this.f2001h = context;
        this.f2004k = insCallback;
        if (this.f2003j == null) {
            this.f2003j = new c();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(F0InsSet.MSG_DISCONNECTED);
        this.f2001h.registerReceiver(this.f2005l, intentFilter);
    }

    public void destroy() {
        try {
            this.f2001h.unregisterReceiver(this.f2005l);
        } catch (Exception e) {
            Log.m1215w("iHealthDUM", "destroy() -- e: " + e);
        }
    }

    public UpDeviceControl getUpDeviceControl(String mac, String type) {
        Log.m1213p("iHealthDUM", Level.VERBOSE, "getUpDeviceControl", mac, type);
        this.f2000g = type;
        try {
            if (type.equals(iHealthDevicesManager.TYPE_AM3)) {
                return iHealthDevicesManager.getInstance().getAm3Control(mac).getUpDeviceControl();
            }
            if (type.equals(iHealthDevicesManager.TYPE_AM3S)) {
                return iHealthDevicesManager.getInstance().getAm3sControl(mac).getUpDeviceControl();
            }
            if (type.contains(iHealthDevicesManager.TYPE_PO3)) {
                return iHealthDevicesManager.getInstance().getPo3Control(mac).getUpDeviceControl();
            }
            if (type.equals(iHealthDevicesManager.TYPE_HS4)) {
                return iHealthDevicesManager.getInstance().getHs4Control(mac).getUpDeviceControl();
            }
            if (type.equals(iHealthDevicesManager.TYPE_HS4S)) {
                return iHealthDevicesManager.getInstance().getHs4sControl(mac).getUpDeviceControl();
            }
            if (type.equals(iHealthDevicesManager.TYPE_AM4)) {
                return iHealthDevicesManager.getInstance().getAm4Control(mac).getUpDeviceControl();
            }
            return type.equals(iHealthDevicesManager.TYPE_BP5S) ? iHealthDevicesManager.getInstance().getBp5sControl(mac).getUpDeviceControl() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isUpgradeState(String mac, String type) {
        Log.m1213p("iHealthDUM", Level.VERBOSE, "isUpgradeState", mac, type);
        if (mac == null || type == null || mac.length() != 12 || type.length() == 0) {
            m1166a(mac, type, 302);
        }
        this.f2002i = getUpDeviceControl(mac, type);
        if (this.f2002i != null) {
            return this.f2002i.isUpgradeState();
        }
        Log.m1214v("iHealthDUM", "mUpDeviceControl is null for mac = " + mac + "   type = " + type);
        m1166a(mac, type, 300);
        return false;
    }

    public synchronized void queryLatestVersionFromCloud(String mac, String type, JSONObject o) {
        if (this.f2003j != null) {
            if (o != null) {
                new C2164t(this, o, mac, type).start();
            } else {
                this.f2006m = false;
                this.f1999f = true;
                if (isUpgradeState(mac, type)) {
                    stopUpgrade(mac, type);
                }
            }
        }
    }

    public synchronized void queryUpgradeInfoFromDeviceAndCloud(String mac, String type) {
        Log.m1213p("iHealthDUM", Level.VERBOSE, "queryUpgradeInfoFromDeviceAndCloud", mac, type);
        if (mac == null || type == null || mac.length() != 12 || type.length() == 0) {
            m1166a(mac, type, 302);
        } else {
            new C2162r(this, mac, type).start();
        }
    }

    public void setType(int type) {
        Log.m1213p("iHealthDUM", Level.VERBOSE, "setType", Integer.valueOf(type));
        if (this.f2003j == null) {
            this.f2003j = new c();
        }
        this.f2003j.a(type);
    }

    public synchronized void startUpGrade(String mac, String type) {
        Log.m1213p("iHealthDUM", Level.VERBOSE, "startUpGrade", mac, type);
        if (!this.f2006m) {
            this.f2006m = true;
            this.f1999f = true;
            if (mac == null || type == null || mac.length() != 12 || type.length() == 0) {
                m1166a(mac, type, 302);
            } else {
                new C2165u(this, mac, type).start();
            }
        }
    }

    public synchronized void startUpGrade(String mac, String type, String fwPath, String fwInfoPath, long crc, String appSecret) {
        Log.m1213p("iHealthDUM", Level.VERBOSE, "startUpGrade", mac, type, fwPath, fwInfoPath, Long.valueOf(crc), appSecret);
        if (!this.f2006m) {
            this.f2006m = true;
            this.f1999f = false;
            if (mac == null || type == null || mac.length() != 12 || type.length() == 0) {
                m1166a(mac, type, 302);
            } else if (!C2160p.m1197a(this.f2001h).equals(appSecret)) {
                m1166a(mac, type, 401);
            } else if (TextUtils.isEmpty(fwInfoPath) || TextUtils.isEmpty(fwPath)) {
                m1166a(mac, type, (int) AmProfile.ERROR_ID_VERSION_NOT_SUPPORT);
            } else {
                File file = new File(fwInfoPath);
                if (file.exists()) {
                    File file2 = new File(fwPath);
                    if (file2.exists()) {
                        this.f1995b = m1161a(file);
                        if (this.f1995b.getCrcCode() != crc) {
                            m1166a(mac, type, (int) HttpStatus.HTTP_NOT_FOUND);
                        } else {
                            this.f1996c = m1172b(file2);
                            byte[] fwSize = this.f1995b.getFwSize();
                            int i = ((fwSize[2] & 255) << 16) + ((fwSize[0] & 255) + ((fwSize[1] & 255) << 8));
                            if (this.f1996c.size() < (i % 128 == 0 ? i / 128 : (i / 128) + 1)) {
                                m1166a(mac, type, 406);
                            } else {
                                queryUpgradeInfoFromDeviceAndCloud(mac, type);
                            }
                        }
                    } else {
                        m1166a(mac, type, (int) AmProfile.ERROR_ID_VERSION_NOT_SUPPORT);
                    }
                } else {
                    m1166a(mac, type, (int) AmProfile.ERROR_ID_VERSION_NOT_SUPPORT);
                }
            }
        }
    }

    public void stopUpgrade(String mac, String type) {
        Log.m1213p("iHealthDUM", Level.VERBOSE, "stopUpgrade", mac, type);
        if (mac == null || type == null || mac.length() != 12 || type.length() == 0) {
            m1166a(mac, type, 302);
        }
        this.f1994a.remove(mac);
        this.f2006m = false;
        this.f1999f = true;
        new C2163s(this, mac, type).start();
    }
}
