package org.philipturbanovtz.dtos.api.measurement.rq;

import java.util.Objects;
import javax.validation.constraints.PositiveOrZero;

public class SaveMeasurementsRqDto {
    @PositiveOrZero
    private final Double gasInCubicMeters;
    @PositiveOrZero
    private final Double hotWaterInCubicMeters;
    @PositiveOrZero
    private final Double coldWaterInCubicMeters;

    public SaveMeasurementsRqDto(Double gasInCubicMeters, Double hotWaterInCubicMeters, Double coldWaterInCubicMeters) {
        this.gasInCubicMeters = gasInCubicMeters;
        this.hotWaterInCubicMeters = hotWaterInCubicMeters;
        this.coldWaterInCubicMeters = coldWaterInCubicMeters;
    }

    public Double getGasInCubicMeters() {
        return gasInCubicMeters;
    }

    public Double getHotWaterInCubicMeters() {
        return hotWaterInCubicMeters;
    }

    public Double getColdWaterInCubicMeters() {
        return coldWaterInCubicMeters;
    }

    public SaveMeasurementsRqDto getNormalizedData() {
        return new SaveMeasurementsRqDto(
            formatValue(this.gasInCubicMeters),
            formatValue(this.hotWaterInCubicMeters),
            formatValue(this.coldWaterInCubicMeters)
        );
    }

    private Double formatValue(Double initValue) {
        return Double.valueOf(String.format("%.2f", initValue).replace(',', '.'));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SaveMeasurementsRqDto that = (SaveMeasurementsRqDto) o;
        return gasInCubicMeters.equals(that.gasInCubicMeters) && hotWaterInCubicMeters.equals(that.hotWaterInCubicMeters) && coldWaterInCubicMeters.equals(that.coldWaterInCubicMeters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gasInCubicMeters, hotWaterInCubicMeters, coldWaterInCubicMeters);
    }

    @Override
    public String toString() {
        return "SaveMeasurementsRqDto{" + "gasInCubicMeters=" + gasInCubicMeters + ", hotWaterInCubicMeters=" + hotWaterInCubicMeters + ", coldWaterInCubicMeters=" + coldWaterInCubicMeters + '}';
    }
}
