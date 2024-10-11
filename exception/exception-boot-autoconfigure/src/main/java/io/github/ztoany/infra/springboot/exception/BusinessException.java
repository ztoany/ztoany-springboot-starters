package io.github.ztoany.infra.springboot.exception;

public class BusinessException extends BaseException {
    public BusinessException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public BusinessException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode, errorMessage, cause);
    }
}
