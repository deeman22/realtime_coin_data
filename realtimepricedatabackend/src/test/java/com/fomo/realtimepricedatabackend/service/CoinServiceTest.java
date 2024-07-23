package com.fomo.realtimepricedatabackend.service;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fomo.realtimepricedatabackend.entity.CoinData;
import com.fomo.realtimepricedatabackend.repository.CoinRepository;

@ExtendWith(MockitoExtension.class)
public class CoinServiceTest {

    @Mock
    private CoinRepository coinRepository;

    @InjectMocks
    private CoinService coinService;

    @Test
    void testGetAllCoins() {
        CoinData coin1 = new CoinData();
        coin1.setCoinId("1");
        coin1.setName("Bitcoin");
        coin1.setSymbol("BTC");

        CoinData coin2 = new CoinData();
        coin2.setCoinId("2");
        coin2.setName("Ethereum");
        coin2.setSymbol("ETH");

        List<CoinData> coins = Arrays.asList(coin1, coin2);

        when(coinRepository.findAll()).thenReturn(coins);

        List<CoinData> result = coinService.getAllCoins();
        assertEquals(2, result.size());
        assertEquals("Bitcoin", result.get(0).getName());
        assertEquals("Ethereum", result.get(1).getName());
        assertEquals("BTC", result.get(0).getSymbol());
        assertEquals("ETH", result.get(1).getSymbol());

    }
}
