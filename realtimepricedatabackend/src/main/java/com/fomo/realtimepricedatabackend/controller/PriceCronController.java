package com.fomo.realtimepricedatabackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fomo.realtimepricedatabackend.service.PriceCronService;

@CrossOrigin("*")
@RestController
@RequestMapping("/price-cron")
public class PriceCronController {

    private final PriceCronService priceCronService;

    public PriceCronController(PriceCronService priceCronService) {
        this.priceCronService = priceCronService;
    }

    @GetMapping("/update-price")
    public void fetchAndSaveUpdatedPrice() {
        priceCronService.fetchAndSavePrices();
    }
}
