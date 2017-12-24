package com.cooey.common.services;

import com.cooey.common.vo.DietTemplate;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface DietTemplatesService {
    @GET("v2/diet/owners/{ownerId}/templates")
    @Headers({"Accept:application/json"})
    Call<List<DietTemplate>> getDietTemplateForUser(@Path("ownerId") String str);

    @GET("v2/diet/owners/{ownerId}/templates")
    @Headers({"Accept:application/json"})
    Observable<List<DietTemplate>> getDietTemplateForUserWithObservable(@Path("ownerId") String str);
}
