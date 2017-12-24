package io.realm;

import io.realm.internal.SharedRealm.SchemaChangedCallback;

class BaseRealm$1 implements SchemaChangedCallback {
    final /* synthetic */ BaseRealm this$0;

    BaseRealm$1(BaseRealm this$0) {
        this.this$0 = this$0;
    }

    public void onSchemaChanged() {
        RealmSchema schema = this.this$0.getSchema();
        if (schema != null) {
            schema.refresh();
        }
    }
}
