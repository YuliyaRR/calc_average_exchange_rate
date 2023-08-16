package org.example.service.core.exceptions;

import org.example.service.core.dto.error.ErrorCode;

public class ConnectionAPIException extends RuntimeException {

    private ErrorCode errorCode;

    public ConnectionAPIException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
