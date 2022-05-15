object Dependencies {

    // KOTLIN COROUTINE
    const val COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.COROUTINES}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.COROUTINES}"

    // ROOM
    const val ROOM = "androidx.room:room-runtime:${BuildDependenciesVersions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${BuildDependenciesVersions.ROOM}"


    // PAGING
    const val PAGING = "androidx.paging:paging-runtime-ktx:${BuildDependenciesVersions.PAGING}"

    // RETROFIT
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.RETROFIT}"
    const val RETROFIT_GSON =
        "com.squareup.retrofit2:converter-gson:${BuildDependenciesVersions.RETROFIT}"
    const val RETROFIT_COROUTINE =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"

    // LOGGING
    const val LOGGING =
        "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.LOGGING}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${BuildDependenciesVersions.OKHTTP}"

    // KOIN
    const val KOIN_CORE = "io.insert-koin:koin-core:${BuildDependenciesVersions.KOIN}"
    const val KOIN_ANDROID = "io.insert-koin:koin-android:${BuildDependenciesVersions.KOIN}"
}

object BuildDependenciesVersions {
    const val COROUTINES = "1.3.9"
    const val ROOM = "2.2.5"
    const val PAGING = "3.1.0"
    const val KOIN = "3.1.2"
    const val RETROFIT = "2.9.0"
    const val LOGGING = "4.9.0"
    const val OKHTTP = "4.9.1"

    // Tests
    const val TEST = "1.3.0"
    const val EXT = "1.1.2"
    const val ARCH_CORE = "2.1.0"
    const val JUNIT = "4.13.1"
    const val ROBOELECTRIC = "4.4"
    const val MOCKK = "1.10.2"
    const val ASSERTJ = "3.17.2"
    const val ESPRESSO = "3.3.0"
    const val FRAGMENT_TEST = "1.2.5"
    const val MOCK_WEB_SERVER = "4.9.0"
}