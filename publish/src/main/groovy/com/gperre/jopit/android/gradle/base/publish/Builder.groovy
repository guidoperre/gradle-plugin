package com.gperre.jopit.android.gradle.base.publish

import org.gradle.api.Project

class Builder {
    Project project = null

    def variant = null

    String taskName = null

    Builder(Project project, def variant, String taskName) {
        this.project = project
        this.variant = variant
        this.taskName = taskName
    }
}
