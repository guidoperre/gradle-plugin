package com.gperre.jopit.android.gradle.base.modules

import com.gperre.jopit.android.gradle.base.publish.ProjectRepositoryConfiguration
import com.gperre.jopit.android.gradle.base.publish.Repository
import com.gperre.jopit.android.gradle.base.publish.RepositoryProvider
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.api.tasks.TaskProvider

abstract class PublishableModule implements Module {

    protected static final String TASK_TYPE_RELEASE = 'Release'
    protected static final String TASK_TYPE_EXPERIMENTAL = 'Experimental'

    private static final String TASK_GET_PROJECT_VERSION = "getProjectVersion"

    @Override
    void configure(Project project) {
        project.with {
            apply plugin: MavenPublishPlugin

            configurations {
                archives {
                    extendsFrom project.configurations.default
                }
            }
        }

        List<Repository> repositories = RepositoryProvider.getRepositories()
        ProjectRepositoryConfiguration.setupPublishingRepositories(project, repositories)

        createGetProjectVersionTask(project)
    }

    private static void createGetProjectVersionTask(Project project) {
        TaskProvider<Task> task = project.tasks.register(TASK_GET_PROJECT_VERSION)
        task.configure { Task it ->
            it.setDescription('Gets project version')

            it.doLast {
                def projectVersion = project.version

                def fileName = "project.version"
                def folder = new File('build')
                if (!folder.exists()) {
                    folder.mkdirs()
                }

                def inputFile = new File("${folder}/${fileName}")
                inputFile.write("version: ${projectVersion}")
                println "See '${folder}/${fileName}' file"
            }
        }
    }

    protected static String getTaskName(String type, String packaging = '', String variantName = '') {
        return "publish${packaging.capitalize()}${type}${variantName.capitalize()}"
    }
}
