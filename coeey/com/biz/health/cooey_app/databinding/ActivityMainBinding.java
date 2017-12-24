package com.biz.health.cooey_app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.dashboard.DashboardViewModel;
import com.cooey.android.vitals.views.VitalBlueprintSelectionView;
import com.cooey.common.BindingAdapters;
import com.cooey.common.vo.User;
import humanize.util.Constants;

public class ActivityMainBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final AppBarLayout appbar;
    @NonNull
    public final CollapsingToolbarLayout collapsingToolbar;
    @NonNull
    public final CoordinatorLayout container;
    @NonNull
    public final FloatingActionButton floatingActionButton;
    @NonNull
    public final ViewPager homeViewPager;
    @Nullable
    private DashboardViewModel mDashboardViewModel;
    private long mDirtyFlags = -1;
    @NonNull
    private final ImageView mboundView1;
    @NonNull
    private final TextView mboundView2;
    @NonNull
    public final TabLayout tabs;
    @NonNull
    public final Toolbar toolbar;
    @NonNull
    public final VitalBlueprintSelectionView vitalBlueprintSelector;

    static {
        sViewsWithIds.put(C0644R.id.appbar, 5);
        sViewsWithIds.put(C0644R.id.collapsing_toolbar, 6);
        sViewsWithIds.put(C0644R.id.toolbar, 7);
        sViewsWithIds.put(C0644R.id.vital_blueprint_selector, 8);
        sViewsWithIds.put(C0644R.id.floating_action_button, 9);
    }

    public ActivityMainBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 2);
        Object[] bindings = mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.appbar = (AppBarLayout) bindings[5];
        this.collapsingToolbar = (CollapsingToolbarLayout) bindings[6];
        this.container = (CoordinatorLayout) bindings[0];
        this.container.setTag(null);
        this.floatingActionButton = (FloatingActionButton) bindings[9];
        this.homeViewPager = (ViewPager) bindings[4];
        this.homeViewPager.setTag(null);
        this.mboundView1 = (ImageView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.tabs = (TabLayout) bindings[3];
        this.tabs.setTag(null);
        this.toolbar = (Toolbar) bindings[7];
        this.vitalBlueprintSelector = (VitalBlueprintSelectionView) bindings[8];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (10 != variableId) {
            return false;
        }
        setDashboardViewModel((DashboardViewModel) variable);
        return true;
    }

    public void setDashboardViewModel(@Nullable DashboardViewModel DashboardViewModel) {
        updateRegistration(1, DashboardViewModel);
        this.mDashboardViewModel = DashboardViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    @Nullable
    public DashboardViewModel getDashboardViewModel() {
        return this.mDashboardViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeDashboardViewModelUser((User) object, fieldId);
            case 1:
                return onChangeDashboardViewModel((DashboardViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeDashboardViewModelUser(User DashboardViewModelUser, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeDashboardViewModel(DashboardViewModel DashboardViewModel, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (fieldId != 54) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        PagerAdapter dashboardViewModelViewPagerAdapter = null;
        String dashboardViewModelUserLastName = null;
        String dashboardViewModelUserFirstName = null;
        Drawable dashboardViewModelUserGenderEqualsIgnoreCaseJavaLangStringFemaleMboundView1AndroidDrawableProfileImageFemaleMboundView1AndroidDrawableProfileImageMale = null;
        String dashboardViewModelUserGender = null;
        User dashboardViewModelUser = null;
        String dashboardViewModelUserFirstNameJavaLangStringDashboardViewModelUserLastName = null;
        DashboardViewModel dashboardViewModel = this.mDashboardViewModel;
        boolean dashboardViewModelUserGenderEqualsIgnoreCaseJavaLangStringFemale = false;
        if ((15 & dirtyFlags) != 0) {
            if (!((14 & dirtyFlags) == 0 || dashboardViewModel == null)) {
                dashboardViewModelViewPagerAdapter = dashboardViewModel.getViewPagerAdapter();
            }
            if ((11 & dirtyFlags) != 0) {
                if (dashboardViewModel != null) {
                    dashboardViewModelUser = dashboardViewModel.getUser();
                }
                updateRegistration(0, dashboardViewModelUser);
                if (dashboardViewModelUser != null) {
                    dashboardViewModelUserLastName = dashboardViewModelUser.getLastName();
                    dashboardViewModelUserFirstName = dashboardViewModelUser.getFirstName();
                    dashboardViewModelUserGender = dashboardViewModelUser.getGender();
                }
                String dashboardViewModelUserFirstNameJavaLangString = dashboardViewModelUserFirstName + Constants.SPACE;
                if (dashboardViewModelUserGender != null) {
                    dashboardViewModelUserGenderEqualsIgnoreCaseJavaLangStringFemale = dashboardViewModelUserGender.equalsIgnoreCase("female");
                }
                if ((11 & dirtyFlags) != 0) {
                    if (dashboardViewModelUserGenderEqualsIgnoreCaseJavaLangStringFemale) {
                        dirtyFlags |= 32;
                    } else {
                        dirtyFlags |= 16;
                    }
                }
                dashboardViewModelUserFirstNameJavaLangStringDashboardViewModelUserLastName = dashboardViewModelUserFirstNameJavaLangString + dashboardViewModelUserLastName;
                dashboardViewModelUserGenderEqualsIgnoreCaseJavaLangStringFemaleMboundView1AndroidDrawableProfileImageFemaleMboundView1AndroidDrawableProfileImageMale = dashboardViewModelUserGenderEqualsIgnoreCaseJavaLangStringFemale ? getDrawableFromResource(this.mboundView1, C0644R.drawable.profile_image_female) : getDrawableFromResource(this.mboundView1, C0644R.drawable.profile_image_male);
            }
        }
        if ((14 & dirtyFlags) != 0) {
            BindingAdapters.onSetViewPager(this.homeViewPager, dashboardViewModelViewPagerAdapter);
        }
        if ((11 & dirtyFlags) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView1, dashboardViewModelUserGenderEqualsIgnoreCaseJavaLangStringFemaleMboundView1AndroidDrawableProfileImageFemaleMboundView1AndroidDrawableProfileImageMale);
            TextViewBindingAdapter.setText(this.mboundView2, dashboardViewModelUserFirstNameJavaLangStringDashboardViewModelUserLastName);
        }
        if ((8 & dirtyFlags) != 0) {
            BindingAdapters.onSetFont(this.mboundView2, "Lato-Black");
            BindingAdapters.onSetViewPager(this.tabs, this.homeViewPager);
        }
    }

    @NonNull
    public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityMainBinding) DataBindingUtil.inflate(inflater, C0644R.layout.activity_main, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.activity_main, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityMainBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_main_0".equals(view.getTag())) {
            return new ActivityMainBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
