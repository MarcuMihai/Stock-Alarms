package application.dtos;

import application.entities.Stock;
import application.entities.User;

import java.util.UUID;

public class AlarmDTO {
    private UUID id;
    private User user;
    private Stock stock;
    private float definitionPrice;
    private float currentPrice;
    private float variancePercentage;
    private float targetPercentage;
    private boolean active;

    public AlarmDTO() {
    }

    public AlarmDTO(UUID id, User user, Stock stock, float definitionPrice, float currentPrice, float variancePercentage, float targetPercentage, boolean active) {
        this.id = id;
        this.user = user;
        this.stock = stock;
        this.definitionPrice = definitionPrice;
        this.currentPrice = currentPrice;
        this.variancePercentage = variancePercentage;
        this.targetPercentage = targetPercentage;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public float getDefinitionPrice() {
        return definitionPrice;
    }

    public void setDefinitionPrice(float definitionPrice) {
        this.definitionPrice = definitionPrice;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public float getVariancePercentage() {
        return variancePercentage;
    }

    public void setVariancePercentage(float variancePercentage) {
        this.variancePercentage = variancePercentage;
    }

    public float getTargetPercentage() {
        return targetPercentage;
    }

    public void setTargetPercentage(float targetPercentage) {
        this.targetPercentage = targetPercentage;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
