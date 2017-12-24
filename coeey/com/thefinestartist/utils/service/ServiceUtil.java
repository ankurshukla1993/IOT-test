package com.thefinestartist.utils.service;

import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.NetworkStatsManager;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.content.RestrictionsManager;
import android.content.pm.LauncherApps;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.midi.MidiManager;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.DropBoxManager;
import android.os.PowerManager;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;
import com.facebook.internal.ServerProtocol;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.firebase.auth.PhoneAuthProvider;
import com.ihealth.communication.control.AmProfile;
import com.thefinestartist.Base;

public class ServiceUtil {
    private ServiceUtil() {
    }

    public static Object getSystemService(@NonNull String serviceName) {
        return Base.getContext().getSystemService(serviceName);
    }

    public static AccessibilityManager getAccessibilityManager() {
        return (AccessibilityManager) getSystemService("accessibility");
    }

    @TargetApi(19)
    public static CaptioningManager getCaptioningManager() {
        return (CaptioningManager) getSystemService("captioning");
    }

    public static AccountManager getAccountManager() {
        return (AccountManager) getSystemService("account");
    }

    public static ActivityManager getActivityManager() {
        return (ActivityManager) getSystemService(AmProfile.SYNC_ACTIVITY_DATA_AM);
    }

    public static AlarmManager getAlarmManager() {
        return (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    public static AudioManager getAudioManager() {
        return (AudioManager) getSystemService("audio");
    }

    @TargetApi(16)
    public static MediaRouter getMediaRouter() {
        return (MediaRouter) getSystemService("media_router");
    }

    @TargetApi(18)
    public static BluetoothManager getBluetoothManager() {
        return (BluetoothManager) getSystemService("bluetooth");
    }

    public static ClipboardManager getClipboardManager() {
        return (ClipboardManager) getSystemService("clipboard");
    }

    public static ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) getSystemService("connectivity");
    }

    @TargetApi(8)
    public static DevicePolicyManager getDevicePolicyManager() {
        return (DevicePolicyManager) getSystemService("device_policy");
    }

    @TargetApi(9)
    public static DownloadManager getDownloadManager() {
        return (DownloadManager) getSystemService("download");
    }

    @TargetApi(21)
    public static BatteryManager getBatteryManager() {
        return (BatteryManager) getSystemService("batterymanager");
    }

    @TargetApi(10)
    public static NfcManager getNfcManager() {
        return (NfcManager) getSystemService("nfc");
    }

    @TargetApi(8)
    public static DropBoxManager getDropBoxManager() {
        return (DropBoxManager) getSystemService("dropbox");
    }

    @TargetApi(16)
    public static InputManager getInputManager() {
        return (InputManager) getSystemService("input");
    }

    @TargetApi(17)
    public static DisplayManager getDisplayManager() {
        return (DisplayManager) getSystemService(ServerProtocol.DIALOG_PARAM_DISPLAY);
    }

    public static InputMethodManager getInputMethodManager() {
        return (InputMethodManager) getSystemService("input_method");
    }

    @TargetApi(14)
    public static TextServicesManager getTextServicesManager() {
        return (TextServicesManager) getSystemService("textservices");
    }

    public static KeyguardManager getKeyguardManager() {
        return (KeyguardManager) getSystemService("keyguard");
    }

    public static LayoutInflater getLayoutInflater() {
        return (LayoutInflater) getSystemService("layout_inflater");
    }

    public static LocationManager getLocationManager() {
        return (LocationManager) getSystemService(Param.LOCATION);
    }

