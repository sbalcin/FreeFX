package com.company.freefx.type;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "transactionId",
        "transactionStartDate",
        "transactionEndDate"
})
public class ConversionHistoryRequest {
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("transactionStartDate")
    private Long transactionStartDate;
    @JsonProperty("transactionEndDate")
    private Long transactionEndDate;

    @JsonProperty("transactionId")
    public String getTransactionId() {
        return transactionId;
    }

    @JsonProperty("transactionId")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @JsonProperty("transactionStartDate")
    public Long getTransactionStartDate() {
        return transactionStartDate;
    }

    @JsonProperty("transactionStartDate")
    public void setTransactionStartDate(Long transactionStartDate) {
        this.transactionStartDate = transactionStartDate;
    }

    @JsonProperty("transactionEndDate")
    public Long getTransactionEndDate() {
        return transactionEndDate;
    }

    @JsonProperty("transactionEndDate")
    public void setTransactionEndDate(Long transactionEndDate) {
        this.transactionEndDate = transactionEndDate;
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "ConversionHistoryRequest{" + "transactionId='" + transactionId + '\'' + ", transactionStartDate=" + transactionStartDate
                + ", transactionEndDate=" + transactionEndDate + ", additionalProperties=" + additionalProperties + '}';
    }
}