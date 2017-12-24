package com.cooey.android.vitals.repositories;

import android.arch.lifecycle.LiveData;
import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.android.vitals.dao.VitalBlueprintDao;
import com.cooey.android.vitals.services.VitalBlueprintService;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010J\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u00102\u0006\u0010\u0013\u001a\u00020\u0014J\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u0016J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u00162\u0006\u0010\u0013\u001a\u00020\u0014J\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00160\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001aR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "", "vitalBlueprintService", "Lcom/cooey/android/vitals/services/VitalBlueprintService;", "vitalBlueprintDao", "Lcom/cooey/android/vitals/dao/VitalBlueprintDao;", "(Lcom/cooey/android/vitals/services/VitalBlueprintService;Lcom/cooey/android/vitals/dao/VitalBlueprintDao;)V", "getVitalBlueprintDao", "()Lcom/cooey/android/vitals/dao/VitalBlueprintDao;", "setVitalBlueprintDao", "(Lcom/cooey/android/vitals/dao/VitalBlueprintDao;)V", "getVitalBlueprintService", "()Lcom/cooey/android/vitals/services/VitalBlueprintService;", "setVitalBlueprintService", "(Lcom/cooey/android/vitals/services/VitalBlueprintService;)V", "getVitalBlueprintsFromDatabase", "Landroid/arch/lifecycle/LiveData;", "", "Lcom/cooey/android/vitals/VitalBlueprint;", "isPrimary", "", "getVitalBlueprintsFromDatabaseSync", "", "getVitalsBlueprintsFromServer", "Lretrofit2/Call;", "tenantId", "", "syncVitalBlueprints", "", "token", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalBlueprintsRepository.kt */
public final class VitalBlueprintsRepository {
    @NotNull
    private VitalBlueprintDao vitalBlueprintDao;
    @NotNull
    private VitalBlueprintService vitalBlueprintService;

    public VitalBlueprintsRepository(@NotNull VitalBlueprintService vitalBlueprintService, @NotNull VitalBlueprintDao vitalBlueprintDao) {
        Intrinsics.checkParameterIsNotNull(vitalBlueprintService, "vitalBlueprintService");
        Intrinsics.checkParameterIsNotNull(vitalBlueprintDao, "vitalBlueprintDao");
        this.vitalBlueprintService = vitalBlueprintService;
        this.vitalBlueprintDao = vitalBlueprintDao;
    }

    @NotNull
    public final VitalBlueprintDao getVitalBlueprintDao() {
        return this.vitalBlueprintDao;
    }

    @NotNull
    public final VitalBlueprintService getVitalBlueprintService() {
        return this.vitalBlueprintService;
    }

    public final void setVitalBlueprintDao(@NotNull VitalBlueprintDao <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.vitalBlueprintDao = <set-?>;
    }

    public final void setVitalBlueprintService(@NotNull VitalBlueprintService <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.vitalBlueprintService = <set-?>;
    }

    public final void syncVitalBlueprints(@NotNull String token, @NotNull String tenantId) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        Intrinsics.checkParameterIsNotNull(tenantId, "tenantId");
        Call vitalBlueprintsForTenant = this.vitalBlueprintService.getVitalBlueprintsForTenant(token, tenantId);
        if (vitalBlueprintsForTenant != null) {
            vitalBlueprintsForTenant.enqueue(new VitalBlueprintsRepository$syncVitalBlueprints$1(this));
        }
    }

    @NotNull
    public final Call<List<VitalBlueprint>> getVitalsBlueprintsFromServer(@NotNull String tenantId) {
        Intrinsics.checkParameterIsNotNull(tenantId, "tenantId");
        Call<List<VitalBlueprint>> vitalBlueprintsForTenant = this.vitalBlueprintService.getVitalBlueprintsForTenant("token", tenantId);
        if (vitalBlueprintsForTenant == null) {
            Intrinsics.throwNpe();
        }
        return vitalBlueprintsForTenant;
    }

    @NotNull
    public final LiveData<List<VitalBlueprint>> getVitalBlueprintsFromDatabase() {
        return this.vitalBlueprintDao.getAll();
    }

    @NotNull
    public final LiveData<List<VitalBlueprint>> getVitalBlueprintsFromDatabase(boolean isPrimary) {
        return this.vitalBlueprintDao.getAll(isPrimary);
    }

    @NotNull
    public final List<VitalBlueprint> getVitalBlueprintsFromDatabaseSync() {
        return this.vitalBlueprintDao.getAllSync();
    }

    @NotNull
    public final List<VitalBlueprint> getVitalBlueprintsFromDatabaseSync(boolean isPrimary) {
        return this.vitalBlueprintDao.getAllSync(isPrimary);
    }
}
