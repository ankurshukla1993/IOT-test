package com.facebook.stetho;

import android.app.Application;
import android.content.Context;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.dumpapp.plugins.CrashDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.FilesDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.HprofDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.SharedPreferencesDumperPlugin;
import com.facebook.stetho.inspector.elements.android.ActivityTracker;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Stetho {

    public static final class DefaultDumperPluginsBuilder {
        private final Context mContext;
        private final PluginBuilder<DumperPlugin> mDelegate = new PluginBuilder();

        public DefaultDumperPluginsBuilder(Context context) {
            this.mContext = context;
        }

        public DefaultDumperPluginsBuilder provide(DumperPlugin plugin) {
            this.mDelegate.provide(plugin.getName(), plugin);
            return this;
        }

        private DefaultDumperPluginsBuilder provideIfDesired(DumperPlugin plugin) {
            this.mDelegate.provideIfDesired(plugin.getName(), plugin);
            return this;
        }

        public DefaultDumperPluginsBuilder remove(String pluginName) {
            this.mDelegate.remove(pluginName);
            return this;
        }

        public Iterable<DumperPlugin> finish() {
            provideIfDesired(new HprofDumperPlugin(this.mContext));
            provideIfDesired(new SharedPreferencesDumperPlugin(this.mContext));
            provideIfDesired(new CrashDumperPlugin());
            provideIfDesired(new FilesDumperPlugin(this.mContext));
            return this.mDelegate.finish();
        }
    }

    private static class PluginBuilder<T> {
        private boolean mFinished;
        private final ArrayList<T> mPlugins;
        private final Set<String> mProvidedNames;
        private final Set<String> mRemovedNames;

        private PluginBuilder() {
            this.mProvidedNames = new HashSet();
            this.mRemovedNames = new HashSet();
            this.mPlugins = new ArrayList();
        }

        public void provide(String name, T plugin) {
            throwIfFinished();
            this.mPlugins.add(plugin);
            this.mProvidedNames.add(name);
        }

        public void provideIfDesired(String name, T plugin) {
            throwIfFinished();
            if (!this.mRemovedNames.contains(name) && this.mProvidedNames.add(name)) {
                this.mPlugins.add(plugin);
            }
        }

        public void remove(String pluginName) {
            throwIfFinished();
            this.mRemovedNames.remove(pluginName);
        }

        private void throwIfFinished() {
            if (this.mFinished) {
                throw new IllegalStateException("Must not continue to build after finish()");
            }
        }

        public Iterable<T> finish() {
            this.mFinished = true;
            return this.mPlugins;
        }
    }

    private Stetho() {
    }

    public static InitializerBuilder newInitializerBuilder(Context context) {
        return new InitializerBuilder(context, null);
    }

    public static void initializeWithDefaults(final Context context) {
        initialize(new Initializer(context) {
            protected Iterable<DumperPlugin> getDumperPlugins() {
                return new DefaultDumperPluginsBuilder(context).finish();
            }

            protected Iterable<ChromeDevtoolsDomain> getInspectorModules() {
                return new DefaultInspectorModulesBuilder(context).finish();
            }
        });
    }

    public static void initialize(Initializer initializer) {
        if (!ActivityTracker.get().beginTrackingIfPossible((Application) Initializer.access$100(initializer).getApplicationContext())) {
            LogUtil.m201w("Automatic activity tracking not available on this API level, caller must invoke ActivityTracker methods manually!");
        }
        initializer.start();
    }

    public static DumperPluginsProvider defaultDumperPluginsProvider(final Context context) {
        return new DumperPluginsProvider() {
            public Iterable<DumperPlugin> get() {
                return new DefaultDumperPluginsBuilder(context).finish();
            }
        };
    }

    public static InspectorModulesProvider defaultInspectorModulesProvider(final Context context) {
        return new InspectorModulesProvider() {
            public Iterable<ChromeDevtoolsDomain> get() {
                return new DefaultInspectorModulesBuilder(context).finish();
            }
        };
    }
}
