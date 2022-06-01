package com.gperre.jopit.android.gradle.dependencies

object Gson {
    private const val gson_version = "2.8.6"

    const val gson = "com.google.code.gson:gson:$gson_version"
}

object Retrofit {
    private const val retrofit_version = "2.9.0"
    private const val gson_version = "2.8.6"

    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
    const val converterGson = "com.squareup.retrofit2:converter-gson:$retrofit_version"
    const val gson = "com.google.code.gson:gson:$gson_version"
}

object Okhttp {
    private const val version = "4.9.3"

    const val core = "com.squareup.okhttp3:okhttp:$version"
    const val interceptors = "com.squareup.okhttp3:logging-interceptor:$version"
}
