package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class zzfda<MessageType extends zzfcz<MessageType, BuilderType>, BuilderType extends zzfda<MessageType, BuilderType>> implements zzffj {
    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzfer.checkNotNull(iterable);
        if (iterable instanceof zzffd) {
            List zzcwb = ((zzffd) iterable).zzcwb();
            zzffd com_google_android_gms_internal_zzffd = (zzffd) list;
            int size = list.size();
            for (Object next : zzcwb) {
                if (next == null) {
                    String str = "Element at index " + (com_google_android_gms_internal_zzffd.size() - size) + " is null.";
                    for (int size2 = com_google_android_gms_internal_zzffd.size() - 1; size2 >= size; size2--) {
                        com_google_android_gms_internal_zzffd.remove(size2);
                    }
                    throw new NullPointerException(str);
                } else if (!(next instanceof zzfdh)) {
                    com_google_android_gms_internal_zzffd.add((String) next);
                }
            }
        } else if (iterable instanceof zzffn) {
            list.addAll((Collection) iterable);
        } else {
            zzb((Iterable) iterable, (List) list);
        }
    }

    private static <T> void zzb(Iterable<T> iterable, List<? super T> list) {
        if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
            ((ArrayList) list).ensureCapacity(((Collection) iterable).size() + list.size());
        }
        int size = list.size();
        for (Object next : iterable) {
            if (next == null) {
                String str = "Element at index " + (list.size() - size) + " is null.";
                for (int size2 = list.size() - 1; size2 >= size; size2--) {
                    list.remove(size2);
                }
                throw new NullPointerException(str);
            }
            list.add(next);
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzctg();
    }

    protected abstract BuilderType zza(MessageType messageType);

    public abstract BuilderType zza(zzfdq com_google_android_gms_internal_zzfdq, zzfea com_google_android_gms_internal_zzfea) throws IOException;

    public /* synthetic */ zzffj zzb(zzfdq com_google_android_gms_internal_zzfdq, zzfea com_google_android_gms_internal_zzfea) throws IOException {
        return zza(com_google_android_gms_internal_zzfdq, com_google_android_gms_internal_zzfea);
    }

    public final /* synthetic */ zzffj zzc(zzffi com_google_android_gms_internal_zzffi) {
        if (zzcvh().getClass().isInstance(com_google_android_gms_internal_zzffi)) {
            return zza((zzfcz) com_google_android_gms_internal_zzffi);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    public abstract BuilderType zzctg();
}
