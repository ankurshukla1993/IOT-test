package com.google.android.gms.internal;

import android.os.SystemClock;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class zzag implements zzb {
    private final Map<String, zzah> zzbt;
    private long zzbu;
    private final File zzbv;
    private final int zzbw;

    public zzag(File file) {
        this(file, 5242880);
    }

    private zzag(File file, int i) {
        this.zzbt = new LinkedHashMap(16, 0.75f, true);
        this.zzbu = 0;
        this.zzbv = file;
        this.zzbw = 5242880;
    }

    private final synchronized void remove(String str) {
        boolean delete = zze(str).delete();
        removeEntry(str);
        if (!delete) {
            zzab.zzb("Could not delete cache entry for key=%s, filename=%s", str, zzd(str));
        }
    }

    private final void removeEntry(String str) {
        zzah com_google_android_gms_internal_zzah = (zzah) this.zzbt.remove(str);
        if (com_google_android_gms_internal_zzah != null) {
            this.zzbu -= com_google_android_gms_internal_zzah.zzbx;
        }
    }

    private static int zza(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    private static InputStream zza(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    static String zza(zzai com_google_android_gms_internal_zzai) throws IOException {
        return new String(zza(com_google_android_gms_internal_zzai, zzc(com_google_android_gms_internal_zzai)), "UTF-8");
    }

    static void zza(OutputStream outputStream, int i) throws IOException {
        outputStream.write(i & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write(i >>> 24);
    }

    static void zza(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) ((int) j));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static void zza(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        zza(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    private final void zza(String str, zzah com_google_android_gms_internal_zzah) {
        if (this.zzbt.containsKey(str)) {
            zzah com_google_android_gms_internal_zzah2 = (zzah) this.zzbt.get(str);
            this.zzbu = (com_google_android_gms_internal_zzah.zzbx - com_google_android_gms_internal_zzah2.zzbx) + this.zzbu;
        } else {
            this.zzbu += com_google_android_gms_internal_zzah.zzbx;
        }
        this.zzbt.put(str, com_google_android_gms_internal_zzah);
    }

    private static byte[] zza(zzai com_google_android_gms_internal_zzai, long j) throws IOException {
        long zzn = com_google_android_gms_internal_zzai.zzn();
        if (j < 0 || j > zzn || ((long) ((int) j)) != j) {
            throw new IOException("streamToBytes length=" + j + ", maxLength=" + zzn);
        }
        byte[] bArr = new byte[((int) j)];
        new DataInputStream(com_google_android_gms_internal_zzai).readFully(bArr);
        return bArr;
    }

    static int zzb(InputStream inputStream) throws IOException {
        return (((zza(inputStream) | 0) | (zza(inputStream) << 8)) | (zza(inputStream) << 16)) | (zza(inputStream) << 24);
    }

    static Map<String, String> zzb(zzai com_google_android_gms_internal_zzai) throws IOException {
        int zzb = zzb((InputStream) com_google_android_gms_internal_zzai);
        Map<String, String> emptyMap = zzb == 0 ? Collections.emptyMap() : new HashMap(zzb);
        for (int i = 0; i < zzb; i++) {
            emptyMap.put(zza(com_google_android_gms_internal_zzai).intern(), zza(com_google_android_gms_internal_zzai).intern());
        }
        return emptyMap;
    }

    static long zzc(InputStream inputStream) throws IOException {
        return (((((((0 | (((long) zza(inputStream)) & 255)) | ((((long) zza(inputStream)) & 255) << 8)) | ((((long) zza(inputStream)) & 255) << 16)) | ((((long) zza(inputStream)) & 255) << 24)) | ((((long) zza(inputStream)) & 255) << 32)) | ((((long) zza(inputStream)) & 255) << 40)) | ((((long) zza(inputStream)) & 255) << 48)) | ((((long) zza(inputStream)) & 255) << 56);
    }

    private static String zzd(String str) {
        int length = str.length() / 2;
        String valueOf = String.valueOf(String.valueOf(str.substring(0, length).hashCode()));
        String valueOf2 = String.valueOf(String.valueOf(str.substring(length).hashCode()));
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    private final File zze(String str) {
        return new File(this.zzbv, zzd(str));
    }

    public final synchronized void initialize() {
        zzai com_google_android_gms_internal_zzai;
        if (this.zzbv.exists()) {
            File[] listFiles = this.zzbv.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    try {
                        long length = file.length();
                        com_google_android_gms_internal_zzai = new zzai(new BufferedInputStream(zza(file)), length);
                        zzah zzc = zzah.zzc(com_google_android_gms_internal_zzai);
                        zzc.zzbx = length;
                        zza(zzc.key, zzc);
                        com_google_android_gms_internal_zzai.close();
                    } catch (IOException e) {
                        file.delete();
                    } catch (Throwable th) {
                        com_google_android_gms_internal_zzai.close();
                    }
                }
            }
        } else if (!this.zzbv.mkdirs()) {
            zzab.zzc("Unable to create cache dir %s", this.zzbv.getAbsolutePath());
        }
    }

    public final synchronized zzc zza(String str) {
        zzc com_google_android_gms_internal_zzc;
        zzah com_google_android_gms_internal_zzah = (zzah) this.zzbt.get(str);
        if (com_google_android_gms_internal_zzah == null) {
            com_google_android_gms_internal_zzc = null;
        } else {
            File zze = zze(str);
            zzai com_google_android_gms_internal_zzai;
            try {
                com_google_android_gms_internal_zzai = new zzai(new BufferedInputStream(zza(zze)), zze.length());
                if (TextUtils.equals(str, zzah.zzc(com_google_android_gms_internal_zzai).key)) {
                    byte[] zza = zza(com_google_android_gms_internal_zzai, com_google_android_gms_internal_zzai.zzn());
                    zzc com_google_android_gms_internal_zzc2 = new zzc();
                    com_google_android_gms_internal_zzc2.data = zza;
                    com_google_android_gms_internal_zzc2.zza = com_google_android_gms_internal_zzah.zza;
                    com_google_android_gms_internal_zzc2.zzb = com_google_android_gms_internal_zzah.zzb;
                    com_google_android_gms_internal_zzc2.zzc = com_google_android_gms_internal_zzah.zzc;
                    com_google_android_gms_internal_zzc2.zzd = com_google_android_gms_internal_zzah.zzd;
                    com_google_android_gms_internal_zzc2.zze = com_google_android_gms_internal_zzah.zze;
                    com_google_android_gms_internal_zzc2.zzf = com_google_android_gms_internal_zzah.zzf;
                    com_google_android_gms_internal_zzai.close();
                    com_google_android_gms_internal_zzc = com_google_android_gms_internal_zzc2;
                } else {
                    zzab.zzb("%s: key=%s, found=%s", zze.getAbsolutePath(), str, zzah.zzc(com_google_android_gms_internal_zzai).key);
                    removeEntry(str);
                    com_google_android_gms_internal_zzai.close();
                    com_google_android_gms_internal_zzc = null;
                }
            } catch (IOException e) {
                zzab.zzb("%s: %s", zze.getAbsolutePath(), e.toString());
                remove(str);
                com_google_android_gms_internal_zzc = null;
            } catch (Throwable th) {
                com_google_android_gms_internal_zzai.close();
            }
        }
        return com_google_android_gms_internal_zzc;
    }

    public final synchronized void zza(String str, zzc com_google_android_gms_internal_zzc) {
        int i = 0;
        synchronized (this) {
            int length = com_google_android_gms_internal_zzc.data.length;
            if (this.zzbu + ((long) length) >= ((long) this.zzbw)) {
                int i2;
                if (zzab.DEBUG) {
                    zzab.zza("Pruning old cache entries.", new Object[0]);
                }
                long j = this.zzbu;
                long elapsedRealtime = SystemClock.elapsedRealtime();
                Iterator it = this.zzbt.entrySet().iterator();
                while (it.hasNext()) {
                    zzah com_google_android_gms_internal_zzah = (zzah) ((Entry) it.next()).getValue();
                    if (zze(com_google_android_gms_internal_zzah.key).delete()) {
                        this.zzbu -= com_google_android_gms_internal_zzah.zzbx;
                    } else {
                        zzab.zzb("Could not delete cache entry for key=%s, filename=%s", com_google_android_gms_internal_zzah.key, zzd(com_google_android_gms_internal_zzah.key));
                    }
                    it.remove();
                    i2 = i + 1;
                    if (((float) (this.zzbu + ((long) length))) < ((float) this.zzbw) * 0.9f) {
                        break;
                    }
                    i = i2;
                }
                i2 = i;
                if (zzab.DEBUG) {
                    zzab.zza("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.zzbu - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                }
            }
            File zze = zze(str);
            try {
                OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(zze));
                zzah com_google_android_gms_internal_zzah2 = new zzah(str, com_google_android_gms_internal_zzc);
                if (com_google_android_gms_internal_zzah2.zza(bufferedOutputStream)) {
                    bufferedOutputStream.write(com_google_android_gms_internal_zzc.data);
                    bufferedOutputStream.close();
                    zza(str, com_google_android_gms_internal_zzah2);
                } else {
                    bufferedOutputStream.close();
                    zzab.zzb("Failed to write header for %s", zze.getAbsolutePath());
                    throw new IOException();
                }
            } catch (IOException e) {
                if (!zze.delete()) {
                    zzab.zzb("Could not clean up file %s", zze.getAbsolutePath());
                }
            }
        }
    }
}
