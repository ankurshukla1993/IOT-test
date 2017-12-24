package com.cooey.common.generators;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cooey.common.R;
import com.cooey.common.holders.NoteTimelineViewHolder;
import com.cooey.common.holders.TimelineItemViewHolder;
import com.cooey.common.views.TimelineItemView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/cooey/common/generators/NoteTimelineViewHolderGenerator;", "Lcom/cooey/common/generators/ITimelineViewHolderGenerator;", "()V", "generate", "Lcom/cooey/common/holders/TimelineItemViewHolder;", "parent", "Landroid/view/ViewGroup;", "viewType", "", "common_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: NoteTimelineViewHolderGenerator.kt */
public final class NoteTimelineViewHolderGenerator implements ITimelineViewHolderGenerator {
    @Nullable
    public TimelineItemViewHolder generate(@Nullable ViewGroup parent, int viewType) {
        TimelineItemView timelineItemView = LayoutInflater.from(parent != null ? parent.getContext() : null).inflate(R.layout.item_note_timeline, parent, false);
        if (timelineItemView != null) {
            return new NoteTimelineViewHolder(timelineItemView);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.cooey.common.views.TimelineItemView");
    }
}
