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

rootProject.name = "ScrumExams"
include(":app")
include(":professionalsoftwaredeveloper:presentation")
include(":professionalsoftwaredeveloper:data")
include(":professionalsoftwaredeveloper:domain")
include(":core:resources")
include(":core:domain")
include(":core:data")
include(":core:utilities")
include(":professionalscrummaster:presentation")
include(":professionalscrummaster:data")
include(":professionalscrummaster:domain")
include(":core:presentation")
include(":professionalscrumproductowner:domain")
include(":professionalscrumproductowner:data")
