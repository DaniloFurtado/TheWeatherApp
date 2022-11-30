plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = rootProject.extra.get("androidCompileSdkVersion") as Int

    defaultConfig {
        applicationId = "com.danilo.furtado.theweatherapp"
        minSdk = rootProject.extra.get("androidMinSdkVersion") as Int
        targetSdk = rootProject.extra.get("androidTargetSdkVersion") as Int
        versionCode = rootProject.extra.get("androidVersionCode") as Int
        versionName = rootProject.extra.get("androidVersionName") as String

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    val dependenciesProject: Map<String, String> by rootProject.extra

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("com.squareup.picasso:picasso:2.8")
    // injection
    implementation(dependenciesProject["koinCore"].toString())
    implementation(dependenciesProject["koinAndroid"].toString())
    // Navigation
    implementation(dependenciesProject["navFragKtx"].toString())
    implementation(dependenciesProject["navUiKtx"].toString())
    // Retrofit
    implementation(dependenciesProject["retrofit"].toString())
    implementation(dependenciesProject["retrofitConvertGson"].toString())
    implementation(dependenciesProject["loggingInterceptor"].toString())

    testImplementation(dependenciesProject["junit"].toString())
    testImplementation(dependenciesProject["testXCore"].toString())
    testImplementation(dependenciesProject["coroutineTest"].toString())
    testImplementation(dependenciesProject["kotlinTest"].toString())
    testImplementation(dependenciesProject["mockitoCore"].toString())
    testImplementation(dependenciesProject["ioMockk"].toString())

    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
