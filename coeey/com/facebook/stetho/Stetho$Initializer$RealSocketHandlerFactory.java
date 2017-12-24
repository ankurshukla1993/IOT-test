package com.facebook.stetho;

import com.facebook.stetho.Stetho.Initializer;
import com.facebook.stetho.dumpapp.DumpappHttpSocketLikeHandler;
import com.facebook.stetho.dumpapp.DumpappSocketLikeHandler;
import com.facebook.stetho.dumpapp.Dumper;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.inspector.DevtoolsSocketHandler;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.server.ProtocolDetectingSocketHandler;
import com.facebook.stetho.server.ProtocolDetectingSocketHandler.AlwaysMatchMatcher;
import com.facebook.stetho.server.ProtocolDetectingSocketHandler.ExactMagicMatcher;
import com.facebook.stetho.server.SocketHandler;
import com.facebook.stetho.server.SocketHandlerFactory;

class Stetho$Initializer$RealSocketHandlerFactory implements SocketHandlerFactory {
    final /* synthetic */ Initializer this$0;

    private Stetho$Initializer$RealSocketHandlerFactory(Initializer initializer) {
        this.this$0 = initializer;
    }

    public SocketHandler create() {
        ProtocolDetectingSocketHandler socketHandler = new ProtocolDetectingSocketHandler(Initializer.access$100(this.this$0));
        Iterable<DumperPlugin> dumperPlugins = this.this$0.getDumperPlugins();
        if (dumperPlugins != null) {
            Dumper dumper = new Dumper(dumperPlugins);
            socketHandler.addHandler(new ExactMagicMatcher(DumpappSocketLikeHandler.PROTOCOL_MAGIC), new DumpappSocketLikeHandler(dumper));
            DumpappHttpSocketLikeHandler legacyHandler = new DumpappHttpSocketLikeHandler(dumper);
            socketHandler.addHandler(new ExactMagicMatcher("GET /dumpapp".getBytes()), legacyHandler);
            socketHandler.addHandler(new ExactMagicMatcher("POST /dumpapp".getBytes()), legacyHandler);
        }
        Iterable<ChromeDevtoolsDomain> inspectorModules = this.this$0.getInspectorModules();
        if (inspectorModules != null) {
            socketHandler.addHandler(new AlwaysMatchMatcher(), new DevtoolsSocketHandler(Initializer.access$100(this.this$0), inspectorModules));
        }
        return socketHandler;
    }
}
