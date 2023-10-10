package io.github.ztoany.infra.springboot.logging.tomcat;


import io.github.ztoany.infra.springboot.util.ApplicationNameResolver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.boot.autoconfigure.web.ServerProperties;


public class TomcatAccessLogDefaultValueProcessor implements InstantiationAwareBeanPostProcessor {
    private ApplicationNameResolver applicationNameResolver;

    private static final String DIR = "/var/log";
    private static final String ACCESS_LOG_TYPE = "-access";
    private static final String FILE_DATE_FORMAT = "-yyyy-MM-dd";
    private static final String UTF8 = "utf-8";
    private static final int MAX_DAYS = 30;

    private static final String DEFAULT_PATTERN = "%a %l %u %t \"%r\" %s %b \"%{Referer}i\" \"%{User-Agent}i\" \"%{X-Forwarded-For}i\" %F %D %h %{X-Request-Id}i";

    public TomcatAccessLogDefaultValueProcessor(ApplicationNameResolver applicationNameResolver) {
        this.applicationNameResolver = applicationNameResolver;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if(bean instanceof ServerProperties) {
            ServerProperties serverProperties = (ServerProperties) bean;
            ServerProperties.Tomcat.Accesslog accesslog = serverProperties.getTomcat().getAccesslog();
            accesslog.setEnabled(true);
            accesslog.setDirectory(DIR);
            accesslog.setPrefix(applicationNameResolver.getApplicationName() + ACCESS_LOG_TYPE);
            accesslog.setBuffered(true);
            accesslog.setFileDateFormat(FILE_DATE_FORMAT);
            accesslog.setMaxDays(MAX_DAYS);
            accesslog.setPattern(DEFAULT_PATTERN);
            accesslog.setEncoding(UTF8);
            accesslog.setRotate(true);
        }

        return true;
    }
}
