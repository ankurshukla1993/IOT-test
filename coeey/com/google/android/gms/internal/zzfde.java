package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfde extends zzfee<zzfde, zza> implements zzffk {
    private static volatile zzffm<zzfde> zzbas;
    private static final zzfde zzpak;
    private String zzlso = "";
    private zzfdh zzlsp = zzfdh.zzpal;

    public static final class zza extends zzfef<zzfde, zza> implements zzffk {
        private zza() {
            super(zzfde.zzpak);
        }
    }

    static {
        zzfee com_google_android_gms_internal_zzfde = new zzfde();
        zzpak = com_google_android_gms_internal_zzfde;
        com_google_android_gms_internal_zzfde.zza(zzfem.zzpcf, null, null);
        com_google_android_gms_internal_zzfde.zzpbs.zzbim();
    }

    private zzfde() {
    }

    public static zzfde zzctj() {
        return zzpak;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        boolean z = true;
        boolean z2;
        switch (zzfdf.zzbaq[i - 1]) {
            case 1:
                return new zzfde();
            case 2:
                return zzpak;
            case 3:
                return null;
            case 4:
                return new zza();
            case 5:
                zzfen com_google_android_gms_internal_zzfen = (zzfen) obj;
                zzfde com_google_android_gms_internal_zzfde = (zzfde) obj2;
                this.zzlso = com_google_android_gms_internal_zzfen.zza(!this.zzlso.isEmpty(), this.zzlso, !com_google_android_gms_internal_zzfde.zzlso.isEmpty(), com_google_android_gms_internal_zzfde.zzlso);
                z2 = this.zzlsp != zzfdh.zzpal;
                zzfdh com_google_android_gms_internal_zzfdh = this.zzlsp;
                if (com_google_android_gms_internal_zzfde.zzlsp == zzfdh.zzpal) {
                    z = false;
                }
                this.zzlsp = com_google_android_gms_internal_zzfen.zza(z2, com_google_android_gms_internal_zzfdh, z, com_google_android_gms_internal_zzfde.zzlsp);
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
                                case 10:
                                    this.zzlso = com_google_android_gms_internal_zzfdq.zzctz();
                                    break;
                                case 18:
                                    this.zzlsp = com_google_android_gms_internal_zzfdq.zzcua();
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
                    synchronized (zzfde.class) {
                        if (zzbas == null) {
                            zzbas = new zzfeg(zzpak);
                        }
                    }
                }
                return zzbas;
            default:
                throw new UnsupportedOperationException();
        }
        return zzpak;
    }

    public final void zza(zzfdv com_google_android_gms_internal_zzfdv) throws IOException {
        if (!this.zzlso.isEmpty()) {
            com_google_android_gms_internal_zzfdv.zzn(1, this.zzlso);
        }
        if (!this.zzlsp.isEmpty()) {
            com_google_android_gms_internal_zzfdv.zza(2, this.zzlsp);
        }
        this.zzpbs.zza(com_google_android_gms_internal_zzfdv);
    }

    public final int zzhl() {
        int i = this.zzpbt;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (!this.zzlso.isEmpty()) {
            i = zzfdv.zzo(1, this.zzlso) + 0;
        }
        if (!this.zzlsp.isEmpty()) {
            i += zzfdv.zzb(2, this.zzlsp);
        }
        i += this.zzpbs.zzhl();
        this.zzpbt = i;
        return i;
    }
}
