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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.summary.info.SummaryInfoViewModel;
import com.cooey.common.vo.User;
import humanize.util.Constants;

public class ItemLayoutSummaryInfoBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView ageInfoText;
    @NonNull
    public final TextView ageText;
    @NonNull
    public final TextView alcoholText;
    @NonNull
    public final TextView alcoholTitle;
    @NonNull
    public final TextView allergyText;
    @NonNull
    public final TextView allergyTitle;
    @NonNull
    public final TextView bmiTitle;
    @NonNull
    public final TextView bmiValue;
    @NonNull
    public final TextView diagnosisText;
    @NonNull
    public final TextView habitsText;
    @NonNull
    public final TextView heightText;
    @NonNull
    public final TextView heightTitle;
    @NonNull
    public final TextView heightUnits;
    @NonNull
    public final TextView hipTitle;
    @NonNull
    public final TextView hipUnits;
    @NonNull
    public final TextView hipValue;
    @NonNull
    public final TextView infoText;
    private long mDirtyFlags = -1;
    @Nullable
    private SummaryInfoViewModel mSummaryInfoViewModel;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final TextView nameText;
    @NonNull
    public final TextView parentsHistoryTitle;
    @NonNull
    public final TextView sleepingText;
    @NonNull
    public final TextView sleepingTitle;
    @NonNull
    public final TextView smokingText;
    @NonNull
    public final TextView smokingTitle;
    @NonNull
    public final TextView waistHipRatioTitle;
    @NonNull
    public final TextView waistHipRatioValue;
    @NonNull
    public final TextView waistText;
    @NonNull
    public final TextView waistTitle;
    @NonNull
    public final TextView waistUnits;
    @NonNull
    public final TextView waterText;
    @NonNull
    public final TextView waterTitle;
    @NonNull
    public final TextView weightText;
    @NonNull
    public final TextView weightTitle;
    @NonNull
    public final TextView weightUnits;

    static {
        sViewsWithIds.put(C0644R.id.info_text, 4);
        sViewsWithIds.put(C0644R.id.diagnosis_text, 5);
        sViewsWithIds.put(C0644R.id.weight_title, 6);
        sViewsWithIds.put(C0644R.id.weight_text, 7);
        sViewsWithIds.put(C0644R.id.weight_units, 8);
        sViewsWithIds.put(C0644R.id.height_title, 9);
        sViewsWithIds.put(C0644R.id.height_text, 10);
        sViewsWithIds.put(C0644R.id.height_units, 11);
        sViewsWithIds.put(C0644R.id.waist_title, 12);
        sViewsWithIds.put(C0644R.id.waist_text, 13);
        sViewsWithIds.put(C0644R.id.waist_units, 14);
        sViewsWithIds.put(C0644R.id.hip_title, 15);
        sViewsWithIds.put(C0644R.id.hip_value, 16);
        sViewsWithIds.put(C0644R.id.hip_units, 17);
        sViewsWithIds.put(C0644R.id.bmi_title, 18);
        sViewsWithIds.put(C0644R.id.bmi_value, 19);
        sViewsWithIds.put(C0644R.id.waist_hip_ratio_title, 20);
        sViewsWithIds.put(C0644R.id.waist_hip_ratio_value, 21);
        sViewsWithIds.put(C0644R.id.smoking_text, 22);
        sViewsWithIds.put(C0644R.id.smoking_title, 23);
        sViewsWithIds.put(C0644R.id.alcohol_text, 24);
        sViewsWithIds.put(C0644R.id.alcohol_title, 25);
        sViewsWithIds.put(C0644R.id.sleeping_text, 26);
        sViewsWithIds.put(C0644R.id.sleeping_title, 27);
        sViewsWithIds.put(C0644R.id.water_text, 28);
        sViewsWithIds.put(C0644R.id.water_title, 29);
        sViewsWithIds.put(C0644R.id.parents_history_title, 30);
        sViewsWithIds.put(C0644R.id.habits_text, 31);
        sViewsWithIds.put(C0644R.id.allergy_title, 32);
        sViewsWithIds.put(C0644R.id.allergy_text, 33);
    }

    public ItemLayoutSummaryInfoBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 2);
        Object[] bindings = mapBindings(bindingComponent, root, 34, sIncludes, sViewsWithIds);
        this.ageInfoText = (TextView) bindings[3];
        this.ageInfoText.setTag(null);
        this.ageText = (TextView) bindings[2];
        this.ageText.setTag(null);
        this.alcoholText = (TextView) bindings[24];
        this.alcoholTitle = (TextView) bindings[25];
        this.allergyText = (TextView) bindings[33];
        this.allergyTitle = (TextView) bindings[32];
        this.bmiTitle = (TextView) bindings[18];
        this.bmiValue = (TextView) bindings[19];
        this.diagnosisText = (TextView) bindings[5];
        this.habitsText = (TextView) bindings[31];
        this.heightText = (TextView) bindings[10];
        this.heightTitle = (TextView) bindings[9];
        this.heightUnits = (TextView) bindings[11];
        this.hipTitle = (TextView) bindings[15];
        this.hipUnits = (TextView) bindings[17];
        this.hipValue = (TextView) bindings[16];
        this.infoText = (TextView) bindings[4];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.nameText = (TextView) bindings[1];
        this.nameText.setTag(null);
        this.parentsHistoryTitle = (TextView) bindings[30];
        this.sleepingText = (TextView) bindings[26];
        this.sleepingTitle = (TextView) bindings[27];
        this.smokingText = (TextView) bindings[22];
        this.smokingTitle = (TextView) bindings[23];
        this.waistHipRatioTitle = (TextView) bindings[20];
        this.waistHipRatioValue = (TextView) bindings[21];
        this.waistText = (TextView) bindings[13];
        this.waistTitle = (TextView) bindings[12];
        this.waistUnits = (TextView) bindings[14];
        this.waterText = (TextView) bindings[28];
        this.waterTitle = (TextView) bindings[29];
        this.weightText = (TextView) bindings[7];
        this.weightTitle = (TextView) bindings[6];
        this.weightUnits = (TextView) bindings[8];
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
        if (42 != variableId) {
            return false;
        }
        setSummaryInfoViewModel((SummaryInfoViewModel) variable);
        return true;
    }

    public void setSummaryInfoViewModel(@Nullable SummaryInfoViewModel SummaryInfoViewModel) {
        updateRegistration(1, SummaryInfoViewModel);
        this.mSummaryInfoViewModel = SummaryInfoViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(42);
        super.requestRebind();
    }

    @Nullable
    public SummaryInfoViewModel getSummaryInfoViewModel() {
        return this.mSummaryInfoViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeSummaryInfoViewModelUser((User) object, fieldId);
            case 1:
                return onChangeSummaryInfoViewModel((SummaryInfoViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeSummaryInfoViewModelUser(User SummaryInfoViewModelUser, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeSummaryInfoViewModel(SummaryInfoViewModel SummaryInfoViewModel, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (fieldId == 51) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (fieldId != 1) {
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
        String summaryInfoViewModelUserFirstName = null;
        String summaryInfoViewModelUserGender = null;
        int summaryInfoViewModelAge = 0;
        String summaryInfoViewModelUserLastName = null;
        User summaryInfoViewModelUser = null;
        SummaryInfoViewModel summaryInfoViewModel = this.mSummaryInfoViewModel;
        String summaryInfoViewModelUserFirstNameJavaLangStringSummaryInfoViewModelUserLastName = null;
        if ((15 & dirtyFlags) != 0) {
            if (!((14 & dirtyFlags) == 0 || summaryInfoViewModel == null)) {
                summaryInfoViewModelAge = summaryInfoViewModel.getAge();
            }
            if ((11 & dirtyFlags) != 0) {
                if (summaryInfoViewModel != null) {
                    summaryInfoViewModelUser = summaryInfoViewModel.getUser();
                }
                updateRegistration(0, summaryInfoViewModelUser);
                if (summaryInfoViewModelUser != null) {
                    summaryInfoViewModelUserFirstName = summaryInfoViewModelUser.getFirstName();
                    summaryInfoViewModelUserGender = summaryInfoViewModelUser.getGender();
                    summaryInfoViewModelUserLastName = summaryInfoViewModelUser.getLastName();
                }
                summaryInfoViewModelUserFirstNameJavaLangStringSummaryInfoViewModelUserLastName = (summaryInfoViewModelUserFirstName + Constants.SPACE) + summaryInfoViewModelUserLastName;
            }
        }
        if ((11 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.ageInfoText, summaryInfoViewModelUserGender);
            TextViewBindingAdapter.setText(this.nameText, summaryInfoViewModelUserFirstNameJavaLangStringSummaryInfoViewModelUserLastName);
        }
        if ((14 & dirtyFlags) != 0) {
            this.ageText.setText(summaryInfoViewModelAge);
        }
    }

    @NonNull
    public static ItemLayoutSummaryInfoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemLayoutSummaryInfoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ItemLayoutSummaryInfoBinding) DataBindingUtil.inflate(inflater, C0644R.layout.item_layout_summary_info, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ItemLayoutSummaryInfoBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemLayoutSummaryInfoBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.item_layout_summary_info, null, false), bindingComponent);
    }

    @NonNull
    public static ItemLayoutSummaryInfoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemLayoutSummaryInfoBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/item_layout_summary_info_0".equals(view.getTag())) {
            return new ItemLayoutSummaryInfoBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
