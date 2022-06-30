plugins {
    `java-library`
}

repositories {
    mavenLocal()
    mavenCentral()
}

tasks {
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    }
}