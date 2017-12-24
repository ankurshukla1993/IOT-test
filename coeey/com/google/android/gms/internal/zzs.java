package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzs {
    private final AtomicInteger zzaw;
    private final Map<String, Queue<zzp<?>>> zzax;
    private final Set<zzp<?>> zzay;
    private final PriorityBlockingQueue<zzp<?>> zzaz;
    private final PriorityBlockingQueue<zzp<?>> zzba;
    private final zzl[] zzbb;
    private zzd zzbc;
    private final List<Object> zzbd;
    private final zzb zzi;
    private final zzw zzj;
    private final zzk zzx;

    public zzs(zzb com_google_android_gms_internal_zzb, zzk com_google_android_gms_internal_zzk) {
        this(com_google_android_gms_internal_zzb, com_google_android_gms_internal_zzk, 4);
    }

    private zzs(zzb com_google_android_gms_internal_zzb, zzk com_google_android_gms_internal_zzk, int i) {
        this(com_google_android_gms_internal_zzb, com_google_android_gms_internal_zzk, 4, new zzh(new Handler(Looper.getMainLooper())));
    }

    private zzs(zzb com_google_android_gms_internal_zzb, zzk com_google_android_gms_internal_zzk, int i, zzw com_google_android_gms_internal_zzw) {
        this.zzaw = new AtomicInteger();
        this.zzax = new HashMap();
        this.zzay = new HashSet();
        this.zzaz = new PriorityBlockingQueue();
        this.zzba = new PriorityBlockingQueue();
        this.zzbd = new ArrayList();
        this.zzi = com_google_android_gms_internal_zzb;
        this.zzx = com_google_android_gms_internal_zzk;
        this.zzbb = new zzl[4];
        this.zzj = com_google_android_gms_internal_zzw;
    }

    public final void start() {
        int i = 0;
        if (this.zzbc != null) {
            this.zzbc.quit();
        }
        for (zzl com_google_android_gms_internal_zzl : this.zzbb) {
            if (com_google_android_gms_internal_zzl != null) {
                com_google_android_gms_internal_zzl.quit();
            }
        }
        this.zzbc = new zzd(this.zzaz, this.zzba, this.zzi, this.zzj);
        this.zzbc.start();
        while (i < this.zzbb.length) {
            zzl com_google_android_gms_internal_zzl2 = new zzl(this.zzba, this.zzx, this.zzi, this.zzj);
            this.zzbb[i] = com_google_android_gms_internal_zzl2;
            com_google_android_gms_internal_zzl2.start();
            i++;
        }
    }

    public final <T> zzp<T> zzc(zzp<T> com_google_android_gms_internal_zzp_T) {
        com_google_android_gms_internal_zzp_T.zza(this);
        synchronized (this.zzay) {
            this.zzay.add(com_google_android_gms_internal_zzp_T);
        }
        com_google_android_gms_internal_zzp_T.zza(this.zzaw.incrementAndGet());
        com_google_android_gms_internal_zzp_T.zzb("add-to-queue");
        if (com_google_android_gms_internal_zzp_T.zzh()) {
            synchronized (this.zzax) {
                String zzd = com_google_android_gms_internal_zzp_T.zzd();
                if (this.zzax.containsKey(zzd)) {
                    Queue queue = (Queue) this.zzax.get(zzd);
                    if (queue == null) {
                        queue = new LinkedList();
                    }
                    queue.add(com_google_android_gms_internal_zzp_T);
                    this.zzax.put(zzd, queue);
                    if (zzab.DEBUG) {
                        zzab.zza("Request for cacheKey=%s is in flight, putting on hold.", zzd);
                    }
                } else {
                    this.zzax.put(zzd, null);
                    this.zzaz.add(com_google_android_gms_internal_zzp_T);
                }
            }
        } else {
            this.zzba.add(com_google_android_gms_internal_zzp_T);
        }
        return com_google_android_gms_internal_zzp_T;
    }

    final <T> void zzd(zzp<T> com_google_android_gms_internal_zzp_T) {
        synchronized (this.zzay) {
            this.zzay.remove(com_google_android_gms_internal_zzp_T);
        }
        synchronized (this.zzbd) {
            Iterator it = this.zzbd.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        if (com_google_android_gms_internal_zzp_T.zzh()) {
            synchronized (this.zzax) {
                Queue queue = (Queue) this.zzax.remove(com_google_android_gms_internal_zzp_T.zzd());
                if (queue != null) {
                    if (zzab.DEBUG) {
                        zzab.zza("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(queue.size()), r2);
                    }
                    this.zzaz.addAll(queue);
                }
            }
        }
    }
}
