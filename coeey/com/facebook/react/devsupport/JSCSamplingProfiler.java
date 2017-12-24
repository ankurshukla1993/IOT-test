package com.facebook.react.devsupport;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

@ReactModule(name = "JSCSamplingProfiler", needsEagerInit = true)
public class JSCSamplingProfiler extends ReactContextBaseJavaModule {
    private static final HashSet<JSCSamplingProfiler> sRegisteredDumpers = new HashSet();
    @Nullable
    private String mOperationError = null;
    private boolean mOperationInProgress = false;
    private int mOperationToken = 0;
    @Nullable
    private SamplingProfiler mSamplingProfiler = null;
    @Nullable
    private String mSamplingProfilerResult = null;

    private static synchronized void registerSamplingProfiler(JSCSamplingProfiler dumper) {
        synchronized (JSCSamplingProfiler.class) {
            if (sRegisteredDumpers.contains(dumper)) {
                throw new RuntimeException("a JSCSamplingProfiler registered more than once");
            }
            sRegisteredDumpers.add(dumper);
        }
    }

    private static synchronized void unregisterSamplingProfiler(JSCSamplingProfiler dumper) {
        synchronized (JSCSamplingProfiler.class) {
            sRegisteredDumpers.remove(dumper);
        }
    }

    public static synchronized List<String> poke(long timeout) throws ProfilerException {
        LinkedList<String> results;
        synchronized (JSCSamplingProfiler.class) {
            results = new LinkedList();
            if (sRegisteredDumpers.isEmpty()) {
                throw new ProfilerException("No JSC registered");
            }
            Iterator it = sRegisteredDumpers.iterator();
            while (it.hasNext()) {
                JSCSamplingProfiler dumper = (JSCSamplingProfiler) it.next();
                dumper.pokeHelper(timeout);
                results.add(dumper.mSamplingProfilerResult);
            }
        }
        return results;
    }

    public JSCSamplingProfiler(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    private synchronized void pokeHelper(long timeout) throws ProfilerException {
        if (this.mSamplingProfiler == null) {
            throw new ProfilerException("SamplingProfiler.js module not connected");
        }
        this.mSamplingProfiler.poke(getOperationToken());
        waitForOperation(timeout);
    }

    private int getOperationToken() throws ProfilerException {
        if (this.mOperationInProgress) {
            throw new ProfilerException("Another operation already in progress.");
        }
        this.mOperationInProgress = true;
        int i = this.mOperationToken + 1;
        this.mOperationToken = i;
        return i;
    }

    private void waitForOperation(long timeout) throws ProfilerException {
        try {
            wait(timeout);
            if (this.mOperationInProgress) {
                this.mOperationInProgress = false;
                throw new ProfilerException("heap capture timed out.");
            } else if (this.mOperationError != null) {
                throw new ProfilerException(this.mOperationError);
            }
        } catch (InterruptedException e) {
            throw new ProfilerException("Waiting for heap capture failed: " + e.getMessage());
        }
    }

    @ReactMethod
    public synchronized void operationComplete(int token, String result, String error) {
        if (token == this.mOperationToken) {
            this.mOperationInProgress = false;
            this.mSamplingProfilerResult = result;
            this.mOperationError = error;
            notify();
        } else {
            throw new RuntimeException("Completed operation is not in progress.");
        }
    }

    public String getName() {
        return "JSCSamplingProfiler";
    }

    public void initialize() {
        super.initialize();
        this.mSamplingProfiler = (SamplingProfiler) getReactApplicationContext().getJSModule(SamplingProfiler.class);
        registerSamplingProfiler(this);
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        unregisterSamplingProfiler(this);
        this.mSamplingProfiler = null;
    }
}
