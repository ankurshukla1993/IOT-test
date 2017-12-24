package com.facebook.stetho.inspector.database;

import com.facebook.stetho.inspector.protocol.module.DatabaseDescriptor;

class DatabaseDriver2Adapter$StringDatabaseDescriptor implements DatabaseDescriptor {
    public final String name;

    public DatabaseDriver2Adapter$StringDatabaseDescriptor(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }
}
