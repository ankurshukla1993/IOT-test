package io.realm;

public abstract class Realm$Callback extends BaseRealm$InstanceCallback<Realm> {
    public abstract void onSuccess(Realm realm);

    public void onError(Throwable exception) {
        super.onError(exception);
    }
}
