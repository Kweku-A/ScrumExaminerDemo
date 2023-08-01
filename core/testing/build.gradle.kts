@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    kotlin("kapt")
   // alias(libs.plugins.hiltPlugin)
}

android {
    namespace = "com.kweku.armah.testing"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.androidx.runner)
    implementation(libs.junit)
    implementation(libs.androidx.test.ext.junit)
    implementation(libs.espresso.core)
    implementation(platform(libs.compose.bom))
    api(libs.ui.test.junit4)

    implementation(libs.hilt.android.test)
    implementation(libs.hilt.android.test)
    kapt(libs.hilt.compiler)
    // ...with Kotlin.

}

kotlin {
    sourceSets.all {
        languageSettings.apply {
            languageVersion = "1.9" // possible values: "1.4", "1.5", "1.6", "1.7", "1.8", "1.9"
            apiVersion = "1.9" // possible values: "1.3", "1.4", "1.5", "1.6", "1.7", "1.8", "1.9"
            // enableLanguageFeature("InlineClasses") // language feature name
            optIn("kotlin.ExperimentalUnsignedTypes") // annotation FQ-name
            progressiveMode = true // false by default
        }
    }
}