package com.biz.health.cooey_app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.summary.medicines.MedicinesInfoViewModel;

public class ItemSummaryMedicinesBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView activityText;
    @NonNull
    public final TextView activityTitle;
    @NonNull
    public final TextView activityUnits;
    private long mDirtyFlags = -1;
    @Nullable
    private MedicinesInfoViewModel mMedicinesInfoViewModel;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final LinearLayout medicineContainer1;
    @NonNull
    public final LinearLayout medicineContainer2;
    @NonNull
    public final LinearLayout medicineContainer3;
    @NonNull
    public final TextView medicineInfo1;
    @NonNull
    public final TextView medicineInfo2;
    @NonNull
    public final TextView medicineInfo3;
    @NonNull
    public final TextView medicineName1;
    @NonNull
    public final TextView medicineName2;
    @NonNull
    public final TextView medicineName3;
    @NonNull
    public final TextView medicineTitle;
    @NonNull
    public final ImageView moodImage;
    @NonNull
    public final TextView moodText;
    @NonNull
    public final TextView moodTitle;

    static {
        sViewsWithIds.put(C0644R.id.activity_title, 1);
        sViewsWithIds.put(C0644R.id.activity_text, 2);
        sViewsWithIds.put(C0644R.id.activity_units, 3);
        sViewsWithIds.put(C0644R.id.mood_title, 4);
        sViewsWithIds.put(C0644R.id.mood_image, 5);
        sViewsWithIds.put(C0644R.id.mood_text, 6);
        sViewsWithIds.put(C0644R.id.medicine_title, 7);
        sViewsWithIds.put(C0644R.id.medicine_container_1, 8);
        sViewsWithIds.put(C0644R.id.medicine_name_1, 9);
        sViewsWithIds.put(C0644R.id.medicine_info_1, 10);
        sViewsWithIds.put(C0644R.id.medicine_container_2, 11);
        sViewsWithIds.put(C0644R.id.medicine_name_2, 12);
        sViewsWithIds.put(C0644R.id.medicine_info_2, 13);
        sViewsWithIds.put(C0644R.id.medicine_container_3, 14);
        sViewsWithIds.put(C0644R.id.medicine_name_3, 15);
        sViewsWithIds.put(C0644R.id.medicine_info_3, 16);
    }

    public ItemSummaryMedicinesBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds);
        this.activityText = (TextView) bindings[2];
        this.activityTitle = (TextView) bindings[1];
        this.activityUnits = (TextView) bindings[3];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.medicineContainer1 = (LinearLayout) bindings[8];
        this.medicineContainer2 = (LinearLayout) bindings[11];
        this.medicineContainer3 = (LinearLayout) bindings[14];
        this.medicineInfo1 = (TextView) bindings[10];
        this.medicineInfo2 = (TextView) bindings[13];
        this.medicineInfo3 = (TextView) bindings[16];
        this.medicineName1 = (TextView) bindings[9];
        this.medicineName2 = (TextView) bindings[12];
        this.medicineName3 = (TextView) bindings[15];
        this.medicineTitle = (TextView) bindings[7];
        this.moodImage = (ImageView) bindings[5];
        this.moodText = (TextView) bindings[6];
        this.moodTitle = (TextView) bindings[4];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        if (31 != variableId) {
            return false;
        }
        setMedicinesInfoViewModel((MedicinesInfoViewModel) variable);
        return true;
    }

    public void setMedicinesInfoViewModel(@Nullable MedicinesInfoViewModel MedicinesInfoViewModel) {
        this.mMedicinesInfoViewModel = MedicinesInfoViewModel;
    }

    @Nullable
    public MedicinesInfoViewModel getMedicinesInfoViewModel() {
        return this.mMedicinesInfoViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMedicinesInfoViewModel((MedicinesInfoViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMedicinesInfoViewModel(MedicinesInfoViewModel MedicinesInfoViewModel, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }

    @NonNull
    public static ItemSummaryMedicinesBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemSummaryMedicinesBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ItemSummaryMedicinesBinding) DataBindingUtil.inflate(inflater, C0644R.layout.item_summary_medicines, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ItemSummaryMedicinesBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemSummaryMedicinesBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.item_summary_medicines, null, false), bindingComponent);
    }

    @NonNull
    public static ItemSummaryMedicinesBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemSummaryMedicinesBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/item_summary_medicines_0".equals(view.getTag())) {
            return new ItemSummaryMedicinesBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
