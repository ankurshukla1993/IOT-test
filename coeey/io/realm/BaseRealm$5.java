package io.realm;

import java.util.concurrent.atomic.AtomicBoolean;

class BaseRealm$5 implements Callback {
    final /* synthetic */ RealmConfiguration val$configuration;
    final /* synthetic */ AtomicBoolean val$fileNotFound;
    final /* synthetic */ RealmMigration val$migration;

    public void onResult(int r11) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1431)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r10 = this;
        if (r11 == 0) goto L_0x0021;
    L_0x0002:
        r7 = new java.lang.IllegalStateException;
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = "Cannot migrate a Realm file that is already open: ";
        r8 = r8.append(r9);
        r9 = r10.val$configuration;
        r9 = r9.getPath();
        r8 = r8.append(r9);
        r8 = r8.toString();
        r7.<init>(r8);
        throw r7;
    L_0x0021:
        r4 = new java.io.File;
        r7 = r10.val$configuration;
        r7 = r7.getPath();
        r4.<init>(r7);
        r7 = r4.exists();
        if (r7 != 0) goto L_0x0039;
    L_0x0032:
        r7 = r10.val$fileNotFound;
        r8 = 1;
        r7.set(r8);
    L_0x0038:
        return;
    L_0x0039:
        r7 = r10.val$configuration;
        r1 = r7.getSchemaMediator();
        r5 = new io.realm.internal.OsSchemaInfo;
        r7 = r1.getExpectedObjectSchemaInfoMap();
        r7 = r7.values();
        r5.<init>(r7);
        r2 = 0;
        r7 = r10.val$migration;
        if (r7 == 0) goto L_0x0078;
    L_0x0051:
        r3 = r10.val$migration;
    L_0x0053:
        if (r3 == 0) goto L_0x0059;
    L_0x0055:
        r2 = io.realm.BaseRealm.access$000(r3);
    L_0x0059:
        r7 = new io.realm.internal.OsRealmConfig$Builder;
        r8 = r10.val$configuration;
        r7.<init>(r8);
        r8 = 0;
        r7 = r7.autoUpdateNotification(r8);
        r7 = r7.schemaInfo(r5);
        r0 = r7.migrationCallback(r2);
        r6 = 0;
        r6 = io.realm.internal.SharedRealm.getInstance(r0);	 Catch:{ all -> 0x007f }
        if (r6 == 0) goto L_0x0038;
    L_0x0074:
        r6.close();
        goto L_0x0038;
    L_0x0078:
        r7 = r10.val$configuration;
        r3 = r7.getMigration();
        goto L_0x0053;
    L_0x007f:
        r7 = move-exception;
        if (r6 == 0) goto L_0x0085;
    L_0x0082:
        r6.close();
    L_0x0085:
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.BaseRealm$5.onResult(int):void");
    }

    BaseRealm$5(RealmConfiguration realmConfiguration, AtomicBoolean atomicBoolean, RealmMigration realmMigration) {
        this.val$configuration = realmConfiguration;
        this.val$fileNotFound = atomicBoolean;
        this.val$migration = realmMigration;
    }
}
