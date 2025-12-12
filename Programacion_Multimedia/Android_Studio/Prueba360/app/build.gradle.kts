plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.prueba360"

    // Esto lo dejé como lo tenías tú (parece que usas una versión muy nueva o preview)
    compileSdk {
        // Si te da error aquí, cámbialo simplemente por: compileSdk = 35
        // version = release(36)
    }
    // Para asegurar compatibilidad, lo estándar ahora mismo suele ser:
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.prueba360"
        minSdk = 24
        targetSdk = 35 // Ajustado a 35 para ir sobre seguro, si tienes la 36 déjala
        versionCode = 1
        versionName = "1.0"

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
}

dependencies {
    // Tus librerías que ya venían (Version Catalog)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // --- GLIDE (Corregido para Kotlin DSL) ---
    // Fíjate: Paréntesis () y comillas dobles ""
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
}