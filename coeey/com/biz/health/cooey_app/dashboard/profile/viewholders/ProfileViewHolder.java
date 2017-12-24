package com.biz.health.cooey_app.dashboard.profile.viewholders;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.cooey.common.vo.User;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/biz/health/cooey_app/dashboard/profile/viewholders/ProfileViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "user", "Lcom/cooey/common/vo/User;", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: ProfileViewHolder.kt */
public abstract class ProfileViewHolder extends ViewHolder {
    public abstract void bind(@NotNull User user);

    public ProfileViewHolder(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super(view);
    }
}
