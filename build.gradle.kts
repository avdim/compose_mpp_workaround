plugins {
    kotlin("multiplatform") version KOTLIN_VERSION apply false
}

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$ANDROID_GRADLE_PLUGIN")
    }
}

allprojects {
    repositories {
        mavenLocal()
        google()
        jcenter()
        maven {
            setUrl("https://dl.bintray.com/kotlin/kotlin-eap")
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += listOf(
            "-Xskip-prerelease-check"
//            ,"-Xallow-jvm-ir-dependencies"
//            ,"-XXLanguage:+NonParenthesizedAnnotationsOnFunctionalTypes"
        )
//        kotlinOptions.freeCompilerArgs += listOf("-P", "plugin:androidx.compose.plugins.idea:enabled=true")//"-P plugin:androidx.compose.plugins.idea:enabled=true"

    }
}
