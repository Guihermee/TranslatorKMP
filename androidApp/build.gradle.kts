plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "br.com.cerniauskas.translatorkmp.android"
    compileSdk = 35
    defaultConfig {
        applicationId = "br.com.cerniauskas.translatorkmp.android"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.bundles.coil) // Coil for images

    // Koin for DI
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    implementation(libs.androidx.compose.material.icons.extended) // Extended icons

    // Navigation
    implementation(libs.jetbrains.compose.navigation)
    implementation(libs.kotlinx.serialization.json)
}