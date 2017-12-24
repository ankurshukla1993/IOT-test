package com.cooey.android.vitals.adapters;

import android.arch.lifecycle.Observer;
import android.widget.LinearLayout;
import com.cooey.android.vitals.Vital;
import com.cooey.android.vitals.adapters.VitalWidgetRecyclerAdapter.VitalWidgetItemViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "vital", "Lcom/cooey/android/vitals/Vital;", "onChanged"}, k = 3, mv = {1, 1, 7})
/* compiled from: VitalWidgetRecyclerAdapter.kt */
final class VitalWidgetRecyclerAdapter$VitalWidgetItemViewHolder$bind$2<T> implements Observer<Vital> {
    final /* synthetic */ ObjectRef $fieldValueContainer;
    final /* synthetic */ VitalWidgetItemViewHolder this$0;

    VitalWidgetRecyclerAdapter$VitalWidgetItemViewHolder$bind$2(VitalWidgetItemViewHolder vitalWidgetItemViewHolder, ObjectRef objectRef) {
        this.this$0 = vitalWidgetItemViewHolder;
        this.$fieldValueContainer = objectRef;
    }

    public final void onChanged(@Nullable Vital vital) {
        if (vital == null) {
            this.this$0.addEmptyField((LinearLayout) this.$fieldValueContainer.element);
        } else {
            this.this$0.addFields(vital.getParameters(), (LinearLayout) this.$fieldValueContainer.element);
        }
    }
}
