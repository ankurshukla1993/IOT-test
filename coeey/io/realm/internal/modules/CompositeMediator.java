package io.realm.internal.modules;

import android.util.JsonReader;
import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmObjectProxy.CacheData;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import io.realm.internal.Util;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class CompositeMediator extends RealmProxyMediator {
    private final Map<Class<? extends RealmModel>, RealmProxyMediator> mediators;

    public CompositeMediator(RealmProxyMediator... mediators) {
        HashMap<Class<? extends RealmModel>, RealmProxyMediator> tempMediators = new HashMap();
        if (mediators != null) {
            for (RealmProxyMediator mediator : mediators) {
                for (Class<? extends RealmModel> realmClass : mediator.getModelClasses()) {
                    tempMediators.put(realmClass, mediator);
                }
            }
        }
        this.mediators = Collections.unmodifiableMap(tempMediators);
    }

    public Map<Class<? extends RealmModel>, OsObjectSchemaInfo> getExpectedObjectSchemaInfoMap() {
        Map<Class<? extends RealmModel>, OsObjectSchemaInfo> infoMap = new HashMap();
        for (RealmProxyMediator mediator : this.mediators.values()) {
            infoMap.putAll(mediator.getExpectedObjectSchemaInfoMap());
        }
        return infoMap;
    }

    public ColumnInfo createColumnInfo(Class<? extends RealmModel> clazz, OsSchemaInfo osSchemaInfo) {
        return getMediator(clazz).createColumnInfo(clazz, osSchemaInfo);
    }

    public List<String> getFieldNames(Class<? extends RealmModel> clazz) {
        return getMediator(clazz).getFieldNames(clazz);
    }

    public String getTableName(Class<? extends RealmModel> clazz) {
        return getMediator(clazz).getTableName(clazz);
    }

    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        return getMediator(clazz).newInstance(clazz, baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
    }

    public Set<Class<? extends RealmModel>> getModelClasses() {
        return this.mediators.keySet();
    }

    public <E extends RealmModel> E copyOrUpdate(Realm realm, E object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        return getMediator(Util.getOriginalModelClass(object.getClass())).copyOrUpdate(realm, object, update, cache);
    }

    public void insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        getMediator(Util.getOriginalModelClass(object.getClass())).insert(realm, object, cache);
    }

    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        getMediator(Util.getOriginalModelClass(Util.getOriginalModelClass(((RealmModel) objects.iterator().next()).getClass()))).insert(realm, objects);
    }

    public void insertOrUpdate(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        getMediator(Util.getOriginalModelClass(object.getClass())).insertOrUpdate(realm, object, cache);
    }

    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        getMediator(Util.getOriginalModelClass(Util.getOriginalModelClass(((RealmModel) objects.iterator().next()).getClass()))).insertOrUpdate(realm, objects);
    }

    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update) throws JSONException {
        return getMediator(clazz).createOrUpdateUsingJsonObject(clazz, realm, json, update);
    }

    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader) throws IOException {
        return getMediator(clazz).createUsingJsonStream(clazz, realm, reader);
    }

    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        return getMediator(Util.getOriginalModelClass(realmObject.getClass())).createDetachedCopy(realmObject, maxDepth, cache);
    }

    public boolean transformerApplied() {
        for (Entry<Class<? extends RealmModel>, RealmProxyMediator> entry : this.mediators.entrySet()) {
            if (!((RealmProxyMediator) entry.getValue()).transformerApplied()) {
                return false;
            }
        }
        return true;
    }

    private RealmProxyMediator getMediator(Class<? extends RealmModel> clazz) {
        RealmProxyMediator mediator = (RealmProxyMediator) this.mediators.get(clazz);
        if (mediator != null) {
            return mediator;
        }
        throw new IllegalArgumentException(clazz.getSimpleName() + " is not part of the schema for this Realm");
    }
}
