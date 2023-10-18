package application.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "alarms", schema = "public")
public class Alarm implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "stock", nullable = false)
    private String stock;

    @Column(name = "definition_price", nullable = false)
    private float definitionPrice;

    @Column(name = "current_price", nullable = false)
    private float currentPrice;

    @Column(name = "variance_percentage", nullable = false)
    private float variancePercentage;

    @Column(name = "low_target_percentage", nullable = false)
    private float lowTargetPercentage;

    @Column(name = "high_target_percentage", nullable = false)
    private float highTargetPercentage;

    @Column(name = "active", nullable = false)
    private boolean active;

    public Alarm() {}

    public Alarm(UUID id, User user, String stock, float definitionPrice, float currentPrice, float lowTargetPercentage, float highTargetPercentage, boolean active) {
        this.id = id;
        this.user = user;
        this.stock = stock;
        this.definitionPrice = definitionPrice;
        this.currentPrice = currentPrice;
        this.variancePercentage = ((currentPrice-definitionPrice)/definitionPrice)*100;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public void setVariancePercentage() {
        this.variancePercentage = ((currentPrice-definitionPrice)/definitionPrice)*100;
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