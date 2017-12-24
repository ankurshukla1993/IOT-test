package com.cooey.android.vitals.repositories;

import android.support.v4.app.NotificationCompat;
import com.cooey.android.vitals.Vital;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J0\u0010\u000b\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\b2\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"com/cooey/android/vitals/repositories/VitalRepository$syncVitals$1", "Lretrofit2/Callback;", "", "Lcom/cooey/android/vitals/Vital;", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalRepository.kt */
public final class VitalRepository$syncVitals$1 implements Callback<List<? extends Vital>> {
    final /* synthetic */ VitalRepository this$0;

    public void onResponse(@org.jetbrains.annotations.NotNull retrofit2.Call<java.util.List<com.cooey.android.vitals.Vital>> r1, @org.jetbrains.annotations.NotNull retrofit2.Response<java.util.List<com.cooey.android.vitals.Vital>> r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.cooey.android.vitals.repositories.VitalRepository$syncVitals$1.onResponse(retrofit2.Call, retrofit2.Response):void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
	at jadx.core.dex.nodes.MethodNode.addJump(MethodNode.java:370)
	at jadx.core.dex.nodes.MethodNode.initJumps(MethodNode.java:356)
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:106)
	... 5 more
*/
        /*
        r0 = this;
        r5 = "call";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r5);
        r5 = "response";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r5);
        r4 = r9.body();
        r4 = (java.util.List) r4;
        if (r4 != 0) goto L_0x0015;
    L_0x0012:
        kotlin.jvm.internal.Intrinsics.throwNpe();
    L_0x0015:
        r5 = r7.this$0;
        r5 = r5.getVitalDao();
        r6 = "vitals";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r6);
        r5.insert(r4);
        r2 = new java.util.ArrayList;
        r2.<init>();
        r2 = (java.util.List) r2;
        r0 = r4;
        r0 = (java.lang.Iterable) r0;
        r5 = r0.iterator();
        r6 = r5.hasNext();
        if (r6 == 0) goto L_0x004a;
    L_0x0038:
        r1 = r5.next();
        r3 = r1;
        r3 = (com.cooey.android.vitals.Vital) r3;
        r6 = r7.this$0;
        r6 = r6.getTimelineItemForVital(r3);
        r2.add(r6);
        goto L_0x0032;
        r5 = r7.this$0;
        r5 = r5.getTimelineItemDao();
        r5.insert(r2);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cooey.android.vitals.repositories.VitalRepository$syncVitals$1.onResponse(retrofit2.Call, retrofit2.Response):void");
    }

    VitalRepository$syncVitals$1(VitalRepository $outer) {
        this.this$0 = $outer;
    }

    public void onFailure(@NotNull Call<List<Vital>> call, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(t, "t");
        String failure = "";
    }
}
