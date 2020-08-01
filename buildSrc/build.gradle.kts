plugins {
  kotlin("jvm") version embeddedKotlinVersion
}

repositories {
  jcenter()
}

dependencies {
  implementation(gradleApi())
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions.jvmTarget = "1.8"
}
