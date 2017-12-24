package com.cooey.android.vitals.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.android.vitals.views.VitalInputDialogFragment;
import com.cooey.common.vo.Session;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: VitalBlueprintSelectionRecyclerAdapter.kt */
final class C0650x172864fd implements OnClickListener {
    final /* synthetic */ FragmentManager $fragmentManager;
    final /* synthetic */ Session $session;
    final /* synthetic */ String $userId;
    final /* synthetic */ VitalBlueprint $vitalBlueprint;
    final /* synthetic */ VitalRepository $vitalRepository;

    C0650x172864fd(VitalBlueprint vitalBlueprint, String str, Session session, VitalRepository vitalRepository, FragmentManager fragmentManager) {
        this.$vitalBlueprint = vitalBlueprint;
        this.$userId = str;
        this.$session = session;
        this.$vitalRepository = vitalRepository;
        this.$fragmentManager = fragmentManager;
    }

    public final void onClick(View it) {
        FragmentTransaction transaction;
        VitalInputDialogFragment vitalInputFragment = VitalInputDialogFragment.Companion.newIntance();
        vitalInputFragment.setVitalBlueprint(this.$vitalBlueprint);
        vitalInputFragment.setUserId(this.$userId);
        vitalInputFragment.setSession(this.$session);
        vitalInputFragment.setVitalRepository(this.$vitalRepository);
        FragmentManager fragmentManager = this.$fragmentManager;
        if (fragmentManager != null) {
            transaction = fragmentManager.beginTransaction();
        } else {
            transaction = null;
        }
        if (transaction != null) {
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
        if (transaction != null) {
            FragmentTransaction add = transaction.add(16908290, (Fragment) vitalInputFragment);
            if (add != null) {
                FragmentTransaction addToBackStack = add.addToBackStack(null);
                if (addToBackStack != null) {
                    addToBackStack.commit();
                }
            }
        }
    }
}
