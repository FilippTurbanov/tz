package org.philipturbanovtz.dtos.api.http;

public class HttpResponseDto {
    private final int code;
    private final String message;
    private final Object data;

    public HttpResponseDto(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
