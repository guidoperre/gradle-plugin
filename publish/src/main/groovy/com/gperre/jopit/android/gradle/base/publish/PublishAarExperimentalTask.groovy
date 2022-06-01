package com.gperre.jopit.android.gradle.base.publish

import com.gperre.jopit.android.gradle.base.utils.*
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider

class PublishAarExperimentalTask extends PublishAarTask {

    @Override
    TaskProvider<Task> register(Builder builder) {
        super.register(builder)

        VersionContainer.put(project.name, builder.taskName, flavorVersion("EXPERIMENTAL-${project.version}-${getTimestamp()}", builder.variant))

        TaskProvider<Task> task
        if (project.tasks.names.contains(builder.taskName)) {
            task = project.tasks.named(builder.taskName)
        } else {
            task = project.tasks.register(builder.taskName)
            task.configure {
                group = TASK_GROUP

                doLast {
                    VersionContainer.logVersion("${project.group}:${project.name}:${VersionContainer.get(project.name, task.name, project.version as String)}")
                }

                dependsOn getBundleTaskName(project, variant), getSourcesJarTaskName(variant), getJavadocJarTask(variant)
                finalizedBy "publish${taskName.capitalize()}PublicationToAndroidExperimentalRepository"
            }
        }

        createMavenPublication()

        return task
    }
}