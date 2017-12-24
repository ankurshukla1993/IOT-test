package com.facebook.react.cxxbridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.common.futures.SimpleSettableFuture;
import com.facebook.react.module.model.Info;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.facebook.systrace.SystraceMessage.Builder;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nullable;
import javax.inject.Provider;

public class ModuleHolder {
    private final Info mInfo;
    private boolean mInitializeNeeded;
    @Nullable
    private NativeModule mModule;
    @Nullable
    private Provider<? extends NativeModule> mProvider;

    public ModuleHolder(ReactModuleInfo info, Provider<? extends NativeModule> provider) {
        this.mInfo = info;
        this.mProvider = provider;
        if (this.mInfo.needsEagerInit()) {
            this.mModule = doCreate();
        }
    }

    public ModuleHolder(LegacyModuleInfo info, NativeModule nativeModule) {
        this.mInfo = info;
        this.mModule = nativeModule;
    }

    public synchronized void initialize() {
        if (this.mModule != null) {
            doInitialize(this.mModule);
        } else {
            this.mInitializeNeeded = true;
        }
    }

    public synchronized void destroy() {
        if (this.mModule != null) {
            this.mModule.onCatalystInstanceDestroy();
        }
    }

    public Info getInfo() {
        return this.mInfo;
    }

    public synchronized NativeModule getModule() {
        if (this.mModule == null) {
            this.mModule = doCreate();
        }
        return this.mModule;
    }

    private NativeModule doCreate() {
        NativeModule module = create();
        this.mProvider = null;
        return module;
    }

    private NativeModule create() {
        boolean isEagerModule = this.mInfo instanceof LegacyModuleInfo;
        String name = isEagerModule ? ((LegacyModuleInfo) this.mInfo).mType.getSimpleName() : this.mInfo.name();
        if (!isEagerModule) {
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_START);
        }
        SystraceMessage.beginSection(0, "createModule").arg("name", name).flush();
        NativeModule module = (NativeModule) ((Provider) Assertions.assertNotNull(this.mProvider)).get();
        if (this.mInitializeNeeded) {
            doInitialize(module);
            this.mInitializeNeeded = false;
        }
        Systrace.endSection(0);
        if (!isEagerModule) {
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
        }
        return module;
    }

    private void doInitialize(NativeModule module) {
        Builder section = SystraceMessage.beginSection(0, "initialize");
        if (module instanceof CxxModuleWrapper) {
            section.arg("className", module.getClass().getSimpleName());
        } else {
            section.arg("name", this.mInfo.name());
        }
        section.flush();
        callInitializeOnUiThread(module);
        Systrace.endSection(0);
    }

    private static void callInitializeOnUiThread(NativeModule module) {
        Exception e;
        if (UiThreadUtil.isOnUiThread()) {
            module.initialize();
            return;
        }
        SimpleSettableFuture<Void> future = new SimpleSettableFuture();
        UiThreadUtil.runOnUiThread(new 1(module, future));
        try {
            future.get();
        } catch (InterruptedException e2) {
            e = e2;
            throw new RuntimeException(e);
        } catch (ExecutionException e3) {
            e = e3;
            throw new RuntimeException(e);
        }
    }
}
