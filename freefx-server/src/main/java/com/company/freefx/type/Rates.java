package com.company.freefx.type;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"BGN",
"CAD",
"BRL",
"HUF",
"DKK",
"JPY",
"ILS",
"TRY",
"RON",
"GBP",
"PHP",
"HRK",
"NOK",
"ZAR",
"MXN",
"AUD",
"USD",
"KRW",
"HKD",
"EUR",
"ISK",
"CZK",
"THB",
"MYR",
"NZD",
"PLN",
"CHF",
"SEK",
"CNY",
"SGD",
"INR",
"IDR",
"RUB"
})
public class Rates {

@JsonProperty("BGN")
private Double bGN;
@JsonProperty("CAD")
private Double cAD;
@JsonProperty("BRL")
private Double bRL;
@JsonProperty("HUF")
private Double hUF;
@JsonProperty("DKK")
private Double dKK;
@JsonProperty("JPY")
private Double jPY;
@JsonProperty("ILS")
private Double iLS;
@JsonProperty("TRY")
private Double tRY;
@JsonProperty("RON")
private Double rON;
@JsonProperty("GBP")
private Double gBP;
@JsonProperty("PHP")
private Double pHP;
@JsonProperty("HRK")
private Double hRK;
@JsonProperty("NOK")
private Double nOK;
@JsonProperty("ZAR")
private Double zAR;
@JsonProperty("MXN")
private Double mXN;
@JsonProperty("AUD")
private Double aUD;
@JsonProperty("USD")
private Double uSD;
@JsonProperty("KRW")
private Double kRW;
@JsonProperty("HKD")
private Double hKD;
@JsonProperty("EUR")
private Double eUR;
@JsonProperty("ISK")
private Double iSK;
@JsonProperty("CZK")
private Double cZK;
@JsonProperty("THB")
private Double tHB;
@JsonProperty("MYR")
private Double mYR;
@JsonProperty("NZD")
private Double nZD;
@JsonProperty("PLN")
private Double pLN;
@JsonProperty("CHF")
private Double cHF;
@JsonProperty("SEK")
private Double sEK;
@JsonProperty("CNY")
private Double cNY;
@JsonProperty("SGD")
private Double sGD;
@JsonProperty("INR")
private Double iNR;
@JsonProperty("IDR")
private Double iDR;
@JsonProperty("RUB")
private Double rUB;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("BGN")
public Double getBGN() {
return bGN;
}

@JsonProperty("BGN")
public void setBGN(Double bGN) {
this.bGN = bGN;
}

@JsonProperty("CAD")
public Double getCAD() {
return cAD;
}

@JsonProperty("CAD")
public void setCAD(Double cAD) {
this.cAD = cAD;
}

@JsonProperty("BRL")
public Double getBRL() {
return bRL;
}

@JsonProperty("BRL")
public void setBRL(Double bRL) {
this.bRL = bRL;
}

@JsonProperty("HUF")
public Double getHUF() {
return hUF;
}

@JsonProperty("HUF")
public void setHUF(Double hUF) {
this.hUF = hUF;
}

@JsonProperty("DKK")
public Double getDKK() {
return dKK;
}

@JsonProperty("DKK")
public void setDKK(Double dKK) {
this.dKK = dKK;
}

@JsonProperty("JPY")
public Double getJPY() {
return jPY;
}

@JsonProperty("JPY")
public void setJPY(Double jPY) {
this.jPY = jPY;
}

@JsonProperty("ILS")
public Double getILS() {
return iLS;
}

@JsonProperty("ILS")
public void setILS(Double iLS) {
this.iLS = iLS;
}

@JsonProperty("TRY")
public Double getTRY() {
return tRY;
}

@JsonProperty("TRY")
public void setTRY(Double tRY) {
this.tRY = tRY;
}

@JsonProperty("RON")
public Double getRON() {
return rON;
}

@JsonProperty("RON")
public void setRON(Double rON) {
this.rON = rON;
}

@JsonProperty("GBP")
public Double getGBP() {
return gBP;
}

@JsonProperty("GBP")
public void setGBP(Double gBP) {
this.gBP = gBP;
}

@JsonProperty("PHP")
public Double getPHP() {
return pHP;
}

@JsonProperty("PHP")
public void setPHP(Double pHP) {
this.pHP = pHP;
}

@JsonProperty("HRK")
public Double getHRK() {
return hRK;
}

@JsonProperty("HRK")
public void setHRK(Double hRK) {
this.hRK = hRK;
}

@JsonProperty("NOK")
public Double getNOK() {
return nOK;
}

@JsonProperty("NOK")
public void setNOK(Double nOK) {
this.nOK = nOK;
}

@JsonProperty("ZAR")
public Double getZAR() {
return zAR;
}

@JsonProperty("ZAR")
public void setZAR(Double zAR) {
this.zAR = zAR;
}

@JsonProperty("MXN")
public Double getMXN() {
return mXN;
}

@JsonProperty("MXN")
public void setMXN(Double mXN) {
this.mXN = mXN;
}

@JsonProperty("AUD")
public Double getAUD() {
return aUD;
}

@JsonProperty("AUD")
public void setAUD(Double aUD) {
this.aUD = aUD;
}

@JsonProperty("USD")
public Double getUSD() {
return uSD;
}

@JsonProperty("USD")
public void setUSD(Double uSD) {
this.uSD = uSD;
}

@JsonProperty("KRW")
public Double getKRW() {
return kRW;
}

@JsonProperty("KRW")
public void setKRW(Double kRW) {
this.kRW = kRW;
}

@JsonProperty("HKD")
public Double getHKD() {
return hKD;
}

@JsonProperty("HKD")
public void setHKD(Double hKD) {
this.hKD = hKD;
}

@JsonProperty("EUR")
public Double getEUR() {
return eUR;
}

@JsonProperty("EUR")
public void setEUR(Double eUR) {
this.eUR = eUR;
}

@JsonProperty("ISK")
public Double getISK() {
return iSK;
}

@JsonProperty("ISK")
public void setISK(Double iSK) {
this.iSK = iSK;
}

@JsonProperty("CZK")
public Double getCZK() {
return cZK;
}

@JsonProperty("CZK")
public void setCZK(Double cZK) {
this.cZK = cZK;
}

@JsonProperty("THB")
public Double getTHB() {
return tHB;
}

@JsonProperty("THB")
public void setTHB(Double tHB) {
this.tHB = tHB;
}

@JsonProperty("MYR")
public Double getMYR() {
return mYR;
}

@JsonProperty("MYR")
public void setMYR(Double mYR) {
this.mYR = mYR;
}

@JsonProperty("NZD")
public Double getNZD() {
return nZD;
}

@JsonProperty("NZD")
public void setNZD(Double nZD) {
this.nZD = nZD;
}

@JsonProperty("PLN")
public Double getPLN() {
return pLN;
}

@JsonProperty("PLN")
public void setPLN(Double pLN) {
this.pLN = pLN;
}

@JsonProperty("CHF")
public Double getCHF() {
return cHF;
}

@JsonProperty("CHF")
public void setCHF(Double cHF) {
this.cHF = cHF;
}

@JsonProperty("SEK")
public Double getSEK() {
return sEK;
}

@JsonProperty("SEK")
public void setSEK(Double sEK) {
this.sEK = sEK;
}

@JsonProperty("CNY")
public Double getCNY() {
return cNY;
}

@JsonProperty("CNY")
public void setCNY(Double cNY) {
this.cNY = cNY;
}

@JsonProperty("SGD")
public Double getSGD() {
return sGD;
}

@JsonProperty("SGD")
public void setSGD(Double sGD) {
this.sGD = sGD;
}

@JsonProperty("INR")
public Double getINR() {
return iNR;
}

@JsonProperty("INR")
public void setINR(Double iNR) {
this.iNR = iNR;
}

@JsonProperty("IDR")
public Double getIDR() {
return iDR;
}

@JsonProperty("IDR")
public void setIDR(Double iDR) {
this.iDR = iDR;
}

@JsonProperty("RUB")
public Double getRUB() {
return rUB;
}

@JsonProperty("RUB")
public void setRUB(Double rUB) {
this.rUB = rUB;
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