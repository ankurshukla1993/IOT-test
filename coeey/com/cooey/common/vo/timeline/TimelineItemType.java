package com.cooey.common.vo.timeline;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/cooey/common/vo/timeline/TimelineItemType;", "", "()V", "ACTION", "", "getACTION", "()I", "EVENT", "getEVENT", "MEDICINE", "getMEDICINE", "NOTE", "getNOTE", "VITAL", "getVITAL", "common_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: TimelineItemType.kt */
public final class TimelineItemType {
    private static final int ACTION = 3;
    private static final int EVENT = 1;
    public static final TimelineItemType INSTANCE = null;
    private static final int MEDICINE = 4;
    private static final int NOTE = 2;
    private static final int VITAL = 0;

    static {
        TimelineItemType timelineItemType = new TimelineItemType();
    }

    private TimelineItemType() {
        INSTANCE = this;
        EVENT = 1;
        NOTE = 2;
        ACTION = 3;
        MEDICINE = 4;
    }

    public final int getVITAL() {
        return VITAL;
    }

    public final int getEVENT() {
        return EVENT;
    }

    public final int getNOTE() {
        return NOTE;
    }

    public final int getACTION() {
        return ACTION;
    }

    public final int getMEDICINE() {
        return MEDICINE;
    }
}
