plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
apply(from = rootProject.file("gradle/publish.gradle.kts"))

android {
    namespace = "net.kwmt27.lint.library"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    lint {
        checkDependencies = true
    }
}
dependencies {
    implementation(project(":lint:checks"))
    lintPublish(project(":lint:checks"))
}
