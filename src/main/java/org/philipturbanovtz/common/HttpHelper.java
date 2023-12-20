package org.philipturbanovtz.common;

import org.philipturbanovtz.dtos.api.http.HttpResponseDto;
import org.philipturbanovtz.enums.ResponseStatusCodeEnum;

public class HttpHelper {
    public static HttpResponseDto generateResponseOK(Object data) {
        return new HttpResponseDto(
            ResponseStatusCodeEnum.SUCCESS.getCode(),
            null,
            data
        );
    }

    public static HttpResponseDto generateResponseErr(int code, String message, Object data) {
        return new HttpResponseDto(code, message, null);
    }
}
