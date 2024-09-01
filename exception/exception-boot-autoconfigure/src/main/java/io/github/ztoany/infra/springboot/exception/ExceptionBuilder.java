package io.github.ztoany.infra.springboot.exception;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class ExceptionBuilder {
    private static MessageSource messageSource;

    public static void setMessageSource(MessageSource messageSource) {
        ExceptionBuilder.messageSource = messageSource;
    }

    public static SystemException systemException(String code) {
        var msg = messageSource.getMessage(code, null, Locale.getDefault());
        return new SystemException(code, msg);
    }

    public static SystemException systemException(String code, Locale locale) {
        var msg = messageSource.getMessage(code, null, locale);
        return new SystemException(code, msg);
    }

    public static BusinessException businessException(String code) {
        var msg = messageSource.getMessage(code, null, Locale.getDefault());
        return new BusinessException(code, msg);
    }

    public static BusinessException businessException(String code, Locale locale) {
        var msg = messageSource.getMessage(code, null, locale);
        return new BusinessException(code, msg);
    }
}
