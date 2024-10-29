package io.github.ztoany.infra.springboot.exception;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class ExceptionBuilder {
    private static MessageSource messageSource;

    public static void setMessageSource(MessageSource messageSource) {
        ExceptionBuilder.messageSource = messageSource;
    }

    public static SystemException systemException(String code) {
        var msg = messageSource.getMessage(code, null, LocaleUtils.handleHttpAcceptLanguage());
        return new SystemException(code, msg);
    }

    public static SystemException systemException(String code, Locale locale) {
        var msg = messageSource.getMessage(code, null, locale);
        return new SystemException(code, msg);
    }

    public static BusinessException businessException(String code) {
        var msg = messageSource.getMessage(code, null, LocaleUtils.handleHttpAcceptLanguage());
        return new BusinessException(code, msg);
    }

    public static BusinessException businessException(String code, Locale locale) {
        var msg = messageSource.getMessage(code, null, locale);
        return new BusinessException(code, msg);
    }

    public static EntityNotFoundException entityNotFoundException(String entityName, String id) {
        var msg = messageSource.getMessage(PredefinedErrorCodes.ENTITY_NOT_FOUND, new Object[]{entityName, id}, LocaleUtils.handleHttpAcceptLanguage());
        return new EntityNotFoundException(PredefinedErrorCodes.ENTITY_NOT_FOUND, msg);
    }

    public static EntityNotFoundException entityNotFoundException(String entityName, String id, Locale locale) {
        var msg = messageSource.getMessage(PredefinedErrorCodes.ENTITY_NOT_FOUND, new Object[]{entityName, id}, locale);
        return new EntityNotFoundException(PredefinedErrorCodes.ENTITY_NOT_FOUND, msg);
    }

    public static EntityNotFoundException entityNotFoundException(String entityName, Long id) {
        var msg = messageSource.getMessage(PredefinedErrorCodes.ENTITY_NOT_FOUND, new Object[]{entityName, id == null ? id : id.toString()}, LocaleUtils.handleHttpAcceptLanguage());
        return new EntityNotFoundException(PredefinedErrorCodes.ENTITY_NOT_FOUND, msg);
    }

    public static EntityNotFoundException entityNotFoundException(String entityName, Long id, Locale locale) {
        var msg = messageSource.getMessage(PredefinedErrorCodes.ENTITY_NOT_FOUND, new Object[]{entityName, id == null ? id : id.toString()}, locale);
        return new EntityNotFoundException(PredefinedErrorCodes.ENTITY_NOT_FOUND, msg);
    }

    public static EntityNotFoundException entityNotFoundException(String entityName, Integer id) {
        var msg = messageSource.getMessage(PredefinedErrorCodes.ENTITY_NOT_FOUND, new Object[]{entityName, id}, LocaleUtils.handleHttpAcceptLanguage());
        return new EntityNotFoundException(PredefinedErrorCodes.ENTITY_NOT_FOUND, msg);
    }

    public static EntityNotFoundException entityNotFoundException(String entityName, Integer id, Locale locale) {
        var msg = messageSource.getMessage(PredefinedErrorCodes.ENTITY_NOT_FOUND, new Object[]{entityName, id}, locale);
        return new EntityNotFoundException(PredefinedErrorCodes.ENTITY_NOT_FOUND, msg);
    }
}
