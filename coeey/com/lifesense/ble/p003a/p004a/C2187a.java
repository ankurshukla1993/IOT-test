package com.lifesense.ble.p003a.p004a;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.GeneratedMessage.FieldAccessorTable;

public final class C2187a {
    private static Descriptor f2132A;
    private static FieldAccessorTable f2133B;
    private static Descriptor f2134C;
    private static FieldAccessorTable f2135D;
    private static Descriptor f2136E;
    private static FieldAccessorTable f2137F;
    private static FileDescriptor f2138G;
    private static Descriptor f2139a;
    private static FieldAccessorTable f2140b;
    private static Descriptor f2141c;
    private static FieldAccessorTable f2142d;
    private static Descriptor f2143e;
    private static FieldAccessorTable f2144f;
    private static Descriptor f2145g;
    private static FieldAccessorTable f2146h;
    private static Descriptor f2147i;
    private static FieldAccessorTable f2148j;
    private static Descriptor f2149k;
    private static FieldAccessorTable f2150l;
    private static Descriptor f2151m;
    private static FieldAccessorTable f2152n;
    private static Descriptor f2153o;
    private static FieldAccessorTable f2154p;
    private static Descriptor f2155q;
    private static FieldAccessorTable f2156r;
    private static Descriptor f2157s;
    private static FieldAccessorTable f2158t;
    private static Descriptor f2159u;
    private static FieldAccessorTable f2160v;
    private static Descriptor f2161w;
    private static FieldAccessorTable f2162x;
    private static Descriptor f2163y;
    private static FieldAccessorTable f2164z;

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0017BluetoothProtobuf.proto\u0012 com.lifesense.bpmonitor.protobuf\"\r\n\u000bBaseRequest\"/\n\fBaseResponse\u0012\u000f\n\u0007ErrCode\u0018\u0001 \u0002(\u0005\u0012\u000e\n\u0006ErrMsg\u0018\u0002 \u0001(\t\"\n\n\bBasePush\"ù\u0001\n\u000bAuthRequest\u0012B\n\u000bBaseRequest\u0018\u0001 \u0002(\u000b2-.com.lifesense.bpmonitor.protobuf.BaseRequest\u0012 \n\u0018Md5DeviceTypeAndDeviceId\u0018\u0002 \u0002(\f\u0012\u0014\n\fProtoVersion\u0018\u0003 \u0002(\u0005\u0012\u0011\n\tAuthProto\u0018\u0004 \u0002(\u0005\u0012\u0012\n\nAuthMethod\u0018\u0005 \u0002(\u0005\u0012\u000f\n\u0007AesSign\u0018\u0006 \u0002(\f\u0012\u0010\n\bTimeZone\u0018\n \u0001(\t\u0012\u0010\n\bLanguage\u0018\u000b \u0001(\t\u0012\u0012\n\nDeviceName\u0018\f \u0001(\t\"k\n\fAuthRespo", "nse\u0012D\n\fBaseResponse\u0018\u0001 \u0002(\u000b2..com.lifesense.bpmonitor.protobuf.BaseResponse\u0012\u0015\n\rAesSessionKey\u0018\u0002 \u0002(\f\"}\n\u000bInitRequest\u0012B\n\u000bBaseRequest\u0018\u0001 \u0002(\u000b2-.com.lifesense.bpmonitor.protobuf.BaseRequest\u0012\u0017\n\u000fRespFieldFilter\u0018\u0002 \u0001(\f\u0012\u0011\n\tChallenge\u0018\u0003 \u0001(\f\"Ç\u0002\n\fInitResponse\u0012D\n\fBaseResponse\u0018\u0001 \u0002(\u000b2..com.lifesense.bpmonitor.protobuf.BaseResponse\u0012\u0012\n\nUserIdHigh\u0018\u0002 \u0002(\r\u0012\u0011\n\tUserIdLow\u0018\u0003 \u0002(\r\u0012\u0018\n\u0010ChalleangeAnswer\u0018\u0004 \u0001(\r\u0012\u0012\n\nInitScence\u0018\u0005 \u0001(\u0005\u0012!\n\u0019A", "utoSyncMaxDurationSecond\u0018\u0006 \u0001(\r\u0012\u0014\n\fUserNickName\u0018\u000b \u0001(\t\u0012\u0014\n\fPlatformType\u0018\f \u0001(\u0005\u0012\r\n\u0005Model\u0018\r \u0001(\t\u0012\n\n\u0002Os\u0018\u000e \u0001(\t\u0012\f\n\u0004Time\u0018\u000f \u0001(\u0005\u0012\u0010\n\bTimeZone\u0018\u0010 \u0001(\u0005\u0012\u0012\n\nTimeString\u0018\u0011 \u0001(\t\"\u0001\n SendDataToManufacturerSvrRequest\u0012B\n\u000bBaseRequest\u0018\u0001 \u0002(\u000b2-.com.lifesense.bpmonitor.protobuf.BaseRequest\u0012\f\n\u0004Data\u0018\u0002 \u0002(\f\u0012\f\n\u0004Type\u0018\u0003 \u0001(\r\"w\n!SendDataToManufacturerSvrResponse\u0012D\n\fBaseResponse\u0018\u0001 \u0002(\u000b2..com.lifesense.bpmonitor.protobuf.BaseResponse\u0012\f\n\u0004Dat", "a\u0018\u0002 \u0001(\f\"w\n\u001bManufacturerSvrSendDataPush\u0012<\n\bBasePush\u0018\u0001 \u0002(\u000b2*.com.lifesense.bpmonitor.protobuf.BasePush\u0012\f\n\u0004Data\u0018\u0002 \u0002(\f\u0012\f\n\u0004Type\u0018\u0003 \u0001(\r\"t\n\u000eSwitchViewPush\u0012<\n\bBasePush\u0018\u0001 \u0002(\u000b2*.com.lifesense.bpmonitor.protobuf.BasePush\u0012\u0014\n\fSwitchViewOp\u0018\u0002 \u0002(\u0005\u0012\u000e\n\u0006ViewId\u0018\u0003 \u0002(\u0005\"o\n\u0013SwitchBackgroudPush\u0012<\n\bBasePush\u0018\u0001 \u0002(\u000b2*.com.lifesense.bpmonitor.protobuf.BasePush\u0012\u001a\n\u0012SwitchBackgroundOp\u0018\u0002 \u0002(\u0005\"§\u0001\n\u0010WristbandRequest\u0012Q\n\bStepData\u0018\u0001 \u0003(\u000b2?", ".com.lifesense.bpmonitor.protobuf.WristbandRequest.StepDataItem\u0012\u000f\n\u0007ExtData\u0018\u0002 \u0001(\f\u001a/\n\fStepDataItem\u0012\f\n\u0004Step\u0018\u0001 \u0001(\r\u0012\u0011\n\tTimestamp\u0018\u0002 \u0001(\r\"\u0013\n\u0011WristBandResponse\"\u000f\n\rWrishBandPush*²\u0002\n\u0007EmCmdId\u0012\f\n\bECI_none\u0010\u0000\u0012\u0011\n\fECI_req_auth\u0010N\u0012&\n!ECI_req_sendDataToManufacturerSvr\u0010N\u0012\u0011\n\fECI_req_init\u0010N\u0012\u0013\n\rECI_resp_auth\u0010¡\u0001\u0012(\n\"ECI_resp_sendDataToManufacturerSvr\u0010¢\u0001\u0012\u0013\n\rECI_resp_init\u0010£\u0001\u0012&\n ECI_push_manufacturerSvrSendData\u0010±ê\u0001\u0012\u0019\n\u0013E", "CI_push_switchView\u0010²ê\u0001\u0012\u001e\n\u0018ECI_push_switchBackgroud\u0010³ê\u0001\u0012\u0014\n\u000eECI_err_decode\u0010¯ê\u0001*ì\u0002\n\u000bEmErrorCode\u0012\u0017\n\nEEC_system\u0010ÿÿÿÿÿÿÿÿÿ\u0001\u0012\u0019\n\fEEC_needAuth\u0010þÿÿÿÿÿÿÿÿ\u0001\u0012\u001f\n\u0012EEC_sessionTimeout\u0010ýÿÿÿÿÿÿÿÿ\u0001\u0012\u0017\n\nEEC_decode\u0010üÿÿÿÿÿÿÿÿ\u0001\u0012\u001e\n\u0011EEC_deviceIsBlock\u0010ûÿÿÿÿÿÿÿÿ\u0001\u0012.\n!EEC_serviceUnAvalibleInBackground\u0010úÿÿÿÿÿÿÿÿ\u0001\u0012-\n EEC_deviceProtoVersionNeedUpdate\u0010ùÿÿÿÿÿÿÿÿ\u0001\u0012,\n\u001fEEC_phoneProtoVersionNeedUpdate\u0010øÿÿÿÿÿÿÿÿ\u0001\u0012\u001e\n\u0011EEC_maxReqInQueue\u0010÷ÿÿ", "ÿÿÿÿÿÿ\u0001\u0012\"\n\u0015EEC_userExitWxAccount\u0010öÿÿÿÿÿÿÿÿ\u0001* \u0001\n\u0015EmInitRespFieldFilter\u0012\u0016\n\u0012EIRFF_userNickName\u0010\u0001\u0012\u0016\n\u0012EIRFF_platformType\u0010\u0002\u0012\u000f\n\u000bEIRFF_model\u0010\u0004\u0012\f\n\bEIRFF_os\u0010\b\u0012\u000e\n\nEIRFF_time\u0010\u0010\u0012\u0012\n\u000eEIRFF_timeZone\u0010 \u0012\u0014\n\u0010EIRFF_timeString\u0010@*4\n\fEmInitScence\u0012\u0012\n\u000eEIS_deviceChat\u0010\u0001\u0012\u0010\n\fEIS_autoSync\u0010\u0002*q\n\u000eEmPlatformType\u0012\u000b\n\u0007EPT_ios\u0010\u0001\u0012\u000f\n\u000bEPT_andriod\u0010\u0002\u0012\n\n\u0006EPT_wp\u0010\u0003\u0012\r\n\tEPT_s60v3\u0010\u0004\u0012\r\n\tEPT_s60v5\u0010\u0005\u0012\u000b\n\u0007EPT_s40\u0010\u0006\u0012\n\n\u0006EPT_bb\u0010\u0007*/\n\u000eEmSwitchViewOp\u0012\u000e\n\nESV", "O_enter\u0010\u0001\u0012\r\n\tESVO_exit\u0010\u0002*\"\n\bEmViewId\u0012\u0016\n\u0012EVI_deviceChatView\u0010\u0001*Y\n\u0014EmSwitchBackgroundOp\u0012\u0018\n\u0014ESBO_enterBackground\u0010\u0001\u0012\u0017\n\u0013ESBO_enterForground\u0010\u0002\u0012\u000e\n\nESBO_sleep\u0010\u0003"}, new FileDescriptor[0], new C2188b());
    }

    public static FileDescriptor m1234a() {
        return f2138G;
    }
}
