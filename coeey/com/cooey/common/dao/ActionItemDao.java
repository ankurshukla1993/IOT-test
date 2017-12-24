package com.cooey.common.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.cooey.common.vo.ActionItem;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Dao
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J,\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\b0\u00032\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH'J\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\b0\u00032\u0006\u0010\t\u001a\u00020\u0006H'J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004H'J\u001b\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012H'¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/cooey/common/dao/ActionItemDao;", "", "getActionItem", "Landroid/arch/lifecycle/LiveData;", "Lcom/cooey/common/vo/ActionItem;", "id", "", "getActionItemsBetween", "", "patientId", "startTime", "", "endTime", "getActionItemsForUser", "insert", "", "actionItem", "actionItems", "", "([Lcom/cooey/common/vo/ActionItem;)V", "common_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: ActionItemDao.kt */
public interface ActionItemDao {
    @Query("SELECT * FROM actionItems WHERE id = :id LIMIT 1")
    @NotNull
    LiveData<ActionItem> getActionItem(@NotNull String str);

    @Query("SELECT * FROM actionItems WHERE patientId = :patientId AND scheduledOn > :startTime AND scheduledOn < :endTime ORDER BY scheduledOn DESC ")
    @NotNull
    LiveData<List<ActionItem>> getActionItemsBetween(@NotNull String str, long j, long j2);

    @Query("SELECT * FROM actionItems WHERE patientId = :patientId ORDER BY scheduledOn DESC")
    @NotNull
    LiveData<List<ActionItem>> getActionItemsForUser(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull ActionItem actionItem);

    @Insert(onConflict = 1)
    void insert(@NotNull ActionItem[] actionItemArr);
}
