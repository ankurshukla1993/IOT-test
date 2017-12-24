package org.webrtc;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest.Builder;
import android.net.wifi.WifiInfo;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.List;

public class NetworkMonitorAutoDetect extends BroadcastReceiver {
    static final long INVALID_NET_ID = -1;
    private static final String TAG = "NetworkMonitorAutoDetect";
    private final NetworkCallback allNetworkCallback;
    private ConnectionType connectionType;
    private ConnectivityManagerDelegate connectivityManagerDelegate;
    private final Context context;
    private final IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    private boolean isRegistered;
    private final NetworkCallback mobileNetworkCallback;
    private final Observer observer;
    private WifiManagerDelegate wifiManagerDelegate;
    private String wifiSSID;

    public interface Observer {
        void onConnectionTypeChanged(ConnectionType connectionType);

        void onNetworkConnect(NetworkInformation networkInformation);

        void onNetworkDisconnect(long j);
    }

    public enum ConnectionType {
        CONNECTION_UNKNOWN,
        CONNECTION_ETHERNET,
        CONNECTION_WIFI,
        CONNECTION_4G,
        CONNECTION_3G,
        CONNECTION_2G,
        CONNECTION_UNKNOWN_CELLULAR,
        CONNECTION_BLUETOOTH,
        CONNECTION_NONE
    }

    static class ConnectivityManagerDelegate {
        private final ConnectivityManager connectivityManager;

        ConnectivityManagerDelegate(Context context) {
            this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        }

        ConnectivityManagerDelegate() {
            this.connectivityManager = null;
        }

        NetworkState getNetworkState() {
            if (this.connectivityManager == null) {
                return new NetworkState(false, -1, -1);
            }
            return getNetworkState(this.connectivityManager.getActiveNetworkInfo());
        }

        @SuppressLint({"NewApi"})
        NetworkState getNetworkState(Network network) {
            if (this.connectivityManager == null) {
                return new NetworkState(false, -1, -1);
            }
            return getNetworkState(this.connectivityManager.getNetworkInfo(network));
        }

        NetworkState getNetworkState(NetworkInfo networkInfo) {
            if (networkInfo == null || !networkInfo.isConnected()) {
                return new NetworkState(false, -1, -1);
            }
            return new NetworkState(true, networkInfo.getType(), networkInfo.getSubtype());
        }

        @SuppressLint({"NewApi"})
        Network[] getAllNetworks() {
            if (this.connectivityManager == null) {
                return new Network[0];
            }
            return this.connectivityManager.getAllNetworks();
        }

        List<NetworkInformation> getActiveNetworkList() {
            if (!supportNetworkCallback()) {
                return null;
            }
            List<NetworkInformation> netInfoList = new ArrayList();
            for (Network network : getAllNetworks()) {
                NetworkInformation info = networkToInfo(network);
                if (info != null) {
                    netInfoList.add(info);
                }
            }
            return netInfoList;
        }

        @SuppressLint({"NewApi"})
        long getDefaultNetId() {
            if (!supportNetworkCallback()) {
                return -1;
            }
            NetworkInfo defaultNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
            if (defaultNetworkInfo == null) {
                return -1;
            }
            long defaultNetId = -1;
            for (Network network : getAllNetworks()) {
                if (hasInternetCapability(network)) {
                    NetworkInfo networkInfo = this.connectivityManager.getNetworkInfo(network);
                    if (networkInfo != null && networkInfo.getType() == defaultNetworkInfo.getType()) {
                        if (defaultNetId != -1) {
                            throw new RuntimeException("Multiple connected networks of same type are not supported.");
                        }
                        defaultNetId = NetworkMonitorAutoDetect.networkToNetId(network);
                    }
                }
            }
            return defaultNetId;
        }

        @SuppressLint({"NewApi"})
        private NetworkInformation networkToInfo(Network network) {
            LinkProperties linkProperties = this.connectivityManager.getLinkProperties(network);
            if (linkProperties == null) {
                Logging.m2191w(NetworkMonitorAutoDetect.TAG, "Detected unknown network: " + network.toString());
                return null;
            } else if (linkProperties.getInterfaceName() == null) {
                Logging.m2191w(NetworkMonitorAutoDetect.TAG, "Null interface name for network " + network.toString());
                return null;
            } else {
                NetworkState networkState = getNetworkState(network);
                if (networkState.connected && networkState.getNetworkType() == 17) {
                    networkState = getNetworkState();
                }
                ConnectionType connectionType = NetworkMonitorAutoDetect.getConnectionType(networkState);
                if (connectionType == ConnectionType.CONNECTION_NONE) {
                    Logging.m2187d(NetworkMonitorAutoDetect.TAG, "Network " + network.toString() + " is disconnected");
                    return null;
                }
                if (connectionType == ConnectionType.CONNECTION_UNKNOWN || connectionType == ConnectionType.CONNECTION_UNKNOWN_CELLULAR) {
                    Logging.m2187d(NetworkMonitorAutoDetect.TAG, "Network " + network.toString() + " connection type is " + connectionType + " because it has type " + networkState.getNetworkType() + " and subtype " + networkState.getNetworkSubType());
                }
                return new NetworkInformation(linkProperties.getInterfaceName(), connectionType, NetworkMonitorAutoDetect.networkToNetId(network), getIPAddresses(linkProperties));
            }
        }

