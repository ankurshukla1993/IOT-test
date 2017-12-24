package com.biz.health.cooey_app.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.MainActivity;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.services.ApiClient;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Channel;
import com.cooey.common.vo.ChannelType;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.SettingsConfig;
import com.cooey.common.vo.User;
import com.google.firebase.iid.FirebaseInstanceId;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExternalIdLogin extends AppCompatActivity {
    ApiClient apiClient = new ApiClient(this, "http://api.cooey.co.in/ehealth/");

    class C06701 implements OnClickListener {
        C06701() {
        }

        public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", 0).setAction("Action", null).show();
        }
    }

    class C06733 implements Callback<SettingsConfig> {
        C06733() {
        }

        public void onResponse(Call<SettingsConfig> call, Response<SettingsConfig> response) {
            if (response.body() != null) {
                ((SettingsConfig) response.body()).setIscareplanEnabled(Boolean.valueOf(true));
                new PreferenceStore().setPartnerConfig(ExternalIdLogin.this, (SettingsConfig) response.body());
            }
        }

        public void onFailure(Call<SettingsConfig> call, Throwable t) {
            t.printStackTrace();
        }
    }

    class C06755 implements Callback<Channel> {
        C06755() {
        }

        public void onResponse(Call<Channel> call, Response<Channel> response) {
        }

        public void onFailure(Call<Channel> call, Throwable t) {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0644R.layout.activity_external_id_login);
        setSupportActionBar((Toolbar) findViewById(C0644R.id.toolbar));
        ((FloatingActionButton) findViewById(C0644R.id.fab)).setOnClickListener(new C06701());
        final EditText externalIdEditText = (EditText) findViewById(C0644R.id.external_id_edit_text);
        ((Button) findViewById(C0644R.id.external_login_Button)).setOnClickListener(new OnClickListener() {

            class C06711 implements Callback<Session> {
                C06711() {
                }

                public void onResponse(Call<Session> call, Response<Session> response) {
                    try {
                        new PreferenceStore().setSession(ExternalIdLogin.this, (Session) response.body());
                        ExternalIdLogin.this.getPartnerConfig(response);
                        ExternalIdLogin.this.getUser(response);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                public void onFailure(Call<Session> call, Throwable t) {
                    String data = "";
                }
            }

            public void onClick(View view) {
                ExternalIdLogin.this.apiClient.getSessionsService().getSessionForExternalId(externalIdEditText.getText().toString()).enqueue(new C06711());
            }
        });
    }

    private void getPartnerConfig(Response<Session> response) {
        String token = null;
        if (!(response.body() == null || ((Session) response.body()).getId() == null)) {
            token = ((Session) response.body()).getId();
        }
        if (token != null) {
            this.apiClient.getPartnerConfigService().getPartnerConfig(token, ((Session) response.body()).getTenantId(), CTConstants.PATIENTTYPE).enqueue(new C06733());
        }
    }

    private void getUser(Response<Session> response) {
        final Session session = (Session) response.body();
        this.apiClient.getUsersService().get_0(session.getUserId(), session.getId()).enqueue(new Callback<User>() {
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null && response.isSuccessful()) {
                    new PreferenceStore().setUser(ExternalIdLogin.this, (User) response.body());
                    ExternalIdLogin.this.registerFCMToken(session);
                    ExternalIdLogin.this.startActivity(new Intent(ExternalIdLogin.this, MainActivity.class));
                    ExternalIdLogin.this.finish();
                }
            }

            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    private void registerFCMToken(Session session) {
        String token = FirebaseInstanceId.getInstance().getToken();
        ApiClient apiClient = new ApiClient(this, "http://api.cooey.co.in/ehealth/");
        if (session != null && session.getId() != null && token != null) {
            String patientId = session.getUserId();
            Channel channel = new Channel();
            channel.setSource("ANDROID");
            channel.setSourceId(token);
            channel.setType(ChannelType.NOTIFICATION);
            channel.setUserId(patientId);
            apiClient.getChannelsService().create(channel).enqueue(new C06755());
        }
    }
}
