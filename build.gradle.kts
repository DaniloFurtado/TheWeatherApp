// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra["androidVersionCode"] = 1
    extra["androidVersionName"] = "1.0"
    extra["androidMinSdkVersion"] = 24
    extra["androidTargetSdkVersion"] = 33
    extra["androidCompileSdkVersion"] = 33
    val koinVersion = "3.1.2"
    val kotlinVersion = "1.6.0"
    val mockitoCoreVersion = "2.28.2"
    val ioMockkVersion = "1.12.0"
    val junitVersion = "4.13.2"
    val retrofitVersion = "2.9.0"
    val fragmentKtxVersion = "2.5.2"
    val navVersion = "2.5.2"
    val gsonVersion = "2.8.9"
    val glideVersion = "4.14.2"
    val loggingInterceptorVersion = "4.9.3"
    val coreKtxVersion = "1.4.0"
    val coroutinesTestVersion = "1.4.2"
    val dependenciesProject = hashMapOf<String, String>()
    dependenciesProject["koinCore"] = "io.insert-koin:koin-core:$koinVersion"
    dependenciesProject["junit"] = "junit:junit:$junitVersion"
    dependenciesProject["testXCore"] = "androidx.test:core:$coreKtxVersion"
    dependenciesProject["coroutineTest"] = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion"
    dependenciesProject["mockitoCore"] = "org.mockito:mockito-core:$mockitoCoreVersion"
    dependenciesProject["ioMockk"] = "io.mockk:mockk:$ioMockkVersion"
    dependenciesProject["kotlinTest"] = "org.jetbrains.kotlin:kotlin-test:$kotlinVersion"
    dependenciesProject["koinAndroid"] = "io.insert-koin:koin-android:$koinVersion"
    dependenciesProject["navFragKtx"] = "androidx.navigation:navigation-fragment-ktx:$fragmentKtxVersion"
    dependenciesProject["navUiKtx"] = "androidx.navigation:navigation-ui-ktx:$navVersion"
    dependenciesProject["retrofit"] = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    dependenciesProject["retrofitConvertGson"] = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    dependenciesProject["coroutinesCore"] = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinVersion"
    dependenciesProject["gson"] = "com.google.code.gson:gson:$gsonVersion"
    dependenciesProject["glide"] = "com.github.bumptech.glide:glide:$glideVersion"
    dependenciesProject["loggingInterceptor"] = "com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVersion"

    extra["dependenciesProject"] = dependenciesProject
}

plugins {
    id("com.android.application") version "7.2.2" apply false
    id("com.android.library") version "7.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.7.0" apply false
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}
