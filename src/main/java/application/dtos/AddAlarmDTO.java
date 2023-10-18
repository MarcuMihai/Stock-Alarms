package application.dtos;

import java.util.UUID;

public class AddAlarmDTO {

    private UUID id;
    private UUID user;
    private String stock;
    private float definitionPrice;
    private float currentPrice;
    private float variancePercentage;
    private float lowTargetPercentage;
    private float highTargetPercentage;
    private boolean active;

    public AddAlarmDTO() {
    }

    public AddAlarmDTO(UUID id, UUID user, String stock, float definitionPrice, float currentPrice, float variancePercentage, float lowTargetPercentage, float highTargetPercentage, boolean active) {
        this.id = id;
        this.user = user;
        this.stock = stock;
        this.definitionPrice = definitionPrice;
        this.currentPrice = currentPrice;
        this.variancePercentage = variancePercentage;
        this.lowTargetPercentage = lowTargetPercentage;
        this.highTargetPercentage = highTargetPercentage;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
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

    public float getLowTargetPercentage() {
        return lowTargetPercentage;
    }

    public void setLowTargetPercentage(float lowTargetPercentage) {
        this.lowTargetPercentage = lowTargetPercentage;
    }

    public float getHighTargetPercentage() {
        return highTargetPercentage;
    }

    public void setHighTargetPercentage(float highTargetPercentage) {
        this.highTargetPercentage = highTargetPercentage;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}