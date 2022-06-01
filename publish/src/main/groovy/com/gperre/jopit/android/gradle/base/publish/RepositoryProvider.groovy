package com.gperre.jopit.android.gradle.base.publish

class RepositoryProvider {
    private static final List<Repository> REPOSITORIES = new ArrayList<Repository>() {
        {
            add(
                new Repository(
                    "AndroidExperimental",
                    "YOUR_EXPERIMENTAL_URL",
                    new EnvironmentPublishCredentials()
                )
            )
            add(
                new Repository(
                    "AndroidRelease",
                    "YOUR_RELEASE_URL",
                    new EnvironmentPublishCredentials()
                )
            )
        }
    }

    static List<Repository> getRepositories() {
        return REPOSITORIES
    }
}
