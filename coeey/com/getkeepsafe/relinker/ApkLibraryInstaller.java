package com.getkeepsafe.relinker;

import com.getkeepsafe.relinker.ReLinker.LibraryInstaller;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ApkLibraryInstaller implements LibraryInstaller {
    private static final int COPY_BUFFER_SIZE = 4096;
    private static final int MAX_TRIES = 5;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void installLibrary(android.content.Context r24, java.lang.String[] r25, java.lang.String r26, java.io.File r27, com.getkeepsafe.relinker.ReLinkerInstance r28) {
        /*
        r23 = this;
        r15 = 0;
        r5 = r24.getApplicationInfo();	 Catch:{ all -> 0x00a4 }
        r13 = 0;
        r14 = r13;
    L_0x0007:
        r13 = r14 + 1;
        r19 = 5;
        r0 = r19;
        if (r14 >= r0) goto L_0x0021;
    L_0x000f:
        r18 = new java.util.zip.ZipFile;	 Catch:{ IOException -> 0x0032 }
        r19 = new java.io.File;	 Catch:{ IOException -> 0x0032 }
        r0 = r5.sourceDir;	 Catch:{ IOException -> 0x0032 }
        r20 = r0;
        r19.<init>(r20);	 Catch:{ IOException -> 0x0032 }
        r20 = 1;
        r18.<init>(r19, r20);	 Catch:{ IOException -> 0x0032 }
        r15 = r18;
    L_0x0021:
        if (r15 != 0) goto L_0x0035;
    L_0x0023:
        r19 = "FATAL! Couldn't find application APK!";
        r0 = r28;
        r1 = r19;
        r0.log(r1);	 Catch:{ all -> 0x00a4 }
        if (r15 == 0) goto L_0x0031;
    L_0x002e:
        r15.close();	 Catch:{ IOException -> 0x0183 }
    L_0x0031:
        return;
    L_0x0032:
        r19 = move-exception;
        r14 = r13;
        goto L_0x0007;
    L_0x0035:
        r13 = 0;
        r14 = r13;
    L_0x0037:
        r13 = r14 + 1;
        r19 = 5;
        r0 = r19;
        if (r14 >= r0) goto L_0x0170;
    L_0x003f:
        r11 = 0;
        r12 = 0;
        r0 = r25;
        r0 = r0.length;	 Catch:{ all -> 0x00a4 }
        r20 = r0;
        r19 = 0;
    L_0x0048:
        r0 = r19;
        r1 = r20;
        if (r0 >= r1) goto L_0x007f;
    L_0x004e:
        r4 = r25[r19];	 Catch:{ all -> 0x00a4 }
        r21 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a4 }
        r21.<init>();	 Catch:{ all -> 0x00a4 }
        r22 = "lib";
        r21 = r21.append(r22);	 Catch:{ all -> 0x00a4 }
        r22 = java.io.File.separatorChar;	 Catch:{ all -> 0x00a4 }
        r21 = r21.append(r22);	 Catch:{ all -> 0x00a4 }
        r0 = r21;
        r21 = r0.append(r4);	 Catch:{ all -> 0x00a4 }
        r22 = java.io.File.separatorChar;	 Catch:{ all -> 0x00a4 }
        r21 = r21.append(r22);	 Catch:{ all -> 0x00a4 }
        r0 = r21;
        r1 = r26;
        r21 = r0.append(r1);	 Catch:{ all -> 0x00a4 }
        r11 = r21.toString();	 Catch:{ all -> 0x00a4 }
        r12 = r15.getEntry(r11);	 Catch:{ all -> 0x00a4 }
        if (r12 == 0) goto L_0x00ab;
    L_0x007f:
        if (r11 == 0) goto L_0x0098;
    L_0x0081:
        r19 = "Looking for %s in APK...";
        r20 = 1;
        r0 = r20;
        r0 = new java.lang.Object[r0];	 Catch:{ all -> 0x00a4 }
        r20 = r0;
        r21 = 0;
        r20[r21] = r11;	 Catch:{ all -> 0x00a4 }
        r0 = r28;
        r1 = r19;
        r2 = r20;
        r0.log(r1, r2);	 Catch:{ all -> 0x00a4 }
    L_0x0098:
        if (r12 != 0) goto L_0x00b8;
    L_0x009a:
        if (r11 == 0) goto L_0x00ae;
    L_0x009c:
        r19 = new com.getkeepsafe.relinker.MissingLibraryException;	 Catch:{ all -> 0x00a4 }
        r0 = r19;
        r0.<init>(r11);	 Catch:{ all -> 0x00a4 }
        throw r19;	 Catch:{ all -> 0x00a4 }
    L_0x00a4:
        r19 = move-exception;
        if (r15 == 0) goto L_0x00aa;
    L_0x00a7:
        r15.close();	 Catch:{ IOException -> 0x0186 }
    L_0x00aa:
        throw r19;
    L_0x00ab:
        r19 = r19 + 1;
        goto L_0x0048;
    L_0x00ae:
        r19 = new com.getkeepsafe.relinker.MissingLibraryException;	 Catch:{ all -> 0x00a4 }
        r0 = r19;
        r1 = r26;
        r0.<init>(r1);	 Catch:{ all -> 0x00a4 }
        throw r19;	 Catch:{ all -> 0x00a4 }
    L_0x00b8:
        r19 = "Found %s! Extracting...";
        r20 = 1;
        r0 = r20;
        r0 = new java.lang.Object[r0];	 Catch:{ all -> 0x00a4 }
        r20 = r0;
        r21 = 0;
        r20[r21] = r11;	 Catch:{ all -> 0x00a4 }
        r0 = r28;
        r1 = r19;
        r2 = r20;
        r0.log(r1, r2);	 Catch:{ all -> 0x00a4 }
        r19 = r27.exists();	 Catch:{ IOException -> 0x00de }
        if (r19 != 0) goto L_0x00e2;
    L_0x00d5:
        r19 = r27.createNewFile();	 Catch:{ IOException -> 0x00de }
        if (r19 != 0) goto L_0x00e2;
    L_0x00db:
        r14 = r13;
        goto L_0x0037;
    L_0x00de:
        r9 = move-exception;
        r14 = r13;
        goto L_0x0037;
    L_0x00e2:
        r10 = 0;
        r7 = 0;
        r10 = r15.getInputStream(r12);	 Catch:{ FileNotFoundException -> 0x0148, IOException -> 0x0156, all -> 0x0164 }
        r8 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0148, IOException -> 0x0156, all -> 0x0164 }
        r0 = r27;
        r8.<init>(r0);	 Catch:{ FileNotFoundException -> 0x0148, IOException -> 0x0156, all -> 0x0164 }
        r0 = r23;
        r16 = r0.copy(r10, r8);	 Catch:{ FileNotFoundException -> 0x018f, IOException -> 0x018c, all -> 0x0189 }
        r19 = r8.getFD();	 Catch:{ FileNotFoundException -> 0x018f, IOException -> 0x018c, all -> 0x0189 }
        r19.sync();	 Catch:{ FileNotFoundException -> 0x018f, IOException -> 0x018c, all -> 0x0189 }
        r20 = r27.length();	 Catch:{ FileNotFoundException -> 0x018f, IOException -> 0x018c, all -> 0x0189 }
        r19 = (r16 > r20 ? 1 : (r16 == r20 ? 0 : -1));
        if (r19 == 0) goto L_0x0111;
    L_0x0104:
        r0 = r23;
        r0.closeSilently(r10);	 Catch:{ all -> 0x00a4 }
        r0 = r23;
        r0.closeSilently(r8);	 Catch:{ all -> 0x00a4 }
        r14 = r13;
        goto L_0x0037;
    L_0x0111:
        r0 = r23;
        r0.closeSilently(r10);	 Catch:{ all -> 0x00a4 }
        r0 = r23;
        r0.closeSilently(r8);	 Catch:{ all -> 0x00a4 }
        r19 = 1;
        r20 = 0;
        r0 = r27;
        r1 = r19;
        r2 = r20;
        r0.setReadable(r1, r2);	 Catch:{ all -> 0x00a4 }
        r19 = 1;
        r20 = 0;
        r0 = r27;
        r1 = r19;
        r2 = r20;
        r0.setExecutable(r1, r2);	 Catch:{ all -> 0x00a4 }
        r19 = 1;
        r0 = r27;
        r1 = r19;
        r0.setWritable(r1);	 Catch:{ all -> 0x00a4 }
        if (r15 == 0) goto L_0x0031;
    L_0x0140:
        r15.close();	 Catch:{ IOException -> 0x0145 }
        goto L_0x0031;
    L_0x0145:
        r19 = move-exception;
        goto L_0x0031;
    L_0x0148:
        r6 = move-exception;
    L_0x0149:
        r0 = r23;
        r0.closeSilently(r10);	 Catch:{ all -> 0x00a4 }
        r0 = r23;
        r0.closeSilently(r7);	 Catch:{ all -> 0x00a4 }
        r14 = r13;
        goto L_0x0037;
    L_0x0156:
        r6 = move-exception;
    L_0x0157:
        r0 = r23;
        r0.closeSilently(r10);	 Catch:{ all -> 0x00a4 }
        r0 = r23;
        r0.closeSilently(r7);	 Catch:{ all -> 0x00a4 }
        r14 = r13;
        goto L_0x0037;
    L_0x0164:
        r19 = move-exception;
    L_0x0165:
        r0 = r23;
        r0.closeSilently(r10);	 Catch:{ all -> 0x00a4 }
        r0 = r23;
        r0.closeSilently(r7);	 Catch:{ all -> 0x00a4 }
        throw r19;	 Catch:{ all -> 0x00a4 }
    L_0x0170:
        r19 = "FATAL! Couldn't extract the library from the APK!";
        r0 = r28;
        r1 = r19;
        r0.log(r1);	 Catch:{ all -> 0x00a4 }
        if (r15 == 0) goto L_0x0031;
    L_0x017b:
        r15.close();	 Catch:{ IOException -> 0x0180 }
        goto L_0x0031;
    L_0x0180:
        r19 = move-exception;
        goto L_0x0031;
    L_0x0183:
        r19 = move-exception;
        goto L_0x0031;
    L_0x0186:
        r20 = move-exception;
        goto L_0x00aa;
    L_0x0189:
        r19 = move-exception;
        r7 = r8;
        goto L_0x0165;
    L_0x018c:
        r6 = move-exception;
        r7 = r8;
        goto L_0x0157;
    L_0x018f:
        r6 = move-exception;
        r7 = r8;
        goto L_0x0149;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getkeepsafe.relinker.ApkLibraryInstaller.installLibrary(android.content.Context, java.lang.String[], java.lang.String, java.io.File, com.getkeepsafe.relinker.ReLinkerInstance):void");
    }

    private long copy(InputStream in, OutputStream out) throws IOException {
        long copied = 0;
        byte[] buf = new byte[4096];
        while (true) {
            int read = in.read(buf);
            if (read == -1) {
                out.flush();
                return copied;
            }
            out.write(buf, 0, read);
            copied += (long) read;
        }
    }

    private void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
}
