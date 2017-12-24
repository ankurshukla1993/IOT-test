package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.internal.zzbei;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager {
    private static final Object zzftx = new Object();
    private static HashSet<Uri> zzfty = new HashSet();
    private static ImageManager zzftz;
    private final Context mContext;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final ExecutorService zzfua = Executors.newFixedThreadPool(4);
    private final zza zzfub = null;
    private final zzbei zzfuc = new zzbei();
    private final Map<zza, ImageReceiver> zzfud = new HashMap();
    private final Map<Uri, ImageReceiver> zzfue = new HashMap();
    private final Map<Uri, Long> zzfuf = new HashMap();

    @KeepName
    final class ImageReceiver extends ResultReceiver {
        private final Uri mUri;
        private final ArrayList<zza> zzfug = new ArrayList();
        private /* synthetic */ ImageManager zzfuh;

        ImageReceiver(ImageManager imageManager, Uri uri) {
            this.zzfuh = imageManager;
            super(new Handler(Looper.getMainLooper()));
            this.mUri = uri;
        }

        public final void onReceiveResult(int i, Bundle bundle) {
            this.zzfuh.zzfua.execute(new zzb(this.zzfuh, this.mUri, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }

        public final void zzajr() {
            Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.putExtra("com.google.android.gms.extras.uri", this.mUri);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            this.zzfuh.mContext.sendBroadcast(intent);
        }

        public final void zzb(zza com_google_android_gms_common_images_zza) {
            com.google.android.gms.common.internal.zzc.zzfz("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zzfug.add(com_google_android_gms_common_images_zza);
        }

        public final void zzc(zza com_google_android_gms_common_images_zza) {
            com.google.android.gms.common.internal.zzc.zzfz("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zzfug.remove(com_google_android_gms_common_images_zza);
        }
    }

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    static final class zza extends LruCache<zzb, Bitmap> {
        protected final /* synthetic */ void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
            super.entryRemoved(z, (zzb) obj, (Bitmap) obj2, (Bitmap) obj3);
        }

        protected final /* synthetic */ int sizeOf(Object obj, Object obj2) {
            Bitmap bitmap = (Bitmap) obj2;
            return bitmap.getHeight() * bitmap.getRowBytes();
        }
    }

    final class zzb implements Runnable {
        private final Uri mUri;
        private /* synthetic */ ImageManager zzfuh;
        private final ParcelFileDescriptor zzfui;

        public zzb(ImageManager imageManager, Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.zzfuh = imageManager;
            this.mUri = uri;
            this.zzfui = parcelFileDescriptor;
        }

        public final void run() {
            String str = "LoadBitmapFromDiskRunnable can't be executed in the main thread";
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                String valueOf = String.valueOf(Thread.currentThread());
                String valueOf2 = String.valueOf(Looper.getMainLooper().getThread());
                Log.e("Asserts", new StringBuilder((String.valueOf(valueOf).length() + 56) + String.valueOf(valueOf2).length()).append("checkNotMainThread: current thread ").append(valueOf).append(" IS the main thread ").append(valueOf2).append("!").toString());
                throw new IllegalStateException(str);
            }
            boolean z = false;
            Bitmap bitmap = null;
            if (this.zzfui != null) {
                try {
                    bitmap = BitmapFactory.decodeFileDescriptor(this.zzfui.getFileDescriptor());
                } catch (Throwable e) {
                    String valueOf3 = String.valueOf(this.mUri);
                    Log.e("ImageManager", new StringBuilder(String.valueOf(valueOf3).length() + 34).append("OOM while loading bitmap for uri: ").append(valueOf3).toString(), e);
                    z = true;
                }
                try {
                    this.zzfui.close();
                } catch (Throwable e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.zzfuh.mHandler.post(new zzd(this.zzfuh, this.mUri, bitmap, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e3) {
                String valueOf4 = String.valueOf(this.mUri);
                Log.w("ImageManager", new StringBuilder(String.valueOf(valueOf4).length() + 32).append("Latch interrupted while posting ").append(valueOf4).toString());
            }
        }
    }

    final class zzc implements Runnable {
        private /* synthetic */ ImageManager zzfuh;
        private final zza zzfuj;

        public zzc(ImageManager imageManager, zza com_google_android_gms_common_images_zza) {
            this.zzfuh = imageManager;
            this.zzfuj = com_google_android_gms_common_images_zza;
        }

        public final void run() {
            com.google.android.gms.common.internal.zzc.zzfz("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) this.zzfuh.zzfud.get(this.zzfuj);
            if (imageReceiver != null) {
                this.zzfuh.zzfud.remove(this.zzfuj);
                imageReceiver.zzc(this.zzfuj);
            }
            zzb com_google_android_gms_common_images_zzb = this.zzfuj.zzful;
            if (com_google_android_gms_common_images_zzb.uri == null) {
                this.zzfuj.zza(this.zzfuh.mContext, this.zzfuh.zzfuc, true);
                return;
            }
            Bitmap zza = this.zzfuh.zza(com_google_android_gms_common_images_zzb);
            if (zza != null) {
                this.zzfuj.zza(this.zzfuh.mContext, zza, true);
                return;
            }
            Long l = (Long) this.zzfuh.zzfuf.get(com_google_android_gms_common_images_zzb.uri);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    this.zzfuj.zza(this.zzfuh.mContext, this.zzfuh.zzfuc, true);
                    return;
                }
                this.zzfuh.zzfuf.remove(com_google_android_gms_common_images_zzb.uri);
            }
            this.zzfuj.zza(this.zzfuh.mContext, this.zzfuh.zzfuc);
            imageReceiver = (ImageReceiver) this.zzfuh.zzfue.get(com_google_android_gms_common_images_zzb.uri);
            if (imageReceiver == null) {
                imageReceiver = new ImageReceiver(this.zzfuh, com_google_android_gms_common_images_zzb.uri);
                this.zzfuh.zzfue.put(com_google_android_gms_common_images_zzb.uri, imageReceiver);
            }
            imageReceiver.zzb(this.zzfuj);
            if (!(this.zzfuj instanceof zzd)) {
                this.zzfuh.zzfud.put(this.zzfuj, imageReceiver);
            }
            synchronized (ImageManager.zzftx) {
                if (!ImageManager.zzfty.contains(com_google_android_gms_common_images_zzb.uri)) {
                    ImageManager.zzfty.add(com_google_android_gms_common_images_zzb.uri);
                    imageReceiver.zzajr();
                }
            }
        }
    }

    final class zzd implements Runnable {
        private final Bitmap mBitmap;
        private final Uri mUri;
        private final CountDownLatch zzaoi;
        private /* synthetic */ ImageManager zzfuh;
        private boolean zzfuk;

        public zzd(ImageManager imageManager, Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.zzfuh = imageManager;
            this.mUri = uri;
            this.mBitmap = bitmap;
            this.zzfuk = z;
            this.zzaoi = countDownLatch;
        }

        public final void run() {
            com.google.android.gms.common.internal.zzc.zzfz("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.mBitmap != null;
            if (this.zzfuh.zzfub != null) {
                if (this.zzfuk) {
                    this.zzfuh.zzfub.evictAll();
                    System.gc();
                    this.zzfuk = false;
                    this.zzfuh.mHandler.post(this);
                    return;
                } else if (z) {
                    this.zzfuh.zzfub.put(new zzb(this.mUri), this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) this.zzfuh.zzfue.remove(this.mUri);
            if (imageReceiver != null) {
                ArrayList zza = imageReceiver.zzfug;
                int size = zza.size();
                for (int i = 0; i < size; i++) {
                    zza com_google_android_gms_common_images_zza = (zza) zza.get(i);
                    if (z) {
                        com_google_android_gms_common_images_zza.zza(this.zzfuh.mContext, this.mBitmap, false);
                    } else {
                        this.zzfuh.zzfuf.put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                        com_google_android_gms_common_images_zza.zza(this.zzfuh.mContext, this.zzfuh.zzfuc, false);
                    }
                    if (!(com_google_android_gms_common_images_zza instanceof zzd)) {
                        this.zzfuh.zzfud.remove(com_google_android_gms_common_images_zza);
                    }
                }
            }
            this.zzaoi.countDown();
            synchronized (ImageManager.zzftx) {
                ImageManager.zzfty.remove(this.mUri);
            }
        }
    }

    private ImageManager(Context context, boolean z) {
        this.mContext = context.getApplicationContext();
    }

    public static ImageManager create(Context context) {
        if (zzftz == null) {
            zzftz = new ImageManager(context, false);
        }
        return zzftz;
    }

    private final Bitmap zza(zzb com_google_android_gms_common_images_zzb) {
        return this.zzfub == null ? null : (Bitmap) this.zzfub.get(com_google_android_gms_common_images_zzb);
    }

    private final void zza(zza com_google_android_gms_common_images_zza) {
        com.google.android.gms.common.internal.zzc.zzfz("ImageManager.loadImage() must be called in the main thread");
        new zzc(this, com_google_android_gms_common_images_zza).run();
    }

    public final void loadImage(ImageView imageView, int i) {
        zza(new zzc(imageView, i));
    }

    public final void loadImage(ImageView imageView, Uri uri) {
        zza(new zzc(imageView, uri));
    }

    public final void loadImage(ImageView imageView, Uri uri, int i) {
        zza com_google_android_gms_common_images_zzc = new zzc(imageView, uri);
        com_google_android_gms_common_images_zzc.zzfun = i;
        zza(com_google_android_gms_common_images_zzc);
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri) {
        zza(new zzd(onImageLoadedListener, uri));
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri, int i) {
        zza com_google_android_gms_common_images_zzd = new zzd(onImageLoadedListener, uri);
        com_google_android_gms_common_images_zzd.zzfun = i;
        zza(com_google_android_gms_common_images_zzd);
    }
}
