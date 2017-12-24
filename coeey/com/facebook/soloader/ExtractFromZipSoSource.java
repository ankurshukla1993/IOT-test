package com.facebook.soloader;

import android.content.Context;
import com.facebook.soloader.UnpackingSoSource.Dso;
import com.facebook.soloader.UnpackingSoSource.DsoManifest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ExtractFromZipSoSource extends UnpackingSoSource {
    protected final File mZipFileName;
    protected final String mZipSearchPattern;

    protected class ZipUnpacker extends Unpacker {
        private ZipDso[] mDsos;
        private final ZipFile mZipFile;

        private final class ZipBackedInputDsoIterator extends InputDsoIterator {
            private int mCurrentDso;

            private ZipBackedInputDsoIterator() {
            }

            public boolean hasNext() {
                ZipUnpacker.this.ensureDsos();
                return this.mCurrentDso < ZipUnpacker.this.mDsos.length;
            }

            public InputDso next() throws IOException {
                ZipUnpacker.this.ensureDsos();
                ZipDso[] access$100 = ZipUnpacker.this.mDsos;
                int i = this.mCurrentDso;
                this.mCurrentDso = i + 1;
                ZipDso zipDso = access$100[i];
                InputStream is = ZipUnpacker.this.mZipFile.getInputStream(zipDso.backingEntry);
                try {
                    InputDso ret = new InputDso(zipDso, is);
                    is = null;
                    return ret;
                } finally {
                    if (is != null) {
                        is.close();
                    }
                }
            }
        }

        ZipUnpacker() throws IOException {
            this.mZipFile = new ZipFile(ExtractFromZipSoSource.this.mZipFileName);
        }

        final ZipDso[] ensureDsos() {
            if (this.mDsos == null) {
                int i;
                ZipDso zd;
                HashMap<String, ZipDso> providedLibraries = new HashMap();
                Pattern zipSearchPattern = Pattern.compile(ExtractFromZipSoSource.this.mZipSearchPattern);
                String[] supportedAbis = SysUtil.getSupportedAbis();
                Enumeration<? extends ZipEntry> entries = this.mZipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = (ZipEntry) entries.nextElement();
                    Matcher m = zipSearchPattern.matcher(entry.getName());
                    if (m.matches()) {
                        String libraryAbi = m.group(1);
                        String soName = m.group(2);
                        int abiScore = SysUtil.findAbiScore(supportedAbis, libraryAbi);
                        if (abiScore >= 0) {
                            ZipDso so = (ZipDso) providedLibraries.get(soName);
                            if (so == null || abiScore < so.abiScore) {
                                providedLibraries.put(soName, new ZipDso(soName, entry, abiScore));
                            }
                        }
                    }
                }
                ZipDso[] dsos = (ZipDso[]) providedLibraries.values().toArray(new ZipDso[providedLibraries.size()]);
                Arrays.sort(dsos);
                int nrFilteredDsos = 0;
                for (i = 0; i < dsos.length; i++) {
                    zd = dsos[i];
                    if (shouldExtract(zd.backingEntry, zd.name)) {
                        nrFilteredDsos++;
                    } else {
                        dsos[i] = null;
                    }
                }
                ZipDso[] filteredDsos = new ZipDso[nrFilteredDsos];
                int j = 0;
                for (ZipDso zd2 : dsos) {
                    if (zd2 != null) {
                        int j2 = j + 1;
                        filteredDsos[j] = zd2;
                        j = j2;
                    }
                }
                this.mDsos = filteredDsos;
            }
            return this.mDsos;
        }

        protected boolean shouldExtract(ZipEntry ze, String soName) {
            return true;
        }

        public void close() throws IOException {
            this.mZipFile.close();
        }

        protected final DsoManifest getDsoManifest() throws IOException {
            return new DsoManifest(ensureDsos());
        }

        protected final InputDsoIterator openDsoIterator() throws IOException {
            return new ZipBackedInputDsoIterator();
        }
    }

    private static final class ZipDso extends Dso implements Comparable {
        final int abiScore;
        final ZipEntry backingEntry;

        ZipDso(String name, ZipEntry backingEntry, int abiScore) {
            super(name, makePseudoHash(backingEntry));
            this.backingEntry = backingEntry;
            this.abiScore = abiScore;
        }

        private static String makePseudoHash(ZipEntry ze) {
            return String.format("pseudo-zip-hash-1-%s-%s-%s-%s", new Object[]{ze.getName(), Long.valueOf(ze.getSize()), Long.valueOf(ze.getCompressedSize()), Long.valueOf(ze.getCrc())});
        }

        public int compareTo(Object other) {
            return this.name.compareTo(((ZipDso) other).name);
        }
    }

    public ExtractFromZipSoSource(Context context, String name, File zipFileName, String zipSearchPattern) {
        super(context, name);
        this.mZipFileName = zipFileName;
        this.mZipSearchPattern = zipSearchPattern;
    }

    protected Unpacker makeUnpacker() throws IOException {
        return new ZipUnpacker();
    }
}
