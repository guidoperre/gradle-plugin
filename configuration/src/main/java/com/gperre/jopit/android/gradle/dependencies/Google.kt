package com.gperre.jopit.android.gradle.dependencies

object Room {
    private const val room_version = "2.3.0"

    const val core = "androidx.room:room-runtime:$room_version"
    const val compiler = "androidx.room:room-compiler:$room_version"
    const val ktx = "androidx.room:room-ktx:$room_version"
    const val ktxCompiler = "androidx.room:room-compiler:$room_version"
}

object Google {
    private const val location_version = "19.0.1"
    private const val maps_version = "18.0.0"
    private const val google_firebase_version = "29.0.4"
    private const val google_version = "20.1.0"
    private const val google_maps_compose_version = "1.0.0"

    const val mapsCompose = "com.google.maps.android:maps-compose:$google_maps_compose_version"
    const val platform = "com.google.firebase:firebase-bom:$google_firebase_version"
    const val authFirebase = "com.google.firebase:firebase-auth"
    const val auth = "com.google.android.gms:play-services-auth:$google_version"
    const val maps = "com.google.android.gms:play-services-maps:$maps_version"
    const val location = "com.google.android.gms:play-services-location:$location_version"
}
