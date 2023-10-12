package io.github.ztoany.infra.springboot.jpa.auditing;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class SpringSecurityOidcUserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return OidcUserUtil.getOidcSubjectIdOrEmptyStr();
    }
}
