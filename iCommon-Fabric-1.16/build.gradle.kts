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
    archivesBaseName = "iCommon"
    version = "1.16.5"
    group = "com.javazilla.mods"
}


dependencies {
    minecraft ("com.mojang:minecraft:1.16.5")
    mappings ("net.fabricmc:yarn:1.16.5+build.9:v2")
    modImplementation ("net.fabricmc:fabric-loader:0.11.3")
}

sourceSets {
    main {
        java {
            srcDir("src/main/java")
            srcDir("${rootProject.projectDir}/iCommon-API/src/main/java")
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