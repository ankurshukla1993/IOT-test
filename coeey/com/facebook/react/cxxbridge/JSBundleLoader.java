package com.facebook.react.cxxbridge;

import android.content.Context;
import com.facebook.react.devsupport.DebugServerException;

public abstract class JSBundleLoader {
    public abstract String loadScript(CatalystInstanceImpl catalystInstanceImpl);

    public static JSBundleLoader createAssetLoader(final Context context, final String assetUrl) {
        return new JSBundleLoader() {
            public String loadScript(CatalystInstanceImpl instance) {
                instance.loadScriptFromAssets(context.getAssets(), assetUrl);
                return assetUrl;
            }
        };
    }

    public static JSBundleLoader createFileLoader(final String fileName) {
        return new JSBundleLoader() {
            public String loadScript(CatalystInstanceImpl instance) {
                instance.loadScriptFromFile(fileName, fileName);
                return fileName;
            }
        };
    }

    public static JSBundleLoader createCachedBundleFromNetworkLoader(final String sourceURL, final String cachedFileLocation) {
        return new JSBundleLoader() {
            public String loadScript(CatalystInstanceImpl instance) {
                try {
                    instance.loadScriptFromFile(cachedFileLocation, sourceURL);
                    return sourceURL;
                } catch (Exception e) {
                    throw DebugServerException.makeGeneric(e.getMessage(), e);
                }
            }
        };
    }

    public static JSBundleLoader createRemoteDebuggerBundleLoader(final String proxySourceURL, final String realSourceURL) {
        return new JSBundleLoader() {
            public String loadScript(CatalystInstanceImpl instance) {
                instance.setSourceURLs(realSourceURL, proxySourceURL);
                return realSourceURL;
            }
        };
    }
}
