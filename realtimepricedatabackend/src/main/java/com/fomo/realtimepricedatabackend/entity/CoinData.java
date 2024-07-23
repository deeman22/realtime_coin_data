package com.fomo.realtimepricedatabackend.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "coins")
public class CoinData {

    private String coinId;
    private String symbol;
    private String name;

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CoinData{");
        sb.append("id=").append(coinId);
        sb.append(", symbol=").append(symbol);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }

}
