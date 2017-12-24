package com.facebook.react.cxxbridge;

public class OptimizedJSBundleLoader extends JSBundleLoader {
    private int mLoadFlags;
    private String mPath;
    private String mSourceURL;

    public OptimizedJSBundleLoader(String path, String sourceURL, int loadFlags) {
        this.mLoadFlags = loadFlags;
        this.mSourceURL = sourceURL;
        this.mPath = path;
    }

    public String loadScript(CatalystInstanceImpl instance) {
        instance.loadScriptFromOptimizedBundle(this.mPath, this.mSourceURL, this.mLoadFlags);
        return this.mSourceURL;
    }
}
