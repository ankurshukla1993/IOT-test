package okhttp3;

import java.io.IOException;
import java.net.Authenticator;
import java.net.Authenticator.RequestorType;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.List;

public final class JavaNetAuthenticator implements Authenticator {
    public Request authenticate(Route route, Response response) throws IOException {
        List<Challenge> challenges = response.challenges();
        Request request = response.request();
        HttpUrl url = request.url();
        boolean proxyAuthorization = response.code() == 407;
        Proxy proxy = route.proxy();
        int size = challenges.size();
        for (int i = 0; i < size; i++) {
            Challenge challenge = (Challenge) challenges.get(i);
            if ("Basic".equalsIgnoreCase(challenge.scheme())) {
                PasswordAuthentication auth;
                if (proxyAuthorization) {
                    InetSocketAddress proxyAddress = (InetSocketAddress) proxy.address();
                    auth = Authenticator.requestPasswordAuthentication(proxyAddress.getHostName(), getConnectToInetAddress(proxy, url), proxyAddress.getPort(), url.scheme(), challenge.realm(), challenge.scheme(), url.url(), RequestorType.PROXY);
                } else {
                    auth = Authenticator.requestPasswordAuthentication(url.host(), getConnectToInetAddress(proxy, url), url.port(), url.scheme(), challenge.realm(), challenge.scheme(), url.url(), RequestorType.SERVER);
                }
                if (auth != null) {
                    return request.newBuilder().header(proxyAuthorization ? "Proxy-Authorization" : "Authorization", Credentials.basic(auth.getUserName(), new String(auth.getPassword()))).build();
                }
            }
        }
        return null;
    }

    private InetAddress getConnectToInetAddress(Proxy proxy, HttpUrl url) throws IOException {
        if (proxy == null || proxy.type() == Type.DIRECT) {
            return InetAddress.getByName(url.host());
        }
        return ((InetSocketAddress) proxy.address()).getAddress();
    }
}
