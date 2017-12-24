package com.lifesense.ble.p003a.p004a;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;

class C2211x extends AbstractParser {
    C2211x() {
    }

    public C2210w m1520a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new C2210w(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1520a(codedInputStream, extensionRegistryLite);
    }
}
