import kotlin.text.capitalize

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
    sourceSets.configureEach {
        val root = "src/android${name.capitalize()}"
        setRoot(root)
        java.srcDirs("$root/kotlin")
    }
}

kotlin {
    android()
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

//todo Workaround here:
configurations {
    create("composeCompiler") {
        isCanBeConsumed = false
    }
}
dependencies {
    "composeCompiler"("androidx.compose:compose-compiler:$ANDROID_COMPOSE_VERSION")
}
android {
    afterEvaluate {
        val composeCompilerJar =
            configurations["composeCompiler"]
                .resolve()
                .singleOrNull()
                ?: error("Please add \"androidx.compose:compose-compiler\" (and only that) as a \"composeCompiler\" dependency")
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions.freeCompilerArgs += listOf("-Xuse-ir", "-Xplugin=$composeCompilerJar")
        }
    }
}
