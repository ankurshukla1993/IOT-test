package com.flipboard.bottomsheet.commons;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import com.flipboard.bottomsheet.ViewTransformer;

public class BottomSheetFragment extends Fragment implements BottomSheetFragmentInterface {
    private BottomSheetFragmentDelegate delegate;

    public void show(FragmentManager manager, @IdRes int bottomSheetLayoutId) {
        getDelegate().show(manager, bottomSheetLayoutId);
    }

    public int show(FragmentTransaction transaction, @IdRes int bottomSheetLayoutId) {
        return getDelegate().show(transaction, bottomSheetLayoutId);
    }

    public void dismiss() {
        getDelegate().dismiss();
    }

    public void dismissAllowingStateLoss() {
        getDelegate().dismissAllowingStateLoss();
    }

    public ViewTransformer getViewTransformer() {
        return null;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        getDelegate().onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
        getDelegate().onDetach();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDelegate().onCreate(savedInstanceState);
    }

    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        return getDelegate().getLayoutInflater(savedInstanceState, super.getLayoutInflater(savedInstanceState));
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDelegate().onActivityCreated(savedInstanceState);
    }

    public void onStart() {
        super.onStart();
        getDelegate().onStart();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getDelegate().onSaveInstanceState(outState);
    }

    public void onDestroyView() {
        getDelegate().onDestroyView();
        super.onDestroyView();
    }

    private BottomSheetFragmentDelegate getDelegate() {
        if (this.delegate == null) {
            this.delegate = BottomSheetFragmentDelegate.create(this);
        }
        return this.delegate;
    }
}
