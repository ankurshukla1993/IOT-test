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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class FilterableMediator extends RealmProxyMediator {
    private final Set<Class<? extends RealmModel>> allowedClasses;
    private final RealmProxyMediator originalMediator;

    public FilterableMediator(RealmProxyMediator originalMediator, Collection<Class<? extends RealmModel>> allowedClasses) {
        this.originalMediator = originalMediator;
        Set<Class<? extends RealmModel>> tempAllowedClasses = new HashSet();
        if (originalMediator != null) {
            Set<Class<? extends RealmModel>> originalClasses = originalMediator.getModelClasses();
            for (Class<? extends RealmModel> clazz : allowedClasses) {
                if (originalClasses.contains(clazz)) {
                    tempAllowedClasses.add(clazz);
                }
            }
        }
        this.allowedClasses = Collections.unmodifiableSet(tempAllowedClasses);
    }

    public Map<Class<? extends RealmModel>, OsObjectSchemaInfo> getExpectedObjectSchemaInfoMap() {
        Map<Class<? extends RealmModel>, OsObjectSchemaInfo> infoMap = new HashMap();
        for (Entry<Class<? extends RealmModel>, OsObjectSchemaInfo> entry : this.originalMediator.getExpectedObjectSchemaInfoMap().entrySet()) {
            if (this.allowedClasses.contains(entry.getKey())) {
                infoMap.put(entry.getKey(), entry.getValue());
            }
        }
        return infoMap;
    }

    public ColumnInfo createColumnInfo(Class<? extends RealmModel> clazz, OsSchemaInfo osSchemaInfo) {
        checkSchemaHasClass(clazz);
        return this.originalMediator.createColumnInfo(clazz, osSchemaInfo);
    }

    public List<String> getFieldNames(Class<? extends RealmModel> clazz) {
        checkSchemaHasClass(clazz);
        return this.originalMediator.getFieldNames(clazz);
    }

    public String getTableName(Class<? extends RealmModel> clazz) {
        checkSchemaHasClass(clazz);
        return this.originalMediator.getTableName(clazz);
    }

    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        checkSchemaHasClass(clazz);
        return this.originalMediator.newInstance(clazz, baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
    }

    public Set<Class<? extends RealmModel>> getModelClasses() {
        return this.allowedClasses;
    }

    public <E extends RealmModel> E copyOrUpdate(Realm realm, E object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        checkSchemaHasClass(Util.getOriginalModelClass(object.getClass()));
        return this.originalMediator.copyOrUpdate(realm, object, update, cache);
    }

    public void insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        checkSchemaHasClass(Util.getOriginalModelClass(object.getClass()));
        this.originalMediator.insert(realm, object, cache);
    }

    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        checkSchemaHasClass(Util.getOriginalModelClass(((RealmModel) objects.iterator().next()).getClass()));
        this.originalMediator.insert(realm, objects);
    }

    public void insertOrUpdate(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        checkSchemaHasClass(Util.getOriginalModelClass(object.getClass()));
        this.originalMediator.insertOrUpdate(realm, object, cache);
    }

    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        checkSchemaHasClass(Util.getOriginalModelClass(((RealmModel) objects.iterator().next()).getClass()));
        this.originalMediator.insertOrUpdate(realm, objects);
    }

    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update) throws JSONException {
        checkSchemaHasClass(clazz);
        return this.originalMediator.createOrUpdateUsingJsonObject(clazz, realm, json, update);
    }

    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader) throws IOException {
        checkSchemaHasClass(clazz);
        return this.originalMediator.createUsingJsonStream(clazz, realm, reader);
    }

    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        checkSchemaHasClass(Util.getOriginalModelClass(realmObject.getClass()));
        return this.originalMediator.createDetachedCopy(realmObject, maxDepth, cache);
    }

    public boolean transformerApplied() {
        if (this.originalMediator == null) {
            return true;
        }
        return this.originalMediator.transformerApplied();
    }

    private void checkSchemaHasClass(Class<? extends RealmModel> clazz) {
        if (!this.allowedClasses.contains(clazz)) {
            throw new IllegalArgumentException(clazz.getSimpleName() + " is not part of the schema for this Realm");
        }
    }
}
