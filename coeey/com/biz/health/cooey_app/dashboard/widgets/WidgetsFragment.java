package com.biz.health.cooey_app.dashboard.widgets;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.databinding.FragmentWidgetBinding;
import com.cooey.android.vitals.repositories.VitalBlueprintsRepository;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.common.realm_store.VitalStore;
import com.cooey.common.vo.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.realm.RealmResults;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 72\u00020\u00012\u00020\u0002:\u000278B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\u0012\u0010-\u001a\u00020*2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J(\u00100\u001a\u0004\u0018\u0001012\b\u00102\u001a\u0004\u0018\u0001032\b\u00104\u001a\u0004\u0018\u0001052\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00106\u001a\u00020*H\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010#\u001a\u0004\u0018\u00010$X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u00069"}, d2 = {"Lcom/biz/health/cooey_app/dashboard/widgets/WidgetsFragment;", "Landroid/support/v4/app/Fragment;", "Landroid/arch/lifecycle/LifecycleOwner;", "()V", "binding", "Lcom/biz/health/cooey_app/databinding/FragmentWidgetBinding;", "getBinding", "()Lcom/biz/health/cooey_app/databinding/FragmentWidgetBinding;", "setBinding", "(Lcom/biz/health/cooey_app/databinding/FragmentWidgetBinding;)V", "mListener", "Lcom/biz/health/cooey_app/dashboard/widgets/WidgetsFragment$OnFragmentInteractionListener;", "sessionId", "", "user", "Lcom/cooey/common/vo/User;", "userParam", "vitalBlueprintRepository", "Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "getVitalBlueprintRepository", "()Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "setVitalBlueprintRepository", "(Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;)V", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "widgetRecyclerAdapter", "Lcom/biz/health/cooey_app/dashboard/widgets/WidgetRecyclerAdapter;", "getWidgetRecyclerAdapter", "()Lcom/biz/health/cooey_app/dashboard/widgets/WidgetRecyclerAdapter;", "setWidgetRecyclerAdapter", "(Lcom/biz/health/cooey_app/dashboard/widgets/WidgetRecyclerAdapter;)V", "widgetsViewModel", "Lcom/biz/health/cooey_app/dashboard/widgets/WidgetsViewModel;", "getWidgetsViewModel", "()Lcom/biz/health/cooey_app/dashboard/widgets/WidgetsViewModel;", "setWidgetsViewModel", "(Lcom/biz/health/cooey_app/dashboard/widgets/WidgetsViewModel;)V", "onAttach", "", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDetach", "Companion", "OnFragmentInteractionListener", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: WidgetsFragment.kt */
public final class WidgetsFragment extends Fragment implements LifecycleOwner {
    private static final String ARG_SESSION_ID = ARG_SESSION_ID;
    private static final String ARG_USER = ARG_USER;
    public static final Companion Companion = new Companion();
    private HashMap _$_findViewCache;
    @Nullable
    private FragmentWidgetBinding binding;
    private OnFragmentInteractionListener mListener;
    private String sessionId;
    private User user;
    private String userParam;
    @Nullable
    private VitalBlueprintsRepository vitalBlueprintRepository;
    @Nullable
    private VitalRepository vitalRepository;
    @Nullable
    private WidgetRecyclerAdapter widgetRecyclerAdapter;
    @Nullable
    private WidgetsViewModel widgetsViewModel;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/biz/health/cooey_app/dashboard/widgets/WidgetsFragment$Companion;", "", "()V", "ARG_SESSION_ID", "", "getARG_SESSION_ID", "()Ljava/lang/String;", "ARG_USER", "getARG_USER", "newInstance", "Lcom/biz/health/cooey_app/dashboard/widgets/WidgetsFragment;", "user", "Lcom/cooey/common/vo/User;", "sessionId", "app_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: WidgetsFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        private final String getARG_USER() {
            return WidgetsFragment.ARG_USER;
        }

        private final String getARG_SESSION_ID() {
            return WidgetsFragment.ARG_SESSION_ID;
        }

        @NotNull
        public final WidgetsFragment newInstance(@NotNull User user, @NotNull String sessionId) {
            Intrinsics.checkParameterIsNotNull(user, "user");
            Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
            WidgetsFragment fragment = new WidgetsFragment();
            Bundle args = new Bundle();
            Gson gson = new GsonBuilder().create();
            args.putString(getARG_USER(), User.toJson(user));
            args.putString(getARG_SESSION_ID(), sessionId);
            fragment.setArguments(args);
            return fragment;
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/biz/health/cooey_app/dashboard/widgets/WidgetsFragment$OnFragmentInteractionListener;", "", "onFragmentInteraction", "", "uri", "Landroid/net/Uri;", "app_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: WidgetsFragment.kt */
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
    public final VitalRepository getVitalRepository() {
        return this.vitalRepository;
    }

    public final void setVitalRepository(@Nullable VitalRepository <set-?>) {
        this.vitalRepository = <set-?>;
    }

    @Nullable
    public final VitalBlueprintsRepository getVitalBlueprintRepository() {
        return this.vitalBlueprintRepository;
    }

    public final void setVitalBlueprintRepository(@Nullable VitalBlueprintsRepository <set-?>) {
        this.vitalBlueprintRepository = <set-?>;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.userParam = getArguments().getString(Companion.getARG_USER());
            this.user = User.fromJson(this.userParam);
            this.sessionId = getArguments().getString(Companion.getARG_SESSION_ID());
        }
    }

    @Nullable
    public final FragmentWidgetBinding getBinding() {
        return this.binding;
    }

    public final void setBinding(@Nullable FragmentWidgetBinding <set-?>) {
        this.binding = <set-?>;
    }

    @Nullable
    public final WidgetsViewModel getWidgetsViewModel() {
        return this.widgetsViewModel;
    }

    public final void setWidgetsViewModel(@Nullable WidgetsViewModel <set-?>) {
        this.widgetsViewModel = <set-?>;
    }

    @Nullable
    public final WidgetRecyclerAdapter getWidgetRecyclerAdapter() {
        return this.widgetRecyclerAdapter;
    }

    public final void setWidgetRecyclerAdapter(@Nullable WidgetRecyclerAdapter <set-?>) {
        this.widgetRecyclerAdapter = <set-?>;
    }

    @Nullable
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflater == null) {
            Intrinsics.throwNpe();
        }
        this.binding = (FragmentWidgetBinding) DataBindingUtil.inflate(inflater, C0644R.layout.fragment_widget, container, false);
        this.widgetsViewModel = new WidgetsViewModel(getContext(), this.user);
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        LayoutInflater from = LayoutInflater.from(getContext());
        Intrinsics.checkExpressionValueIsNotNull(from, "LayoutInflater.from(context)");
        User user = this.user;
        if (user == null) {
            Intrinsics.throwNpe();
        }
        String str = this.sessionId;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        LifecycleOwner lifecycleOwner = this;
        VitalRepository vitalRepository = this.vitalRepository;
        if (vitalRepository == null) {
            Intrinsics.throwNpe();
        }
        VitalBlueprintsRepository vitalBlueprintsRepository = this.vitalBlueprintRepository;
        if (vitalBlueprintsRepository == null) {
            Intrinsics.throwNpe();
        }
        this.widgetRecyclerAdapter = new WidgetRecyclerAdapter(context, from, user, str, lifecycleOwner, vitalRepository, vitalBlueprintsRepository);
        WidgetsViewModel widgetsViewModel = this.widgetsViewModel;
        if (widgetsViewModel != null) {
            widgetsViewModel.setWidgetRecyclerAdapter(this.widgetRecyclerAdapter);
        }
        WidgetsViewModel widgetsViewModel2 = this.widgetsViewModel;
        if (widgetsViewModel2 != null) {
            RealmResults allVitals = VitalStore.getInstance(getActivity()).getAllVitals();
            if (allVitals == null) {
                Intrinsics.throwNpe();
            }
            widgetsViewModel2.setVitalList(allVitals);
        }
        FragmentWidgetBinding fragmentWidgetBinding = this.binding;
        if (fragmentWidgetBinding != null) {
            fragmentWidgetBinding.setWidgetsViewModel(this.widgetsViewModel);
        }
        fragmentWidgetBinding = this.binding;
        return fragmentWidgetBinding != null ? fragmentWidgetBinding.getRoot() : null;
    }

    public void onAttach(@Nullable Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = (OnFragmentInteractionListener) null;
    }
}