        @SuppressLint({"NewApi"})
        boolean hasInternetCapability(Network network) {
            if (this.connectivityManager == null) {
                return false;
            }
            NetworkCapabilities capabilities = this.connectivityManager.getNetworkCapabilities(network);
            if (capabilities == null || !capabilities.hasCapability(12)) {
                return false;
            }
            return true;
        }

        @SuppressLint({"NewApi"})
        public void registerNetworkCallback(NetworkCallback networkCallback) {
            this.connectivityManager.registerNetworkCallback(new Builder().addCapability(12).build(), networkCallback);
        }

        @SuppressLint({"NewApi"})
        public void requestMobileNetwork(NetworkCallback networkCallback) {
            Builder builder = new Builder();
            builder.addCapability(12).addTransportType(0);
            this.connectivityManager.requestNetwork(builder.build(), networkCallback);
        }

        @SuppressLint({"NewApi"})
        IPAddress[] getIPAddresses(LinkProperties linkProperties) {
            IPAddress[] ipAddresses = new IPAddress[linkProperties.getLinkAddresses().size()];
            int i = 0;
            for (LinkAddress linkAddress : linkProperties.getLinkAddresses()) {
                ipAddresses[i] = new IPAddress(linkAddress.getAddress().getAddress());
                i++;
            }
            return ipAddresses;
        }

        @SuppressLint({"NewApi"})
        public void releaseCallback(NetworkCallback networkCallback) {
            if (supportNetworkCallback()) {
                Logging.m2187d(NetworkMonitorAutoDetect.TAG, "Unregister network callback");
                this.connectivityManager.unregisterNetworkCallback(networkCallback);
            }
        }

        public boolean supportNetworkCallback() {
            return VERSION.SDK_INT >= 21 && this.connectivityManager != null;
        }
    }

    public static class IPAddress {
        public final byte[] address;

        public IPAddress(byte[] address) {
            this.address = address;
        }
    }

    public static class NetworkInformation {
        public final long handle;
        public final IPAddress[] ipAddresses;
        public final String name;
        public final ConnectionType type;

        public NetworkInformation(String name, ConnectionType type, long handle, IPAddress[] addresses) {
            this.name = name;
            this.type = type;
            this.handle = handle;
            this.ipAddresses = addresses;
        }
    }

    static class NetworkState {
        private final boolean connected;
        private final int subtype;
        private final int type;

        public NetworkState(boolean connected, int type, int subtype) {
            this.connected = connected;
            this.type = type;
            this.subtype = subtype;
        }

        public boolean isConnected() {
            return this.connected;
        }

        public int getNetworkType() {
            return this.type;
        }

        public int getNetworkSubType() {
            return this.subtype;
        }
    }

    @SuppressLint({"NewApi"})
    private class SimpleNetworkCallback extends NetworkCallback {
        private SimpleNetworkCallback() {
        }

