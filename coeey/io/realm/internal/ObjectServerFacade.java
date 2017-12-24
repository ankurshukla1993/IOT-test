package io.realm.internal;

import android.content.Context;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmException;
import java.lang.reflect.InvocationTargetException;

public class ObjectServerFacade {
    private static final ObjectServerFacade nonSyncFacade = new ObjectServerFacade();
    private static ObjectServerFacade syncFacade;

    static {
        syncFacade = null;
        try {
            syncFacade = (ObjectServerFacade) Class.forName("io.realm.internal.SyncObjectServerFacade").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e2) {
            throw new RealmException("Failed to init SyncObjectServerFacade", e2);
        } catch (IllegalAccessException e3) {
            throw new RealmException("Failed to init SyncObjectServerFacade", e3);
        } catch (NoSuchMethodException e4) {
            throw new RealmException("Failed to init SyncObjectServerFacade", e4);
        } catch (InvocationTargetException e5) {
            throw new RealmException("Failed to init SyncObjectServerFacade", e5.getTargetException());
        }
    }

    public void init(Context context) {
    }

    public void realmClosed(RealmConfiguration configuration) {
    }

    public Object[] getUserAndServerUrl(RealmConfiguration config) {
        return new Object[6];
    }

    public static ObjectServerFacade getFacade(boolean needSyncFacade) {
        if (needSyncFacade) {
            return syncFacade;
        }
        return nonSyncFacade;
    }

    public static ObjectServerFacade getSyncFacadeIfPossible() {
        if (syncFacade != null) {
            return syncFacade;
        }
        return nonSyncFacade;
    }

    public void wrapObjectStoreSessionIfRequired(RealmConfiguration config) {
    }

    public String getSyncServerCertificateAssetName(RealmConfiguration config) {
        return null;
    }

    public String getSyncServerCertificateFilePath(RealmConfiguration config) {
        return null;
    }

    public void downloadRemoteChanges(RealmConfiguration config) {
    }

    public boolean wasDownloadInterrupted(Throwable throwable) {
        return false;
    }
}
