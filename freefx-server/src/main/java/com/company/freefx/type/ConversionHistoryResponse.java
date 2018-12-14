package com.company.freefx.type;

import com.company.freefx.h2.model.Conversion;

import java.util.List;

public class ConversionHistoryResponse {

    private List<Conversion> conversions;

    public List<Conversion> getConversions() {
        return conversions;
    }

    public void setConversions(List<Conversion> conversions) {
        this.conversions = conversions;
    }

    @Override
    public String toString() {
        return "ConversionHistoryResponse{" + "conversions=" + conversions + '}';
    }
}