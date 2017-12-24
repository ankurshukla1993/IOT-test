package com.facebook.react;

import android.app.Application;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.uimanager.UIImplementationProvider;
import java.util.List;
import javax.annotation.Nullable;

public abstract class ReactNativeHost {
    private final Application mApplication;
    @Nullable
    private ReactInstanceManager mReactInstanceManager;

    protected abstract List<ReactPackage> getPackages();

    public abstract boolean getUseDeveloperSupport();

    protected ReactNativeHost(Application application) {
        this.mApplication = application;
    }

    public ReactInstanceManager getReactInstanceManager() {
        if (this.mReactInstanceManager == null) {
            this.mReactInstanceManager = createReactInstanceManager();
        }
        return this.mReactInstanceManager;
    }

    public boolean hasInstance() {
        return this.mReactInstanceManager != null;
    }

    public void clear() {
        if (this.mReactInstanceManager != null) {
            this.mReactInstanceManager.destroy();
            this.mReactInstanceManager = null;
        }
    }

    protected ReactInstanceManager createReactInstanceManager() {
        ReactInstanceManagerBuilder builder = ReactInstanceManager.builder().setApplication(this.mApplication).setJSMainModuleName(getJSMainModuleName()).setUseDeveloperSupport(getUseDeveloperSupport()).setRedBoxHandler(getRedBoxHandler()).setUIImplementationProvider(getUIImplementationProvider()).setInitialLifecycleState(LifecycleState.BEFORE_CREATE);
        for (ReactPackage reactPackage : getPackages()) {
            builder.addPackage(reactPackage);
        }
        String jsBundleFile = getJSBundleFile();
        if (jsBundleFile != null) {
            builder.setJSBundleFile(jsBundleFile);
        } else {
            builder.setBundleAssetName((String) Assertions.assertNotNull(getBundleAssetName()));
        }
        return builder.build();
    }

    @Nullable
    protected RedBoxHandler getRedBoxHandler() {
        return null;
    }

    protected final Application getApplication() {
        return this.mApplication;
    }

    protected UIImplementationProvider getUIImplementationProvider() {
        return new UIImplementationProvider();
    }

    protected String getJSMainModuleName() {
        return "index.android";
    }

    @Nullable
    protected String getJSBundleFile() {
        return null;
    }

    @Nullable
    protected String getBundleAssetName() {
        return "index.android.bundle";
    }
}
