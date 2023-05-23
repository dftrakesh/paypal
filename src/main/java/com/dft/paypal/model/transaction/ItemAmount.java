
package com.dft.paypal.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemAmount {

    @JsonProperty("currency_code")
    private String currencyCode;
    @JsonProperty("value")
    private String value;

}