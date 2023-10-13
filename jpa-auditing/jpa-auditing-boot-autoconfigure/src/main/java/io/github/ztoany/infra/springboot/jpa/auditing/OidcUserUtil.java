package io.github.ztoany.infra.springboot.jpa.auditing;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Optional;

public class OidcUserUtil {
    public static Optional<String> getOidcSubjectIdOrEmptyStr() {
        try {
            var op = getOidcSubjectId();
            return op.isEmpty() ? Optional.of("") : op;
        } catch (Exception ex) {
            return Optional.of("");
        }
    }

    public static Optional<String> getOidcSubjectIdOrException() {
        return getOidcSubjectId();
    }

    private static Optional<String> getOidcSubjectId() {
        return Optional.of(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(DefaultOidcUser.class::cast)
                .map(DefaultOidcUser::getUserInfo)
                .map(OidcUserInfo::getClaims)
                .map(e -> (String)e.get("sub"));
    }
}
