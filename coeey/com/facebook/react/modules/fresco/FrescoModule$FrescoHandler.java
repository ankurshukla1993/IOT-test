package com.facebook.react.modules.fresco;

import com.facebook.common.soloader.SoLoaderShim.Handler;
import com.facebook.soloader.SoLoader;

class FrescoModule$FrescoHandler implements Handler {
    private FrescoModule$FrescoHandler() {
    }

    public void loadLibrary(String libraryName) {
        SoLoader.loadLibrary(libraryName);
    }
}
