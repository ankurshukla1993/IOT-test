package com.biz.health.cooey_app.account;

import android.util.Log;
import com.biz.health.cooey_app.C0644R;
import com.cooey.common.services.requests.CreateSessionRequest;
import com.cooey.common.services.requests.CreateSessionRequest.AuthenticationProviderEnum;
import com.cooey.common.services.requests.CreateSessionRequest.TypeEnum;
import com.facebook.AccessToken;
import com.facebook.GraphRequest.GraphJSONObjectCallback;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"com/biz/health/cooey_app/account/CooeyLoginActivity$getFacebookDisplayName$graphRequest$1", "Lcom/facebook/GraphRequest$GraphJSONObjectCallback;", "(Lcom/biz/health/cooey_app/account/CooeyLoginActivity;Lcom/facebook/login/LoginResult;)V", "onCompleted", "", "object", "Lorg/json/JSONObject;", "response", "Lcom/facebook/GraphResponse;", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: CooeyLoginActivity.kt */
public final class CooeyLoginActivity$getFacebookDisplayName$graphRequest$1 implements GraphJSONObjectCallback {
    final /* synthetic */ LoginResult $result;
    final /* synthetic */ CooeyLoginActivity this$0;

    CooeyLoginActivity$getFacebookDisplayName$graphRequest$1(CooeyLoginActivity $outer, LoginResult $captured_local_variable$1) {
        this.this$0 = $outer;
        this.$result = $captured_local_variable$1;
    }

