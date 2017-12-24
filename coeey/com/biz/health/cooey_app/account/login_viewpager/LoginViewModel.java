package com.biz.health.cooey_app.account.login_viewpager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.Button;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.services.ApiClient;
import com.cooey.common.services.requests.CreateSessionRequest;
import com.cooey.common.services.requests.CreateSessionRequest.TypeEnum;
import com.cooey.common.vo.Channel;
import com.cooey.common.vo.ChannelType;
import com.cooey.common.vo.Session;
import com.google.firebase.iid.FirebaseInstanceId;
import retrofit2.Response;

public class LoginViewModel extends BaseObservable {
    private ApiClient apiClient;
    private final Context context;
    private String email;
    private boolean inProgress;
    private String password;

    public LoginViewModel(Context context) {
        this.context = context;
        this.apiClient = new ApiClient(context, "http://api.cooey.co.in/ehealth/");
    }

    @Bindable
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(13);
    }

    @Bindable
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(36);
    }

    @Bindable
    public boolean isInProgress() {
        return this.inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
        notifyPropertyChanged(17);
    }

    public void onLoginClick() {
        setInProgress(true);
        CreateSessionRequest createSessionRequest = new CreateSessionRequest();
        createSessionRequest.setEmail(this.email);
        createSessionRequest.setPassword(this.password);
        createSessionRequest.setType(TypeEnum.PATIENT);
        this.apiClient.getSessionsService().create(createSessionRequest).enqueue(new 1(this));
    }

    private void getPartnerConfig(Response<Session> response) {
        String token = null;
        if (!(response.body() == null || ((Session) response.body()).getId() == null)) {
            token = ((Session) response.body()).getId();
        }
        if (token != null) {
            this.apiClient.getPartnerConfigService().getPartnerConfig(token, ((Session) response.body()).getTenantId(), CTConstants.PATIENTTYPE).enqueue(new 2(this));
        }
    }

    private void getUser(Response<Session> response) {
        Session session = (Session) response.body();
        this.apiClient.getUsersService().get_0(session.getUserId(), session.getId()).enqueue(new 3(this, session));
    }

    private void registerFCMToken(Session session) {
        String token = FirebaseInstanceId.getInstance().getToken();
        ApiClient apiClient = new ApiClient(this.context, "http://api.cooey.co.in/ehealth/");
        if (session != null && session.getId() != null && token != null) {
            String patientId = session.getUserId();
            Channel channel = new Channel();
            channel.setSource("ANDROID");
            channel.setSourceId(token);
            channel.setType(ChannelType.NOTIFICATION);
            channel.setUserId(patientId);
            apiClient.getChannelsService().create(channel).enqueue(new 4(this));
        }
    }

    public void showDialog(Activity activity, String msg) {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(false);
        dialog.setContentView(C0644R.layout.error_dialog);
        ((TextView) dialog.findViewById(C0644R.id.text_dialog)).setText(msg);
        ((Button) dialog.findViewById(C0644R.id.btn_dialog)).setOnClickListener(new 5(this, dialog));
        dialog.show();
    }
}
