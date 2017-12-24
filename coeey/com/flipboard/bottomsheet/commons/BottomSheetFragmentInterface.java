package com.flipboard.bottomsheet.commons;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.flipboard.bottomsheet.ViewTransformer;

public interface BottomSheetFragmentInterface {
    void dismiss();

    void dismissAllowingStateLoss();

    ViewTransformer getViewTransformer();

    int show(FragmentTransaction fragmentTransaction, @IdRes int i);

    void show(FragmentManager fragmentManager, @IdRes int i);
}
