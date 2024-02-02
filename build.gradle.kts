import java.net.URI

plugins {
    java
    `maven-publish`
    signing
}

apply(plugin = "maven-publish")
apply(plugin = "signing")

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(8)) }
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

tasks.compileJava {
    options.encoding = "UTF-8"
    options.javaModuleVersion.set(provider { project.version as String })
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])

            pom {
                withXml {
                    val root = asNode()
                    root.appendNode("description", project.description)
                    root.appendNode("name", project.name)
                    root.appendNode("url", "https://github.com/TonimatasDEV/PacketLib.git")
                }

                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        name.set("TonimatasDEV")
                    }
                }

                scm {
                    url.set("https://github.com/TonimatasDEV/PacketLib.git")
                }
            }
        }
    }

    repositories {
        // Sonatype OSSRH
        maven {
            url = URI("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("PASSWORD")
            }
        }
    }
}

signing {
    isRequired = gradle.taskGraph.hasTask("publish")
    sign(publishing.publications["maven"])
}

tasks.withType<PublishToMavenRepository>() {
    dependsOn(tasks.publishToMavenLocal)
}

tasks.withType<Jar>() {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}