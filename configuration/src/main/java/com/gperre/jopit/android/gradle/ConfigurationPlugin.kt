package com.gperre.jopit.android.gradle

import com.gperre.jopit.android.gradle.configuration.configureDependencies
import com.gperre.jopit.android.gradle.configuration.configurePlugins
import com.gperre.jopit.android.gradle.configuration.configureRepositories
import org.gradle.api.Plugin
import org.gradle.api.Project

class ConfigurationPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.configureDependencies()
        project.configurePlugins()
        project.configureRepositories()
    }
}


