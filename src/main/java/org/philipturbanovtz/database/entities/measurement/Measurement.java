package org.philipturbanovtz.database.entities.measurement;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.philipturbanovtz.database.entities.user.User;
import org.philipturbanovtz.dtos.api.measurement.rq.SaveMeasurementsRqDto;

@Entity
@Table(name = "measurements")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "gas_cubic_meters", nullable = false)
    private Double gasInCubicMeters;

    @Column(name = "hot_water_cubic_meters", nullable = false)
    private Double hotWaterInCubicMeters;

    @Column(name = "cold_water_cubic_meters", nullable = false)
    private Double coldWaterInCubicMeters;

    @Column(name = "created_at_ms", nullable = false)
    private Long createdAtMs;

    public Measurement(SaveMeasurementsRqDto dataToSave, User user) {
        this.user = user;
        this.gasInCubicMeters = dataToSave.getGasInCubicMeters();
        this.hotWaterInCubicMeters = dataToSave.getHotWaterInCubicMeters();
        this.coldWaterInCubicMeters = dataToSave.getColdWaterInCubicMeters();
        this.createdAtMs = System.currentTimeMillis();
    }

    public Measurement() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getGasInCubicMeters() {
        return gasInCubicMeters;
    }

    public void setGasInCubicMeters(Double gasInCubicMeters) {
        this.gasInCubicMeters = gasInCubicMeters;
    }

    public Double getHotWaterInCubicMeters() {
        return hotWaterInCubicMeters;
    }

    public void setHotWaterInCubicMeters(Double hotWaterInCubicMeters) {
        this.hotWaterInCubicMeters = hotWaterInCubicMeters;
    }

    public Double getColdWaterInCubicMeters() {
        return coldWaterInCubicMeters;
    }

    public void setColdWaterInCubicMeters(Double coldWaterInCubicMeters) {
        this.coldWaterInCubicMeters = coldWaterInCubicMeters;
    }

    public Long getCreatedAtMs() {
        return createdAtMs;
    }

    public void setCreatedAtMs(Long createdAtMs) {
        this.createdAtMs = createdAtMs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Measurement that = (Measurement) o;
        return id.equals(that.id) && user.equals(that.user) && gasInCubicMeters.equals(that.gasInCubicMeters) && hotWaterInCubicMeters.equals(that.hotWaterInCubicMeters) && coldWaterInCubicMeters.equals(that.coldWaterInCubicMeters) && createdAtMs.equals(that.createdAtMs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, gasInCubicMeters, hotWaterInCubicMeters, coldWaterInCubicMeters, createdAtMs);
    }

    @Override
    public String toString() {
        return "Measurement{" + "id=" + id + ", user=" + user + ", gasInCubicMeters=" + gasInCubicMeters + ", hotWaterInCubicMeters=" + hotWaterInCubicMeters + ", coldWaterInCubicMeters=" + coldWaterInCubicMeters + ", createdAtMs=" + createdAtMs + '}';
    }
}