    public void onCompleted(@Nullable JSONObject object, @Nullable GraphResponse response) {
        try {
            JSONObject jSONObject;
            String email;
            String firstName;
            String lastName;
            String gender;
            Profile profile;
            String id;
            String link;
            CreateSessionRequest createSessionRequest;
            LoginResult loginResult;
            AccessToken accessToken;
            String userId;
            Log.i("Response", String.valueOf(response));
            if (response != null) {
                jSONObject = response.getJSONObject();
                if (jSONObject != null) {
                    email = jSONObject.getString("email");
                    if (response != null) {
                        jSONObject = response.getJSONObject();
                        if (jSONObject != null) {
                            firstName = jSONObject.getString("first_name");
                            if (response != null) {
                                jSONObject = response.getJSONObject();
                                if (jSONObject != null) {
                                    lastName = jSONObject.getString("last_name");
                                    if (response != null) {
                                        jSONObject = response.getJSONObject();
                                        if (jSONObject != null) {
                                            gender = jSONObject.getString("gender");
                                            profile = Profile.getCurrentProfile();
                                            id = profile.getId();
                                            link = profile.getLinkUri().toString();
                                            if (!(Profile.getCurrentProfile() == null || Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString() == null)) {
                                                this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                                            }
                                            if (email != null) {
                                                if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                                                    this.this$0.setEmail(email);
                                                }
                                            }
                                            if (firstName != null) {
                                                if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                                                    this.this$0.setFirstName(firstName);
                                                }
                                            }
                                            if (lastName != null) {
                                                if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                                                    this.this$0.setLastName(lastName);
                                                }
                                            }
                                            createSessionRequest = new CreateSessionRequest();
                                            loginResult = this.$result;
                                            if (loginResult != null) {
                                                accessToken = loginResult.getAccessToken();
                                                if (accessToken != null) {
                                                    userId = accessToken.getUserId();
                                                    createSessionRequest.setAuthId(userId);
                                                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                                    createSessionRequest.setType(TypeEnum.PATIENT);
                                                    loginResult = this.$result;
                                                    if (loginResult != null) {
                                                        accessToken = loginResult.getAccessToken();
                                                        if (accessToken != null) {
                                                            userId = accessToken.getToken();
                                                            createSessionRequest.setAuthToken(userId);
                                                            createSessionRequest.setActive(true);
                                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                                        }
                                                    }
                                                    userId = null;
                                                    createSessionRequest.setAuthToken(userId);
                                                    createSessionRequest.setActive(true);
                                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                                }
                                            }
                                            userId = null;
                                            createSessionRequest.setAuthId(userId);
                                            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                            createSessionRequest.setType(TypeEnum.PATIENT);
                                            loginResult = this.$result;
                                            if (loginResult != null) {
                                                accessToken = loginResult.getAccessToken();
                                                if (accessToken != null) {
                                                    userId = accessToken.getToken();
                                                    createSessionRequest.setAuthToken(userId);
                                                    createSessionRequest.setActive(true);
                                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                                }
                                            }
                                            userId = null;
                                            createSessionRequest.setAuthToken(userId);
                                            createSessionRequest.setActive(true);
                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                        }
                                    }
                                    profile = Profile.getCurrentProfile();
                                    id = profile.getId();
                                    link = profile.getLinkUri().toString();
                                    this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                                    if (email != null) {
                                        if (((CharSequence) email).length() <= 0) {
                                        }
                                        if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                                            this.this$0.setEmail(email);
                                        }
                                    }
                                    if (firstName != null) {
                                        if (((CharSequence) firstName).length() <= 0) {
                                        }
                                        if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                                            this.this$0.setFirstName(firstName);
                                        }
                                    }
                                    if (lastName != null) {
                                        if (((CharSequence) lastName).length() <= 0) {
                                        }
                                        if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                                            this.this$0.setLastName(lastName);
                                        }
                                    }
                                    createSessionRequest = new CreateSessionRequest();
                                    loginResult = this.$result;
                                    if (loginResult != null) {
                                        accessToken = loginResult.getAccessToken();
                                        if (accessToken != null) {
                                            userId = accessToken.getUserId();
                                            createSessionRequest.setAuthId(userId);
                                            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                            createSessionRequest.setType(TypeEnum.PATIENT);
                                            loginResult = this.$result;
                                            if (loginResult != null) {
                                                accessToken = loginResult.getAccessToken();
                                                if (accessToken != null) {
                                                    userId = accessToken.getToken();
                                                    createSessionRequest.setAuthToken(userId);
                                                    createSessionRequest.setActive(true);
                                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                                }
                                            }
                                            userId = null;
                                            createSessionRequest.setAuthToken(userId);
                                            createSessionRequest.setActive(true);
                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                        }
                                    }
                                    userId = null;
                                    createSessionRequest.setAuthId(userId);
                                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                    createSessionRequest.setType(TypeEnum.PATIENT);
                                    loginResult = this.$result;
                                    if (loginResult != null) {
                                        accessToken = loginResult.getAccessToken();
                                        if (accessToken != null) {
                                            userId = accessToken.getToken();
                                            createSessionRequest.setAuthToken(userId);
                                            createSessionRequest.setActive(true);
                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                        }
                                    }
                                    userId = null;
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            lastName = null;
                            if (response != null) {
                                jSONObject = response.getJSONObject();
                                if (jSONObject != null) {
                                    gender = jSONObject.getString("gender");
                                    profile = Profile.getCurrentProfile();
                                    id = profile.getId();
                                    link = profile.getLinkUri().toString();
                                    this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                                    if (email != null) {
                                        if (((CharSequence) email).length() <= 0) {
                                        }
                                        if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                                            this.this$0.setEmail(email);
                                        }
                                    }
                                    if (firstName != null) {
                                        if (((CharSequence) firstName).length() <= 0) {
                                        }
                                        if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                                            this.this$0.setFirstName(firstName);
                                        }
                                    }
                                    if (lastName != null) {
                                        if (((CharSequence) lastName).length() <= 0) {
                                        }
                                        if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                                            this.this$0.setLastName(lastName);
                                        }
                                    }
                                    createSessionRequest = new CreateSessionRequest();
                                    loginResult = this.$result;
                                    if (loginResult != null) {
                                        accessToken = loginResult.getAccessToken();
                                        if (accessToken != null) {
                                            userId = accessToken.getUserId();
                                            createSessionRequest.setAuthId(userId);
                                            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                            createSessionRequest.setType(TypeEnum.PATIENT);
                                            loginResult = this.$result;
                                            if (loginResult != null) {
                                                accessToken = loginResult.getAccessToken();
                                                if (accessToken != null) {
                                                    userId = accessToken.getToken();
                                                    createSessionRequest.setAuthToken(userId);
                                                    createSessionRequest.setActive(true);
                                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                                }
                                            }
                                            userId = null;
                                            createSessionRequest.setAuthToken(userId);
                                            createSessionRequest.setActive(true);
                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                        }
                                    }
                                    userId = null;
                                    createSessionRequest.setAuthId(userId);
                                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                    createSessionRequest.setType(TypeEnum.PATIENT);
                                    loginResult = this.$result;
                                    if (loginResult != null) {
                                        accessToken = loginResult.getAccessToken();
                                        if (accessToken != null) {
                                            userId = accessToken.getToken();
                                            createSessionRequest.setAuthToken(userId);
                                            createSessionRequest.setActive(true);
                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                        }
                                    }
                                    userId = null;
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            profile = Profile.getCurrentProfile();
                            id = profile.getId();
                            link = profile.getLinkUri().toString();
                            this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                            if (email != null) {
                                if (((CharSequence) email).length() <= 0) {
                                }
                                if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setEmail(email);
                                }
                            }
                            if (firstName != null) {
                                if (((CharSequence) firstName).length() <= 0) {
                                }
                                if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setFirstName(firstName);
                                }
                            }
                            if (lastName != null) {
                                if (((CharSequence) lastName).length() <= 0) {
                                }
                                if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setLastName(lastName);
                                }
                            }
                            createSessionRequest = new CreateSessionRequest();
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getUserId();
                                    createSessionRequest.setAuthId(userId);
                                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                    createSessionRequest.setType(TypeEnum.PATIENT);
                                    loginResult = this.$result;
                                    if (loginResult != null) {
                                        accessToken = loginResult.getAccessToken();
                                        if (accessToken != null) {
                                            userId = accessToken.getToken();
                                            createSessionRequest.setAuthToken(userId);
                                            createSessionRequest.setActive(true);
                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                        }
                                    }
                                    userId = null;
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthId(userId);
                            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                            createSessionRequest.setType(TypeEnum.PATIENT);
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getToken();
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthToken(userId);
                            createSessionRequest.setActive(true);
                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                            this.this$0.cooeySignIn(createSessionRequest, true);
                        }
                    }
                    firstName = null;
                    if (response != null) {
                        jSONObject = response.getJSONObject();
                        if (jSONObject != null) {
                            lastName = jSONObject.getString("last_name");
                            if (response != null) {
                                jSONObject = response.getJSONObject();
                                if (jSONObject != null) {
                                    gender = jSONObject.getString("gender");
                                    profile = Profile.getCurrentProfile();
                                    id = profile.getId();
                                    link = profile.getLinkUri().toString();
                                    this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                                    if (email != null) {
                                        if (((CharSequence) email).length() <= 0) {
                                        }
                                        if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                                            this.this$0.setEmail(email);
                                        }
                                    }
                                    if (firstName != null) {
                                        if (((CharSequence) firstName).length() <= 0) {
                                        }
                                        if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                                            this.this$0.setFirstName(firstName);
                                        }
                                    }
                                    if (lastName != null) {
                                        if (((CharSequence) lastName).length() <= 0) {
                                        }
                                        if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                                            this.this$0.setLastName(lastName);
                                        }
                                    }
                                    createSessionRequest = new CreateSessionRequest();
                                    loginResult = this.$result;
                                    if (loginResult != null) {
                                        accessToken = loginResult.getAccessToken();
                                        if (accessToken != null) {
                                            userId = accessToken.getUserId();
                                            createSessionRequest.setAuthId(userId);
                                            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                            createSessionRequest.setType(TypeEnum.PATIENT);
                                            loginResult = this.$result;
                                            if (loginResult != null) {
                                                accessToken = loginResult.getAccessToken();
                                                if (accessToken != null) {
                                                    userId = accessToken.getToken();
                                                    createSessionRequest.setAuthToken(userId);
                                                    createSessionRequest.setActive(true);
                                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                                }
                                            }
                                            userId = null;
                                            createSessionRequest.setAuthToken(userId);
                                            createSessionRequest.setActive(true);
                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                        }
                                    }
                                    userId = null;
                                    createSessionRequest.setAuthId(userId);
                                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                    createSessionRequest.setType(TypeEnum.PATIENT);
                                    loginResult = this.$result;
                                    if (loginResult != null) {
                                        accessToken = loginResult.getAccessToken();
                                        if (accessToken != null) {
                                            userId = accessToken.getToken();
                                            createSessionRequest.setAuthToken(userId);
                                            createSessionRequest.setActive(true);
                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                        }
                                    }
                                    userId = null;
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            profile = Profile.getCurrentProfile();
                            id = profile.getId();
                            link = profile.getLinkUri().toString();
                            this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                            if (email != null) {
                                if (((CharSequence) email).length() <= 0) {
                                }
                                if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setEmail(email);
                                }
                            }
                            if (firstName != null) {
                                if (((CharSequence) firstName).length() <= 0) {
                                }
                                if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setFirstName(firstName);
                                }
                            }
                            if (lastName != null) {
                                if (((CharSequence) lastName).length() <= 0) {
                                }
                                if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setLastName(lastName);
                                }
                            }
                            createSessionRequest = new CreateSessionRequest();
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getUserId();
                                    createSessionRequest.setAuthId(userId);
                                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                    createSessionRequest.setType(TypeEnum.PATIENT);
                                    loginResult = this.$result;
                                    if (loginResult != null) {
                                        accessToken = loginResult.getAccessToken();
                                        if (accessToken != null) {
                                            userId = accessToken.getToken();
                                            createSessionRequest.setAuthToken(userId);
                                            createSessionRequest.setActive(true);
                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                        }
                                    }
                                    userId = null;
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthId(userId);
                            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                            createSessionRequest.setType(TypeEnum.PATIENT);
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getToken();
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthToken(userId);
                            createSessionRequest.setActive(true);
                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                            this.this$0.cooeySignIn(createSessionRequest, true);
                        }
                    }
                    lastName = null;
                    if (response != null) {
                        jSONObject = response.getJSONObject();
                        if (jSONObject != null) {
                            gender = jSONObject.getString("gender");
                            profile = Profile.getCurrentProfile();
                            id = profile.getId();
                            link = profile.getLinkUri().toString();
                            this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                            if (email != null) {
                                if (((CharSequence) email).length() <= 0) {
                                }
                                if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setEmail(email);
                                }
                            }
                            if (firstName != null) {
                                if (((CharSequence) firstName).length() <= 0) {
                                }
                                if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setFirstName(firstName);
                                }
                            }
                            if (lastName != null) {
                                if (((CharSequence) lastName).length() <= 0) {
                                }
                                if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setLastName(lastName);
                                }
                            }
                            createSessionRequest = new CreateSessionRequest();
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getUserId();
                                    createSessionRequest.setAuthId(userId);
                                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                    createSessionRequest.setType(TypeEnum.PATIENT);
                                    loginResult = this.$result;
                                    if (loginResult != null) {
                                        accessToken = loginResult.getAccessToken();
                                        if (accessToken != null) {
                                            userId = accessToken.getToken();
                                            createSessionRequest.setAuthToken(userId);
                                            createSessionRequest.setActive(true);
                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                        }
                                    }
                                    userId = null;
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthId(userId);
                            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                            createSessionRequest.setType(TypeEnum.PATIENT);
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getToken();
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthToken(userId);
                            createSessionRequest.setActive(true);
                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                            this.this$0.cooeySignIn(createSessionRequest, true);
                        }
                    }
                    profile = Profile.getCurrentProfile();
                    id = profile.getId();
                    link = profile.getLinkUri().toString();
                    this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                    if (email != null) {
                        if (((CharSequence) email).length() <= 0) {
                        }
                        if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                            this.this$0.setEmail(email);
                        }
                    }
                    if (firstName != null) {
                        if (((CharSequence) firstName).length() <= 0) {
                        }
                        if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                            this.this$0.setFirstName(firstName);
                        }
                    }
                    if (lastName != null) {
                        if (((CharSequence) lastName).length() <= 0) {
                        }
                        if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                            this.this$0.setLastName(lastName);
                        }
                    }
                    createSessionRequest = new CreateSessionRequest();
                    loginResult = this.$result;
                    if (loginResult != null) {
                        accessToken = loginResult.getAccessToken();
                        if (accessToken != null) {
                            userId = accessToken.getUserId();
                            createSessionRequest.setAuthId(userId);
                            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                            createSessionRequest.setType(TypeEnum.PATIENT);
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getToken();
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthToken(userId);
                            createSessionRequest.setActive(true);
                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                            this.this$0.cooeySignIn(createSessionRequest, true);
                        }
                    }
                    userId = null;
                    createSessionRequest.setAuthId(userId);
                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                    createSessionRequest.setType(TypeEnum.PATIENT);
                    loginResult = this.$result;
                    if (loginResult != null) {
                        accessToken = loginResult.getAccessToken();
                        if (accessToken != null) {
                            userId = accessToken.getToken();
                            createSessionRequest.setAuthToken(userId);
                            createSessionRequest.setActive(true);
                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                            this.this$0.cooeySignIn(createSessionRequest, true);
                        }
                    }
                    userId = null;
                    createSessionRequest.setAuthToken(userId);
                    createSessionRequest.setActive(true);
                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                    this.this$0.cooeySignIn(createSessionRequest, true);
                }
            }
            email = null;
            if (response != null) {
                jSONObject = response.getJSONObject();
                if (jSONObject != null) {
                    firstName = jSONObject.getString("first_name");
                    if (response != null) {
                        jSONObject = response.getJSONObject();
                        if (jSONObject != null) {
                            lastName = jSONObject.getString("last_name");
                            if (response != null) {
                                jSONObject = response.getJSONObject();
                                if (jSONObject != null) {
                                    gender = jSONObject.getString("gender");
                                    profile = Profile.getCurrentProfile();
                                    id = profile.getId();
                                    link = profile.getLinkUri().toString();
                                    this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                                    if (email != null) {
                                        if (((CharSequence) email).length() <= 0) {
                                        }
                                        if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                                            this.this$0.setEmail(email);
                                        }
                                    }
                                    if (firstName != null) {
                                        if (((CharSequence) firstName).length() <= 0) {
                                        }
                                        if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                                            this.this$0.setFirstName(firstName);
                                        }
                                    }
                                    if (lastName != null) {
                                        if (((CharSequence) lastName).length() <= 0) {
                                        }
                                        if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                                            this.this$0.setLastName(lastName);
                                        }
                                    }
                                    createSessionRequest = new CreateSessionRequest();
                                    loginResult = this.$result;
                                    if (loginResult != null) {
                                        accessToken = loginResult.getAccessToken();
                                        if (accessToken != null) {
                                            userId = accessToken.getUserId();
                                            createSessionRequest.setAuthId(userId);
                                            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                            createSessionRequest.setType(TypeEnum.PATIENT);
                                            loginResult = this.$result;
                                            if (loginResult != null) {
                                                accessToken = loginResult.getAccessToken();
                                                if (accessToken != null) {
                                                    userId = accessToken.getToken();
                                                    createSessionRequest.setAuthToken(userId);
                                                    createSessionRequest.setActive(true);
                                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                                }
                                            }
                                            userId = null;
                                            createSessionRequest.setAuthToken(userId);
                                            createSessionRequest.setActive(true);
                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                        }
                                    }
                                    userId = null;
                                    createSessionRequest.setAuthId(userId);
                                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                    createSessionRequest.setType(TypeEnum.PATIENT);
                                    loginResult = this.$result;
                                    if (loginResult != null) {
                                        accessToken = loginResult.getAccessToken();
                                        if (accessToken != null) {
                                            userId = accessToken.getToken();
                                            createSessionRequest.setAuthToken(userId);
                                            createSessionRequest.setActive(true);
                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                        }
                                    }
                                    userId = null;
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            profile = Profile.getCurrentProfile();
                            id = profile.getId();
                            link = profile.getLinkUri().toString();
                            this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                            if (email != null) {
                                if (((CharSequence) email).length() <= 0) {
                                }
                                if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setEmail(email);
                                }
                            }
                            if (firstName != null) {
                                if (((CharSequence) firstName).length() <= 0) {
                                }
                                if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setFirstName(firstName);
                                }
                            }
                            if (lastName != null) {
                                if (((CharSequence) lastName).length() <= 0) {
                                }
                                if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setLastName(lastName);
                                }
                            }
                            createSessionRequest = new CreateSessionRequest();
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getUserId();
                                    createSessionRequest.setAuthId(userId);
                                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                    createSessionRequest.setType(TypeEnum.PATIENT);
                                    loginResult = this.$result;
                                    if (loginResult != null) {
                                        accessToken = loginResult.getAccessToken();
                                        if (accessToken != null) {
                                            userId = accessToken.getToken();
                                            createSessionRequest.setAuthToken(userId);
                                            createSessionRequest.setActive(true);
                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                        }
                                    }
                                    userId = null;
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthId(userId);
                            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                            createSessionRequest.setType(TypeEnum.PATIENT);
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getToken();
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthToken(userId);
                            createSessionRequest.setActive(true);
                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                            this.this$0.cooeySignIn(createSessionRequest, true);
                        }
                    }
                    lastName = null;
                    if (response != null) {
                        jSONObject = response.getJSONObject();
                        if (jSONObject != null) {
                            gender = jSONObject.getString("gender");
                            profile = Profile.getCurrentProfile();
                            id = profile.getId();
                            link = profile.getLinkUri().toString();
                            this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                            if (email != null) {
                                if (((CharSequence) email).length() <= 0) {
                                }
                                if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setEmail(email);
                                }
                            }
                            if (firstName != null) {
                                if (((CharSequence) firstName).length() <= 0) {
                                }
                                if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setFirstName(firstName);
                                }
                            }
                            if (lastName != null) {
                                if (((CharSequence) lastName).length() <= 0) {
                                }
                                if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setLastName(lastName);
                                }
                            }
                            createSessionRequest = new CreateSessionRequest();
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getUserId();
                                    createSessionRequest.setAuthId(userId);
                                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                    createSessionRequest.setType(TypeEnum.PATIENT);
                                    loginResult = this.$result;
                                    if (loginResult != null) {
                                        accessToken = loginResult.getAccessToken();
                                        if (accessToken != null) {
                                            userId = accessToken.getToken();
                                            createSessionRequest.setAuthToken(userId);
                                            createSessionRequest.setActive(true);
                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                        }
                                    }
                                    userId = null;
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthId(userId);
                            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                            createSessionRequest.setType(TypeEnum.PATIENT);
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getToken();
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthToken(userId);
                            createSessionRequest.setActive(true);
                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                            this.this$0.cooeySignIn(createSessionRequest, true);
                        }
                    }
                    profile = Profile.getCurrentProfile();
                    id = profile.getId();
                    link = profile.getLinkUri().toString();
                    this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                    if (email != null) {
                        if (((CharSequence) email).length() <= 0) {
                        }
                        if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                            this.this$0.setEmail(email);
                        }
                    }
                    if (firstName != null) {
                        if (((CharSequence) firstName).length() <= 0) {
                        }
                        if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                            this.this$0.setFirstName(firstName);
                        }
                    }
                    if (lastName != null) {
                        if (((CharSequence) lastName).length() <= 0) {
                        }
                        if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                            this.this$0.setLastName(lastName);
                        }
                    }
                    createSessionRequest = new CreateSessionRequest();
                    loginResult = this.$result;
                    if (loginResult != null) {
                        accessToken = loginResult.getAccessToken();
                        if (accessToken != null) {
                            userId = accessToken.getUserId();
                            createSessionRequest.setAuthId(userId);
                            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                            createSessionRequest.setType(TypeEnum.PATIENT);
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getToken();
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthToken(userId);
                            createSessionRequest.setActive(true);
                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                            this.this$0.cooeySignIn(createSessionRequest, true);
                        }
                    }
                    userId = null;
                    createSessionRequest.setAuthId(userId);
                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                    createSessionRequest.setType(TypeEnum.PATIENT);
                    loginResult = this.$result;
                    if (loginResult != null) {
                        accessToken = loginResult.getAccessToken();
                        if (accessToken != null) {
                            userId = accessToken.getToken();
                            createSessionRequest.setAuthToken(userId);
                            createSessionRequest.setActive(true);
                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                            this.this$0.cooeySignIn(createSessionRequest, true);
                        }
                    }
                    userId = null;
                    createSessionRequest.setAuthToken(userId);
                    createSessionRequest.setActive(true);
                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                    this.this$0.cooeySignIn(createSessionRequest, true);
                }
            }
            firstName = null;
            if (response != null) {
                jSONObject = response.getJSONObject();
                if (jSONObject != null) {
                    lastName = jSONObject.getString("last_name");
                    if (response != null) {
                        jSONObject = response.getJSONObject();
                        if (jSONObject != null) {
                            gender = jSONObject.getString("gender");
                            profile = Profile.getCurrentProfile();
                            id = profile.getId();
                            link = profile.getLinkUri().toString();
                            this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                            if (email != null) {
                                if (((CharSequence) email).length() <= 0) {
                                }
                                if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setEmail(email);
                                }
                            }
                            if (firstName != null) {
                                if (((CharSequence) firstName).length() <= 0) {
                                }
                                if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setFirstName(firstName);
                                }
                            }
                            if (lastName != null) {
                                if (((CharSequence) lastName).length() <= 0) {
                                }
                                if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                                    this.this$0.setLastName(lastName);
                                }
                            }
                            createSessionRequest = new CreateSessionRequest();
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getUserId();
                                    createSessionRequest.setAuthId(userId);
                                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                                    createSessionRequest.setType(TypeEnum.PATIENT);
                                    loginResult = this.$result;
                                    if (loginResult != null) {
                                        accessToken = loginResult.getAccessToken();
                                        if (accessToken != null) {
                                            userId = accessToken.getToken();
                                            createSessionRequest.setAuthToken(userId);
                                            createSessionRequest.setActive(true);
                                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                            this.this$0.cooeySignIn(createSessionRequest, true);
                                        }
                                    }
                                    userId = null;
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthId(userId);
                            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                            createSessionRequest.setType(TypeEnum.PATIENT);
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getToken();
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthToken(userId);
                            createSessionRequest.setActive(true);
                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                            this.this$0.cooeySignIn(createSessionRequest, true);
                        }
                    }
                    profile = Profile.getCurrentProfile();
                    id = profile.getId();
                    link = profile.getLinkUri().toString();
                    this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                    if (email != null) {
                        if (((CharSequence) email).length() <= 0) {
                        }
                        if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                            this.this$0.setEmail(email);
                        }
                    }
                    if (firstName != null) {
                        if (((CharSequence) firstName).length() <= 0) {
                        }
                        if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                            this.this$0.setFirstName(firstName);
                        }
                    }
                    if (lastName != null) {
                        if (((CharSequence) lastName).length() <= 0) {
                        }
                        if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                            this.this$0.setLastName(lastName);
                        }
                    }
                    createSessionRequest = new CreateSessionRequest();
                    loginResult = this.$result;
                    if (loginResult != null) {
                        accessToken = loginResult.getAccessToken();
                        if (accessToken != null) {
                            userId = accessToken.getUserId();
                            createSessionRequest.setAuthId(userId);
                            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                            createSessionRequest.setType(TypeEnum.PATIENT);
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getToken();
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthToken(userId);
                            createSessionRequest.setActive(true);
                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                            this.this$0.cooeySignIn(createSessionRequest, true);
                        }
                    }
                    userId = null;
                    createSessionRequest.setAuthId(userId);
                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                    createSessionRequest.setType(TypeEnum.PATIENT);
                    loginResult = this.$result;
                    if (loginResult != null) {
                        accessToken = loginResult.getAccessToken();
                        if (accessToken != null) {
                            userId = accessToken.getToken();
                            createSessionRequest.setAuthToken(userId);
                            createSessionRequest.setActive(true);
                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                            this.this$0.cooeySignIn(createSessionRequest, true);
                        }
                    }
                    userId = null;
                    createSessionRequest.setAuthToken(userId);
                    createSessionRequest.setActive(true);
                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                    this.this$0.cooeySignIn(createSessionRequest, true);
                }
            }
            lastName = null;
            if (response != null) {
                jSONObject = response.getJSONObject();
                if (jSONObject != null) {
                    gender = jSONObject.getString("gender");
                    profile = Profile.getCurrentProfile();
                    id = profile.getId();
                    link = profile.getLinkUri().toString();
                    this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                    if (email != null) {
                        if (((CharSequence) email).length() <= 0) {
                        }
                        if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                            this.this$0.setEmail(email);
                        }
                    }
                    if (firstName != null) {
                        if (((CharSequence) firstName).length() <= 0) {
                        }
                        if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                            this.this$0.setFirstName(firstName);
                        }
                    }
                    if (lastName != null) {
                        if (((CharSequence) lastName).length() <= 0) {
                        }
                        if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                            this.this$0.setLastName(lastName);
                        }
                    }
                    createSessionRequest = new CreateSessionRequest();
                    loginResult = this.$result;
                    if (loginResult != null) {
                        accessToken = loginResult.getAccessToken();
                        if (accessToken != null) {
                            userId = accessToken.getUserId();
                            createSessionRequest.setAuthId(userId);
                            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                            createSessionRequest.setType(TypeEnum.PATIENT);
                            loginResult = this.$result;
                            if (loginResult != null) {
                                accessToken = loginResult.getAccessToken();
                                if (accessToken != null) {
                                    userId = accessToken.getToken();
                                    createSessionRequest.setAuthToken(userId);
                                    createSessionRequest.setActive(true);
                                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                                    this.this$0.cooeySignIn(createSessionRequest, true);
                                }
                            }
                            userId = null;
                            createSessionRequest.setAuthToken(userId);
                            createSessionRequest.setActive(true);
                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                            this.this$0.cooeySignIn(createSessionRequest, true);
                        }
                    }
                    userId = null;
                    createSessionRequest.setAuthId(userId);
                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                    createSessionRequest.setType(TypeEnum.PATIENT);
                    loginResult = this.$result;
                    if (loginResult != null) {
                        accessToken = loginResult.getAccessToken();
                        if (accessToken != null) {
                            userId = accessToken.getToken();
                            createSessionRequest.setAuthToken(userId);
                            createSessionRequest.setActive(true);
                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                            this.this$0.cooeySignIn(createSessionRequest, true);
                        }
                    }
                    userId = null;
                    createSessionRequest.setAuthToken(userId);
                    createSessionRequest.setActive(true);
                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                    this.this$0.cooeySignIn(createSessionRequest, true);
                }
            }
            profile = Profile.getCurrentProfile();
            id = profile.getId();
            link = profile.getLinkUri().toString();
            this.this$0.setProfilePic(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
            if (email != null) {
                if (((CharSequence) email).length() <= 0) {
                }
                if ((((CharSequence) email).length() <= 0 ? 1 : null) != null) {
                    this.this$0.setEmail(email);
                }
            }
            if (firstName != null) {
                if (((CharSequence) firstName).length() <= 0) {
                }
                if ((((CharSequence) firstName).length() <= 0 ? 1 : null) != null) {
                    this.this$0.setFirstName(firstName);
                }
            }
            if (lastName != null) {
                if (((CharSequence) lastName).length() <= 0) {
                }
                if ((((CharSequence) lastName).length() <= 0 ? 1 : null) != null) {
                    this.this$0.setLastName(lastName);
                }
            }
            createSessionRequest = new CreateSessionRequest();
            loginResult = this.$result;
            if (loginResult != null) {
                accessToken = loginResult.getAccessToken();
                if (accessToken != null) {
                    userId = accessToken.getUserId();
                    createSessionRequest.setAuthId(userId);
                    createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
                    createSessionRequest.setType(TypeEnum.PATIENT);
                    loginResult = this.$result;
                    if (loginResult != null) {
                        accessToken = loginResult.getAccessToken();
                        if (accessToken != null) {
                            userId = accessToken.getToken();
                            createSessionRequest.setAuthToken(userId);
                            createSessionRequest.setActive(true);
                            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                            this.this$0.cooeySignIn(createSessionRequest, true);
                        }
                    }
                    userId = null;
                    createSessionRequest.setAuthToken(userId);
                    createSessionRequest.setActive(true);
                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                    this.this$0.cooeySignIn(createSessionRequest, true);
                }
            }
            userId = null;
            createSessionRequest.setAuthId(userId);
            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.FACEBOOK);
            createSessionRequest.setType(TypeEnum.PATIENT);
            loginResult = this.$result;
            if (loginResult != null) {
                accessToken = loginResult.getAccessToken();
                if (accessToken != null) {
                    userId = accessToken.getToken();
                    createSessionRequest.setAuthToken(userId);
                    createSessionRequest.setActive(true);
                    createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
                    this.this$0.cooeySignIn(createSessionRequest, true);
                }
            }
            userId = null;
            createSessionRequest.setAuthToken(userId);
            createSessionRequest.setActive(true);
            createSessionRequest.setTenantId(this.this$0.getString(C0644R.string.tenant_id));
            this.this$0.cooeySignIn(createSessionRequest, true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
