package com.fomo.realtimepricedatabackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fomo.realtimepricedatabackend.entity.CoinData;
import com.fomo.realtimepricedatabackend.service.CoinService;

@CrossOrigin("*")
@RestController
@RequestMapping("/coin")
public class CoinController {

    private final CoinService coinService;

    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping
    public List<CoinData> getAllCoins() {
        return coinService.getAllCoins();
    }

}
