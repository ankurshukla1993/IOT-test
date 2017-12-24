package com.google.common.base;

import com.google.common.annotations.VisibleForTesting;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinalizableReferenceQueue implements Closeable {
    private static final String FINALIZER_CLASS_NAME = "com.google.common.base.internal.Finalizer";
    private static final Logger logger = Logger.getLogger(FinalizableReferenceQueue.class.getName());
    private static final Method startFinalizer = getStartFinalizer(loadFinalizer(new SystemLoader(), new DecoupledLoader(), new DirectLoader()));
    final PhantomReference<Object> frqRef = new PhantomReference(this, this.queue);
    final ReferenceQueue<Object> queue = new ReferenceQueue();
    final boolean threadStarted;

    static class DecoupledLoader implements FinalizerLoader {
        private static final String LOADING_ERROR = "Could not load Finalizer in its own class loader.Loading Finalizer in the current class loader instead. As a result, you will not be ableto garbage collect this class loader. To support reclaiming this class loader, eitherresolve the underlying issue, or move Google Collections to your system class path.";

        DecoupledLoader() {
        }

        public Class<?> loadFinalizer() {
            try {
                return newLoader(getBaseUrl()).loadClass(FinalizableReferenceQueue.FINALIZER_CLASS_NAME);
            } catch (Exception e) {
                FinalizableReferenceQueue.logger.log(Level.WARNING, LOADING_ERROR, e);
                return null;
            }
        }

        URL getBaseUrl() throws IOException {
            String finalizerPath = String.valueOf(FinalizableReferenceQueue.FINALIZER_CLASS_NAME.replace('.', '/')).concat(".class");
            URL finalizerUrl = getClass().getClassLoader().getResource(finalizerPath);
            if (finalizerUrl == null) {
                throw new FileNotFoundException(finalizerPath);
            }
            String urlString = finalizerUrl.toString();
            if (urlString.endsWith(finalizerPath)) {
                return new URL(finalizerUrl, urlString.substring(0, urlString.length() - finalizerPath.length()));
            }
            String str = "Unsupported path style: ";
            String valueOf = String.valueOf(urlString);
            throw new IOException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }

        URLClassLoader newLoader(URL base) {
            return new URLClassLoader(new URL[]{base}, null);
        }
    }

    static class DirectLoader implements FinalizerLoader {
        DirectLoader() {
        }

        public Class<?> loadFinalizer() {
            try {
                return Class.forName(FinalizableReferenceQueue.FINALIZER_CLASS_NAME);
            } catch (ClassNotFoundException e) {
                throw new AssertionError(e);
            }
        }
    }

    static class SystemLoader implements FinalizerLoader {
        @VisibleForTesting
        static boolean disabled;

        SystemLoader() {
        }

        public Class<?> loadFinalizer() {
            Class<?> cls = null;
            if (!disabled) {
                try {
                    ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
                    if (systemLoader != null) {
                        try {
                            cls = systemLoader.loadClass(FinalizableReferenceQueue.FINALIZER_CLASS_NAME);
                        } catch (ClassNotFoundException e) {
                        }
                    }
                } catch (SecurityException e2) {
                    FinalizableReferenceQueue.logger.info("Not allowed to access system class loader.");
                }
            }
            return cls;
        }
    }

    public FinalizableReferenceQueue() {
        boolean threadStarted = false;
        try {
            startFinalizer.invoke(null, new Object[]{FinalizableReference.class, this.queue, this.frqRef});
            threadStarted = true;
        } catch (IllegalAccessException impossible) {
            throw new AssertionError(impossible);
        } catch (Throwable t) {
            logger.log(Level.INFO, "Failed to start reference finalizer thread. Reference cleanup will only occur when new references are created.", t);
        }
        this.threadStarted = threadStarted;
    }

    public void close() {
        this.frqRef.enqueue();
        cleanUp();
    }

    void cleanUp() {
        if (!this.threadStarted) {
            while (true) {
                Reference<?> reference = this.queue.poll();
                if (reference != null) {
                    reference.clear();
                    try {
                        ((FinalizableReference) reference).finalizeReferent();
                    } catch (Throwable t) {
                        logger.log(Level.SEVERE, "Error cleaning up after reference.", t);
                    }
                } else {
                    return;
                }
            }
        }
    }

    private static Class<?> loadFinalizer(FinalizerLoader... loaders) {
        for (FinalizerLoader loader : loaders) {
            Class<?> finalizer = loader.loadFinalizer();
            if (finalizer != null) {
                return finalizer;
            }
        }
        throw new AssertionError();
    }

    static Method getStartFinalizer(Class<?> finalizer) {
        try {
            return finalizer.getMethod("startFinalizer", new Class[]{Class.class, ReferenceQueue.class, PhantomReference.class});
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        }
    }
}
