package com.facebook.soloader;

import android.content.Context;
import com.facebook.soloader.UnpackingSoSource.Dso;
import com.facebook.soloader.UnpackingSoSource.DsoManifest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public final class ExoSoSource extends UnpackingSoSource {

    private final class ExoUnpacker extends Unpacker {
        private final FileDso[] mDsos;

        private final class FileBackedInputDsoIterator extends InputDsoIterator {
            private int mCurrentDso;

            private FileBackedInputDsoIterator() {
            }

            public boolean hasNext() {
                return this.mCurrentDso < ExoUnpacker.this.mDsos.length;
            }

            public InputDso next() throws IOException {
                FileDso[] access$100 = ExoUnpacker.this.mDsos;
                int i = this.mCurrentDso;
                this.mCurrentDso = i + 1;
                FileDso fileDso = access$100[i];
                FileInputStream dsoFile = new FileInputStream(fileDso.backingFile);
                try {
                    InputDso ret = new InputDso(fileDso, dsoFile);
                    dsoFile = null;
                    return ret;
                } finally {
                    if (dsoFile != null) {
                        dsoFile.close();
                    }
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        ExoUnpacker() throws java.io.IOException {
            /*
            r28 = this;
            r0 = r29;
            r1 = r28;
            com.facebook.soloader.ExoSoSource.this = r0;
            r28.<init>();
            r0 = r29;
            r8 = r0.mContext;
            r9 = new java.io.File;
            r22 = new java.lang.StringBuilder;
            r22.<init>();
            r23 = "/data/local/tmp/exopackage/";
            r22 = r22.append(r23);
            r23 = r8.getPackageName();
            r22 = r22.append(r23);
            r23 = "/native-libs/";
            r22 = r22.append(r23);
            r22 = r22.toString();
            r0 = r22;
            r9.<init>(r0);
            r18 = new java.util.ArrayList;
            r18.<init>();
            r5 = com.facebook.soloader.SysUtil.getSupportedAbis();
            r14 = r5.length;
            r13 = 0;
        L_0x003c:
            if (r13 >= r14) goto L_0x0194;
        L_0x003e:
            r3 = r5[r13];
            r4 = new java.io.File;
            r4.<init>(r9, r3);
            r22 = r4.isDirectory();
            if (r22 != 0) goto L_0x004e;
        L_0x004b:
            r13 = r13 + 1;
            goto L_0x003c;
        L_0x004e:
            r16 = new java.io.File;
            r22 = "metadata.txt";
            r0 = r16;
            r1 = r22;
            r0.<init>(r4, r1);
            r22 = r16.isFile();
            if (r22 == 0) goto L_0x004b;
        L_0x005f:
            r11 = new java.io.FileReader;
            r0 = r16;
            r11.<init>(r0);
            r24 = 0;
            r7 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00c1, all -> 0x0168 }
            r7.<init>(r11);	 Catch:{ Throwable -> 0x00c1, all -> 0x0168 }
            r23 = 0;
        L_0x006f:
            r15 = r7.readLine();	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            if (r15 == 0) goto L_0x0145;
        L_0x0075:
            r22 = r15.length();	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            if (r22 == 0) goto L_0x006f;
        L_0x007b:
            r22 = 32;
            r0 = r22;
            r19 = r15.indexOf(r0);	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r22 = -1;
            r0 = r19;
            r1 = r22;
            if (r0 != r1) goto L_0x00d2;
        L_0x008b:
            r22 = new java.lang.RuntimeException;	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r25 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r25.<init>();	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r26 = "illegal line in exopackage metadata: [";
            r25 = r25.append(r26);	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r0 = r25;
            r25 = r0.append(r15);	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r26 = "]";
            r25 = r25.append(r26);	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r25 = r25.toString();	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r0 = r22;
            r1 = r25;
            r0.<init>(r1);	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            throw r22;	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
        L_0x00b0:
            r22 = move-exception;
            throw r22;	 Catch:{ all -> 0x00b2 }
        L_0x00b2:
            r23 = move-exception;
            r27 = r23;
            r23 = r22;
            r22 = r27;
        L_0x00b9:
            if (r7 == 0) goto L_0x00c0;
        L_0x00bb:
            if (r23 == 0) goto L_0x017b;
        L_0x00bd:
            r7.close();	 Catch:{ Throwable -> 0x0171, all -> 0x0168 }
        L_0x00c0:
            throw r22;	 Catch:{ Throwable -> 0x00c1, all -> 0x0168 }
        L_0x00c1:
            r22 = move-exception;
            throw r22;	 Catch:{ all -> 0x00c3 }
        L_0x00c3:
            r23 = move-exception;
            r27 = r23;
            r23 = r22;
            r22 = r27;
        L_0x00ca:
            if (r11 == 0) goto L_0x00d1;
        L_0x00cc:
            if (r23 == 0) goto L_0x018f;
        L_0x00ce:
            r11.close();	 Catch:{ Throwable -> 0x0185 }
        L_0x00d1:
            throw r22;
        L_0x00d2:
            r22 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r22.<init>();	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r25 = 0;
            r0 = r25;
            r1 = r19;
            r25 = r15.substring(r0, r1);	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r0 = r22;
            r1 = r25;
            r22 = r0.append(r1);	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r25 = ".so";
            r0 = r22;
            r1 = r25;
            r22 = r0.append(r1);	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r20 = r22.toString();	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r17 = r18.size();	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r10 = 0;
            r12 = 0;
        L_0x00fd:
            r0 = r17;
            if (r12 >= r0) goto L_0x011a;
        L_0x0101:
            r0 = r18;
            r22 = r0.get(r12);	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r22 = (com.facebook.soloader.ExoSoSource.FileDso) r22;	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r0 = r22;
            r0 = r0.name;	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r22 = r0;
            r0 = r22;
            r1 = r20;
            r22 = r0.equals(r1);	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            if (r22 == 0) goto L_0x0142;
        L_0x0119:
            r10 = 1;
        L_0x011a:
            if (r10 != 0) goto L_0x006f;
        L_0x011c:
            r22 = r19 + 1;
            r0 = r22;
            r6 = r15.substring(r0);	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r22 = new com.facebook.soloader.ExoSoSource$FileDso;	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r25 = new java.io.File;	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r0 = r25;
            r0.<init>(r4, r6);	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r0 = r22;
            r1 = r20;
            r2 = r25;
            r0.<init>(r1, r6, r2);	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            r0 = r18;
            r1 = r22;
            r0.add(r1);	 Catch:{ Throwable -> 0x00b0, all -> 0x013f }
            goto L_0x006f;
        L_0x013f:
            r22 = move-exception;
            goto L_0x00b9;
        L_0x0142:
            r12 = r12 + 1;
            goto L_0x00fd;
        L_0x0145:
            if (r7 == 0) goto L_0x014c;
        L_0x0147:
            if (r23 == 0) goto L_0x016d;
        L_0x0149:
            r7.close();	 Catch:{ Throwable -> 0x015f, all -> 0x0168 }
        L_0x014c:
            if (r11 == 0) goto L_0x004b;
        L_0x014e:
            if (r24 == 0) goto L_0x0180;
        L_0x0150:
            r11.close();	 Catch:{ Throwable -> 0x0155 }
            goto L_0x004b;
        L_0x0155:
            r21 = move-exception;
            r0 = r24;
            r1 = r21;
            r0.addSuppressed(r1);
            goto L_0x004b;
        L_0x015f:
            r21 = move-exception;
            r0 = r23;
            r1 = r21;
            r0.addSuppressed(r1);	 Catch:{ Throwable -> 0x00c1, all -> 0x0168 }
            goto L_0x014c;
        L_0x0168:
            r22 = move-exception;
            r23 = r24;
            goto L_0x00ca;
        L_0x016d:
            r7.close();	 Catch:{ Throwable -> 0x00c1, all -> 0x0168 }
            goto L_0x014c;
        L_0x0171:
            r21 = move-exception;
            r0 = r23;
            r1 = r21;
            r0.addSuppressed(r1);	 Catch:{ Throwable -> 0x00c1, all -> 0x0168 }
            goto L_0x00c0;
        L_0x017b:
            r7.close();	 Catch:{ Throwable -> 0x00c1, all -> 0x0168 }
            goto L_0x00c0;
        L_0x0180:
            r11.close();
            goto L_0x004b;
        L_0x0185:
            r21 = move-exception;
            r0 = r23;
            r1 = r21;
            r0.addSuppressed(r1);
            goto L_0x00d1;
        L_0x018f:
            r11.close();
            goto L_0x00d1;
        L_0x0194:
            r22 = r18.size();
            r0 = r22;
            r0 = new com.facebook.soloader.ExoSoSource.FileDso[r0];
            r22 = r0;
            r0 = r18;
            r1 = r22;
            r22 = r0.toArray(r1);
            r22 = (com.facebook.soloader.ExoSoSource.FileDso[]) r22;
            r0 = r22;
            r1 = r28;
            r1.mDsos = r0;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.ExoSoSource.ExoUnpacker.<init>(com.facebook.soloader.ExoSoSource):void");
        }

        protected DsoManifest getDsoManifest() throws IOException {
            return new DsoManifest(this.mDsos);
        }

        protected InputDsoIterator openDsoIterator() throws IOException {
            return new FileBackedInputDsoIterator();
        }
    }

    private static final class FileDso extends Dso {
        final File backingFile;

        FileDso(String name, String hash, File backingFile) {
            super(name, hash);
            this.backingFile = backingFile;
        }
    }

    public ExoSoSource(Context context, String name) {
        super(context, name);
    }

    protected Unpacker makeUnpacker() throws IOException {
        return new ExoUnpacker();
    }
}
