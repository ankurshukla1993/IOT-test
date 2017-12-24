package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.Address;
import com.cooey.devices.helpers.CooeySQLHelper;
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

public class AddressRealmProxy extends Address implements RealmObjectProxy, AddressRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private AddressColumnInfo columnInfo;
    private ProxyState<Address> proxyState;

    static final class AddressColumnInfo extends ColumnInfo {
        long cityIndex;
        long line1Index;
        long line2Index;
        long stateIndex;

        AddressColumnInfo(OsSchemaInfo schemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Address");
            this.line1Index = addColumnDetails("line1", objectSchemaInfo);
            this.line2Index = addColumnDetails("line2", objectSchemaInfo);
            this.cityIndex = addColumnDetails(CooeySQLHelper.COLUMN_CITY, objectSchemaInfo);
            this.stateIndex = addColumnDetails("state", objectSchemaInfo);
        }

        AddressColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new AddressColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            AddressColumnInfo src = (AddressColumnInfo) rawSrc;
            AddressColumnInfo dst = (AddressColumnInfo) rawDst;
            dst.line1Index = src.line1Index;
            dst.line2Index = src.line2Index;
            dst.cityIndex = src.cityIndex;
            dst.stateIndex = src.stateIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("line1");
        fieldNames.add("line2");
        fieldNames.add(CooeySQLHelper.COLUMN_CITY);
        fieldNames.add("state");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    AddressRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (AddressColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$line1() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.line1Index);
    }

    public void realmSet$line1(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.line1Index);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.line1Index, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.line1Index, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.line1Index, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$line2() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.line2Index);
    }

    public void realmSet$line2(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.line2Index);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.line2Index, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.line2Index, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.line2Index, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$city() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.cityIndex);
    }

    public void realmSet$city(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.cityIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.cityIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.cityIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.cityIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$state() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.stateIndex);
    }

    public void realmSet$state(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.stateIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.stateIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.stateIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.stateIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Address");
        builder.addPersistedProperty("line1", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("line2", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(CooeySQLHelper.COLUMN_CITY, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("state", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static AddressColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new AddressColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Address";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Address createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        Address obj = (Address) realm.createObjectInternal(Address.class, true, Collections.emptyList());
        AddressRealmProxyInterface objProxy = obj;
        if (json.has("line1")) {
            if (json.isNull("line1")) {
                objProxy.realmSet$line1(null);
            } else {
                objProxy.realmSet$line1(json.getString("line1"));
            }
        }
        if (json.has("line2")) {
            if (json.isNull("line2")) {
                objProxy.realmSet$line2(null);
            } else {
                objProxy.realmSet$line2(json.getString("line2"));
            }
        }
        if (json.has(CooeySQLHelper.COLUMN_CITY)) {
            if (json.isNull(CooeySQLHelper.COLUMN_CITY)) {
                objProxy.realmSet$city(null);
            } else {
                objProxy.realmSet$city(json.getString(CooeySQLHelper.COLUMN_CITY));
            }
        }
        if (json.has("state")) {
            if (json.isNull("state")) {
                objProxy.realmSet$state(null);
            } else {
                objProxy.realmSet$state(json.getString("state"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static Address createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Address obj = new Address();
        AddressRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("line1")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$line1(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$line1(null);
                }
            } else if (name.equals("line2")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$line2(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$line2(null);
                }
            } else if (name.equals(CooeySQLHelper.COLUMN_CITY)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$city(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$city(null);
                }
            } else if (!name.equals("state")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$state(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$state(null);
            }
        }
        reader.endObject();
        return (Address) realm.copyToRealm(obj);
    }

    public static Address copyOrUpdate(Realm realm, Address object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Address) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Address copy(Realm realm, Address newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Address) cachedRealmObject;
        }
        Address realmObject = (Address) realm.createObjectInternal(Address.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        AddressRealmProxyInterface realmObjectSource = newObject;
        AddressRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$line1(realmObjectSource.realmGet$line1());
        realmObjectCopy.realmSet$line2(realmObjectSource.realmGet$line2());
        realmObjectCopy.realmSet$city(realmObjectSource.realmGet$city());
        realmObjectCopy.realmSet$state(realmObjectSource.realmGet$state());
        return realmObject;
    }

    public static long insert(Realm realm, Address object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Address.class);
        long tableNativePtr = table.getNativePtr();
        AddressColumnInfo columnInfo = (AddressColumnInfo) realm.getSchema().getColumnInfo(Address.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$line1 = object.realmGet$line1();
        if (realmGet$line1 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.line1Index, rowIndex, realmGet$line1, false);
        }
        String realmGet$line2 = object.realmGet$line2();
        if (realmGet$line2 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.line2Index, rowIndex, realmGet$line2, false);
        }
        String realmGet$city = object.realmGet$city();
        if (realmGet$city != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cityIndex, rowIndex, realmGet$city, false);
        }
        String realmGet$state = object.realmGet$state();
        if (realmGet$state == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.stateIndex, rowIndex, realmGet$state, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Address.class);
        long tableNativePtr = table.getNativePtr();
        AddressColumnInfo columnInfo = (AddressColumnInfo) realm.getSchema().getColumnInfo(Address.class);
        while (objects.hasNext()) {
            Address object = (Address) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$line1 = object.realmGet$line1();
                    if (realmGet$line1 != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.line1Index, rowIndex, realmGet$line1, false);
                    }
                    String realmGet$line2 = object.realmGet$line2();
                    if (realmGet$line2 != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.line2Index, rowIndex, realmGet$line2, false);
                    }
                    String realmGet$city = object.realmGet$city();
                    if (realmGet$city != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.cityIndex, rowIndex, realmGet$city, false);
                    }
                    String realmGet$state = object.realmGet$state();
                    if (realmGet$state != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.stateIndex, rowIndex, realmGet$state, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Address object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Address.class);
        long tableNativePtr = table.getNativePtr();
        AddressColumnInfo columnInfo = (AddressColumnInfo) realm.getSchema().getColumnInfo(Address.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$line1 = object.realmGet$line1();
        if (realmGet$line1 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.line1Index, rowIndex, realmGet$line1, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.line1Index, rowIndex, false);
        }
        String realmGet$line2 = object.realmGet$line2();
        if (realmGet$line2 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.line2Index, rowIndex, realmGet$line2, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.line2Index, rowIndex, false);
        }
        String realmGet$city = object.realmGet$city();
        if (realmGet$city != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cityIndex, rowIndex, realmGet$city, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.cityIndex, rowIndex, false);
        }
        String realmGet$state = object.realmGet$state();
        if (realmGet$state != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.stateIndex, rowIndex, realmGet$state, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.stateIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Address.class);
        long tableNativePtr = table.getNativePtr();
        AddressColumnInfo columnInfo = (AddressColumnInfo) realm.getSchema().getColumnInfo(Address.class);
        while (objects.hasNext()) {
            Address object = (Address) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$line1 = object.realmGet$line1();
                    if (realmGet$line1 != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.line1Index, rowIndex, realmGet$line1, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.line1Index, rowIndex, false);
                    }
                    String realmGet$line2 = object.realmGet$line2();
                    if (realmGet$line2 != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.line2Index, rowIndex, realmGet$line2, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.line2Index, rowIndex, false);
                    }
                    String realmGet$city = object.realmGet$city();
                    if (realmGet$city != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.cityIndex, rowIndex, realmGet$city, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.cityIndex, rowIndex, false);
                    }
                    String realmGet$state = object.realmGet$state();
                    if (realmGet$state != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.stateIndex, rowIndex, realmGet$state, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.stateIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Address createDetachedCopy(Address realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Address unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Address();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Address) cachedObject.object;
        } else {
            unmanagedObject = (Address) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        AddressRealmProxyInterface unmanagedCopy = unmanagedObject;
        AddressRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$line1(realmSource.realmGet$line1());
        unmanagedCopy.realmSet$line2(realmSource.realmGet$line2());
        unmanagedCopy.realmSet$city(realmSource.realmGet$city());
        unmanagedCopy.realmSet$state(realmSource.realmGet$state());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Address = proxy[");
        stringBuilder.append("{line1:");
        stringBuilder.append(realmGet$line1() != null ? realmGet$line1() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{line2:");
        stringBuilder.append(realmGet$line2() != null ? realmGet$line2() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{city:");
        stringBuilder.append(realmGet$city() != null ? realmGet$city() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{state:");
        stringBuilder.append(realmGet$state() != null ? realmGet$state() : "null");
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
        AddressRealmProxy aAddress = (AddressRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aAddress.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aAddress.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aAddress.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
