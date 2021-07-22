import net.fabricmc.loom.task.RemapJarTask

plugins {
    id ("fabric-loom") version "0.7-SNAPSHOT"
    id ("maven-publish")
	id ("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

base {
    archivesBaseName = "iCommon-Fabric"
    version = "1.16.5"
    group = "com.javazilla.mods"
}


dependencies {
    minecraft ("com.mojang:minecraft:1.16.5")
    mappings ("net.fabricmc:yarn:1.16.5+build.9:v2")
    modImplementation ("net.fabricmc:fabric-loader:0.11.3")
    //api (project(":iCommon-API"))
    //include (project(":iCommon-API"))
}

sourceSets {
    project(":iCommon-API")
    main {
        java {
            srcDir("src/main/java")
          //  srcDir("${rootProject.projectDir}/iCommon-API/src/main/java")
           // compileClasspath += project(":iCommon-API").sourceSets.main.java
           // runtimeClasspath = output + compileClasspath
        }
        resources {
            srcDir("${rootProject.projectDir}/iCommon-API/src/main/resources")
        }
    }
}

tasks.getByName<ProcessResources>("processResources") {
    filesMatching("fabric.mod.json") {
        expand(
            mutableMapOf(
                "version" to "1.1"
            )
        )
    }
}

val remapJar = tasks.getByName<RemapJarTask>("remapJar")

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.group.toString()
            artifactId = project.name.toLowerCase()
            version = project.version.toString()
            
            pom {
                name.set(project.name.toLowerCase())
                description.set("A concise description of my library")
                url.set("http://www.example.com/")
            }

            artifact(remapJar)
        }
    }

    repositories {
        val mavenUsername: String? by project
        val mavenPassword: String? by project
        mavenPassword?.let {
            maven(url = "https://repo.codemc.io/repository/maven-releases/") {
                credentials {
                    username = mavenUsername
                    password = mavenPassword
                }
            }
        }
    }
}