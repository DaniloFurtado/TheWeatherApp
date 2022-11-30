plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    val dependenciesProject: Map<String, String> by rootProject.extra

    implementation(dependenciesProject["koinCore"].toString())
    implementation(dependenciesProject["coroutinesCore"].toString())
    // Unit test
    testImplementation(dependenciesProject["junit"].toString())
    testImplementation(dependenciesProject["mockitoCore"].toString())
    testImplementation(dependenciesProject["ioMockk"].toString())
    testImplementation(dependenciesProject["kotlinTest"].toString())
    testImplementation(dependenciesProject["testXCore"].toString())
    testImplementation(dependenciesProject["coroutineTest"].toString())
}
