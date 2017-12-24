package com.biz.health.cooey_app.dashboard.history;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.databinding.FragmentHistoryBinding;
import com.cooey.common.vo.User;

public class HistoryFragment extends Fragment {
    private static final String ARG_USER = "USER";
    private OnFragmentInteractionListener mListener;
    private User user;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static HistoryFragment newInstance(User user) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER, User.toJson(user));
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.user = User.fromJson(getArguments().getString(ARG_USER));
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHistoryBinding binding = (FragmentHistoryBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), C0644R.layout.fragment_history, container, false);
        binding.setHistoryViewModel(new HistoryViewModel(getContext(), this.user));
        return binding.getRoot();
    }

    public void onButtonPressed(Uri uri) {
        if (this.mListener != null) {
            this.mListener.onFragmentInteraction(uri);
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }
}
