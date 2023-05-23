
package com.dft.paypal.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayerInfo {

    @JsonProperty("account_id")
    private String accountId;
    @JsonProperty("email_address")
    private String emailAddress;
    @JsonProperty("address_status")
    private String addressStatus;
    @JsonProperty("payer_status")
    private String payerStatus;
    @JsonProperty("payer_name")
    private PayerName payerName;
    @JsonProperty("country_code")
    private String countryCode;

}
