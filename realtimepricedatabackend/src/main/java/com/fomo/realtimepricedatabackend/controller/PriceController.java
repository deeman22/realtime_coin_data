package com.fomo.realtimepricedatabackend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fomo.realtimepricedatabackend.entity.CoinPriceHistory;
import com.fomo.realtimepricedatabackend.service.PriceService;

@CrossOrigin("*")
@RestController
@RequestMapping("/price")
public class PriceController {

    private static final Logger logger = LoggerFactory.getLogger(PriceController.class);
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/{coinId}")
    public ResponseEntity<List<CoinPriceHistory>> getPriceList(@PathVariable String coinId, @RequestParam int count) {
        try {
            logger.info("Fetching price list for coinId: {}", coinId);
            List<CoinPriceHistory> priceList = priceService.getPriceListByCoinId(coinId, count);
            return ResponseEntity.ok(priceList);
        } catch (Exception e) {
            logger.error("Error fetching price list for coinId: {}", coinId, e);
            return ResponseEntity.status(500).body(null);
        }
    }
}
