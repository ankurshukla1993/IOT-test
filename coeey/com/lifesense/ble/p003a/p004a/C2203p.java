package com.lifesense.ble.p003a.p004a;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;

class C2203p extends AbstractParser {
    C2203p() {
    }

    public C2202o m1439a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new C2202o(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1439a(codedInputStream, extensionRegistryLite);
    }
}
