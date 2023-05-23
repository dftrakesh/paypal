
package com.dft.paypal.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayerName {

    @JsonProperty("given_name")
    private String givenName;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("alternate_full_name")
    private String alternateFullName;
}
