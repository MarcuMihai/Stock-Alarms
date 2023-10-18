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

    @Column(name = "target_percentage", nullable = false)
    private float targetPercentage;

    @Column(name = "active", nullable = false)
    private boolean active;

    public Alarm() {}

    public Alarm(UUID id, User user, String stock, float definitionPrice, float currentPrice, float variancePercentage, float targetPercentage, boolean active) {
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