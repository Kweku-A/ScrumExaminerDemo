@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    kotlin("kapt")
    alias(libs.plugins.hiltPlugin)
}

android {
    namespace = "com.kweku.armah.pspo.presentation"
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
                "proguard-rules.pro",
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {

    implementation(project(":professionalscrumproductowner:domain"))
    implementation(project(":core:resources"))
    implementation(project(":core:domain"))
    implementation(project(":core:presentation"))
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation("androidx.compose.foundation:foundation:1.4.3")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    // implementation(libs.androidx.material)
    implementation(libs.androidx.material.icons.extended)
    implementation(platform(libs.kotlin.bom))

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.androidx.activity.ktx)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
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
