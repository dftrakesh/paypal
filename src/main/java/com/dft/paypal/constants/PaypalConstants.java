package com.dft.paypal.constants;

public interface PaypalConstants extends BaseConstantCodes {

    String VERSION = "/v1";
    String API_BASE = "https://api.paypal.com" + VERSION;
    String TRANSACTION_ENDPOINT = "/reporting/transactions";
    String TOKEN_ENDPOINT = "/oauth2/token?grant_type=client_credentials";
}
