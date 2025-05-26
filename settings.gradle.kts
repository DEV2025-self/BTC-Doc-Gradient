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
        google()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.google.com") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url  = uri ("https://jitpack.io" )}
        maven { url  = uri ("https://maven.google.com" )} // Google's Maven repository - FCM
        maven { url  = uri ("https://artifacts.applovin.com/android") }
    }
}

rootProject.name = "BTC Doc Gradient"
include(":app")
include (":AdsLib")