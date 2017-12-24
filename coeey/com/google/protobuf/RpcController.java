package com.google.protobuf;

public interface RpcController {
    String errorText();

    boolean failed();

    boolean isCanceled();

    void notifyOnCancel(RpcCallback rpcCallback);

    void reset();

    void setFailed(String str);

    void startCancel();
}
