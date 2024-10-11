package io.github.ztoany.infra.springboot.exception;

public class FormattedMessageBusinessException extends BusinessException {
    public FormattedMessageBusinessException(String errorCode, String errorMessage, Object... args) {
        super(errorCode, String.format(errorMessage, args));
    }
}
