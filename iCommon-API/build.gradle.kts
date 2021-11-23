import net.fabricmc.loom.task.RemapJarTask

plugins {
    id ("fabric-loom") version "0.9-SNAPSHOT"
    id ("java-library")
    id ("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

base {
    archivesBaseName = "iCommon"
    version = "-The-API"
    group = "com.javazilla.mods"
}

repositories {
	maven {
            url = uri("https://maven.fabricmc.net/")
        }
}

tasks.withType<Jar> { duplicatesStrategy = DuplicatesStrategy.INCLUDE }

dependencies {
    minecraft ("com.mojang:minecraft:1.16.5")
    mappings ("net.fabricmc:yarn:1.16.5+build.10:v2")
    modImplementation ("net.fabricmc:fabric-loader:0.11.3")
}

tasks.getByName<ProcessResources>("processResources") {
duplicatesStrategy = DuplicatesStrategy.INCLUDE
    filesMatching("fabric.mod.json") {
        expand(
            mutableMapOf(
                "version" to "1.1"
            )
        )
    }
}