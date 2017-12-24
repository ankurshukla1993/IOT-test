package com.flipboard.bottomsheet.commons;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.AccessFragmentInternals;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.OnSheetDismissedListener;

public final class BottomSheetFragmentDelegate implements OnSheetDismissedListener {
    private static final String SAVED_BACK_STACK_ID = "bottomsheet:backStackId";
    private static final String SAVED_BOTTOM_SHEET_LAYOUT_ID = "bottomsheet:bottomSheetLayoutId";
    private static final String SAVED_SHOWS_BOTTOM_SHEET = "bottomsheet:savedBottomSheet";
    private int backStackId = -1;
    private BottomSheetLayout bottomSheetLayout;
    @IdRes
    private int bottomSheetLayoutId = -1;
    private boolean dismissed;
    private Fragment fragment;
    private BottomSheetFragmentInterface sheetFragmentInterface;
    private boolean shownByMe;
    private boolean showsBottomSheet = true;
    private boolean viewDestroyed;

    public static BottomSheetFragmentDelegate create(BottomSheetFragmentInterface sheetFragmentInterface) {
        return new BottomSheetFragmentDelegate(sheetFragmentInterface);
    }

    private BottomSheetFragmentDelegate(BottomSheetFragmentInterface sheetFragmentInterface) {
        if (sheetFragmentInterface instanceof Fragment) {
            this.sheetFragmentInterface = sheetFragmentInterface;
            this.fragment = (Fragment) sheetFragmentInterface;
            return;
        }
        throw new IllegalArgumentException("sheetFragmentInterface must be an instance of a Fragment too!");
    }

    public void show(FragmentManager manager, @IdRes int bottomSheetLayoutId) {
        this.dismissed = false;
        this.shownByMe = true;
        this.bottomSheetLayoutId = bottomSheetLayoutId;
        manager.beginTransaction().add(this.fragment, String.valueOf(bottomSheetLayoutId)).commit();
    }

    public int show(FragmentTransaction transaction, @IdRes int bottomSheetLayoutId) {
        this.dismissed = false;
        this.shownByMe = true;
        this.bottomSheetLayoutId = bottomSheetLayoutId;
        transaction.add(this.fragment, String.valueOf(bottomSheetLayoutId));
        this.viewDestroyed = false;
        this.backStackId = transaction.commit();
        return this.backStackId;
    }

    public void dismiss() {
        dismissInternal(false);
    }

    public void dismissAllowingStateLoss() {
        dismissInternal(true);
    }

    private void dismissInternal(boolean allowStateLoss) {
        if (!this.dismissed) {
            this.dismissed = true;
            this.shownByMe = false;
            if (this.bottomSheetLayout != null) {
                this.bottomSheetLayout.dismissSheet();
                this.bottomSheetLayout = null;
            }
            this.viewDestroyed = true;
            if (this.backStackId >= 0) {
                this.fragment.getFragmentManager().popBackStack(this.backStackId, 1);
                this.backStackId = -1;
                return;
            }
            FragmentTransaction ft = this.fragment.getFragmentManager().beginTransaction();
            ft.remove(this.fragment);
            if (allowStateLoss) {
                ft.commitAllowingStateLoss();
            } else {
                ft.commit();
            }
        }
    }

    public void onAttach(Context context) {
        if (!this.shownByMe) {
            this.dismissed = false;
        }
    }

    public void onDetach() {
        if (!this.shownByMe && !this.dismissed) {
            this.dismissed = true;
        }
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.showsBottomSheet = AccessFragmentInternals.getContainerId(this.fragment) == 0;
        if (savedInstanceState != null) {
            this.showsBottomSheet = savedInstanceState.getBoolean(SAVED_SHOWS_BOTTOM_SHEET, this.showsBottomSheet);
            this.backStackId = savedInstanceState.getInt(SAVED_BACK_STACK_ID, -1);
            this.bottomSheetLayoutId = savedInstanceState.getInt(SAVED_BOTTOM_SHEET_LAYOUT_ID, -1);
        }
    }

    @CheckResult
    public LayoutInflater getLayoutInflater(Bundle savedInstanceState, LayoutInflater superInflater) {
        if (!this.showsBottomSheet) {
            return superInflater;
        }
        this.bottomSheetLayout = getBottomSheetLayout();
        if (this.bottomSheetLayout != null) {
            return LayoutInflater.from(this.bottomSheetLayout.getContext());
        }
        return LayoutInflater.from(this.fragment.getContext());
    }

    public BottomSheetLayout getBottomSheetLayout() {
        if (this.bottomSheetLayout == null) {
            this.bottomSheetLayout = findBottomSheetLayout();
        }
        return this.bottomSheetLayout;
    }

    @Nullable
    private BottomSheetLayout findBottomSheetLayout() {
        Fragment parentFragment = this.fragment.getParentFragment();
        if (parentFragment != null) {
            View view = parentFragment.getView();
            if (view != null) {
                return (BottomSheetLayout) view.findViewById(this.bottomSheetLayoutId);
            }
            return null;
        }
        Activity parentActivity = this.fragment.getActivity();
        if (parentActivity != null) {
            return (BottomSheetLayout) parentActivity.findViewById(this.bottomSheetLayoutId);
        }
        return null;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (this.showsBottomSheet) {
            View view = this.fragment.getView();
            if (view != null && view.getParent() != null) {
                throw new IllegalStateException("BottomSheetFragment can not be attached to a container view");
            }
        }
    }

    public void onStart() {
        if (this.bottomSheetLayout != null) {
            this.viewDestroyed = false;
            this.bottomSheetLayout.showWithSheetView(this.fragment.getView(), this.sheetFragmentInterface.getViewTransformer());
            this.bottomSheetLayout.addOnSheetDismissedListener(this);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!this.showsBottomSheet) {
            outState.putBoolean(SAVED_SHOWS_BOTTOM_SHEET, false);
        }
        if (this.backStackId != -1) {
            outState.putInt(SAVED_BACK_STACK_ID, this.backStackId);
        }
        if (this.bottomSheetLayoutId != -1) {
            outState.putInt(SAVED_BOTTOM_SHEET_LAYOUT_ID, this.bottomSheetLayoutId);
        }
    }

    public void onDestroyView() {
        if (this.bottomSheetLayout != null) {
            this.viewDestroyed = true;
            this.bottomSheetLayout.dismissSheet();
            this.bottomSheetLayout = null;
        }
    }

    @CallSuper
    public void onDismissed(BottomSheetLayout bottomSheetLayout) {
        if (!this.viewDestroyed) {
            dismissInternal(true);
        }
    }
}
