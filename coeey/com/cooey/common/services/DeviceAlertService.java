package com.cooey.common.services;

import com.cooey.common.vo.DeviceAlert;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface DeviceAlertService {
    @GET("v2/devices/{patientId}/alert/emergency")
    Call<List<DeviceAlert>> getDeviceAlert(@Header("X-Auth-Token") String str, @Path("patientId") String str2);
}
