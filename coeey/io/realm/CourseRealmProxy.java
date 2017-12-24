package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.Course;
import com.facebook.share.internal.ShareConstants;
import io.realm.exceptions.RealmException;
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

public class CourseRealmProxy extends Course implements RealmObjectProxy, CourseRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private CourseColumnInfo columnInfo;
    private ProxyState<Course> proxyState;

    static final class CourseColumnInfo extends ColumnInfo {
        long assignedByIndex;
        long completedIndex;
        long descriptionIndex;
        long endTimeIndex;
        long idIndex;
        long nameIndex;
        long startTimeIndex;
        long tenantIdIndex;

        CourseColumnInfo(OsSchemaInfo schemaInfo) {
            super(8);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Course");
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.tenantIdIndex = addColumnDetails("tenantId", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.startTimeIndex = addColumnDetails("startTime", objectSchemaInfo);
            this.endTimeIndex = addColumnDetails("endTime", objectSchemaInfo);
            this.descriptionIndex = addColumnDetails("description", objectSchemaInfo);
            this.completedIndex = addColumnDetails("completed", objectSchemaInfo);
            this.assignedByIndex = addColumnDetails("assignedBy", objectSchemaInfo);
        }

        CourseColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new CourseColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            CourseColumnInfo src = (CourseColumnInfo) rawSrc;
            CourseColumnInfo dst = (CourseColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.tenantIdIndex = src.tenantIdIndex;
            dst.nameIndex = src.nameIndex;
            dst.startTimeIndex = src.startTimeIndex;
            dst.endTimeIndex = src.endTimeIndex;
            dst.descriptionIndex = src.descriptionIndex;
            dst.completedIndex = src.completedIndex;
            dst.assignedByIndex = src.assignedByIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("tenantId");
        fieldNames.add("name");
        fieldNames.add("startTime");
        fieldNames.add("endTime");
        fieldNames.add("description");
        fieldNames.add("completed");
        fieldNames.add("assignedBy");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    CourseRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (CourseColumnInfo) context.getColumnInfo();
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

    public String realmGet$tenantId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.tenantIdIndex);
    }

    public void realmSet$tenantId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.tenantIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.tenantIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.tenantIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.tenantIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$name() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.nameIndex);
    }

    public void realmSet$name(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.nameIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.nameIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.nameIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.nameIndex, row.getIndex(), value, true);
            }
        }
    }

    public long realmGet$startTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.startTimeIndex);
    }

    public void realmSet$startTime(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.startTimeIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.startTimeIndex, row.getIndex(), value, true);
        }
    }

    public long realmGet$endTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.endTimeIndex);
    }

    public void realmSet$endTime(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.endTimeIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.endTimeIndex, row.getIndex(), value, true);
        }
    }

    public String realmGet$description() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.descriptionIndex);
    }

    public void realmSet$description(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.descriptionIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.descriptionIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.descriptionIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.descriptionIndex, row.getIndex(), value, true);
            }
        }
    }

    public boolean realmGet$completed() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.completedIndex);
    }

    public void realmSet$completed(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.completedIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.completedIndex, row.getIndex(), value, true);
        }
    }

    public String realmGet$assignedBy() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.assignedByIndex);
    }

    public void realmSet$assignedBy(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.assignedByIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.assignedByIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.assignedByIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.assignedByIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Course");
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, true, true, false);
        builder.addPersistedProperty("tenantId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("name", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("startTime", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("endTime", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("completed", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("assignedBy", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static CourseColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new CourseColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Course";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Course createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = Collections.emptyList();
        Course course = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(Course.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Course.class), false, Collections.emptyList());
                    course = new CourseRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (course == null) {
            if (!json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            } else if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                course = (CourseRealmProxy) realm.createObjectInternal(Course.class, null, true, excludeFields);
            } else {
                CourseRealmProxy obj = (CourseRealmProxy) realm.createObjectInternal(Course.class, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID), true, excludeFields);
            }
        }
        CourseRealmProxyInterface objProxy = course;
        if (json.has("tenantId")) {
            if (json.isNull("tenantId")) {
                objProxy.realmSet$tenantId(null);
            } else {
                objProxy.realmSet$tenantId(json.getString("tenantId"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name(json.getString("name"));
            }
        }
        if (json.has("startTime")) {
            if (json.isNull("startTime")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'startTime' to null.");
            }
            objProxy.realmSet$startTime(json.getLong("startTime"));
        }
        if (json.has("endTime")) {
            if (json.isNull("endTime")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'endTime' to null.");
            }
            objProxy.realmSet$endTime(json.getLong("endTime"));
        }
        if (json.has("description")) {
            if (json.isNull("description")) {
                objProxy.realmSet$description(null);
            } else {
                objProxy.realmSet$description(json.getString("description"));
            }
        }
        if (json.has("completed")) {
            if (json.isNull("completed")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'completed' to null.");
            }
            objProxy.realmSet$completed(json.getBoolean("completed"));
        }
        if (json.has("assignedBy")) {
            if (json.isNull("assignedBy")) {
                objProxy.realmSet$assignedBy(null);
            } else {
                objProxy.realmSet$assignedBy(json.getString("assignedBy"));
            }
        }
        return course;
    }

    @TargetApi(11)
    public static Course createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        Course obj = new Course();
        CourseRealmProxyInterface objProxy = obj;
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
            } else if (name.equals("tenantId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tenantId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$tenantId(null);
                }
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("startTime")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$startTime(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'startTime' to null.");
                }
            } else if (name.equals("endTime")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$endTime(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'endTime' to null.");
                }
            } else if (name.equals("description")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$description(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$description(null);
                }
            } else if (name.equals("completed")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$completed(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'completed' to null.");
                }
            } else if (!name.equals("assignedBy")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$assignedBy(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$assignedBy(null);
            }
        }
        reader.endObject();
        if (jsonHasPrimaryKey) {
            return (Course) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    public static Course copyOrUpdate(Realm realm, Course object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Course) cachedRealmObject;
        }
        Course update2;
        Course realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(Course.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Course.class), false, Collections.emptyList());
                    Course realmObject2 = new CourseRealmProxy();
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

    public static Course copy(Realm realm, Course newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Course) cachedRealmObject;
        }
        Course realmObject = (Course) realm.createObjectInternal(Course.class, newObject.realmGet$id(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        CourseRealmProxyInterface realmObjectSource = newObject;
        CourseRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$startTime(realmObjectSource.realmGet$startTime());
        realmObjectCopy.realmSet$endTime(realmObjectSource.realmGet$endTime());
        realmObjectCopy.realmSet$description(realmObjectSource.realmGet$description());
        realmObjectCopy.realmSet$completed(realmObjectSource.realmGet$completed());
        realmObjectCopy.realmSet$assignedBy(realmObjectSource.realmGet$assignedBy());
        return realmObject;
    }

    public static long insert(Realm realm, Course object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Course.class);
        long tableNativePtr = table.getNativePtr();
        CourseColumnInfo columnInfo = (CourseColumnInfo) realm.getSchema().getColumnInfo(Course.class);
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
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        }
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.startTimeIndex, rowIndex, object.realmGet$startTime(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
        String realmGet$description = object.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.completedIndex, rowIndex, object.realmGet$completed(), false);
        String realmGet$assignedBy = object.realmGet$assignedBy();
        if (realmGet$assignedBy == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.assignedByIndex, rowIndex, realmGet$assignedBy, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Course.class);
        long tableNativePtr = table.getNativePtr();
        CourseColumnInfo columnInfo = (CourseColumnInfo) realm.getSchema().getColumnInfo(Course.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Course object = (Course) objects.next();
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
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    }
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.startTimeIndex, rowIndex, object.realmGet$startTime(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
                    String realmGet$description = object.realmGet$description();
                    if (realmGet$description != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.completedIndex, rowIndex, object.realmGet$completed(), false);
                    String realmGet$assignedBy = object.realmGet$assignedBy();
                    if (realmGet$assignedBy != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.assignedByIndex, rowIndex, realmGet$assignedBy, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Course object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Course.class);
        long tableNativePtr = table.getNativePtr();
        CourseColumnInfo columnInfo = (CourseColumnInfo) realm.getSchema().getColumnInfo(Course.class);
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
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
        }
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.startTimeIndex, rowIndex, object.realmGet$startTime(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
        String realmGet$description = object.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.completedIndex, rowIndex, object.realmGet$completed(), false);
        String realmGet$assignedBy = object.realmGet$assignedBy();
        if (realmGet$assignedBy != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.assignedByIndex, rowIndex, realmGet$assignedBy, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.assignedByIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Course.class);
        long tableNativePtr = table.getNativePtr();
        CourseColumnInfo columnInfo = (CourseColumnInfo) realm.getSchema().getColumnInfo(Course.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Course object = (Course) objects.next();
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
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
                    }
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.startTimeIndex, rowIndex, object.realmGet$startTime(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
                    String realmGet$description = object.realmGet$description();
                    if (realmGet$description != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.completedIndex, rowIndex, object.realmGet$completed(), false);
                    String realmGet$assignedBy = object.realmGet$assignedBy();
                    if (realmGet$assignedBy != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.assignedByIndex, rowIndex, realmGet$assignedBy, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.assignedByIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Course createDetachedCopy(Course realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Course unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Course();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Course) cachedObject.object;
        } else {
            unmanagedObject = (Course) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        CourseRealmProxyInterface unmanagedCopy = unmanagedObject;
        CourseRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$tenantId(realmSource.realmGet$tenantId());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$startTime(realmSource.realmGet$startTime());
        unmanagedCopy.realmSet$endTime(realmSource.realmGet$endTime());
        unmanagedCopy.realmSet$description(realmSource.realmGet$description());
        unmanagedCopy.realmSet$completed(realmSource.realmGet$completed());
        unmanagedCopy.realmSet$assignedBy(realmSource.realmGet$assignedBy());
        return unmanagedObject;
    }

    static Course update(Realm realm, Course realmObject, Course newObject, Map<RealmModel, RealmObjectProxy> map) {
        CourseRealmProxyInterface realmObjectTarget = realmObject;
        CourseRealmProxyInterface realmObjectSource = newObject;
        realmObjectTarget.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectTarget.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectTarget.realmSet$startTime(realmObjectSource.realmGet$startTime());
        realmObjectTarget.realmSet$endTime(realmObjectSource.realmGet$endTime());
        realmObjectTarget.realmSet$description(realmObjectSource.realmGet$description());
        realmObjectTarget.realmSet$completed(realmObjectSource.realmGet$completed());
        realmObjectTarget.realmSet$assignedBy(realmObjectSource.realmGet$assignedBy());
        return realmObject;
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
        CourseRealmProxy aCourse = (CourseRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aCourse.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aCourse.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aCourse.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
