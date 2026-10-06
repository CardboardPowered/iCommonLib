import net.fabricmc.loom.task.RemapJarTask

plugins {
    id ("net.fabricmc.fabric-loom-remap") version "1.15-SNAPSHOT"
    id ("maven-publish")
	id ("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

// Preprocess
extensions.extraProperties["targetVersion"] = "mc201"
extensions.extraProperties["inputSourceDir"] = "${rootProject.projectDir}/iCommon-API/src/main/java"
// extensions.extraProperties["excludedFiles"] =
    //listOf("java/me/isaiah/multiworld/command/GameruleCommand.java")
    //      java/me/isaiah/multiworld/command/GameruleCommand.java


val createPreprocessor = rootProject.extra["createPreprocessor"] as groovy.lang.Closure<*>
createPreprocessor.call(project)


base {
    archivesName = "iCommon-Fabric"
    version = "1.20.1"
    group = "com.javazilla.mods"
}

// Grab Mojang Mappings with Identifier instead of ResourceLocation
repositories {
    mavenCentral()  
    maven {         
        url = uri("https://pisaiah.com/maven-repo")
    }
}

dependencies {
	// 1.20.1
    minecraft("com.mojang:minecraft:1.20.1") 
    // mappings("me.isaiah:mojmap:1.20.1")
	// mappings("net.fabricmc:yarn:1.20.1+build.1:v2")
	
	mappings(loom.layered {
        mappings(file("mojmap-1.20.1.jar"))
   })
	
    modImplementation("net.fabricmc:fabric-loader:" + project.property("loader_version"))
	
	annotationProcessor("com.pkware.jabel:jabel-javac-plugin:1.0.1-1")
    compileOnly("com.pkware.jabel:jabel-javac-plugin:1.0.1-1")
	
	setOf(
		"fabric-api-base",
		"fabric-lifecycle-events-v1",
		"fabric-networking-api-v1"
	).forEach {
		// Add each module as a dependency
		modImplementation(fabricApi.module(it, "0.92.11+1.20.1"))
	}
}

// 1.20.5 now requires JDK 21
tasks.withType<JavaCompile>().configureEach {
    sourceCompatibility = JavaVersion.VERSION_21.toString() // for the IDE support
    options.release.set(16)

    javaCompiler.set(
        javaToolchains.compilerFor {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    )
}

sourceSets {
    main {
        java {
           // srcDir("${rootProject.projectDir}/iCommon-API/src/main/java/com")
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
        if(null != System.getenv("BUILD_NUMBER")){
			expand(mutableMapOf("version" to System.getenv("BUILD_NUMBER").toString()))
		} else {
			expand(mutableMapOf("version" to "dev"))
		}
    }
}

val remapJar = tasks.getByName<RemapJarTask>("remapJar")

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.group.toString()
            artifactId = project.name.lowercase()
            version = project.version.toString()
            
            pom {
                name.set(project.name.lowercase())
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