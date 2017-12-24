package com.facebook.stetho.inspector.protocol.module;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.facebook.GraphResponse;
import com.facebook.internal.ServerProtocol;
import com.facebook.stetho.inspector.protocol.module.BaseDatabaseDriver.ExecuteResultHandler;
import com.facebook.stetho.inspector.protocol.module.Database.ExecuteSQLResponse;
import java.util.Arrays;
import java.util.Collections;

class Database$1 implements ExecuteResultHandler<ExecuteSQLResponse> {
    final /* synthetic */ Database this$0;

    Database$1(Database this$0) {
        this.this$0 = this$0;
    }

    public ExecuteSQLResponse handleRawQuery() throws SQLiteException {
        ExecuteSQLResponse response = new ExecuteSQLResponse();
        response.columnNames = Collections.singletonList(GraphResponse.SUCCESS_KEY);
        response.values = Collections.singletonList(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        return response;
    }

    public ExecuteSQLResponse handleSelect(Cursor result) throws SQLiteException {
        ExecuteSQLResponse response = new ExecuteSQLResponse();
        response.columnNames = Arrays.asList(result.getColumnNames());
        response.values = Database.access$200(result, Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        return response;
    }

    public ExecuteSQLResponse handleInsert(long insertedId) throws SQLiteException {
        ExecuteSQLResponse response = new ExecuteSQLResponse();
        response.columnNames = Collections.singletonList("ID of last inserted row");
        response.values = Collections.singletonList(String.valueOf(insertedId));
        return response;
    }

    public ExecuteSQLResponse handleUpdateDelete(int count) throws SQLiteException {
        ExecuteSQLResponse response = new ExecuteSQLResponse();
        response.columnNames = Collections.singletonList("Modified rows");
        response.values = Collections.singletonList(String.valueOf(count));
        return response;
    }
}
