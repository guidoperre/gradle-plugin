package com.gperre.jopit.android.gradle.dependencies

object Images {
    private const val glide_version = "4.12.0"
    private const val shimmer_version = "0.5.0"
    private const val coil_version = "2.0.0-rc02"

    const val glideAnnotations = "com.github.bumptech.glide:compiler:$glide_version"
    const val glide = "com.github.bumptech.glide:glide:$glide_version"
    const val shimmer = "com.facebook.shimmer:shimmer:$shimmer_version"
    const val coil = "io.coil-kt:coil-compose:$coil_version"
}
