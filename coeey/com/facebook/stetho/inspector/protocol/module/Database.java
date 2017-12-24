package com.facebook.stetho.inspector.protocol.module;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError.ErrorCode;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.json.annotation.JsonProperty;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@TargetApi(11)
public class Database implements ChromeDevtoolsDomain {
    private static final int MAX_BLOB_LENGTH = 512;
    private static final int MAX_EXECUTE_RESULTS = 250;
    private static final String UNKNOWN_BLOB_LABEL = "{blob}";
    private final ChromePeerManager mChromePeerManager = new ChromePeerManager();
    private List<DatabaseDriver2> mDatabaseDrivers = new ArrayList();
    private final ObjectMapper mObjectMapper;
    private final DatabasePeerRegistrationListener mPeerListener = new DatabasePeerRegistrationListener(this.mDatabaseDrivers, null);

    public static class AddDatabaseEvent {
        @JsonProperty(required = true)
        public DatabaseObject database;
    }

    @Deprecated
    public static abstract class DatabaseDriver extends BaseDatabaseDriver<String> {
        public DatabaseDriver(Context context) {
            super(context);
        }
    }

    public static class DatabaseObject {
        @JsonProperty(required = true)
        public String domain;
        @JsonProperty(required = true)
        public String id;
        @JsonProperty(required = true)
        public String name;
        @JsonProperty(required = true)
        public String version;
    }

    public static class Error {
        @JsonProperty(required = true)
        public int code;
        @JsonProperty(required = true)
        public String message;
    }

    public static class ExecuteSQLRequest {
        @JsonProperty(required = true)
        public String databaseId;
        @JsonProperty(required = true)
        public String query;
    }

    public static class ExecuteSQLResponse implements JsonRpcResult {
        @JsonProperty
        public List<String> columnNames;
        @JsonProperty
        public Error sqlError;
        @JsonProperty
        public List<String> values;
    }

    private static class GetDatabaseTableNamesRequest {
        @JsonProperty(required = true)
        public String databaseId;

        private GetDatabaseTableNamesRequest() {
        }
    }

    private static class GetDatabaseTableNamesResponse implements JsonRpcResult {
        @JsonProperty(required = true)
        public List<String> tableNames;

        private GetDatabaseTableNamesResponse() {
        }
    }

    public Database() {
        this.mChromePeerManager.setListener(this.mPeerListener);
        this.mObjectMapper = new ObjectMapper();
    }

    public void add(DatabaseDriver2 databaseDriver) {
        this.mDatabaseDrivers.add(databaseDriver);
    }

    @ChromeDevtoolsMethod
    public void enable(JsonRpcPeer peer, JSONObject params) {
        this.mChromePeerManager.addPeer(peer);
    }

    @ChromeDevtoolsMethod
    public void disable(JsonRpcPeer peer, JSONObject params) {
        this.mChromePeerManager.removePeer(peer);
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getDatabaseTableNames(JsonRpcPeer peer, JSONObject params) throws JsonRpcException {
        DatabaseDescriptorHolder holder = this.mPeerListener.getDatabaseDescriptorHolder(((GetDatabaseTableNamesRequest) this.mObjectMapper.convertValue(params, GetDatabaseTableNamesRequest.class)).databaseId);
        try {
            GetDatabaseTableNamesResponse response = new GetDatabaseTableNamesResponse();
            response.tableNames = holder.driver.getTableNames(holder.descriptor);
            return response;
        } catch (SQLiteException e) {
            throw new JsonRpcException(new JsonRpcError(ErrorCode.INVALID_REQUEST, e.toString(), null));
        }
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult executeSQL(JsonRpcPeer peer, JSONObject params) {
        ExecuteSQLRequest request = (ExecuteSQLRequest) this.mObjectMapper.convertValue(params, ExecuteSQLRequest.class);
        DatabaseDescriptorHolder holder = this.mPeerListener.getDatabaseDescriptorHolder(request.databaseId);
        try {
            return holder.driver.executeSQL(holder.descriptor, request.query, new 1(this));
        } catch (RuntimeException e) {
            LogUtil.e(e, "Exception executing: %s", new Object[]{request.query});
            Error error = new Error();
            error.code = 0;
            error.message = e.getMessage();
            JsonRpcResult response = new ExecuteSQLResponse();
            response.sqlError = error;
            return response;
        }
    }

    private static ArrayList<String> flattenRows(Cursor cursor, int limit) {
        int column;
        Util.throwIfNot(limit >= 0);
        ArrayList<String> flatList = new ArrayList();
        int numColumns = cursor.getColumnCount();
        for (int row = 0; row < limit && cursor.moveToNext(); row++) {
            for (column = 0; column < numColumns; column++) {
                switch (cursor.getType(column)) {
                    case 0:
                        flatList.add(null);
                        break;
                    case 1:
                        flatList.add(String.valueOf(cursor.getLong(column)));
                        break;
                    case 2:
                        flatList.add(String.valueOf(cursor.getDouble(column)));
                        break;
                    case 4:
                        flatList.add(blobToString(cursor.getBlob(column)));
                        break;
                    default:
                        flatList.add(cursor.getString(column));
                        break;
                }
            }
        }
        if (!cursor.isAfterLast()) {
            for (column = 0; column < numColumns; column++) {
                flatList.add("{truncated}");
            }
        }
        return flatList;
    }

    private static String blobToString(byte[] blob) {
        if (blob.length <= 512 && fastIsAscii(blob)) {
            try {
                return new String(blob, "US-ASCII");
            } catch (UnsupportedEncodingException e) {
            }
        }
        return UNKNOWN_BLOB_LABEL;
    }

    private static boolean fastIsAscii(byte[] blob) {
        for (byte b : blob) {
            if ((b & -128) != 0) {
                return false;
            }
        }
        return true;
    }
}
