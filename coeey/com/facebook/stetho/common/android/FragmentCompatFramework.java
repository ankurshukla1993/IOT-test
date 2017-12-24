package com.facebook.stetho.common.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build.VERSION;
import com.facebook.stetho.common.android.FragmentCompat.FragmentManagerAccessorViaReflection;

@TargetApi(11)
final class FragmentCompatFramework extends FragmentCompat<Fragment, DialogFragment, FragmentManager, Activity> {
    private static final DialogFragmentAccessorFramework sDialogFragmentAccessor = new DialogFragmentAccessorFramework(sFragmentAccessor);
    private static final FragmentAccessorFrameworkHoneycomb sFragmentAccessor;
    private static final FragmentActivityAccessorFramework sFragmentActivityAccessor = new FragmentActivityAccessorFramework(null);
    private static final FragmentManagerAccessorViaReflection<FragmentManager, Fragment> sFragmentManagerAccessor = new FragmentManagerAccessorViaReflection();

    FragmentCompatFramework() {
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            sFragmentAccessor = new FragmentAccessorFrameworkJellyBean(null);
        } else {
            sFragmentAccessor = new FragmentAccessorFrameworkHoneycomb(null);
        }
    }

    public Class<Fragment> getFragmentClass() {
        return Fragment.class;
    }

    public Class<DialogFragment> getDialogFragmentClass() {
        return DialogFragment.class;
    }

    public Class<Activity> getFragmentActivityClass() {
        return Activity.class;
    }

    public FragmentAccessorFrameworkHoneycomb forFragment() {
        return sFragmentAccessor;
    }

    public DialogFragmentAccessorFramework forDialogFragment() {
        return sDialogFragmentAccessor;
    }

    public FragmentManagerAccessorViaReflection<FragmentManager, Fragment> forFragmentManager() {
        return sFragmentManagerAccessor;
    }

    public FragmentActivityAccessorFramework forFragmentActivity() {
        return sFragmentActivityAccessor;
    }
}
