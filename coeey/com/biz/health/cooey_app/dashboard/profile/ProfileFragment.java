package com.biz.health.cooey_app.dashboard.profile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.RNFetchBlob.RNFetchBlobConst;
import com.biz.health.cooey_app.C0644R;
import com.cooey.common.generators.ServiceGenerator;
import com.cooey.common.services.CareplanService;
import com.cooey.common.vo.User;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \"2\u00020\u0001:\u0002\"#B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u000e\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018J(\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010!\u001a\u00020\u0012H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006$"}, d2 = {"Lcom/biz/health/cooey_app/dashboard/profile/ProfileFragment;", "Landroid/support/v4/app/Fragment;", "()V", "mListener", "Lcom/biz/health/cooey_app/dashboard/profile/ProfileFragment$OnFragmentInteractionListener;", "sessionId", "", "getSessionId", "()Ljava/lang/String;", "setSessionId", "(Ljava/lang/String;)V", "user", "Lcom/cooey/common/vo/User;", "getUser", "()Lcom/cooey/common/vo/User;", "setUser", "(Lcom/cooey/common/vo/User;)V", "getCareplanDetails", "", "onAttach", "context", "Landroid/content/Context;", "onButtonPressed", "uri", "Landroid/net/Uri;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "Companion", "OnFragmentInteractionListener", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: ProfileFragment.kt */
public final class ProfileFragment extends Fragment {
    public static final Companion Companion = new Companion();
    private HashMap _$_findViewCache;
    private OnFragmentInteractionListener mListener;
    @Nullable
    private String sessionId;
    @Nullable
    private User user;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\b"}, d2 = {"Lcom/biz/health/cooey_app/dashboard/profile/ProfileFragment$Companion;", "", "()V", "getInstance", "Lcom/biz/health/cooey_app/dashboard/profile/ProfileFragment;", "userId", "", "sessionId", "app_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: ProfileFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final ProfileFragment getInstance(@NotNull String userId, @NotNull String sessionId) {
            Intrinsics.checkParameterIsNotNull(userId, "userId");
            Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
            ProfileFragment profileFragment = new ProfileFragment();
            profileFragment.getArguments().putString("userId", userId);
            profileFragment.getArguments().putString("sessionId", sessionId);
            return profileFragment;
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/biz/health/cooey_app/dashboard/profile/ProfileFragment$OnFragmentInteractionListener;", "", "onFragmentInteraction", "", "uri", "Landroid/net/Uri;", "app_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: ProfileFragment.kt */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(@NotNull Uri uri);
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = getView();
        if (view == null) {
            return null;
        }
        view = view.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Nullable
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getCareplanDetails();
        if (inflater == null) {
            Intrinsics.throwNpe();
        }
        return inflater.inflate(C0644R.layout.fragment_profile, container, false);
    }

    private final void getCareplanDetails() {
        CareplanService service = (CareplanService) new ServiceGenerator("http://api.cooey.co.in/ehealth/").create(CareplanService.class);
        String str = this.sessionId;
        User user = this.user;
        service.getCareplan(str, user != null ? user.getId() : null).enqueue(new ProfileFragment$getCareplanDetails$1());
    }

    public final void onButtonPressed(@NotNull Uri uri) {
        Intrinsics.checkParameterIsNotNull(uri, RNFetchBlobConst.DATA_ENCODE_URI);
        if (this.mListener != null) {
            OnFragmentInteractionListener onFragmentInteractionListener = this.mListener;
            if (onFragmentInteractionListener == null) {
                Intrinsics.throwNpe();
            }
            onFragmentInteractionListener.onFragmentInteraction(uri);
        }
    }

    public void onAttach(@Nullable Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = (OnFragmentInteractionListener) null;
    }

    @Nullable
    public final User getUser() {
        return this.user;
    }

    public final void setUser(@Nullable User <set-?>) {
        this.user = <set-?>;
    }

    @Nullable
    public final String getSessionId() {
        return this.sessionId;
    }

    public final void setSessionId(@Nullable String <set-?>) {
        this.sessionId = <set-?>;
    }
}
