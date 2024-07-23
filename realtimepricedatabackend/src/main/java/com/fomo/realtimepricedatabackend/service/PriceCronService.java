package com.fomo.realtimepricedatabackend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fomo.realtimepricedatabackend.entity.CoinPriceHistory;
import com.fomo.realtimepricedatabackend.repository.CoinPriceHistoryRepository;
import com.fomo.realtimepricedatabackend.repository.CoinRepository;

@Service
public class PriceCronService {

    private static final Logger logger = LoggerFactory.getLogger(PriceCronService.class);
    private final CoinRepository coinRepository;
    private final CoinPriceHistoryRepository priceRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RestTemplate restTemplate;

    @Value("${coingecko.api.url}")
    private String apiUrl;

    // @Value("${coingecko.api.token}")
    // private String apiToken;

    public PriceCronService(CoinRepository coinRepository, CoinPriceHistoryRepository priceRepository) {
        this.coinRepository = coinRepository;
        this.priceRepository = priceRepository;
    }

    @Scheduled(fixedRate = 30000)
    public void fetchAndSavePrices() {
        String coinIds = getAllCoinIds();
        if (coinIds.isEmpty()) {
            logger.warn("No coin IDs found in the repository.");
            return;
        }

        String url = apiUrl + coinIds;
        // HttpHeaders headers = new HttpHeaders();
        // headers.add("x-cg-demo-api-key", apiToken);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode() != HttpStatus.OK) {
                logger.error("Failed to fetch prices, status code: {}", response.getStatusCode());
                return;
            }

            String jsonResponse = response.getBody();
            logger.debug("Response: {}", jsonResponse);
            List<CoinPriceHistory> coinPriceHistories = processJsonResponse(jsonResponse);
            priceRepository.saveAll(coinPriceHistories);

        } catch (JsonMappingException e) {
            logger.error("Failed to parse JSON response: {}", e.getMessage());
        } catch (JsonProcessingException e) {
            logger.error("Failed to process response: {}", e.getMessage());
        } catch (RestClientException e) {
            logger.error("Error while fetching prices from API: {}", e.getMessage(), e);
        }
    }

    private String getAllCoinIds() {
        StringBuilder getAllIds = new StringBuilder();
        coinRepository.findAll().forEach(coin -> {
            getAllIds.append(coin.getCoinId()).append(",");
        });
        return getAllIds.length() > 0 ? getAllIds.substring(0, getAllIds.length() - 1) : "";
    }

    private List<CoinPriceHistory> processJsonResponse(String jsonResponse)
            throws JsonMappingException, JsonProcessingException {

        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        Iterator<String> coinIds = rootNode.fieldNames();

        List<CoinPriceHistory> coinPriceHistories = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        while (coinIds.hasNext()) {
            String coinId = coinIds.next();
            double price = rootNode.get(coinId).get("usd").asDouble();

            CoinPriceHistory coinPriceHistory = new CoinPriceHistory();
            coinPriceHistory.setCoinId(coinId);
            coinPriceHistory.setPrice(price);
            coinPriceHistory.setTimestamp(now);

            coinPriceHistories.add(coinPriceHistory);
        }
        return coinPriceHistories;
    }
}
