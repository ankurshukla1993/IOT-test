package com.cooey.common.services;

import com.cooey.common.vo.careplan.Feature;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface FeatureService {
    @GET("feature/all/{courseId}")
    Call<List<Feature>> getAllFeatures(@Header("X-Auth-Token") String str, @Path("courseId") String str2);

    @GET("feature/{id}")
    Call<Feature> getFeature(@Header("X-Auth-Token") String str, @Path("id") String str2);
}
