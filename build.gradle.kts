import org.jetbrains.intellij.platform.gradle.TestFrameworkType

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.20"
    id("org.jetbrains.intellij.platform") version "2.0.1"
}

group = "github.zimo"
//SNAPSHOT
version = "1.1.6"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        create(
            "IC",
            "2023.3.7",
            useInstaller = false
        )
        bundledPlugin("com.intellij.gradle")
        instrumentationTools()
        testFramework(TestFrameworkType.Platform)
    }
    
    // 声明对Kotlin标准库的依赖关系
    implementation(kotlin("test"))
    implementation("io.vertx:vertx-web:4.4.4")

    implementation("com.fasterxml.jackson.core:jackson-annotations:2.17.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
}

// Configure Gradle IntelliJ Platform Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin.html
intellijPlatform {
    pluginConfiguration {
        id.set("github.zimo.autojsx.wifi")
        name.set("Autojsx.WIFI")
        version.set(project.version.toString())
        ideaVersion {
            sinceBuild.set("233")
            untilBuild.set("241.*")
        }
    }
}

dependencies {
    intellijPlatform {
        create(
            "IC",
            "2023.3.7",
            useInstaller = false
        )
        bundledPlugin("com.intellij.gradle")
        testFramework(TestFrameworkType.Platform)
    }
    
    // 声明对Kotlin标准库的依赖关系
    implementation(kotlin("test"))
    implementation("io.vertx:vertx-web:4.4.4")

    implementation("com.fasterxml.jackson.core:jackson-annotations:2.17.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
