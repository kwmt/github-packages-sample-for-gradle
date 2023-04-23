package net.kwmt27.plugin

import net.kwmt27.plugin.internal.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * plugin/build.gradle.ktsで参照
 */
@Suppress("unused")
class LintPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                implementation("net.kwmt27:lint-library:1.0.0")
            }
        }
    }
}
