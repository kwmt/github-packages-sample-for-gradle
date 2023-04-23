plugins {
    id("java-library")
    id("kotlin")
    id("com.android.lint")
}
apply(from = rootProject.file("gradle/publish.gradle.kts"))

lint {
    htmlReport = true
    htmlOutput = file("lint-report.html")
    textReport = true
    absolutePaths = false
    ignoreTestSources = true
}


dependencies {
    val lintVersion = "30.3.1"
    val kotlinVersion = "1.8.0"
    // For a description of the below dependencies, see the main project README
    compileOnly("com.android.tools.lint:lint-api:$lintVersion")
    // You typically don't need this one:
    compileOnly("com.android.tools.lint:lint-checks:$lintVersion")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")

    testImplementation("junit:junit:4.13.2")
    testImplementation("com.android.tools.lint:lint:$lintVersion")
    testImplementation("com.android.tools.lint:lint-tests:$lintVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
