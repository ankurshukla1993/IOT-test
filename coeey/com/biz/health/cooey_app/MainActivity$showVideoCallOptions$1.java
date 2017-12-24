package com.biz.health.cooey_app;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;
import com.biz.health.cooey_app.video_call.VideoCallService;
import com.cooey.android.users.UserSelectedCallback;
import com.cooey.android.video_call.VideoCallActivity;
import com.cooey.api.ApiClient;
import com.cooey.api.client.models.User;
import com.cooey.api.client.models.VideoCallSession;
import com.cooey.common.stores.PreferenceStore;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "selectedUser", "Lcom/cooey/api/client/models/User;", "kotlin.jvm.PlatformType", "onUserSelected"}, k = 3, mv = {1, 1, 7})
/* compiled from: MainActivity.kt */
final class MainActivity$showVideoCallOptions$1 implements UserSelectedCallback {
    final /* synthetic */ BottomSheetDialog $dialog;
    final /* synthetic */ MainActivity this$0;

    MainActivity$showVideoCallOptions$1(MainActivity mainActivity, BottomSheetDialog bottomSheetDialog) {
        this.this$0 = mainActivity;
        this.$dialog = bottomSheetDialog;
    }

    public final void onUserSelected(User selectedUser) {
        Toast.makeText(this.this$0, "calling..", 1).show();
        this.$dialog.dismiss();
        String token = new PreferenceStore().getSession(this.this$0).getId();
        com.cooey.common.vo.User user = this.this$0.getUser();
        if (user == null) {
            Intrinsics.throwNpe();
        }
        String patientId = user.getId();
        VideoCallSession videoCallSession = new VideoCallSession();
        videoCallSession.setUpdatedOn(Long.valueOf(Calendar.getInstance().getTimeInMillis()));
        videoCallSession.setCreatedOn(Long.valueOf(Calendar.getInstance().getTimeInMillis()));
        videoCallSession.setFrom(patientId);
        videoCallSession.setTo(selectedUser.getId());
        ((VideoCallService) new ApiClient().createService(VideoCallService.class)).createVideoCallRequest(token, videoCallSession).enqueue(new Callback<VideoCallSession>() {
            public void onResponse(@NotNull Call<VideoCallSession> call, @NotNull Response<VideoCallSession> response) {
                Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
                Intrinsics.checkParameterIsNotNull(response, "response");
                VideoCallSession responseSession = (VideoCallSession) response.body();
                Intent i = new Intent(this.this$0, VideoCallActivity.class);
                String str = "url";
                if (responseSession == null) {
                    Intrinsics.throwNpe();
                }
                i.putExtra(str, responseSession.getJitsiVideoUrl());
                this.this$0.startActivity(i);
            }

            public void onFailure(@NotNull Call<VideoCallSession> call, @NotNull Throwable t) {
                Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
                Intrinsics.checkParameterIsNotNull(t, "t");
                String data = "";
            }
        });
    }
}
