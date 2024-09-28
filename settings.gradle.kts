rootProject.name = "testing-and-verification-software"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.toml"))
        }
    }

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

includeBuild("build-logic")
include("practice:practice3")
