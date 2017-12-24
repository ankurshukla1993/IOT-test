package com.facebook.stetho.dumpapp;

import com.facebook.stetho.server.SocketLike;
import com.facebook.stetho.server.SocketLikeHandler;
import com.facebook.stetho.server.http.ExactPathMatcher;
import com.facebook.stetho.server.http.HandlerRegistry;
import com.facebook.stetho.server.http.LightHttpServer;
import java.io.IOException;

@Deprecated
public class DumpappHttpSocketLikeHandler implements SocketLikeHandler {
    private final LightHttpServer mServer;

    public DumpappHttpSocketLikeHandler(Dumper dumper) {
        HandlerRegistry registry = new HandlerRegistry();
        registry.register(new ExactPathMatcher("/dumpapp"), new DumpappLegacyHttpHandler(dumper));
        this.mServer = new LightHttpServer(registry);
    }

    public void onAccepted(SocketLike socket) throws IOException {
        this.mServer.serve(socket);
    }
}
