package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class BitmapTeleporter extends zzbej implements ReflectedParcelable {
    public static final Creator<BitmapTeleporter> CREATOR = new zza();
    private ParcelFileDescriptor zzcoi;
    private int zzdzm;
    private int zzefe;
    private Bitmap zzfsx;
    private boolean zzfsy;
    private File zzfsz;

    BitmapTeleporter(int i, ParcelFileDescriptor parcelFileDescriptor, int i2) {
        this.zzdzm = i;
        this.zzcoi = parcelFileDescriptor;
        this.zzefe = i2;
        this.zzfsx = null;
        this.zzfsy = false;
    }

    public BitmapTeleporter(Bitmap bitmap) {
        this.zzdzm = 1;
        this.zzcoi = null;
        this.zzefe = 0;
        this.zzfsx = bitmap;
        this.zzfsy = true;
    }

    private static void zza(Closeable closeable) {
        try {
            closeable.close();
        } catch (Throwable e) {
            Log.w("BitmapTeleporter", "Could not close stream", e);
        }
    }

    private final FileOutputStream zzajl() {
        if (this.zzfsz == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        try {
            File createTempFile = File.createTempFile("teleporter", ".tmp", this.zzfsz);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                this.zzcoi = ParcelFileDescriptor.open(createTempFile, 268435456);
                createTempFile.delete();
                return fileOutputStream;
            } catch (FileNotFoundException e) {
                throw new IllegalStateException("Temporary file is somehow already deleted");
            }
        } catch (Throwable e2) {
            throw new IllegalStateException("Could not create temporary file", e2);
        }
    }

    public final void release() {
        if (!this.zzfsy) {
            try {
                this.zzcoi.close();
            } catch (Throwable e) {
                Log.w("BitmapTeleporter", "Could not close PFD", e);
            }
        }
    }

    public final void setTempDir(File file) {
        if (file == null) {
            throw new NullPointerException("Cannot set null temp directory");
        }
        this.zzfsz = file;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzcoi == null) {
            Bitmap bitmap = this.zzfsx;
            Buffer allocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
            bitmap.copyPixelsToBuffer(allocate);
            byte[] array = allocate.array();
            Closeable dataOutputStream = new DataOutputStream(new BufferedOutputStream(zzajl()));
            try {
                dataOutputStream.writeInt(array.length);
                dataOutputStream.writeInt(bitmap.getWidth());
                dataOutputStream.writeInt(bitmap.getHeight());
                dataOutputStream.writeUTF(bitmap.getConfig().toString());
                dataOutputStream.write(array);
                zza(dataOutputStream);
            } catch (Throwable e) {
                throw new IllegalStateException("Could not write into unlinked file", e);
            } catch (Throwable th) {
                zza(dataOutputStream);
            }
        }
        int i2 = i | 1;
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, this.zzcoi, i2, false);
        zzbem.zzc(parcel, 3, this.zzefe);
        zzbem.zzai(parcel, zze);
        this.zzcoi = null;
    }

    public final Bitmap zzajk() {
        if (!this.zzfsy) {
            Closeable dataInputStream = new DataInputStream(new AutoCloseInputStream(this.zzcoi));
            try {
                byte[] bArr = new byte[dataInputStream.readInt()];
                int readInt = dataInputStream.readInt();
                int readInt2 = dataInputStream.readInt();
                Config valueOf = Config.valueOf(dataInputStream.readUTF());
                dataInputStream.read(bArr);
                zza(dataInputStream);
                Buffer wrap = ByteBuffer.wrap(bArr);
                Bitmap createBitmap = Bitmap.createBitmap(readInt, readInt2, valueOf);
                createBitmap.copyPixelsFromBuffer(wrap);
                this.zzfsx = createBitmap;
                this.zzfsy = true;
            } catch (Throwable e) {
                throw new IllegalStateException("Could not read from parcel file descriptor", e);
            } catch (Throwable th) {
                zza(dataInputStream);
            }
        }
        return this.zzfsx;
    }
}
