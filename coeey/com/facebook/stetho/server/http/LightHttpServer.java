package com.facebook.stetho.server.http;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.facebook.stetho.server.LeakyBufferedInputStream;
import com.facebook.stetho.server.SocketLike;
import humanize.util.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LightHttpServer {
    private static final String TAG = "LightHttpServer";
    private final HandlerRegistry mHandlerRegistry;

    private static class HttpMessageReader {
        private final StringBuilder mBuffer = new StringBuilder();
        private final BufferedInputStream mIn;
        private final NewLineDetector mNewLineDetector = new NewLineDetector();

        private static class NewLineDetector {
            private static final int STATE_ON_CR = 2;
            private static final int STATE_ON_CRLF = 3;
            private static final int STATE_ON_OTHER = 1;
            private int state;

            private NewLineDetector() {
                this.state = 1;
            }

            public void accept(char c) {
                switch (this.state) {
                    case 1:
                        if (c == '\r') {
                            this.state = 2;
                            return;
                        }
                        return;
                    case 2:
                        if (c == '\n') {
                            this.state = 3;
                            return;
                        } else {
                            this.state = 1;
                            return;
                        }
                    case 3:
                        if (c == '\r') {
                            this.state = 2;
                            return;
                        } else {
                            this.state = 1;
                            return;
                        }
                    default:
                        throw new IllegalArgumentException("Unknown state: " + this.state);
                }
            }

            public int state() {
                return this.state;
            }
        }

        public HttpMessageReader(BufferedInputStream in) {
            this.mIn = in;
        }

        @Nullable
        public String readLine() throws IOException {
            while (true) {
                int b = this.mIn.read();
                if (b >= 0) {
                    char c = (char) b;
                    this.mNewLineDetector.accept(c);
                    switch (this.mNewLineDetector.state()) {
                        case 1:
                            this.mBuffer.append(c);
                            break;
                        case 2:
                            break;
                        case 3:
                            String result = this.mBuffer.toString();
                            this.mBuffer.setLength(0);
                            return result;
                        default:
                            break;
                    }
                }
                return null;
            }
        }
    }

    public static class HttpMessageWriter {
        private static final byte[] CRLF = "\r\n".getBytes();
        private final BufferedOutputStream mOut;

        public HttpMessageWriter(BufferedOutputStream out) {
            this.mOut = out;
        }

        public void writeLine(String line) throws IOException {
            int N = line.length();
            for (int i = 0; i < N; i++) {
                this.mOut.write(line.charAt(i));
            }
            this.mOut.write(CRLF);
        }

        public void writeLine() throws IOException {
            this.mOut.write(CRLF);
        }

        public void flush() throws IOException {
            this.mOut.flush();
        }
    }

    public LightHttpServer(HandlerRegistry handlerRegistry) {
        this.mHandlerRegistry = handlerRegistry;
    }

    public void serve(SocketLike socket) throws IOException {
        LeakyBufferedInputStream input = new LeakyBufferedInputStream(socket.getInput(), 1024);
        OutputStream output = socket.getOutput();
        HttpMessageReader reader = new HttpMessageReader(input);
        HttpMessageWriter writer = new HttpMessageWriter(new BufferedOutputStream(output));
        SocketLike anotherSocketLike = new SocketLike(socket, input);
        LightHttpRequest scratchRequest = new LightHttpRequest();
        LightHttpResponse scratchResponse = new LightHttpResponse();
        while (true) {
            LightHttpRequest request = readRequestMessage(scratchRequest, reader);
            if (request != null) {
                LightHttpResponse response = scratchResponse;
                response.reset();
                if (dispatchToHandler(anotherSocketLike, request, response)) {
                    writeFullResponse(response, writer, output);
                } else {
                    return;
                }
            }
            return;
        }
    }

    private boolean dispatchToHandler(SocketLike socketLike, LightHttpRequest request, LightHttpResponse response) throws IOException {
        PrintWriter stackWriter;
        boolean z = true;
        HttpHandler handler = this.mHandlerRegistry.lookup(request.uri.getPath());
        if (handler == null) {
            response.code = HttpStatus.HTTP_NOT_FOUND;
            response.reasonPhrase = "Not found";
            response.body = LightHttpBody.create("No handler found\n", "text/plain");
        } else {
            try {
                z = handler.handleRequest(socketLike, request, response);
            } catch (RuntimeException e) {
                response.code = 500;
                response.reasonPhrase = "Internal Server Error";
                StringWriter stack = new StringWriter();
                stackWriter = new PrintWriter(stack);
                e.printStackTrace(stackWriter);
                response.body = LightHttpBody.create(stack.toString(), "text/plain");
            } finally {
                stackWriter.close();
            }
        }
        return z;
    }

    @Nullable
    private static LightHttpRequest readRequestMessage(LightHttpRequest request, HttpMessageReader reader) throws IOException {
        request.reset();
        String requestLine = reader.readLine();
        if (requestLine == null) {
            return null;
        }
        String[] requestParts = requestLine.split(Constants.SPACE, 3);
        if (requestParts.length != 3) {
            throw new IOException("Invalid request line: " + requestLine);
        }
        request.method = requestParts[0];
        request.uri = Uri.parse(requestParts[1]);
        request.protocol = requestParts[2];
        readHeaders(request, reader);
        return request;
    }

    private static void readHeaders(LightHttpMessage message, HttpMessageReader reader) throws IOException {
        while (true) {
            String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new EOFException();
            } else if (!"".equals(headerLine)) {
                String[] headerParts = headerLine.split(": ", 2);
                if (headerParts.length != 2) {
                    throw new IOException("Malformed header: " + headerLine);
                }
                String name = headerParts[0];
                String value = headerParts[1];
                message.headerNames.add(name);
                message.headerValues.add(value);
            } else {
                return;
            }
        }
    }

    private static void writeFullResponse(LightHttpResponse response, HttpMessageWriter writer, OutputStream output) throws IOException {
        response.prepare();
        writeResponseMessage(response, writer);
        if (response.body != null) {
            response.body.writeTo(output);
        }
    }

    public static void writeResponseMessage(LightHttpResponse response, HttpMessageWriter writer) throws IOException {
        writer.writeLine("HTTP/1.1 " + response.code + Constants.SPACE + response.reasonPhrase);
        int N = response.headerNames.size();
        for (int i = 0; i < N; i++) {
            String value = (String) response.headerValues.get(i);
            writer.writeLine(((String) response.headerNames.get(i)) + ": " + value);
        }
        writer.writeLine();
        writer.flush();
    }
}
