package com.cooey.android.vitals.services;

import com.cooey.android.vitals.Vital;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0004H'J\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\u0006H'JQ\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000b0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u00062\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u0006H'¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/cooey/android/vitals/services/VitalsService;", "", "addVitalForUser", "Lretrofit2/Call;", "Lcom/cooey/android/vitals/Vital;", "token", "", "vital", "getVital", "vitalId", "getVitalsForUser", "", "userId", "skip", "", "limit", "type", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lretrofit2/Call;", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalsService.kt */
public interface VitalsService {

    @Metadata(bv = {1, 0, 2}, k = 3, mv = {1, 1, 7})
    /* compiled from: VitalsService.kt */
    public static final class DefaultImpls {
        @NotNull
        @GET("v2/vitals/user/{userId}")
        public static /* bridge */ /* synthetic */ Call getVitalsForUser$default(VitalsService vitalsService, String str, String str2, Integer num, Integer num2, String str3, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getVitalsForUser");
            }
            return vitalsService.getVitalsForUser(str, str2, (i & 4) != 0 ? Integer.valueOf(0) : num, (i & 8) != 0 ? Integer.valueOf(50) : num2, (i & 16) != 0 ? (String) null : str3);
        }
    }

    @NotNull
    @POST("v2/vitals")
    @Headers({"Accept: application/json"})
    Call<Vital> addVitalForUser(@NotNull @Header("X-Auth-Token") String str, @NotNull @Body Vital vital);

    @NotNull
    @GET("v2/vitals/{vitalId}")
    Call<Vital> getVital(@NotNull @Header("X-Auth-Token") String str, @NotNull @Path("vitalId") String str2);

    @NotNull
    @GET("v2/vitals/user/{userId}")
    Call<List<Vital>> getVitalsForUser(@NotNull @Header("X-Auth-Token") String str, @NotNull @Path("userId") String str2, @Nullable @Query("skip") Integer num, @Nullable @Query("limit") Integer num2, @Nullable @Query("type") String str3);
}
