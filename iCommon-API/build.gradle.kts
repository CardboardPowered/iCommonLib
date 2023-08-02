import net.fabricmc.loom.task.RemapJarTask

plugins {
    id ("fabric-loom") version "1.3-SNAPSHOT"
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
    minecraft ("com.mojang:minecraft:1.17.1")
    mappings ("net.fabricmc:yarn:1.17.1+build.65:v2")
    modImplementation ("net.fabricmc:fabric-loader:0.14.9")
    //modImplementation "net.fabricmc.fabric-api:fabric-api:0.28.5+1.15"
	
	setOf(
		"fabric-api-base",
		"fabric-command-api-v1",
		"fabric-lifecycle-events-v1",
		"fabric-networking-api-v1"
	).forEach {
		// Add each module as a dependency
		modImplementation(fabricApi.module(it, "0.46.1+1.17"))
	}
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