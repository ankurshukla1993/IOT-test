package com.facebook.react.cxxbridge;

import com.facebook.common.logging.FLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public final class FallbackJSBundleLoader extends JSBundleLoader {
    static final String RECOVERABLE = "facebook::react::Recoverable";
    static final String TAG = "FallbackJSBundleLoader";
    private Stack<JSBundleLoader> mLoaders = new Stack();
    private final ArrayList<Exception> mRecoveredErrors = new ArrayList();

    public FallbackJSBundleLoader(List<JSBundleLoader> loaders) {
        ListIterator<JSBundleLoader> it = loaders.listIterator(loaders.size());
        while (it.hasPrevious()) {
            this.mLoaders.push(it.previous());
        }
    }

    public String loadScript(CatalystInstanceImpl instance) {
        while (true) {
            try {
                break;
            } catch (Throwable e) {
                if (e.getMessage().startsWith(RECOVERABLE)) {
                    this.mLoaders.pop();
                    this.mRecoveredErrors.add(e);
                    FLog.wtf(TAG, "Falling back from recoverable error", e);
                } else {
                    throw e;
                }
            }
        }
        return getDelegateLoader().loadScript(instance);
    }

    private JSBundleLoader getDelegateLoader() {
        if (!this.mLoaders.empty()) {
            return (JSBundleLoader) this.mLoaders.peek();
        }
        Throwable fallbackException = new RuntimeException("No fallback options available");
        Throwable tail = fallbackException;
        Iterator it = this.mRecoveredErrors.iterator();
        while (it.hasNext()) {
            tail.initCause((Exception) it.next());
            while (tail.getCause() != null) {
                tail = tail.getCause();
            }
        }
        throw fallbackException;
    }
}
