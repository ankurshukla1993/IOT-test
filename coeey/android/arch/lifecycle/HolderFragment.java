package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({Scope.LIBRARY_GROUP})
public class HolderFragment extends Fragment {
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final String HOLDER_TAG = "android.arch.lifecycle.state.StateProviderHolderFragment";
    private static final String LOG_TAG = "ViewModelStores";
    private static final HolderFragmentManager sHolderFragmentManager = new HolderFragmentManager();
    private ViewModelStore mViewModelStore = new ViewModelStore();

    static class HolderFragmentManager {
        private ActivityLifecycleCallbacks mActivityCallbacks = new C00071();
        private boolean mActivityCallbacksIsAdded = false;
        private Map<Activity, HolderFragment> mNotCommittedActivityHolders = new HashMap();
        private Map<Fragment, HolderFragment> mNotCommittedFragmentHolders = new HashMap();
        private FragmentLifecycleCallbacks mParentDestroyedCallback = new C00082();

        class C00071 extends EmptyActivityLifecycleCallbacks {
            C00071() {
            }

            public void onActivityDestroyed(Activity activity) {
                if (((HolderFragment) HolderFragmentManager.this.mNotCommittedActivityHolders.remove(activity)) != null) {
                    Log.e(HolderFragment.LOG_TAG, "Failed to save a ViewModel for " + activity);
                }
            }
        }

        class C00082 extends FragmentLifecycleCallbacks {
            C00082() {
            }

            public void onFragmentDestroyed(FragmentManager fm, Fragment parentFragment) {
                super.onFragmentDestroyed(fm, parentFragment);
                if (((HolderFragment) HolderFragmentManager.this.mNotCommittedFragmentHolders.remove(parentFragment)) != null) {
                    Log.e(HolderFragment.LOG_TAG, "Failed to save a ViewModel for " + parentFragment);
                }
            }
        }

        HolderFragmentManager() {
        }

        void holderFragmentCreated(Fragment holderFragment) {
            Fragment parentFragment = holderFragment.getParentFragment();
            if (parentFragment != null) {
                this.mNotCommittedFragmentHolders.remove(parentFragment);
                parentFragment.getFragmentManager().unregisterFragmentLifecycleCallbacks(this.mParentDestroyedCallback);
                return;
            }
            this.mNotCommittedActivityHolders.remove(holderFragment.getActivity());
        }

        private static HolderFragment findHolderFragment(FragmentManager manager) {
            if (manager.isDestroyed()) {
                throw new IllegalStateException("Can't access ViewModels from onDestroy");
            }
            Fragment fragmentByTag = manager.findFragmentByTag(HolderFragment.HOLDER_TAG);
            if (fragmentByTag == null || (fragmentByTag instanceof HolderFragment)) {
                return (HolderFragment) fragmentByTag;
            }
            throw new IllegalStateException("Unexpected fragment instance was returned by HOLDER_TAG");
        }

        private static HolderFragment createHolderFragment(FragmentManager fragmentManager) {
            Fragment holder = new HolderFragment();
            fragmentManager.beginTransaction().add(holder, HolderFragment.HOLDER_TAG).commitAllowingStateLoss();
            return holder;
        }

        HolderFragment holderFragmentFor(FragmentActivity activity) {
            FragmentManager fm = activity.getSupportFragmentManager();
            HolderFragment holder = findHolderFragment(fm);
            if (holder != null) {
                return holder;
            }
            holder = (HolderFragment) this.mNotCommittedActivityHolders.get(activity);
            if (holder != null) {
                return holder;
            }
            if (!this.mActivityCallbacksIsAdded) {
                this.mActivityCallbacksIsAdded = true;
                activity.getApplication().registerActivityLifecycleCallbacks(this.mActivityCallbacks);
            }
            holder = createHolderFragment(fm);
            this.mNotCommittedActivityHolders.put(activity, holder);
            return holder;
        }

        HolderFragment holderFragmentFor(Fragment parentFragment) {
            FragmentManager fm = parentFragment.getChildFragmentManager();
            HolderFragment holder = findHolderFragment(fm);
            if (holder != null) {
                return holder;
            }
            holder = (HolderFragment) this.mNotCommittedFragmentHolders.get(parentFragment);
            if (holder != null) {
                return holder;
            }
            parentFragment.getFragmentManager().registerFragmentLifecycleCallbacks(this.mParentDestroyedCallback, false);
            holder = createHolderFragment(fm);
            this.mNotCommittedFragmentHolders.put(parentFragment, holder);
            return holder;
        }
    }

    public HolderFragment() {
        setRetainInstance(true);
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sHolderFragmentManager.holderFragmentCreated(this);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mViewModelStore.clear();
    }

    public ViewModelStore getViewModelStore() {
        return this.mViewModelStore;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static HolderFragment holderFragmentFor(FragmentActivity activity) {
        return sHolderFragmentManager.holderFragmentFor(activity);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static HolderFragment holderFragmentFor(Fragment fragment) {
        return sHolderFragmentManager.holderFragmentFor(fragment);
    }
}
