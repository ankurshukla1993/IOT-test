package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.RealmBoolean;
import com.cooey.common.vo.Reminder;
import com.facebook.share.internal.ShareConstants;
import io.realm.exceptions.RealmException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReminderRealmProxy extends Reminder implements RealmObjectProxy, ReminderRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private RealmList<RealmBoolean> activeDaysRealmList;
    private ReminderColumnInfo columnInfo;
    private ProxyState<Reminder> proxyState;

    static final class ReminderColumnInfo extends ColumnInfo {
        long activeDaysIndex;
        long idIndex;
        long timeOfDayIndex;

        ReminderColumnInfo(OsSchemaInfo schemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Reminder");
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.activeDaysIndex = addColumnDetails("activeDays", objectSchemaInfo);
            this.timeOfDayIndex = addColumnDetails("timeOfDay", objectSchemaInfo);
        }

        ReminderColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new ReminderColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ReminderColumnInfo src = (ReminderColumnInfo) rawSrc;
            ReminderColumnInfo dst = (ReminderColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.activeDaysIndex = src.activeDaysIndex;
            dst.timeOfDayIndex = src.timeOfDayIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("activeDays");
        fieldNames.add("timeOfDay");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ReminderRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ReminderColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$id() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.idIndex);
    }

    public void realmSet$id(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'id' cannot be changed after object was created.");
        }
    }

    public RealmList<RealmBoolean> realmGet$activeDays() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.activeDaysRealmList != null) {
            return this.activeDaysRealmList;
        }
        this.activeDaysRealmList = new RealmList(RealmBoolean.class, this.proxyState.getRow$realm().getLinkList(this.columnInfo.activeDaysIndex), this.proxyState.getRealm$realm());
        return this.activeDaysRealmList;
    }

    public void realmSet$activeDays(RealmList<RealmBoolean> value) {
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("activeDays")) {
                return;
            }
            if (!(value == null || value.isManaged())) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<RealmBoolean> original = value;
                value = new RealmList();
                Iterator it = original.iterator();
                while (it.hasNext()) {
                    RealmBoolean item = (RealmBoolean) it.next();
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList osList = this.proxyState.getRow$realm().getLinkList(this.columnInfo.activeDaysIndex);
        osList.removeAll();
        if (value != null) {
            Iterator it2 = value.iterator();
            while (it2.hasNext()) {
                RealmModel linkedObject = (RealmModel) it2.next();
                if (!RealmObject.isManaged(linkedObject) || !RealmObject.isValid(linkedObject)) {
                    throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
                } else if (((RealmObjectProxy) linkedObject).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                    throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
                } else {
                    osList.addRow(((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
                }
            }
        }
    }

    public String realmGet$timeOfDay() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.timeOfDayIndex);
    }

    public void realmSet$timeOfDay(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.timeOfDayIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.timeOfDayIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.timeOfDayIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.timeOfDayIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Reminder");
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, true, true, false);
        builder.addPersistedLinkProperty("activeDays", RealmFieldType.LIST, "RealmBoolean");
        builder.addPersistedProperty("timeOfDay", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ReminderColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ReminderColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Reminder";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Reminder createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(1);
        Reminder reminder = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(Reminder.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Reminder.class), false, Collections.emptyList());
                    reminder = new ReminderRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (reminder == null) {
            if (json.has("activeDays")) {
                excludeFields.add("activeDays");
            }
            if (json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                    reminder = (ReminderRealmProxy) realm.createObjectInternal(Reminder.class, null, true, excludeFields);
                } else {
                    String string = json.getString(ShareConstants.WEB_DIALOG_PARAM_ID);
                    ReminderRealmProxy obj = (ReminderRealmProxy) realm.createObjectInternal(Reminder.class, string, true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        ReminderRealmProxyInterface objProxy = reminder;
        if (json.has("activeDays")) {
            if (json.isNull("activeDays")) {
                objProxy.realmSet$activeDays(null);
            } else {
                objProxy.realmGet$activeDays().clear();
                JSONArray array = json.getJSONArray("activeDays");
                for (int i = 0; i < array.length(); i++) {
                    objProxy.realmGet$activeDays().add(RealmBooleanRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update));
                }
            }
        }
        if (json.has("timeOfDay")) {
            if (json.isNull("timeOfDay")) {
                objProxy.realmSet$timeOfDay(null);
            } else {
                objProxy.realmSet$timeOfDay(json.getString("timeOfDay"));
            }
        }
        return reminder;
    }

    @TargetApi(11)
    public static Reminder createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        Reminder obj = new Reminder();
        ReminderRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$id(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("activeDays")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$activeDays(null);
                } else {
                    objProxy.realmSet$activeDays(new RealmList());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        objProxy.realmGet$activeDays().add(RealmBooleanRealmProxy.createUsingJsonStream(realm, reader));
                    }
                    reader.endArray();
                }
            } else if (!name.equals("timeOfDay")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$timeOfDay(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$timeOfDay(null);
            }
        }
        reader.endObject();
        if (jsonHasPrimaryKey) {
            return (Reminder) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    public static Reminder copyOrUpdate(Realm realm, Reminder object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        Throwable th;
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
            return (Reminder) cachedRealmObject;
        }
        Reminder update2;
        Reminder realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(Reminder.class);
            long pkColumnIndex = table.getPrimaryKey();
            String value = object.realmGet$id();
            if (value == null) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, value);
            }
            if (rowIndex == -1) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Reminder.class), false, Collections.emptyList());
                    Reminder realmObject2 = new ReminderRealmProxy();
                    try {
                        cache.put(object, (RealmObjectProxy) realmObject2);
                        objectContext.clear();
                        realmObject = realmObject2;
                    } catch (Throwable th2) {
                        th = th2;
                        realmObject = realmObject2;
                        objectContext.clear();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    objectContext.clear();
                    throw th;
                }
            }
        }
        if (canUpdate) {
            update2 = update(realm, realmObject, object, cache);
        } else {
            update2 = copy(realm, object, update, cache);
        }
        return update2;
    }

    public static Reminder copy(Realm realm, Reminder newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Reminder) cachedRealmObject;
        }
        Reminder realmObject = (Reminder) realm.createObjectInternal(Reminder.class, newObject.realmGet$id(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ReminderRealmProxyInterface realmObjectSource = newObject;
        ReminderRealmProxyInterface realmObjectCopy = realmObject;
        RealmList<RealmBoolean> activeDaysList = realmObjectSource.realmGet$activeDays();
        if (activeDaysList != null) {
            RealmList<RealmBoolean> activeDaysRealmList = realmObjectCopy.realmGet$activeDays();
            activeDaysRealmList.clear();
            for (int i = 0; i < activeDaysList.size(); i++) {
                RealmBoolean activeDaysItem = (RealmBoolean) activeDaysList.get(i);
                RealmBoolean cacheactiveDays = (RealmBoolean) cache.get(activeDaysItem);
                if (cacheactiveDays != null) {
                    activeDaysRealmList.add(cacheactiveDays);
                } else {
                    activeDaysRealmList.add(RealmBooleanRealmProxy.copyOrUpdate(realm, activeDaysItem, update, cache));
                }
            }
        }
        realmObjectCopy.realmSet$timeOfDay(realmObjectSource.realmGet$timeOfDay());
        return realmObject;
    }

    public static long insert(Realm realm, Reminder object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Reminder.class);
        long tableNativePtr = table.getNativePtr();
        ReminderColumnInfo columnInfo = (ReminderColumnInfo) realm.getSchema().getColumnInfo(Reminder.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = object.realmGet$id();
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == -1) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, Long.valueOf(rowIndex));
        RealmList<RealmBoolean> activeDaysList = object.realmGet$activeDays();
        if (activeDaysList != null) {
            OsList activeDaysOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.activeDaysIndex);
            Iterator it = activeDaysList.iterator();
            while (it.hasNext()) {
                RealmBoolean activeDaysItem = (RealmBoolean) it.next();
                Long cacheItemIndexactiveDays = (Long) cache.get(activeDaysItem);
                if (cacheItemIndexactiveDays == null) {
                    cacheItemIndexactiveDays = Long.valueOf(RealmBooleanRealmProxy.insert(realm, activeDaysItem, (Map) cache));
                }
                activeDaysOsList.addRow(cacheItemIndexactiveDays.longValue());
            }
        }
        String realmGet$timeOfDay = object.realmGet$timeOfDay();
        if (realmGet$timeOfDay == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.timeOfDayIndex, rowIndex, realmGet$timeOfDay, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Reminder.class);
        long tableNativePtr = table.getNativePtr();
        ReminderColumnInfo columnInfo = (ReminderColumnInfo) realm.getSchema().getColumnInfo(Reminder.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Reminder object = (Reminder) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex;
                    String primaryKeyValue = object.realmGet$id();
                    if (primaryKeyValue == null) {
                        rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                    } else {
                        rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                    }
                    if (rowIndex == -1) {
                        rowIndex = OsObject.createRowWithPrimaryKey(table, primaryKeyValue);
                    } else {
                        Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                    }
                    cache.put(object, Long.valueOf(rowIndex));
                    RealmList<RealmBoolean> activeDaysList = object.realmGet$activeDays();
                    if (activeDaysList != null) {
                        OsList activeDaysOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.activeDaysIndex);
                        Iterator it = activeDaysList.iterator();
                        while (it.hasNext()) {
                            RealmBoolean activeDaysItem = (RealmBoolean) it.next();
                            Long cacheItemIndexactiveDays = (Long) cache.get(activeDaysItem);
                            if (cacheItemIndexactiveDays == null) {
                                cacheItemIndexactiveDays = Long.valueOf(RealmBooleanRealmProxy.insert(realm, activeDaysItem, (Map) cache));
                            }
                            activeDaysOsList.addRow(cacheItemIndexactiveDays.longValue());
                        }
                    }
                    String realmGet$timeOfDay = object.realmGet$timeOfDay();
                    if (realmGet$timeOfDay != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.timeOfDayIndex, rowIndex, realmGet$timeOfDay, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Reminder object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Reminder.class);
        long tableNativePtr = table.getNativePtr();
        ReminderColumnInfo columnInfo = (ReminderColumnInfo) realm.getSchema().getColumnInfo(Reminder.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = object.realmGet$id();
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == -1) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, primaryKeyValue);
        }
        cache.put(object, Long.valueOf(rowIndex));
        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.activeDaysIndex);
        osList.removeAll();
        RealmList<RealmBoolean> activeDaysList = object.realmGet$activeDays();
        if (activeDaysList != null) {
            Iterator it = activeDaysList.iterator();
            while (it.hasNext()) {
                RealmBoolean activeDaysItem = (RealmBoolean) it.next();
                Long cacheItemIndexactiveDays = (Long) cache.get(activeDaysItem);
                if (cacheItemIndexactiveDays == null) {
                    cacheItemIndexactiveDays = Long.valueOf(RealmBooleanRealmProxy.insertOrUpdate(realm, activeDaysItem, (Map) cache));
                }
                osList.addRow(cacheItemIndexactiveDays.longValue());
            }
        }
        String realmGet$timeOfDay = object.realmGet$timeOfDay();
        if (realmGet$timeOfDay != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.timeOfDayIndex, rowIndex, realmGet$timeOfDay, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.timeOfDayIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Reminder.class);
        long tableNativePtr = table.getNativePtr();
        ReminderColumnInfo columnInfo = (ReminderColumnInfo) realm.getSchema().getColumnInfo(Reminder.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Reminder object = (Reminder) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex;
                    String primaryKeyValue = object.realmGet$id();
                    if (primaryKeyValue == null) {
                        rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                    } else {
                        rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                    }
                    if (rowIndex == -1) {
                        rowIndex = OsObject.createRowWithPrimaryKey(table, primaryKeyValue);
                    }
                    cache.put(object, Long.valueOf(rowIndex));
                    OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.activeDaysIndex);
                    osList.removeAll();
                    RealmList<RealmBoolean> activeDaysList = object.realmGet$activeDays();
                    if (activeDaysList != null) {
                        Iterator it = activeDaysList.iterator();
                        while (it.hasNext()) {
                            RealmBoolean activeDaysItem = (RealmBoolean) it.next();
                            Long cacheItemIndexactiveDays = (Long) cache.get(activeDaysItem);
                            if (cacheItemIndexactiveDays == null) {
                                cacheItemIndexactiveDays = Long.valueOf(RealmBooleanRealmProxy.insertOrUpdate(realm, activeDaysItem, (Map) cache));
                            }
                            osList.addRow(cacheItemIndexactiveDays.longValue());
                        }
                    }
                    String realmGet$timeOfDay = object.realmGet$timeOfDay();
                    if (realmGet$timeOfDay != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.timeOfDayIndex, rowIndex, realmGet$timeOfDay, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.timeOfDayIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Reminder createDetachedCopy(Reminder realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Reminder unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Reminder();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Reminder) cachedObject.object;
        } else {
            unmanagedObject = (Reminder) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        ReminderRealmProxyInterface unmanagedCopy = unmanagedObject;
        ReminderRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$activeDays(null);
        } else {
            RealmList<RealmBoolean> managedactiveDaysList = realmSource.realmGet$activeDays();
            RealmList<RealmBoolean> unmanagedactiveDaysList = new RealmList();
            unmanagedCopy.realmSet$activeDays(unmanagedactiveDaysList);
            int nextDepth = currentDepth + 1;
            int size = managedactiveDaysList.size();
            for (int i = 0; i < size; i++) {
                unmanagedactiveDaysList.add(RealmBooleanRealmProxy.createDetachedCopy((RealmBoolean) managedactiveDaysList.get(i), nextDepth, maxDepth, cache));
            }
        }
        unmanagedCopy.realmSet$timeOfDay(realmSource.realmGet$timeOfDay());
        return unmanagedObject;
    }

    static Reminder update(Realm realm, Reminder realmObject, Reminder newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ReminderRealmProxyInterface realmObjectTarget = realmObject;
        ReminderRealmProxyInterface realmObjectSource = newObject;
        RealmList<RealmBoolean> activeDaysList = realmObjectSource.realmGet$activeDays();
        RealmList<RealmBoolean> activeDaysRealmList = realmObjectTarget.realmGet$activeDays();
        activeDaysRealmList.clear();
        if (activeDaysList != null) {
            for (int i = 0; i < activeDaysList.size(); i++) {
                RealmBoolean activeDaysItem = (RealmBoolean) activeDaysList.get(i);
                RealmBoolean cacheactiveDays = (RealmBoolean) cache.get(activeDaysItem);
                if (cacheactiveDays != null) {
                    activeDaysRealmList.add(cacheactiveDays);
                } else {
                    activeDaysRealmList.add(RealmBooleanRealmProxy.copyOrUpdate(realm, activeDaysItem, true, cache));
                }
            }
        }
        realmObjectTarget.realmSet$timeOfDay(realmObjectSource.realmGet$timeOfDay());
        return realmObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Reminder = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{activeDays:");
        stringBuilder.append("RealmList<RealmBoolean>[").append(realmGet$activeDays().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timeOfDay:");
        stringBuilder.append(realmGet$timeOfDay() != null ? realmGet$timeOfDay() : "null");
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
        ReminderRealmProxy aReminder = (ReminderRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aReminder.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aReminder.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aReminder.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
