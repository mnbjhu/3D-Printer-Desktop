import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlin_version = "1.5.21"
val spek_version = "2.0.17"

plugins {
    kotlin("jvm") version "1.5.21"
    id("org.jetbrains.compose") version "1.0.0-alpha3"
}

group = "me.james"
version = "1.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(compose.desktop.currentOs)
    testImplementation(compose("org.jetbrains.compose.ui:ui-test-junit4"))
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    testImplementation ("org.spekframework.spek2:spek-dsl-jvm:$spek_version")
    testRuntimeOnly ("org.spekframework.spek2:spek-runner-junit5:$spek_version")
    testRuntimeOnly ("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "3D-Printer-Desktop"
            packageVersion = "1.0.0"
        }
    }
}