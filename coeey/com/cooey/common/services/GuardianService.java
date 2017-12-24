package com.cooey.common.services;

import com.cooey.common.vo.Guardian;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GuardianService {
    @GET("v2/users/{userId}/guardians")
    @Headers({"Accept: application/json"})
    Call<List<Guardian>> getGuardiansForPatient(@Header("X-Auth-Token") String str, @Path("userId") String str2, @Query("limit") int i, @Query("offset") int i2);
}
