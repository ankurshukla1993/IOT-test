package com.biz.health.cooey_app;

import android.view.View;
import android.view.View.OnClickListener;
import com.cooey.common.generators.TimelineItemTypeSelectionDialogGenerator;
import com.cooey.common.vo.timeline.TimelineItemType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: MainActivity.kt */
final class MainActivity$initalize$2 implements OnClickListener {
    final /* synthetic */ MainActivity this$0;

    MainActivity$initalize$2(MainActivity mainActivity) {
        this.this$0 = mainActivity;
    }

    public final void onClick(View it) {
        TimelineItemTypeSelectionDialogGenerator.Companion.generate(this.this$0, new Function1<boolean[], Unit>() {

            @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 7})
            /* compiled from: MainActivity.kt */
            static final class C06331 extends Lambda implements Function1<Integer, Boolean> {
                public static final C06331 INSTANCE = new C06331();

                C06331() {
                    super(1);
                }

                public final boolean invoke(int it) {
                    return true;
                }
            }

            public final void invoke(@NotNull boolean[] booleans) {
                Intrinsics.checkParameterIsNotNull(booleans, "booleans");
                CollectionsKt__MutableCollectionsKt.removeAll(this.this$0.getFilteredTypes(), (Function1) C06331.INSTANCE);
                if (booleans[0]) {
                    this.this$0.getFilteredTypes().add(Integer.valueOf(TimelineItemType.INSTANCE.getVITAL()));
                }
                if (booleans[1]) {
                    this.this$0.getFilteredTypes().add(Integer.valueOf(TimelineItemType.INSTANCE.getNOTE()));
                }
                if (booleans[2]) {
                    this.this$0.getFilteredTypes().add(Integer.valueOf(TimelineItemType.INSTANCE.getACTION()));
                }
                if (booleans[3]) {
                    this.this$0.getFilteredTypes().add(Integer.valueOf(TimelineItemType.INSTANCE.getEVENT()));
                }
                this.this$0.initTimelineAdapterData();
            }
        }).show();
    }
}