    public static NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService("notification");
    }

    @TargetApi(16)
    public static NsdManager getNsdManager() {
        return (NsdManager) getSystemService("servicediscovery");
    }

    public static PowerManager getPowerManager() {
        return (PowerManager) getSystemService("power");
    }

    public static SearchManager getSearchManager() {
        return (SearchManager) getSystemService(Event.SEARCH);
    }

    public static SensorManager getSensorManager() {
        return (SensorManager) getSystemService("sensor");
    }

    @TargetApi(9)
    public static StorageManager getStorageManager() {
        return (StorageManager) getSystemService("storage");
    }

    public static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) getSystemService(PhoneAuthProvider.PROVIDER_ID);
    }

    @TargetApi(22)
    public static SubscriptionManager getSubscriptionManager() {
        return (SubscriptionManager) getSystemService("telephony_subscription_service");
    }

    @TargetApi(23)
    public static CarrierConfigManager getCarrierConfigManager() {
        return (CarrierConfigManager) getSystemService("carrier_config");
    }

    @TargetApi(21)
    public static TelecomManager getTelecomManager() {
        return (TelecomManager) getSystemService("telecom");
    }

    @TargetApi(8)
    public static UiModeManager getUiModeManager() {
        return (UiModeManager) getSystemService("uimode");
    }

    @TargetApi(12)
    public static UsbManager getUsbManager() {
        return (UsbManager) getSystemService("usb");
    }

    public static Vibrator getVibrator() {
        return (Vibrator) getSystemService("vibrator");
    }

    public static WallpaperManager getWallpaperManager() {
        return WallpaperManager.getInstance(Base.getContext());
    }

    public static WifiManager getWifiManager() {
        return (WifiManager) getSystemService("wifi");
    }

    @TargetApi(14)
    public static WifiP2pManager getWifiP2pManager() {
        return (WifiP2pManager) getSystemService("wifip2p");
    }

    public static WindowManager getWindowManager() {
        return (WindowManager) getSystemService("window");
    }

    @TargetApi(17)
    public static UserManager getUserManager() {
        return (UserManager) getSystemService("user");
    }

    @TargetApi(19)
    public static AppOpsManager getAppOpsManager() {
        return (AppOpsManager) getSystemService("appops");
    }

    @TargetApi(21)
    public static CameraManager getCameraManager() {
        return (CameraManager) getSystemService("camera");
    }

    @TargetApi(21)
    public static LauncherApps getLauncherApps() {
        return (LauncherApps) getSystemService("launcherapps");
    }

    @TargetApi(21)
    public static RestrictionsManager getRestrictionsManager() {
        return (RestrictionsManager) getSystemService("restrictions");
    }

    @TargetApi(19)
    public static PrintManager getPrintManager() {
        return (PrintManager) getSystemService("print");
    }

    @TargetApi(19)
    public static ConsumerIrManager getConsumerIrManager() {
        return (ConsumerIrManager) getSystemService("consumer_ir");
    }

    @TargetApi(21)
    public static MediaSessionManager getMediaSessionManager() {
        return (MediaSessionManager) getSystemService("media_session");
    }

    @TargetApi(23)
    public static FingerprintManager getFingerprintManager() {
        return (FingerprintManager) getSystemService("fingerprint");
    }

    @TargetApi(21)
    public static TvInputManager getTvInputManager() {
        return (TvInputManager) getSystemService("tv_input");
    }

    @TargetApi(22)
    public static UsageStatsManager getUsageStatsManager() {
        return (UsageStatsManager) getSystemService("usagestats");
    }

    @TargetApi(23)
    public static NetworkStatsManager getNetworkStatsManager() {
        return (NetworkStatsManager) getSystemService("netstats");
    }

    @TargetApi(21)
    public static JobScheduler getJobScheduler() {
        return (JobScheduler) getSystemService("jobscheduler");
    }

    @TargetApi(21)
    public static MediaProjectionManager getMediaProjectionManager() {
        return (MediaProjectionManager) getSystemService("media_projection");
    }

    @TargetApi(21)
    public static AppWidgetManager getAppWidgetManager() {
        return (AppWidgetManager) getSystemService("appwidget");
    }

    @TargetApi(23)
    public static MidiManager getMidiManager() {
        return (MidiManager) getSystemService("midi");
    }
}
