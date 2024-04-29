pluginManagement {
    repositories {
        google()
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

rootProject.name = "effective_test"
include(":app")
include(":core:core_utils")
include(":core:core_data")
include(":core:core_domain")
include(":tiket:presentation")
include(":hotel:presentation")
include(":breafly:presentation")
include(":subscriptions:presentation")
include(":profile:presentation")
