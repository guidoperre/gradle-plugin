package com.gperre.jopit.android.gradle.configuration

import org.gradle.api.Project

internal fun Project.configurePlugins() {
    project.plugins.withId(ANDROID_LIBRARY_PLUGIN) {
        plugins.apply("jopit.publish")
    }
}

private const val ANDROID_LIBRARY_PLUGIN = "com.android.library"