plugins {
    `java-gradle-plugin`
    `maven-publish`
    `kotlin-dsl`
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
}

gradlePlugin {
    plugins {
        create("configuration") {
            id = "jopit.configuration"
            implementationClass = "com.gperre.jopit.android.gradle.ConfigurationPlugin"
        }
    }
}

publishing {
    repositories {
        maven {
            name = "releases"
            url = uri("YOUR_URI")
            isAllowInsecureProtocol = true
        }
    }
}

val projectGroup: String by project

tasks.withType(PublishToMavenRepository::class.java) {
    doLast {
        val dependencyText = "$projectGroup:${project.name}:${publication.version}"
        val publishingText = "Publishing version: $dependencyText"
        println("\u001B[92m$publishingText\u001B[0m")
    }
}
