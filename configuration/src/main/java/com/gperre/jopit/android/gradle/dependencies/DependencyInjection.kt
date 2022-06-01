package com.gperre.jopit.android.gradle.dependencies

object Hilt {
    private const val hilt = "2.39.1"
    private const val hilt_compiler = "1.0.0-alpha03"
    private const val hilt_navigation_compose = "1.0.0-alpha03"

    const val core = "com.google.dagger:hilt-android:$hilt"
    const val compiler = "androidx.hilt:hilt-compiler:$hilt_compiler"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hilt"
    const val navigationCompose = "androidx.hilt:hilt-navigation-compose:$hilt_navigation_compose"
}
