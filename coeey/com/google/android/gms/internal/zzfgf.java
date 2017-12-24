package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfgf extends zzfee<zzfgf, zza> implements zzffk {
    private static volatile zzffm<zzfgf> zzbas;
    private static final zzfgf zzpej;
    private long zzpeh;
    private int zzpei;

    public static final class zza extends zzfef<zzfgf, zza> implements zzffk {
        private zza() {
            super(zzfgf.zzpej);
        }

        public final zza zzdf(long j) {
            zzcvi();
            ((zzfgf) this.zzpbv).zzde(j);
            return this;
        }

        public final zza zzls(int i) {
            zzcvi();
            ((zzfgf) this.zzpbv).setNanos(i);
            return this;
        }
    }

    static {
        zzfee com_google_android_gms_internal_zzfgf = new zzfgf();
        zzpej = com_google_android_gms_internal_zzfgf;
        com_google_android_gms_internal_zzfgf.zza(zzfem.zzpcf, null, null);
        com_google_android_gms_internal_zzfgf.zzpbs.zzbim();
    }

    private zzfgf() {
    }

    private final void setNanos(int i) {
        this.zzpei = i;
    }

    public static zza zzcwq() {
        zzfee com_google_android_gms_internal_zzfee = zzpej;
        zzfef com_google_android_gms_internal_zzfef = (zzfef) com_google_android_gms_internal_zzfee.zza(zzfem.zzpch, null, null);
        com_google_android_gms_internal_zzfef.zza(com_google_android_gms_internal_zzfee);
        return (zza) com_google_android_gms_internal_zzfef;
    }

    public static zzfgf zzcwr() {
        return zzpej;
    }

    private final void zzde(long j) {
        this.zzpeh = j;
    }

    public final int getNanos() {
        return this.zzpei;
    }

    public final long getSeconds() {
        return this.zzpeh;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        boolean z = true;
        boolean z2;
        switch (zzfgg.zzbaq[i - 1]) {
            case 1:
                return new zzfgf();
            case 2:
                return zzpej;
            case 3:
                return null;
            case 4:
                return new zza();
            case 5:
                zzfen com_google_android_gms_internal_zzfen = (zzfen) obj;
                zzfgf com_google_android_gms_internal_zzfgf = (zzfgf) obj2;
                this.zzpeh = com_google_android_gms_internal_zzfen.zza(this.zzpeh != 0, this.zzpeh, com_google_android_gms_internal_zzfgf.zzpeh != 0, com_google_android_gms_internal_zzfgf.zzpeh);
                z2 = this.zzpei != 0;
                int i2 = this.zzpei;
                if (com_google_android_gms_internal_zzfgf.zzpei == 0) {
                    z = false;
                }
                this.zzpei = com_google_android_gms_internal_zzfen.zza(z2, i2, z, com_google_android_gms_internal_zzfgf.zzpei);
                return this;
            case 6:
                zzfdq com_google_android_gms_internal_zzfdq = (zzfdq) obj;
                if (((zzfea) obj2) != null) {
                    z2 = false;
                    while (!z2) {
                        try {
                            int zzcts = com_google_android_gms_internal_zzfdq.zzcts();
                            switch (zzcts) {
                                case 0:
                                    z2 = true;
                                    break;
                                case 8:
                                    this.zzpeh = com_google_android_gms_internal_zzfdq.zzctu();
                                    break;
                                case 16:
                                    this.zzpei = com_google_android_gms_internal_zzfdq.zzctv();
                                    break;
                                default:
                                    boolean z3;
                                    if ((zzcts & 7) == 4) {
                                        z3 = false;
                                    } else {
                                        if (this.zzpbs == zzfgi.zzcwu()) {
                                            this.zzpbs = zzfgi.zzcwv();
                                        }
                                        z3 = this.zzpbs.zzb(zzcts, com_google_android_gms_internal_zzfdq);
                                    }
                                    if (!z3) {
                                        z2 = true;
                                        break;
                                    }
                                    break;
                            }
                        } catch (zzfew e) {
                            throw new RuntimeException(e.zzh(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new zzfew(e2.getMessage()).zzh(this));
                        }
                    }
                    break;
                }
                throw new NullPointerException();
            case 7:
                break;
            case 8:
                if (zzbas == null) {
                    synchronized (zzfgf.class) {
                        if (zzbas == null) {
                            zzbas = new zzfeg(zzpej);
                        }
                    }
                }
                return zzbas;
            default:
                throw new UnsupportedOperationException();
        }
        return zzpej;
    }

    public final void zza(zzfdv com_google_android_gms_internal_zzfdv) throws IOException {
        if (this.zzpeh != 0) {
            com_google_android_gms_internal_zzfdv.zza(1, this.zzpeh);
        }
        if (this.zzpei != 0) {
            com_google_android_gms_internal_zzfdv.zzaa(2, this.zzpei);
        }
        this.zzpbs.zza(com_google_android_gms_internal_zzfdv);
    }

    public final int zzhl() {
        int i = this.zzpbt;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (this.zzpeh != 0) {
            i = zzfdv.zzc(1, this.zzpeh) + 0;
        }
        if (this.zzpei != 0) {
            i += zzfdv.zzad(2, this.zzpei);
        }
        i += this.zzpbs.zzhl();
        this.zzpbt = i;
        return i;
    }
}
