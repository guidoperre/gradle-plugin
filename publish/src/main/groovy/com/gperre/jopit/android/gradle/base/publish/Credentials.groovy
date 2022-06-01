package com.gperre.jopit.android.gradle.base.publish

import javax.annotation.Nullable

interface Credentials {

    @Nullable
    String getUsername()

    @Nullable
    String getPassword()
}

class EnvironmentPublishCredentials implements Credentials {
    private static final String REPOSITORY_USER_ENV = "NEXUS_JOPIT_USER"
    private static final String REPOSITORY_PASSWORD_ENV = "NEXUS_JOPIT_PASSWORD"

    @Override
    String getUsername() {
        return System.getenv(REPOSITORY_USER_ENV)
    }

    @Override
    String getPassword() {
        return System.getenv(REPOSITORY_PASSWORD_ENV)
    }
}
