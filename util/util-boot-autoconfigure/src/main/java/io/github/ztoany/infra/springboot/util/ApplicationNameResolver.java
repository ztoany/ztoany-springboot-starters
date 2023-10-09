package io.github.ztoany.infra.springboot.util;

import org.springframework.beans.factory.annotation.Value;

public class ApplicationNameResolver {
    @Value("${spring.application.name:spring}")
    private String applicationName;

    public String getApplicationName() {
        return applicationName;
    }
}
