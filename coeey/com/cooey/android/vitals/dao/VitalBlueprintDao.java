package com.cooey.android.vitals.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.cooey.android.vitals.VitalBlueprint;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Dao
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\b\u0010\u0006\u001a\u00020\u0007H'J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n0\tH'J\u001c\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n0\t2\u0006\u0010\u000b\u001a\u00020\fH'J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH'J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e2\u0006\u0010\u000b\u001a\u00020\fH'J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H'J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\u0012\u001a\u00020\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH'¨\u0006\u0014"}, d2 = {"Lcom/cooey/android/vitals/dao/VitalBlueprintDao;", "", "delete", "", "vitalBlueprint", "Lcom/cooey/android/vitals/VitalBlueprint;", "deleteAll", "", "getAll", "Landroid/arch/lifecycle/LiveData;", "", "isPrimary", "", "getAllSync", "", "getVitalBlueprintForType", "type", "", "insert", "vitalBlueprints", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalBlueprintDao.kt */
public interface VitalBlueprintDao {
    @Delete
    void delete(@NotNull VitalBlueprint vitalBlueprint);

    @Query("DELETE FROM vitalBlueprints")
    int deleteAll();

    @Query("SELECT * FROM vitalBlueprints")
    @NotNull
    LiveData<List<VitalBlueprint>> getAll();

    @Query("SELECT * FROM vitalBlueprints WHERE isPrimary = :isPrimary")
    @NotNull
    LiveData<List<VitalBlueprint>> getAll(boolean z);

    @Query("SELECT * FROM vitalBlueprints")
    @NotNull
    List<VitalBlueprint> getAllSync();

    @Query("SELECT * FROM vitalBlueprints WHERE isPrimary = :isPrimary")
    @NotNull
    List<VitalBlueprint> getAllSync(boolean z);

    @Query("SELECT * FROM vitalBlueprints WHERE type = :type LIMIT 1")
    @NotNull
    VitalBlueprint getVitalBlueprintForType(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull VitalBlueprint vitalBlueprint);

    @Insert(onConflict = 1)
    void insert(@NotNull List<VitalBlueprint> list);
}
