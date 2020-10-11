# build JetPack Compose + Kotlin MPP
## Use workaround
Look at lib-compose/build.gradle.kts:
```Kotlin
plugins {
    id("com.android.library")
    kotlin("multiplatform")
}
//...
//todo Workaround here:
configurations {
    create("composeCompiler") {
        isCanBeConsumed = false
    }
}
dependencies {
    "composeCompiler"("androidx.compose.compiler:compiler:$ANDROID_COMPOSE_VERSION")
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
```
## Build this sample .apk
```bash
./gradlew assembleDebug
temporarily MPP works better on version Android Studio 4.2 Canary-6
```
Install apk from dir: ```app/build/outputs/apk/debug/app-debug.apk```

## Additional info for JetBrains Kotlin Team and Google Android Team:
Sorry for Russian language, later will translate to EN.  
Есть известный баг @Composable функции не работают с Kotlin Multiplatform ```id("org.jetbrains.kotlin.multiplatform")```  
Баг заведён в JetBrains YouTrack: https://youtrack.jetbrains.com/issue/KT-38694  
И в Google IssueTracker: https://issuetracker.google.com/issues/155536223  
  
Ключевой момент что для починки нужно добавить аргумент компилятора "-Xplugin=$composeCompilerJar"  
В случае с модулями id("kotlin-android") аргумент -Xplugin уже добавляется  
В случае с  kotlin("multiplatform") я добавил этот аргумент руками  
  
Как починить багу?  
Эта логика описана в репозитории AOSP: https://webcache.googleusercontent.com/search?q=cache:Wl-GOo5My08J:https://android.googlesource.com/platform/frameworks/support/%2B/androidx-master-dev/buildSrc/src/main/kotlin/androidx/build/AndroidXUiPlugin.kt+&cd=1&hl=en&ct=clnk&gl=ru#100  
Предположу что где-то там можно решить эту проблему удобным способом.  
Обратите внимание что добавление -Xplugin происходит до конфигурации project.configureForMultiplatform()  
Возможно стоит изменить порядок кода.  
Один из авторов этого кода - Nikolay Igotti. Возможно он сможет помочь с решением этой задачи.  
  
  
### P.S.
Thanks to Google and JetBrains for amazing JetPack Compose and Kotlin Multiplatform.  
Together we can make these tools even better.  
  
Thanks to https://github.com/r4zzz4k fot help  
  
