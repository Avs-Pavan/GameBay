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
        maven{
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "GameBay"
include(":app")
include(":core:presentation:designsystem")
include(":core:domain")
include(":core:data")
include(":feature:gameschedule:domain")
include(":feature:gameschedule:data")
include(":feature:gameschedule:presentation")
