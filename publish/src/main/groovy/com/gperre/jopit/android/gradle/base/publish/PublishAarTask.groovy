package com.gperre.jopit.android.gradle.base.publish

import com.gperre.jopit.android.gradle.base.utils.PomUtils
import com.gperre.jopit.android.gradle.base.utils.VersionContainer
import org.gradle.api.*
import org.gradle.api.artifacts.Configuration
import org.gradle.api.publish.PublicationContainer
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.external.javadoc.JavadocMemberLevel

abstract class PublishAarTask extends PublishTask {

    Project project

    def variant

    String taskName

    TaskProvider<Task> register(Builder builder) {
        project = builder.project
        variant = builder.variant
        taskName = builder.taskName

        if (variant == null || project == null || taskName == null) {
            throw new GradleException("Builder was missing properties")
        }
    }

    protected void createMavenPublication() {
        project.publishing.publications { PublicationContainer it ->
            def flavored = variant.flavorName && !variant.flavorName.isEmpty()
            def baseVariantArtifactId = flavored ? variant.flavorName.replace('_', '-') : variant.name
            def variantArtifactId = "${project.name}-$baseVariantArtifactId"
            def javaDocDestDir = project.file("${project.buildDir}/docs/javadoc/${variantArtifactId}")
            def sourceDirs = variant.sourceSets.collect {it.javaDirectories}

            String javadocTaskName = "${variant.name}Javadoc"
            TaskProvider<Javadoc> javadoc
            if (project.tasks.names.contains(javadocTaskName)) {
                javadoc = project.tasks.named(javadocTaskName)
            } else {
                javadoc = project.tasks.register(javadocTaskName, Javadoc)
                javadoc.configure {
                    description "Generates Javadoc for ${variant.name}."
                    group 'Documentation'

                    // Yes, javaCompile is deprecated, but whats the alternative?
                    source = variant.javaCompile.source

                    destinationDir = javaDocDestDir

                    classpath += project.files(project.android.getBootClasspath().join(File.pathSeparator))
                    project.configurations.findAll { it.canBeResolved && it.state != Configuration.State.UNRESOLVED }.each {
                        classpath += it
                    }

                    if (JavaVersion.current().isJava11Compatible()) {
                        options.addStringOption('Xdoclint:none', '-quiet')
                    }

                    options.memberLevel = JavadocMemberLevel.PROTECTED

                    options.links("http://docs.oracle.com/javase/7/docs/api/")
                    options.links("http://d.android.com/reference/")
                    exclude '**/BuildConfig.java'
                    exclude '**/R.java'
                    failOnError false
                }
            }

            String javadocJarTaskName = "${variant.name}JavadocJar"
            TaskProvider<Jar> javadocJar
            if (project.tasks.names.contains(javadocJarTaskName)) {
                javadocJar = project.tasks.named(javadocJarTaskName)
            } else {
                javadocJar = project.tasks.register(javadocJarTaskName, Jar)
                javadocJar.configure {
                    dependsOn javadoc
                    description "Puts Javadoc for ${variant.name} in a jar."
                    group 'Documentation'
                    classifier = 'javadoc'
                    from javadoc.get().destinationDir
                }
            }

            String sourcesTaskName = "${variant.name}SourcesJar"
            TaskProvider<Jar> sourcesJar
            if (project.tasks.names.contains(sourcesTaskName)) {
                sourcesJar = project.tasks.named(sourcesTaskName)
            } else {
                sourcesJar = project.tasks.register(sourcesTaskName, Jar)
                sourcesJar.configure {
                    description "Puts sources for ${variant.name} in a jar."
                    group 'Packaging'

                    from sourceDirs
                    classifier = 'sources'
                }
            }

            it.register(taskName, MavenPublication).configure {
                artifactId = project.name
                groupId = project.group
                version = VersionContainer.get(project.name, taskName, project.version as String)

                artifacts = [
                        variant.outputs.first().packageLibrary,
                        sourcesJar.get(),
                        javadocJar.get()
                ]

                pom.withXml { XmlProvider xmlProvider ->
                    xmlProvider.asNode().packaging*.value = 'aar'

                    PomUtils.injectDependencies(project, xmlProvider, variant.name, variant.flavorName)
                    PomUtils.composeDynamicDependencies(project, xmlProvider)

                    project.file("${project.buildDir}/publications/${taskName}/pom-default.xml")
                            .write(xmlProvider.asString().toString())
                }
            }

            String pomTaskName = "generatePomFileFor${taskName.capitalize()}Publication"
            if (project.tasks.names.contains(pomTaskName)) {
                project.tasks.named(taskName).configure {
                    dependsOn pomTaskName
                }
            }
        }
    }

    protected static String flavorVersion(String version, def variant) {
        if (variant.flavorName) {
            return "${variant.flavorName}-${version}"
        }
        return version
    }

    protected static String getBundleTaskName(Project project, def variant) {
        def bundleTask = "bundle${variant.name.capitalize()}"
        return project.tasks.names.contains("${bundleTask}Aar") ? "${bundleTask}Aar" : bundleTask
    }

    protected static String getSourcesJarTaskName(def variant) {
        return "${variant.name}SourcesJar"
    }

    protected static String getJavadocJarTask(def variant) {
        return "${variant.name}JavadocJar"
    }
}
