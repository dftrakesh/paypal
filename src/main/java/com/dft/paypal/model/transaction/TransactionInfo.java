
package com.dft.paypal.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionInfo {

    @JsonProperty("paypal_account_id")
    private String paypalAccountId;
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("transaction_event_code")
    private String transactionEventCode;
    @JsonProperty("transaction_initiation_date")
    private String transactionInitiationDate;
    @JsonProperty("transaction_updated_date")
    private String transactionUpdatedDate;
    @JsonProperty("transaction_amount")
    private TransactionAmount transactionAmount;
    @JsonProperty("fee_amount")
    private FeeAmount feeAmount;
    @JsonProperty("transaction_status")
    private String transactionStatus;
    @JsonProperty("invoice_id")
    private String invoiceId;
    @JsonProperty("custom_field")
    private String customField;
    @JsonProperty("protection_eligibility")
    private String protectionEligibility;

}
