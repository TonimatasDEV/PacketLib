plugins {
    `java-library`
}

apply(plugin = "maven-publish")
apply(plugin = "signing")

java {
    toolchain { 
        languageVersion.set(JavaLanguageVersion.of(8))
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    withJavadocJar()
    withSourcesJar()
}

group = "dev.tonimatas.packetlib"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}