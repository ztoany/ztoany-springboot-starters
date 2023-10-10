package io.github.ztoany.infra.springboot.hibernate.id.snowflake;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@IdGeneratorType(SnowflakeIdGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, METHOD })
public @interface SnowflakeId {
    enum Style {
        SINGLETON,
        INDIVIDUAL
    }

    Style style() default Style.SINGLETON;
}
