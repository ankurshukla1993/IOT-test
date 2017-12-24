package com.biz.health.cooey_app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.dashboard.widgets.holders.SummaryViewModel;
import com.cooey.common.BindingAdapters;

public class SummaryWidgetBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    @Nullable
    private SummaryViewModel mSummaryViewModel;
    @NonNull
    private final FrameLayout mboundView0;
    @NonNull
    private final TextView mboundView1;
    @NonNull
    public final RelativeLayout relative1;
    @NonNull
    public final RelativeLayout relative2;
    @NonNull
    public final RelativeLayout relative3;
    @NonNull
    public final LinearLayout summaryContainer4;
    @NonNull
    public final LinearLayout summaryContainer5;
    @NonNull
    public final LinearLayout summaryContainer6;
    @NonNull
    public final ImageView summaryImage4;
    @NonNull
    public final ImageView summaryImage5;
    @NonNull
    public final ImageView summaryImage6;
    @NonNull
    public final TextView summaryText4;
    @NonNull
    public final TextView summaryText5;
    @NonNull
    public final TextView summaryText6;
    @NonNull
    public final TextView summaryTextBlood;
    @NonNull
    public final TextView summaryTextBp;
    @NonNull
    public final TextView summaryTextWeight;

    static {
        sViewsWithIds.put(C0644R.id.summary_container_4, 5);
        sViewsWithIds.put(C0644R.id.relative1, 6);
        sViewsWithIds.put(C0644R.id.summary_image_4, 7);
        sViewsWithIds.put(C0644R.id.summary_text_blood, 8);
        sViewsWithIds.put(C0644R.id.summary_container_5, 9);
        sViewsWithIds.put(C0644R.id.relative2, 10);
        sViewsWithIds.put(C0644R.id.summary_image_5, 11);
        sViewsWithIds.put(C0644R.id.summary_text_bp, 12);
        sViewsWithIds.put(C0644R.id.summary_container_6, 13);
        sViewsWithIds.put(C0644R.id.relative3, 14);
        sViewsWithIds.put(C0644R.id.summary_image_6, 15);
        sViewsWithIds.put(C0644R.id.summary_text_weight, 16);
    }

    public SummaryWidgetBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds);
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.relative1 = (RelativeLayout) bindings[6];
        this.relative2 = (RelativeLayout) bindings[10];
        this.relative3 = (RelativeLayout) bindings[14];
        this.summaryContainer4 = (LinearLayout) bindings[5];
        this.summaryContainer5 = (LinearLayout) bindings[9];
        this.summaryContainer6 = (LinearLayout) bindings[13];
        this.summaryImage4 = (ImageView) bindings[7];
        this.summaryImage5 = (ImageView) bindings[11];
        this.summaryImage6 = (ImageView) bindings[15];
        this.summaryText4 = (TextView) bindings[2];
        this.summaryText4.setTag(null);
        this.summaryText5 = (TextView) bindings[3];
        this.summaryText5.setTag(null);
        this.summaryText6 = (TextView) bindings[4];
        this.summaryText6.setTag(null);
        this.summaryTextBlood = (TextView) bindings[8];
        this.summaryTextBp = (TextView) bindings[12];
        this.summaryTextWeight = (TextView) bindings[16];
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
        if (45 != variableId) {
            return false;
        }
        setSummaryViewModel((SummaryViewModel) variable);
        return true;
    }

    public void setSummaryViewModel(@Nullable SummaryViewModel SummaryViewModel) {
        updateRegistration(0, SummaryViewModel);
        this.mSummaryViewModel = SummaryViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(45);
        super.requestRebind();
    }

    @Nullable
    public SummaryViewModel getSummaryViewModel() {
        return this.mSummaryViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeSummaryViewModel((SummaryViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeSummaryViewModel(SummaryViewModel SummaryViewModel, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (fieldId == 3) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (fieldId != 7) {
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
        SummaryViewModel summaryViewModel = this.mSummaryViewModel;
        String summaryViewModelWeight = null;
        String summaryViewModelBpText = null;
        String summaryViewModelBloodGlucoseText = null;
        if ((15 & dirtyFlags) != 0) {
            if (!((9 & dirtyFlags) == 0 || summaryViewModel == null)) {
                summaryViewModelWeight = summaryViewModel.getWeight();
            }
            if (!((13 & dirtyFlags) == 0 || summaryViewModel == null)) {
                summaryViewModelBpText = summaryViewModel.getBpText();
            }
            if (!((11 & dirtyFlags) == 0 || summaryViewModel == null)) {
                summaryViewModelBloodGlucoseText = summaryViewModel.getBloodGlucoseText();
            }
        }
        if ((8 & dirtyFlags) != 0) {
            BindingAdapters.onSetFont(this.mboundView1, "Lato-Bold");
            BindingAdapters.onSetFont(this.summaryText4, "Lato-Bold");
            BindingAdapters.onSetFont(this.summaryText5, "Lato-Bold");
            BindingAdapters.onSetFont(this.summaryText6, "Lato-Bold");
        }
        if ((11 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.summaryText4, summaryViewModelBloodGlucoseText);
        }
        if ((13 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.summaryText5, summaryViewModelBpText);
        }
        if ((9 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.summaryText6, summaryViewModelWeight);
        }
    }

    @NonNull
    public static SummaryWidgetBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SummaryWidgetBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (SummaryWidgetBinding) DataBindingUtil.inflate(inflater, C0644R.layout.summary_widget, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static SummaryWidgetBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SummaryWidgetBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.summary_widget, null, false), bindingComponent);
    }

    @NonNull
    public static SummaryWidgetBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SummaryWidgetBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/summary_widget_0".equals(view.getTag())) {
            return new SummaryWidgetBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
