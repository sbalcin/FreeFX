package com.company.freefx.type;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"date",
"rates",
"base"
})
public class RateProviderResponse {

@JsonProperty("date")
private String date;
@JsonProperty("rates")
private Rates rates;
@JsonProperty("base")
private String base;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("date")
public String getDate() {
return date;
}

@JsonProperty("date")
public void setDate(String date) {
this.date = date;
}

@JsonProperty("rates")
public Rates getRates() {
return rates;
}

@JsonProperty("rates")
public void setRates(Rates rates) {
this.rates = rates;
}

@JsonProperty("base")
public String getBase() {
return base;
}

@JsonProperty("base")
public void setBase(String base) {
this.base = base;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}