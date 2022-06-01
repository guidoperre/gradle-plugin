package com.gperre.jopit.android.gradle.base.publish

import org.gradle.api.Project

class ProjectRepositoryConfiguration {

    static void setupPublishingRepositories(Project project, List<Repository> repositories) {
        repositories.forEach({ repository ->

            project.publishing.repositories.maven {
                name = repository.name
                url = repository.url
                allowInsecureProtocol(true)
                if (repository.credentials.username == null || repository.credentials.password == null) {
                    println "[!] No repository credentials were found. Configuring ${repository.name} publishing without them."
                } else {
                    credentials {
                        credentials {
                            username repository.credentials.username
                            password repository.credentials.password
                        }
                    }
                }
            }
        })
    }
}
