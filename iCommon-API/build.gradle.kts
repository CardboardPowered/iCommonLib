import net.fabricmc.loom.task.RemapJarTask

plugins {
    id ("fabric-loom")
    id ("java-library")
    id ("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

base {
    archivesName = "iCommon"
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
	// 1.18.2
    minecraft("com.mojang:minecraft:1.18.2") 
    mappings("net.fabricmc:yarn:1.18.2+build.2:v2")
    modImplementation("net.fabricmc:fabric-loader:" + project.property("loader_version"))
	
	setOf(
		"fabric-api-base",
		"fabric-command-api-v1",
		"fabric-lifecycle-events-v1",
		"fabric-networking-api-v1"
	).forEach {
		// Add each module as a dependency
		modImplementation(fabricApi.module(it, "0.77.0+1.18.2"))
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