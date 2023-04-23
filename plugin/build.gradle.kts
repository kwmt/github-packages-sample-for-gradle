@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("jvm")
    `kotlin-dsl`
}

apply(from = rootProject.file("gradle/publish.gradle.kts"))

sourceSets.configureEach {
    java.srcDirs("src/$name/kotlin")
}

java {
    withSourcesJar()
    withJavadocJar()
}

val pluginVersion = "1.0.1"
val pluginGroupId = "net.kwmt27.plugin.lint"

group = pluginGroupId
version = pluginVersion

gradlePlugin {
    plugins {
        create("android-lint-plugin") {
            id = pluginGroupId
            implementationClass = "net.kwmt27.plugin.LintPlugin"
        }
    }
}
