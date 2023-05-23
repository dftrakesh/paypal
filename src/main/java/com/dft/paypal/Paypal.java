package com.dft.paypal;

import com.dft.paypal.constants.PaypalConstants;
import com.dft.paypal.model.PaypalCredentials;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Paypal implements PaypalConstants {

    private final HttpClient client;
    int MAX_ATTEMPTS = 50;
    int TIME_OUT_DURATION = 60000;
    private final String authentication;
    private final String token;

    public Paypal(PaypalCredentials paypalCredentials) {
        client = HttpClient.newHttpClient();
        String authString = paypalCredentials.getClientId() + ":" + paypalCredentials.getClientSecret();
        byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
        this.authentication = new String(authEncBytes);
        this.token = paypalCredentials.getToken();
    }

    @SneakyThrows
    protected URI baseUrl(String path) {
        return new URI(API_BASE + FORWARD_SLASH + path);
    }

    @SneakyThrows
    protected HttpRequest get(URI uri) {
        HttpRequest.Builder builder = HttpRequest.newBuilder(uri)
                .header(HTTP_REQUEST_PROPERTY_ACCEPT, HTTP_REQUEST_ACCEPT_TYPE_JSON)
                .header(HTTP_REQUEST_PROPERTY_AUTHORIZATION, "Bearer " + token)
                .GET();
        return builder.build();
    }

    @SneakyThrows
    protected HttpRequest post(URI uri, final String jsonBody) {
        HttpRequest.Builder builder = HttpRequest.newBuilder(uri)
                .header(HTTP_REQUEST_PROPERTY_CONTENT_TYPE, HTTP_REQUEST_CONTENT_TYPE_JSON)
                .header(HTTP_REQUEST_PROPERTY_ACCEPT, HTTP_REQUEST_ACCEPT_TYPE_JSON)
                .header(HTTP_REQUEST_PROPERTY_AUTHORIZATION, "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody));
        return builder.build();
    }

    @SneakyThrows
    protected HttpRequest accessTokenRequest() {
        return HttpRequest.newBuilder(new URI(API_BASE + TOKEN_ENDPOINT))
                .header(HTTP_REQUEST_PROPERTY_CONTENT_TYPE, HTTP_REQUEST_PROPERTY_URL_ENCOADED)
                .header(HTTP_REQUEST_PROPERTY_ACCEPT, HTTP_REQUEST_ACCEPT_TYPE_JSON)
                .header(HTTP_REQUEST_PROPERTY_AUTHORIZATION, "Basic " + authentication)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
    }

    @SneakyThrows
    protected URI addParameters(URI uri, HashMap<String, String> params) {
        String query = uri.getQuery();
        StringBuilder builder = new StringBuilder();
        if (query != null)
            builder.append(query);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String keyValueParam = entry.getKey() + "=" + entry.getValue();
            if (!builder.toString().isEmpty())
                builder.append("&");
            builder.append(keyValueParam);
        }
        return new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), builder.toString(), uri.getFragment());
    }

    @SneakyThrows
    public <T> T getRequestWrapped(HttpRequest request, HttpResponse.BodyHandler<T> handler) {
        return client
                .sendAsync(request, handler)
                .thenComposeAsync(response -> tryResend(client, request, handler, response, 1))
                .get()
                .body();
    }

    @SneakyThrows
    public <T> CompletableFuture<HttpResponse<T>> tryResend(HttpClient client,
                                                            HttpRequest request,
                                                            HttpResponse.BodyHandler<T> handler,
                                                            HttpResponse<T> resp, int count) {
        if (resp.statusCode() == 429 && count < MAX_ATTEMPTS) {
            Thread.sleep(TIME_OUT_DURATION);
            return client.sendAsync(request, handler)
                    .thenComposeAsync(response -> tryResend(client, request, handler, response, count + 1));
        }
        return CompletableFuture.completedFuture(resp);
    }
}
