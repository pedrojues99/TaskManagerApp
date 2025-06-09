plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.taskmanager.domain"
    compileSdk = 35

    defaultConfig {
        minSdk = 27
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}
dependencies {
    coreLibraryDesugaring(libs.desugar.jdk.libs)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    implementation(libs.javax.inject)
}