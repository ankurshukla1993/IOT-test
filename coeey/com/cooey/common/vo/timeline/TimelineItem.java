package com.cooey.common.vo.timeline;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.facebook.share.internal.ShareConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J=\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0005HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\b\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000e¨\u0006&"}, d2 = {"Lcom/cooey/common/vo/timeline/TimelineItem;", "", "id", "", "type", "", "timestamp", "", "userId", "subType", "(Ljava/lang/String;IJLjava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getSubType", "setSubType", "getTimestamp", "()J", "setTimestamp", "(J)V", "getType", "()I", "setType", "(I)V", "getUserId", "setUserId", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "common_release"}, k = 1, mv = {1, 1, 7})
@Entity(tableName = "timelineItems")
/* compiled from: TimelineItem.kt */
public final class TimelineItem {
    @PrimaryKey
    @NotNull
    private String id;
    @Nullable
    private String subType;
    private long timestamp;
    private int type;
    @NotNull
    private String userId;

    @NotNull
    public static /* bridge */ /* synthetic */ TimelineItem copy$default(TimelineItem timelineItem, String str, int i, long j, String str2, String str3, int i2, Object obj) {
        return timelineItem.copy((i2 & 1) != 0 ? timelineItem.id : str, (i2 & 2) != 0 ? timelineItem.type : i, (i2 & 4) != 0 ? timelineItem.timestamp : j, (i2 & 8) != 0 ? timelineItem.userId : str2, (i2 & 16) != 0 ? timelineItem.subType : str3);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    public final int component2() {
        return this.type;
    }

    public final long component3() {
        return this.timestamp;
    }

    @NotNull
    public final String component4() {
        return this.userId;
    }

    @Nullable
    public final String component5() {
        return this.subType;
    }

    @NotNull
    public final TimelineItem copy(@NotNull String id, int type, long timestamp, @NotNull String userId, @Nullable String subType) {
        Intrinsics.checkParameterIsNotNull(id, ShareConstants.WEB_DIALOG_PARAM_ID);
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        return new TimelineItem(id, type, timestamp, userId, subType);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (!(obj instanceof TimelineItem)) {
                return false;
            }
            TimelineItem timelineItem = (TimelineItem) obj;
            if (!Intrinsics.areEqual(this.id, timelineItem.id)) {
                return false;
            }
            if (!(this.type == timelineItem.type)) {
                return false;
            }
            if (!((this.timestamp == timelineItem.timestamp) && Intrinsics.areEqual(this.userId, timelineItem.userId) && Intrinsics.areEqual(this.subType, timelineItem.subType))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        String str = this.id;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.type) * 31;
        long j = this.timestamp;
        int i2 = (hashCode + ((int) (j ^ (j >>> 32)))) * 31;
        str = this.userId;
        hashCode = ((str != null ? str.hashCode() : 0) + i2) * 31;
        String str2 = this.subType;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "TimelineItem(id=" + this.id + ", type=" + this.type + ", timestamp=" + this.timestamp + ", userId=" + this.userId + ", subType=" + this.subType + ")";
    }

    public TimelineItem(@NotNull String id, int type, long timestamp, @NotNull String userId, @Nullable String subType) {
        Intrinsics.checkParameterIsNotNull(id, ShareConstants.WEB_DIALOG_PARAM_ID);
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
        this.userId = userId;
        this.subType = subType;
    }

    public /* synthetic */ TimelineItem(String str, int i, long j, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, j, str2, (i2 & 16) != 0 ? (String) null : str3);
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @Nullable
    public final String getSubType() {
        return this.subType;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final int getType() {
        return this.type;
    }

    @NotNull
    public final String getUserId() {
        return this.userId;
    }

    public final void setId(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.id = <set-?>;
    }

    public final void setSubType(@Nullable String <set-?>) {
        this.subType = <set-?>;
    }

    public final void setTimestamp(long <set-?>) {
        this.timestamp = <set-?>;
    }

    public final void setType(int <set-?>) {
        this.type = <set-?>;
    }

    public final void setUserId(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.userId = <set-?>;
    }
}
