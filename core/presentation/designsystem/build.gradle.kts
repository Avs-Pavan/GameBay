plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.pavan.gamebay.core.presentaion.designsystem"
    compileSdk = 35

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    /**
     * Core dependencies for the design system module.
     * Using `api` to expose these dependencies to consumers of this module.
     */
    api(libs.androidx.core.ktx)

    /**
     * Lifecycle runtime dependencies for the design system module.
     * Using `api` to expose these dependencies to consumers of this module.
     */
    api(libs.androidx.lifecycle.runtime.ktx)

    /**
     * Compose activity dependencies for the design system module.
     * Using `api` to expose these dependencies to consumers of this module.
     */
    api(libs.androidx.activity.compose)

    /**
     * Compose BOM (Bill of Materials) dependencies for the design system module.
     * Using `api` to expose these dependencies to consumers of this module.
     */
    api(platform(libs.androidx.compose.bom))

    /**
     * UI dependencies for the design system module.
     * Using `api` to expose these dependencies to consumers of this module.
     */
    api(libs.androidx.ui)

    /**
     * UI graphics dependencies for the design system module.
     * Using `api` to expose these dependencies to consumers of this module.
     */
    api(libs.androidx.ui.graphics)

    /**
     * UI tooling preview dependencies for the design system module.
     * Using `api` to expose these dependencies to consumers of this module.
     */
    api(libs.androidx.ui.tooling.preview)

    /**
     * Material3 dependencies for the design system module.
     * Using `api` to expose these dependencies to consumers of this module.
     */
    api(libs.androidx.material3)


    /**
     * Core KTX dependencies for the design system module.
     * Expose these dependencies to consumers of this module.
     */
    api(libs.androidx.core.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)

    /**
     * Coil dependencies for the design system module.
     * */
    api(libs.coil.compose)
    api(libs.coil.network.okhttp)

    /**
     * Lottie
     * */
    api(libs.airbnb.lottie)

    testApi(libs.junit)
    androidTestApi(libs.androidx.junit)
    androidTestApi(libs.androidx.espresso.core)
    androidTestApi(platform(libs.androidx.compose.bom))
    androidTestApi(libs.androidx.ui.test.junit4)
    debugApi(libs.androidx.ui.tooling)
    debugApi(libs.androidx.ui.test.manifest)
}