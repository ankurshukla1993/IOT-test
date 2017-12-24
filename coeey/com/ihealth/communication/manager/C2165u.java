package com.ihealth.communication.manager;

import com.ihealth.communication.control.UpgradeProfile;
import com.ihealth.communication.utils.FirmWare;
import com.ihealth.communication.utils.FirmWare.FirmwareInfo;
import com.ihealth.communication.utils.Log;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

class C2165u extends Thread {
    final /* synthetic */ String f2031a;
    final /* synthetic */ String f2032b;
    final /* synthetic */ iHealthDevicesUpgradeManager f2033c;

    C2165u(iHealthDevicesUpgradeManager com_ihealth_communication_manager_iHealthDevicesUpgradeManager, String str, String str2) {
        this.f2033c = com_ihealth_communication_manager_iHealthDevicesUpgradeManager;
        this.f2031a = str;
        this.f2032b = str2;
    }

    public void run() {
        String str;
        String str2;
        String str3;
        JSONException jSONException;
        int i;
        String str4;
        boolean a;
        JSONObject jSONObject = (JSONObject) this.f2033c.f1994a.get(this.f2031a);
        if (jSONObject == null) {
            if (this.f2033c.f2002i != null) {
                this.f2033c.f2002i.returnComm();
            }
            this.f2033c.m1166a(this.f2031a, this.f2032b, 201);
            return;
        }
        String str5;
        String str6;
        this.f2033c.f1998e = 0;
        try {
            str = (String) jSONObject.get(UpgradeProfile.DEVICE_HARDWARE_VERSION);
            try {
                str5 = (String) jSONObject.get(UpgradeProfile.DEVICE_TYPE);
                try {
                    str6 = (String) jSONObject.get("up.device.up.mode");
                } catch (JSONException e) {
                    str6 = null;
                    str2 = null;
                    str3 = str;
                    str = null;
                    jSONException = e;
                    i = 0;
                    jSONException.printStackTrace();
                    str4 = str2;
                    str2 = str3;
                    a = this.f2033c.m1174b(str4, str);
                    if (str2 != null) {
                    }
                    if (this.f2033c.isUpgradeState(this.f2031a, this.f2032b)) {
                        this.f2033c.stopUpgrade(this.f2031a, this.f2032b);
                    }
                    if (this.f2033c.f2002i != null) {
                        this.f2033c.f2002i.returnComm();
                    }
                    this.f2033c.m1166a(this.f2031a, this.f2032b, 201);
                }
                try {
                    str2 = (String) jSONObject.get(UpgradeProfile.DEVICE_FIRMWARE_VERSION);
                    try {
                        str3 = (String) jSONObject.get(UpgradeProfile.DEVICE_CLOUD_FIRMWARE_VERSION);
                    } catch (JSONException e2) {
                        str3 = str;
                        str = null;
                        jSONException = e2;
                        i = 0;
                        jSONException.printStackTrace();
                        str4 = str2;
                        str2 = str3;
                        a = this.f2033c.m1174b(str4, str);
                        if (str2 != null) {
                        }
                        if (this.f2033c.isUpgradeState(this.f2031a, this.f2032b)) {
                            this.f2033c.stopUpgrade(this.f2031a, this.f2032b);
                        }
                        if (this.f2033c.f2002i != null) {
                            this.f2033c.f2002i.returnComm();
                        }
                        this.f2033c.m1166a(this.f2031a, this.f2032b, 201);
                    }
                } catch (JSONException e22) {
                    str2 = null;
                    str3 = str;
                    str = null;
                    jSONException = e22;
                    i = 0;
                    jSONException.printStackTrace();
                    str4 = str2;
                    str2 = str3;
                    a = this.f2033c.m1174b(str4, str);
                    if (str2 != null) {
                    }
                    if (this.f2033c.isUpgradeState(this.f2031a, this.f2032b)) {
                        this.f2033c.stopUpgrade(this.f2031a, this.f2032b);
                    }
                    if (this.f2033c.f2002i != null) {
                        this.f2033c.f2002i.returnComm();
                    }
                    this.f2033c.m1166a(this.f2031a, this.f2032b, 201);
                }
                try {
                    int intValue = ((Integer) jSONObject.get(UpgradeProfile.DEVICE_UPGRADE_FLAG)).intValue();
                    try {
                        this.f2033c.f1998e = ((Integer) jSONObject.get(UpgradeProfile.DEVICE_BLOCK_NUM)).intValue();
                        i = intValue;
                        str4 = str2;
                        str2 = str;
                        str = str3;
                    } catch (JSONException e222) {
                        JSONException jSONException2 = e222;
                        i = intValue;
                        jSONException = jSONException2;
                        String str7 = str3;
                        str3 = str;
                        str = str7;
                        jSONException.printStackTrace();
                        str4 = str2;
                        str2 = str3;
                        a = this.f2033c.m1174b(str4, str);
                        if (str2 != null) {
                        }
                        if (this.f2033c.isUpgradeState(this.f2031a, this.f2032b)) {
                            this.f2033c.stopUpgrade(this.f2031a, this.f2032b);
                        }
                        if (this.f2033c.f2002i != null) {
                            this.f2033c.f2002i.returnComm();
                        }
                        this.f2033c.m1166a(this.f2031a, this.f2032b, 201);
                    }
                } catch (JSONException e2222) {
                    jSONException = e2222;
                    i = 0;
                    String str8 = str3;
                    str3 = str;
                    str = str8;
                    jSONException.printStackTrace();
                    str4 = str2;
                    str2 = str3;
                    a = this.f2033c.m1174b(str4, str);
                    if (str2 != null) {
                    }
                    if (this.f2033c.isUpgradeState(this.f2031a, this.f2032b)) {
                        this.f2033c.stopUpgrade(this.f2031a, this.f2032b);
                    }
                    if (this.f2033c.f2002i != null) {
                        this.f2033c.f2002i.returnComm();
                    }
                    this.f2033c.m1166a(this.f2031a, this.f2032b, 201);
                }
            } catch (JSONException e22222) {
                str5 = null;
                str6 = null;
                str2 = null;
                str3 = str;
                str = null;
                jSONException = e22222;
                i = 0;
                jSONException.printStackTrace();
                str4 = str2;
                str2 = str3;
                a = this.f2033c.m1174b(str4, str);
                if (str2 != null) {
                }
                if (this.f2033c.isUpgradeState(this.f2031a, this.f2032b)) {
                    this.f2033c.stopUpgrade(this.f2031a, this.f2032b);
                }
                if (this.f2033c.f2002i != null) {
                    this.f2033c.f2002i.returnComm();
                }
                this.f2033c.m1166a(this.f2031a, this.f2032b, 201);
            }
        } catch (JSONException e222222) {
            str = null;
            jSONException = e222222;
            i = 0;
            str3 = null;
            str5 = null;
            str6 = null;
            str2 = null;
            jSONException.printStackTrace();
            str4 = str2;
            str2 = str3;
            a = this.f2033c.m1174b(str4, str);
            if (str2 != null) {
            }
            if (this.f2033c.isUpgradeState(this.f2031a, this.f2032b)) {
                this.f2033c.stopUpgrade(this.f2031a, this.f2032b);
            }
            if (this.f2033c.f2002i != null) {
                this.f2033c.f2002i.returnComm();
            }
            this.f2033c.m1166a(this.f2031a, this.f2032b, 201);
        }
        a = this.f2033c.m1174b(str4, str);
        if (str2 != null || str2.length() == 0 || str6 == null || str6.length() == 0 || str5 == null || str5.length() == 0 || str4 == null || str4.length() == 0 || str == null || str.length() == 0) {
            if (this.f2033c.isUpgradeState(this.f2031a, this.f2032b)) {
                this.f2033c.stopUpgrade(this.f2031a, this.f2032b);
            }
            if (this.f2033c.f2002i != null) {
                this.f2033c.f2002i.returnComm();
            }
            this.f2033c.m1166a(this.f2031a, this.f2032b, 201);
        } else if ((str4.equals(str) && r0 == 0) || a) {
            if (this.f2033c.f2002i != null) {
                this.f2033c.f2002i.returnComm();
            }
            this.f2033c.m1166a(this.f2031a, this.f2032b, 400);
        } else {
            Object obj;
            this.f2033c.f2004k.onNotify(this.f2031a, this.f2032b, UpgradeProfile.ACTION_DEVICE_UP_START_DOWNLOAD, null);
            Object obj2;
            if (!this.f2033c.f2003j.a(str, str5, str6, str2, "", 1, 0, 10000000, new C2166v(this))) {
                Log.m1215w("iHealthDUM", "download info failed");
                obj2 = null;
                obj = null;
            } else if (this.f2033c.f2003j.a(str, str5, str6, str2, "", 0, 0, 10000000, new C2167w(this))) {
                obj2 = 1;
                obj = 1;
            } else {
                Log.m1215w("iHealthDUM", "download firmware failed");
                obj2 = null;
                int i2 = 1;
            }
            List arrayList = new ArrayList();
            if (obj == null || r0 == null) {
                if (this.f2033c.isUpgradeState(this.f2031a, this.f2032b)) {
                    this.f2033c.stopUpgrade(this.f2031a, this.f2032b);
                }
                if (this.f2033c.f2002i != null) {
                    this.f2033c.f2002i.returnComm();
                }
                this.f2033c.m1166a(this.f2031a, this.f2032b, 200);
                return;
            }
            byte[] blockNum;
            this.f2033c.f2004k.onNotify(this.f2031a, this.f2032b, UpgradeProfile.ACTION_DEVICE_UP_DOWNLOAD_COMPLETED, null);
            if (Integer.parseInt(str4.replace(".", "")) != Integer.parseInt(str.replace(".", ""))) {
                this.f2033c.f1997d = false;
            } else {
                this.f2033c.f1997d = true;
            }
            FirmWare h = this.f2033c.m1160a();
            if (h.getBlockNum() != null) {
                blockNum = h.getBlockNum();
                arrayList.add(Byte.valueOf(blockNum[0]));
                arrayList.add(Byte.valueOf(blockNum[1]));
            }
            if (h.getFwVer() != null) {
                blockNum = h.getFwVer();
                arrayList.add(Byte.valueOf(blockNum[0]));
                arrayList.add(Byte.valueOf(blockNum[1]));
                arrayList.add(Byte.valueOf(blockNum[2]));
            }
            if (h.getFwSize() != null) {
                blockNum = h.getFwSize();
                arrayList.add(Byte.valueOf(blockNum[0]));
                arrayList.add(Byte.valueOf(blockNum[1]));
                arrayList.add(Byte.valueOf(blockNum[2]));
            }
            List fwList = h.getFwList();
            for (int i3 = 0; i3 < h.getFwAmount(); i3++) {
                FirmwareInfo firmwareInfo = (FirmwareInfo) fwList.get(i3);
                arrayList.add(Byte.valueOf(firmwareInfo.getFwVerN()[0]));
                arrayList.add(Byte.valueOf(firmwareInfo.getFwVerN()[1]));
                arrayList.add(Byte.valueOf(firmwareInfo.getFwVerN()[2]));
                arrayList.add(Byte.valueOf(firmwareInfo.getFwSizeN()[0]));
                arrayList.add(Byte.valueOf(firmwareInfo.getFwSizeN()[1]));
                arrayList.add(Byte.valueOf(firmwareInfo.getFwSizeN()[2]));
            }
            this.f2033c.f2002i = this.f2033c.getUpDeviceControl(this.f2031a, this.f2032b);
            if (this.f2033c.f2002i == null) {
                this.f2033c.f2006m = false;
                this.f2033c.f1999f = true;
                if (this.f2033c.isUpgradeState(this.f2031a, this.f2032b)) {
                    this.f2033c.stopUpgrade(this.f2031a, this.f2032b);
                    return;
                }
                return;
            }
            List i4 = this.f2033c.m1176c();
            this.f2033c.f2002i.borrowComm();
            this.f2033c.f2002i.setInformation(arrayList);
            this.f2033c.f2002i.setData(h, i4);
            this.f2033c.f2002i.startUpgrade();
        }
    }
}
