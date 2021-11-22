import net.fabricmc.loom.task.RemapJarTask

plugins {
    id ("fabric-loom") version "0.10-SNAPSHOT"
    id ("maven-publish")
	id ("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

base {
    archivesBaseName = "iCommon-Fabric"
    version = "1.18"
    group = "com.javazilla.mods"
}


dependencies {
    annotationProcessor("com.github.bsideup.jabel:jabel-javac-plugin:0.4.1")

    //minecraft ("com.mojang:minecraft:1.18-pre5")
    //mappings ("net.fabricmc:yarn:1.18-pre5+build.4:v2")
    //modImplementation ("net.fabricmc:fabric-loader:0.12.5")
    
    // Use pre1 as Jenkins currently does not support Java 17
    //
    minecraft("com.mojang:minecraft:1.18-pre1")
    mappings("net.fabricmc:yarn:1.18-pre1+build.14:v2")
    modImplementation("net.fabricmc:fabric-loader:0.12.5")

    //minecraft ("com.mojang:minecraft:21w37a")
    //mappings ("net.fabricmc:yarn:21w37a+build.5:v2")
    //modImplementation ("net.fabricmc:fabric-loader:0.11.7")
    annotationProcessor("com.github.bsideup.jabel:jabel-javac-plugin:0.4.1")
}


sourceSets {
    main {
        java {
            srcDir("${rootProject.projectDir}/iCommon-API/src/main/java/com")
            srcDir("${rootProject.projectDir}/iCommon-Fabric-1.17/src/main/java")

            // Needs fixing for 1.18:
            exclude("**/MixinWorld.java")
            
            srcDir("src/main/java")
        }
        resources {
            srcDir("${rootProject.projectDir}/iCommon-API/src/main/resources")
        }
    }
}

/*configure([tasks.compileJava]) {
    sourceCompatibility = 16 // for the IDE support
    options.release = 8

    javaCompiler = javaToolchains.compilerFor {
        languageVersion = JavaLanguageVersion.of(16)
    }
}*/

//tasks.getByName("compileJava") {
    //sourceCompatibility = 16
    //options.release = 8
//}


tasks.withType<Jar> { duplicatesStrategy = DuplicatesStrategy.INHERIT }

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