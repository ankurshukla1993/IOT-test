package com.facebook.soloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

public final class MinElf {
    public static final int DT_NEEDED = 1;
    public static final int DT_NULL = 0;
    public static final int DT_STRTAB = 5;
    public static final int ELF_MAGIC = 1179403647;
    public static final int PN_XNUM = 65535;
    public static final int PT_DYNAMIC = 2;
    public static final int PT_LOAD = 1;

    private static class ElfError extends RuntimeException {
        ElfError(String why) {
            super(why);
        }
    }

    public static String[] extract_DT_NEEDED(File elfFile) throws IOException {
        FileInputStream is = new FileInputStream(elfFile);
        try {
            String[] extract_DT_NEEDED = extract_DT_NEEDED(is.getChannel());
            return extract_DT_NEEDED;
        } finally {
            is.close();
        }
    }

    public static String[] extract_DT_NEEDED(FileChannel fc) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        if (getu32(fc, bb, 0) != 1179403647) {
            throw new ElfError("file is not ELF");
        }
        long dyn;
        long ptr_DT_STRTAB;
        long off_DT_STRTAB;
        int i;
        boolean is32 = getu8(fc, bb, 4) == (short) 1;
        if (getu8(fc, bb, 5) == (short) 2) {
            bb.order(ByteOrder.BIG_ENDIAN);
        }
        long e_phoff = is32 ? getu32(fc, bb, 28) : get64(fc, bb, 32);
        long e_phnum = is32 ? (long) getu16(fc, bb, 44) : (long) getu16(fc, bb, 56);
        int e_phentsize = is32 ? getu16(fc, bb, 42) : getu16(fc, bb, 54);
        if (e_phnum == 65535) {
            long sh_info;
            long e_shoff = is32 ? getu32(fc, bb, 32) : get64(fc, bb, 40);
            if (is32) {
                sh_info = getu32(fc, bb, 28 + e_shoff);
            } else {
                sh_info = getu32(fc, bb, 44 + e_shoff);
            }
            e_phnum = sh_info;
        }
        long dynStart = 0;
        long phdr = e_phoff;
        for (long i2 = 0; i2 < e_phnum; i2++) {
            int nr_DT_NEEDED;
            long d_tag;
            long p_vaddr;
            String[] needed;
            if ((is32 ? getu32(fc, bb, 0 + phdr) : getu32(fc, bb, 0 + phdr)) == 2) {
                dynStart = is32 ? getu32(fc, bb, 4 + phdr) : get64(fc, bb, 8 + phdr);
                if (dynStart != 0) {
                    throw new ElfError("ELF file does not contain dynamic linking information");
                }
                nr_DT_NEEDED = 0;
                dyn = dynStart;
                ptr_DT_STRTAB = 0;
                do {
                    d_tag = is32 ? getu32(fc, bb, 0 + dyn) : get64(fc, bb, 0 + dyn);
                    if (d_tag != 1) {
                        if (nr_DT_NEEDED != Integer.MAX_VALUE) {
                            throw new ElfError("malformed DT_NEEDED section");
                        }
                        nr_DT_NEEDED++;
                    } else if (d_tag == 5) {
                        ptr_DT_STRTAB = is32 ? getu32(fc, bb, 4 + dyn) : get64(fc, bb, 8 + dyn);
                    }
                    dyn += is32 ? 8 : 16;
                } while (d_tag != 0);
                if (ptr_DT_STRTAB != 0) {
                    throw new ElfError("Dynamic section string-table not found");
                }
                off_DT_STRTAB = 0;
                phdr = e_phoff;
                for (i = 0; ((long) i) < e_phnum; i++) {
                    if ((is32 ? getu32(fc, bb, 0 + phdr) : getu32(fc, bb, 0 + phdr)) != 1) {
                        p_vaddr = is32 ? getu32(fc, bb, 8 + phdr) : get64(fc, bb, 16 + phdr);
                        long p_memsz = is32 ? getu32(fc, bb, 20 + phdr) : get64(fc, bb, 40 + phdr);
                        if (p_vaddr <= ptr_DT_STRTAB && ptr_DT_STRTAB < p_vaddr + p_memsz) {
                            off_DT_STRTAB = (is32 ? getu32(fc, bb, 4 + phdr) : get64(fc, bb, 8 + phdr)) + (ptr_DT_STRTAB - p_vaddr);
                            if (off_DT_STRTAB != 0) {
                                throw new ElfError("did not find file offset of DT_STRTAB table");
                            }
                            needed = new String[nr_DT_NEEDED];
                            nr_DT_NEEDED = 0;
                            dyn = dynStart;
                            do {
                                d_tag = is32 ? getu32(fc, bb, 0 + dyn) : get64(fc, bb, 0 + dyn);
                                if (d_tag == 1) {
                                    needed[nr_DT_NEEDED] = getSz(fc, bb, off_DT_STRTAB + (is32 ? getu32(fc, bb, 4 + dyn) : get64(fc, bb, 8 + dyn)));
                                    if (nr_DT_NEEDED != Integer.MAX_VALUE) {
                                        throw new ElfError("malformed DT_NEEDED section");
                                    }
                                    nr_DT_NEEDED++;
                                }
                                dyn += is32 ? 8 : 16;
                            } while (d_tag != 0);
                            if (nr_DT_NEEDED == needed.length) {
                                return needed;
                            }
                            throw new ElfError("malformed DT_NEEDED section");
                        }
                    }
                    phdr += (long) e_phentsize;
                }
                if (off_DT_STRTAB != 0) {
                    needed = new String[nr_DT_NEEDED];
                    nr_DT_NEEDED = 0;
                    dyn = dynStart;
                    do {
                        if (is32) {
                        }
                        if (d_tag == 1) {
                            if (is32) {
                            }
                            needed[nr_DT_NEEDED] = getSz(fc, bb, off_DT_STRTAB + (is32 ? getu32(fc, bb, 4 + dyn) : get64(fc, bb, 8 + dyn)));
                            if (nr_DT_NEEDED != Integer.MAX_VALUE) {
                                nr_DT_NEEDED++;
                            } else {
                                throw new ElfError("malformed DT_NEEDED section");
                            }
                        }
                        if (is32) {
                        }
                        dyn += is32 ? 8 : 16;
                    } while (d_tag != 0);
                    if (nr_DT_NEEDED == needed.length) {
                        return needed;
                    }
                    throw new ElfError("malformed DT_NEEDED section");
                }
                throw new ElfError("did not find file offset of DT_STRTAB table");
            }
            phdr += (long) e_phentsize;
        }
        if (dynStart != 0) {
            nr_DT_NEEDED = 0;
            dyn = dynStart;
            ptr_DT_STRTAB = 0;
            do {
                if (is32) {
                }
                if (d_tag != 1) {
                    if (d_tag == 5) {
                        if (is32) {
                        }
                    }
                } else if (nr_DT_NEEDED != Integer.MAX_VALUE) {
                    nr_DT_NEEDED++;
                } else {
                    throw new ElfError("malformed DT_NEEDED section");
                }
                if (is32) {
                }
                dyn += is32 ? 8 : 16;
            } while (d_tag != 0);
            if (ptr_DT_STRTAB != 0) {
                off_DT_STRTAB = 0;
                phdr = e_phoff;
                for (i = 0; ((long) i) < e_phnum; i++) {
                    if (is32) {
                    }
                    if ((is32 ? getu32(fc, bb, 0 + phdr) : getu32(fc, bb, 0 + phdr)) != 1) {
                        if (is32) {
                        }
                        if (is32) {
                        }
                        if (is32) {
                        }
                        off_DT_STRTAB = (is32 ? getu32(fc, bb, 4 + phdr) : get64(fc, bb, 8 + phdr)) + (ptr_DT_STRTAB - p_vaddr);
                        if (off_DT_STRTAB != 0) {
                            throw new ElfError("did not find file offset of DT_STRTAB table");
                        }
                        needed = new String[nr_DT_NEEDED];
                        nr_DT_NEEDED = 0;
                        dyn = dynStart;
                        do {
                            if (is32) {
                            }
                            if (d_tag == 1) {
                                if (is32) {
                                }
                                needed[nr_DT_NEEDED] = getSz(fc, bb, off_DT_STRTAB + (is32 ? getu32(fc, bb, 4 + dyn) : get64(fc, bb, 8 + dyn)));
                                if (nr_DT_NEEDED != Integer.MAX_VALUE) {
                                    throw new ElfError("malformed DT_NEEDED section");
                                }
                                nr_DT_NEEDED++;
                            }
                            if (is32) {
                            }
                            dyn += is32 ? 8 : 16;
                        } while (d_tag != 0);
                        if (nr_DT_NEEDED == needed.length) {
                            return needed;
                        }
                        throw new ElfError("malformed DT_NEEDED section");
                    }
                    phdr += (long) e_phentsize;
                }
                if (off_DT_STRTAB != 0) {
                    needed = new String[nr_DT_NEEDED];
                    nr_DT_NEEDED = 0;
                    dyn = dynStart;
                    do {
                        if (is32) {
                        }
                        if (d_tag == 1) {
                            if (is32) {
                            }
                            needed[nr_DT_NEEDED] = getSz(fc, bb, off_DT_STRTAB + (is32 ? getu32(fc, bb, 4 + dyn) : get64(fc, bb, 8 + dyn)));
                            if (nr_DT_NEEDED != Integer.MAX_VALUE) {
                                nr_DT_NEEDED++;
                            } else {
                                throw new ElfError("malformed DT_NEEDED section");
                            }
                        }
                        if (is32) {
                        }
                        dyn += is32 ? 8 : 16;
                    } while (d_tag != 0);
                    if (nr_DT_NEEDED == needed.length) {
                        return needed;
                    }
                    throw new ElfError("malformed DT_NEEDED section");
                }
                throw new ElfError("did not find file offset of DT_STRTAB table");
            }
            throw new ElfError("Dynamic section string-table not found");
        }
        throw new ElfError("ELF file does not contain dynamic linking information");
    }

    private static String getSz(FileChannel fc, ByteBuffer bb, long offset) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long offset2 = offset + 1;
            short b = getu8(fc, bb, offset);
            if (b == (short) 0) {
                return sb.toString();
            }
            sb.append((char) b);
            offset = offset2;
        }
    }

    private static void read(FileChannel fc, ByteBuffer bb, int sz, long offset) throws IOException {
        bb.position(0);
        bb.limit(sz);
        while (bb.remaining() > 0) {
            int numBytesRead = fc.read(bb, offset);
            if (numBytesRead == -1) {
                break;
            }
            offset += (long) numBytesRead;
        }
        if (bb.remaining() > 0) {
            throw new ElfError("ELF file truncated");
        }
        bb.position(0);
    }

    private static long get64(FileChannel fc, ByteBuffer bb, long offset) throws IOException {
        read(fc, bb, 8, offset);
        return bb.getLong();
    }

    private static long getu32(FileChannel fc, ByteBuffer bb, long offset) throws IOException {
        read(fc, bb, 4, offset);
        return ((long) bb.getInt()) & 4294967295L;
    }

    private static int getu16(FileChannel fc, ByteBuffer bb, long offset) throws IOException {
        read(fc, bb, 2, offset);
        return bb.getShort() & 65535;
    }

    private static short getu8(FileChannel fc, ByteBuffer bb, long offset) throws IOException {
        read(fc, bb, 1, offset);
        return (short) (bb.get() & 255);
    }
}
