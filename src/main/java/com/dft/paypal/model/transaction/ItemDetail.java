
package com.dft.paypal.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDetail {

    @JsonProperty("item_name")
    private String itemName;
    @JsonProperty("item_quantity")
    private String itemQuantity;
    @JsonProperty("item_unit_price")
    private ItemUnitPrice itemUnitPrice;
    @JsonProperty("item_amount")
    private ItemAmount itemAmount;
    @JsonProperty("total_item_amount")
    private TotalItemAmount totalItemAmount;
    @JsonProperty("invoice_number")
    private String invoiceNumber;
}
