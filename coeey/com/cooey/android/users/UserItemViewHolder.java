package com.cooey.android.users;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.cooey.android.users.databinding.LayoutPeopleItemViewBinding;
import com.cooey.api.client.models.User;

public class UserItemViewHolder extends ViewHolder {
    private final LayoutPeopleItemViewBinding layoutPeopleItemViewBinding;
    private User user;
    private final UserSelectedCallback userSelectedCallback;

    public UserItemViewHolder(LayoutPeopleItemViewBinding layoutPeopleItemViewBinding, UserSelectedCallback userSelectedCallback) {
        super(layoutPeopleItemViewBinding.getRoot());
        this.layoutPeopleItemViewBinding = layoutPeopleItemViewBinding;
        this.userSelectedCallback = userSelectedCallback;
    }

    public void setUser(final User user) {
        this.user = user;
        this.layoutPeopleItemViewBinding.setPeopleItemViewModel(new PeopleItemViewModel(user));
        this.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (UserItemViewHolder.this.userSelectedCallback != null) {
                    UserItemViewHolder.this.userSelectedCallback.onUserSelected(user);
                    Toast.makeText(UserItemViewHolder.this.itemView.getContext(), user.getFirstName(), 0).show();
                }
            }
        });
    }
}
