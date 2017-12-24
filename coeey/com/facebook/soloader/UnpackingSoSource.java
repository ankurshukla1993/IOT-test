package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import android.util.Log;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public abstract class UnpackingSoSource extends DirectorySoSource {
    private static final String DEPS_FILE_NAME = "dso_deps";
    private static final String LOCK_FILE_NAME = "dso_lock";
    private static final String MANIFEST_FILE_NAME = "dso_manifest";
    private static final byte MANIFEST_VERSION = (byte) 1;
    private static final byte STATE_CLEAN = (byte) 1;
    private static final byte STATE_DIRTY = (byte) 0;
    private static final String STATE_FILE_NAME = "dso_state";
    private static final String TAG = "fb-UnpackingSoSource";
    protected final Context mContext;

    protected static abstract class Unpacker implements Closeable {
        protected abstract DsoManifest getDsoManifest() throws IOException;

        protected abstract InputDsoIterator openDsoIterator() throws IOException;

        protected Unpacker() {
        }

        public void close() throws IOException {
        }
    }

    protected static abstract class InputDsoIterator implements Closeable {
        public abstract boolean hasNext();

        public abstract InputDso next() throws IOException;

        protected InputDsoIterator() {
        }

        public void close() throws IOException {
        }
    }

    public static class Dso {
        public final String hash;
        public final String name;

        public Dso(String name, String hash) {
            this.name = name;
            this.hash = hash;
        }
    }

    public static final class DsoManifest {
        public final Dso[] dsos;

        public DsoManifest(Dso[] dsos) {
            this.dsos = dsos;
        }

        static final DsoManifest read(DataInput xdi) throws IOException {
            if (xdi.readByte() != 1) {
                throw new RuntimeException("wrong dso manifest version");
            }
            int nrDso = xdi.readInt();
            if (nrDso < 0) {
                throw new RuntimeException("illegal number of shared libraries");
            }
            Dso[] dsos = new Dso[nrDso];
            for (int i = 0; i < nrDso; i++) {
                dsos[i] = new Dso(xdi.readUTF(), xdi.readUTF());
            }
            return new DsoManifest(dsos);
        }

        public final void write(DataOutput xdo) throws IOException {
            xdo.writeByte(1);
            xdo.writeInt(this.dsos.length);
            for (int i = 0; i < this.dsos.length; i++) {
                xdo.writeUTF(this.dsos[i].name);
                xdo.writeUTF(this.dsos[i].hash);
            }
        }
    }

    protected static final class InputDso implements Closeable {
        public final InputStream content;
        public final Dso dso;

        public InputDso(Dso dso, InputStream content) {
            this.dso = dso;
            this.content = content;
        }

        public void close() throws IOException {
            this.content.close();
        }
    }

    protected abstract Unpacker makeUnpacker() throws IOException;

    protected UnpackingSoSource(Context context, String name) {
        super(getSoStorePath(context, name), 1);
        this.mContext = context;
    }

    public static File getSoStorePath(Context context, String name) {
        return new File(context.getApplicationInfo().dataDir + "/" + name);
    }

    private static void writeState(File stateFileName, byte state) throws IOException {
        Throwable th;
        RandomAccessFile stateFile = new RandomAccessFile(stateFileName, "rw");
        Throwable th2 = null;
        try {
            stateFile.seek(0);
            stateFile.write(state);
            stateFile.setLength(stateFile.getFilePointer());
            stateFile.getFD().sync();
            if (stateFile == null) {
                return;
            }
            if (th2 != null) {
                try {
                    stateFile.close();
                    return;
                } catch (Throwable x2) {
                    th2.addSuppressed(x2);
                    return;
                }
            }
            stateFile.close();
            return;
        } catch (Throwable th22) {
            Throwable th3 = th22;
            th22 = th;
            th = th3;
        }
        if (stateFile != null) {
            if (th22 != null) {
                try {
                    stateFile.close();
                } catch (Throwable x22) {
                    th22.addSuppressed(x22);
                }
            } else {
                stateFile.close();
            }
        }
        throw th;
        throw th;
    }

    private void deleteUnmentionedFiles(Dso[] dsos) throws IOException {
        String[] existingFiles = this.soDirectory.list();
        if (existingFiles == null) {
            throw new IOException("unable to list directory " + this.soDirectory);
        }
        for (String fileName : existingFiles) {
            if (!(fileName.equals(STATE_FILE_NAME) || fileName.equals(LOCK_FILE_NAME) || fileName.equals(DEPS_FILE_NAME) || fileName.equals(MANIFEST_FILE_NAME))) {
                boolean found = false;
                int j = 0;
                while (!found && j < dsos.length) {
                    if (dsos[j].name.equals(fileName)) {
                        found = true;
                    }
                    j++;
                }
                if (!found) {
                    File fileNameToDelete = new File(this.soDirectory, fileName);
                    Log.v(TAG, "deleting unaccounted-for file " + fileNameToDelete);
                    SysUtil.dumbDeleteRecursive(fileNameToDelete);
                }
            }
        }
    }

    private void extractDso(InputDso iDso, byte[] ioBuffer) throws IOException {
        RandomAccessFile dsoFile;
        Log.i(TAG, "extracting DSO " + iDso.dso.name);
        File dsoFileName = new File(this.soDirectory, iDso.dso.name);
        try {
            dsoFile = new RandomAccessFile(dsoFileName, "rw");
        } catch (IOException ex) {
            Log.w(TAG, "error overwriting " + dsoFileName + " trying to delete and start over", ex);
            dsoFileName.delete();
            dsoFile = new RandomAccessFile(dsoFileName, "rw");
        }
        try {
            int sizeHint = iDso.content.available();
            if (sizeHint > 1) {
                SysUtil.fallocateIfSupported(dsoFile.getFD(), (long) sizeHint);
            }
            SysUtil.copyBytes(dsoFile, iDso.content, Integer.MAX_VALUE, ioBuffer);
            dsoFile.setLength(dsoFile.getFilePointer());
            if (!dsoFileName.setExecutable(true, false)) {
                throw new IOException("cannot make file executable: " + dsoFileName);
            }
        } finally {
            dsoFile.close();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void regenerate(byte r17, com.facebook.soloader.UnpackingSoSource.DsoManifest r18, com.facebook.soloader.UnpackingSoSource.InputDsoIterator r19) throws java.io.IOException {
        /*
        r16 = this;
        r11 = "fb-UnpackingSoSource";
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r13 = "regenerating DSO store ";
        r12 = r12.append(r13);
        r13 = r16.getClass();
        r13 = r13.getName();
        r12 = r12.append(r13);
        r12 = r12.toString();
        android.util.Log.v(r11, r12);
        r8 = new java.io.File;
        r0 = r16;
        r11 = r0.soDirectory;
        r12 = "dso_manifest";
        r8.<init>(r11, r12);
        r7 = new java.io.RandomAccessFile;
        r11 = "rw";
        r7.<init>(r8, r11);
        r13 = 0;
        r2 = 0;
        r11 = 1;
        r0 = r17;
        if (r0 != r11) goto L_0x0096;
    L_0x0039:
        r2 = com.facebook.soloader.UnpackingSoSource.DsoManifest.read(r7);	 Catch:{ Exception -> 0x008e }
        r3 = r2;
    L_0x003e:
        if (r3 != 0) goto L_0x00f7;
    L_0x0040:
        r2 = new com.facebook.soloader.UnpackingSoSource$DsoManifest;	 Catch:{ Throwable -> 0x00f2, all -> 0x00be }
        r11 = 0;
        r11 = new com.facebook.soloader.UnpackingSoSource.Dso[r11];	 Catch:{ Throwable -> 0x00f2, all -> 0x00be }
        r2.<init>(r11);	 Catch:{ Throwable -> 0x00f2, all -> 0x00be }
    L_0x0048:
        r0 = r18;
        r11 = r0.dsos;	 Catch:{ Throwable -> 0x00ac, all -> 0x00be }
        r0 = r16;
        r0.deleteUnmentionedFiles(r11);	 Catch:{ Throwable -> 0x00ac, all -> 0x00be }
        r11 = 32768; // 0x8000 float:4.5918E-41 double:1.61895E-319;
        r6 = new byte[r11];	 Catch:{ Throwable -> 0x00ac, all -> 0x00be }
    L_0x0056:
        r11 = r19.hasNext();	 Catch:{ Throwable -> 0x00ac, all -> 0x00be }
        if (r11 == 0) goto L_0x00d8;
    L_0x005c:
        r5 = r19.next();	 Catch:{ Throwable -> 0x00ac, all -> 0x00be }
        r12 = 0;
        r9 = 1;
        r4 = 0;
    L_0x0063:
        if (r9 == 0) goto L_0x0098;
    L_0x0065:
        r11 = r2.dsos;	 Catch:{ Throwable -> 0x00c1, all -> 0x00f5 }
        r11 = r11.length;	 Catch:{ Throwable -> 0x00c1, all -> 0x00f5 }
        if (r4 >= r11) goto L_0x0098;
    L_0x006a:
        r11 = r2.dsos;	 Catch:{ Throwable -> 0x00c1, all -> 0x00f5 }
        r11 = r11[r4];	 Catch:{ Throwable -> 0x00c1, all -> 0x00f5 }
        r11 = r11.name;	 Catch:{ Throwable -> 0x00c1, all -> 0x00f5 }
        r14 = r5.dso;	 Catch:{ Throwable -> 0x00c1, all -> 0x00f5 }
        r14 = r14.name;	 Catch:{ Throwable -> 0x00c1, all -> 0x00f5 }
        r11 = r11.equals(r14);	 Catch:{ Throwable -> 0x00c1, all -> 0x00f5 }
        if (r11 == 0) goto L_0x008b;
    L_0x007a:
        r11 = r2.dsos;	 Catch:{ Throwable -> 0x00c1, all -> 0x00f5 }
        r11 = r11[r4];	 Catch:{ Throwable -> 0x00c1, all -> 0x00f5 }
        r11 = r11.hash;	 Catch:{ Throwable -> 0x00c1, all -> 0x00f5 }
        r14 = r5.dso;	 Catch:{ Throwable -> 0x00c1, all -> 0x00f5 }
        r14 = r14.hash;	 Catch:{ Throwable -> 0x00c1, all -> 0x00f5 }
        r11 = r11.equals(r14);	 Catch:{ Throwable -> 0x00c1, all -> 0x00f5 }
        if (r11 == 0) goto L_0x008b;
    L_0x008a:
        r9 = 0;
    L_0x008b:
        r4 = r4 + 1;
        goto L_0x0063;
    L_0x008e:
        r1 = move-exception;
        r11 = "fb-UnpackingSoSource";
        r12 = "error reading existing DSO manifest";
        android.util.Log.i(r11, r12, r1);	 Catch:{ Throwable -> 0x00ac, all -> 0x00be }
    L_0x0096:
        r3 = r2;
        goto L_0x003e;
    L_0x0098:
        if (r9 == 0) goto L_0x009f;
    L_0x009a:
        r0 = r16;
        r0.extractDso(r5, r6);	 Catch:{ Throwable -> 0x00c1, all -> 0x00f5 }
    L_0x009f:
        if (r5 == 0) goto L_0x0056;
    L_0x00a1:
        if (r12 == 0) goto L_0x00ba;
    L_0x00a3:
        r5.close();	 Catch:{ Throwable -> 0x00a7, all -> 0x00be }
        goto L_0x0056;
    L_0x00a7:
        r10 = move-exception;
        r12.addSuppressed(r10);	 Catch:{ Throwable -> 0x00ac, all -> 0x00be }
        goto L_0x0056;
    L_0x00ac:
        r11 = move-exception;
    L_0x00ad:
        throw r11;	 Catch:{ all -> 0x00ae }
    L_0x00ae:
        r12 = move-exception;
        r15 = r12;
        r12 = r11;
        r11 = r15;
    L_0x00b2:
        if (r7 == 0) goto L_0x00b9;
    L_0x00b4:
        if (r12 == 0) goto L_0x00ee;
    L_0x00b6:
        r7.close();	 Catch:{ Throwable -> 0x00e9 }
    L_0x00b9:
        throw r11;
    L_0x00ba:
        r5.close();	 Catch:{ Throwable -> 0x00ac, all -> 0x00be }
        goto L_0x0056;
    L_0x00be:
        r11 = move-exception;
        r12 = r13;
        goto L_0x00b2;
    L_0x00c1:
        r11 = move-exception;
        throw r11;	 Catch:{ all -> 0x00c3 }
    L_0x00c3:
        r12 = move-exception;
        r15 = r12;
        r12 = r11;
        r11 = r15;
    L_0x00c7:
        if (r5 == 0) goto L_0x00ce;
    L_0x00c9:
        if (r12 == 0) goto L_0x00d4;
    L_0x00cb:
        r5.close();	 Catch:{ Throwable -> 0x00cf, all -> 0x00be }
    L_0x00ce:
        throw r11;	 Catch:{ Throwable -> 0x00ac, all -> 0x00be }
    L_0x00cf:
        r10 = move-exception;
        r12.addSuppressed(r10);	 Catch:{ Throwable -> 0x00ac, all -> 0x00be }
        goto L_0x00ce;
    L_0x00d4:
        r5.close();	 Catch:{ Throwable -> 0x00ac, all -> 0x00be }
        goto L_0x00ce;
    L_0x00d8:
        if (r7 == 0) goto L_0x00df;
    L_0x00da:
        if (r13 == 0) goto L_0x00e5;
    L_0x00dc:
        r7.close();	 Catch:{ Throwable -> 0x00e0 }
    L_0x00df:
        return;
    L_0x00e0:
        r10 = move-exception;
        r13.addSuppressed(r10);
        goto L_0x00df;
    L_0x00e5:
        r7.close();
        goto L_0x00df;
    L_0x00e9:
        r10 = move-exception;
        r12.addSuppressed(r10);
        goto L_0x00b9;
    L_0x00ee:
        r7.close();
        goto L_0x00b9;
    L_0x00f2:
        r11 = move-exception;
        r2 = r3;
        goto L_0x00ad;
    L_0x00f5:
        r11 = move-exception;
        goto L_0x00c7;
    L_0x00f7:
        r2 = r3;
        goto L_0x0048;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.regenerate(byte, com.facebook.soloader.UnpackingSoSource$DsoManifest, com.facebook.soloader.UnpackingSoSource$InputDsoIterator):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean refreshLocked(com.facebook.soloader.FileLocker r24, int r25, byte[] r26) throws java.io.IOException {
        /*
        r23 = this;
        r7 = new java.io.File;
        r0 = r23;
        r3 = r0.soDirectory;
        r5 = "dso_state";
        r7.<init>(r3, r5);
        r15 = new java.io.RandomAccessFile;
        r3 = "rw";
        r15.<init>(r7, r3);
        r5 = 0;
        r14 = r15.readByte();	 Catch:{ EOFException -> 0x00c2, Throwable -> 0x00d1, all -> 0x019f }
        r3 = 1;
        if (r14 == r3) goto L_0x0045;
    L_0x001a:
        r3 = "fb-UnpackingSoSource";
        r8 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x00c2, Throwable -> 0x00d1, all -> 0x019f }
        r8.<init>();	 Catch:{ EOFException -> 0x00c2, Throwable -> 0x00d1, all -> 0x019f }
        r18 = "dso store ";
        r0 = r18;
        r8 = r8.append(r0);	 Catch:{ EOFException -> 0x00c2, Throwable -> 0x00d1, all -> 0x019f }
        r0 = r23;
        r0 = r0.soDirectory;	 Catch:{ EOFException -> 0x00c2, Throwable -> 0x00d1, all -> 0x019f }
        r18 = r0;
        r0 = r18;
        r8 = r8.append(r0);	 Catch:{ EOFException -> 0x00c2, Throwable -> 0x00d1, all -> 0x019f }
        r18 = " regeneration interrupted: wiping clean";
        r0 = r18;
        r8 = r8.append(r0);	 Catch:{ EOFException -> 0x00c2, Throwable -> 0x00d1, all -> 0x019f }
        r8 = r8.toString();	 Catch:{ EOFException -> 0x00c2, Throwable -> 0x00d1, all -> 0x019f }
        android.util.Log.v(r3, r8);	 Catch:{ EOFException -> 0x00c2, Throwable -> 0x00d1, all -> 0x019f }
        r14 = 0;
    L_0x0045:
        if (r15 == 0) goto L_0x004c;
    L_0x0047:
        if (r5 == 0) goto L_0x00cc;
    L_0x0049:
        r15.close();	 Catch:{ Throwable -> 0x00c5 }
    L_0x004c:
        r4 = new java.io.File;
        r0 = r23;
        r3 = r0.soDirectory;
        r5 = "dso_deps";
        r4.<init>(r3, r5);
        r10 = 0;
        r9 = new java.io.RandomAccessFile;
        r3 = "rw";
        r9.<init>(r4, r3);
        r18 = 0;
        r20 = r9.length();	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
        r0 = r20;
        r3 = (int) r0;	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
        r12 = new byte[r3];	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
        r3 = r9.read(r12);	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
        r5 = r12.length;	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
        if (r3 == r5) goto L_0x0079;
    L_0x0071:
        r3 = "fb-UnpackingSoSource";
        r5 = "short read of so store deps file: marking unclean";
        android.util.Log.v(r3, r5);	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
        r14 = 0;
    L_0x0079:
        r0 = r26;
        r3 = java.util.Arrays.equals(r12, r0);	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
        if (r3 != 0) goto L_0x0089;
    L_0x0081:
        r3 = "fb-UnpackingSoSource";
        r5 = "deps mismatch on deps store: regenerating";
        android.util.Log.v(r3, r5);	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
        r14 = 0;
    L_0x0089:
        if (r14 != 0) goto L_0x00b7;
    L_0x008b:
        r3 = "fb-UnpackingSoSource";
        r5 = "so store dirty: regenerating";
        android.util.Log.v(r3, r5);	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
        r3 = 0;
        writeState(r7, r3);	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
        r16 = r23.makeUnpacker();	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
        r8 = 0;
        r10 = r16.getDsoManifest();	 Catch:{ Throwable -> 0x00f3, all -> 0x0117 }
        r13 = r16.openDsoIterator();	 Catch:{ Throwable -> 0x00f3, all -> 0x0117 }
        r5 = 0;
        r0 = r23;
        r0.regenerate(r14, r10, r13);	 Catch:{ Throwable -> 0x011a }
        if (r13 == 0) goto L_0x00b0;
    L_0x00ab:
        if (r5 == 0) goto L_0x0113;
    L_0x00ad:
        r13.close();	 Catch:{ Throwable -> 0x00ec, all -> 0x0117 }
    L_0x00b0:
        if (r16 == 0) goto L_0x00b7;
    L_0x00b2:
        if (r8 == 0) goto L_0x013b;
    L_0x00b4:
        r16.close();	 Catch:{ Throwable -> 0x0130, all -> 0x0137 }
    L_0x00b7:
        if (r9 == 0) goto L_0x00be;
    L_0x00b9:
        if (r18 == 0) goto L_0x0155;
    L_0x00bb:
        r9.close();	 Catch:{ Throwable -> 0x014b }
    L_0x00be:
        if (r10 != 0) goto L_0x0165;
    L_0x00c0:
        r3 = 0;
    L_0x00c1:
        return r3;
    L_0x00c2:
        r11 = move-exception;
        r14 = 0;
        goto L_0x0045;
    L_0x00c5:
        r17 = move-exception;
        r0 = r17;
        r5.addSuppressed(r0);
        goto L_0x004c;
    L_0x00cc:
        r15.close();
        goto L_0x004c;
    L_0x00d1:
        r3 = move-exception;
        throw r3;	 Catch:{ all -> 0x00d3 }
    L_0x00d3:
        r5 = move-exception;
        r22 = r5;
        r5 = r3;
        r3 = r22;
    L_0x00d9:
        if (r15 == 0) goto L_0x00e0;
    L_0x00db:
        if (r5 == 0) goto L_0x00e8;
    L_0x00dd:
        r15.close();	 Catch:{ Throwable -> 0x00e1 }
    L_0x00e0:
        throw r3;
    L_0x00e1:
        r17 = move-exception;
        r0 = r17;
        r5.addSuppressed(r0);
        goto L_0x00e0;
    L_0x00e8:
        r15.close();
        goto L_0x00e0;
    L_0x00ec:
        r17 = move-exception;
        r0 = r17;
        r5.addSuppressed(r0);	 Catch:{ Throwable -> 0x00f3, all -> 0x0117 }
        goto L_0x00b0;
    L_0x00f3:
        r3 = move-exception;
        throw r3;	 Catch:{ all -> 0x00f5 }
    L_0x00f5:
        r5 = move-exception;
        r22 = r5;
        r5 = r3;
        r3 = r22;
    L_0x00fb:
        if (r16 == 0) goto L_0x0102;
    L_0x00fd:
        if (r5 == 0) goto L_0x0147;
    L_0x00ff:
        r16.close();	 Catch:{ Throwable -> 0x0140, all -> 0x0137 }
    L_0x0102:
        throw r3;	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
    L_0x0103:
        r3 = move-exception;
        throw r3;	 Catch:{ all -> 0x0105 }
    L_0x0105:
        r5 = move-exception;
        r22 = r5;
        r5 = r3;
        r3 = r22;
    L_0x010b:
        if (r9 == 0) goto L_0x0112;
    L_0x010d:
        if (r5 == 0) goto L_0x0161;
    L_0x010f:
        r9.close();	 Catch:{ Throwable -> 0x015a }
    L_0x0112:
        throw r3;
    L_0x0113:
        r13.close();	 Catch:{ Throwable -> 0x00f3, all -> 0x0117 }
        goto L_0x00b0;
    L_0x0117:
        r3 = move-exception;
        r5 = r8;
        goto L_0x00fb;
    L_0x011a:
        r5 = move-exception;
        throw r5;	 Catch:{ all -> 0x011c }
    L_0x011c:
        r3 = move-exception;
        if (r13 == 0) goto L_0x0124;
    L_0x011f:
        if (r5 == 0) goto L_0x012c;
    L_0x0121:
        r13.close();	 Catch:{ Throwable -> 0x0125, all -> 0x0117 }
    L_0x0124:
        throw r3;	 Catch:{ Throwable -> 0x00f3, all -> 0x0117 }
    L_0x0125:
        r17 = move-exception;
        r0 = r17;
        r5.addSuppressed(r0);	 Catch:{ Throwable -> 0x00f3, all -> 0x0117 }
        goto L_0x0124;
    L_0x012c:
        r13.close();	 Catch:{ Throwable -> 0x00f3, all -> 0x0117 }
        goto L_0x0124;
    L_0x0130:
        r17 = move-exception;
        r0 = r17;
        r8.addSuppressed(r0);	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
        goto L_0x00b7;
    L_0x0137:
        r3 = move-exception;
        r5 = r18;
        goto L_0x010b;
    L_0x013b:
        r16.close();	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
        goto L_0x00b7;
    L_0x0140:
        r17 = move-exception;
        r0 = r17;
        r5.addSuppressed(r0);	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
        goto L_0x0102;
    L_0x0147:
        r16.close();	 Catch:{ Throwable -> 0x0103, all -> 0x0137 }
        goto L_0x0102;
    L_0x014b:
        r17 = move-exception;
        r0 = r18;
        r1 = r17;
        r0.addSuppressed(r1);
        goto L_0x00be;
    L_0x0155:
        r9.close();
        goto L_0x00be;
    L_0x015a:
        r17 = move-exception;
        r0 = r17;
        r5.addSuppressed(r0);
        goto L_0x0112;
    L_0x0161:
        r9.close();
        goto L_0x0112;
    L_0x0165:
        r6 = r10;
        r2 = new com.facebook.soloader.UnpackingSoSource$1;
        r3 = r23;
        r5 = r26;
        r8 = r24;
        r2.<init>(r4, r5, r6, r7, r8);
        r3 = r25 & 1;
        if (r3 == 0) goto L_0x019b;
    L_0x0175:
        r3 = new java.lang.Thread;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r8 = "SoSync:";
        r5 = r5.append(r8);
        r0 = r23;
        r8 = r0.soDirectory;
        r8 = r8.getName();
        r5 = r5.append(r8);
        r5 = r5.toString();
        r3.<init>(r2, r5);
        r3.start();
    L_0x0198:
        r3 = 1;
        goto L_0x00c1;
    L_0x019b:
        r2.run();
        goto L_0x0198;
    L_0x019f:
        r3 = move-exception;
        goto L_0x00d9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.refreshLocked(com.facebook.soloader.FileLocker, int, byte[]):boolean");
    }

    protected byte[] getDepsBlock() throws IOException {
        Throwable th;
        Parcel parcel = Parcel.obtain();
        Unpacker u = makeUnpacker();
        Throwable th2 = null;
        try {
            Dso[] dsos = u.getDsoManifest().dsos;
            parcel.writeByte((byte) 1);
            parcel.writeInt(dsos.length);
            for (int i = 0; i < dsos.length; i++) {
                parcel.writeString(dsos[i].name);
                parcel.writeString(dsos[i].hash);
            }
            if (u != null) {
                if (th2 != null) {
                    try {
                        u.close();
                    } catch (Throwable x2) {
                        th2.addSuppressed(x2);
                    }
                } else {
                    u.close();
                }
            }
            byte[] depsBlock = parcel.marshall();
            parcel.recycle();
            return depsBlock;
        } catch (Throwable th22) {
            Throwable th3 = th22;
            th22 = th;
            th = th3;
        }
        throw th;
        if (u != null) {
            if (th22 != null) {
                try {
                    u.close();
                } catch (Throwable x22) {
                    th22.addSuppressed(x22);
                }
            } else {
                u.close();
            }
        }
        throw th;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void prepare(int r7) throws java.io.IOException {
        /*
        r6 = this;
        r2 = r6.soDirectory;
        com.facebook.soloader.SysUtil.mkdirOrThrow(r2);
        r1 = new java.io.File;
        r2 = r6.soDirectory;
        r3 = "dso_lock";
        r1.<init>(r2, r3);
        r0 = com.facebook.soloader.FileLocker.lock(r1);
        r2 = "fb-UnpackingSoSource";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0072 }
        r3.<init>();	 Catch:{ all -> 0x0072 }
        r4 = "locked dso store ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0072 }
        r4 = r6.soDirectory;	 Catch:{ all -> 0x0072 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0072 }
        r3 = r3.toString();	 Catch:{ all -> 0x0072 }
        android.util.Log.v(r2, r3);	 Catch:{ all -> 0x0072 }
        r2 = r6.getDepsBlock();	 Catch:{ all -> 0x0072 }
        r2 = r6.refreshLocked(r0, r7, r2);	 Catch:{ all -> 0x0072 }
        if (r2 == 0) goto L_0x0057;
    L_0x0036:
        r0 = 0;
    L_0x0037:
        if (r0 == 0) goto L_0x0093;
    L_0x0039:
        r2 = "fb-UnpackingSoSource";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "releasing dso store lock for ";
        r3 = r3.append(r4);
        r4 = r6.soDirectory;
        r3 = r3.append(r4);
        r3 = r3.toString();
        android.util.Log.v(r2, r3);
        r0.close();
    L_0x0056:
        return;
    L_0x0057:
        r2 = "fb-UnpackingSoSource";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0072 }
        r3.<init>();	 Catch:{ all -> 0x0072 }
        r4 = "dso store is up-to-date: ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0072 }
        r4 = r6.soDirectory;	 Catch:{ all -> 0x0072 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0072 }
        r3 = r3.toString();	 Catch:{ all -> 0x0072 }
        android.util.Log.i(r2, r3);	 Catch:{ all -> 0x0072 }
        goto L_0x0037;
    L_0x0072:
        r2 = move-exception;
        if (r0 == 0) goto L_0x00b4;
    L_0x0075:
        r3 = "fb-UnpackingSoSource";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "releasing dso store lock for ";
        r4 = r4.append(r5);
        r5 = r6.soDirectory;
        r4 = r4.append(r5);
        r4 = r4.toString();
        android.util.Log.v(r3, r4);
        r0.close();
    L_0x0092:
        throw r2;
    L_0x0093:
        r2 = "fb-UnpackingSoSource";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "not releasing dso store lock for ";
        r3 = r3.append(r4);
        r4 = r6.soDirectory;
        r3 = r3.append(r4);
        r4 = " (syncer thread started)";
        r3 = r3.append(r4);
        r3 = r3.toString();
        android.util.Log.v(r2, r3);
        goto L_0x0056;
    L_0x00b4:
        r3 = "fb-UnpackingSoSource";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "not releasing dso store lock for ";
        r4 = r4.append(r5);
        r5 = r6.soDirectory;
        r4 = r4.append(r5);
        r5 = " (syncer thread started)";
        r4 = r4.append(r5);
        r4 = r4.toString();
        android.util.Log.v(r3, r4);
        goto L_0x0092;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.prepare(int):void");
    }
}
