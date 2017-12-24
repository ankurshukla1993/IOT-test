package com.facebook.react.devsupport;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import javax.annotation.Nullable;

@ReactModule(name = "JSCHeapCapture", needsEagerInit = true)
public class JSCHeapCapture extends ReactContextBaseJavaModule {
    private static final HashSet<JSCHeapCapture> sRegisteredDumpers = new HashSet();
    @Nullable
    private PerCaptureCallback mCaptureInProgress = null;
    @Nullable
    private HeapCapture mHeapCapture = null;

    private static synchronized void registerHeapCapture(JSCHeapCapture dumper) {
        synchronized (JSCHeapCapture.class) {
            if (sRegisteredDumpers.contains(dumper)) {
                throw new RuntimeException("a JSCHeapCapture registered more than once");
            }
            sRegisteredDumpers.add(dumper);
        }
    }

    private static synchronized void unregisterHeapCapture(JSCHeapCapture dumper) {
        synchronized (JSCHeapCapture.class) {
            sRegisteredDumpers.remove(dumper);
        }
    }

    public static synchronized void captureHeap(String path, CaptureCallback callback) {
        synchronized (JSCHeapCapture.class) {
            LinkedList<File> captureFiles = new LinkedList();
            LinkedList<CaptureException> captureFailures = new LinkedList();
            if (sRegisteredDumpers.isEmpty()) {
                captureFailures.add(new CaptureException("No JSC registered"));
                callback.onComplete(captureFiles, captureFailures);
            } else {
                int disambiguate = 0;
                File f = new File(path + "/capture" + Integer.toString(0) + ".json");
                while (f.delete()) {
                    disambiguate++;
                    f = new File(path + "/capture" + Integer.toString(disambiguate) + ".json");
                }
                int numRegisteredDumpers = sRegisteredDumpers.size();
                Iterator it = sRegisteredDumpers.iterator();
                while (it.hasNext()) {
                    ((JSCHeapCapture) it.next()).captureHeapHelper(new File(path + "/capture" + Integer.toString(0) + ".json"), new 1(captureFiles, captureFailures, numRegisteredDumpers, callback));
                }
            }
        }
    }

    public JSCHeapCapture(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    private synchronized void captureHeapHelper(File file, PerCaptureCallback callback) {
        if (this.mHeapCapture == null) {
            callback.onFailure(new CaptureException("HeapCapture.js module not connected"));
        } else if (this.mCaptureInProgress != null) {
            callback.onFailure(new CaptureException("Heap capture already in progress"));
        } else {
            this.mCaptureInProgress = callback;
            this.mHeapCapture.captureHeap(file.getPath());
        }
    }

    @ReactMethod
    public synchronized void captureComplete(String path, String error) {
        if (this.mCaptureInProgress != null) {
            if (error == null) {
                this.mCaptureInProgress.onSuccess(new File(path));
            } else {
                this.mCaptureInProgress.onFailure(new CaptureException(error));
            }
            this.mCaptureInProgress = null;
        }
    }

    public String getName() {
        return "JSCHeapCapture";
    }

    public void initialize() {
        super.initialize();
        this.mHeapCapture = (HeapCapture) getReactApplicationContext().getJSModule(HeapCapture.class);
        registerHeapCapture(this);
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        unregisterHeapCapture(this);
        this.mHeapCapture = null;
    }
}
