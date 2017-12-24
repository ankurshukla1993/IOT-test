package com.biz.health.cooey_app.notifications;

import com.cooey.common.services.ApiClient;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Channel;
import com.cooey.common.vo.ChannelType;
import com.cooey.common.vo.User;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CooeyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    class C07051 implements Callback<Channel> {
        C07051() {
        }

        public void onResponse(Call<Channel> call, Response<Channel> response) {
        }

        public void onFailure(Call<Channel> call, Throwable t) {
        }
    }

    public void onTokenRefresh() {
        sendRegistrationToServer(FirebaseInstanceId.getInstance().getToken());
    }

    private void sendRegistrationToServer(String refreshedToken) {
        ApiClient apiClient = new ApiClient(getApplicationContext(), "http://api.cooey.co.in/ehealth/");
        User user = new PreferenceStore().getUser(this);
        if (user != null && user.getId() != null) {
            String patientId = user.getId();
            Channel channel = new Channel();
            channel.setSource("ANDROID");
            channel.setSourceId(refreshedToken);
            channel.setType(ChannelType.NOTIFICATION);
            channel.setUserId(patientId);
            apiClient.getChannelsService().create(channel).enqueue(new C07051());
        }
    }
}
