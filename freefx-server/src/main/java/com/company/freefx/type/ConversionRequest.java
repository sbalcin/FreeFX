package com.company.freefx.type;

import com.fasterxml.jackson.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sourceAmount",
        "sourceCurreny",
        "targetCurreny"
})
public class ConversionRequest {
    @JsonProperty("sourceAmount")
    private BigDecimal sourceAmount;
    @JsonProperty("sourceCurreny")
    private String sourceCurreny;
    @JsonProperty("targetCurreny")
    private String targetCurreny;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    @JsonProperty("sourceAmount")
    public BigDecimal getSourceAmount() {
        return sourceAmount;
    }

    @JsonProperty("sourceAmount")
    public void setSourceAmount(BigDecimal sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    @JsonProperty("sourceCurreny")
    public String getSourceCurreny() {
        return sourceCurreny;
    }

    @JsonProperty("sourceCurreny")
    public void setSourceCurreny(String sourceCurreny) {
        this.sourceCurreny = sourceCurreny;
    }

    @JsonProperty("targetCurreny")
    public String getTargetCurreny() {
        return targetCurreny;
    }

    @JsonProperty("targetCurreny")
    public void setTargetCurreny(String targetCurreny) {
        this.targetCurreny = targetCurreny;
    }

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
        return "ConversionRequest{" +
                "sourceAmount=" + sourceAmount +
                ", sourceCurreny=" + sourceCurreny +
                ", targetCurreny=" + targetCurreny +
                '}';
    }
}