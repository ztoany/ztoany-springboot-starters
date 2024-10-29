package io.github.ztoany.infra.springboot.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class LocaleUtils {
    public static Locale handleHttpAcceptLanguage() {
        var locale = LocaleContextHolder.getLocale();
        if (locale == null || locale.getLanguage().isEmpty()) {
            locale = Locale.getDefault();
        }
        return locale;
    }
}
