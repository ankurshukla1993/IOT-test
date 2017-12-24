package com.ihealth.communication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import java.lang.reflect.Method;
import java.util.List;

public class WifiAdmin {
    private final Context f2116a;
    private WifiManager f2117b;
    private List f2118c;
    private List f2119d;

    public WifiAdmin(Context context) {
        this.f2116a = context;
        this.f2117b = (WifiManager) context.getSystemService("wifi");
    }

    private WifiConfiguration m1230a(String str) {
        for (WifiConfiguration wifiConfiguration : this.f2117b.getConfiguredNetworks()) {
            if (wifiConfiguration.SSID.equals("\"" + str + "\"")) {
                return wifiConfiguration;
            }
        }
        return null;
    }

    private String m1231a(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    private Method m1232b(int i) {
        Method method;
        Method[] declaredMethods;
        int length;
        int i2;
        Method method2;
        Class[] parameterTypes;
        if (VERSION.SDK_INT >= 17) {
            declaredMethods = this.f2117b.getClass().getDeclaredMethods();
            length = declaredMethods.length;
            i2 = 0;
            method = null;
            while (i2 < length) {
                method2 = declaredMethods[i2];
                if ("connect".equalsIgnoreCase(method2.getName())) {
                    parameterTypes = method2.getParameterTypes();
                    if (parameterTypes != null && parameterTypes.length > 0 && "int".equalsIgnoreCase(parameterTypes[0].getName())) {
                        i2++;
                        method = method2;
                    }
                }
                method2 = method;
                i2++;
                method = method2;
            }
            if (method != null) {
                try {
                    method.invoke(this.f2117b, new Object[]{Integer.valueOf(i), null});
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        } else if (VERSION.SDK_INT == 16) {
            method = null;
        } else if (VERSION.SDK_INT < 14 || VERSION.SDK_INT >= 16) {
            return null;
        } else {
            declaredMethods = this.f2117b.getClass().getDeclaredMethods();
            length = declaredMethods.length;
            i2 = 0;
            method = null;
            while (i2 < length) {
                method2 = declaredMethods[i2];
                if ("connectNetwork".equalsIgnoreCase(method2.getName())) {
                    parameterTypes = method2.getParameterTypes();
                    if (parameterTypes != null && parameterTypes.length > 0 && "int".equalsIgnoreCase(parameterTypes[0].getName())) {
                        i2++;
                        method = method2;
                    }
                }
                method2 = method;
                i2++;
                method = method2;
            }
            if (method != null) {
                try {
                    method.invoke(this.f2117b, new Object[]{Integer.valueOf(i)});
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
        }
        return method;
    }

    public boolean addNetwork(WifiConfiguration wcg) {
        int addNetwork = this.f2117b.addNetwork(wcg);
        return m1232b(addNetwork) != null || this.f2117b.enableNetwork(addNetwork, true);
    }

    public int checkState() {
        return this.f2117b.getWifiState();
    }

    public void closeWifi() {
        if (this.f2117b.isWifiEnabled()) {
            this.f2117b.setWifiEnabled(false);
        }
    }

    public void connectConfiguration(int networkId) {
        this.f2117b.enableNetwork(networkId, true);
    }

    public WifiConfiguration createWifiInfo(String ssid, String password, int type) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = "\"" + ssid + "\"";
        WifiConfiguration a = m1230a(ssid);
        if (a != null) {
            this.f2117b.removeNetwork(a.networkId);
        }
        if (type == 1) {
            wifiConfiguration.wepKeys[0] = "\"\"";
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.wepTxKeyIndex = 0;
        }
        if (type == 2) {
            wifiConfiguration.hiddenSSID = true;
            wifiConfiguration.wepKeys[0] = "\"" + password + "\"";
            wifiConfiguration.allowedAuthAlgorithms.set(1);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedGroupCiphers.set(0);
            wifiConfiguration.allowedGroupCiphers.set(1);
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.wepTxKeyIndex = 0;
        }
        if (type == 3) {
            wifiConfiguration.preSharedKey = "\"" + password + "\"";
            wifiConfiguration.hiddenSSID = true;
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedKeyManagement.set(1);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.status = 2;
        }
        return wifiConfiguration;
    }

    public void disconnectWifi(int netId) {
        this.f2117b.disableNetwork(netId);
        this.f2117b.disconnect();
    }

    public List getConfiguration() {
        return this.f2119d;
    }

    public String getIPAddress() {
        WifiInfo connectionInfo = this.f2117b.getConnectionInfo();
        return connectionInfo == null ? "" : m1231a(connectionInfo.getIpAddress());
    }

    public String getMacAddress() {
        WifiInfo connectionInfo = this.f2117b.getConnectionInfo();
        return connectionInfo == null ? "NULL" : connectionInfo.getMacAddress();
    }

    public int getNetworkId() {
        WifiInfo connectionInfo = this.f2117b.getConnectionInfo();
        return connectionInfo == null ? 0 : connectionInfo.getNetworkId();
    }

    public String getSSID() {
        WifiInfo connectionInfo = this.f2117b.getConnectionInfo();
        return connectionInfo == null ? null : connectionInfo.getSSID();
    }

    public String getServerAddress() {
        DhcpInfo dhcpInfo = this.f2117b.getDhcpInfo();
        return dhcpInfo == null ? "" : m1231a(dhcpInfo.serverAddress);
    }

    public WifiInfo getWifiInfo() {
        WifiInfo connectionInfo = this.f2117b.getConnectionInfo();
        return connectionInfo == null ? null : connectionInfo;
    }

    public List getWifiList() {
        this.f2118c = this.f2117b.getScanResults();
        this.f2119d = this.f2117b.getConfiguredNetworks();
        return this.f2118c;
    }

    public boolean isWifiConnected() {
        return ((ConnectivityManager) this.f2116a.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
    }

    public boolean isWifiEnabled() {
        return this.f2117b.isWifiEnabled();
    }

    public StringBuilder lookUpScan() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.f2118c.size(); i++) {
            stringBuilder.append("Index_").append(Integer.valueOf(i + 1).toString()).append(":");
            stringBuilder.append(((ScanResult) this.f2118c.get(i)).toString());
            stringBuilder.append("/n");
        }
        return stringBuilder;
    }

    public void openWifi() {
        if (!this.f2117b.isWifiEnabled()) {
            this.f2117b.setWifiEnabled(true);
        }
    }

    public void startScan() {
        this.f2117b.startScan();
    }
}
