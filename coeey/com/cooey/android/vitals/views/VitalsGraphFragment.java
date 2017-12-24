package com.cooey.android.vitals.views;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cooey.android.vitals.R;
import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.android.vitals.repositories.VitalRepository;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000  2\u00020\u00012\u00020\u0002:\u0001 B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0012\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J(\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001f\u001a\u00020\u0013H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006!"}, d2 = {"Lcom/cooey/android/vitals/views/VitalsGraphFragment;", "Landroid/support/v4/app/Fragment;", "Landroid/arch/lifecycle/LifecycleOwner;", "()V", "userId", "", "vitalBlueprint", "Lcom/cooey/android/vitals/VitalBlueprint;", "getVitalBlueprint", "()Lcom/cooey/android/vitals/VitalBlueprint;", "setVitalBlueprint", "(Lcom/cooey/android/vitals/VitalBlueprint;)V", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "onAttach", "", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDetach", "Companion", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalsGraphFragment.kt */
public final class VitalsGraphFragment extends Fragment implements LifecycleOwner {
    private static final String ARG_USER_ID = ARG_USER_ID;
    public static final Companion Companion = new Companion();
    private HashMap _$_findViewCache;
    private String userId;
    @Nullable
    private VitalBlueprint vitalBlueprint;
    @Nullable
    private VitalRepository vitalRepository;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/cooey/android/vitals/views/VitalsGraphFragment$Companion;", "", "()V", "ARG_USER_ID", "", "getARG_USER_ID", "()Ljava/lang/String;", "newInstance", "Lcom/cooey/android/vitals/views/VitalsGraphFragment;", "userId", "vitals_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: VitalsGraphFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        private final String getARG_USER_ID() {
            return VitalsGraphFragment.ARG_USER_ID;
        }

        @NotNull
        public final VitalsGraphFragment newInstance(@NotNull String userId) {
            Intrinsics.checkParameterIsNotNull(userId, "userId");
            VitalsGraphFragment fragment = new VitalsGraphFragment();
            Bundle args = new Bundle();
            args.putString(getARG_USER_ID(), userId);
            fragment.setArguments(args);
            return fragment;
        }
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
    public final VitalBlueprint getVitalBlueprint() {
        return this.vitalBlueprint;
    }

    public final void setVitalBlueprint(@Nullable VitalBlueprint <set-?>) {
        this.vitalBlueprint = <set-?>;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.userId = getArguments().getString(Companion.getARG_USER_ID());
        }
    }

    @Nullable
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflater == null) {
            Intrinsics.throwNpe();
        }
        View view = inflater.inflate(R.layout.fragment_vitals_graph, container, false);
        VitalGraphView vitalGraphView = (VitalGraphView) view.findViewById(R.id.vital_graph_view);
        vitalGraphView.setVitalRepository(this.vitalRepository);
        vitalGraphView.setLifeCycleOwner(this);
        vitalGraphView.setUserId(this.userId);
        VitalBlueprint vitalBlueprint = this.vitalBlueprint;
        if (vitalBlueprint == null) {
            Intrinsics.throwNpe();
        }
        vitalGraphView.loadVitals(vitalBlueprint);
        return view;
    }

    public void onAttach(@Nullable Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
    }
}
