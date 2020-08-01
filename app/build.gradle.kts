plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion(ANDROID_COMPILE_SDK)
    buildToolsVersion(ANDROID_BUILD_TOOLS_VERSION)

    defaultConfig {
        applicationId("com.sample.compose")
        minSdkVersion(ANDROID_MIN_SDK)
        targetSdkVersion(ANDROID_TARGET_SDK)
        versionCode(1)
        versionName("1.0")
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
}

dependencies {
    implementation(project(":lib-compose"))
    implementation("androidx.appcompat:appcompat:$ANDROID_APP_COMPAT")
}
