import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "es.iescarrillo.diseofigma"
    compileSdk = 36
    // He puesto 34 (Android 14) que es estable. 36 es beta aún.

    defaultConfig {
        applicationId = "es.iescarrillo.diseofigma"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // --- INICIO CONFIGURACIÓN API KEY ---
        // 1. Definir el archivo local.properties
        val localPropertiesFile = rootProject.file("local.properties")
        val properties = Properties()

        // 2. Cargarlo si existe
        if (localPropertiesFile.exists()) {
            properties.load(FileInputStream(localPropertiesFile))
        }

        // 3. Obtener la clave (o dejarla vacía si no la encuentra)
        val mapsApiKey = properties.getProperty("MAPS_API_KEY") ?: ""

        // 4. Inyectarla en el Manifest
        manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey
        // --- FIN CONFIGURACIÓN API KEY ---
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Asegúrate de que esta línea coincide con cómo se llama en tu libs.versions.toml
    // Si te da error en rojo, cámbiala por: implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation(libs.play.services.maps)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}