package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfeo extends zzfee<zzfeo, zza> implements zzffk {
    private static volatile zzffm<zzfeo> zzbas;
    private static final zzfeo zzpcp;
    private int zzpco;

    public static final class zza extends zzfef<zzfeo, zza> implements zzffk {
        private zza() {
            super(zzfeo.zzpcp);
        }

        public final zza zzli(int i) {
            zzcvi();
            ((zzfeo) this.zzpbv).setValue(i);
            return this;
        }
    }

    static {
        zzfee com_google_android_gms_internal_zzfeo = new zzfeo();
        zzpcp = com_google_android_gms_internal_zzfeo;
        com_google_android_gms_internal_zzfeo.zza(zzfem.zzpcf, null, null);
        com_google_android_gms_internal_zzfeo.zzpbs.zzbim();
    }

    private zzfeo() {
    }

    private final void setValue(int i) {
        this.zzpco = i;
    }

    public static zza zzcvn() {
        zzfee com_google_android_gms_internal_zzfee = zzpcp;
        zzfef com_google_android_gms_internal_zzfef = (zzfef) com_google_android_gms_internal_zzfee.zza(zzfem.zzpch, null, null);
        com_google_android_gms_internal_zzfef.zza(com_google_android_gms_internal_zzfee);
        return (zza) com_google_android_gms_internal_zzfef;
    }

    public static zzfeo zzcvo() {
        return zzpcp;
    }

    public final int getValue() {
        return this.zzpco;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        boolean z = true;
        boolean z2;
        switch (zzfep.zzbaq[i - 1]) {
            case 1:
                return new zzfeo();
            case 2:
                return zzpcp;
            case 3:
                return null;
            case 4:
                return new zza();
            case 5:
                zzfen com_google_android_gms_internal_zzfen = (zzfen) obj;
                zzfeo com_google_android_gms_internal_zzfeo = (zzfeo) obj2;
                z2 = this.zzpco != 0;
                int i2 = this.zzpco;
                if (com_google_android_gms_internal_zzfeo.zzpco == 0) {
                    z = false;
                }
                this.zzpco = com_google_android_gms_internal_zzfen.zza(z2, i2, z, com_google_android_gms_internal_zzfeo.zzpco);
                return this;
            case 6:
                zzfdq com_google_android_gms_internal_zzfdq = (zzfdq) obj;
                if (((zzfea) obj2) != null) {
                    boolean z3 = false;
                    while (!z3) {
                        try {
                            int zzcts = com_google_android_gms_internal_zzfdq.zzcts();
                            switch (zzcts) {
                                case 0:
                                    z3 = true;
                                    break;
                                case 8:
                                    this.zzpco = com_google_android_gms_internal_zzfdq.zzctv();
                                    break;
                                default:
                                    if ((zzcts & 7) == 4) {
                                        z2 = false;
                                    } else {
                                        if (this.zzpbs == zzfgi.zzcwu()) {
                                            this.zzpbs = zzfgi.zzcwv();
                                        }
                                        z2 = this.zzpbs.zzb(zzcts, com_google_android_gms_internal_zzfdq);
                                    }
                                    if (!z2) {
                                        z3 = true;
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
                    synchronized (zzfeo.class) {
                        if (zzbas == null) {
                            zzbas = new zzfeg(zzpcp);
                        }
                    }
                }
                return zzbas;
            default:
                throw new UnsupportedOperationException();
        }
        return zzpcp;
    }

    public final void zza(zzfdv com_google_android_gms_internal_zzfdv) throws IOException {
        if (this.zzpco != 0) {
            com_google_android_gms_internal_zzfdv.zzaa(1, this.zzpco);
        }
        this.zzpbs.zza(com_google_android_gms_internal_zzfdv);
    }

    public final int zzhl() {
        int i = this.zzpbt;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (this.zzpco != 0) {
            i = zzfdv.zzad(1, this.zzpco) + 0;
        }
        i += this.zzpbs.zzhl();
        this.zzpbt = i;
        return i;
    }
}
