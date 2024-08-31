package io.github.ztoany.infra.springboot.error.message;

import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.StringUtils;

import java.time.Duration;


public class PredefinedErrorMessageAutoConfiguration extends MessageSourceAutoConfiguration {

    @Bean
    @Override
    public MessageSource messageSource(MessageSourceProperties properties) {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        if (StringUtils.hasText(properties.getBasename())) {
            messageSource.setBasenames(StringUtils
                    .commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(properties.getBasename())));
        }
        if (properties.getEncoding() != null) {
            messageSource.setDefaultEncoding(properties.getEncoding().name());
        }
        messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
        Duration cacheDuration = properties.getCacheDuration();
        if (cacheDuration != null) {
            messageSource.setCacheMillis(cacheDuration.toMillis());
        }
        messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
        messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());

        handleAfterInitMessageSourceBean(messageSource);

        return messageSource;
    }

    private void handleAfterInitMessageSourceBean(ReloadableResourceBundleMessageSource messageSource) {
        messageSource.addBasenames("classpath:i18n/predefined_error_messages");
        ExceptionBuilder.setMessageSource(messageSource);
    }
}
