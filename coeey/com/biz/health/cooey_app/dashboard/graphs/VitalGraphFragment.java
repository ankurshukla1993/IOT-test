package com.biz.health.cooey_app.dashboard.graphs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.RNFetchBlob.RNFetchBlobConst;
import com.biz.health.cooey_app.C0644R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0002\u0019\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eJ\u0012\u0010\u000f\u001a\u00020\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J(\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0018\u001a\u00020\tH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/biz/health/cooey_app/dashboard/graphs/VitalGraphFragment;", "Landroid/support/v4/app/Fragment;", "()V", "mListener", "Lcom/biz/health/cooey_app/dashboard/graphs/VitalGraphFragment$OnFragmentInteractionListener;", "mParam1", "", "mParam2", "onAttach", "", "context", "Landroid/content/Context;", "onButtonPressed", "uri", "Landroid/net/Uri;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDetach", "Companion", "OnFragmentInteractionListener", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalGraphFragment.kt */
public final class VitalGraphFragment extends Fragment {
    private static final String ARG_PARAM1 = ARG_PARAM1;
    private static final String ARG_PARAM2 = ARG_PARAM2;
    public static final Companion Companion = new Companion();
    private HashMap _$_findViewCache;
    private OnFragmentInteractionListener mListener;
    private String mParam1;
    private String mParam2;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/biz/health/cooey_app/dashboard/graphs/VitalGraphFragment$Companion;", "", "()V", "ARG_PARAM1", "", "getARG_PARAM1", "()Ljava/lang/String;", "ARG_PARAM2", "getARG_PARAM2", "newInstance", "Lcom/biz/health/cooey_app/dashboard/graphs/VitalGraphFragment;", "param1", "param2", "app_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: VitalGraphFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        private final String getARG_PARAM1() {
            return VitalGraphFragment.ARG_PARAM1;
        }

        private final String getARG_PARAM2() {
            return VitalGraphFragment.ARG_PARAM2;
        }

        @NotNull
        public final VitalGraphFragment newInstance(@NotNull String param1, @NotNull String param2) {
            Intrinsics.checkParameterIsNotNull(param1, VitalGraphFragment.ARG_PARAM1);
            Intrinsics.checkParameterIsNotNull(param2, VitalGraphFragment.ARG_PARAM2);
            VitalGraphFragment fragment = new VitalGraphFragment();
            Bundle args = new Bundle();
            args.putString(getARG_PARAM1(), param1);
            args.putString(getARG_PARAM2(), param2);
            fragment.setArguments(args);
            return fragment;
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/biz/health/cooey_app/dashboard/graphs/VitalGraphFragment$OnFragmentInteractionListener;", "", "onFragmentInteraction", "", "uri", "Landroid/net/Uri;", "app_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: VitalGraphFragment.kt */
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

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(Companion.getARG_PARAM1());
            this.mParam2 = getArguments().getString(Companion.getARG_PARAM2());
        }
    }

    @Nullable
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflater == null) {
            Intrinsics.throwNpe();
        }
        return inflater.inflate(C0644R.layout.fragment_vital_graph, container, false);
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
        if (context instanceof OnFragmentInteractionListener) {
            this.mListener = (OnFragmentInteractionListener) context;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        throw new RuntimeException(stringBuilder.append(context.toString()).append(" must implement OnFragmentInteractionListener").toString());
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = (OnFragmentInteractionListener) null;
    }
}
