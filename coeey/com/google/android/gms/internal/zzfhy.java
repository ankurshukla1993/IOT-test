package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfhy extends zzfee<zzfhy, zza> implements zzffk {
    private static volatile zzffm<zzfhy> zzbas;
    private static final zzfhy zzpjj;
    private int zzltg;
    private int zzpjg;
    private String zzpjh = "";
    private zzfev<zzfde> zzpji = zzfee.zzcvf();

    public static final class zza extends zzfef<zzfhy, zza> implements zzffk {
        private zza() {
            super(zzfhy.zzpjj);
        }
    }

    static {
        zzfee com_google_android_gms_internal_zzfhy = new zzfhy();
        zzpjj = com_google_android_gms_internal_zzfhy;
        com_google_android_gms_internal_zzfhy.zza(zzfem.zzpcf, null, null);
        com_google_android_gms_internal_zzfhy.zzpbs.zzbim();
    }

    private zzfhy() {
    }

    public static zzfhy zzcxo() {
        return zzpjj;
    }

    public final int getCode() {
        return this.zzpjg;
    }

    public final String getMessage() {
        return this.zzpjh;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        boolean z = false;
        boolean z2 = true;
        switch (zzfhz.zzbaq[i - 1]) {
            case 1:
                return new zzfhy();
            case 2:
                return zzpjj;
            case 3:
                this.zzpji.zzbim();
                return null;
            case 4:
                return new zza();
            case 5:
                zzfen com_google_android_gms_internal_zzfen = (zzfen) obj;
                zzfhy com_google_android_gms_internal_zzfhy = (zzfhy) obj2;
                this.zzpjg = com_google_android_gms_internal_zzfen.zza(this.zzpjg != 0, this.zzpjg, com_google_android_gms_internal_zzfhy.zzpjg != 0, com_google_android_gms_internal_zzfhy.zzpjg);
                boolean z3 = !this.zzpjh.isEmpty();
                String str = this.zzpjh;
                if (com_google_android_gms_internal_zzfhy.zzpjh.isEmpty()) {
                    z2 = false;
                }
                this.zzpjh = com_google_android_gms_internal_zzfen.zza(z3, str, z2, com_google_android_gms_internal_zzfhy.zzpjh);
                this.zzpji = com_google_android_gms_internal_zzfen.zza(this.zzpji, com_google_android_gms_internal_zzfhy.zzpji);
                if (com_google_android_gms_internal_zzfen != zzfel.zzpcb) {
                    return this;
                }
                this.zzltg |= com_google_android_gms_internal_zzfhy.zzltg;
                return this;
            case 6:
                zzfdq com_google_android_gms_internal_zzfdq = (zzfdq) obj;
                zzfea com_google_android_gms_internal_zzfea = (zzfea) obj2;
                if (com_google_android_gms_internal_zzfea != null) {
                    while (!z) {
                        try {
                            int zzcts = com_google_android_gms_internal_zzfdq.zzcts();
                            switch (zzcts) {
                                case 0:
                                    z = true;
                                    break;
                                case 8:
                                    this.zzpjg = com_google_android_gms_internal_zzfdq.zzctv();
                                    break;
                                case 18:
                                    this.zzpjh = com_google_android_gms_internal_zzfdq.zzctz();
                                    break;
                                case 26:
                                    if (!this.zzpji.zzcth()) {
                                        zzfev com_google_android_gms_internal_zzfev = this.zzpji;
                                        zzcts = com_google_android_gms_internal_zzfev.size();
                                        this.zzpji = com_google_android_gms_internal_zzfev.zzln(zzcts == 0 ? 10 : zzcts << 1);
                                    }
                                    this.zzpji.add((zzfde) com_google_android_gms_internal_zzfdq.zza(zzfde.zzctj(), com_google_android_gms_internal_zzfea));
                                    break;
                                default:
                                    if (!zza(zzcts, com_google_android_gms_internal_zzfdq)) {
                                        z = true;
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
                    synchronized (zzfhy.class) {
                        if (zzbas == null) {
                            zzbas = new zzfeg(zzpjj);
                        }
                    }
                }
                return zzbas;
            default:
                throw new UnsupportedOperationException();
        }
        return zzpjj;
    }

    public final void zza(zzfdv com_google_android_gms_internal_zzfdv) throws IOException {
        if (this.zzpjg != 0) {
            com_google_android_gms_internal_zzfdv.zzaa(1, this.zzpjg);
        }
        if (!this.zzpjh.isEmpty()) {
            com_google_android_gms_internal_zzfdv.zzn(2, this.zzpjh);
        }
        for (int i = 0; i < this.zzpji.size(); i++) {
            com_google_android_gms_internal_zzfdv.zza(3, (zzffi) this.zzpji.get(i));
        }
        this.zzpbs.zza(com_google_android_gms_internal_zzfdv);
    }

    public final int zzhl() {
        int i = 0;
        int i2 = this.zzpbt;
        if (i2 != -1) {
            return i2;
        }
        i2 = this.zzpjg != 0 ? zzfdv.zzad(1, this.zzpjg) + 0 : 0;
        if (!this.zzpjh.isEmpty()) {
            i2 += zzfdv.zzo(2, this.zzpjh);
        }
        int i3 = i2;
        while (i < this.zzpji.size()) {
            i++;
            i3 = zzfdv.zzb(3, (zzffi) this.zzpji.get(i)) + i3;
        }
        i2 = this.zzpbs.zzhl() + i3;
        this.zzpbt = i2;
        return i2;
    }
}
