package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.diet.time.BreakFast;
import com.cooey.common.vo.diet.time.Dinner;
import com.cooey.common.vo.diet.time.Lunch;
import com.cooey.common.vo.diet.weekdays.Tuesday;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsObjectSchemaInfo.Builder;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmObjectProxy.CacheData;
import io.realm.internal.Row;
import io.realm.internal.Table;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class TuesdayRealmProxy extends Tuesday implements RealmObjectProxy, TuesdayRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private TuesdayColumnInfo columnInfo;
    private ProxyState<Tuesday> proxyState;

    static final class TuesdayColumnInfo extends ColumnInfo {
        long breakFastIndex;
        long dinnerIndex;
        long lunchIndex;

        TuesdayColumnInfo(OsSchemaInfo schemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Tuesday");
            this.breakFastIndex = addColumnDetails("breakFast", objectSchemaInfo);
            this.lunchIndex = addColumnDetails("lunch", objectSchemaInfo);
            this.dinnerIndex = addColumnDetails("dinner", objectSchemaInfo);
        }

        TuesdayColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new TuesdayColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            TuesdayColumnInfo src = (TuesdayColumnInfo) rawSrc;
            TuesdayColumnInfo dst = (TuesdayColumnInfo) rawDst;
            dst.breakFastIndex = src.breakFastIndex;
            dst.lunchIndex = src.lunchIndex;
            dst.dinnerIndex = src.dinnerIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("breakFast");
        fieldNames.add("lunch");
        fieldNames.add("dinner");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TuesdayRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (TuesdayColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public BreakFast realmGet$breakFast() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.breakFastIndex)) {
            return null;
        }
        return (BreakFast) this.proxyState.getRealm$realm().get(BreakFast.class, this.proxyState.getRow$realm().getLink(this.columnInfo.breakFastIndex), false, Collections.emptyList());
    }

    public void realmSet$breakFast(BreakFast value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.breakFastIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.breakFastIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("breakFast")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (BreakFast) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.breakFastIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.breakFastIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public Lunch realmGet$lunch() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.lunchIndex)) {
            return null;
        }
        return (Lunch) this.proxyState.getRealm$realm().get(Lunch.class, this.proxyState.getRow$realm().getLink(this.columnInfo.lunchIndex), false, Collections.emptyList());
    }

    public void realmSet$lunch(Lunch value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.lunchIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.lunchIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("lunch")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Lunch) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.lunchIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.lunchIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public Dinner realmGet$dinner() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.dinnerIndex)) {
            return null;
        }
        return (Dinner) this.proxyState.getRealm$realm().get(Dinner.class, this.proxyState.getRow$realm().getLink(this.columnInfo.dinnerIndex), false, Collections.emptyList());
    }

    public void realmSet$dinner(Dinner value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.dinnerIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.dinnerIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("dinner")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Dinner) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.dinnerIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.dinnerIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Tuesday");
        builder.addPersistedLinkProperty("breakFast", RealmFieldType.OBJECT, "BreakFast");
        builder.addPersistedLinkProperty("lunch", RealmFieldType.OBJECT, "Lunch");
        builder.addPersistedLinkProperty("dinner", RealmFieldType.OBJECT, "Dinner");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TuesdayColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new TuesdayColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Tuesday";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Tuesday createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(3);
        if (json.has("breakFast")) {
            excludeFields.add("breakFast");
        }
        if (json.has("lunch")) {
            excludeFields.add("lunch");
        }
        if (json.has("dinner")) {
            excludeFields.add("dinner");
        }
        Tuesday obj = (Tuesday) realm.createObjectInternal(Tuesday.class, true, excludeFields);
        TuesdayRealmProxyInterface objProxy = obj;
        if (json.has("breakFast")) {
            if (json.isNull("breakFast")) {
                objProxy.realmSet$breakFast(null);
            } else {
                objProxy.realmSet$breakFast(BreakFastRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("breakFast"), update));
            }
        }
        if (json.has("lunch")) {
            if (json.isNull("lunch")) {
                objProxy.realmSet$lunch(null);
            } else {
                objProxy.realmSet$lunch(LunchRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("lunch"), update));
            }
        }
        if (json.has("dinner")) {
            if (json.isNull("dinner")) {
                objProxy.realmSet$dinner(null);
            } else {
                objProxy.realmSet$dinner(DinnerRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("dinner"), update));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static Tuesday createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Tuesday obj = new Tuesday();
        TuesdayRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("breakFast")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$breakFast(null);
                } else {
                    objProxy.realmSet$breakFast(BreakFastRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("lunch")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$lunch(null);
                } else {
                    objProxy.realmSet$lunch(LunchRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (!name.equals("dinner")) {
                reader.skipValue();
            } else if (reader.peek() == JsonToken.NULL) {
                reader.skipValue();
                objProxy.realmSet$dinner(null);
            } else {
                objProxy.realmSet$dinner(DinnerRealmProxy.createUsingJsonStream(realm, reader));
            }
        }
        reader.endObject();
        return (Tuesday) realm.copyToRealm(obj);
    }

    public static Tuesday copyOrUpdate(Realm realm, Tuesday object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            } else if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(object);
        if (cachedRealmObject != null) {
            return (Tuesday) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Tuesday copy(Realm realm, Tuesday newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Tuesday) cachedRealmObject;
        }
        Tuesday realmObject = (Tuesday) realm.createObjectInternal(Tuesday.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        TuesdayRealmProxyInterface realmObjectSource = newObject;
        TuesdayRealmProxyInterface realmObjectCopy = realmObject;
        BreakFast breakFastObj = realmObjectSource.realmGet$breakFast();
        if (breakFastObj == null) {
            realmObjectCopy.realmSet$breakFast(null);
        } else {
            BreakFast cachebreakFast = (BreakFast) cache.get(breakFastObj);
            if (cachebreakFast != null) {
                realmObjectCopy.realmSet$breakFast(cachebreakFast);
            } else {
                realmObjectCopy.realmSet$breakFast(BreakFastRealmProxy.copyOrUpdate(realm, breakFastObj, update, cache));
            }
        }
        Lunch lunchObj = realmObjectSource.realmGet$lunch();
        if (lunchObj == null) {
            realmObjectCopy.realmSet$lunch(null);
        } else {
            Lunch cachelunch = (Lunch) cache.get(lunchObj);
            if (cachelunch != null) {
                realmObjectCopy.realmSet$lunch(cachelunch);
            } else {
                realmObjectCopy.realmSet$lunch(LunchRealmProxy.copyOrUpdate(realm, lunchObj, update, cache));
            }
        }
        Dinner dinnerObj = realmObjectSource.realmGet$dinner();
        if (dinnerObj == null) {
            realmObjectCopy.realmSet$dinner(null);
        } else {
            Dinner cachedinner = (Dinner) cache.get(dinnerObj);
            if (cachedinner != null) {
                realmObjectCopy.realmSet$dinner(cachedinner);
            } else {
                realmObjectCopy.realmSet$dinner(DinnerRealmProxy.copyOrUpdate(realm, dinnerObj, update, cache));
            }
        }
        return realmObject;
    }

    public static long insert(Realm realm, Tuesday object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Tuesday.class);
        long tableNativePtr = table.getNativePtr();
        TuesdayColumnInfo columnInfo = (TuesdayColumnInfo) realm.getSchema().getColumnInfo(Tuesday.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        BreakFast breakFastObj = object.realmGet$breakFast();
        if (breakFastObj != null) {
            Long cachebreakFast = (Long) cache.get(breakFastObj);
            if (cachebreakFast == null) {
                cachebreakFast = Long.valueOf(BreakFastRealmProxy.insert(realm, breakFastObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.breakFastIndex, rowIndex, cachebreakFast.longValue(), false);
        }
        Lunch lunchObj = object.realmGet$lunch();
        if (lunchObj != null) {
            Long cachelunch = (Long) cache.get(lunchObj);
            if (cachelunch == null) {
                cachelunch = Long.valueOf(LunchRealmProxy.insert(realm, lunchObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.lunchIndex, rowIndex, cachelunch.longValue(), false);
        }
        Dinner dinnerObj = object.realmGet$dinner();
        if (dinnerObj == null) {
            return rowIndex;
        }
        Long cachedinner = (Long) cache.get(dinnerObj);
        if (cachedinner == null) {
            cachedinner = Long.valueOf(DinnerRealmProxy.insert(realm, dinnerObj, (Map) cache));
        }
        Table.nativeSetLink(tableNativePtr, columnInfo.dinnerIndex, rowIndex, cachedinner.longValue(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Tuesday.class);
        long tableNativePtr = table.getNativePtr();
        TuesdayColumnInfo columnInfo = (TuesdayColumnInfo) realm.getSchema().getColumnInfo(Tuesday.class);
        while (objects.hasNext()) {
            Tuesday object = (Tuesday) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    BreakFast breakFastObj = object.realmGet$breakFast();
                    if (breakFastObj != null) {
                        Long cachebreakFast = (Long) cache.get(breakFastObj);
                        if (cachebreakFast == null) {
                            cachebreakFast = Long.valueOf(BreakFastRealmProxy.insert(realm, breakFastObj, (Map) cache));
                        }
                        table.setLink(columnInfo.breakFastIndex, rowIndex, cachebreakFast.longValue(), false);
                    }
                    Lunch lunchObj = object.realmGet$lunch();
                    if (lunchObj != null) {
                        Long cachelunch = (Long) cache.get(lunchObj);
                        if (cachelunch == null) {
                            cachelunch = Long.valueOf(LunchRealmProxy.insert(realm, lunchObj, (Map) cache));
                        }
                        table.setLink(columnInfo.lunchIndex, rowIndex, cachelunch.longValue(), false);
                    }
                    Dinner dinnerObj = object.realmGet$dinner();
                    if (dinnerObj != null) {
                        Long cachedinner = (Long) cache.get(dinnerObj);
                        if (cachedinner == null) {
                            cachedinner = Long.valueOf(DinnerRealmProxy.insert(realm, dinnerObj, (Map) cache));
                        }
                        table.setLink(columnInfo.dinnerIndex, rowIndex, cachedinner.longValue(), false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Tuesday object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Tuesday.class);
        long tableNativePtr = table.getNativePtr();
        TuesdayColumnInfo columnInfo = (TuesdayColumnInfo) realm.getSchema().getColumnInfo(Tuesday.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        BreakFast breakFastObj = object.realmGet$breakFast();
        if (breakFastObj != null) {
            Long cachebreakFast = (Long) cache.get(breakFastObj);
            if (cachebreakFast == null) {
                cachebreakFast = Long.valueOf(BreakFastRealmProxy.insertOrUpdate(realm, breakFastObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.breakFastIndex, rowIndex, cachebreakFast.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.breakFastIndex, rowIndex);
        }
        Lunch lunchObj = object.realmGet$lunch();
        if (lunchObj != null) {
            Long cachelunch = (Long) cache.get(lunchObj);
            if (cachelunch == null) {
                cachelunch = Long.valueOf(LunchRealmProxy.insertOrUpdate(realm, lunchObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.lunchIndex, rowIndex, cachelunch.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.lunchIndex, rowIndex);
        }
        Dinner dinnerObj = object.realmGet$dinner();
        if (dinnerObj != null) {
            Long cachedinner = (Long) cache.get(dinnerObj);
            if (cachedinner == null) {
                cachedinner = Long.valueOf(DinnerRealmProxy.insertOrUpdate(realm, dinnerObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.dinnerIndex, rowIndex, cachedinner.longValue(), false);
            return rowIndex;
        }
        Table.nativeNullifyLink(tableNativePtr, columnInfo.dinnerIndex, rowIndex);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Tuesday.class);
        long tableNativePtr = table.getNativePtr();
        TuesdayColumnInfo columnInfo = (TuesdayColumnInfo) realm.getSchema().getColumnInfo(Tuesday.class);
        while (objects.hasNext()) {
            Tuesday object = (Tuesday) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    BreakFast breakFastObj = object.realmGet$breakFast();
                    if (breakFastObj != null) {
                        Long cachebreakFast = (Long) cache.get(breakFastObj);
                        if (cachebreakFast == null) {
                            cachebreakFast = Long.valueOf(BreakFastRealmProxy.insertOrUpdate(realm, breakFastObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.breakFastIndex, rowIndex, cachebreakFast.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.breakFastIndex, rowIndex);
                    }
                    Lunch lunchObj = object.realmGet$lunch();
                    if (lunchObj != null) {
                        Long cachelunch = (Long) cache.get(lunchObj);
                        if (cachelunch == null) {
                            cachelunch = Long.valueOf(LunchRealmProxy.insertOrUpdate(realm, lunchObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.lunchIndex, rowIndex, cachelunch.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.lunchIndex, rowIndex);
                    }
                    Dinner dinnerObj = object.realmGet$dinner();
                    if (dinnerObj != null) {
                        Long cachedinner = (Long) cache.get(dinnerObj);
                        if (cachedinner == null) {
                            cachedinner = Long.valueOf(DinnerRealmProxy.insertOrUpdate(realm, dinnerObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.dinnerIndex, rowIndex, cachedinner.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.dinnerIndex, rowIndex);
                    }
                }
            }
        }
    }

    public static Tuesday createDetachedCopy(Tuesday realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Tuesday unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Tuesday();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Tuesday) cachedObject.object;
        } else {
            unmanagedObject = (Tuesday) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        TuesdayRealmProxyInterface unmanagedCopy = unmanagedObject;
        TuesdayRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$breakFast(BreakFastRealmProxy.createDetachedCopy(realmSource.realmGet$breakFast(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$lunch(LunchRealmProxy.createDetachedCopy(realmSource.realmGet$lunch(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$dinner(DinnerRealmProxy.createDetachedCopy(realmSource.realmGet$dinner(), currentDepth + 1, maxDepth, cache));
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Tuesday = proxy[");
        stringBuilder.append("{breakFast:");
        stringBuilder.append(realmGet$breakFast() != null ? "BreakFast" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{lunch:");
        stringBuilder.append(realmGet$lunch() != null ? "Lunch" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dinner:");
        stringBuilder.append(realmGet$dinner() != null ? "Dinner" : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public ProxyState<?> realmGet$proxyState() {
        return this.proxyState;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        String realmName = this.proxyState.getRealm$realm().getPath();
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        long rowIndex = this.proxyState.getRow$realm().getIndex();
        if (realmName != null) {
            hashCode = realmName.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + 527) * 31;
        if (tableName != null) {
            i = tableName.hashCode();
        }
        return ((hashCode + i) * 31) + ((int) ((rowIndex >>> 32) ^ rowIndex));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TuesdayRealmProxy aTuesday = (TuesdayRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aTuesday.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTuesday.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aTuesday.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
