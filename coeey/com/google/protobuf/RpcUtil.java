package com.google.protobuf;

public final class RpcUtil {

    final class C20021 implements RpcCallback {
        final /* synthetic */ Message val$defaultInstance;
        final /* synthetic */ RpcCallback val$originalCallback;
        final /* synthetic */ Class val$originalClass;

        C20021(Class cls, Message message, RpcCallback rpcCallback) {
            this.val$originalClass = cls;
            this.val$defaultInstance = message;
            this.val$originalCallback = rpcCallback;
        }

        public void run(Message message) {
            Object obj;
            try {
                obj = (Message) this.val$originalClass.cast(message);
            } catch (ClassCastException e) {
                obj = RpcUtil.copyAsType(this.val$defaultInstance, message);
            }
            this.val$originalCallback.run(obj);
        }
    }

    final class C20032 implements RpcCallback {
        private boolean alreadyCalled = false;
        final /* synthetic */ RpcCallback val$originalCallback;

        C20032(RpcCallback rpcCallback) {
            this.val$originalCallback = rpcCallback;
        }

        public void run(Object obj) {
            synchronized (this) {
                if (this.alreadyCalled) {
                    throw new AlreadyCalledException();
                }
                this.alreadyCalled = true;
            }
            this.val$originalCallback.run(obj);
        }
    }

    public final class AlreadyCalledException extends RuntimeException {
        private static final long serialVersionUID = 5469741279507848266L;

        public AlreadyCalledException() {
            super("This RpcCallback was already called and cannot be called multiple times.");
        }
    }

    private RpcUtil() {
    }

    private static Message copyAsType(Message message, Message message2) {
        return message.newBuilderForType().mergeFrom(message2).build();
    }

    public static RpcCallback generalizeCallback(RpcCallback rpcCallback, Class cls, Message message) {
        return new C20021(cls, message, rpcCallback);
    }

    public static RpcCallback newOneTimeCallback(RpcCallback rpcCallback) {
        return new C20032(rpcCallback);
    }

    public static RpcCallback specializeCallback(RpcCallback rpcCallback) {
        return rpcCallback;
    }
}
