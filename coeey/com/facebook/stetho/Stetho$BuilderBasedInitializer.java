package com.facebook.stetho;

import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import javax.annotation.Nullable;

class Stetho$BuilderBasedInitializer extends Stetho$Initializer {
    @Nullable
    private final DumperPluginsProvider mDumperPlugins;
    @Nullable
    private final InspectorModulesProvider mInspectorModules;

    private Stetho$BuilderBasedInitializer(Stetho$InitializerBuilder b) {
        super(b.mContext);
        this.mDumperPlugins = b.mDumperPlugins;
        this.mInspectorModules = b.mInspectorModules;
    }

    @Nullable
    protected Iterable<DumperPlugin> getDumperPlugins() {
        return this.mDumperPlugins != null ? this.mDumperPlugins.get() : null;
    }

    @Nullable
    protected Iterable<ChromeDevtoolsDomain> getInspectorModules() {
        return this.mInspectorModules != null ? this.mInspectorModules.get() : null;
    }
}
