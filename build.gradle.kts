// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidTest) apply false
    alias(libs.plugins.hiltPlugin) apply false
    alias(libs.plugins.kspPlugin) apply false
    id("org.sonarqube") version "4.2.1.3168"
}
true // Needed to make the Suppress annotation work for the plugins block

sonar {
    properties {
        property("sonar.projectKey", "Kweku-A_ScrumExams_AYw6BGArqsL00adsOdul")
        property("sonar.projectName", "ScrumExams")
        property(
            "sonar.host.url",
            "http://localhost:9000/",
        )
//        property(
//            "sonar.token",
//            System.getProperty("sonarToken").toString(),
//        )
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "${rootProject.projectDir}/app/build/reports/jacoco/debug/jacoco.xml",
        )
        property("sonar.skipCompile", false)
        property("sonar.gradle.skipCompile", false)
    }
}
