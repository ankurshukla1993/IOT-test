package com.lifesense.ble.p003a.p004a;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.GeneratedMessage.FieldAccessorTable;

class C2188b implements InternalDescriptorAssigner {
    C2188b() {
    }

    public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
        C2187a.f2138G = fileDescriptor;
        C2187a.f2139a = (Descriptor) C2187a.m1234a().getMessageTypes().get(0);
        C2187a.f2140b = new FieldAccessorTable(C2187a.f2139a, new String[0]);
        C2187a.f2141c = (Descriptor) C2187a.m1234a().getMessageTypes().get(1);
        C2187a.f2142d = new FieldAccessorTable(C2187a.f2141c, new String[]{"ErrCode", "ErrMsg"});
        C2187a.f2143e = (Descriptor) C2187a.m1234a().getMessageTypes().get(2);
        C2187a.f2144f = new FieldAccessorTable(C2187a.f2143e, new String[0]);
        C2187a.f2145g = (Descriptor) C2187a.m1234a().getMessageTypes().get(3);
        C2187a.f2146h = new FieldAccessorTable(C2187a.f2145g, new String[]{"BaseRequest", "Md5DeviceTypeAndDeviceId", "ProtoVersion", "AuthProto", "AuthMethod", "AesSign", "TimeZone", "Language", "DeviceName"});
        C2187a.f2147i = (Descriptor) C2187a.m1234a().getMessageTypes().get(4);
        C2187a.f2148j = new FieldAccessorTable(C2187a.f2147i, new String[]{"BaseResponse", "AesSessionKey"});
        C2187a.f2149k = (Descriptor) C2187a.m1234a().getMessageTypes().get(5);
        C2187a.f2150l = new FieldAccessorTable(C2187a.f2149k, new String[]{"BaseRequest", "RespFieldFilter", "Challenge"});
        C2187a.f2151m = (Descriptor) C2187a.m1234a().getMessageTypes().get(6);
        C2187a.f2152n = new FieldAccessorTable(C2187a.f2151m, new String[]{"BaseResponse", "UserIdHigh", "UserIdLow", "ChalleangeAnswer", "InitScence", "AutoSyncMaxDurationSecond", "UserNickName", "PlatformType", "Model", "Os", "Time", "TimeZone", "TimeString"});
        C2187a.f2153o = (Descriptor) C2187a.m1234a().getMessageTypes().get(7);
        C2187a.f2154p = new FieldAccessorTable(C2187a.f2153o, new String[]{"BaseRequest", "Data", "Type"});
        C2187a.f2155q = (Descriptor) C2187a.m1234a().getMessageTypes().get(8);
        C2187a.f2156r = new FieldAccessorTable(C2187a.f2155q, new String[]{"BaseResponse", "Data"});
        C2187a.f2157s = (Descriptor) C2187a.m1234a().getMessageTypes().get(9);
        C2187a.f2158t = new FieldAccessorTable(C2187a.f2157s, new String[]{"BasePush", "Data", "Type"});
        C2187a.f2159u = (Descriptor) C2187a.m1234a().getMessageTypes().get(10);
        C2187a.f2160v = new FieldAccessorTable(C2187a.f2159u, new String[]{"BasePush", "SwitchViewOp", "ViewId"});
        C2187a.f2161w = (Descriptor) C2187a.m1234a().getMessageTypes().get(11);
        C2187a.f2162x = new FieldAccessorTable(C2187a.f2161w, new String[]{"BasePush", "SwitchBackgroundOp"});
        C2187a.f2163y = (Descriptor) C2187a.m1234a().getMessageTypes().get(12);
        C2187a.f2164z = new FieldAccessorTable(C2187a.f2163y, new String[]{"StepData", "ExtData"});
        C2187a.f2132A = (Descriptor) C2187a.f2163y.getNestedTypes().get(0);
        C2187a.f2133B = new FieldAccessorTable(C2187a.f2132A, new String[]{"Step", "Timestamp"});
        C2187a.f2134C = (Descriptor) C2187a.m1234a().getMessageTypes().get(13);
        C2187a.f2135D = new FieldAccessorTable(C2187a.f2134C, new String[0]);
        C2187a.f2136E = (Descriptor) C2187a.m1234a().getMessageTypes().get(14);
        C2187a.f2137F = new FieldAccessorTable(C2187a.f2136E, new String[0]);
        return null;
    }
}
