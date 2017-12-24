package com.biz.health.cooey_app;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import com.cooey.android.users.UserSelectedCallback;
import com.cooey.api.client.models.User;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "selectedUser", "Lcom/cooey/api/client/models/User;", "kotlin.jvm.PlatformType", "onUserSelected"}, k = 3, mv = {1, 1, 7})
/* compiled from: MainActivity.kt */
final class MainActivity$showChatOptions$1 implements UserSelectedCallback {
    final /* synthetic */ BottomSheetDialog $dialog;
    final /* synthetic */ MainActivity this$0;

    MainActivity$showChatOptions$1(MainActivity mainActivity, BottomSheetDialog bottomSheetDialog) {
        this.this$0 = mainActivity;
        this.$dialog = bottomSheetDialog;
    }

    public final void onUserSelected(User selectedUser) {
        this.$dialog.dismiss();
        Intent i = new Intent(this.this$0, NewMessagesActivity.class);
        String str = "senderId";
        com.cooey.common.vo.User user = this.this$0.getUser();
        if (user == null) {
            Intrinsics.throwNpe();
        }
        i.putExtra(str, user.getId());
        i.putExtra("receiverId", selectedUser.getId());
        this.this$0.startActivity(i);
    }
}
