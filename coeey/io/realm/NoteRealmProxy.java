package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.Note;
import com.facebook.react.modules.appstate.AppStateModule;
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

public class NoteRealmProxy extends Note implements RealmObjectProxy, NoteRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private NoteColumnInfo columnInfo;
    private ProxyState<Note> proxyState;

    static final class NoteColumnInfo extends ColumnInfo {
        long activeIndex;
        long applicationIdIndex;
        long archivedIndex;
        long contentIndex;
        long createdOnIndex;
        long idIndex;
        long tenantIdIndex;
        long typeIndex;
        long updatedOnIndex;
        long userIdIndex;

        NoteColumnInfo(OsSchemaInfo schemaInfo) {
            super(10);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Note");
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.createdOnIndex = addColumnDetails("createdOn", objectSchemaInfo);
            this.updatedOnIndex = addColumnDetails("updatedOn", objectSchemaInfo);
            this.tenantIdIndex = addColumnDetails("tenantId", objectSchemaInfo);
            this.contentIndex = addColumnDetails("content", objectSchemaInfo);
            this.userIdIndex = addColumnDetails("userId", objectSchemaInfo);
            this.typeIndex = addColumnDetails("type", objectSchemaInfo);
            this.applicationIdIndex = addColumnDetails("applicationId", objectSchemaInfo);
            this.archivedIndex = addColumnDetails("archived", objectSchemaInfo);
            this.activeIndex = addColumnDetails(AppStateModule.APP_STATE_ACTIVE, objectSchemaInfo);
        }

        NoteColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new NoteColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            NoteColumnInfo src = (NoteColumnInfo) rawSrc;
            NoteColumnInfo dst = (NoteColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.createdOnIndex = src.createdOnIndex;
            dst.updatedOnIndex = src.updatedOnIndex;
            dst.tenantIdIndex = src.tenantIdIndex;
            dst.contentIndex = src.contentIndex;
            dst.userIdIndex = src.userIdIndex;
            dst.typeIndex = src.typeIndex;
            dst.applicationIdIndex = src.applicationIdIndex;
            dst.archivedIndex = src.archivedIndex;
            dst.activeIndex = src.activeIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("createdOn");
        fieldNames.add("updatedOn");
        fieldNames.add("tenantId");
        fieldNames.add("content");
        fieldNames.add("userId");
        fieldNames.add("type");
        fieldNames.add("applicationId");
        fieldNames.add("archived");
        fieldNames.add(AppStateModule.APP_STATE_ACTIVE);
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    NoteRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (NoteColumnInfo) context.getColumnInfo();
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

    public long realmGet$createdOn() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.createdOnIndex);
    }

    public void realmSet$createdOn(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.createdOnIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.createdOnIndex, row.getIndex(), value, true);
        }
    }

    public long realmGet$updatedOn() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.updatedOnIndex);
    }

    public void realmSet$updatedOn(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.updatedOnIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.updatedOnIndex, row.getIndex(), value, true);
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

    public String realmGet$content() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.contentIndex);
    }

    public void realmSet$content(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.contentIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.contentIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.contentIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.contentIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$userId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.userIdIndex);
    }

    public void realmSet$userId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.userIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.userIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.userIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.userIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$type() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.typeIndex);
    }

    public void realmSet$type(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.typeIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.typeIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.typeIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.typeIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$applicationId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.applicationIdIndex);
    }

    public void realmSet$applicationId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.applicationIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.applicationIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.applicationIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.applicationIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public boolean realmGet$archived() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.archivedIndex);
    }

    public void realmSet$archived(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.archivedIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.archivedIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$active() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.activeIndex);
    }

    public void realmSet$active(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.activeIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.activeIndex, row.getIndex(), value, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Note");
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, true, true, false);
        builder.addPersistedProperty("createdOn", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("updatedOn", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("tenantId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("content", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("userId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("applicationId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("archived", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty(AppStateModule.APP_STATE_ACTIVE, RealmFieldType.BOOLEAN, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static NoteColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new NoteColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Note";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Note createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = Collections.emptyList();
        Note note = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(Note.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Note.class), false, Collections.emptyList());
                    note = new NoteRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (note == null) {
            if (!json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            } else if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                note = (NoteRealmProxy) realm.createObjectInternal(Note.class, null, true, excludeFields);
            } else {
                NoteRealmProxy obj = (NoteRealmProxy) realm.createObjectInternal(Note.class, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID), true, excludeFields);
            }
        }
        NoteRealmProxyInterface objProxy = note;
        if (json.has("createdOn")) {
            if (json.isNull("createdOn")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'createdOn' to null.");
            }
            objProxy.realmSet$createdOn(json.getLong("createdOn"));
        }
        if (json.has("updatedOn")) {
            if (json.isNull("updatedOn")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'updatedOn' to null.");
            }
            objProxy.realmSet$updatedOn(json.getLong("updatedOn"));
        }
        if (json.has("tenantId")) {
            if (json.isNull("tenantId")) {
                objProxy.realmSet$tenantId(null);
            } else {
                objProxy.realmSet$tenantId(json.getString("tenantId"));
            }
        }
        if (json.has("content")) {
            if (json.isNull("content")) {
                objProxy.realmSet$content(null);
            } else {
                objProxy.realmSet$content(json.getString("content"));
            }
        }
        if (json.has("userId")) {
            if (json.isNull("userId")) {
                objProxy.realmSet$userId(null);
            } else {
                objProxy.realmSet$userId(json.getString("userId"));
            }
        }
        if (json.has("type")) {
            if (json.isNull("type")) {
                objProxy.realmSet$type(null);
            } else {
                objProxy.realmSet$type(json.getString("type"));
            }
        }
        if (json.has("applicationId")) {
            if (json.isNull("applicationId")) {
                objProxy.realmSet$applicationId(null);
            } else {
                objProxy.realmSet$applicationId(json.getString("applicationId"));
            }
        }
        if (json.has("archived")) {
            if (json.isNull("archived")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'archived' to null.");
            }
            objProxy.realmSet$archived(json.getBoolean("archived"));
        }
        if (json.has(AppStateModule.APP_STATE_ACTIVE)) {
            if (json.isNull(AppStateModule.APP_STATE_ACTIVE)) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'active' to null.");
            }
            objProxy.realmSet$active(json.getBoolean(AppStateModule.APP_STATE_ACTIVE));
        }
        return note;
    }

    @TargetApi(11)
    public static Note createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        Note obj = new Note();
        NoteRealmProxyInterface objProxy = obj;
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
            } else if (name.equals("createdOn")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$createdOn(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'createdOn' to null.");
                }
            } else if (name.equals("updatedOn")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$updatedOn(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'updatedOn' to null.");
                }
            } else if (name.equals("tenantId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tenantId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$tenantId(null);
                }
            } else if (name.equals("content")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$content(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$content(null);
                }
            } else if (name.equals("userId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$userId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$userId(null);
                }
            } else if (name.equals("type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$type(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$type(null);
                }
            } else if (name.equals("applicationId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$applicationId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$applicationId(null);
                }
            } else if (name.equals("archived")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$archived(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'archived' to null.");
                }
            } else if (!name.equals(AppStateModule.APP_STATE_ACTIVE)) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$active(reader.nextBoolean());
            } else {
                reader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'active' to null.");
            }
        }
        reader.endObject();
        if (jsonHasPrimaryKey) {
            return (Note) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    public static Note copyOrUpdate(Realm realm, Note object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Note) cachedRealmObject;
        }
        Note update2;
        Note realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(Note.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Note.class), false, Collections.emptyList());
                    Note realmObject2 = new NoteRealmProxy();
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

    public static Note copy(Realm realm, Note newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Note) cachedRealmObject;
        }
        Note realmObject = (Note) realm.createObjectInternal(Note.class, newObject.realmGet$id(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        NoteRealmProxyInterface realmObjectSource = newObject;
        NoteRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$createdOn(realmObjectSource.realmGet$createdOn());
        realmObjectCopy.realmSet$updatedOn(realmObjectSource.realmGet$updatedOn());
        realmObjectCopy.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectCopy.realmSet$content(realmObjectSource.realmGet$content());
        realmObjectCopy.realmSet$userId(realmObjectSource.realmGet$userId());
        realmObjectCopy.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectCopy.realmSet$applicationId(realmObjectSource.realmGet$applicationId());
        realmObjectCopy.realmSet$archived(realmObjectSource.realmGet$archived());
        realmObjectCopy.realmSet$active(realmObjectSource.realmGet$active());
        return realmObject;
    }

    public static long insert(Realm realm, Note object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Note.class);
        long tableNativePtr = table.getNativePtr();
        NoteColumnInfo columnInfo = (NoteColumnInfo) realm.getSchema().getColumnInfo(Note.class);
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
        Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        }
        String realmGet$content = object.realmGet$content();
        if (realmGet$content != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
        }
        String realmGet$userId = object.realmGet$userId();
        if (realmGet$userId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        }
        String realmGet$applicationId = object.realmGet$applicationId();
        if (realmGet$applicationId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.applicationIdIndex, rowIndex, realmGet$applicationId, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.archivedIndex, rowIndex, object.realmGet$archived(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.activeIndex, rowIndex, object.realmGet$active(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Note.class);
        long tableNativePtr = table.getNativePtr();
        NoteColumnInfo columnInfo = (NoteColumnInfo) realm.getSchema().getColumnInfo(Note.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Note object = (Note) objects.next();
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
                    Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    }
                    String realmGet$content = object.realmGet$content();
                    if (realmGet$content != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
                    }
                    String realmGet$userId = object.realmGet$userId();
                    if (realmGet$userId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    }
                    String realmGet$applicationId = object.realmGet$applicationId();
                    if (realmGet$applicationId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.applicationIdIndex, rowIndex, realmGet$applicationId, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.archivedIndex, rowIndex, object.realmGet$archived(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.activeIndex, rowIndex, object.realmGet$active(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Note object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Note.class);
        long tableNativePtr = table.getNativePtr();
        NoteColumnInfo columnInfo = (NoteColumnInfo) realm.getSchema().getColumnInfo(Note.class);
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
        Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
        }
        String realmGet$content = object.realmGet$content();
        if (realmGet$content != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.contentIndex, rowIndex, false);
        }
        String realmGet$userId = object.realmGet$userId();
        if (realmGet$userId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.userIdIndex, rowIndex, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
        }
        String realmGet$applicationId = object.realmGet$applicationId();
        if (realmGet$applicationId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.applicationIdIndex, rowIndex, realmGet$applicationId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.applicationIdIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.archivedIndex, rowIndex, object.realmGet$archived(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.activeIndex, rowIndex, object.realmGet$active(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Note.class);
        long tableNativePtr = table.getNativePtr();
        NoteColumnInfo columnInfo = (NoteColumnInfo) realm.getSchema().getColumnInfo(Note.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Note object = (Note) objects.next();
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
                    Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
                    }
                    String realmGet$content = object.realmGet$content();
                    if (realmGet$content != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.contentIndex, rowIndex, false);
                    }
                    String realmGet$userId = object.realmGet$userId();
                    if (realmGet$userId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.userIdIndex, rowIndex, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
                    }
                    String realmGet$applicationId = object.realmGet$applicationId();
                    if (realmGet$applicationId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.applicationIdIndex, rowIndex, realmGet$applicationId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.applicationIdIndex, rowIndex, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.archivedIndex, rowIndex, object.realmGet$archived(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.activeIndex, rowIndex, object.realmGet$active(), false);
                }
            }
        }
    }

    public static Note createDetachedCopy(Note realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Note unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Note();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Note) cachedObject.object;
        } else {
            unmanagedObject = (Note) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        NoteRealmProxyInterface unmanagedCopy = unmanagedObject;
        NoteRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$createdOn(realmSource.realmGet$createdOn());
        unmanagedCopy.realmSet$updatedOn(realmSource.realmGet$updatedOn());
        unmanagedCopy.realmSet$tenantId(realmSource.realmGet$tenantId());
        unmanagedCopy.realmSet$content(realmSource.realmGet$content());
        unmanagedCopy.realmSet$userId(realmSource.realmGet$userId());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());
        unmanagedCopy.realmSet$applicationId(realmSource.realmGet$applicationId());
        unmanagedCopy.realmSet$archived(realmSource.realmGet$archived());
        unmanagedCopy.realmSet$active(realmSource.realmGet$active());
        return unmanagedObject;
    }

    static Note update(Realm realm, Note realmObject, Note newObject, Map<RealmModel, RealmObjectProxy> map) {
        NoteRealmProxyInterface realmObjectTarget = realmObject;
        NoteRealmProxyInterface realmObjectSource = newObject;
        realmObjectTarget.realmSet$createdOn(realmObjectSource.realmGet$createdOn());
        realmObjectTarget.realmSet$updatedOn(realmObjectSource.realmGet$updatedOn());
        realmObjectTarget.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectTarget.realmSet$content(realmObjectSource.realmGet$content());
        realmObjectTarget.realmSet$userId(realmObjectSource.realmGet$userId());
        realmObjectTarget.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectTarget.realmSet$applicationId(realmObjectSource.realmGet$applicationId());
        realmObjectTarget.realmSet$archived(realmObjectSource.realmGet$archived());
        realmObjectTarget.realmSet$active(realmObjectSource.realmGet$active());
        return realmObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Note = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{createdOn:");
        stringBuilder.append(realmGet$createdOn());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{updatedOn:");
        stringBuilder.append(realmGet$updatedOn());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tenantId:");
        stringBuilder.append(realmGet$tenantId() != null ? realmGet$tenantId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{content:");
        stringBuilder.append(realmGet$content() != null ? realmGet$content() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{userId:");
        stringBuilder.append(realmGet$userId() != null ? realmGet$userId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{type:");
        stringBuilder.append(realmGet$type() != null ? realmGet$type() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{applicationId:");
        stringBuilder.append(realmGet$applicationId() != null ? realmGet$applicationId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{archived:");
        stringBuilder.append(realmGet$archived());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{active:");
        stringBuilder.append(realmGet$active());
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
        NoteRealmProxy aNote = (NoteRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aNote.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aNote.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aNote.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
