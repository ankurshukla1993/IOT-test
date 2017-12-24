package com.ihealth.communication.clientmanager;

import com.ihealth.communication.manager.iHealthDevicesCallback;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class iHealthDeviceClientMap {
    private Map f469a = new ConcurrentHashMap();
    private Map f470b = new ConcurrentHashMap();
    private int f471c = 0;
    private final Object f472d = new Object();

    public int add(iHealthDevicesCallback miHealthDevicesCallback) {
        int i;
        synchronized (this.f472d) {
            this.f471c++;
            this.f469a.put(Integer.valueOf(this.f471c), miHealthDevicesCallback);
            i = this.f471c;
        }
        return i;
    }

    public void addCallbackFilter(int clientId, String[] filterStr) {
        String str = null;
        for (String str2 : filterStr) {
            str = str + str2;
        }
        String str3 = (String) this.f470b.get(Integer.valueOf(clientId));
        if (str3 != null) {
            this.f470b.put(Integer.valueOf(clientId), str3 + str);
        } else {
            this.f470b.put(Integer.valueOf(clientId), str);
        }
    }

    public void clear() {
        synchronized (this.f472d) {
            this.f471c = 0;
            this.f469a.clear();
            this.f470b.clear();
        }
    }

    public iHealthDevicesCallback getCallback(int clientId) {
        return (iHealthDevicesCallback) this.f469a.get(Integer.valueOf(clientId));
    }

    public synchronized List getCallbacklist(String mac, String type) {
        List arrayList;
        arrayList = new ArrayList();
        for (Integer intValue : this.f469a.keySet()) {
            int intValue2 = intValue.intValue();
            String str = (String) this.f470b.get(Integer.valueOf(intValue2));
            if (str == null) {
                arrayList.add(this.f469a.get(Integer.valueOf(intValue2)));
            } else if (str.contains(mac) || str.contains(type)) {
                arrayList.add(this.f469a.get(Integer.valueOf(intValue2)));
            }
        }
        return arrayList;
    }

    public List getCallbacklist_All() {
        List arrayList = new ArrayList();
        for (Integer intValue : this.f469a.keySet()) {
            arrayList.add(this.f469a.get(Integer.valueOf(intValue.intValue())));
        }
        return arrayList;
    }

    public void remove(int clientId) {
        synchronized (this.f472d) {
            this.f469a.remove(Integer.valueOf(clientId));
            this.f470b.remove(Integer.valueOf(clientId));
        }
    }
}
