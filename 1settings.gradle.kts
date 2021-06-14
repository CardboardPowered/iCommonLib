rootProject.name = "iCommon"
include("api")
include("1.14.4")
include("1.15.2")
include("1.16.5")

pluginManagement {
    repositories {
        maven {
            name("Fabric")
            url("https://maven.fabricmc.net/")
        }
        gradlePluginPortal()
    }
}
