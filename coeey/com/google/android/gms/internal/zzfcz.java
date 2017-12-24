package com.google.android.gms.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public abstract class zzfcz<MessageType extends zzfcz<MessageType, BuilderType>, BuilderType extends zzfda<MessageType, BuilderType>> implements zzffi {
    private static boolean zzpag = false;
    protected int zzpaf = 0;

    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzfda.zza((Iterable) iterable, (List) list);
    }

    public final byte[] toByteArray() {
        try {
            byte[] bArr = new byte[zzhl()];
            zzfdv zzbb = zzfdv.zzbb(bArr);
            zza(zzbb);
            zzbb.zzcus();
            return bArr;
        } catch (Throwable e) {
            String str = "byte array";
            String name = getClass().getName();
            throw new RuntimeException(new StringBuilder((String.valueOf(name).length() + 62) + String.valueOf(str).length()).append("Serializing ").append(name).append(" to a ").append(str).append(" threw an IOException (should never happen).").toString(), e);
        }
    }

    public final zzfdh toByteString() {
        try {
            zzfdm zzke = zzfdh.zzke(zzhl());
            zza(zzke.zzctr());
            return zzke.zzctq();
        } catch (Throwable e) {
            String str = "ByteString";
            String name = getClass().getName();
            throw new RuntimeException(new StringBuilder((String.valueOf(name).length() + 62) + String.valueOf(str).length()).append("Serializing ").append(name).append(" to a ").append(str).append(" threw an IOException (should never happen).").toString(), e);
        }
    }

    public final void writeTo(OutputStream outputStream) throws IOException {
        zzfdv zzb = zzfdv.zzb(outputStream, zzfdv.zzkr(zzhl()));
        zza(zzb);
        zzb.flush();
    }
}
