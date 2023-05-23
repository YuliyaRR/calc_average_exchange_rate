package by.fin.module.core.exceptions;

import by.fin.module.core.dto.error.ErrorCode;

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

