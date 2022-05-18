object Dependencies {

    // KOTLIN COROUTINE
    const val COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.COROUTINES}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.COROUTINES}"

    // ROOM
    const val ROOM_KTX = "androidx.room:room-ktx:${BuildDependenciesVersions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${BuildDependenciesVersions.ROOM}"


    // RETROFIT
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.RETROFIT}"
    const val RETROFIT_GSON =
        "com.squareup.retrofit2:converter-gson:${BuildDependenciesVersions.RETROFIT}"


    // LOGGING
    const val LOGGING =
        "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.LOGGING}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${BuildDependenciesVersions.OKHTTP}"

    // KOIN
    const val KOIN_CORE = "io.insert-koin:koin-core:${BuildDependenciesVersions.KOIN}"
    const val KOIN_ANDROID = "io.insert-koin:koin-android:${BuildDependenciesVersions.KOIN}"

    // Test features
    const val KOINT_TEST = "io.insert-koin:koin-test:${BuildDependenciesVersions.KOIN}"
    const val JUNIT = "junit:junit:4.13.2"
    const val MOCK = "org.mockito:mockito-core:4.3.1"
    const val CORE_TEST = "androidx.arch.core:core-testing:2.1.0"
    const val MOCK_KOTLIN = "org.mockito.kotlin:mockito-kotlin:4.0.0"
    const val MOCK_IO = "io.mockk:mockk:1.12.2"
    const val COROUTINE_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0"
    const val MOCK_SERVER = "com.squareup.okhttp3:mockwebserver:5.0.0-alpha.2"

    const val FIRE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics-ktx"
    const val FIRE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
    const val FIRE_BOM = "com.google.firebase:firebase-bom:30.0.1"
}

object BuildDependenciesVersions {
    const val COROUTINES = "1.3.9"
    const val ROOM = "2.3.0"
    const val KOIN = "3.1.2"
    const val RETROFIT = "2.9.0"
    const val LOGGING = "4.9.0"
    const val OKHTTP = "4.9.1"
}