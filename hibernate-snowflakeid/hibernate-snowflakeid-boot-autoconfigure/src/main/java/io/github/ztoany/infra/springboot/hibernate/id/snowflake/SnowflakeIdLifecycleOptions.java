package io.github.ztoany.infra.springboot.hibernate.id.snowflake;

import org.hibernate.resource.beans.container.spi.BeanContainer;

public class SnowflakeIdLifecycleOptions implements BeanContainer.LifecycleOptions {
    @Override
    public boolean canUseCachedReferences() {
        return true;
    }

    @Override
    public boolean useJpaCompliantCreation() {
        return false;
    }
}
