plugins {
    id("com.android.dynamic-feature")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.favorite"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)

    // Jetpack Compose
    implementation(libs.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.play.services.analytics.impl)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.engage.core)

    // Jetpack Compose dependencies
    implementation("androidx.compose.ui:ui:1.5.1")
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.1")
    implementation("androidx.compose.runtime:runtime:1.5.1")
    implementation(project(":core"))

    // Test Dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Core Module dependency
    implementation(project(":app"))

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.3.0")

    // Dagger Dependencies
    implementation("com.google.dagger:dagger:2.48")
    kapt("com.google.dagger:dagger-compiler:2.48")

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
}
