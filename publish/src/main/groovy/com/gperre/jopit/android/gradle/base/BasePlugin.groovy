package com.gperre.jopit.android.gradle.base

import com.gperre.jopit.android.gradle.base.modules.AndroidLibraryPublishableModule
import com.gperre.jopit.android.gradle.base.utils.VersionContainer
import org.gradle.api.Plugin
import org.gradle.api.Project

class BasePlugin implements Plugin<Object> {

    public static final String ANDROID_LIBRARY_PLUGIN = 'com.android.library'

    private static final ANDROID_LIBRARY_MODULES = { ->
        return [
            new AndroidLibraryPublishableModule()
        ]
    }

    private Project project

    @Override
    void apply(Object target) {
        if (target instanceof Project) {
            apply((Project) target)
        }
    }

    void apply(Project project) {
        this.project = project

        VersionContainer.init()

        project.plugins.withId(ANDROID_LIBRARY_PLUGIN) {
            ANDROID_LIBRARY_MODULES().each { module -> module.configure(project) }
        }
    }
}
