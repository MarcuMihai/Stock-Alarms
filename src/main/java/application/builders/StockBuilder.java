package application.builders;

import application.dtos.StockDTO;
import application.entities.Stock;

public class StockBuilder {
    private StockBuilder() {
    }

    public static StockDTO toDTO(Stock stock) {
        return new StockDTO(
                stock.getSymbol(),
                stock.getCurrentPrice());
    }

    public static Stock toEntity(StockDTO stockDTO) {
        return new Stock(
                stockDTO.getSymbol(),
                stockDTO.getCurrentPrice());
    }
}
