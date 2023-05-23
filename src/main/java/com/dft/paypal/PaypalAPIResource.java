package com.dft.paypal;

import com.dft.paypal.handler.JsonBodyHandler;
import com.dft.paypal.model.PaypalCredentials;
import com.dft.paypal.model.token.AccessToken;
import com.dft.paypal.model.transaction.TransactionDetailResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class PaypalAPIResource extends Paypal {
    private final ObjectMapper objectMapper;

    public PaypalAPIResource(PaypalCredentials paypalCredentials) {
        super(paypalCredentials);
        objectMapper = new ObjectMapper();
    }

    @SneakyThrows
    public AccessToken getAccessToken() {
        HttpRequest request = accessTokenRequest();
        HttpResponse.BodyHandler<AccessToken> handler = new JsonBodyHandler<>(AccessToken.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public TransactionDetailResponse getTransactionDetails(HashMap<String, String> params) {
        URI uri = new URI(API_BASE + TRANSACTION_ENDPOINT);
        uri = addParameters(uri, params);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<TransactionDetailResponse> handler = new JsonBodyHandler<>(TransactionDetailResponse.class);
        return getRequestWrapped(request, handler);
    }

}

