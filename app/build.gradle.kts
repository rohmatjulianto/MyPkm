plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.firebase.crashlytics")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.joule.mypoke"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.6.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.2")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.github.bumptech.glide:glide:4.13.0")

    Dependencies.apply {
        // dependency
        implementation(KOIN_ANDROID)
        implementation(KOIN_CORE)

        // coroutines
        implementation(COROUTINES)
        implementation(COROUTINES_ANDROID)

        // remote
        implementation(RETROFIT)
        implementation(RETROFIT_GSON)
        implementation(LOGGING)
        implementation(OKHTTP)

        // local
        implementation(ROOM_KTX)
        kapt(ROOM_COMPILER)

        // firebase crashlytics
        implementation(FIRE_CRASHLYTICS)
        implementation(FIRE_ANALYTICS)
        implementation(platform(FIRE_BOM))

        testImplementation(KOINT_TEST)
        testImplementation(JUNIT)
        testImplementation(MOCK)
        testImplementation(MOCK_IO)
        testImplementation(MOCK_KOTLIN)
        testImplementation(MOCK_SERVER)
        testImplementation(CORE_TEST)
        testImplementation(COROUTINE_TEST)
    }

    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}