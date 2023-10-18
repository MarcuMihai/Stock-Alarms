package application.services;

import application.builders.AlarmBuilder;
import application.builders.StockBuilder;
import application.dtos.AddAlarmDTO;
import application.dtos.AlarmDTO;
import application.dtos.StockDTO;
import application.entities.Alarm;
import application.entities.Stock;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final RestTemplate restTemplate;
    private final AlarmService alarmService;

    private static final String baseUrl = "https://www.alphavantage.co";
    private static final String apiKey = "NPDF5F31P6WBH5LD";

    private final int pollingInterval;

    @Autowired
    public StockService(AlarmService alarmService, @Value("${polling.interval.seconds}") int pollingInterval, RestTemplate restTemplate) {
        this.alarmService = alarmService;
        this.pollingInterval = pollingInterval;
        this.restTemplate = restTemplate;
    }

    public StockDTO getStockBySymbol(String symbol) {
        String url = baseUrl + "/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            JSONObject jsonResponse = new JSONObject(responseEntity.getBody());
            JSONObject globalQuote = jsonResponse.getJSONObject("Global Quote");
            String stockSymbol = globalQuote.getString("01. symbol");
            float currentPrice = Float.parseFloat(globalQuote.getString("02. open"));
            Stock stock = new Stock();
            stock.setSymbol(stockSymbol);
            stock.setCurrentPrice(currentPrice);
            return StockBuilder.toDTO(stock);
        } else {
            return null;
        }
    }

    @Scheduled(fixedRateString = "${polling.interval.seconds}000")
    public void monitorStockPrices() {
        List<AlarmDTO> alarmDTOS = alarmService.getAlarms();
        List<Alarm> alarms = alarmDTOS.stream().map(AlarmBuilder::toEntity).collect(Collectors.toList());
        //the hashmap is used to save the current price of each stock so the function does not need to call the api for the same stock multiple times
        Map<String, Float> monitoredStocks = new HashMap<>();
        for (Alarm alarm: alarms
             ) {
            if (alarm.getActive()) {
                Stock stock = new Stock(alarm.getStock(), alarm.getCurrentPrice());
                if (!monitoredStocks.containsKey(alarm.getStock())) {
                    stock = StockBuilder.toEntity(getStockBySymbol(alarm.getStock()));
                    monitoredStocks.put(alarm.getStock(), stock.getCurrentPrice());
                } else {
                    stock.setCurrentPrice(monitoredStocks.get(alarm.getStock()));
                }
                alarm.setCurrentPrice(stock.getCurrentPrice());
                alarm.setVariancePercentage();
                if (alarm.getVariancePercentage() <= alarm.getLowTargetPercentage() || alarm.getVariancePercentage() >= alarm.getHighTargetPercentage()) {
                    //sendEmail(User user, Alarm alarm);
                    alarm.setActive(false);
                }
                alarmService.update(new AddAlarmDTO(alarm.getId(),
                        alarm.getUser().getId(),
                        alarm.getStock(),
                        alarm.getDefinitionPrice(),
                        alarm.getCurrentPrice(),
                        alarm.getVariancePercentage(),
                        alarm.getLowTargetPercentage(),
                        alarm.getHighTargetPercentage(),
                        alarm.getActive()));
            }
        }
        LOGGER.info("\nPolling function done successfully\n");
    }
}
