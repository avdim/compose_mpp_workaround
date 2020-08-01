plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

android {
    compileSdkVersion(ANDROID_COMPILE_SDK)
    defaultConfig {
        minSdkVersion(ANDROID_MIN_SDK)
        targetSdkVersion(ANDROID_TARGET_SDK)
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerVersion = ANDROID_KOTLIN_COMPILER_VERSION
        kotlinCompilerExtensionVersion = ANDROID_COMPOSE_VERSION
    }
}

kotlin {
    android {

    }
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation("androidx.appcompat:appcompat:$ANDROID_APP_COMPAT")

                implementation("androidx.compose.runtime:runtime:$ANDROID_COMPOSE_VERSION")
                implementation("androidx.compose.foundation:foundation:$ANDROID_COMPOSE_VERSION")
            }
        }
    }
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    //todo Workaround here:
    val composeCompilerJar =
        file("compose-compiler-0.1.0-dev15.jar").absolutePath //need download jar
    kotlinOptions.freeCompilerArgs += listOf("-Xuse-ir", "-Xplugin=$composeCompilerJar")
}
