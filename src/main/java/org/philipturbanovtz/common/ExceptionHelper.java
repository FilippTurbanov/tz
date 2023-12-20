package org.philipturbanovtz.common;

public class ExceptionHelper {
    public static Integer determineErrorCode(Integer incomingCode, Integer defaultCode) {
        int errCode;
        if (incomingCode == null) {
            errCode = defaultCode;
        } else {
            errCode = incomingCode;
        }
        return errCode;
    }
}
