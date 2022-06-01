package com.gperre.jopit.android.gradle.base.modules

import com.gperre.jopit.android.gradle.base.publish.*
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider


class AndroidLibraryPublishableModule extends PublishableModule {

    public static final String PACKAGING = 'Aar'
    public static final String RELEASE_VARIANT = 'release'

    private Project project

    @Override
    void configure(Project project) {
        super.configure(project)

        this.project = project

        project.android.libraryVariants.all { def libraryVariant ->
            createTasksFor(libraryVariant)
        }
    }

    protected void createTasksFor(def libraryVariant) {
        String variantName = libraryVariant.name

        def experimentalTask = createTask(new PublishAarExperimentalTask(), libraryVariant,
                getTaskName(TASK_TYPE_EXPERIMENTAL, PACKAGING, variantName))
        def releaseTask = createTask(new PublishAarPrivateReleaseTask(), libraryVariant,
                getTaskName(TASK_TYPE_RELEASE, PACKAGING, variantName))

        if (libraryVariant.name.toLowerCase().contains(RELEASE_VARIANT)) {
            createStubTask(getTaskName(TASK_TYPE_EXPERIMENTAL, PACKAGING), experimentalTask)
            createStubTask(getTaskName(TASK_TYPE_RELEASE, PACKAGING), releaseTask)
            createStubTask(getTaskName(TASK_TYPE_EXPERIMENTAL), experimentalTask)
            createStubTask(getTaskName(TASK_TYPE_RELEASE), releaseTask)
        }
    }

    protected TaskProvider<Task> createTask(PublishAarTask task, def libraryVariant, String theTaskName) {
        return task.register(new Builder(this.project, libraryVariant, theTaskName))
    }

    protected void createStubTask(String name, TaskProvider<Task> realTask) {
        if (project.tasks.names.contains(name)) {
            project.tasks.named(name).configure {
                dependsOn realTask
            }
        } else {
            project.tasks.register(name) { Task it ->
                it.group = PublishTask.TASK_GROUP
                it.dependsOn realTask
            }
        }
    }
}
