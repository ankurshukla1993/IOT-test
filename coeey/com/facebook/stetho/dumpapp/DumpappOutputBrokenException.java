package com.facebook.stetho.dumpapp;

class DumpappOutputBrokenException extends RuntimeException {
    public DumpappOutputBrokenException(String detailMessage) {
        super(detailMessage);
    }

    public DumpappOutputBrokenException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DumpappOutputBrokenException(Throwable throwable) {
        super(throwable);
    }
}
