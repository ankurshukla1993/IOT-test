package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class zzfee<MessageType extends zzfee<MessageType, BuilderType>, BuilderType extends zzfef<MessageType, BuilderType>> extends zzfcz<MessageType, BuilderType> {
    protected zzfgi zzpbs = zzfgi.zzcwu();
    protected int zzpbt = -1;

    protected static <T extends zzfee<T, ?>> T zza(T t, zzfdh com_google_android_gms_internal_zzfdh) throws zzfew {
        Object obj = 1;
        T zza = zza((zzfee) t, com_google_android_gms_internal_zzfdh, zzfea.zzcuz());
        if (zza != null) {
            if ((zza.zza(zzfem.zzpcc, Boolean.TRUE, null) != null ? 1 : null) == null) {
                throw new zzfgh(zza).zzcwt().zzh(zza);
            }
        }
        if (zza != null) {
            if (zza.zza(zzfem.zzpcc, Boolean.TRUE, null) == null) {
                obj = null;
            }
            if (obj == null) {
                throw new zzfgh(zza).zzcwt().zzh(zza);
            }
        }
        return zza;
    }

    private static <T extends zzfee<T, ?>> T zza(T t, zzfdh com_google_android_gms_internal_zzfdh, zzfea com_google_android_gms_internal_zzfea) throws zzfew {
        T zza;
        try {
            zzfdq zzctl = com_google_android_gms_internal_zzfdh.zzctl();
            zza = zza((zzfee) t, zzctl, com_google_android_gms_internal_zzfea);
            zzctl.zzkf(0);
            return zza;
        } catch (zzfew e) {
            throw e.zzh(zza);
        } catch (zzfew e2) {
            throw e2;
        }
    }

    static <T extends zzfee<T, ?>> T zza(T t, zzfdq com_google_android_gms_internal_zzfdq, zzfea com_google_android_gms_internal_zzfea) throws zzfew {
        zzfee com_google_android_gms_internal_zzfee = (zzfee) t.zza(zzfem.zzpcg, null, null);
        try {
            com_google_android_gms_internal_zzfee.zza(zzfem.zzpce, (Object) com_google_android_gms_internal_zzfdq, (Object) com_google_android_gms_internal_zzfea);
            com_google_android_gms_internal_zzfee.zza(zzfem.zzpcf, null, null);
            com_google_android_gms_internal_zzfee.zzpbs.zzbim();
            return com_google_android_gms_internal_zzfee;
        } catch (RuntimeException e) {
            if (e.getCause() instanceof zzfew) {
                throw ((zzfew) e.getCause());
            }
            throw e;
        }
    }

    protected static <T extends zzfee<T, ?>> T zza(T t, byte[] bArr) throws zzfew {
        T zza = zza((zzfee) t, bArr, zzfea.zzcuz());
        if (zza != null) {
            if ((zza.zza(zzfem.zzpcc, Boolean.TRUE, null) != null ? 1 : null) == null) {
                throw new zzfgh(zza).zzcwt().zzh(zza);
            }
        }
        return zza;
    }

    private static <T extends zzfee<T, ?>> T zza(T t, byte[] bArr, zzfea com_google_android_gms_internal_zzfea) throws zzfew {
        T zza;
        try {
            zzfdq zzba = zzfdq.zzba(bArr);
            zza = zza((zzfee) t, zzba, com_google_android_gms_internal_zzfea);
            zzba.zzkf(0);
            return zza;
        } catch (zzfew e) {
            throw e.zzh(zza);
        } catch (zzfew e2) {
            throw e2;
        }
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        Throwable e;
        try {
            return method.invoke(obj, objArr);
        } catch (Throwable e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            e2 = e3.getCause();
            if (e2 instanceof RuntimeException) {
                throw ((RuntimeException) e2);
            } else if (e2 instanceof Error) {
                throw ((Error) e2);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", e2);
            }
        }
    }

    protected static zzfeu zzcve() {
        return zzfeq.zzcvq();
    }

    protected static <E> zzfev<E> zzcvf() {
        return zzffo.zzcwf();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zzfee) zza(zzfem.zzpci, null, null)).getClass().isInstance(obj)) {
            return false;
        }
        try {
            Object obj2 = zzfeh.zzpbx;
            obj = (zzfee) obj;
            zza(zzfem.zzpcd, obj2, obj);
            this.zzpbs = obj2.zza(this.zzpbs, obj.zzpbs);
            return true;
        } catch (zzfei e) {
            return false;
        }
    }

    public int hashCode() {
        if (this.zzpaf != 0) {
            return this.zzpaf;
        }
        Object com_google_android_gms_internal_zzfek = new zzfek();
        zza(zzfem.zzpcd, com_google_android_gms_internal_zzfek, (Object) this);
        this.zzpbs = com_google_android_gms_internal_zzfek.zza(this.zzpbs, this.zzpbs);
        this.zzpaf = com_google_android_gms_internal_zzfek.zzpca;
        return this.zzpaf;
    }

    public final boolean isInitialized() {
        return zza(zzfem.zzpcc, Boolean.TRUE, null) != null;
    }

    public String toString() {
        return zzffl.zza(this, super.toString());
    }

    protected abstract Object zza(int i, Object obj, Object obj2);

    protected final boolean zza(int i, zzfdq com_google_android_gms_internal_zzfdq) throws IOException {
        if ((i & 7) == 4) {
            return false;
        }
        if (this.zzpbs == zzfgi.zzcwu()) {
            this.zzpbs = zzfgi.zzcwv();
        }
        return this.zzpbs.zzb(i, com_google_android_gms_internal_zzfdq);
    }

    public final zzffm<MessageType> zzcvd() {
        return (zzffm) zza(zzfem.zzpcj, null, null);
    }

    public final /* synthetic */ zzffj zzcvg() {
        zzfef com_google_android_gms_internal_zzfef = (zzfef) zza(zzfem.zzpch, null, null);
        com_google_android_gms_internal_zzfef.zza(this);
        return com_google_android_gms_internal_zzfef;
    }

    public final /* synthetic */ zzffi zzcvh() {
        return (zzfee) zza(zzfem.zzpci, null, null);
    }
}
