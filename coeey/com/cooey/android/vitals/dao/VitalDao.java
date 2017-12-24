package com.cooey.android.vitals.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.cooey.android.vitals.Vital;
import com.cooey.android.vitals.VitalBlueprint;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Dao
@Metadata(bv = {1, 0, 2}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\b\u0010\u0006\u001a\u00020\u0007H'J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH'J\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH'J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\u0011\u001a\u00020\u000eH'J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\u000f\u001a\u00020\u000eH'J$\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH'J$\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH'J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\u0015\u001a\u00020\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\tH'J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\u0018"}, d2 = {"Lcom/cooey/android/vitals/dao/VitalDao;", "", "delete", "", "vital", "Lcom/cooey/android/vitals/Vital;", "deleteAll", "", "getAll", "", "Lcom/cooey/android/vitals/VitalBlueprint;", "getLatestVitalForTypeByTimestampDesc", "Landroid/arch/lifecycle/LiveData;", "type", "", "userId", "getVital", "id", "getVitalsForPatient", "getVitalsForTypeByTimestampAsc", "getVitalsForTypeByTimestampDesc", "insert", "vitals", "update", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalDao.kt */
public interface VitalDao {
    @Delete
    void delete(@NotNull Vital vital);

    @Query("DELETE FROM vitals")
    int deleteAll();

    @Query("SELECT * FROM vitals")
    @NotNull
    List<VitalBlueprint> getAll();

    @Query("SELECT * FROM vitals WHERE vitalType = :type AND userId = :userId ORDER BY takenOn DESC LIMIT 1")
    @NotNull
    LiveData<Vital> getLatestVitalForTypeByTimestampDesc(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM vitals WHERE id = :id LIMIT 1")
    @NotNull
    LiveData<Vital> getVital(@NotNull String str);

    @Query("SELECT * FROM vitals WHERE userId = :userId")
    @NotNull
    LiveData<Vital> getVitalsForPatient(@NotNull String str);

    @Query("SELECT * FROM vitals WHERE vitalType = :type AND userId = :userId ORDER BY takenOn ASC")
    @NotNull
    LiveData<List<Vital>> getVitalsForTypeByTimestampAsc(@NotNull String str, @NotNull String str2);

    @Query("SELECT * FROM vitals WHERE vitalType = :type AND userId = :userId ORDER BY takenOn DESC")
    @NotNull
    LiveData<List<Vital>> getVitalsForTypeByTimestampDesc(@NotNull String str, @NotNull String str2);

    @Insert(onConflict = 1)
    void insert(@NotNull Vital vital);

    @Insert(onConflict = 1)
    void insert(@NotNull List<Vital> list);

    @Update
    void update(@NotNull Vital vital);
}
