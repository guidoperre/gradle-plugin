package com.gperre.jopit.android.gradle.configuration

import com.gperre.jopit.android.gradle.dependencies.*
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureDependencies() = dependencies {
    // Android
    implementation(Android.junit)
    implementation(Android.material)
    implementation(Android.liveData)
    implementation(Android.lifecycleScope)
    implementation(Android.lifecycleViewModel)

    // Android test
    androidTestImplementation(Android.robolectric)
    androidTestImplementation(Android.testCore)
    androidTestImplementation(Android.testRunner)

    // Dependency injection
    implementation(Hilt.core)
    implementation(Hilt.navigationCompose)
    kapt(Hilt.hiltCompiler)
    kapt(Hilt.compiler)
}

private fun DependencyHandlerScope.implementation(
    dependency: String
) = add("implementation", dependency)

private fun DependencyHandlerScope.androidTestImplementation(
    dependency: String
) = add("androidTestImplementation", dependency)

private fun DependencyHandlerScope.debugImplementation(
    dependency: String
) = add("debugImplementation", dependency)

private fun DependencyHandlerScope.kapt(
    dependency: String
) = add("kapt", dependency)
