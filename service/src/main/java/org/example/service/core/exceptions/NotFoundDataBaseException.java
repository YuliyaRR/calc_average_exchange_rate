package org.example.service.core.exceptions;

import org.example.service.core.dto.error.ErrorCode;

public class NotFoundDataBaseException extends RuntimeException {
    private ErrorCode errorCode;

    public NotFoundDataBaseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

