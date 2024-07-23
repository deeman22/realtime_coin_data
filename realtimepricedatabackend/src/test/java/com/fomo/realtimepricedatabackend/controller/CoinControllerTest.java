package com.fomo.realtimepricedatabackend.controller;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fomo.realtimepricedatabackend.entity.CoinData;
import com.fomo.realtimepricedatabackend.service.CoinService;

@WebMvcTest(CoinController.class)
public class CoinControllerTest {

    @MockBean
    private CoinService coinService;

    @InjectMocks
    private CoinController coinController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(coinController).build();
    }

    @Test
    public void testGetAllCoins() throws Exception {
        CoinData coin1 = new CoinData();
        CoinData coin2 = new CoinData();

        when(coinService.getAllCoins()).thenReturn(Arrays.asList(coin1, coin2));

        mockMvc.perform(get("/coin"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{\"id\":1,\"name\":\"Bitcoin\",\"price\":10000},{\"id\":2,\"name\":\"Ethereum\",\"price\":2000}]"));
    }
}
