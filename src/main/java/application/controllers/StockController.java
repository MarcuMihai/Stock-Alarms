package application.controllers;

import application.dtos.StockDTO;
import application.services.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping()
    public ResponseEntity<List<StockDTO>> getStocks() {
        List<StockDTO> stockDTOs = stockService.getStocks();
        return new ResponseEntity<>(stockDTOs, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> insertStock(@RequestBody StockDTO stockDTO) {
        UUID stockId = stockService.insert(stockDTO);
        if (stockId==null) {
            return new ResponseEntity<>("A stock with the same name exists already", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(stockId, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateStock(@RequestBody StockDTO stockDTO) {
        UUID stockId = stockService.update(stockDTO);
        if (stockId==null) {
            return new ResponseEntity<>("There is no stock with these characteristics", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(stockId, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UUID> deleteStock(@PathVariable("id") UUID id) {
        stockService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StockDTO> getStockById(@PathVariable("id") UUID id) {
        StockDTO stockDTO = stockService.findStockById(id);
        return new ResponseEntity<>(stockDTO, HttpStatus.OK);
    }
}
