import net.fabricmc.loom.task.RemapJarTask

plugins {
    id ("fabric-loom") version "0.6-SNAPSHOT"
    //id ("java")
    id ("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

base {
    archivesBaseName = "iCommon"
    version = "1.16-b1"
    group = "com.javazilla.mods"
}

repositories {
	maven {
            url = uri("https://maven.fabricmc.net/")
        }
}

dependencies {
    minecraft ("com.mojang:minecraft:1.16.5")
    mappings ("net.fabricmc:yarn:1.16.5+build.5:v2")
    modImplementation ("net.fabricmc:fabric-loader:0.11.3")
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
