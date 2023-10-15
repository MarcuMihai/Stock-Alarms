package application.builders;

import application.dtos.StockDTO;
import application.entities.Stock;

public class StockBuilder {
    private StockBuilder() {
    }

    public static StockDTO toDTO(Stock stock) {
        return new StockDTO(stock.getId(),
                stock.getName(),
                stock.getCurrentPrice(),
                stock.getAlarms());
    }

    public static Stock toEntity(StockDTO stockDTO) {
        return new Stock(stockDTO.getId(),
                stockDTO.getName(),
                stockDTO.getCurrentPrice(),
                stockDTO.getAlarms());
    }
}
