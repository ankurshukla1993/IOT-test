package io.reactivex.disposables;

import io.reactivex.annotations.NonNull;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableContainer;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.OpenHashSet;
import java.util.ArrayList;

public final class CompositeDisposable implements Disposable, DisposableContainer {
    volatile boolean disposed;
    OpenHashSet<Disposable> resources;

    public CompositeDisposable(@NonNull Disposable... resources) {
        ObjectHelper.requireNonNull(resources, "resources is null");
        this.resources = new OpenHashSet(resources.length + 1);
        for (Disposable d : resources) {
            ObjectHelper.requireNonNull(d, "Disposable item is null");
            this.resources.add(d);
        }
    }

    public CompositeDisposable(@NonNull Iterable<? extends Disposable> resources) {
        ObjectHelper.requireNonNull(resources, "resources is null");
        this.resources = new OpenHashSet();
        for (Disposable d : resources) {
            ObjectHelper.requireNonNull(d, "Disposable item is null");
            this.resources.add(d);
        }
    }

    public void dispose() {
        if (!this.disposed) {
            synchronized (this) {
                if (this.disposed) {
                    return;
                }
                this.disposed = true;
                OpenHashSet<Disposable> set = this.resources;
                this.resources = null;
                dispose(set);
            }
        }
    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public boolean add(@NonNull Disposable d) {
        ObjectHelper.requireNonNull(d, "d is null");
        if (!this.disposed) {
            synchronized (this) {
                if (!this.disposed) {
                    OpenHashSet<Disposable> set = this.resources;
                    if (set == null) {
                        set = new OpenHashSet();
                        this.resources = set;
                    }
                    set.add(d);
                    return true;
                }
            }
        }
        d.dispose();
        return false;
    }

    public boolean addAll(@NonNull Disposable... ds) {
        int length;
        boolean z = false;
        ObjectHelper.requireNonNull(ds, "ds is null");
        if (!this.disposed) {
            synchronized (this) {
                if (this.disposed) {
                } else {
                    OpenHashSet<Disposable> set = this.resources;
                    if (set == null) {
                        set = new OpenHashSet(ds.length + 1);
                        this.resources = set;
                    }
                    length = ds.length;
                    int i;
                    while (i < length) {
                        Disposable d = ds[i];
                        ObjectHelper.requireNonNull(d, "d is null");
                        set.add(d);
                        i++;
                    }
                    z = true;
                }
            }
            return z;
        }
        for (Disposable d2 : ds) {
            d2.dispose();
        }
        return z;
    }

    public boolean remove(@NonNull Disposable d) {
        if (!delete(d)) {
            return false;
        }
        d.dispose();
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean delete(@io.reactivex.annotations.NonNull io.reactivex.disposables.Disposable r4) {
        /*
        r3 = this;
        r1 = 0;
        r2 = "Disposable item is null";
        io.reactivex.internal.functions.ObjectHelper.requireNonNull(r4, r2);
        r2 = r3.disposed;
        if (r2 == 0) goto L_0x000b;
    L_0x000a:
        return r1;
    L_0x000b:
        monitor-enter(r3);
        r2 = r3.disposed;	 Catch:{ all -> 0x0012 }
        if (r2 == 0) goto L_0x0015;
    L_0x0010:
        monitor-exit(r3);	 Catch:{ all -> 0x0012 }
        goto L_0x000a;
    L_0x0012:
        r1 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0012 }
        throw r1;
    L_0x0015:
        r0 = r3.resources;	 Catch:{ all -> 0x0012 }
        if (r0 == 0) goto L_0x001f;
    L_0x0019:
        r2 = r0.remove(r4);	 Catch:{ all -> 0x0012 }
        if (r2 != 0) goto L_0x0021;
    L_0x001f:
        monitor-exit(r3);	 Catch:{ all -> 0x0012 }
        goto L_0x000a;
    L_0x0021:
        monitor-exit(r3);	 Catch:{ all -> 0x0012 }
        r1 = 1;
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.disposables.CompositeDisposable.delete(io.reactivex.disposables.Disposable):boolean");
    }

    public void clear() {
        if (!this.disposed) {
            synchronized (this) {
                if (this.disposed) {
                    return;
                }
                OpenHashSet<Disposable> set = this.resources;
                this.resources = null;
                dispose(set);
            }
        }
    }

    public int size() {
        int i = 0;
        if (!this.disposed) {
            synchronized (this) {
                if (this.disposed) {
                } else {
                    OpenHashSet<Disposable> set = this.resources;
                    if (set != null) {
                        i = set.size();
                    }
                }
            }
        }
        return i;
    }

    void dispose(OpenHashSet<Disposable> set) {
        if (set != null) {
            Iterable errors = null;
            for (Object o : set.keys()) {
                if (o instanceof Disposable) {
                    try {
                        ((Disposable) o).dispose();
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        if (errors == null) {
                            errors = new ArrayList();
                        }
                        errors.add(ex);
                    }
                }
            }
            if (errors == null) {
                return;
            }
            if (errors.size() == 1) {
                throw ExceptionHelper.wrapOrThrow((Throwable) errors.get(0));
            }
            throw new CompositeException(errors);
        }
    }
}
