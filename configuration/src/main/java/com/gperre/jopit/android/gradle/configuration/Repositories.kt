package com.gperre.jopit.android.gradle.configuration

import org.gradle.api.Project
import org.gradle.kotlin.dsl.repositories

internal fun Project.configureRepositories() = repositories {
    google()
    mavenCentral()
    maven {
        url = uri("YOUR_URI")
        isAllowInsecureProtocol = true
    }
}
