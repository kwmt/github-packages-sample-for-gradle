package net.kwmt27.plugin.internal

import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.implementation(
    dependency: Any
) {
    add("implementation", dependency)
}
