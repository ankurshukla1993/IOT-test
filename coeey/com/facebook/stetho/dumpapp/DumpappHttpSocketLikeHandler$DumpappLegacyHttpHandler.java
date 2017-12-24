package com.facebook.stetho.dumpapp;

import com.facebook.stetho.server.SocketLike;
import com.facebook.stetho.server.http.HttpHandler;
import com.facebook.stetho.server.http.HttpStatus;
import com.facebook.stetho.server.http.LightHttpBody;
import com.facebook.stetho.server.http.LightHttpRequest;
import com.facebook.stetho.server.http.LightHttpResponse;
import com.lifesense.ble.bean.WeightData_A3;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

class DumpappHttpSocketLikeHandler$DumpappLegacyHttpHandler implements HttpHandler {
    private static final String CONTENT_TYPE = "application/octet-stream";
    private static final String QUERY_PARAM_ARGV = "argv";
    private static final String RESPONSE_HEADER_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private final Dumper mDumper;

    public DumpappHttpSocketLikeHandler$DumpappLegacyHttpHandler(Dumper dumper) {
        this.mDumper = dumper;
    }

    public boolean handleRequest(SocketLike socket, LightHttpRequest request, LightHttpResponse response) throws IOException {
        boolean getMethod;
        boolean postMethod = HttpRequest.METHOD_POST.equals(request.method);
        if (postMethod || !HttpRequest.METHOD_GET.equals(request.method)) {
            getMethod = false;
        } else {
            getMethod = true;
        }
        if (getMethod || postMethod) {
            List<String> argv = request.uri.getQueryParameters(QUERY_PARAM_ARGV);
            ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
            Framer framer = new Framer(new ByteArrayInputStream(new byte[0]), outputBuffer);
            framer.getStderr().println("*** " + (postMethod ? WeightData_A3.IMPEDANCE_STATUS_ERROR : "WARNING") + ": Using legacy HTTP protocol; update dumpapp script! ***");
            if (getMethod) {
                DumpappSocketLikeHandler.dump(this.mDumper, framer, (String[]) argv.toArray(new String[argv.size()]));
            } else {
                framer.writeExitCode(1);
            }
            response.code = 200;
            response.reasonPhrase = "OK";
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.body = LightHttpBody.create(outputBuffer.toByteArray(), CONTENT_TYPE);
        } else {
            response.code = HttpStatus.HTTP_NOT_IMPLEMENTED;
            response.reasonPhrase = "Not implemented";
            response.body = LightHttpBody.create(request.method + " not implemented", "text/plain");
        }
        return true;
    }
}
