plugins {
    id("project.publishing-conventions")
}

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    api(project(":commons-validate"))
    compileOnly(libs.spigot)
}