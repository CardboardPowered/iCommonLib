import net.fabricmc.loom.task.RemapJarTask

plugins {
    id ("fabric-loom") version "1.15-SNAPSHOT"
    id ("java-library")
    id ("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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
    minecraft("com.mojang:minecraft:1.21.11") 
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:" + project.property("loader_version"))
	
	setOf(
		"fabric-api-base",
		"fabric-lifecycle-events-v1",
		"fabric-networking-api-v1"
	).forEach {
		// Add each module as a dependency
		modImplementation(fabricApi.module(it, "0.138.3+1.21.11"))
	}
}

tasks.getByName<ProcessResources>("processResources") {
duplicatesStrategy = DuplicatesStrategy.INCLUDE
    filesMatching("fabric.mod.json") {
        if(null != System.getenv("BUILD_NUMBER")){
			expand(mutableMapOf("version" to System.getenv("BUILD_NUMBER").toString()))
		} else {
			expand(mutableMapOf("version" to "dev"))
		}
    }
}