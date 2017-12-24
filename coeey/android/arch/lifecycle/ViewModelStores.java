package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class ViewModelStores {
    private ViewModelStores() {
    }

    @MainThread
    public static ViewModelStore of(FragmentActivity activity) {
        return HolderFragment.holderFragmentFor(activity).getViewModelStore();
    }

    @MainThread
    public static ViewModelStore of(Fragment fragment) {
        return HolderFragment.holderFragmentFor(fragment).getViewModelStore();
    }
}
