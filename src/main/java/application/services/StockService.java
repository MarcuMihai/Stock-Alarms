package application.services;

import application.builders.StockBuilder;
import application.dtos.StockDTO;
import application.entities.Stock;
import application.repositories.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockDTO> getStocks() {
        List<Stock> stocksList = stockRepository.findAll();
        return stocksList.stream()
                .map(StockBuilder::toDTO)
                .collect(Collectors.toList());
    }

    public StockDTO findStockById(UUID id) {
        Optional<Stock> stockOptional = stockRepository.findById(id);
        if (stockOptional.isEmpty()) {
            LOGGER.error("Stock with id {} was not found in db", id);
            return null;
        }
        return StockBuilder.toDTO(stockOptional.get());
    }

    public UUID insert(StockDTO stockDTO) {
        if (stockRepository.findByName(stockDTO.getName()) != null) {
            LOGGER.error("A stock with the name {} already exists in db", stockDTO.getName());
            return null;
        }
        Stock stock = StockBuilder.toEntity(stockDTO);
        stock = stockRepository.save(stock);
        LOGGER.debug("Stock with id {} was inserted in db", stock.getId());
        return stock.getId();
    }

    public UUID update(StockDTO stockDTO) {
        if (stockRepository.findByName(stockDTO.getName()) == null) {
            LOGGER.error("The stock with the name {} does not exist in db", stockDTO.getName());
            return null;
        }
        Stock stock = StockBuilder.toEntity(stockDTO);
        stock = stockRepository.save(stock);
        LOGGER.debug("Stock with id {} was updated in db", stock.getId());
        return stock.getId();
    }

    public void delete(UUID id) {
        stockRepository.deleteById(id);
        LOGGER.debug("Stock with id {} was deleted from db", id);
    }

}
