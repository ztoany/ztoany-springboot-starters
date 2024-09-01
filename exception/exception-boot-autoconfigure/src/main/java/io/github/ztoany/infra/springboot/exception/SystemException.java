package io.github.ztoany.infra.springboot.exception;

public class SystemException extends BaseException {
    public SystemException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public SystemException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode, errorMessage, cause);
    }
}
