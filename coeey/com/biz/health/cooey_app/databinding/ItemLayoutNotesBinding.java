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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.summary.notes.SummaryPatientNotesViewModel;

public class ItemLayoutNotesBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView date1;
    @NonNull
    public final TextView date2;
    private long mDirtyFlags = -1;
    @Nullable
    private SummaryPatientNotesViewModel mSummaryPatientNotesViewModel;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final TextView note1;
    @NonNull
    public final TextView note2;
    @NonNull
    public final TextView summaryNotesTitle;
    @NonNull
    public final LinearLayout vitalsContainer;

    static {
        sViewsWithIds.put(C0644R.id.vitals_container, 1);
        sViewsWithIds.put(C0644R.id.summary_notes_title, 2);
        sViewsWithIds.put(C0644R.id.note_1, 3);
        sViewsWithIds.put(C0644R.id.date_1, 4);
        sViewsWithIds.put(C0644R.id.note_2, 5);
        sViewsWithIds.put(C0644R.id.date_2, 6);
    }

    public ItemLayoutNotesBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.date1 = (TextView) bindings[4];
        this.date2 = (TextView) bindings[6];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.note1 = (TextView) bindings[3];
        this.note2 = (TextView) bindings[5];
        this.summaryNotesTitle = (TextView) bindings[2];
        this.vitalsContainer = (LinearLayout) bindings[1];
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
        if (43 != variableId) {
            return false;
        }
        setSummaryPatientNotesViewModel((SummaryPatientNotesViewModel) variable);
        return true;
    }

    public void setSummaryPatientNotesViewModel(@Nullable SummaryPatientNotesViewModel SummaryPatientNotesViewModel) {
        this.mSummaryPatientNotesViewModel = SummaryPatientNotesViewModel;
    }

    @Nullable
    public SummaryPatientNotesViewModel getSummaryPatientNotesViewModel() {
        return this.mSummaryPatientNotesViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeSummaryPatientNotesViewModel((SummaryPatientNotesViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeSummaryPatientNotesViewModel(SummaryPatientNotesViewModel SummaryPatientNotesViewModel, int fieldId) {
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
    public static ItemLayoutNotesBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemLayoutNotesBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ItemLayoutNotesBinding) DataBindingUtil.inflate(inflater, C0644R.layout.item_layout_notes, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ItemLayoutNotesBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemLayoutNotesBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.item_layout_notes, null, false), bindingComponent);
    }

    @NonNull
    public static ItemLayoutNotesBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemLayoutNotesBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/item_layout_notes_0".equals(view.getTag())) {
            return new ItemLayoutNotesBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
