import net.fabricmc.loom.task.RemapJarTask

plugins {
    id ("net.fabricmc.fabric-loom") version "1.15-SNAPSHOT"
    id ("maven-publish")
	id ("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

base {
    archivesName = "iCommon-Fabric"
    version = "26.2"
    group = "com.javazilla.mods"
}

repositories {
	maven { url = uri("https://maven.neoforged.net/releases") }
}

dependencies {
	// annotationProcessor("com.pkware.jabel:jabel-javac-plugin:1.0.1-1")
    // compileOnly("com.pkware.jabel:jabel-javac-plugin:1.0.1-1")

    implementation(project(mapOf("path" to ":iCommon-API")))
    implementation(project(mapOf("path" to ":iCommon-API")))

	// 26.2
	minecraft("com.mojang:minecraft:26.2")
    implementation("net.fabricmc:fabric-loader:" + project.property("loader_version"))
	
	setOf(
		"fabric-api-base",
		// "fabric-command-api-v1",
		"fabric-lifecycle-events-v1",
		"fabric-networking-api-v1"
	).forEach {
		// Add each module as a dependency
		implementation(fabricApi.module(it, "0.161.0+26.2"))
	}
	
	// (Experimental) Neoforge Support
	implementation("net.neoforged:neoforge:26.1.0.1-beta") {
        exclude(group = "org.ow2.asm")
    }
    implementation("net.neoforged.fancymodloader:loader:11.0.3") {
        exclude(group = "org.ow2.asm")
    }
}

sourceSets {
    main {
        java {
            srcDir("src/main/java")
        }
        resources {
            srcDir("${rootProject.projectDir}/iCommon-API/src/main/resources")
        }
    }
}

// 1.20.5 now requires JDK 21
tasks.withType<JavaCompile>().configureEach {
    sourceCompatibility = JavaVersion.VERSION_25.toString() // for the IDE support
    options.release.set(25)

    javaCompiler.set(
        javaToolchains.compilerFor {
            languageVersion.set(JavaLanguageVersion.of(25))
        }
    )
}

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

// val remapJar = tasks.getByName<RemapJarTask>("jar")

/*
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

            artifact(jar)
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
*/