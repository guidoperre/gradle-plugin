package com.gperre.jopit.android.gradle.dependencies

object Compose {
    private const val compose = "1.0.5"

    const val ui = "androidx.compose.ui:ui:$compose"
    const val runtime = "androidx.compose.runtime:runtime:$compose"
    const val uiGraphics = "androidx.compose.ui:ui-graphics:$compose"
    const val uiTooling = "androidx.compose.ui:ui-tooling:$compose"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:$compose"
    const val material = "androidx.compose.material:material:$compose"
    const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$compose"
    const val uiTestJUnit = "androidx.compose.ui:ui-test-junit4:$compose"
    const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$compose"
    const val liveData = "androidx.compose.runtime:runtime-livedata:$compose"
}

object ComposeExternal {
    private const val activity_compose = "1.4.0"
    private const val constraint_layout_version = "1.0.0-beta02"
    private const val nav_compose_version = "2.4.2"
    private const val paging_compose_version = "1.0.0-alpha12"
    private const val accompanist_version = "0.23.1"

    const val activity = "androidx.activity:activity-compose:$activity_compose"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:$constraint_layout_version"
    const val navigation = "androidx.navigation:navigation-compose:$nav_compose_version"
    const val paging = "androidx.paging:paging-compose:$paging_compose_version"
    const val pager = "com.google.accompanist:accompanist-pager:$accompanist_version"
    const val pagerIndicator = "com.google.accompanist:accompanist-pager-indicators:$accompanist_version"
    const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:$accompanist_version"
    const val placeHolder = "com.google.accompanist:accompanist-placeholder:$accompanist_version"
    const val navigationAnimation = "com.google.accompanist:accompanist-navigation-animation:$accompanist_version"
}
