package com.fomo.realtimepricedatabackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fomo.realtimepricedatabackend.entity.CoinPriceHistory;

@Repository
public interface CoinPriceHistoryRepository extends MongoRepository<CoinPriceHistory, String> {
    Page<CoinPriceHistory> findByCoinIdOrderByTimestampDesc(String coinId, Pageable pageable);
}
