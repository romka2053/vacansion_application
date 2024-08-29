pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "vacantionTest"
include(":app")
include(":core-network-impl")
include(":core")
include(":feature-login")
include(":core-ui")
include(":feature-search")
include(":feature-vacancy")
include(":feature-response")
include(":feature-response-api")
include(":core-local-db-api")
include(":core-db-room")
include(":navigation")
include(":core-network-api")
include(":feature-favorite")
