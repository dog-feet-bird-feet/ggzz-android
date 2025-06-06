plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // di
    implementation(libs.javax.inject)

    // coroutines
    implementation(libs.kotlinx.coroutines.core)

    // unit test
    testImplementation(libs.assertj.core)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)

    // Okhttp3
    implementation(libs.okhttp)
}

tasks.test {
    useJUnitPlatform()
}
