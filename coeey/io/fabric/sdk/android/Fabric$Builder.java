package io.fabric.sdk.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.PriorityThreadPoolExecutor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class Fabric$Builder {
    private String appIdentifier;
    private String appInstallIdentifier;
    private final Context context;
    private boolean debuggable;
    private Handler handler;
    private InitializationCallback<Fabric> initializationCallback;
    private Kit[] kits;
    private Logger logger;
    private PriorityThreadPoolExecutor threadPoolExecutor;

    public Fabric$Builder(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        this.context = context;
    }

    public Fabric$Builder kits(Kit... kits) {
        if (this.kits != null) {
            throw new IllegalStateException("Kits already set.");
        }
        this.kits = kits;
        return this;
    }

    @Deprecated
    public Fabric$Builder executorService(ExecutorService executorService) {
        return this;
    }

    public Fabric$Builder threadPoolExecutor(PriorityThreadPoolExecutor threadPoolExecutor) {
        if (threadPoolExecutor == null) {
            throw new IllegalArgumentException("PriorityThreadPoolExecutor must not be null.");
        } else if (this.threadPoolExecutor != null) {
            throw new IllegalStateException("PriorityThreadPoolExecutor already set.");
        } else {
            this.threadPoolExecutor = threadPoolExecutor;
            return this;
        }
    }

    @Deprecated
    public Fabric$Builder handler(Handler handler) {
        return this;
    }

    public Fabric$Builder logger(Logger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger must not be null.");
        } else if (this.logger != null) {
            throw new IllegalStateException("Logger already set.");
        } else {
            this.logger = logger;
            return this;
        }
    }

    public Fabric$Builder appIdentifier(String appIdentifier) {
        if (appIdentifier == null) {
            throw new IllegalArgumentException("appIdentifier must not be null.");
        } else if (this.appIdentifier != null) {
            throw new IllegalStateException("appIdentifier already set.");
        } else {
            this.appIdentifier = appIdentifier;
            return this;
        }
    }

    public Fabric$Builder appInstallIdentifier(String appInstallIdentifier) {
        if (appInstallIdentifier == null) {
            throw new IllegalArgumentException("appInstallIdentifier must not be null.");
        } else if (this.appInstallIdentifier != null) {
            throw new IllegalStateException("appInstallIdentifier already set.");
        } else {
            this.appInstallIdentifier = appInstallIdentifier;
            return this;
        }
    }

    public Fabric$Builder debuggable(boolean enabled) {
        this.debuggable = enabled;
        return this;
    }

    public Fabric$Builder initializationCallback(InitializationCallback<Fabric> initializationCallback) {
        if (initializationCallback == null) {
            throw new IllegalArgumentException("initializationCallback must not be null.");
        } else if (this.initializationCallback != null) {
            throw new IllegalStateException("initializationCallback already set.");
        } else {
            this.initializationCallback = initializationCallback;
            return this;
        }
    }

    public Fabric build() {
        Map<Class<? extends Kit>, Kit> kitMap;
        if (this.threadPoolExecutor == null) {
            this.threadPoolExecutor = PriorityThreadPoolExecutor.create();
        }
        if (this.handler == null) {
            this.handler = new Handler(Looper.getMainLooper());
        }
        if (this.logger == null) {
            if (this.debuggable) {
                this.logger = new DefaultLogger(3);
            } else {
                this.logger = new DefaultLogger();
            }
        }
        if (this.appIdentifier == null) {
            this.appIdentifier = this.context.getPackageName();
        }
        if (this.initializationCallback == null) {
            this.initializationCallback = InitializationCallback.EMPTY;
        }
        if (this.kits == null) {
            kitMap = new HashMap();
        } else {
            kitMap = Fabric.access$000(Arrays.asList(this.kits));
        }
        Context appContext = this.context.getApplicationContext();
        return new Fabric(appContext, kitMap, this.threadPoolExecutor, this.handler, this.logger, this.debuggable, this.initializationCallback, new IdManager(appContext, this.appIdentifier, this.appInstallIdentifier, kitMap.values()), Fabric.access$100(this.context));
    }
}
