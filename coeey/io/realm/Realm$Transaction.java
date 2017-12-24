package io.realm;

public interface Realm$Transaction {

    public static class Callback {
        public void onSuccess() {
        }

        public void onError(Exception ignore) {
        }
    }

    public interface OnError {
        void onError(Throwable th);
    }

    public interface OnSuccess {
        void onSuccess();
    }

    void execute(Realm realm);
}
