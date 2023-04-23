apply(plugin = "maven-publish")

project.afterEvaluate {
    fun String.toArtifactId(): String {
        return split(":").filter { it.isNotEmpty() }.joinToString("-")
    }
    configure<PublishingExtension> {
        components.forEach { component ->
            publications {
                create<MavenPublication>(component.name) {
                    from(component)
                    groupId = "net.kwmt27"
                    artifactId = project.path.toArtifactId()
                    version = "1.0.0"
                }
            }
        }

        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/kwmt/github-packages-sample-for-gradle")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}
