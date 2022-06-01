val projectGroup: String by project
val libraryVersion: String by project

@Suppress("GradleDynamicVersion")
buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
    }
}


allprojects {
    repositories {
        google()
        mavenCentral()
    }
    group = projectGroup
    version = libraryVersion
}
