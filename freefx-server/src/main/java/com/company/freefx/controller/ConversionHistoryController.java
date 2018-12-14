package com.company.freefx.controller;

import com.company.freefx.service.ConversionHistoryService;
import com.company.freefx.type.ConversionHistoryRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversionHistory")
@AllArgsConstructor
public class ConversionHistoryController {


    private final ConversionHistoryService conversionHistoryService;

    @PostMapping("/fetchConversions/")
    public ResponseEntity<?> fetchConversions(@RequestBody ConversionHistoryRequest request) {

        return conversionHistoryService.fetchConversions(request);
    }
}