package com.facebook.stetho.inspector.network;

import com.facebook.stetho.common.Util;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

public abstract class DownloadingAsyncPrettyPrinterFactory implements AsyncPrettyPrinterFactory {
    protected abstract void doPrint(PrintWriter printWriter, InputStream inputStream, String str) throws IOException;

    @Nullable
    protected abstract MatchResult matchAndParseHeader(String str, String str2);

    public AsyncPrettyPrinter getInstance(String headerName, String headerValue) {
        MatchResult result = matchAndParseHeader(headerName, headerValue);
        if (result == null) {
            return null;
        }
        URL schemaURL = parseURL(result.getSchemaUri());
        if (schemaURL == null) {
            return getErrorAsyncPrettyPrinter(headerName, headerValue);
        }
        ExecutorService executorService = AsyncPrettyPrinterExecutorHolder.getExecutorService();
        if (executorService != null) {
            return new 1(this, executorService.submit(new Request(schemaURL)), result);
        }
        return null;
    }

    @Nullable
    private static URL parseURL(String uri) {
        try {
            return new URL(uri);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private static void doErrorPrint(PrintWriter output, InputStream payload, String errorMessage) throws IOException {
        output.print(errorMessage + "\n" + Util.readAsUTF8(payload));
    }

    private static AsyncPrettyPrinter getErrorAsyncPrettyPrinter(String headerName, String headerValue) {
        return new 2(headerName, headerValue);
    }
}
