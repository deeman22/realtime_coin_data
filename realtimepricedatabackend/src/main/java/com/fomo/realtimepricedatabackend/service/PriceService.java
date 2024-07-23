package com.fomo.realtimepricedatabackend.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fomo.realtimepricedatabackend.entity.CoinPriceHistory;
import com.fomo.realtimepricedatabackend.repository.CoinPriceHistoryRepository;

@Service
public class PriceService {

    private final CoinPriceHistoryRepository priceRepository;

    public PriceService(CoinPriceHistoryRepository priceRepositoy) {
        this.priceRepository = priceRepositoy;
    }

    public List<CoinPriceHistory> getPriceListByCoinId(String coinId, int count) {
        Pageable pageable = PageRequest.of(0, count);
        return priceRepository.findByCoinIdOrderByTimestampDesc(coinId, pageable).getContent();
    }

}