        public void onAvailable(Network network) {
            Logging.m2187d(NetworkMonitorAutoDetect.TAG, "Network becomes available: " + network.toString());
            onNetworkChanged(network);
        }

        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            Logging.m2187d(NetworkMonitorAutoDetect.TAG, "capabilities changed: " + networkCapabilities.toString());
            onNetworkChanged(network);
        }

        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            Logging.m2187d(NetworkMonitorAutoDetect.TAG, "link properties changed: " + linkProperties.toString());
            onNetworkChanged(network);
        }

        public void onLosing(Network network, int maxMsToLive) {
            Logging.m2187d(NetworkMonitorAutoDetect.TAG, "Network " + network.toString() + " is about to lose in " + maxMsToLive + "ms");
        }

        public void onLost(Network network) {
            Logging.m2187d(NetworkMonitorAutoDetect.TAG, "Network " + network.toString() + " is disconnected");
            NetworkMonitorAutoDetect.this.observer.onNetworkDisconnect(NetworkMonitorAutoDetect.networkToNetId(network));
        }

        private void onNetworkChanged(Network network) {
            NetworkInformation networkInformation = NetworkMonitorAutoDetect.this.connectivityManagerDelegate.networkToInfo(network);
            if (networkInformation != null) {
                NetworkMonitorAutoDetect.this.observer.onNetworkConnect(networkInformation);
            }
        }
    }

    static class WifiManagerDelegate {
        private final Context context;

        WifiManagerDelegate(Context context) {
            this.context = context;
        }

        WifiManagerDelegate() {
            this.context = null;
        }

        String getWifiSSID() {
            Intent intent = this.context.registerReceiver(null, new IntentFilter("android.net.wifi.STATE_CHANGE"));
            if (intent != null) {
                WifiInfo wifiInfo = (WifiInfo) intent.getParcelableExtra("wifiInfo");
                if (wifiInfo != null) {
                    String ssid = wifiInfo.getSSID();
                    if (ssid != null) {
                        return ssid;
                    }
                }
            }
            return "";
        }
    }

    @SuppressLint({"NewApi"})
    public NetworkMonitorAutoDetect(Observer observer, Context context) {
        this.observer = observer;
        this.context = context;
        this.connectivityManagerDelegate = new ConnectivityManagerDelegate(context);
        this.wifiManagerDelegate = new WifiManagerDelegate(context);
        NetworkState networkState = this.connectivityManagerDelegate.getNetworkState();
        this.connectionType = getConnectionType(networkState);
        this.wifiSSID = getWifiSSID(networkState);
        registerReceiver();
        if (this.connectivityManagerDelegate.supportNetworkCallback()) {
            NetworkCallback tempNetworkCallback = new NetworkCallback();
            try {
                this.connectivityManagerDelegate.requestMobileNetwork(tempNetworkCallback);
            } catch (SecurityException e) {
                Logging.m2191w(TAG, "Unable to obtain permission to request a cellular network.");
                tempNetworkCallback = null;
            }
            this.mobileNetworkCallback = tempNetworkCallback;
            this.allNetworkCallback = new SimpleNetworkCallback();
            this.connectivityManagerDelegate.registerNetworkCallback(this.allNetworkCallback);
            return;
        }
        this.mobileNetworkCallback = null;
        this.allNetworkCallback = null;
    }

    public boolean supportNetworkCallback() {
        return this.connectivityManagerDelegate.supportNetworkCallback();
    }

    void setConnectivityManagerDelegateForTests(ConnectivityManagerDelegate delegate) {
        this.connectivityManagerDelegate = delegate;
    }

    void setWifiManagerDelegateForTests(WifiManagerDelegate delegate) {
        this.wifiManagerDelegate = delegate;
    }

    boolean isReceiverRegisteredForTesting() {
        return this.isRegistered;
    }

    List<NetworkInformation> getActiveNetworkList() {
        return this.connectivityManagerDelegate.getActiveNetworkList();
    }

    public void destroy() {
        if (this.allNetworkCallback != null) {
            this.connectivityManagerDelegate.releaseCallback(this.allNetworkCallback);
        }
        if (this.mobileNetworkCallback != null) {
            this.connectivityManagerDelegate.releaseCallback(this.mobileNetworkCallback);
        }
        unregisterReceiver();
    }

    private void registerReceiver() {
        if (!this.isRegistered) {
            this.isRegistered = true;
            this.context.registerReceiver(this, this.intentFilter);
        }
    }

    private void unregisterReceiver() {
        if (this.isRegistered) {
            this.isRegistered = false;
            this.context.unregisterReceiver(this);
        }
    }

    public NetworkState getCurrentNetworkState() {
        return this.connectivityManagerDelegate.getNetworkState();
    }

    public long getDefaultNetId() {
        return this.connectivityManagerDelegate.getDefaultNetId();
    }

    public static ConnectionType getConnectionType(NetworkState networkState) {
        if (!networkState.isConnected()) {
            return ConnectionType.CONNECTION_NONE;
        }
        switch (networkState.getNetworkType()) {
            case 0:
                switch (networkState.getNetworkSubType()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return ConnectionType.CONNECTION_2G;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return ConnectionType.CONNECTION_3G;
                    case 13:
                        return ConnectionType.CONNECTION_4G;
                    default:
                        return ConnectionType.CONNECTION_UNKNOWN_CELLULAR;
                }
            case 1:
                return ConnectionType.CONNECTION_WIFI;
            case 6:
                return ConnectionType.CONNECTION_4G;
            case 7:
                return ConnectionType.CONNECTION_BLUETOOTH;
            case 9:
                return ConnectionType.CONNECTION_ETHERNET;
            default:
                return ConnectionType.CONNECTION_UNKNOWN;
        }
    }

    private String getWifiSSID(NetworkState networkState) {
        if (getConnectionType(networkState) != ConnectionType.CONNECTION_WIFI) {
            return "";
        }
        return this.wifiManagerDelegate.getWifiSSID();
    }

    public void onReceive(Context context, Intent intent) {
        NetworkState networkState = getCurrentNetworkState();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            connectionTypeChanged(networkState);
        }
    }

    private void connectionTypeChanged(NetworkState networkState) {
        ConnectionType newConnectionType = getConnectionType(networkState);
        String newWifiSSID = getWifiSSID(networkState);
        if (newConnectionType != this.connectionType || !newWifiSSID.equals(this.wifiSSID)) {
            this.connectionType = newConnectionType;
            this.wifiSSID = newWifiSSID;
            Logging.m2187d(TAG, "Network connectivity changed, type is: " + this.connectionType);
            this.observer.onConnectionTypeChanged(newConnectionType);
        }
    }

    @SuppressLint({"NewApi"})
    private static long networkToNetId(Network network) {
        if (VERSION.SDK_INT >= 23) {
            return network.getNetworkHandle();
        }
        return (long) Integer.parseInt(network.toString());
    }
}
