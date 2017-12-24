package com.google.android.gms.internal;

import com.facebook.imageutils.JfifUtil;
import java.io.IOException;

public final class zzclb extends zzfhe<zzclb> {
    private static volatile zzclb[] zzjis;
    public String zzch;
    public String zzcv;
    public Long zzfhr;
    public String zzicq;
    public String zziux;
    public String zziuy;
    public String zzivb;
    public String zzivf;
    public Integer zzjit;
    public zzcky[] zzjiu;
    public zzcld[] zzjiv;
    public Long zzjiw;
    public Long zzjix;
    public Long zzjiy;
    public Long zzjiz;
    public Long zzjja;
    public String zzjjb;
    public String zzjjc;
    public String zzjjd;
    public Integer zzjje;
    public Long zzjjf;
    public Long zzjjg;
    public String zzjjh;
    public Boolean zzjji;
    public String zzjjj;
    public Long zzjjk;
    public Integer zzjjl;
    public Boolean zzjjm;
    public zzckx[] zzjjn;
    public Integer zzjjo;
    private Integer zzjjp;
    private Integer zzjjq;
    public String zzjjr;
    public Long zzjjs;
    public String zzjjt;

    public zzclb() {
        this.zzjit = null;
        this.zzjiu = zzcky.zzbav();
        this.zzjiv = zzcld.zzbay();
        this.zzjiw = null;
        this.zzjix = null;
        this.zzjiy = null;
        this.zzjiz = null;
        this.zzjja = null;
        this.zzjjb = null;
        this.zzcv = null;
        this.zzjjc = null;
        this.zzjjd = null;
        this.zzjje = null;
        this.zziuy = null;
        this.zzch = null;
        this.zzicq = null;
        this.zzjjf = null;
        this.zzjjg = null;
        this.zzjjh = null;
        this.zzjji = null;
        this.zzjjj = null;
        this.zzjjk = null;
        this.zzjjl = null;
        this.zzivb = null;
        this.zziux = null;
        this.zzjjm = null;
        this.zzjjn = zzckx.zzbau();
        this.zzivf = null;
        this.zzjjo = null;
        this.zzjjp = null;
        this.zzjjq = null;
        this.zzjjr = null;
        this.zzjjs = null;
        this.zzfhr = null;
        this.zzjjt = null;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public static zzclb[] zzbax() {
        if (zzjis == null) {
            synchronized (zzfhi.zzphg) {
                if (zzjis == null) {
                    zzjis = new zzclb[0];
                }
            }
        }
        return zzjis;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzclb)) {
            return false;
        }
        zzclb com_google_android_gms_internal_zzclb = (zzclb) obj;
        if (this.zzjit == null) {
            if (com_google_android_gms_internal_zzclb.zzjit != null) {
                return false;
            }
        } else if (!this.zzjit.equals(com_google_android_gms_internal_zzclb.zzjit)) {
            return false;
        }
        if (!zzfhi.equals(this.zzjiu, com_google_android_gms_internal_zzclb.zzjiu)) {
            return false;
        }
        if (!zzfhi.equals(this.zzjiv, com_google_android_gms_internal_zzclb.zzjiv)) {
            return false;
        }
        if (this.zzjiw == null) {
            if (com_google_android_gms_internal_zzclb.zzjiw != null) {
                return false;
            }
        } else if (!this.zzjiw.equals(com_google_android_gms_internal_zzclb.zzjiw)) {
            return false;
        }
        if (this.zzjix == null) {
            if (com_google_android_gms_internal_zzclb.zzjix != null) {
                return false;
            }
        } else if (!this.zzjix.equals(com_google_android_gms_internal_zzclb.zzjix)) {
            return false;
        }
        if (this.zzjiy == null) {
            if (com_google_android_gms_internal_zzclb.zzjiy != null) {
                return false;
            }
        } else if (!this.zzjiy.equals(com_google_android_gms_internal_zzclb.zzjiy)) {
            return false;
        }
        if (this.zzjiz == null) {
            if (com_google_android_gms_internal_zzclb.zzjiz != null) {
                return false;
            }
        } else if (!this.zzjiz.equals(com_google_android_gms_internal_zzclb.zzjiz)) {
            return false;
        }
        if (this.zzjja == null) {
            if (com_google_android_gms_internal_zzclb.zzjja != null) {
                return false;
            }
        } else if (!this.zzjja.equals(com_google_android_gms_internal_zzclb.zzjja)) {
            return false;
        }
        if (this.zzjjb == null) {
            if (com_google_android_gms_internal_zzclb.zzjjb != null) {
                return false;
            }
        } else if (!this.zzjjb.equals(com_google_android_gms_internal_zzclb.zzjjb)) {
            return false;
        }
        if (this.zzcv == null) {
            if (com_google_android_gms_internal_zzclb.zzcv != null) {
                return false;
            }
        } else if (!this.zzcv.equals(com_google_android_gms_internal_zzclb.zzcv)) {
            return false;
        }
        if (this.zzjjc == null) {
            if (com_google_android_gms_internal_zzclb.zzjjc != null) {
                return false;
            }
        } else if (!this.zzjjc.equals(com_google_android_gms_internal_zzclb.zzjjc)) {
            return false;
        }
        if (this.zzjjd == null) {
            if (com_google_android_gms_internal_zzclb.zzjjd != null) {
                return false;
            }
        } else if (!this.zzjjd.equals(com_google_android_gms_internal_zzclb.zzjjd)) {
            return false;
        }
        if (this.zzjje == null) {
            if (com_google_android_gms_internal_zzclb.zzjje != null) {
                return false;
            }
        } else if (!this.zzjje.equals(com_google_android_gms_internal_zzclb.zzjje)) {
            return false;
        }
        if (this.zziuy == null) {
            if (com_google_android_gms_internal_zzclb.zziuy != null) {
                return false;
            }
        } else if (!this.zziuy.equals(com_google_android_gms_internal_zzclb.zziuy)) {
            return false;
        }
        if (this.zzch == null) {
            if (com_google_android_gms_internal_zzclb.zzch != null) {
                return false;
            }
        } else if (!this.zzch.equals(com_google_android_gms_internal_zzclb.zzch)) {
            return false;
        }
        if (this.zzicq == null) {
            if (com_google_android_gms_internal_zzclb.zzicq != null) {
                return false;
            }
        } else if (!this.zzicq.equals(com_google_android_gms_internal_zzclb.zzicq)) {
            return false;
        }
        if (this.zzjjf == null) {
            if (com_google_android_gms_internal_zzclb.zzjjf != null) {
                return false;
            }
        } else if (!this.zzjjf.equals(com_google_android_gms_internal_zzclb.zzjjf)) {
            return false;
        }
        if (this.zzjjg == null) {
            if (com_google_android_gms_internal_zzclb.zzjjg != null) {
                return false;
            }
        } else if (!this.zzjjg.equals(com_google_android_gms_internal_zzclb.zzjjg)) {
            return false;
        }
        if (this.zzjjh == null) {
            if (com_google_android_gms_internal_zzclb.zzjjh != null) {
                return false;
            }
        } else if (!this.zzjjh.equals(com_google_android_gms_internal_zzclb.zzjjh)) {
            return false;
        }
        if (this.zzjji == null) {
            if (com_google_android_gms_internal_zzclb.zzjji != null) {
                return false;
            }
        } else if (!this.zzjji.equals(com_google_android_gms_internal_zzclb.zzjji)) {
            return false;
        }
        if (this.zzjjj == null) {
            if (com_google_android_gms_internal_zzclb.zzjjj != null) {
                return false;
            }
        } else if (!this.zzjjj.equals(com_google_android_gms_internal_zzclb.zzjjj)) {
            return false;
        }
        if (this.zzjjk == null) {
            if (com_google_android_gms_internal_zzclb.zzjjk != null) {
                return false;
            }
        } else if (!this.zzjjk.equals(com_google_android_gms_internal_zzclb.zzjjk)) {
            return false;
        }
        if (this.zzjjl == null) {
            if (com_google_android_gms_internal_zzclb.zzjjl != null) {
                return false;
            }
        } else if (!this.zzjjl.equals(com_google_android_gms_internal_zzclb.zzjjl)) {
            return false;
        }
        if (this.zzivb == null) {
            if (com_google_android_gms_internal_zzclb.zzivb != null) {
                return false;
            }
        } else if (!this.zzivb.equals(com_google_android_gms_internal_zzclb.zzivb)) {
            return false;
        }
        if (this.zziux == null) {
            if (com_google_android_gms_internal_zzclb.zziux != null) {
                return false;
            }
        } else if (!this.zziux.equals(com_google_android_gms_internal_zzclb.zziux)) {
            return false;
        }
        if (this.zzjjm == null) {
            if (com_google_android_gms_internal_zzclb.zzjjm != null) {
                return false;
            }
        } else if (!this.zzjjm.equals(com_google_android_gms_internal_zzclb.zzjjm)) {
            return false;
        }
        if (!zzfhi.equals(this.zzjjn, com_google_android_gms_internal_zzclb.zzjjn)) {
            return false;
        }
        if (this.zzivf == null) {
            if (com_google_android_gms_internal_zzclb.zzivf != null) {
                return false;
            }
        } else if (!this.zzivf.equals(com_google_android_gms_internal_zzclb.zzivf)) {
            return false;
        }
        if (this.zzjjo == null) {
            if (com_google_android_gms_internal_zzclb.zzjjo != null) {
                return false;
            }
        } else if (!this.zzjjo.equals(com_google_android_gms_internal_zzclb.zzjjo)) {
            return false;
        }
        if (this.zzjjp == null) {
            if (com_google_android_gms_internal_zzclb.zzjjp != null) {
                return false;
            }
        } else if (!this.zzjjp.equals(com_google_android_gms_internal_zzclb.zzjjp)) {
            return false;
        }
        if (this.zzjjq == null) {
            if (com_google_android_gms_internal_zzclb.zzjjq != null) {
                return false;
            }
        } else if (!this.zzjjq.equals(com_google_android_gms_internal_zzclb.zzjjq)) {
            return false;
        }
        if (this.zzjjr == null) {
            if (com_google_android_gms_internal_zzclb.zzjjr != null) {
                return false;
            }
        } else if (!this.zzjjr.equals(com_google_android_gms_internal_zzclb.zzjjr)) {
            return false;
        }
        if (this.zzjjs == null) {
            if (com_google_android_gms_internal_zzclb.zzjjs != null) {
                return false;
            }
        } else if (!this.zzjjs.equals(com_google_android_gms_internal_zzclb.zzjjs)) {
            return false;
        }
        if (this.zzfhr == null) {
            if (com_google_android_gms_internal_zzclb.zzfhr != null) {
                return false;
            }
        } else if (!this.zzfhr.equals(com_google_android_gms_internal_zzclb.zzfhr)) {
            return false;
        }
        if (this.zzjjt == null) {
            if (com_google_android_gms_internal_zzclb.zzjjt != null) {
                return false;
            }
        } else if (!this.zzjjt.equals(com_google_android_gms_internal_zzclb.zzjjt)) {
            return false;
        }
        return (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzclb.zzpgy == null || com_google_android_gms_internal_zzclb.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzclb.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzjjt == null ? 0 : this.zzjjt.hashCode()) + (((this.zzfhr == null ? 0 : this.zzfhr.hashCode()) + (((this.zzjjs == null ? 0 : this.zzjjs.hashCode()) + (((this.zzjjr == null ? 0 : this.zzjjr.hashCode()) + (((this.zzjjq == null ? 0 : this.zzjjq.hashCode()) + (((this.zzjjp == null ? 0 : this.zzjjp.hashCode()) + (((this.zzjjo == null ? 0 : this.zzjjo.hashCode()) + (((this.zzivf == null ? 0 : this.zzivf.hashCode()) + (((((this.zzjjm == null ? 0 : this.zzjjm.hashCode()) + (((this.zziux == null ? 0 : this.zziux.hashCode()) + (((this.zzivb == null ? 0 : this.zzivb.hashCode()) + (((this.zzjjl == null ? 0 : this.zzjjl.hashCode()) + (((this.zzjjk == null ? 0 : this.zzjjk.hashCode()) + (((this.zzjjj == null ? 0 : this.zzjjj.hashCode()) + (((this.zzjji == null ? 0 : this.zzjji.hashCode()) + (((this.zzjjh == null ? 0 : this.zzjjh.hashCode()) + (((this.zzjjg == null ? 0 : this.zzjjg.hashCode()) + (((this.zzjjf == null ? 0 : this.zzjjf.hashCode()) + (((this.zzicq == null ? 0 : this.zzicq.hashCode()) + (((this.zzch == null ? 0 : this.zzch.hashCode()) + (((this.zziuy == null ? 0 : this.zziuy.hashCode()) + (((this.zzjje == null ? 0 : this.zzjje.hashCode()) + (((this.zzjjd == null ? 0 : this.zzjjd.hashCode()) + (((this.zzjjc == null ? 0 : this.zzjjc.hashCode()) + (((this.zzcv == null ? 0 : this.zzcv.hashCode()) + (((this.zzjjb == null ? 0 : this.zzjjb.hashCode()) + (((this.zzjja == null ? 0 : this.zzjja.hashCode()) + (((this.zzjiz == null ? 0 : this.zzjiz.hashCode()) + (((this.zzjiy == null ? 0 : this.zzjiy.hashCode()) + (((this.zzjix == null ? 0 : this.zzjix.hashCode()) + (((this.zzjiw == null ? 0 : this.zzjiw.hashCode()) + (((((((this.zzjit == null ? 0 : this.zzjit.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + zzfhi.hashCode(this.zzjiu)) * 31) + zzfhi.hashCode(this.zzjiv)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + zzfhi.hashCode(this.zzjjn)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (!(this.zzpgy == null || this.zzpgy.isEmpty())) {
            i = this.zzpgy.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzfhk zza(zzfhb com_google_android_gms_internal_zzfhb) throws IOException {
        while (true) {
            int zzcts = com_google_android_gms_internal_zzfhb.zzcts();
            int zzb;
            Object obj;
            switch (zzcts) {
                case 0:
                    break;
                case 8:
                    this.zzjit = Integer.valueOf(com_google_android_gms_internal_zzfhb.zzcuh());
                    continue;
                case 18:
                    zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 18);
                    zzcts = this.zzjiu == null ? 0 : this.zzjiu.length;
                    obj = new zzcky[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzjiu, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = new zzcky();
                        com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = new zzcky();
                    com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                    this.zzjiu = obj;
                    continue;
                case 26:
                    zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 26);
                    zzcts = this.zzjiv == null ? 0 : this.zzjiv.length;
                    obj = new zzcld[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzjiv, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = new zzcld();
                        com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = new zzcld();
                    com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                    this.zzjiv = obj;
                    continue;
                case 32:
                    this.zzjiw = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 40:
                    this.zzjix = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 48:
                    this.zzjiy = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 56:
                    this.zzjja = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 66:
                    this.zzjjb = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 74:
                    this.zzcv = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 82:
                    this.zzjjc = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 90:
                    this.zzjjd = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 96:
                    this.zzjje = Integer.valueOf(com_google_android_gms_internal_zzfhb.zzcuh());
                    continue;
                case 106:
                    this.zziuy = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 114:
                    this.zzch = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 130:
                    this.zzicq = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 136:
                    this.zzjjf = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 144:
                    this.zzjjg = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 154:
                    this.zzjjh = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 160:
                    this.zzjji = Boolean.valueOf(com_google_android_gms_internal_zzfhb.zzcty());
                    continue;
                case 170:
                    this.zzjjj = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 176:
                    this.zzjjk = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 184:
                    this.zzjjl = Integer.valueOf(com_google_android_gms_internal_zzfhb.zzcuh());
                    continue;
                case 194:
                    this.zzivb = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 202:
                    this.zziux = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case JfifUtil.MARKER_RST0 /*208*/:
                    this.zzjiz = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 224:
                    this.zzjjm = Boolean.valueOf(com_google_android_gms_internal_zzfhb.zzcty());
                    continue;
                case 234:
                    zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 234);
                    zzcts = this.zzjjn == null ? 0 : this.zzjjn.length;
                    obj = new zzckx[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzjjn, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = new zzckx();
                        com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = new zzckx();
                    com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                    this.zzjjn = obj;
                    continue;
                case 242:
                    this.zzivf = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 248:
                    this.zzjjo = Integer.valueOf(com_google_android_gms_internal_zzfhb.zzcuh());
                    continue;
                case 256:
                    this.zzjjp = Integer.valueOf(com_google_android_gms_internal_zzfhb.zzcuh());
                    continue;
                case 264:
                    this.zzjjq = Integer.valueOf(com_google_android_gms_internal_zzfhb.zzcuh());
                    continue;
                case TiffUtil.TIFF_TAG_ORIENTATION /*274*/:
                    this.zzjjr = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 280:
                    this.zzjjs = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 288:
                    this.zzfhr = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 298:
                    this.zzjjt = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                default:
                    if (!super.zza(com_google_android_gms_internal_zzfhb, zzcts)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final void zza(zzfhc com_google_android_gms_internal_zzfhc) throws IOException {
        int i = 0;
        if (this.zzjit != null) {
            com_google_android_gms_internal_zzfhc.zzaa(1, this.zzjit.intValue());
        }
        if (this.zzjiu != null && this.zzjiu.length > 0) {
            for (zzfhk com_google_android_gms_internal_zzfhk : this.zzjiu) {
                if (com_google_android_gms_internal_zzfhk != null) {
                    com_google_android_gms_internal_zzfhc.zza(2, com_google_android_gms_internal_zzfhk);
                }
            }
        }
        if (this.zzjiv != null && this.zzjiv.length > 0) {
            for (zzfhk com_google_android_gms_internal_zzfhk2 : this.zzjiv) {
                if (com_google_android_gms_internal_zzfhk2 != null) {
                    com_google_android_gms_internal_zzfhc.zza(3, com_google_android_gms_internal_zzfhk2);
                }
            }
        }
        if (this.zzjiw != null) {
            com_google_android_gms_internal_zzfhc.zzf(4, this.zzjiw.longValue());
        }
        if (this.zzjix != null) {
            com_google_android_gms_internal_zzfhc.zzf(5, this.zzjix.longValue());
        }
        if (this.zzjiy != null) {
            com_google_android_gms_internal_zzfhc.zzf(6, this.zzjiy.longValue());
        }
        if (this.zzjja != null) {
            com_google_android_gms_internal_zzfhc.zzf(7, this.zzjja.longValue());
        }
        if (this.zzjjb != null) {
            com_google_android_gms_internal_zzfhc.zzn(8, this.zzjjb);
        }
        if (this.zzcv != null) {
            com_google_android_gms_internal_zzfhc.zzn(9, this.zzcv);
        }
        if (this.zzjjc != null) {
            com_google_android_gms_internal_zzfhc.zzn(10, this.zzjjc);
        }
        if (this.zzjjd != null) {
            com_google_android_gms_internal_zzfhc.zzn(11, this.zzjjd);
        }
        if (this.zzjje != null) {
            com_google_android_gms_internal_zzfhc.zzaa(12, this.zzjje.intValue());
        }
        if (this.zziuy != null) {
            com_google_android_gms_internal_zzfhc.zzn(13, this.zziuy);
        }
        if (this.zzch != null) {
            com_google_android_gms_internal_zzfhc.zzn(14, this.zzch);
        }
        if (this.zzicq != null) {
            com_google_android_gms_internal_zzfhc.zzn(16, this.zzicq);
        }
        if (this.zzjjf != null) {
            com_google_android_gms_internal_zzfhc.zzf(17, this.zzjjf.longValue());
        }
        if (this.zzjjg != null) {
            com_google_android_gms_internal_zzfhc.zzf(18, this.zzjjg.longValue());
        }
        if (this.zzjjh != null) {
            com_google_android_gms_internal_zzfhc.zzn(19, this.zzjjh);
        }
        if (this.zzjji != null) {
            com_google_android_gms_internal_zzfhc.zzl(20, this.zzjji.booleanValue());
        }
        if (this.zzjjj != null) {
            com_google_android_gms_internal_zzfhc.zzn(21, this.zzjjj);
        }
        if (this.zzjjk != null) {
            com_google_android_gms_internal_zzfhc.zzf(22, this.zzjjk.longValue());
        }
        if (this.zzjjl != null) {
            com_google_android_gms_internal_zzfhc.zzaa(23, this.zzjjl.intValue());
        }
        if (this.zzivb != null) {
            com_google_android_gms_internal_zzfhc.zzn(24, this.zzivb);
        }
        if (this.zziux != null) {
            com_google_android_gms_internal_zzfhc.zzn(25, this.zziux);
        }
        if (this.zzjiz != null) {
            com_google_android_gms_internal_zzfhc.zzf(26, this.zzjiz.longValue());
        }
        if (this.zzjjm != null) {
            com_google_android_gms_internal_zzfhc.zzl(28, this.zzjjm.booleanValue());
        }
        if (this.zzjjn != null && this.zzjjn.length > 0) {
            while (i < this.zzjjn.length) {
                zzfhk com_google_android_gms_internal_zzfhk3 = this.zzjjn[i];
                if (com_google_android_gms_internal_zzfhk3 != null) {
                    com_google_android_gms_internal_zzfhc.zza(29, com_google_android_gms_internal_zzfhk3);
                }
                i++;
            }
        }
        if (this.zzivf != null) {
            com_google_android_gms_internal_zzfhc.zzn(30, this.zzivf);
        }
        if (this.zzjjo != null) {
            com_google_android_gms_internal_zzfhc.zzaa(31, this.zzjjo.intValue());
        }
        if (this.zzjjp != null) {
            com_google_android_gms_internal_zzfhc.zzaa(32, this.zzjjp.intValue());
        }
        if (this.zzjjq != null) {
            com_google_android_gms_internal_zzfhc.zzaa(33, this.zzjjq.intValue());
        }
        if (this.zzjjr != null) {
            com_google_android_gms_internal_zzfhc.zzn(34, this.zzjjr);
        }
        if (this.zzjjs != null) {
            com_google_android_gms_internal_zzfhc.zzf(35, this.zzjjs.longValue());
        }
        if (this.zzfhr != null) {
            com_google_android_gms_internal_zzfhc.zzf(36, this.zzfhr.longValue());
        }
        if (this.zzjjt != null) {
            com_google_android_gms_internal_zzfhc.zzn(37, this.zzjjt);
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int i;
        int i2 = 0;
        int zzo = super.zzo();
        if (this.zzjit != null) {
            zzo += zzfhc.zzad(1, this.zzjit.intValue());
        }
        if (this.zzjiu != null && this.zzjiu.length > 0) {
            i = zzo;
            for (zzfhk com_google_android_gms_internal_zzfhk : this.zzjiu) {
                if (com_google_android_gms_internal_zzfhk != null) {
                    i += zzfhc.zzb(2, com_google_android_gms_internal_zzfhk);
                }
            }
            zzo = i;
        }
        if (this.zzjiv != null && this.zzjiv.length > 0) {
            i = zzo;
            for (zzfhk com_google_android_gms_internal_zzfhk2 : this.zzjiv) {
                if (com_google_android_gms_internal_zzfhk2 != null) {
                    i += zzfhc.zzb(3, com_google_android_gms_internal_zzfhk2);
                }
            }
            zzo = i;
        }
        if (this.zzjiw != null) {
            zzo += zzfhc.zzc(4, this.zzjiw.longValue());
        }
        if (this.zzjix != null) {
            zzo += zzfhc.zzc(5, this.zzjix.longValue());
        }
        if (this.zzjiy != null) {
            zzo += zzfhc.zzc(6, this.zzjiy.longValue());
        }
        if (this.zzjja != null) {
            zzo += zzfhc.zzc(7, this.zzjja.longValue());
        }
        if (this.zzjjb != null) {
            zzo += zzfhc.zzo(8, this.zzjjb);
        }
        if (this.zzcv != null) {
            zzo += zzfhc.zzo(9, this.zzcv);
        }
        if (this.zzjjc != null) {
            zzo += zzfhc.zzo(10, this.zzjjc);
        }
        if (this.zzjjd != null) {
            zzo += zzfhc.zzo(11, this.zzjjd);
        }
        if (this.zzjje != null) {
            zzo += zzfhc.zzad(12, this.zzjje.intValue());
        }
        if (this.zziuy != null) {
            zzo += zzfhc.zzo(13, this.zziuy);
        }
        if (this.zzch != null) {
            zzo += zzfhc.zzo(14, this.zzch);
        }
        if (this.zzicq != null) {
            zzo += zzfhc.zzo(16, this.zzicq);
        }
        if (this.zzjjf != null) {
            zzo += zzfhc.zzc(17, this.zzjjf.longValue());
        }
        if (this.zzjjg != null) {
            zzo += zzfhc.zzc(18, this.zzjjg.longValue());
        }
        if (this.zzjjh != null) {
            zzo += zzfhc.zzo(19, this.zzjjh);
        }
        if (this.zzjji != null) {
            this.zzjji.booleanValue();
            zzo += zzfhc.zzkw(20) + 1;
        }
        if (this.zzjjj != null) {
            zzo += zzfhc.zzo(21, this.zzjjj);
        }
        if (this.zzjjk != null) {
            zzo += zzfhc.zzc(22, this.zzjjk.longValue());
        }
        if (this.zzjjl != null) {
            zzo += zzfhc.zzad(23, this.zzjjl.intValue());
        }
        if (this.zzivb != null) {
            zzo += zzfhc.zzo(24, this.zzivb);
        }
        if (this.zziux != null) {
            zzo += zzfhc.zzo(25, this.zziux);
        }
        if (this.zzjiz != null) {
            zzo += zzfhc.zzc(26, this.zzjiz.longValue());
        }
        if (this.zzjjm != null) {
            this.zzjjm.booleanValue();
            zzo += zzfhc.zzkw(28) + 1;
        }
        if (this.zzjjn != null && this.zzjjn.length > 0) {
            while (i2 < this.zzjjn.length) {
                zzfhk com_google_android_gms_internal_zzfhk3 = this.zzjjn[i2];
                if (com_google_android_gms_internal_zzfhk3 != null) {
                    zzo += zzfhc.zzb(29, com_google_android_gms_internal_zzfhk3);
                }
                i2++;
            }
        }
        if (this.zzivf != null) {
            zzo += zzfhc.zzo(30, this.zzivf);
        }
        if (this.zzjjo != null) {
            zzo += zzfhc.zzad(31, this.zzjjo.intValue());
        }
        if (this.zzjjp != null) {
            zzo += zzfhc.zzad(32, this.zzjjp.intValue());
        }
        if (this.zzjjq != null) {
            zzo += zzfhc.zzad(33, this.zzjjq.intValue());
        }
        if (this.zzjjr != null) {
            zzo += zzfhc.zzo(34, this.zzjjr);
        }
        if (this.zzjjs != null) {
            zzo += zzfhc.zzc(35, this.zzjjs.longValue());
        }
        if (this.zzfhr != null) {
            zzo += zzfhc.zzc(36, this.zzfhr.longValue());
        }
        return this.zzjjt != null ? zzo + zzfhc.zzo(37, this.zzjjt) : zzo;
    }
}
