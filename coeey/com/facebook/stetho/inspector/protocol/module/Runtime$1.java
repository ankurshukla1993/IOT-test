package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.console.RuntimeRepl;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;

class Runtime$1 implements RuntimeReplFactory {

    class C14541 implements RuntimeRepl {
        C14541() {
        }

        public Object evaluate(String expression) throws Throwable {
            return "Not supported with legacy Runtime module";
        }
    }

    Runtime$1() {
    }

    public RuntimeRepl newInstance() {
        return new C14541();
    }
}
