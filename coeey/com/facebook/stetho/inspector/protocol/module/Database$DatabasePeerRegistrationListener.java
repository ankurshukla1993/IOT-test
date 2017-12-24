package com.facebook.stetho.inspector.protocol.module;

import android.util.SparseArray;
import com.facebook.stetho.inspector.helper.ObjectIdMapper;
import com.facebook.stetho.inspector.helper.PeersRegisteredListener;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.protocol.module.Database.AddDatabaseEvent;
import com.facebook.stetho.inspector.protocol.module.Database.DatabaseObject;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
class Database$DatabasePeerRegistrationListener extends PeersRegisteredListener {
    private final List<DatabaseDriver2> mDatabaseDrivers;
    @GuardedBy("this")
    private final SparseArray<Database$DatabaseDescriptorHolder> mDatabaseHolders;
    @GuardedBy("this")
    private final ObjectIdMapper mDatabaseIdMapper;

    private Database$DatabasePeerRegistrationListener(List<DatabaseDriver2> databaseDrivers) {
        this.mDatabaseHolders = new SparseArray();
        this.mDatabaseIdMapper = new ObjectIdMapper();
        this.mDatabaseDrivers = databaseDrivers;
    }

    public Database$DatabaseDescriptorHolder getDatabaseDescriptorHolder(String databaseId) {
        return (Database$DatabaseDescriptorHolder) this.mDatabaseHolders.get(Integer.parseInt(databaseId));
    }

    protected synchronized void onFirstPeerRegistered() {
        for (DatabaseDriver2<?> driver : this.mDatabaseDrivers) {
            for (DatabaseDescriptor desc : driver.getDatabaseNames()) {
                if (this.mDatabaseIdMapper.getIdForObject(desc) == null) {
                    this.mDatabaseHolders.put(Integer.valueOf(this.mDatabaseIdMapper.putObject(desc)).intValue(), new Database$DatabaseDescriptorHolder(driver, desc));
                }
            }
        }
    }

    protected synchronized void onLastPeerUnregistered() {
        this.mDatabaseIdMapper.clear();
        this.mDatabaseHolders.clear();
    }

    protected synchronized void onPeerAdded(JsonRpcPeer peer) {
        int N = this.mDatabaseHolders.size();
        for (int i = 0; i < N; i++) {
            int id = this.mDatabaseHolders.keyAt(i);
            Database$DatabaseDescriptorHolder holder = (Database$DatabaseDescriptorHolder) this.mDatabaseHolders.valueAt(i);
            DatabaseObject databaseParams = new DatabaseObject();
            databaseParams.id = String.valueOf(id);
            databaseParams.name = holder.descriptor.name();
            databaseParams.domain = holder.driver.getContext().getPackageName();
            databaseParams.version = "N/A";
            AddDatabaseEvent eventParams = new AddDatabaseEvent();
            eventParams.database = databaseParams;
            peer.invokeMethod("Database.addDatabase", eventParams, null);
        }
    }

    protected synchronized void onPeerRemoved(JsonRpcPeer peer) {
    }
}
