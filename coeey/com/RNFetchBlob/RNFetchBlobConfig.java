package com.RNFetchBlob;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.views.scroll.ReactScrollViewHelper;

public class RNFetchBlobConfig {
    public ReadableMap addAndroidDownloads;
    public String appendExt;
    public Boolean auto;
    public ReadableArray binaryContentTypes = null;
    public Boolean fileCache;
    public Boolean followRedirect = Boolean.valueOf(true);
    public Boolean increment = Boolean.valueOf(false);
    public String key;
    public String mime;
    public Boolean overwrite = Boolean.valueOf(true);
    public String path;
    public long timeout = 60000;
    public Boolean trusty;

    RNFetchBlobConfig(ReadableMap options) {
        String str = null;
        boolean z = false;
        if (options != null) {
            String string;
            boolean z2;
            this.fileCache = Boolean.valueOf(options.hasKey("fileCache") ? options.getBoolean("fileCache") : false);
            if (options.hasKey(RNFetchBlobConst.RNFB_RESPONSE_PATH)) {
                string = options.getString(RNFetchBlobConst.RNFB_RESPONSE_PATH);
            } else {
                string = null;
            }
            this.path = string;
            this.appendExt = options.hasKey("appendExt") ? options.getString("appendExt") : "";
            if (options.hasKey("trusty")) {
                z2 = options.getBoolean("trusty");
            } else {
                z2 = false;
            }
            this.trusty = Boolean.valueOf(z2);
            if (options.hasKey("addAndroidDownloads")) {
                this.addAndroidDownloads = options.getMap("addAndroidDownloads");
            }
            if (options.hasKey("binaryContentTypes")) {
                this.binaryContentTypes = options.getArray("binaryContentTypes");
            }
            if (this.path != null && this.path.toLowerCase().contains("?append=true")) {
                this.overwrite = Boolean.valueOf(false);
            }
            if (options.hasKey("overwrite")) {
                this.overwrite = Boolean.valueOf(options.getBoolean("overwrite"));
            }
            if (options.hasKey("followRedirect")) {
                this.followRedirect = Boolean.valueOf(options.getBoolean("followRedirect"));
            }
            this.key = options.hasKey("key") ? options.getString("key") : null;
            if (options.hasKey("contentType")) {
                str = options.getString("contentType");
            }
            this.mime = str;
            if (options.hasKey("increment")) {
                z2 = options.getBoolean("increment");
            } else {
                z2 = false;
            }
            this.increment = Boolean.valueOf(z2);
            if (options.hasKey(ReactScrollViewHelper.AUTO)) {
                z = options.getBoolean(ReactScrollViewHelper.AUTO);
            }
            this.auto = Boolean.valueOf(z);
            if (options.hasKey("timeout")) {
                this.timeout = (long) options.getInt("timeout");
            }
        }
    }
}
