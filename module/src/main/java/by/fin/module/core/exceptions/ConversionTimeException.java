package by.fin.module.core.exceptions;

import by.fin.module.core.dto.error.ErrorCode;

public class ConversionTimeException extends RuntimeException {
    private ErrorCode errorCode;

    public ConversionTimeException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}

