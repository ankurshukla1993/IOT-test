package com.cooey.common.services;

import com.cooey.common.vo.careplan.Course;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CourseService {
    @GET("course")
    Call<Course> getCourseById(@Header("X-Auth-Token") String str, @Query("courseId") String str2);

    @GET("course/owner")
    Call<List<Course>> getCourses(@Header("X-Auth-Token") String str, @Query("id") String str2, @Query("type") String str3);

    @POST("course")
    Call<List<Course>> postCourses(@Header("X-Auth-Token") String str, @Body Course course);
}
