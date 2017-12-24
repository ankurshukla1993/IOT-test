package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.Message;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.measurement.AppMeasurement$Param;
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

public class MessageRealmProxy extends Message implements RealmObjectProxy, MessageRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private MessageColumnInfo columnInfo;
    private ProxyState<Message> proxyState;

    static final class MessageColumnInfo extends ColumnInfo {
        long fromIndex;
        long idIndex;
        long messageIndex;
        long messageTypeIndex;
        long parametersIndex;
        long timestampIndex;

        MessageColumnInfo(OsSchemaInfo schemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Message");
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.messageIndex = addColumnDetails("message", objectSchemaInfo);
            this.fromIndex = addColumnDetails("from", objectSchemaInfo);
            this.messageTypeIndex = addColumnDetails("messageType", objectSchemaInfo);
            this.timestampIndex = addColumnDetails(AppMeasurement$Param.TIMESTAMP, objectSchemaInfo);
            this.parametersIndex = addColumnDetails("parameters", objectSchemaInfo);
        }

        MessageColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new MessageColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            MessageColumnInfo src = (MessageColumnInfo) rawSrc;
            MessageColumnInfo dst = (MessageColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.messageIndex = src.messageIndex;
            dst.fromIndex = src.fromIndex;
            dst.messageTypeIndex = src.messageTypeIndex;
            dst.timestampIndex = src.timestampIndex;
            dst.parametersIndex = src.parametersIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("message");
        fieldNames.add("from");
        fieldNames.add("messageType");
        fieldNames.add(AppMeasurement$Param.TIMESTAMP);
        fieldNames.add("parameters");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    MessageRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (MessageColumnInfo) context.getColumnInfo();
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

    public String realmGet$message() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.messageIndex);
    }

    public void realmSet$message(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.messageIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.messageIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.messageIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.messageIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$from() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.fromIndex);
    }

    public void realmSet$from(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.fromIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.fromIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.fromIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.fromIndex, row.getIndex(), value, true);
            }
        }
    }

    public int realmGet$messageType() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.messageTypeIndex);
    }

    public void realmSet$messageType(int value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.messageTypeIndex, (long) value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.messageTypeIndex, row.getIndex(), (long) value, true);
        }
    }

    public long realmGet$timestamp() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.timestampIndex);
    }

    public void realmSet$timestamp(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.timestampIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.timestampIndex, row.getIndex(), value, true);
        }
    }

    public String realmGet$parameters() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.parametersIndex);
    }

    public void realmSet$parameters(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.parametersIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.parametersIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.parametersIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.parametersIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Message");
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, true, true, false);
        builder.addPersistedProperty("message", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("from", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("messageType", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty(AppMeasurement$Param.TIMESTAMP, RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("parameters", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static MessageColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new MessageColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Message";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Message createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = Collections.emptyList();
        Message message = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(Message.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Message.class), false, Collections.emptyList());
                    message = new MessageRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (message == null) {
            if (!json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            } else if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                message = (MessageRealmProxy) realm.createObjectInternal(Message.class, null, true, excludeFields);
            } else {
                MessageRealmProxy obj = (MessageRealmProxy) realm.createObjectInternal(Message.class, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID), true, excludeFields);
            }
        }
        MessageRealmProxyInterface objProxy = message;
        if (json.has("message")) {
            if (json.isNull("message")) {
                objProxy.realmSet$message(null);
            } else {
                objProxy.realmSet$message(json.getString("message"));
            }
        }
        if (json.has("from")) {
            if (json.isNull("from")) {
                objProxy.realmSet$from(null);
            } else {
                objProxy.realmSet$from(json.getString("from"));
            }
        }
        if (json.has("messageType")) {
            if (json.isNull("messageType")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'messageType' to null.");
            }
            objProxy.realmSet$messageType(json.getInt("messageType"));
        }
        if (json.has(AppMeasurement$Param.TIMESTAMP)) {
            if (json.isNull(AppMeasurement$Param.TIMESTAMP)) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
            }
            objProxy.realmSet$timestamp(json.getLong(AppMeasurement$Param.TIMESTAMP));
        }
        if (json.has("parameters")) {
            if (json.isNull("parameters")) {
                objProxy.realmSet$parameters(null);
            } else {
                objProxy.realmSet$parameters(json.getString("parameters"));
            }
        }
        return message;
    }

    @TargetApi(11)
    public static Message createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        Message obj = new Message();
        MessageRealmProxyInterface objProxy = obj;
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
            } else if (name.equals("message")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$message(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$message(null);
                }
            } else if (name.equals("from")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$from(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$from(null);
                }
            } else if (name.equals("messageType")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$messageType(reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'messageType' to null.");
                }
            } else if (name.equals(AppMeasurement$Param.TIMESTAMP)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$timestamp(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
                }
            } else if (!name.equals("parameters")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$parameters(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$parameters(null);
            }
        }
        reader.endObject();
        if (jsonHasPrimaryKey) {
            return (Message) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    public static Message copyOrUpdate(Realm realm, Message object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Message) cachedRealmObject;
        }
        Message update2;
        Message realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(Message.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Message.class), false, Collections.emptyList());
                    Message realmObject2 = new MessageRealmProxy();
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

    public static Message copy(Realm realm, Message newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Message) cachedRealmObject;
        }
        Message realmObject = (Message) realm.createObjectInternal(Message.class, newObject.realmGet$id(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        MessageRealmProxyInterface realmObjectSource = newObject;
        MessageRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$message(realmObjectSource.realmGet$message());
        realmObjectCopy.realmSet$from(realmObjectSource.realmGet$from());
        realmObjectCopy.realmSet$messageType(realmObjectSource.realmGet$messageType());
        realmObjectCopy.realmSet$timestamp(realmObjectSource.realmGet$timestamp());
        realmObjectCopy.realmSet$parameters(realmObjectSource.realmGet$parameters());
        return realmObject;
    }

    public static long insert(Realm realm, Message object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Message.class);
        long tableNativePtr = table.getNativePtr();
        MessageColumnInfo columnInfo = (MessageColumnInfo) realm.getSchema().getColumnInfo(Message.class);
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
        String realmGet$message = object.realmGet$message();
        if (realmGet$message != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.messageIndex, rowIndex, realmGet$message, false);
        }
        String realmGet$from = object.realmGet$from();
        if (realmGet$from != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fromIndex, rowIndex, realmGet$from, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.messageTypeIndex, rowIndex, (long) object.realmGet$messageType(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
        String realmGet$parameters = object.realmGet$parameters();
        if (realmGet$parameters == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Message.class);
        long tableNativePtr = table.getNativePtr();
        MessageColumnInfo columnInfo = (MessageColumnInfo) realm.getSchema().getColumnInfo(Message.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Message object = (Message) objects.next();
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
                    String realmGet$message = object.realmGet$message();
                    if (realmGet$message != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.messageIndex, rowIndex, realmGet$message, false);
                    }
                    String realmGet$from = object.realmGet$from();
                    if (realmGet$from != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.fromIndex, rowIndex, realmGet$from, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.messageTypeIndex, rowIndex, (long) object.realmGet$messageType(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
                    String realmGet$parameters = object.realmGet$parameters();
                    if (realmGet$parameters != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Message object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Message.class);
        long tableNativePtr = table.getNativePtr();
        MessageColumnInfo columnInfo = (MessageColumnInfo) realm.getSchema().getColumnInfo(Message.class);
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
        String realmGet$message = object.realmGet$message();
        if (realmGet$message != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.messageIndex, rowIndex, realmGet$message, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.messageIndex, rowIndex, false);
        }
        String realmGet$from = object.realmGet$from();
        if (realmGet$from != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fromIndex, rowIndex, realmGet$from, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.fromIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.messageTypeIndex, rowIndex, (long) object.realmGet$messageType(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
        String realmGet$parameters = object.realmGet$parameters();
        if (realmGet$parameters != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.parametersIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Message.class);
        long tableNativePtr = table.getNativePtr();
        MessageColumnInfo columnInfo = (MessageColumnInfo) realm.getSchema().getColumnInfo(Message.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Message object = (Message) objects.next();
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
                    String realmGet$message = object.realmGet$message();
                    if (realmGet$message != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.messageIndex, rowIndex, realmGet$message, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.messageIndex, rowIndex, false);
                    }
                    String realmGet$from = object.realmGet$from();
                    if (realmGet$from != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.fromIndex, rowIndex, realmGet$from, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.fromIndex, rowIndex, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.messageTypeIndex, rowIndex, (long) object.realmGet$messageType(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
                    String realmGet$parameters = object.realmGet$parameters();
                    if (realmGet$parameters != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.parametersIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Message createDetachedCopy(Message realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Message unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Message();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Message) cachedObject.object;
        } else {
            unmanagedObject = (Message) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        MessageRealmProxyInterface unmanagedCopy = unmanagedObject;
        MessageRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$message(realmSource.realmGet$message());
        unmanagedCopy.realmSet$from(realmSource.realmGet$from());
        unmanagedCopy.realmSet$messageType(realmSource.realmGet$messageType());
        unmanagedCopy.realmSet$timestamp(realmSource.realmGet$timestamp());
        unmanagedCopy.realmSet$parameters(realmSource.realmGet$parameters());
        return unmanagedObject;
    }

    static Message update(Realm realm, Message realmObject, Message newObject, Map<RealmModel, RealmObjectProxy> map) {
        MessageRealmProxyInterface realmObjectTarget = realmObject;
        MessageRealmProxyInterface realmObjectSource = newObject;
        realmObjectTarget.realmSet$message(realmObjectSource.realmGet$message());
        realmObjectTarget.realmSet$from(realmObjectSource.realmGet$from());
        realmObjectTarget.realmSet$messageType(realmObjectSource.realmGet$messageType());
        realmObjectTarget.realmSet$timestamp(realmObjectSource.realmGet$timestamp());
        realmObjectTarget.realmSet$parameters(realmObjectSource.realmGet$parameters());
        return realmObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Message = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{message:");
        stringBuilder.append(realmGet$message() != null ? realmGet$message() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{from:");
        stringBuilder.append(realmGet$from() != null ? realmGet$from() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{messageType:");
        stringBuilder.append(realmGet$messageType());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timestamp:");
        stringBuilder.append(realmGet$timestamp());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{parameters:");
        stringBuilder.append(realmGet$parameters() != null ? realmGet$parameters() : "null");
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
        MessageRealmProxy aMessage = (MessageRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aMessage.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aMessage.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aMessage.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
