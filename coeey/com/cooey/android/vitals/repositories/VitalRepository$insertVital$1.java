package com.cooey.android.vitals.repositories;

import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.cooey.android.vitals.Vital;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J$\u0010\n\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\fH\u0016¨\u0006\r"}, d2 = {"com/cooey/android/vitals/repositories/VitalRepository$insertVital$1", "Lretrofit2/Callback;", "Lcom/cooey/android/vitals/Vital;", "()V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalRepository.kt */
public final class VitalRepository$insertVital$1 implements Callback<Vital> {
    VitalRepository$insertVital$1() {
    }

    public void onFailure(@NotNull Call<Vital> call, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(t, "t");
        StringBuilder append = new StringBuilder().append("");
        t.printStackTrace();
        Log.d("Exception", append.append(Unit.INSTANCE).toString());
    }

    public void onResponse(@NotNull Call<Vital> call, @NotNull Response<Vital> response) {
        Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(response, "response");
        Vital response2 = (Vital) response.body();
    }
}
