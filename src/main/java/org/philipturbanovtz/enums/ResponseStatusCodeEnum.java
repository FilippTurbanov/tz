package org.philipturbanovtz.enums;

public enum ResponseStatusCodeEnum {
    SUCCESS(0),
    ERROR(1),
    MEASUREMENT_ERROR(100),
    INVALID_MEASUREMENT_DATA_ERROR(101),
    SAVE_MEASUREMENT_ERROR(102),
    MEASUREMENT_NOT_FOUND_ERROR(103);
    private final int code;

    ResponseStatusCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
