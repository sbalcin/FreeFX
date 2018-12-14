package com.company.freefx.controller;

import com.company.freefx.service.ConversionService;
import com.company.freefx.type.ConversionRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conversion")
@AllArgsConstructor
public class ConversionController {


    private final ConversionService service;

    @PostMapping("/convert/")
    public ResponseEntity<?> convert(@RequestBody ConversionRequest request) {

        return service.convert(request);
    }
}