package application.controllers;

import application.dtos.StockDTO;
import application.services.StockService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{symbol}")
    public StockDTO getStockBySymbol(@PathVariable String symbol) {
        return stockService.getStockBySymbol(symbol);
    }
}
