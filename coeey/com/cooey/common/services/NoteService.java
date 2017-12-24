package com.cooey.common.services;

import com.cooey.common.vo.Note;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NoteService {
    @GET("v2/notes/user/{userId}")
    Call<List<Note>> getAllNotesForUser(@Header("X-Auth-Token") String str, @Path("userId") String str2);

    @POST("v2/notes")
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> postNote(@Header("X-Auth-Token") String str, @Body Note note);
}
