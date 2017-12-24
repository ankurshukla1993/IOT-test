package com.biz.health.cooey_app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.dashboard.graphs.GraphsViewModel;
import com.cooey.common.BindingAdapters;
import com.github.mikephil.charting.charts.LineChart;

public class FragmentGraphBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final LineChart bloodGlucoseGraph;
    @NonNull
    public final CardView bloodGlucseGraphCard;
    @NonNull
    public final LineChart bloodPressureGraph;
    @NonNull
    public final CardView bloodPressureGraphCard;
    @NonNull
    public final Spinner bpTimeSpinner;
    @NonNull
    public final Spinner glucoseTimeSpinner;
    private long mDirtyFlags = -1;
    @Nullable
    private GraphsViewModel mGraphsViewModel;
    @NonNull
    private final NestedScrollView mboundView0;
    @NonNull
    private final TextView mboundView1;
    @NonNull
    private final TextView mboundView2;
    @NonNull
    private final TextView mboundView3;
    @NonNull
    public final LineChart weightGraph;
    @NonNull
    public final CardView weightGraphCard;
    @NonNull
    public final Spinner weightTimeSpinner;

    static {
        sViewsWithIds.put(C0644R.id.bloodPressureGraphCard, 4);
        sViewsWithIds.put(C0644R.id.bp_time_spinner, 5);
        sViewsWithIds.put(C0644R.id.bloodPressureGraph, 6);
        sViewsWithIds.put(C0644R.id.bloodGlucseGraphCard, 7);
        sViewsWithIds.put(C0644R.id.glucose_time_spinner, 8);
        sViewsWithIds.put(C0644R.id.bloodGlucoseGraph, 9);
        sViewsWithIds.put(C0644R.id.weightGraphCard, 10);
        sViewsWithIds.put(C0644R.id.weight_time_spinner, 11);
        sViewsWithIds.put(C0644R.id.weightGraph, 12);
    }

    public FragmentGraphBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds);
        this.bloodGlucoseGraph = (LineChart) bindings[9];
        this.bloodGlucseGraphCard = (CardView) bindings[7];
        this.bloodPressureGraph = (LineChart) bindings[6];
        this.bloodPressureGraphCard = (CardView) bindings[4];
        this.bpTimeSpinner = (Spinner) bindings[5];
        this.glucoseTimeSpinner = (Spinner) bindings[8];
        this.mboundView0 = (NestedScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.weightGraph = (LineChart) bindings[12];
        this.weightGraphCard = (CardView) bindings[10];
        this.weightTimeSpinner = (Spinner) bindings[11];
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
        if (14 != variableId) {
            return false;
        }
        setGraphsViewModel((GraphsViewModel) variable);
        return true;
    }

    public void setGraphsViewModel(@Nullable GraphsViewModel GraphsViewModel) {
        this.mGraphsViewModel = GraphsViewModel;
    }

    @Nullable
    public GraphsViewModel getGraphsViewModel() {
        return this.mGraphsViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeGraphsViewModel((GraphsViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeGraphsViewModel(GraphsViewModel GraphsViewModel, int fieldId) {
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
        if ((2 & dirtyFlags) != 0) {
            BindingAdapters.onSetFont(this.mboundView1, "Lato-Bold");
            BindingAdapters.onSetFont(this.mboundView2, "Lato-Bold");
            BindingAdapters.onSetFont(this.mboundView3, "Lato-Bold");
        }
    }

    @NonNull
    public static FragmentGraphBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentGraphBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (FragmentGraphBinding) DataBindingUtil.inflate(inflater, C0644R.layout.fragment_graph, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static FragmentGraphBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentGraphBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.fragment_graph, null, false), bindingComponent);
    }

    @NonNull
    public static FragmentGraphBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentGraphBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/fragment_graph_0".equals(view.getTag())) {
            return new FragmentGraphBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
