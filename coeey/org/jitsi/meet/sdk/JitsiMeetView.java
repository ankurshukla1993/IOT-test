package org.jitsi.meet.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import com.RNFetchBlob.RNFetchBlobPackage;
import com.corbt.keepawake.KCKeepAwakePackage;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.oblador.vectoricons.VectorIconsPackage;
import com.ocetnik.timer.BackgroundTimerPackage;
import com.oney.WebRTCModule.WebRTCModulePackage;
import com.rnimmersive.RNImmersivePackage;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;

public class JitsiMeetView extends FrameLayout {
    private static final int BACKGROUND_COLOR = -15658735;
    private static ReactInstanceManager reactInstanceManager;
    private static final Set<JitsiMeetView> views = Collections.newSetFromMap(new WeakHashMap());
    private final String externalAPIScope;
    private JitsiMeetViewListener listener;
    private ReactRootView reactRootView;
    private boolean welcomePageEnabled;

    static class C25521 extends ReactPackageAdapter {
        C25521() {
        }

        public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
            return JitsiMeetView.createNativeModules(reactContext);
        }
    }

    private static List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Arrays.asList(new NativeModule[]{new AndroidSettingsModule(reactContext), new AudioModeModule(reactContext), new ExternalAPIModule(reactContext), new ProximityModule(reactContext)});
    }

    public static JitsiMeetView findViewByExternalAPIScope(String externalAPIScope) {
        synchronized (views) {
            for (JitsiMeetView view : views) {
                if (view.externalAPIScope.equals(externalAPIScope)) {
                    return view;
                }
            }
            return null;
        }
    }

    private static void initReactInstanceManager(Application application) {
        reactInstanceManager = ReactInstanceManager.builder().setApplication(application).setBundleAssetName("index.android.bundle").setJSMainModuleName("index.android").addPackage(new KCKeepAwakePackage()).addPackage(new MainReactPackage()).addPackage(new VectorIconsPackage()).addPackage(new BackgroundTimerPackage()).addPackage(new WebRTCModulePackage()).addPackage(new RNFetchBlobPackage()).addPackage(new RNImmersivePackage()).addPackage(new C25521()).setUseDeveloperSupport(false).setInitialLifecycleState(LifecycleState.RESUMED).build();
    }

    private static boolean loadURLStringInViews(String urlString) {
        synchronized (views) {
            if (views.isEmpty()) {
                return false;
            }
            for (JitsiMeetView view : views) {
                view.loadURLString(urlString);
            }
            return true;
        }
    }

    public static boolean onBackPressed() {
        if (reactInstanceManager == null) {
            return false;
        }
        reactInstanceManager.onBackPressed();
        return true;
    }

    public static void onHostDestroy(Activity activity) {
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostDestroy(activity);
        }
    }

    public static void onHostPause(Activity activity) {
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostPause(activity);
        }
    }

    public static void onHostResume(Activity activity) {
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostResume(activity, null);
        }
    }

    public static void onNewIntent(Intent intent) {
        if ("android.intent.action.VIEW".equals(intent.getAction())) {
            Uri uri = intent.getData();
            if (uri != null && loadURLStringInViews(uri.toString())) {
                return;
            }
        }
        if (reactInstanceManager != null) {
            reactInstanceManager.onNewIntent(intent);
        }
    }

    public JitsiMeetView(@NonNull Context context) {
        super(context);
        setBackgroundColor(BACKGROUND_COLOR);
        if (reactInstanceManager == null) {
            initReactInstanceManager(((Activity) context).getApplication());
        }
        this.externalAPIScope = UUID.randomUUID().toString();
        synchronized (views) {
            views.add(this);
        }
    }

    public void dispose() {
        if (this.reactRootView != null) {
            removeView(this.reactRootView);
            this.reactRootView.unmountReactApplication();
            this.reactRootView = null;
        }
    }

    public JitsiMeetViewListener getListener() {
        return this.listener;
    }

    public boolean getWelcomePageEnabled() {
        return this.welcomePageEnabled;
    }

    public void loadURL(@Nullable URL url) {
        loadURLString(url == null ? null : url.toString());
    }

    public void loadURLObject(@Nullable Bundle urlObject) {
        Bundle props = new Bundle();
        props.putString("externalAPIScope", this.externalAPIScope);
        if (urlObject != null) {
            props.putBundle("url", urlObject);
        }
        props.putBoolean("welcomePageEnabled", this.welcomePageEnabled);
        dispose();
        this.reactRootView = new ReactRootView(getContext());
        this.reactRootView.startReactApplication(reactInstanceManager, "App", props);
        this.reactRootView.setBackgroundColor(BACKGROUND_COLOR);
        addView(this.reactRootView);
    }

    public void loadURLString(@Nullable String urlString) {
        Bundle urlObject;
        if (urlString == null) {
            urlObject = null;
        } else {
            urlObject = new Bundle();
            urlObject.putString("url", urlString);
        }
        loadURLObject(urlObject);
    }

    public void setListener(JitsiMeetViewListener listener) {
        this.listener = listener;
    }

    public void setWelcomePageEnabled(boolean welcomePageEnabled) {
        this.welcomePageEnabled = welcomePageEnabled;
    }
}
