package io.github.ztoany.infra.springboot.logging;

import io.github.ztoany.infra.springboot.logging.tomcat.TomcatAccessLogDefaultValueProcessor;
import io.github.ztoany.infra.springboot.util.ApplicationNameResolver;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.UpgradeProtocol;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;


@AutoConfiguration
public class LoggingAutoConfiguration {
    @Bean
    @ConditionalOnClass({ Tomcat.class, UpgradeProtocol.class })
    public TomcatAccessLogDefaultValueProcessor tomcatAccessLogDefaultValueProcessor(ApplicationNameResolver applicationNameResolver) {
        return new TomcatAccessLogDefaultValueProcessor(applicationNameResolver);
    }
}
