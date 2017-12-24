package com.cooey.android.users;

import com.cooey.api.client.models.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class PeopleSelectorViewModel$1 implements Callback<List<User>> {
    final /* synthetic */ PeopleSelectorViewModel this$0;

    PeopleSelectorViewModel$1(PeopleSelectorViewModel this$0) {
        this.this$0 = this$0;
    }

    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        this.this$0.setUsersDataProgress(false);
        this.this$0.setUsers((List) response.body());
    }

    public void onFailure(Call<List<User>> call, Throwable t) {
        String data = "";
        this.this$0.setUsersDataProgress(false);
    }
}
