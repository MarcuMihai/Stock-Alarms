package application.dtos;

import application.entities.Alarm;

import java.util.List;
import java.util.UUID;

public class StockDTO {
    private UUID id;
    private String name;
    private float currentPrice;
    private List<Alarm> alarms;

    public StockDTO() {
    }

    public StockDTO(UUID id, String name, float currentPrice, List<Alarm> alarms) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.alarms = alarms;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public List<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }
}
