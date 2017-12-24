package org.webrtc;

import android.content.Context;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.webrtc.NetworkMonitorAutoDetect.ConnectionType;
import org.webrtc.NetworkMonitorAutoDetect.NetworkInformation;
import org.webrtc.NetworkMonitorAutoDetect.Observer;

public class NetworkMonitor {
    private static final String TAG = "NetworkMonitor";
    private static NetworkMonitor instance;
    private final Context applicationContext;
    private NetworkMonitorAutoDetect autoDetector;
    private ConnectionType currentConnectionType = ConnectionType.CONNECTION_UNKNOWN;
    private final ArrayList<Long> nativeNetworkObservers;
    private final ArrayList<NetworkObserver> networkObservers;

    class C26141 implements Observer {
        C26141() {
        }

        public void onConnectionTypeChanged(ConnectionType newConnectionType) {
            NetworkMonitor.this.updateCurrentConnectionType(newConnectionType);
        }

        public void onNetworkConnect(NetworkInformation networkInfo) {
            NetworkMonitor.this.notifyObserversOfNetworkConnect(networkInfo);
        }

        public void onNetworkDisconnect(long networkHandle) {
            NetworkMonitor.this.notifyObserversOfNetworkDisconnect(networkHandle);
        }
    }

    public interface NetworkObserver {
        void onConnectionTypeChanged(ConnectionType connectionType);
    }

    private native void nativeNotifyConnectionTypeChanged(long j);

    private native void nativeNotifyOfActiveNetworkList(long j, NetworkInformation[] networkInformationArr);

    private native void nativeNotifyOfNetworkConnect(long j, NetworkInformation networkInformation);

    private native void nativeNotifyOfNetworkDisconnect(long j, long j2);

    private NetworkMonitor(Context context) {
        assertIsTrue(context != null);
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.applicationContext = context;
        this.nativeNetworkObservers = new ArrayList();
        this.networkObservers = new ArrayList();
    }

    public static NetworkMonitor init(Context context) {
        if (!isInitialized()) {
            instance = new NetworkMonitor(context);
        }
        return instance;
    }

    public static boolean isInitialized() {
        return instance != null;
    }

    public static NetworkMonitor getInstance() {
        return instance;
    }

    public static void setAutoDetectConnectivityState(boolean shouldAutoDetect) {
        getInstance().setAutoDetectConnectivityStateInternal(shouldAutoDetect);
    }

    private static void assertIsTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected to be true");
        }
    }

    private void startMonitoring(long nativeObserver) {
        Logging.m2187d(TAG, "Start monitoring from native observer " + nativeObserver);
        this.nativeNetworkObservers.add(Long.valueOf(nativeObserver));
        setAutoDetectConnectivityStateInternal(true);
    }

    private void stopMonitoring(long nativeObserver) {
        Logging.m2187d(TAG, "Stop monitoring from native observer " + nativeObserver);
        setAutoDetectConnectivityStateInternal(false);
        this.nativeNetworkObservers.remove(Long.valueOf(nativeObserver));
    }

    private boolean networkBindingSupported() {
        return this.autoDetector != null && this.autoDetector.supportNetworkCallback();
    }

    private static int androidSdkInt() {
        return VERSION.SDK_INT;
    }

    private ConnectionType getCurrentConnectionType() {
        return this.currentConnectionType;
    }

    private long getCurrentDefaultNetId() {
        return this.autoDetector == null ? -1 : this.autoDetector.getDefaultNetId();
    }

    private void destroyAutoDetector() {
        if (this.autoDetector != null) {
            this.autoDetector.destroy();
            this.autoDetector = null;
        }
    }

    private void setAutoDetectConnectivityStateInternal(boolean shouldAutoDetect) {
        if (!shouldAutoDetect) {
            destroyAutoDetector();
        } else if (this.autoDetector == null) {
            this.autoDetector = new NetworkMonitorAutoDetect(new C26141(), this.applicationContext);
            updateCurrentConnectionType(NetworkMonitorAutoDetect.getConnectionType(this.autoDetector.getCurrentNetworkState()));
            updateActiveNetworkList();
        }
    }

    private void updateCurrentConnectionType(ConnectionType newConnectionType) {
        this.currentConnectionType = newConnectionType;
        notifyObserversOfConnectionTypeChange(newConnectionType);
    }

    private void notifyObserversOfConnectionTypeChange(ConnectionType newConnectionType) {
        Iterator it = this.nativeNetworkObservers.iterator();
        while (it.hasNext()) {
            nativeNotifyConnectionTypeChanged(((Long) it.next()).longValue());
        }
        Iterator it2 = this.networkObservers.iterator();
        while (it2.hasNext()) {
            ((NetworkObserver) it2.next()).onConnectionTypeChanged(newConnectionType);
        }
    }

    private void notifyObserversOfNetworkConnect(NetworkInformation networkInfo) {
        Iterator it = this.nativeNetworkObservers.iterator();
        while (it.hasNext()) {
            nativeNotifyOfNetworkConnect(((Long) it.next()).longValue(), networkInfo);
        }
    }

    private void notifyObserversOfNetworkDisconnect(long networkHandle) {
        Iterator it = this.nativeNetworkObservers.iterator();
        while (it.hasNext()) {
            nativeNotifyOfNetworkDisconnect(((Long) it.next()).longValue(), networkHandle);
        }
    }

    private void updateActiveNetworkList() {
        List<NetworkInformation> networkInfoList = this.autoDetector.getActiveNetworkList();
        if (networkInfoList != null && networkInfoList.size() != 0) {
            NetworkInformation[] networkInfos = (NetworkInformation[]) networkInfoList.toArray(new NetworkInformation[networkInfoList.size()]);
            Iterator it = this.nativeNetworkObservers.iterator();
            while (it.hasNext()) {
                nativeNotifyOfActiveNetworkList(((Long) it.next()).longValue(), networkInfos);
            }
        }
    }

    public static void addNetworkObserver(NetworkObserver observer) {
        getInstance().addNetworkObserverInternal(observer);
    }

    private void addNetworkObserverInternal(NetworkObserver observer) {
        this.networkObservers.add(observer);
    }

    public static void removeNetworkObserver(NetworkObserver observer) {
        getInstance().removeNetworkObserverInternal(observer);
    }

    private void removeNetworkObserverInternal(NetworkObserver observer) {
        this.networkObservers.remove(observer);
    }

    public static boolean isOnline() {
        return getInstance().getCurrentConnectionType() != ConnectionType.CONNECTION_NONE;
    }

    static void resetInstanceForTests(Context context) {
        instance = new NetworkMonitor(context);
    }

    public static NetworkMonitorAutoDetect getAutoDetectorForTest() {
        return getInstance().autoDetector;
    }
}
