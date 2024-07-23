package com.fomo.realtimepricedatabackend.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fomo.realtimepricedatabackend.entity.CoinData;
import com.fomo.realtimepricedatabackend.repository.CoinRepository;

@Service
public class CoinService {

    private static final Logger logger = LoggerFactory.getLogger(CoinService.class);
    private final CoinRepository coinRepository;

    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public List<CoinData> getAllCoins() {
        try {
            return coinRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching all coins", e);
            throw new RuntimeException("Error fetching all coins", e);
        }
    }

    public CoinData getCoinById(String coinId) {
        try {
            Optional<CoinData> coinData = coinRepository.findById(coinId);
            if (coinData.isPresent()) {
                return coinData.get();
            } else {
                logger.warn("Coin with id {} not found", coinId);
                throw new RuntimeException("Coin not found");
            }
        } catch (Exception e) {
            logger.error("Error fetching coin by id", e);
            throw new RuntimeException("Error fetching coin by id", e);
        }
    }
}
