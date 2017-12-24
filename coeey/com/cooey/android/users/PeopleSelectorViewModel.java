package com.cooey.android.users;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.cooey.api.ApiClient;
import com.cooey.api.client.UsersApi;
import com.cooey.api.client.models.User;
import java.util.List;

public class PeopleSelectorViewModel extends BaseObservable {
    ApiClient apiClient = new ApiClient();
    private final Context context;
    private boolean isUsersDataProgress;
    private final String patientId;
    private PeopleSelectorRecyclerViewAdapter recyclerViewAdapter;
    private final UserSelectedCallback userSelectedCallback;
    private List<User> users;

    @Bindable
    public PeopleSelectorRecyclerViewAdapter getRecyclerViewAdapter() {
        return this.recyclerViewAdapter;
    }

    @Bindable
    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        this.recyclerViewAdapter.setUsers(users);
        this.recyclerViewAdapter.notifyDataSetChanged();
        notifyPropertyChanged(BR.users);
    }

    public PeopleSelectorViewModel(Context context, String patientId, UserSelectedCallback userSelectedCallback) {
        this.context = context;
        this.patientId = patientId;
        this.userSelectedCallback = userSelectedCallback;
        this.recyclerViewAdapter = new PeopleSelectorRecyclerViewAdapter(userSelectedCallback);
        getPeople();
    }

    public void setRecyclerViewAdapter(PeopleSelectorRecyclerViewAdapter recyclerViewAdapter) {
        this.recyclerViewAdapter = recyclerViewAdapter;
        notifyPropertyChanged(BR.recyclerViewAdapter);
    }

    @Bindable
    public boolean isUsersDataProgress() {
        return this.isUsersDataProgress;
    }

    public void setUsersDataProgress(boolean usersDataProgress) {
        this.isUsersDataProgress = usersDataProgress;
        notifyPropertyChanged(BR.usersDataProgress);
    }

    public void getPeople() {
        setUsersDataProgress(true);
        ((UsersApi) this.apiClient.createService(UsersApi.class)).getCaretakers(this.patientId, "token").enqueue(new 1(this));
    }
}
