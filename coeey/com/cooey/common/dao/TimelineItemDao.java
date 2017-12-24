package com.cooey.common.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.cooey.common.vo.timeline.TimelineItem;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Dao
@Metadata(bv = {1, 0, 2}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH'J,\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH'J/\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010\b\u001a\u00020\tH'¢\u0006\u0002\u0010\u000fJ=\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u000e2\u0006\u0010\b\u001a\u00020\tH'¢\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\b\u001a\u00020\tH'J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0005H'J\u0016\u0010\u0014\u001a\u00020\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H'¨\u0006\u0018"}, d2 = {"Lcom/cooey/common/dao/TimelineItemDao;", "", "getTimelineItemsByType", "Landroid/arch/lifecycle/LiveData;", "", "Lcom/cooey/common/vo/timeline/TimelineItem;", "type", "", "userId", "", "getTimelineItemsByTypeAndSubType", "subType", "getTimelineItemsByTypes", "types", "", "([Ljava/lang/Integer;Ljava/lang/String;)Landroid/arch/lifecycle/LiveData;", "getTimelineItemsByTypesAndSubTypes", "subTypes", "([Ljava/lang/Integer;[Ljava/lang/String;Ljava/lang/String;)Landroid/arch/lifecycle/LiveData;", "getTimelineItemsForUser", "insert", "", "timelineItem", "timelineItems", "common_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: TimelineItemDao.kt */
public interface TimelineItemDao {
    @Query("SELECT * FROM timelineItems WHERE type = :type AND userId = :userId ORDER by timestamp DESC")
    @NotNull
    LiveData<List<TimelineItem>> getTimelineItemsByType(int i, @NotNull String str);

    @Query("SELECT * FROM timelineItems WHERE type = :type AND subType = :subType AND userId = :userId ORDER by timestamp DESC")
    @NotNull
    LiveData<List<TimelineItem>> getTimelineItemsByTypeAndSubType(int i, @NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM timelineItems WHERE type IN (:types) AND userId = :userId ORDER by timestamp DESC")
    @NotNull
    LiveData<List<TimelineItem>> getTimelineItemsByTypes(@NotNull Integer[] numArr, @NotNull String str);

    @Query("SELECT * FROM timelineItems WHERE type IN (:types) AND subType IN (:subTypes) AND userId = :userId ORDER by timestamp DESC")
    @NotNull
    LiveData<List<TimelineItem>> getTimelineItemsByTypesAndSubTypes(@NotNull Integer[] numArr, @NotNull String[] strArr, @NotNull String str);

    @Query("SELECT * FROM timelineItems WHERE userId = :userId ORDER BY timestamp DESC")
    @NotNull
    LiveData<List<TimelineItem>> getTimelineItemsForUser(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull TimelineItem timelineItem);

    @Insert(onConflict = 1)
    void insert(@NotNull List<TimelineItem> list);
}
