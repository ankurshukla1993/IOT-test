package com.ihealth.communication.privatecontrol;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.control.AbiControl;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.utils.Log;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AbiControlSubManager {
    private Map f2041a;
    private Map f2042b;
    private Map f2043c;
    private Map f2044d;
    private Map f2045e;
    private Map f2046f;
    private boolean f2047g;
    private AbiControlSub f2048h;
    private AbiControlSub f2049i;
    private AbiControlSub f2050j;

    private AbiControlSubManager() {
        this.f2047g = false;
    }

    private void m1201a(String str, AbiControlSub... abiControlSubArr) {
        this.f2044d.put(str, abiControlSubArr);
    }

    public static AbiControlSubManager getInstance() {
        return C2170b.f2051a;
    }

    public void addAbiControlSubDown(String mac) {
        Log.m1214v("BtAbiControlSubManager", "addAbiControlSubDown - " + mac);
        if (mac != null) {
            this.f2042b.put(mac, this.f2049i);
        }
    }

    public void addAbiControlSubUp(String mac) {
        Log.m1214v("BtAbiControlSubManager", "addAbiControlSubUp - " + mac);
        if (mac != null) {
            this.f2041a.put(mac, this.f2048h);
        }
    }

    public void createControlDown(Context context, BaseComm comm, BaseCommProtocol baseCommProtocol, String username, String mac, String type, String abiType, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f2049i = new AbiControlSub(context, comm, baseCommProtocol, username, mac, type, abiType, insCallback, mBaseCommCallback);
        this.f2049i.init();
    }

    public void createControlUnkonwn(Context context, BaseComm comm, BaseCommProtocol baseCommProtocol, String username, String mac, String type, String abiType, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f2050j = new AbiControlSub(context, comm, baseCommProtocol, username, mac, type, abiType, insCallback, mBaseCommCallback);
        this.f2050j.init();
    }

    public void createControlUp(Context context, BaseComm comm, BaseCommProtocol baseCommProtocol, String username, String mac, String type, String abiType, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f2048h = new AbiControlSub(context, comm, baseCommProtocol, username, mac, type, abiType, insCallback, mBaseCommCallback);
        this.f2048h.init();
    }

    public void destory() {
        this.f2041a.clear();
        this.f2042b.clear();
        this.f2043c.clear();
        this.f2044d.clear();
        this.f2045e.clear();
        this.f2046f.clear();
    }

    public boolean disconnect(String mac) {
        int i = 0;
        AbiControlSub abiControlSub = (AbiControlSub) this.f2041a.get(mac);
        if (abiControlSub != null) {
            abiControlSub.disconnect();
            return true;
        }
        AbiControlSub[] abiControlSubArr = (AbiControlSub[]) this.f2044d.get(mac);
        if (abiControlSubArr.length < 2) {
            return false;
        }
        int length = abiControlSubArr.length;
        while (i < length) {
            abiControlSubArr[i].disconnect();
            i++;
        }
        return true;
    }

    public String getAbiConnected() {
        String str = "";
        for (String str2 : this.f2042b.keySet()) {
        }
        return this.f2041a.size() > 0 ? str : null;
    }

    public String getAbiConnectedForArm() {
        Iterator it = this.f2041a.keySet().iterator();
        return it.hasNext() ? (String) it.next() : null;
    }

    public synchronized AbiControl getAbiControl(String mac) {
        AbiControl abiControl;
        if (mac == null) {
            Log.m1214v("BtAbiControlSubManager", "mac is null");
            abiControl = null;
        } else if (((AbiControl) this.f2043c.get(mac)) != null) {
            abiControl = (AbiControl) this.f2043c.get(mac);
        } else if (this.f2042b.size() <= 0 || this.f2041a.size() <= 0) {
            Log.m1214v("BtAbiControlSubManager", "!(mapContorlSubDown.size() > 0 && mapContorlSubUp.size() > 0)");
            abiControl = null;
        } else {
            AbiControlSub abiControlSub;
            AbiControlSub abiControlSub2 = (AbiControlSub) this.f2042b.get(mac);
            Iterator it = this.f2041a.keySet().iterator();
            if (it.hasNext()) {
                abiControlSub = (AbiControlSub) this.f2041a.get((String) it.next());
            } else {
                abiControlSub = null;
            }
            if (abiControlSub2 == null || abiControlSub == null) {
                abiControl = null;
            } else {
                AbiControl abiControl2 = new AbiControl(mac);
                this.f2043c.put(mac, abiControl2);
                m1201a(mac, abiControlSub, abiControlSub2);
                abiControl = abiControl2;
            }
        }
        return abiControl;
    }

    public synchronized AbiControl getAbiControlforUp(String mac) {
        AbiControl abiControl;
        if (mac == null) {
            abiControl = null;
        } else if (((AbiControl) this.f2043c.get(mac)) != null) {
            abiControl = (AbiControl) this.f2043c.get(mac);
        } else if (this.f2041a.size() <= 0) {
            abiControl = null;
        } else if (((AbiControlSub) this.f2041a.get(mac)) != null) {
            abiControl = new AbiControl(mac);
            this.f2043c.put(mac, abiControl);
        } else {
            abiControl = null;
        }
        return abiControl;
    }

    public boolean getBattery(String mac) {
        int i = 0;
        AbiControlSub abiControlSub = (AbiControlSub) this.f2041a.get(mac);
        if (abiControlSub != null) {
            abiControlSub.getBattery();
            return true;
        }
        AbiControlSub[] abiControlSubArr = (AbiControlSub[]) this.f2044d.get(mac);
        if (abiControlSubArr == null || abiControlSubArr.length < 2) {
            return false;
        }
        int length = abiControlSubArr.length;
        while (i < length) {
            abiControlSubArr[i].getBattery();
            i++;
        }
        return true;
    }

    public void init() {
        this.f2041a = new ConcurrentHashMap();
        this.f2042b = new ConcurrentHashMap();
        this.f2043c = new ConcurrentHashMap();
        this.f2044d = new ConcurrentHashMap();
        this.f2045e = new ConcurrentHashMap();
        this.f2046f = new ConcurrentHashMap();
    }

    public boolean notNeedWaitZore(String mAddress) {
        if (this.f2047g) {
            return ((Boolean) this.f2045e.get(mAddress)).booleanValue();
        }
        String abiConnectedForArm = getAbiConnectedForArm();
        String abiConnected = getAbiConnected();
        if (abiConnectedForArm == null || abiConnected == null) {
            return false;
        }
        return this.f2045e.get(abiConnectedForArm) != null && this.f2045e.get(abiConnected) != null && ((Boolean) this.f2045e.get(abiConnectedForArm)).booleanValue() && ((Boolean) this.f2045e.get(abiConnected)).booleanValue();
    }

    public boolean notNneedWaitPressure(String mAddress) {
        if (this.f2047g) {
            return ((Boolean) this.f2046f.get(mAddress)).booleanValue();
        }
        String abiConnectedForArm = getAbiConnectedForArm();
        String abiConnected = getAbiConnected();
        if (abiConnectedForArm == null || abiConnected == null) {
            return false;
        }
        return this.f2045e.get(abiConnectedForArm) != null && this.f2045e.get(abiConnected) != null && ((Boolean) this.f2045e.get(abiConnectedForArm)).booleanValue() && ((Boolean) this.f2045e.get(abiConnected)).booleanValue();
    }

    public void remove(String mac) {
        this.f2041a.remove(mac);
        this.f2042b.remove(mac);
        this.f2043c.remove(mac);
        this.f2044d.remove(mac);
    }

    public void setPressure(String mac) {
        this.f2046f.put(mac, Boolean.valueOf(true));
    }

    public void setZore(String mac) {
        this.f2045e.put(mac, Boolean.valueOf(true));
    }

    public boolean startMeasure(String mac) {
        int i = 0;
        this.f2045e.clear();
        this.f2046f.clear();
        this.f2047g = false;
        AbiControlSub abiControlSub = (AbiControlSub) this.f2041a.get(mac);
        if (abiControlSub != null) {
            abiControlSub.startMeasure();
            this.f2047g = true;
            return true;
        }
        AbiControlSub[] abiControlSubArr = (AbiControlSub[]) this.f2044d.get(mac);
        if (abiControlSubArr == null || abiControlSubArr.length < 2) {
            return false;
        }
        int length = abiControlSubArr.length;
        while (i < length) {
            abiControlSubArr[i].startMeasure();
            i++;
        }
        return true;
    }

    public boolean stopMeasure(String mac) {
        int i = 0;
        AbiControlSub abiControlSub = (AbiControlSub) this.f2041a.get(mac);
        if (abiControlSub != null) {
            abiControlSub.interruptMeasure();
            return true;
        }
        AbiControlSub[] abiControlSubArr = (AbiControlSub[]) this.f2044d.get(mac);
        if (abiControlSubArr.length < 2) {
            return false;
        }
        int length = abiControlSubArr.length;
        while (i < length) {
            abiControlSubArr[i].interruptMeasure();
            i++;
        }
        return true;
    }
}
