package io.realm;

class BaseRealm$3 implements Callback0 {
    final /* synthetic */ BaseRealm this$0;

    BaseRealm$3(BaseRealm this$0) {
        this.this$0 = this$0;
    }

    public void onCall() {
        if (this.this$0.sharedRealm == null || this.this$0.sharedRealm.isClosed()) {
            throw new IllegalStateException("This Realm instance has already been closed, making it unusable.");
        }
        this.this$0.sharedRealm.stopWaitForChange();
    }
}
