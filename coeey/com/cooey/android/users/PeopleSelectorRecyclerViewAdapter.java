package com.cooey.android.users;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cooey.android.users.databinding.LayoutPeopleItemViewBinding;
import com.cooey.api.client.models.User;
import java.util.List;

public class PeopleSelectorRecyclerViewAdapter extends Adapter<UserItemViewHolder> {
    private final UserSelectedCallback userSelectedCallback;
    private List<User> users;

    public PeopleSelectorRecyclerViewAdapter(UserSelectedCallback userSelectedCallback) {
        this.userSelectedCallback = userSelectedCallback;
    }

    public UserItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserItemViewHolder(LayoutPeopleItemViewBinding.inflate(LayoutInflater.from(parent.getContext())), this.userSelectedCallback);
    }

    public void onBindViewHolder(UserItemViewHolder holder, int position) {
        holder.setUser((User) this.users.get(position));
    }

    public int getItemCount() {
        if (this.users == null) {
            return 0;
        }
        return this.users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
