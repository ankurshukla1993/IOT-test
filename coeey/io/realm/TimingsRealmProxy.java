package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.vo.Timings;
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

public class TimingsRealmProxy extends Timings implements RealmObjectProxy, TimingsRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private TimingsColumnInfo columnInfo;
    private ProxyState<Timings> proxyState;

    static final class TimingsColumnInfo extends ColumnInfo {
        long FRIDAYIndex;
        long MONDAYIndex;
        long SATURDAYIndex;
        long SUNDAYIndex;
        long THURSDAYIndex;
        long TUESDAYIndex;
        long WEDNESDAYIndex;

        TimingsColumnInfo(OsSchemaInfo schemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Timings");
            this.WEDNESDAYIndex = addColumnDetails(CTConstants.WEDNESDAY, objectSchemaInfo);
            this.MONDAYIndex = addColumnDetails(CTConstants.MONDAY, objectSchemaInfo);
            this.THURSDAYIndex = addColumnDetails(CTConstants.THURSDAY, objectSchemaInfo);
            this.SUNDAYIndex = addColumnDetails(CTConstants.SUNDAY, objectSchemaInfo);
            this.TUESDAYIndex = addColumnDetails(CTConstants.TUESDAY, objectSchemaInfo);
            this.FRIDAYIndex = addColumnDetails(CTConstants.FRIDAY, objectSchemaInfo);
            this.SATURDAYIndex = addColumnDetails(CTConstants.SATURDAY, objectSchemaInfo);
        }

        TimingsColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new TimingsColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            TimingsColumnInfo src = (TimingsColumnInfo) rawSrc;
            TimingsColumnInfo dst = (TimingsColumnInfo) rawDst;
            dst.WEDNESDAYIndex = src.WEDNESDAYIndex;
            dst.MONDAYIndex = src.MONDAYIndex;
            dst.THURSDAYIndex = src.THURSDAYIndex;
            dst.SUNDAYIndex = src.SUNDAYIndex;
            dst.TUESDAYIndex = src.TUESDAYIndex;
            dst.FRIDAYIndex = src.FRIDAYIndex;
            dst.SATURDAYIndex = src.SATURDAYIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(CTConstants.WEDNESDAY);
        fieldNames.add(CTConstants.MONDAY);
        fieldNames.add(CTConstants.THURSDAY);
        fieldNames.add(CTConstants.SUNDAY);
        fieldNames.add(CTConstants.TUESDAY);
        fieldNames.add(CTConstants.FRIDAY);
        fieldNames.add(CTConstants.SATURDAY);
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TimingsRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (TimingsColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$WEDNESDAY() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.WEDNESDAYIndex);
    }

    public void realmSet$WEDNESDAY(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.WEDNESDAYIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.WEDNESDAYIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.WEDNESDAYIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.WEDNESDAYIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$MONDAY() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.MONDAYIndex);
    }

    public void realmSet$MONDAY(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.MONDAYIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.MONDAYIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.MONDAYIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.MONDAYIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$THURSDAY() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.THURSDAYIndex);
    }

    public void realmSet$THURSDAY(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.THURSDAYIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.THURSDAYIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.THURSDAYIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.THURSDAYIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$SUNDAY() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.SUNDAYIndex);
    }

    public void realmSet$SUNDAY(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.SUNDAYIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.SUNDAYIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.SUNDAYIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.SUNDAYIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$TUESDAY() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.TUESDAYIndex);
    }

    public void realmSet$TUESDAY(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.TUESDAYIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.TUESDAYIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.TUESDAYIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.TUESDAYIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$FRIDAY() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.FRIDAYIndex);
    }

    public void realmSet$FRIDAY(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.FRIDAYIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.FRIDAYIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.FRIDAYIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.FRIDAYIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$SATURDAY() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.SATURDAYIndex);
    }

    public void realmSet$SATURDAY(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.SATURDAYIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.SATURDAYIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.SATURDAYIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.SATURDAYIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Timings");
        builder.addPersistedProperty(CTConstants.WEDNESDAY, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(CTConstants.MONDAY, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(CTConstants.THURSDAY, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(CTConstants.SUNDAY, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(CTConstants.TUESDAY, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(CTConstants.FRIDAY, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(CTConstants.SATURDAY, RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TimingsColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new TimingsColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Timings";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Timings createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        Timings obj = (Timings) realm.createObjectInternal(Timings.class, true, Collections.emptyList());
        TimingsRealmProxyInterface objProxy = obj;
        if (json.has(CTConstants.WEDNESDAY)) {
            if (json.isNull(CTConstants.WEDNESDAY)) {
                objProxy.realmSet$WEDNESDAY(null);
            } else {
                objProxy.realmSet$WEDNESDAY(json.getString(CTConstants.WEDNESDAY));
            }
        }
        if (json.has(CTConstants.MONDAY)) {
            if (json.isNull(CTConstants.MONDAY)) {
                objProxy.realmSet$MONDAY(null);
            } else {
                objProxy.realmSet$MONDAY(json.getString(CTConstants.MONDAY));
            }
        }
        if (json.has(CTConstants.THURSDAY)) {
            if (json.isNull(CTConstants.THURSDAY)) {
                objProxy.realmSet$THURSDAY(null);
            } else {
                objProxy.realmSet$THURSDAY(json.getString(CTConstants.THURSDAY));
            }
        }
        if (json.has(CTConstants.SUNDAY)) {
            if (json.isNull(CTConstants.SUNDAY)) {
                objProxy.realmSet$SUNDAY(null);
            } else {
                objProxy.realmSet$SUNDAY(json.getString(CTConstants.SUNDAY));
            }
        }
        if (json.has(CTConstants.TUESDAY)) {
            if (json.isNull(CTConstants.TUESDAY)) {
                objProxy.realmSet$TUESDAY(null);
            } else {
                objProxy.realmSet$TUESDAY(json.getString(CTConstants.TUESDAY));
            }
        }
        if (json.has(CTConstants.FRIDAY)) {
            if (json.isNull(CTConstants.FRIDAY)) {
                objProxy.realmSet$FRIDAY(null);
            } else {
                objProxy.realmSet$FRIDAY(json.getString(CTConstants.FRIDAY));
            }
        }
        if (json.has(CTConstants.SATURDAY)) {
            if (json.isNull(CTConstants.SATURDAY)) {
                objProxy.realmSet$SATURDAY(null);
            } else {
                objProxy.realmSet$SATURDAY(json.getString(CTConstants.SATURDAY));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static Timings createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Timings obj = new Timings();
        TimingsRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(CTConstants.WEDNESDAY)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$WEDNESDAY(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$WEDNESDAY(null);
                }
            } else if (name.equals(CTConstants.MONDAY)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$MONDAY(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$MONDAY(null);
                }
            } else if (name.equals(CTConstants.THURSDAY)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$THURSDAY(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$THURSDAY(null);
                }
            } else if (name.equals(CTConstants.SUNDAY)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$SUNDAY(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$SUNDAY(null);
                }
            } else if (name.equals(CTConstants.TUESDAY)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$TUESDAY(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$TUESDAY(null);
                }
            } else if (name.equals(CTConstants.FRIDAY)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$FRIDAY(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$FRIDAY(null);
                }
            } else if (!name.equals(CTConstants.SATURDAY)) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$SATURDAY(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$SATURDAY(null);
            }
        }
        reader.endObject();
        return (Timings) realm.copyToRealm(obj);
    }

    public static Timings copyOrUpdate(Realm realm, Timings object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Timings) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Timings copy(Realm realm, Timings newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Timings) cachedRealmObject;
        }
        Timings realmObject = (Timings) realm.createObjectInternal(Timings.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        TimingsRealmProxyInterface realmObjectSource = newObject;
        TimingsRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$WEDNESDAY(realmObjectSource.realmGet$WEDNESDAY());
        realmObjectCopy.realmSet$MONDAY(realmObjectSource.realmGet$MONDAY());
        realmObjectCopy.realmSet$THURSDAY(realmObjectSource.realmGet$THURSDAY());
        realmObjectCopy.realmSet$SUNDAY(realmObjectSource.realmGet$SUNDAY());
        realmObjectCopy.realmSet$TUESDAY(realmObjectSource.realmGet$TUESDAY());
        realmObjectCopy.realmSet$FRIDAY(realmObjectSource.realmGet$FRIDAY());
        realmObjectCopy.realmSet$SATURDAY(realmObjectSource.realmGet$SATURDAY());
        return realmObject;
    }

    public static long insert(Realm realm, Timings object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Timings.class);
        long tableNativePtr = table.getNativePtr();
        TimingsColumnInfo columnInfo = (TimingsColumnInfo) realm.getSchema().getColumnInfo(Timings.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$WEDNESDAY = object.realmGet$WEDNESDAY();
        if (realmGet$WEDNESDAY != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.WEDNESDAYIndex, rowIndex, realmGet$WEDNESDAY, false);
        }
        String realmGet$MONDAY = object.realmGet$MONDAY();
        if (realmGet$MONDAY != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.MONDAYIndex, rowIndex, realmGet$MONDAY, false);
        }
        String realmGet$THURSDAY = object.realmGet$THURSDAY();
        if (realmGet$THURSDAY != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.THURSDAYIndex, rowIndex, realmGet$THURSDAY, false);
        }
        String realmGet$SUNDAY = object.realmGet$SUNDAY();
        if (realmGet$SUNDAY != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.SUNDAYIndex, rowIndex, realmGet$SUNDAY, false);
        }
        String realmGet$TUESDAY = object.realmGet$TUESDAY();
        if (realmGet$TUESDAY != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.TUESDAYIndex, rowIndex, realmGet$TUESDAY, false);
        }
        String realmGet$FRIDAY = object.realmGet$FRIDAY();
        if (realmGet$FRIDAY != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.FRIDAYIndex, rowIndex, realmGet$FRIDAY, false);
        }
        String realmGet$SATURDAY = object.realmGet$SATURDAY();
        if (realmGet$SATURDAY == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.SATURDAYIndex, rowIndex, realmGet$SATURDAY, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Timings.class);
        long tableNativePtr = table.getNativePtr();
        TimingsColumnInfo columnInfo = (TimingsColumnInfo) realm.getSchema().getColumnInfo(Timings.class);
        while (objects.hasNext()) {
            Timings object = (Timings) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$WEDNESDAY = object.realmGet$WEDNESDAY();
                    if (realmGet$WEDNESDAY != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.WEDNESDAYIndex, rowIndex, realmGet$WEDNESDAY, false);
                    }
                    String realmGet$MONDAY = object.realmGet$MONDAY();
                    if (realmGet$MONDAY != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.MONDAYIndex, rowIndex, realmGet$MONDAY, false);
                    }
                    String realmGet$THURSDAY = object.realmGet$THURSDAY();
                    if (realmGet$THURSDAY != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.THURSDAYIndex, rowIndex, realmGet$THURSDAY, false);
                    }
                    String realmGet$SUNDAY = object.realmGet$SUNDAY();
                    if (realmGet$SUNDAY != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.SUNDAYIndex, rowIndex, realmGet$SUNDAY, false);
                    }
                    String realmGet$TUESDAY = object.realmGet$TUESDAY();
                    if (realmGet$TUESDAY != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.TUESDAYIndex, rowIndex, realmGet$TUESDAY, false);
                    }
                    String realmGet$FRIDAY = object.realmGet$FRIDAY();
                    if (realmGet$FRIDAY != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.FRIDAYIndex, rowIndex, realmGet$FRIDAY, false);
                    }
                    String realmGet$SATURDAY = object.realmGet$SATURDAY();
                    if (realmGet$SATURDAY != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.SATURDAYIndex, rowIndex, realmGet$SATURDAY, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Timings object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Timings.class);
        long tableNativePtr = table.getNativePtr();
        TimingsColumnInfo columnInfo = (TimingsColumnInfo) realm.getSchema().getColumnInfo(Timings.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$WEDNESDAY = object.realmGet$WEDNESDAY();
        if (realmGet$WEDNESDAY != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.WEDNESDAYIndex, rowIndex, realmGet$WEDNESDAY, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.WEDNESDAYIndex, rowIndex, false);
        }
        String realmGet$MONDAY = object.realmGet$MONDAY();
        if (realmGet$MONDAY != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.MONDAYIndex, rowIndex, realmGet$MONDAY, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.MONDAYIndex, rowIndex, false);
        }
        String realmGet$THURSDAY = object.realmGet$THURSDAY();
        if (realmGet$THURSDAY != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.THURSDAYIndex, rowIndex, realmGet$THURSDAY, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.THURSDAYIndex, rowIndex, false);
        }
        String realmGet$SUNDAY = object.realmGet$SUNDAY();
        if (realmGet$SUNDAY != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.SUNDAYIndex, rowIndex, realmGet$SUNDAY, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.SUNDAYIndex, rowIndex, false);
        }
        String realmGet$TUESDAY = object.realmGet$TUESDAY();
        if (realmGet$TUESDAY != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.TUESDAYIndex, rowIndex, realmGet$TUESDAY, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.TUESDAYIndex, rowIndex, false);
        }
        String realmGet$FRIDAY = object.realmGet$FRIDAY();
        if (realmGet$FRIDAY != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.FRIDAYIndex, rowIndex, realmGet$FRIDAY, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.FRIDAYIndex, rowIndex, false);
        }
        String realmGet$SATURDAY = object.realmGet$SATURDAY();
        if (realmGet$SATURDAY != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.SATURDAYIndex, rowIndex, realmGet$SATURDAY, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.SATURDAYIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Timings.class);
        long tableNativePtr = table.getNativePtr();
        TimingsColumnInfo columnInfo = (TimingsColumnInfo) realm.getSchema().getColumnInfo(Timings.class);
        while (objects.hasNext()) {
            Timings object = (Timings) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$WEDNESDAY = object.realmGet$WEDNESDAY();
                    if (realmGet$WEDNESDAY != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.WEDNESDAYIndex, rowIndex, realmGet$WEDNESDAY, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.WEDNESDAYIndex, rowIndex, false);
                    }
                    String realmGet$MONDAY = object.realmGet$MONDAY();
                    if (realmGet$MONDAY != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.MONDAYIndex, rowIndex, realmGet$MONDAY, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.MONDAYIndex, rowIndex, false);
                    }
                    String realmGet$THURSDAY = object.realmGet$THURSDAY();
                    if (realmGet$THURSDAY != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.THURSDAYIndex, rowIndex, realmGet$THURSDAY, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.THURSDAYIndex, rowIndex, false);
                    }
                    String realmGet$SUNDAY = object.realmGet$SUNDAY();
                    if (realmGet$SUNDAY != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.SUNDAYIndex, rowIndex, realmGet$SUNDAY, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.SUNDAYIndex, rowIndex, false);
                    }
                    String realmGet$TUESDAY = object.realmGet$TUESDAY();
                    if (realmGet$TUESDAY != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.TUESDAYIndex, rowIndex, realmGet$TUESDAY, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.TUESDAYIndex, rowIndex, false);
                    }
                    String realmGet$FRIDAY = object.realmGet$FRIDAY();
                    if (realmGet$FRIDAY != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.FRIDAYIndex, rowIndex, realmGet$FRIDAY, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.FRIDAYIndex, rowIndex, false);
                    }
                    String realmGet$SATURDAY = object.realmGet$SATURDAY();
                    if (realmGet$SATURDAY != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.SATURDAYIndex, rowIndex, realmGet$SATURDAY, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.SATURDAYIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Timings createDetachedCopy(Timings realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Timings unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Timings();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Timings) cachedObject.object;
        } else {
            unmanagedObject = (Timings) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        TimingsRealmProxyInterface unmanagedCopy = unmanagedObject;
        TimingsRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$WEDNESDAY(realmSource.realmGet$WEDNESDAY());
        unmanagedCopy.realmSet$MONDAY(realmSource.realmGet$MONDAY());
        unmanagedCopy.realmSet$THURSDAY(realmSource.realmGet$THURSDAY());
        unmanagedCopy.realmSet$SUNDAY(realmSource.realmGet$SUNDAY());
        unmanagedCopy.realmSet$TUESDAY(realmSource.realmGet$TUESDAY());
        unmanagedCopy.realmSet$FRIDAY(realmSource.realmGet$FRIDAY());
        unmanagedCopy.realmSet$SATURDAY(realmSource.realmGet$SATURDAY());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Timings = proxy[");
        stringBuilder.append("{WEDNESDAY:");
        stringBuilder.append(realmGet$WEDNESDAY() != null ? realmGet$WEDNESDAY() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{MONDAY:");
        stringBuilder.append(realmGet$MONDAY() != null ? realmGet$MONDAY() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{THURSDAY:");
        stringBuilder.append(realmGet$THURSDAY() != null ? realmGet$THURSDAY() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{SUNDAY:");
        stringBuilder.append(realmGet$SUNDAY() != null ? realmGet$SUNDAY() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{TUESDAY:");
        stringBuilder.append(realmGet$TUESDAY() != null ? realmGet$TUESDAY() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{FRIDAY:");
        stringBuilder.append(realmGet$FRIDAY() != null ? realmGet$FRIDAY() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{SATURDAY:");
        stringBuilder.append(realmGet$SATURDAY() != null ? realmGet$SATURDAY() : "null");
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
        TimingsRealmProxy aTimings = (TimingsRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aTimings.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTimings.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aTimings.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
