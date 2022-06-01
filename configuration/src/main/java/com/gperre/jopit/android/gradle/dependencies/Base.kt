package com.gperre.jopit.android.gradle.dependencies

object AndroidSdk {
    const val compile = 31
    const val min = 24
    const val target = 31
}

object Android {
    private const val material_version = "1.4.0"
    private const val test_core_version = "1.4.0"
    private const val test_runner_version = "1.4.0"
    private const val junit_version = "4.13.2"
    private const val robolectric_version = "4.4"
    private const val lifecycle_version = "2.2.0"

    const val material = ("com.google.android.material:material:$material_version")
    const val testRunner = ("androidx.test:runner:$test_runner_version")
    const val testCore = ("androidx.test:core:$test_core_version")
    const val junit = ("junit:junit:$junit_version")
    const val robolectric = ("org.robolectric:robolectric:$robolectric_version")
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    const val lifecycleScope = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
}
