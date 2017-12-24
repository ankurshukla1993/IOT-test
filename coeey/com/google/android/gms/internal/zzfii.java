package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class zzfii extends zzfhe<zzfii> implements Cloneable {
    private String tag;
    private int zzakb;
    private boolean zzmnq;
    private zzfik zzoan;
    public long zzpkg;
    public long zzpkh;
    private long zzpki;
    private int zzpkj;
    private zzfij[] zzpkk;
    private byte[] zzpkl;
    private zzfig zzpkm;
    public byte[] zzpkn;
    private String zzpko;
    private String zzpkp;
    private zzfif zzpkq;
    private String zzpkr;
    public long zzpks;
    private zzfih zzpkt;
    public byte[] zzpku;
    private String zzpkv;
    private int zzpkw;
    private int[] zzpkx;
    private long zzpky;

    public zzfii() {
        this.zzpkg = 0;
        this.zzpkh = 0;
        this.zzpki = 0;
        this.tag = "";
        this.zzpkj = 0;
        this.zzakb = 0;
        this.zzmnq = false;
        this.zzpkk = zzfij.zzcxz();
        this.zzpkl = zzfhn.zzphr;
        this.zzpkm = null;
        this.zzpkn = zzfhn.zzphr;
        this.zzpko = "";
        this.zzpkp = "";
        this.zzpkq = null;
        this.zzpkr = "";
        this.zzpks = 180000;
        this.zzpkt = null;
        this.zzpku = zzfhn.zzphr;
        this.zzpkv = "";
        this.zzpkw = 0;
        this.zzpkx = zzfhn.zzphl;
        this.zzpky = 0;
        this.zzoan = null;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.zzfii zzar(com.google.android.gms.internal.zzfhb r8) throws java.io.IOException {
        /*
        r7 = this;
        r1 = 0;
    L_0x0001:
        r0 = r8.zzcts();
        switch(r0) {
            case 0: goto L_0x000e;
            case 8: goto L_0x000f;
            case 18: goto L_0x0016;
            case 26: goto L_0x001d;
            case 34: goto L_0x005c;
            case 50: goto L_0x0063;
            case 58: goto L_0x006a;
            case 66: goto L_0x007b;
            case 74: goto L_0x0082;
            case 80: goto L_0x0094;
            case 88: goto L_0x009c;
            case 96: goto L_0x00a4;
            case 106: goto L_0x00ac;
            case 114: goto L_0x00b4;
            case 120: goto L_0x00bc;
            case 130: goto L_0x00c4;
            case 136: goto L_0x00d6;
            case 146: goto L_0x00de;
            case 152: goto L_0x00e6;
            case 160: goto L_0x0119;
            case 162: goto L_0x014d;
            case 168: goto L_0x018f;
            case 176: goto L_0x0197;
            case 186: goto L_0x019f;
            case 194: goto L_0x01b1;
            default: goto L_0x0008;
        };
    L_0x0008:
        r0 = super.zza(r8, r0);
        if (r0 != 0) goto L_0x0001;
    L_0x000e:
        return r7;
    L_0x000f:
        r2 = r8.zzctu();
        r7.zzpkg = r2;
        goto L_0x0001;
    L_0x0016:
        r0 = r8.readString();
        r7.tag = r0;
        goto L_0x0001;
    L_0x001d:
        r0 = 26;
        r2 = com.google.android.gms.internal.zzfhn.zzb(r8, r0);
        r0 = r7.zzpkk;
        if (r0 != 0) goto L_0x0049;
    L_0x0027:
        r0 = r1;
    L_0x0028:
        r2 = r2 + r0;
        r2 = new com.google.android.gms.internal.zzfij[r2];
        if (r0 == 0) goto L_0x0032;
    L_0x002d:
        r3 = r7.zzpkk;
        java.lang.System.arraycopy(r3, r1, r2, r1, r0);
    L_0x0032:
        r3 = r2.length;
        r3 = r3 + -1;
        if (r0 >= r3) goto L_0x004d;
    L_0x0037:
        r3 = new com.google.android.gms.internal.zzfij;
        r3.<init>();
        r2[r0] = r3;
        r3 = r2[r0];
        r8.zza(r3);
        r8.zzcts();
        r0 = r0 + 1;
        goto L_0x0032;
    L_0x0049:
        r0 = r7.zzpkk;
        r0 = r0.length;
        goto L_0x0028;
    L_0x004d:
        r3 = new com.google.android.gms.internal.zzfij;
        r3.<init>();
        r2[r0] = r3;
        r0 = r2[r0];
        r8.zza(r0);
        r7.zzpkk = r2;
        goto L_0x0001;
    L_0x005c:
        r0 = r8.readBytes();
        r7.zzpkl = r0;
        goto L_0x0001;
    L_0x0063:
        r0 = r8.readBytes();
        r7.zzpkn = r0;
        goto L_0x0001;
    L_0x006a:
        r0 = r7.zzpkq;
        if (r0 != 0) goto L_0x0075;
    L_0x006e:
        r0 = new com.google.android.gms.internal.zzfif;
        r0.<init>();
        r7.zzpkq = r0;
    L_0x0075:
        r0 = r7.zzpkq;
        r8.zza(r0);
        goto L_0x0001;
    L_0x007b:
        r0 = r8.readString();
        r7.zzpko = r0;
        goto L_0x0001;
    L_0x0082:
        r0 = r7.zzpkm;
        if (r0 != 0) goto L_0x008d;
    L_0x0086:
        r0 = new com.google.android.gms.internal.zzfig;
        r0.<init>();
        r7.zzpkm = r0;
    L_0x008d:
        r0 = r7.zzpkm;
        r8.zza(r0);
        goto L_0x0001;
    L_0x0094:
        r0 = r8.zzcty();
        r7.zzmnq = r0;
        goto L_0x0001;
    L_0x009c:
        r0 = r8.zzctv();
        r7.zzpkj = r0;
        goto L_0x0001;
    L_0x00a4:
        r0 = r8.zzctv();
        r7.zzakb = r0;
        goto L_0x0001;
    L_0x00ac:
        r0 = r8.readString();
        r7.zzpkp = r0;
        goto L_0x0001;
    L_0x00b4:
        r0 = r8.readString();
        r7.zzpkr = r0;
        goto L_0x0001;
    L_0x00bc:
        r2 = r8.zzcug();
        r7.zzpks = r2;
        goto L_0x0001;
    L_0x00c4:
        r0 = r7.zzpkt;
        if (r0 != 0) goto L_0x00cf;
    L_0x00c8:
        r0 = new com.google.android.gms.internal.zzfih;
        r0.<init>();
        r7.zzpkt = r0;
    L_0x00cf:
        r0 = r7.zzpkt;
        r8.zza(r0);
        goto L_0x0001;
    L_0x00d6:
        r2 = r8.zzctu();
        r7.zzpkh = r2;
        goto L_0x0001;
    L_0x00de:
        r0 = r8.readBytes();
        r7.zzpku = r0;
        goto L_0x0001;
    L_0x00e6:
        r2 = r8.getPosition();
        r3 = r8.zzctv();	 Catch:{ IllegalArgumentException -> 0x010c }
        switch(r3) {
            case 0: goto L_0x0115;
            case 1: goto L_0x0115;
            case 2: goto L_0x0115;
            default: goto L_0x00f1;
        };	 Catch:{ IllegalArgumentException -> 0x010c }
    L_0x00f1:
        r4 = new java.lang.IllegalArgumentException;	 Catch:{ IllegalArgumentException -> 0x010c }
        r5 = 45;
        r6 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x010c }
        r6.<init>(r5);	 Catch:{ IllegalArgumentException -> 0x010c }
        r3 = r6.append(r3);	 Catch:{ IllegalArgumentException -> 0x010c }
        r5 = " is not a valid enum InternalEvent";
        r3 = r3.append(r5);	 Catch:{ IllegalArgumentException -> 0x010c }
        r3 = r3.toString();	 Catch:{ IllegalArgumentException -> 0x010c }
        r4.<init>(r3);	 Catch:{ IllegalArgumentException -> 0x010c }
        throw r4;	 Catch:{ IllegalArgumentException -> 0x010c }
    L_0x010c:
        r3 = move-exception;
        r8.zzlv(r2);
        r7.zza(r8, r0);
        goto L_0x0001;
    L_0x0115:
        r7.zzpkw = r3;	 Catch:{ IllegalArgumentException -> 0x010c }
        goto L_0x0001;
    L_0x0119:
        r0 = 160; // 0xa0 float:2.24E-43 double:7.9E-322;
        r2 = com.google.android.gms.internal.zzfhn.zzb(r8, r0);
        r0 = r7.zzpkx;
        if (r0 != 0) goto L_0x013f;
    L_0x0123:
        r0 = r1;
    L_0x0124:
        r2 = r2 + r0;
        r2 = new int[r2];
        if (r0 == 0) goto L_0x012e;
    L_0x0129:
        r3 = r7.zzpkx;
        java.lang.System.arraycopy(r3, r1, r2, r1, r0);
    L_0x012e:
        r3 = r2.length;
        r3 = r3 + -1;
        if (r0 >= r3) goto L_0x0143;
    L_0x0133:
        r3 = r8.zzctv();
        r2[r0] = r3;
        r8.zzcts();
        r0 = r0 + 1;
        goto L_0x012e;
    L_0x013f:
        r0 = r7.zzpkx;
        r0 = r0.length;
        goto L_0x0124;
    L_0x0143:
        r3 = r8.zzctv();
        r2[r0] = r3;
        r7.zzpkx = r2;
        goto L_0x0001;
    L_0x014d:
        r0 = r8.zzcuh();
        r3 = r8.zzki(r0);
        r2 = r8.getPosition();
        r0 = r1;
    L_0x015a:
        r4 = r8.zzcuj();
        if (r4 <= 0) goto L_0x0166;
    L_0x0160:
        r8.zzctv();
        r0 = r0 + 1;
        goto L_0x015a;
    L_0x0166:
        r8.zzlv(r2);
        r2 = r7.zzpkx;
        if (r2 != 0) goto L_0x0184;
    L_0x016d:
        r2 = r1;
    L_0x016e:
        r0 = r0 + r2;
        r0 = new int[r0];
        if (r2 == 0) goto L_0x0178;
    L_0x0173:
        r4 = r7.zzpkx;
        java.lang.System.arraycopy(r4, r1, r0, r1, r2);
    L_0x0178:
        r4 = r0.length;
        if (r2 >= r4) goto L_0x0188;
    L_0x017b:
        r4 = r8.zzctv();
        r0[r2] = r4;
        r2 = r2 + 1;
        goto L_0x0178;
    L_0x0184:
        r2 = r7.zzpkx;
        r2 = r2.length;
        goto L_0x016e;
    L_0x0188:
        r7.zzpkx = r0;
        r8.zzkj(r3);
        goto L_0x0001;
    L_0x018f:
        r2 = r8.zzctu();
        r7.zzpki = r2;
        goto L_0x0001;
    L_0x0197:
        r2 = r8.zzctu();
        r7.zzpky = r2;
        goto L_0x0001;
    L_0x019f:
        r0 = r7.zzoan;
        if (r0 != 0) goto L_0x01aa;
    L_0x01a3:
        r0 = new com.google.android.gms.internal.zzfik;
        r0.<init>();
        r7.zzoan = r0;
    L_0x01aa:
        r0 = r7.zzoan;
        r8.zza(r0);
        goto L_0x0001;
    L_0x01b1:
        r0 = r8.readString();
        r7.zzpkv = r0;
        goto L_0x0001;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfii.zzar(com.google.android.gms.internal.zzfhb):com.google.android.gms.internal.zzfii");
    }

    private final zzfii zzcxy() {
        try {
            zzfii com_google_android_gms_internal_zzfii = (zzfii) super.zzcxe();
            if (this.zzpkk != null && this.zzpkk.length > 0) {
                com_google_android_gms_internal_zzfii.zzpkk = new zzfij[this.zzpkk.length];
                for (int i = 0; i < this.zzpkk.length; i++) {
                    if (this.zzpkk[i] != null) {
                        com_google_android_gms_internal_zzfii.zzpkk[i] = (zzfij) this.zzpkk[i].clone();
                    }
                }
            }
            if (this.zzpkm != null) {
                com_google_android_gms_internal_zzfii.zzpkm = (zzfig) this.zzpkm.clone();
            }
            if (this.zzpkq != null) {
                com_google_android_gms_internal_zzfii.zzpkq = (zzfif) this.zzpkq.clone();
            }
            if (this.zzpkt != null) {
                com_google_android_gms_internal_zzfii.zzpkt = (zzfih) this.zzpkt.clone();
            }
            if (this.zzpkx != null && this.zzpkx.length > 0) {
                com_google_android_gms_internal_zzfii.zzpkx = (int[]) this.zzpkx.clone();
            }
            if (this.zzoan != null) {
                com_google_android_gms_internal_zzfii.zzoan = (zzfik) this.zzoan.clone();
            }
            return com_google_android_gms_internal_zzfii;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzcxy();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfii)) {
            return false;
        }
        zzfii com_google_android_gms_internal_zzfii = (zzfii) obj;
        if (this.zzpkg != com_google_android_gms_internal_zzfii.zzpkg) {
            return false;
        }
        if (this.zzpkh != com_google_android_gms_internal_zzfii.zzpkh) {
            return false;
        }
        if (this.zzpki != com_google_android_gms_internal_zzfii.zzpki) {
            return false;
        }
        if (this.tag == null) {
            if (com_google_android_gms_internal_zzfii.tag != null) {
                return false;
            }
        } else if (!this.tag.equals(com_google_android_gms_internal_zzfii.tag)) {
            return false;
        }
        if (this.zzpkj != com_google_android_gms_internal_zzfii.zzpkj) {
            return false;
        }
        if (this.zzakb != com_google_android_gms_internal_zzfii.zzakb) {
            return false;
        }
        if (this.zzmnq != com_google_android_gms_internal_zzfii.zzmnq) {
            return false;
        }
        if (!zzfhi.equals(this.zzpkk, com_google_android_gms_internal_zzfii.zzpkk)) {
            return false;
        }
        if (!Arrays.equals(this.zzpkl, com_google_android_gms_internal_zzfii.zzpkl)) {
            return false;
        }
        if (this.zzpkm == null) {
            if (com_google_android_gms_internal_zzfii.zzpkm != null) {
                return false;
            }
        } else if (!this.zzpkm.equals(com_google_android_gms_internal_zzfii.zzpkm)) {
            return false;
        }
        if (!Arrays.equals(this.zzpkn, com_google_android_gms_internal_zzfii.zzpkn)) {
            return false;
        }
        if (this.zzpko == null) {
            if (com_google_android_gms_internal_zzfii.zzpko != null) {
                return false;
            }
        } else if (!this.zzpko.equals(com_google_android_gms_internal_zzfii.zzpko)) {
            return false;
        }
        if (this.zzpkp == null) {
            if (com_google_android_gms_internal_zzfii.zzpkp != null) {
                return false;
            }
        } else if (!this.zzpkp.equals(com_google_android_gms_internal_zzfii.zzpkp)) {
            return false;
        }
        if (this.zzpkq == null) {
            if (com_google_android_gms_internal_zzfii.zzpkq != null) {
                return false;
            }
        } else if (!this.zzpkq.equals(com_google_android_gms_internal_zzfii.zzpkq)) {
            return false;
        }
        if (this.zzpkr == null) {
            if (com_google_android_gms_internal_zzfii.zzpkr != null) {
                return false;
            }
        } else if (!this.zzpkr.equals(com_google_android_gms_internal_zzfii.zzpkr)) {
            return false;
        }
        if (this.zzpks != com_google_android_gms_internal_zzfii.zzpks) {
            return false;
        }
        if (this.zzpkt == null) {
            if (com_google_android_gms_internal_zzfii.zzpkt != null) {
                return false;
            }
        } else if (!this.zzpkt.equals(com_google_android_gms_internal_zzfii.zzpkt)) {
            return false;
        }
        if (!Arrays.equals(this.zzpku, com_google_android_gms_internal_zzfii.zzpku)) {
            return false;
        }
        if (this.zzpkv == null) {
            if (com_google_android_gms_internal_zzfii.zzpkv != null) {
                return false;
            }
        } else if (!this.zzpkv.equals(com_google_android_gms_internal_zzfii.zzpkv)) {
            return false;
        }
        if (this.zzpkw != com_google_android_gms_internal_zzfii.zzpkw) {
            return false;
        }
        if (!zzfhi.equals(this.zzpkx, com_google_android_gms_internal_zzfii.zzpkx)) {
            return false;
        }
        if (this.zzpky != com_google_android_gms_internal_zzfii.zzpky) {
            return false;
        }
        if (this.zzoan == null) {
            if (com_google_android_gms_internal_zzfii.zzoan != null) {
                return false;
            }
        } else if (!this.zzoan.equals(com_google_android_gms_internal_zzfii.zzoan)) {
            return false;
        }
        return (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzfii.zzpgy == null || com_google_android_gms_internal_zzfii.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzfii.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((this.zzmnq ? 1231 : 1237) + (((((((this.tag == null ? 0 : this.tag.hashCode()) + ((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.zzpkg ^ (this.zzpkg >>> 32)))) * 31) + ((int) (this.zzpkh ^ (this.zzpkh >>> 32)))) * 31) + ((int) (this.zzpki ^ (this.zzpki >>> 32)))) * 31)) * 31) + this.zzpkj) * 31) + this.zzakb) * 31)) * 31) + zzfhi.hashCode(this.zzpkk)) * 31) + Arrays.hashCode(this.zzpkl);
        zzfig com_google_android_gms_internal_zzfig = this.zzpkm;
        hashCode = (this.zzpkp == null ? 0 : this.zzpkp.hashCode()) + (((this.zzpko == null ? 0 : this.zzpko.hashCode()) + (((((com_google_android_gms_internal_zzfig == null ? 0 : com_google_android_gms_internal_zzfig.hashCode()) + (hashCode * 31)) * 31) + Arrays.hashCode(this.zzpkn)) * 31)) * 31);
        zzfif com_google_android_gms_internal_zzfif = this.zzpkq;
        hashCode = (((this.zzpkr == null ? 0 : this.zzpkr.hashCode()) + (((com_google_android_gms_internal_zzfif == null ? 0 : com_google_android_gms_internal_zzfif.hashCode()) + (hashCode * 31)) * 31)) * 31) + ((int) (this.zzpks ^ (this.zzpks >>> 32)));
        zzfih com_google_android_gms_internal_zzfih = this.zzpkt;
        hashCode = (((((((this.zzpkv == null ? 0 : this.zzpkv.hashCode()) + (((((com_google_android_gms_internal_zzfih == null ? 0 : com_google_android_gms_internal_zzfih.hashCode()) + (hashCode * 31)) * 31) + Arrays.hashCode(this.zzpku)) * 31)) * 31) + this.zzpkw) * 31) + zzfhi.hashCode(this.zzpkx)) * 31) + ((int) (this.zzpky ^ (this.zzpky >>> 32)));
        zzfik com_google_android_gms_internal_zzfik = this.zzoan;
        hashCode = ((com_google_android_gms_internal_zzfik == null ? 0 : com_google_android_gms_internal_zzfik.hashCode()) + (hashCode * 31)) * 31;
        if (!(this.zzpgy == null || this.zzpgy.isEmpty())) {
            i = this.zzpgy.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzfhk zza(zzfhb com_google_android_gms_internal_zzfhb) throws IOException {
        return zzar(com_google_android_gms_internal_zzfhb);
    }

    public final void zza(zzfhc com_google_android_gms_internal_zzfhc) throws IOException {
        int i = 0;
        if (this.zzpkg != 0) {
            com_google_android_gms_internal_zzfhc.zzf(1, this.zzpkg);
        }
        if (!(this.tag == null || this.tag.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(2, this.tag);
        }
        if (this.zzpkk != null && this.zzpkk.length > 0) {
            for (zzfhk com_google_android_gms_internal_zzfhk : this.zzpkk) {
                if (com_google_android_gms_internal_zzfhk != null) {
                    com_google_android_gms_internal_zzfhc.zza(3, com_google_android_gms_internal_zzfhk);
                }
            }
        }
        if (!Arrays.equals(this.zzpkl, zzfhn.zzphr)) {
            com_google_android_gms_internal_zzfhc.zzc(4, this.zzpkl);
        }
        if (!Arrays.equals(this.zzpkn, zzfhn.zzphr)) {
            com_google_android_gms_internal_zzfhc.zzc(6, this.zzpkn);
        }
        if (this.zzpkq != null) {
            com_google_android_gms_internal_zzfhc.zza(7, this.zzpkq);
        }
        if (!(this.zzpko == null || this.zzpko.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(8, this.zzpko);
        }
        if (this.zzpkm != null) {
            com_google_android_gms_internal_zzfhc.zza(9, this.zzpkm);
        }
        if (this.zzmnq) {
            com_google_android_gms_internal_zzfhc.zzl(10, this.zzmnq);
        }
        if (this.zzpkj != 0) {
            com_google_android_gms_internal_zzfhc.zzaa(11, this.zzpkj);
        }
        if (this.zzakb != 0) {
            com_google_android_gms_internal_zzfhc.zzaa(12, this.zzakb);
        }
        if (!(this.zzpkp == null || this.zzpkp.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(13, this.zzpkp);
        }
        if (!(this.zzpkr == null || this.zzpkr.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(14, this.zzpkr);
        }
        if (this.zzpks != 180000) {
            com_google_android_gms_internal_zzfhc.zzg(15, this.zzpks);
        }
        if (this.zzpkt != null) {
            com_google_android_gms_internal_zzfhc.zza(16, this.zzpkt);
        }
        if (this.zzpkh != 0) {
            com_google_android_gms_internal_zzfhc.zzf(17, this.zzpkh);
        }
        if (!Arrays.equals(this.zzpku, zzfhn.zzphr)) {
            com_google_android_gms_internal_zzfhc.zzc(18, this.zzpku);
        }
        if (this.zzpkw != 0) {
            com_google_android_gms_internal_zzfhc.zzaa(19, this.zzpkw);
        }
        if (this.zzpkx != null && this.zzpkx.length > 0) {
            while (i < this.zzpkx.length) {
                com_google_android_gms_internal_zzfhc.zzaa(20, this.zzpkx[i]);
                i++;
            }
        }
        if (this.zzpki != 0) {
            com_google_android_gms_internal_zzfhc.zzf(21, this.zzpki);
        }
        if (this.zzpky != 0) {
            com_google_android_gms_internal_zzfhc.zzf(22, this.zzpky);
        }
        if (this.zzoan != null) {
            com_google_android_gms_internal_zzfhc.zza(23, this.zzoan);
        }
        if (!(this.zzpkv == null || this.zzpkv.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(24, this.zzpkv);
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    public final /* synthetic */ zzfhe zzcxe() throws CloneNotSupportedException {
        return (zzfii) clone();
    }

    public final /* synthetic */ zzfhk zzcxf() throws CloneNotSupportedException {
        return (zzfii) clone();
    }

    protected final int zzo() {
        int i;
        int i2 = 0;
        int zzo = super.zzo();
        if (this.zzpkg != 0) {
            zzo += zzfhc.zzc(1, this.zzpkg);
        }
        if (!(this.tag == null || this.tag.equals(""))) {
            zzo += zzfhc.zzo(2, this.tag);
        }
        if (this.zzpkk != null && this.zzpkk.length > 0) {
            i = zzo;
            for (zzfhk com_google_android_gms_internal_zzfhk : this.zzpkk) {
                if (com_google_android_gms_internal_zzfhk != null) {
                    i += zzfhc.zzb(3, com_google_android_gms_internal_zzfhk);
                }
            }
            zzo = i;
        }
        if (!Arrays.equals(this.zzpkl, zzfhn.zzphr)) {
            zzo += zzfhc.zzd(4, this.zzpkl);
        }
        if (!Arrays.equals(this.zzpkn, zzfhn.zzphr)) {
            zzo += zzfhc.zzd(6, this.zzpkn);
        }
        if (this.zzpkq != null) {
            zzo += zzfhc.zzb(7, this.zzpkq);
        }
        if (!(this.zzpko == null || this.zzpko.equals(""))) {
            zzo += zzfhc.zzo(8, this.zzpko);
        }
        if (this.zzpkm != null) {
            zzo += zzfhc.zzb(9, this.zzpkm);
        }
        if (this.zzmnq) {
            zzo += zzfhc.zzkw(10) + 1;
        }
        if (this.zzpkj != 0) {
            zzo += zzfhc.zzad(11, this.zzpkj);
        }
        if (this.zzakb != 0) {
            zzo += zzfhc.zzad(12, this.zzakb);
        }
        if (!(this.zzpkp == null || this.zzpkp.equals(""))) {
            zzo += zzfhc.zzo(13, this.zzpkp);
        }
        if (!(this.zzpkr == null || this.zzpkr.equals(""))) {
            zzo += zzfhc.zzo(14, this.zzpkr);
        }
        if (this.zzpks != 180000) {
            zzo += zzfhc.zzh(15, this.zzpks);
        }
        if (this.zzpkt != null) {
            zzo += zzfhc.zzb(16, this.zzpkt);
        }
        if (this.zzpkh != 0) {
            zzo += zzfhc.zzc(17, this.zzpkh);
        }
        if (!Arrays.equals(this.zzpku, zzfhn.zzphr)) {
            zzo += zzfhc.zzd(18, this.zzpku);
        }
        if (this.zzpkw != 0) {
            zzo += zzfhc.zzad(19, this.zzpkw);
        }
        if (this.zzpkx != null && this.zzpkx.length > 0) {
            i = 0;
            while (i2 < this.zzpkx.length) {
                i += zzfhc.zzkx(this.zzpkx[i2]);
                i2++;
            }
            zzo = (zzo + i) + (this.zzpkx.length * 2);
        }
        if (this.zzpki != 0) {
            zzo += zzfhc.zzc(21, this.zzpki);
        }
        if (this.zzpky != 0) {
            zzo += zzfhc.zzc(22, this.zzpky);
        }
        if (this.zzoan != null) {
            zzo += zzfhc.zzb(23, this.zzoan);
        }
        return (this.zzpkv == null || this.zzpkv.equals("")) ? zzo : zzo + zzfhc.zzo(24, this.zzpkv);
    }
}
