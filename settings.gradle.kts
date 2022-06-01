pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "gradle-plugin"

include(":publish")
include(":configuration")
