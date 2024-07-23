package com.fomo.realtimepricedatabackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fomo.realtimepricedatabackend.entity.CoinData;

@Repository
public interface CoinRepository extends MongoRepository<CoinData, String> {

}
