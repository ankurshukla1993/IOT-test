package com.cooey.maya;

import com.google.common.net.HttpHeaders;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.websocket.ClientEndpointConfig.Configurator;

public class ClientConfigurator extends Configurator {
    static volatile boolean called = false;

    public void beforeRequest(Map<String, List<String>> headers) {
        called = true;
        headers.put(HttpHeaders.CONNECTION, Arrays.asList(new String[]{"keep-alive", HttpHeaders.UPGRADE}));
    }
}
