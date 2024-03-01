plugins {
    `java-library`
    `maven-publish`
}

apply(plugin = "maven-publish")
apply(plugin = "signing")

val projectVersion: String by extra

java {
    toolchain { 
        languageVersion.set(JavaLanguageVersion.of(8))
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    withJavadocJar()
    withSourcesJar()
}

group = "dev.tonimatas.packetlib"
version = projectVersion

repositories {
    mavenCentral()
}

dependencies {
    
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/TonimatasDEV/PacketLib")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}