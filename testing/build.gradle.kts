@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    kotlin("kapt")
    alias(libs.plugins.kspPlugin)
    alias(libs.plugins.hiltPlugin)
}

android {
    namespace = "com.kweku.armah.testing"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    // targetProjectPath = ":app"
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.junit)
    implementation(libs.androidx.test.ext.junit)
    implementation(libs.espresso.core)
    implementation(platform(libs.compose.bom))
    api(libs.ui.test.junit4)

    implementation(libs.hilt.android)
    androidTestImplementation(libs.hilt.android.test)
    kapt(libs.hilt.compiler)
}

dependencies {
    // project dependencies - added for dependency injection to work
    implementation(project(":professionalscrummaster:presentation"))
    implementation(project(":professionalscrummaster:domain"))
    implementation(project(":professionalscrummaster:data"))
}

dependencies {
    // project dependencies - added for dependency injection to work
    implementation(project(":professionalsoftwaredeveloper:presentation"))
    implementation(project(":professionalsoftwaredeveloper:domain"))
    implementation(project(":professionalsoftwaredeveloper:data"))
}

dependencies {
    // project dependencies - added for dependency injection to work
    implementation(project(":professionalscrumproductowner:presentation"))
    implementation(project(":professionalscrumproductowner:domain"))
    implementation(project(":professionalscrumproductowner:data"))
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:presentation"))
    implementation(project(":core:utilities"))
    implementation(project(":core:resources"))
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.kotlin.test)
    implementation(libs.kotlinx.coroutines.test)
}

dependencies {
    implementation(libs.androidx.test.ext.junit)
    implementation(libs.espresso.core)
}

dependencies {
    implementation(libs.junit)
    ksp(libs.androidx.room.compiler)
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
