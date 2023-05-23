
package com.dft.paypal.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDetail {

    @JsonProperty("transaction_info")
    private TransactionInfo transactionInfo;
    @JsonProperty("payer_info")
    private PayerInfo payerInfo;
    @JsonProperty("shipping_info")
    private ShippingInfo shippingInfo;
    @JsonProperty("cart_info")
    private CartInfo cartInfo;
    @JsonProperty("store_info")
    private StoreInfo storeInfo;
    @JsonProperty("auction_info")
    private AuctionInfo auctionInfo;
    @JsonProperty("incentive_info")
    private IncentiveInfo incentiveInfo;
}
