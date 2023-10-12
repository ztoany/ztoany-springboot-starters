gradle util:util-boot-autoconfigure:publishAllPublicationsToMavenCentral --no-configuration-cache
gradle closeAndReleaseRepository

gradle servlet-exception-handler:servlet-exception-handler-boot-autoconfigure:publishAllPublicationsToMavenCentral --no-configuration-cache
gradle closeAndReleaseRepository

gradle servlet-exception-handler:servlet-exception-handler-boot-starter:publishAllPublicationsToMavenCentral --no-configuration-cache
gradle closeAndReleaseRepository

gradle logging:logging-boot-autoconfigure:publishAllPublicationsToMavenCentral --no-configuration-cache
gradle closeAndReleaseRepository

gradle logging:logging-boot-starter:publishAllPublicationsToMavenCentral --no-configuration-cache
gradle closeAndReleaseRepository

gradle hibernate-snowflakeid:hibernate-snowflakeid-boot-autoconfigure:publishAllPublicationsToMavenCentral --no-configuration-cache
gradle closeAndReleaseRepository

gradle hibernate-snowflakeid:hibernate-snowflakeid-boot-starter:publishAllPublicationsToMavenCentral --no-configuration-cache
gradle closeAndReleaseRepository

gradle jpa-auditing:jpa-auditing-boot-autoconfigure:publishAllPublicationsToMavenCentral --no-configuration-cache
gradle closeAndReleaseRepository

gradle jpa-auditing:jpa-auditing-boot-starter:publishAllPublicationsToMavenCentral --no-configuration-cache
gradle closeAndReleaseRepository

