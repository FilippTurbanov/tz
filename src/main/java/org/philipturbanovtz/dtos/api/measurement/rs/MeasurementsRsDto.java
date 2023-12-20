package org.philipturbanovtz.dtos.api.measurement.rs;

import java.util.Objects;
import org.philipturbanovtz.database.entities.measurement.Measurement;

public class MeasurementsRsDto {
    private long userId;
    private double gasInCubicMeters;
    private double hotWaterInCubicMeters;
    private double coldWaterInCubicMeters;
    private long createdAtMs;

    public MeasurementsRsDto(Measurement measurement) {
        this.userId = measurement.getUser().getId();
        this.gasInCubicMeters = measurement.getGasInCubicMeters();
        this.hotWaterInCubicMeters = measurement.getHotWaterInCubicMeters();
        this.coldWaterInCubicMeters = measurement.getColdWaterInCubicMeters();
        this.createdAtMs = measurement.getCreatedAtMs();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getGasInCubicMeters() {
        return gasInCubicMeters;
    }

    public void setGasInCubicMeters(double gasInCubicMeters) {
        this.gasInCubicMeters = gasInCubicMeters;
    }

    public double getHotWaterInCubicMeters() {
        return hotWaterInCubicMeters;
    }

    public void setHotWaterInCubicMeters(double hotWaterInCubicMeters) {
        this.hotWaterInCubicMeters = hotWaterInCubicMeters;
    }

    public double getColdWaterInCubicMeters() {
        return coldWaterInCubicMeters;
    }

    public void setColdWaterInCubicMeters(double coldWaterInCubicMeters) {
        this.coldWaterInCubicMeters = coldWaterInCubicMeters;
    }

    public double getCreatedAtMs() {
        return createdAtMs;
    }

    public void setCreatedAtMs(long createdAtMs) {
        this.createdAtMs = createdAtMs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MeasurementsRsDto that = (MeasurementsRsDto) o;
        return userId == that.userId && Double.compare(that.gasInCubicMeters, gasInCubicMeters) == 0 && Double.compare(that.hotWaterInCubicMeters, hotWaterInCubicMeters) == 0 && Double.compare(that.coldWaterInCubicMeters, coldWaterInCubicMeters) == 0 && createdAtMs == that.createdAtMs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, gasInCubicMeters, hotWaterInCubicMeters, coldWaterInCubicMeters, createdAtMs);
    }

    @Override
    public String toString() {
        return "MeasurementsRsDto{" + "userId=" + userId + ", gasInCubicMeters=" + gasInCubicMeters + ", hotWaterInCubicMeters=" + hotWaterInCubicMeters + ", coldWaterInCubicMeters=" + coldWaterInCubicMeters + ", createdAtMs=" + createdAtMs + '}';
    }
}
