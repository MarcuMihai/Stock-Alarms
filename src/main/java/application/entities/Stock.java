package application.entities;

import java.io.Serializable;

public class Stock implements Serializable {

    private String symbol;

    private float currentPrice;

    public Stock() {}

    public Stock(String symbol, float currentPrice) {
        this.symbol = symbol;
        this.currentPrice = currentPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String name) {
        this.symbol = name;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }
}

