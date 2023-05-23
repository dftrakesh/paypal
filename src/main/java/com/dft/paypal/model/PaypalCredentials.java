package com.dft.paypal.model;

import lombok.Data;

@Data
public class PaypalCredentials {
    private String clientId;
    private String clientSecret;
    private String token;

}
