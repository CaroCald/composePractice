plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hiltAndroid)
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.practicecompose"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.practicecompose"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.material.icons.extended)

    // Lifecycle Livedata
    implementation(libs.lifecycleViewModelKtx)
    implementation(libs.lifecycleLiveDataKtx)

    // Room
    implementation(libs.roomKtx)
    ksp(libs.roomCompiler)
    androidTestImplementation(libs.roomTesting)

    // Datastore
    implementation(libs.datastorePreferences)

    // Compose Navigation
    implementation(libs.navigationCompose)

    // Coroutines
    implementation(libs.coroutines)

    // Livedata
    implementation(libs.composeRuntimeLiveData)

    // Unit test
    testImplementation(libs.junit)

    implementation(libs.gson)

    // Hilt
    implementation(libs.hiltAndroid)
    ksp(libs.hiltCompiler)
    implementation(libs.hiltNavigationCompose)



    // Play In-App Update
    implementation(libs.appUpdate)
    implementation(libs.appUpdateKtx)

    // Google Play API
    implementation(libs.playServicesAuth)

    // Coil Load Image Url
    implementation(libs.coilCompose)

    // Accompanist - Status Bar
    implementation(libs.accompanistSystemUiController)

    implementation(libs.retrofit)
    implementation(libs.gsonConverter)
    implementation(libs.gson)
    implementation(libs.logging.interceptor)
    //
    implementation(libs.security.crypto)

    implementation(kotlin("reflect"))
}// Allow references to generated code
