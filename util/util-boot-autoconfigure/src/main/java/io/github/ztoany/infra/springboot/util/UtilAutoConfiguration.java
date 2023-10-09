package io.github.ztoany.infra.springboot.util;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class UtilAutoConfiguration {
    @Bean
    public ApplicationNameResolver applicationNameResolver() {
        return new ApplicationNameResolver();
    }
}
