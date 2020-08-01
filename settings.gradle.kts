pluginManagement {
    repositories {
        gradlePluginPortal()
        jcenter()
        mavenCentral()
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
        maven { setUrl("https://dl.bintray.com/kotlin/kotlinx") }
    }
}

rootProject.name = "Compose-MPP-workaround"
include("app")
include("lib-compose")
