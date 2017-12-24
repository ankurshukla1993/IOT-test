package com.facebook.stetho.dumpapp;

class UnexpectedFrameException extends DumpappFramingException {
    public UnexpectedFrameException(byte expected, byte got) {
        super("Expected '" + expected + "', got: '" + got + "'");
    }
}
