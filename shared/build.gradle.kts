import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)

    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }
    
    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.jetbrains.compose.navigation)
            implementation(libs.kotlinx.serialization.json)

            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            api(libs.koin.core)

            implementation(libs.bundles.ktor)
            implementation(libs.bundles.coil)

            implementation(libs.kotlin.date.time)

            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        iosMain.dependencies {
            // Dependência especifica do IOS
            implementation(libs.ktor.client.darwin)

        }
        androidMain.dependencies {
            // Dependência especifica do android
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.ktor.client.okhttp)
        }
        dependencies {
            ksp(libs.androidx.room.compiler)
        }
    }
}

android {
    namespace = "br.com.cerniauskas.translatorkmp"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}