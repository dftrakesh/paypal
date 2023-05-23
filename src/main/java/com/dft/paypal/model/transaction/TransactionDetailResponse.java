
package com.dft.paypal.model.transaction;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDetailResponse {

    @JsonProperty("transaction_details")
    private List<TransactionDetail> transactionDetails;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("last_refreshed_datetime")
    private String lastRefreshedDatetime;
    @JsonProperty("page")
    private Integer page;
    @JsonProperty("total_items")
    private Integer totalItems;
    @JsonProperty("total_pages")
    private Integer totalPages;
    @JsonProperty("links")
    private List<Link> links;

}
