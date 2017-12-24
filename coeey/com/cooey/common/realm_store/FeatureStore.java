package com.cooey.common.realm_store;

import android.content.Context;
import com.cooey.common.vo.ProtoRealm;
import com.cooey.common.vo.careplan.Feature;
import com.facebook.share.internal.ShareConstants;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.Sort;
import java.util.List;

public class FeatureStore {
    private Realm realm;

    public FeatureStore(Context context) {
        this.realm = ProtoRealm.getInstance(context).getRealm();
    }

    public FeatureStore(Realm realm) {
        this.realm = realm;
    }

    public static FeatureStore getInstance(Context context) {
        return new FeatureStore(context);
    }

    public void writeToFeature(final Feature feature) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(feature);
            }
        });
    }

    public void writeToFeatures(final List<Feature> feature) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(feature);
            }
        });
    }

    public Feature getFeatureById(String id) {
        return (Feature) this.realm.where(Feature.class).equalTo(ShareConstants.WEB_DIALOG_PARAM_ID, id).findFirst();
    }

    public List<Feature> getAllFeaturesByType(String type) {
        return this.realm.copyFromRealm(this.realm.where(Feature.class).equalTo("type", type).findAll());
    }

    public List<Feature> getAllFeatures() {
        return this.realm.where(Feature.class).findAllSorted("startTime", Sort.DESCENDING);
    }

    public Feature getCourse(String id) {
        return (Feature) this.realm.where(Feature.class).equalTo(ShareConstants.WEB_DIALOG_PARAM_ID, id).findFirst();
    }
}
