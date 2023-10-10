package io.github.ztoany.infra.springboot.jpa.auditing;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

@AutoConfiguration
public class JpaAuditingAutoConfiguration {
    @Bean
    public AuditorAware springSecurityOidcUserAuditorAware() {
        return new SpringSecurityOidcUserAuditorAware();
    }
}
