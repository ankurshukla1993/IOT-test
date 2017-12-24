package com.lifesense.ble.p003a.p004a;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;

class C2191d extends AbstractParser {
    C2191d() {
    }

    public C2190c m1315a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new C2190c(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1315a(codedInputStream, extensionRegistryLite);
    }
}
